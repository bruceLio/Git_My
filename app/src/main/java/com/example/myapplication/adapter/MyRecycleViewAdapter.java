package com.example.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * @author lixiaolong
 * @date 2019-05-17
 */
public class MyRecycleViewAdapter extends RecyclerArrayAdapter<MyRecycleViewAdapter.MyViedHolder> {


    public MyRecycleViewAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public class MyViedHolder extends RecyclerView.ViewHolder {

        public MyViedHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
