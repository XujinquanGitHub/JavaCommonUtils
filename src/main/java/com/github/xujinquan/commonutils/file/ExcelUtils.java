package com.github.xujinquan.commonutils.file;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * @program: commonutils
 * @description: Excel工具类
 * @author: 许金泉
 * @create: 2020-08-28 10:53
 **/
public class ExcelUtils {


    public static <T> void generateExcelFile(String fileSrc, String sheetName, Iterable<T> data, Map<String, Function<T, String>> headData) throws IOException {
        FileOutputStream out = new FileOutputStream(fileSrc);
        generateExcelFile(out, sheetName, data, headData);
        out.close();
    }

    public static <T> void generateExcelFile(OutputStream stream, String sheetName, Iterable<T> data, Map<String, Function<T, String>> headData) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow headRow = sheet.createRow(0);
        CreationHelper createHelper = workbook.getCreationHelper();
        Set<String> headTitleList = headData.keySet();
        int columnNum = 0;
        for (String title : headTitleList) {
            headRow.createCell(columnNum++).setCellValue(createHelper.createRichTextString(title));
        }

        int rowIndex = 1;
        for (T item : data) {
            int columnDataNum = 0;
            HSSFRow dataRow = sheet.createRow(rowIndex++);
            for (String title : headTitleList) {
                dataRow.createCell(columnDataNum++).setCellValue(headData.get(title).apply(item));
            }
        }
        workbook.setSheetName(0, sheetName);
        workbook.write(stream);
    }


}
