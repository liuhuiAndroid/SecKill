package com.lh.seckill.rabbitmq;

import com.lh.seckill.domain.SeckillUser;

/**
 * Created by lh on 2018/4/24.
 */
public class SeckillMessage {

    private SeckillUser user;
    private long goodsId;

    public SeckillUser getUser() {
        return user;
    }

    public void setUser(SeckillUser user) {
        this.user = user;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }
}
