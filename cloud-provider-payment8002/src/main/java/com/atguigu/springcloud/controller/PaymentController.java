package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.atguigu.springcloud.service.PaymentService;

import java.util.Objects;


/**
 * @author niulijie
 * @since 2022-03-21 16:20:30
 */
@Validated
@RestController
//@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;
     /**
     *  
     * @param  
     */
    @PostMapping("/payment/create")
    public CommonResult<?> creat(@RequestBody Payment payment) {
        int result = paymentService.creat(payment);
        if (result > 0) {
            return new CommonResult<>(200, "插入成功,serverPort:"+serverPort, result);
        } else {
            return new CommonResult<>(400, "插入失败", null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<?> getPaymentById(@PathVariable("id") Integer id) {
        Payment payment = paymentService.getPaymentById(id);
        if (!Objects.isNull(payment)) {
            return new CommonResult<>(200, "查询成功,serverPort:"+serverPort, payment);
        } else {
            return new CommonResult<>(400, "查询失败，查询ID："+ id, null);
        }
    }
}
