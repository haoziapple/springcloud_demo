package com.haozi.springboot.hostess.bean;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Administrator on 2017/9/11.
 */
public class TestValidateBean {
    @NotBlank(message = "test1 is blank")
    private String test1;
    @NotBlank(message = "test2 is blank")
    private String test2;
    @NotBlank(message = "test3 is blank")
    private String test3;
    public String getTest1() {
        return test1;
    }

    public void setTest1(String test1) {
        this.test1 = test1;
    }

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2;
    }

    public String getTest3() {
        return test3;
    }

    public void setTest3(String test3) {
        this.test3 = test3;
    }



    @Override
    public String toString() {
        return "TestValidateBean{" +
                "test2='" + test2 + '\'' +
                ", test3='" + test3 + '\'' +
                ", test1='" + test1 + '\'' +
                '}';
    }
}
