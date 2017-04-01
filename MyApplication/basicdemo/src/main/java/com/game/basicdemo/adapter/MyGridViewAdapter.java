package com.game.basicdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.game.basicdemo.R;
import com.game.basicdemo.bean.Picture;

import java.util.List;

/**
 * Created by admin on 2017/2/10.
 */

public class MyGridViewAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<Picture> pictureList;
    private Context context;
    public MyGridViewAdapter(List<Picture> list,Context context){
        this.pictureList = list;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        if(pictureList!=null){
            return pictureList.size();
        }else{
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return pictureList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.piture_item,null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(pictureList.get(position).getTitle());
        viewHolder.image.setImageResource(pictureList.get(position).getImageId());
        return convertView;
    }
    class ViewHolder{
        public TextView title;
        public ImageView image;
    }
}
