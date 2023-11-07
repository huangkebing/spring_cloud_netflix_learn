package com.kebing.server.consumer.feign.fallback;

import com.kebing.server.consumer.entity.Record;
import com.kebing.server.consumer.feign.RecordFeignClient;

public class RecordFeignClientFallback implements RecordFeignClient {
    @Override
    public Record getRecord(String rid) {
        Record record = new Record();
        record.setRid("-1");
        record.setWork("3");
        record.setDream("lose");
        return record;
    }
}
