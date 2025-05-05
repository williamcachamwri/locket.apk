package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzdo;
import com.google.android.gms.internal.measurement.zznm;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzls extends zzh {
    /* access modifiers changed from: private */
    public final zzmq zza;
    /* access modifiers changed from: private */
    public zzgb zzb;
    private volatile Boolean zzc;
    private final zzav zzd;
    private final zznl zze;
    private final List<Runnable> zzf = new ArrayList();
    private final zzav zzg;

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    /* access modifiers changed from: protected */
    public final boolean zzz() {
        return false;
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public final /* bridge */ /* synthetic */ zzb zzc() {
        return super.zzc();
    }

    private final zzo zzc(boolean z) {
        return zzg().zza(z ? zzj().zzx() : null);
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzab zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzag zze() {
        return super.zze();
    }

    /* access modifiers changed from: protected */
    public final zzaj zzaa() {
        zzt();
        zzu();
        zzgb zzgb = this.zzb;
        if (zzgb == null) {
            zzae();
            zzj().zzc().zza("Failed to get consents; not connected to service yet.");
            return null;
        }
        zzo zzc2 = zzc(false);
        Preconditions.checkNotNull(zzc2);
        try {
            zzaj zza2 = zzgb.zza(zzc2);
            zzar();
            return zza2;
        } catch (RemoteException e) {
            zzj().zzg().zza("Failed to get consents; remote exception", e);
            return null;
        }
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzaz zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzgg zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzgf zzh() {
        return super.zzh();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzi() {
        return super.zzi();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgo zzj() {
        return super.zzj();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzha zzk() {
        return super.zzk();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhv zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ zzjq zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ zzlj zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzls zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zznb zzp() {
        return super.zzp();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzos zzq() {
        return super.zzq();
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzab() {
        return this.zzc;
    }

    static /* synthetic */ void zzd(zzls zzls) {
        zzls.zzt();
        if (zzls.zzal()) {
            zzls.zzj().zzp().zza("Inactivity, disconnecting from the service");
            zzls.zzaf();
        }
    }

    static /* synthetic */ void zza(zzls zzls, ComponentName componentName) {
        zzls.zzt();
        if (zzls.zzb != null) {
            zzls.zzb = null;
            zzls.zzj().zzp().zza("Disconnected from device MeasurementService", componentName);
            zzls.zzt();
            zzls.zzae();
        }
    }

    protected zzls(zzhy zzhy) {
        super(zzhy);
        this.zze = new zznl(zzhy.zzb());
        this.zza = new zzmq(this);
        this.zzd = new zzlt(this, zzhy);
        this.zzg = new zzmg(this, zzhy);
    }

    /* access modifiers changed from: protected */
    public final void zzac() {
        zzt();
        zzu();
        zza((Runnable) new zzme(this, zzc(true)));
    }

    /* access modifiers changed from: protected */
    public final void zzad() {
        zzt();
        zzu();
        zzo zzc2 = zzc(true);
        zzh().zzab();
        zza((Runnable) new zzmb(this, zzc2));
    }

    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    /* access modifiers changed from: package-private */
    public final void zzae() {
        zzt();
        zzu();
        if (!zzal()) {
            if (zzap()) {
                this.zza.zza();
            } else if (!zze().zzy()) {
                List<ResolveInfo> queryIntentServices = zza().getPackageManager().queryIntentServices(new Intent().setClassName(zza(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
                if (queryIntentServices != null && !queryIntentServices.isEmpty()) {
                    Intent intent = new Intent("com.google.android.gms.measurement.START");
                    intent.setComponent(new ComponentName(zza(), "com.google.android.gms.measurement.AppMeasurementService"));
                    this.zza.zza(intent);
                    return;
                }
                zzj().zzg().zza("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
            }
        }
    }

    public final void zzaf() {
        zzt();
        zzu();
        this.zza.zzb();
        try {
            ConnectionTracker.getInstance().unbindService(zza(), this.zza);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.zzb = null;
    }

    /* access modifiers changed from: private */
    public final void zzaq() {
        zzt();
        zzj().zzp().zza("Processing queued up service tasks", Integer.valueOf(this.zzf.size()));
        for (Runnable run : this.zzf) {
            try {
                run.run();
            } catch (RuntimeException e) {
                zzj().zzg().zza("Task exception while flushing queue", e);
            }
        }
        this.zzf.clear();
        this.zzg.zza();
    }

    public final void zza(zzdo zzdo) {
        zzt();
        zzu();
        zza((Runnable) new zzmc(this, zzc(false), zzdo));
    }

    public final void zza(AtomicReference<String> atomicReference) {
        zzt();
        zzu();
        zza((Runnable) new zzlz(this, atomicReference, zzc(false)));
    }

    /* access modifiers changed from: protected */
    public final void zza(zzdo zzdo, String str, String str2) {
        zzt();
        zzu();
        zza((Runnable) new zzmo(this, str, str2, zzc(false), zzdo));
    }

    /* access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzae>> atomicReference, String str, String str2, String str3) {
        zzt();
        zzu();
        zza((Runnable) new zzml(this, atomicReference, str, str2, str3, zzc(false)));
    }

    /* access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzno>> atomicReference, Bundle bundle) {
        zzt();
        zzu();
        zza((Runnable) new zzly(this, atomicReference, zzc(false), bundle));
    }

    /* access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzon>> atomicReference, boolean z) {
        zzt();
        zzu();
        zza((Runnable) new zzlv(this, atomicReference, zzc(false), z));
    }

    /* access modifiers changed from: protected */
    public final void zza(zzdo zzdo, String str, String str2, boolean z) {
        zzt();
        zzu();
        zza((Runnable) new zzlw(this, str, str2, zzc(false), z, zzdo));
    }

    /* access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzon>> atomicReference, String str, String str2, String str3, boolean z) {
        zzt();
        zzu();
        zza((Runnable) new zzmn(this, atomicReference, str, str2, str3, zzc(false), z));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzag() {
        zzgb zzgb = this.zzb;
        if (zzgb == null) {
            zzj().zzg().zza("Failed to send Dma consent settings to service");
            return;
        }
        try {
            zzo zzc2 = zzc(false);
            Preconditions.checkNotNull(zzc2);
            zzgb.zzg(zzc2);
            zzar();
        } catch (RemoteException e) {
            zzj().zzg().zza("Failed to send Dma consent settings to the service", e);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzah() {
        zzgb zzgb = this.zzb;
        if (zzgb == null) {
            zzj().zzg().zza("Failed to send storage consent settings to service");
            return;
        }
        try {
            zzo zzc2 = zzc(false);
            Preconditions.checkNotNull(zzc2);
            zzgb.zzi(zzc2);
            zzar();
        } catch (RemoteException e) {
            zzj().zzg().zza("Failed to send storage consent settings to the service", e);
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbf zzbf, String str) {
        Preconditions.checkNotNull(zzbf);
        zzt();
        zzu();
        zza((Runnable) new zzmj(this, true, zzc(true), zzh().zza(zzbf), zzbf, str));
    }

    public final void zza(zzdo zzdo, zzbf zzbf, String str) {
        zzt();
        zzu();
        if (zzq().zza((int) GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE) != 0) {
            zzj().zzu().zza("Not bundling data. Service unavailable or out of date");
            zzq().zza(zzdo, new byte[0]);
            return;
        }
        zza((Runnable) new zzmi(this, zzbf, str, zzdo));
    }

    /* access modifiers changed from: private */
    public final void zzar() {
        zzt();
        this.zze.zzb();
        this.zzd.zza(zzbh.zzal.zza(null).longValue());
    }

    /* access modifiers changed from: protected */
    public final void zzai() {
        zzt();
        zzu();
        zzo zzc2 = zzc(false);
        zzh().zzaa();
        zza((Runnable) new zzma(this, zzc2));
    }

    private final void zza(Runnable runnable) throws IllegalStateException {
        zzt();
        if (zzal()) {
            runnable.run();
        } else if (((long) this.zzf.size()) >= 1000) {
            zzj().zzg().zza("Discarding data. Max runnable queue size reached");
        } else {
            this.zzf.add(runnable);
            this.zzg.zza(60000);
            zzae();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzgb zzgb, AbstractSafeParcelable abstractSafeParcelable, zzo zzo) {
        int i;
        long j;
        long j2;
        long j3;
        zzgb zzgb2 = zzgb;
        AbstractSafeParcelable abstractSafeParcelable2 = abstractSafeParcelable;
        zzo zzo2 = zzo;
        zzt();
        zzu();
        int i2 = 100;
        int i3 = 100;
        int i4 = 0;
        while (i4 < 1001 && i3 == i2) {
            ArrayList arrayList = new ArrayList();
            List<AbstractSafeParcelable> zza2 = zzh().zza(i2);
            if (zza2 != null) {
                arrayList.addAll(zza2);
                i = zza2.size();
            } else {
                i = 0;
            }
            if (abstractSafeParcelable2 != null && i < i2) {
                arrayList.add(abstractSafeParcelable2);
            }
            boolean zza3 = zze().zza(zzbh.zzce);
            ArrayList arrayList2 = arrayList;
            int size = arrayList.size();
            int i5 = 0;
            while (i5 < size) {
                int i6 = i5 + 1;
                AbstractSafeParcelable abstractSafeParcelable3 = (AbstractSafeParcelable) arrayList.get(i5);
                if (abstractSafeParcelable3 instanceof zzbf) {
                    if (zza3) {
                        try {
                            j3 = this.zzu.zzb().currentTimeMillis();
                            try {
                                j2 = this.zzu.zzb().elapsedRealtime();
                            } catch (RemoteException e) {
                                e = e;
                                j2 = 0;
                                j = j3;
                                zzj().zzg().zza("Failed to send event to the service", e);
                                zzgm.zza(this.zzu).zza(36301, 13, j, this.zzu.zzb().currentTimeMillis(), (int) (this.zzu.zzb().elapsedRealtime() - j2));
                                i5 = i6;
                            }
                        } catch (RemoteException e2) {
                            e = e2;
                            j2 = 0;
                            j = 0;
                            zzj().zzg().zza("Failed to send event to the service", e);
                            if (zza3 && j != 0) {
                                zzgm.zza(this.zzu).zza(36301, 13, j, this.zzu.zzb().currentTimeMillis(), (int) (this.zzu.zzb().elapsedRealtime() - j2));
                            }
                            i5 = i6;
                        }
                    } else {
                        j3 = 0;
                        j2 = 0;
                    }
                    try {
                        zzgb2.zza((zzbf) abstractSafeParcelable3, zzo2);
                        if (zza3) {
                            zzj().zzp().zza("Logging telemetry for logEvent from database");
                            zzgm.zza(this.zzu).zza(36301, 0, j3, this.zzu.zzb().currentTimeMillis(), (int) (this.zzu.zzb().elapsedRealtime() - j2));
                        }
                    } catch (RemoteException e3) {
                        e = e3;
                        j = j3;
                        zzj().zzg().zza("Failed to send event to the service", e);
                        zzgm.zza(this.zzu).zza(36301, 13, j, this.zzu.zzb().currentTimeMillis(), (int) (this.zzu.zzb().elapsedRealtime() - j2));
                        i5 = i6;
                    }
                } else if (abstractSafeParcelable3 instanceof zzon) {
                    try {
                        zzgb2.zza((zzon) abstractSafeParcelable3, zzo2);
                    } catch (RemoteException e4) {
                        zzj().zzg().zza("Failed to send user property to the service", e4);
                    }
                } else if (abstractSafeParcelable3 instanceof zzae) {
                    try {
                        zzgb2.zza((zzae) abstractSafeParcelable3, zzo2);
                    } catch (RemoteException e5) {
                        zzj().zzg().zza("Failed to send conditional user property to the service", e5);
                    }
                } else {
                    zzj().zzg().zza("Discarding data. Unrecognized parcel type.");
                }
                i5 = i6;
            }
            i4++;
            i3 = i;
            i2 = 100;
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(zzae zzae) {
        Preconditions.checkNotNull(zzae);
        zzt();
        zzu();
        zza((Runnable) new zzmm(this, true, zzc(true), zzh().zza(zzae), new zzae(zzae), zzae));
    }

    /* access modifiers changed from: protected */
    public final void zza(boolean z) {
        zzt();
        zzu();
        if ((!zznm.zza() || !zze().zza(zzbh.zzcx)) && z) {
            zzh().zzaa();
        }
        if (zzan()) {
            zza((Runnable) new zzmk(this, zzc(false)));
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(zzlk zzlk) {
        zzt();
        zzu();
        zza((Runnable) new zzmd(this, zzlk));
    }

    public final void zza(Bundle bundle) {
        zzt();
        zzu();
        zza((Runnable) new zzmf(this, zzc(false), bundle));
    }

    /* access modifiers changed from: protected */
    public final void zzaj() {
        zzt();
        zzu();
        zza((Runnable) new zzlu(this));
    }

    /* access modifiers changed from: protected */
    public final void zzak() {
        zzt();
        zzu();
        zza((Runnable) new zzmh(this, zzc(true)));
    }

    /* access modifiers changed from: protected */
    public final void zza(zzgb zzgb) {
        zzt();
        Preconditions.checkNotNull(zzgb);
        this.zzb = zzgb;
        zzar();
        zzaq();
    }

    /* access modifiers changed from: protected */
    public final void zzb(boolean z) {
        zzt();
        zzu();
        if ((!zznm.zza() || !zze().zza(zzbh.zzcx)) && z) {
            zzh().zzaa();
        }
        zza((Runnable) new zzlr(this));
    }

    /* access modifiers changed from: protected */
    public final void zza(zzon zzon) {
        zzt();
        zzu();
        zza((Runnable) new zzlx(this, zzc(true), zzh().zza(zzon), zzon));
    }

    public final boolean zzal() {
        zzt();
        zzu();
        return this.zzb != null;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzam() {
        zzt();
        zzu();
        if (zzap() && zzq().zzg() < 200900) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzan() {
        zzt();
        zzu();
        if (zzap() && zzq().zzg() < zzbh.zzbt.zza(null).intValue()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzao() {
        zzt();
        zzu();
        if (zzap() && zzq().zzg() < 241200) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00f5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzap() {
        /*
            r5 = this;
            r5.zzt()
            r5.zzu()
            java.lang.Boolean r0 = r5.zzc
            if (r0 != 0) goto L_0x0102
            r5.zzt()
            r5.zzu()
            com.google.android.gms.measurement.internal.zzha r0 = r5.zzk()
            java.lang.Boolean r0 = r0.zzp()
            r1 = 1
            if (r0 == 0) goto L_0x0023
            boolean r2 = r0.booleanValue()
            if (r2 == 0) goto L_0x0023
            goto L_0x00fc
        L_0x0023:
            com.google.android.gms.measurement.internal.zzgg r2 = r5.zzg()
            int r2 = r2.zzaa()
            r3 = 0
            if (r2 != r1) goto L_0x0031
        L_0x002e:
            r0 = r1
            goto L_0x00d8
        L_0x0031:
            com.google.android.gms.measurement.internal.zzgo r2 = r5.zzj()
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzp()
            java.lang.String r4 = "Checking service availability"
            r2.zza(r4)
            com.google.android.gms.measurement.internal.zzos r2 = r5.zzq()
            r4 = 12451000(0xbdfcb8, float:1.7447567E-38)
            int r2 = r2.zza((int) r4)
            if (r2 == 0) goto L_0x00c9
            if (r2 == r1) goto L_0x00b9
            r4 = 2
            if (r2 == r4) goto L_0x0099
            r0 = 3
            if (r2 == r0) goto L_0x0089
            r0 = 9
            if (r2 == r0) goto L_0x007b
            r0 = 18
            if (r2 == r0) goto L_0x006d
            com.google.android.gms.measurement.internal.zzgo r0 = r5.zzj()
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzu()
            java.lang.String r1 = "Unexpected service status"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.zza(r1, r2)
            goto L_0x0096
        L_0x006d:
            com.google.android.gms.measurement.internal.zzgo r0 = r5.zzj()
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzu()
            java.lang.String r2 = "Service updating"
            r0.zza(r2)
            goto L_0x002e
        L_0x007b:
            com.google.android.gms.measurement.internal.zzgo r0 = r5.zzj()
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzu()
            java.lang.String r1 = "Service invalid"
            r0.zza(r1)
            goto L_0x0096
        L_0x0089:
            com.google.android.gms.measurement.internal.zzgo r0 = r5.zzj()
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzu()
            java.lang.String r1 = "Service disabled"
            r0.zza(r1)
        L_0x0096:
            r0 = r3
            r1 = r0
            goto L_0x00d8
        L_0x0099:
            com.google.android.gms.measurement.internal.zzgo r2 = r5.zzj()
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzc()
            java.lang.String r4 = "Service container out of date"
            r2.zza(r4)
            com.google.android.gms.measurement.internal.zzos r2 = r5.zzq()
            int r2 = r2.zzg()
            r4 = 17443(0x4423, float:2.4443E-41)
            if (r2 >= r4) goto L_0x00b3
            goto L_0x00c6
        L_0x00b3:
            if (r0 != 0) goto L_0x00b6
            goto L_0x00b7
        L_0x00b6:
            r1 = r3
        L_0x00b7:
            r0 = r3
            goto L_0x00d8
        L_0x00b9:
            com.google.android.gms.measurement.internal.zzgo r0 = r5.zzj()
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzp()
            java.lang.String r2 = "Service missing"
            r0.zza(r2)
        L_0x00c6:
            r0 = r1
            r1 = r3
            goto L_0x00d8
        L_0x00c9:
            com.google.android.gms.measurement.internal.zzgo r0 = r5.zzj()
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzp()
            java.lang.String r2 = "Service available"
            r0.zza(r2)
            goto L_0x002e
        L_0x00d8:
            if (r1 != 0) goto L_0x00f2
            com.google.android.gms.measurement.internal.zzag r2 = r5.zze()
            boolean r2 = r2.zzy()
            if (r2 == 0) goto L_0x00f2
            com.google.android.gms.measurement.internal.zzgo r0 = r5.zzj()
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzg()
            java.lang.String r2 = "No way to upload. Consider using the full version of Analytics"
            r0.zza(r2)
            goto L_0x00f3
        L_0x00f2:
            r3 = r0
        L_0x00f3:
            if (r3 == 0) goto L_0x00fc
            com.google.android.gms.measurement.internal.zzha r0 = r5.zzk()
            r0.zza((boolean) r1)
        L_0x00fc:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            r5.zzc = r0
        L_0x0102:
            java.lang.Boolean r0 = r5.zzc
            boolean r0 = r0.booleanValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzls.zzap():boolean");
    }
}
