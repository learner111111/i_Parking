package com.example.lvmingzhen.intelligentparking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by lvmingzhen on 2018/5/22.
 */
/*
   //实际在模拟器运行时金额不发生改变。。。出问题了    应该使用活动信息回传作为标志而不能用键值对进行存储进行提取回传
 */
public class zhuanzhang extends AppCompatActivity {
    private EditText jine;
    private EditText mima;
    private Button queren;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState)//将要打开的卡的信息传入这里，作为此页面的数据
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuanzhang);
        jine=(EditText) findViewById(R.id.jine1);
        mima=(EditText) findViewById(R.id.yinhangka_mima1);
        queren=(Button) findViewById(R.id.queren1);
        pref=getSharedPreferences("yuE",MODE_PRIVATE);
        editor=pref.edit();
        queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
    }
    private void check()
    {
       if(!istrue1())
       {
           jine.setError("输入格式不正确");
       }
        if(!istrue3())
        {
            jine.setError("银行卡余额不足");
        }
        if(!istrue2())
        {
            mima.setError("密码错误");
        }
        if(istrue1()&&istrue2()&&istrue3())
        {
            //按照输入的金额将银行卡内相应的钱转入余额，并给出相应的通知
            float jinef1=pref.getFloat("Yue",0);
            float jinef2=Float.parseFloat(jine.getText().toString());
            editor.putFloat("Yue",jinef1+jinef2);
            editor.putBoolean("panduan2",true);
            editor.apply();//实际在模拟器运行时金额不发生改变。。。出问题了    应该使用活动信息回传作为标志而不能用键值对进行存储进行提取回传

            Toast.makeText(zhuanzhang.this,"转入余额成功！",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    private Boolean istrue1()
    {
        String jinex=jine.getText().toString();
        float jinei=Float.parseFloat(jinex);
        if(jinei<=0.0)//判断输入金额是否正确，是否超出银行卡余额
        {
            return false;
        }
        return true;
    }
    private Boolean istrue3()
    {
        String jinex=jine.getText().toString();
        float jinei=Float.parseFloat(jinex);
        if(jinei>10000.0)//判断输入金额是否正确，是否超出银行卡余额
        {
            return false;
        }
        return true;
    }
    private Boolean istrue2()
    {
        String mimax=mima.getText().toString();
        if(mimax.length()!=6)//判断密码是否正确
        {
            return false;
        }
        return true;
    }
}
