package com.wbj.reggie.controller;

import com.wbj.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {
    @Value("${reggie.path}")
    private String basePath;

    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {
//        String originalFilename = file.getOriginalFilename();
//        String uuid = UUID.randomUUID().toString();
//        String fileName = uuid + originalFilename.substring(originalFilename.lastIndexOf("."));
//
//        log.info("保存路径：{}",basePath+fileName);
//
//        File filePath = new File(basePath);
//        if (!filePath.exists()) {
//            filePath.mkdirs();
//        }
//
//        try {
//            file.transferTo(filePath);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return R.success(fileName);
        return null;
    }
}
