package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;
import com.google.android.gms.internal.p002firebaseauthapi.zzuo;
import java.io.IOException;
import java.io.OutputStream;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbi  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzbi implements zzce {
    private final OutputStream zza;

    public static zzce zza(OutputStream outputStream) {
        return new zzbi(outputStream);
    }

    private zzbi(OutputStream outputStream) {
        this.zza = outputStream;
    }

    public final void zza(zzuo zzuo) throws IOException {
        try {
            zzajy.zza zzn = zzuo.zzn();
            zzajy.zza zza2 = zzn;
            ((zzuo) ((zzajy) ((zzuo.zza) zzn).zza().zze())).zza(this.zza);
        } finally {
            this.zza.close();
        }
    }

    public final void zza(zzwa zzwa) throws IOException {
        try {
            zzwa.zza(this.zza);
        } finally {
            this.zza.close();
        }
    }
}
