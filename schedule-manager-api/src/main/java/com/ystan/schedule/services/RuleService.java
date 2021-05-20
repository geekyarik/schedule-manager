package com.ystan.schedule.services;

import com.ystan.schedule.models.Rule;
import com.ystan.schedule.models.RuleDTO;
import com.ystan.schedule.services.common.CRUDService;

import java.util.List;

public interface RuleService extends CRUDService<String, RuleDTO> {
    List<RuleDTO> findBySchoolId(String id);
}
