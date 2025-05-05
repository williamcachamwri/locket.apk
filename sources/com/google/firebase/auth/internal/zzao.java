package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.MultiFactorSession;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.TotpMultiFactorInfo;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzao extends MultiFactorSession {
    public static final Parcelable.Creator<zzao> CREATOR = new zzan();
    private String zza;
    private String zzb;
    private List<PhoneMultiFactorInfo> zzc;
    private List<TotpMultiFactorInfo> zzd;
    private zzad zze;

    public final zzad zza() {
        return this.zze;
    }

    public static zzao zza(String str, zzad zzad) {
        Preconditions.checkNotEmpty(str);
        zzao zzao = new zzao();
        zzao.zza = str;
        zzao.zze = zzad;
        return zzao;
    }

    public static zzao zza(List<MultiFactorInfo> list, String str) {
        Preconditions.checkNotNull(list);
        Preconditions.checkNotEmpty(str);
        zzao zzao = new zzao();
        zzao.zzc = new ArrayList();
        zzao.zzd = new ArrayList();
        for (MultiFactorInfo next : list) {
            if (next instanceof PhoneMultiFactorInfo) {
                zzao.zzc.add((PhoneMultiFactorInfo) next);
            } else if (next instanceof TotpMultiFactorInfo) {
                zzao.zzd.add((TotpMultiFactorInfo) next);
            } else {
                throw new IllegalArgumentException("MultiFactorInfo must be either PhoneMultiFactorInfo or TotpMultiFactorInfo. The factorId of this MultiFactorInfo: " + next.getFactorId());
            }
        }
        zzao.zzb = str;
        return zzao;
    }

    public final String zzb() {
        return this.zza;
    }

    public final String zzc() {
        return this.zzb;
    }

    private zzao() {
    }

    zzao(String str, String str2, List<PhoneMultiFactorInfo> list, List<TotpMultiFactorInfo> list2, zzad zzad) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = list;
        this.zzd = list2;
        this.zze = zzad;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeTypedList(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zze, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean zzd() {
        return this.zza != null;
    }
}
