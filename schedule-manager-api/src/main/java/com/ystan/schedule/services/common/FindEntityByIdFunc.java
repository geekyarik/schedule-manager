package com.ystan.schedule.services.common;

public interface FindEntityByIdFunc<ID, ENTITY> {

    ENTITY findEntityById(ID id);
}
