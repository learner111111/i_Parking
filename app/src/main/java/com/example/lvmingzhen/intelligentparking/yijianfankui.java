package com.example.lvmingzhen.intelligentparking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by lvmingzhen on 2018/5/1.
 */

public class yijianfankui extends AppCompatActivity {
    private Button tijiao;
    private EditText fankui;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yijianfankui);
        fankui=(EditText) findViewById(R.id.fankui2);        //在此用户会提出意见
        tijiao=(Button) findViewById(R.id.tijiaofankui);
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将用户的反馈意见反馈到后端
            }
        });
    }
}
