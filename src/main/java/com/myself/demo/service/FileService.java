package com.myself.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.myself.demo.exception.GlobalException;
import com.myself.demo.mapper.FileMapper;
import com.myself.demo.model.ReturnMessage;
import com.myself.demo.pojo.FilePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileService {
    @Autowired
    FileMapper fileMapper;
    //文件上传,code=1代表成功，code=2代表失败
    public JSONObject upload(MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
                return ReturnMessage.fail("未选择需上传的日志文件");
        }

        String filePath = new File("D:/test").getPath();
        File fileUpload = new File(filePath);
        if (!fileUpload.exists()) {
            fileUpload.mkdirs();
        }
        //获取当前日期
        String time = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String sp = filePath+"/"+time;
        File secondPath = new File(sp);
        if (!secondPath.exists()){
           secondPath.mkdirs();
        }

        UUID uuid = UUID.randomUUID();
        String originalFilename = file.getOriginalFilename();
        fileUpload = new File(secondPath, uuid+file.getOriginalFilename());
        if (fileUpload.exists()) {
                return ReturnMessage.fail("上传的文件已存在");
        }
        //设置需要传入数据库对象
        FilePojo filePojo = new FilePojo();
        filePojo.setName(originalFilename);
        filePojo.setData(time);
        filePojo.setPath(fileUpload.getAbsolutePath());
        filePojo.setSize(((int)file.getSize()));//大小为byte单位
        filePojo.setType(originalFilename.substring(originalFilename.lastIndexOf(".",originalFilename.length())));
        filePojo.setUuid(uuid.toString());
        try {
            fileMapper.insertAllColumn(filePojo);//存入数据库
            file.transferTo(fileUpload);//文件存放到二级目录
            return ReturnMessage.success(uuid);
        } catch (IOException e) {
            throw new GlobalException("上传日志文件到服务器失败：" + e.toString());
        }
    }
    //文件下载，这个我前端没有写下载按钮，直接输入之前上传    uuid+文件名称就可以下载
    public void download(String name, HttpServletResponse response) throws GlobalException, IOException {
        File file = new File("D:/test/20220209/"+name);
        if (!file.exists()) {
            throw new GlobalException(name + "文件不存在");
        }
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + name);

        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            OutputStream os = response.getOutputStream();

            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        }
    }

    public JSONObject returnToClient(UUID uuid) {
       /* EntityWrapper<FilePojo> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("uuid", "6880cf89-7b8b-4db2-98d0-bb3c95086016");
        List<FilePojo> filePojos = fileMapper.selectList(entityWrapper);*/
        FilePojo filePojo = fileMapper.selectByUUID(uuid.toString());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("file",filePojo);
        return jsonObject;
    }
}
