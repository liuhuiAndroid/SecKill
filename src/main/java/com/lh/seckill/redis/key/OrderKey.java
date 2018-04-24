package com.lh.seckill.redis.key;

import com.lh.seckill.redis.BasePrefix;

/**
 * Created by lh on 2018/4/23.
 */
public class OrderKey extends BasePrefix {


    public OrderKey(String prefix) {
        super(prefix);
    }

    public static OrderKey getSeckillOrderByUidGid = new OrderKey("moug");

}
