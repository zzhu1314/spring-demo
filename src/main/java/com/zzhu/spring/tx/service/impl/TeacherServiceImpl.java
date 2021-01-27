package com.zzhu.spring.tx.service.impl;

import com.zzhu.spring.tx.entity.Teacher;
import com.zzhu.spring.tx.mapper.TeacherMapper;
import com.zzhu.spring.tx.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertTeacher() {
        Teacher teacher = new Teacher();
        teacher.setAge(12);
        teacher.setName("miss Zhang");
        teacherMapper.insertTeacher(teacher);
        throw new RuntimeException();
    }
}
