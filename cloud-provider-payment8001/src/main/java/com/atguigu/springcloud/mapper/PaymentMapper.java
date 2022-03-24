package com.atguigu.springcloud.mapper;

import com.atguigu.springcloud.entities.Payment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 测试支付表(Payment)表数据库访问层
 *
 * @author niulijie
 * @since 2022-03-21 16:20:31
 */
 @Mapper
public interface PaymentMapper extends BaseMapper<Payment>{

  public int create(Payment payment);

  public Payment getPaymentById(@Param("id") Integer id);
}
