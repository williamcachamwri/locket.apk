package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public abstract class zzhj<T> {
    private static final Object zza = new Object();
    @Nullable
    private static volatile zzhu zzb = null;
    private static volatile boolean zzc = false;
    private static zzhy zzd = new zzhy(new zzho());
    private static final AtomicInteger zze = new AtomicInteger();
    private final zzhr zzf;
    private final String zzg;
    private Object zzh;
    private volatile int zzi;
    private volatile T zzj;
    private final boolean zzk;
    private volatile boolean zzl;

    static /* synthetic */ boolean zzd() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public abstract T zza(Object obj);

    static /* synthetic */ zzhj zza(zzhr zzhr, String str, Boolean bool, boolean z) {
        return new zzhq(zzhr, str, bool, true);
    }

    static /* synthetic */ zzhj zza(zzhr zzhr, String str, Double d, boolean z) {
        return new zzhp(zzhr, str, d, true);
    }

    static /* synthetic */ zzhj zza(zzhr zzhr, String str, Long l, boolean z) {
        return new zzhn(zzhr, str, l, true);
    }

    static /* synthetic */ zzhj zza(zzhr zzhr, String str, String str2, boolean z) {
        return new zzhs(zzhr, str, str2, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0087  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T zza() {
        /*
            r8 = this;
            boolean r0 = r8.zzk
            if (r0 != 0) goto L_0x0011
            com.google.android.gms.internal.measurement.zzhy r0 = zzd
            java.lang.String r1 = r8.zzg
            boolean r0 = r0.zza(r1)
            java.lang.String r1 = "Attempt to access PhenotypeFlag not via codegen. All new PhenotypeFlags must be accessed through codegen APIs. If you believe you are seeing this error by mistake, you can add your flag to the exemption list located at //java/com/google/android/libraries/phenotype/client/lockdown/flags.textproto. Send the addition CL to ph-reviews@. See go/phenotype-android-codegen for information about generated code. See go/ph-lockdown for more information about this error."
            com.google.common.base.Preconditions.checkState(r0, r1)
        L_0x0011:
            java.util.concurrent.atomic.AtomicInteger r0 = zze
            int r0 = r0.get()
            int r1 = r8.zzi
            if (r1 >= r0) goto L_0x009b
            monitor-enter(r8)
            int r1 = r8.zzi     // Catch:{ all -> 0x0098 }
            if (r1 >= r0) goto L_0x0096
            com.google.android.gms.internal.measurement.zzhu r1 = zzb     // Catch:{ all -> 0x0098 }
            com.google.common.base.Optional r2 = com.google.common.base.Optional.absent()     // Catch:{ all -> 0x0098 }
            r3 = 0
            if (r1 == 0) goto L_0x0051
            com.google.common.base.Supplier r2 = r1.zzb()     // Catch:{ all -> 0x0098 }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x0098 }
            com.google.common.base.Optional r2 = (com.google.common.base.Optional) r2     // Catch:{ all -> 0x0098 }
            boolean r4 = r2.isPresent()     // Catch:{ all -> 0x0098 }
            if (r4 == 0) goto L_0x0051
            java.lang.Object r3 = r2.get()     // Catch:{ all -> 0x0098 }
            com.google.android.gms.internal.measurement.zzhh r3 = (com.google.android.gms.internal.measurement.zzhh) r3     // Catch:{ all -> 0x0098 }
            com.google.android.gms.internal.measurement.zzhr r4 = r8.zzf     // Catch:{ all -> 0x0098 }
            android.net.Uri r4 = r4.zzb     // Catch:{ all -> 0x0098 }
            com.google.android.gms.internal.measurement.zzhr r5 = r8.zzf     // Catch:{ all -> 0x0098 }
            java.lang.String r5 = r5.zza     // Catch:{ all -> 0x0098 }
            com.google.android.gms.internal.measurement.zzhr r6 = r8.zzf     // Catch:{ all -> 0x0098 }
            java.lang.String r6 = r6.zzd     // Catch:{ all -> 0x0098 }
            java.lang.String r7 = r8.zzg     // Catch:{ all -> 0x0098 }
            java.lang.String r3 = r3.zza(r4, r5, r6, r7)     // Catch:{ all -> 0x0098 }
        L_0x0051:
            if (r1 == 0) goto L_0x0055
            r4 = 1
            goto L_0x0056
        L_0x0055:
            r4 = 0
        L_0x0056:
            java.lang.String r5 = "Must call PhenotypeFlagInitializer.maybeInit() first"
            com.google.common.base.Preconditions.checkState(r4, r5)     // Catch:{ all -> 0x0098 }
            com.google.android.gms.internal.measurement.zzhr r4 = r8.zzf     // Catch:{ all -> 0x0098 }
            boolean r4 = r4.zzf     // Catch:{ all -> 0x0098 }
            if (r4 == 0) goto L_0x006f
            java.lang.Object r4 = r8.zza((com.google.android.gms.internal.measurement.zzhu) r1)     // Catch:{ all -> 0x0098 }
            if (r4 == 0) goto L_0x0068
            goto L_0x0081
        L_0x0068:
            java.lang.Object r4 = r8.zzb((com.google.android.gms.internal.measurement.zzhu) r1)     // Catch:{ all -> 0x0098 }
            if (r4 == 0) goto L_0x007d
            goto L_0x0081
        L_0x006f:
            java.lang.Object r4 = r8.zzb((com.google.android.gms.internal.measurement.zzhu) r1)     // Catch:{ all -> 0x0098 }
            if (r4 == 0) goto L_0x0076
            goto L_0x0081
        L_0x0076:
            java.lang.Object r4 = r8.zza((com.google.android.gms.internal.measurement.zzhu) r1)     // Catch:{ all -> 0x0098 }
            if (r4 == 0) goto L_0x007d
            goto L_0x0081
        L_0x007d:
            java.lang.Object r4 = r8.zze()     // Catch:{ all -> 0x0098 }
        L_0x0081:
            boolean r1 = r2.isPresent()     // Catch:{ all -> 0x0098 }
            if (r1 == 0) goto L_0x0092
            if (r3 != 0) goto L_0x008e
            java.lang.Object r4 = r8.zze()     // Catch:{ all -> 0x0098 }
            goto L_0x0092
        L_0x008e:
            java.lang.Object r4 = r8.zza((java.lang.Object) r3)     // Catch:{ all -> 0x0098 }
        L_0x0092:
            r8.zzj = r4     // Catch:{ all -> 0x0098 }
            r8.zzi = r0     // Catch:{ all -> 0x0098 }
        L_0x0096:
            monitor-exit(r8)     // Catch:{ all -> 0x0098 }
            goto L_0x009b
        L_0x0098:
            r0 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x0098 }
            throw r0
        L_0x009b:
            T r0 = r8.zzj
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhj.zza():java.lang.Object");
    }

    private final T zze() {
        return this.zzh;
    }

    @Nullable
    private final T zza(zzhu zzhu) {
        if (!this.zzf.zze && (this.zzf.zzh == null || this.zzf.zzh.apply(zzhu.zza()).booleanValue())) {
            Object zza2 = zzhc.zza(zzhu.zza()).zza(this.zzf.zze ? null : zza(this.zzf.zzc));
            if (zza2 != null) {
                return zza(zza2);
            }
        }
        return null;
    }

    @Nullable
    private final T zzb(zzhu zzhu) {
        zzhb zzhb;
        Object zza2;
        if (this.zzf.zzb == null) {
            zzhb = zzhw.zza(zzhu.zza(), this.zzf.zza, new zzhm());
        } else if (zzhi.zza(zzhu.zza(), this.zzf.zzb)) {
            zzhb = this.zzf.zzg ? zzgu.zza(zzhu.zza().getContentResolver(), zzhk.zza(zzhk.zza(zzhu.zza(), this.zzf.zzb.getLastPathSegment())), new zzhm()) : zzgu.zza(zzhu.zza().getContentResolver(), this.zzf.zzb, new zzhm());
        } else {
            zzhb = null;
        }
        if (zzhb == null || (zza2 = zzhb.zza(zzb())) == null) {
            return null;
        }
        return zza(zza2);
    }

    public final String zzb() {
        return zza(this.zzf.zzd);
    }

    private final String zza(String str) {
        if (str != null && str.isEmpty()) {
            return this.zzg;
        }
        return str + this.zzg;
    }

    static {
        new AtomicReference();
    }

    private zzhj(zzhr zzhr, String str, T t, boolean z) {
        this.zzi = -1;
        if (zzhr.zza == null && zzhr.zzb == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        } else if (zzhr.zza == null || zzhr.zzb == null) {
            this.zzf = zzhr;
            this.zzg = str;
            this.zzh = t;
            this.zzk = z;
            this.zzl = false;
        } else {
            throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
        }
    }

    public static void zzc() {
        zze.incrementAndGet();
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public static void zzb(android.content.Context r3) {
        /*
            com.google.android.gms.internal.measurement.zzhu r0 = zzb
            if (r0 != 0) goto L_0x004d
            if (r3 != 0) goto L_0x0007
            goto L_0x004d
        L_0x0007:
            java.lang.Object r0 = zza
            monitor-enter(r0)
            com.google.android.gms.internal.measurement.zzhu r1 = zzb     // Catch:{ all -> 0x004a }
            if (r1 != 0) goto L_0x0048
            if (r3 == 0) goto L_0x0048
            monitor-enter(r0)     // Catch:{ all -> 0x004a }
            com.google.android.gms.internal.measurement.zzhu r1 = zzb     // Catch:{ all -> 0x0045 }
            android.content.Context r2 = r3.getApplicationContext()     // Catch:{ all -> 0x0045 }
            if (r2 != 0) goto L_0x001a
            goto L_0x001b
        L_0x001a:
            r3 = r2
        L_0x001b:
            if (r1 == 0) goto L_0x0023
            android.content.Context r2 = r1.zza()     // Catch:{ all -> 0x0045 }
            if (r2 == r3) goto L_0x0043
        L_0x0023:
            if (r1 == 0) goto L_0x002e
            com.google.android.gms.internal.measurement.zzgu.zzc()     // Catch:{ all -> 0x0045 }
            com.google.android.gms.internal.measurement.zzhw.zza()     // Catch:{ all -> 0x0045 }
            com.google.android.gms.internal.measurement.zzhc.zza()     // Catch:{ all -> 0x0045 }
        L_0x002e:
            com.google.android.gms.internal.measurement.zzhl r1 = new com.google.android.gms.internal.measurement.zzhl     // Catch:{ all -> 0x0045 }
            r1.<init>(r3)     // Catch:{ all -> 0x0045 }
            com.google.common.base.Supplier r1 = com.google.common.base.Suppliers.memoize(r1)     // Catch:{ all -> 0x0045 }
            com.google.android.gms.internal.measurement.zzgv r2 = new com.google.android.gms.internal.measurement.zzgv     // Catch:{ all -> 0x0045 }
            r2.<init>(r3, r1)     // Catch:{ all -> 0x0045 }
            zzb = r2     // Catch:{ all -> 0x0045 }
            java.util.concurrent.atomic.AtomicInteger r3 = zze     // Catch:{ all -> 0x0045 }
            r3.incrementAndGet()     // Catch:{ all -> 0x0045 }
        L_0x0043:
            monitor-exit(r0)     // Catch:{ all -> 0x0045 }
            goto L_0x0048
        L_0x0045:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0045 }
            throw r3     // Catch:{ all -> 0x004a }
        L_0x0048:
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            return
        L_0x004a:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            throw r3
        L_0x004d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhj.zzb(android.content.Context):void");
    }
}
