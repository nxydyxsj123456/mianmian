package com.it.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.dao.store.QuestionDao;
import com.it.domain.store.Question;
import com.it.service.store.QuestionService;
import com.it.utils.MapperFactory;
import com.it.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class QuestionServiceImpl implements QuestionService {
    @Override
    public String save(Question question ,boolean flag) {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            QuestionDao questionDao=sqlSession.getMapper(QuestionDao.class);

            String id=  UUID.randomUUID().toString();
            question.setId(id);

            question.setReviewStatus("0");

            question.setCreateTime(new Date());

            if(flag)
            {
                question.setPicture(id);
            }


            questionDao.save(question);

            TransactionUtil.commit(sqlSession);

            return question.getId();
        }
        catch (Exception e)
        {
            TransactionUtil.rollback(sqlSession);
            throw  new RuntimeException(e);
        }finally{
            TransactionUtil.close(sqlSession);

        }


    }

    @Override
    public void delete(Question question) {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            QuestionDao questionDao=sqlSession.getMapper(QuestionDao.class);


            questionDao.delete(question);

            TransactionUtil.commit(sqlSession);
        }
        catch (Exception e)
        {
            TransactionUtil.rollback(sqlSession);
            throw  new RuntimeException(e);
        }finally{
            TransactionUtil.close(sqlSession);

        }
    }

    @Override
    public void update(Question question ,boolean flag) {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            QuestionDao questionDao=sqlSession.getMapper(QuestionDao.class);

            if(flag)
            {
                question.setPicture(question.getId());
            }

            questionDao.update(question);

            TransactionUtil.commit(sqlSession);


        }
        catch (Exception e)
        {
            TransactionUtil.rollback(sqlSession);
            throw  new RuntimeException(e);
        }finally{
            TransactionUtil.close(sqlSession);

        }
    }

    @Override
    public Question findById(String id) {

        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            QuestionDao questionDao=sqlSession.getMapper(QuestionDao.class);


            return questionDao.findById(id);
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }finally{
            TransactionUtil.close(sqlSession);

        }

    }

    @Override
    public List<Question> findAll() {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            QuestionDao questionDao=sqlSession.getMapper(QuestionDao.class);


            return questionDao.findAll();
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }finally{
            TransactionUtil.close(sqlSession);

        }
    }

    @Override
    public PageInfo findAll(int page, int size) {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            QuestionDao questionDao=sqlSession.getMapper(QuestionDao.class);

            PageHelper.startPage(page,size);

            List<Question> all= questionDao.findAll();

            PageInfo pageInfo =new PageInfo(all);

            return pageInfo;
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }finally{
            TransactionUtil.close(sqlSession);

        }
    }

    @Override
    public void getReport() throws IOException {
        //拿数据
        SqlSession sqlSession=null;
        List<Question> all=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            QuestionDao questionDao=sqlSession.getMapper(QuestionDao.class);



            all= questionDao.findAll();


        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }finally{
            TransactionUtil.close(sqlSession);
        }



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
        //String [] fields2={ "id","companyId","catalogId","remark","subject","picture","analysis","type","defficulty","sClassic","state","reviewStatus"};

        XSSFRow row2 = sheet.createRow(2);



        for(int i=0;i<fields1.length;i++)
        {
            sheet.setColumnWidth(i+1,4000);
            XSSFCell temp_cell2 = row2.createCell(1+i);
            //XSSFCell temp_cell3 = row3.createCell(1+i);

            CellStyle cs_field =wb.createCellStyle();

//            cs_field.setBorderTop(BorderStyle.THIN);
//            cs_field.setBorderBottom(BorderStyle.THIN);
//            cs_field.setBorderLeft(BorderStyle.THIN);
//            cs_field.setBorderRight(BorderStyle.THIN);

            cs_field.setAlignment(HorizontalAlignment.CENTER);
            cs_field.setVerticalAlignment(VerticalAlignment.CENTER);
            temp_cell2.setCellStyle(cs_field);
            //temp_cell3.setCellStyle(cs_field);

            temp_cell2.setCellValue(fields1[i]);
            //temp_cell3.setCellValue(fields2[i]);
        }


        int rowIndex=0;

        CellStyle cs_field =wb.createCellStyle();
        cs_field.setAlignment(HorizontalAlignment.CENTER);
        cs_field.setVerticalAlignment(VerticalAlignment.CENTER);

        for(Question question : all)
        {
            XSSFRow row = sheet.createRow(3+rowIndex);
            XSSFCell temp_cell;

            temp_cell = row.createCell(1);
            temp_cell.setCellValue(question.getId());
            temp_cell.setCellStyle(cs_field);

            temp_cell = row.createCell(2);
            temp_cell.setCellValue(question.getCompanyId());
            temp_cell.setCellStyle(cs_field);

            temp_cell = row.createCell(3);
            temp_cell.setCellValue(question.getCatalogId());
            temp_cell.setCellStyle(cs_field);

            rowIndex++;
        }

        FileOutputStream fos=new FileOutputStream("test.xlsx");

        wb.write(fos);

        wb.close();

        fos.close();
    }
}
