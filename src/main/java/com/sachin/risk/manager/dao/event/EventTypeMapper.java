package com.sachin.risk.manager.dao.event;

import com.sachin.risk.common.core.model.EventType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author shicheng.zhang
 * @since 17-4-21 下午6:02
 */
public interface EventTypeMapper {

    public List<EventType> queryByCondition(Map<String, Object> param);

    public List<EventType> queryWithPage(Map<String, Object> param);

    public long count(Map<String, Object> param);

    public int add(EventType eventType);

    public int delete(@Param("id") Long id);

    public int update(EventType eventType);
}
