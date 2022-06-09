package com.it.service.store;

import com.github.pagehelper.PageInfo;
import com.it.domain.store.Catalog;

import java.util.List;

public interface CatalogService {

    /**
     * 添加
     * @param catalog
     * @return
     */
    void save(Catalog catalog);

    void delete(Catalog catalog);

    void update(Catalog catalog);

    Catalog findById(String id );

    /**
     * 查询全部数据
     * @return
     */
    List<Catalog> findAll();

    PageInfo findAll(int page,int size);

}
