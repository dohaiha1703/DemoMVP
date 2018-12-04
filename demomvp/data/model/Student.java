package com.duan1.nhom4.demomvp.data.model;

public class Student {
    private int mId;
    private String mName;
    private String mBirthDay;
    private String mClassStudent;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
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
