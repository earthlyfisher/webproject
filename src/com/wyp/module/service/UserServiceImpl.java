package com.wyp.module.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyp.module.cache.LicenseCacheDao;
import com.wyp.module.cache.UserCacheDao;
import com.wyp.module.common.UserType;
import com.wyp.module.controller.UserController;
import com.wyp.module.dao.UserDao;
import com.wyp.module.pojo.Customer;
import com.wyp.module.utils.CipherUtil;

@Service("userService")
public class UserServiceImpl implements UserService {

	private Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserCacheDao userCacheDao;

	@Autowired
	private LicenseCacheDao licenseCacheDao;

	/**
	 * @return
	 */
	@Override
	public List<Customer> showList() {
		List<Object> list=userCacheDao.findAllList();
		for(Object obj:list){
			userCacheDao.delete((Customer)obj);
		}
		
		System.out.println("before add .......................................");
		Customer customer = new Customer();
		customer.setId(1);
		customer.setName("Gavin");
		customer.setAge(12);
		userCacheDao.insert(customer);
		customer.setId(2);
		customer.setName("wyp");
		userCacheDao.insert(customer);
		System.out.println("after add.........................");
		
		System.out.println(userCacheDao.get("Gavin"));
		
		System.out.println(userCacheDao.get("wyp"));

		System.out.println("start update.........................");
		customer.setId(3);
		userCacheDao.update(customer);
		
		System.out.println("after update.........................");
		System.out.println(userCacheDao.get("wyp"));
		
		list=userCacheDao.findAllList();
		for(Object obj:list){
			System.out.println(obj);
		}
		
		System.out.println("start delete.........................");
		userCacheDao.delete(customer);
		
		System.out.println("after delete.........................");
		list=userCacheDao.findAllList();
		for(Object obj:list){
			System.out.println(obj);
		}
		return null;
	}

	/**
	 * @param customer
	 */
	@Override
	public void addUser(Customer customer) {

		userDao.insert(customer);
	}

	/**
	 * @param customer
	 */
	@Override
	public UserType registerUser(Customer customer) {
		// 判断数据库有无此用户名记录
		Customer dbCustomer = userDao.get(customer);
		if (null != dbCustomer) {
			return UserType.HAS_ACCOUNT;
		}

		// 添加用户
		String salt = CipherUtil.randomSalt();
		customer.setSalt(salt);
		customer.setPassword(CipherUtil.generatePassword(customer.getPassword(), salt));
		int count = userDao.insert(customer);
		if (count > 0) {
			logger.info("login success!");
			return UserType.ACCOUNT_REGISTER_SUCCESS;
		}
		return UserType.ACCOUNT_ERROR;
	}

	@Override
	public Customer findCustomer4Login(Customer customer) {
		// get dbcustomer by name,and get password salt
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
