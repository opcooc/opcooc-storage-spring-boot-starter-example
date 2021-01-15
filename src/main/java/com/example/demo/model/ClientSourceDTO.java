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
package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author shenqicheng
 */
@Data
public class ClientSourceDTO {

    @ApiModelProperty("ClientDriver唯一名称")
    private String name;

    @ApiModelProperty("bucketName")
    private String bucketName;

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

    @ApiModelProperty("basePath")
    private String basePath;

    @ApiModelProperty("autoCreateBucket")
    private Boolean autoCreateBucket = false;

    @ApiModelProperty("decryptCallbackClassName")
    private String decryptCallbackClassName;

    @ApiModelProperty("解密公匙")
    private String publicKey;
}
