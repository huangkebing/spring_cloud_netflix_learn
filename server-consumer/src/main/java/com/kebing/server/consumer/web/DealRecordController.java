package com.kebing.server.consumer.web;

import com.kebing.server.consumer.entity.Record;
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

    private final RestTemplate restTemplate;
    private final LoadBalancerClient loadBalancerClient;

    public DealRecordController(RestTemplate restTemplate, LoadBalancerClient loadBalancerClient) {
        this.restTemplate = restTemplate;
        this.loadBalancerClient = loadBalancerClient;
    }

    @RequestMapping("/deal/record/{rid}")
    public Object dealRecord(@PathVariable String rid){
        return restTemplate.getForObject("http://user-provider/record/" + rid, Record.class);
    }

    @RequestMapping("/logUserInstance")
    public void logUserInstance(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("user-provider");
        // 打印当前选择的是哪个结点
        log.info("{}:{}:{}",serviceInstance.getServiceId(),serviceInstance.getHost(),serviceInstance.getPort());
    }
}
