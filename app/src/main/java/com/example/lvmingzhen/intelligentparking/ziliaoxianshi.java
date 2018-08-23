package com.example.lvmingzhen.intelligentparking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by lvmingzhen on 2018/5/10.
 */

public class ziliaoxianshi extends AppCompatActivity {
    private Button xiugaixinxi;
    private TextView shoujihao;
    private SharedPreferences pref;
    private TextView nicheng;
    private TextView shengfen;
    private TextView chengshi;
    private TextView chehao;
    private TextView xingming;
    private TextView xingbie;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ziliaoxianshi);
        xiugaixinxi=(Button) findViewById(R.id.xiugaixinxi);
        shoujihao=(TextView) findViewById(R.id.shoujihao3);
        nicheng=(TextView) findViewById(R.id.nicheng3);
        shengfen=(TextView) findViewById(R.id.shengfen1);
        chengshi=(TextView) findViewById(R.id.chengshi1);
        chehao=(TextView) findViewById(R.id.chehao1);
        xingming=(TextView) findViewById(R.id.xingming3);
        xingbie=(TextView) findViewById(R.id.xingbie3);
        editor=getSharedPreferences("names",MODE_PRIVATE).edit();
        editor.putBoolean("xingming",true);
        editor.putString("name",xingming.getText().toString());
        pref=getSharedPreferences("yonghuming",MODE_PRIVATE);
        String phone=pref.getString("phoneEditText","");
        shoujihao.setText(phone);
        xiugaixinxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ziliaoxianshi.this,ziliao.class);
                startActivityForResult(intent,1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        switch (requestCode)
        {
            case 1:
                if(resultCode==RESULT_OK)
                {
                    String nichengx=data.getStringExtra("nicheng");
                    String shengfenx=data.getStringExtra("sheng");
                    String chengshix=data.getStringExtra("cheng");
                    String chehaox=data.getStringExtra("chehao");
                    String xingmingx=data.getStringExtra("xingming");
                    String xingbiex=data.getStringExtra("xingbie");
                    nicheng.setText(nichengx);
                    shengfen.setText(shengfenx);
                    chengshi.setText(chengshix);
                    chehao.setText(chehaox);
                    xingming.setText(xingmingx);
                    xingbie.setText(xingbiex);
                    break;
                }
            default:
        }
    }
}