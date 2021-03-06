package com.example.studentmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteARecordActivity extends AppCompatActivity {
    EditText deleteRoll;
    Button deleteButton;
    DataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_a_record);

        deleteButton=findViewById(R.id.deleteButton);
        deleteRoll=findViewById(R.id.deleteRoll);
        db=new DataBaseHelper(DeleteARecordActivity.this);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rollNumber=deleteRoll.getText().toString();
                if(rollNumber.equals("")) {
                    Toast.makeText(DeleteARecordActivity.this, "Enter roll no", Toast.LENGTH_SHORT).show();
                }
                else{
                    try{
                        int rollNbr=Integer.parseInt(rollNumber);
                        Cursor res=db.getAllData();     //the next few lines are to check if the roll no already exists in db or not
                        res.moveToFirst();
                        int flag=0;
                        while(res.moveToNext() && res.getCount()!=0){
                            int roll_in_db=res.getInt(1);
                            if(roll_in_db==rollNbr){
                                flag=1;
                                break;
                            }
                        }
                        db.deleteData(rollNbr);
                        Toast.makeText(DeleteARecordActivity.this,"Data Deleted Successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DeleteARecordActivity.this,MainActivity.class));
                        finish();

                    }catch (Exception e){
                        Toast.makeText(DeleteARecordActivity.this,"Roll no should be a integer",Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
    }
}