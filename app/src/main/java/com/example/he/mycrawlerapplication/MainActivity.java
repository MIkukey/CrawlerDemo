package com.example.he.mycrawlerapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.he.mycrawlerapplication.bean.CrawlerData;
import com.example.he.mycrawlerapplication.bean.ListViewAdapter;
import com.example.he.mycrawlerapplication.bean.Newsinfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CrawlerData crawlerData;
    ListView listView;
    List<Newsinfo> list;
    ListViewAdapter listViewAdapter;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Document document= Jsoup.parse(msg.obj.toString());
                    Elements elements=document.getElementsByClass("Q-tpWrap");

                    for (int i = 0; i <elements.size() ; i++) {
                        String imgUrl;
                        if (elements.get(i).getElementsByClass("picto").size() > 0) {
                            if(i<10){
                                imgUrl=elements.get(i).getElementsByClass("picto").get(0).attr("src");
                            }else {
                                imgUrl = elements.get(i).getElementsByClass("picto").get(0).attr("_src");
                            }
                            Log.e("MainActivity", imgUrl);
                           String url=elements.get(i).getElementsByClass("linkto").get(0).attr("href");
                           String title=elements.get(i).getElementsByClass("linkto").get(0).text();
                            list.add(new Newsinfo(title,url,imgUrl));
                        }
                    }
                    listViewAdapter=new ListViewAdapter(getBaseContext(),list);
                    listView.setAdapter(listViewAdapter);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       list=new ArrayList<>();
        listView= (ListView) findViewById(R.id.myListView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                crawlerData=new CrawlerData(getBaseContext());
                crawlerData.getInfo(new CrawlerData.Callback() {
                    @Override
                    public void onClick(Object object) {
                        Message message=new Message();
                        message.obj=object;
                        message.what=1;
                        handler.sendMessage(message);
                    }
                });

            }
        }).start();
    }
}
