package com.example.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.bean.Channel;
import com.example.myapplication.util.ItemMoveAdapter;
import com.example.myapplication.util.L;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lixiaolong
 * @date 2019/2/1
 */
public class ChannelAdapter extends RecyclerView.Adapter implements ItemMoveAdapter {
    private static final int ITEM = 1;
    private static final int LAB = 2;
    private final Context mContext;
    private final List<Object> mData;
    private List<Channel> topData = new ArrayList<>();
    private List<Channel> bottomData = new ArrayList<>();

    public int getmPosition() {
        return mPosition;
    }

    private int mPosition = -1;

    public void setFixPosition(int mPosition) {
        this.mPosition = mPosition;
    }


    public ChannelAdapter(Context context, List<Channel> data, List<Channel> bottomData) {
        this.mContext = context;
        this.topData = data;
        this.bottomData = bottomData;
        mData = new ArrayList<>();
        mData.add(new Object());
        mData.addAll(topData);
        mData.add(new Object());
        mData.addAll(bottomData);
    }

    public int getMidPosition() {
        return topData.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position) instanceof Channel) {
            return ITEM;
        } else {
            return LAB;
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int i) {
                    if (getItemViewType(i) == LAB) {
                        return ((GridLayoutManager) layoutManager).getSpanCount();

                    }
                    return 1;
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == ITEM) {
            return new SimpleAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, viewGroup, false));
        } else {
            return new LabViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_lab, viewGroup, false));
        }


    }

    long lastClick = 0L;

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {

        if (viewHolder instanceof LabViewHolder) {

        } else {
            final int position = viewHolder.getAdapterPosition();
            final Channel data = (Channel) mData.get(position);
            ((SimpleAdapter.ViewHolder) viewHolder).textView.setText(data.name);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (position <= mPosition) {
                        return;
                    }
                    if (System.currentTimeMillis() - lastClick < 200) {
                        return;
                    }
                    lastClick = System.currentTimeMillis();
                    L.e("start");
                    onItemMove(mData.indexOf(data), getMidPosition());
                    if (topData.contains(data)) {
                        topData.remove(data);
                        bottomData.add(0, data);
                    } else if (bottomData.contains(data)) {
                        topData.add(topData.size() - 1 >= 0 ? topData.size() - 1 : 0, data);
                        bottomData.remove(data);
                    }
                    L.e("end");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
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

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        notifyItemMoved(fromPosition, toPosition);
        move(mData, fromPosition, toPosition);
        notifyItemRangeChanged(fromPosition < toPosition ? fromPosition : toPosition, Math.abs(fromPosition - toPosition) + 1);
        return false;
    }

    public static class LabViewHolder extends RecyclerView.ViewHolder {

        public LabViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
