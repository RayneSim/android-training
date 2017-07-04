package com.example.testing.raynesim.customviewgroupdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by leisure on 2017/6/23.
 */

public class CircleMenuAdapter extends BaseAdapter {

    List<MenuItem> mMenuItems;

    public CircleMenuAdapter(List<MenuItem> mMenuItems) {
        this.mMenuItems = mMenuItems;
    }

    @Override
    public int getCount() {
        return mMenuItems.size();
    }

    @Override
    public MenuItem getItem(int position) {
        return mMenuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View itemView = mInflater.inflate(R.layout.circle_menu_item, parent, false);
        initMenuItem(itemView, position);
        return itemView;
    }

    private void initMenuItem(View itemView, int position) {
        //获取数据
        final MenuItem item = getItem(position);
        ImageView iv = (ImageView) itemView.findViewById(R.id.id_circle_menu_item_image);
        TextView tv = (TextView) itemView.findViewById(R.id.id_circle_menu_item_text);
        //数据绑定
        iv.setImageResource(item.imageId);
        tv.setText(item.title);
    }
}
