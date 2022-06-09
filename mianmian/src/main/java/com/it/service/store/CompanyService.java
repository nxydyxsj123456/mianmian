package com.it.service.store;

import com.github.pagehelper.PageInfo;
import com.it.domain.store.Company;

import java.util.List;

public interface CompanyService {

    /**
     * 添加
     * @param company
     * @return
     */
    void save(Company company);

    void delete(Company company);

    void update(Company company);

    Company findById(String id );

    /**
     * 查询全部数据
     * @return
     */
    List<Company> findAll();

    PageInfo findAll(int page,int size);

}
