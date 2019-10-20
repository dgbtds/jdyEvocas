package cn.ihep.jdy.release.controller.manager;

import cn.ihep.jdy.release.dao.model.ResultModel;
import cn.ihep.jdy.release.service.ManagerService.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Api(
        tags={"管理行为接口"}
)
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @ApiOperation( value = "转交：将一个置业顾问的客户全部转交给另一个置业顾问" )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name= "managerId",
                    value= "管理id",
                    required= true,
                    paramType="query",
                    dataType="Long")
            ,
            @ApiImplicitParam(
                    name= "fromaiUserId",
                    value= "被转交者id",
                    required= true,
                    paramType="query",
                    dataType="Long")
            ,
            @ApiImplicitParam(
                    name= "toaiUserId",
                    value= "转交人",
                    required= true,
                    paramType="query",
                    dataType="Long")
    } )
    @RequestMapping(value ="/transfer",method = RequestMethod.POST)
    public  ResultModel transfer( @RequestParam  Long managerId,@RequestParam  Long fromaiUserId,@RequestParam  Long toaiUserId){

        return  managerService.transfer(managerId,fromaiUserId,toaiUserId);

    }
    @ApiOperation(
            value = "删除一个组，组内人员回到默认未分组状态"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name= "managerId",
                    value= "管理id",
                    required= true,
                    paramType="query",
                    dataType="Long")
            ,
            @ApiImplicitParam(
                    name= "groupId",
                    value= "组号",
                    required= true,
                    paramType="query",
                    dataType="Integer")

    } )
    @RequestMapping(value="/delGroup",method = RequestMethod.POST)
    public ResultModel delGroup(@RequestParam Long managerId,@RequestParam Integer groupId ){
        if(groupId<1){
            return  new ResultModel(1003);
        }
        return managerService.deleteGroup(managerId,groupId);
    }
    @ApiOperation(
            value = "修改一个置业顾问的组号"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name= "managerId",
                    value= "管理id",
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
                    name= "groupId",
                    value= "置业顾问组号",
                    required= true,
                    paramType="query",
                    dataType="Integer")
    } )
    @RequestMapping(value = "/changeGroup",method = RequestMethod.POST)
    public ResultModel changeGroup(@RequestParam Long managerId,@RequestParam Long aiUserId,@RequestParam Integer groupId ){
        if(groupId<0){
            return  new ResultModel(1003);
        }
        return managerService.changeGroup(managerId,aiUserId,groupId);
    }
    @ApiOperation(
            value = "修改一组销售的组号"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name= "managerId",
                    value= "管理id",
                    required= true,
                    paramType="query",
                    dataType="Long")
            ,
            @ApiImplicitParam(
                    name= "aiUserIds",
                    value= "置业顾问id的数组",
                    required= true,
                    paramType="query",
                    allowMultiple=true,//数组认定
                    dataType="Long")
            ,
            @ApiImplicitParam(
                    name= "groupId",
                    value= "置业顾问组号",
                    required= true,
                    paramType="query",
                    dataType="Integer")
    } )
    @RequestMapping(value="/changeGroups",method = RequestMethod.POST)
    public ResultModel changeGroups(@RequestParam Long managerId,@RequestParam Long[] aiUserIds,@RequestParam Integer groupId ){
        return managerService.changeGrouplist(managerId,aiUserIds, groupId);
    }
    @ApiOperation(
            value = "修改一个销售的组内接待顺序（不是组内唯一会失败）"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name= "managerId",
                    value= "管理id",
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
                    name= "index",
                    value= "置业顾问接待顺序",
                    required= true,
                    paramType="query",
                    dataType="Long")
    } )
    @RequestMapping(value="/setIndex",method = RequestMethod.POST)
    public ResultModel setIndex(@RequestParam Long managerId,@RequestParam Long aiUserId,@RequestParam Long index  ){
        return managerService.setIndex( managerId,  aiUserId,  index);
    }
    @ApiOperation(
            value = "修改一组销售的组内接待顺序（不是组内唯一会失败）"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name= "managerId",
                    value= "管理id",
                    required= true,
                    paramType="query",
                    dataType="Long")
            ,
            @ApiImplicitParam(
                    name= "aiUserIds",
                    value= "置业顾问id",
                    required= true,
                    allowMultiple=true,//数组认定
                    paramType="query",
                    dataType="Long")
            ,
            @ApiImplicitParam(
                    name= "indexs",
                    value= "置业顾问接待顺序",
                    required= true,
                    allowMultiple=true,//数组认定
                    paramType="query",
                    dataType="Long")
    } )
    @RequestMapping(value="/setIndexList",method = RequestMethod.POST)
    public ResultModel setIndexList(@RequestParam Long managerId,@RequestParam Long[] aiUserIds,@RequestParam Long[] indexs ){

        return managerService.setIndexlist( managerId,  aiUserIds,  indexs);
    }
    @ApiOperation(
            value = "修改一个销售的接待次数"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name= "managerId",
                    value= "管理id",
                    required= true,
                    paramType="query",
                    dataType="Long")
            ,
            @ApiImplicitParam(
                    name= "aiUserId",
                    value= "置业顾问id",
                    required= true,
                    allowMultiple=false,//数组认定
                    paramType="query",
                    dataType="Long")
            ,
            @ApiImplicitParam(
                    name= "count",
                    value= "置业顾问接待次数",
                    required= true,
                    allowMultiple=false,//数组认定
                    paramType="query",
                    dataType="Long")
    } )
    @RequestMapping(value="/setTimes",method = RequestMethod.POST)
    public ResultModel setAcceptTimes(@RequestParam Long managerId,@RequestParam Long aiUserId,@RequestParam Long count){
        if(count<0){
            return  new ResultModel(1003);
        }
        return managerService.setTimes( managerId,  aiUserId,  count);
    }
    @ApiOperation(
            value = "修改一组销售的接待次数"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name= "managerId",
                    value= "管理id",
                    required= true,
                    paramType="query",
                    dataType="Long")
            ,
            @ApiImplicitParam(
                    name= "aiUserIds",
                    value= "置业顾问id",
                    required= true,
                    allowMultiple=true,//数组认定
                    paramType="query",
                    dataType="Long")
            ,
            @ApiImplicitParam(
                    name= "counts",
                    value= "置业顾问接待次数",
                    required= true,
                    allowMultiple=true,//数组认定
                    paramType="query",
                    dataType="Long")
    } )
    @RequestMapping(value="/setTimesList",method = RequestMethod.POST)
    public ResultModel setAcceptsTimes(@RequestParam Long managerId,@RequestParam Long[] aiUserIds,@RequestParam Long[] counts ){

        return managerService. setTimesList(managerId,  aiUserIds,  counts);
    }
    @ApiOperation(
            value = "获得一个组所有置业顾问信息"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name= "managerId",
                    value= "管理id",
                    required= true,
                    paramType="query",
                    dataType="Long")
            ,
            @ApiImplicitParam(
                    name= "groupId",
                    value= "组号",
                    required= true,
                    allowMultiple=false,//数组认定
                    paramType="query",
                    dataType="Integer")
    } )
    @RequestMapping(value="/getGroupById",method = RequestMethod.POST)
    public ResultModel getGroupbyCompanyAndgroupId(@RequestParam Long managerId,@RequestParam Integer groupId ){
        if(groupId<0){
            return  new ResultModel(1003);
        }
        return managerService. getGroupbyCompanyAndGroupId( managerId,groupId) ;
    }
    @ApiOperation(
            value = "获得所有组信息"
    )
    @ApiImplicitParam(
            name= "managerId",
            value= "管理id",
            required= true,
            paramType="query",
            dataType="Long")
    @RequestMapping(value="/getGroup",method = RequestMethod.POST)
    public ResultModel getGroupbyCompany(@RequestParam Long managerId){

        return managerService. getGroupbyCompany( managerId) ;
    }
    @ApiOperation(
            value = "获得公司所有客户列表"
    )
    @ApiImplicitParam(
            name= "managerId",
            value= "管理id",
            required= true,
            paramType="query",
            dataType="Long")
    @RequestMapping(value="/getCustomerList",method = RequestMethod.POST)
    public ResultModel getCustomerList(@RequestParam Long managerId ){

        return managerService. getCustomerList( managerId) ;
    }
    @ApiOperation(
            value = "设置销售在岗状态"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name= "managerId",
                    value= "管理id",
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
                    name= "status",
                    value= "置业顾问在岗与否",
                    required= true,
                    paramType="query",
                    dataType="int")
    } )
    @RequestMapping(value = "/setWorkStatus",method = RequestMethod.POST)
    public ResultModel setWorkStatus(@RequestParam Long managerId,@RequestParam Long aiUserId,@RequestParam Integer status) {
        if(status<0){
            return new ResultModel(1003);
        }
        ResultModel resultModel = managerService.setworkStatus(managerId,aiUserId,status);
        return  resultModel;
    }
    @ApiOperation(
            value = "获得所有转交历史,status:0为主动转交，1为被动转交,其他未转交"
    )
    @ApiImplicitParam(
            name= "managerId",
            value= "管理id",
            required= true,
            paramType="query",
            dataType="Long")
    @RequestMapping(value = "/getPassHistory",method = RequestMethod.POST)
    public ResultModel getPassHistory(@RequestParam Long managerId) {

        ResultModel resultModel = managerService.getPassHistory(managerId);
        return  resultModel;
    }

}
