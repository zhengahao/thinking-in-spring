package com.example.lambda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;


/**
 * @Author：zzh
 * @Description: url: https://mp.weixin.qq.com/s/G0UfF78dpzWLUKTZsuR3Fw
 * @Date: 2021/2/3 6:56 下午
 */
public class LambdaCollectDemo {

    public static void main(String[] args) {

        //testCollect();
        //testFilter();
        //testMap();
        //testFlagMap();
        //testMaxMin();
        //testCount();
        //testReduce();
        //testCollections();
        //testPartitioning();
        //testGrouping();
        testJoining();
    }


    private static void testFilter() {
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 22, 175));
        students.add(new Student("红发", 40, 180));
        students.add(new Student("白胡子", 50, 185));

        List<Student> list = students.stream()
                .filter(student -> student.getHeight() < 180)
                .collect(Collectors.toList());

        System.out.println(list);
    }

    private static void testCollect() {
        List<Student> list = Stream.of(
                new Student("路飞", 22, 175),
                new Student("红发", 40, 180),
                new Student("白胡子", 50, 185)
        ).collect(Collectors.toList());

        System.out.println(list);
    }

    private static void testMap() {
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 22, 175));
        students.add(new Student("红发", 40, 180));
        students.add(new Student("白胡子", 50, 185));

        List<String> list = students.stream()
                .map(Student::getName)
                .collect(Collectors.toList());
        System.out.println(list);
    }

    private static void testFlagMap() {
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 22, 175));
        students.add(new Student("红发", 40, 180));
        students.add(new Student("白胡子", 50, 185));


        List<Student> students2 = new ArrayList<>(3);

        students2.add(new Student("艾斯", 25, 183));
        students2.add(new Student("雷利", 48, 176));
        List<Student> studentList = Stream.of(students, students2)
                .flatMap(students1 -> students1.stream())
                .collect(Collectors.toList());

        System.out.println(studentList);
    }

    private static void testMaxMin() {
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 22, 175));
        students.add(new Student("红发", 40, 180));
        students.add(new Student("白胡子", 50, 185));

        Optional<Student> max = students.stream().max(comparing(student -> student.getAge()));
        Optional<Student> min = students.stream().min(comparing(student -> student.getAge()));

        if (max.isPresent()) {
            System.out.println(max.get());
        }

        if (min.isPresent()) {
            System.out.println(min.get());
        }

    }

    private static void testCount() {
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 22, 175));
        students.add(new Student("红发", 40, 180));
        students.add(new Student("白胡子", 50, 185));

        long count = students.stream().filter(student -> student.getAge() < 45).count();
        System.out.println("年龄小于45岁的人数是：" + count);
    }

    /**
     * reduce 操作可以实现从一组值中生成一个值 。
     */
    private static void testReduce() {
        int count = Stream.of(1, 2, 3, 4).reduce(0, (accx, x) -> accx + x);
        System.out.println("我们看得reduce接收了一个初始值为0的累加器，依次取出值与累加器相加，最后累加器的值就是最终的结果：" + count);
    }


    private static void testCollections() {
        List<Student> students1 = new ArrayList<>(3);
        students1.add(new Student("路飞", 23, 175));
        students1.add(new Student("红发", 40, 180));
        students1.add(new Student("白胡子", 50, 185));

        OutstandingClass ostClass1 = new OutstandingClass("一班", students1);
        //复制students1，并移除一个学生
        List<Student> students2 = new ArrayList<>(students1);
        students2.remove(1);
        OutstandingClass ostClass2 = new OutstandingClass("二班", students2);
        //将ostClass1、ostClass2转换为Stream
        Stream<OutstandingClass> classStream = Stream.of(ostClass1, ostClass2);
        OutstandingClass outstandingClass = biggestGroup(classStream);
        System.out.println("人数最多的班级是：" + outstandingClass.getName());

        System.out.println("一班平均年龄是：" + averageNumberOfStudent(students1));

    }

    /**
     * 常用的流操作是将其分解成两个集合，Collectors.partitioningBy帮我们实现了，
     * 接收一个Predicate函数式接口。
     * <p>
     * 将示例学生分为会唱歌与不会唱歌的两个集合。
     */
    private static void testPartitioning() {

        List<Student> students1 = new ArrayList<>(3);

        List<SpecialityEnum> lufeiSpeciality = Stream.of(SpecialityEnum.SING, SpecialityEnum.DANCE).collect(Collectors.toList());
        List<SpecialityEnum> hongfaSpeciality = Stream.of(SpecialityEnum.RUNNING, SpecialityEnum.DANCE).collect(Collectors.toList());
        List<SpecialityEnum> baihuziSpeciality = Stream.of(SpecialityEnum.SWING, SpecialityEnum.DANCE).collect(Collectors.toList());

        students1.add(new Student("路飞", 23, 175, lufeiSpeciality));
        students1.add(new Student("红发", 40, 180, hongfaSpeciality));
        students1.add(new Student("白胡子", 50, 185, baihuziSpeciality));

        Map<Boolean, List<Student>> listMap = students1.stream().collect(
                Collectors.partitioningBy(
                        student -> student.getSpecialitys().contains(SpecialityEnum.SING)));

        System.out.println(listMap);
    }

    /**
     * 数据分组是一种更自然的分割数据操作，与将数据分成 ture 和 false 两部分不同，可以使用任意值对数据分组。
     * Collectors.groupingBy接收一个Function做转换。
     */
    private static void testGrouping() {

        List<Student> students1 = new ArrayList<>(3);

        List<SpecialityEnum> lufeiSpeciality = Stream.of(SpecialityEnum.SING, SpecialityEnum.DANCE).collect(Collectors.toList());
        List<SpecialityEnum> hongfaSpeciality = Stream.of(SpecialityEnum.RUNNING, SpecialityEnum.DANCE).collect(Collectors.toList());
        List<SpecialityEnum> baihuziSpeciality = Stream.of(SpecialityEnum.SWING, SpecialityEnum.DANCE).collect(Collectors.toList());

        students1.add(new Student("路飞", 23, 175, lufeiSpeciality));
        students1.add(new Student("红发", 40, 180, hongfaSpeciality));
        students1.add(new Student("白胡子", 50, 185, baihuziSpeciality));

        Map<SpecialityEnum, List<Student>> map = students1.stream().collect(
                Collectors.groupingBy(student -> student.getSpecialitys().get(0)));

        //Collectors.groupingBy与SQL 中的 group by 操作是一样的。
        System.out.println(map);

    }

    private static void testJoining() {
        List<Student> students1 = new ArrayList<>(3);

        students1.add(new Student("路飞", 23, 175));
        students1.add(new Student("红发", 40, 180));
        students1.add(new Student("白胡子", 50, 185));

        String name = students1.stream().map(Student::getName).collect(Collectors.joining(",","[","]"));
        System.out.println(name);

    }

    /**
     * 获取人数最多的班级
     */
    private static OutstandingClass biggestGroup(Stream<OutstandingClass> outstandingClasses) {
        //maxBy或者minBy就是求最大值与最小值。
        return outstandingClasses.collect(
                Collectors.maxBy(Comparator.comparing(ostClass -> ostClass.getStudents().size())))
                .orElseGet(OutstandingClass::new);
    }

    /**
     * 计算平均年龄
     */
    private static double averageNumberOfStudent(List<Student> students) {
        return students.stream().collect(Collectors.averagingInt(Student::getAge));
    }

}
