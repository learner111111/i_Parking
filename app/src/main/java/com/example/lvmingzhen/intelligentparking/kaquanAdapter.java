package com.example.lvmingzhen.intelligentparking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by lvmingzhen on 2018/5/23.
 */

public class kaquanAdapter extends ArrayAdapter<kaquan> {
    private int resourceId;
    public kaquanAdapter(Context context, int textViewResourceId, List<kaquan> objects)
    {
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        kaquan quan=getItem(position);
        View view;
        kaquanAdapter.ViewHolder viewHolder;
        if(convertView==null)
        {
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.kaquanImamge=(ImageView)view.findViewById(R.id.kaquan_image);
            viewHolder.kaquanName=(TextView) view.findViewById(R.id.kaquan_name);
            viewHolder.kaquanInformation=(TextView) view.findViewById(R.id.kaquan_information);
            viewHolder.kaquanTime=(TextView) view.findViewById(R.id.kaquan_time);
            viewHolder.kaquanMoney=(TextView) view.findViewById(R.id.kaquan_money);
            viewHolder.kaquanXianzhi=(TextView) view.findViewById(R.id.kaquan_xianzhi);
            view.setTag(viewHolder);
        }
        else
        {
            view=convertView;
            viewHolder=(kaquanAdapter.ViewHolder)view.getTag();
        }
        viewHolder.kaquanImamge.setImageResource(quan.getImageId());
        viewHolder.kaquanName.setText(quan.getName());
        viewHolder.kaquanInformation.setText(quan.getImformation());
        viewHolder.kaquanTime.setText(quan.getTime());
        viewHolder.kaquanMoney.setText(quan.getMoney());
        viewHolder.kaquanXianzhi.setText(quan.getXianzhi());
        return view;
    }
    class ViewHolder
    {
        ImageView kaquanImamge;
        TextView kaquanName;
        TextView kaquanInformation;
        TextView kaquanTime;
        TextView kaquanMoney;
        TextView kaquanXianzhi;
    }
}
