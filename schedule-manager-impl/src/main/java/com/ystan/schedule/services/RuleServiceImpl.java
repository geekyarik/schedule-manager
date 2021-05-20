package com.ystan.schedule.services;

import com.ystan.schedule.mappers.RuleMapper;
import com.ystan.schedule.models.Rule;
import com.ystan.schedule.models.RuleDTO;
import com.ystan.schedule.repositories.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleMapper ruleMapper;

    @Autowired
    private RuleRepository ruleRepository;

    @Override
    public RuleDTO saveOrUpdate(RuleDTO model) {
        final Rule saved = ruleRepository.save(ruleMapper.toEntity(model));
        return ruleMapper.toDto(saved);
    }

    @Override
    public RuleDTO findById(String s) {
        return ruleMapper.toDto(ruleRepository.getOne(s));
    }

    @Override
    public String delete(String s) {
        ruleRepository.deleteById(s);

        return s;
    }

    @Override
    public List<RuleDTO> findBySchoolId(String id) {
        return ruleRepository.findBySchoolId(id).stream()
                .map(ruleMapper::toDto)
                .collect(Collectors.toList());
    }
}
