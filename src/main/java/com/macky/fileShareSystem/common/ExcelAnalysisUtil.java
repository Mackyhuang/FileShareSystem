package com.macky.fileShareSystem.common;

import com.macky.fileShareSystem.entity.ExcelRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/7 19:30
 * 表格解析类  用于扩展名为 .xls .xlsx
 */
public class ExcelAnalysisUtil {

    /**
     * 获取Excel的信息
     * @param filePath
     * @return list 含有每行信息的列表
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static List getExcelData(String filePath) throws FileNotFoundException, IOException {
        File excelFile = new File(filePath);
        List<ExcelRow> list = new ArrayList<ExcelRow>();
        Row row = null;
        Cell cell = null;
        int rowcount = 0;
        Sheet sheet = null;
        /*
         * 判断给定文件的类型; 1.如果是xls的问价那类型就创建XSSFWorkBook ;
         * 2.如果是xlsx的文件类型就创建HSSFWorkBook ;
         */

        String Extension = filePath.substring(filePath.indexOf('.'));
        if (Extension.equals(".xls")) {
            HSSFWorkbook xlswb = new HSSFWorkbook(new FileInputStream(excelFile));
            sheet = xlswb.getSheetAt(0);
        } else if (Extension.equals(".xlsx")) {
            XSSFWorkbook wbxlsx = new XSSFWorkbook(new FileInputStream(excelFile));
            sheet = wbxlsx.getSheetAt(0);
        } else {
            System.out.println("指定的文件不是excle文件！");
        }
        rowcount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        for (int i = 0; i < rowcount + 1; i++) {
            row = sheet.getRow(i);
            Object[] obj = new Object[ExcelRow.MAX_OF_EXCEL + 1];
            ExcelRow excelRow = new ExcelRow();

            for (int j = 0; j < ExcelRow.MAX_OF_EXCEL; j++) {
                cell = row.getCell(j);
                if (cell == null){
                    obj[j] = null;
                    continue;
                }
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        obj[j] = cell.getRichStringCellValue().getString();
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            obj[j] = cell.getDateCellValue();
                        } else {
                            obj[j] = cell.getNumericCellValue();
                        }
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        obj[j] = cell.getBooleanCellValue();
                        break;
                    default:
                }
            }
            excelRow.setField1(String.valueOf(obj[0]));
            excelRow.setField2(String.valueOf(obj[1]));
            excelRow.setField3(String.valueOf(obj[2]));
            excelRow.setField4(String.valueOf(obj[3]));
            excelRow.setField5(String.valueOf(obj[4]));
            list.add(excelRow);
        }
        return list;
    }

    public static List ReadExcel(String filePath) throws FileNotFoundException, IOException{
        List resultList =  ExcelAnalysisUtil.getExcelData(filePath);
        if (resultList == null ||resultList.size() <= 0){
            return null;
        }else {
            return resultList;
        }

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String filePathxlsx = "C:\\Users\\MackyHuang\\Desktop\\xm\\二阶段\\上传图片模板.xlsx";
        List testData = ExcelAnalysisUtil.getExcelData(filePathxlsx);
        for (int i = 0; i < testData.size(); i++){
            System.out.println(testData.get(i));
        }

    }
}
