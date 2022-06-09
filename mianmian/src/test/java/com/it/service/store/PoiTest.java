package com.it.service.store;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import javax.print.attribute.standard.SheetCollate;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PoiTest {

    @Test
    public void testWriteByPoi() throws IOException {

        XSSFWorkbook wb = new XSSFWorkbook();


        XSSFSheet sheet = wb.createSheet("1111");

        XSSFRow row = sheet.createRow(1);

        XSSFCell cell = row.createCell(1);

        cell.setCellValue("ceshi");


        FileOutputStream fos=new FileOutputStream("01.xlsx");

        wb.write(fos);

        wb.close();

        fos.close();

    }

    @Test
    public void testReadByPoi() throws IOException {

        XSSFWorkbook wb = new XSSFWorkbook("01.xlsx");


        XSSFSheet sheet = wb.getSheetAt(0);

        XSSFRow row = sheet.getRow(1);

        XSSFCell cell = row.getCell(1);

        System.out.println(cell.getStringCellValue());

        wb.close();



    }

    @Test
    public void testProjectPoi() throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();


        XSSFSheet sheet = wb.createSheet("题目数据文件");

        //制作标题
        XSSFRow row1 = sheet.createRow(1);

        XSSFCell cell_1_1 = row1.createCell(1);

        cell_1_1.setCellValue("在线试题导出信息");

        CellStyle cs_title =wb.createCellStyle();
        cs_title.setAlignment(HorizontalAlignment.CENTER);
        cs_title.setVerticalAlignment(VerticalAlignment.CENTER);

//        cs_title.setBorderTop(BorderStyle.THIN);
//        cs_title.setBorderBottom(BorderStyle.THIN);
//        cs_title.setBorderLeft(BorderStyle.THIN);
//        cs_title.setBorderRight(BorderStyle.THIN);

        cell_1_1.setCellStyle(cs_title);
        sheet.addMergedRegion(new CellRangeAddress(1,1,1,12));



        //制作表头
        String [] fields1={ "题目ID","所属公司ID","所属目录ID","题目简介","题干描述","题干配图","题目分析","题目类型","题目难度","是否经典题","题目状态","审核状态"};
        String [] fields2={ "id","companyId","catalogId","remark","subject","picture","analysis","type","defficulty","sClassic","state","reviewStatus"};

        XSSFRow row2 = sheet.createRow(2);

        XSSFRow row3 = sheet.createRow(3);

        for(int i=0;i<fields1.length;i++)
        {
            sheet.setColumnWidth(i+1,4000);
            XSSFCell temp_cell2 = row2.createCell(1+i);
            XSSFCell temp_cell3 = row3.createCell(1+i);

            CellStyle cs_field =wb.createCellStyle();

//            cs_field.setBorderTop(BorderStyle.THIN);
//            cs_field.setBorderBottom(BorderStyle.THIN);
//            cs_field.setBorderLeft(BorderStyle.THIN);
//            cs_field.setBorderRight(BorderStyle.THIN);

            cs_field.setAlignment(HorizontalAlignment.CENTER);
            cs_field.setVerticalAlignment(VerticalAlignment.CENTER);
            temp_cell2.setCellStyle(cs_field);
            temp_cell3.setCellStyle(cs_field);

            temp_cell2.setCellValue(fields1[i]);
            temp_cell3.setCellValue(fields2[i]);
        }


        FileOutputStream fos=new FileOutputStream("test.xlsx");

        wb.write(fos);

        wb.close();

        fos.close();
    }
}
