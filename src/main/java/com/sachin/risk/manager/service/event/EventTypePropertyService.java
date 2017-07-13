package com.sachin.risk.manager.service.event;

import java.util.List;
import java.util.Map;

import com.sachin.risk.common.core.model.EventType;
import com.sachin.risk.common.core.model.EventTypeProperty;
import com.sachin.risk.manager.model.PageModel;
import com.sachin.risk.manager.model.event.EventTypePropertyExt;

/**
 * @author shicheng.zhang
 * @since 17-4-21 下午6:00
 */
public interface EventTypePropertyService {

    PageModel<EventTypePropertyExt> pageQueryEventTypeProperty(Map<String, Object> params);

    EventTypeProperty queryEventTypePropertyById(long id);

    EventTypeProperty queryEventTypeProperty(long eventTypeId, long eventPropId);

    void deleteEventTypeProperty(long id);

    int updateEventTypePropertyStatus(long id, int status);

    long addEventTypeProperty(EventTypeProperty property, String operator);

    int updateEventTypeProperty(EventTypeProperty property, String operator);
}
