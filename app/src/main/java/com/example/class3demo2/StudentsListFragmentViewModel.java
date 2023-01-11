package com.example.class3demo2;

import androidx.lifecycle.ViewModel;

import com.example.class3demo2.model.Student;

import java.util.LinkedList;
import java.util.List;

public class StudentsListFragmentViewModel extends ViewModel {
    private List<Student> data = new LinkedList<>();

    List<Student> getData(){
        return data;
    }

    void setData(List<Student> list){
        data = list;
    }
}
