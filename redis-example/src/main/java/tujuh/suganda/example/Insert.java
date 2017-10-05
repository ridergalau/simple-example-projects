package tujuh.suganda.example;

import redis.clients.jedis.Jedis;
import tujuh.suganda.config.RedisConfig;

public class Insert {
	static RedisConfig redisConf;

	public static void main(String[] args) {
		Jedis redis = redisConf.con();
		// Default db is index 0
		// for defind index db
		redis.select(Integer.parseInt("8"));
		
		String key = "test";
		try {
			redis.set(key, "the value");
			System.out.println("Stored string in redis:: " + redis.get(key));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("=========FINISH==========");
	}
}
