/**
 * ApplicationContextHelper.java
 * Created at 2020/3/13
 * Created by DELL
 * Copyright (C) 2020 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.library.eroge.erogelib.config.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ClassName: ApplicationContextHelper
 * Description:
 * Author: DELL
 * Date: 2020/3/13
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {
    /**
     * 定义日志接口
     */
    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextHelper.class);

    /**
     * Spring应用上下文环境
     */
    private static List<ApplicationContext> applicationContextList = new ArrayList<>();

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContextList.get(0);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        logger.info("执行了Bean 的初始化");
        applicationContextList.add(applicationContext);
    }

    /**
     * 获取对象
     *
     * @param name
     * @return Object 一个以所给名字注册的bean的实例
     * @throws BeansException
     */
    public static Object getBeanByName(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 获取对象
     *
     * @param beanType
     * @return Object 一个以所给名字注册的bean的实例
     * @throws BeansException
     */
    public static <T> T getBeanByType(Class<T> beanType) {
        try {
            return getApplicationContext().getBean(beanType);
        } catch (BeansException e) {
            logger.error("当前线程不存在此对象或不存在登录对象，请检查");
        }
        return null;
    }

    /**
     * 获取类型为requiredType的对象
     * 如果bean不能被类型转换，相应的异常将会被抛出（BeanNotOfRequiredTypeException）
     *
     * @param name     bean注册名
     * @param beanType 返回对象类型
     * @return Object 返回requiredType类型对象
     * @throws BeansException
     */
    public static <T> T getBeanByName(String name, Class<T> beanType) {
        return getApplicationContext().getBean(name, beanType);
    }


    /**
     * @param name
     * @return Class 注册对象的类型
     * @throws NoSuchBeanDefinitionException
     */
    public static Class getType(String name) {
        return getApplicationContext().getType(name);
    }

    /**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
     *
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    public static String[] getAliases(String name) {
        return getApplicationContext().getAliases(name);
    }

    /**
     * 使用jackjson把Bean转Map
     *
     * @param obj
     * @return
     * @throws IOException
     */
    public static Map<String, Object> beanToMap(Object obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper(); //转换器
        // 对象--json
        String json = mapper.writeValueAsString(obj); //将对象转换成json
        // json--map
        return mapper.readValue(json, Map.class);
    }
}
