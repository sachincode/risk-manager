package com.sachin.risk.manager.controller.event;

import com.google.common.collect.Maps;
import com.sachin.risk.common.core.enums.BusiType;
import com.sachin.risk.common.core.enums.DataType;
import com.sachin.risk.common.core.model.EventProperty;
import com.sachin.risk.manager.model.HttpResult;
import com.sachin.risk.manager.model.PageModel;
import com.sachin.risk.manager.model.event.EventPropertyParam;
import com.sachin.risk.manager.service.event.EventPropertyService;
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
 * @since 17-5-23 下午10:43
 */
@Controller
@RequestMapping("/event/property")
public class EventPropertyController {

    public static final Logger LOGGER = LoggerFactory.getLogger(EventPropertyController.class);

    @Resource
    private EventPropertyService eventPropertyService;

    @RequestMapping("query.do")
    @ResponseBody
    public ModelAndView query(@RequestParam(required = false, defaultValue = "1")int pageNum,
            @RequestParam(required = false, defaultValue = "10")int pageSize) {
        ModelAndView view = new ModelAndView("event/property/query");
        Map<String, Object> params = Maps.newHashMap();
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        params.put("start", (pageNum - 1) * pageSize);
        PageModel<EventProperty> pageModel = eventPropertyService.pageQueryEventProperty(params);
        view.addObject("pageModel", pageModel);
        return view;
    }

    @RequestMapping("addPage.do")
    @ResponseBody
    public ModelAndView addPage() {
        ModelAndView view = new ModelAndView("event/property/add");
        view.addObject("dataTypes", DataType.values());
        view.addObject("busiTypes", BusiType.values());
        return view;
    }

    @RequestMapping("add.do")
    @ResponseBody
    public HttpResult add(@RequestBody EventPropertyParam eventProperty, HttpServletRequest request) {
        LOGGER.info("add event property request param is: {}", eventProperty);
        try {
            String name = CookieUtil.getLoginUsername(request);
            Long id = eventPropertyService.addEventProperty(transform(eventProperty), name);
            return HttpResult.success(id);
        } catch (Exception e) {
            LOGGER.error("add event property error.", e);
            return HttpResult.error(e.getMessage(), null);
        }
    }

    @RequestMapping("updatePage.do")
    @ResponseBody
    public ModelAndView updatePage(long id) {
        EventProperty eventProperty = eventPropertyService.queryEventPropertyById(id);
        if (eventProperty == null) {
            throw new IllegalArgumentException("该事件属性不存在");
        }
        return new ModelAndView("event/property/update")
                .addObject("eventProperty", eventProperty)
                .addObject("dataTypes", DataType.values())
                .addObject("busiTypes", BusiType.values());
    }

    @RequestMapping("update.do")
    @ResponseBody
    public HttpResult update(@RequestBody EventPropertyParam eventProperty, HttpServletRequest request) {
        LOGGER.info("update event property request param is: {}", eventProperty);
        try {
            String name = CookieUtil.getLoginUsername(request);
            eventPropertyService.updateEventProperty(transform(eventProperty), name);
            return HttpResult.success();
        } catch (Exception e) {
            LOGGER.error("update event property error.", e);
            return HttpResult.error(e.getMessage(), null);
        }
    }

    @RequestMapping("queryEvent.do")
    @ResponseBody
    public ModelAndView queryProperty(long id, @RequestParam(required = false, defaultValue = "1") int pageNum,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        EventProperty eventProperty = eventPropertyService.queryEventPropertyById(id);
        if (eventProperty == null) {
            throw new IllegalArgumentException("该事件属性不存在");
        }
        Map<String, Object> params = Maps.newHashMap();
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        params.put("start", (pageNum - 1) * pageSize);
        ModelAndView view = new ModelAndView("event/property/event").addObject("eventProperty", eventProperty);
        //PageModel<DictEntry> pageModel = dictService.pageQueryDictEntry(params);
        return view.addObject("pageModel", null);
    }

    @RequestMapping("updateStatus.do")
    @ResponseBody
    public HttpResult updateStatus(long id, int status) {
        LOGGER.info("updateStatus event property request param id: {}, status: {}", id, status);
        try {
            int affect = eventPropertyService.updateEventPropertyStatus(id, status);
            return HttpResult.success(affect);
        } catch (Exception e) {
            LOGGER.error("updateStatus event property error.", e);
            return HttpResult.error(e.getMessage(), null);
        }
    }

    private EventProperty transform(EventPropertyParam param) {
        EventProperty property = new EventProperty();
        property.setId(param.getId());
        property.setPropKey(param.getPropKey());
        property.setPropName(param.getPropName());
        property.setDescription(param.getDescription());
        property.setDataType(DataType.codeOf(param.getDataType()));
        property.setBusiType(BusiType.codeOf(param.getBusiType()));
        property.setStatus(param.getStatus());
        return property;
    }

}
