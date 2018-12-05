package com.duan1.nhom4.demomvp.data;

public interface Callback<T> {
    void onGetDataSuccess(T data);

    void onGetDataFailed(Exception e);
}
