package com.google.ads.interactivemedia.v3.internal;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzkk extends zzkx {
    private static volatile String zzh;
    private static final Object zzi = new Object();

    public zzkk(zzjj zzjj, String str, String str2, zzan zzan, int i, int i2) {
        super(zzjj, "cfPFolnFcyO2M4b7dfdBFR1LJKqZp4Fuk/UdR9bfuLBzuF+2QIdBkATGw9zmvT3l", "2ySKasqG9MJf+B86/j4Y0JFrwsiYz8yWF0K2o6c0cu0=", zzan, i, 1);
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        this.zzd.zzB(ExifInterface.LONGITUDE_EAST);
        if (zzh == null) {
            synchronized (zzi) {
                if (zzh == null) {
                    zzh = (String) this.zze.invoke((Object) null, new Object[0]);
                }
            }
        }
        synchronized (this.zzd) {
            this.zzd.zzB(zzh);
        }
    }
}
