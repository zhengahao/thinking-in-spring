package org.example.thinking.dependency.injection.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectedUser {
}
