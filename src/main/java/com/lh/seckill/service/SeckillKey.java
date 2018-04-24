package com.lh.seckill.service;

import com.lh.seckill.redis.BasePrefix;

/**
 * Created by lh on 2018/4/24.
 */
public class SeckillKey extends BasePrefix {


    private SeckillKey(String prefix) {
        super(prefix);
    }

    public static SeckillKey isGoodsOver = new SeckillKey("go");

}
