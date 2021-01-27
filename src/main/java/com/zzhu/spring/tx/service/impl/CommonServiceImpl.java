package com.zzhu.spring.tx.service.impl;

import com.zzhu.spring.tx.service.CommonService;
import com.zzhu.spring.tx.service.IStudentService;
import com.zzhu.spring.tx.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 只要最外层事务能捕获到异常，不管里层事务是什么类型 都会回滚
 *
 * nested 嵌套事务 还是在原来事务的基础上 。还是和原来的事务一起提交 只是设置了回滚点，回滚到设置回滚点时的哪个事务 最外层事务用NESTED是不会创建回滚点的
 *
 * 只要异常被当前事务监测到  那么当前事务肯定会回滚
 *
 *
 * 情况分析： 前提 主事务的异常不外抛，否则事务都会回滚  student先执行
 * 1.若student用 REQUIRED  teacher用NESTED  当teacher抛异常时 student不会回滚
 * 2.若student用 REQUIRED  teacher用REQUIRED  当teacher抛异常时 student会回滚    这里对RConnectionHolder的rollbackOnly设置成了true
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private IStudentService studentService;
    @Autowired
    private ITeacherService teacherService;
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void transaction(){
        //这种情况下事务仍能回滚，因为内部方法只要抛出异常就能通知到ConnectionHolder设置RowBackOnly的属性值为true
        //Transaction rolled back because it has been marked as rollback-only
      try {
          studentService.insertStudent();
          System.out.println(TransactionSynchronizationManager.getCurrentTransactionName()+"main====");
            teacherService.insertTeacher();
      } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
