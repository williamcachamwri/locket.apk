package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.p002firebaseauthapi.zzag;
import com.google.android.gms.internal.p002firebaseauthapi.zzahr;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class zze extends OAuthCredential {
    public static final Parcelable.Creator<zze> CREATOR = new zzd();
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final zzahr zzd;
    private final String zze;
    private final String zzf;
    private final String zzg;

    public final AuthCredential zza() {
        return new zze(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg);
    }

    public static zze zza(zzahr zzahr) {
        Preconditions.checkNotNull(zzahr, "Must specify a non-null webSignInCredential");
        return new zze((String) null, (String) null, (String) null, zzahr, (String) null, (String) null, (String) null);
    }

    public static zze zza(String str, String str2, String str3) {
        return zza(str, str2, str3, (String) null, (String) null);
    }

    public static zze zza(String str, String str2, String str3, String str4, String str5) {
        Preconditions.checkNotEmpty(str, "Must specify a non-empty providerId");
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            return new zze(str, str2, str3, (zzahr) null, str4, str5, (String) null);
        }
        throw new IllegalArgumentException("Must specify an idToken or an accessToken.");
    }

    static zze zza(String str, String str2, String str3, String str4) {
        Preconditions.checkNotEmpty(str, "Must specify a non-empty providerId");
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            return new zze(str, str2, str3, (zzahr) null, (String) null, (String) null, str4);
        }
        throw new IllegalArgumentException("Must specify an idToken or an accessToken.");
    }

    public static zzahr zza(zze zze2, String str) {
        Preconditions.checkNotNull(zze2);
        zzahr zzahr = zze2.zzd;
        if (zzahr != null) {
            return zzahr;
        }
        return new zzahr(zze2.getIdToken(), zze2.getAccessToken(), zze2.getProvider(), (String) null, zze2.getSecret(), (String) null, str, zze2.zze, zze2.zzg);
    }

    public String getAccessToken() {
        return this.zzc;
    }

    public String getIdToken() {
        return this.zzb;
    }

    public String getProvider() {
        return this.zza;
    }

    public String getSecret() {
        return this.zzf;
    }

    public String getSignInMethod() {
        return this.zza;
    }

    zze(String str, String str2, String str3, zzahr zzahr, String str4, String str5, String str6) {
        this.zza = zzag.zzb(str);
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = zzahr;
        this.zze = str4;
        this.zzf = str5;
        this.zzg = str6;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getProvider(), false);
        SafeParcelWriter.writeString(parcel, 2, getIdToken(), false);
        SafeParcelWriter.writeString(parcel, 3, getAccessToken(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zze, false);
        SafeParcelWriter.writeString(parcel, 6, getSecret(), false);
        SafeParcelWriter.writeString(parcel, 7, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
