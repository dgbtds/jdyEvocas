package cn.ihep.jdy.release.pojo;

import javax.persistence.*;

@Entity
@Table(name = "alluser")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long agentId;
    private Long pid;

    private Long companyId;

    private String name;
    private String mobile;
    private String passWord;
    private Long roleId;
    private java.sql.Timestamp time;

    private Integer status;

    private Integer onlyCustomerFlag;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getOnlyCustomerFlag() {
        return onlyCustomerFlag;
    }

    public void setOnlyCustomerFlag(Integer onlyCustomerFlag) {
        this.onlyCustomerFlag = onlyCustomerFlag;
    }
}
