package com.example.studentlistapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentlistapp.model.Model;
import com.example.studentlistapp.model.Student;


public class EditActivity extends AppCompatActivity {

    EditText etname;
    EditText etid;
    CheckBox cb;
    Button save;
    Button cancel;
    Button delete;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setTitle("Edit Student");
        etname = findViewById(R.id.edit_name_et);
        etid = findViewById(R.id.edit_id_et);
        cb = findViewById(R.id.edit_cb);
        save = findViewById(R.id.edit_save_btn);
        cancel = findViewById(R.id.edit_cancel_btn);
        delete = findViewById(R.id.edit_delete_btn);

        Bundle incomingMessages = getIntent().getExtras();
        if(incomingMessages != null) {
            String name = incomingMessages.getString("name");
            String id = incomingMessages.getString("id");
            boolean flag = incomingMessages.getBoolean("checkbox");
            position = incomingMessages.getInt("position", position);

            etname.setText(name);
            etid.setText(id);
            cb.setChecked(flag);


        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });

    }

    private void save() {

        Intent i = new Intent(getApplicationContext(), StudentListRvAcivity.class);
        Model.instance.getAllStudents().remove(position);
        String name = etname.getText().toString();
        String id = etid.getText().toString();
        boolean flag = cb.isChecked();
        Student s = new Student(name , id , flag);

        Model.instance.getAllStudents().add(s);
        startActivity(i);
    }

    private void delete() {
        Intent i = new Intent(getApplicationContext(), StudentListRvAcivity.class);
        Model.instance.getAllStudents().remove(position);
        startActivity(i);
    }

    private void cancel() {
        Intent i = new Intent(getApplicationContext(), StudentListRvAcivity.class);
        startActivity(i);

    }
}