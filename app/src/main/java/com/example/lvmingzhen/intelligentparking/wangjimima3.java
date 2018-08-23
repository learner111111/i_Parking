package com.example.lvmingzhen.intelligentparking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lvmingzhen on 2018/4/24.
 */

public class wangjimima3 extends AppCompatActivity {
    private Button zhuce;
    private Button fasong2;
    private EditText phoneEditText;
    private EditText passEditText1;
    private EditText passEditText2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wangjimima3);
        zhuce=(Button) findViewById(R.id.zhaohui);
        fasong2=(Button) findViewById(R.id.fasong2);
        phoneEditText = (EditText) findViewById(R.id.editTextP);
        passEditText1 = (EditText) findViewById(R.id.editTextM);
        passEditText2 = (EditText) findViewById(R.id.editTextY);
        fasong2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String number = phoneEditText.getText().toString();
                if(isValidEmail(number))
                {
                    //给此手机号发送验证码
                }
                else
                {
                    phoneEditText.setError("错误的手机号");
                }
            }
        });
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }
    public void checkLogin() {

        final String pass = passEditText1.getText().toString();
        if (!isValidPassword1(pass)) {
            passEditText1.setError("车牌号错误");
        }
        final String pass2=passEditText2.getText().toString();
        if(!isValidPassword2(pass2))
        {
            passEditText2.setError("验证码错误");
        }
        if( isValidPassword1(pass)&&isValidPassword2(pass2))
        {
            Toast.makeText(wangjimima3.this,"找回成功",Toast.LENGTH_SHORT).show();
            //将之前存储的密码发送至对应的用户手机上面
            TimerTask task=new TimerTask() {
                @Override
                public void run() {
                    Intent intent=new Intent(wangjimima3.this,denglu1.class);
                    startActivity(intent);
                }
            };
            Timer timer=new Timer();
            timer.schedule(task,1000);
            finish();
        }
        }
    private boolean isValidEmail(String number) {
        if(number.length()==11)//判断手机号是否已被注册
            return true;
        return false;
    }

    // validating password
    private boolean isValidPassword1(String pass) {
        if (pass.length() ==5) {
            return true;
        }
        return false;
    }
    private boolean isValidPassword2(String pass2) {
        if (pass2 != null ) {
            return true;
        }
        return false;//判断验证码是否与生成的对应验证码相同
    }
    }
