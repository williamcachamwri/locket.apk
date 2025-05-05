package com.google.android.gms.measurement.internal;

import android.os.Binder;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.internal.measurement.zzad;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzc;
import com.google.android.gms.internal.measurement.zznr;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final class zzic extends zzge {
    /* access modifiers changed from: private */
    public final zznv zza;
    private Boolean zzb;
    private String zzc;

    public final zzaj zza(zzo zzo) {
        zzb(zzo, false);
        Preconditions.checkNotEmpty(zzo.zza);
        try {
            return (zzaj) this.zza.zzl().zzb(new zziv(this, zzo)).get(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            this.zza.zzj().zzg().zza("Failed to get consent. appId", zzgo.zza(zzo.zza), e);
            return new zzaj((Bundle) null);
        }
    }

    /* access modifiers changed from: package-private */
    public final zzbf zzb(zzbf zzbf, zzo zzo) {
        boolean z = false;
        if (!(!"_cmp".equals(zzbf.zza) || zzbf.zzb == null || zzbf.zzb.zza() == 0)) {
            String zzd = zzbf.zzb.zzd("_cis");
            if ("referrer broadcast".equals(zzd) || "referrer API".equals(zzd)) {
                z = true;
            }
        }
        if (!z) {
            return zzbf;
        }
        this.zza.zzj().zzo().zza("Event has been filtered ", zzbf.toString());
        return new zzbf("_cmpx", zzbf.zzb, zzbf.zzc, zzbf.zzd);
    }

    public final String zzb(zzo zzo) {
        zzb(zzo, false);
        return this.zza.zzb(zzo);
    }

    public final List<zzno> zza(zzo zzo, Bundle bundle) {
        zzb(zzo, false);
        Preconditions.checkNotNull(zzo.zza);
        try {
            return (List) this.zza.zzl().zza(new zziy(this, zzo, bundle)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzj().zzg().zza("Failed to get trigger URIs. appId", zzgo.zza(zzo.zza), e);
            return Collections.emptyList();
        }
    }

    public final List<zzon> zza(zzo zzo, boolean z) {
        zzb(zzo, false);
        String str = zzo.zza;
        Preconditions.checkNotNull(str);
        try {
            List<zzop> list = (List) this.zza.zzl().zza(new zzjb(this, str)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzop zzop : list) {
                if (z || !zzos.zzg(zzop.zzc)) {
                    arrayList.add(new zzon(zzop));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzj().zzg().zza("Failed to get user properties. appId", zzgo.zza(zzo.zza), e);
            return null;
        }
    }

    public final List<zzae> zza(String str, String str2, zzo zzo) {
        zzb(zzo, false);
        String str3 = zzo.zza;
        Preconditions.checkNotNull(str3);
        try {
            return (List) this.zza.zzl().zza(new zzir(this, str3, str, str2)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzj().zzg().zza("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }

    public final List<zzae> zza(String str, String str2, String str3) {
        zza(str, true);
        try {
            return (List) this.zza.zzl().zza(new zziq(this, str, str2, str3)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzj().zzg().zza("Failed to get conditional user properties as", e);
            return Collections.emptyList();
        }
    }

    public final List<zzon> zza(String str, String str2, boolean z, zzo zzo) {
        zzb(zzo, false);
        String str3 = zzo.zza;
        Preconditions.checkNotNull(str3);
        try {
            List<zzop> list = (List) this.zza.zzl().zza(new zzip(this, str3, str, str2)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzop zzop : list) {
                if (z || !zzos.zzg(zzop.zzc)) {
                    arrayList.add(new zzon(zzop));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzj().zzg().zza("Failed to query user properties. appId", zzgo.zza(zzo.zza), e);
            return Collections.emptyList();
        }
    }

    public final List<zzon> zza(String str, String str2, String str3, boolean z) {
        zza(str, true);
        try {
            List<zzop> list = (List) this.zza.zzl().zza(new zzio(this, str, str2, str3)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzop zzop : list) {
                if (z || !zzos.zzg(zzop.zzc)) {
                    arrayList.add(new zzon(zzop));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzj().zzg().zza("Failed to get user properties as. appId", zzgo.zza(str), e);
            return Collections.emptyList();
        }
    }

    public zzic(zznv zznv) {
        this(zznv, (String) null);
    }

    private zzic(zznv zznv, String str) {
        Preconditions.checkNotNull(zznv);
        this.zza = zznv;
        this.zzc = null;
    }

    public final void zzc(zzo zzo) {
        zzb(zzo, false);
        zzb((Runnable) new zzil(this, zzo));
    }

    public final void zzd(zzo zzo) {
        zzb(zzo, false);
        zzb((Runnable) new zzii(this, zzo));
    }

    private final void zzb(zzo zzo, boolean z) {
        Preconditions.checkNotNull(zzo);
        Preconditions.checkNotEmpty(zzo.zza);
        zza(zzo.zza, false);
        this.zza.zzq().zza(zzo.zzb, zzo.zzp);
    }

    private final void zza(String str, boolean z) {
        boolean z2;
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                try {
                    if (this.zzb == null) {
                        if (!"com.google.android.gms".equals(this.zzc) && !UidVerifier.isGooglePlayServicesUid(this.zza.zza(), Binder.getCallingUid())) {
                            if (!GoogleSignatureVerifier.getInstance(this.zza.zza()).isUidGoogleSigned(Binder.getCallingUid())) {
                                z2 = false;
                                this.zzb = Boolean.valueOf(z2);
                            }
                        }
                        z2 = true;
                        this.zzb = Boolean.valueOf(z2);
                    }
                    if (this.zzb.booleanValue()) {
                        return;
                    }
                } catch (SecurityException e) {
                    this.zza.zzj().zzg().zza("Measurement Service called with invalid calling package. appId", zzgo.zza(str));
                    throw e;
                }
            }
            if (this.zzc == null && GooglePlayServicesUtilLight.uidHasPackageName(this.zza.zza(), Binder.getCallingUid(), str)) {
                this.zzc = str;
            }
            if (!str.equals(this.zzc)) {
                throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[]{str}));
            }
            return;
        }
        this.zza.zzj().zzg().zza("Measurement Service called without app package");
        throw new SecurityException("Measurement Service called without app package");
    }

    /* access modifiers changed from: package-private */
    public final void zzc(zzbf zzbf, zzo zzo) {
        zzb zzb2;
        boolean z;
        if (!this.zza.zzi().zzk(zzo.zza)) {
            zzd(zzbf, zzo);
            return;
        }
        this.zza.zzj().zzp().zza("EES config found for", zzo.zza);
        zzhl zzi = this.zza.zzi();
        String str = zzo.zza;
        if (TextUtils.isEmpty(str)) {
            zzb2 = null;
        } else {
            zzb2 = zzi.zza.get(str);
        }
        if (zzb2 == null) {
            this.zza.zzj().zzp().zza("EES not loaded for", zzo.zza);
            zzd(zzbf, zzo);
            return;
        }
        try {
            Map<String, Object> zza2 = this.zza.zzp().zza(zzbf.zzb.zzb(), true);
            String zza3 = zzji.zza(zzbf.zza);
            if (zza3 == null) {
                zza3 = zzbf.zza;
            }
            z = zzb2.zza(new zzad(zza3, zzbf.zzd, zza2));
        } catch (zzc unused) {
            this.zza.zzj().zzg().zza("EES error. appId, eventName", zzo.zzb, zzbf.zza);
            z = false;
        }
        if (!z) {
            this.zza.zzj().zzp().zza("EES was not applied to event", zzbf.zza);
            zzd(zzbf, zzo);
            return;
        }
        if (zzb2.zzd()) {
            this.zza.zzj().zzp().zza("EES edited event", zzbf.zza);
            zzd(this.zza.zzp().zza(zzb2.zza().zzb()), zzo);
        } else {
            zzd(zzbf, zzo);
        }
        if (zzb2.zzc()) {
            for (zzad next : zzb2.zza().zzc()) {
                this.zza.zzj().zzp().zza("EES logging created event", next.zzb());
                zzd(this.zza.zzp().zza(next), zzo);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Bundle bundle, String str) {
        boolean zza2 = this.zza.zze().zza(zzbh.zzde);
        boolean zza3 = this.zza.zze().zza(zzbh.zzdg);
        if (!bundle.isEmpty() || !zza2 || !zza3) {
            this.zza.zzf().zzb(str, bundle);
            if (zza3 && this.zza.zzf().zzt(str)) {
                this.zza.zzf().zza(str, bundle);
                return;
            }
            return;
        }
        this.zza.zzf().zzp(str);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Bundle bundle, String str) {
        if (bundle.isEmpty()) {
            this.zza.zzf().zzp(str);
            return;
        }
        this.zza.zzf().zzb(str, bundle);
        this.zza.zzf().zza(str, bundle);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzj(zzo zzo) {
        this.zza.zzr();
        this.zza.zzf(zzo);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzk(zzo zzo) {
        this.zza.zzr();
        this.zza.zzg(zzo);
    }

    public final void zza(zzbf zzbf, zzo zzo) {
        Preconditions.checkNotNull(zzbf);
        zzb(zzo, false);
        zzb((Runnable) new zziu(this, zzbf, zzo));
    }

    public final void zza(zzbf zzbf, String str, String str2) {
        Preconditions.checkNotNull(zzbf);
        Preconditions.checkNotEmpty(str);
        zza(str, true);
        zzb((Runnable) new zzix(this, zzbf, str));
    }

    private final void zzd(zzbf zzbf, zzo zzo) {
        this.zza.zzr();
        this.zza.zza(zzbf, zzo);
    }

    public final void zze(zzo zzo) {
        Preconditions.checkNotEmpty(zzo.zza);
        zza(zzo.zza, false);
        zzb((Runnable) new zzit(this, zzo));
    }

    private final void zza(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (this.zza.zzl().zzg()) {
            runnable.run();
        } else {
            this.zza.zzl().zzc(runnable);
        }
    }

    private final void zzb(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (this.zza.zzl().zzg()) {
            runnable.run();
        } else {
            this.zza.zzl().zzb(runnable);
        }
    }

    public final void zza(zzae zzae, zzo zzo) {
        Preconditions.checkNotNull(zzae);
        Preconditions.checkNotNull(zzae.zzc);
        zzb(zzo, false);
        zzae zzae2 = new zzae(zzae);
        zzae2.zza = zzo.zza;
        zzb((Runnable) new zzin(this, zzae2, zzo));
    }

    public final void zza(zzae zzae) {
        Preconditions.checkNotNull(zzae);
        Preconditions.checkNotNull(zzae.zzc);
        Preconditions.checkNotEmpty(zzae.zza);
        zza(zzae.zza, true);
        zzb((Runnable) new zzim(this, new zzae(zzae)));
    }

    public final void zzf(zzo zzo) {
        Preconditions.checkNotEmpty(zzo.zza);
        Preconditions.checkNotNull(zzo.zzt);
        zza((Runnable) new zzis(this, zzo));
    }

    public final void zza(long j, String str, String str2, String str3) {
        zzb((Runnable) new zzik(this, str2, str3, str, j));
    }

    public final void zza(Bundle bundle, zzo zzo) {
        zzb(zzo, false);
        String str = zzo.zza;
        Preconditions.checkNotNull(str);
        zzb((Runnable) new zzig(this, bundle, str));
    }

    public final void zzb(Bundle bundle, zzo zzo) {
        if (zznr.zza() && this.zza.zze().zza(zzbh.zzdg)) {
            zzb(zzo, false);
            String str = zzo.zza;
            Preconditions.checkNotNull(str);
            zzb((Runnable) new zzie(this, bundle, str));
        }
    }

    public final void zzg(zzo zzo) {
        Preconditions.checkNotEmpty(zzo.zza);
        Preconditions.checkNotNull(zzo.zzt);
        zza((Runnable) new zzih(this, zzo));
    }

    public final void zzh(zzo zzo) {
        zzb(zzo, false);
        zzb((Runnable) new zzij(this, zzo));
    }

    public final void zzi(zzo zzo) {
        Preconditions.checkNotEmpty(zzo.zza);
        Preconditions.checkNotNull(zzo.zzt);
        zza((Runnable) new zzif(this, zzo));
    }

    public final void zza(zzon zzon, zzo zzo) {
        Preconditions.checkNotNull(zzon);
        zzb(zzo, false);
        zzb((Runnable) new zziz(this, zzon, zzo));
    }

    public final byte[] zza(zzbf zzbf, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzbf);
        zza(str, true);
        this.zza.zzj().zzc().zza("Log and bundle. event", this.zza.zzg().zza(zzbf.zza));
        long nanoTime = this.zza.zzb().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zza.zzl().zzb(new zziw(this, zzbf, str)).get();
            if (bArr == null) {
                this.zza.zzj().zzg().zza("Log and bundle returned null. appId", zzgo.zza(str));
                bArr = new byte[0];
            }
            this.zza.zzj().zzc().zza("Log and bundle processed. event, size, time_ms", this.zza.zzg().zza(zzbf.zza), Integer.valueOf(bArr.length), Long.valueOf((this.zza.zzb().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzj().zzg().zza("Failed to log and bundle. appId, event, error", zzgo.zza(str), this.zza.zzg().zza(zzbf.zza), e);
            return null;
        }
    }
}
