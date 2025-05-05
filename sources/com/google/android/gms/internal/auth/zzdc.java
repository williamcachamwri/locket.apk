package com.google.android.gms.internal.auth;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
public abstract class zzdc {
    private static final Object zza = new Object();
    public static final /* synthetic */ int zzd = 0;
    @Nullable
    private static volatile zzda zze = null;
    private static volatile boolean zzf = false;
    private static final AtomicReference zzg = new AtomicReference();
    private static final zzde zzh = new zzde(zzcu.zza);
    private static final AtomicInteger zzi = new AtomicInteger();
    final zzcz zzb;
    final String zzc;
    private final Object zzj;
    private volatile int zzk = -1;
    private volatile Object zzl;

    /* synthetic */ zzdc(zzcz zzcz, String str, Object obj, boolean z, zzdb zzdb) {
        if (zzcz.zza != null) {
            this.zzb = zzcz;
            this.zzc = str;
            this.zzj = obj;
            return;
        }
        throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
    }

    public static void zzc() {
        zzi.incrementAndGet();
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
    public static void zzd(android.content.Context r3) {
        /*
            com.google.android.gms.internal.auth.zzda r0 = zze
            if (r0 != 0) goto L_0x0048
            if (r3 != 0) goto L_0x0007
            goto L_0x0048
        L_0x0007:
            java.lang.Object r0 = zza
            monitor-enter(r0)
            com.google.android.gms.internal.auth.zzda r1 = zze     // Catch:{ all -> 0x0045 }
            if (r1 != 0) goto L_0x0043
            monitor-enter(r0)     // Catch:{ all -> 0x0045 }
            com.google.android.gms.internal.auth.zzda r1 = zze     // Catch:{ all -> 0x0040 }
            android.content.Context r2 = r3.getApplicationContext()     // Catch:{ all -> 0x0040 }
            if (r2 == 0) goto L_0x0018
            r3 = r2
        L_0x0018:
            if (r1 == 0) goto L_0x0020
            android.content.Context r1 = r1.zza()     // Catch:{ all -> 0x0040 }
            if (r1 == r3) goto L_0x003e
        L_0x0020:
            com.google.android.gms.internal.auth.zzcg.zzd()     // Catch:{ all -> 0x0040 }
            com.google.android.gms.internal.auth.zzdd.zzc()     // Catch:{ all -> 0x0040 }
            com.google.android.gms.internal.auth.zzco.zze()     // Catch:{ all -> 0x0040 }
            com.google.android.gms.internal.auth.zzct r1 = new com.google.android.gms.internal.auth.zzct     // Catch:{ all -> 0x0040 }
            r1.<init>(r3)     // Catch:{ all -> 0x0040 }
            com.google.android.gms.internal.auth.zzdj r1 = com.google.android.gms.internal.auth.zzdo.zza(r1)     // Catch:{ all -> 0x0040 }
            com.google.android.gms.internal.auth.zzcd r2 = new com.google.android.gms.internal.auth.zzcd     // Catch:{ all -> 0x0040 }
            r2.<init>(r3, r1)     // Catch:{ all -> 0x0040 }
            zze = r2     // Catch:{ all -> 0x0040 }
            java.util.concurrent.atomic.AtomicInteger r3 = zzi     // Catch:{ all -> 0x0040 }
            r3.incrementAndGet()     // Catch:{ all -> 0x0040 }
        L_0x003e:
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            goto L_0x0043
        L_0x0040:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            throw r3     // Catch:{ all -> 0x0045 }
        L_0x0043:
            monitor-exit(r0)     // Catch:{ all -> 0x0045 }
            return
        L_0x0045:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0045 }
            throw r3
        L_0x0048:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzdc.zzd(android.content.Context):void");
    }

    /* access modifiers changed from: package-private */
    public abstract Object zza(Object obj);

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00bd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzb() {
        /*
            r8 = this;
            java.util.concurrent.atomic.AtomicInteger r0 = zzi
            int r0 = r0.get()
            int r1 = r8.zzk
            if (r1 >= r0) goto L_0x00c8
            monitor-enter(r8)
            int r1 = r8.zzk     // Catch:{ all -> 0x00c5 }
            if (r1 >= r0) goto L_0x00c3
            com.google.android.gms.internal.auth.zzda r1 = zze     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.internal.auth.zzdh r2 = com.google.android.gms.internal.auth.zzdh.zzc()     // Catch:{ all -> 0x00c5 }
            r3 = 0
            if (r1 == 0) goto L_0x003b
            com.google.android.gms.internal.auth.zzdj r2 = r1.zzb()     // Catch:{ all -> 0x00c5 }
            java.lang.Object r2 = r2.zza()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.internal.auth.zzdh r2 = (com.google.android.gms.internal.auth.zzdh) r2     // Catch:{ all -> 0x00c5 }
            boolean r4 = r2.zzb()     // Catch:{ all -> 0x00c5 }
            if (r4 == 0) goto L_0x003b
            java.lang.Object r4 = r2.zza()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.internal.auth.zzci r4 = (com.google.android.gms.internal.auth.zzci) r4     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.internal.auth.zzcz r5 = r8.zzb     // Catch:{ all -> 0x00c5 }
            android.net.Uri r6 = r5.zza     // Catch:{ all -> 0x00c5 }
            java.lang.String r5 = r5.zzc     // Catch:{ all -> 0x00c5 }
            java.lang.String r7 = r8.zzc     // Catch:{ all -> 0x00c5 }
            java.lang.String r4 = r4.zza(r6, r3, r5, r7)     // Catch:{ all -> 0x00c5 }
            goto L_0x003c
        L_0x003b:
            r4 = r3
        L_0x003c:
            java.lang.String r5 = "Must call PhenotypeFlag.init() first"
            if (r1 == 0) goto L_0x00bd
            com.google.android.gms.internal.auth.zzcz r5 = r8.zzb     // Catch:{ all -> 0x00c5 }
            android.net.Uri r5 = r5.zza     // Catch:{ all -> 0x00c5 }
            if (r5 == 0) goto L_0x0065
            android.content.Context r6 = r1.zza()     // Catch:{ all -> 0x00c5 }
            boolean r5 = com.google.android.gms.internal.auth.zzcq.zza(r6, r5)     // Catch:{ all -> 0x00c5 }
            if (r5 == 0) goto L_0x0063
            android.content.Context r5 = r1.zza()     // Catch:{ all -> 0x00c5 }
            android.content.ContentResolver r5 = r5.getContentResolver()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.internal.auth.zzcz r6 = r8.zzb     // Catch:{ all -> 0x00c5 }
            android.net.Uri r6 = r6.zza     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.internal.auth.zzcs r7 = com.google.android.gms.internal.auth.zzcs.zza     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.internal.auth.zzcg r5 = com.google.android.gms.internal.auth.zzcg.zza(r5, r6, r7)     // Catch:{ all -> 0x00c5 }
            goto L_0x006f
        L_0x0063:
            r5 = r3
            goto L_0x006f
        L_0x0065:
            android.content.Context r5 = r1.zza()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.internal.auth.zzcs r6 = com.google.android.gms.internal.auth.zzcs.zza     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.internal.auth.zzdd r5 = com.google.android.gms.internal.auth.zzdd.zza(r5, r3, r6)     // Catch:{ all -> 0x00c5 }
        L_0x006f:
            if (r5 == 0) goto L_0x007e
            java.lang.String r6 = r8.zzc     // Catch:{ all -> 0x00c5 }
            java.lang.Object r5 = r5.zzb(r6)     // Catch:{ all -> 0x00c5 }
            if (r5 == 0) goto L_0x007e
            java.lang.Object r5 = r8.zza(r5)     // Catch:{ all -> 0x00c5 }
            goto L_0x007f
        L_0x007e:
            r5 = r3
        L_0x007f:
            if (r5 != 0) goto L_0x00a9
            com.google.android.gms.internal.auth.zzcz r5 = r8.zzb     // Catch:{ all -> 0x00c5 }
            boolean r5 = r5.zzd     // Catch:{ all -> 0x00c5 }
            if (r5 != 0) goto L_0x00a3
            android.content.Context r1 = r1.zza()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.internal.auth.zzco r1 = com.google.android.gms.internal.auth.zzco.zza(r1)     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.internal.auth.zzcz r5 = r8.zzb     // Catch:{ all -> 0x00c5 }
            boolean r5 = r5.zzd     // Catch:{ all -> 0x00c5 }
            if (r5 == 0) goto L_0x0097
            r5 = r3
            goto L_0x0099
        L_0x0097:
            java.lang.String r5 = r8.zzc     // Catch:{ all -> 0x00c5 }
        L_0x0099:
            java.lang.String r1 = r1.zzb(r5)     // Catch:{ all -> 0x00c5 }
            if (r1 == 0) goto L_0x00a3
            java.lang.Object r3 = r8.zza(r1)     // Catch:{ all -> 0x00c5 }
        L_0x00a3:
            if (r3 != 0) goto L_0x00a8
            java.lang.Object r5 = r8.zzj     // Catch:{ all -> 0x00c5 }
            goto L_0x00a9
        L_0x00a8:
            r5 = r3
        L_0x00a9:
            boolean r1 = r2.zzb()     // Catch:{ all -> 0x00c5 }
            if (r1 == 0) goto L_0x00b8
            if (r4 != 0) goto L_0x00b4
            java.lang.Object r5 = r8.zzj     // Catch:{ all -> 0x00c5 }
            goto L_0x00b8
        L_0x00b4:
            java.lang.Object r5 = r8.zza(r4)     // Catch:{ all -> 0x00c5 }
        L_0x00b8:
            r8.zzl = r5     // Catch:{ all -> 0x00c5 }
            r8.zzk = r0     // Catch:{ all -> 0x00c5 }
            goto L_0x00c3
        L_0x00bd:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00c5 }
            r0.<init>(r5)     // Catch:{ all -> 0x00c5 }
            throw r0     // Catch:{ all -> 0x00c5 }
        L_0x00c3:
            monitor-exit(r8)     // Catch:{ all -> 0x00c5 }
            goto L_0x00c8
        L_0x00c5:
            r0 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x00c5 }
            throw r0
        L_0x00c8:
            java.lang.Object r0 = r8.zzl
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzdc.zzb():java.lang.Object");
    }
}
