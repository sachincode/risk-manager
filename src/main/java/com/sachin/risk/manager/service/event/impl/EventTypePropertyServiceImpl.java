package com.sachin.risk.manager.service.event.impl;

import com.sachin.risk.common.core.model.EventTypeProperty;
import com.sachin.risk.manager.dao.event.EventTypePropertyMapper;
import com.sachin.risk.manager.model.PageModel;
import com.sachin.risk.manager.model.event.EventTypePropertyExt;
import com.sachin.risk.manager.service.event.EventTypePropertyService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shicheng.zhang
 * @since 17-5-26 下午5:18
 */
@Service
public class EventTypePropertyServiceImpl implements EventTypePropertyService {

    @Resource
    private EventTypePropertyMapper eventTypePropertyMapper;

    @Override
    public PageModel<EventTypePropertyExt> pageQueryEventTypeProperty(Map<String, Object> params) {
        long count = eventTypePropertyMapper.count(params);
        List<EventTypePropertyExt> typeProperties = eventTypePropertyMapper.queryWithPage(params);
        PageModel<EventTypePropertyExt> pageModel = new PageModel<>();
        pageModel.setList(typeProperties);
        pageModel.setPageNo((Integer) params.get("pageNum"));
        pageModel.setPageSize((Integer) params.get("pageSize"));
        pageModel.setTotalRecords((int) count);
        return pageModel;
    }

    @Override
    public EventTypeProperty queryEventTypePropertyById(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        List<EventTypeProperty> typeProperties = eventTypePropertyMapper.queryByCondition(params);
        if (CollectionUtils.isNotEmpty(typeProperties)) {
            return typeProperties.get(0);
        }
        return null;
    }

    @Override
    public EventTypeProperty queryEventTypeProperty(long eventTypeId, long eventPropId) {
        Map<String, Object> params = new HashMap<>();
        params.put("eventTypeId", eventTypeId);
        params.put("eventPropId", eventPropId);
        List<EventTypeProperty> typeProperties = eventTypePropertyMapper.queryByCondition(params);
        if (CollectionUtils.isNotEmpty(typeProperties)) {
            return typeProperties.get(0);
        }
        return null;
    }

    @Override
    public void deleteEventTypeProperty(long id) {
        eventTypePropertyMapper.delete(id);
    }

    @Override
    public int updateEventTypePropertyStatus(long id, int status) {
        if (id <= 0 || (status != 1 && status != 2)) {
            throw new IllegalArgumentException("请求参数错误");
        }
        EventTypeProperty property = queryEventTypePropertyById(id);
        if (property == null) {
            throw new IllegalArgumentException("该事件类型属性不存在");
        }
        property.setStatus(status);
        return eventTypePropertyMapper.update(property);
    }

    @Override
    public long addEventTypeProperty(EventTypeProperty property, String operator) {
        EventTypeProperty typeProperty = queryEventTypeProperty(property.getEventTypeId(), property.getEventPropId());
        if (typeProperty != null) {
            throw new IllegalArgumentException("事件类型属性已存在");
        }
        property.setCreateBy(operator);
        property.setUpdateBy(operator);
        property.setCreateTime(new Date());
        property.setUpdateTime(new Date());
        eventTypePropertyMapper.add(property);
        return property.getId();
    }

    @Override
    public int updateEventTypeProperty(EventTypeProperty property, String operator) {
        EventTypeProperty old = queryEventTypePropertyById(property.getId());
        if (old == null) {
            throw new IllegalArgumentException("事件类型属性不存在");
        }
        property.setUpdateBy(operator);
        property.setUpdateTime(new Date());
        return eventTypePropertyMapper.update(property);
    }
}
