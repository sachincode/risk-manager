package com.sachin.risk.manager.model.event;


/**
 * @author shicheng.zhang
 * @since 17-7-13 下午2:49
 */
public class EventTypeParam {

    /** 主键id **/
    private long id;
    /** 事件类型编码 **/
    private String code;
    /** 事件类型名称 **/
    private String name;
    /** 描述 **/
    private String description;
    /** 事件类型:1同步2异步 **/
    private int type;
    /** 模块 **/
    private String module;
    /** 事件类型:1上线2下线 **/
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EventTypeParam{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", module='" + module + '\'' +
                ", status=" + status +
                '}';
    }
}
