package com.lh.seckill.redis.key;

import com.lh.seckill.redis.BasePrefix;

/**
 * Created by lh on 2018/4/19.
 */
public class SeckillUserKey extends BasePrefix {

    public SeckillUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static final int TOKEN_EXPIRE = 3600*24 * 2;

    public static SeckillUserKey token = new SeckillUserKey(TOKEN_EXPIRE, "tk");

}