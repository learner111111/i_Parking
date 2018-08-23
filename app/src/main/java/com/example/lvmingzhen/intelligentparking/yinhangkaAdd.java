package com.example.lvmingzhen.intelligentparking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by lvmingzhen on 2018/5/16.
 */

public class yinhangkaAdd extends AppCompatActivity {
    private TextView chikaren;
    private EditText kahao;
    private EditText shoujihao;
    private Button xiayibu;
    private SharedPreferences pref;
    private String kaleixing1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yinhangkaadd);
        chikaren=(TextView) findViewById(R.id.chikaren2);
        kahao=(EditText) findViewById(R.id.kahao2);
        shoujihao=(EditText) findViewById(R.id.shoujihao5);
        xiayibu=(Button) findViewById(R.id.xiayibu);
        pref=getSharedPreferences("names",MODE_PRIVATE);
        String name=pref.getString("name","");
        chikaren.setText(name);
        xiayibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
        kaleixing1="";//将正确卡号的卡类型从后台得出放入此字符串内。
    }
    private void check()
    {
        final String kahaox=kahao.getText().toString();
        if(!isValidkahao(kahaox))
            kahao.setError("错误的或已被绑定或不支持的卡号！");
        final String phone=shoujihao.getText().toString();
        if(!isValidPhone(phone))
            shoujihao.setError("错误的或不匹配的手机号！");
        if(isValidkahao(kahaox)&&isValidPhone(phone))
        {
            Intent intent1=new Intent();
            intent1.putExtra("kahao1",kahaox);
            intent1.putExtra("kaleixing",kaleixing1);
            setResult(RESULT_OK,intent1);
            Intent intent2=new Intent(yinhangkaAdd.this,yinhangkaAdd2.class);
            intent2.putExtra("phone",phone);
            intent2.putExtra("kahao",kahaox);
            intent2.putExtra("kaleixing1",kaleixing1);
            startActivity(intent2);
            finish();
        }
    }
    private boolean isValidkahao(String number) {
        if(number.length()==19)//验证此卡号是否存在
            return true;
        return false;
    }

    private boolean isValidPhone(String number) {
        if(number.length()==11)//验证手机号是否匹配
            return true;
        return false;
    }
}
