package com.it.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.dao.store.CatalogDao;
import com.it.domain.store.Catalog;
import com.it.service.store.CatalogService;
import com.it.utils.MapperFactory;
import com.it.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CatalogServiceImpl implements CatalogService {
    @Override
    public void save(Catalog catalog) {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            CatalogDao catalogDao=sqlSession.getMapper(CatalogDao.class);

            String id=  UUID.randomUUID().toString();
            catalog.setId(id);

            //添加创建时间
            catalog.setCreateTime(new Date());

            catalogDao.save(catalog);

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
    public void delete(Catalog catalog) {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            CatalogDao catalogDao=sqlSession.getMapper(CatalogDao.class);


            catalogDao.delete(catalog);

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
    public void update(Catalog catalog) {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            CatalogDao catalogDao=sqlSession.getMapper(CatalogDao.class);


            catalogDao.update(catalog);

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
    public Catalog findById(String id) {

        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            CatalogDao catalogDao=sqlSession.getMapper(CatalogDao.class);


            return catalogDao.findById(id);
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }finally{
            TransactionUtil.close(sqlSession);

        }

    }

    @Override
    public List<Catalog> findAll() {
        SqlSession sqlSession=null;
        try {
            sqlSession = MapperFactory.getSqlSession();

            CatalogDao catalogDao=sqlSession.getMapper(CatalogDao.class);


            return catalogDao.findAll();
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

            CatalogDao catalogDao=sqlSession.getMapper(CatalogDao.class);

            PageHelper.startPage(page,size);

            List<Catalog> all= catalogDao.findAll();

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
