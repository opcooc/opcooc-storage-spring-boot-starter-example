package com.example.demo.service;

import com.example.demo.model.TenantDTO;

public interface DemoService {

    void session();

    void header();

    void spelByKey(String tenantName);

    void spelByTenant(TenantDTO dto);


}
