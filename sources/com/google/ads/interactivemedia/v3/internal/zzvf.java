package com.google.ads.interactivemedia.v3.internal;

import java.lang.Thread;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzvf {
    @CheckForNull
    private String zza = null;

    public final zzvf zza(String str) {
        String.format(Locale.ROOT, "imasdk-%d", new Object[]{0});
        this.zza = "imasdk-%d";
        return this;
    }

    public final ThreadFactory zzb() {
        String str = this.zza;
        return new zzve(Executors.defaultThreadFactory(), str, str != null ? new AtomicLong(0) : null, (Boolean) null, (Integer) null, (Thread.UncaughtExceptionHandler) null);
    }
}
