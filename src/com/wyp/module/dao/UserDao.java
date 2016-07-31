package com.wyp.module.dao;

import com.wyp.module.pojo.Customer;
import org.springframework.stereotype.Repository;

/**
 * UserDao.
 * @author earthlyfish
 * @since v1.0
 * @see UserDao
 */
@Repository("userDao")
public interface UserDao extends CrudDao<Customer>{


}
