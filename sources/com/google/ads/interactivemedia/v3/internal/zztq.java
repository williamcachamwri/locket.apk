package com.google.ads.interactivemedia.v3.internal;

import java.util.Objects;
import java.util.concurrent.Future;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
abstract class zztq extends zztw {
    @CheckForNull
    private zzri zza;

    static {
        new zzut(zztq.class);
    }

    zztq(zzri zzri, boolean z, boolean z2) {
        super(zzri.size());
        zzri.getClass();
        zzri zzri2 = zzri;
        this.zza = zzri;
    }

    private final void zzA(@CheckForNull zzri zzri) {
        int zzx = zzx();
        zzqh.zzi(zzx >= 0, "Less than 0 remaining futures");
        if (zzx == 0) {
            zzz();
            zzs();
            zzv(2);
        }
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public final String zza() {
        zzri zzri = this.zza;
        if (zzri != null) {
            return "futures=".concat(zzri.toString());
        }
        return super.zza();
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        zzri zzri = this.zza;
        boolean z = true;
        zzv(1);
        boolean isCancelled = isCancelled();
        if (zzri == null) {
            z = false;
        }
        if (z && isCancelled) {
            boolean zzr = zzr();
            zzss zze = zzri.iterator();
            while (zze.hasNext()) {
                ((Future) zze.next()).cancel(zzr);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public abstract void zzs();

    /* access modifiers changed from: package-private */
    public final void zzt() {
        Objects.requireNonNull(this.zza);
        if (this.zza.isEmpty()) {
            zzs();
            return;
        }
        zztp zztp = new zztp(this, (zzri) null);
        zzss zze = this.zza.iterator();
        while (zze.hasNext()) {
            zzuu zzuu = (zzuu) zze.next();
            if (zzuu.isDone()) {
                zzA((zzri) null);
            } else {
                zzuu.zzo(zztp, zzua.INSTANCE);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzu(zzri zzri) {
        zzA((zzri) null);
    }

    /* access modifiers changed from: package-private */
    public void zzv(int i) {
        this.zza = null;
    }
}
