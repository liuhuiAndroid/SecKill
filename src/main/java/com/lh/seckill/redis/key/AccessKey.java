package com.lh.seckill.redis.key;

import com.lh.seckill.redis.BasePrefix;

/**
 * Created by lh on 2018/4/24.
 */
public class AccessKey extends BasePrefix {

    private AccessKey( int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static AccessKey withExpire(int expireSeconds) {
        return new AccessKey(expireSeconds, "access");
    }

}
