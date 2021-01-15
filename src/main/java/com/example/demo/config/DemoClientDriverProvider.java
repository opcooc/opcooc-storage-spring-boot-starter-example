package com.example.demo.config;

import com.opcooc.storage.drivers.ClientDriver;
import com.opcooc.storage.enums.DefaultDriverType;
import com.opcooc.storage.holder.ClientDriverHolder;
import com.opcooc.storage.provider.AbstractClientDriverProvider;
import com.opcooc.storage.spring.boot.autoconfigure.ClientDriverProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义ClientDriver加载器  (需要注入IOC)
 */
public class DemoClientDriverProvider extends AbstractClientDriverProvider {

    @Autowired
    private ClientDriverHolder clientDriverHolder;

    @Override
    public Map<String, ClientDriver> loadClientDrivers() {
        Map<String, ClientDriverProperty> map = new HashMap<>(6);
        ClientDriverProperty property = new ClientDriverProperty();
//        property.setDriverName("demo_driver_provider");
        property.setDefaultBucket("opcooc");
        property.setEndPoint("xxx");
        property.setAccessKey("xxx");
        property.setSecretKey("xxx");
        property.setAutoCreateBucket(false);
//        property.setFirstPath("");
        property.setType(DefaultDriverType.S3);
        property.setRegion("cn-north-1");

        map.put("demo_driver_provider", property);
        return createClientDriverMap(map);
    }

}
