package com.example.demo.config;

import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.BucketPolicy;
import com.google.common.collect.Lists;
import com.opcooc.storage.args.*;
import com.opcooc.storage.client.Client;
import com.opcooc.storage.drivers.ClientDriver;
import com.opcooc.storage.model.FileBasicInfo;
import com.opcooc.storage.spring.boot.autoconfigure.ClientDriverProperty;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 自定义驱动
 */
public class DemoClientDriver implements ClientDriver {

    @Override
    public Client connect() {
        return new DemoClient();
    }

    @Override
    public ClientDriverProperty getConfiguration() {
        return new ClientDriverProperty();
    }

    @Override
    public void close() throws IOException {

    }

    static class DemoClient implements Client {

        @Override
        public void createFolder(SetFolderArgs args) {

        }

        @Override
        public void setBucketAcl(SetBucketAclArgs args) {

        }

        @Override
        public AccessControlList getBucketAcl(GetBucketAclArgs args) {
            return null;
        }

        @Override
        public void setBucketPolicy(SetBucketPolicyArgs args) {

        }

        @Override
        public BucketPolicy getBucketPolicy(GetBucketPolicyArgs args) {
            return null;
        }

        @Override
        public void deleteBucketPolicy(DeleteBucketPolicyArgs args) {

        }

        @Override
        public String createBucket(CreateBucketArgs args) {
            return null;
        }

        @Override
        public void deleteBucket(DeleteBucketArgs args) {

        }

        @Override
        public List<String> listBuckets() {
            return Lists.newArrayList();
        }

        @Override
        public boolean doesBucketExist(DoesBucketExistArgs args) {
            return false;
        }

        @Override
        public void setObjectAcl(SetObjectAclArgs args) {

        }

        @Override
        public AccessControlList getObjectAcl(GetObjectAclArgs args) {
            return null;
        }

        @Override
        public FileBasicInfo uploadObject(UploadObjectArgs args) {
            return null;
        }

        @Override
        public FileBasicInfo uploadFile(UploadFileArgs args) {
            return null;
        }

        @Override
        public FileBasicInfo uploadUrl(UploadUrlArgs args) {
            return null;
        }

        @Override
        public void copyObject(CopyObjectArgs args) {

        }

        @Override
        public List<FileBasicInfo> listObjects(ListObjectsArgs args) {
            return null;
        }

        @Override
        public FileBasicInfo getObjectMetadata(ObjectMetadataArgs args) {
            return null;
        }

        @Override
        public boolean objectExist(DoesObjectExistArgs args) {
            return false;
        }

        @Override
        public InputStream getObjectToStream(GetObjectToStreamArgs args) {
            return null;
        }

        @Override
        public File geObjectToFile(GetObjectToFileArgs args) {
            return null;
        }

        @Override
        public void deleteObject(DeleteObjectArgs args) {

        }

        @Override
        public void deleteObjects(DeleteObjectsArgs args) {

        }

        @Override
        public String generatePresignedUrl(GetPresignedObjectUrlArgs args) {
            return null;
        }

        @Override
        public String getUrl(GetUrlArgs args) {
            return null;
        }
    }
}
