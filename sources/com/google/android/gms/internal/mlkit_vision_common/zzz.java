package com.google.android.gms.internal.mlkit_vision_common;

import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
final class zzz extends zzr {
    static final zzr zza = new zzz((Object) null, new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    private zzz(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    static zzz zzg(int i, Object[] objArr, zzq zzq) {
        Object obj = objArr[0];
        obj.getClass();
        Object obj2 = objArr[1];
        obj2.getClass();
        zzi.zza(obj, obj2);
        return new zzz((Object) null, objArr, 1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0020 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001f A[RETURN] */
    @javax.annotation.CheckForNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object get(@javax.annotation.CheckForNull java.lang.Object r5) {
        /*
            r4 = this;
            java.lang.Object[] r0 = r4.zzb
            int r1 = r4.zzc
            r2 = 0
            if (r5 != 0) goto L_0x0009
        L_0x0007:
            r5 = r2
            goto L_0x001d
        L_0x0009:
            r3 = 1
            if (r1 != r3) goto L_0x0007
            r1 = 0
            r1 = r0[r1]
            r1.getClass()
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L_0x0007
            r5 = r0[r3]
            r5.getClass()
        L_0x001d:
            if (r5 != 0) goto L_0x0020
            return r2
        L_0x0020:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_common.zzz.get(java.lang.Object):java.lang.Object");
    }

    public final int size() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final zzl zza() {
        return new zzy(this.zzb, 1, this.zzc);
    }

    /* access modifiers changed from: package-private */
    public final zzs zzd() {
        return new zzw(this, this.zzb, 0, this.zzc);
    }

    /* access modifiers changed from: package-private */
    public final zzs zze() {
        return new zzx(this, new zzy(this.zzb, 0, this.zzc));
    }
}
