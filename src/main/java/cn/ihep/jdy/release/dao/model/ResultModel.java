package cn.ihep.jdy.release.dao.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "返回响应数据")
public class ResultModel {
    @ApiModelProperty(value = "0成功，其他失败")
    private Integer status;
    @ApiModelProperty(value = "返回信息")
    private String message;
    @ApiModelProperty(value = "返回对象")
    private Object data;

    public ResultModel(Integer status) {
        this.status = status;
        this.message = ResultMessage.getMessage(status);
        this.data = "";
    }

    public ResultModel(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.data = "";
    }

    public ResultModel(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
