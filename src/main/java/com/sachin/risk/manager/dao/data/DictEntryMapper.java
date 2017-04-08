package com.sachin.risk.manager.dao.data;

import com.sachin.risk.common.data.model.DictEntry;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author shicheng.zhang
 * @date 17-3-23
 * @time 下午9:16
 * @Description:
 */
public interface DictEntryMapper {

    public List<DictEntry> queryByCondition(Map param);

    public List<DictEntry> queryWithPage(Map param);

    public long count(Map param);

    public int add(DictEntry dictEntry);

    public int batchAdd(@Param("list") List list);

    public void delete(@Param("id") Long id);

    public void deleteByTableId(@Param("tableId") Long tableId);

    public void update(DictEntry sysDict);

}
