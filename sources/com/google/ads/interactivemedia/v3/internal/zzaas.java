package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.BitSet;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaas extends zzwj {
    zzaas() {
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        BitSet bitSet = new BitSet();
        zzacc.zzi();
        int zzr = zzacc.zzr();
        int i = 0;
        while (zzr != 2) {
            int i2 = zzr - 1;
            if (i2 == 5 || i2 == 6) {
                int zzb = zzacc.zzb();
                if (zzb == 0) {
                    continue;
                    i++;
                    zzr = zzacc.zzr();
                } else if (zzb != 1) {
                    throw new zzwe("Invalid bitset value " + zzb + ", expected 0 or 1; at path " + zzacc.zzf());
                }
            } else if (i2 != 7) {
                throw new zzwe("Invalid bitset value type: " + zzacd.zza(zzr) + "; at path " + zzacc.zze());
            } else if (!zzacc.zzq()) {
                i++;
                zzr = zzacc.zzr();
            }
            bitSet.set(i);
            i++;
            zzr = zzacc.zzr();
        }
        zzacc.zzk();
        return bitSet;
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        BitSet bitSet = (BitSet) obj;
        zzace.zzb();
        int length = bitSet.length();
        for (int i = 0; i < length; i++) {
            zzace.zzi(bitSet.get(i) ? 1 : 0);
        }
        zzace.zzd();
    }
}
