package com.duan1.nhom4.demomvp.data.model;

/**
 * Created by dohaiha on 09-Apr-18.
 */

public class Student {

    private int mStt;
    private String mTen;
    private String mNgaySinh;
    private String mLop;

    public Student(int mStt, String mTen, String mNgaySinh, String mLop) {
        this.mStt = mStt;
        this.mTen = mTen;
        this.mNgaySinh = mNgaySinh;
        this.mLop = mLop;
    }

    public Student() {
    }

    public int getmStt() {
        return mStt;
    }

    public void setmStt(int mStt) {
        this.mStt = mStt;
    }

    public String getmTen() {
        return mTen;
    }

    public void setmTen(String mTen) {
        this.mTen = mTen;
    }

    public String getmNgaySinh() {
        return mNgaySinh;
    }

    public void setmNgaySinh(String mNgaySinh) {
        this.mNgaySinh = mNgaySinh;
    }

    public String getmLop() {
        return mLop;
    }

    public void setmLop(String mLop) {
        this.mLop = mLop;
    }
}
