package com.zzhu.spring.tx;

import com.zzhu.spring.tx.annotation.MyService;
import org.springframework.stereotype.Service;

public interface C {

    @MyService
    void setInner();
}
