package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzwa;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbv  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzbv {
    private final List<zzby> zza = new ArrayList();
    private zzng zzb = zzng.zza;
    private boolean zzc = false;

    public final zzbv zza(zzby zzby) {
        if (zzby.zze == null) {
            if (zzby.zza) {
                zzb();
            }
            zzby.zze = this;
            this.zza.add(zzby);
            return this;
        }
        throw new IllegalStateException("Entry has already been added to a KeysetHandle.Builder");
    }

    public final zzbt zza() throws GeneralSecurityException {
        int i;
        if (!this.zzc) {
            this.zzc = true;
            zzwa.zza zzc2 = zzwa.zzc();
            ArrayList arrayList = new ArrayList(this.zza.size());
            List<zzby> list = this.zza;
            int i2 = 0;
            while (i2 < list.size() - 1) {
                if (list.get(i2).zzd != zzbx.zza || list.get(i2 + 1).zzd == zzbx.zza) {
                    i2++;
                } else {
                    throw new GeneralSecurityException("Entries with 'withRandomId()' may only be followed by other entries with 'withRandomId()'.");
                }
            }
            HashSet hashSet = new HashSet();
            Integer num = null;
            for (zzby next : this.zza) {
                if (next.zzb == null) {
                    throw new GeneralSecurityException("Key Status not set.");
                } else if (next.zzd != null) {
                    if (next.zzd == zzbx.zza) {
                        i = 0;
                        while (true) {
                            if (i != 0 && !hashSet.contains(Integer.valueOf(i))) {
                                break;
                            }
                            i = zzpr.zza();
                        }
                    } else {
                        i = zzbx.zza(next.zzd);
                    }
                    if (!hashSet.contains(Integer.valueOf(i))) {
                        hashSet.add(Integer.valueOf(i));
                        zzbp zza2 = zznv.zza().zza(next.zzc, next.zzc.zza() ? Integer.valueOf(i) : null);
                        zzca zzca = r10;
                        zzca zzca2 = new zzca(zza2, next.zzb, i, next.zza);
                        zzc2.zza(zzbt.zzb(zza2, next.zzb, i));
                        if (next.zza) {
                            if (num == null) {
                                num = Integer.valueOf(i);
                                if (next.zzb != zzbr.zza) {
                                    throw new GeneralSecurityException("Primary key is not enabled");
                                }
                            } else {
                                throw new GeneralSecurityException("Two primaries were set");
                            }
                        }
                        arrayList.add(zzca);
                    } else {
                        throw new GeneralSecurityException("Id " + i + " is used twice in the keyset");
                    }
                } else {
                    throw new GeneralSecurityException("No ID was set (with withFixedId or withRandomId)");
                }
            }
            if (num != null) {
                zzc2.zza(num.intValue());
                zzwa zzwa = (zzwa) ((zzajy) zzc2.zze());
                zzbt.zzd(zzwa);
                return new zzbt(zzwa, arrayList, this.zzb);
            }
            throw new GeneralSecurityException("No primary was set");
        }
        throw new GeneralSecurityException("KeysetHandle.Builder#build must only be called once");
    }

    /* access modifiers changed from: private */
    public final void zzb() {
        for (zzby zza2 : this.zza) {
            zza2.zza = false;
        }
    }
}
