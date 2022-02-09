package com.myself.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.myself.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class FileController {
    @Autowired
    FileService fileService;
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject logUpload(@RequestParam("file") MultipartFile file) throws Exception {
        return fileService.upload(file);
    }
    @RequestMapping(value = "/download/{name}")
    public void Download(@PathVariable String name, HttpServletResponse response) throws Exception {
        fileService.download(name, response);
    }

    @RequestMapping("/get/{uuid}")
    @ResponseBody
    public JSONObject returnToClient(@PathVariable UUID uuid) throws Exception {
        return fileService.returnToClient(uuid);
    }

}
