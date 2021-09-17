package com.library.eroge.erogelib.config;

import com.library.eroge.erogelib.config.exceptionCatchUtils.interceptor.ResponseResultInterceptor;
import com.library.eroge.erogelib.config.exceptionCatchUtils.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@SpringBootConfiguration
public class WebProxyConfiger implements WebMvcConfigurer {

    private TokenInterceptor tokenInterceptor;

    @Value("${CROS_MAPPING}")
    private String mapping;

    @Value("${CROS_ALLOWED_ORIGINS}")
    private String[] allowedOrigins;

    @Value("${CROS_ALLOWED_METHOD}")
    private String[] allowedMethods;

    //构造方法
    public WebProxyConfiger(TokenInterceptor tokenInterceptor){
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(mapping)
                .allowedOrigins(allowedOrigins)
                .allowedMethods(allowedMethods)
                .allowCredentials(true);
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer){
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
        configurer.setDefaultTimeout(30000);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ResponseResultInterceptor interceptor = new ResponseResultInterceptor();
        // 异常拦截
        registry.addInterceptor(interceptor);

        // 登录TOKEN拦截
        List<String> excludePath = new ArrayList<>();
        //排除拦截，除了注册登录(此时还没token)，其他都拦截
        excludePath.add("/access/register");   //注册
        excludePath.add("/tmUser/insertTmUser");   //注册
        excludePath.add("/access/userLogin");    //登录
        excludePath.add("/error");    //
        excludePath.add("/static/**");  //静态资源
        excludePath.add("/assets/**");  //静态资源

        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
