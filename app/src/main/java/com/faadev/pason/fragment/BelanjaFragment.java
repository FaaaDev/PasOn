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
import com.faadev.pason.adapter.CategoryAdapter;
import com.faadev.pason.adapter.ItemAdapter;
import com.faadev.pason.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;


public class BelanjaFragment extends Fragment {

    private RecyclerView rv_category, rv_item;
    private LinearLayoutManager llm1, llm2;
    private ItemAdapter itemAdapter;
    private CategoryAdapter categoryAdapter;
    private List<CategoryModel> cmod;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_belanja, container, false);

        _init(root);
        _prep();

        return root;
    }

    private void _init(View root){
        rv_category = root.findViewById(R.id.rv_category);
        rv_item = root.findViewById(R.id.rv_item);
    }

    private void _prep(){
        cmod = new ArrayList<>();
        cmod.add(new CategoryModel("Semua Kategori", true));
        cmod.add(new CategoryModel("Sayuran", false));
        cmod.add(new CategoryModel("Daging", false));
        cmod.add(new CategoryModel("Ikan", false));
        cmod.add(new CategoryModel("Buah", false));
        cmod.add(new CategoryModel("Bumbu", false));
        cmod.add(new CategoryModel("Herbal", false));
        cmod.add(new CategoryModel("Jajanan", false));


        llm2 = new LinearLayoutManager(getContext());
        itemAdapter = new ItemAdapter(getContext());
        rv_item.setLayoutManager(llm2);
        rv_item.setAdapter(itemAdapter);
        llm1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoryAdapter = new CategoryAdapter(getContext(), cmod);
        rv_category.setLayoutManager(llm1);
        rv_category.setAdapter(categoryAdapter);
    }
}