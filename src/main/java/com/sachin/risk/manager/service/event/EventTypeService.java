package com.sachin.risk.manager.service.event;

import com.sachin.risk.common.core.model.EventType;
import com.sachin.risk.manager.model.PageModel;

import java.util.List;
import java.util.Map;

/**
 * @author shicheng.zhang
 * @since 17-4-21 下午6:00
 */
public interface EventTypeService {

    PageModel<EventType> pageQueryEventType(Map<String, Object> params);

    EventType queryEventTypeById(long id);

    EventType queryEventTypeByCode(String code);

    List<EventType> queryAllEventType();

    void deleteEventType(long id);

    int updateEventTypeStatus(long id, int status);

    long addEventType(EventType eventType, String operator);

    int updateEventType(EventType eventType, String operator);
}
