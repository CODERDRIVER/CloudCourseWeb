package edu.xidian.sselab.cloudcourse.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        List<Student> lists = new ArrayList<Student>();
        Student student = new Student();
        student.setUsername("lxy");
        student.setAge(100);
        Student student1 = new Student();
        student1.setUsername("sq");
        student1.setAge(103);
        Student student2 = new Student();
        student2.setUsername("lq");
        student2.setAge(310);
        Student student3 = new Student();
        student3.setUsername("hpl");
        student3.setAge(130);
        Student student4 = new Student();
        student4.setUsername("lxy");
        student4.setAge(120);
        lists.add(student);
        lists.add(student1);
        lists.add(student2);
        lists.add(student3);
        lists.add(student4);
        Collections.sort(lists, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge()-o2.getAge();
            }
        });
        for(Student s:lists)
        {
            System.out.println(s.toString());
        }
    }
}
