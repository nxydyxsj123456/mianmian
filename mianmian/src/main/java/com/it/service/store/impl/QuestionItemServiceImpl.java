package com.it.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.dao.store.QuestionItemDao;
import com.it.domain.store.QuestionItem;
import com.it.service.store.QuestionItemService;
import com.it.utils.MapperFactory;
import com.it.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class QuestionItemServiceImpl implements QuestionItemService {
    @Override
    public String save(QuestionItem questionItem) {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            QuestionItemDao questionItemDao=sqlSession.getMapper(QuestionItemDao.class);

            String id=  UUID.randomUUID().toString();
            questionItem.setId(id);



            questionItemDao.save(questionItem);

            TransactionUtil.commit(sqlSession);

            return questionItem.getId();
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
    public void delete(QuestionItem questionItem) {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            QuestionItemDao questionItemDao=sqlSession.getMapper(QuestionItemDao.class);


            questionItemDao.delete(questionItem);

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
    public void update(QuestionItem questionItem ) {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            QuestionItemDao questionItemDao=sqlSession.getMapper(QuestionItemDao.class);



            questionItemDao.update(questionItem);

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
    public QuestionItem findById(String id) {

        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            QuestionItemDao questionItemDao=sqlSession.getMapper(QuestionItemDao.class);


            return questionItemDao.findById(id);
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }finally{
            TransactionUtil.close(sqlSession);

        }

    }

//    @Override
//    public List<QuestionItem> findAll() {
//        SqlSession sqlSession=null;
//        try {
//            sqlSession = MapperFactory.getSqlSession();
//
//            QuestionItemDao questionItemDao=sqlSession.getMapper(QuestionItemDao.class);
//
//
//            return questionItemDao.findAll();
//        }
//        catch (Exception e)
//        {
//            throw  new RuntimeException(e);
//        }finally{
//            TransactionUtil.close(sqlSession);
//
//        }
//    }

    @Override
    public PageInfo findAll(String questionId ,int page, int size) {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            QuestionItemDao questionItemDao=sqlSession.getMapper(QuestionItemDao.class);

            PageHelper.startPage(page,size);

            List<QuestionItem> all= questionItemDao.findAll(questionId);

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
}
