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

import cn.hutool.core.io.FileUtil;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.opcooc.storage.StorageClient;
import com.opcooc.storage.annotation.OS;
import com.opcooc.storage.args.*;
import com.opcooc.storage.model.FileBasicInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Collections;
import java.util.List;


/**
 * @author shenqicheng
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Api("api测试")
@OS("#tenantName")
public class ApiController {

    public static final String BUCKET_NAME = "opcooc";
    public static final String FILE_NAME = "classpath:demo/demo.jpg";

    private final ResourceLoader resourceLoader;
    private final StorageClient client;

    @GetMapping("/createFolder")
    @ApiOperation("创建文件夹")
    public void createFolder(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String folderName) {
        client.createFolder(SetFolderArgs.builder().bucketName(BUCKET_NAME).folderName(folderName).build());
    }

    @GetMapping("/setBucketAcl")
    @ApiOperation("设置存储空间(主目录)ACL模式(只支持兼容s3协议的服务商)")
    public void setBucketAcl(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String bucketName) {
        client.setBucketAcl(SetBucketAclArgs.builder().bucketName(bucketName).cannedAcl(CannedAccessControlList.PublicRead).build());
    }

    @GetMapping("/getBucketAcl")
    @ApiOperation("读取存储空间(主目录)ACL模式(只支持兼容s3协议的服务商)")
    public void getBucketAcl(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String bucketName) {
        client.getBucketAcl(GetBucketAclArgs.builder().bucketName(bucketName).build());
    }

    @GetMapping("/setBucketPolicy")
    @ApiOperation("设置 存储空间(主目录) 策略")
    public void setBucketPolicy(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String bucketName, @RequestParam String policyText) {
        //policyText
        //{"Statement":[{"Action":"s3:GetObject","Effect":"Allow","Principal":"*","Resource":"arn:aws:s3:::opcooc/demo*"}],"Version":"2012-10-17"}
        client.setBucketPolicy(SetBucketPolicyArgs.builder().bucketName(bucketName).policyText(policyText).build());
    }

    @GetMapping("/getBucketPolicy")
    @ApiOperation("获取 存储空间(主目录) 策略")
    public void getBucketPolicy(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String bucketName) {
        client.getBucketPolicy(GetBucketPolicyArgs.builder().bucketName(bucketName).build());
    }

    @GetMapping("/deleteBucketPolicy")
    @ApiOperation("删除 存储空间(主目录) 策略")
    public void deleteBucketPolicy(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String bucketName) {
        client.deleteBucketPolicy(DeleteBucketPolicyArgs.builder().bucketName(bucketName).build());
    }

    @GetMapping("/createBucket")
    @ApiOperation("创建存储空间(主目录)")
    public void createBucket(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String bucketName) {
        client.createBucket(CreateBucketArgs.builder().bucketName(bucketName).build());
    }

    @GetMapping("/deleteBucket")
    @ApiOperation("删除存储空间(主目录)")
    public void deleteBucket(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String bucketName) {
        client.deleteBucket(DeleteBucketArgs.builder().bucketName(bucketName).build());
    }

    @GetMapping("/listBuckets")
    @ApiOperation("获取所有存储空间(主目录)名称")
    public List<String> listBuckets(@RequestParam(defaultValue = "s3_minio") String tenantName) {
        return client.listBuckets();
    }

    @GetMapping("/doesBucketExist")
    @ApiOperation("判断桶是否存在")
    public boolean doesBucketExist(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String bucketName) {
        return client.doesBucketExist(DoesBucketExistArgs.builder().bucketName(bucketName).build());
    }

    @GetMapping("/setObjectAcl")
    @ApiOperation("设置存储空间(主目录)ACL模式(只支持兼容s3协议的服务商)")
    public void setObjectAcl(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String bucketName, @RequestParam String objectName) {
        client.setObjectAcl(SetObjectAclArgs.builder().bucketName(bucketName).objectName(objectName).cannedAcl(CannedAccessControlList.PublicRead).build());
    }

    @GetMapping("/getObjectAcl")
    @ApiOperation("读取存储空间(主目录)ACL模式(只支持兼容s3协议的服务商)")
    public void getObjectAcl(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String bucketName, @RequestParam String objectName) {
        client.getObjectAcl(GetObjectAclArgs.builder().bucketName(bucketName).objectName(objectName).build());
    }

    @GetMapping("/uploadObject")
    @ApiOperation("上传文件到服务器")
    public FileBasicInfo uploadObject(@RequestParam(defaultValue = "s3_minio") String tenantName) {
        try (InputStream inputStream = resourceLoader.getResource(FILE_NAME).getInputStream()) {
            return client.uploadObject(UploadObjectArgs.builder()
                    .stream(inputStream)
                    .objectSize(inputStream.available())
                    .bucketName(BUCKET_NAME)
                    .objectName("demo/object/demo.jpg").build());
        } catch (Exception e) {
            throw new RuntimeException("上传文件错误!");
        }
    }

    @GetMapping("/uploadFile")
    @ApiOperation("上传文件到服务器")
    public FileBasicInfo uploadFile(@RequestParam(defaultValue = "s3_minio") String tenantName) throws IOException {
        return client.uploadFile(UploadFileArgs.builder()
                .file(resourceLoader.getResource(FILE_NAME).getFile())
                .objectName("demo/file/demo.jpg")
                .bucketName(BUCKET_NAME).build());
    }

    @GetMapping("/uploadUrl")
    @ApiOperation("上传文件到服务器")
    public FileBasicInfo uploadUrl(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String url) {
        return client.uploadUrl(UploadUrlArgs.builder()
                .url(url)
                .objectName("demo/url/demo.jpg")
                .bucketName(BUCKET_NAME).build());
    }

    @GetMapping("/copyObject")
    @ApiOperation("复制文件")
    public void copyObject(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String objectName) {
        CopySource source = CopySource.builder().objectName(objectName).bucketName(BUCKET_NAME).build();
        client.copyObject(CopyObjectArgs.builder().source(source).objectName("demo/copy/demo.jpg").bucketName("copy-opcooc").build());
    }

    @GetMapping("/listObjects")
    @ApiOperation("获取指定存储空间(主目录)名称 指定前缀 的下级所有文件")
    public List<FileBasicInfo> listObjects(@RequestParam(defaultValue = "s3_minio") String tenantName) {
        return client.listObjects(ListObjectsArgs.builder().maxKeys(1000).prefix("demo").recursive(true).bucketName(BUCKET_NAME).build());
    }


    @GetMapping("/getObjectMetadata")
    @ApiOperation("获取对象元数据")
    public FileBasicInfo getObjectMetadata(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String objectName) {
        return client.getObjectMetadata(ObjectMetadataArgs.builder().bucketName(BUCKET_NAME).objectName(objectName).build());
    }


    @GetMapping("/objectExist")
    @ApiOperation("判断对象是否存在")
    public boolean objectExist(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String objectName) {
        return client.objectExist(DoesObjectExistArgs.builder().bucketName(BUCKET_NAME).objectName(objectName).build());
    }

    @GetMapping("/getObjectToStream")
    @ApiOperation("获得文件 InputStream")
    public Long getObjectToStream(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String objectName) {
        File file = FileUtil.touch(objectName);
        try (final ReadableByteChannel readableByteChannel = Channels.newChannel(client
                .getObjectToStream(GetObjectToStreamArgs.builder().bucketName(BUCKET_NAME).objectName(objectName).build()));
             final FileOutputStream fos = new FileOutputStream(file)) {
            fos.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            return file.length();
        } catch (Exception e) {
            throw new RuntimeException("下载文件错误!");
        }
    }

    @GetMapping("/geObjectToFile")
    @ApiOperation("获得文件")
    public Long geObjectToFile(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String objectName) {
        File file = client.geObjectToFile(GetObjectToFileArgs.builder()
                .file(FileUtil.touch(objectName)).bucketName(BUCKET_NAME).objectName(objectName).build());
        return file.length();
    }


    @GetMapping("/deleteObject")
    @ApiOperation("删除单个文件")
    public void deleteObject(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String objectName) {
        client.deleteObject(DeleteObjectArgs.builder().bucketName(BUCKET_NAME).objectName(objectName).build());
    }

    @GetMapping("/deleteObjects")
    @ApiOperation("删除文件集合")
    public void deleteObjects(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String objectName) {
        client.deleteObjects(DeleteObjectsArgs.builder().objects(Collections.singletonList(objectName)).bucketName(BUCKET_NAME).build());
    }

    @GetMapping("/generatePutPresignedUrl")
    @ApiOperation("生成签名的上传URL")
    public String generatePutPresignedUrl(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String objectName) {
        return client.generatePresignedUrl(GetPresignedObjectUrlArgs.builder()
                .expiry(30).method(HttpMethod.PUT).bucketName(BUCKET_NAME).objectName(objectName).specType(false).build());
    }

    @GetMapping("/generateSpecTypePresignedUrl")
    @ApiOperation("生成签名的上传URL，specType是否严格文件类型 ")
    public String generateSpecTypePresignedUrl(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String objectName) {
        return client.generatePresignedUrl(GetPresignedObjectUrlArgs.builder()
                .expiry(30).method(HttpMethod.PUT).bucketName(BUCKET_NAME).objectName(objectName).specType(true).build());
    }

    @GetMapping("/generateGetPresignedUrl")
    @ApiOperation("生成签名的下载URL")
    public String generateGetPresignedUrl(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String objectName) {
        return client.generatePresignedUrl(GetPresignedObjectUrlArgs.builder()
                .expiry(30).method(HttpMethod.GET).bucketName(BUCKET_NAME).objectName(objectName).build());
    }

    @GetMapping("/httpUploadFile")
    @ApiOperation("使用PresignedUrl上传文件")
    public Boolean httpUploadFile(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String putUrl) throws IOException {
        return client.httpUploadFile(putUrl, resourceLoader.getResource(FILE_NAME).getFile());
    }

    @GetMapping("/getUrl")
    @ApiOperation("得到s3文件永久url")
    public String getUrl(@RequestParam(defaultValue = "s3_minio") String tenantName, @RequestParam String objectName) {
        return client.getUrl(GetUrlArgs.builder().bucketName(BUCKET_NAME).objectName(objectName).build());
    }

}
