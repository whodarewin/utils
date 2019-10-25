package com.hc.utils.sample;

import org.apache.commons.collections.CollectionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * TestAspect
 *
 * @author han.congcong
 * @date 2019/10/25
 */
@Component
@Aspect
class TestAspect {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Before(value = "execution(* com.hc.utils.sample.*.*(..))")
    public void authority(JoinPoint joinPoint) {
        //获得验证器
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {

            Set<ConstraintViolation<Object>> constraintViolations = validator.validate(arg);

            if (CollectionUtils.isEmpty(constraintViolations)) {
                continue;

            }
            ConstraintViolation violation = constraintViolations.iterator().next();

            throw new IllegalArgumentException(violation.getPropertyPath() + " " + violation.getMessage());

        }

    }
}
