package com.library.eroge.erogelib.config.exceptionCatchUtils;


import com.library.eroge.erogelib.config.exceptionCatchUtils.BaseError.CommonEnumBaseError;
import com.library.eroge.erogelib.config.exceptionCatchUtils.annotation.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler implements ResponseBodyAdvice<Object> {

    // 标记名称
    public static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 判断是否要执行 beforeBodyWrite 方法，true为执行，false不执行，有注解标记的时候处理返回值
    @Override
    public boolean supports(MethodParameter arg0, Class<? extends HttpMessageConverter<?>> arg1) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        // 判断请求是否有包装标记
        ResponseResult responseResultAnn = (ResponseResult) request.getAttribute(RESPONSE_RESULT_ANN);
        return responseResultAnn != null;
    }

    // 对返回值做包装处理
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter arg1, MediaType arg2,
                                  Class<? extends HttpMessageConverter<?>> arg3, ServerHttpRequest arg4,
                                  ServerHttpResponse arg5) {
        if (body instanceof ResultBody) {
            return (ResultBody) body;
        } else if (body instanceof String) {
            return body;
        }
        return ResultBody.success(body);
    }

    /* *
     * 处理自定义的业务异常
     */
    @ExceptionHandler(value = BizExceptionHandler.class)
    @ResponseBody
    public  ResultBody bizExceptionHandler(HttpServletRequest req , HttpServletResponse response, BizExceptionHandler e){
        logger.error("发生业务异常！原因是：{}",e.getErrorMsg());
        if(e.getErrorCode() == null){
            e.setErrorCode("500");
        }
        response.setStatus(Integer.parseInt(e.getErrorCode()));
        return ResultBody.error(e.getErrorCode(),e.getErrorMsg());
    }

    /* *
     * 处理空指针的异常
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req ,HttpServletResponse response, NullPointerException e){
        logger.error("发生空指针异常！原因是:",e);
        response.setStatus(Integer.parseInt(CommonEnumBaseError.BODY_NOT_MATCH.getResultCode()));
        return ResultBody.error(CommonEnumBaseError.BODY_NOT_MATCH);
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req,HttpServletResponse response, Exception e){
        logger.error("未知异常！原因是:",e);
        response.setStatus(Integer.parseInt(CommonEnumBaseError.INTERNAL_SERVER_ERROR.getResultCode()));
        return ResultBody.error(CommonEnumBaseError.INTERNAL_SERVER_ERROR);
    }

}
