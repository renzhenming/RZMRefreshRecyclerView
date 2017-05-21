package com.ren.myapplication;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/20.
 */
public class DragLayoutView extends GridLayout {

    /**默认列数*/
    private int mColumCount = 4;

    /**是否可以拖拽*/
    private boolean mCanDrag = true;

    /**获取到所有item的位置信息*/
    private List<Rect> items = new ArrayList<>();

    /**item之间的margin*/
    private int mMargin = 5;

    /**正在被拖拽的item*/
    private View mDragItemView;

    public int getmMargin() {
        return mMargin;
    }

    public void setmMargin(int mMargin) {
        this.mMargin = mMargin;
    }

    public int getmColumCount() {
        return mColumCount;
    }

    public void setmColumCount(int mColumCount) {
        this.mColumCount = mColumCount;
    }

    public boolean ismCanDrag() {
        return mCanDrag;
    }

    public DragLayoutView(Context context) {
        this(context,null);
    }

    public DragLayoutView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public DragLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setColumnCount(mColumCount);
        setLayoutTransition(new LayoutTransition());
    }

    /**
     * 设置是否可以拖拽
     * @param mCanDrag
     */
    public void setmCanDrag(boolean mCanDrag) {
        this.mCanDrag = mCanDrag;
        if (mCanDrag){
            setOnDragListener(mDragListener);
        }else{
            setOnDragListener(null);
        }
    }

    /**
     * 设置外部数据进入
     * @param data
     */
    public void setData(List<String> data){
        if (data == null)
            return;
        for (int i = 0; i < data.size(); i++) {
            addItem(data.get(i));
        }
    }

    /**
     * 设置数据
     * @param itemValue
     */
    private void addItem(String itemValue) {
        TextView view = new TextView(getContext());
        view.setGravity(Gravity.CENTER);
        view.setBackgroundResource(R.drawable.item_bg);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        //宽度
        params.width = getResources().getDisplayMetrics().widthPixels / 4 - 2 * mMargin;
        //外边距
        params.setMargins(mMargin, mMargin, mMargin, mMargin);
        //内边距
        view.setPadding(0, mMargin, 0, mMargin);
        view.setLayoutParams(params);
        view.setText(itemValue);
        addView(view, 0);
        if (mCanDrag){
            view.setOnLongClickListener(onLongClickListener);
        }else{
            view.setOnLongClickListener(null);
        }
    }


    private OnDragListener mDragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()){
                case DragEvent.ACTION_DRAG_STARTED:
                    //按下就初始化所有item的位置集合
                    initItemRects();
                    setEnabled(false);
                    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa");
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    setEnabled(true);
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DRAG_LOCATION:
                    int position = getExchangeItemPosition(event);
                    //同一个位置不进行交换
                    if (position != -1 && mDragItemView != getChildAt(position)){
                        //移除拖动起始位置的view
                        removeView(mDragItemView);
                        //将拖动的view添加到覆盖的位置，被覆盖的view会自动移动位置（GridLayout的功能）
                        addView(mDragItemView,position);
                    }
                    System.out.println("aaaaaaabbbbbbbbbbbbbbbbb");
                    break;
                case DragEvent.ACTION_DROP:

                    break;


            }
            return true;
        }
    };

    /**
     * event用于获取当前所在位置的x y
     * @param event
     */
    private int getExchangeItemPosition(DragEvent event) {
        for (int i = 0 ; i < items.size() ; i ++){
            Rect rect = items.get(i);
            if (rect.contains((int)event.getX(),(int)event.getY())){
                return i;
            }
        }
        return -1;
    }

    /**
     * 在按下的一刻获取到所有item的位置信息
     */
    private void initItemRects() {
        for (int i = 0 ; i < getChildCount() ; i ++){
            View view = getChildAt(i);
            Rect rect = new Rect(view.getLeft(),view.getTop(),view.getRight(),view.getBottom());
            items.add(rect);
        }
    }


    /**
     * 长按点击拖拽
     */
    private OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            mDragItemView = v;
            v.startDrag(null, new View.DragShadowBuilder(v), null, 0);
            return true;
        }
    };
}


