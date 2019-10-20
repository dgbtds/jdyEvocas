package cn.ihep.jdy.release.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@DynamicUpdate
@DynamicInsert
@ApiModel(value = "客户访问与转交表")
public class CustomerVisit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "所属公司Id")
    private Long companyId;

    @ApiModelProperty(value = "被转交置业顾问id")
    private Long fromUserId;

    @ApiModelProperty(value = "转交置业顾问id")
    private Long toUserId;

    @ApiModelProperty(value = "客户id")
    private Long customerId;

    @ApiModelProperty(value = "来访时是周几",example = "0")
    private Integer weekIndex;

    @ApiModelProperty(value = "来访时间，格式yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private java.sql.Timestamp time;

    @ApiModelProperty(value = "转交人，0 由管理转交，1由客户转交，其他客户未有转交",example = "0")
    private Integer status;

    @Override
    public String toString() {
        return "CustomerVisit{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                ", customerId=" + customerId +
                ", weekIndex=" + weekIndex +
                ", time=" + time +
                ", status=" + status +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }


    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }


    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getWeekIndex() {
        return weekIndex;
    }

    public void setWeekIndex(Integer weekIndex) {
        this.weekIndex = weekIndex;
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
}
