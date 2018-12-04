package com.duan1.nhom4.demomvp.data.repository;

import com.duan1.nhom4.demomvp.data.Callback;
import com.duan1.nhom4.demomvp.data.model.Student;
import com.duan1.nhom4.demomvp.data.source.StudentDataSource;

import java.util.List;

public class StudentRepository implements StudentDataSource.Local, StudentDataSource.Remote {

    private StudentDataSource.Local mLocal;
    private StudentDataSource.Remote mRemote;
    private static StudentRepository sInstance;

    public static StudentRepository getInstance(StudentDataSource.Local local,
                                                StudentDataSource.Remote remote) {
        if (sInstance == null) {
            sInstance = new StudentRepository(local, remote);
        }
        return sInstance;
    }

    private StudentRepository(StudentDataSource.Local local, StudentDataSource.Remote remote) {
        mLocal = local;
        mRemote = remote;
    }

    @Override
    public void getStudents(Callback<List<Student>> callback) {
        mLocal.getStudents(callback);
    }

    @Override
    public void addStudent(Student student) {
        mLocal.addStudent(student);
    }

    @Override
    public void getStudentsFromSever(Callback<List<Student>> callback) {
        mRemote.getStudentsFromSever(callback);
    }
}
