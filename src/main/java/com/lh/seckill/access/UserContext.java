package com.lh.seckill.access;

import com.lh.seckill.domain.SeckillUser;

/**
 * Created by lh on 2018/4/24.
 */
public class UserContext {

    private static ThreadLocal<SeckillUser> userHolder = new ThreadLocal<SeckillUser>();

    public static void setUser(SeckillUser user) {
        userHolder.set(user);
    }

    public static SeckillUser getUser() {
        return userHolder.get();
    }
    
}
