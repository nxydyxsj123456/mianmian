package com.it.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.dao.store.CourseDao;
import com.it.domain.store.Course;
import com.it.service.store.CourseService;
import com.it.utils.MapperFactory;
import com.it.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CourseServiceImpl implements CourseService {
    @Override
    public void save(Course course) {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            CourseDao courseDao=sqlSession.getMapper(CourseDao.class);

            String id=  UUID.randomUUID().toString();
            course.setId(id);

            //添加创建时间
            course.setCreateTime(new Date());

            courseDao.save(course);

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
    public void delete(Course course) {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            CourseDao courseDao=sqlSession.getMapper(CourseDao.class);


            courseDao.delete(course);

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
    public void update(Course course) {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            CourseDao courseDao=sqlSession.getMapper(CourseDao.class);


            courseDao.update(course);

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
    public Course findById(String id) {

        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            CourseDao courseDao=sqlSession.getMapper(CourseDao.class);


            return courseDao.findById(id);
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }finally{
            TransactionUtil.close(sqlSession);

        }

    }

    @Override
    public List<Course> findAll() {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            CourseDao courseDao=sqlSession.getMapper(CourseDao.class);


            return courseDao.findAll();
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

            CourseDao courseDao=sqlSession.getMapper(CourseDao.class);

            PageHelper.startPage(page,size);

            List<Course> all= courseDao.findAll();

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
