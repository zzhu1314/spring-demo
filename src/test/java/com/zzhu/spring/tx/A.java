package com.zzhu.spring.tx;

import org.aspectj.apache.bcel.classfile.InnerClass;

public class A {
    public InnerClass innerClass;

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

    public static void main(String[] args) {

    }
}

class B {
    public void test() {


    }

    public static void main(String[] args) {
        new A().new InnerClass().match();
    }
}