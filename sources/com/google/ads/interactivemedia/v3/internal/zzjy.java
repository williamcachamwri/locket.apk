package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzjy extends zzkx {
    public zzjy(zzjj zzjj, String str, String str2, zzan zzan, int i, int i2) {
        super(zzjj, "9AapCvSXzV8coBAg7sVelaiXfAsx9AWmDDIfeprqYS1mc42o+3U7/Q/ITW6cj3Q0", "GaGK7jWkEusMCurSk2Iqvi/xAbfN6zA5X3MQPC18/40=", zzan, i, 5);
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        this.zzd.zzm(-1);
        this.zzd.zzl(-1);
        int[] iArr = (int[]) this.zze.invoke((Object) null, new Object[]{this.zza.zzb()});
        synchronized (this.zzd) {
            this.zzd.zzm((long) iArr[0]);
            this.zzd.zzl((long) iArr[1]);
            int i = iArr[2];
            if (i != Integer.MIN_VALUE) {
                this.zzd.zzk((long) i);
            }
        }
    }
}
