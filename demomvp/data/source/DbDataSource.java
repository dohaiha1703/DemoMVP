package com.duan1.nhom4.demomvp.data.source;

import com.duan1.nhom4.demomvp.data.Callback;
import com.duan1.nhom4.demomvp.data.model.Student;

import java.util.List;

public interface DbDataSource {
    interface Local {
        void getData(Callback<List<Student>> callback);

        void addStudent(String name, String birthDay, String classStudent);
    }

    interface Remote {
        void getDataFromSever();
    }
}
