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
 * Created by lvmingzhen on 2018/5/16.
 */

public class yinhangkaAdapter extends ArrayAdapter<yinhangka> {
    private int resourceId;
    public yinhangkaAdapter(Context context, int textViewResourceId, List<yinhangka>objects)
    {
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        yinhangka ka=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null)
        {
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.yinhangkaImamge=(ImageView)view.findViewById(R.id.yinhangka_image);
            viewHolder.yinhangkaName=(TextView) view.findViewById(R.id.yinhangka_kahao);
            view.setTag(viewHolder);
        }
        else
        {
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.yinhangkaImamge.setImageResource(ka.getImageId());
        viewHolder.yinhangkaName.setText(ka.getName());
        return view;
    }
    class ViewHolder
    {
        ImageView yinhangkaImamge;
        TextView yinhangkaName;
    }
}
