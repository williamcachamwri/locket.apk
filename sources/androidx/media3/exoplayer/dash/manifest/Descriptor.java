package androidx.media3.exoplayer.dash.manifest;

import androidx.media3.common.util.Util;

public final class Descriptor {
    public final String id;
    public final String schemeIdUri;
    public final String value;

    public Descriptor(String str, String str2, String str3) {
        this.schemeIdUri = str;
        this.value = str2;
        this.id = str3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Descriptor descriptor = (Descriptor) obj;
        if (!Util.areEqual(this.schemeIdUri, descriptor.schemeIdUri) || !Util.areEqual(this.value, descriptor.value) || !Util.areEqual(this.id, descriptor.id)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = this.schemeIdUri.hashCode() * 31;
        String str = this.value;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.id;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }
}
