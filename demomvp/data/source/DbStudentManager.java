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
    private static final String TABLE_NAME = "STUDENT_LIST";
    private static final String COLUMN_NO = "NUMBER";
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_BIRTH = "BIRTH";
    private static final String COLUMN_CLASS = "CLASS_STUDENT";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + COLUMN_NO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NAME + " TEXT, "
                    + COLUMN_BIRTH + " DATETIME, "
                    + COLUMN_CLASS + " TEXT )";


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Student_db";

    public DbStudentManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DbStudentManager", "DbStudentManager");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void insertStudent(String mName, String mBirth, String mClass) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, mName);
            values.put(COLUMN_BIRTH, String.valueOf(mBirth));
            values.put(COLUMN_CLASS, mClass);
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
                    student.setNo(cursor.getInt(cursor.getColumnIndex(DbStudentManager.COLUMN_NO)));
                    student.setName(cursor.getString(cursor.getColumnIndex(DbStudentManager.COLUMN_NAME)));
                    student.setBirth(cursor.getString(cursor.getColumnIndex(DbStudentManager.COLUMN_BIRTH)));
                    student.setClassStudent(cursor.getString(cursor.getColumnIndex(DbStudentManager.COLUMN_CLASS)));
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
