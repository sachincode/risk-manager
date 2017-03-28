package com.sachin.risk.manager.util;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @author shicheng.zhang
 * @date 17-3-24
 * @time 上午10:53
 * @Description:
 */
public class ExcelUtil {

    private static final String EMPTY = "";
    private static final String EXCEL_2003_POSTFIX = ".xls";
    private static final String EXCEL_2010_POSTFIX = ".xlsx";


    /**
     * 读取excel文件内容
     * @param file 上传的文件
     * @return 嵌套关系：sheet-row-cell
     * @throws Exception
     */
    public static List<List<List<String>>> readExcel(MultipartFile file) throws Exception {
        String name = file.getOriginalFilename();
        if (name.endsWith(EXCEL_2003_POSTFIX)) {
            return readXls(file.getInputStream());
        } else if (name.endsWith(EXCEL_2010_POSTFIX)) {
            return readXlsx(file.getInputStream());
        } else {
            throw new Exception("不是Excel文件");
        }
    }

    /**
     * 读取excel文件内容
     * @param path 文件路径名
     * @return 嵌套关系：sheet-row-cell
     * @throws Exception
     */
    public static List<List<List<String>>> readExcel(String path) throws Exception {
        if (StringUtils.isBlank(path)) {
            throw new Exception("文件路径不存在");
        }
        if (path.endsWith(EXCEL_2003_POSTFIX)) {
            return readXls(path);
        } else if (path.endsWith(EXCEL_2010_POSTFIX)) {
            return readXlsx(path);
        } else {
            throw new Exception("不是Excel文件");
        }
    }


    public static List<List<List<String>>> readXls(String path) throws Exception {
        InputStream is = new FileInputStream(path);
        return readXls(is);
    }

    /**
     * 读取xls文件
     * @param is 文件流
     * @return  嵌套关系：sheet-row-cell
     * @throws Exception
     */
    public static List<List<List<String>>> readXls(InputStream is) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook(is);
        List<List<List<String>>> resultAll = Lists.newArrayList();
        // 循环处理每一页（sheet）
        for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
            List<List<String>> result = Lists.newArrayList();
            HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            if (sheet == null) {
                continue;
            }
            // 循环处理每一行
            for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                HSSFRow row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                int maxCellIndex = row.getLastCellNum();
                List<String> rowList = Lists.newArrayList();
                for (int cellIndex = row.getFirstCellNum(); cellIndex < maxCellIndex; cellIndex++) {
                    HSSFCell cell = row.getCell(cellIndex);
                    if (cell == null) {
                        rowList.add(EMPTY);
                    } else {
                        rowList.add(getStringValue(cell));
                    }
                }
                if (!isEmpty(rowList)) {
                    result.add(rowList);
                }
            }
            resultAll.add(result);
        }
        return resultAll;
    }


    public static List<List<List<String>>> readXlsx(String path) throws Exception {
        InputStream is = new FileInputStream(path);
        return readXlsx(is);
    }

    /**
     * 读取xlsx文件
     * @param is 文件流
     * @return 嵌套关系：sheet-row-cell
     * @throws Exception
     */
    public static List<List<List<String>>> readXlsx(InputStream is) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        List<List<List<String>>> resultAll = Lists.newArrayList();

        for (XSSFSheet sheet : workbook) {
            List<List<String>> result = Lists.newArrayList();
            if (sheet == null) {
                continue;
            }
            for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                int maxCellIndex = row.getLastCellNum();
                List<String> rowList = Lists.newArrayList();
                for (int cellIndex = row.getFirstCellNum(); cellIndex < maxCellIndex; cellIndex++) {
                    XSSFCell cell = row.getCell(cellIndex);
                    if (cell == null) {
                        rowList.add(EMPTY);
                    } else {
                        rowList.add(getStringValue(cell));
                    }
                }
                if (!isEmpty(rowList)) {
                    result.add(rowList);
                }
            }
            resultAll.add(result);
        }
        return resultAll;
    }

    private static String getStringValue(Cell cell) {
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_BOOLEAN:
            return String.valueOf(cell.getBooleanCellValue());
        case Cell.CELL_TYPE_FORMULA:
            return cell.getCellFormula();
        case Cell.CELL_TYPE_NUMERIC:
            cell.setCellType(Cell.CELL_TYPE_STRING);
            return cell.getStringCellValue();
        case Cell.CELL_TYPE_STRING:
            return cell.getStringCellValue();
        default:
            return EMPTY;
        }
    }

    private static boolean isEmpty(List<String> list) {
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        boolean empty = true;
        for (String s : list) {
            if (StringUtils.isNotBlank(s)) {
                empty = false;
            }
        }
        return empty;
    }
}
