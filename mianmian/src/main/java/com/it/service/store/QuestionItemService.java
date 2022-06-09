package com.it.service.store;

import com.github.pagehelper.PageInfo;
import com.it.domain.store.QuestionItem;

import java.util.List;

public interface QuestionItemService {

    /**
     * 添加
     * @param questionItem
     * @return
     */
    String save(QuestionItem questionItem );

    void delete(QuestionItem questionItem);

    void update(QuestionItem questionItem);

    QuestionItem findById(String id );

    /**
     * 查询全部数据
     * @return
     */

    PageInfo findAll(String questionId,int page,int size);

}
