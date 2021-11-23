package com.example.studentlistapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  {
    EditText nameEt;
    EditText idEt;
    CheckBox cb;
    Button saveBtn;
    Button cancelBtn;
    //List<Student> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("New Student");
        nameEt = findViewById(R.id.main_name_et);
        idEt = findViewById(R.id.main_id_et);
        cb = findViewById(R.id.main_cb);
        saveBtn = findViewById(R.id.main_save_btn);
        cancelBtn = findViewById(R.id.main_cancel_btn);

        //data = Model.instance.getAllStudents();

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    private void cancel() {
        Intent i = new Intent(getApplicationContext(), StudentListRvAcivity.class);
        startActivity(i);

    }

    private void save() {
        String name = nameEt.getText().toString();
        String id = idEt.getText().toString();
        boolean flag = cb.isChecked();
        //Log.d("TAG","saved name:" + name + " id:" + id + " flag:" + flag);
        //Student s = new Student(name, id, flag);
        Intent i = new Intent(getApplicationContext(), StudentListRvAcivity.class);
        i.putExtra("name", name);
        i.putExtra("id", id);
        i.putExtra("checkbox", flag);
        startActivity(i);

    }
}