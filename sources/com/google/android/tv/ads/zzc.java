package com.google.android.tv.ads;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzc implements Parcelable.Creator {
    zzc() {
    }

    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new zzd(parcel.readInt(), parcel.readInt(), parcel.readInt() == 0 ? parcel.readString() : null, parcel.readInt() == 0 ? parcel.readString() : null, parcel.readInt() == 0 ? parcel.readString() : null);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzd[i];
    }
}
