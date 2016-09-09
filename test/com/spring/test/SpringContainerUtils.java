package com.spring.test;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.wyp.module.common.MyBeanPostProcessor;

public class SpringContainerUtils implements ApplicationContextAware {

	private ApplicationContext act;

	private static ServletContext servletContext;

	/**
	 * 由于BeanFactory没有ApplicationContext的Automatic BeanPostProcessor
	 * registration功能， 需要手动注册.
	 */
	public void registerBeanPostProcessor4BeanFactory() {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		// populate the factory with bean definitions
		// now register any needed BeanPostProcessor instances
		MyBeanPostProcessor postProcessor = new MyBeanPostProcessor();
		factory.addBeanPostProcessor(postProcessor);
	}

	public static void testContext() {
		WebApplicationContext wact =ContextLoader.getCurrentWebApplicationContext();
		servletContext = wact.getServletContext();
		
		// 获取项目根
		System.out.println(servletContext.getContextPath());
		//wact.getBean("");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.act = applicationContext;
	}
	
	public static void main(String[] args) {
		System.out.println(ClassLoader.getSystemClassLoader());
	}
}
