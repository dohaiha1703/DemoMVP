package com.duan1.nhom4.demomvp.data.source;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.constraint.ConstraintLayout;
import android.util.Log;

import com.duan1.nhom4.demomvp.data.Callback;
import com.duan1.nhom4.demomvp.data.model.Student;

import java.util.ArrayList;
import java.util.List;

public class DbStudentManager extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "STUDENT";
    private static final String COLUMN_NO = "NUMBER";
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_BIRTH = "BIRTHDAY";
    private static final String COLUMN_CLASS = "CLASSSTUDENT";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + COLUMN_NO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NAME + " TEXT, "
                    + COLUMN_BIRTH + " DATETIME, "
                    + COLUMN_CLASS + " TEXT )";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Student_db";

    private static DbStudentManager sInstance;

    public static DbStudentManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DbStudentManager(context);
        }
        return sInstance;
    }

    private DbStudentManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void insertStudent(String name, String birthDay, String classStudent) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, name);
            values.put(COLUMN_BIRTH, String.valueOf(birthDay));
            values.put(COLUMN_CLASS, classStudent);
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
                    student.setBirthDay(cursor.getString(cursor.getColumnIndex(DbStudentManager.COLUMN_BIRTH)));
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
