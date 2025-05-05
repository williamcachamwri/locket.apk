package com.google.android.gms.internal.fido;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.apache.commons.codec.CharEncoding;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
public final class zzde {
    static final Charset zza = Charset.forName(CharEncoding.US_ASCII);
    static final Charset zzb = Charset.forName("UTF-8");
    static final Charset zzc = Charset.forName(CharEncoding.ISO_8859_1);
    public static final byte[] zzd;
    public static final ByteBuffer zze;
    public static final zzdd zzf;

    static {
        byte[] bArr = new byte[0];
        zzd = bArr;
        zze = ByteBuffer.wrap(bArr);
        int i = zzdd.zza;
        zzdb zzdb = new zzdb(bArr, 0, 0, false, (zzda) null);
        try {
            zzdb.zza(0);
            zzf = zzdb;
        } catch (zzdf e) {
            throw new IllegalArgumentException(e);
        }
    }
}
