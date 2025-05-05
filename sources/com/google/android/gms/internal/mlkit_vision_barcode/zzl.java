package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public final class zzl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzl> CREATOR = new zzy();
    public zzp zza;
    public String zzb;
    public String zzc;
    public zzq[] zzd;
    public zzn[] zze;
    public String[] zzf;
    public zzi[] zzg;

    public zzl() {
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zza, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.zzd, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 6, this.zze, i, false);
        SafeParcelWriter.writeStringArray(parcel, 7, this.zzf, false);
        SafeParcelWriter.writeTypedArray(parcel, 8, this.zzg, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public zzl(zzp zzp, String str, String str2, zzq[] zzqArr, zzn[] zznArr, String[] strArr, zzi[] zziArr) {
        this.zza = zzp;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = zzqArr;
        this.zze = zznArr;
        this.zzf = strArr;
        this.zzg = zziArr;
    }
}
