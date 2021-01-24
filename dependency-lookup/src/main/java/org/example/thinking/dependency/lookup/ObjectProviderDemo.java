package org.example.thinking.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 通过{@link ObjectProvider} 进行依赖查找
 */
public class ObjectProviderDemo { // Configuration是非必须的

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类 ObjectProviderDemo 作为配置类（Configuration Class）
        applicationContext.register(ObjectProviderDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        //依赖查找对象
        lookupByObjectProvider(applicationContext);
        //关闭应用上下文
        applicationContext.close();
    }

    @Bean// 方法名称就是Bean的名称
    public String helloworld() {
        return "hello,world";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }
}
