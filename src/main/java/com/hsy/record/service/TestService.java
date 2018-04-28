package com.hsy.record.service;

import com.hsy.core.fastdfs.FastDFSEngine;
import org.apache.commons.io.FileUtils;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by developer2 on 2018/4/28.
 */
@Service
public class TestService {

    @Autowired
    private FastDFSEngine fastDFSEngine;

    /**
     * 根据byte数组，生成文件
     */
    public static void getFile(byte[] bfile, String filePath,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath+"\\"+fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public void test () throws IOException, MyException {
//        String fileId = fastDFSEngine.upload(FileUtils.readFileToByteArray(new File("D:\\hsyfdst.png")), "test.png");
//        System.out.print(fileId);
        getFile(fastDFSEngine.download("group1/M00/00/00/rBCppVrkQliAV7zLAADYSNRwZls3112534.png"),"D:\\","gege.png");
    }

    public static void main(String[] args) throws IOException {
        System.out.print(FileUtils.readFileToByteArray(new File("D:\\hsyfdst.png")).length);
    }
}
