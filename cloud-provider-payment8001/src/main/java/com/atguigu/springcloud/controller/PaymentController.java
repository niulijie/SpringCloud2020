package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * @author niulijie
 * @since 2022-03-21 16:20:30
 * @dec 服务提供者集群搭建
 */
@Validated
@RestController
@Slf4j
//@RequestMapping("payment")
//@RefreshScope//与value搭配，可以动态获取配置文件信息，不用重启项目
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

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

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("***********service:"+service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+","+instance.getHost()+","+instance.getPort()+","+instance.getUri());
        }
        return this.discoveryClient;
    }
}
