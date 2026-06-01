package com.kk.api.quantization.controller.v;

import com.kk.business.quantization.dao.entity.Daily;
import com.kk.business.quantization.model.vo.FileSliceInfoVo;
import com.kk.business.quantization.service.executor.ITaskExecutor;
import com.kk.common.base.email.EmailSendMsg;
import com.kk.common.base.email.EmailUtil;
import com.kk.common.web.model.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kk.business.quantization.constant.ServiceSystemKey;
import com.kk.common.constant.SystemKey;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: kk
 * @Date: 2021/11/15 16:23
 */
@Tag(name = "/"+SystemKey.ApiPrefixLogin+"/v1/fileserver",description = "文件服务")
@ResponseBody
@RestController
@RequestMapping("/"+SystemKey.ApiPrefixLogin+"/v1/fileserver")
@Slf4j
public class FileServerController {

    @Resource(name="DailyTaskExecutor")
    public ITaskExecutor dailyTaskExecutor;
    @Resource
    public EmailUtil emailUtil;



    @Operation(summary = "sendEmailMsg")
    @Parameters(  {
            @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER, required = false)
    })
    @PostMapping("/sendEmailMsg")
    public ApiResult<List<Daily>> daily(@Valid @RequestBody EmailSendMsg vo) throws Exception {
        log.info("daily===================");
        emailUtil.sendMimeMail(vo);
        return new  ApiResult();

    }

    private static HashMap<String, FileSliceInfoVo> fileMap = new HashMap();
    // 模拟保存的位置
    private String savePath="E:\\data";
    @PostMapping(value = "/upload4slice")
    public void fileUpload(FileSliceInfoVo fileInfo, HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file"); // 通过参数名获取指定文件
        //创建分片
        File fileblob = new File(savePath+"\\"+fileInfo.getFileName()+"."+"blob");
        System.out.println("保存分片"+fileblob.getName());
        //保存分片  后面为true 表示在已有的文件进行追加。
        FileCopyUtils.copy(multipartFile.getInputStream(),new FileOutputStream(fileblob,true));
        //判断是否全部上传完成。
        if((fileInfo.getShardIndex()+1) == Math.ceil(fileInfo.getShardTotal())){
            System.out.println("上传完成，重命名");
            File saveFile = new File(savePath+"\\"+fileInfo.getFileName());
            //重命名操作
            fileblob.renameTo(saveFile);
        }
        // 类似保存到数据库，这里简化下，直接放到map中。
        fileMap.put(fileInfo.getHasdCode(),fileInfo);
    }
    @GetMapping("/getfile/{hashCode}")
    public FileSliceInfoVo getFileInfoByCode(@PathVariable("hashCode") String hashCode){
        return fileMap.get(hashCode);
    }
}
