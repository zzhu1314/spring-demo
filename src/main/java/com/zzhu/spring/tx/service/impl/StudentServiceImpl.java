package com.zzhu.spring.tx.service.impl;

import com.zzhu.spring.tx.entity.Student;
import com.zzhu.spring.tx.mapper.StudentMapper;
import com.zzhu.spring.tx.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Transactional(propagation = Propagation.NESTED,isolation = Isolation.DEFAULT)
    @Override
    public void insertStudent() {
        //这种情况下事务就不能回滚，因为spring在methodInterceptor的链式调用时无法catch到
     /*   try {*/
            Student student = new Student("zhuzhou",24);
            System.out.println(TransactionSynchronizationManager.getCurrentTransactionName()+" student=====");
            studentMapper.insert(student);
            //throw new RuntimeException();
      /*  } catch (RuntimeException e) {
            e.printStackTrace();
        }*/
    }
}
