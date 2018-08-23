package com.example.lvmingzhen.intelligentparking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by lvmingzhen on 2018/5/1.
 */

public class bangzhu extends AppCompatActivity{
    TextView bangzhu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bangzhu);
        bangzhu=(TextView) findViewById(R.id.bangzhuyonghu);
        //在此有层次的罗列出对用户有帮助的信息
    }
}
