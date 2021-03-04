package com.example.thinking.test.lambda;

import java.util.List;

/**
 * @Author：zzh
 * @Description: 班级
 * @Date: 2021/2/7 3:21 下午
 */
public class OutstandingClass {

    private String name;

    private List<Student> students;

    public OutstandingClass(String name, List<Student> students) {
        this.name = name;
        this.students = students;
    }

    public OutstandingClass() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
