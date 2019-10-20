package cn.ihep.jdy.release.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "customer",uniqueConstraints ={
        @UniqueConstraint(columnNames = {"mobile","company_id"})

})
@ApiModel(value = "客户属性表")
public class AiCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//save方法后返回实体
    private Long id;

    @ApiModelProperty(value = "顾客姓名")
    private String name;

    @ApiModelProperty(value = "性别 0女  1男  2未知")
    private Integer sex;

    @ApiModelProperty(value = "年龄")
    private Long age;

    @ApiModelProperty(value = "访问公司")
    @Column(name = "company_id")
    private Long companyId;

    @ApiModelProperty(value = "手机号")
    @Column(name = "mobile")
    private String mobile;

    @ApiModelProperty(value = "现在地址")
    private String currentAddress;

    @Column(name = "customer_logo")
    @ApiModelProperty(value = "robot获取头像路径")
    private String customerLogo;

    @ApiModelProperty(value = "置业顾问上传顾客照片路径")
    @Column(name = "upload_pic")
    private String uploadPic;

    @ApiModelProperty(value = "专属置业顾问Id")
    @Column(name = "user_id")
    private Long counselorId;

    @ApiModelProperty(value = "工作地点")
    private String workAddress;

    @ApiModelProperty(value = "顾客买房侧重 1品牌 2地段 3户型 4园林 5其他",example = "1")
    private Integer buyCare;

    @ApiModelProperty(value = "顾客买房原因 0改善生活品质  1婚房 2其他",example = "1")
    private Integer buyCause;

    @ApiModelProperty(value = "顾客考虑买房面积")
    private String buyArea;

    @ApiModelProperty(value = "渠道来源")
    private String channelSource;

    @ApiModelProperty(value = "到访次数")
    private Long visitNum;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "录入时间")
    private Timestamp time;

    @ApiModelProperty(value = "0无效 其他有效",example = "0")
    private Integer status;

    @ApiModelProperty(value = "客户类型（0.普通客户,其他A类客户）",example = "0")
    private Integer type;

    @ApiModelProperty(value = "是否是首次购买（0.否，其他是）",example = "0")
    private Integer firstBuy;

    @ApiModelProperty(value = "顾客标签多个#号隔开")
    private String tag;

    @ApiModelProperty(value = "购房预算")
    private String budget;



    public Long getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(Long visitNum) {
        this.visitNum = visitNum;
    }

    public String getUploadPic() {
        return uploadPic;
    }

    public void setUploadPic(String uploadPic) {
        this.uploadPic = uploadPic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }


    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }


    public void setCounselorId(Long counselorId) {
        this.counselorId = counselorId;
    }

    public Long getCounselorId() {
        return counselorId;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }


    public String getCustomerLogo() {
        return customerLogo;
    }

    public void setCustomerLogo(String customerLogo) {
        this.customerLogo = customerLogo;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }


    public Integer getBuyCare() {
        return buyCare;
    }

    public void setBuyCare(Integer buyCare) {
        this.buyCare = buyCare;
    }


    public Integer getBuyCause() {
        return buyCause;
    }

    public void setBuyCause(Integer buyCause) {
        this.buyCause = buyCause;
    }


    public String getBuyArea() {
        return buyArea;
    }

    public void setBuyArea(String buyArea) {
        this.buyArea = buyArea;
    }


    public String getChannelSource() {
        return channelSource;
    }

    public void setChannelSource(String channelSource) {
        this.channelSource = channelSource;
    }


    public java.sql.Timestamp getTime() {
        return time;
    }

    public void setTime(java.sql.Timestamp time) {
        this.time = time;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Integer getFirstBuy() {
        return firstBuy;
    }

    public void setFirstBuy(Integer firstBuy) {
        this.firstBuy = firstBuy;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

}
