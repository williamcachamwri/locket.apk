package com.google.firebase.auth;

import android.os.Parcelable;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzas implements Parcelable.Creator<TotpMultiFactorInfo> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r11) {
        /*
            r10 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r11)
            r1 = 0
            r2 = 0
            r5 = r1
            r6 = r5
            r9 = r6
            r7 = r2
        L_0x000b:
            int r1 = r11.dataPosition()
            if (r1 >= r0) goto L_0x0042
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r11)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            r3 = 1
            if (r2 == r3) goto L_0x003d
            r3 = 2
            if (r2 == r3) goto L_0x0038
            r3 = 3
            if (r2 == r3) goto L_0x0033
            r3 = 4
            if (r2 == r3) goto L_0x0029
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r11, r1)
            goto L_0x000b
        L_0x0029:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase-auth-api.zzahp> r2 = com.google.android.gms.internal.p002firebaseauthapi.zzahp.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r11, r1, r2)
            r9 = r1
            com.google.android.gms.internal.firebase-auth-api.zzahp r9 = (com.google.android.gms.internal.p002firebaseauthapi.zzahp) r9
            goto L_0x000b
        L_0x0033:
            long r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r11, r1)
            goto L_0x000b
        L_0x0038:
            java.lang.String r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r11, r1)
            goto L_0x000b
        L_0x003d:
            java.lang.String r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r11, r1)
            goto L_0x000b
        L_0x0042:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r11, r0)
            com.google.firebase.auth.TotpMultiFactorInfo r11 = new com.google.firebase.auth.TotpMultiFactorInfo
            r4 = r11
            r4.<init>(r5, r6, r7, r9)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.zzas.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new TotpMultiFactorInfo[i];
    }
}
