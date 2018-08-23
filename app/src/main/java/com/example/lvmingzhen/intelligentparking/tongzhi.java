package com.example.lvmingzhen.intelligentparking;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by lvmingzhen on 2018/5/3.
 */

public class tongzhi extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup group1;
    private RadioGroup group2;
    private RadioGroup group3;
    private RadioGroup group4;
    private RadioButton kai1;
    private RadioButton kai2;
    private RadioButton kai3;
    private RadioButton kai4;
    private RadioButton guan1;
    private RadioButton guan2;
    private RadioButton guan3;
    private RadioButton guan4;
    private SharedPreferences pref1;
    private SharedPreferences pref2;
    private SharedPreferences pref3;
    private SharedPreferences pref4;
    private SharedPreferences.Editor editor1;
    private SharedPreferences.Editor editor2;
    private SharedPreferences.Editor editor3;
    private SharedPreferences.Editor editor4;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tongzhi);
        group1=(RadioGroup) findViewById(R.id.radioGroup1);
        group2=(RadioGroup) findViewById(R.id.radioGroup2);
        group3=(RadioGroup) findViewById(R.id.radioGroup3);
        group4=(RadioGroup) findViewById(R.id.radioGroup4);
        kai1=(RadioButton) findViewById(R.id.kai1);
        kai2=(RadioButton) findViewById(R.id.kai2);
        kai3=(RadioButton) findViewById(R.id.kai3);
        kai4=(RadioButton) findViewById(R.id.kai4);
        guan1=(RadioButton) findViewById(R.id.guan1);
        guan2=(RadioButton) findViewById(R.id.guan2);
        guan3=(RadioButton) findViewById(R.id.guan3);
        guan4=(RadioButton) findViewById(R.id.guan4);
        pref1=getSharedPreferences("xintongzhi",MODE_PRIVATE);
        pref2=getSharedPreferences("xiangqingxinxi",MODE_PRIVATE);
        pref3=getSharedPreferences("shengyin",MODE_PRIVATE);
        pref4=getSharedPreferences("zhendong",MODE_PRIVATE);
        editor1=pref1.edit();
        editor2=pref2.edit();
        editor3=pref3.edit();
        editor4=pref4.edit();
        editor1.putBoolean("xintongZhi",true);
        editor2.putBoolean("xiangqingxinXi",true);
        editor3.putBoolean("shengYin",true);
        editor4.putBoolean("zhenDong",true);
        group1.check(kai1.getId());//初始化为全部开启
        group2.check(kai2.getId());
        group3.check(kai3.getId());
        group4.check(kai4.getId());
        group1.setOnCheckedChangeListener(this);
        group2.setOnCheckedChangeListener(this);
        group3.setOnCheckedChangeListener(this);
        group4.setOnCheckedChangeListener(this);
    }
    @Override
    public void onCheckedChanged(RadioGroup group,int checkedId)//通过判断存入键值对的值对错与否，从而得知是否开启相关内容
    {
        switch (group.getId()) {
            case R.id.radioGroup1: //可以开启通知的事件有开始计费、确认车位后、缴费提醒、缴费完成后（均是在关闭app时选择是否给出通知，若app在活动状态，则是声音震动）
                if (kai1.getId() == checkedId) {
                    editor1.putBoolean("xintongZhi",true);
                }
                if (guan1.getId() == checkedId)
                {
                    editor1.putBoolean("xintongZhi",false);
                }
                break;
            case R.id.radioGroup2: //若开启则可以在消息通知栏显示出通知的详情信息
                if (kai2.getId() == checkedId) {
                    editor2.putBoolean("xiangqingxinXi",true);
                }
                if (guan2.getId() == checkedId)
                {
                    editor2.putBoolean("xiangqingxinXi",false);
                }
                break;
            case R.id.radioGroup3: //可以有声音的事件有导航、开始计费、确认车位后、缴费提醒、缴费完成后（均是在app在活动状态时选择是否有声音，若不在活动状态，则是通知）
                if (kai3.getId() == checkedId) {
                    editor3.putBoolean("shengYin",true);
                }
                if (guan3.getId() == checkedId)
                {
                    editor3.putBoolean("shengYin",false);
                }
                break;
            case R.id.radioGroup4: //可以有震动的事件有开始计费、确认车位后、缴费提醒、缴费完成后（均是在app在活动状态时选择是否有震动，若不在活动状态，则是通知）
                if (kai4.getId() == checkedId) {
                    editor4.putBoolean("zhenDong",true);
                }
                if (guan4.getId() == checkedId)
                {
                    editor4.putBoolean("zhenDong",false);
                }
                break;
            default:
                editor1.putBoolean("xintongZhi",true);
                editor2.putBoolean("xiangqingxinXi",true);
                editor3.putBoolean("shengYin",true);
                editor4.putBoolean("zhenDong",true);
                break;
        }
    }
}
