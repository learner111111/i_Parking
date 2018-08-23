package com.example.lvmingzhen.intelligentparking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lvmingzhen on 2018/5/16.
 */

public class yinhangkaAdd2 extends AppCompatActivity {
    private TextView kaleixing;
    private EditText yanzhengma;
    private Button fasong;
    private Button tijiao;
    private String kahaox;
    private Intent intent;
    private boolean OK;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yinhangkaadd2);
        kaleixing=(TextView) findViewById(R.id.kaleixing2);//将卡号匹配到的银行卡类型显示出来
        yanzhengma=(EditText) findViewById(R.id.yanzhengma);
        fasong=(Button) findViewById(R.id.yanzhengma2);
        tijiao=(Button) findViewById(R.id.xiayibu2);
        editor=getSharedPreferences("Ok",MODE_PRIVATE).edit();
        OK=false;
        intent=getIntent();
        String kaleixingx=intent.getStringExtra("kaleixing1");
        kaleixing.setText(kaleixingx);
        fasong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 final String phonex=intent.getStringExtra("phone");
                //给上一步正确输入的手机号发送验证短信
            }
        });
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
    }
    private void check()
    {
        final String yanzhengmax=yanzhengma.getText().toString();
        if(yanzhengmax.length()!=0)//将所有关于银行卡的信息(卡号、卡类型、预留电话)存入数据库中
        {
            String kahao2=intent.getStringExtra("kahao");
            //将所有关于银行卡的信息(卡号、卡类型、预留电话)存入数据库中
            OK=true;
            Boolean ok=OK;
            editor.putBoolean("oK",ok);
            editor.apply();
            OK=false;
            Toast.makeText(yinhangkaAdd2.this,"添加成功！",Toast.LENGTH_SHORT).show();
            finish();
        }
        if(yanzhengmax.length()==0)//判断验证码是正确
        {

            yanzhengma.setError("验证码错误!");
        }
    }
}
