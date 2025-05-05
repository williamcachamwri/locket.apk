package com.google.android.gms.internal.pal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CountDownLatch;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzbm implements Runnable {
    private zzbm() {
    }

    /* synthetic */ zzbm(zzbl zzbl) {
    }

    public final void run() {
        CountDownLatch countDownLatch;
        try {
            zzbn.zzc = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            countDownLatch = zzbn.zzb;
        } catch (NoSuchAlgorithmException unused) {
            countDownLatch = zzbn.zzb;
        } catch (Throwable th) {
            zzbn.zzb.countDown();
            throw th;
        }
        countDownLatch.countDown();
    }
}
