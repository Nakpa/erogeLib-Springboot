package com.library.eroge.erogelib.config.exceptionCatchUtils.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.library.eroge.erogelib.config.exceptionCatchUtils.BaseError.CommonEnumBaseError;
import com.library.eroge.erogelib.config.exceptionCatchUtils.BizExceptionHandler;
import com.library.eroge.erogelib.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception{
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        List<String> tokenAcceptPath = new ArrayList<>();
        tokenAcceptPath.add("/build-erogeLib/access/userLogin");
        tokenAcceptPath.add("/build-erogeLib/access/register");
        tokenAcceptPath.add("/access/userLogin");
        tokenAcceptPath.add("/access/userLogin");
        if(!tokenAcceptPath.contains(request.getServletPath())){
            if(request.getServletPath().equals("/error")){
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return true;
            }
        } else {
            return true;
        }
        response.setCharacterEncoding("utf-8");
//        log.info("  >>>>>>>>>>>>>  token {}" , request.getHeader("token"));
        log.info("  >>>>>>>>>>>>>  token {}" , request.getHeader("Authorization"));
        String token = request.getHeader("Authorization");
        log.info(">>>>>>>>>>>>>  当前请求uri {}" , request.getServletPath());
        if(token != null){
            boolean result = TokenUtil.verify(token);
            if(result){
                log.info(">>>>>>>>>>>>>  通过拦截器");
                return true;
            }
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
//        try{
            JSONObject json = new JSONObject();
            log.info(">>>>>>>>>>>>> TOKEN认证失败，未通过拦截器");
//            response.getWriter().append(json.toJSONString());
            throw new BizExceptionHandler(CommonEnumBaseError.ERROR_TOEKN_VERIFY.getResultCode()
                    , CommonEnumBaseError.ERROR_TOEKN_VERIFY.getResultMsg());
//        }catch (Exception e){
//            log.error("TokenUtil/verify" , e);
//            response.setStatus(403);
//            response.sendError(403);
//            return false;
//        }
    }


}
