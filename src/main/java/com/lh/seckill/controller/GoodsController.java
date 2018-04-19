package com.lh.seckill.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lh on 2018/4/19.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/goods")
public class GoodsController {

    @RequestMapping("/to_list")
    public String list() {
        return "goods_list";
    }

}
