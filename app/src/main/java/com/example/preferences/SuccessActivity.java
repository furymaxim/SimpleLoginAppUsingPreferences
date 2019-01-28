package com.example.preferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class SuccessActivity extends AppCompatActivity {

    TextView welcome;
    EditText login;
    EditText password;
    SharedPreferences sharedPreferences;
    CheckBox checkBox;
    String savedUserName;
    Button logOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        welcome = (TextView)findViewById(R.id.welcome);
        logOut = (Button) findViewById(R.id.logOut);
        login = (EditText)findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        checkBox = (CheckBox) findViewById(R.id.myCheckBox);

        savedUserName = sharedPreferences.getString("user_login","");

        welcome.setText("Welcome, " + savedUserName);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("username",savedUserName);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        finish();
    }
}
