package com.wyp.module.service;


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
	 void addUser(Customer customer);
}