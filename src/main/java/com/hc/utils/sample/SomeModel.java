package com.hc.utils.sample;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * SampleModel
 *
 * @author han.congcong
 * @date 2019/10/25
 */
public class SomeModel{

    @NotNull(message = "用户名不能为null")
    private String userName;
    @Email(message = "email格式不正确")
    private String email;

    @Max(value = 20,message = "年龄最大不能超过20岁")
    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SomeModel{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
