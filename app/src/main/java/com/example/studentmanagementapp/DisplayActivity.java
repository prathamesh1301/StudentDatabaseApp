package com.example.studentmanagementapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button backButton;
    DataBaseHelper db;
    ArrayList<String> nameA,dobA;
    ArrayList<Integer> rollA;
    RecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        recyclerView=findViewById(R.id.recyclerView);
        backButton=findViewById(R.id.backButton);
        nameA=new ArrayList<>();
        rollA=new ArrayList<>();
        dobA=new ArrayList<>();
        db=new DataBaseHelper(DisplayActivity.this);

        storeintoArray();

        adapter=new RecyclerAdapter(DisplayActivity.this,nameA,dobA,rollA);
        recyclerView.setLayoutManager(new LinearLayoutManager(DisplayActivity.this));
        recyclerView.scrollToPosition(nameA.size());
        recyclerView.setAdapter(adapter);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DisplayActivity.this,MainActivity.class));
                finish();
            }
        });


    }

    public void storeintoArray(){
        Cursor res=db.getAllData();
        if(res.getCount()==0){
            return;
        }else{
            res.moveToFirst();
            while (res.moveToNext()){
                nameA.add(res.getString(0));
                rollA.add(res.getInt(1));
                dobA.add(res.getString(2));
            }

        }
    }
}