package com.sachin.risk.manager.controller.event;

import com.google.common.collect.Maps;
import com.sachin.risk.common.core.model.EventType;
import com.sachin.risk.manager.model.HttpResult;
import com.sachin.risk.manager.model.PageModel;
import com.sachin.risk.manager.service.event.EventTypeService;
import com.sachin.risk.manager.util.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author shicheng.zhang
 * @since 17-4-16 下午10:43
 */
@Controller
@RequestMapping("/event/type")
public class EventTypeController {

    public static final Logger LOGGER = LoggerFactory.getLogger(EventTypeController.class);

    @Resource
    private EventTypeService eventTypeService;

    @RequestMapping("query.do")
    @ResponseBody
    public ModelAndView query(@RequestParam(required = false, defaultValue = "1")int pageNum,
            @RequestParam(required = false, defaultValue = "10")int pageSize) {
        ModelAndView view = new ModelAndView("event/type/query");
        Map<String, Object> params = Maps.newHashMap();
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        params.put("start", (pageNum - 1) * pageSize);
        PageModel<EventType> pageModel = eventTypeService.pageQueryEventType(params);
        view.addObject("pageModel", pageModel);
        return view;
    }

    @RequestMapping("addPage.do")
    @ResponseBody
    public ModelAndView addPage() {
        return new ModelAndView("event/type/add");
    }

    @RequestMapping("add.do")
    @ResponseBody
    public HttpResult add(@RequestBody EventType eventType, HttpServletRequest request) {
        LOGGER.info("add event type request param is: {}", eventType);
        try {
            String name = CookieUtil.getLoginUsername(request);
            Long id = eventTypeService.addEventType(eventType, name);
            return HttpResult.success(id);
        } catch (Exception e) {
            LOGGER.error("add event type error.", e);
            return HttpResult.error(e.getMessage(), null);
        }
    }

    @RequestMapping("updatePage.do")
    @ResponseBody
    public ModelAndView updatePage(long id) {
        EventType eventType = eventTypeService.queryEventTypeById(id);
        if (eventType == null) {
            throw new IllegalArgumentException("该事件类型不存在");
        }
        return new ModelAndView("event/type/update").addObject("eventType", eventType);
    }

    @RequestMapping("update.do")
    @ResponseBody
    public HttpResult update(@RequestBody EventType eventType, HttpServletRequest request) {
        LOGGER.info("update event type request param is: {}", eventType);
        try {
            String name = CookieUtil.getLoginUsername(request);
            eventTypeService.updateEventType(eventType, name);
            return HttpResult.success();
        } catch (Exception e) {
            LOGGER.error("update event type error.", e);
            return HttpResult.error(e.getMessage(), null);
        }
    }

    @RequestMapping("queryProperty.do")
    @ResponseBody
    public ModelAndView queryProperty(long id, @RequestParam(required = false, defaultValue = "1") int pageNum,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        EventType eventType = eventTypeService.queryEventTypeById(id);
        if (eventType == null) {
            throw new IllegalArgumentException("该事件类型不存在");
        }
        Map<String, Object> params = Maps.newHashMap();
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        params.put("start", (pageNum - 1) * pageSize);
        ModelAndView view = new ModelAndView("event/type/property").addObject("eventType", eventType);
        //PageModel<DictEntry> pageModel = dictService.pageQueryDictEntry(params);
        return view.addObject("pageModel", null);
    }

    @RequestMapping("updateStatus.do")
    @ResponseBody
    public HttpResult updateStatus(long id, int status) {
        LOGGER.info("updateStatus event type request param id: {}, status: {}", id, status);
        try {
            int affect = eventTypeService.updateEventTypeStatus(id, status);
            return HttpResult.success(affect);
        } catch (Exception e) {
            LOGGER.error("updateStatus event type error.", e);
            return HttpResult.error(e.getMessage(), null);
        }
    }

}
