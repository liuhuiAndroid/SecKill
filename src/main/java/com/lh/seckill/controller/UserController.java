package com.lh.seckill.controller;

import com.lh.seckill.domain.SeckillUser;
import com.lh.seckill.redis.RedisService;
import com.lh.seckill.result.Result;
import com.lh.seckill.service.SeckillUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lh on 2018/4/20.
 * 压力测试
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {

    @Autowired
    SeckillUserService userService;

    @Autowired
    RedisService redisService;

    /**
     * QPS:366.6
     * 1000 * 10
     */
    @RequestMapping("/info")
    @ResponseBody
    public Result<SeckillUser> info(Model model, SeckillUser user) {
        return Result.success(user);
    }

}