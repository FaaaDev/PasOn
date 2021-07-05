package com.faadev.pason.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.faadev.pason.R;
import com.faadev.pason.adapter.DismisListener;
import com.faadev.pason.adapter.ProductAdapter;

public class ProductFragment extends Fragment {

    private RecyclerView rv_product;
    private LinearLayoutManager llm;
    private ProductAdapter productAdapter;
    private LinearLayout btn_add;
    private DismisListener dismisListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_product, container, false);

        _init(root);
        _prep();

        return root;
    }

    private void _init(View root){
        rv_product = root.findViewById(R.id.rv_product);
        btn_add = root.findViewById(R.id.btn_add);
    }

    private void _prep(){
        productAdapter = new ProductAdapter(getContext());
        llm = new LinearLayoutManager(getContext());
        rv_product.setLayoutManager(llm);
        rv_product.setAdapter(productAdapter);

        dismisListener = new DismisListener() {
            @Override
            public void onDismisDialog() {
                System.out.println("DISMISED");
            }
        };

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFragment addFragment = new AddFragment();
                addFragment.setDismisListener(dismisListener);
                addFragment.show(getChildFragmentManager(), addFragment.getTag());
            }
        });
    }


}