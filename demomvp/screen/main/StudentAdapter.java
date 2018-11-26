package com.duan1.nhom4.demomvp.screen.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.duan1.nhom4.demomvp.R;
import com.duan1.nhom4.demomvp.data.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dohaiha on 09-Apr-18.
 */

public class StudentAdapter extends ArrayAdapter {

    private Context mContext;
    private int mResource;
    private ArrayList<Student> mStudents;

    public StudentAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);

        this.mContext = context;
        this.mResource = resource;
        this.mStudents = (ArrayList<Student>) objects;
    }
    public void addData(List<Student> students){
        mStudents.addAll(students);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_student, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.text_class = convertView.findViewById(R.id.text_class);
            viewHolder.text_name = convertView.findViewById(R.id.text_name);
            viewHolder.text_birth = convertView.findViewById(R.id.text_birth);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Student student = mStudents.get(position);
        viewHolder.text_class.setText(student.getClassStudent());
        viewHolder.text_name.setText(student.getName());
        viewHolder.text_birth.setText(student.getBirth() + "");

        return convertView;
    }

    class ViewHolder {
        TextView text_class;
        TextView text_name;
        TextView text_birth;
    }
}
