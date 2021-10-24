package com.example.rewaa;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;
import static com.example.rewaa.Sign_Up.user_email;
import static com.example.rewaa.Sign_Up.user_name;

public class Home extends AppCompatActivity implements Drawer_Adapter.selectdraweritem, LogoutTimer.LogOutListener {
    Drawer_Adapter drawer_adapter;
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    TextView lg_name, my_name, logout;
    ArrayList<String> menu_title_array = new ArrayList<>();
    ArrayList<Integer> menu_title_icon = new ArrayList<Integer>();
    public static final String my = "my";
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawablelayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.back));
        }

        sharedPreferences = getSharedPreferences(my, Context.MODE_PRIVATE);

        recyclerView = findViewById(R.id.recycler_drawer);
        drawerLayout = findViewById(R.id.drawer_layout);
        lg_name = findViewById(R.id.lg_name);
        my_name = findViewById(R.id.my_name);
        logout = findViewById(R.id.logout);

        lg_name.setText(sharedPreferences.getString(user_name, ""));
        my_name.setText(sharedPreferences.getString(user_name, ""));

        menu_title_icon.add(R.drawable.ic_iconapp);
        menu_title_array.add("data table");

        drawer_adapter = new Drawer_Adapter(menu_title_array, menu_title_icon, Home.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(drawer_adapter);

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(Home.this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("login_status");
                editor.commit();

                Intent i = new Intent(Home.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogoutTimer.startLogoutTimer(this, this);
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        LogoutTimer.startLogoutTimer(this, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void openselecteddraweritem(int pos) {
        switch (pos) {
            case 0:
                drawerLayout.close();
                Intent i = new Intent(Home.this, DataTable.class);
                startActivity(i);
                break;

            default:
                drawerLayout.close();
                Toast.makeText(this, "Drawer close", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void doLogout() {

        showToast(getString(R.string.MyMessage));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("login_status");
        editor.commit();

        Intent i = new Intent(Home.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();

    }
    public void showToast(final String toast)
    {
        runOnUiThread(() -> Toast.makeText(Home.this, toast, Toast.LENGTH_SHORT).show());
    }
}