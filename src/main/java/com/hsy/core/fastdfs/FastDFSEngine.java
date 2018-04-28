package com.hsy.core.fastdfs;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;

public class FastDFSEngine {
    private static final Logger log = LoggerFactory.getLogger(FastDFSEngine.class);
    private String clientConfigFileName;
    private TrackerClient tracker;

    public FastDFSEngine(String clientConfigFileName) throws Exception {
        this.clientConfigFileName = clientConfigFileName;
        ClientGlobal.init(this.getConfigFile());
        this.tracker = new TrackerClient();
    }

    private String getConfigFile() throws Exception {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        String fileName = resourceLoader.getResource("classpath:" + this.clientConfigFileName).getFile().getAbsolutePath();
        log.info(fileName);
        return fileName;
    }

    public String upload(byte[] fileBuff, String fileName) throws IOException, MyException {
        TrackerServer trackerServer = null;

        String var6;
        try {
            trackerServer = this.tracker.getConnection();
            StorageClient1 client = new StorageClient1(trackerServer, (StorageServer)null);
            NameValuePair[] metaList = new NameValuePair[]{new NameValuePair("fileName", fileName)};
            var6 = client.upload_file1(fileBuff, (String)null, metaList) + "." + FilenameUtils.getExtension(fileName);
        } finally {
            if(trackerServer != null) {
                trackerServer.close();
            }

        }

        return var6;
    }



    public byte[] download(String fileId) throws IOException, MyException {
        TrackerServer trackerServer = null;

        byte[] var4;
        try {
            trackerServer = this.tracker.getConnection();
            StorageClient1 client = new StorageClient1(trackerServer, (StorageServer)null);
            var4 = client.download_file1(this.eraseExtension(fileId));
        } finally {
            if(trackerServer != null) {
                trackerServer.close();
            }

        }

        return var4;
    }

    public String update(String fileId, byte[] fileBuff, String fileName) throws IOException, MyException {
        TrackerServer trackerServer = null;

        String var8;
        try {
            trackerServer = this.tracker.getConnection();
            StorageClient1 client = new StorageClient1(trackerServer, (StorageServer)null);
            int res = client.delete_file1(this.eraseExtension(fileId));
            if(res != 0) {
                throw new MyException("Delete old file failed, error code:" + res);
            }

            NameValuePair[] metaList = new NameValuePair[]{new NameValuePair("fileName", fileName)};
            var8 = client.upload_file1(fileBuff, (String)null, metaList) + "." + FilenameUtils.getExtension(fileName);
        } finally {
            if(trackerServer != null) {
                trackerServer.close();
            }

        }

        return var8;
    }

    public boolean delete(String fileId) throws IOException, MyException {
        TrackerServer trackerServer = null;

        boolean var5;
        try {
            trackerServer = this.tracker.getConnection();
            StorageClient1 client = new StorageClient1(trackerServer, (StorageServer)null);
            int res = client.delete_file1(this.eraseExtension(fileId));
            var5 = res == 0;
        } finally {
            if(trackerServer != null) {
                trackerServer.close();
            }

        }

        return var5;
    }

    private String eraseExtension(String fileName) {
        return FilenameUtils.getFullPath(fileName) + FilenameUtils.getBaseName(fileName);
    }

    public static void main(String[] args) {
        try {
            String e = "/Users/wanghongwei/Downloads/IMG_2760.PNG";
            FastDFSEngine engine = new FastDFSEngine("fdfs_client.conf");
            String fileId = "group1/M00/00/00/wKgBI1iaDGKAeFHIAAEvRwCpM6o4635375.PNG";
            log.warn("fileId=" + fileId);
            fileId = engine.update(fileId, FileUtils.readFileToByteArray(new File(e)), e);
            log.debug("update res new fileId=" + fileId);
            byte[] fileBytes = engine.download(fileId);
            FileUtils.writeByteArrayToFile(new File("test4.JPG"), fileBytes);
            log.debug(FilenameUtils.getBaseName(fileId));
            log.debug(FilenameUtils.getExtension(fileId));
            log.debug(FilenameUtils.getFullPath(fileId));
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }
}
