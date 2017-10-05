package tujuh.suganda.example;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;
import tujuh.suganda.config.RedisConfig;

public class GetAllKey {
	static RedisConfig redisConf;

	public static void main(String[] args) {
		Jedis redis = redisConf.con();
		redis.select(8);
		Set<String> list1 = redis.keys("*");
		for (Iterator iterator = list1.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}

	}
}
