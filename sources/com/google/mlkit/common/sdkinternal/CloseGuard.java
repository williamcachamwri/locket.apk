package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.internal.mlkit_common.zzmm;
import com.google.android.gms.internal.mlkit_common.zzmn;
import com.google.android.gms.internal.mlkit_common.zzmv;
import com.google.android.gms.internal.mlkit_common.zzmw;
import com.google.android.gms.internal.mlkit_common.zzsh;
import com.google.android.gms.internal.mlkit_common.zzsk;
import com.google.android.gms.internal.mlkit_common.zzss;
import com.google.mlkit.common.sdkinternal.Cleaner;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.Closeable;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public class CloseGuard implements Closeable {
    public static final int API_TRANSLATE = 1;
    private final AtomicBoolean zza = new AtomicBoolean();
    private final String zzb;
    private final Cleaner.Cleanable zzc;

    /* compiled from: com.google.mlkit:common@@18.11.0 */
    public static class Factory {
        private final Cleaner zza;

        public Factory(Cleaner cleaner) {
            this.zza = cleaner;
        }

        public CloseGuard create(Object obj, int i, Runnable runnable) {
            return new CloseGuard(obj, i, this.zza, runnable, zzss.zzb("common"));
        }
    }

    CloseGuard(Object obj, int i, Cleaner cleaner, Runnable runnable, zzsh zzsh) {
        this.zzb = obj.toString();
        this.zzc = cleaner.register(obj, new zze(this, i, zzsh, runnable));
    }

    public final void close() {
        this.zza.set(true);
        this.zzc.clean();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(int i, zzsh zzsh, Runnable runnable) {
        if (!this.zza.get()) {
            SentryLogcatAdapter.e("MlKitCloseGuard", String.format(Locale.ENGLISH, "%s has not been closed", new Object[]{this.zzb}));
            zzmw zzmw = new zzmw();
            zzmn zzmn = new zzmn();
            zzmn.zzb(zzmm.zzb(i));
            zzmw.zzh(zzmn.zzc());
            zzsh.zzd(zzsk.zzf(zzmw), zzmv.HANDLE_LEAKED);
        }
        runnable.run();
    }
}
