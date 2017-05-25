package com.sachin.risk.manager.dao.event;

import java.util.List;
import java.util.Map;

import com.sachin.risk.common.core.model.EventProperty;
import org.apache.ibatis.annotations.Param;

/**
 * @author shicheng.zhang
 * @since 17-5-24 下午6:02
 */
public interface EventPropertyMapper {

    public List<EventProperty> queryByCondition(Map<String, Object> param);

    public List<EventProperty> queryWithPage(Map<String, Object> param);

    public long count(Map<String, Object> param);

    public int add(EventProperty eventProperty);

    public int delete(@Param("id") Long id);

    public int update(EventProperty eventProperty);
}
