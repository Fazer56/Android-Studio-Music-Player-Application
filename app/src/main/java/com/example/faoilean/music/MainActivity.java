package com.example.faoilean.music;

/*
Music app
Student: Faoilean Fortune
Student Number: C15380201
Mobile Software Development*/

import android.annotation.TargetApi;
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

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private TextView txt;
    private EditText edt1;
    private EditText edt2;
    private String[] choices;
    private DBHelper mydbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.tex);
        button1 = (Button) findViewById(R.id.btn1);
        button2 = (Button) findViewById(R.id.btn2);
        edt1 = (EditText) findViewById(R.id.editText);
        edt2 = (EditText) findViewById(R.id.editText2);
        edt1.setText("");
        edt2.setText("");

        mydbHelper = new DBHelper(this, null, null, 1);
        //mydbHelper.addArtist();
        //int id = mydbHelper.getId("kwesi123");

        //txt.setText(String.valueOf(id));

        button1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                onLogInClick(v);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegisterClick(v);
            }
        });

        // mydbHelper = new DBHelper(this, null, null, 1);

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onLogInClick(View view){


        String userName = edt1.getText().toString();
        String passWord = edt2.getText().toString();
        //log user in.
        String passwordChk = mydbHelper.pWordSearch(userName);
       if(!Objects.equals(passwordChk, "Empty")){
           if (!Objects.equals(passWord, passwordChk)) {
               Toast.makeText(this, "Username or Password Incorect!", Toast.LENGTH_LONG).show();
               edt1.setText("");
               edt2.setText("");
           }
           else if (Objects.equals(edt1.getText().toString(), "") || Objects.equals(edt1.getText().toString(), "")) {
               Toast.makeText(this, "Please enter Username and Password!", Toast.LENGTH_LONG).show();
               edt1.setText("");
               edt2.setText("");
           }
           else {
               Intent getLoginScreenIntent = new Intent(this, SongListed.class);
               final int result = 1;

               getLoginScreenIntent.putExtra("Username", userName);
               Toast.makeText(this, "Welcome " + userName, Toast.LENGTH_LONG).show();
               startActivity(getLoginScreenIntent);
               edt1.setText("");
               edt2.setText("");
           }
       }
       else{
           Toast.makeText(this, "Please Register before logging in!", Toast.LENGTH_LONG).show();
       }
    }

    public void onRegisterClick(View view) {

        Intent getRegisterScreenIntent = new Intent(this, Register.class);

        startActivityForResult(getRegisterScreenIntent, 2);

    }
}
