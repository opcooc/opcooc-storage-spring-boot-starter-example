package com.example.demo.config;

import com.opcooc.storage.args.ObjectArgs;
import com.opcooc.storage.spring.boot.autoconfigure.ClientDriverProperty;
import com.opcooc.storage.support.ObjectConverter;

/**
 * 自定义对象路径转换器  (需要注入IOC)
 */
public class DemoObjectConverter implements ObjectConverter {
    @Override
    public String convert(ClientDriverProperty config, ObjectArgs object) {
        //自定义标识
        String sign = object.getSign();
        return String.format("%s-%s", "demo/", object.getObjectName());
    }
}
