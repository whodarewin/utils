package com.hc.utils.sample;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.validation.Valid;


/**
 * BeanValidatorSample
 *
 * @author han.congcong
 * @date 2019/10/25
 */
@Component("test")
public class BeanValidatorSample {

    public boolean test(SomeModel someModel,int i){
        Preconditions.checkNotNull(someModel);
        System.out.println(someModel);
        return true;
    }

    public static void main(String[] args){
        BeanFactory ctx = new ClassPathXmlApplicationContext("spring.xml");
        BeanValidatorSample foo = (BeanValidatorSample)ctx.getBean("test");
        SomeModel model = new SomeModel();
        model.setEmail("abc@126.com");
        model.setUserName("cc");
        foo.test(model,1);
    }

}






