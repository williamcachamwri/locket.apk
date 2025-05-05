package com.google.android.gms.internal.pal;

import java.io.IOException;
import java.io.StringWriter;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public class zzyy {
    public final String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            zzabe zzabe = new zzabe(stringWriter);
            zzabe.zzj(true);
            zzaba.zzV.zzb(zzabe, this);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public int zza() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String zzd() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public final zzzb zzf() {
        if (this instanceof zzzb) {
            return (zzzb) this;
        }
        new StringBuilder("Not a JSON Object: ").append(this);
        throw new IllegalStateException("Not a JSON Object: ".concat(toString()));
    }
}
