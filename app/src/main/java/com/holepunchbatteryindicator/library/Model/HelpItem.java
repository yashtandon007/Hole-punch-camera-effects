package com.holepunchbatteryindicator.library.Model;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class HelpItem implements Parcelable {
    public static final Creator<HelpItem> CREATOR = new Creator<HelpItem>() {
        public HelpItem createFromParcel(Parcel parcel) {
            return new HelpItem(parcel);
        }

        public HelpItem[] newArray(int i) {
            return new HelpItem[i];
        }
    };
    private boolean check = true;
    private int counter = 0;
    private Drawable dIcon = null;
    private boolean header = false;
    private boolean hide = false;
    private int icon = 0;
    private String name;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getName());
        parcel.writeInt(getIcon());
        parcel.writeParcelable(((BitmapDrawable) getDrawableIcon()).getBitmap(), i);
        parcel.writeInt(getCounter());
        parcel.writeByte(isHide() ? (byte) 1 : 0);
        parcel.writeByte(isCheck() ? (byte) 1 : 0);
        parcel.writeByte(isHeader() ? (byte) 1 : 0);
    }

    public HelpItem() {
    }

    protected HelpItem(Parcel parcel) {
        boolean z = false;
        setName(parcel.readString());
        setIcon(parcel.readInt());
        setIcon((Drawable) new BitmapDrawable((Bitmap) parcel.readParcelable(getClass().getClassLoader())));
        setCounter(parcel.readInt());
        setHide(parcel.readByte() != 0);
        setCheck(parcel.readByte() != 0);
        setHeader(parcel.readByte() != 0 ? true : z);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getIcon() {
        return this.icon;
    }

    public void setIcon(int i) {
        this.icon = i;
    }

    public void setIcon(Drawable drawable) {
        this.dIcon = drawable;
    }

    public Drawable getDrawableIcon() {
        return this.dIcon;
    }

    public int getCounter() {
        return this.counter;
    }

    public void setCounter(int i) {
        this.counter = i;
    }

    public boolean isHide() {
        return this.hide;
    }

    public void setHide(boolean z) {
        this.hide = z;
    }

    public boolean isCheck() {
        return this.check;
    }

    public void setCheck(boolean z) {
        this.check = z;
    }

    public boolean isHeader() {
        return this.header;
    }

    public void setHeader(boolean z) {
        this.header = z;
    }
}
