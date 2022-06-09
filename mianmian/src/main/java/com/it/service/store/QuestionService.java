package com.it.service.store;

import com.github.pagehelper.PageInfo;
import com.it.domain.store.Question;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface QuestionService {

    /**
     * 添加
     * @param question
     * @return
     */
    String save(Question question ,boolean flag);

    void delete(Question question);

    void update(Question question ,boolean flag);

    Question findById(String id );

    /**
     * 查询全部数据
     * @return
     */
    List<Question> findAll();

    PageInfo findAll(int page,int size);

    void getReport() throws IOException;
}
