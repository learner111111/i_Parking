package com.example.lvmingzhen.intelligentparking;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lvmingzhen on 2018/5/1.
 */

public class shezhi extends AppCompatActivity implements View.OnClickListener {
    private Button tongzhi;
    private Button qingli;
    private Button shanchu;
    private ProgressDialog dialog1;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shezhi);
        tongzhi=(Button)findViewById(R.id.tongzhi);
        qingli=(Button)findViewById(R.id.qingli);
        shanchu=(Button)findViewById(R.id.shanchu);
        pref=getSharedPreferences("yonghuming",MODE_PRIVATE);
        tongzhi.setOnClickListener(this);
        qingli.setOnClickListener(this);
        shanchu.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.tongzhi:
                Intent intent1=new Intent(shezhi.this,tongzhi.class);
                startActivity(intent1);
                break;
            case R.id.qingli:
                //开始清理缓存
                dialog1=new ProgressDialog(shezhi.this);
                dialog1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog1.setTitle("缓存清理");
                dialog1.setMessage("正在清理缓存");
                dialog1.setCanceledOnTouchOutside(false);
                dialog1.setIcon(R.mipmap.ic_launcher);
                dialog1.setCancelable(true);//可以通过返回键取消
                dialog1.setOnDismissListener(new DialogInterface.OnDismissListener() {

                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Toast.makeText(shezhi.this,"清理成功",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog1.show();
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            dialog1.dismiss();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
                break;
            case R.id.shanchu:
                AlertDialog.Builder dialog2=new AlertDialog.Builder(shezhi.this);
                dialog2.setMessage("确认删除此账号的所有信息？");
                dialog2.setIcon(R.mipmap.ic_launcher);
                dialog2.setCancelable(false);//可以通过返回键取消
                dialog2.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //清理此账号的所有信息,完成后直接进入登录界面
                        editor=pref.edit();
                        editor.clear();
                        editor.putBoolean("jizhumima",false);
                        editor.apply();
                        Intent intent=new Intent(shezhi.this,denglu1.class);
                        startActivity(intent);
                        Toast.makeText(shezhi.this,"您已删除此账号",Toast.LENGTH_SHORT).show();
                        finish();

                    }
                })  ;
                dialog2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(shezhi.this,"您已取消删除此账号",Toast.LENGTH_SHORT).show();
                    }
                })  ;
                dialog2.show();
                break;
        }
    }
    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);//直接结束程序
    }*/
}
