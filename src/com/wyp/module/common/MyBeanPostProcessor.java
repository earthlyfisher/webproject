package com.wyp.module.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {  
	
    public MyBeanPostProcessor() {  
       super();  
       System.out.println("这是BeanPostProcessor实现类构造器！！");          
    }  
  
    @Override  
    public Object postProcessAfterInitialization(Object bean, String propArg)  
            throws BeansException {  
        System.out.println("bean处理器：bean创建之后....当前实例类："+bean.getClass().getName()+";当前属性参数："+propArg);  
        return bean;  
    }  
  
    @Override  
    public Object postProcessBeforeInitialization(Object bean, String propArg)  
            throws BeansException {  
        System.out.println("bean处理器：bean创建之前....当前实例类："+bean.getClass().getName()+";当前属性参数："+propArg);  
        return bean;  
    }  
}  
