package com.example.demo.controller;

import com.example.demo.model.TenantDTO;
import com.example.demo.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/processor")
@Api("参数处理器测试")
public class ProcessorController {

    private final DemoService demoService;

    @GetMapping("/session")
    @ApiOperation("session处理器")
    public void session(HttpServletRequest request) {
        request.getSession().setAttribute("tenantName", "s3_minio");
        demoService.session();
    }

    @GetMapping("/header")
    @ApiOperation("header处理器")
    public void header() {
        demoService.header();
    }

    @GetMapping("/spel")
    @ApiOperation("spel处理器")
    public void spelByKey(@RequestParam String tenantName) {
        demoService.spelByKey(tenantName);
    }

    @PostMapping("/spel")
    @ApiOperation("spel处理器")
    public void spelByTenant(@RequestBody TenantDTO dto) {
        demoService.spelByTenant(dto);
    }

}
