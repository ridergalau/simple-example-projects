package tujuh.suganda.example;

import redis.clients.jedis.Jedis;
import tujuh.suganda.config.RedisConfig;

public class TestCon {
static RedisConfig redis;
	public static void main(String[] args) {
	Jedis conf = redis.con();
	System.out.println("Ping to Server \nReply : " + conf.ping());
}
}
