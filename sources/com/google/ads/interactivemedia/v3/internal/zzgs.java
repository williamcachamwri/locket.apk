package com.google.ads.interactivemedia.v3.internal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CountDownLatch;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzgs implements Runnable {
    private zzgs() {
        throw null;
    }

    /* synthetic */ zzgs(zzgr zzgr) {
    }

    public final void run() {
        CountDownLatch countDownLatch;
        try {
            zzgt.zzd = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            countDownLatch = zzgt.zzb;
        } catch (NoSuchAlgorithmException unused) {
            countDownLatch = zzgt.zzb;
        } catch (Throwable th) {
            zzgt.zzb.countDown();
            throw th;
        }
        countDownLatch.countDown();
    }
}
