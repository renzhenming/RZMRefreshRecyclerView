package com.ren.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/14.
 */
public class RecyclerAdapter extends RecyclerView.Adapter {
    private final ArrayList<String> mList;
    private final MainActivity context;

    public RecyclerAdapter(MainActivity context, ArrayList<String> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        viewHolder.mItem.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView mItem;

        public ViewHolder(View itemView) {
            super(itemView);
            mItem = (TextView) itemView.findViewById(R.id.list_item);
        }
    }
}
