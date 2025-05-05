package com.google.android.gms.internal.fido;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import okhttp3.HttpUrl;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
public final class zzdi extends zzdr {
    private final zzaz zza;
    private final int zzb;

    zzdi(zzaz zzaz) throws zzdh {
        zzaz.getClass();
        this.zza = zzaz;
        int i = 0;
        int i2 = 0;
        while (true) {
            zzaz zzaz2 = this.zza;
            if (i >= zzaz2.size()) {
                break;
            }
            int zzb2 = ((zzdr) zzaz2.get(i)).zzb();
            if (i2 < zzb2) {
                i2 = zzb2;
            }
            i++;
        }
        int i3 = i2 + 1;
        this.zzb = i3;
        if (i3 > 4) {
            throw new zzdh("Exceeded cutoff limit for max depth of cbor value");
        }
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        zzdr zzdr = (zzdr) obj;
        if (zzd(Byte.MIN_VALUE) != zzdr.zza()) {
            return zzd(Byte.MIN_VALUE) - zzdr.zza();
        }
        zzdi zzdi = (zzdi) zzdr;
        zzaz zzaz = this.zza;
        int size = zzaz.size();
        zzaz zzaz2 = zzdi.zza;
        if (size != zzaz2.size()) {
            return zzaz.size() - zzaz2.size();
        }
        int i = 0;
        while (true) {
            zzaz zzaz3 = this.zza;
            if (i >= zzaz3.size()) {
                return 0;
            }
            int compareTo = ((zzdr) zzaz3.get(i)).compareTo((zzdr) zzdi.zza.get(i));
            if (compareTo != 0) {
                return compareTo;
            }
            i++;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return this.zza.equals(((zzdi) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(zzd(Byte.MIN_VALUE)), this.zza});
    }

    public final String toString() {
        if (this.zza.isEmpty()) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        ArrayList arrayList = new ArrayList();
        zzaz zzaz = this.zza;
        int size = zzaz.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(((zzdr) zzaz.get(i)).toString().replace("\n", "\n  "));
        }
        zzag zza2 = zzag.zza(",\n  ");
        StringBuilder sb = new StringBuilder("[\n  ");
        try {
            zza2.zzb(sb, arrayList.iterator());
            sb.append("\n]");
            return sb.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        return zzd(Byte.MIN_VALUE);
    }

    /* access modifiers changed from: protected */
    public final int zzb() {
        return this.zzb;
    }
}
