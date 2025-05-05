package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.internal.zzkp;
import com.google.android.recaptcha.internal.zzkq;
import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public abstract class zzkq<MessageType extends zzkq<MessageType, BuilderType>, BuilderType extends zzkp<MessageType, BuilderType>> implements zzok {
    protected int zza = 0;

    protected static void zzc(Iterable iterable, List list) {
        zzkp.zzd(iterable, list);
    }

    /* access modifiers changed from: package-private */
    public int zza(zzoy zzoy) {
        throw null;
    }

    public final zzlg zzb() {
        try {
            int zzo = zzo();
            zzlg zzlg = zzlg.zzb;
            byte[] bArr = new byte[zzo];
            zzlm zzlm = new zzlm(bArr, 0, zzo);
            zze(zzlm);
            zzlm.zzC();
            return new zzle(bArr);
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a ByteString threw an IOException (should never happen).", e);
        }
    }

    public final byte[] zzd() {
        try {
            int zzo = zzo();
            byte[] bArr = new byte[zzo];
            zzlm zzlm = new zzlm(bArr, 0, zzo);
            zze(zzlm);
            zzlm.zzC();
            return bArr;
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a byte array threw an IOException (should never happen).", e);
        }
    }
}
