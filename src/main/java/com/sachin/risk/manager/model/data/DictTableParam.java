package com.sachin.risk.manager.model.data;

/**
 * @author shicheng.zhang
 * @date 17-3-24
 * @time 上午11:43
 * @Description:
 */
public class DictTableParam {

    private long id;
    /** 字典表名 **/
    private String tableName;
    /** 字典表描述 **/
    private String tableDesc;
    /** 字典表类型:1.通用字典表2.列表字典表 **/
    private int tableType;
    /** 字典表中文名 **/
    private String tableNameCn;

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

    public String getTableDesc() {
        return tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }

    public int getTableType() {
        return tableType;
    }

    public void setTableType(int tableType) {
        this.tableType = tableType;
    }

    public String getTableNameCn() {
        return tableNameCn;
    }

    public void setTableNameCn(String tableNameCn) {
        this.tableNameCn = tableNameCn;
    }

    @Override
    public String toString() {
        return "DictTableParam{" +
                "tableName='" + tableName + '\'' +
                ", tableDesc='" + tableDesc + '\'' +
                ", tableType=" + tableType +
                ", tableNameCn='" + tableNameCn + '\'' +
                '}';
    }
}
