package com.duan1.nhom4.demomvp.data.source;

import com.duan1.nhom4.demomvp.data.Callback;
import com.duan1.nhom4.demomvp.data.model.Student;

import java.util.List;

public interface StudentDataSource {
    interface Local {
        void getStudents(Callback<List<Student>> callback);

        void addStudent(Student student);
    }

    interface Remote {
        void getStudentsFromSever(Callback<List<Student>> callback);
    }
}
