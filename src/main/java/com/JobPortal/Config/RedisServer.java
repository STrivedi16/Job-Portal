package com.JobPortal.Config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("RedisService")
public class RedisServer {

	public RedisServer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private CacheConfig cc;
	
	public boolean isKeyExist(String key1,String key2)
	{
		return cc.redisTemplate().opsForHash().hasKey(key1, key2);
	}
	
	public void addInCache(String key1, String key2, Object val)
	{
		cc.redisTemplate().opsForHash().put(key1, key2, val);
//		cc.redisTemplate().expire(key2, Duration.ofMinutes(1));
	}
	
	public Object getFromCache(String key1, String key2)
	{
		return cc.redisTemplate().opsForHash().get(key1, key2);
	}
	
	public void removeKeyFromCache(String key)
	{
		cc.redisTemplate().delete(key);
		return;
	}
}
