package com.faadev.pason.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.faadev.pason.R;
import com.faadev.pason.adapter.TabAdapter;
import com.faadev.pason.fragment.BelanjaFragment;
import com.faadev.pason.fragment.OrderFragment;
import com.faadev.pason.fragment.PesananFragment;
import com.faadev.pason.fragment.ProductFragment;
import com.faadev.pason.fragment.ProfilFragment;
import com.google.android.material.tabs.TabLayout;

import biz.laenger.android.vpbs.BottomSheetUtils;
import biz.laenger.android.vpbs.ViewPagerBottomSheetBehavior;

public class ShopActivity extends AppCompatActivity {

    private TabLayout tab;
    private TabAdapter adapter;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                this.getWindow().setNavigationBarColor(Color.WHITE);
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
            }
        }

        _init();
        _prep();

    }

    private void _init(){
        tab = findViewById(R.id.tab);
        vp = findViewById(R.id.vp);
    }

    private void _prep(){
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new ProductFragment(), "Produk");
        adapter.addFragment(new OrderFragment(), "Pesanan");
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
        tab.setTabRippleColor(null);
    }
}