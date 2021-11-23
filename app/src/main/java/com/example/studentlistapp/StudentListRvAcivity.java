package com.example.studentlistapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.studentlistapp.model.Model;
import com.example.studentlistapp.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class StudentListRvAcivity extends AppCompatActivity {

    List<Student> data;
    FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list_rv_acivity);
        setTitle("Student List");
        add = findViewById(R.id.studentlist_rv_add);
        data = Model.instance.getAllStudents();

        RecyclerView list = findViewById(R.id.studentlist_rv);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(this));

        MyAdapter adapter = new MyAdapter();
        list.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                //Log.d("TAG","row was clicked " + position);
                displayForm(position);

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        Bundle incomingMessages = getIntent().getExtras();
        if(incomingMessages != null){

            String name = incomingMessages.getString("name");
            String id = incomingMessages.getString("id");
            boolean flag = incomingMessages.getBoolean("checkbox");
            Student s = new Student(name, id, flag);
            Model.instance.addStudent(s);

        }



    }

    private void displayForm(int position) {

        Intent i = new Intent(getApplicationContext(), DisplayActivity.class);
        Student s = Model.instance.getAllStudents().get(position);

        i.putExtra("position", position);
        i.putExtra("name", s.getName());
        i.putExtra("id", s.getId());
        i.putExtra("checkbox", s.isFlag());
        startActivity(i);



    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv;
        TextView idTv;
        CheckBox cb;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.listrow_name_tv);
            idTv = itemView.findViewById(R.id.listrow_id_tv);
            cb = itemView.findViewById(R.id.listrow_cb);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    listener.onItemClick(pos);
                }
            });
        }
    }

    interface OnItemClickListener{
        void onItemClick(int position);
    }
    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        OnItemClickListener listener;
        public void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_list_row,parent,false);
            MyViewHolder holder = new MyViewHolder(view,listener);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Student student = data.get(position);
            holder.nameTv.setText(student.getName());
            holder.idTv.setText(student.getId());
            holder.cb.setChecked(student.isFlag());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}