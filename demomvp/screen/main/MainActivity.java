package com.duan1.nhom4.demomvp.screen.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.duan1.nhom4.demomvp.R;
import com.duan1.nhom4.demomvp.data.model.Student;
import com.duan1.nhom4.demomvp.data.repository.StudentRepository;
import com.duan1.nhom4.demomvp.data.source.DbStudentManager;
import com.duan1.nhom4.demomvp.data.source.local.DbLocalDataSource;
import com.duan1.nhom4.demomvp.data.source.remote.DbRemoteDatasource;
import com.duan1.nhom4.demomvp.screen.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MainContract.View {

    private static final String TAG = "MainActivity";
    private StudentRecyclerViewAdapter mAdapter;
    private List<Student> mStudents;
    private MainPresenter mPresenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void initComponent() {
        RecyclerView lvStudent = findViewById(R.id.list_student);
        mStudents = new ArrayList<>();
        mAdapter = new StudentRecyclerViewAdapter(mStudents);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        lvStudent.setLayoutManager(mLayoutManager);
        lvStudent.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter = new MainPresenter(new StudentRepository(
                new DbLocalDataSource(new DbStudentManager(this)),
                new DbRemoteDatasource()));
        mPresenter.setView(this);
        fakeData();
        mPresenter.getData();
    }

    public void onGetStudentSuccess(List<Student> students) {
        mAdapter.addData(students);
    }

    @Override
    public void onGetDataFailed(Exception e) {
        Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    private void fakeData() {
        for (int i = 0; i < 5; i++) {
            mPresenter.addStudent("Student " + i, "3/4/1922", "PT " + i);
        }
    }
}
