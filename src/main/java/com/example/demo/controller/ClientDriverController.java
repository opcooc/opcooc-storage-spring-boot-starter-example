package com.example.demo.controller;

import com.example.demo.config.DemoDecryptCallback;
import com.example.demo.model.ClientSourceDTO;
import com.opcooc.storage.drivers.ClientDriver;
import com.opcooc.storage.drivers.DynamicRoutingClientDriver;
import com.opcooc.storage.holder.ClientDriverHolder;
import com.opcooc.storage.spring.boot.autoconfigure.ClientDriverProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
@Api("客户端操作测试")
public class ClientDriverController {

    private final ClientDriver clientDriver;

    private final ClientDriverHolder clientDriverHolder;

    @GetMapping
    @ApiOperation("获取当前所有客户端")
    public Set<String> now() {
        DynamicRoutingClientDriver os = (DynamicRoutingClientDriver) clientDriver;
        return os.getCurrentClientDrivers().keySet();
    }

    @PostMapping("/add")
    @ApiOperation("通用添加客户端")
    public Set<String> add(@Validated @RequestBody ClientSourceDTO dto) {
        ClientDriverProperty property = new ClientDriverProperty();
        BeanUtils.copyProperties(dto, property);
        //add DecryptCallback
        property.setDecryptCallback(new DemoDecryptCallback());

        DynamicRoutingClientDriver routingClientDriver = (DynamicRoutingClientDriver) this.clientDriver;
        ClientDriver driver = clientDriverHolder.getClientDriver(property);
        routingClientDriver.addClientDriver(property.getDriverName(), driver);
        return routingClientDriver.getCurrentClientDrivers().keySet();
    }

    @DeleteMapping
    @ApiOperation("删除客户端")
    public String remove(String name) {
        DynamicRoutingClientDriver os = (DynamicRoutingClientDriver) clientDriver;
        os.removeClientDriver(name);
        return "删除成功";
    }

}
