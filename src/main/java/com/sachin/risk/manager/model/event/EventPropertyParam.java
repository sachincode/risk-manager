package com.sachin.risk.manager.model.event;

/**
 * @author shicheng.zhang
 * @since 17-5-25 下午3:27
 */
public class EventPropertyParam {

    /** 主键id **/
    private long id;
    /** 属性key **/
    private String propKey;
    /** 属性名 **/
    private String propName;
    /** 描述 **/
    private String description;
    /** 数据类型 **/
    private int dataType;
    /** 业务类型 **/
    private int busiType;
    /** 状态:1上线2下线 **/
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPropKey() {
        return propKey;
    }

    public void setPropKey(String propKey) {
        this.propKey = propKey;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getBusiType() {
        return busiType;
    }

    public void setBusiType(int busiType) {
        this.busiType = busiType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EventPropertyParam{" +
                "id=" + id +
                ", propKey='" + propKey + '\'' +
                ", propName='" + propName + '\'' +
                ", description='" + description + '\'' +
                ", dataType=" + dataType +
                ", busiType=" + busiType +
                ", status=" + status +
                '}';
    }
}
