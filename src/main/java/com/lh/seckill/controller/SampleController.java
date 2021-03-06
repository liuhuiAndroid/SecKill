package com.lh.seckill.controller;

import com.lh.seckill.domain.User;
import com.lh.seckill.rabbitmq.MQSender;
import com.lh.seckill.redis.RedisService;
import com.lh.seckill.redis.key.UserKey;
import com.lh.seckill.result.CodeMsg;
import com.lh.seckill.result.Result;
import com.lh.seckill.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

/**
 * Created by lh on 2018/4/19.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    MQSender sender;

    /**
     * Hello World
     */
    @ApiOperation(value = "测试hello world", notes = "第一个测试Api")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    /**
     * 测试Result
     */
    @RequestMapping("/helloSuccess")
    @ResponseBody
    public Result<String> helloSuccess() {
        return Result.success("Hello Success");
    }

    /**
     * 测试Result
     */
    @RequestMapping("/helloError")
    @ResponseBody
    public Result<CodeMsg> helloError() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    /**
     * 测试Thymeleaf
     */
    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "HiphopMan");
        return "hello";
    }

    /**
     * 测试mybatis和druid
     */
    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {
        User user = userService.getById(1);
        return Result.success(user);
    }

    /**
     * 测试事务
     */
    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx() {
        userService.tx();
        return Result.success(true);
    }

    /**
     * 测试Redis
     */
    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        User user = redisService.get(UserKey.getById, "" + 1, User.class);
        return Result.success(user);
    }

    /**
     * 测试Redis
     */
    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user = new User();
        user.setId(1);
        user.setName("1111");
        redisService.set(UserKey.getById, "" + 1, user);//UserKey:id1
        return Result.success(true);
    }

    /**
     * Headers模式
     */
    @RequestMapping("/mq/header")
    @ResponseBody
    public Result<String> header() {
        sender.sendHeader("hello,java");
        return Result.success("Hello，world");
    }

    /**
     * Fanout模式
     */
    @RequestMapping("/mq/fanout")
    @ResponseBody
    public Result<String> fanout() {
        sender.sendFanout("hello,java");
        return Result.success("Hello，world");
    }

    /**
     * Topic模式
     */
    @RequestMapping("/mq/topic")
    @ResponseBody
    public Result<String> topic() {
        sender.sendTopic("hello,java");
        return Result.success("Hello，world");
    }

    /**
     * Direct模式
     */
    @RequestMapping("/mq")
    @ResponseBody
    public Result<String> mq() {
        sender.send("hello,java");
        return Result.success("Hello，world");
    }

}