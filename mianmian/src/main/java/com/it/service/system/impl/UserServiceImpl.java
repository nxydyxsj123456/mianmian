package com.it.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.dao.system.UserDao;
import com.it.domain.system.User;
import com.it.service.system.UserService;
import com.it.utils.MD5Util;
import com.it.utils.MapperFactory;
import com.it.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public void save(User user) {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            UserDao userDao=sqlSession.getMapper(UserDao.class);

            String id=  UUID.randomUUID().toString();
            user.setId(id);
            user.setPassword(MD5Util.md5(user.getPassword()));
            userDao.save(user);

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
    public void delete(User user) {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            UserDao userDao=sqlSession.getMapper(UserDao.class);


            userDao.delete(user);

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
    public void update(User user) {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            UserDao userDao=sqlSession.getMapper(UserDao.class);


            userDao.update(user);

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
    public User findById(String id) {

        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            UserDao userDao=sqlSession.getMapper(UserDao.class);


            return userDao.findById(id);
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }finally{
            TransactionUtil.close(sqlSession);

        }

    }

    @Override
    public List<User> findAll() {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            UserDao userDao=sqlSession.getMapper(UserDao.class);


            return userDao.findAll();
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

            UserDao userDao=sqlSession.getMapper(UserDao.class);

            PageHelper.startPage(page,size);

            List<User> all= userDao.findAll();

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
