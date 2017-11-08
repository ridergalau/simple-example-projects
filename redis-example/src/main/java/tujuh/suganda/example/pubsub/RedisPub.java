package tujuh.suganda.example.pubsub;

import java.util.Scanner;

import redis.clients.jedis.Jedis;
import tujuh.suganda.config.RedisConfig;

public class RedisPub {
	static RedisConfig redisConf;
    public static void main(String  args[]){
    	Jedis jedis = redisConf.con();
		
        Scanner scanner = new Scanner(System.in);
        
        System.out.printf("Enter the channel name:");
        String channel=scanner.nextLine();
        System.out.println("Starting publisher for channel "+ channel);
        while (true){
            System.out.println("Enter the string to Publish:");
            String msg = scanner.nextLine();
            jedis.publish(channel,msg);
        }
    }
}