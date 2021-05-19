package com.ystan.schedule.services.common;

public interface CRUDService<ID, DTO> {

    DTO saveOrUpdate(DTO model);

    DTO findById(ID id);

    ID delete(ID id);
}
