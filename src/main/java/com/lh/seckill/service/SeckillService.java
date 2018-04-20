package com.lh.seckill.service;

import com.lh.seckill.domain.OrderInfo;
import com.lh.seckill.domain.SeckillUser;
import com.lh.seckill.vo.GoodsVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lh on 2018/4/20.
 */
@Service
public class SeckillService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Transactional
    public OrderInfo seckill(SeckillUser user, GoodsVo goods) {
        //减库存 下订单 写入秒杀订单
        goodsService.reduceStock(goods);
        //order_info seckill_order
        return orderService.createOrder(user, goods);
    }

}
