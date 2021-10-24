package com.example.rewaa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.rewaa.Sign_Up.user_email;

import static com.example.rewaa.Sign_Up.user_pass;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    Button login_btn;
    TextView signup;


    public static final String my = "my";
    public static final String login_status = "login_status";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.back));
        }

        signup = findViewById(R.id.signup);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_btn);


        sharedPreferences = getSharedPreferences(my, Context.MODE_PRIVATE);

        String s_id = sharedPreferences.getString(user_email, "");
        String s_psw = sharedPreferences.getString(user_pass, "");

        check_login();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String u_id = email.getText().toString();
                String u_pw = password.getText().toString();

                if (u_id.equals("")) {
                    email.setError("enter user id");
                } else if (u_pw.equals("")) {
                    password.setError("enter password");
                } else {

                    if (s_id.equals(email.getText().toString())) {

                        if (s_psw.equals(password.getText().toString())) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(login_status, true);
                            editor.commit();
                            Intent i = new Intent(MainActivity.this, Home.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "password not match", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "enter valid username", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Sign_Up.class);
                startActivity(i);
            }
        });
    }

    public void check_login() {
        if (sharedPreferences.getBoolean(login_status, false) == true) {
            Intent j = new Intent(MainActivity.this, Home.class);
            finish();
            startActivity(j);
        }

    }
}