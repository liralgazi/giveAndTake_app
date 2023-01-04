package com.example.class3demo2.model;

import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Model {
    private static final Model _instance = new Model();

    private Executor executor = Executors.newSingleThreadExecutor();
    private Handler mainHandler = HandlerCompat.createAsync(Looper.getMainLooper());
    private FirebaseModel firebaseModel = new FirebaseModel();

    public static Model instance(){
        return _instance;
    }
    private Model(){
    }

    AppLocalDbRepository localDb = AppLocalDb.getAppDb();
    public interface GetAllStudentsListener{
        void onComplete(List<Student> data);
    }
    public void getAllStudents(GetAllStudentsListener callback){
        firebaseModel.getAllStudents(callback);
//        executor.execute(()->{
//            List<Student> data = localDb.studentDao().getAll();
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            mainHandler.post(()->{
//                callback.onComplete(data);
//            });
//        });
    }

    public interface AddStudentListener{
        void onComplete();
    }
    public void addStudent(Student st, AddStudentListener listener){
        firebaseModel.addStudent(st,listener);
//        executor.execute(()->{
//            localDb.studentDao().insertAll(st);
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            mainHandler.post(()->{
//                listener.onComplete();
//            });
//        });
    }


}
