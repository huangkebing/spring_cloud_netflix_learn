package com.kebing.server.provider.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单定义一个接口
 */
@RestController
public class RecordController {

    @RequestMapping("/record/{rid}")
    public Object queryRecord(@PathVariable String rid){
        Map<String, String> map = new HashMap<>();
        map.put("rid", rid);
        map.put("work", "3");
        map.put("dream", "win");
        return map;
    }
}
