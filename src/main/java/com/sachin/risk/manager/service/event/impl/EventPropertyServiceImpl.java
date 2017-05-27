package com.sachin.risk.manager.service.event.impl;

import com.google.common.base.Preconditions;
import com.sachin.risk.common.core.model.EventProperty;
import com.sachin.risk.manager.dao.event.EventPropertyMapper;
import com.sachin.risk.manager.model.PageModel;
import com.sachin.risk.manager.service.event.EventPropertyService;
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
 * @since 17-5-24 下午6:33
 */
@Service
public class EventPropertyServiceImpl implements EventPropertyService {

    @Resource
    private EventPropertyMapper eventPropertyMapper;

    @Override
    public PageModel<EventProperty> pageQueryEventProperty(Map<String, Object> params) {
        long count = eventPropertyMapper.count(params);
        List<EventProperty> eventProperties = eventPropertyMapper.queryWithPage(params);
        PageModel<EventProperty> pageModel = new PageModel<>();
        pageModel.setList(eventProperties);
        pageModel.setPageNo((Integer) params.get("pageNum"));
        pageModel.setPageSize((Integer) params.get("pageSize"));
        pageModel.setTotalRecords((int) count);
        return pageModel;
    }

    @Override
    public EventProperty queryEventPropertyById(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        List<EventProperty> eventProperties = eventPropertyMapper.queryByCondition(params);
        if (CollectionUtils.isNotEmpty(eventProperties)) {
            return eventProperties.get(0);
        }
        return null;
    }

    @Override
    public EventProperty queryEventPropertyByKey(String propKey) {
        Preconditions.checkArgument(StringUtils.isNotBlank(propKey));
        Map<String, Object> params = new HashMap<>();
        params.put("propKey", propKey);
        List<EventProperty> eventProperties = eventPropertyMapper.queryByCondition(params);
        if (eventProperties.size() > 0) {
            return eventProperties.get(0);
        }
        return null;
    }

    @Override
    public List<EventProperty> queryAllEventProperty() {
        Map<String, Object> params = new HashMap<>();
        return eventPropertyMapper.queryByCondition(params);
    }

    @Override
    public void deleteEventProperty(long id) {
        eventPropertyMapper.delete(id);
    }

    @Override
    public int updateEventPropertyStatus(long id, int status) {
        if (id <= 0 || (status != 1 && status != 2)) {
            throw new IllegalArgumentException("请求参数错误");
        }
        EventProperty eventProperty = queryEventPropertyById(id);
        if (eventProperty == null) {
            throw new IllegalArgumentException("该事件属性不存在");
        }
        eventProperty.setStatus(status);
        return eventPropertyMapper.update(eventProperty);
    }

    @Override
    public long addEventProperty(EventProperty eventProperty, String operator) {
        EventProperty eventPropertyByKey = queryEventPropertyByKey(eventProperty.getPropKey());
        if (eventPropertyByKey != null) {
            throw new IllegalArgumentException("事件属性编码已存在：" + eventProperty.getPropKey());
        }
        eventProperty.setCreateBy(operator);
        eventProperty.setUpdateBy(operator);
        eventProperty.setCreateTime(new Date());
        eventProperty.setUpdateTime(new Date());
        eventPropertyMapper.add(eventProperty);
        return eventProperty.getId();
    }

    @Override
    public int updateEventProperty(EventProperty eventProperty, String operator) {
        EventProperty old = queryEventPropertyById(eventProperty.getId());
        if (old == null) {
            throw new IllegalArgumentException("事件属性不存在");
        }
        eventProperty.setUpdateBy(operator);
        eventProperty.setUpdateTime(new Date());
        return eventPropertyMapper.update(eventProperty);
    }
}
