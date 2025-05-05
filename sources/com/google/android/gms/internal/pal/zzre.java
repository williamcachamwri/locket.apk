package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzre {
    @Nullable
    private ArrayList zza = new ArrayList();
    private zzrb zzb = zzrb.zza;
    @Nullable
    private Integer zzc = null;

    public final zzre zza(zzkj zzkj, int i, zzks zzks) {
        ArrayList arrayList = this.zza;
        if (arrayList != null) {
            arrayList.add(new zzrg(zzkj, i, zzks, (zzrf) null));
            return this;
        }
        throw new IllegalStateException("addEntry cannot be called after build()");
    }

    public final zzre zzb(zzrb zzrb) {
        if (this.zza != null) {
            this.zzb = zzrb;
            return this;
        }
        throw new IllegalStateException("setAnnotations cannot be called after build()");
    }

    public final zzre zzc(int i) {
        if (this.zza != null) {
            this.zzc = Integer.valueOf(i);
            return this;
        }
        throw new IllegalStateException("setPrimaryKeyId cannot be called after build()");
    }

    public final zzri zzd() throws GeneralSecurityException {
        if (this.zza != null) {
            Integer num = this.zzc;
            if (num != null) {
                int intValue = num.intValue();
                ArrayList arrayList = this.zza;
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    int i2 = i + 1;
                    if (((zzrg) arrayList.get(i)).zza() != intValue) {
                        i = i2;
                    }
                }
                throw new GeneralSecurityException("primary key ID is not present in entries");
            }
            zzri zzri = new zzri(this.zzb, Collections.unmodifiableList(this.zza), this.zzc, (zzrh) null);
            this.zza = null;
            return zzri;
        }
        throw new IllegalStateException("cannot call build() twice");
    }
}
