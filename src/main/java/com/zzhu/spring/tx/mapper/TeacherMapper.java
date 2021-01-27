package com.zzhu.spring.tx.mapper;

import com.zzhu.spring.tx.entity.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper {

    @Insert(value = "insert into teacher value(#{age},#{name})")
   void insertTeacher(Teacher teacher);
}
