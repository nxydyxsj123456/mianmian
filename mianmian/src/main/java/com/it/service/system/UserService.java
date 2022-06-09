package com.it.service.system;

import com.github.pagehelper.PageInfo;
import com.it.domain.system.User;

import java.util.List;

public interface UserService {

    /**
     * 添加
     * @param user
     * @return
     */
    void save(User user);

    void delete(User user);

    void update(User user);

    User findById(String id );

    /**
     * 查询全部数据
     * @return
     */
    List<User> findAll();

    PageInfo findAll(int page,int size);

}
