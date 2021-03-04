package org.example.thinking.dependency.lookup.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Map;

/**
 * @Author：zzh
 * @Description: TODO
 * @Date: 2021/3/3 10:59 上午
 */
public class CollectionLookUpDemo {

    @Bean
    public String message() {
        return "message";
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(CollectionLookUpDemo.class);

        applicationContext.refresh();

        String[] beanNames = applicationContext.getBeanNamesForType(String.class);
        Map<String, Object> maps = applicationContext.getBeansWithAnnotation(Bean.class);
        Annotation annotation = applicationContext.findAnnotationOnBean("message", Bean.class);

        System.out.println("beanNames: " + Arrays.toString(beanNames));
        System.out.println("maps:  " + maps);
        System.out.println("annotation:  " + annotation);


        applicationContext.close();

    }
}
