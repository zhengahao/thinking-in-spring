package com.example.lambda;

import java.math.BigDecimal;
import java.util.function.*;

/**
 * @Author：zzh
 * @Description: TODO
 * @Date: 2021/2/3 6:06 下午
 */
public class LambdaDemo {

    public static void main(String[] args) {
        Predicate<Integer> predicate = x -> x > 185;

        Student student = new Student("9🐲", 23, 175);
        System.out.println("9龙的身高高于185吗？：" + predicate.test(student.getHeight()));

        Consumer<String> consumer = System.out::println;
        consumer.accept("立春快乐～");


        // 将T映射为R（转换功能）
        Function<Student, String> function = Student::getName;
        String name = function.apply(student);
        System.out.println(name);

        // 生产消息
        Supplier<Integer> supplier = () -> Integer.valueOf(BigDecimal.TEN.toString());
        System.out.println(supplier.get());

        // 一元操作: 逻辑非（!）
        UnaryOperator<Boolean> unaryOperator = uglily -> !uglily;
        boolean apply2 = unaryOperator.apply(true);
        System.out.println(apply2);

        // 二元操作: 求两个数的乘积（*）
        BinaryOperator<Integer> binaryOperator = (x, y) -> x * y;
        Integer apply = binaryOperator.apply(1, 2);
        System.out.println(apply);

        test(() -> "我是一个演示的函数式接口");

    }

    private static void test(Worker worker) {
        String work = worker.worker();
        System.out.println(work);
    }


    public interface Worker {
        String worker();
    }
}
