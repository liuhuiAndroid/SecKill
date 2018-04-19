package com.lh.seckill.redis.key;

import com.lh.seckill.redis.BasePrefix;

/**
 * Created by lh on 2018/4/19.
 */
public class UserKey extends BasePrefix {

    public UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");

    public static UserKey getByName = new UserKey("name");

}
