package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzib;
import com.google.android.gms.internal.measurement.zzid;
import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
public abstract class zzib<MessageType extends zzib<MessageType, BuilderType>, BuilderType extends zzid<MessageType, BuilderType>> implements zzlc {
    protected int zza = 0;

    /* access modifiers changed from: package-private */
    public int zzby() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public int zza(zzlu zzlu) {
        int zzby = zzby();
        if (zzby != -1) {
            return zzby;
        }
        int zza2 = zzlu.zza(this);
        zzc(zza2);
        return zza2;
    }

    public final zzik zzbz() {
        try {
            zzit zzc = zzik.zzc(zzcb());
            zza(zzc.zzb());
            return zzc.zza();
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a ByteString threw an IOException (should never happen).", e);
        }
    }

    protected static <T> void zza(Iterable<T> iterable, List<? super T> list) {
        zzid.zza(iterable, list);
    }

    /* access modifiers changed from: package-private */
    public void zzc(int i) {
        throw new UnsupportedOperationException();
    }

    public final byte[] zzca() {
        try {
            byte[] bArr = new byte[zzcb()];
            zzjc zzb = zzjc.zzb(bArr);
            zza(zzb);
            zzb.zzb();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a byte array threw an IOException (should never happen).", e);
        }
    }
}
