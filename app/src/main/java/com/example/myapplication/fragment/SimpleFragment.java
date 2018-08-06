package com.example.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

public class SimpleFragment extends Fragment {
    private TextView tv;

    public static SimpleFragment getInstance(String text) {
        Bundle bundle = new Bundle();
        bundle.putString("value", text);
        SimpleFragment simpleFragment = new SimpleFragment();
        simpleFragment.setArguments(bundle);
        return simpleFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_simple, container, false);
        tv = view.findViewById(R.id.textView);
        if (getArguments() != null) {
            String value = getArguments().getString("value");
            tv.setText(value);
        }
        return view;
    }
}
