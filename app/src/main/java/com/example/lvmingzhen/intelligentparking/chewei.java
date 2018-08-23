package com.example.lvmingzhen.intelligentparking;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by lvmingzhen on 2018/4/28.
 */

public class chewei extends Fragment implements View.OnClickListener{
    TextView shijianxianshi;
    TextView feiyongxianshi;
    TextView zuiyouchewei2;
    TextView zuiyouchukou2;
    Button zuiyouchewei;
    Button zuiyouchukou;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.chewei,container,false);
        shijianxianshi=(TextView)view.findViewById(R.id.shijianxianshi2);
        feiyongxianshi=(TextView)view.findViewById(R.id.feiyongxianshi2);
        zuiyouchewei2=(TextView)view.findViewById(R.id.zuiyouchewei2);
        zuiyouchukou2=(TextView)view.findViewById(R.id.zuiyouchukou2);
        zuiyouchewei=(Button)view.findViewById(R.id.zuiyouchewei);
        zuiyouchukou=(Button)view.findViewById(R.id.zuiyouchukou);
        zuiyouchewei.setOnClickListener(this);
        zuiyouchukou.setOnClickListener(this);
        if(true)//当开始计时的时候，将实时运算出来的最优车位和根据目的地算出来的最优出口给用户呈现出来,并开始动态更新时间和费用信息
        {
            String yongshi=new String();
            String yongqian=new String();
            String chewei=new String();
            String chukou=new String();
            shijianxianshi.setText(yongshi);
            feiyongxianshi.setText(yongqian);
            zuiyouchewei2.setText(chewei);
            zuiyouchukou2.setText(chukou);
        }
        return view;
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {

            case R.id.zuiyouchewei :
                break;//按下后开始进行车位导航
            case R.id.zuiyouchukou :
                break;//按下后开始进行出口导航
        }
    }
}
