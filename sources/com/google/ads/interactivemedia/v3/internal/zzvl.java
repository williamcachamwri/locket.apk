package com.google.ads.interactivemedia.v3.internal;

import com.squareup.kotlinpoet.FileSpecKt;
import java.util.Objects;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzvl {
    public static final zzvl zza = new zzvl("", "", false);
    private final String zzb;
    private final String zzc;
    private final boolean zzd;

    static {
        new zzvl("\n", FileSpecKt.DEFAULT_INDENT, true);
    }

    private zzvl(String str, String str2, boolean z) {
        Objects.requireNonNull(str, "newline == null");
        Objects.requireNonNull(str2, "indent == null");
        if (!str.matches("[\r\n]*")) {
            throw new IllegalArgumentException("Only combinations of \\n and \\r are allowed in newline.");
        } else if (str2.matches("[ \t]*")) {
            this.zzb = str;
            this.zzc = str2;
            this.zzd = z;
        } else {
            throw new IllegalArgumentException("Only combinations of spaces and tabs are allowed in indent.");
        }
    }

    public final String zza() {
        return this.zzc;
    }

    public final String zzb() {
        return this.zzb;
    }

    public final boolean zzc() {
        return this.zzd;
    }
}
