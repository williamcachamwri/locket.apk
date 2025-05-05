package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzvq;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzeo  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzeo implements zzbg {
    private static final byte[] zza = new byte[0];
    private static final Set<String> zzb;
    private final String zzc;
    private final zzch zzd;
    private final zzbg zze;

    public static zzbg zza(zzcw zzcw, zzbg zzbg) throws GeneralSecurityException {
        try {
            return new zzeo(zzvu.zza(zzcp.zza((zzch) zzcw), zzajk.zza()), zzbg);
        } catch (zzakf e) {
            throw new GeneralSecurityException(e);
        }
    }

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("type.googleapis.com/google.crypto.tink.AesGcmKey");
        hashSet.add("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key");
        hashSet.add("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key");
        hashSet.add("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey");
        hashSet.add("type.googleapis.com/google.crypto.tink.AesGcmSivKey");
        hashSet.add("type.googleapis.com/google.crypto.tink.AesEaxKey");
        zzb = Collections.unmodifiableSet(hashSet);
    }

    @Deprecated
    private zzeo(zzvu zzvu, zzbg zzbg) throws GeneralSecurityException {
        if (zzb.contains(zzvu.zzf())) {
            this.zzc = zzvu.zzf();
            this.zzd = zzcp.zza(((zzvu) ((zzajy) zzvu.zza(zzvu).zza(zzws.RAW).zze())).zzk());
            this.zze = zzbg;
            return;
        }
        throw new IllegalArgumentException("Unsupported DEK key type: " + zzvu.zzf() + ". Only Tink AEAD key types are supported.");
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            int i = wrap.getInt();
            if (i <= 0 || i > 4096 || i > bArr.length - 4) {
                throw new GeneralSecurityException("length of encrypted DEK too large");
            }
            byte[] bArr3 = new byte[i];
            wrap.get(bArr3, 0, i);
            byte[] bArr4 = new byte[wrap.remaining()];
            wrap.get(bArr4, 0, wrap.remaining());
            return ((zzbg) zzoc.zza().zza(zzof.zza().zza(zzpc.zza(this.zzc, zzaip.zza(this.zze.zza(bArr3, zza)), zzvq.zzb.SYMMETRIC, zzws.RAW, (Integer) null), zzbq.zza()), zzbg.class)).zza(bArr4, bArr2);
        } catch (IndexOutOfBoundsException | NegativeArraySizeException | BufferUnderflowException e) {
            throw new GeneralSecurityException("invalid ciphertext", e);
        }
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        zzbp zza2 = zznv.zza().zza(this.zzd, (Integer) null);
        byte[] zzb2 = this.zze.zzb(((zzpc) zzof.zza().zza(zza2, zzpc.class, zzbq.zza())).zzd().zzd(), zza);
        if (zzb2.length <= 4096) {
            byte[] zzb3 = ((zzbg) zzoc.zza().zza(zza2, zzbg.class)).zzb(bArr, bArr2);
            return ByteBuffer.allocate(zzb2.length + 4 + zzb3.length).putInt(zzb2.length).put(zzb2).put(zzb3).array();
        }
        throw new GeneralSecurityException("length of encrypted DEK too large");
    }
}
