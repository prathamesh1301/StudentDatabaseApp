package com.example.studentmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDetailsActivity extends AppCompatActivity {
    EditText nameEdit,rollEdit,dobEdit;
    Button updateButton;
    DataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);

        nameEdit=findViewById(R.id.studentNameEdit);
        rollEdit=findViewById(R.id.studentRollEdit);
        dobEdit=findViewById(R.id.studentDobEdit);
        updateButton=findViewById(R.id.updateButton);
        db=new DataBaseHelper(EditDetailsActivity.this);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=nameEdit.getText().toString();
                String Dob=dobEdit.getText().toString();
                String Roll=rollEdit.getText().toString();
                if(Name.equals("") || Dob.equals("") || Roll.equals("")){
                    Toast.makeText(EditDetailsActivity.this,"Enter data in the fields",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    try{
                        int rollNumbr=Integer.parseInt(Roll);
                        Cursor res=db.getAllData();     //the next few lines are to check if the roll no already exists in db or not
                        res.moveToFirst();
                        int flag=0;
                        while(res.moveToNext() && res.getCount()!=0){
                            int roll_in_db=res.getInt(1);
                            if(roll_in_db==rollNumbr){
                                flag=1;
                                break;
                            }
                        }
                        if(flag==1){
                            db.updateData(rollNumbr,Name,Dob);
                            Toast.makeText(EditDetailsActivity.this,"Data Updated",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditDetailsActivity.this,MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(EditDetailsActivity.this,"Roll no not found in record",Toast.LENGTH_SHORT).show();
                        }

                    }catch (Exception e){
                        Toast.makeText(EditDetailsActivity.this,"Roll no should be integer",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}