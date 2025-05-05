package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzbf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbf> CREATOR = new zzbi();
    public final String zza;
    public final zzbe zzb;
    public final String zzc;
    public final long zzd;

    public final String toString() {
        String str = this.zzc;
        String str2 = this.zza;
        return "origin=" + str + ",name=" + str2 + ",params=" + String.valueOf(this.zzb);
    }

    zzbf(zzbf zzbf, long j) {
        Preconditions.checkNotNull(zzbf);
        this.zza = zzbf.zza;
        this.zzb = zzbf.zzb;
        this.zzc = zzbf.zzc;
        this.zzd = j;
    }

    public zzbf(String str, zzbe zzbe, String str2, long j) {
        this.zza = str;
        this.zzb = zzbe;
        this.zzc = str2;
        this.zzd = j;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzb, i, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeLong(parcel, 5, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
