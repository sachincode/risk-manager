package com.sachin.risk.manager.model.data;

import java.util.Date;

/**
 * @author shicheng.zhang
 * @date 17-3-23
 * @time 下午2:34
 * @Description:
 */
public class DictLog {

    private long id;
    /** 字典表名 **/
    private String tableName;
    /** 操作人 **/
    private String operator;
    /** 操作类型 1新增2修改3删除 **/
    private int operateType;
    /** 操作详情 **/
    private String operateDetail;
    /** 操作时间 **/
    private Date operateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getOperateType() {
        return operateType;
    }

    public void setOperateType(int operateType) {
        this.operateType = operateType;
    }

    public String getOperateDetail() {
        return operateDetail;
    }

    public void setOperateDetail(String operateDetail) {
        this.operateDetail = operateDetail;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    @Override
    public String toString() {
        return "DictLog{" +
                "id=" + id +
                ", tableName='" + tableName + '\'' +
                ", operator='" + operator + '\'' +
                ", operateType=" + operateType +
                ", operateDetail='" + operateDetail + '\'' +
                ", operateTime=" + operateTime +
                '}';
    }
}
