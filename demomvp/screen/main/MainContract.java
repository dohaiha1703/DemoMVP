package com.duan1.nhom4.demomvp.screen.main;

import com.duan1.nhom4.demomvp.data.model.Student;

import java.util.List;

public class MainContract {
    interface View {
        void onGetStudentSuccess(List<Student> data);

        void onGetDataFailed(Exception e);
    }

    interface Presenter<View> {
        void setView(MainContract.View view);

        void addStudent(String ten, String ngay_sinh, String lop);

        void getData();
    }
}