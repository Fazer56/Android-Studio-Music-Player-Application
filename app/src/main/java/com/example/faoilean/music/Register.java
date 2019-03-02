package com.example.faoilean.music;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


/*
Music app
Student: Faoilean Fortune
Student Number: C15380201
Mobile Software Development*/
public class Register extends AppCompatActivity {

    EditText username;
    EditText firstname;
    EditText lastname;
    EditText password;
    EditText confPword;
    DBHelper myDBhelper;
    Button regbtn;
    Button delbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent activityThatCalled = getIntent();

        username = (EditText) findViewById(R.id.username);
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.surname);
        password = (EditText) findViewById(R.id.password);
        confPword = (EditText) findViewById(R.id.confpassword);
        myDBhelper = new DBHelper(this, null, null, 1);
        regbtn = (Button) findViewById(R.id.submit);
        delbtn = (Button) findViewById(R.id.delete);
        //printUserName();
        regbtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                registerButtonClicked();
            }
        });

        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteButtonClicked();
            }
        });


    }

    //register the user
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void registerButtonClicked() {


        if(!Objects.equals(password.getText().toString(), confPword.getText().toString())) {

            Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_SHORT).show();

        }
        else{
            User user = new User(username.getText().toString(), password.getText().toString(), firstname.getText().toString(), lastname.getText().toString());
            myDBhelper.addUser(user);
            Toast.makeText(this, "Success! Welcome " +username.getText().toString(), Toast.LENGTH_LONG).show();
            finish();
        }
    }
    //delete account.
    public void deleteButtonClicked() {
        myDBhelper.deleteUser(username.getText().toString());
        Toast.makeText(this, "Account Deleted " +username.getText().toString(), Toast.LENGTH_LONG).show();
        finish();
    }
}
