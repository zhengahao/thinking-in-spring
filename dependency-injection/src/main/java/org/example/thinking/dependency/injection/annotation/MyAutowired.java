package org.example.thinking.dependency.injection.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

/**
 * 自定义注解（元标注）
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Autowired //Target=ElementType.ANNOTATION_TYPE，所以Autowired支持标注在注解上面。java里面不允许注解进行继承，只能在注解上面打标注
public @interface MyAutowired {

    boolean required() default true;

}
