package com.example.lvmingzhen.intelligentparking;

import android.content.Intent;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lvmingzhen on 2018/4/22.
 */

public class zhuce2 extends AppCompatActivity {
    private Button zhuce;
    private Button fasong;
    private EditText phoneEditText;
    private EditText passEditText1;
    private EditText passEditText2;
    private Spinner shengfen;
    private Spinner chengshi;
    private EditText chehao;
    private String sheng;
    private String cheng;
    private String sex;
    private String[] mItem;
    private String[] mItem2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce2);
        zhuce=(Button) findViewById(R.id.zhuce);
        fasong=(Button) findViewById(R.id.fasong) ;
        phoneEditText = (EditText) findViewById(R.id.editText1);
        passEditText1 = (EditText) findViewById(R.id.editText2);
        passEditText2 = (EditText) findViewById(R.id.editText3);
        shengfen=(Spinner)findViewById(R.id.shengfen2);//存
        chengshi=(Spinner)findViewById(R.id.chengshi2);//存
        chehao=(EditText)findViewById(R.id.chehao2);//存
        fasong.setOnClickListener(new View.OnClickListener() {
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
        mItem = getResources().getStringArray(R.array.shengfen);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shengfen.setAdapter(adapter);
        shengfen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] sheng_fen=getResources().getStringArray(R.array.shengfen);
                sheng=sheng_fen[position];
                //Toast.makeText(ziliao.this,"您选择了"+sheng_fen[position]+"省",Toast.LENGTH_SHORT).show();
                //  pos为位置
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
         mItem2 = getResources().getStringArray(R.array.chengshi);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mItem2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chengshi.setAdapter(adapter2);
        chengshi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] cheng_shi=getResources().getStringArray(R.array.chengshi);
                cheng=cheng_shi[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
            passEditText1.setError("密码格式错误");
        }
        final String chehao1 = chehao.getText().toString();
        if (!isValidchehao(chehao1)) {
            chehao.setError("长度须为5！");
        }
        final String pass2=passEditText2.getText().toString();
        if(!isValidPassword2(pass2))
        {
            passEditText2.setError("验证码错误");
        }
        if( isValidPassword1(pass)&&isValidPassword2(pass2)&&isValidchehao(chehao1))
        {
            Toast.makeText(zhuce2.this,"注册成功！",Toast.LENGTH_SHORT).show();
            //将新的用户名和密码加入数据库内
            TimerTask task=new TimerTask() {
                @Override
                public void run() {
                    Intent intent=new Intent(zhuce2.this,denglu1.class);
                    startActivity(intent);
                }
            };
            Timer timer=new Timer();
            timer.schedule(task,1000);
            finish();
        }

    }
    private boolean isValidEmail(String number) {
        if(number.length()==11)//判断手机号是否存在及是否已被注册
            return true;
        return false;
    }

    // validating password
    private boolean isValidPassword1(String pass) {
        if (pass != null && pass.length() >= 6 && pass.length()<=16) {
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
    private boolean isValidchehao(String chehao) {
        if(chehao.length()==5)
            return true;
        return false;
    }
}
