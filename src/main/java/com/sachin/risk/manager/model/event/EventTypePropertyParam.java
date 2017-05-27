package com.sachin.risk.manager.model.event;

/**
 * @author shicheng.zhang
 * @since 17-5-26 下午3:27
 */
public class EventTypePropertyParam {

    /** 主键id **/
    private long id;
    /** 事件类型id **/
    private long eventTypeId;
    /** 事件属性id **/
    private long eventPropId;
    /** 加密类型0无1phone2mail3bankno4personalid5passport **/
    private int encryptType;
    /** 排序序号 **/
    private int sortNumber;
    /** 字典表解码表名 **/
    private String dictTableName;
    /** 状态:1上线2下线 **/
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(long eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public long getEventPropId() {
        return eventPropId;
    }

    public void setEventPropId(long eventPropId) {
        this.eventPropId = eventPropId;
    }

    public int getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(int encryptType) {
        this.encryptType = encryptType;
    }

    public int getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(int sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getDictTableName() {
        return dictTableName;
    }

    public void setDictTableName(String dictTableName) {
        this.dictTableName = dictTableName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EventTypePropertyParam{" +
                "id=" + id +
                ", eventTypeId=" + eventTypeId +
                ", eventPropId=" + eventPropId +
                ", encryptType=" + encryptType +
                ", sortNumber=" + sortNumber +
                ", dictTableName='" + dictTableName + '\'' +
                ", status=" + status +
                '}';
    }
}
