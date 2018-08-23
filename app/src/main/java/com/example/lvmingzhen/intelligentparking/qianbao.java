package com.example.lvmingzhen.intelligentparking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvmingzhen on 2018/5/1.
 */

public class qianbao extends AppCompatActivity {
    private TextView yue;
    private Button yinhangka1;
    private List<yinhangka>yinhangkaList;
    private ListView listView;
    private yinhangkaAdapter adapter;
    private String kahaox;
    private String kaleixingx;
    private SharedPreferences pref;
    private SharedPreferences pref2;
    private SharedPreferences.Editor editor;
    private int pos=-1;
    private float yuef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qianbao);
        pref2=getSharedPreferences("yuE",MODE_PRIVATE);
        editor=pref2.edit();
        editor.putFloat("Yue",yuef);
        editor.apply();
        yuef=pref2.getFloat("Yue",0);
        yue=(TextView) findViewById(R.id.yue2);//实时显示余额   将余额的初始值设为从数据库中得到的实时的值
        String yueff=String.valueOf(yuef);
        yue.setText(yueff);
        yinhangka1=(Button) findViewById(R.id.yinhangka);
        if(pref2.getBoolean("panduan2",false))
        {
            yuef=pref2.getFloat("Yue",0);
            String qiann=String.valueOf(yuef);
            yue.setText(qiann);
            editor.putBoolean("panduan2",false);
            editor.apply();
        }
        pref=getSharedPreferences("Ok",MODE_PRIVATE);
        yinhangkaList=new ArrayList<>();
        adapter=new yinhangkaAdapter(qianbao.this,R.layout.yinhangka,yinhangkaList);
        listView=(ListView)findViewById(R.id.yinhangka_list);
        listView.setAdapter(adapter);
        yinhangka1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(qianbao.this,yinhangkaAdd.class);
                startActivityForResult(intent,2);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos=position;
                yinhangka ka=yinhangkaList.get(position);
                Intent intent=new Intent(qianbao.this,yinhangkamanager.class);
                intent.putExtra("kaHao",ka.getName());
                intent.putExtra("kaleiXing",ka.getImageId());
                startActivityForResult(intent,3);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        switch (requestCode)
        {
            case 2:
                if(resultCode==RESULT_OK)
                {
                    kahaox=data.getStringExtra("kahao1");
                    kaleixingx=data.getStringExtra("kaleixing");
                    Boolean ok=pref.getBoolean("oK",false);
                    if(ok==true)
                    {
                        adds(kahaox,kaleixingx);//在listview里添加新的银行卡信息
                    }
                }
                break;
            case 3:
                if(resultCode==RESULT_OK)
                {
                    Boolean ok=data.getBooleanExtra("panduan",false);
                    if(ok==true)
                    {
                        removes(pos);//删除相应的银行卡
                    }
                }
                break;
        }
    }
    private void adds(String kahao,String kaleixing)
    {
        String kahao1="";
        String kahao2="****  ****  ****  ";
        if(kahao.length()==19)
        kahao1=kahao.substring(15);
        kahao2+=kahao1;
        yinhangka ka=new yinhangka(kahao2,R.drawable.ic_launcher);//将卡号与对应的卡类型的图片显示出来
        yinhangkaList.add(ka);
        adapter.notifyDataSetChanged();
    }
    private void removes(int i)
    {
        yinhangkaList.remove(i);
        adapter.notifyDataSetChanged();
    }
    }
