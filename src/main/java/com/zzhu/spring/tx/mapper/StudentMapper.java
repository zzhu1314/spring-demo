package com.zzhu.spring.tx.mapper;

import com.zzhu.spring.tx.entity.Student;
import org.apache.ibatis.annotations.Insert;

public interface StudentMapper {

    @Insert(value = "insert into student (age,name) value(#{age},#{name})")
    void insert(Student student);
}
