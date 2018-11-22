package com.duan1.nhom4.demomvp.data.source;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.duan1.nhom4.demomvp.data.Callback;
import com.duan1.nhom4.demomvp.data.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dohaiha on 06-Apr-18.
 */

public class DbStudentManager extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "DANH_SACH_SINH_VIEN";
    public static final String COLUMN_STT = "STT";
    public static final String COLUMN_TEN_SV = "TEN_SV";
    public static final String COLUMN_NGAY_SINH = "NGAY_SINH";
    public static final String COLUMN_LOP = "LOP_HOC";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + COLUMN_STT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_TEN_SV + " TEXT, "
                    + COLUMN_NGAY_SINH + " DATETIME, "
                    + COLUMN_LOP + " TEXT )";


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "lopHoc_db";

    public DbStudentManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DbStudentManager", "DbStudentManager");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
//        Toast.makeText(context, "Create Database successfully", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);
    }


    public void insertStudent(String tenSV, String ngaySinh, String lop) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TEN_SV, tenSV);
            values.put(COLUMN_NGAY_SINH, String.valueOf(ngaySinh));
            values.put(COLUMN_LOP, lop);
            db.insertOrThrow(TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            throw e;
        } finally {
            db.endTransaction();
        }
    }

    public void getAllStudent(Callback<List<Student>> callback) {
        Exception exception = null;
        List<Student> listStudent = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + DbStudentManager.TABLE_NAME;
        SQLiteDatabase db = (this.getWritableDatabase());
        Cursor cursor = db.rawQuery(selectQuery, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Student student = new Student();
                    student.setmStt(cursor.getInt(cursor.getColumnIndex(DbStudentManager.COLUMN_STT)));
                    student.setmTen(cursor.getString(cursor.getColumnIndex(DbStudentManager.COLUMN_TEN_SV)));
                    student.setmNgaySinh(cursor.getString(cursor.getColumnIndex(DbStudentManager.COLUMN_NGAY_SINH)));
                    student.setmLop(cursor.getString(cursor.getColumnIndex(DbStudentManager.COLUMN_LOP)));
                    listStudent.add(student);
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            exception = e;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        if (exception != null) {
            callback.onGetDataFailed(exception);
            return;
        }
        callback.onGetDataSuccess(listStudent);
    }
}
