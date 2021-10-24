package com.example.rewaa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Sign_Up extends AppCompatActivity {

    EditText reg_password, reg_email, contact, name;
    Button sign_btn;
    TextView signin;

    public static final String my = "my";
    public static final String user_name = "user_name";
    public static final String user_email = "user_email";
    public static final String user_pass = "user_pass";
    public static final String login_status = "login_status";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.back));
        }

        name = findViewById(R.id.name);
        reg_email = findViewById(R.id.reg_email);
        reg_password = findViewById(R.id.reg_password);
        sign_btn = findViewById(R.id.sign_btn);
        signin = findViewById(R.id.signin);
        sharedPreferences = getSharedPreferences(my, Context.MODE_PRIVATE);

        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Log.d("heretest", "name" + name.getText().toString());
                editor.putString(user_name, name.getText().toString());
                editor.putString(user_email, reg_email.getText().toString());
                editor.putString(user_pass, reg_password.getText().toString());
                editor.putBoolean(login_status, true);

                editor.commit();

                Intent i = new Intent(Sign_Up.this, Home.class);
                startActivity(i);
                finish();
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sign_Up.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}