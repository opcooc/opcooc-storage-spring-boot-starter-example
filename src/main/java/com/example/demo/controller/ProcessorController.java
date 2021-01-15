/*
 * Copyright © 2020-2025 organization opcooc
 * <pre>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <pre/>
 */
package com.example.demo.controller;

import com.example.demo.model.TenantDTO;
import com.example.demo.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shenqicheng
 */
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
