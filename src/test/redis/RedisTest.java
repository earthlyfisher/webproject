package test.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisTest {

	private Jedis jedis;

	@Before
	public void setUp() {
		jedis = new Jedis("localhost", 6379);
	}

	@Test
	public void testString() {
		jedis.set("name", "wangyp");
		System.out.println(jedis.get("name"));

		jedis.append("name", "wangqingxuan");
		System.out.println(jedis.get("name"));

		jedis.del("name");
		System.out.println(jedis.get("name"));

		jedis.mset("name", "wangyp", "age", "26");
		jedis.incr("age");
		System.out.println(jedis.get("name") + "-" + jedis.get("age"));
	}
    
	@Test
	public void testMap(){
		Map<String,String> map=new HashMap<>();
		map.put("name", "Gavin");
		map.put("age", "26");
		jedis.hmset("user", map);
		
		List<String> rsMap=jedis.hmget("user", "name","age");
		System.out.println(rsMap);
		
		jedis.hdel("user", "age");
		System.out.println(jedis.hmget("user", "age"));
		System.out.println(jedis.hlen("user"));
		System.out.println(jedis.exists("user"));
		System.out.println(jedis.hkeys("user"));
		System.out.println(jedis.hvals("user"));
		
		Iterator<String> it=jedis.hkeys("user").iterator();
	    while(it.hasNext()){
	    	String key=it.next();
	    	System.out.println(key+"="+jedis.hmget("user", key));
	    }
	}
	
	@Test
	public void testList(){
		jedis.lpush("java framework", "spring","mybatis");
		jedis.lpush("java framework", "struts");
		System.out.println(jedis.lrange("java framework", 0, -1));
	   
		jedis.del("java framework");
		jedis.rpush("java framework", "spring");
		jedis.rpush("java framework", "mabatis");
		System.out.println(jedis.lrange("java framework", 0, -1));
		
		System.out.println(jedis.sort("java framework"));
		System.out.println(jedis.lrange("java framework", 0, -1));
	}
	
	@Test
	public void testSet(){
		jedis.del("user");
		jedis.sadd("user","111");
		jedis.sadd("user","222");
		jedis.sadd("user", "333");
		System.out.println(jedis.smembers("user"));
		jedis.srem("user", "111");
		System.out.println("所有成员"+jedis.smembers("user"));
		System.out.println("是否存在111"+jedis.sismember("user", "111"));
		System.out.println("成员数量"+jedis.scard("user"));
		System.out.println("随机成员"+jedis.srandmember("user"));
	}
	
	@Test
	public void testListSort(){
		jedis.del("sort".getBytes());
		jedis.rpush("sort", "1","5","3","2","3","8");
		jedis.rpush("sort", "6");
	   
		System.out.println(jedis.lrange("sort", 0, -1));
		System.out.println(jedis.sort("sort"));
		System.out.println(jedis.lrange("sort", 0, -1));
	}
	
	@Test
	public void testRedisPool(){
		Jedis jedis=null;
		jedis=RedisUtils.getJedis();
		System.out.println(jedis);
		jedis.set("key", "value");
		jedis=RedisUtils.getJedis();
		System.out.println(jedis);
		System.out.println(jedis.get("key"));
	}
}
