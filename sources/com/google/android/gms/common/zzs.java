package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import io.sentry.android.core.SentryLogcatAdapter;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public final class zzs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzs> CREATOR = new zzt();
    private final String zza;
    @Nullable
    private final zzj zzb;
    private final boolean zzc;
    private final boolean zzd;

    zzs(String str, @Nullable IBinder iBinder, boolean z, boolean z2) {
        byte[] bArr;
        this.zza = str;
        zzk zzk = null;
        if (iBinder != null) {
            try {
                IObjectWrapper zzd2 = zzz.zzg(iBinder).zzd();
                if (zzd2 == null) {
                    bArr = null;
                } else {
                    bArr = (byte[]) ObjectWrapper.unwrap(zzd2);
                }
                if (bArr != null) {
                    zzk = new zzk(bArr);
                } else {
                    SentryLogcatAdapter.e("GoogleCertificatesQuery", "Could not unwrap certificate");
                }
            } catch (RemoteException e) {
                SentryLogcatAdapter.e("GoogleCertificatesQuery", "Could not unwrap certificate", e);
            }
        }
        this.zzb = zzk;
        this.zzc = z;
        this.zzd = z2;
    }

    zzs(String str, @Nullable zzj zzj, boolean z, boolean z2) {
        this.zza = str;
        this.zzb = zzj;
        this.zzc = z;
        this.zzd = z2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        String str = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, str, false);
        zzj zzj = this.zzb;
        if (zzj == null) {
            SentryLogcatAdapter.w("GoogleCertificatesQuery", "certificate binder is null");
            zzj = null;
        }
        SafeParcelWriter.writeIBinder(parcel, 2, zzj, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzc);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
