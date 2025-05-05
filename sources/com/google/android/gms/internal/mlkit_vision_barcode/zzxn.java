package com.google.android.gms.internal.mlkit_vision_barcode;

import android.graphics.Point;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public abstract class zzxn {
    public static zzxn zzg(Iterable iterable, int i, int i2, float f) {
        Iterator it = iterable.iterator();
        int i3 = 0;
        int i4 = i;
        int i5 = i2;
        int i6 = 0;
        while (it.hasNext()) {
            Point point = (Point) it.next();
            i4 = Math.min(i4, point.x);
            i5 = Math.min(i5, point.y);
            i3 = Math.max(i3, point.x);
            i6 = Math.max(i6, point.y);
        }
        float f2 = (float) i;
        float f3 = (float) i2;
        return new zzxg((((float) i4) + 0.0f) / f2, (((float) i5) + 0.0f) / f3, (((float) i3) + 0.0f) / f2, (((float) i6) + 0.0f) / f3, 0.0f);
    }

    /* access modifiers changed from: package-private */
    public abstract float zza();

    /* access modifiers changed from: package-private */
    public abstract float zzb();

    /* access modifiers changed from: package-private */
    public abstract float zzc();

    /* access modifiers changed from: package-private */
    public abstract float zzd();

    /* access modifiers changed from: package-private */
    public abstract float zze();

    /* access modifiers changed from: package-private */
    public final float zzf() {
        if (zzh()) {
            return (zzb() - zzc()) * (zzd() - zze());
        }
        return 0.0f;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzh() {
        return zzc() >= 0.0f && zzc() < zzb() && zzb() <= 1.0f && zze() >= 0.0f && zze() < zzd() && zzd() <= 1.0f;
    }
}
