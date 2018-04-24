package com.lh.seckill.service;

import com.lh.seckill.dao.SeckillUserDao;
import com.lh.seckill.domain.SeckillUser;
import com.lh.seckill.exception.GlobalException;
import com.lh.seckill.redis.RedisService;
import com.lh.seckill.redis.key.SeckillUserKey;
import com.lh.seckill.result.CodeMsg;
import com.lh.seckill.util.MD5Util;
import com.lh.seckill.util.UUIDUtil;
import com.lh.seckill.vo.LoginVo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by lh on 2018/4/19.
 */
@Service
public class SeckillUserService {

    public static final String COOKI_NAME_TOKEN = "token";

    @Autowired
    SeckillUserDao seckillUserDao;

    @Autowired
    RedisService redisService;

    public String login(HttpServletResponse response, @Valid LoginVo loginVo) {
        if(loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        SeckillUser user = getById(Long.parseLong(mobile));
        if(user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if(!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);
        return token;
    }

    public SeckillUser getById(long id) {
        //取缓存
        SeckillUser user = redisService.get(SeckillUserKey.getById, ""+id, SeckillUser.class);
        if(user != null) {
            return user;
        }
        user = seckillUserDao.getById(id);
        if(user != null) {
            redisService.set(SeckillUserKey.getById, ""+id, user);
        }
        return user;
    }

    // http://blog.csdn.net/tTU1EvLDeLFq5btqiK/article/details/78693323
    public boolean updatePassword(String token, long id, String formPass) {
        //取user
        SeckillUser user = getById(id);
        if(user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //更新数据库
        SeckillUser toBeUpdate = new SeckillUser();
        toBeUpdate.setId(id);
        toBeUpdate.setPassword(MD5Util.formPassToDBPass(formPass, user.getSalt()));
        seckillUserDao.update(toBeUpdate);
        //处理缓存
        redisService.delete(SeckillUserKey.getById, ""+id);
        user.setPassword(toBeUpdate.getPassword());
        redisService.set(SeckillUserKey.token, token, user);
        return true;
    }

    private void addCookie(HttpServletResponse response, String token, SeckillUser user) {
        redisService.set(SeckillUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(SeckillUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public SeckillUser getByToken(HttpServletResponse response, String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        SeckillUser user = redisService.get(SeckillUserKey.token, token, SeckillUser.class);
        //延长有效期
        if(user != null) {
            addCookie(response, token, user);
        }
        return user;
    }

}
