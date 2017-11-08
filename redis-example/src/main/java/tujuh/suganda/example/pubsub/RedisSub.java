package tujuh.suganda.example.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import tujuh.suganda.config.RedisConfig;

import java.util.Scanner;
public class RedisSub {
	static RedisConfig redisConf;

	 public static void main(String args[]) {
	        Jedis jedis = redisConf.con();
	        Scanner scanner = new Scanner(System.in);
	        System.out.printf("Enter the channel name:");
	        String channel = scanner.nextLine();
	        System.out.println("Starting subscriber for channel " + channel);

	        while (true) {
	            jedis.subscribe(new JedisPubSub() {
	                @Override
	                public void onMessage(String channel, String message) {
	                    super.onMessage(channel, message);
	                    System.out.println("Received message:" + message);
	                }

	                @Override
	                public void onSubscribe(String channel, int subscribedChannels) {
	                }

	                @Override
	                public void onUnsubscribe(String channel, int subscribedChannels) {
	                }

	                @Override
	                public void onPMessage(String pattern, String channel, String message) {
	                }

	                @Override
	                public void onPUnsubscribe(String pattern, int subscribedChannels) {
	                }
	                
	                @Override
	                public void onPSubscribe(String pattern, int subscribedChannels) {
	                }
	            }, channel);
	        }
	    }
}
