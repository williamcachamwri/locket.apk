package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzjw extends zzkx {
    private static final zzky zzh = new zzky();
    private final Context zzi;

    public zzjw(zzjj zzjj, String str, String str2, zzan zzan, int i, int i2, Context context) {
        super(zzjj, "NJ8FetXo0KyOsBrkOEKFojsJK8HUQrgQf5Lc3FXu4MGl5bYhJ/tvrJgkMmXasbAM", "s/eU2URRuCeWH32bRw//Xeb2p1pW8UEiL/Xy3irJSyY=", zzan, i, 29);
        this.zzi = context;
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        this.zzd.zzn(ExifInterface.LONGITUDE_EAST);
        AtomicReference zza = zzh.zza(this.zzi.getPackageName());
        if (zza.get() == null) {
            synchronized (zza) {
                if (zza.get() == null) {
                    zza.set((String) this.zze.invoke((Object) null, new Object[]{this.zzi}));
                }
            }
        }
        String str = (String) zza.get();
        synchronized (this.zzd) {
            this.zzd.zzn(zzgl.zza(str.getBytes(), true));
        }
    }
}
