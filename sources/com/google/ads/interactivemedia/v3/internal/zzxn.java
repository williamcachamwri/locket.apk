package com.google.ads.interactivemedia.v3.internal;

import androidx.media3.extractor.ts.TsExtractor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzxn implements Cloneable, zzwk {
    public static final zzxn zza = new zzxn();
    private List zzb = Collections.emptyList();
    private final List zzc = Collections.emptyList();

    public final zzwj zza(zzvr zzvr, zzaca zzaca) {
        Class zzc2 = zzaca.zzc();
        boolean zzc3 = zzc(zzc2, true);
        boolean zzc4 = zzc(zzc2, false);
        if (zzc3 || zzc4) {
            return new zzxm(this, zzc4, zzc3, zzvr, zzaca);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzb */
    public final zzxn clone() {
        try {
            return (zzxn) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean zzc(Class cls, boolean z) {
        List<zzpt> list;
        if (z) {
            list = this.zzb;
        } else if (!Enum.class.isAssignableFrom(cls) && zzabp.zzj(cls)) {
            return true;
        } else {
            list = this.zzc;
        }
        for (zzpt zzpt : list) {
        }
        return false;
    }

    public final boolean zzd(Field field, boolean z) {
        List<zzpt> list;
        if ((field.getModifiers() & TsExtractor.TS_STREAM_TYPE_DTS_HD) != 0 || field.isSynthetic() || zzc(field.getType(), z)) {
            return true;
        }
        if (z) {
            list = this.zzb;
        } else {
            list = this.zzc;
        }
        if (list.isEmpty()) {
            return false;
        }
        Field field2 = (Field) Objects.requireNonNull(field);
        for (zzpt zzpt : list) {
            zzps zzps = (zzps) zzvk.zza(field2).getAnnotation(zzps.class);
            if (zzps != null && Arrays.asList(zzps.zzb()).contains(zzvk.zzb(field2))) {
                return true;
            }
        }
        return false;
    }

    public final zzxn zze(zzpt zzpt, boolean z, boolean z2) {
        zzxn zzb2 = clone();
        ArrayList arrayList = new ArrayList(this.zzb);
        zzb2.zzb = arrayList;
        arrayList.add(zzpt);
        return zzb2;
    }
}
