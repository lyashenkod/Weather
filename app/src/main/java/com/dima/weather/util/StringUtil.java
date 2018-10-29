package com.dima.weather.util;

import android.content.Context;

import java.text.SimpleDateFormat;

/**
 * Created by ishabaev on 26.06.16.
 */
public class StringUtil {

    public static SimpleDateFormat getDateFormat(Context context) {
        return new SimpleDateFormat("EEE, d MMM yyyy HH:mm", context.getResources().getConfiguration().locale);
    }

}
