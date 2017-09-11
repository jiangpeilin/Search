package com.hxl.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/11.
 */

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyHolder> implements View.OnClickListener {

    Context context;
    ArrayList<String> datas = new ArrayList<String>();
    private MyHolder holder;

    public Myadapter(Context context) {
        this.context = context;
    }
    private OnItemClickListener mOnItemClickListener = null;

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        holder = new MyHolder(v);
        v.setOnClickListener(this);
        return holder;
    }


    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(view ,(int)view.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.textView.setText(datas.get(position));
//        holder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                s = datas.get(position);
//                Toast.makeText(context, "内容" + s, Toast.LENGTH_SHORT).show();
//            }
//        });
        holder.itemView.setTag(position);
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void add(ArrayList<String> data) {
        datas.addAll(0,data);
        notifyDataSetChanged();
    }

    public void clear() {
        datas.clear();
        notifyDataSetChanged();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public MyHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview);
        }
    }

}
