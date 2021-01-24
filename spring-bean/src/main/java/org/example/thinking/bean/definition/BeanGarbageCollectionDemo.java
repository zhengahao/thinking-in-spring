package org.example.thinking.bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author：zzh
 * @Description: Bean 垃圾回收 GC示例
 * @Date: 2021/1/10 9:08 下午
 */
public class BeanGarbageCollectionDemo {

    public static void main(String[] args) throws InterruptedException {
        //创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class配置类
        applicationContext.register(BeanInitializationDemo.class);
        //启动spring应用上下文
        applicationContext.refresh();
        //关闭spring应用上下文
        applicationContext.close();
        System.out.println("Spring应用上下文已关闭...");

        Thread.sleep(1000L);
        //强制触发GC，这个方法不一定每次都会执行
        System.gc();
        Thread.sleep(1000L);
    }

}
