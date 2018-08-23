package com.example.lvmingzhen.intelligentparking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvmingzhen on 2018/5/1.
 */

public class kabao extends AppCompatActivity {
    private List<kaquan> kaquanList;
    private ListView listView;
    private kaquanAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kabao);
        kaquanList=new ArrayList<kaquan>();
        init();
        listView=(ListView) findViewById(R.id.kaquan_listView);
        adapter=new kaquanAdapter(kabao.this,R.layout.kaquan,kaquanList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            kaquan quan=kaquanList.get(position);
            //点击后跳转到该停车场的详情信息
            }
        });
        //在listview中可以实时更新卡券，用户并可以对卡券进行使用操作。获得卡券的途径：比如在第一次去某停车场停车付完款后，可得到关于此停车场的卡券
    }
    private void init()//实际应该为与后端相连，得到卡券信息后根据信息进行卡券的动态添加。此处为直接初始化。
    {
        kaquan legou=new kaquan("乐购地下停车场",R.drawable.ic_launcher,"一次只能用一张此类型的卡券","2018年6月30日到期","¥5","满两小时可用");
        kaquanList.add(legou);
        kaquan wanxiangcheng=new kaquan("万象城地下停车场",R.drawable.ic_launcher,"一次只能用一张此类型的卡券","2018年6月30日到期","¥3","满两小时可用");
        kaquanList.add(wanxiangcheng);
    }
}
