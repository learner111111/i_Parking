package com.example.lvmingzhen.intelligentparking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class denglu1 extends AppCompatActivity {

    private EditText phoneEditText;
    private EditText passEditText;
    private Button denglu;
    private Button zhuce;
    private Button wangjimima;
    private CheckBox jizhumima;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.denglu1);
        phoneEditText = (EditText) findViewById(R.id.username);
        passEditText = (EditText) findViewById(R.id.password);
        denglu=(Button) findViewById(R.id.button);
        zhuce=(Button) findViewById(R.id.button3);
        wangjimima=(Button) findViewById(R.id.button2);
        jizhumima=(CheckBox) findViewById(R.id.checkBox);
        jizhumima.setChecked(true);
        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(denglu1.this,zhuce2.class);
                startActivity(intent);
                finish();
            }
        });
        wangjimima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(denglu1.this,wangjimima3.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void checkLogin() {

        final String number = phoneEditText.getText().toString();
        if (!isValidEmail(number)) {

            phoneEditText.setError("错误的手机号");
        }

        final String pass = passEditText.getText().toString();
        if (!isValidPassword(pass)) {
            passEditText.setError("密码错误");
        }

        if(isValidEmail(number) && isValidPassword(pass))
        {
            editor=getSharedPreferences("yonghuming",MODE_PRIVATE).edit();
            if(jizhumima.isChecked())
            {
                editor.putBoolean("jizhumima",true);
                editor.putString("phoneEditText",phoneEditText.getText().toString());
                editor.putString("passEditText",passEditText.getText().toString());
            }
            else
            {
                editor.clear();
            }
            editor.apply();
            Intent intent=new Intent(denglu1.this,zhuyemian.class);
            startActivity(intent);
            finish();
        }

    }

    // validating email id
    private boolean isValidEmail(String number) {
       if(number.length()==11)//验证用户名是否存在
           return true;
        return false;
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 6 && pass.length()<=16) {
            return true;
        }
        return false;//验证密码是否为用户名的对应密码
    }
}
/**
 * Created by lvmingzhen on 2018/4/14.
 */


