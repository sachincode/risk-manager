package com.sachin.risk.manager.service.event.impl;

import com.google.common.base.Preconditions;
import com.sachin.risk.common.core.model.EventType;
import com.sachin.risk.manager.dao.event.EventTypeMapper;
import com.sachin.risk.manager.model.PageModel;
import com.sachin.risk.manager.service.event.EventTypeService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shicheng.zhang
 * @since 17-4-21 下午6:01
 */
@Service
public class EventTypeServiceImpl implements EventTypeService {

    @Resource
    private EventTypeMapper eventTypeMapper;

    @Override
    public PageModel<EventType> pageQueryEventType(Map<String, Object> params) {
        long count = eventTypeMapper.count(params);
        List<EventType> eventTypes = eventTypeMapper.queryWithPage(params);
        PageModel<EventType> pageModel = new PageModel<>();
        pageModel.setList(eventTypes);
        pageModel.setPageNo((Integer) params.get("pageNum"));
        pageModel.setPageSize((Integer) params.get("pageSize"));
        pageModel.setTotalRecords((int) count);
        return pageModel;
    }

    @Override
    public EventType queryEventTypeById(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        List<EventType> eventTypes = eventTypeMapper.queryByCondition(params);
        if (CollectionUtils.isNotEmpty(eventTypes)) {
            return eventTypes.get(0);
        }
        return null;
    }

    @Override
    public EventType queryEventTypeByCode(String code) {
        Preconditions.checkArgument(StringUtils.isNotBlank(code));
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        List<EventType> eventTypes = eventTypeMapper.queryByCondition(params);
        if (eventTypes.size() > 0) {
            return eventTypes.get(0);
        }
        return null;
    }

    @Override
    public void deleteEventType(long id) {
        eventTypeMapper.delete(id);
    }

    @Override
    public int updateEventTypeStatus(long id, int status) {
        if (id <= 0 || (status != 1 && status != 2)) {
            throw new IllegalArgumentException("请求参数错误");
        }
        EventType eventType = queryEventTypeById(id);
        if (eventType == null) {
            throw new IllegalArgumentException("该事件类型不存在");
        }
        eventType.setStatus(status);
        return eventTypeMapper.update(eventType);
    }

    @Override
    public long addEventType(EventType eventType, String operator) {
        EventType eventTypeByCode = queryEventTypeByCode(eventType.getCode());
        if (eventTypeByCode != null) {
            throw new IllegalArgumentException("事件编码已存在：" + eventType.getCode());
        }
        eventType.setCreateBy(operator);
        eventType.setUpdateBy(operator);
        eventType.setCreateTime(new Date());
        eventType.setUpdateTime(new Date());
        eventTypeMapper.add(eventType);
        return eventType.getId();
    }

    @Override
    public int updateEventType(EventType eventType, String operator) {
        EventType old = queryEventTypeById(eventType.getId());
        if (old == null) {
            throw new IllegalArgumentException("事件类型不存在");
        }
        eventType.setUpdateBy(operator);
        eventType.setUpdateTime(new Date());
        return eventTypeMapper.update(eventType);
    }
}
