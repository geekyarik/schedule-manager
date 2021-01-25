package com.ystan.schedule.services;

import com.ystan.schedule.mappers.SubjectMapper;
import com.ystan.schedule.models.Subject;
import com.ystan.schedule.models.SubjectDTO;
import com.ystan.schedule.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public SubjectDTO saveOrUpdate(SubjectDTO subjectDto) {
        Subject subject = subjectMapper.toEntity(subjectDto);
        Subject saved = subjectRepository.save(subject);

        return subjectMapper.toDto(saved);
    }

    @Override
    public SubjectDTO findById(String id) {
        return subjectRepository.findById(id)
                .map(subjectMapper::toDto)
                .orElse(null);
    }

    @Override
    public Subject findEntityById(String id) {
        return subjectRepository.findById(id).orElse(null);
    }
}
