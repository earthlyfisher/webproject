package test;

import java.util.List;

import redis.clients.jedis.Jedis;

public class StaticTest {
	public static void main(String[] args) {
		//Connecting to Redis server on localhost
	      Jedis jedis = new Jedis("localhost");
	      System.out.println("Connection to server sucessfully");
	      //set the data in redis string
	      jedis.set("tutorial-name", "Redis tutorial");
	     // Get the stored data and print it
	     System.out.println("Stored string in redis:: "+ jedis.get("tutorial-name"));
	     jedis.lpush("list", new String[]{"1","2"});
	     jedis.lpush("list".getBytes(), "3".getBytes());
	     List<String> list=jedis.lrange("list", 0, 5);
	     for(String str:list){
	    	 System.out.println(str);
	     }
	     System.out.println("---------------------");
	}
}