package com.duan1.nhom4.demomvp.data.source.remote;

import com.duan1.nhom4.demomvp.data.Callback;
import com.duan1.nhom4.demomvp.data.model.Student;
import com.duan1.nhom4.demomvp.data.source.StudentDataSource;

import java.util.List;

public class RemoteStudentDatasource implements StudentDataSource.Remote {

    @Override
    public void getStudentsFromSever(Callback<List<Student>> callback) {

    }
}
