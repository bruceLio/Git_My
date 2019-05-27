package com.example.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.bean.MusicTest;
import com.example.myapplication.util.L;
import com.example.myapplication.widge.MyMusicView;

import java.util.List;

/**
 * @author lixiaolong
 * @date 2019/4/26
 */
public class MusicTestAdapter extends RecyclerView.Adapter<MusicTestAdapter.mViewHolder> {
    private final Context context;
    private final List<MusicTest> data;

    public MusicTestAdapter(Context context, List<MusicTest> data) {
        this.context = context;
        this.data = data;
    }

    int count = 0;
    int bc = 0;

    @NonNull
    @Override
    public MusicTestAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        L.e(count++ + "onCreateViewHolder");
        return new mViewHolder(LayoutInflater.from(context).inflate(R.layout.item_music_test, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MusicTestAdapter.mViewHolder viewHolder, int i) {
        MusicTest musicTest = data.get(i);
        L.e(bc++ + "onBindViewHolder");
        if (musicTest.state == 0) {
            viewHolder.mv.setVisibility(View.INVISIBLE);
        } else {
            if (i == 0 && viewHolder.mv.getTag() == null) {
                viewHolder.mv.setTag(new Object());
            }
            viewHolder.mv.setVisibility(View.VISIBLE);
            if (musicTest.state == 1) {
                viewHolder.mv.setPlaying(true);
            } else {
                viewHolder.mv.setPlaying(false);

            }
        }
        viewHolder.tv.setText("position" + i);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class mViewHolder extends RecyclerView.ViewHolder {
        public MyMusicView mv;
        public TextView tv;


        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            mv = itemView.findViewById(R.id.mv);
            tv = itemView.findViewById(R.id.tv);
            mv.setPosition(getAdapterPosition() + "");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = getAdapterPosition();
                    reset(adapterPosition);
                }
            });
        }

    }

    private void reset(int position) {
        for (int i = 0; i < data.size(); i++) {
            if (position == i) {
                if (data.get(i).state == 1) {
                    data.get(i).state = 2;
                } else {
                    data.get(i).state = 1;
                }
            } else {
                data.get(i).state = 0;
            }
        }
        notifyDataSetChanged();
    }
}
