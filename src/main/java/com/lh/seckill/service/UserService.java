package com.lh.seckill.service;

import com.lh.seckill.dao.UserDao;
import com.lh.seckill.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lh on 2018/4/19.
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(int id){
        return userDao.getById(id);
    }

    @Transactional
    public void tx() {
        userDao.insert(3,"你呢");
        userDao.insert(1,"你呢");
    }

}