package com.sdaacademy.grzebieluch.pawel.myandroidtools.fragments;


import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdaacademy.grzebieluch.pawel.myandroidtools.R;
import com.sdaacademy.grzebieluch.pawel.myandroidtools.Utils;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlashlightFragment extends Fragment {
    private View view;
    private Context context;

    private Camera camera;
    private Camera.Parameters params;
    private boolean isFlashOn;

    public FlashlightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_flash_light, container, false);

        turnOnFlash();
        return view;
    }

    public boolean isFlashlightAvilable() {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    private void turnOnFlash() {
        if (!isFlashOn) {
            if (camera == null || params == null) {
                return;
            }
            params = camera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview();
            isFlashOn = true;
        }
    }

    private void turnOffFlash() {
        if (isFlashOn) {
            if (camera == null || params == null) {
                return;
            }
            params = camera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            camera.stopPreview();
            isFlashOn = false;
        }
    }
}


