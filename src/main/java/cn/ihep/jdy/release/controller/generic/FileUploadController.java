package cn.ihep.jdy.release.controller.generic;


import cn.ihep.jdy.release.dao.model.ResultModel;
import cn.ihep.jdy.release.dao.pojoRepository.AiUserRepository;
import cn.ihep.jdy.release.pojo.AiUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Api( tags={"上传文件"})
public class FileUploadController {
    @Autowired
    AiUserRepository aiUserRepository;
private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);
    @ApiOperation(value = "置业顾问上传文件")
    @ApiImplicitParams({

         @ApiImplicitParam(
                 name = "aiUserId",
                 value ="置业顾问Id",
                 required = true,
                 paramType = "query",
                 dataType = "Long"
         )
    }
    )
    //上传文件必须指定参数名为file
    @RequestMapping(value ="/company/uploadUserLogo", method = RequestMethod.POST,headers = "content-type=multipart/form-data")
    public ResultModel uploadLogoUser(@RequestParam Long aiuserId,@RequestParam(value = "file") MultipartFile accepterPhoto ){

        AiUser byUserId = aiUserRepository.findByUserId(aiuserId);
        Long companyId=byUserId.getCompanyId();

        String prefixName = "D:"+File.separator+"img"+File.separator;
        //String path =prefixName;
        String path = prefixName +"company_"+ companyId+"_accepter_"+aiuserId+ File.separator +
                new SimpleDateFormat("yyyy-mm-dd").format(new Date());
        ResultModel resultModel = upload(accepterPhoto, path);
        if(resultModel.getStatus()==0){
            byUserId.setUpLogo(resultModel.getMessage());
            aiUserRepository.save(byUserId);
        }
        return  resultModel;
    }
    @ApiOperation(value = "客户上传文件")
    @ApiImplicitParams({

         @ApiImplicitParam(
                 name = "robotId",
                 value ="机器人Id",
                 required = true,
                 paramType = "query",
                 dataType = "String"
         )
    }
    )
    //上传文件必须指定参数名为file
    @RequestMapping(value ="/company/uploadCustomerLogo", method = RequestMethod.POST,headers = "content-type=multipart/form-data")
    public ResultModel uploadLogoRobot(@RequestParam String robotId,@RequestParam(value = "file") MultipartFile accepterPhoto ){
        Long aiuserId=7L;
        AiUser byUserId = aiUserRepository.findByUserId(aiuserId);
        Long companyId=byUserId.getCompanyId();

        String prefixName = "D:"+File.separator+"img"+File.separator;
        //String path =prefixName;
        String path = prefixName +"company_"+ companyId+"Customer"+aiuserId+ File.separator +
                new SimpleDateFormat("yyyy-mm-dd").format(new Date());
        ResultModel resultModel = upload(accepterPhoto, path);
        if(resultModel.getStatus()==0){
            byUserId.setUpLogo(resultModel.getMessage());
            aiUserRepository.save(byUserId);
        }
        return  resultModel;
    }
    public ResultModel upload(MultipartFile file,String filePath){
        if (file.isEmpty()) {
            return new ResultModel(1003,"上传失败，请选择文件");
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        File dir=new File(filePath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File dest = new File(filePath+ suffixName);
        try {
            file.transferTo(dest);
            LOGGER.info(dest+"上传成功");
            return  new ResultModel(0,filePath + suffixName);
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return new ResultModel(1003,"上传失败");
    }
}
