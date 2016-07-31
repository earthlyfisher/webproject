package com.wyp.module.service;

import com.wyp.module.dao.UserDao;
import com.wyp.module.pojo.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
    /**
     * @return
     */
    public List<Customer> showList() {
        return null;
    }

    /**
     * @param customer
     */
    public void addUser(Customer customer) {

        userDao.insert(customer);
    }


}
