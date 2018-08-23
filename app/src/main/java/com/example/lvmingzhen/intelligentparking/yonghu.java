package com.example.lvmingzhen.intelligentparking;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by lvmingzhen on 2018/4/28.
 * 实际项目中很少使用android连接mySQL数据库，通常是在Android客户端通过Http将数据传送到服务端，服务端再连接mySQL数据库
 */

public class yonghu extends Fragment implements View.OnClickListener {
    private zhuyemian activity1;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.yonghu,container,false);
        Button ziliao=(Button)view.findViewById(R.id.ziliao);
        Button mimaxiugai=(Button)view.findViewById(R.id.mimaxiugai);
        Button shezhi=(Button)view.findViewById(R.id.shezhi);
        Button qianbao=(Button)view.findViewById(R.id.qianbao);
        Button kabao=(Button)view.findViewById(R.id.kabao);
        Button bangzhu=(Button)view.findViewById(R.id.bangzhu);
        Button yijianfankui=(Button)view.findViewById(R.id.yijianfankui);
        Button zhanghaoqiehuan=(Button)view.findViewById(R.id.zhanghaoqiehuan);
        Button tuichudenglu=(Button)view.findViewById(R.id.tuichudenglu);
        ziliao.setOnClickListener(this);
        mimaxiugai.setOnClickListener(this);
        shezhi.setOnClickListener(this);
        qianbao.setOnClickListener(this);
        kabao.setOnClickListener(this);
        bangzhu.setOnClickListener(this);
        yijianfankui.setOnClickListener(this);
        zhanghaoqiehuan.setOnClickListener(this);
        tuichudenglu.setOnClickListener(this);
        activity1=(zhuyemian) getActivity();
        pref=activity1.getSharedPreferences("yonghuming", Context.MODE_PRIVATE);
        return view;
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.ziliao:
                final Intent intent1=new Intent(activity1,ziliaoxianshi.class);
                startActivity(intent1);
                break;
            case R.id.mimaxiugai:
                Intent intent2=new Intent(activity1,mimagenggai.class);
                startActivity(intent2);
                break;
            case R.id.shezhi:
                Intent intent3=new Intent(activity1,shezhi.class);
                startActivity(intent3);
                break;
            case R.id.qianbao:
                Intent intent4=new Intent(activity1,qianbao.class);
                startActivity(intent4);
                break;
            case R.id.kabao:
                Intent intent5=new Intent(activity1,kabao.class);
                startActivity(intent5);
                break;
            case R.id.bangzhu:
                Intent intent6=new Intent(activity1,bangzhu.class);
                startActivity(intent6);
                break;
            case R.id.yijianfankui:
                Intent intent7=new Intent(activity1,yijianfankui.class);
                startActivity(intent7);
                break;
            case R.id.zhanghaoqiehuan:
                Intent intent8=new Intent(activity1,qiehuanzhanghao.class);
                startActivity(intent8);
                break;
            case R.id.tuichudenglu:
                AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
                dialog.setMessage("确认退出登录？");
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setCancelable(false);//可以通过返回键取消
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //退出登录,完成后直接退出程序
                        Toast.makeText(getActivity(),"您已退出登录",Toast.LENGTH_SHORT).show();
                        editor=pref.edit();
                        editor.putBoolean("jizhumima",false);
                        editor.apply();
                        activity1.finish();
                    }
                })  ;
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(),"您已取消退出登录",Toast.LENGTH_SHORT).show();
                    }
                })  ;
                dialog.show();
                break;
        }
    }
}
