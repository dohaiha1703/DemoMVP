package com.duan1.nhom4.demomvp.data.repository;

import com.duan1.nhom4.demomvp.data.Callback;
import com.duan1.nhom4.demomvp.data.model.Student;
import com.duan1.nhom4.demomvp.data.source.DbDataSource;

import java.util.List;

public class StudentRepository implements DbDataSource.Local, DbDataSource.Remote {

    private DbDataSource.Local mLocal;
    private DbDataSource.Remote mRemote;
    private static StudentRepository sInstance;

    public static StudentRepository getInstance(DbDataSource.Local local, DbDataSource.Remote remote) {
        if (sInstance == null) {
            sInstance = new StudentRepository(local, remote);
        }
        return sInstance;
    }

    private StudentRepository(DbDataSource.Local local, DbDataSource.Remote remote) {
        mLocal = local;
        mRemote = remote;
    }

    @Override
    public void getData(Callback<List<Student>> callback) {
        mLocal.getData(callback);
    }

    @Override
    public void addStudent(String name, String birthDay, String classStudent) {
        mLocal.addStudent(name, birthDay, classStudent);
    }

    @Override
    public void getDataFromSever() {
        mRemote.getDataFromSever();
    }
}
