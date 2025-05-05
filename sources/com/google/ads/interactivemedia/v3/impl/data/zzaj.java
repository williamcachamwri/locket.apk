package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.zzrm;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaj extends zzcd {
    private final zzrm<zzcc> obstructions;

    private zzaj(zzrm<zzcc> zzrm) {
        this.obstructions = zzrm;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzcd) {
            return this.obstructions.equals(((zzcd) obj).obstructions());
        }
        return false;
    }

    public int hashCode() {
        return this.obstructions.hashCode() ^ 1000003;
    }

    /* access modifiers changed from: package-private */
    public zzrm<zzcc> obstructions() {
        return this.obstructions;
    }

    public String toString() {
        String valueOf = String.valueOf(this.obstructions);
        return "ObstructionListData{obstructions=" + valueOf + "}";
    }
}
