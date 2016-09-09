package com.wyp.module.cache;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.redis.RedisSystemException;
import org.springframework.stereotype.Component;

import com.wyp.module.pojo.License;

@Component("licenseCacheDao")
public class LicenseCacheDao extends RedisBaseDao<Serializable, Serializable> implements CacheCrudDao<License> {

	@Override
	public License get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public License get(License entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findAllList() {
		return null;
	}

	@Override
	public int insert(License entity) {
		redisTemplate.boundValueOps("123").set("TEST_XXX");
		redisTemplate.boundValueOps("age").set("11");
		// 异常代码
		for (int i = 0; i < 5; i++) {
			if (i == 3) {
				throw new RedisSystemException("dsd", new Exception("myself exception....."));
			}
		}
		return 0;
	}

	@Override
	public int update(License entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(License entity) {
		// TODO Auto-generated method stub
		return 0;
	}

}
