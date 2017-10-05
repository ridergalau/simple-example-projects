package tujuh.suganda.config;

import redis.clients.jedis.Jedis;

public class RedisConfig {
	static String REDISHOST = "localhost";
	static int REDISPORT = 6379;

	public static Jedis con() {
		Jedis jedis = new Jedis(REDISHOST, REDISPORT);
		return jedis;
	}

	public static Jedis con(String host, int port) {
		Jedis jedis = new Jedis(host, port);
		return jedis;
	}

}
