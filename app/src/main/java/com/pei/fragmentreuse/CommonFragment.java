package com.pei.fragmentreuse;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/12/2.
 */

public class CommonFragment extends Fragment {

    private String newUrl;
    private Context mContext;
    private CommonBean bean;
    private ListView lv;
    private CommonAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_common,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv = (ListView) view.findViewById(R.id.lv_common);
        adapter = new CommonAdapter(mContext);

        lv.setAdapter(adapter);
        Bundle bundle = getArguments();
        if (bundle != null){
            String id = bundle.getString("data");
            newUrl = "http://api.liwushuo.com/v2/channels/"+id+
                     "/items_v2?ad=2&gender=1&generation=2&limit=20&offset=0";

        }

        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(newUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                bean = gson.fromJson(response,CommonBean.class);
                adapter.setBean(bean);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);
    }

    public static CommonFragment newInstance(int position) {

        Bundle args = new Bundle();
        String data = MyAdapter.getData(position)+"";
        args.putString("data",data);
        CommonFragment fragment = new CommonFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
