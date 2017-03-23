package com.sachin.risk.manager.model.system;

import java.util.Date;

/**
 * @author shicheng.zhang
 * @date 17-1-17
 * @time 下午3:18
 * @Description:
 */
public class User {
    /** 主键id **/
    private long id;
    /** 用户名 **/
    private String username;
    /** 昵称 **/
    private String nickname;
    /** 是否超级管理员 **/
    private int admin;
    /** 备注信息 **/
    private String remarks;
    /** 创建时间 **/
    private Date createdOn;
    /** 创建人 **/
    private String createdBy;
    /** 修改时间 **/
    private Date modifiedOn;
    /** 修改人 **/
    private String modifiedBy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
