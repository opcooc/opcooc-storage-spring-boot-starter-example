package com.example.demo.service.impl;

import com.example.demo.model.TenantDTO;
import com.example.demo.service.DemoService;
import com.opcooc.storage.StorageClient;
import com.opcooc.storage.annotation.OS;
import com.opcooc.storage.args.SetFolderArgs;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@OS("s3")
public class DemoServiceImpl implements DemoService {

    @Resource
    private StorageClient client;


    @Override
    @OS("#session.tenantName")
    public void session() {
        client.createFolder(SetFolderArgs.builder().bucketName("opcooc-session").folderName("session-tenantName/").build());
    }

    @Override
    @OS("#header.tenantName")
    public void header() {
        client.createFolder(SetFolderArgs.builder().bucketName("opcooc-header").folderName("header-tenantName/").build());
    }

    @Override
    @OS("#tenantName")
    public void spelByKey(String tenantName) {
        client.createFolder(SetFolderArgs.builder().bucketName("opcooc-spelbykey").folderName("spelbykey-tenantName/").build());
    }

    @Override
    @OS("#dto.tenantName")
    public void spelByTenant(TenantDTO dto) {
        client.createFolder(SetFolderArgs.builder().bucketName("opcooc-spelbytenant").folderName("spelByTenant-tenantName/").build());
    }
}
