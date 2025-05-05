package com.google.android.gms.internal.mlkit_common;

import java.util.Arrays;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public final class zzq {
    private final String zza;
    private final zzo zzb;
    private zzo zzc;

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.zza);
        sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
        zzo zzo = this.zzb.zzc;
        String str = "";
        while (zzo != null) {
            Object obj = zzo.zzb;
            sb.append(str);
            String str2 = zzo.zza;
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
            zzo = zzo.zzc;
            str = ", ";
        }
        sb.append(AbstractJsonLexerKt.END_OBJ);
        return sb.toString();
    }

    public final zzq zza(String str, @CheckForNull Object obj) {
        zzo zzo = new zzo();
        this.zzc.zzc = zzo;
        this.zzc = zzo;
        zzo.zzb = obj;
        String str2 = str;
        zzo.zza = str;
        return this;
    }

    public final zzq zzb(String str, boolean z) {
        String valueOf = String.valueOf(z);
        zzn zzn = new zzn((zzm) null);
        this.zzc.zzc = zzn;
        this.zzc = zzn;
        zzn.zzb = valueOf;
        String str2 = "isManifestFile";
        zzn.zza = "isManifestFile";
        return this;
    }

    /* synthetic */ zzq(String str, zzp zzp) {
        zzo zzo = new zzo();
        this.zzb = zzo;
        this.zzc = zzo;
        str.getClass();
        String str2 = str;
        this.zza = str;
    }
}
