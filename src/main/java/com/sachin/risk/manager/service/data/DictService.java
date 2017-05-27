package com.sachin.risk.manager.service.data;

import com.sachin.risk.common.data.model.DictEntry;
import com.sachin.risk.common.data.model.DictTable;
import com.sachin.risk.manager.model.PageModel;
import com.sachin.risk.manager.model.data.DictLog;
import com.sachin.risk.manager.model.data.DictEntryParam;
import com.sachin.risk.manager.model.data.DictTableParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author shicheng.zhang
 * @date 17-3-23
 * @time 下午2:37
 * @Description:
 */
public interface DictService {

    PageModel<DictTable> pageQueryDictTable(Map<String, Object> params);

    /**
     * 新增字典表，主表
     * 
     * @param dictTableParam 字典表
     * @param operator 创建人
     * @return
     * @throws Exception
     */
    Long addDictTable(DictTableParam dictTableParam, String operator) throws Exception;

    /**
     * 根据字典表名查询字典表主表
     * @param tableName
     * @return
     */
    List<DictTable> queryDictTableByTableName(String tableName);

    List<DictTable> queryDictTableById(long id);

    List<DictTable> queryDictTableByType(int tableType);

    /**
     * 更新字典表，主表
     * @param dictTableParam
     * @param operator
     */
    void updateDictTable(DictTableParam dictTableParam, String operator);

    /**
     * 删除字典表，主表和详情表
     * @param id
     * @param operator
     */
    void deleteDictTable(long id, String operator) throws Exception;



    /**
     * 添加字典表条目，前提是已经添加了主表
     * @param dictEntryParam
     * @param operator
     * @throws Exception
     */
    void addDictEntry(DictEntryParam dictEntryParam, String operator) throws Exception;

    /**
     * 更新字典表条目
     * @param dictEntryParam
     * @param operator
     * @throws Exception
     */
    void updateDictEntry(DictEntryParam dictEntryParam, String operator) throws Exception;

    /**
     * 根据字典表主表Id和字典表详情编码查询字典表条目
     * @param tableId 字典表主表Id
     * @param dictCode 字典表条目编码
     * @return
     */
    List<DictEntry> queryDictEntryByTableIdCode(long tableId, String dictCode);

    List<DictEntry> queryDictEntryByTableId(long tableId);

    PageModel<DictEntry> pageQueryDictEntry(Map<String, Object> params);

    /**
     * 根据字典表条目Id查询字典表条目
     * @param dictId 字典表条目Id
     * @return
     */
    List<DictEntry> queryDictEntryByDictId(long dictId);

    /**
     * 删除字典表条目
     * @param id
     * @param operator
     */
    void deleteDictEntry(long id, String operator) throws Exception;

    /**
     * 添加字典表操作日志
     * @param tableName 字典表名
     * @param operator 操作人
     * @param operateType 操作类型
     * @param operateDetail 操作详情
     * @return
     */
    DictLog addDictLog(String tableName, String operator, int operateType, String operateDetail);



    /**
     * 批量导入字典表
     * @param file excel文件
     * @param dictType 字典表类型
     * @param operator 操作人
     * @return
     * @throws Exception
     */
    List<String> batchImportDict(MultipartFile file, int dictType, String operator) throws Exception;


    PageModel<DictLog> pageQueryDictLog(Map<String, Object> params);


}
