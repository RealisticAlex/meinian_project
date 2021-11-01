package com.mcs.meinian.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author Alex
 * @DATE 2021/11/1 17:52
 **/
public class POITest {


    public void readExcelFileOne() throws Exception{
        //读取文件获取工作簿对象
        XSSFWorkbook xssf = new XSSFWorkbook("C:\\Users\\毛小帅\\Desktop\\学生信息.xlsx");
        //获取工作表对象
        XSSFSheet sheet = xssf.getSheetAt(0);
        //获取组成sheet的行
        for (Row row : sheet) {
            //获取组成行的单元格
            for (Cell cell : row) {
                System.out.println(cell.getStringCellValue());

            }
        }
        //关闭工作簿
        xssf.close();


    }


    public void readExcelFileOne2() throws Exception{

        XSSFWorkbook xssf = new XSSFWorkbook("C:\\Users\\毛小帅\\Desktop\\学生信息.xlsx");
        XSSFSheet sheetAt = xssf.getSheetAt(0);
        //获取最后一行的行数(从0开始)
        int lastRowNum = sheetAt.getLastRowNum();
        for (int i = 0; i <= lastRowNum; i++) {
            //通过行数获取某一行
            XSSFRow row = sheetAt.getRow(i);
            short lastCellNum = row.getLastCellNum();
            System.out.println();
            for (int j = 0; j < lastCellNum; j++) {
                XSSFCell cell = row.getCell(j);
                System.out.print(" "+cell.getStringCellValue());
            }
        }
    }


    public void writeExcelFile() throws IOException {
        //创建一个工作簿对象
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        //创建一个工作表对象
        XSSFSheet sheet = xssfWorkbook.createSheet();
        //创建行
        XSSFRow row0 = sheet.createRow(0);
        //创建第一行的单元格并赋值
        row0.createCell(0).setCellValue("姓名");
        row0.createCell(1).setCellValue("年龄");
        row0.createCell(2).setCellValue("性别");

        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("小李");
        row1.createCell(1).setCellValue("23");
        row1.createCell(2).setCellValue("男");


        XSSFRow row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("小王");
        row2.createCell(1).setCellValue("24");
        row2.createCell(2).setCellValue("女");

        xssfWorkbook.write(new FileOutputStream("D:\\尚硅谷.xlsx"));

        xssfWorkbook.close();
    }
}
