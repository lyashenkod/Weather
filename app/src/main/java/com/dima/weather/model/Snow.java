
package com.dima.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by Liashenko Dima on 08.04.2017.
 */
public class Snow implements Parcelable {

    @SerializedName("3h")
    @Expose
    public double _3h;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this._3h);
    }


    protected Snow(Parcel in) {
        this._3h = in.readDouble();
    }

    public static final Parcelable.Creator<Snow> CREATOR = new Parcelable.Creator<Snow>() {
        @Override
        public Snow createFromParcel(Parcel source) {
            return new Snow(source);
        }

        @Override
        public Snow[] newArray(int size) {
            return new Snow[size];
        }
    };
}
