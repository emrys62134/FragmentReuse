package com.pei.fragmentreuse;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by dllo on 16/12/1.
 */

public class MyAdapter extends FragmentPagerAdapter {

    private static Bean bean;

    public void setBean(Bean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return CommonFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getData().getCandidates().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return bean.getData().getCandidates().get(position).getName();
    }

    public static int getData(int position){
        return bean.getData().getCandidates().get(position).getId();
    }
}
