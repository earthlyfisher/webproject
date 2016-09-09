package com.wyp.module.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * BeanFactoryPostProcessor是在spring容器加载了bean的定义文件之后，在bean实例化之前执行的.
 * 接口方法的入参是ConfigurrableListableBeanFactory，使用该参数，可以获取到相关bean的定义信息
 * 
 * @author earthlyfisher
 *
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("调用MyBeanFactoryPostProcessor的postProcessBeanFactory");
		BeanDefinition bd = beanFactory.getBeanDefinition("multipartResolver");
		System.out.println("属性值============" + bd.getPropertyValues().toString());
		MutablePropertyValues pv = bd.getPropertyValues();
		if (pv.contains("remark")) {
			pv.addPropertyValue("remark", "把备注信息修改一下");
		}
		bd.setScope(BeanDefinition.SCOPE_PROTOTYPE);
	}

}
