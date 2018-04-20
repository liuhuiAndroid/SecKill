package com.lh.seckill.util;

import java.util.UUID;

/**
 * Created by lh on 2018/4/19.
 */
public class UUIDUtil {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
