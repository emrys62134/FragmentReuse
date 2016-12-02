package com.pei.fragmentreuse;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    /**
     * ViewPager的 Fragment复用 思路:
     * 1, 将TabLayout 对应网址进行解析
     * 2, 将解析之后的内容传给对应的Adapter
     * 3, 在Adapter中获取TabLayout的Title
     * 4, 对应每一个TabLayout Title都有一个与之对应的id
     * 5, 使用一个静态的方法, 将获取的ID供外部类进行使用
     * 6, 在复用的Fragment中通过Adapter提供的静态方法获取TabLayout对应的ID
     * 7, 将获取到的id进行网址拼接
     * 8, 解析之后即可获取对应ViewPager Fragment要显示的内容
     */

    private TabLayout tab;
    private ViewPager vp;
    private String url ="http://api.liwushuo.com/v2/channels/preset?gender=1&generation=2" ;
    private Bean bean;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab = (TabLayout) findViewById(R.id.tab_main);
        vp = (ViewPager) findViewById(R.id.vp_main);
        adapter = new MyAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                bean = gson.fromJson(response,Bean.class);
                adapter.setBean(bean);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);
    }
}
