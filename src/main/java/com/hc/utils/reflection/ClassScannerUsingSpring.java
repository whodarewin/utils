package com.hc.utils.reflection;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * ClassScannerUsingSpring
 *
 * @author han.congcong
 * @date 2019/10/11
 */

public class ClassScannerUsingSpring {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassScanner.class);

    public static Set<Class> findAnnotatedClasses(String scanPackage, Class<? extends Annotation>  annotation, Class clazz)  {
        Preconditions.checkNotNull(annotation);
        ClassPathScanningCandidateComponentProvider provider
                = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(annotation));
        Set<BeanDefinition> beanDefinitionSet = provider.findCandidateComponents(scanPackage);
        Set<Class> ret = new HashSet<>(beanDefinitionSet.size());
        for(BeanDefinition beanDefinition : beanDefinitionSet){
            try {
                Class cls = Class.forName(beanDefinition.getBeanClassName());
                if(clazz != null && clazz.isAssignableFrom(cls)){
                    ret.add(cls);
                }else if(clazz == null){
                    ret.add(cls);
                }
            } catch (ClassNotFoundException e) {
                LOGGER.warn("matcher class {} load failed",beanDefinition.getBeanClassName());
            }
        }
        return ret;
    }
}
