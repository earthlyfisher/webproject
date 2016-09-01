package com.wyp.module.cache;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis操作基类
 * 
 * @author earthlyfisher
 * @param <K>
 *
 */
public class RedisBaseDao<K,V> {

	@Autowired
	protected RedisTemplate<K,V> redisTemplate;
	
	@Resource(name="redisTemplate")
	protected HashOperations<K, Object, Object> hashOperations;

	
}
