package com.dima.weather.screen;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Liashenko Dima on 19.12.2017.
 */

public class ViewHelper {

    private ViewHelper() {
    }

    public static void setAssetImageFile(String fileName, ImageView imageView) {
        Picasso.with(imageView.getContext())
                .load("file:///android_asset/" + fileName)
                .noFade()
                .into(imageView);
    }
}
