package com.sachin.risk.manager.dao.event;

import java.util.List;
import java.util.Map;

import com.sachin.risk.common.core.model.EventTypeProperty;
import com.sachin.risk.manager.model.event.EventTypePropertyExt;
import org.apache.ibatis.annotations.Param;

import com.sachin.risk.common.core.model.EventType;

/**
 * @author shicheng.zhang
 * @since 17-4-21 下午6:02
 */
public interface EventTypePropertyMapper {

    public List<EventTypeProperty> queryByCondition(Map<String, Object> param);

    public List<EventTypePropertyExt> queryWithPage(Map<String, Object> param);

    public long count(Map<String, Object> param);

    public int add(EventTypeProperty property);

    public int delete(@Param("id") Long id);

    public int update(EventTypeProperty property);
}
