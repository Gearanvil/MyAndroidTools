package com.sdaacademy.grzebieluch.pawel.myandroidtools;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by RENT on 2017-05-30.
 */

public class Utils {
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
