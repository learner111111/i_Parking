package com.example.lvmingzhen.intelligentparking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by lvmingzhen on 2018/5/1.
 */

public class mimagenggai extends AppCompatActivity {
    Button xiugai;
    EditText yuanmima;
    EditText xinmima;
    EditText xinmima2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mimagenggai);
        xiugai=(Button)findViewById(R.id.xiugai);
        yuanmima=(EditText) findViewById(R.id.yuanmima2);
        xinmima=(EditText) findViewById(R.id.xinmima2);
        xinmima2=(EditText)findViewById(R.id.xinmima4);
        xiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check2();
            }
        });
    }
    public void check2() {
        final String yuanmima2 = yuanmima.getText().toString();
        if (!isValidyuanmima(yuanmima2)) {
            yuanmima.setError("输入错误！");
        }
        final String xinmima3= xinmima.getText().toString();
        if (!isValidxinmima(xinmima3)) {
            xinmima.setError("长度须在6—16位之间！");
        }
        final String xinmima4= xinmima2.getText().toString();
        if (!isValidxinmima2(xinmima4)) {
            xinmima2.setError("输入不一致！");
        }
        if(isValidyuanmima(yuanmima2)&&isValidxinmima(xinmima3)&&isValidxinmima2(xinmima4))
        {
            //在数据库中更新密码信息
            Toast.makeText(mimagenggai.this,"修改密码成功！",Toast.LENGTH_SHORT).show();
        }
    }
    private boolean isValidyuanmima(String yuanmima) {
        if(yuanmima==yuanmima)//判断是否与原密码一致
            return true;
        return false;
    }
    private boolean isValidxinmima(String xinmima) {
        if(xinmima.length()>=6&&xinmima.length()<=16)//判断格式是否正确
            return true;
        return false;
    }
    private boolean isValidxinmima2(String xinmima2) {
        if(xinmima2==xinmima.getText().toString())//判断是否一致
            return true;
        return false;
    }
}
