package com.duan1.nhom4.demomvp.data.model;

public class Student {

    private int mNo;
    private String mName;
    private String mBirthDay;
    private String mClassStudent;

    public Student(int no, String name, String birthDay, String classStudent) {
        mNo = no;
        mName = name;
        mBirthDay = birthDay;
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

    public String getBirthDay() {
        return mBirthDay;
    }

    public void setBirthDay(String birthDay) {
        mBirthDay = birthDay;
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
