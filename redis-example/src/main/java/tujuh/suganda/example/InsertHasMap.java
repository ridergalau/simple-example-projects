package tujuh.suganda.example;

import java.util.Map;

import redis.clients.jedis.Jedis;
import tujuh.suganda.config.RedisConfig;

public class InsertHasMap {
	static RedisConfig redisConf;

	public static void main(String[] args) {
		Jedis redis = redisConf.con();
		// Default db is index 0
		// for defind index db
		redis.select(Integer.parseInt("8"));
		String key = "person";
		try {
			redis.hset(key, "fullname", "Suganda7");
			redis.hset(key, "addres", "Ciamis");

			// get map value
			String name = redis.hget(key, "fullname");
			System.out.println("The Name : " + name);

			Map<String, String> field = redis.hgetAll(key);
			System.out.println(field);
			// String job = fields.get("fullname");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
