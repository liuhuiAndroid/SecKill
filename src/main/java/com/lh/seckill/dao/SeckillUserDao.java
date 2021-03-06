package com.lh.seckill.dao;

import com.lh.seckill.domain.SeckillUser;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by lh on 2018/4/19.
 */
@Mapper
public interface SeckillUserDao {

    @Select("select * from seckill_user where id = #{id}")
    SeckillUser getById(@Param("id")long id);

    @Update("update seckill_user set password = #{password} where id = #{id}")
    void update(SeckillUser toBeUpdate);

}
