package com.it.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.it.dao.system.DeptDao;
import com.it.domain.system.Dept;
import com.it.service.system.DeptService;
import com.it.utils.MapperFactory;
import com.it.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

public class DeptServiceImpl implements DeptService {
    @Override
    public void save(Dept dept) {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            DeptDao deptDao=sqlSession.getMapper(DeptDao.class);

            String id=  UUID.randomUUID().toString();
            dept.setId(id);
            deptDao.save(dept);

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
    public void delete(Dept dept) {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            DeptDao deptDao=sqlSession.getMapper(DeptDao.class);


            deptDao.delete(dept);

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
    public void update(Dept dept) {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            DeptDao deptDao=sqlSession.getMapper(DeptDao.class);


            deptDao.update(dept);

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
    public Dept findById(String id) {

        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            DeptDao deptDao=sqlSession.getMapper(DeptDao.class);


            return deptDao.findById(id);
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }finally{
            TransactionUtil.close(sqlSession);

        }

    }

    @Override
    public List<Dept> findAll() {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            DeptDao deptDao=sqlSession.getMapper(DeptDao.class);


            return deptDao.findAll();
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

            DeptDao deptDao=sqlSession.getMapper(DeptDao.class);

            PageHelper.startPage(page,size);

            List<Dept> all= deptDao.findAll();

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
