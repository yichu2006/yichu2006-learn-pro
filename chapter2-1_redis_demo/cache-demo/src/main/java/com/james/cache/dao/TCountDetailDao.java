package com.james.cache.dao;

import com.james.cache.entity.TCountDetail;

public interface TCountDetailDao {

    int insertVisitCount(TCountDetail tCountDetail);
    int getVisitCount();

}