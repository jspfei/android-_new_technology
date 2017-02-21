package com.jf.butterknife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017/2/21.
 */

public class MyAdapter extends BaseAdapter {

    private Context context;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView != null){
            holder = (ViewHolder) convertView.getTag();
        }
        else{
            convertView =  LayoutInflater.from(context).inflate(R.layout.whatever,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        }
        holder.title.setText("测试");

        return null;
    }

    static class ViewHolder{
       @BindView(R.id.title)
        TextView title;
        @BindView(R.id.job_title)
        TextView job_title;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
