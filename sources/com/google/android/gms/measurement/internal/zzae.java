package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzae extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzae> CREATOR = new zzad();
    public String zza;
    public String zzb;
    public zzon zzc;
    public long zzd;
    public boolean zze;
    public String zzf;
    public zzbf zzg;
    public long zzh;
    public zzbf zzi;
    public long zzj;
    public zzbf zzk;

    zzae(zzae zzae) {
        Preconditions.checkNotNull(zzae);
        this.zza = zzae.zza;
        this.zzb = zzae.zzb;
        this.zzc = zzae.zzc;
        this.zzd = zzae.zzd;
        this.zze = zzae.zze;
        this.zzf = zzae.zzf;
        this.zzg = zzae.zzg;
        this.zzh = zzae.zzh;
        this.zzi = zzae.zzi;
        this.zzj = zzae.zzj;
        this.zzk = zzae.zzk;
    }

    zzae(String str, String str2, zzon zzon, long j, boolean z, String str3, zzbf zzbf, long j2, zzbf zzbf2, long j3, zzbf zzbf3) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzon;
        this.zzd = j;
        this.zze = z;
        this.zzf = str3;
        this.zzg = zzbf;
        this.zzh = j2;
        this.zzi = zzbf2;
        this.zzj = j3;
        this.zzk = zzbf3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzc, i, false);
        SafeParcelWriter.writeLong(parcel, 5, this.zzd);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zze);
        SafeParcelWriter.writeString(parcel, 7, this.zzf, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzg, i, false);
        SafeParcelWriter.writeLong(parcel, 9, this.zzh);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzi, i, false);
        SafeParcelWriter.writeLong(parcel, 11, this.zzj);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzk, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
