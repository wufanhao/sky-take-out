package com.sky.controller.admin;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 通用接口
 */
@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;
    
    //本地存储文件
  /*@PostMapping("/upload")
    public Result<String> upload(MultipartFile image) throws Exception {
        log.info("文件上传： {}", image);
        //获取原始文件名
        String originalFilename = image.getOriginalFilename();

        //构造唯一的文件名（不能重复） - uuid(通用唯一识别码)
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;
        log.info("新文件名：{}", newFileName);

        //将文件存储在服务器的磁盘目录中 E:\images
        image.transferTo(new File("E:\\images\\" + newFileName));

        return Result.success("文件上传成功");
    } */


    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传：{}", file);

        try {
            //原始文件名
            String originalFileSname = file.getOriginalFilename();
            //截取原始文件名的后缀  dfdfdf.png
            String extension = originalFileSname.substring(originalFileSname.lastIndexOf("."));
            //构造新文件名称
            String objectName = UUID.randomUUID().toString() + extension;

            //文件的请求路径
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filePath);
        } catch (Exception e) {
            log.error("文件上传失败: {}", e);
    }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }

    

}
