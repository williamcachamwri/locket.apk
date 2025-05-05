package com.google.android.gms.internal.fido;

import java.util.Arrays;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
public final class zzam {
    private final String zza;
    private final zzak zzb;
    private zzak zzc;

    /* synthetic */ zzam(String str, zzal zzal) {
        zzak zzak = new zzak((zzaj) null);
        this.zzb = zzak;
        this.zzc = zzak;
        str.getClass();
        this.zza = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.zza);
        sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
        zzak zzak = this.zzb.zzc;
        String str = "";
        while (zzak != null) {
            Object obj = zzak.zzb;
            sb.append(str);
            String str2 = zzak.zza;
            if (str2 != null) {
                sb.append(str2);
                sb.append('=');
            }
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                String deepToString = Arrays.deepToString(new Object[]{obj});
                sb.append(deepToString, 1, deepToString.length() - 1);
            }
            zzak = zzak.zzc;
            str = ", ";
        }
        sb.append(AbstractJsonLexerKt.END_OBJ);
        return sb.toString();
    }

    public final zzam zza(String str, int i) {
        String valueOf = String.valueOf(i);
        zzai zzai = new zzai((zzah) null);
        this.zzc.zzc = zzai;
        this.zzc = zzai;
        zzai.zzb = valueOf;
        zzai.zza = "errorCode";
        return this;
    }

    public final zzam zzb(String str, @CheckForNull Object obj) {
        zzak zzak = new zzak((zzaj) null);
        this.zzc.zzc = zzak;
        this.zzc = zzak;
        zzak.zzb = obj;
        zzak.zza = str;
        return this;
    }
}
