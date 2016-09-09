package com.wyp.module.common;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PostConstructAndPreDestroy {

	static {
		System.out.println("static");
	}

	public PostConstructAndPreDestroy() {
		super();
		System.out.println("constructor");
	}

	{
		System.out.println("class object");
	}
	
	@PostConstruct
	public void postConstruct() {
		System.out.println("postConstruct");
	}

	public void init() {
		System.out.println("init");
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("preDestroy");
	}

	public void destroy() {
		System.out.println("destroy");
	}

}
