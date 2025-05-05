package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.auth.AdditionalUserInfo;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.zze;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzx implements AuthResult {
    public static final Parcelable.Creator<zzx> CREATOR = new zzaa();
    private zzad zza;
    private zzv zzb;
    private zze zzc;

    public final int describeContents() {
        return 0;
    }

    public final AdditionalUserInfo getAdditionalUserInfo() {
        return this.zzb;
    }

    public final AuthCredential getCredential() {
        return this.zzc;
    }

    public final FirebaseUser getUser() {
        return this.zza;
    }

    public zzx(zzad zzad) {
        zzad zzad2 = (zzad) Preconditions.checkNotNull(zzad);
        this.zza = zzad2;
        List<zzz> zzj = zzad2.zzj();
        this.zzb = null;
        for (int i = 0; i < zzj.size(); i++) {
            if (!TextUtils.isEmpty(zzj.get(i).zza())) {
                this.zzb = new zzv(zzj.get(i).getProviderId(), zzj.get(i).zza(), zzad.zzk());
            }
        }
        if (this.zzb == null) {
            this.zzb = new zzv(zzad.zzk());
        }
        this.zzc = zzad.zzh();
    }

    zzx(zzad zzad, zzv zzv, zze zze) {
        this.zza = zzad;
        this.zzb = zzv;
        this.zzc = zze;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getUser(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getAdditionalUserInfo(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzc, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
