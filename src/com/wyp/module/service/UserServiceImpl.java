package com.wyp.module.service;

import com.wyp.module.dao.UserDao;
import com.wyp.module.pojo.Customer;
import com.wyp.module.utils.CipherUtil;

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

	@Override
	public Customer findCustomer4Login(Customer customer) {
		//get dbcustomer by name,and get password salt
		Customer dbCustomer = userDao.get(customer);
		if (null == dbCustomer) {
			return null;
		}
		
		String encryPwd = CipherUtil.generatePassword(customer.getPassword(), dbCustomer.getSalt());
		if (null != encryPwd && encryPwd.equals(dbCustomer.getPassword())) {
			dbCustomer.setPassword("");
			dbCustomer.setSalt("");
			return dbCustomer;
		}
		return null;
	}
}
