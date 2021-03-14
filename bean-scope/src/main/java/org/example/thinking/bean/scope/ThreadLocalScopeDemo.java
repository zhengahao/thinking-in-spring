package org.example.thinking.bean.scope;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;


/**
 * 自定义 Scope {@link ThreadLocalScope} 示例
 */
public class ThreadLocalScopeDemo {

    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    public User getUser() {
        return createUser();
    }

    public static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    public static void main(String[] args) {
        // 创建容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });

        applicationContext.register(ThreadLocalScopeDemo.class);

        applicationContext.refresh();

        scopeBeansByLookUp(applicationContext);

        applicationContext.close();

    }

    private static void scopeBeansByLookUp(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            // singletonUser是共享bean对象
            Thread thread = new Thread(() -> {
                User user = applicationContext.getBean("user", User.class);
                System.out.printf("[Thread id: %d ] user= %s%n", Thread.currentThread().getId(), user);
            });
            thread.start();
            // 强制线程执行完成
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
