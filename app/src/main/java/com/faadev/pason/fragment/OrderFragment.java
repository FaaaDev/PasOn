package com.faadev.pason.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.faadev.pason.R;
import com.faadev.pason.adapter.OrderAdapter;
import com.faadev.pason.adapter.PesananAdapter;


public class OrderFragment extends Fragment {

    private RecyclerView rv_onorder, rv_done;
    private LinearLayoutManager llm1, llm2;
    private OrderAdapter orderAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_order, container, false);

        _init(root);
        _prep();

        return root;
    }

    private void _init(View root){
        rv_onorder = root.findViewById(R.id.rv_onorder);
        rv_done = root.findViewById(R.id.rv_done);
    }

    private void _prep(){
        llm1 = new LinearLayoutManager(getContext());
        llm2 = new LinearLayoutManager(getContext());
        orderAdapter = new OrderAdapter(getContext());
        rv_onorder.setLayoutManager(llm1);
        rv_onorder.setAdapter(orderAdapter);
        rv_done.setLayoutManager(llm2);
        rv_done.setAdapter(orderAdapter);
    }
}