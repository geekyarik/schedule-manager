package com.ystan.schedule.services;

import com.ystan.schedule.mappers.SchoolMapper;
import com.ystan.schedule.models.School;
import com.ystan.schedule.models.SchoolDTO;
import com.ystan.schedule.repositories.SchoolRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public SchoolDTO saveOrUpdate(SchoolDTO schoolDto) {
        School school = schoolMapper.toEntity(schoolDto);
        School saved = schoolRepository.save(school);

        log.info("saved school ", saved);
        return schoolMapper.toDto(saved);
    }

    @Override
    public SchoolDTO findById(String id) {
        log.info("looking for school with id: ", id);
        return schoolRepository.findById(id)
                .map(schoolMapper::toDto)
                .orElse(null);
    }

    @Override
    public School findEntityById(String id) {
        return schoolRepository.findById(id).orElse(null);
    }

    @Override
    public Set<SchoolDTO> getAll() {
        HashSet<SchoolDTO> resultsSet = new HashSet<>();
        schoolRepository.findAll().stream().map(schoolMapper::toDto).forEach(resultsSet::add);
        return resultsSet;
    }

    @Override
    public String delete(String id) {
        schoolRepository.deleteById(id);
        return id;
    }
}
