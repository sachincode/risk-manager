package com.sachin.risk.manager.dao.data;

import com.sachin.risk.common.data.model.DictTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author shicheng.zhang
 * @date 17-3-23
 * @time 下午4:01
 * @Description:
 */
public interface DictTableMapper {

    public List<DictTable> queryByCondition(Map<String, Object> param);

    public List<DictTable> queryWithPage(Map<String, Object> param);

    public long count(Map<String, Object> param);

    public int add(DictTable dictTable);

    public void delete(@Param("id") Long id);

    public void update(DictTable dictTable);
}
