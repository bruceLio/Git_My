package com.example.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.util.ItemMoveAdapter;
import com.example.myapplication.util.L;

import java.util.Iterator;
import java.util.List;

/**
 * @author lixiaolong
 * @date 2018/11/13
 */
public class SimpleAdapter extends RecyclerView.Adapter implements ItemMoveAdapter {
    private final Context mContext;
    private final List<String> mData;
    private OnItemClick itemClickListener;

    public SimpleAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolder) {
            final String data = mData.get(i);
            ((ViewHolder) viewHolder).textView.setText(data);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onClick(data, viewHolder.itemView);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setItemClickListener(OnItemClick itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        notifyItemMoved(fromPosition, toPosition);
        move(mData, fromPosition, toPosition);
        return true;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }


    }

    public interface OnItemClick

    {
        void onClick(String str, View view);
    }

    /**
     * 增加数据
     */
    public void addData(int position, String s) {
        mData.add(position, s);
        notifyItemInserted(position);
    }

    /**
     * 移除数据
     */
    public boolean removeData(int position) {
        L.e("aa--" + mData.size() + position + "--a");
        if (position >= 0 && position < mData.size()) {
            mData.remove(position);
            notifyItemRemoved(position);
            return true;
        } else {
            return false;
        }
    }

    private void move(List list, int from, int to) {
        if (from < to) {
            list.add(to + 1, list.get(from));
            list.remove(from);
        } else {
            list.add(to, list.get(from));
            list.remove(from + 1);
        }
    }
}

