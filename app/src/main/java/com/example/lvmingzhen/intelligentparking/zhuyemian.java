package com.example.lvmingzhen.intelligentparking;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvmingzhen on 2018/4/25.
 */
public class zhuyemian extends AppCompatActivity implements View.OnClickListener{

    private Button chechang;
    private Button chewei;
    private Button yonghu;
    private SharedPreferences pref1;
    private SharedPreferences pref2;
    private SharedPreferences pref3;
    private SharedPreferences pref4;
    private SharedPreferences pref5;
    private SharedPreferences.Editor editor1;
    private SharedPreferences.Editor editor2;
    private SharedPreferences.Editor editor3;
    private SharedPreferences.Editor editor4;
    private SharedPreferences.Editor editor5;

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.caidan,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.saoyisao://实现扫描二维码的功能
                break;
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuyemian);
        chechang=(Button) findViewById(R.id.chechang);
        chewei=(Button) findViewById(R.id.chewei);
        yonghu=(Button) findViewById(R.id.yonghu);
        replaceFragment(new chechang());
        chechang.setOnClickListener(this);
        chewei.setOnClickListener(this);
        yonghu.setOnClickListener(this);
        pref1=getSharedPreferences("readPhoneState",MODE_PRIVATE);
        editor1=pref1.edit();
        pref2=getSharedPreferences("writeExternalStorage",MODE_PRIVATE);
        editor2=pref2.edit();
        pref3=getSharedPreferences("accessCoarseLocation",MODE_PRIVATE);
        editor3=pref3.edit();
        pref4=getSharedPreferences("recordAudio",MODE_PRIVATE);
        editor4=pref4.edit();
        pref5=getSharedPreferences("camera",MODE_PRIVATE);
        editor5=pref5.edit();
        List<String>permissList=new ArrayList<>();
        if(ContextCompat.checkSelfPermission(zhuyemian.this, Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED)
        {
            permissList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(zhuyemian.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            permissList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(ContextCompat.checkSelfPermission(zhuyemian.this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            permissList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(zhuyemian.this, Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED)
        {
            permissList.add(Manifest.permission.RECORD_AUDIO);
        }
        if(ContextCompat.checkSelfPermission(zhuyemian.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
        {
            permissList.add(Manifest.permission.CAMERA);
        }
        if(!permissList.isEmpty())
        {
            String[] permissions=permissList.toArray(new String[permissList.size()]);//将List泛型集合存储进一个字符串数组中
            ActivityCompat.requestPermissions(zhuyemian.this,permissions,1);
        }
        else
        {
            editor1.putBoolean("readPhoneState1",true);
            editor1.apply();
            editor2.putBoolean("writeExternalStorage1",true);
            editor2.apply();
            editor3.putBoolean("accessCoarseLocation1",true);
            editor3.apply();
            editor4.putBoolean("recordAudio1",true);
            editor4.apply();
            editor5.putBoolean("camera1",true);
            editor5.apply();
        }
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.chechang :
                replaceFragment(new chechang());break;
            case R.id.chewei :
                replaceFragment(new chewei());break;
            case R.id.yonghu :
                replaceFragment(new yonghu());break;
        }
    }
    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.xianShi,fragment);
        transaction.commit();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[]grantResults)
    {
        switch (requestCode)
        {
            case 1:
                if(grantResults.length>0)
                {
                    int i;
                    for( i=0;i<grantResults.length;i++)
                    {
                        switch (i) {
                            case 0:
                                if(grantResults[i]!=PackageManager.PERMISSION_GRANTED) {
                                    editor1.putBoolean("readPhoneState1", false);
                                    editor1.apply();
                                    Toast.makeText(zhuyemian.this, "此程序将不能读取手机状态", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    editor1.putBoolean("readPhoneState1",true);
                                    editor1.apply();
                                }
                                break;
                            case 1:
                                if(grantResults[i]!=PackageManager.PERMISSION_GRANTED) {
                                    editor2.putBoolean("writeExternalStorage1", false);
                                    editor2.apply();
                                    Toast.makeText(zhuyemian.this, "此程序将不能读写sd卡", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    editor2.putBoolean("writeExternalStorage1",true);
                                    editor2.apply();
                                }
                                break;
                            case 2:
                                if(grantResults[i]!=PackageManager.PERMISSION_GRANTED) {
                                    editor3.putBoolean("accessCoarseLocation1", false);
                                    editor3.apply();
                                    Toast.makeText(zhuyemian.this, "此程序将不能进行定位", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    editor3.putBoolean("accessCoarseLocation1",true);
                                    editor3.apply();
                                }
                                break;
                            case 3:
                                if(grantResults[i]!=PackageManager.PERMISSION_GRANTED) {
                                    editor4.putBoolean("recordAudio1", false);
                                    editor4.apply();
                                    Toast.makeText(zhuyemian.this, "此程序将不能播放音频", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    editor4.putBoolean("recordAudio1",true);
                                    editor4.apply();
                                }
                                break;
                            case 4:
                                if(grantResults[i]!=PackageManager.PERMISSION_GRANTED) {
                                    editor5.putBoolean("camera1", false);
                                    editor5.apply();
                                    Toast.makeText(zhuyemian.this, "此程序将不能访问相机", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    editor5.putBoolean("camera1",true);
                                    editor5.apply();
                                }
                                break;
                        }
                    }
                }
                else
                {
                    Toast.makeText(zhuyemian.this,"发生了未知的错误",Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }
                break;
            default:
        }
    }
}
