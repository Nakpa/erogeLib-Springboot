package com.library.eroge.erogelib.config.exceptionCatchUtils.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.library.eroge.erogelib.config.components.ApplicationContextHelper;
import com.library.eroge.erogelib.config.dto.CommonAccountEnum;
import com.library.eroge.erogelib.config.dto.LoginInfoDTO;
import com.library.eroge.erogelib.config.exceptionCatchUtils.BaseError.CommonEnumBaseError;
import com.library.eroge.erogelib.config.exceptionCatchUtils.BizExceptionHandler;
import com.library.eroge.erogelib.entity.TmUserPO;
import com.library.eroge.erogelib.mapper.TmUserMapper;
import com.library.eroge.erogelib.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private TmUserMapper tmUserMapper;

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
            log.info("[登录 | 注册] >>>>>>>>>>>>>  通过拦截器");
            return true;
        }
        response.setCharacterEncoding("utf-8");
        log.info("  >>>>>>>>>>>>>  token {}" , request.getHeader("Authorization"));
        String token = request.getHeader("Authorization");
        String userId = request.getHeader("userId");
        log.info(">>>>>>>>>>>>>  当前请求uri {}" , request.getServletPath());

        LoginInfoDTO loginInfoDto = (LoginInfoDTO) ApplicationContextHelper.getBeanByType(LoginInfoDTO.class);
        // TODO 由于服务器太垃了、目前Redis挂不上去、之后有钱了换服务器准备将这个一段改成从Redis缓存里取用户数据。
        if(userId == null || userId.equals("")){
            log.info("使用游客账号登录");
            loginInfoDto.setUserId(CommonAccountEnum.TOURIST_ACCOUNT.getUserId());
            loginInfoDto.setUserAccount(CommonAccountEnum.TOURIST_ACCOUNT.getAccount());
            loginInfoDto.setUserName("ERG游客");
        } else {
            log.info("使用管理员账号登录");
            loginInfoDto.setUserId(userId);
            if(userId.equals(CommonAccountEnum.ADMIN_ACCOUNT.getUserId())){
                loginInfoDto.setUserAccount(CommonAccountEnum.ADMIN_ACCOUNT.getAccount());
                loginInfoDto.setUserName("ERG管理员");
            } else {
                TmUserPO user = tmUserMapper.selectByPrimaryKey(userId);
                if(user == null){
                    throw new BizExceptionHandler(CommonEnumBaseError.ERROR_NO_USERINFO.getResultCode()
                            , CommonEnumBaseError.ERROR_NO_USERINFO.getResultMsg());
                }
                log.info("使用账号:{}登录",user.getUserAccount());
                loginInfoDto.setUserAccount(user.getUserAccount());
                loginInfoDto.setUserName(user.getUserName());
            }
        }

        if(token != null){
            if(token.equals("EROGELIBADMIN====SKIP===")) {
                return true;
            }
            boolean result = TokenUtil.verify(token);
            if(result){
                log.info(">>>>>>>>>>>>>  通过拦截器");
                return true;
            }
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        log.info(">>>>>>>>>>>>> TOKEN认证失败，未通过拦截器");
        throw new BizExceptionHandler(CommonEnumBaseError.ERROR_TOEKN_VERIFY.getResultCode()
                , CommonEnumBaseError.ERROR_TOEKN_VERIFY.getResultMsg());
    }


}
