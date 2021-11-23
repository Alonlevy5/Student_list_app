package com.example.studentlistapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    TextView nametv;
    TextView idtv;
    CheckBox cb;
    Button editbtn;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        setTitle("Student Details");
        nametv = findViewById(R.id.display_tv_name);
        idtv = findViewById(R.id.display_tv_id);
        cb = findViewById(R.id.edit_cb);
        editbtn = findViewById(R.id.display_editbtn);

        Bundle incomingMessages = getIntent().getExtras();
        if(incomingMessages != null) {
            String name = incomingMessages.getString("name");
            String id = incomingMessages.getString("id");
            boolean flag = incomingMessages.getBoolean("checkbox");
            position = incomingMessages.getInt("position");

            nametv.setText(name);
            idtv.setText(id);
            cb.setChecked(flag);


        }

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editusr();
            }
        });

    }

    private void editusr() {

        Intent i = new Intent(getApplicationContext(), EditActivity.class);
        boolean flag = cb.isChecked();
        String name = nametv.getText().toString();
        String id = idtv.getText().toString();

        i.putExtra("position", position);
        i.putExtra("name", name);
        i.putExtra("id", id);
        i.putExtra("checkbox", flag);
        startActivity(i);


    }
}