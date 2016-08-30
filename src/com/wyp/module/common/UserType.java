package com.wyp.module.common;

public enum UserType {
	HAS_ACCOUNT("1"),//有用户
	ACCOUNT_INFO_ERROR("2"),//用户信息错误
	ACCOUNT_LOCKED("3"),//用户锁定
	ACCOUNT_REGISTER_SUCCESS("4"),//用户注册成功
	ACCOUNT_ERROR("5");//用户注册成功

	String value;

	private UserType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
