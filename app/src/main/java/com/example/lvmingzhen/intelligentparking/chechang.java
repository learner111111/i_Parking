package com.example.lvmingzhen.intelligentparking;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.support.v7.widget.SearchView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;


/**
 * Created by lvmingzhen on 2018/4/28.
 */

public class chechang extends Fragment implements View.OnClickListener,OnGetPoiSearchResultListener,OnGetSuggestionResultListener {
    private Button juli;
    private Button shijian;
    private Button yongqian;
    private Button xunzhao;
    private Button next;
    /*private SearchView chaxun;*/
    private ListView chaxun2;
    private MapView mapView;
    private BaiduMap baiduMap;
    private PoiSearch mpoiSearch;
    private SuggestionSearch mSuggestionSearch;
    private MapStatusUpdate update;
    private SharedPreferences pref1;
    private SharedPreferences pref2;
    private SharedPreferences pref3;
    private EditText cities;
    private LocationClient mLocationCilent;
    private MyLocationData.Builder builder;
    private MyLocationData data;
    private boolean isFirstLocate=true;
    private int load_Index = 0;
    //自动填充的text
    private AutoCompleteTextView keyWorldsView = null;
    private ArrayAdapter<String> sugAdapter = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        SDKInitializer.initialize(getActivity().getApplicationContext());
        mLocationCilent=new LocationClient(getActivity().getApplicationContext());
        mLocationCilent.registerLocationListener(new MyLocationListener());
        View view=inflater.inflate(R.layout.chechang,container,false);
        String[] pipei={"aa","bb","cc"};//此处实时存储搜索后匹配的十个字符串。
        mapView=(MapView)view.findViewById(R.id.tingchechang);
        juli=(Button)view.findViewById(R.id.juli);
        shijian=(Button)view.findViewById(R.id.shijian);
        yongqian=(Button)view.findViewById(R.id.yongqian);
        xunzhao=(Button)view.findViewById(R.id.search);
        next=(Button)view.findViewById(R.id.map_next_data);
        keyWorldsView = (AutoCompleteTextView) view.findViewById(R.id.searchkey);
        cities=(EditText)view.findViewById(R.id.city);
        /*chaxun=(SearchView) view.findViewById(R.id.chaxun);
        chaxun2=(ListView)view.findViewById(R.id.chaxun_listView);*/
        // 加载一个显示坐标的一个图标
        BitmapDescriptor bimp = new BitmapDescriptorFactory()
                .fromResource(R.drawable.jiemian);
        BDLocation location=new BDLocation();
        // 构建MarkerOption，用于在地图上添加Marker
        LatLng latlng=new LatLng(location.getLatitude(),location.getLongitude());
        OverlayOptions option = new MarkerOptions().position(latlng).icon(bimp);
        baiduMap=mapView.getMap();
        baiduMap.addOverlay(option);
        // 实例化PoiSearch
        mpoiSearch = PoiSearch.newInstance();
        // 注册搜索事件监听
        mpoiSearch.setOnGetPoiSearchResultListener(this);
        // 实例化建议查询类
        mSuggestionSearch = SuggestionSearch.newInstance();
        baiduMap.setMyLocationEnabled(true);
        sugAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line);
        keyWorldsView.setAdapter(sugAdapter);
        juli.setOnClickListener(this);
        shijian.setOnClickListener(this);
        yongqian.setOnClickListener(this);
        map();
        /*chaxun2.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, pipei));
        chaxun2.setTextFilterEnabled(true);//设置lv可以被过滤
        chaxun= (SearchView) view.findViewById(R.id.chaxun);
        // 设置该SearchView默认是否自动缩小为图标
        chaxun.setIconifiedByDefault(false);
        // 为该SearchView组件设置事件监听器
        chaxun.setOnQueryTextListener(this);
        // 设置该SearchView显示搜索按钮
        chaxun.setSubmitButtonEnabled(true);
        // 设置该SearchView内默认显示的提示文本
        chaxun.setQueryHint("请输入您的目的地");*/
        OnGetSuggestionResultListener listener = new OnGetSuggestionResultListener() {
            public void onGetSuggestionResult(SuggestionResult res) {

                if (res == null || res.getAllSuggestions() == null) {
                    return;
                    //未找到相关结果
                }

                //获取在线建议检索结果
            }
        };
        final String currentPosition;
        currentPosition=location.getCity();
        mSuggestionSearch.setOnGetSuggestionResultListener(listener);
        // 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新
        keyWorldsView.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {           //cs为原有文本，即被替换的文本
                if (cs.length() <= 0) {
                    return;
                }
                 cities.setText(currentPosition);
                /**
                 * 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新
                 */
                mSuggestionSearch.requestSuggestion((new SuggestionSearchOption()).keyword(cs.toString()).city(currentPosition));
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
        return  view;
    }
    // 用户输入字符时激发事件
    /*@Override
    public boolean onQueryTextChange(String newText) {
        /*if (TextUtils.isEmpty(newText)) {
            // 清除ListView的过滤
            chaxun2.clearTextFilter();
        } else {
            // 使用用户输入的内容对ListView的列表项进行过滤
            chaxun2.setFilterText(newText);
        }
        return true;
    }
    // 单击搜索按钮时激发该方法,用户点击搜索时激发listView实时显示相匹配的前十个地点
    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(getActivity(), "您的选择是:" + query, Toast.LENGTH_SHORT).show();
        return false;
    }*/
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.juli :
                break;//按下后突出显示距离最近的停车场
            case R.id.shijian :
                break;//按下后突出显示时间最短的停车场
            case R.id.yongqian :
                break;//按下后突出显示用钱最少的停车场
            case R.id.search :
                searchButtonProcess(getView());
                break;
            case R.id.map_next_data :
                goToNextPage(getView());
        }
    }
    private void map()
    {
        Boolean q,w,e;
        pref1=getActivity().getSharedPreferences("readPhoneState", Context.MODE_PRIVATE);
        pref2=getActivity().getSharedPreferences("writeExternalStorage", Context.MODE_PRIVATE);
        pref3=getActivity().getSharedPreferences("accessCoarseLocation", Context.MODE_PRIVATE);
        q=pref1.getBoolean("readPhoneState1",false);
        w=pref2.getBoolean("writeExternalStorage1",false);
        e=pref3.getBoolean("accessCoarseLocation1",false);
        //当可以定位时所可以做的多余的事情，一般不需要写，都在定位后的onReceiveLocation函数中写
        if(q&&w&&e)
        {
            initLocation();
            mLocationCilent.start();
            Toast.makeText(getActivity(),"可以定位！",Toast.LENGTH_LONG).show();
        }
        //当不可以定位时所需要做的事情
        else {
            Toast.makeText(getActivity(), "无法定位！", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onResume()
    {
        super.onResume();
        mapView.onResume();
    }
    @Override
    public void onPause()
    {
        super.onPause();
        mapView.onPause();
    }
    public void onDestroy()
    {
        super.onDestroy();
        mLocationCilent.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
        mpoiSearch.destroy();
        mSuggestionSearch.destroy();
    }
    private void xianshi(BDLocation location)
    {
        if(isFirstLocate) {
            LatLng lat=new LatLng(location.getLatitude(),location.getLongitude());
            update=MapStatusUpdateFactory.newLatLng(lat);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16.5f);
            baiduMap.animateMapStatus(update);
            isFirstLocate=false;
        }
        builder=new MyLocationData.Builder();
        builder.latitude(location.getLatitude());
        builder.longitude(location.getLongitude());
        data=builder.build();
        baiduMap.setMyLocationData(data);
    }
    private void initLocation()
    {
        LocationClientOption option =new LocationClientOption();
        option.setScanSpan(3000);
        option.setIsNeedAddress(true);
        //option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        mLocationCilent.setLocOption(option);
    }
    public class MyLocationListener implements BDLocationListener
    {
        @Override
        public void onReceiveLocation(BDLocation location)
        {
            if(location.getLocType()==BDLocation.TypeGpsLocation||location.getLocType()==BDLocation.TypeNetWorkLocation)
            {
                xianshi(location);
            }
            //xianshi(location);
        }
    }
    public void searchButtonProcess(View v) {
        EditText editcity = (EditText) v.findViewById(R.id.city);
        EditText editSearchKey = (EditText) v.findViewById(R.id.searchkey);
        // PoiCitySearchOption（）poi城市内检索参数 city搜索城市 keyword key搜索关键字 pageNum -
        // 分页编号
        mpoiSearch.searchInCity((new PoiCitySearchOption())
                .city(editcity.getText().toString())
                .keyword(editSearchKey.getText().toString())
                .pageNum(load_Index));
    }
    public void goToNextPage(View v) {
        load_Index++;
        searchButtonProcess(null);
    }
    // 针对检索功能模块（POI检索、线路规划等），地图SDK还对外提供相应的覆盖物来快速展示结果信息。这些方法都是开源的，开发者可根据自己的实际去求来做个性化的定制。
    // 利用检索结果覆盖物展示POI搜索结果的方式如下：
    // 第一步，构造自定义 PoiOverlay 类；
    private class MyPoiOverlay extends PoiOverlay {
        public MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }
        @Override
        public boolean onPoiClick(int index) {
            super.onPoiClick(index);
            // PoiInfo类是poi信息类
            PoiInfo poi = getPoiResult().getAllPoi().get(index);
            // 判断poi点是否有详情页面，这里也可以判断其它不是餐厅页面需要自己去查找方法api
            if (poi.hasCaterDetails) {
                // 返回该 poi 详情检索参数对象
                mpoiSearch.searchPoiDetail((new PoiDetailSearchOption()).poiUid(poi.uid));
            }
            return true;
        }
    }
    // 检索查询事件监听实现的方法
    @Override
    public void onGetPoiDetailResult(PoiDetailResult result) {
        if (result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(getActivity(), "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "成功，查看详情页面", Toast.LENGTH_SHORT).show();
        }
    }
    // 检索查询事件监听实现的方法
    @Override
    public void onGetPoiResult(PoiResult result) {
        if (result == null
                || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
            return;
        }
        // 判断搜索结果状态码result.error是否等于检索结果状态码， SearchResult.ERRORNO值的没问题
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            baiduMap.clear();
            PoiOverlay overlay = new MyPoiOverlay(baiduMap);
            baiduMap.setOnMarkerClickListener(overlay);
            overlay.setData(result);
            overlay.addToMap();
            overlay.zoomToSpan();
            return;
        }
        // AMBIGUOUS_KEYWORD表示 检索词有岐义
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {
            // 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
            String strInfo = "在";//使用stringbulid比较好
            for (CityInfo cityInfo : result.getSuggestCityList()) {
                strInfo += cityInfo.city;
                strInfo += ",";
            }
            strInfo += "找到结果";
            Toast.makeText(getActivity(), strInfo, Toast.LENGTH_LONG).show();
        }
    }
    // 建议查询事件监听所要实现的方法,这个方法主要是为AutoCompleteTextView提供参数
    @Override
    public void onGetSuggestionResult(SuggestionResult res) {
        if (res == null || res.getAllSuggestions() == null) {
            return;
        }
        sugAdapter.clear();
        //遍历res中所有建议的数组，将数据存放在sugadapter适配器中  getAllSuggestions是一个list链表
        for (SuggestionResult.SuggestionInfo info : res.getAllSuggestions()) {
            if (info.key != null)
                sugAdapter.add(info.key);
        }
        //改变适配器
        sugAdapter.notifyDataSetChanged();
    }
    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult result) {
        //poi 室内检索结果回调
    }
}
