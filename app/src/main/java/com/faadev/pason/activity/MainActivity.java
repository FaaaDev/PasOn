package com.faadev.pason.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.faadev.pason.R;
import com.faadev.pason.adapter.TabAdapter;
import com.faadev.pason.adapter.ZoomOutPageTransformer;
import com.faadev.pason.fragment.BelanjaFragment;
import com.faadev.pason.fragment.PesananFragment;
import com.faadev.pason.fragment.ProfilFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;

import biz.laenger.android.vpbs.BottomSheetUtils;
import biz.laenger.android.vpbs.ViewPagerBottomSheetBehavior;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private TabAdapter adapter;
    private ViewPager vp;
    private ViewPagerBottomSheetBehavior bottomSheetBehavior;
    private LinearLayout bottom_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        bottom_home = findViewById(R.id.bottom_home);
        bottomSheetBehavior = ViewPagerBottomSheetBehavior.from(bottom_home);
    }

    private void _prep(){
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new BelanjaFragment(), "");
        adapter.addFragment(new PesananFragment(), "");
        adapter.addFragment(new ProfilFragment(), "");
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setIcon(R.drawable.ic_baseline_shopping_cart).setText("Belanja");
        tab.getTabAt(1).setIcon(R.drawable.ic_baseline_local_mall).setText("Pesanan");
        tab.getTabAt(2).setIcon(R.drawable.ic_baseline_face).setText("Profile");
        tab.setTabRippleColor(null);
        BottomSheetUtils.setupViewPager(vp);

        bottomSheetBehavior.setState(ViewPagerBottomSheetBehavior.STATE_EXPANDED);
    }
}