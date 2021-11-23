package com.example.studentlistapp.model;

import java.util.LinkedList;
import java.util.List;

public class Model {
    public static final Model instance = new Model();

    List<Student> data = new LinkedList<Student>();

    private Model(){
        //for(int i=0;i<1;i++){
            Student s = new Student("Test","1234",true);
            data.add(s);
       // }
    }

    public List<Student> getAllStudents(){
        return data;
    }

    public void addStudent(Student student){
        data.add(student);
    }

    public void deleteStudent(Student student){data.remove(student);}


}
