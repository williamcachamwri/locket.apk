package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
public final class zzv implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r1v7, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r11) {
        /*
            r10 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r11)
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            r1 = 0
            r3 = 0
            r4 = r1
            r5 = r4
            r6 = r5
            r7 = r6
        L_0x000f:
            int r1 = r11.dataPosition()
            if (r1 >= r0) goto L_0x0071
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r11)
            int r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            r9 = 1
            if (r8 == r9) goto L_0x0065
            r9 = 2
            if (r8 == r9) goto L_0x0054
            r9 = 3
            if (r8 == r9) goto L_0x0048
            r9 = 4
            if (r8 == r9) goto L_0x003c
            r9 = 5
            if (r8 == r9) goto L_0x0030
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r11, r1)
            goto L_0x000f
        L_0x0030:
            java.lang.String r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r11, r1)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r9)
            r2.add(r1)
            goto L_0x000f
        L_0x003c:
            java.lang.String r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r11, r1)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r9)
            r2.add(r1)
            goto L_0x000f
        L_0x0048:
            java.lang.String r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r11, r1)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r9)
            r2.add(r1)
            goto L_0x000f
        L_0x0054:
            android.os.Parcelable$Creator<com.google.android.gms.auth.api.accounttransfer.zzw> r4 = com.google.android.gms.auth.api.accounttransfer.zzw.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r11, r1, r4)
            r4 = r1
            com.google.android.gms.auth.api.accounttransfer.zzw r4 = (com.google.android.gms.auth.api.accounttransfer.zzw) r4
            java.lang.Integer r1 = java.lang.Integer.valueOf(r9)
            r2.add(r1)
            goto L_0x000f
        L_0x0065:
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r11, r1)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r9)
            r2.add(r1)
            goto L_0x000f
        L_0x0071:
            int r1 = r11.dataPosition()
            if (r1 != r0) goto L_0x007e
            com.google.android.gms.auth.api.accounttransfer.zzu r11 = new com.google.android.gms.auth.api.accounttransfer.zzu
            r1 = r11
            r1.<init>(r2, r3, r4, r5, r6, r7)
            return r11
        L_0x007e:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException r1 = new com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Overread allowed size end="
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0, r11)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.accounttransfer.zzv.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzu[i];
    }
}
