package com.example.lvmingzhen.intelligentparking;

/**
 * Created by lvmingzhen on 2018/5/23.
 */

public class kaquan {
    private String name;
    private int imageId;
    private String imformation;
    private String time;
    private String money;
    private String xianzhi;
    public kaquan(String name,int imageId,String imformation,String time,String money,String xianzhi)
    {
        this.name=name;
        this.imageId=imageId;
        this.imformation=imformation;
        this.time=time;
        this.money=money;
        this.xianzhi=xianzhi;
    }
    public String getName()
    {
        return name;
    }
    public int getImageId()
    {
        return imageId;
    }
    public String getImformation()
    {
        return imformation;
    }
    public  String getTime()
    {
        return time;
    }
    public String getMoney()
    {
        return money;
    }
    public String getXianzhi()
    {
        return xianzhi;
    }
}
