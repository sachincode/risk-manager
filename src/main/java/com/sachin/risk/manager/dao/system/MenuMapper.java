package com.sachin.risk.manager.dao.system;

import com.sachin.risk.manager.model.system.Menu;

import java.util.List;
import java.util.Map;

/**
 * @author shicheng.zhang
 * @date 17-3-19
 * @time 下午10:04
 * @Description:
 */
public interface MenuMapper {

    public List<Menu> query(Map<String, Object> params);
}
