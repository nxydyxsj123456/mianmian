package com.it.service.store;

import com.github.pagehelper.PageInfo;
import com.it.domain.store.Course;

import java.util.List;

public interface CourseService {

    /**
     * 添加
     * @param course
     * @return
     */
    void save(Course course);

    void delete(Course course);

    void update(Course course);

    Course findById(String id );

    /**
     * 查询全部数据
     * @return
     */
    List<Course> findAll();

    PageInfo findAll(int page,int size);

}
