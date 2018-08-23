package com.example.lvmingzhen.intelligentparking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by lvmingzhen on 2018/5/1.
 */

public class ziliao extends AppCompatActivity {
    private EditText nicheng;
    private TextView shoujihao;
    private Spinner shengfen;
    private Spinner chengshi;
    private EditText chehao;
    private EditText xingming;
    private Spinner xingbie;
    private Button baocunxiugai;
    private String sheng;
    private String cheng;
    private String sex;
    private SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ziliao);
        nicheng=(EditText)findViewById(R.id.nicheng2);//存
        shoujihao=(TextView)findViewById(R.id.shoujihao2);
        shengfen=(Spinner)findViewById(R.id.shengfen);//存
        chengshi=(Spinner)findViewById(R.id.chengshi);//存
        chehao=(EditText)findViewById(R.id.chehao);//存
        xingming=(EditText)findViewById(R.id.xingming2);//存
        xingbie=(Spinner)findViewById(R.id.xingbie2);//存
        baocunxiugai=(Button)findViewById(R.id.baocunxiugai);
        pref=getSharedPreferences("yonghuming",MODE_PRIVATE);
        String phone=pref.getString("phoneEditText","");
        shoujihao.setText(phone);
        String[] mItem = getResources().getStringArray(R.array.shengfen);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shengfen.setAdapter(adapter);
        shengfen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] sheng_fen=getResources().getStringArray(R.array.shengfen);
                sheng=sheng_fen[position];
                //Toast.makeText(ziliao.this,"您选择了"+sheng_fen[position]+"省",Toast.LENGTH_SHORT).show();
                //  pos为位置
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        String[] mItem2 = getResources().getStringArray(R.array.chengshi);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mItem2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chengshi.setAdapter(adapter2);
        chengshi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] cheng_shi=getResources().getStringArray(R.array.chengshi);
                cheng=cheng_shi[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        String[] mItem3 = getResources().getStringArray(R.array.xingbie);
        ArrayAdapter<String> adapter3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mItem3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        xingbie.setAdapter(adapter3);
        xingbie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] xing_bie=getResources().getStringArray(R.array.xingbie);
                sex=xing_bie[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        baocunxiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
    }
    public void check() {

        final String nicheng2 = nicheng.getText().toString();
        if (!isValidnicheng(nicheng2)) {
            nicheng.setError("长度须小于8！");
        }
        final String chehao2 = chehao.getText().toString();
        if (!isValidchehao(chehao2)) {
            chehao.setError("长度须为5！");
        }
        final String xingming2 = xingming.getText().toString();
        if (!isValidxingming(xingming2)) {
            xingming.setError("长度须在1—10之间！");
        }
        if(isValidnicheng(nicheng2)&&isValidchehao(chehao2)&&isValidxingming(xingming2))
        {
            Intent intent=new Intent();
            huichuan(intent);
            //在数据库中更新所有修改的信息
            Toast.makeText(ziliao.this,"保存修改成功！",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    private boolean isValidnicheng(String nicheng) {
        if(nicheng.length()<=8)
            return true;
        return false;
    }
    private boolean isValidchehao(String chehao) {
        if(chehao.length()==5)
            return true;
        return false;
    }
    private boolean isValidxingming(String xingming) {
        if(xingming.length()>0&&xingming.length()<=10)
            return true;
        return false;
    }
    private void huichuan(Intent intent)
    {
        intent.putExtra("nicheng",nicheng.getText().toString());
        intent.putExtra("sheng",sheng);
        intent.putExtra("cheng",cheng);
        intent.putExtra("chehao",chehao.getText().toString());
        intent.putExtra("xingming",xingming.getText().toString());
        intent.putExtra("xingbie",sex);
        setResult(RESULT_OK,intent);
    }
   /*public void select(String []x)
    {
        String[] mItem = getResources().getStringArray(x);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        x.setAdapter(adapter);
        x.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] sheng_fen=getResources().getStringArray(x);
                //Toast.makeText(ziliao.this,"您选择了"+sheng_fen[position]+"省",Toast.LENGTH_SHORT).show();
                //将选择的省份上传到数据库  pos为位置
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }*/
}
