package com.ystan.schedule.services;

import com.ystan.schedule.mappers.TeacherMapper;
import com.ystan.schedule.models.*;
import com.ystan.schedule.repositories.SchoolRepository;
import com.ystan.schedule.repositories.SubjectRepository;
import com.ystan.schedule.repositories.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private UserService userService;

    @Override
    public TeacherDTO saveOrUpdate(TeacherDTO teacherDto) {
        Teacher teacher = teacherMapper.toEntity(teacherDto);
        Teacher saved = teacherRepository.save(teacher);

        log.info("saved teacher ", saved);
        return teacherMapper.toDto(saved);
    }

    @Override
    public TeacherDTO findById(String id) {
        mapRegisteredUsers();
        log.info("looking for teacher with id: ", id);
        return teacherRepository.findById(id)
                .map(teacherMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<TeacherDTO> findBySubjectId(String schoolId) {
        mapRegisteredUsers();
        return teacherRepository.findBySubjectId(schoolId).stream()
                .map(teacherMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TeacherDTO> findBySchoolId(String schoolId) {
        mapRegisteredUsers();
        return teacherRepository.findBySchoolId(schoolId).stream()
                .map(teacherMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public String addSubjectToTeacher(String teacherId, String subjectId) {
        return subjectRepository.addSubjectToTeacher(teacherId, subjectId);
    }

    @Override
    public String dropSubjectFromTeacher(String teacherId, String subjectId) {
        subjectRepository.dropSubjectFromTeacher(teacherId, subjectId);

        return subjectId;
    }

    @Override
    public Teacher findEntityById(String id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public String delete(String id) {
        teacherRepository.deleteById(id);

        return id;
    }

    private void mapRegisteredUsers() {
        userService.allUsers().stream()
                .filter(this::notExistsTeacherWithEmail)
                .forEach(this::saveTeacherFromUser);
    }

    private boolean notExistsTeacherWithEmail(User user) {
        return !teacherRepository.existsByEmail(user.getEmail());
    }

    private void saveTeacherFromUser(User user) {
        final School school = schoolRepository.findAll().stream().findFirst().get();
        Teacher teacher = new Teacher();
        teacher.setEmail(user.getEmail());
        teacher.setFirstName(user.getFirstName());
        teacher.setMiddleName(user.getMiddleName());
        teacher.setLastName(user.getLastName());
        teacher.setSchool(school);

        teacherRepository.save(teacher);
    }
}
