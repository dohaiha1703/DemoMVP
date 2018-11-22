package com.duan1.nhom4.demomvp.data.repository;

import com.duan1.nhom4.demomvp.data.Callback;
import com.duan1.nhom4.demomvp.data.model.Student;
import com.duan1.nhom4.demomvp.data.source.DbDataSource;

import java.util.List;

public class StudentRepository implements DbDataSource.Local, DbDataSource.Remote {

    DbDataSource.Local mLocal;
    DbDataSource.Remote mRemote;

    public StudentRepository(DbDataSource.Local local, DbDataSource.Remote remote) {
        mLocal = local;
        mRemote = remote;
    }

    @Override
    public void getData(Callback<List<Student>> callback) {
        mLocal.getData(callback);
    }

    @Override
    public void addStudent(String ten, String ngay_sinh, String lop) {
        mLocal.addStudent(ten, ngay_sinh, lop);
    }

    @Override
    public void getDataFromSever() {
        mRemote.getDataFromSever();
    }
}
