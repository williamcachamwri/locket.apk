package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzkh extends zzkx {
    private final zzjb zzh;

    public zzkh(zzjj zzjj, String str, String str2, zzan zzan, int i, int i2, zzjb zzjb) {
        super(zzjj, "Fus2TIottASqUG+EGDCyGO+axdDK4SxdbOtAeYlmTQFyRNCoSHhhJulqJwIS8hGd", "ll+J41g6Bvm1JCdBcQ1AcuCOT9Ou/f0f9V5bVMwdM4A=", zzan, i, 94);
        this.zzh = zzjb;
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        int intValue = ((Integer) this.zze.invoke((Object) null, new Object[]{this.zzh.zza()})).intValue();
        synchronized (this.zzd) {
            this.zzd.zzae(zzbc.zza(intValue));
        }
    }
}
