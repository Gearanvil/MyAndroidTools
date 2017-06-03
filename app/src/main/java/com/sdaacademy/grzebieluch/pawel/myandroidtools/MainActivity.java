package com.sdaacademy.grzebieluch.pawel.myandroidtools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.github.abara.library.batterystats.BatteryStats;
import com.sdaacademy.grzebieluch.pawel.myandroidtools.fragments.BatteryFragment;
import com.sdaacademy.grzebieluch.pawel.myandroidtools.fragments.FlashlightFragment;
import com.sdaacademy.grzebieluch.pawel.myandroidtools.fragments.LocalizationFragment;
import com.sdaacademy.grzebieluch.pawel.myandroidtools.fragments.StopwatchFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final int TAB_BATTERY_POSITION = 0;
    private static final int TAB_FLASHLIGHT_POSITION = 1;
    private static final int TAB_STOPWATCH_POSITION = 2;
    private static final int TAB_LOCALIZATION_POSITION = 3;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    private BatteryFragment batteryFragment;
    private FlashlightFragment flashlightFragment;
    private StopwatchFragment stopwatchFragment;
    private LocalizationFragment localizationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        addFragments();
        pageChangeListener();


    }

    private void pageChangeListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void addFragments() {
        List<Fragment> fragments = new ArrayList<>();
        batteryFragment = new BatteryFragment();
        flashlightFragment = new FlashlightFragment();
        stopwatchFragment = new StopwatchFragment();
        localizationFragment = new LocalizationFragment();

        fragments.add(batteryFragment);
        fragments.add(flashlightFragment);
        fragments.add(stopwatchFragment);
        fragments.add(localizationFragment);
        MyPageAdapter adapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(TAB_BATTERY_POSITION)
                .setIcon(getDrawable(R.drawable.ic_battery_unknown_black_24dp));
        tabLayout.getTabAt(TAB_FLASHLIGHT_POSITION)
                .setIcon(getDrawable(R.drawable.ic_flash_on_black_24dp));
        tabLayout.getTabAt(TAB_STOPWATCH_POSITION)
                .setIcon(getDrawable(R.drawable.ic_watch_black_24dp));
        tabLayout.getTabAt(TAB_LOCALIZATION_POSITION)
                .setIcon(getDrawable(R.drawable.ic_gps_fixed_black_24dp));
    }


}