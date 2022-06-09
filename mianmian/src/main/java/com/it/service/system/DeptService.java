package com.it.service.system;

import com.github.pagehelper.PageInfo;
import com.it.domain.system.Dept;


import java.util.List;

public interface DeptService {

    /**
     * 添加
     * @param dept
     * @return
     */
    void save(Dept dept);

    void delete(Dept dept);

    void update(Dept dept);

    Dept findById(String id );

    /**
     * 查询全部数据
     * @return
     */
    List<Dept> findAll();

    PageInfo findAll(int page,int size);

}
