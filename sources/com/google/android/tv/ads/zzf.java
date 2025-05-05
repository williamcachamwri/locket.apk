package com.google.android.tv.ads;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzf extends C$AutoValue_IconClickFallbackImages {
    public static final Parcelable.Creator<zzf> CREATOR = new zze();

    zzf(List list) {
        super(list);
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(getIconClickFallbackImageList());
    }
}
