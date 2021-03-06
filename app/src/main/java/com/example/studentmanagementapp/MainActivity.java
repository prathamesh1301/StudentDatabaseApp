package com.example.studentmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addButton,displayAllButton,editButton,deleteActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton=findViewById(R.id.addButton);
        displayAllButton=findViewById(R.id.displayAllButton);
        editButton=findViewById(R.id.editButton);
        deleteActivity=findViewById(R.id.deleteActivity);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });

        displayAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayActivity();
            }
        });

        deleteActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteRecordActivity();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,EditDetailsActivity.class));
            }
        });
    }



    public void addStudent(){
        Intent intent=new Intent(MainActivity.this,AddUserActivity.class);
        startActivity(intent);
    }

    public void displayActivity(){
        Intent intent=new Intent(MainActivity.this,DisplayActivity.class);
        startActivity(intent);
    }

    public void DeleteRecordActivity(){
        Intent intent=new Intent(MainActivity.this,DeleteARecordActivity.class);
        startActivity(intent);
    }


}