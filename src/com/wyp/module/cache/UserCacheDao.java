package com.wyp.module.cache;

import java.util.List;

import org.springframework.data.redis.connection.SortParameters.Order;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.query.SortQueryBuilder;
import org.springframework.stereotype.Component;

import com.wyp.module.pojo.Customer;

@Component("userCacheDao")
public class UserCacheDao extends RedisBaseDao<String, Customer> implements CacheCrudDao<Customer> {

	private static final String KEY = Customer.class.getName();

	@Override
	public Customer get(String id) {
		return (Customer) hashOperations.get(KEY, id);
	}

	@Override
	public Customer get(Customer entity) {
		ListOperations<String, Customer> list = redisTemplate.opsForList();
		List<Customer> lists = list.range(KEY, 0, -1);
		// 此处由于配有默认的KEYSerializer,所以不用做序列化处理
		return list.leftPop(KEY);
	}

	@Override
	public List<Object> findAllList() {
		//sort,不能使用user作为key值
		List<Object> lst=hashOperations.multiGet(KEY, hashOperations.keys(KEY));
		Customer[] arrays=new Customer[lst.size()];
		for(int i=0;i<lst.size();i++){
			arrays[i]=(Customer) lst.get(i);
		}
        SetOperations<String, Customer>  setOper= redisTemplate.opsForSet();
        String setKey = "not use user for key"; 
        setOper.add(setKey,arrays);
        SortQueryBuilder<String> builder = SortQueryBuilder.sort(setKey);  
        builder.alphabetical(true);//对字符串使用“字典顺序”  
        builder.order(Order.DESC);//倒序  
        builder.limit(0, 2);  
        //builder.limit(new Range(0, 2));  
        List<Customer> results = redisTemplate.sort(builder.build());  
        for(Customer item : results){  
            System.out.println(item);  
        }  
		return hashOperations.multiGet(KEY, hashOperations.keys(KEY));
	}

	@Override
	public int insert(final Customer entity) {
		// 通过list队列存储
		// Queue LILO
		//long count = redisTemplate.opsForList().rightPush(KEY, entity);
		/*
		long count = redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				JdkSerializationRedisSerializer valueSerializer = (JdkSerializationRedisSerializer) redisTemplate
						.getValueSerializer();
				return connection.lPush(redisTemplate.getStringSerializer().serialize(KEY),
						valueSerializer.serialize(entity));
			}
		});
		 */

		// 通过hashmap存储
		if (hashOperations.hasKey(KEY, entity.getName())) {
			delete(entity);
		}
		hashOperations.put(KEY, entity.getName(), entity);
		return 0;
	}

	@Override
	public int update(Customer entity) {
		// 通过hashmap存储
		if (hashOperations.hasKey(KEY, entity.getName())) {
			delete(entity);
		}
		hashOperations.put(KEY, entity.getName(), entity);
		return 0;
	}

	@Override
	public int delete(Customer entity) {
		long count = hashOperations.delete(KEY, entity.getName());
		return (int) count;
	}

}
