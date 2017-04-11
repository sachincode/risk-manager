package com.sachin.risk.manager.model.data;

/**
 * @author shicheng.zhang
 * @date 17-3-24
 * @time 上午10:14
 * @Description:
 */
public class DictEntryParam {
    /** 编码ID **/
    private Long id;
    /** 字典表ID **/
    private Long tableId;
    /** 字典表名 **/
    private String tableName;
    /** 编码 **/
    private String dictCode;
    /** 名称 **/
    private String dictName;
    /** 描述 **/
    private String dictDesc;
    /** 排序 **/
    private Integer dictSort;
    /** 扩展字段1 **/
    private String ext1;
    /** 扩展字段2 **/
    private String ext2;
    /** 扩展字段3 **/
    private String ext3;
    /** 扩展字段4 **/
    private String ext4;
    /** 扩展字段5 **/
    private String ext5;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }

    public Integer getDictSort() {
        return dictSort;
    }

    public void setDictSort(Integer dictSort) {
        this.dictSort = dictSort;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4;
    }

    public String getExt5() {
        return ext5;
    }

    public void setExt5(String ext5) {
        this.ext5 = ext5;
    }

    @Override
    public String toString() {
        return "SysDictParam{" + "id=" + id + ", tableId=" + tableId + ", tableName='" + tableName + '\''
                + ", dictCode='" + dictCode + '\'' + ", dictName='" + dictName + '\'' + ", dictDesc='" + dictDesc + '\''
                + ", dictSort=" + dictSort + ", ext1='" + ext1 + '\'' + ", ext2='" + ext2 + '\'' + ", ext3='" + ext3
                + '\'' + ", ext4='" + ext4 + '\'' + ", ext5='" + ext5 + '\'' + '}';
    }
}
