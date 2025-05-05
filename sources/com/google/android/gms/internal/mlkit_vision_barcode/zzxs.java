package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public final class zzxs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzxs> CREATOR = new zzyh();
    private final zzxw zza;
    private final String zzb;
    private final String zzc;
    private final zzxx[] zzd;
    private final zzxu[] zze;
    private final String[] zzf;
    private final zzxp[] zzg;

    public zzxs(zzxw zzxw, String str, String str2, zzxx[] zzxxArr, zzxu[] zzxuArr, String[] strArr, zzxp[] zzxpArr) {
        this.zza = zzxw;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = zzxxArr;
        this.zze = zzxuArr;
        this.zzf = strArr;
        this.zzg = zzxpArr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeTypedArray(parcel, 4, this.zzd, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.zze, i, false);
        SafeParcelWriter.writeStringArray(parcel, 6, this.zzf, false);
        SafeParcelWriter.writeTypedArray(parcel, 7, this.zzg, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzxw zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final zzxp[] zzd() {
        return this.zzg;
    }

    public final zzxu[] zze() {
        return this.zze;
    }

    public final zzxx[] zzf() {
        return this.zzd;
    }

    public final String[] zzg() {
        return this.zzf;
    }
}
