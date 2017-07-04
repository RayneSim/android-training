package com.example.testing.raynesim.customviewgroupdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    List<MenuItem> mMenuItems = new ArrayList<>();

    private CircleMenuLayout circleMenuLayout;

    private String[] mItemTexts = new String[]{
            "安全中心" , "特色服务" ,"投资理财"
            , "转账汇款" , "我的账户" , "信用卡"
    };

    private int[] mItemImgs = new int[]{
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        circleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menu_layout);
//        circleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);
//        circleMenuLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this,
//                        mItemTexts[position], Toast.LENGTH_SHORT).show();
//            }
//        });



        //模拟数据
        mockMenuItems();
//        mListView = (ListView) findViewById(R.id.id_menu_layout);
        //设置适配器
//        mListView.setAdapter(new CircleMenuAdapter(mMenuItems));
//        //设置点击事件
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, mMenuItems.get(position).title,
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

        circleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menu_layout);
        circleMenuLayout.setAdapter(new CircleMenuAdapter(mMenuItems));
        circleMenuLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, mMenuItems.get(position).title,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public <T extends ViewGroup> void initView(T viewGroup){
//        viewGroup = (T) findViewById(R.id.id_menu_layout);
//        viewGroup.setAdapter(new CircleMenuAdapter(mMenuItems));
//        viewGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, mMenuItems.get(position).title,
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


    private void mockMenuItems() {
        for (int i = 0; i < mItemImgs.length; i++){
            mMenuItems.add(new MenuItem(mItemImgs[i], mItemTexts[i]));
        }
    }
}
