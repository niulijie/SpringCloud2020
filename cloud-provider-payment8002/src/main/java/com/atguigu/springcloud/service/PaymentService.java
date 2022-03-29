package com.atguigu.springcloud.service;


import com.atguigu.springcloud.entities.Payment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 测试支付表(Payment)表服务实现类
 *
 * @author niulijie
 * @since 2022-03-21 16:20:20
 */
public interface PaymentService extends IService<Payment> {

    int creat(Payment payment);

    Payment getPaymentById(Integer id);
}
