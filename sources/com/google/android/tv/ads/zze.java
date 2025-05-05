package com.google.android.tv.ads;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zze implements Parcelable.Creator {
    zze() {
    }

    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new zzf(parcel.readArrayList(IconClickFallbackImages.class.getClassLoader()));
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzf[i];
    }
}
