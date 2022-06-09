package com.it.dao.store;

import com.it.domain.store.QuestionItem;

import java.util.List;

public interface QuestionItemDao {


    int save(QuestionItem questionItem);

    int delete(QuestionItem questionItem);

    int update(QuestionItem questionItem);

    QuestionItem findById(String id );

    List<QuestionItem> findAll(String questionId);
}
