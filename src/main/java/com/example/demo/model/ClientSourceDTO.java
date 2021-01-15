package com.example.demo.model;

import com.opcooc.storage.enums.DefaultDriverType;
import com.opcooc.storage.support.DecryptCallback;
import com.opcooc.storage.support.DefaultDecryptCallback;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ClientSourceDTO {

    @ApiModelProperty("客户端驱动名称唯一标识")
    private String driverName;

    @ApiModelProperty("驱动类型")
    private DefaultDriverType type = DefaultDriverType.S3;

    @ApiModelProperty("默认主目录(需要保证唯一)")
    private String defaultBucket;

    @ApiModelProperty("endPoint")
    private String endPoint;

    @ApiModelProperty("accessKey")
    private String accessKey;

    @ApiModelProperty("secretKey")
    private String secretKey;

    @ApiModelProperty("pathStyle")
    private Boolean pathStyle;

    @ApiModelProperty("region")
    private String region;

    @ApiModelProperty("第一目录层级(默认为空, 当存在时所有路径都以 [firstPath + objectName] 拼接 ** 需要自己实现ObjectConverter **)")
    private String firstPath;

    @ApiModelProperty("是否自动创建主目录")
    private Boolean autoCreateBucket = false;

    @ApiModelProperty("解密公匙")
    private String publicKey;
}
