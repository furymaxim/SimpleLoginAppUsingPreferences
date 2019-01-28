package com.example.preferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText login;
    EditText password;
    CheckBox checkBox;
    Button signInButton;
    String savedTextLogin;
    String savedTexPassword;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        login = (EditText)findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        checkBox = (CheckBox) findViewById(R.id.myCheckBox);
        signInButton = (Button)findViewById(R.id.signInButton);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sharedPreferencesEditor = sharedPreferences.edit();

        savedTextLogin = sharedPreferences.getString("user_login","");
        savedTexPassword = sharedPreferences.getString("user_password","");


        login.setText(savedTextLogin);
        password.setText(savedTexPassword);



        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferencesEditor.putString("user_login",login.getText().toString());
                sharedPreferencesEditor.putString("user_password",password.getText().toString());
                sharedPreferencesEditor.apply();
                Log.d("MyLog_name",sharedPreferences.getString("user_login",""));
                Log.d("MyLog_pw",sharedPreferences.getString("user_password",""));


                Log.d("MyTag",login.getText().toString());
                Log.d("MyTag",password.getText().toString());


                if(login.getText().toString().equals(getString(R.string.user_name)) && password.getText().toString().equals(getString(R.string.user_pw))){
                    startActivityForResult(new Intent(MainActivity.this, SuccessActivity.class),1);

                } else {
                    Toast.makeText(getApplicationContext(),"Wrong data(очищаю пароль)",Toast.LENGTH_SHORT).show();
                    password.setText("");
                }

            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sharedPreferencesEditor = sharedPreferences.edit();

        if(checkBox.isChecked()){
            sharedPreferencesEditor.putString("user_login",login.getText().toString());
            sharedPreferencesEditor.putString("user_password",password.getText().toString());
            sharedPreferencesEditor.apply();

        } else {
            sharedPreferencesEditor.putString("user_login","");
            sharedPreferencesEditor.putString("user_password","");
            sharedPreferencesEditor.apply();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data!=null){
            String savedUser = data.getStringExtra("username");
            Toast.makeText(getApplicationContext(),"Прощай, " + savedUser,Toast.LENGTH_SHORT).show();
            if(!checkBox.isChecked()) {password.setText("");}
        }else if(requestCode == 1 && resultCode == RESULT_OK){
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
        } else if(data == null){

        }
    }

}
