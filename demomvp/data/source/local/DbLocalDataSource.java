package com.duan1.nhom4.demomvp.data.source.local;

import com.duan1.nhom4.demomvp.data.Callback;
import com.duan1.nhom4.demomvp.data.model.Student;
import com.duan1.nhom4.demomvp.data.source.DbDataSource;
import com.duan1.nhom4.demomvp.data.source.DbStudentManager;

import java.util.List;

public class DbLocalDataSource implements DbDataSource.Local {

    private DbStudentManager mDbManager;
    private static DbLocalDataSource sInstance;

    public static DbLocalDataSource getInstance(DbStudentManager dbManager) {
        if (sInstance == null) {
            sInstance = new DbLocalDataSource(dbManager);
        }
        return sInstance;
    }

    private DbLocalDataSource(DbStudentManager dbManager) {
        mDbManager = dbManager;
    }

    @Override
    public void getData(Callback<List<Student>> callback) {
        mDbManager.getAllStudent(callback);
    }

    @Override
    public void addStudent(String name, String birthDay, String classStudent) {
        mDbManager.insertStudent(name, birthDay, classStudent);
    }

}
