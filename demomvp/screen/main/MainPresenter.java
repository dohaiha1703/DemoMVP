package com.duan1.nhom4.demomvp.screen.main;

import com.duan1.nhom4.demomvp.data.Callback;
import com.duan1.nhom4.demomvp.data.model.Student;
import com.duan1.nhom4.demomvp.data.repository.StudentRepository;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    private StudentRepository mRepository;
    private MainContract.View mView;

    public MainPresenter(StudentRepository repository) {
        mRepository = repository;
    }

    @Override
    public void setView(MainContract.View view) {
        mView = view;
    }

    @Override
    public void addStudent(String name, String birthDay, String classStudent) {
        mRepository.addStudent(name, birthDay, classStudent);
    }

    @Override
    public void getData() {
        mRepository.getData(new Callback<List<Student>>() {
            @Override
            public void onGetDataSuccess(List<Student> data) {
                mView.onGetStudentSuccess(data);
            }

            @Override
            public void onGetDataFailed(Exception e) {
                mView.onGetDataFailed(e);
            }
        });
    }

}
