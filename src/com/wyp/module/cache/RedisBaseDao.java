package com.wyp.module.cache;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis操作基类
 * 
 * @author earthlyfisher
 * @param <K>
 *
 */
public class RedisBaseDao<K, V> {

	@Autowired
	protected RedisTemplate<K, V> redisTemplate;

	@Resource(name = "redisTemplate")
	protected HashOperations<K, Object, Object> hashOperations;

	/**
	 * clear all db data.
	 */
	public void clearRedisData() {
		redisTemplate.execute(new RedisCallback<Object>() {

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushAll();
				return "ok";
			}
		});
	}
	
	/**
	 * clear current db data.
	 */
	public void clearRedisDb() {
		redisTemplate.execute(new RedisCallback<Object>() {

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushDb();
				return "ok";
			}
		});
	}
}
