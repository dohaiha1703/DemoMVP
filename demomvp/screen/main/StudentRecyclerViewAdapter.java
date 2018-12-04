package com.duan1.nhom4.demomvp.screen.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.duan1.nhom4.demomvp.R;
import com.duan1.nhom4.demomvp.data.model.Student;

import java.util.List;

public class StudentRecyclerViewAdapter extends
        RecyclerView.Adapter<StudentRecyclerViewAdapter.ViewHolder> {

    private List<Student> mStudents;

    public StudentRecyclerViewAdapter(List<Student> students) {
        mStudents = students;
    }

    public void addData(List<Student> students) {
        mStudents.addAll(students);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_student, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Student student = mStudents.get(i);
        viewHolder.text_name.setText(student.getName());
        viewHolder.text_birth.setText(student.getBirthDay());
        viewHolder.text_class.setText(student.getClassStudent());
    }

    @Override
    public int getItemCount() {
        return mStudents.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text_name;
        public TextView text_class;
        public TextView text_birth;

        public ViewHolder(View itemView) {
            super(itemView);
            text_class = itemView.findViewById(R.id.text_class);
            text_birth = itemView.findViewById(R.id.text_birth);
            text_name = itemView.findViewById(R.id.text_name);
        }
    }
}
