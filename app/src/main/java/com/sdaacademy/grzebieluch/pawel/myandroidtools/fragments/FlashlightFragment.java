package com.sdaacademy.grzebieluch.pawel.myandroidtools.fragments;


import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sdaacademy.grzebieluch.pawel.myandroidtools.R;
import com.sdaacademy.grzebieluch.pawel.myandroidtools.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlashlightFragment extends Fragment {
    private View view;
    private Context context;
    private boolean toggle;
    private Camera camera;
    private Camera.Parameters params;
    private boolean isFlashOn;

    @BindView(R.id.relativeFlashlight)
    RelativeLayout relativeLayout;

    public FlashlightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_flash_light, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public boolean isFlashlightAvilable() {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    public void turnOnFlash() {
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

    public void turnOffFlash() {
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
    @TargetApi(Build.VERSION_CODES.M)
    private void flashForMAndAboveVersion() {
        toggle = !toggle;
        try {
            CameraManager cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
            for (String id : cameraManager.getCameraIdList()) {
                if (cameraManager.getCameraCharacteristics(id).get(CameraCharacteristics.FLASH_INFO_AVAILABLE)) {
                    cameraManager.setTorchMode(id, toggle);
                    if (toggle) {
                        relativeLayout.setBackgroundResource(android.R.color.holo_blue_bright);
                    } else {
                        relativeLayout.setBackgroundResource(android.R.color.black);
                    }
                }
            }
        } catch (Exception e2) {
            Log.d("TORCH", String.valueOf(e2));
        }
    }
    @OnClick(R.id.toogleButtonFlashlight)
    public void flash() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flashForMAndAboveVersion();
        } else {
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
                Toast.makeText(context, "We need your permission to flash", Toast.LENGTH_SHORT).show();
            } else {
                if (!isFlashOn)
                    turnOnFlash();
                else
                    turnOffFlash();
            }
        }
    }
}


