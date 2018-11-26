package com.duan1.nhom4.demomvp.data.model;

public class Student {

    private int mNo;
    private String mName;
    private String mBirth;
    private String mClassStudent;

    public Student(int no, String name, String birth, String classStudent) {
        mNo = no;
        mName = name;
        mBirth = birth;
        mClassStudent = classStudent;
    }

    public int getNo() {
        return mNo;
    }

    public void setNo(int no) {
        mNo = no;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getBirth() {
        return mBirth;
    }

    public void setBirth(String birth) {
        mBirth = birth;
    }

    public String getClassStudent() {
        return mClassStudent;
    }

    public void setClassStudent(String classStudent) {
        mClassStudent = classStudent;
    }

    public Student() {

    }
}
