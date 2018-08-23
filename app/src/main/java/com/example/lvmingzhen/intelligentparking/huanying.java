package com.example.lvmingzhen.intelligentparking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lvmingzhen on 2018/5/15.
 */

public class huanying extends AppCompatActivity {
    private SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.huanying);
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    pref=getSharedPreferences("yonghuming",MODE_PRIVATE);
                    if(pref.getBoolean("jizhumima",false)==true)
                    {
                        Thread.sleep(2000);
                        Intent intent2=new Intent(huanying.this,zhuyemian.class);
                        startActivity(intent2);
                        finish();
                    }
                    else
                    {
                        Thread.sleep(2000);
                        Intent intent1=new Intent(huanying.this,denglu1.class);
                        startActivity(intent1);
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
