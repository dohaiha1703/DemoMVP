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
    public void addStudent(String ten, String ngay_sinh, String lop) {
        mRepository.addStudent(ten, ngay_sinh, lop);
    }

    @Override
    public void getData(){
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
