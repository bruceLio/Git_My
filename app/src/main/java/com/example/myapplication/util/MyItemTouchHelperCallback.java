package com.example.myapplication.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.myapplication.adapter.ChannelAdapter;

/**
 * @author lixiaolong
 * @date 2018/12/14
 */
public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private final ChannelAdapter adapter;

    public MyItemTouchHelperCallback(ChannelAdapter mAdapter) {
        this.adapter = mAdapter;
        fixedCount = adapter.getmPosition() + 1;
    }

    private int fixedCount = 3 + 1;

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.getAdapterPosition() < fixedCount || viewHolder instanceof ChannelAdapter.LabViewHolder || viewHolder.getAdapterPosition() > adapter.getMidPosition())
            return 0;
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, 0);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder source, @NonNull RecyclerView.ViewHolder target) {
        if (source.getAdapterPosition() < fixedCount || target.getAdapterPosition() < fixedCount || target.getAdapterPosition() > adapter.getMidPosition() || target instanceof ChannelAdapter.LabViewHolder)
            return false;
        return adapter.onItemMove(source.getAdapterPosition(), target.getAdapterPosition());
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }


}
