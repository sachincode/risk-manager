package com.sachin.risk.manager.dao.data;

import com.sachin.risk.manager.model.data.DictLog;

import java.util.List;
import java.util.Map;

/**
 * @author shicheng.zhang
 * @date 17-3-23
 * @time 下午2:47
 * @Description:
 */
public interface DictLogMapper {

    public List<DictLog> queryByCondition(Map<String, Object> param);

    public List<DictLog> queryWithPage(Map<String, Object> param);

    public long count(Map<String, Object> param);

    public int add(DictLog dictLog);
}
