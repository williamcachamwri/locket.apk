package com.google.android.recaptcha.internal;

import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.UInt;
import kotlin.collections.CollectionsKt;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzib implements zzhz {
    private final zzia zza;

    public zzib(zzia zzia, zzhy zzhy) {
        this.zza = zzia;
    }

    private final zzub zzb(String str, List list) throws zzcg {
        CharSequence charSequence = str;
        if (charSequence.length() != 0) {
            try {
                zzhx zzhx = new zzhx(this.zza.zza(CollectionsKt.toLongArray(list)), 255, zzhx.zza);
                StringBuilder sb = new StringBuilder(str.length());
                for (int i = 0; i < charSequence.length(); i++) {
                    sb.append((char) UInt.m2539constructorimpl(UInt.m2539constructorimpl(charSequence.charAt(i)) ^ UInt.m2539constructorimpl((int) zzhx.zza())));
                }
                return zzub.zzg(zzkj.zzh().zzj(sb.toString()));
            } catch (Exception e) {
                throw new zzcg(3, 18, e);
            }
        } else {
            throw new zzcg(3, 17, (Throwable) null);
        }
    }

    public final zzub zza(zzud zzud) throws zzcg {
        zzjj zzb = zzjj.zzb();
        zzub zzb2 = zzb(zzud.zzj(), zzud.zzk());
        zzb.zzf();
        long zza2 = zzb.zza(TimeUnit.MICROSECONDS);
        int i = zzbm.zza;
        zzbm.zza(zzbn.zzh.zza(), zza2);
        return zzb2;
    }
}
