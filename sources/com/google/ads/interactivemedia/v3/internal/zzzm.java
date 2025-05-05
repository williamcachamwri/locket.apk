package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.lang.reflect.Field;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
abstract class zzzm {
    final String zzg;
    final Field zzh;
    final String zzi;

    protected zzzm(String str, Field field) {
        this.zzg = str;
        this.zzh = field;
        this.zzi = field.getName();
    }

    /* access modifiers changed from: package-private */
    public abstract void zza(zzacc zzacc, int i, Object[] objArr) throws IOException, zzwa;

    /* access modifiers changed from: package-private */
    public abstract void zzb(zzacc zzacc, Object obj) throws IOException, IllegalAccessException;

    /* access modifiers changed from: package-private */
    public abstract void zzc(zzace zzace, Object obj) throws IOException, IllegalAccessException;
}
