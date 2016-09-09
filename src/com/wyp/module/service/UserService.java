package com.wyp.module.service;

import com.wyp.module.common.UserType;
import com.wyp.module.pojo.Customer;

import java.util.List;

public interface UserService {

	/**
	 *
	 * @return
	 */
	List<Customer> showList();

	/**
	 *
	 * @param customer
	 */
	int addUser(Customer customer);
	
	/**
	 * user register
	 * @param customer
	 */
	UserType registerUser(Customer customer);

	/**
	 * 登录认证.
	 * 
	 * @param customer
	 * @return
	 */
	Customer findCustomer4Login(Customer customer);
}