package com.sachin.risk.manager.service.data.impl;

import com.sachin.risk.manager.model.data.DictTableParam;
import com.sachin.risk.manager.service.data.DictService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class DictServiceImplTest {

    @Resource
    private DictService dictService;

    @Test
    public void testAddDictTable() throws Exception {
        DictTableParam param = new DictTableParam();
        param.setTableName("test_table");
        param.setTableNameCn("测试");
        param.setTableType(1);
        param.setTableDesc("测试的");
        Long aLong = dictService.addDictTable(param, "shicheng.zhang");
        System.out.println(aLong);
    }

    @Test
    public void testQueryDictTableByTableName() throws Exception {

    }

    @Test
    public void testAddDictLog() throws Exception {

    }
}