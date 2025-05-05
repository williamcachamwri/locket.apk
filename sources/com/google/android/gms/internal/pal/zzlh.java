package com.google.android.gms.internal.pal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzlh {
    public static final Charset zza = Charset.forName("UTF-8");

    public static zzwg zza(zzwb zzwb) {
        zzwd zza2 = zzwg.zza();
        zza2.zzb(zzwb.zzc());
        for (zzwa zzwa : zzwb.zzg()) {
            zzwe zza3 = zzwf.zza();
            zza3.zzb(zzwa.zzc().zzg());
            zza3.zzd(zzwa.zzi());
            zza3.zzc(zzwa.zzj());
            zza3.zza(zzwa.zza());
            zza2.zza((zzwf) zza3.zzan());
        }
        return (zzwg) zza2.zzan();
    }

    public static void zzb(zzwb zzwb) throws GeneralSecurityException {
        int zzc = zzwb.zzc();
        int i = 0;
        boolean z = false;
        boolean z2 = true;
        for (zzwa zzwa : zzwb.zzg()) {
            if (zzwa.zzi() == 3) {
                if (!zzwa.zzh()) {
                    throw new GeneralSecurityException(String.format("key %d has no key data", new Object[]{Integer.valueOf(zzwa.zza())}));
                } else if (zzwa.zzj() == 2) {
                    throw new GeneralSecurityException(String.format("key %d has unknown prefix", new Object[]{Integer.valueOf(zzwa.zza())}));
                } else if (zzwa.zzi() != 2) {
                    if (zzwa.zza() == zzc) {
                        if (!z) {
                            z = true;
                        } else {
                            throw new GeneralSecurityException("keyset contains multiple primary keys");
                        }
                    }
                    z2 &= zzwa.zzc().zzc() == zzvn.ASYMMETRIC_PUBLIC;
                    i++;
                } else {
                    throw new GeneralSecurityException(String.format("key %d has unknown status", new Object[]{Integer.valueOf(zzwa.zza())}));
                }
            }
        }
        if (i == 0) {
            throw new GeneralSecurityException("keyset must contain at least one ENABLED key");
        } else if (!z && !z2) {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
    }

    public static byte[] zzc(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }
}
