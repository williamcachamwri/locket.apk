package com.google.android.recaptcha.internal;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzkz extends zzla {
    final /* synthetic */ zzlg zza;
    private int zzb = 0;
    private final int zzc;

    zzkz(zzlg zzlg) {
        this.zza = zzlg;
        this.zzc = zzlg.zzd();
    }

    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    public final byte zza() {
        int i = this.zzb;
        if (i < this.zzc) {
            this.zzb = i + 1;
            return this.zza.zzb(i);
        }
        throw new NoSuchElementException();
    }
}
