package com.kebing.server.consumer.feign;

import com.kebing.server.consumer.entity.Record;
import com.kebing.server.consumer.feign.fallback.RecordFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-provider", fallback = RecordFeignClientFallback.class)
public interface RecordFeignClient {
    @GetMapping("/record/{rid}")
    Record getRecord(@PathVariable("rid") String rid);
}
