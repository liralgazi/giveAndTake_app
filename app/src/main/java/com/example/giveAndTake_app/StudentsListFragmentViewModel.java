package com.example.giveAndTake_app;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.giveAndTake_app.model.Model;
import com.example.giveAndTake_app.model.Student;

import java.util.List;

public class StudentsListFragmentViewModel extends ViewModel {
    private LiveData<List<Student>> data = Model.instance().getAllStudents();

    LiveData<List<Student>> getData(){
        return data;
    }
}
