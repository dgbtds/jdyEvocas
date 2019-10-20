package cn.ihep.jdy.release.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "accepter",uniqueConstraints = {
        @UniqueConstraint(columnNames = {
        "company_id","group_id","sort_index"})
        ,
        @UniqueConstraint(columnNames = {"user_id"})
        ,
        @UniqueConstraint(columnNames = {"up_logo"})

})
@ApiModel("置业顾问表")
public class AiUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "公司ID")
    @Column(name = "company_id")
    private Long companyId;

    @ApiModelProperty(value = "用户id")
    @Column(name = "user_id")
    private Long userId;

    @ApiModelProperty(value = "组内接待顺序，同公司同组唯一")
    @Column(name = "sort_index")
    private Long sortIndex;

    @ApiModelProperty(value = "转交数")
    private Long passCount;

    @ApiModelProperty(value = "被转交数")
    private Long loseCount;

    @ApiModelProperty(value = "接待客户次数")
    @Column(name = "distribute_count",columnDefinition = "Long default 0")
    private Long distributeCount;

    @ApiModelProperty(value = "可接待客户次数")
    @Column(name = "accept_times",columnDefinition = "Long default 3")
    private Long acceptTimes;

    @ApiModelProperty(value = "状态：0 在岗，其他离岗",example = "0")
    private Integer workStatus;

    @ApiModelProperty(value = "所属小组，默认0组未分组",example = "0")
    @Column(name = "group_id",columnDefinition = "int default 0")
    private int groupId;

    @ApiModelProperty(value = "销售客服头像路径")
    @Column(name = "up_logo")
    private String upLogo;


    public String getUpLogo() {
        return upLogo;
    }

    public void setUpLogo(String upLogo) {
        this.upLogo = upLogo;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(value = "销售客服评分")
    @Column(name = "score",columnDefinition = "int default 100")
    private Long score;

    @Transient
    private String name;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public Long getAcceptTimes() {
        return acceptTimes;
    }

    public void setAcceptTimes(Long acceptTimes) {

        this.acceptTimes = acceptTimes;
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


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public Long getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Long sortIndex) {
        this.sortIndex = sortIndex;
    }


    public Long getPassCount() {
        return passCount;
    }

    public void setPassCount(Long passCount) {
        this.passCount = passCount;
    }


    public Long getLoseCount() {
        return loseCount;
    }

    public void setLoseCount(Long loseCount) {
        this.loseCount = loseCount;
    }


    public Long getDistributeCount() {
        return distributeCount;
    }

    public void setDistributeCount(Long distributeCount) {
        this.distributeCount = distributeCount;
    }


    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

}
