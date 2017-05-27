package com.sachin.risk.manager.service.data.impl;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sachin.risk.common.data.model.DictEntry;
import com.sachin.risk.common.data.model.DictTable;
import com.sachin.risk.manager.dao.data.DictEntryMapper;
import com.sachin.risk.manager.dao.data.DictLogMapper;
import com.sachin.risk.manager.dao.data.DictTableMapper;
import com.sachin.risk.manager.model.PageModel;
import com.sachin.risk.manager.model.data.DictEntryParam;
import com.sachin.risk.manager.model.data.DictLog;
import com.sachin.risk.manager.model.data.DictTableParam;
import com.sachin.risk.manager.service.data.DictService;
import com.sachin.risk.manager.util.ExcelUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author shicheng.zhang
 * @date 17-3-23
 * @time 下午2:37
 * @Description:
 */
@Service
public class DictServiceImpl implements DictService {

    @Resource
    private DictTableMapper dictTableMapper;
    @Resource
    private DictLogMapper dictLogMapper;
    @Resource
    private DictEntryMapper dictEntryMapper;

    private static final int ADD = 1;
    private static final int UPDATE = 2;
    private static final int DELETE = 3;
    private static final Joiner JOINER = Joiner.on(",").skipNulls();
    private static final Joiner LINE_JOINER = Joiner.on("|").skipNulls();
    public static final Logger LOGGER = LoggerFactory.getLogger(DictServiceImpl.class);

    @Override
    public PageModel<DictTable> pageQueryDictTable(Map<String, Object> params) {
        long count = dictTableMapper.count(params);
        List<DictTable> dictTables = dictTableMapper.queryWithPage(params);
        PageModel<DictTable> pageModel = new PageModel<>();
        pageModel.setList(dictTables);
        pageModel.setPageNo((Integer) params.get("pageNum"));
        pageModel.setPageSize((Integer) params.get("pageSize"));
        pageModel.setTotalRecords((int) count);
        return pageModel;
    }

    @Override
    public Long addDictTable(DictTableParam dictTableParam, String operator) throws Exception {
        List<DictTable> dictTables = queryDictTableByTableName(dictTableParam.getTableName());
        if (dictTables.size() > 0) {
            throw new Exception("该字典表名已存在: " + dictTableParam.getTableName());
        }
        DictTable dictTable = doAddDictTable(dictTableParam.getTableName(), dictTableParam.getTableNameCn(),
                dictTableParam.getTableDesc(), dictTableParam.getTableType(), operator);
        addDictLog(dictTableParam.getTableName(), operator, ADD, "新建字典表 " + dictTableParam.getTableName());
        return dictTable.getId();
    }

    private DictTable doAddDictTable(String tableName, String tableNameCn, String tableDesc, int tableType,
            String operator) {
        DictTable dictTable = new DictTable();
        dictTable.setTableName(tableName);
        dictTable.setTableDesc(tableDesc);
        dictTable.setTableType(tableType);
        dictTable.setCreateBy(operator);
        dictTable.setUpdateBy(operator);
        dictTable.setCreateTime(new Date());
        dictTable.setTableNameCn(tableNameCn);
        dictTableMapper.add(dictTable);
        return dictTable;
    }

    @Override
    public List<DictTable> queryDictTableByTableName(String tableName) {
        Preconditions.checkNotNull(tableName);
        Map<String, Object> params = Maps.newHashMap();
        params.put("tableName", tableName);
        return dictTableMapper.queryByCondition(params);
    }

    @Override
    public List<DictTable> queryDictTableById(long id) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("id", id);
        return dictTableMapper.queryByCondition(params);
    }

    @Override
    public List<DictTable> queryDictTableByType(int tableType) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("tableType", tableType);
        return dictTableMapper.queryByCondition(params);
    }

    @Override
    public void updateDictTable(DictTableParam dictTableParam, String operator) {
        List<DictTable> tables = queryDictTableByTableName(dictTableParam.getTableName());
        DictTable dictTable = doUpdateDictTable(dictTableParam, operator);
        addDictLog(dictTable.getTableName(), operator, UPDATE, makeUpdateTable(tables.get(0), dictTable));
    }

    private String makeUpdateTable(DictTable oldTable, DictTable newTable) {
        List<String> list = Lists.newArrayList();
        if (!oldTable.getTableNameCn().equals(newTable.getTableNameCn())) {
            list.add("名称(" + oldTable.getTableNameCn() + "->" + newTable.getTableNameCn() + ")");
        }
        if (!oldTable.getTableDesc().equals(newTable.getTableDesc())) {
            list.add("描述(" + oldTable.getTableDesc() + "->" + newTable.getTableDesc() + ")");
        }
        return JOINER.join(list);
    }

    private DictTable doUpdateDictTable(DictTableParam dictTableParam, String operator) {
        Preconditions.checkNotNull(dictTableParam.getId());
        DictTable param = new DictTable();
        param.setId(dictTableParam.getId());
        param.setTableName(dictTableParam.getTableName());
        param.setUpdateBy(operator);
        param.setTableDesc(dictTableParam.getTableDesc());
        param.setTableNameCn(dictTableParam.getTableNameCn());
        dictTableMapper.update(param);
        return param;
    }

    private void doUpdateDictTableTime(long tableId, String operator) {
        DictTable param = new DictTable();
        param.setId(tableId);
        param.setUpdateBy(operator);
        param.setUpdateTime(new Date());
        dictTableMapper.update(param);
    }

    @Override
    public void deleteDictTable(long id, String operator) throws Exception {
        List<DictTable> dictTables = queryDictTableById(id);
        if (dictTables.size() <= 0) {
            throw new Exception("该字典表不存在, id: " + id);
        }
        dictTableMapper.delete(id);
        dictEntryMapper.deleteByTableId(id);
        addDictLog(dictTables.get(0).getTableName(), operator, DELETE, "删除字典表 " + dictTables.get(0).getTableName());
    }



    @Override
    public void addDictEntry(DictEntryParam dictEntryParam, String operator) throws Exception {
        Preconditions.checkNotNull(operator);
        Preconditions.checkNotNull(dictEntryParam.getTableId());
        if (StringUtils.isBlank(dictEntryParam.getDictCode()) || StringUtils.isBlank(dictEntryParam.getDictName())) {
            throw new Exception("编码和编码名不能为空");
        }
        List<DictEntry> dictEntries = queryDictEntryByTableIdCode(dictEntryParam.getTableId(),
                dictEntryParam.getDictCode());
        if (dictEntries.size() > 0) {
            throw new Exception("该表名-编码已经存在: " + dictEntryParam.getTableName() + "-" + dictEntryParam.getDictCode());
        }
        DictEntry dictEntry = setSysDict(dictEntryParam, operator, true);
        dictEntryMapper.add(dictEntry);
        doUpdateDictTableTime(dictEntry.getTableId(), operator);
        addDictLog(dictEntryParam.getTableName(), operator, ADD, "新建编码 " + dictEntryParam.getDictCode());
    }

    @Override
    public void updateDictEntry(DictEntryParam dictEntryParam, String operator) throws Exception {
        Preconditions.checkNotNull(operator);
        List<DictEntry> dictEntries = queryDictEntryByTableIdCode(dictEntryParam.getTableId(), dictEntryParam.getDictCode());
        for (DictEntry dict : dictEntries) {
            if (dict.getId() != dictEntryParam.getId()) {
                throw new Exception("该编码已被使用: " + dictEntryParam.getDictCode());
            }
        }
        List<DictEntry> oldSysDict = queryDictEntryByDictId(dictEntryParam.getId());
        DictEntry sysDict = setSysDict(dictEntryParam, operator, false);
        dictEntryMapper.update(sysDict);
        doUpdateDictTableTime(sysDict.getTableId(), operator);
        addDictLog(dictEntryParam.getTableName(), operator, UPDATE, makeUpdateDetail(oldSysDict.get(0), sysDict));
    }

    private String makeUpdateDetail(DictEntry oldDict, DictEntry newDict) {
        List<String> list = Lists.newArrayList();
        if (!oldDict.getDictCode().equals(newDict.getDictCode())) {
            list.add("编码(" + oldDict.getDictCode() + "->" + newDict.getDictCode() + ")");
        }
        if (!oldDict.getDictName().equals(newDict.getDictName())) {
            list.add("编码名(" + oldDict.getDictName() + "->" + newDict.getDictName() + ")");
        }
        if (!oldDict.getDictDesc().equals(newDict.getDictDesc())) {
            list.add("编码描述(" + oldDict.getDictDesc() + "->" + newDict.getDictDesc() + ")");
        }
        if (oldDict.getDictSort() != newDict.getDictSort()) {
            list.add("编码排序(" + oldDict.getDictSort() + "->" + newDict.getDictSort() + ")");
        }
        if (!oldDict.getExt1().equals(newDict.getExt1())) {
            list.add("ext1(" + oldDict.getExt1() + "->" + newDict.getExt1() + ")");
        }
        if (!oldDict.getExt2().equals(newDict.getExt2())) {
            list.add("ext2(" + oldDict.getExt2() + "->" + newDict.getExt2() + ")");
        }
        if (!oldDict.getExt3().equals(newDict.getExt3())) {
            list.add("ext3(" + oldDict.getExt3() + "->" + newDict.getExt3() + ")");
        }
        if (!oldDict.getExt4().equals(newDict.getExt4())) {
            list.add("ext4(" + oldDict.getExt4() + "->" + newDict.getExt4() + ")");
        }
        if (!oldDict.getExt5().equals(newDict.getExt5())) {
            list.add("ext5(" + oldDict.getExt5() + "->" + newDict.getExt5() + ")");
        }
        return JOINER.join(list);
    }

    @Override
    public List<DictEntry> queryDictEntryByTableIdCode(long tableId, String dictCode) {
        Preconditions.checkNotNull(tableId);
        Preconditions.checkNotNull(dictCode);
        Map<String, Object> params = Maps.newHashMap();
        params.put("tableId", tableId);
        params.put("dictCode", dictCode);
        return dictEntryMapper.queryByCondition(params);
    }

    @Override
    public List<DictEntry> queryDictEntryByTableId(long tableId) {
        Preconditions.checkNotNull(tableId);
        Map<String, Object> params = Maps.newHashMap();
        params.put("tableId", tableId);
        return dictEntryMapper.queryByCondition(params);
    }

    @Override
    public PageModel<DictEntry> pageQueryDictEntry(Map<String, Object> params) {
        long count = dictEntryMapper.count(params);
        List<DictEntry> dictEntries = dictEntryMapper.queryWithPage(params);
        PageModel<DictEntry> pageModel = new PageModel<>();
        pageModel.setList(dictEntries);
        pageModel.setPageNo((Integer) params.get("pageNum"));
        pageModel.setPageSize((Integer) params.get("pageSize"));
        pageModel.setTotalRecords((int) count);
        return pageModel;
    }

    @Override
    public List<DictEntry> queryDictEntryByDictId(long dictId) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("id", dictId);
        return dictEntryMapper.queryByCondition(params);
    }

    @Override
    public void deleteDictEntry(long id, String operator) throws Exception {
        List<DictEntry> dictEntries = queryDictEntryByDictId(id);
        if (dictEntries.size() <= 0) {
            throw new Exception("该字典表条目不存在, id: " + id);
        }
        DictEntry dictEntry = dictEntries.get(0);
        List<DictTable> dictTables = queryDictTableById(dictEntry.getTableId());
        String tableName = dictTables.get(0).getTableName();
        dictEntryMapper.delete(id);
        doUpdateDictTableTime(dictEntry.getTableId(), operator);
        addDictLog(tableName, operator, DELETE, "删除编码 " + dictEntry.getDictCode());
    }

    private DictEntry setSysDict(DictEntryParam dictEntryParam, String operator, boolean isAdd) {
        DictEntry dictEntry = new DictEntry();
        if (isAdd) {
            dictEntry.setCreateBy(operator);
            dictEntry.setCreateTime(new Date());
        } else {
            dictEntry.setId(dictEntryParam.getId());
        }
        dictEntry.setTableId(dictEntryParam.getTableId());
        dictEntry.setDictCode(dictEntryParam.getDictCode());
        dictEntry.setDictName(dictEntryParam.getDictName());
        dictEntry.setDictDesc(dictEntryParam.getDictDesc());
        dictEntry.setDictSort(dictEntryParam.getDictSort());
        dictEntry.setUpdateBy(operator);
        dictEntry.setExt1(dictEntryParam.getExt1());
        dictEntry.setExt2(dictEntryParam.getExt2());
        dictEntry.setExt3(dictEntryParam.getExt3());
        dictEntry.setExt4(dictEntryParam.getExt4());
        dictEntry.setExt5(dictEntryParam.getExt5());
        return dictEntry;
    }

    @Override
    public DictLog addDictLog(String tableName, String operator, int operateType, String operateDetail) {
        DictLog dictLog = new DictLog();
        dictLog.setTableName(tableName);
        dictLog.setOperateDetail(operateDetail);
        dictLog.setOperateType(operateType);
        dictLog.setOperator(operator);
        dictLogMapper.add(dictLog);
        return dictLog;
    }

    @Override
    public List<String> batchImportDict(MultipartFile file, int dictType, String operator) throws Exception {
        List<List<List<String>>> lists = ExcelUtil.readExcel(file);
        if (lists.size() == 0 || lists.get(0).size() <= 1) {
            throw new Exception("文件空");
        }
        List<List<String>> rows = lists.get(0);
        // 检查字典表名是否存在，不存在则添加，存储字典表ID
        Map<String, Long> nameIds = checkDictTableName(rows, dictType, operator);

        List<String> results = Lists.newArrayList();
        List<DictEntry> sysDicts = Lists.newArrayList();
        boolean first = true;
        for (int i = 1; i < rows.size(); i++) {
            try {
                DictEntry dict = getDictByRow(rows.get(i), nameIds, operator);
                sysDicts.add(dict);
            } catch (Exception e) {
                if (first) {
                    first = false;
                    results.add("错误行:");
                }
                results.add(e.getMessage() + ": " + LINE_JOINER.join(rows.get(i)));
            }
        }
        List<DictEntry> duplicateList = batchAddDicts(sysDicts, nameIds, operator);
        if (duplicateList.size() > 0) {
            for (DictEntry dict : duplicateList) {
                results.add(0, LINE_JOINER.join(Lists.newArrayList(dict.getDictCode(), dict.getDictName())));
            }
            results.add(0, "本批数据发现以下数据已经存在于数据库中:");
        }
        results.add(0, "成功导入" + (sysDicts.size() - duplicateList.size()) + "条");
        return results;
    }

    @Override
    public PageModel<DictLog> pageQueryDictLog(Map<String, Object> params) {
        long count = dictLogMapper.count(params);
        List<DictLog> dictLogs = dictLogMapper.queryWithPage(params);
        PageModel<DictLog> pageModel = new PageModel<>();
        pageModel.setList(dictLogs);
        pageModel.setPageNo((Integer) params.get("pageNum"));
        pageModel.setPageSize((Integer) params.get("pageSize"));
        pageModel.setTotalRecords((int) count);
        return pageModel;
    }

    private DictEntry getDictByRow(List<String> row, Map<String, Long> nameIds, String operator) throws Exception {
        checkRow(row);
        DictEntry dict = new DictEntry();
        dict.setCreateBy(operator);
        dict.setUpdateBy(operator);
        dict.setCreateTime(new Date());
        // 字典表名 编码 编码名 描述 排序 扩展字段1 扩展字段2 扩展字段3 扩展字段4 扩展字段5
        for (int i = 0; i < row.size(); i++) {
            String content = row.get(i);
            if (i == 0) {
                if (nameIds.get(content) == null) {
                    throw new Exception("字典表不存在");
                }
                dict.setTableId(nameIds.get(content));
            } else if (i == 1) {
                dict.setDictCode(content);
            } else if (i == 2) {
                dict.setDictName(content);
            } else if (i == 3) {
                dict.setDictDesc(content);
            } else if (i == 4) {
                if (StringUtils.isNotBlank(content)) {
                    dict.setDictSort(Integer.parseInt(content));
                }
            } else if (i == 5) {
                dict.setExt1(content);
            } else if (i == 6) {
                dict.setExt2(content);
            } else if (i == 7) {
                dict.setExt3(content);
            } else if (i == 8) {
                dict.setExt4(content);
            } else if (i == 9) {
                dict.setExt5(content);
            }
        }
        if (StringUtils.isBlank(dict.getDictDesc())) {
            dict.setDictDesc("批量导入");
        }
        return dict;
    }

    private void checkRow(List<String> row) throws Exception {
        if (row.size() < 3) {
            throw new Exception("缺少字段");
        }
        if (StringUtils.isBlank(row.get(0))) {
            throw new Exception("字典表名为空");
        }
        if (StringUtils.isBlank(row.get(1))) {
            throw new Exception("编码为空");
        }
        if (StringUtils.isBlank(row.get(2))) {
            throw new Exception("编码名为空");
        }
    }

    private Map<String, Long> checkDictTableName(List<List<String>> rows, Integer dictType, String operator) {
        Map<String, Long> nameIds = Maps.newHashMap();
        Set<String> tableNameSet = Sets.newHashSet();
        for (int i = 1; i < rows.size(); i++) {
            if (rows.get(i).size() > 0 && StringUtils.isNotBlank(rows.get(i).get(0))) {
                tableNameSet.add(rows.get(i).get(0));
            }
        }
        for (String name : tableNameSet) {
            List<DictTable> sysDictTables = queryDictTableByTableName(name);
            if (sysDictTables.size() > 0) {
                nameIds.put(sysDictTables.get(0).getTableName(), sysDictTables.get(0).getId());
                if (sysDictTables.get(0).getTableType() != dictType) {
                    LOGGER.warn("table type not equal with selected type. table name: {}", name);
                }
            } else {
                Long id = doAddDictTable(name, "", "", dictType, operator).getId();
                addDictLog(name, operator, ADD, "批量导入新建字典表 " + name);
                nameIds.put(name, id);
            }
        }
        return nameIds;
    }

    private List<DictEntry> batchAddDicts(List<DictEntry> dictEntries, Map<String, Long> nameIds, String operator) {
        Map<Long, String> idNames = Maps.newHashMap();
        for (Map.Entry<String, Long> entry : nameIds.entrySet()) {
            idNames.put(entry.getValue(), entry.getKey());
        }
        List<DictEntry> duplicateList = Lists.newArrayList();
        for (DictEntry dict : dictEntries) {
            List<DictEntry> dicts = queryDictEntryByTableIdCode(dict.getTableId(), dict.getDictCode());
            if (dicts.size() > 0) {
                duplicateList.add(dict);
                continue;
            }
            dictEntryMapper.add(dict);
            doUpdateDictTableTime(dict.getTableId(), dict.getCreateBy());
            addDictLog(idNames.get(dict.getTableId()), operator, ADD, "批量导入新建编码 " + dict.getDictCode());
        }
        return duplicateList;
    }

}
