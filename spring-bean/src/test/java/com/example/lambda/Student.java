package com.example.lambda;

import java.util.List;

/**
 * @Author：zzh
 * @Description: 学生
 * @Date: 2021/2/3 6:08 下午
 */
public class Student {

    private String name;
    private int age;
    private int height;
    private List<SpecialityEnum> specialitys;

    public Student(String name, int age, int height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public Student(String name, int age, int height, List<SpecialityEnum> specialitys) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.specialitys = specialitys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }

    public List<SpecialityEnum> getSpecialitys() {
        return specialitys;
    }

    public void setSpecialitys(List<SpecialityEnum> specialitys) {
        this.specialitys = specialitys;
    }
}
