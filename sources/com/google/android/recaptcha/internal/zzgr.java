package com.google.android.recaptcha.internal;

import java.util.Collection;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.Charsets;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzgr implements zzgz {
    public static final zzgr zza = new zzgr();

    private zzgr() {
    }

    public final void zza(int i, zzgf zzgf, zzug... zzugArr) throws zzcg {
        String str;
        String str2;
        zzug[] zzugArr2 = zzugArr;
        if (zzugArr2.length == 1) {
            Object zza2 = zzgf.zzc().zza(zzugArr2[0]);
            if (true != (zza2 instanceof Object)) {
                zza2 = null;
            }
            if (zza2 != null) {
                if (zza2 instanceof int[]) {
                    str = ArraysKt.joinToString$default((int[]) zza2, (CharSequence) ",", (CharSequence) "[", (CharSequence) "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
                } else {
                    if (zza2 instanceof byte[]) {
                        str2 = new String((byte[]) zza2, Charsets.UTF_8);
                    } else if (zza2 instanceof long[]) {
                        str = ArraysKt.joinToString$default((long[]) zza2, (CharSequence) ",", (CharSequence) "[", (CharSequence) "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
                    } else if (zza2 instanceof short[]) {
                        str = ArraysKt.joinToString$default((short[]) zza2, (CharSequence) ",", (CharSequence) "[", (CharSequence) "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
                    } else if (zza2 instanceof float[]) {
                        str = ArraysKt.joinToString$default((float[]) zza2, (CharSequence) ",", (CharSequence) "[", (CharSequence) "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
                    } else if (zza2 instanceof double[]) {
                        str = ArraysKt.joinToString$default((double[]) zza2, (CharSequence) ",", (CharSequence) "[", (CharSequence) "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
                    } else if (zza2 instanceof char[]) {
                        str2 = new String((char[]) zza2);
                    } else if (zza2 instanceof Object[]) {
                        str = ArraysKt.joinToString$default((Object[]) zza2, (CharSequence) ",", (CharSequence) "[", (CharSequence) "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
                    } else if (zza2 instanceof Collection) {
                        str = CollectionsKt.joinToString$default((Iterable) zza2, ",", "[", "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
                    } else {
                        throw new zzcg(4, 5, (Throwable) null);
                    }
                    str = str2;
                }
                zzgf.zzc().zze(i, str);
                return;
            }
            throw new zzcg(4, 5, (Throwable) null);
        }
        throw new zzcg(4, 3, (Throwable) null);
    }
}
