package com.sdaacademy.grzebieluch.pawel.myandroidtools.fragments;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sdaacademy.grzebieluch.pawel.myandroidtools.R;
import com.sdaacademy.grzebieluch.pawel.myandroidtools.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class VolumeFragment extends Fragment {
    private Context context;
    private AudioManager audioManager;
    private View view;

    public VolumeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_volume, container, false);
        ButterKnife.bind(this, view);
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        return view;
    }
    @OnClick(R.id.btn_mute)
    public void muteAudio() {
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_MUTE,AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
        Utils.showToast(getContext(),"Dzialam");
    }

    @OnClick(R.id.btn_volume_up)
    public void volumeUp(){
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_RAISE,AudioManager.FLAG_SHOW_UI);
    }
  @OnClick(R.id.btn_volume_down)
    public void volumeDown(){
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_LOWER,AudioManager.FLAG_SHOW_UI);
    }

}
