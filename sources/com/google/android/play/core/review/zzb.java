package com.google.android.play.core.review;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.play:review@@2.0.1 */
final class zzb implements Parcelable.Creator {
    zzb() {
    }

    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new zza((PendingIntent) parcel.readParcelable(ReviewInfo.class.getClassLoader()), parcel.readInt() != 0);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new ReviewInfo[i];
    }
}
