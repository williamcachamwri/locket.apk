package com.google.android.gms.internal.atv_ads_framework;

import java.io.IOException;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class zzct extends IOException {
    zzct() {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }

    zzct(String str, Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(String.valueOf(str)), th);
    }

    zzct(Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
    }
}
