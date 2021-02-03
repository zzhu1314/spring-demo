package com.zzhu.spring.tx;

import com.zzhu.spring.tx.annotation.MyService;
import org.aspectj.apache.bcel.classfile.InnerClass;

import java.lang.reflect.Method;

public class A {
    public InnerClass innerClass;

    @MyService
    public void setInner() {
        this.innerClass = new InnerClass();
    }

    public void way() {
        System.out.println("way====");
    }

    public class InnerClass {
        public void match() {
            way();
        }
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method methodC = C.class.getMethod("setInner", null);
        System.out.println(methodC.isAnnotationPresent(MyService.class));
        Method methodB = B.class.getMethod("setInner", null);
        System.out.println(methodB.isAnnotationPresent(MyService.class));


    }

}

/*class B {
    public void test() {
    public static void main(String[] args) {
        new A().new InnerClass().match();
    }
}
    }*/


