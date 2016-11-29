package com.example.he.mycrawlerapplication.bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.he.mycrawlerapplication.R;

import java.util.List;

/**
 * Created by HE on 2016/11/27.
 */

public class ListViewAdapter extends BaseAdapter{
    Context context;
    List<Newsinfo> list;
    CrawlerData crawlerData;

    public ListViewAdapter(Context context, List<Newsinfo> list) {
        this.context = context;
        this.list = list;
        crawlerData=new CrawlerData(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(context).inflate(R.layout.listview_item,null);
        TextView textView= (TextView) view.findViewById(R.id.textView);
        ImageView imageView= (ImageView) view.findViewById(R.id.imageView);
        textView.setText(list.get(i).getTitel());
        crawlerData.getImg(imageView,list.get(i).getImage());
        return view;
    }

}

