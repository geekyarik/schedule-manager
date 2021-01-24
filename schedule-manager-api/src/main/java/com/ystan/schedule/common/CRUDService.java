package com.ystan.schedule.common;

public interface CRUDService<ID, MODEL> {

    MODEL saveOrUpdate(MODEL model);

    MODEL findById(ID id);
}
