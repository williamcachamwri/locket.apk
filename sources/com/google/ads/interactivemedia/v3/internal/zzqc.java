package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzqc {
    private final String zza;
    private final zzqa zzb;
    private zzqa zzc;

    private final zzqa zzc() {
        zzqa zzqa = new zzqa();
        this.zzc.zzc = zzqa;
        this.zzc = zzqa;
        return zzqa;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.zza);
        sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
        zzqa zzqa = this.zzb.zzc;
        String str = "";
        while (zzqa != null) {
            Object obj = zzqa.zzb;
            sb.append(str);
            String str2 = zzqa.zza;
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
            zzqa = zzqa.zzc;
            str = ", ";
        }
        sb.append(AbstractJsonLexerKt.END_OBJ);
        return sb.toString();
    }

    public final zzqc zza(String str, @CheckForNull Object obj) {
        zzqa zzc2 = zzc();
        zzc2.zzb = obj;
        String str2 = "consentKeyTypes";
        zzc2.zza = "consentKeyTypes";
        return this;
    }

    public final zzqc zzb(@CheckForNull Object obj) {
        zzc().zzb = obj;
        return this;
    }

    /* synthetic */ zzqc(String str, zzqb zzqb) {
        zzqa zzqa = new zzqa();
        this.zzb = zzqa;
        this.zzc = zzqa;
        str.getClass();
        String str2 = str;
        this.zza = str;
    }
}
