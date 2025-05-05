package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Objects;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzdp extends zzcu {
    final transient Object[] zza;

    private zzdp(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zza = objArr;
    }

    static zzdp zzg(int i, Object[] objArr, zzct zzct) {
        zzby.zzb(Objects.requireNonNull(objArr[0]), Objects.requireNonNull(objArr[1]));
        return new zzdp((Object) null, objArr, 1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x001d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e A[RETURN] */
    @javax.annotation.CheckForNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object get(@javax.annotation.CheckForNull java.lang.Object r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 != 0) goto L_0x0005
        L_0x0003:
            r4 = r0
            goto L_0x001b
        L_0x0005:
            java.lang.Object[] r1 = r3.zza
            r2 = 0
            r2 = r1[r2]
            java.lang.Object r2 = java.util.Objects.requireNonNull(r2)
            boolean r4 = r2.equals(r4)
            if (r4 == 0) goto L_0x0003
            r4 = 1
            r4 = r1[r4]
            java.lang.Object r4 = java.util.Objects.requireNonNull(r4)
        L_0x001b:
            if (r4 != 0) goto L_0x001e
            return r0
        L_0x001e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzdp.get(java.lang.Object):java.lang.Object");
    }

    public final int size() {
        return 1;
    }

    /* access modifiers changed from: package-private */
    public final zzcn zza() {
        return new zzdo(this.zza, 1, 1);
    }

    /* access modifiers changed from: package-private */
    public final zzcv zzd() {
        return new zzdm(this, this.zza, 0, 1);
    }

    /* access modifiers changed from: package-private */
    public final zzcv zze() {
        return new zzdn(this, new zzdo(this.zza, 0, 1));
    }
}
