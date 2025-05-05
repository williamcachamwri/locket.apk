package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzor extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzor> CREATOR = new zzos();
    public final int zza;
    private zzbp zzb = null;
    private byte[] zzc;

    zzor(int i, byte[] bArr) {
        this.zza = i;
        this.zzc = bArr;
        zzb();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i2);
        byte[] bArr = this.zzc;
        if (bArr == null) {
            bArr = this.zzb.zzav();
        }
        SafeParcelWriter.writeByteArray(parcel, 2, bArr, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzbp zza() {
        if (this.zzb == null) {
            try {
                this.zzb = zzbp.zzd(this.zzc, zzadk.zza());
                this.zzc = null;
            } catch (zzaeg | NullPointerException e) {
                throw new IllegalStateException(e);
            }
        }
        zzb();
        return this.zzb;
    }

    private final void zzb() {
        zzbp zzbp = this.zzb;
        if (zzbp == null && this.zzc != null) {
            return;
        }
        if (zzbp != null && this.zzc == null) {
            return;
        }
        if (zzbp != null && this.zzc != null) {
            throw new IllegalStateException("Invalid internal representation - full");
        } else if (zzbp == null && this.zzc == null) {
            throw new IllegalStateException("Invalid internal representation - empty");
        } else {
            throw new IllegalStateException("Impossible");
        }
    }
}
