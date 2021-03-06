package com.example.studentmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUserActivity extends AppCompatActivity {
    EditText studentName,studentRoll,studentDob;
    Button saveButton;
    DataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        studentName=findViewById(R.id.studentName);
        studentDob=findViewById(R.id.studentDob);
        studentRoll=findViewById(R.id.studentRoll);
        saveButton=findViewById(R.id.saveButton);
        db=new DataBaseHelper(AddUserActivity.this);

        saveButtonClicked();


    }

    public void saveButtonClicked(){
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=studentName.getText().toString();
                String dob=studentDob.getText().toString();
                String roll=studentRoll.getText().toString();
                if(name.equals("") || dob.equals("")|| roll.equals("")){
                    Toast.makeText(AddUserActivity.this, "Enter data in fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    try{

                    int rollno = Integer.parseInt(roll);
                    Cursor res = db.getAllData();     //the next few lines are to check if the roll no already exists in db or not
                    res.moveToFirst();
                    int flag = 0;
                    while (res.moveToNext() && res.getCount() != 0) {
                        int roll_in_db = res.getInt(1);
                        if (roll_in_db == rollno) {
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {    //flag=0 means roll no not present in db and we can add this data in db
                        boolean status = db.addStudentDetail(name, rollno, dob);  //adds data to db, it gives a boolean value if true then successfully added else failed
                        if (status) {
                            Toast.makeText(AddUserActivity.this, "Student details pushed to database", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddUserActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(AddUserActivity.this, "Had a problem while uploading", Toast.LENGTH_SHORT).show();
                        }
                    } else {  //flag=1 means roll no exists and we make a toast to show it to user
                        Toast.makeText(AddUserActivity.this, "Roll no already exists.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }catch (Exception e){
                        Toast.makeText(AddUserActivity.this, "Roll no should be integer.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}