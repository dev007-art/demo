package com.myself.demo;

import com.myself.demo.service.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
class DemoApplicationTests {
    //一级二级目录测试测试
    @Autowired
    FileService fileService;
    @Test
    void contextLoads() {
        String filePath = new File("D:/cc").getPath();
        File fileUpload = new File(filePath);
        if (!fileUpload.exists()) {
            fileUpload.mkdirs();
        }
        String timePath = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String path = filePath+"/"+timePath;
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
        }
        System.out.println(path);
    }
    @Test
    void pathTest(){
        UUID uuid =UUID.randomUUID();
        System.out.println(uuid.toString());
    }

}
