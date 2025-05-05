package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.zzrm;
import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzah extends zzca {
    private zzrm<zzcc> obstructions;

    zzah() {
    }

    public zzcd build() {
        zzrm<zzcc> zzrm = this.obstructions;
        if (zzrm != null) {
            return new zzaj(zzrm);
        }
        throw new IllegalStateException("Missing required properties: obstructions");
    }

    public zzca obstructions(List<zzcc> list) {
        this.obstructions = zzrm.zzk(list);
        return this;
    }
}
