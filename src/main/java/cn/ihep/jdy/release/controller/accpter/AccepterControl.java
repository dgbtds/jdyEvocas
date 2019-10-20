package cn.ihep.jdy.release.controller.accpter;

import cn.ihep.jdy.release.dao.model.ResultModel;
import cn.ihep.jdy.release.pojo.AiCustomer;
import cn.ihep.jdy.release.pojo.CustomerFollowUp;
import cn.ihep.jdy.release.pojo.CustomerVisit;
import cn.ihep.jdy.release.service.user.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(
        tags={"销售行为接口"}
)
@RestController
@RequestMapping("/accepter")
public class AccepterControl {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "获得销售在岗与否属性")
    @ApiImplicitParam(
            name= "aiUserId",
            value= "置业顾问id",
            required= true,
            paramType="query",
            dataType="Long"
    )
    @RequestMapping(value = "/getWorkStatus",method = RequestMethod.POST)
    public ResultModel getWorkStatus( @RequestParam  Long aiUserId) {
        ResultModel result = userService.getWorkStatus(aiUserId);
        return result;
    }
    @ApiOperation(value = "修改销售在岗与否属性")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name= "aiUserId",
                    value= "置业顾问id",
                    required= true,
                    paramType="query",
                    dataType="Long")
            ,
            @ApiImplicitParam(
                    name= "status",
                    value= "置业顾问在岗与否,0在岗,1离岗,其他的错误",
                    required= true,
                    paramType="query",
                    dataType="int")

    } )
    @RequestMapping(value = "/workStatus",method = RequestMethod.POST)
    public ResultModel workStatus(@RequestParam  Long aiUserId,@RequestParam Integer status) {
        return   userService.workStatus(aiUserId,status);

    }
    @ApiOperation(value = "获得客户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name= "customerId",
                    value= "客户Id",
                    required= true,
                    paramType="query",
                    dataType="Long")
            ,
            @ApiImplicitParam(
                    name= "aiUserId",
                    value= "置业顾问id",
                    required= true,
                    paramType="query",
                    dataType="Long")
    })
    @ApiResponse(code = 200,message = "客户跟进记录",response = AiCustomer.class)
    @RequestMapping(value = "/getCustomerDetail",method = RequestMethod.POST)
    public ResultModel getMyCustomerDetail( @RequestParam Long customerId,@RequestParam Long aiUserId) {
        ResultModel result = userService.getMyCustomerDetail(customerId,aiUserId);
        return result;
    }

    @ApiOperation(value = "获得名下所有客户基本信息")
    @ApiImplicitParam(
            name= "aiUserId",
            value= "置业顾问id",
            required= true,
            paramType="query",
            dataType="Long")
    @RequestMapping(value = "/getCustomer",method = RequestMethod.POST)
    public ResultModel getMyCustomer( @RequestParam Long aiUserId) {
        ResultModel result = userService.getMyCustomer(aiUserId);
        return result;
    }
    @ApiOperation(value = "设置客户基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "aiCustomer",
                    value = "aiCustomer的json串，必需带有name，mobile,customerLogo属性",
                    required= true,
                    paramType="body",
                    dataType="AiCustomer"
            ),
            @ApiImplicitParam(
                    name= "aiUserId",
                    value= "置业顾问id",
                    required= true,
                    paramType="query",
                    dataType="Long")
    } )
    @RequestMapping(value = "/setCustomer",method = RequestMethod.POST)
    public ResultModel setCustomer( @RequestBody AiCustomer aiCustomer,@RequestParam Long aiUserId) {
        if(aiCustomer.getName()==null||aiCustomer.getMobile()==null||aiCustomer.getCustomerLogo()==null){
            return new ResultModel(1003);
        }
        return  userService.setCustomer(aiCustomer,aiUserId);
    }

    @ApiOperation(value = "增加客户跟进记录")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name= "customerId",
                    value= "客户Id",
                    required= true,
                    paramType="query",
                    dataType="Long")
            ,
            @ApiImplicitParam(
                    name= "aiUserId",
                    value= "置业顾问id",
                    required= true,
                    paramType="query",
                    dataType="Long")
            ,
            @ApiImplicitParam(
                    name= "content",
                    value= "跟进客户记录",
                    required= true,
                    paramType="query",
                    dataType="String")
    } )
    @RequestMapping(value = "/addCustomerRecord",method = RequestMethod.POST)
    public ResultModel addCustomerRecord( @RequestParam Long customerId,@RequestParam Long aiUserId,@RequestParam String content) {

        ResultModel result = userService.addCustomerRecord(customerId,aiUserId,content);
        return result;
    }

    @ApiOperation(value = "删除客户跟进记录")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name= "customerId",
                    value= "客户Id",
                    required= true,
                    paramType="query",
                    dataType="Long")
            ,
            @ApiImplicitParam(
                    name= "aiUserId",
                    value= "置业顾问id",
                    required= true,
                    paramType="query",
                    dataType="Long")
            ,
            @ApiImplicitParam(
                    name= "recordId",
                    value= "跟进记录id",
                    required= true,
                    paramType="query",
                    dataType="Long")
    } )
    @RequestMapping(value = "/delCustomerRecord",method = RequestMethod.POST)
    public ResultModel delCustomerRecord( @RequestParam Long customerId,@RequestParam Long aiUserId,@RequestParam Long recordId) {

        ResultModel result = userService.delCustomerRecord(customerId,aiUserId,recordId);
        return result;
    }
    @ApiOperation(value = "获得客户跟进记录")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name= "customerId",
                    value= "客户Id",
                    required= true,
                    paramType="query",
                    dataType="Long")
            ,
            @ApiImplicitParam(
                    name= "aiUserId",
                    value= "置业顾问id",
                    required= true,
                    paramType="query",
                    dataType="Long")

    } )
    @ApiResponse(code = 200,message = "所有的客户跟进记录",response = CustomerFollowUp.class)
    @RequestMapping(value = "/getCustomerRecord",method = RequestMethod.POST)
    public ResultModel getCustomerRecord( @RequestParam Long customerId,@RequestParam Long aiUserId) {

        ResultModel result = userService.getCustomerRecord(customerId,aiUserId);
        return result;
    }


    @ApiOperation(value = "获得客户访问记录")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name= "customerId",
                    value= "客户Id",
                    required= true,
                    paramType="query",
                    dataType="Long")
            ,
            @ApiImplicitParam(
                    name= "aiUserId",
                    value= "置业顾问id",
                    required= true,
                    paramType="query",
                    dataType="Long")

    } )
    @ApiResponse(code = 200,message = "客户访问记录",response = CustomerVisit.class)
    @RequestMapping(value = "/getReview",method = RequestMethod.POST)
    public ResultModel getReview( @RequestParam Long customerId,@RequestParam Long aiUserId) {

        ResultModel result = userService.getVisitReview(customerId,aiUserId);
        return result;
    }


}
