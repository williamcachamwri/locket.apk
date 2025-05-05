package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.Map;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzms extends zzmr {
    zzms() {
    }

    /* access modifiers changed from: package-private */
    public final void zza(Object obj) {
        ((zznc) obj).zzb.zzg();
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzqa zzqa, Map.Entry entry) throws IOException {
        zznd zznd = (zznd) entry.getKey();
        zzpy zzpy = zzpy.DOUBLE;
        switch (zznd.zzb.ordinal()) {
            case 0:
                zzqa.zzf(zznd.zza, ((Double) entry.getValue()).doubleValue());
                return;
            case 1:
                zzqa.zzo(zznd.zza, ((Float) entry.getValue()).floatValue());
                return;
            case 2:
                zzqa.zzt(zznd.zza, ((Long) entry.getValue()).longValue());
                return;
            case 3:
                zzqa.zzK(zznd.zza, ((Long) entry.getValue()).longValue());
                return;
            case 4:
                zzqa.zzr(zznd.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 5:
                zzqa.zzm(zznd.zza, ((Long) entry.getValue()).longValue());
                return;
            case 6:
                zzqa.zzk(zznd.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 7:
                zzqa.zzb(zznd.zza, ((Boolean) entry.getValue()).booleanValue());
                return;
            case 8:
                zzqa.zzG(zznd.zza, (String) entry.getValue());
                return;
            case 9:
                zzqa.zzq(zznd.zza, entry.getValue(), zzou.zza().zzb(entry.getValue().getClass()));
                return;
            case 10:
                zzqa.zzv(zznd.zza, entry.getValue(), zzou.zza().zzb(entry.getValue().getClass()));
                return;
            case 11:
                zzqa.zzd(zznd.zza, (zzlg) entry.getValue());
                return;
            case 12:
                zzqa.zzI(zznd.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 13:
                zzqa.zzr(zznd.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 14:
                zzqa.zzx(zznd.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 15:
                zzqa.zzz(zznd.zza, ((Long) entry.getValue()).longValue());
                return;
            case 16:
                zzqa.zzB(zznd.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 17:
                zzqa.zzD(zznd.zza, ((Long) entry.getValue()).longValue());
                return;
            default:
                return;
        }
    }
}
