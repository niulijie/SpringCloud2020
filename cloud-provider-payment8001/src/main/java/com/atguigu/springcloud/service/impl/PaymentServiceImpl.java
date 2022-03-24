package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.mapper.PaymentMapper;
import com.atguigu.springcloud.service.PaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 测试支付表(Payment)表服务实现类
 *
 * @author niulijie
 * @since 2022-03-21 16:20:29
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {

     @Resource
    private PaymentMapper paymentMapper;


    @Override
    public int creat(Payment payment) {
        return paymentMapper.create(payment);
    }

    @Override
    public Payment getPaymentById(Integer id) {
        return paymentMapper.getPaymentById(id);
    }
}
