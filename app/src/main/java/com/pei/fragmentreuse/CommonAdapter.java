package com.pei.fragmentreuse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/12/2.
 */

public class CommonAdapter extends BaseAdapter {

    private CommonBean bean;
    private Context mContext;



    public CommonAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setBean(CommonBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getData().getItems().size();
    }

    @Override
    public Object getItem(int i) {
        return bean == null ? null : bean.getData().getItems().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_common,viewGroup,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(bean.getData().getItems().get(i).getTitle());
        viewHolder.desc.setText(bean.getData().getItems().get(i).getIntroduction());
        Picasso.with(mContext).load(bean.getData().getItems().get(i).getCover_image_url()).into(viewHolder.pic);
        return view;
    }
    class ViewHolder {

        private ImageView pic;
        private TextView title;
        private TextView desc;
        private TextView user;
        private TextView count;
        public ViewHolder(View view) {
            pic = (ImageView) view.findViewById(R.id.pic_common);
            title = (TextView) view.findViewById(R.id.title_common);
            desc = (TextView) view.findViewById(R.id.desc_common);
            user = (TextView) view.findViewById(R.id.user_common);
            count = (TextView) view.findViewById(R.id.count_count);
        }
    }
}
