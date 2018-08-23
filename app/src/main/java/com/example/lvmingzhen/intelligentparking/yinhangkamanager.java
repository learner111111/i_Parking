package com.example.lvmingzhen.intelligentparking;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lvmingzhen on 2018/5/21.
 */

public class yinhangkamanager extends AppCompatActivity implements View.OnClickListener {
    private TextView yinhangka;
    private Button zhuanzhang;
    private Button tixian;
    private Button shanchu;
    private Intent intent;
    private String kahao;
    private int kaleixing;
    private Boolean OK;
    @Override
    protected void onCreate(Bundle savedInstanceState)//将要打开的卡的信息传入这里，作为此页面的数据
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yinhangkamanager);
        yinhangka=(TextView) findViewById(R.id.yinhangkaxinxi);
        zhuanzhang=(Button) findViewById(R.id.zhuanzhang);
        tixian=(Button) findViewById(R.id.tixian);
        shanchu=(Button) findViewById(R.id.shanchuka);
        OK=false;
        intent=getIntent();
        kahao=intent.getStringExtra("kaHao");
        kaleixing=intent.getIntExtra("kaleiXing",-1);
        yinhangka.setText(kahao);
        yinhangka.setBackground(getDrawable(R.drawable.kayangban));//实际应设置与卡类型相匹配的图片
        zhuanzhang.setOnClickListener(this);
        tixian.setOnClickListener(this);
        shanchu.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.zhuanzhang:
                Intent intent1=new Intent(yinhangkamanager.this,zhuanzhang.class);
                startActivity(intent1);
                //将银行卡的钱转入余额内（转入新页面，输入转账金额，进行交易）（转入成功后有通知）
                break;
            case R.id.tixian:
                Intent intent2=new Intent(yinhangkamanager.this,tixian.class);
                startActivity(intent2);
                //余额内的钱提现出来给银行卡（转入新页面，输入转账金额，进行交易）（转入成功后有通知）
                break;
            case R.id.shanchuka://弹出对话框，确认后，在数据库中删除此银行卡信息,在上个页面listView中亦删除
                AlertDialog.Builder dialog=new AlertDialog.Builder(yinhangkamanager.this);
                dialog.setMessage("确认删除此银行卡信息？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        OK=true;
                        Boolean ook=OK;
                        intent.putExtra("panduan",ook);
                        setResult(RESULT_OK,intent);
                        OK=false;
                        Toast.makeText(yinhangkamanager.this,"您已删除此银行卡",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(yinhangkamanager.this,"您已取消该操作",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                break;
            default:break;
        }
    }
    /*@Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        switch (requestCode)
        {
            case 4:

                break;
            case 5:

                break;
        }
    }*/
}
