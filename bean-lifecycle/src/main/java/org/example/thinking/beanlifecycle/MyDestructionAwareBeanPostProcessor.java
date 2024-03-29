package org.example.thinking.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * {@link DestructionAwareBeanPostProcessor} 实现
 */
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            // afterSingletonsInstantiated() = The user Holder V8
            // userHolder 的description此时等于The user holder V8
            userHolder.setDescription("user holder V9");
            System.out.println("postProcessBeforeDestruction(): " + userHolder.getDescription());
        }
    }
}
