package com.ren.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DragGridLayout mTopDrag = (DragGridLayout) findViewById(R.id.top_drag);
        final DragGridLayout mBottomDrag = (DragGridLayout) findViewById(R.id.bottom_drag);

        mBottomDrag.setHasCanDrag(false);
        mTopDrag.setHasCanDrag(true);

        List<String> items = new ArrayList<>();
        items.add("北京");
        items.add("上海");
        items.add("深圳");
        items.add("广州");
        items.add("武汉");
        items.add("济南");
        items.add("重庆");
        items.add("长沙");
        items.add("大连");
        items.add("南京");
        items.add("石家庄");

        mTopDrag.setItems(items);

        List<String> items2 = new ArrayList<>();
        items2.add("日本");
        items2.add("东京");
        items2.add("韩国");
        items2.add("新加坡");
        items2.add("纽约");
        items2.add("常德");
        items2.add("广西");
        items2.add("哈尔滨");
        items2.add("南昌");
        items2.add("无锡");
        items2.add("海南");

        mBottomDrag.setItems(items2);

        //设置条目点击监听
        mTopDrag.setOnDragItemClickListener(new DragGridLayout.OnDragItemClickListener() {
            @Override
            public void onDragItemClick(TextView tv) {
                //移除点击的条目，把条目添加到下面的Gridlayout
                mTopDrag.removeView(tv);//移除是需要时间,不能直接添加
                mBottomDrag.addItem(tv.getText().toString());
            }
        });

        mBottomDrag.setOnDragItemClickListener(new DragGridLayout.OnDragItemClickListener() {
            @Override
            public void onDragItemClick(TextView tv) {
                //移除点击的条目，把条目添加到上面的Gridlayout
                mBottomDrag.removeView(tv);//移除是需要时间,不能直接添加
                mTopDrag.addItem(tv.getText().toString());
            }
        });

    }
}
