package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.firebase.auth.zze;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzaa implements Parcelable.Creator<zzx> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzad zzad = null;
        zzv zzv = null;
        zze zze = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                zzad = (zzad) SafeParcelReader.createParcelable(parcel, readHeader, zzad.CREATOR);
            } else if (fieldId == 2) {
                zzv = (zzv) SafeParcelReader.createParcelable(parcel, readHeader, zzv.CREATOR);
            } else if (fieldId != 3) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                zze = (zze) SafeParcelReader.createParcelable(parcel, readHeader, zze.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzx(zzad, zzv, zze);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzx[i];
    }
}
