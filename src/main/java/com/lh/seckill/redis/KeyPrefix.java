package com.lh.seckill.redis;

public interface KeyPrefix {
		
	int expireSeconds();
	
	String getPrefix();
	
}
