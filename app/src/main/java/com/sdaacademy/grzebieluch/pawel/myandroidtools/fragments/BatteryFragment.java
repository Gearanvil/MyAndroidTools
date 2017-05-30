package com.sdaacademy.grzebieluch.pawel.myandroidtools.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.abara.library.batterystats.BatteryStats;
import com.sdaacademy.grzebieluch.pawel.myandroidtools.MainActivity;
import com.sdaacademy.grzebieluch.pawel.myandroidtools.R;

import static android.provider.LiveFolders.INTENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class BatteryFragment extends Fragment {
    private int level = 0;
    private Context context;
    private TextView textView;
    private View view;

    public BatteryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_battery, container, false);
        textView = (TextView) view.findViewById(R.id.fragment_battery_lvl);
        batterySetup(textView);


        return view;
    }


//

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void batterySetup(TextView textView){
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = (level / (float)scale);

        textView.setText(String.valueOf(batteryPct));

    }




}
