package com.duan1.nhom4.demomvp.data.source;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.duan1.nhom4.demomvp.data.Callback;
import com.duan1.nhom4.demomvp.data.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDatabaseManager extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "STUDENT";
    private static final String COLUMN_ID = "NUMBER";
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_BIRTH = "BIRTHDAY";
    private static final String COLUMN_CLASS = "CLASSSTUDENT";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NAME + " TEXT, "
                    + COLUMN_BIRTH + " DATETIME, "
                    + COLUMN_CLASS + " TEXT )";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Student_db";

    private static StudentDatabaseManager sInstance;

    public static StudentDatabaseManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new StudentDatabaseManager(context);
        }
        return sInstance;
    }

    private StudentDatabaseManager(Context context) {
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

    public void insertStudent(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, student.getName());
            values.put(COLUMN_BIRTH, String.valueOf(student.getBirthDay()));
            values.put(COLUMN_CLASS, student.getClassStudent());
            db.insertOrThrow(TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            throw e;
        } finally {
            db.endTransaction();
        }
    }

    public void getStudents(Callback<List<Student>> callback) {
        SQLiteException exception = null;
        List<Student> listStudent = new ArrayList<>();
        String[] columns = {COLUMN_ID, COLUMN_NAME, COLUMN_BIRTH, COLUMN_CLASS};
        SQLiteDatabase db = (this.getWritableDatabase());
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Student student = new Student();
                    student.setId(cursor.getInt(cursor.getColumnIndex(StudentDatabaseManager.COLUMN_ID)));
                    student.setName(cursor.getString(cursor.getColumnIndex(StudentDatabaseManager.COLUMN_NAME)));
                    student.setBirthDay(cursor.getString(cursor.getColumnIndex(StudentDatabaseManager.COLUMN_BIRTH)));
                    student.setClassStudent(cursor.getString(cursor.getColumnIndex(StudentDatabaseManager.COLUMN_CLASS)));
                    listStudent.add(student);
                } while (cursor.moveToNext());
            }
        } catch (SQLiteException e) {
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
