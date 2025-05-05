package com.google.ads.interactivemedia.v3.internal;

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

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzgt {
    static boolean zza = false;
    static final CountDownLatch zzb = new CountDownLatch(1);
    public static final /* synthetic */ int zzc = 0;
    /* access modifiers changed from: private */
    public static MessageDigest zzd;
    private static final Object zze = new Object();
    private static final Object zzf = new Object();

    static String zza(byte[] bArr, String str) throws GeneralSecurityException, UnsupportedEncodingException {
        byte[] bArr2;
        Vector zzb2 = zzb(bArr, 255);
        if (zzb2 == null || zzb2.isEmpty()) {
            bArr2 = zzg(zzf(4096).zzav(), str, true);
        } else {
            zzcg zza2 = zzch.zza();
            int size = zzb2.size();
            for (int i = 0; i < size; i++) {
                zza2.zza(zzacw.zzp(zzg((byte[]) zzb2.get(i), str, false), 0, 256));
            }
            byte[] zze2 = zze(bArr);
            zzacw zzacw = zzacw.zzb;
            zza2.zzb(zzacw.zzp(zze2, 0, zze2.length));
            bArr2 = ((zzch) zza2.zzal()).zzav();
        }
        return zzgl.zza(bArr2, true);
    }

    static Vector zzb(byte[] bArr, int i) {
        int length = bArr.length;
        if (length <= 0) {
            return null;
        }
        int i2 = length + 254;
        Vector vector = new Vector();
        int i3 = 0;
        while (i3 < i2 / 255) {
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
        synchronized (zzf) {
            if (!zza) {
                zza = true;
                new Thread(new zzgs((zzgr) null)).start();
            }
        }
    }

    public static byte[] zze(byte[] bArr) throws NoSuchAlgorithmException {
        byte[] digest;
        synchronized (zze) {
            zzd();
            MessageDigest messageDigest = null;
            try {
                if (zzb.await(2, TimeUnit.SECONDS)) {
                    MessageDigest messageDigest2 = zzd;
                    if (messageDigest2 != null) {
                        messageDigest = messageDigest2;
                    }
                }
            } catch (InterruptedException unused) {
            }
            if (messageDigest != null) {
                messageDigest.reset();
                messageDigest.update(bArr);
                digest = zzd.digest();
            } else {
                throw new NoSuchAlgorithmException("Cannot compute hash");
            }
        }
        return digest;
    }

    static zzbp zzf(int i) {
        zzan zza2 = zzbp.zza();
        zza2.zzD(4096);
        return (zzbp) zza2.zzal();
    }

    private static byte[] zzg(byte[] bArr, String str, boolean z) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] bArr2;
        int length = bArr.length;
        int i = true != z ? 255 : 239;
        if (length > i) {
            bArr = zzf(4096).zzav();
        }
        int i2 = i + 1;
        int length2 = bArr.length;
        byte b = (byte) length2;
        if (length2 < i) {
            byte[] bArr3 = new byte[(i - length2)];
            new SecureRandom().nextBytes(bArr3);
            bArr2 = ByteBuffer.allocate(i2).put(b).put(bArr).put(bArr3).array();
        } else {
            bArr2 = ByteBuffer.allocate(i2).put(b).put(bArr).array();
        }
        if (z) {
            bArr2 = ByteBuffer.allocate(256).put(zze(bArr2)).put(bArr2).array();
        }
        byte[] bArr4 = new byte[256];
        zzgu[] zzguArr = new zzht().zzcG;
        int length3 = zzguArr.length;
        for (int i3 = 0; i3 < 12; i3++) {
            zzguArr[i3].zza(bArr2, bArr4);
        }
        if (str != null && str.length() > 0) {
            if (str.length() > 32) {
                str = str.substring(0, 32);
            }
            new zzgj(str.getBytes("UTF-8")).zza(bArr4);
        }
        return bArr4;
    }
}
