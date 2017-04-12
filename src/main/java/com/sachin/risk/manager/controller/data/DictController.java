package com.sachin.risk.manager.controller.data;

import com.google.common.collect.Maps;
import com.sachin.risk.common.data.model.DictEntry;
import com.sachin.risk.common.data.model.DictTable;
import com.sachin.risk.manager.model.HttpResult;
import com.sachin.risk.manager.model.PageModel;
import com.sachin.risk.manager.model.data.DictEntryParam;
import com.sachin.risk.manager.model.data.DictLog;
import com.sachin.risk.manager.model.data.DictTableParam;
import com.sachin.risk.manager.service.data.DictService;
import com.sachin.risk.manager.util.CookieUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author shicheng.zhang
 * @date 17-3-24
 * @time 上午11:04
 * @Description:
 */
@Controller
@RequestMapping("/data/dict")
public class DictController {

    public static final Logger LOGGER = LoggerFactory.getLogger(DictController.class);

    @Resource
    private DictService dictService;

    // pageNum: 0 pageSize: 10 tableType: 2 keyWord: "reason" operateDetail operator tableName
    @RequestMapping("query.do")
    @ResponseBody
    public ModelAndView query(@RequestParam(required = false, defaultValue = "1")int index,
            @RequestParam(required = false, defaultValue = "1")int pageNum,
            @RequestParam(required = false, defaultValue = "10")int pageSize,
            @RequestParam(required = false)String keyWord, @RequestParam(required = false)String tableName,
            @RequestParam(required = false)String operator, @RequestParam(required = false)String operateDetail) {
        ModelAndView view = new ModelAndView("data/dict/query").addObject("index", index).addObject("keyWord", keyWord)
                .addObject("tableName", tableName).addObject("operator", operator).addObject("operateDetail", operateDetail);
        Map<String, Object> params = Maps.newHashMap();
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        params.put("start", (pageNum - 1) * pageSize);
        if (index == 1 || index == 2 || index == 3) {
            params.put("tableType", index);
            params.put("keyWord", keyWord);
            PageModel pageModel = dictService.pageQueryDictTable(params);
            view.addObject("pageModel", pageModel);
        } else if (index == 4) {
            params.put("tableName", tableName);
            params.put("operator", operator);
            params.put("operateDetail", operateDetail);
            PageModel pageModel = dictService.pageQueryDictLog(params);
            view.addObject("pageModel", pageModel);
        } else {
            throw new IllegalArgumentException("请求参数错误. index: " + index);
        }
        return view;
    }

    @RequestMapping("addTablePage.do")
    @ResponseBody
    public ModelAndView addTablePage() {
        return new ModelAndView("data/dict/addTable");
    }

    @RequestMapping("addTable.do")
    @ResponseBody
    public HttpResult addTable(@RequestBody DictTableParam dictTableParam, HttpServletRequest request) {
        LOGGER.info("add dict table request param is: {}", dictTableParam);
        try {
            String name = CookieUtil.getLoginUsername(request);
            Long id = dictService.addDictTable(dictTableParam, name);
            return HttpResult.success(id);
        } catch (Exception e) {
            LOGGER.error("add dict table error.", e);
            return HttpResult.error(e.getMessage(), null);
        }
    }

    // keyWords: "" pageNum: 0 pageSize: 10 tableId: 194
    @RequestMapping("queryEntry.do")
    @ResponseBody
    public ModelAndView queryEntry(long tableId, @RequestParam(required = false, defaultValue = "1")int pageNum,
            @RequestParam(required = false, defaultValue = "10")int pageSize,
            @RequestParam(required = false)String keyWords) {
        List<DictTable> dictTables = dictService.queryDictTableById(tableId);
        if (CollectionUtils.isEmpty(dictTables)) {
            throw new IllegalArgumentException("该字典表不存在");
        }
        Map<String, Object> params = Maps.newHashMap();
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        params.put("start", (pageNum - 1) * pageSize);
        params.put("keyWords", keyWords);
        PageModel<DictEntry> pageModel = dictService.pageQueryDictEntry(params);
        return new ModelAndView("data/dict/queryDict").addObject("table", dictTables.get(0))
                .addObject("pageModel", pageModel).addObject("keyWords", keyWords);
    }

    @RequestMapping("addEntryPage.do")
    @ResponseBody
    public ModelAndView addEntryPage(long tableId) {
        List<DictTable> dictTables = dictService.queryDictTableById(tableId);
        if (CollectionUtils.isEmpty(dictTables)) {
            throw new IllegalArgumentException("该字典表不存在");
        }
        return new ModelAndView("data/dict/addDict").addObject("dictTable", dictTables.get(0));
    }

    @RequestMapping("addEntry.do")
    @ResponseBody
    public HttpResult addEntry(@RequestBody DictEntryParam dictEntryParam, HttpServletRequest request) {
        LOGGER.info("add dict entry request param is: {}", dictEntryParam);
        try {
            String name = CookieUtil.getLoginUsername(request);
            dictService.addDictEntry(dictEntryParam, name);
            return HttpResult.success();
        } catch (Exception e) {
            LOGGER.error("add dict entry error.", e);
            return HttpResult.error(e.getMessage(), null);
        }
    }

    @RequestMapping("deleteEntry.do")
    @ResponseBody
    public HttpResult deleteEntry(long id, HttpServletRequest request) {
        LOGGER.info("delete dict entry request param is: {}", id);
        String name = CookieUtil.getLoginUsername(request);
        try {
            dictService.deleteDictEntry(id, name);
            return HttpResult.success();
        } catch (Exception e) {
            LOGGER.error("delete dict entry error.", e);
            return HttpResult.error(e.getMessage(), null);
        }
    }

    @RequestMapping("updateEntryPage.do")
    @ResponseBody
    public ModelAndView updateEntryPage(long id) {
        List<DictEntry> dictEntries = dictService.queryDictEntryByDictId(id);
        if (CollectionUtils.isEmpty(dictEntries)) {
            throw new IllegalArgumentException("该字典表条目不存在");
        }
        List<DictTable> dictTables = dictService.queryDictTableById(dictEntries.get(0).getTableId());
        if (CollectionUtils.isEmpty(dictTables)) {
            throw new IllegalArgumentException("该字典表不存在");
        }
        return new ModelAndView("data/dict/updateDict")
                .addObject("dictEntry", dictEntries.get(0))
                .addObject("dictTable", dictTables.get(0));
    }

    @RequestMapping("updateEntry.do")
    @ResponseBody
    public HttpResult updateEntry(@RequestBody DictEntryParam dictEntryParam, HttpServletRequest request) {
        LOGGER.info("update dict entry request param is: {}", dictEntryParam);
        String name = CookieUtil.getLoginUsername(request);
        try {
            dictService.updateSysDictDetail(dictEntryParam, name);
            return HttpResult.success();
        } catch (Exception e) {
            LOGGER.error("update dict entry error.", e);
            return HttpResult.error(e.getMessage(), null);
        }
    }

    @RequestMapping("importDictPage.do")
    @ResponseBody
    public ModelAndView importDictPage() {
        return new ModelAndView("data/dict/importfile");
    }

    @RequestMapping("importDict.do")
    @ResponseBody
    public HttpResult importDict(@RequestParam(name = "excelFile", required = true) MultipartFile excelFile,
            @RequestParam(name = "dictType", required = true) Integer dictType, HttpServletRequest request) {
        LOGGER.info("import dict detail request param is, dictType: {}, filename: {}", dictType,
                excelFile.getOriginalFilename());
        String name = CookieUtil.getLoginUsername(request);
        try {
            List<String> result = dictService.batchImportDict(excelFile, dictType, name);
            return HttpResult.success(result);
        } catch (Exception e) {
            LOGGER.error("import dict detail error.", e);
            return HttpResult.error(e.getMessage(), null);
        }
    }

    @RequestMapping("deleteTable.do")
    @ResponseBody
    public HttpResult deleteTable(long id, HttpServletRequest request) {
        LOGGER.info("delete dict table request param table id is: {}", id);
        String name = CookieUtil.getLoginUsername(request);
        try {
            dictService.deleteDictTable(id, name);
            return HttpResult.success();
        } catch (Exception e) {
            LOGGER.error("delete dict table error.", e);
            return HttpResult.error(e.getMessage(), null);
        }
    }

    @RequestMapping("updateTablePage.do")
    @ResponseBody
    public ModelAndView updateTablePage(long tableId) {
        List<DictTable> dictTables = dictService.queryDictTableById(tableId);
        if (CollectionUtils.isEmpty(dictTables)) {
            throw new IllegalArgumentException("该字典表不存在");
        }
        return new ModelAndView("data/dict/updateTable").addObject("dictTable", dictTables.get(0));
    }

    @RequestMapping("updateTable.do")
    @ResponseBody
    public HttpResult updateTable(@RequestBody DictTableParam dictTableParam, HttpServletRequest request) {
        LOGGER.info("update dict table request param table id is: {}", dictTableParam.getId());
        String name = CookieUtil.getLoginUsername(request);
        dictService.updateDictTable(dictTableParam, name);
        return HttpResult.success();
    }

}
