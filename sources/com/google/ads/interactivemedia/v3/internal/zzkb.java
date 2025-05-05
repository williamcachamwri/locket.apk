package com.google.ads.interactivemedia.v3.internal;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.UUID;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzkb extends zzkx {
    public zzkb(zzjj zzjj, String str, String str2, zzan zzan, int i, int i2) {
        super(zzjj, "CgPRYuzQrSKB4HHU/qweoT6whjRKh5s88SYFeVTlix/HzZdKOZnoIu1auPhHwMiw", "UcPRGM0BZSE4Gd9/Us196LnIBiXWDE9D3TOlCfboVSQ=", zzan, i, 24);
    }

    private final void zzc() {
        AdvertisingIdClient zzh = this.zza.zzh();
        if (zzh != null) {
            try {
                AdvertisingIdClient.Info info = zzh.getInfo();
                String id = info.getId();
                int i = zzjm.zza;
                if (id != null && id.matches("^[a-fA-F0-9]{8}-([a-fA-F0-9]{4}-){3}[a-fA-F0-9]{12}$")) {
                    UUID fromString = UUID.fromString(id);
                    byte[] bArr = new byte[16];
                    ByteBuffer wrap = ByteBuffer.wrap(bArr);
                    wrap.putLong(fromString.getMostSignificantBits());
                    wrap.putLong(fromString.getLeastSignificantBits());
                    id = zzgl.zza(bArr, true);
                }
                if (id != null) {
                    synchronized (this.zzd) {
                        this.zzd.zzs(id);
                        this.zzd.zzr(info.isLimitAdTrackingEnabled());
                        this.zzd.zzab(6);
                    }
                }
            } catch (IOException unused) {
            }
        }
    }

    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        zze();
        return null;
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        if (!this.zza.zzq()) {
            synchronized (this.zzd) {
                this.zzd.zzs((String) this.zze.invoke((Object) null, new Object[]{this.zza.zzb()}));
            }
            return;
        }
        zzc();
    }

    public final Void zze() throws Exception {
        if (this.zza.zzr()) {
            super.zze();
            return null;
        }
        if (this.zza.zzq()) {
            zzc();
        }
        return null;
    }
}
