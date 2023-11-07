package com.kebing.server.consumer.web;

import com.kebing.server.consumer.entity.Record;
import com.kebing.server.consumer.feign.RecordFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DealRecordController {
    protected static Logger log = LoggerFactory.getLogger(DealRecordController.class);

    private final LoadBalancerClient loadBalancerClient;

    private final RecordFeignClient recordFeignClient;

    public DealRecordController(LoadBalancerClient loadBalancerClient, RecordFeignClient recordFeignClient) {
        this.loadBalancerClient = loadBalancerClient;
        this.recordFeignClient = recordFeignClient;
    }

    @RequestMapping("/deal/record/{rid}")
    public Object dealRecord(@PathVariable String rid){
        return recordFeignClient.getRecord(rid);
    }

    @RequestMapping("/logUserInstance")
    public void logUserInstance(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("user-provider");
        // 打印当前选择的是哪个结点
        log.info("{}:{}:{}",serviceInstance.getServiceId(),serviceInstance.getHost(),serviceInstance.getPort());
    }
}
