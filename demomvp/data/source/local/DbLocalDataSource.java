package com.duan1.nhom4.demomvp.data.source.local;

import com.duan1.nhom4.demomvp.data.Callback;
import com.duan1.nhom4.demomvp.data.model.Student;
import com.duan1.nhom4.demomvp.data.source.DbDataSource;
import com.duan1.nhom4.demomvp.data.source.DbStudentManager;

import java.util.List;

public class DbLocalDataSource implements DbDataSource.Local {

    private DbStudentManager mDbManager ;

    public DbLocalDataSource(DbStudentManager dbManager) {
        mDbManager = dbManager;
    }

    @Override
    public void getData(Callback<List<Student>> callback) {
      mDbManager.getAllStudent(callback);
    }

    @Override
    public void addStudent(String ten, String ngay_sinh, String lop) {
        mDbManager.insertStudent(ten, ngay_sinh, lop);
    }

}
