package com.example.george.androidutils.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.george.androidutils.App;
import com.example.george.androidutils.R;

/**
 * Created By George
 * Description:
 * 自定义toast
 */
public final class ToastUtils {
    private static Toast toast;

    public static void showShortToast(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        initToast(text, Toast.LENGTH_SHORT);
    }

    public static void showLongToast(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        initToast(text, Toast.LENGTH_LONG);
    }

    public static void showShortToast(int resId) {
        initToast(resId, Toast.LENGTH_SHORT);
    }

    public static void showLongToast(int resId) {
        initToast(resId, Toast.LENGTH_LONG);
    }

    @SuppressLint("ShowToast")
    private static void initToast(int resId, int duration) {

        if (toast == null) {
            if (App.app == null)
                return;
            toast = Toast.makeText(App.app, resId, duration);
        } else {
            toast.setDuration(duration);
            toast.setText(resId);
        }
        toast.show();
    }

    @SuppressLint("ShowToast")
    private static void initToast(String content, int duration) {
        if (toast == null) {
            if (App.app == null)
                return;
            toast = Toast.makeText(App.app, content, duration);
        } else {
            toast.setDuration(duration);
            toast.setText(content);
        }
        toast.show();
    }

    //=================自定义toast位置=========

    /**
     * 默认居中
     *
     * @param xOffset 相对居中的偏移量x
     * @param yOffset 相对居中的偏移量y
     */
    public static void showLocationToast(int xOffset, int yOffset) {
        Toast toast = Toast.makeText(App.app, "自定义位置Toast", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, xOffset, yOffset);
        toast.show();
    }

    //=================完全自定义toast=========
    public static void showCustom(Activity activity) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_layout, null);
        ImageView image = layout.findViewById(R.id.tvImageToast);
        image.setImageResource(R.mipmap.ic_launcher);
        TextView title = layout.findViewById(R.id.tvTitleToast);
        title.setText("Attention");
        TextView text = layout.findViewById(R.id.tvTextToast);
        text.setText("完全自定义Toast");
        Toast toast = new Toast(App.app);
        toast.setGravity(Gravity.RIGHT | Gravity.TOP, 12, 40);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }


}
