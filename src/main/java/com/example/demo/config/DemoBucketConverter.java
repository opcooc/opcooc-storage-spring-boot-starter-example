package com.example.demo.config;

import com.opcooc.storage.args.BucketArgs;
import com.opcooc.storage.spring.boot.autoconfigure.ClientDriverProperty;
import com.opcooc.storage.support.BucketConverter;

/**
 * 自定义 bucket名称转换器  (需要注入IOC)
 */
public class DemoBucketConverter implements BucketConverter {
    @Override
    public String convert(ClientDriverProperty config, BucketArgs bucket) {
        //自定义标识
        String sign = bucket.getSign();
        return String.format("%s-%s", "demo", bucket.getBucketName());
    }
}
