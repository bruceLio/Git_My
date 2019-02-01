package com.example.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.bean.Channel;
import com.example.myapplication.util.ItemMoveAdapter;

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

    public ChannelAdapter(Context context, List<Object> data) {
        this.mContext = context;
        this.mData = data;
    }

    private int getMidPosition() {
        return topData.size();
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
                        return 3;

                    }
                    return 1;
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        return new SimpleAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, viewGroup, false));

    }

    long lastClick = 0L;

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {


        final int position = viewHolder.getAdapterPosition();
        final Channel data = mData.get(position);
        ((SimpleAdapter.ViewHolder) viewHolder).textView.setText(data);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (System.currentTimeMillis() - lastClick < 300) {
                    return;
                }
                lastClick = System.currentTimeMillis();
                int p = getMidPosition();
                if (position < getMidPosition()) {
                    topData.remove(data);
                    bottomData.add(0, data);
                } else if (position > getMidPosition()) {
                    topData.add(topData.size() - 1 >= 0 ? topData.size() - 1 : 0, data);
                    bottomData.remove(data);
                }
                onItemMove(viewHolder.getAdapterPosition(), p);

            }
        });
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
        notifyItemRangeChanged(0, mData.size());
        return false;
    }
    class LabViewHolder extends RecyclerView.ViewHolder{

        public LabViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
