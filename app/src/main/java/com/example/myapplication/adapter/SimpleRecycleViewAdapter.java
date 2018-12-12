package com.example.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lixiaolong
 * @date 2018/9/5
 */
public class SimpleRecycleViewAdapter extends RecyclerArrayAdapter<String> {
    private List<String> mData = null;

    public SimpleRecycleViewAdapter(Context context) {
        super(context);
    }

    public void setData(List<String> data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, null, false));
    }


    class ViewHolder extends BaseViewHolder<String> {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(android.R.id.text1);
        }

        @Override
        public void setData(String data) {
            super.setData(data);
            textView.setText(data);
        }
    }
}
