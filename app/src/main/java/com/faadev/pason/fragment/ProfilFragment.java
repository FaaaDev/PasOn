package com.faadev.pason.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.faadev.pason.R;
import com.faadev.pason.activity.ShopActivity;

public class ProfilFragment extends Fragment {

    private LinearLayout set1, set2, set3, set4, set5, set6, set7;
    private Button open_shop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profil, container, false);

        _init(root);
        _prep();

        return root;
    }

    private void _init(View root){
        set1 = root.findViewById(R.id.set1);
        set2 = root.findViewById(R.id.set2);
        set3 = root.findViewById(R.id.set3);
        set4 = root.findViewById(R.id.set4);
        set5 = root.findViewById(R.id.set5);
        set6 = root.findViewById(R.id.set6);
        set7 = root.findViewById(R.id.set7);
        open_shop = root.findViewById(R.id.open_shop);
    }

    private void _prep(){
        open_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ShopActivity.class));
            }
        });

        set1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        set2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        set3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        set4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        set5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        set6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        set7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}