package com.duan1.nhom4.demomvp.data.source.local;

import com.duan1.nhom4.demomvp.data.Callback;
import com.duan1.nhom4.demomvp.data.model.Student;
import com.duan1.nhom4.demomvp.data.source.StudentDataSource;
import com.duan1.nhom4.demomvp.data.source.StudentDatabaseManager;

import java.util.List;

public class LocalStudentDataSource implements StudentDataSource.Local {

    private StudentDatabaseManager mDbManager;
    private static LocalStudentDataSource sInstance;

    public static LocalStudentDataSource getInstance(StudentDatabaseManager dbManager) {
        if (sInstance == null) {
            sInstance = new LocalStudentDataSource(dbManager);
        }
        return sInstance;
    }

    private LocalStudentDataSource(StudentDatabaseManager dbManager) {
        mDbManager = dbManager;
    }

    @Override
    public void getStudents(Callback<List<Student>> callback) {
        mDbManager.getStudents(callback);
    }

    @Override
    public void addStudent(Student student) {
        mDbManager.insertStudent(student);
    }
}
