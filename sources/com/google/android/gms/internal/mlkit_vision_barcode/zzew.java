package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.concurrent.RunnableFuture;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzew extends zzef implements RunnableFuture {
    @CheckForNull
    private volatile zzer zzc;

    zzew(zzxh zzxh) {
        this.zzc = new zzev(this, zzxh);
    }

    public final void run() {
        zzer zzer = this.zzc;
        if (zzer != null) {
            zzer.run();
        }
        this.zzc = null;
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public final String zzf() {
        zzer zzer = this.zzc;
        if (zzer == null) {
            return super.zzf();
        }
        String obj = zzer.toString();
        return "task=[" + obj + "]";
    }

    /* access modifiers changed from: protected */
    public final void zzm() {
        zzer zzer;
        if (zzp() && (zzer = this.zzc) != null) {
            zzer.zze();
        }
        this.zzc = null;
    }
}
