package com.google.android.tv.ads;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzd extends C$AutoValue_IconClickFallbackImage {
    public static final Parcelable.Creator<zzd> CREATOR = new zzc();

    zzd(int i, int i2, String str, String str2, String str3) {
        super(i, i2, str, str2, str3);
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getWidth());
        parcel.writeInt(getHeight());
        if (getAltText() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(getAltText());
        }
        if (getCreativeType() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(getCreativeType());
        }
        if (getStaticResourceUri() == null) {
            parcel.writeInt(1);
            return;
        }
        parcel.writeInt(0);
        parcel.writeString(getStaticResourceUri());
    }
}
