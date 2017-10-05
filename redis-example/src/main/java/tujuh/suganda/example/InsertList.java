package tujuh.suganda.example;
import java.util.List;

import redis.clients.jedis.Jedis;
import tujuh.suganda.config.RedisConfig;

public class InsertList {
	static RedisConfig redisConf;

	public static void main(String[] args) {
		Jedis redis = redisConf.con();
		// Default db is index 0
		// for defind index db
		redis.select(Integer.parseInt("8"));

		String key = "booklist";
		try {
			// Insert
			 redis.lpush(key, "Matematika");
			 redis.lpush(key, "Calculus");
			 redis.lpush(key, "Database");
			 redis.lpush(key, "Calculus1");
			 redis.lpush(key, "Database2");
			 redis.lpush(key, "Database2");
			// Query list
			List<String> list = redis.lrange(key, 0, 5);
			for (int i = 0; i < list.size(); i++) {
				System.out.println("index: " + i + " of " + key + ",  Value" + list.get(i));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("=========FINISH==========");
	}
}