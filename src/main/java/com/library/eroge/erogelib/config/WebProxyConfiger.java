package com.library.eroge.erogelib.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class WebProxyConfiger implements WebMvcConfigurer {
    @Value("${CROS_MAPPING}")
    private String mapping;

    @Value("${CROS_ALLOWED_ORIGINS}")
    private String[] allowedOrigins;

    @Value("${CROS_ALLOWED_METHOD}")
    private String[] allowedMethods;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(mapping).allowedOrigins(allowedOrigins).allowedMethods(allowedMethods).allowCredentials(true);
    }
}
