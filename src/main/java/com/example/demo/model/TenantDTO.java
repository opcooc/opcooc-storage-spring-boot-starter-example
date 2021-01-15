package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TenantDTO {

    @ApiModelProperty("tenantName")
    private String tenantName;

}
