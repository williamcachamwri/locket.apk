package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.RecaptchaException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.coroutines.Continuation;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzfh {
    private final Lazy zza = LazyKt.lazy(zzfe.zza);
    private final Lazy zzb = LazyKt.lazy(zzff.zza);
    private final Lazy zzc = LazyKt.lazy(zzfg.zza);

    public zzfh() {
        int i = zzax.zza;
    }

    public static final /* synthetic */ zzfm zzb(zzfh zzfh) {
        return (zzfm) zzfh.zza.getValue();
    }

    static /* synthetic */ Object zze(zzfh zzfh, zzse zzse, zzem zzem, Continuation continuation) throws zzbf {
        zzep zzf;
        try {
            String zzM = zzse.zzM();
            String zzN = zzse.zzN();
            zzas zzf2 = zzfh.zzf();
            String str = null;
            if (zzf2 != null && zzf2.zzd(zzN)) {
                zzep zzf3 = zzem.zzf(25);
                try {
                    String zza2 = zzfh.zzf().zza(zzN);
                    if (zza2 != null) {
                        zzf3.zza();
                        str = zza2;
                    }
                } catch (Exception e) {
                    zzf3.zzb(new zzbf(zzbd.zzk, zzbc.zzR, e.getMessage()));
                }
                zzf3.zzb(new zzbf(zzbd.zzk, zzbc.zzS, (String) null));
            }
            if (str == null) {
                zzas zzf4 = zzfh.zzf();
                if (zzf4 != null) {
                    zzf4.zzb();
                }
                zzf = zzem.zzf(23);
                str = zzfh.zzg().zzb(zzM);
                zzf.zza();
                zzep zzf5 = zzem.zzf(24);
                try {
                    zzas zzf6 = zzfh.zzf();
                    if (zzf6 != null) {
                        zzf6.zzc(zzN, str);
                    }
                    zzf5.zza();
                } catch (Exception e2) {
                    zzf5.zzb(new zzbf(zzbd.zzk, zzbc.zzT, e2.getMessage()));
                }
            }
            return StringsKt.replace$default(zzse.zzl(), "JAVASCRIPT_TAG", str, false, 4, (Object) null);
        } catch (zzbf e3) {
            zzf.zzb(e3);
            throw e3;
        } catch (Exception e4) {
            if (e4 instanceof zzbf) {
                throw e4;
            }
            throw new zzbf(zzbd.zzb, zzbc.zzL, e4.getMessage());
        }
    }

    private final zzas zzf() {
        return (zzas) this.zzb.getValue();
    }

    /* access modifiers changed from: private */
    public final zzfa zzg() {
        return (zzfa) this.zzc.getValue();
    }

    public final Object zzc(String str, zztq zztq, Continuation continuation) throws RecaptchaException {
        return CoroutineScopeKt.coroutineScope(new zzfd(this, str, zztq, (Continuation) null), continuation);
    }

    public final Object zzd(zzse zzse, zzem zzem, Continuation continuation) throws zzbf {
        return zze(this, zzse, zzem, continuation);
    }
}
