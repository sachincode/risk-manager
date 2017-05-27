package com.sachin.risk.manager.controller.event;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.sachin.risk.common.core.enums.EncryptType;
import com.sachin.risk.common.core.model.EventTypeProperty;
import com.sachin.risk.manager.model.event.EventPropertyParam;
import com.sachin.risk.manager.model.event.EventTypePropertyParam;
import com.sachin.risk.manager.service.data.DictService;
import com.sachin.risk.manager.service.event.EventPropertyService;
import com.sachin.risk.manager.service.event.EventTypePropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.sachin.risk.common.core.model.EventType;
import com.sachin.risk.manager.model.HttpResult;
import com.sachin.risk.manager.model.PageModel;
import com.sachin.risk.manager.service.event.EventTypeService;
import com.sachin.risk.manager.util.CookieUtil;

/**
 * @author shicheng.zhang
 * @since 17-4-16 下午10:43
 */
@Controller
@RequestMapping("/event/typepro")
public class EventTypeProController {

    public static final Logger LOGGER = LoggerFactory.getLogger(EventTypeProController.class);

    @Resource
    private EventTypeService eventTypeService;
    @Resource
    private EventPropertyService eventPropertyService;
    @Resource
    private EventTypePropertyService eventTypePropertyService;
    @Resource
    private DictService dictService;

    @RequestMapping("query.do")
    @ResponseBody
    public ModelAndView query(@RequestParam(required = false, defaultValue = "1")int pageNum,
            @RequestParam(required = false, defaultValue = "10")int pageSize,
            @RequestParam(required = false)Long eventTypeId) {
        ModelAndView view = new ModelAndView("event/typepro/query");
        Map<String, Object> params = Maps.newHashMap();
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        params.put("start", (pageNum - 1) * pageSize);
        if (eventTypeId != null && eventTypeId > 0L) {
            params.put("eventTypeId", eventTypeId);
        }
        PageModel<EventTypeProperty> pageModel = eventTypePropertyService.pageQueryEventTypeProperty(params);
        view.addObject("pageModel", pageModel);
        view.addObject("eventTypes", eventTypeService.queryAllEventType());
        view.addObject("eventTypeId", eventTypeId);
        return view;
    }

    @RequestMapping("addPage.do")
    @ResponseBody
    public ModelAndView addPage() {
        ModelAndView view = new ModelAndView("event/typepro/add");
        view.addObject("eventTypes", eventTypeService.queryAllEventType());
        view.addObject("eventProperties", eventPropertyService.queryAllEventProperty());
        view.addObject("encryptTypes", EncryptType.values());
        view.addObject("dictTables", dictService.queryDictTableByType(1));
        return view;
    }

    @RequestMapping("add.do")
    @ResponseBody
    public HttpResult add(@RequestBody EventTypePropertyParam param, HttpServletRequest request) {
        LOGGER.info("add event typepro request param is: {}", param);
        try {
            String name = CookieUtil.getLoginUsername(request);
            Long id = eventTypePropertyService.addEventTypeProperty(transform(param), name);
            return HttpResult.success(id);
        } catch (Exception e) {
            LOGGER.error("add event typepro error.", e);
            return HttpResult.error(e.getMessage(), null);
        }
    }

    @RequestMapping("updatePage.do")
    @ResponseBody
    public ModelAndView updatePage(long id) {
        EventTypeProperty property = eventTypePropertyService.queryEventTypePropertyById(id);
        if (property == null) {
            throw new IllegalArgumentException("该事件类型不存在");
        }
        ModelAndView view = new ModelAndView("event/typepro/update");
        view.addObject("property", property);
        view.addObject("eventTypes", eventTypeService.queryAllEventType());
        view.addObject("eventProperties", eventPropertyService.queryAllEventProperty());
        view.addObject("encryptTypes", EncryptType.values());
        view.addObject("dictTables", dictService.queryDictTableByType(1));
        return view;
    }

    @RequestMapping("update.do")
    @ResponseBody
    public HttpResult update(@RequestBody EventTypePropertyParam param, HttpServletRequest request) {
        LOGGER.info("update event typepro request param is: {}", param);
        try {
            String name = CookieUtil.getLoginUsername(request);
            eventTypePropertyService.updateEventTypeProperty(transform(param), name);
            return HttpResult.success();
        } catch (Exception e) {
            LOGGER.error("update event typepro error.", e);
            return HttpResult.error(e.getMessage(), null);
        }
    }

    @RequestMapping("updateStatus.do")
    @ResponseBody
    public HttpResult updateStatus(long id, int status) {
        LOGGER.info("updateStatus event typepro request param id: {}, status: {}", id, status);
        try {
            int affect = eventTypePropertyService.updateEventTypePropertyStatus(id, status);
            return HttpResult.success(affect);
        } catch (Exception e) {
            LOGGER.error("updateStatus event typepro error.", e);
            return HttpResult.error(e.getMessage(), null);
        }
    }

    private EventTypeProperty transform(EventTypePropertyParam param) {
        EventTypeProperty property = new EventTypeProperty();
        property.setId(param.getId());
        property.setEventTypeId(param.getEventTypeId());
        property.setEventPropId(param.getEventPropId());
        property.setEncryptType(EncryptType.codeOf(param.getEncryptType()));
        property.setDictTableName(param.getDictTableName());
        property.setStatus(param.getStatus());
        property.setSortNumber(param.getSortNumber());
        return property;
    }

}
