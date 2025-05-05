package com.google.android.recaptcha.internal;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzeu implements zzeq {
    /* access modifiers changed from: private */
    public static Timer zza;
    private final Context zzb;
    private final zzev zzc;
    /* access modifiers changed from: private */
    public final CoroutineScope zzd;
    /* access modifiers changed from: private */
    public final zzek zze;

    public zzeu(Context context, zzev zzev, CoroutineScope coroutineScope) {
        this.zzb = context;
        this.zzc = zzev;
        this.zzd = coroutineScope;
        zzei zzei = zzek.zza;
        zzek zzek = null;
        try {
            zzek zzc2 = zzek.zzd;
            zzc2 = zzc2 == null ? new zzek(context, (DefaultConstructorMarker) null) : zzc2;
            zzek.zzd = zzc2;
            zzek = zzc2;
        } catch (Exception unused) {
        }
        this.zze = zzek;
        zzh();
    }

    /* access modifiers changed from: private */
    public final void zzg() {
        zzek zzek;
        zzek zzek2 = this.zze;
        if (zzek2 != null) {
            for (List<zzel> it : CollectionsKt.windowed(zzek2.zzd(), 20, 20, true)) {
                zzrf zzi = zzrh.zzi();
                List arrayList = new ArrayList();
                for (zzel zzel : it) {
                    try {
                        zztz zzk = zztz.zzk(zzkj.zzg().zzj(zzel.zzc()));
                        int zzN = zzk.zzN();
                        int i = zzN - 1;
                        if (zzN != 0) {
                            if (i == 0) {
                                zzi.zzq(zzk.zzf());
                            } else if (i == 1) {
                                zzi.zzr(zzk.zzg());
                            } else if (i == 2) {
                                Unit unit = Unit.INSTANCE;
                            } else {
                                throw new NoWhenBranchMatchedException();
                            }
                            arrayList.add(zzel);
                        } else {
                            throw null;
                        }
                    } catch (Exception unused) {
                        zzek zzek3 = this.zze;
                        if (zzek3 != null) {
                            zzek3.zzf(zzel);
                        }
                    }
                }
                if (zzi.zze() + zzi.zzf() != 0) {
                    if (this.zzc.zza(((zzrh) zzi.zzk()).zzd()) && (zzek = this.zze) != null) {
                        zzek.zza(arrayList);
                    }
                }
            }
        }
    }

    private final void zzh() {
        if (zza == null) {
            Timer timer = new Timer();
            zza = timer;
            timer.schedule(new zzer(this), 120000, 120000);
        }
    }

    public final void zza(zztz zztz) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.zzd, (CoroutineContext) null, (CoroutineStart) null, new zzet(this, zztz, (Continuation) null), 3, (Object) null);
        zzh();
    }
}
