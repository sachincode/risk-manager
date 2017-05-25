package com.sachin.risk.manager.service.event;

import java.util.Map;

import com.sachin.risk.common.core.model.EventProperty;
import com.sachin.risk.manager.model.PageModel;

/**
 * @author shicheng.zhang
 * @since 17-5-24 下午6:00
 */
public interface EventPropertyService {

    PageModel<EventProperty> pageQueryEventProperty(Map<String, Object> params);

    EventProperty queryEventPropertyById(long id);

    EventProperty queryEventPropertyByKey(String propKey);

    void deleteEventProperty(long id);

    int updateEventPropertyStatus(long id, int status);

    long addEventProperty(EventProperty eventProperty, String operator);

    int updateEventProperty(EventProperty eventProperty, String operator);
}
