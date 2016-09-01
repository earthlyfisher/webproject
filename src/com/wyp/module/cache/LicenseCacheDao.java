package com.wyp.module.cache;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wyp.module.pojo.License;

@Repository
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
		long count=redisTemplate.opsForList().leftPush(entity.getName(), entity);
		return (int) count;
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
