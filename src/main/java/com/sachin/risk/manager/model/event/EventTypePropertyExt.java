package com.sachin.risk.manager.model.event;

import com.sachin.risk.common.core.model.EventTypeProperty;

/**
 * @author shicheng.zhang
 * @since 17-7-13 下午3:37
 */
public class EventTypePropertyExt extends EventTypeProperty {

    /** 事件类型编码 **/
    private String code;
    /** 事件类型名称 **/
    private String name;

    /** 属性key **/
    private String propKey;
    /** 属性名 **/
    private String propName;

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


}
