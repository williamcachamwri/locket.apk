package com.google.android.gms.internal.pal;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzev extends zzfg {
    public zzev(zzdu zzdu, String str, String str2, zzr zzr, int i, int i2) {
        super(zzdu, "WepZYnT/MXyJE28LKN26NT6D3mAA2J2spDFApE1ixrQxTNXRg7wshW7BC/EU90LT", "sjYkfzJTuYKxh1jvZaP9n5dx9JGmzJotOUC/vdvgi4M=", zzr, i, 73);
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        try {
            int i = 1;
            boolean booleanValue = ((Boolean) this.zzf.invoke((Object) null, new Object[]{this.zzb.zzb()})).booleanValue();
            zzr zzr = this.zze;
            if (true == booleanValue) {
                i = 2;
            }
            zzr.zzaf(i);
        } catch (InvocationTargetException unused) {
            this.zze.zzaf(3);
        }
    }
}
