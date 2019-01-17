package test.utry;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class testRedis {

	@Test
	public void test() {
		Jedis jedis = new Jedis("111.230.52.34", 6379);
		System.out.println(jedis.ping());
	}
}
