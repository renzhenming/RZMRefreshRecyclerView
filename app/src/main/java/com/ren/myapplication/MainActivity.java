package com.ren.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RZMRefreshRecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private ArrayList<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        mRecyclerView = (RZMRefreshRecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        for (int i = 0 ; i < 15 ; i++){
            mList.add("测试数据："+i);
        }
        mAdapter = new RecyclerAdapter(this,mList);

        View otherHeaderView = LayoutInflater.from(this).inflate(R.layout.other_head_view,null);
        mRecyclerView.addChildHeaderView(otherHeaderView);
        mRecyclerView.setAdapter(mAdapter);
    }
}
