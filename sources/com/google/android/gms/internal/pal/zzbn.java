package com.google.android.gms.internal.pal;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.CharEncoding;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzbn {
    static boolean zza = false;
    static final CountDownLatch zzb = new CountDownLatch(1);
    /* access modifiers changed from: private */
    public static MessageDigest zzc;
    private static final Object zzd = new Object();
    private static final Object zze = new Object();

    static String zza(zzaf zzaf, String str) throws GeneralSecurityException, UnsupportedEncodingException {
        byte[] bArr;
        byte[] zzas = zzaf.zzas();
        if (((Boolean) zzfv.zzc().zzb(zzgk.zzcw)).booleanValue()) {
            Vector zzb2 = zzb(zzas, 255);
            if (zzb2 == null || zzb2.size() == 0) {
                bArr = zzh(zzg(4096).zzas(), str, true);
            } else {
                zzat zza2 = zzau.zza();
                int size = zzb2.size();
                for (int i = 0; i < size; i++) {
                    zza2.zza(zzaby.zzn(zzh((byte[]) zzb2.get(i), str, false)));
                }
                zza2.zzb(zzaby.zzn(zzf(zzas)));
                bArr = ((zzau) zza2.zzan()).zzas();
            }
        } else if (zzdv.zza != null) {
            byte[] zza3 = zzdv.zza.zza(zzas, str != null ? str.getBytes() : new byte[0]);
            zzat zza4 = zzau.zza();
            zza4.zza(zzaby.zzn(zza3));
            zza4.zzc(3);
            bArr = ((zzau) zza4.zzan()).zzas();
        } else {
            throw new GeneralSecurityException();
        }
        return zzbj.zza(bArr, true);
    }

    static Vector zzb(byte[] bArr, int i) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return null;
        }
        int i2 = (length + 254) / 255;
        Vector vector = new Vector();
        int i3 = 0;
        while (i3 < i2) {
            int i4 = i3 * 255;
            try {
                int length2 = bArr.length;
                if (length2 - i4 > 255) {
                    length2 = i4 + 255;
                }
                vector.add(Arrays.copyOfRange(bArr, i4, length2));
                i3++;
            } catch (IndexOutOfBoundsException unused) {
                return null;
            }
        }
        return vector;
    }

    static void zzd() {
        synchronized (zze) {
            if (!zza) {
                zza = true;
                new Thread(new zzbm((zzbl) null)).start();
            }
        }
    }

    static byte[] zze(String str, String str2, boolean z) {
        byte[] bArr;
        zzai zza2 = zzaj.zza();
        try {
            if (str.length() < 3) {
                bArr = str.getBytes(CharEncoding.ISO_8859_1);
            } else {
                bArr = zzbj.zzb(str, true);
            }
            zza2.zzb(zzaby.zzn(bArr));
            zza2.zza(zzaby.zzn(str2.length() < 3 ? str2.getBytes(CharEncoding.ISO_8859_1) : zzbj.zzb(str2, true)));
            return ((zzaj) zza2.zzan()).zzas();
        } catch (UnsupportedEncodingException | GeneralSecurityException unused) {
            return null;
        }
    }

    public static byte[] zzf(byte[] bArr) throws NoSuchAlgorithmException {
        byte[] digest;
        synchronized (zzd) {
            zzd();
            MessageDigest messageDigest = null;
            try {
                if (zzb.await(2, TimeUnit.SECONDS)) {
                    MessageDigest messageDigest2 = zzc;
                    if (messageDigest2 != null) {
                        messageDigest = messageDigest2;
                    }
                }
            } catch (InterruptedException unused) {
            }
            if (messageDigest != null) {
                messageDigest.reset();
                messageDigest.update(bArr);
                digest = zzc.digest();
            } else {
                throw new NoSuchAlgorithmException("Cannot compute hash");
            }
        }
        return digest;
    }

    static zzaf zzg(int i) {
        zzr zza2 = zzaf.zza();
        zza2.zzD(4096);
        return (zzaf) zza2.zzan();
    }

    private static byte[] zzh(byte[] bArr, String str, boolean z) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] bArr2;
        int i = true != z ? 255 : 239;
        if (bArr.length > i) {
            bArr = zzg(4096).zzas();
        }
        int length = bArr.length;
        if (length < i) {
            byte[] bArr3 = new byte[(i - length)];
            new SecureRandom().nextBytes(bArr3);
            bArr2 = ByteBuffer.allocate(i + 1).put((byte) length).put(bArr).put(bArr3).array();
        } else {
            bArr2 = ByteBuffer.allocate(i + 1).put((byte) length).put(bArr).array();
        }
        if (z) {
            bArr2 = ByteBuffer.allocate(256).put(zzf(bArr2)).put(bArr2).array();
        }
        byte[] bArr4 = new byte[256];
        zzbo[] zzboArr = new zzcn().zzcG;
        int length2 = zzboArr.length;
        for (int i2 = 0; i2 < 12; i2++) {
            zzboArr[i2].zza(bArr2, bArr4);
        }
        if (str != null && str.length() > 0) {
            if (str.length() > 32) {
                str = str.substring(0, 32);
            }
            new zzabg(str.getBytes("UTF-8")).zza(bArr4);
        }
        return bArr4;
    }
}
