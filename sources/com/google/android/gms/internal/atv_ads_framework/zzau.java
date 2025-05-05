package com.google.android.gms.internal.atv_ads_framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class zzau {
    /* access modifiers changed from: private */
    public final zzak zza;
    private final zzas zzb;

    private zzau(zzas zzas) {
        zzaj zzaj = zzaj.zza;
        this.zzb = zzas;
        this.zza = zzaj;
    }

    public static zzau zzb(char c) {
        return new zzau(new zzas(new zzah(AbstractJsonLexerKt.COLON)));
    }

    public final List zzc(CharSequence charSequence) {
        charSequence.getClass();
        zzar zzar = new zzar(this.zzb, this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (zzar.hasNext()) {
            arrayList.add((String) zzar.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
