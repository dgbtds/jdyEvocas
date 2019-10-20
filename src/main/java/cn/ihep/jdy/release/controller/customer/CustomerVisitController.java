package cn.ihep.jdy.release.controller.customer;

import cn.ihep.jdy.release.dao.model.ResultModel;
import cn.ihep.jdy.release.pojo.AiCustomer;
import cn.ihep.jdy.release.service.customer.CustomerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Api(
        tags={"客户访问接口"}
)
@RestController
@RequestMapping("/customer")
public class CustomerVisitController {
    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "客户到来")
    @ApiImplicitParam(name = "robotId",
            value = "robotId（String）值，为其sn号",
            required = true,
            paramType = "query",
            dataType = "String"
    )
    @ApiResponse(code = 200,message = "返回销售列表，长度--》最大为3最小1",response = AiCustomer.class)
    @RequestMapping(value = "/first",method = RequestMethod.POST)
    public ResultModel firstVisit(@RequestParam String robotId){
        return  customerService.firstVisit(robotId);
    }
    @ApiOperation(value = "客户选择一个销售")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name= "aiUserId",
                    value= "客户Id",
                    required= true,
                    paramType="query",
                    dataType="Long")
            ,
            @ApiImplicitParam(
                    name= "customerLogo",
                    value= "客户图像地址",
                    required= true,
                    paramType="query",
                    dataType="String")

    } )
    @RequestMapping(value = "/choseOne",method = RequestMethod.POST)
    public ResultModel choseone(@RequestParam Long aiUserId ,@RequestParam String customerLogo ){
        return  customerService.choseone(aiUserId,customerLogo);

    }
}
