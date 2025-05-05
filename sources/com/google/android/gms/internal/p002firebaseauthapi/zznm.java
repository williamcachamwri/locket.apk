package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznm  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zznm {
    @Nullable
    private ArrayList<zznp> zza = new ArrayList<>();
    private zzng zzb = zzng.zza;
    @Nullable
    private Integer zzc = null;

    public final zznm zza(zzbr zzbr, int i, String str, String str2) {
        ArrayList<zznp> arrayList = this.zza;
        if (arrayList != null) {
            arrayList.add(new zznp(zzbr, i, str, str2));
            return this;
        }
        throw new IllegalStateException("addEntry cannot be called after build()");
    }

    public final zznm zza(zzng zzng) {
        if (this.zza != null) {
            this.zzb = zzng;
            return this;
        }
        throw new IllegalStateException("setAnnotations cannot be called after build()");
    }

    public final zznm zza(int i) {
        if (this.zza != null) {
            this.zzc = Integer.valueOf(i);
            return this;
        }
        throw new IllegalStateException("setPrimaryKeyId cannot be called after build()");
    }

    public final zznn zza() throws GeneralSecurityException {
        if (this.zza != null) {
            Integer num = this.zzc;
            if (num != null) {
                int intValue = num.intValue();
                ArrayList<zznp> arrayList = this.zza;
                ArrayList arrayList2 = arrayList;
                int size = arrayList.size();
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i >= size) {
                        break;
                    }
                    zznp zznp = arrayList.get(i);
                    i++;
                    if (zznp.zza() == intValue) {
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    throw new GeneralSecurityException("primary key ID is not present in entries");
                }
            }
            zznn zznn = new zznn(this.zzb, Collections.unmodifiableList(this.zza), this.zzc);
            this.zza = null;
            return zznn;
        }
        throw new IllegalStateException("cannot call build() twice");
    }
}
