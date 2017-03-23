package com.sachin.risk.manager.model.system;

import java.util.Date;

/**
 * @author shicheng.zhang
 * @date 17-3-19
 * @time 下午10:17
 * @Description:
 */
public class Menu {

    /** 主键id **/
    private long id;
    /** 菜单名 **/
    private String menuName;
    /** url **/
    private String url;
    /** 排序编号 **/
    private int sortNumber;
    /** 父菜单id **/
    private long parentMenuId;
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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(int sortNumber) {
        this.sortNumber = sortNumber;
    }

    public long getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(long parentMenuId) {
        this.parentMenuId = parentMenuId;
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

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", url='" + url + '\'' +
                ", sortNumber=" + sortNumber +
                ", parentMenuId=" + parentMenuId +
                ", remarks='" + remarks + '\'' +
                ", createdOn=" + createdOn +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedOn=" + modifiedOn +
                ", modifiedBy='" + modifiedBy + '\'' +
                '}';
    }
}
