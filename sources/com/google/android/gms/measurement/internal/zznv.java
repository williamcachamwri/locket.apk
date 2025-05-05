package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzcf;
import com.google.android.gms.internal.measurement.zzdw;
import com.google.android.gms.internal.measurement.zzfr;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzjt;
import com.google.android.gms.internal.measurement.zzkb;
import com.google.android.gms.internal.measurement.zznm;
import com.google.android.gms.internal.measurement.zzpb;
import com.google.android.gms.internal.measurement.zzpn;
import com.google.android.gms.measurement.internal.zzje;
import com.google.common.net.HttpHeaders;
import expo.modules.contacts.Columns;
import expo.modules.interfaces.permissions.PermissionsResponse;
import io.sentry.protocol.App;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public class zznv implements zzjc {
    private static volatile zznv zza;
    private List<Long> zzaa;
    private long zzab;
    private final Map<String, zzje> zzac;
    private final Map<String, zzax> zzad;
    private final Map<String, zzb> zzae;
    private zzlk zzaf;
    private String zzag;
    private final zzor zzah;
    private zzhl zzb;
    private zzgr zzc;
    private zzal zzd;
    private zzgy zze;
    private zznq zzf;
    private zzt zzg;
    private final zzoo zzh;
    private zzli zzi;
    private zzmw zzj;
    private final zznu zzk;
    private zzhf zzl;
    /* access modifiers changed from: private */
    public final zzhy zzm;
    private boolean zzn;
    private boolean zzo;
    private long zzp;
    private List<Runnable> zzq;
    private final Set<String> zzr;
    private int zzs;
    private int zzt;
    private boolean zzu;
    private boolean zzv;
    private boolean zzw;
    private FileLock zzx;
    private FileChannel zzy;
    private List<Long> zzz;

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    private class zza implements zzas {
        zzfy.zzk zza;
        List<Long> zzb;
        List<zzfy.zzf> zzc;
        private long zzd;

        private static long zza(zzfy.zzf zzf) {
            return ((zzf.zzd() / 1000) / 60) / 60;
        }

        private zza() {
        }

        public final void zza(zzfy.zzk zzk) {
            Preconditions.checkNotNull(zzk);
            this.zza = zzk;
        }

        public final boolean zza(long j, zzfy.zzf zzf) {
            Preconditions.checkNotNull(zzf);
            if (this.zzc == null) {
                this.zzc = new ArrayList();
            }
            if (this.zzb == null) {
                this.zzb = new ArrayList();
            }
            if (!this.zzc.isEmpty() && zza(this.zzc.get(0)) != zza(zzf)) {
                return false;
            }
            long zzcb = this.zzd + ((long) zzf.zzcb());
            zznv.this.zze();
            if (zzcb >= ((long) Math.max(0, zzbh.zzi.zza(null).intValue()))) {
                return false;
            }
            this.zzd = zzcb;
            this.zzc.add(zzf);
            this.zzb.add(Long.valueOf(j));
            int size = this.zzc.size();
            zznv.this.zze();
            if (size >= Math.max(1, zzbh.zzj.zza(null).intValue())) {
                return false;
            }
            return true;
        }
    }

    private final int zza(String str, zzah zzah2) {
        zzjh zza2;
        if (this.zzb.zzb(str) == null) {
            zzah2.zza(zzje.zza.AD_PERSONALIZATION, zzak.FAILSAFE);
            return 1;
        }
        zzg zze2 = zzf().zze(str);
        if (zze2 == null || zzf.zza(zze2.zzak()).zza() != zzjh.POLICY || (zza2 = this.zzb.zza(str, zzje.zza.AD_PERSONALIZATION)) == zzjh.UNINITIALIZED) {
            zzah2.zza(zzje.zza.AD_PERSONALIZATION, zzak.REMOTE_DEFAULT);
            if (this.zzb.zzc(str, zzje.zza.AD_PERSONALIZATION)) {
                return 0;
            }
            return 1;
        }
        zzah2.zza(zzje.zza.AD_PERSONALIZATION, zzak.REMOTE_ENFORCED_DEFAULT);
        if (zza2 == zzjh.GRANTED) {
            return 0;
        }
        return 1;
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    private class zzb {
        final String zza;
        long zzb;

        private zzb(zznv zznv) {
            this(zznv, zznv.zzq().zzp());
        }

        private zzb(zznv zznv, String str) {
            this.zza = str;
            this.zzb = zznv.zzb().elapsedRealtime();
        }
    }

    private final int zza(FileChannel fileChannel) {
        zzl().zzt();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzj().zzg().zza("Bad channel to read from");
            return 0;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0);
            int read = fileChannel.read(allocate);
            if (read != 4) {
                if (read != -1) {
                    zzj().zzu().zza("Unexpected data length. Bytes read", Integer.valueOf(read));
                }
                return 0;
            }
            allocate.flip();
            return allocate.getInt();
        } catch (IOException e) {
            zzj().zzg().zza("Failed to read from channel", e);
            return 0;
        }
    }

    private final long zzx() {
        long currentTimeMillis = zzb().currentTimeMillis();
        zzmw zzmw = this.zzj;
        zzmw.zzal();
        zzmw.zzt();
        long zza2 = zzmw.zzf.zza();
        if (zza2 == 0) {
            zza2 = ((long) zzmw.zzq().zzv().nextInt(86400000)) + 1;
            zzmw.zzf.zza(zza2);
        }
        return ((((currentTimeMillis + zza2) / 1000) / 60) / 60) / 24;
    }

    public final Context zza() {
        return this.zzm.zza();
    }

    /* access modifiers changed from: package-private */
    public final Bundle zza(String str) {
        int i;
        zzl().zzt();
        zzs();
        if (zzi().zzb(str) == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        zzje zzb2 = zzb(str);
        bundle.putAll(zzb2.zzb());
        bundle.putAll(zza(str, zzd(str), zzb2, new zzah()).zzb());
        zzop zze2 = zzf().zze(str, "_npa");
        if (zze2 != null) {
            i = zze2.zze.equals(1L);
        } else {
            i = zza(str, new zzah());
        }
        bundle.putString("ad_personalization", i == 1 ? "denied" : PermissionsResponse.GRANTED_KEY);
        return bundle;
    }

    public final Clock zzb() {
        return ((zzhy) Preconditions.checkNotNull(this.zzm)).zzb();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0151  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x015e  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x016b  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0190  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01bb  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01c1  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01f5  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x023e  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x024e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzg zza(com.google.android.gms.measurement.internal.zzo r14) {
        /*
            r13 = this;
            com.google.android.gms.measurement.internal.zzhv r0 = r13.zzl()
            r0.zzt()
            r13.zzs()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r14)
            java.lang.String r0 = r14.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r0)
            java.lang.String r0 = r14.zzu
            boolean r0 = r0.isEmpty()
            r1 = 0
            if (r0 != 0) goto L_0x0029
            java.util.Map<java.lang.String, com.google.android.gms.measurement.internal.zznv$zzb> r0 = r13.zzae
            java.lang.String r2 = r14.zza
            com.google.android.gms.measurement.internal.zznv$zzb r3 = new com.google.android.gms.measurement.internal.zznv$zzb
            java.lang.String r4 = r14.zzu
            r3.<init>(r4)
            r0.put(r2, r3)
        L_0x0029:
            com.google.android.gms.measurement.internal.zzal r0 = r13.zzf()
            java.lang.String r2 = r14.zza
            com.google.android.gms.measurement.internal.zzg r0 = r0.zze(r2)
            java.lang.String r2 = r14.zza
            com.google.android.gms.measurement.internal.zzje r2 = r13.zzb((java.lang.String) r2)
            java.lang.String r3 = r14.zzt
            com.google.android.gms.measurement.internal.zzje r3 = com.google.android.gms.measurement.internal.zzje.zzb((java.lang.String) r3)
            com.google.android.gms.measurement.internal.zzje r2 = r2.zza((com.google.android.gms.measurement.internal.zzje) r3)
            boolean r3 = r2.zzg()
            if (r3 == 0) goto L_0x0054
            com.google.android.gms.measurement.internal.zzmw r3 = r13.zzj
            java.lang.String r4 = r14.zza
            boolean r5 = r14.zzn
            java.lang.String r3 = r3.zza((java.lang.String) r4, (boolean) r5)
            goto L_0x0056
        L_0x0054:
            java.lang.String r3 = ""
        L_0x0056:
            r4 = 0
            if (r0 != 0) goto L_0x007a
            com.google.android.gms.measurement.internal.zzg r0 = new com.google.android.gms.measurement.internal.zzg
            com.google.android.gms.measurement.internal.zzhy r5 = r13.zzm
            java.lang.String r6 = r14.zza
            r0.<init>(r5, r6)
            boolean r5 = r2.zzh()
            if (r5 == 0) goto L_0x006f
            java.lang.String r5 = r13.zza((com.google.android.gms.measurement.internal.zzje) r2)
            r0.zzb((java.lang.String) r5)
        L_0x006f:
            boolean r2 = r2.zzg()
            if (r2 == 0) goto L_0x013e
            r0.zzh((java.lang.String) r3)
            goto L_0x013e
        L_0x007a:
            boolean r5 = r2.zzg()
            if (r5 == 0) goto L_0x0127
            if (r3 == 0) goto L_0x0127
            java.lang.String r5 = r0.zzaj()
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x0127
            java.lang.String r5 = r0.zzaj()
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            r0.zzh((java.lang.String) r3)
            boolean r3 = r14.zzn
            if (r3 == 0) goto L_0x010f
            com.google.android.gms.measurement.internal.zzmw r3 = r13.zzj
            java.lang.String r6 = r14.zza
            android.util.Pair r3 = r3.zza((java.lang.String) r6, (com.google.android.gms.measurement.internal.zzje) r2)
            java.lang.Object r3 = r3.first
            java.lang.String r6 = "00000000-0000-0000-0000-000000000000"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x010f
            if (r5 != 0) goto L_0x010f
            boolean r3 = com.google.android.gms.internal.measurement.zznm.zza()
            if (r3 == 0) goto L_0x00c9
            com.google.android.gms.measurement.internal.zzag r3 = r13.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzbh.zzcy
            boolean r3 = r3.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r5)
            if (r3 == 0) goto L_0x00c9
            boolean r3 = r2.zzh()
            if (r3 != 0) goto L_0x00c9
            r2 = 1
            goto L_0x00d1
        L_0x00c9:
            java.lang.String r2 = r13.zza((com.google.android.gms.measurement.internal.zzje) r2)
            r0.zzb((java.lang.String) r2)
            r2 = r4
        L_0x00d1:
            com.google.android.gms.measurement.internal.zzal r3 = r13.zzf()
            java.lang.String r5 = r14.zza
            java.lang.String r6 = "_id"
            com.google.android.gms.measurement.internal.zzop r3 = r3.zze(r5, r6)
            if (r3 == 0) goto L_0x013f
            com.google.android.gms.measurement.internal.zzal r3 = r13.zzf()
            java.lang.String r5 = r14.zza
            java.lang.String r6 = "_lair"
            com.google.android.gms.measurement.internal.zzop r3 = r3.zze(r5, r6)
            if (r3 != 0) goto L_0x013f
            com.google.android.gms.common.util.Clock r3 = r13.zzb()
            long r9 = r3.currentTimeMillis()
            com.google.android.gms.measurement.internal.zzop r3 = new com.google.android.gms.measurement.internal.zzop
            java.lang.String r6 = r14.zza
            java.lang.String r7 = "auto"
            java.lang.String r8 = "_lair"
            r11 = 1
            java.lang.Long r11 = java.lang.Long.valueOf(r11)
            r5 = r3
            r5.<init>(r6, r7, r8, r9, r11)
            com.google.android.gms.measurement.internal.zzal r5 = r13.zzf()
            r5.zza((com.google.android.gms.measurement.internal.zzop) r3)
            goto L_0x013f
        L_0x010f:
            java.lang.String r3 = r0.zzad()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x013e
            boolean r3 = r2.zzh()
            if (r3 == 0) goto L_0x013e
            java.lang.String r2 = r13.zza((com.google.android.gms.measurement.internal.zzje) r2)
            r0.zzb((java.lang.String) r2)
            goto L_0x013e
        L_0x0127:
            java.lang.String r3 = r0.zzad()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x013e
            boolean r3 = r2.zzh()
            if (r3 == 0) goto L_0x013e
            java.lang.String r2 = r13.zza((com.google.android.gms.measurement.internal.zzje) r2)
            r0.zzb((java.lang.String) r2)
        L_0x013e:
            r2 = r4
        L_0x013f:
            java.lang.String r3 = r14.zzb
            r0.zzf((java.lang.String) r3)
            java.lang.String r3 = r14.zzp
            r0.zza((java.lang.String) r3)
            java.lang.String r3 = r14.zzk
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0156
            java.lang.String r3 = r14.zzk
            r0.zze((java.lang.String) r3)
        L_0x0156:
            long r5 = r14.zze
            r7 = 0
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x0163
            long r5 = r14.zze
            r0.zzn(r5)
        L_0x0163:
            java.lang.String r3 = r14.zzc
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0170
            java.lang.String r3 = r14.zzc
            r0.zzd((java.lang.String) r3)
        L_0x0170:
            long r5 = r14.zzj
            r0.zzb((long) r5)
            java.lang.String r3 = r14.zzd
            if (r3 == 0) goto L_0x017e
            java.lang.String r3 = r14.zzd
            r0.zzc((java.lang.String) r3)
        L_0x017e:
            long r5 = r14.zzf
            r0.zzk((long) r5)
            boolean r3 = r14.zzh
            r0.zzb((boolean) r3)
            java.lang.String r3 = r14.zzg
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0195
            java.lang.String r3 = r14.zzg
            r0.zzg((java.lang.String) r3)
        L_0x0195:
            boolean r3 = r14.zzn
            r0.zza((boolean) r3)
            java.lang.Boolean r3 = r14.zzq
            r0.zza((java.lang.Boolean) r3)
            long r5 = r14.zzr
            r0.zzl(r5)
            java.lang.String r3 = r14.zzv
            r0.zzj((java.lang.String) r3)
            boolean r3 = com.google.android.gms.internal.measurement.zzny.zza()
            if (r3 == 0) goto L_0x01c1
            com.google.android.gms.measurement.internal.zzag r3 = r13.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzbh.zzbv
            boolean r3 = r3.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r5)
            if (r3 == 0) goto L_0x01c1
            java.util.List<java.lang.String> r1 = r14.zzs
            r0.zza((java.util.List<java.lang.String>) r1)
            goto L_0x01d6
        L_0x01c1:
            boolean r3 = com.google.android.gms.internal.measurement.zzny.zza()
            if (r3 == 0) goto L_0x01d6
            com.google.android.gms.measurement.internal.zzag r3 = r13.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzbh.zzbu
            boolean r3 = r3.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r5)
            if (r3 == 0) goto L_0x01d6
            r0.zza((java.util.List<java.lang.String>) r1)
        L_0x01d6:
            boolean r1 = com.google.android.gms.internal.measurement.zzpu.zza()
            if (r1 == 0) goto L_0x020b
            com.google.android.gms.measurement.internal.zzag r1 = r13.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzbh.zzbx
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r3)
            if (r1 == 0) goto L_0x020b
            r13.zzq()
            java.lang.String r1 = r0.zzac()
            boolean r1 = com.google.android.gms.measurement.internal.zzos.zzf(r1)
            if (r1 == 0) goto L_0x020b
            boolean r1 = r14.zzw
            r0.zzc((boolean) r1)
            com.google.android.gms.measurement.internal.zzag r1 = r13.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzbh.zzby
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r3)
            if (r1 == 0) goto L_0x020b
            java.lang.String r1 = r14.zzac
            r0.zzk((java.lang.String) r1)
        L_0x020b:
            boolean r1 = com.google.android.gms.internal.measurement.zzpn.zza()
            if (r1 == 0) goto L_0x0222
            com.google.android.gms.measurement.internal.zzag r1 = r13.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzbh.zzch
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r3)
            if (r1 == 0) goto L_0x0222
            int r1 = r14.zzaa
            r0.zza((int) r1)
        L_0x0222:
            long r5 = r14.zzx
            r0.zzt(r5)
            java.lang.String r14 = r14.zzad
            r0.zzi((java.lang.String) r14)
            boolean r14 = com.google.android.gms.internal.measurement.zznm.zza()
            if (r14 == 0) goto L_0x024e
            com.google.android.gms.measurement.internal.zzag r14 = r13.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r1 = com.google.android.gms.measurement.internal.zzbh.zzcy
            boolean r14 = r14.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r1)
            if (r14 == 0) goto L_0x024e
            boolean r14 = r0.zzas()
            if (r14 != 0) goto L_0x0246
            if (r2 == 0) goto L_0x025b
        L_0x0246:
            com.google.android.gms.measurement.internal.zzal r14 = r13.zzf()
            r14.zza((com.google.android.gms.measurement.internal.zzg) r0, (boolean) r2, (boolean) r4)
            goto L_0x025b
        L_0x024e:
            boolean r14 = r0.zzas()
            if (r14 == 0) goto L_0x025b
            com.google.android.gms.measurement.internal.zzal r14 = r13.zzf()
            r14.zza((com.google.android.gms.measurement.internal.zzg) r0, (boolean) r4, (boolean) r4)
        L_0x025b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zza(com.google.android.gms.measurement.internal.zzo):com.google.android.gms.measurement.internal.zzg");
    }

    private final zzo zzc(String str) {
        String str2 = str;
        zzg zze2 = zzf().zze(str2);
        if (zze2 == null || TextUtils.isEmpty(zze2.zzaf())) {
            zzj().zzc().zza("No app data available; dropping", str2);
            return null;
        }
        Boolean zza2 = zza(zze2);
        if (zza2 == null || zza2.booleanValue()) {
            zzg zzg2 = zze2;
            return new zzo(str, zze2.zzah(), zze2.zzaf(), zze2.zze(), zze2.zzae(), zze2.zzq(), zze2.zzn(), (String) null, zze2.zzar(), false, zze2.zzag(), zze2.zzd(), 0, 0, zzg2.zzaq(), false, zzg2.zzaa(), zzg2.zzx(), zzg2.zzo(), zzg2.zzan(), (String) null, zzb(str).zzf(), "", (String) null, zzg2.zzat(), zzg2.zzw(), zzb(str).zza(), zzd(str).zzf(), zzg2.zza(), zzg2.zzf(), zzg2.zzam(), zzg2.zzak());
        }
        zzj().zzg().zza("App version does not match; dropping. appId", zzgo.zza(str));
        return null;
    }

    public final zzt zzc() {
        return (zzt) zza((zznr) this.zzg);
    }

    public final zzab zzd() {
        return this.zzm.zzd();
    }

    public final zzag zze() {
        return ((zzhy) Preconditions.checkNotNull(this.zzm)).zzf();
    }

    public final zzal zzf() {
        return (zzal) zza((zznr) this.zzd);
    }

    private final zzax zza(String str, zzax zzax, zzje zzje, zzah zzah2) {
        zzjh zzjh;
        int i = 90;
        boolean z = true;
        if (zzi().zzb(str) == null) {
            if (zzax.zzc() == zzjh.DENIED) {
                i = zzax.zza();
                zzah2.zza(zzje.zza.AD_USER_DATA, i);
            } else {
                zzah2.zza(zzje.zza.AD_USER_DATA, zzak.FAILSAFE);
            }
            return new zzax((Boolean) false, i, (Boolean) true, "-");
        }
        zzjh zzc2 = zzax.zzc();
        if (zzc2 == zzjh.GRANTED || zzc2 == zzjh.DENIED) {
            i = zzax.zza();
            zzah2.zza(zzje.zza.AD_USER_DATA, i);
        } else {
            if (zzc2 != zzjh.POLICY || (zzjh = this.zzb.zza(str, zzje.zza.AD_USER_DATA)) == zzjh.UNINITIALIZED) {
                zzje.zza zzb2 = this.zzb.zzb(str, zzje.zza.AD_USER_DATA);
                zzjh zzc3 = zzje.zzc();
                if (!(zzc3 == zzjh.GRANTED || zzc3 == zzjh.DENIED)) {
                    z = false;
                }
                if (zzb2 != zzje.zza.AD_STORAGE || !z) {
                    zzah2.zza(zzje.zza.AD_USER_DATA, zzak.REMOTE_DEFAULT);
                    if (this.zzb.zzc(str, zzje.zza.AD_USER_DATA)) {
                        zzjh = zzjh.GRANTED;
                    } else {
                        zzjh = zzjh.DENIED;
                    }
                } else {
                    zzah2.zza(zzje.zza.AD_USER_DATA, zzak.REMOTE_DELEGATION);
                    zzc2 = zzc3;
                }
            } else {
                zzah2.zza(zzje.zza.AD_USER_DATA, zzak.REMOTE_ENFORCED_DEFAULT);
            }
            zzc2 = zzjh;
        }
        boolean zzm2 = this.zzb.zzm(str);
        SortedSet<String> zzh2 = zzi().zzh(str);
        if (zzc2 == zzjh.DENIED || zzh2.isEmpty()) {
            return new zzax((Boolean) false, i, Boolean.valueOf(zzm2), "-");
        }
        Boolean valueOf = Boolean.valueOf(zzm2);
        String str2 = "";
        if (zzm2) {
            str2 = TextUtils.join(str2, zzh2);
        }
        return new zzax((Boolean) true, i, valueOf, str2);
    }

    private final zzax zzd(String str) {
        zzl().zzt();
        zzs();
        zzax zzax = this.zzad.get(str);
        if (zzax != null) {
            return zzax;
        }
        zzax zzg2 = zzf().zzg(str);
        this.zzad.put(str, zzg2);
        return zzg2;
    }

    public final zzgh zzg() {
        return this.zzm.zzk();
    }

    public final zzgo zzj() {
        return ((zzhy) Preconditions.checkNotNull(this.zzm)).zzj();
    }

    public final zzgr zzh() {
        return (zzgr) zza((zznr) this.zzc);
    }

    private final zzgy zzy() {
        zzgy zzgy = this.zze;
        if (zzgy != null) {
            return zzgy;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    public final zzhl zzi() {
        return (zzhl) zza((zznr) this.zzb);
    }

    public final zzhv zzl() {
        return ((zzhy) Preconditions.checkNotNull(this.zzm)).zzl();
    }

    /* access modifiers changed from: package-private */
    public final zzhy zzk() {
        return this.zzm;
    }

    /* access modifiers changed from: package-private */
    public final zzje zzb(String str) {
        zzl().zzt();
        zzs();
        zzje zzje = this.zzac.get(str);
        if (zzje == null) {
            zzje = zzf().zzi(str);
            if (zzje == null) {
                zzje = zzje.zza;
            }
            zza(str, zzje);
        }
        return zzje;
    }

    public final zzli zzm() {
        return (zzli) zza((zznr) this.zzi);
    }

    public final zzmw zzn() {
        return this.zzj;
    }

    private final zznq zzz() {
        return (zznq) zza((zznr) this.zzf);
    }

    private static zznr zza(zznr zznr) {
        if (zznr == null) {
            throw new IllegalStateException("Upload Component not created");
        } else if (zznr.zzan()) {
            return zznr;
        } else {
            throw new IllegalStateException("Component not initialized: " + String.valueOf(zznr.getClass()));
        }
    }

    public final zznu zzo() {
        return this.zzk;
    }

    public static zznv zza(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zza == null) {
            synchronized (zznv.class) {
                if (zza == null) {
                    zza = new zznv((zzok) Preconditions.checkNotNull(new zzok(context)));
                }
            }
        }
        return zza;
    }

    public final zzoo zzp() {
        return (zzoo) zza((zznr) this.zzh);
    }

    public final zzos zzq() {
        return ((zzhy) Preconditions.checkNotNull(this.zzm)).zzt();
    }

    private final Boolean zza(zzg zzg2) {
        try {
            if (zzg2.zze() != -2147483648L) {
                if (zzg2.zze() == ((long) Wrappers.packageManager(this.zzm.zza()).getPackageInfo(zzg2.zzac(), 0).versionCode)) {
                    return true;
                }
            } else {
                String str = Wrappers.packageManager(this.zzm.zza()).getPackageInfo(zzg2.zzac(), 0).versionName;
                String zzaf2 = zzg2.zzaf();
                if (zzaf2 != null && zzaf2.equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private static Boolean zzh(zzo zzo2) {
        Boolean bool = zzo2.zzq;
        if (TextUtils.isEmpty(zzo2.zzad)) {
            return bool;
        }
        int i = zzoa.zza[zzf.zza(zzo2.zzad).zza().ordinal()];
        if (i != 1) {
            if (i == 2) {
                return false;
            }
            if (i == 3) {
                return true;
            }
            if (i != 4) {
                return bool;
            }
        }
        return null;
    }

    private final String zza(zzje zzje) {
        if (!zzje.zzh()) {
            return null;
        }
        byte[] bArr = new byte[16];
        zzq().zzv().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, bArr)});
    }

    /* access modifiers changed from: package-private */
    public final String zzb(zzo zzo2) {
        try {
            return (String) zzl().zza(new zzog(this, zzo2)).get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzj().zzg().zza("Failed to get app instance id. appId", zzgo.zza(zzo2.zza), e);
            return null;
        }
    }

    private static String zza(Map<String, List<String>> map, String str) {
        if (map == null) {
            return null;
        }
        for (Map.Entry next : map.entrySet()) {
            if (str.equalsIgnoreCase((String) next.getKey())) {
                if (((List) next.getValue()).isEmpty()) {
                    return null;
                }
                return (String) ((List) next.getValue()).get(0);
            }
        }
        return null;
    }

    static /* synthetic */ void zza(zznv zznv, zzok zzok) {
        zznv.zzl().zzt();
        zznv.zzl = new zzhf(zznv);
        zzal zzal = new zzal(zznv);
        zzal.zzam();
        zznv.zzd = zzal;
        zznv.zze().zza((zzai) Preconditions.checkNotNull(zznv.zzb));
        zzmw zzmw = new zzmw(zznv);
        zzmw.zzam();
        zznv.zzj = zzmw;
        zzt zzt2 = new zzt(zznv);
        zzt2.zzam();
        zznv.zzg = zzt2;
        zzli zzli = new zzli(zznv);
        zzli.zzam();
        zznv.zzi = zzli;
        zznq zznq = new zznq(zznv);
        zznq.zzam();
        zznv.zzf = zznq;
        zznv.zze = new zzgy(zznv);
        if (zznv.zzs != zznv.zzt) {
            zznv.zzj().zzg().zza("Not all upload components initialized", Integer.valueOf(zznv.zzs), Integer.valueOf(zznv.zzt));
        }
        zznv.zzn = true;
    }

    private zznv(zzok zzok) {
        this(zzok, (zzhy) null);
    }

    private zznv(zzok zzok, zzhy zzhy) {
        this.zzn = false;
        this.zzr = new HashSet();
        this.zzah = new zzof(this);
        Preconditions.checkNotNull(zzok);
        this.zzm = zzhy.zza(zzok.zza, (zzdw) null, (Long) null);
        this.zzab = -1;
        this.zzk = new zznu(this);
        zzoo zzoo = new zzoo(this);
        zzoo.zzam();
        this.zzh = zzoo;
        zzgr zzgr = new zzgr(this);
        zzgr.zzam();
        this.zzc = zzgr;
        zzhl zzhl = new zzhl(this);
        zzhl.zzam();
        this.zzb = zzhl;
        this.zzac = new HashMap();
        this.zzad = new HashMap();
        this.zzae = new HashMap();
        zzl().zzb((Runnable) new zznx(this, zzok));
    }

    /* access modifiers changed from: package-private */
    public final void zza(Runnable runnable) {
        zzl().zzt();
        if (this.zzq == null) {
            this.zzq = new ArrayList();
        }
        this.zzq.add(runnable);
    }

    /* access modifiers changed from: package-private */
    public final void zzr() {
        zzl().zzt();
        zzs();
        if (!this.zzo) {
            this.zzo = true;
            if (zzae()) {
                int zza2 = zza(this.zzy);
                int zzab2 = this.zzm.zzh().zzab();
                zzl().zzt();
                if (zza2 > zzab2) {
                    zzj().zzg().zza("Panic: can't downgrade version. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzab2));
                } else if (zza2 >= zzab2) {
                } else {
                    if (zza(zzab2, this.zzy)) {
                        zzj().zzp().zza("Storage version upgraded. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzab2));
                    } else {
                        zzj().zzg().zza("Storage version upgrade failed. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzab2));
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzs() {
        if (!this.zzn) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    private final void zzaa() {
        zzl().zzt();
        if (this.zzu || this.zzv || this.zzw) {
            zzj().zzp().zza("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzu), Boolean.valueOf(this.zzv), Boolean.valueOf(this.zzw));
            return;
        }
        zzj().zzp().zza("Stopping uploading service(s)");
        List<Runnable> list = this.zzq;
        if (list != null) {
            for (Runnable run : list) {
                run.run();
            }
            ((List) Preconditions.checkNotNull(this.zzq)).clear();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, zzfy.zzk.zza zza2) {
        int zza3;
        int indexOf;
        Set<String> zzg2 = zzi().zzg(str);
        if (zzg2 != null) {
            zza2.zzd((Iterable<String>) zzg2);
        }
        if (zzi().zzp(str)) {
            zza2.zzj();
        }
        if (zzi().zzs(str)) {
            String zzy2 = zza2.zzy();
            if (!TextUtils.isEmpty(zzy2) && (indexOf = zzy2.indexOf(".")) != -1) {
                zza2.zzo(zzy2.substring(0, indexOf));
            }
        }
        if (zzi().zzt(str) && (zza3 = zzoo.zza(zza2, (String) Columns.ID)) != -1) {
            zza2.zzc(zza3);
        }
        if (zzi().zzr(str)) {
            zza2.zzk();
        }
        if (zzi().zzo(str)) {
            zza2.zzh();
            if (!zznm.zza() || !zze().zza(zzbh.zzcy) || zzb(str).zzh()) {
                zzb zzb2 = this.zzae.get(str);
                if (zzb2 == null || zzb2.zzb + zze().zzc(str, zzbh.zzaw) < zzb().elapsedRealtime()) {
                    zzb2 = new zzb();
                    this.zzae.put(str, zzb2);
                }
                zza2.zzk(zzb2.zza);
            }
        }
        if (zzi().zzq(str)) {
            zza2.zzr();
        }
    }

    private final void zzb(zzg zzg2) {
        zzg zzg3 = zzg2;
        zzl().zzt();
        if (!TextUtils.isEmpty(zzg2.zzah()) || !TextUtils.isEmpty(zzg2.zzaa())) {
            ArrayMap arrayMap = null;
            if (!zzpb.zza() || !zze().zza(zzbh.zzcf)) {
                String zza2 = this.zzk.zza(zzg3);
                try {
                    String str = (String) Preconditions.checkNotNull(zzg2.zzac());
                    URL url = new URL(zza2);
                    zzj().zzp().zza("Fetching remote configuration", str);
                    zzfr.zzd zzc2 = zzi().zzc(str);
                    String zze2 = zzi().zze(str);
                    if (zzc2 != null) {
                        if (!TextUtils.isEmpty(zze2)) {
                            arrayMap = new ArrayMap();
                            arrayMap.put(HttpHeaders.IF_MODIFIED_SINCE, zze2);
                        }
                        String zzd2 = zzi().zzd(str);
                        if (!TextUtils.isEmpty(zzd2)) {
                            if (arrayMap == null) {
                                arrayMap = new ArrayMap();
                            }
                            arrayMap.put(HttpHeaders.IF_NONE_MATCH, zzd2);
                        }
                    }
                    this.zzu = true;
                    zzgr zzh2 = zzh();
                    zzod zzod = new zzod(this);
                    zzh2.zzt();
                    zzh2.zzal();
                    Preconditions.checkNotNull(url);
                    Preconditions.checkNotNull(zzod);
                    zzh2.zzl().zza((Runnable) new zzgw(zzh2, str, url, (byte[]) null, arrayMap, zzod));
                } catch (MalformedURLException unused) {
                    zzj().zzg().zza("Failed to parse config URL. Not fetching. appId", zzgo.zza(zzg2.zzac()), zza2);
                }
            } else {
                String str2 = (String) Preconditions.checkNotNull(zzg2.zzac());
                zzj().zzp().zza("Fetching remote configuration", str2);
                zzfr.zzd zzc3 = zzi().zzc(str2);
                String zze3 = zzi().zze(str2);
                if (zzc3 != null) {
                    if (!TextUtils.isEmpty(zze3)) {
                        arrayMap = new ArrayMap();
                        arrayMap.put(HttpHeaders.IF_MODIFIED_SINCE, zze3);
                    }
                    String zzd3 = zzi().zzd(str2);
                    if (!TextUtils.isEmpty(zzd3)) {
                        if (arrayMap == null) {
                            arrayMap = new ArrayMap();
                        }
                        arrayMap.put(HttpHeaders.IF_NONE_MATCH, zzd3);
                    }
                }
                ArrayMap arrayMap2 = arrayMap;
                this.zzu = true;
                zzgr zzh3 = zzh();
                zzny zzny = new zzny(this);
                zzh3.zzt();
                zzh3.zzal();
                Preconditions.checkNotNull(zzg2);
                Preconditions.checkNotNull(zzny);
                String zza3 = zzh3.zzo().zza(zzg3);
                try {
                    zzgr zzgr = zzh3;
                    zzh3.zzl().zza((Runnable) new zzgw(zzgr, zzg2.zzac(), new URI(zza3).toURL(), (byte[]) null, arrayMap2, zzny));
                } catch (IllegalArgumentException | MalformedURLException | URISyntaxException unused2) {
                    zzh3.zzj().zzg().zza("Failed to parse config URL. Not fetching. appId", zzgo.zza(zzg2.zzac()), zza3);
                }
            }
        } else {
            zza((String) Preconditions.checkNotNull(zzg2.zzac()), 204, (Throwable) null, (byte[]) null, (Map<String, List<String>>) null);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzg zzg2, zzfy.zzk.zza zza2) {
        zzfy.zzo zzo2;
        zzl().zzt();
        zzs();
        zzah zza3 = zzah.zza(zza2.zzv());
        String zzac2 = zzg2.zzac();
        zzl().zzt();
        zzs();
        zzje zzb2 = zzb(zzac2);
        int i = zzoa.zza[zzb2.zzc().ordinal()];
        if (i == 1) {
            zza3.zza(zzje.zza.AD_STORAGE, zzak.REMOTE_ENFORCED_DEFAULT);
        } else if (i == 2 || i == 3) {
            zza3.zza(zzje.zza.AD_STORAGE, zzb2.zza());
        } else {
            zza3.zza(zzje.zza.AD_STORAGE, zzak.FAILSAFE);
        }
        int i2 = zzoa.zza[zzb2.zzd().ordinal()];
        if (i2 == 1) {
            zza3.zza(zzje.zza.ANALYTICS_STORAGE, zzak.REMOTE_ENFORCED_DEFAULT);
        } else if (i2 == 2 || i2 == 3) {
            zza3.zza(zzje.zza.ANALYTICS_STORAGE, zzb2.zza());
        } else {
            zza3.zza(zzje.zza.ANALYTICS_STORAGE, zzak.FAILSAFE);
        }
        String zzac3 = zzg2.zzac();
        zzl().zzt();
        zzs();
        zzax zza4 = zza(zzac3, zzd(zzac3), zzb(zzac3), zza3);
        zza2.zzb(((Boolean) Preconditions.checkNotNull(zza4.zzd())).booleanValue());
        if (!TextUtils.isEmpty(zza4.zze())) {
            zza2.zzh(zza4.zze());
        }
        zzl().zzt();
        zzs();
        Iterator<zzfy.zzo> it = zza2.zzab().iterator();
        while (true) {
            if (!it.hasNext()) {
                zzo2 = null;
                break;
            }
            zzo2 = it.next();
            if ("_npa".equals(zzo2.zzg())) {
                break;
            }
        }
        if (zzo2 == null) {
            int zza5 = zza(zzg2.zzac(), zza3);
            zza2.zza((zzfy.zzo) ((zzjt) zzfy.zzo.zze().zza("_npa").zzb(zzb().currentTimeMillis()).zza((long) zza5).zzai()));
            zzj().zzp().zza("Setting user property", "non_personalized_ads(_npa)", Integer.valueOf(zza5));
        } else if (zza3.zza(zzje.zza.AD_PERSONALIZATION) == zzak.UNSET) {
            zzop zze2 = zzf().zze(zzg2.zzac(), "_npa");
            if (zze2 == null) {
                Boolean zzx2 = zzg2.zzx();
                if (zzx2 == null || ((zzx2 == Boolean.TRUE && zzo2.zzc() != 1) || (zzx2 == Boolean.FALSE && zzo2.zzc() != 0))) {
                    zza3.zza(zzje.zza.AD_PERSONALIZATION, zzak.API);
                } else {
                    zza3.zza(zzje.zza.AD_PERSONALIZATION, zzak.MANIFEST);
                }
            } else if ("tcf".equals(zze2.zzb)) {
                zza3.zza(zzje.zza.AD_PERSONALIZATION, zzak.TCF);
            } else if (App.TYPE.equals(zze2.zzb)) {
                zza3.zza(zzje.zza.AD_PERSONALIZATION, zzak.API);
            } else {
                zza3.zza(zzje.zza.AD_PERSONALIZATION, zzak.MANIFEST);
            }
        }
        zza2.zzf(zza3.toString());
        boolean zzm2 = this.zzb.zzm(zzg2.zzac());
        List<zzfy.zzf> zzaa2 = zza2.zzaa();
        int i3 = 0;
        for (int i4 = 0; i4 < zzaa2.size(); i4++) {
            if ("_tcf".equals(zzaa2.get(i4).zzg())) {
                zzjt.zzb zzcd = zzaa2.get(i4).zzcd();
                zzjt.zzb zzb3 = zzcd;
                zzfy.zzf.zza zza6 = (zzfy.zzf.zza) zzcd;
                List<zzfy.zzh> zzf2 = zza6.zzf();
                while (true) {
                    if (i3 >= zzf2.size()) {
                        break;
                    } else if ("_tcfd".equals(zzf2.get(i3).zzg())) {
                        zza6.zza(i3, zzfy.zzh.zze().zza("_tcfd").zzb(zznm.zza(zzf2.get(i3).zzh(), zzm2)));
                        break;
                    } else {
                        i3++;
                    }
                }
                zza2.zza(i4, zza6);
                return;
            }
        }
    }

    private static void zza(zzfy.zzf.zza zza2, int i, String str) {
        List<zzfy.zzh> zzf2 = zza2.zzf();
        int i2 = 0;
        while (i2 < zzf2.size()) {
            if (!"_err".equals(zzf2.get(i2).zzg())) {
                i2++;
            } else {
                return;
            }
        }
        zza2.zza((zzfy.zzh) ((zzjt) zzfy.zzh.zze().zza("_err").zza(Long.valueOf((long) i).longValue()).zzai())).zza((zzfy.zzh) ((zzjt) zzfy.zzh.zze().zza("_ev").zzb(str).zzai()));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x002b, code lost:
        r4 = r1.zzag;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.measurement.internal.zzbf r20, com.google.android.gms.measurement.internal.zzo r21) {
        /*
            r19 = this;
            r1 = r19
            r0 = r21
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r21)
            java.lang.String r2 = r0.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2)
            com.google.android.gms.measurement.internal.zzhv r2 = r19.zzl()
            r2.zzt()
            r19.zzs()
            java.lang.String r2 = r0.zza
            r3 = r20
            long r10 = r3.zzd
            com.google.android.gms.measurement.internal.zzgs r3 = com.google.android.gms.measurement.internal.zzgs.zza(r20)
            com.google.android.gms.measurement.internal.zzhv r4 = r19.zzl()
            r4.zzt()
            com.google.android.gms.measurement.internal.zzlk r4 = r1.zzaf
            if (r4 == 0) goto L_0x0039
            java.lang.String r4 = r1.zzag
            if (r4 == 0) goto L_0x0039
            boolean r4 = r4.equals(r2)
            if (r4 != 0) goto L_0x0036
            goto L_0x0039
        L_0x0036:
            com.google.android.gms.measurement.internal.zzlk r4 = r1.zzaf
            goto L_0x003a
        L_0x0039:
            r4 = 0
        L_0x003a:
            android.os.Bundle r5 = r3.zzc
            r12 = 0
            com.google.android.gms.measurement.internal.zzos.zza((com.google.android.gms.measurement.internal.zzlk) r4, (android.os.Bundle) r5, (boolean) r12)
            com.google.android.gms.measurement.internal.zzbf r3 = r3.zza()
            r19.zzp()
            boolean r4 = com.google.android.gms.measurement.internal.zzoo.zza((com.google.android.gms.measurement.internal.zzbf) r3, (com.google.android.gms.measurement.internal.zzo) r0)
            if (r4 != 0) goto L_0x004e
            return
        L_0x004e:
            boolean r4 = r0.zzh
            if (r4 != 0) goto L_0x0056
            r1.zza((com.google.android.gms.measurement.internal.zzo) r0)
            return
        L_0x0056:
            java.util.List<java.lang.String> r4 = r0.zzs
            if (r4 == 0) goto L_0x0099
            java.util.List<java.lang.String> r4 = r0.zzs
            java.lang.String r5 = r3.zza
            boolean r4 = r4.contains(r5)
            if (r4 == 0) goto L_0x0087
            com.google.android.gms.measurement.internal.zzbe r4 = r3.zzb
            android.os.Bundle r4 = r4.zzb()
            java.lang.String r5 = "ga_safelisted"
            r6 = 1
            r4.putLong(r5, r6)
            com.google.android.gms.measurement.internal.zzbf r5 = new com.google.android.gms.measurement.internal.zzbf
            java.lang.String r14 = r3.zza
            com.google.android.gms.measurement.internal.zzbe r15 = new com.google.android.gms.measurement.internal.zzbe
            r15.<init>(r4)
            java.lang.String r4 = r3.zzc
            long r6 = r3.zzd
            r13 = r5
            r16 = r4
            r17 = r6
            r13.<init>(r14, r15, r16, r17)
            goto L_0x009a
        L_0x0087:
            com.google.android.gms.measurement.internal.zzgo r0 = r19.zzj()
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzc()
            java.lang.String r4 = r3.zza
            java.lang.String r3 = r3.zzc
            java.lang.String r5 = "Dropping non-safelisted event. appId, event name, origin"
            r0.zza(r5, r2, r4, r3)
            return
        L_0x0099:
            r13 = r3
        L_0x009a:
            com.google.android.gms.measurement.internal.zzal r3 = r19.zzf()
            r3.zzp()
            com.google.android.gms.measurement.internal.zzal r3 = r19.zzf()     // Catch:{ all -> 0x02fe }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2)     // Catch:{ all -> 0x02fe }
            r3.zzt()     // Catch:{ all -> 0x02fe }
            r3.zzal()     // Catch:{ all -> 0x02fe }
            r4 = 0
            int r4 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            r5 = 2
            r14 = 1
            if (r4 >= 0) goto L_0x00d0
            com.google.android.gms.measurement.internal.zzgo r3 = r3.zzj()     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzu()     // Catch:{ all -> 0x02fe }
            java.lang.String r6 = "Invalid time querying timed out conditional properties"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r2)     // Catch:{ all -> 0x02fe }
            java.lang.Long r8 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x02fe }
            r3.zza(r6, r7, r8)     // Catch:{ all -> 0x02fe }
            java.util.List r3 = java.util.Collections.emptyList()     // Catch:{ all -> 0x02fe }
            goto L_0x00e0
        L_0x00d0:
            java.lang.String r6 = "active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout"
            java.lang.String[] r7 = new java.lang.String[r5]     // Catch:{ all -> 0x02fe }
            r7[r12] = r2     // Catch:{ all -> 0x02fe }
            java.lang.String r8 = java.lang.String.valueOf(r10)     // Catch:{ all -> 0x02fe }
            r7[r14] = r8     // Catch:{ all -> 0x02fe }
            java.util.List r3 = r3.zza((java.lang.String) r6, (java.lang.String[]) r7)     // Catch:{ all -> 0x02fe }
        L_0x00e0:
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x02fe }
        L_0x00e4:
            boolean r6 = r3.hasNext()     // Catch:{ all -> 0x02fe }
            if (r6 == 0) goto L_0x0130
            java.lang.Object r6 = r3.next()     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzae r6 = (com.google.android.gms.measurement.internal.zzae) r6     // Catch:{ all -> 0x02fe }
            if (r6 == 0) goto L_0x00e4
            com.google.android.gms.measurement.internal.zzgo r7 = r19.zzj()     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzp()     // Catch:{ all -> 0x02fe }
            java.lang.String r8 = "User property timed out"
            java.lang.String r9 = r6.zza     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzhy r15 = r1.zzm     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzgh r15 = r15.zzk()     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzon r14 = r6.zzc     // Catch:{ all -> 0x02fe }
            java.lang.String r14 = r14.zza     // Catch:{ all -> 0x02fe }
            java.lang.String r14 = r15.zzc(r14)     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzon r15 = r6.zzc     // Catch:{ all -> 0x02fe }
            java.lang.Object r15 = r15.zza()     // Catch:{ all -> 0x02fe }
            r7.zza(r8, r9, r14, r15)     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzbf r7 = r6.zzg     // Catch:{ all -> 0x02fe }
            if (r7 == 0) goto L_0x0123
            com.google.android.gms.measurement.internal.zzbf r7 = new com.google.android.gms.measurement.internal.zzbf     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzbf r8 = r6.zzg     // Catch:{ all -> 0x02fe }
            r7.<init>(r8, r10)     // Catch:{ all -> 0x02fe }
            r1.zzc(r7, r0)     // Catch:{ all -> 0x02fe }
        L_0x0123:
            com.google.android.gms.measurement.internal.zzal r7 = r19.zzf()     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzon r6 = r6.zzc     // Catch:{ all -> 0x02fe }
            java.lang.String r6 = r6.zza     // Catch:{ all -> 0x02fe }
            r7.zza((java.lang.String) r2, (java.lang.String) r6)     // Catch:{ all -> 0x02fe }
            r14 = 1
            goto L_0x00e4
        L_0x0130:
            com.google.android.gms.measurement.internal.zzal r3 = r19.zzf()     // Catch:{ all -> 0x02fe }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2)     // Catch:{ all -> 0x02fe }
            r3.zzt()     // Catch:{ all -> 0x02fe }
            r3.zzal()     // Catch:{ all -> 0x02fe }
            if (r4 >= 0) goto L_0x0159
            com.google.android.gms.measurement.internal.zzgo r3 = r3.zzj()     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzu()     // Catch:{ all -> 0x02fe }
            java.lang.String r6 = "Invalid time querying expired conditional properties"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r2)     // Catch:{ all -> 0x02fe }
            java.lang.Long r8 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x02fe }
            r3.zza(r6, r7, r8)     // Catch:{ all -> 0x02fe }
            java.util.List r3 = java.util.Collections.emptyList()     // Catch:{ all -> 0x02fe }
            goto L_0x016a
        L_0x0159:
            java.lang.String r6 = "active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live"
            java.lang.String[] r7 = new java.lang.String[r5]     // Catch:{ all -> 0x02fe }
            r7[r12] = r2     // Catch:{ all -> 0x02fe }
            java.lang.String r8 = java.lang.String.valueOf(r10)     // Catch:{ all -> 0x02fe }
            r9 = 1
            r7[r9] = r8     // Catch:{ all -> 0x02fe }
            java.util.List r3 = r3.zza((java.lang.String) r6, (java.lang.String[]) r7)     // Catch:{ all -> 0x02fe }
        L_0x016a:
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x02fe }
            int r7 = r3.size()     // Catch:{ all -> 0x02fe }
            r6.<init>(r7)     // Catch:{ all -> 0x02fe }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x02fe }
        L_0x0177:
            boolean r7 = r3.hasNext()     // Catch:{ all -> 0x02fe }
            if (r7 == 0) goto L_0x01c9
            java.lang.Object r7 = r3.next()     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzae r7 = (com.google.android.gms.measurement.internal.zzae) r7     // Catch:{ all -> 0x02fe }
            if (r7 == 0) goto L_0x0177
            com.google.android.gms.measurement.internal.zzgo r8 = r19.zzj()     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzgq r8 = r8.zzp()     // Catch:{ all -> 0x02fe }
            java.lang.String r9 = "User property expired"
            java.lang.String r14 = r7.zza     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzhy r15 = r1.zzm     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzgh r15 = r15.zzk()     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzon r5 = r7.zzc     // Catch:{ all -> 0x02fe }
            java.lang.String r5 = r5.zza     // Catch:{ all -> 0x02fe }
            java.lang.String r5 = r15.zzc(r5)     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzon r15 = r7.zzc     // Catch:{ all -> 0x02fe }
            java.lang.Object r15 = r15.zza()     // Catch:{ all -> 0x02fe }
            r8.zza(r9, r14, r5, r15)     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzal r5 = r19.zzf()     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzon r8 = r7.zzc     // Catch:{ all -> 0x02fe }
            java.lang.String r8 = r8.zza     // Catch:{ all -> 0x02fe }
            r5.zzh(r2, r8)     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzbf r5 = r7.zzk     // Catch:{ all -> 0x02fe }
            if (r5 == 0) goto L_0x01bc
            com.google.android.gms.measurement.internal.zzbf r5 = r7.zzk     // Catch:{ all -> 0x02fe }
            r6.add(r5)     // Catch:{ all -> 0x02fe }
        L_0x01bc:
            com.google.android.gms.measurement.internal.zzal r5 = r19.zzf()     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzon r7 = r7.zzc     // Catch:{ all -> 0x02fe }
            java.lang.String r7 = r7.zza     // Catch:{ all -> 0x02fe }
            r5.zza((java.lang.String) r2, (java.lang.String) r7)     // Catch:{ all -> 0x02fe }
            r5 = 2
            goto L_0x0177
        L_0x01c9:
            r3 = r6
            java.util.ArrayList r3 = (java.util.ArrayList) r3     // Catch:{ all -> 0x02fe }
            int r3 = r6.size()     // Catch:{ all -> 0x02fe }
            r5 = r12
        L_0x01d1:
            if (r5 >= r3) goto L_0x01e4
            java.lang.Object r7 = r6.get(r5)     // Catch:{ all -> 0x02fe }
            int r5 = r5 + 1
            com.google.android.gms.measurement.internal.zzbf r7 = (com.google.android.gms.measurement.internal.zzbf) r7     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzbf r8 = new com.google.android.gms.measurement.internal.zzbf     // Catch:{ all -> 0x02fe }
            r8.<init>(r7, r10)     // Catch:{ all -> 0x02fe }
            r1.zzc(r8, r0)     // Catch:{ all -> 0x02fe }
            goto L_0x01d1
        L_0x01e4:
            com.google.android.gms.measurement.internal.zzal r3 = r19.zzf()     // Catch:{ all -> 0x02fe }
            java.lang.String r5 = r13.zza     // Catch:{ all -> 0x02fe }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2)     // Catch:{ all -> 0x02fe }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5)     // Catch:{ all -> 0x02fe }
            r3.zzt()     // Catch:{ all -> 0x02fe }
            r3.zzal()     // Catch:{ all -> 0x02fe }
            if (r4 >= 0) goto L_0x021a
            com.google.android.gms.measurement.internal.zzgo r4 = r3.zzj()     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzgq r4 = r4.zzu()     // Catch:{ all -> 0x02fe }
            java.lang.String r6 = "Invalid time querying triggered conditional properties"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r2)     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzgh r3 = r3.zzi()     // Catch:{ all -> 0x02fe }
            java.lang.String r3 = r3.zza((java.lang.String) r5)     // Catch:{ all -> 0x02fe }
            java.lang.Long r5 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x02fe }
            r4.zza(r6, r2, r3, r5)     // Catch:{ all -> 0x02fe }
            java.util.List r2 = java.util.Collections.emptyList()     // Catch:{ all -> 0x02fe }
            goto L_0x022f
        L_0x021a:
            java.lang.String r4 = "active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout"
            r6 = 3
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ all -> 0x02fe }
            r6[r12] = r2     // Catch:{ all -> 0x02fe }
            r2 = 1
            r6[r2] = r5     // Catch:{ all -> 0x02fe }
            java.lang.String r2 = java.lang.String.valueOf(r10)     // Catch:{ all -> 0x02fe }
            r5 = 2
            r6[r5] = r2     // Catch:{ all -> 0x02fe }
            java.util.List r2 = r3.zza((java.lang.String) r4, (java.lang.String[]) r6)     // Catch:{ all -> 0x02fe }
        L_0x022f:
            java.util.ArrayList r14 = new java.util.ArrayList     // Catch:{ all -> 0x02fe }
            int r3 = r2.size()     // Catch:{ all -> 0x02fe }
            r14.<init>(r3)     // Catch:{ all -> 0x02fe }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x02fe }
        L_0x023c:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x02fe }
            if (r3 == 0) goto L_0x02d1
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x02fe }
            r15 = r3
            com.google.android.gms.measurement.internal.zzae r15 = (com.google.android.gms.measurement.internal.zzae) r15     // Catch:{ all -> 0x02fe }
            if (r15 == 0) goto L_0x023c
            com.google.android.gms.measurement.internal.zzon r3 = r15.zzc     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzop r9 = new com.google.android.gms.measurement.internal.zzop     // Catch:{ all -> 0x02fe }
            java.lang.String r4 = r15.zza     // Catch:{ all -> 0x02fe }
            java.lang.Object r4 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)     // Catch:{ all -> 0x02fe }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x02fe }
            java.lang.String r5 = r15.zzb     // Catch:{ all -> 0x02fe }
            java.lang.String r6 = r3.zza     // Catch:{ all -> 0x02fe }
            java.lang.Object r3 = r3.zza()     // Catch:{ all -> 0x02fe }
            java.lang.Object r16 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)     // Catch:{ all -> 0x02fe }
            r3 = r9
            r7 = r10
            r12 = r9
            r9 = r16
            r3.<init>(r4, r5, r6, r7, r9)     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzal r3 = r19.zzf()     // Catch:{ all -> 0x02fe }
            boolean r3 = r3.zza((com.google.android.gms.measurement.internal.zzop) r12)     // Catch:{ all -> 0x02fe }
            if (r3 == 0) goto L_0x0293
            com.google.android.gms.measurement.internal.zzgo r3 = r19.zzj()     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzp()     // Catch:{ all -> 0x02fe }
            java.lang.String r4 = "User property triggered"
            java.lang.String r5 = r15.zza     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzhy r6 = r1.zzm     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzgh r6 = r6.zzk()     // Catch:{ all -> 0x02fe }
            java.lang.String r7 = r12.zzc     // Catch:{ all -> 0x02fe }
            java.lang.String r6 = r6.zzc(r7)     // Catch:{ all -> 0x02fe }
            java.lang.Object r7 = r12.zze     // Catch:{ all -> 0x02fe }
            r3.zza(r4, r5, r6, r7)     // Catch:{ all -> 0x02fe }
            goto L_0x02b4
        L_0x0293:
            com.google.android.gms.measurement.internal.zzgo r3 = r19.zzj()     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzg()     // Catch:{ all -> 0x02fe }
            java.lang.String r4 = "Too many active user properties, ignoring"
            java.lang.String r5 = r15.zza     // Catch:{ all -> 0x02fe }
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r5)     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzhy r6 = r1.zzm     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzgh r6 = r6.zzk()     // Catch:{ all -> 0x02fe }
            java.lang.String r7 = r12.zzc     // Catch:{ all -> 0x02fe }
            java.lang.String r6 = r6.zzc(r7)     // Catch:{ all -> 0x02fe }
            java.lang.Object r7 = r12.zze     // Catch:{ all -> 0x02fe }
            r3.zza(r4, r5, r6, r7)     // Catch:{ all -> 0x02fe }
        L_0x02b4:
            com.google.android.gms.measurement.internal.zzbf r3 = r15.zzi     // Catch:{ all -> 0x02fe }
            if (r3 == 0) goto L_0x02bd
            com.google.android.gms.measurement.internal.zzbf r3 = r15.zzi     // Catch:{ all -> 0x02fe }
            r14.add(r3)     // Catch:{ all -> 0x02fe }
        L_0x02bd:
            com.google.android.gms.measurement.internal.zzon r3 = new com.google.android.gms.measurement.internal.zzon     // Catch:{ all -> 0x02fe }
            r3.<init>(r12)     // Catch:{ all -> 0x02fe }
            r15.zzc = r3     // Catch:{ all -> 0x02fe }
            r3 = 1
            r15.zze = r3     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzal r4 = r19.zzf()     // Catch:{ all -> 0x02fe }
            r4.zza((com.google.android.gms.measurement.internal.zzae) r15)     // Catch:{ all -> 0x02fe }
            r12 = 0
            goto L_0x023c
        L_0x02d1:
            r1.zzc(r13, r0)     // Catch:{ all -> 0x02fe }
            r2 = r14
            java.util.ArrayList r2 = (java.util.ArrayList) r2     // Catch:{ all -> 0x02fe }
            int r2 = r14.size()     // Catch:{ all -> 0x02fe }
            r12 = 0
        L_0x02dc:
            if (r12 >= r2) goto L_0x02ef
            java.lang.Object r3 = r14.get(r12)     // Catch:{ all -> 0x02fe }
            int r12 = r12 + 1
            com.google.android.gms.measurement.internal.zzbf r3 = (com.google.android.gms.measurement.internal.zzbf) r3     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzbf r4 = new com.google.android.gms.measurement.internal.zzbf     // Catch:{ all -> 0x02fe }
            r4.<init>(r3, r10)     // Catch:{ all -> 0x02fe }
            r1.zzc(r4, r0)     // Catch:{ all -> 0x02fe }
            goto L_0x02dc
        L_0x02ef:
            com.google.android.gms.measurement.internal.zzal r0 = r19.zzf()     // Catch:{ all -> 0x02fe }
            r0.zzw()     // Catch:{ all -> 0x02fe }
            com.google.android.gms.measurement.internal.zzal r0 = r19.zzf()
            r0.zzu()
            return
        L_0x02fe:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzal r2 = r19.zzf()
            r2.zzu()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zza(com.google.android.gms.measurement.internal.zzbf, com.google.android.gms.measurement.internal.zzo):void");
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzbf zzbf, String str) {
        zzbf zzbf2 = zzbf;
        String str2 = str;
        zzg zze2 = zzf().zze(str2);
        if (zze2 == null || TextUtils.isEmpty(zze2.zzaf())) {
            zzj().zzc().zza("No app data available; dropping event", str2);
            return;
        }
        Boolean zza2 = zza(zze2);
        if (zza2 == null) {
            if (!"_ui".equals(zzbf2.zza)) {
                zzj().zzu().zza("Could not find package. appId", zzgo.zza(str));
            }
        } else if (!zza2.booleanValue()) {
            zzj().zzg().zza("App version does not match; dropping event. appId", zzgo.zza(str));
            return;
        }
        zzo zzo2 = r2;
        zzg zzg2 = zze2;
        zzo zzo3 = new zzo(str, zze2.zzah(), zze2.zzaf(), zze2.zze(), zze2.zzae(), zze2.zzq(), zze2.zzn(), (String) null, zze2.zzar(), false, zzg2.zzag(), zzg2.zzd(), 0, 0, zzg2.zzaq(), false, zzg2.zzaa(), zzg2.zzx(), zzg2.zzo(), zzg2.zzan(), (String) null, zzb(str2).zzf(), "", (String) null, zzg2.zzat(), zzg2.zzw(), zzb(str2).zza(), zzd(str2).zzf(), zzg2.zza(), zzg2.zzf(), zzg2.zzam(), zzg2.zzak());
        zzb(zzbf2, zzo2);
    }

    private final void zzb(zzbf zzbf, zzo zzo2) {
        Preconditions.checkNotEmpty(zzo2.zza);
        zzgs zza2 = zzgs.zza(zzbf);
        zzq().zza(zza2.zzc, zzf().zzd(zzo2.zza));
        zzq().zza(zza2, zze().zzb(zzo2.zza));
        zzbf zza3 = zza2.zza();
        if ("_cmp".equals(zza3.zza) && "referrer API v2".equals(zza3.zzb.zzd("_cis"))) {
            String zzd2 = zza3.zzb.zzd("gclid");
            if (!TextUtils.isEmpty(zzd2)) {
                zza(new zzon("_lgclid", zza3.zzd, zzd2, "auto"), zzo2);
            }
        }
        zza(zza3, zzo2);
    }

    private final void zza(zzfy.zzk.zza zza2, long j, boolean z) {
        zzop zzop;
        boolean z2;
        String str = z ? "_se" : "_lte";
        zzop zze2 = zzf().zze(zza2.zzt(), str);
        if (zze2 == null || zze2.zze == null) {
            zzop = new zzop(zza2.zzt(), "auto", str, zzb().currentTimeMillis(), Long.valueOf(j));
        } else {
            zzop = new zzop(zza2.zzt(), "auto", str, zzb().currentTimeMillis(), Long.valueOf(((Long) zze2.zze).longValue() + j));
        }
        zzfy.zzo zzo2 = (zzfy.zzo) ((zzjt) zzfy.zzo.zze().zza(str).zzb(zzb().currentTimeMillis()).zza(((Long) zzop.zze).longValue()).zzai());
        int zza3 = zzoo.zza(zza2, str);
        if (zza3 >= 0) {
            zza2.zza(zza3, zzo2);
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2) {
            zza2.zza(zzo2);
        }
        if (j > 0) {
            zzf().zza(zzop);
            zzj().zzp().zza("Updated engagement user property. scope, value", z ? "session-scoped" : "lifetime", zzop.zze);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzt() {
        this.zzt++;
    }

    private final void zzab() {
        zzl().zzt();
        for (String next : this.zzr) {
            if (zzpn.zza() && zze().zze(next, zzbh.zzch)) {
                zzj().zzc().zza("Notifying app that trigger URIs are available. App ID", next);
                Intent intent = new Intent();
                intent.setAction("com.google.android.gms.measurement.TRIGGERS_AVAILABLE");
                intent.setPackage(next);
                this.zzm.zza().sendBroadcast(intent);
            }
        }
        this.zzr.clear();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0110 A[Catch:{ all -> 0x01da, all -> 0x01e3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x015f A[Catch:{ all -> 0x01da, all -> 0x01e3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x016d A[Catch:{ all -> 0x01da, all -> 0x01e3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0193 A[Catch:{ all -> 0x01da, all -> 0x01e3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0197 A[Catch:{ all -> 0x01da, all -> 0x01e3 }] */
    /* renamed from: zzb */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.String r9, int r10, java.lang.Throwable r11, byte[] r12, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r13) {
        /*
            r8 = this;
            com.google.android.gms.measurement.internal.zzhv r0 = r8.zzl()
            r0.zzt()
            r8.zzs()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9)
            r0 = 0
            if (r12 != 0) goto L_0x0012
            byte[] r12 = new byte[r0]     // Catch:{ all -> 0x01e3 }
        L_0x0012:
            com.google.android.gms.measurement.internal.zzgo r1 = r8.zzj()     // Catch:{ all -> 0x01e3 }
            com.google.android.gms.measurement.internal.zzgq r1 = r1.zzp()     // Catch:{ all -> 0x01e3 }
            java.lang.String r2 = "onConfigFetched. Response size"
            int r3 = r12.length     // Catch:{ all -> 0x01e3 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x01e3 }
            r1.zza(r2, r3)     // Catch:{ all -> 0x01e3 }
            com.google.android.gms.measurement.internal.zzal r1 = r8.zzf()     // Catch:{ all -> 0x01e3 }
            r1.zzp()     // Catch:{ all -> 0x01e3 }
            com.google.android.gms.measurement.internal.zzal r1 = r8.zzf()     // Catch:{ all -> 0x01da }
            com.google.android.gms.measurement.internal.zzg r1 = r1.zze(r9)     // Catch:{ all -> 0x01da }
            r2 = 200(0xc8, float:2.8E-43)
            r3 = 304(0x130, float:4.26E-43)
            r4 = 1
            if (r10 == r2) goto L_0x0040
            r2 = 204(0xcc, float:2.86E-43)
            if (r10 == r2) goto L_0x0040
            if (r10 != r3) goto L_0x0044
        L_0x0040:
            if (r11 != 0) goto L_0x0044
            r2 = r4
            goto L_0x0045
        L_0x0044:
            r2 = r0
        L_0x0045:
            if (r1 != 0) goto L_0x005a
            com.google.android.gms.measurement.internal.zzgo r10 = r8.zzj()     // Catch:{ all -> 0x01da }
            com.google.android.gms.measurement.internal.zzgq r10 = r10.zzu()     // Catch:{ all -> 0x01da }
            java.lang.String r11 = "App does not exist in onConfigFetched. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r9)     // Catch:{ all -> 0x01da }
            r10.zza(r11, r9)     // Catch:{ all -> 0x01da }
            goto L_0x01c6
        L_0x005a:
            r5 = 404(0x194, float:5.66E-43)
            if (r2 != 0) goto L_0x00ba
            if (r10 != r5) goto L_0x0061
            goto L_0x00ba
        L_0x0061:
            com.google.android.gms.common.util.Clock r12 = r8.zzb()     // Catch:{ all -> 0x01da }
            long r12 = r12.currentTimeMillis()     // Catch:{ all -> 0x01da }
            r1.zzm(r12)     // Catch:{ all -> 0x01da }
            com.google.android.gms.measurement.internal.zzal r12 = r8.zzf()     // Catch:{ all -> 0x01da }
            r12.zza((com.google.android.gms.measurement.internal.zzg) r1, (boolean) r0, (boolean) r0)     // Catch:{ all -> 0x01da }
            com.google.android.gms.measurement.internal.zzgo r12 = r8.zzj()     // Catch:{ all -> 0x01da }
            com.google.android.gms.measurement.internal.zzgq r12 = r12.zzp()     // Catch:{ all -> 0x01da }
            java.lang.String r13 = "Fetching config failed. code, error"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x01da }
            r12.zza(r13, r1, r11)     // Catch:{ all -> 0x01da }
            com.google.android.gms.measurement.internal.zzhl r11 = r8.zzi()     // Catch:{ all -> 0x01da }
            r11.zzi(r9)     // Catch:{ all -> 0x01da }
            com.google.android.gms.measurement.internal.zzmw r9 = r8.zzj     // Catch:{ all -> 0x01da }
            com.google.android.gms.measurement.internal.zzhb r9 = r9.zze     // Catch:{ all -> 0x01da }
            com.google.android.gms.common.util.Clock r11 = r8.zzb()     // Catch:{ all -> 0x01da }
            long r11 = r11.currentTimeMillis()     // Catch:{ all -> 0x01da }
            r9.zza(r11)     // Catch:{ all -> 0x01da }
            r9 = 503(0x1f7, float:7.05E-43)
            if (r10 == r9) goto L_0x00a4
            r9 = 429(0x1ad, float:6.01E-43)
            if (r10 != r9) goto L_0x00a3
            goto L_0x00a4
        L_0x00a3:
            r4 = r0
        L_0x00a4:
            if (r4 == 0) goto L_0x00b5
            com.google.android.gms.measurement.internal.zzmw r9 = r8.zzj     // Catch:{ all -> 0x01da }
            com.google.android.gms.measurement.internal.zzhb r9 = r9.zzc     // Catch:{ all -> 0x01da }
            com.google.android.gms.common.util.Clock r10 = r8.zzb()     // Catch:{ all -> 0x01da }
            long r10 = r10.currentTimeMillis()     // Catch:{ all -> 0x01da }
            r9.zza(r10)     // Catch:{ all -> 0x01da }
        L_0x00b5:
            r8.zzac()     // Catch:{ all -> 0x01da }
            goto L_0x01c6
        L_0x00ba:
            boolean r11 = com.google.android.gms.internal.measurement.zzpb.zza()     // Catch:{ all -> 0x01da }
            java.lang.String r2 = "ETag"
            java.lang.String r4 = "Last-Modified"
            r6 = 0
            if (r11 == 0) goto L_0x00da
            com.google.android.gms.measurement.internal.zzag r11 = r8.zze()     // Catch:{ all -> 0x01da }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbh.zzcf     // Catch:{ all -> 0x01da }
            boolean r11 = r11.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r7)     // Catch:{ all -> 0x01da }
            if (r11 == 0) goto L_0x00da
            java.lang.String r11 = zza((java.util.Map<java.lang.String, java.util.List<java.lang.String>>) r13, (java.lang.String) r4)     // Catch:{ all -> 0x01da }
            java.lang.String r13 = zza((java.util.Map<java.lang.String, java.util.List<java.lang.String>>) r13, (java.lang.String) r2)     // Catch:{ all -> 0x01da }
            goto L_0x010e
        L_0x00da:
            if (r13 == 0) goto L_0x00e3
            java.lang.Object r11 = r13.get(r4)     // Catch:{ all -> 0x01da }
            java.util.List r11 = (java.util.List) r11     // Catch:{ all -> 0x01da }
            goto L_0x00e4
        L_0x00e3:
            r11 = r6
        L_0x00e4:
            if (r11 == 0) goto L_0x00f3
            boolean r4 = r11.isEmpty()     // Catch:{ all -> 0x01da }
            if (r4 != 0) goto L_0x00f3
            java.lang.Object r11 = r11.get(r0)     // Catch:{ all -> 0x01da }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x01da }
            goto L_0x00f4
        L_0x00f3:
            r11 = r6
        L_0x00f4:
            if (r13 == 0) goto L_0x00fd
            java.lang.Object r13 = r13.get(r2)     // Catch:{ all -> 0x01da }
            java.util.List r13 = (java.util.List) r13     // Catch:{ all -> 0x01da }
            goto L_0x00fe
        L_0x00fd:
            r13 = r6
        L_0x00fe:
            if (r13 == 0) goto L_0x010d
            boolean r2 = r13.isEmpty()     // Catch:{ all -> 0x01da }
            if (r2 != 0) goto L_0x010d
            java.lang.Object r13 = r13.get(r0)     // Catch:{ all -> 0x01da }
            java.lang.String r13 = (java.lang.String) r13     // Catch:{ all -> 0x01da }
            goto L_0x010e
        L_0x010d:
            r13 = r6
        L_0x010e:
            if (r10 == r5) goto L_0x012a
            if (r10 != r3) goto L_0x0113
            goto L_0x012a
        L_0x0113:
            com.google.android.gms.measurement.internal.zzhl r2 = r8.zzi()     // Catch:{ all -> 0x01da }
            boolean r11 = r2.zza(r9, r12, r11, r13)     // Catch:{ all -> 0x01da }
            if (r11 != 0) goto L_0x014b
            com.google.android.gms.measurement.internal.zzal r9 = r8.zzf()     // Catch:{ all -> 0x01e3 }
            r9.zzu()     // Catch:{ all -> 0x01e3 }
            r8.zzu = r0
            r8.zzaa()
            return
        L_0x012a:
            com.google.android.gms.measurement.internal.zzhl r11 = r8.zzi()     // Catch:{ all -> 0x01da }
            com.google.android.gms.internal.measurement.zzfr$zzd r11 = r11.zzc(r9)     // Catch:{ all -> 0x01da }
            if (r11 != 0) goto L_0x014b
            com.google.android.gms.measurement.internal.zzhl r11 = r8.zzi()     // Catch:{ all -> 0x01da }
            boolean r11 = r11.zza(r9, r6, r6, r6)     // Catch:{ all -> 0x01da }
            if (r11 != 0) goto L_0x014b
            com.google.android.gms.measurement.internal.zzal r9 = r8.zzf()     // Catch:{ all -> 0x01e3 }
            r9.zzu()     // Catch:{ all -> 0x01e3 }
            r8.zzu = r0
            r8.zzaa()
            return
        L_0x014b:
            com.google.android.gms.common.util.Clock r11 = r8.zzb()     // Catch:{ all -> 0x01da }
            long r2 = r11.currentTimeMillis()     // Catch:{ all -> 0x01da }
            r1.zzd((long) r2)     // Catch:{ all -> 0x01da }
            com.google.android.gms.measurement.internal.zzal r11 = r8.zzf()     // Catch:{ all -> 0x01da }
            r11.zza((com.google.android.gms.measurement.internal.zzg) r1, (boolean) r0, (boolean) r0)     // Catch:{ all -> 0x01da }
            if (r10 != r5) goto L_0x016d
            com.google.android.gms.measurement.internal.zzgo r10 = r8.zzj()     // Catch:{ all -> 0x01da }
            com.google.android.gms.measurement.internal.zzgq r10 = r10.zzv()     // Catch:{ all -> 0x01da }
            java.lang.String r11 = "Config not found. Using empty config. appId"
            r10.zza(r11, r9)     // Catch:{ all -> 0x01da }
            goto L_0x0183
        L_0x016d:
            com.google.android.gms.measurement.internal.zzgo r9 = r8.zzj()     // Catch:{ all -> 0x01da }
            com.google.android.gms.measurement.internal.zzgq r9 = r9.zzp()     // Catch:{ all -> 0x01da }
            java.lang.String r11 = "Successfully fetched config. Got network response. code, size"
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x01da }
            int r12 = r12.length     // Catch:{ all -> 0x01da }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ all -> 0x01da }
            r9.zza(r11, r10, r12)     // Catch:{ all -> 0x01da }
        L_0x0183:
            com.google.android.gms.measurement.internal.zzgr r9 = r8.zzh()     // Catch:{ all -> 0x01da }
            boolean r9 = r9.zzu()     // Catch:{ all -> 0x01da }
            if (r9 == 0) goto L_0x0197
            boolean r9 = r8.zzad()     // Catch:{ all -> 0x01da }
            if (r9 == 0) goto L_0x0197
            r8.zzw()     // Catch:{ all -> 0x01da }
            goto L_0x01c6
        L_0x0197:
            com.google.android.gms.measurement.internal.zzag r9 = r8.zze()     // Catch:{ all -> 0x01da }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r10 = com.google.android.gms.measurement.internal.zzbh.zzcb     // Catch:{ all -> 0x01da }
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r10)     // Catch:{ all -> 0x01da }
            if (r9 == 0) goto L_0x01c3
            com.google.android.gms.measurement.internal.zzgr r9 = r8.zzh()     // Catch:{ all -> 0x01da }
            boolean r9 = r9.zzu()     // Catch:{ all -> 0x01da }
            if (r9 == 0) goto L_0x01c3
            com.google.android.gms.measurement.internal.zzal r9 = r8.zzf()     // Catch:{ all -> 0x01da }
            java.lang.String r10 = r1.zzac()     // Catch:{ all -> 0x01da }
            boolean r9 = r9.zzs(r10)     // Catch:{ all -> 0x01da }
            if (r9 == 0) goto L_0x01c3
            java.lang.String r9 = r1.zzac()     // Catch:{ all -> 0x01da }
            r8.zze((java.lang.String) r9)     // Catch:{ all -> 0x01da }
            goto L_0x01c6
        L_0x01c3:
            r8.zzac()     // Catch:{ all -> 0x01da }
        L_0x01c6:
            com.google.android.gms.measurement.internal.zzal r9 = r8.zzf()     // Catch:{ all -> 0x01da }
            r9.zzw()     // Catch:{ all -> 0x01da }
            com.google.android.gms.measurement.internal.zzal r9 = r8.zzf()     // Catch:{ all -> 0x01e3 }
            r9.zzu()     // Catch:{ all -> 0x01e3 }
            r8.zzu = r0
            r8.zzaa()
            return
        L_0x01da:
            r9 = move-exception
            com.google.android.gms.measurement.internal.zzal r10 = r8.zzf()     // Catch:{ all -> 0x01e3 }
            r10.zzu()     // Catch:{ all -> 0x01e3 }
            throw r9     // Catch:{ all -> 0x01e3 }
        L_0x01e3:
            r9 = move-exception
            r8.zzu = r0
            r8.zzaa()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zza(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }

    /* access modifiers changed from: package-private */
    public final void zza(boolean z) {
        zzac();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x008f A[Catch:{ all -> 0x022f }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0097 A[Catch:{ all -> 0x022f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(boolean r16, int r17, java.lang.Throwable r18, byte[] r19, java.lang.String r20, java.util.List<android.util.Pair<com.google.android.gms.internal.measurement.zzfy.zzj, com.google.android.gms.measurement.internal.zznw>> r21) {
        /*
            r15 = this;
            r1 = r15
            r0 = r17
            r2 = r18
            r8 = r20
            com.google.android.gms.measurement.internal.zzhv r3 = r15.zzl()
            r3.zzt()
            r15.zzs()
            r9 = 0
            if (r19 != 0) goto L_0x0017
            byte[] r3 = new byte[r9]     // Catch:{ all -> 0x022f }
            goto L_0x0019
        L_0x0017:
            r3 = r19
        L_0x0019:
            java.util.List<java.lang.Long> r4 = r1.zzz     // Catch:{ all -> 0x022f }
            java.lang.Object r4 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)     // Catch:{ all -> 0x022f }
            r10 = r4
            java.util.List r10 = (java.util.List) r10     // Catch:{ all -> 0x022f }
            r11 = 0
            r1.zzz = r11     // Catch:{ all -> 0x022f }
            r12 = 1
            if (r16 == 0) goto L_0x00b2
            r4 = 200(0xc8, float:2.8E-43)
            if (r0 == r4) goto L_0x0030
            r4 = 204(0xcc, float:2.86E-43)
            if (r0 != r4) goto L_0x0034
        L_0x0030:
            if (r2 != 0) goto L_0x0034
            goto L_0x00b2
        L_0x0034:
            boolean r4 = com.google.android.gms.internal.measurement.zzpb.zza()     // Catch:{ all -> 0x022f }
            java.lang.String r5 = "Network upload failed. Will retry later. code, error"
            if (r4 == 0) goto L_0x006d
            com.google.android.gms.measurement.internal.zzag r4 = r15.zze()     // Catch:{ all -> 0x022f }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzbh.zzcf     // Catch:{ all -> 0x022f }
            boolean r4 = r4.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r6)     // Catch:{ all -> 0x022f }
            if (r4 == 0) goto L_0x006d
            java.lang.String r4 = new java.lang.String     // Catch:{ all -> 0x022f }
            java.nio.charset.Charset r6 = java.nio.charset.StandardCharsets.UTF_8     // Catch:{ all -> 0x022f }
            r4.<init>(r3, r6)     // Catch:{ all -> 0x022f }
            int r3 = r4.length()     // Catch:{ all -> 0x022f }
            r6 = 32
            int r3 = java.lang.Math.min(r6, r3)     // Catch:{ all -> 0x022f }
            java.lang.String r3 = r4.substring(r9, r3)     // Catch:{ all -> 0x022f }
            com.google.android.gms.measurement.internal.zzgo r4 = r15.zzj()     // Catch:{ all -> 0x022f }
            com.google.android.gms.measurement.internal.zzgq r4 = r4.zzv()     // Catch:{ all -> 0x022f }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r17)     // Catch:{ all -> 0x022f }
            r4.zza(r5, r6, r2, r3)     // Catch:{ all -> 0x022f }
            goto L_0x007c
        L_0x006d:
            com.google.android.gms.measurement.internal.zzgo r3 = r15.zzj()     // Catch:{ all -> 0x022f }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzp()     // Catch:{ all -> 0x022f }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r17)     // Catch:{ all -> 0x022f }
            r3.zza(r5, r4, r2)     // Catch:{ all -> 0x022f }
        L_0x007c:
            com.google.android.gms.measurement.internal.zzmw r2 = r1.zzj     // Catch:{ all -> 0x022f }
            com.google.android.gms.measurement.internal.zzhb r2 = r2.zze     // Catch:{ all -> 0x022f }
            com.google.android.gms.common.util.Clock r3 = r15.zzb()     // Catch:{ all -> 0x022f }
            long r3 = r3.currentTimeMillis()     // Catch:{ all -> 0x022f }
            r2.zza(r3)     // Catch:{ all -> 0x022f }
            r2 = 503(0x1f7, float:7.05E-43)
            if (r0 == r2) goto L_0x0095
            r2 = 429(0x1ad, float:6.01E-43)
            if (r0 != r2) goto L_0x0094
            goto L_0x0095
        L_0x0094:
            r12 = r9
        L_0x0095:
            if (r12 == 0) goto L_0x00a6
            com.google.android.gms.measurement.internal.zzmw r0 = r1.zzj     // Catch:{ all -> 0x022f }
            com.google.android.gms.measurement.internal.zzhb r0 = r0.zzc     // Catch:{ all -> 0x022f }
            com.google.android.gms.common.util.Clock r2 = r15.zzb()     // Catch:{ all -> 0x022f }
            long r2 = r2.currentTimeMillis()     // Catch:{ all -> 0x022f }
            r0.zza(r2)     // Catch:{ all -> 0x022f }
        L_0x00a6:
            com.google.android.gms.measurement.internal.zzal r0 = r15.zzf()     // Catch:{ all -> 0x022f }
            r0.zza((java.util.List<java.lang.Long>) r10)     // Catch:{ all -> 0x022f }
            r15.zzac()     // Catch:{ all -> 0x022f }
            goto L_0x0229
        L_0x00b2:
            com.google.android.gms.measurement.internal.zzgo r2 = r15.zzj()     // Catch:{ all -> 0x022f }
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzp()     // Catch:{ all -> 0x022f }
            java.lang.String r4 = "Network upload successful with code"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r17)     // Catch:{ all -> 0x022f }
            r2.zza(r4, r5)     // Catch:{ all -> 0x022f }
            if (r16 == 0) goto L_0x00d4
            com.google.android.gms.measurement.internal.zzmw r2 = r1.zzj     // Catch:{ SQLiteException -> 0x01fe }
            com.google.android.gms.measurement.internal.zzhb r2 = r2.zzd     // Catch:{ SQLiteException -> 0x01fe }
            com.google.android.gms.common.util.Clock r4 = r15.zzb()     // Catch:{ SQLiteException -> 0x01fe }
            long r4 = r4.currentTimeMillis()     // Catch:{ SQLiteException -> 0x01fe }
            r2.zza(r4)     // Catch:{ SQLiteException -> 0x01fe }
        L_0x00d4:
            com.google.android.gms.measurement.internal.zzmw r2 = r1.zzj     // Catch:{ SQLiteException -> 0x01fe }
            com.google.android.gms.measurement.internal.zzhb r2 = r2.zze     // Catch:{ SQLiteException -> 0x01fe }
            r13 = 0
            r2.zza(r13)     // Catch:{ SQLiteException -> 0x01fe }
            r15.zzac()     // Catch:{ SQLiteException -> 0x01fe }
            if (r16 == 0) goto L_0x00f9
            com.google.android.gms.measurement.internal.zzgo r2 = r15.zzj()     // Catch:{ SQLiteException -> 0x01fe }
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzp()     // Catch:{ SQLiteException -> 0x01fe }
            java.lang.String r4 = "Successful upload. Got network response. code, size"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r17)     // Catch:{ SQLiteException -> 0x01fe }
            int r3 = r3.length     // Catch:{ SQLiteException -> 0x01fe }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x01fe }
            r2.zza(r4, r0, r3)     // Catch:{ SQLiteException -> 0x01fe }
            goto L_0x0106
        L_0x00f9:
            com.google.android.gms.measurement.internal.zzgo r0 = r15.zzj()     // Catch:{ SQLiteException -> 0x01fe }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzp()     // Catch:{ SQLiteException -> 0x01fe }
            java.lang.String r2 = "Purged empty bundles"
            r0.zza(r2)     // Catch:{ SQLiteException -> 0x01fe }
        L_0x0106:
            com.google.android.gms.measurement.internal.zzal r0 = r15.zzf()     // Catch:{ SQLiteException -> 0x01fe }
            r0.zzp()     // Catch:{ SQLiteException -> 0x01fe }
            com.google.android.gms.measurement.internal.zzag r0 = r15.zze()     // Catch:{ all -> 0x01f5 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbh.zzcb     // Catch:{ all -> 0x01f5 }
            boolean r0 = r0.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r2)     // Catch:{ all -> 0x01f5 }
            if (r0 == 0) goto L_0x0149
            java.util.Iterator r0 = r21.iterator()     // Catch:{ all -> 0x01f5 }
        L_0x011d:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x01f5 }
            if (r2 == 0) goto L_0x0149
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x01f5 }
            android.util.Pair r2 = (android.util.Pair) r2     // Catch:{ all -> 0x01f5 }
            java.lang.Object r3 = r2.first     // Catch:{ all -> 0x01f5 }
            r4 = r3
            com.google.android.gms.internal.measurement.zzfy$zzj r4 = (com.google.android.gms.internal.measurement.zzfy.zzj) r4     // Catch:{ all -> 0x01f5 }
            java.lang.Object r2 = r2.second     // Catch:{ all -> 0x01f5 }
            com.google.android.gms.measurement.internal.zznw r2 = (com.google.android.gms.measurement.internal.zznw) r2     // Catch:{ all -> 0x01f5 }
            com.google.android.gms.measurement.internal.zzal r3 = r15.zzf()     // Catch:{ all -> 0x01f5 }
            java.lang.String r5 = r2.zzb()     // Catch:{ all -> 0x01f5 }
            java.util.Map r6 = r2.zzc()     // Catch:{ all -> 0x01f5 }
            com.google.android.gms.measurement.internal.zznt r7 = r2.zza()     // Catch:{ all -> 0x01f5 }
            r2 = r3
            r3 = r20
            r2.zza(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x01f5 }
            goto L_0x011d
        L_0x0149:
            java.util.Iterator r2 = r10.iterator()     // Catch:{ all -> 0x01f5 }
        L_0x014d:
            boolean r0 = r2.hasNext()     // Catch:{ all -> 0x01f5 }
            if (r0 == 0) goto L_0x01a3
            java.lang.Object r0 = r2.next()     // Catch:{ all -> 0x01f5 }
            r3 = r0
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ all -> 0x01f5 }
            com.google.android.gms.measurement.internal.zzal r4 = r15.zzf()     // Catch:{ SQLiteException -> 0x0196 }
            long r5 = r3.longValue()     // Catch:{ SQLiteException -> 0x0196 }
            r4.zzt()     // Catch:{ SQLiteException -> 0x0196 }
            r4.zzal()     // Catch:{ SQLiteException -> 0x0196 }
            android.database.sqlite.SQLiteDatabase r0 = r4.e_()     // Catch:{ SQLiteException -> 0x0196 }
            java.lang.String[] r7 = new java.lang.String[r12]     // Catch:{ SQLiteException -> 0x0196 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0196 }
            r7[r9] = r5     // Catch:{ SQLiteException -> 0x0196 }
            java.lang.String r5 = "queue"
            java.lang.String r6 = "rowid=?"
            int r0 = r0.delete(r5, r6, r7)     // Catch:{ SQLiteException -> 0x0187 }
            if (r0 != r12) goto L_0x017f
            goto L_0x014d
        L_0x017f:
            android.database.sqlite.SQLiteException r0 = new android.database.sqlite.SQLiteException     // Catch:{ SQLiteException -> 0x0187 }
            java.lang.String r5 = "Deleted fewer rows from queue than expected"
            r0.<init>(r5)     // Catch:{ SQLiteException -> 0x0187 }
            throw r0     // Catch:{ SQLiteException -> 0x0187 }
        L_0x0187:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzgo r4 = r4.zzj()     // Catch:{ SQLiteException -> 0x0196 }
            com.google.android.gms.measurement.internal.zzgq r4 = r4.zzg()     // Catch:{ SQLiteException -> 0x0196 }
            java.lang.String r5 = "Failed to delete a bundle in a queue table"
            r4.zza(r5, r0)     // Catch:{ SQLiteException -> 0x0196 }
            throw r0     // Catch:{ SQLiteException -> 0x0196 }
        L_0x0196:
            r0 = move-exception
            java.util.List<java.lang.Long> r4 = r1.zzaa     // Catch:{ all -> 0x01f5 }
            if (r4 == 0) goto L_0x01a2
            boolean r3 = r4.contains(r3)     // Catch:{ all -> 0x01f5 }
            if (r3 == 0) goto L_0x01a2
            goto L_0x014d
        L_0x01a2:
            throw r0     // Catch:{ all -> 0x01f5 }
        L_0x01a3:
            com.google.android.gms.measurement.internal.zzal r0 = r15.zzf()     // Catch:{ all -> 0x01f5 }
            r0.zzw()     // Catch:{ all -> 0x01f5 }
            com.google.android.gms.measurement.internal.zzal r0 = r15.zzf()     // Catch:{ SQLiteException -> 0x01fe }
            r0.zzu()     // Catch:{ SQLiteException -> 0x01fe }
            r1.zzaa = r11     // Catch:{ SQLiteException -> 0x01fe }
            com.google.android.gms.measurement.internal.zzgr r0 = r15.zzh()     // Catch:{ SQLiteException -> 0x01fe }
            boolean r0 = r0.zzu()     // Catch:{ SQLiteException -> 0x01fe }
            if (r0 == 0) goto L_0x01c7
            boolean r0 = r15.zzad()     // Catch:{ SQLiteException -> 0x01fe }
            if (r0 == 0) goto L_0x01c7
            r15.zzw()     // Catch:{ SQLiteException -> 0x01fe }
            goto L_0x01f2
        L_0x01c7:
            com.google.android.gms.measurement.internal.zzag r0 = r15.zze()     // Catch:{ SQLiteException -> 0x01fe }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbh.zzcb     // Catch:{ SQLiteException -> 0x01fe }
            boolean r0 = r0.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r2)     // Catch:{ SQLiteException -> 0x01fe }
            if (r0 == 0) goto L_0x01eb
            com.google.android.gms.measurement.internal.zzgr r0 = r15.zzh()     // Catch:{ SQLiteException -> 0x01fe }
            boolean r0 = r0.zzu()     // Catch:{ SQLiteException -> 0x01fe }
            if (r0 == 0) goto L_0x01eb
            com.google.android.gms.measurement.internal.zzal r0 = r15.zzf()     // Catch:{ SQLiteException -> 0x01fe }
            boolean r0 = r0.zzs(r8)     // Catch:{ SQLiteException -> 0x01fe }
            if (r0 == 0) goto L_0x01eb
            r15.zze((java.lang.String) r8)     // Catch:{ SQLiteException -> 0x01fe }
            goto L_0x01f2
        L_0x01eb:
            r2 = -1
            r1.zzab = r2     // Catch:{ SQLiteException -> 0x01fe }
            r15.zzac()     // Catch:{ SQLiteException -> 0x01fe }
        L_0x01f2:
            r1.zzp = r13     // Catch:{ SQLiteException -> 0x01fe }
            goto L_0x0229
        L_0x01f5:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzal r2 = r15.zzf()     // Catch:{ SQLiteException -> 0x01fe }
            r2.zzu()     // Catch:{ SQLiteException -> 0x01fe }
            throw r0     // Catch:{ SQLiteException -> 0x01fe }
        L_0x01fe:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzgo r2 = r15.zzj()     // Catch:{ all -> 0x022f }
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzg()     // Catch:{ all -> 0x022f }
            java.lang.String r3 = "Database error while trying to delete uploaded bundles"
            r2.zza(r3, r0)     // Catch:{ all -> 0x022f }
            com.google.android.gms.common.util.Clock r0 = r15.zzb()     // Catch:{ all -> 0x022f }
            long r2 = r0.elapsedRealtime()     // Catch:{ all -> 0x022f }
            r1.zzp = r2     // Catch:{ all -> 0x022f }
            com.google.android.gms.measurement.internal.zzgo r0 = r15.zzj()     // Catch:{ all -> 0x022f }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzp()     // Catch:{ all -> 0x022f }
            java.lang.String r2 = "Disable upload, time"
            long r3 = r1.zzp     // Catch:{ all -> 0x022f }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x022f }
            r0.zza(r2, r3)     // Catch:{ all -> 0x022f }
        L_0x0229:
            r1.zzv = r9
            r15.zzaa()
            return
        L_0x022f:
            r0 = move-exception
            r1.zzv = r9
            r15.zzaa()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zza(boolean, int, java.lang.Throwable, byte[], java.lang.String, java.util.List):void");
    }

    /* JADX WARNING: type inference failed for: r8v4, types: [java.lang.String] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.String r5, int r6, java.lang.Throwable r7, byte[] r8, com.google.android.gms.measurement.internal.zzoj r9) {
        /*
            r4 = this;
            com.google.android.gms.measurement.internal.zzhv r0 = r4.zzl()
            r0.zzt()
            r4.zzs()
            r0 = 0
            if (r8 != 0) goto L_0x0013
            byte[] r8 = new byte[r0]     // Catch:{ all -> 0x0010 }
            goto L_0x0013
        L_0x0010:
            r5 = move-exception
            goto L_0x00f6
        L_0x0013:
            r1 = 200(0xc8, float:2.8E-43)
            if (r6 == r1) goto L_0x001b
            r1 = 204(0xcc, float:2.86E-43)
            if (r6 != r1) goto L_0x00b3
        L_0x001b:
            if (r7 != 0) goto L_0x00b3
            if (r9 == 0) goto L_0x007a
            com.google.android.gms.measurement.internal.zzal r7 = r4.zzf()     // Catch:{ all -> 0x0010 }
            long r8 = r9.zza()     // Catch:{ all -> 0x0010 }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x0010 }
            r7.zzt()     // Catch:{ all -> 0x0010 }
            r7.zzal()     // Catch:{ all -> 0x0010 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r8)     // Catch:{ all -> 0x0010 }
            boolean r9 = com.google.android.gms.internal.measurement.zzpu.zza()     // Catch:{ all -> 0x0010 }
            if (r9 == 0) goto L_0x0046
            com.google.android.gms.measurement.internal.zzag r9 = r7.zze()     // Catch:{ all -> 0x0010 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r1 = com.google.android.gms.measurement.internal.zzbh.zzcb     // Catch:{ all -> 0x0010 }
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r1)     // Catch:{ all -> 0x0010 }
            if (r9 == 0) goto L_0x007a
        L_0x0046:
            android.database.sqlite.SQLiteDatabase r9 = r7.e_()     // Catch:{ all -> 0x0010 }
            r1 = 1
            java.lang.String[] r2 = new java.lang.String[r1]     // Catch:{ all -> 0x0010 }
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ all -> 0x0010 }
            r2[r0] = r8     // Catch:{ all -> 0x0010 }
            java.lang.String r8 = "upload_queue"
            java.lang.String r3 = "rowid=?"
            int r8 = r9.delete(r8, r3, r2)     // Catch:{ SQLiteException -> 0x006b }
            if (r8 == r1) goto L_0x007a
            com.google.android.gms.measurement.internal.zzgo r8 = r7.zzj()     // Catch:{ SQLiteException -> 0x006b }
            com.google.android.gms.measurement.internal.zzgq r8 = r8.zzu()     // Catch:{ SQLiteException -> 0x006b }
            java.lang.String r9 = "Deleted fewer rows from upload_queue than expected"
            r8.zza(r9)     // Catch:{ SQLiteException -> 0x006b }
            goto L_0x007a
        L_0x006b:
            r5 = move-exception
            com.google.android.gms.measurement.internal.zzgo r6 = r7.zzj()     // Catch:{ all -> 0x0010 }
            com.google.android.gms.measurement.internal.zzgq r6 = r6.zzg()     // Catch:{ all -> 0x0010 }
            java.lang.String r7 = "Failed to delete a MeasurementBatch in a upload_queue table"
            r6.zza(r7, r5)     // Catch:{ all -> 0x0010 }
            throw r5     // Catch:{ all -> 0x0010 }
        L_0x007a:
            com.google.android.gms.measurement.internal.zzgo r7 = r4.zzj()     // Catch:{ all -> 0x0010 }
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzp()     // Catch:{ all -> 0x0010 }
            java.lang.String r8 = "Successfully uploaded batch from upload queue. appId, status"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0010 }
            r7.zza(r8, r5, r6)     // Catch:{ all -> 0x0010 }
            com.google.android.gms.measurement.internal.zzag r6 = r4.zze()     // Catch:{ all -> 0x0010 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbh.zzcb     // Catch:{ all -> 0x0010 }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r7)     // Catch:{ all -> 0x0010 }
            if (r6 == 0) goto L_0x00af
            com.google.android.gms.measurement.internal.zzgr r6 = r4.zzh()     // Catch:{ all -> 0x0010 }
            boolean r6 = r6.zzu()     // Catch:{ all -> 0x0010 }
            if (r6 == 0) goto L_0x00af
            com.google.android.gms.measurement.internal.zzal r6 = r4.zzf()     // Catch:{ all -> 0x0010 }
            boolean r6 = r6.zzs(r5)     // Catch:{ all -> 0x0010 }
            if (r6 == 0) goto L_0x00af
            r4.zze((java.lang.String) r5)     // Catch:{ all -> 0x0010 }
            goto L_0x00f0
        L_0x00af:
            r4.zzac()     // Catch:{ all -> 0x0010 }
            goto L_0x00f0
        L_0x00b3:
            java.lang.String r1 = new java.lang.String     // Catch:{ all -> 0x0010 }
            java.nio.charset.Charset r2 = java.nio.charset.StandardCharsets.UTF_8     // Catch:{ all -> 0x0010 }
            r1.<init>(r8, r2)     // Catch:{ all -> 0x0010 }
            int r8 = r1.length()     // Catch:{ all -> 0x0010 }
            r2 = 32
            int r8 = java.lang.Math.min(r2, r8)     // Catch:{ all -> 0x0010 }
            java.lang.String r8 = r1.substring(r0, r8)     // Catch:{ all -> 0x0010 }
            com.google.android.gms.measurement.internal.zzgo r1 = r4.zzj()     // Catch:{ all -> 0x0010 }
            com.google.android.gms.measurement.internal.zzgq r1 = r1.zzv()     // Catch:{ all -> 0x0010 }
            java.lang.String r2 = "Network upload failed. Will retry later. appId, status, error"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0010 }
            if (r7 != 0) goto L_0x00d9
            r7 = r8
        L_0x00d9:
            r1.zza(r2, r5, r6, r7)     // Catch:{ all -> 0x0010 }
            if (r9 == 0) goto L_0x00ed
            com.google.android.gms.measurement.internal.zzal r5 = r4.zzf()     // Catch:{ all -> 0x0010 }
            long r6 = r9.zza()     // Catch:{ all -> 0x0010 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0010 }
            r5.zza((java.lang.Long) r6)     // Catch:{ all -> 0x0010 }
        L_0x00ed:
            r4.zzac()     // Catch:{ all -> 0x0010 }
        L_0x00f0:
            r4.zzv = r0
            r4.zzaa()
            return
        L_0x00f6:
            r4.zzv = r0
            r4.zzaa()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zza(java.lang.String, int, java.lang.Throwable, byte[], com.google.android.gms.measurement.internal.zzoj):void");
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzg zzg2, zzfy.zzk.zza zza2) {
        zzl().zzt();
        zzs();
        zzfy.zza.C0006zza zzc2 = zzfy.zza.zzc();
        byte[] zzav = zzg2.zzav();
        if (zzav != null) {
            try {
                zzc2 = (zzfy.zza.C0006zza) zzoo.zza(zzc2, zzav);
            } catch (zzkb unused) {
                zzj().zzu().zza("Failed to parse locally stored ad campaign info. appId", zzgo.zza(zzg2.zzac()));
            }
        }
        Iterator<zzfy.zzf> it = zza2.zzaa().iterator();
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                break;
            }
            zzfy.zzf next = it.next();
            if (next.zzg().equals("_cmp")) {
                String str = (String) zzoo.zza(next, "gclid", (Object) "");
                String str2 = (String) zzoo.zza(next, "gbraid", (Object) "");
                String str3 = (String) zzoo.zza(next, "gad_source", (Object) "");
                if (!str.isEmpty() || !str2.isEmpty()) {
                    long longValue = ((Long) zzoo.zza(next, "click_timestamp", (Object) 0L)).longValue();
                    if (longValue <= 0) {
                        longValue = next.zzd();
                    }
                    if ("referrer API v2".equals(zzoo.zzb(next, "_cis"))) {
                        if (longValue > zzc2.zzb()) {
                            z = true;
                        }
                        if (z) {
                            if (str.isEmpty()) {
                                zzc2.zzh();
                            } else {
                                zzc2.zzf(str);
                            }
                            if (str2.isEmpty()) {
                                zzc2.zzg();
                            } else {
                                zzc2.zze(str2);
                            }
                            if (str3.isEmpty()) {
                                zzc2.zzf();
                            } else {
                                zzc2.zzd(str3);
                            }
                            zzc2.zzb(longValue);
                        }
                    } else {
                        if (longValue > zzc2.zza()) {
                            z = true;
                        }
                        if (z) {
                            if (str.isEmpty()) {
                                zzc2.zze();
                            } else {
                                zzc2.zzc(str);
                            }
                            if (str2.isEmpty()) {
                                zzc2.zzd();
                            } else {
                                zzc2.zzb(str2);
                            }
                            if (str3.isEmpty()) {
                                zzc2.zzc();
                            } else {
                                zzc2.zza(str3);
                            }
                            zzc2.zza(longValue);
                        }
                    }
                }
            }
        }
        if (!((zzfy.zza) ((zzjt) zzc2.zzai())).equals(zzfy.zza.zze())) {
            zza2.zza((zzfy.zza) ((zzjt) zzc2.zzai()));
        }
        zzg2.zza(((zzfy.zza) ((zzjt) zzc2.zzai())).zzca());
        if (zzg2.zzas()) {
            zzf().zza(zzg2, false, false);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzc(zzo zzo2) {
        zzl().zzt();
        zzs();
        Preconditions.checkNotNull(zzo2);
        Preconditions.checkNotEmpty(zzo2.zza);
        if (zze().zza(zzbh.zzdc)) {
            int i = 0;
            if (zze().zza(zzbh.zzbj)) {
                long currentTimeMillis = zzb().currentTimeMillis();
                int zzb2 = zze().zzb((String) null, zzbh.zzau);
                zze();
                long zzg2 = currentTimeMillis - zzag.zzg();
                while (i < zzb2 && zza((String) null, zzg2)) {
                    i++;
                }
            } else {
                zze();
                long zzh2 = zzag.zzh();
                while (((long) i) < zzh2 && zza(zzo2.zza, 0)) {
                    i++;
                }
            }
            if (zze().zza(zzbh.zzbk)) {
                zzab();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x03b2 A[Catch:{ SQLiteException -> 0x01b6, all -> 0x054a }] */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x03dd A[Catch:{ SQLiteException -> 0x01b6, all -> 0x054a }] */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x03f3 A[SYNTHETIC, Splitter:B:128:0x03f3] */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x0492 A[Catch:{ SQLiteException -> 0x01b6, all -> 0x054a }] */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x04b2 A[Catch:{ SQLiteException -> 0x01b6, all -> 0x054a }] */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x051c A[Catch:{ SQLiteException -> 0x01b6, all -> 0x054a }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x010b A[Catch:{ SQLiteException -> 0x01b6, all -> 0x054a }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x01cb A[Catch:{ SQLiteException -> 0x01b6, all -> 0x054a }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0225 A[Catch:{ SQLiteException -> 0x01b6, all -> 0x054a }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0232 A[Catch:{ SQLiteException -> 0x01b6, all -> 0x054a }] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0245 A[Catch:{ SQLiteException -> 0x01b6, all -> 0x054a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzd(com.google.android.gms.measurement.internal.zzo r25) {
        /*
            r24 = this;
            r1 = r24
            r2 = r25
            java.lang.String r3 = "_sysu"
            java.lang.String r4 = "_sys"
            java.lang.String r5 = "_pfo"
            java.lang.String r6 = "com.android.vending"
            java.lang.String r0 = "_npa"
            java.lang.String r7 = "_uwa"
            java.lang.String r8 = "app_id=?"
            com.google.android.gms.measurement.internal.zzhv r9 = r24.zzl()
            r9.zzt()
            r24.zzs()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r25)
            java.lang.String r9 = r2.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9)
            boolean r9 = zzi(r25)
            if (r9 != 0) goto L_0x002b
            return
        L_0x002b:
            com.google.android.gms.measurement.internal.zzal r9 = r24.zzf()
            java.lang.String r10 = r2.zza
            com.google.android.gms.measurement.internal.zzg r9 = r9.zze(r10)
            r10 = 0
            r11 = 0
            if (r9 == 0) goto L_0x005f
            java.lang.String r13 = r9.zzah()
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 == 0) goto L_0x005f
            java.lang.String r13 = r2.zzb
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x005f
            r9.zzd((long) r11)
            com.google.android.gms.measurement.internal.zzal r13 = r24.zzf()
            r13.zza((com.google.android.gms.measurement.internal.zzg) r9, (boolean) r10, (boolean) r10)
            com.google.android.gms.measurement.internal.zzhl r9 = r24.zzi()
            java.lang.String r13 = r2.zza
            r9.zzj(r13)
        L_0x005f:
            boolean r9 = r2.zzh
            if (r9 != 0) goto L_0x0067
            r24.zza((com.google.android.gms.measurement.internal.zzo) r25)
            return
        L_0x0067:
            long r13 = r2.zzl
            int r9 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r9 != 0) goto L_0x0075
            com.google.android.gms.common.util.Clock r9 = r24.zzb()
            long r13 = r9.currentTimeMillis()
        L_0x0075:
            com.google.android.gms.measurement.internal.zzhy r9 = r1.zzm
            com.google.android.gms.measurement.internal.zzaz r9 = r9.zzg()
            r9.zzt()
            int r9 = r2.zzm
            r15 = 1
            if (r9 == 0) goto L_0x009d
            if (r9 == r15) goto L_0x009d
            com.google.android.gms.measurement.internal.zzgo r16 = r24.zzj()
            com.google.android.gms.measurement.internal.zzgq r15 = r16.zzu()
            java.lang.String r11 = r2.zza
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r11)
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.String r12 = "Incorrect app type, assuming installed app. appId, appType"
            r15.zza(r12, r11, r9)
            r9 = r10
        L_0x009d:
            com.google.android.gms.measurement.internal.zzal r11 = r24.zzf()
            r11.zzp()
            com.google.android.gms.measurement.internal.zzal r11 = r24.zzf()     // Catch:{ all -> 0x054a }
            java.lang.String r12 = r2.zza     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzop r11 = r11.zze(r12, r0)     // Catch:{ all -> 0x054a }
            java.lang.Boolean r12 = zzh(r25)     // Catch:{ all -> 0x054a }
            r21 = r3
            r22 = r4
            if (r11 == 0) goto L_0x00c5
            java.lang.String r15 = "auto"
            java.lang.String r3 = r11.zzb     // Catch:{ all -> 0x054a }
            boolean r3 = r15.equals(r3)     // Catch:{ all -> 0x054a }
            if (r3 == 0) goto L_0x00c3
            goto L_0x00c5
        L_0x00c3:
            r3 = 1
            goto L_0x00f9
        L_0x00c5:
            if (r12 == 0) goto L_0x00f3
            com.google.android.gms.measurement.internal.zzon r0 = new com.google.android.gms.measurement.internal.zzon     // Catch:{ all -> 0x054a }
            java.lang.String r16 = "_npa"
            boolean r3 = r12.booleanValue()     // Catch:{ all -> 0x054a }
            if (r3 == 0) goto L_0x00d4
            r3 = 1
            goto L_0x00d6
        L_0x00d4:
            r3 = 0
        L_0x00d6:
            java.lang.Long r19 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x054a }
            java.lang.String r20 = "auto"
            r3 = 1
            r15 = r0
            r17 = r13
            r15.<init>(r16, r17, r19, r20)     // Catch:{ all -> 0x054a }
            if (r11 == 0) goto L_0x00ef
            java.lang.Object r4 = r11.zze     // Catch:{ all -> 0x054a }
            java.lang.Long r11 = r0.zzc     // Catch:{ all -> 0x054a }
            boolean r4 = r4.equals(r11)     // Catch:{ all -> 0x054a }
            if (r4 != 0) goto L_0x00f9
        L_0x00ef:
            r1.zza((com.google.android.gms.measurement.internal.zzon) r0, (com.google.android.gms.measurement.internal.zzo) r2)     // Catch:{ all -> 0x054a }
            goto L_0x00f9
        L_0x00f3:
            r3 = 1
            if (r11 == 0) goto L_0x00f9
            r1.zza((java.lang.String) r0, (com.google.android.gms.measurement.internal.zzo) r2)     // Catch:{ all -> 0x054a }
        L_0x00f9:
            com.google.android.gms.measurement.internal.zzal r0 = r24.zzf()     // Catch:{ all -> 0x054a }
            java.lang.String r4 = r2.zza     // Catch:{ all -> 0x054a }
            java.lang.Object r4 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)     // Catch:{ all -> 0x054a }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzg r0 = r0.zze(r4)     // Catch:{ all -> 0x054a }
            if (r0 == 0) goto L_0x01c9
            r24.zzq()     // Catch:{ all -> 0x054a }
            java.lang.String r11 = r2.zzb     // Catch:{ all -> 0x054a }
            java.lang.String r12 = r0.zzah()     // Catch:{ all -> 0x054a }
            java.lang.String r15 = r2.zzp     // Catch:{ all -> 0x054a }
            java.lang.String r4 = r0.zzaa()     // Catch:{ all -> 0x054a }
            boolean r4 = com.google.android.gms.measurement.internal.zzos.zza((java.lang.String) r11, (java.lang.String) r12, (java.lang.String) r15, (java.lang.String) r4)     // Catch:{ all -> 0x054a }
            if (r4 == 0) goto L_0x01c9
            com.google.android.gms.measurement.internal.zzgo r4 = r24.zzj()     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzgq r4 = r4.zzu()     // Catch:{ all -> 0x054a }
            java.lang.String r11 = "New GMP App Id passed in. Removing cached database data. appId"
            java.lang.String r12 = r0.zzac()     // Catch:{ all -> 0x054a }
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r12)     // Catch:{ all -> 0x054a }
            r4.zza(r11, r12)     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzal r4 = r24.zzf()     // Catch:{ all -> 0x054a }
            java.lang.String r11 = r0.zzac()     // Catch:{ all -> 0x054a }
            r4.zzal()     // Catch:{ all -> 0x054a }
            r4.zzt()     // Catch:{ all -> 0x054a }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r11)     // Catch:{ all -> 0x054a }
            android.database.sqlite.SQLiteDatabase r0 = r4.e_()     // Catch:{ SQLiteException -> 0x01b6 }
            java.lang.String[] r12 = new java.lang.String[]{r11}     // Catch:{ SQLiteException -> 0x01b6 }
            java.lang.String r15 = "events"
            int r15 = r0.delete(r15, r8, r12)     // Catch:{ SQLiteException -> 0x01b6 }
            int r15 = r15 + r10
            java.lang.String r10 = "user_attributes"
            int r10 = r0.delete(r10, r8, r12)     // Catch:{ SQLiteException -> 0x01b6 }
            int r15 = r15 + r10
            java.lang.String r10 = "conditional_properties"
            int r10 = r0.delete(r10, r8, r12)     // Catch:{ SQLiteException -> 0x01b6 }
            int r15 = r15 + r10
            java.lang.String r10 = "apps"
            int r10 = r0.delete(r10, r8, r12)     // Catch:{ SQLiteException -> 0x01b6 }
            int r15 = r15 + r10
            java.lang.String r10 = "raw_events"
            int r10 = r0.delete(r10, r8, r12)     // Catch:{ SQLiteException -> 0x01b6 }
            int r15 = r15 + r10
            java.lang.String r10 = "raw_events_metadata"
            int r10 = r0.delete(r10, r8, r12)     // Catch:{ SQLiteException -> 0x01b6 }
            int r15 = r15 + r10
            java.lang.String r10 = "event_filters"
            int r10 = r0.delete(r10, r8, r12)     // Catch:{ SQLiteException -> 0x01b6 }
            int r15 = r15 + r10
            java.lang.String r10 = "property_filters"
            int r10 = r0.delete(r10, r8, r12)     // Catch:{ SQLiteException -> 0x01b6 }
            int r15 = r15 + r10
            java.lang.String r10 = "audience_filter_values"
            int r10 = r0.delete(r10, r8, r12)     // Catch:{ SQLiteException -> 0x01b6 }
            int r15 = r15 + r10
            java.lang.String r10 = "consent_settings"
            int r10 = r0.delete(r10, r8, r12)     // Catch:{ SQLiteException -> 0x01b6 }
            int r15 = r15 + r10
            java.lang.String r10 = "default_event_params"
            int r10 = r0.delete(r10, r8, r12)     // Catch:{ SQLiteException -> 0x01b6 }
            int r15 = r15 + r10
            java.lang.String r10 = "trigger_uris"
            int r0 = r0.delete(r10, r8, r12)     // Catch:{ SQLiteException -> 0x01b6 }
            int r15 = r15 + r0
            if (r15 <= 0) goto L_0x01c8
            com.google.android.gms.measurement.internal.zzgo r0 = r4.zzj()     // Catch:{ SQLiteException -> 0x01b6 }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzp()     // Catch:{ SQLiteException -> 0x01b6 }
            java.lang.String r8 = "Deleted application data. app, records"
            java.lang.Integer r10 = java.lang.Integer.valueOf(r15)     // Catch:{ SQLiteException -> 0x01b6 }
            r0.zza(r8, r11, r10)     // Catch:{ SQLiteException -> 0x01b6 }
            goto L_0x01c8
        L_0x01b6:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzgo r4 = r4.zzj()     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzgq r4 = r4.zzg()     // Catch:{ all -> 0x054a }
            java.lang.String r8 = "Error deleting application data. appId, error"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r11)     // Catch:{ all -> 0x054a }
            r4.zza(r8, r10, r0)     // Catch:{ all -> 0x054a }
        L_0x01c8:
            r0 = 0
        L_0x01c9:
            if (r0 == 0) goto L_0x0220
            long r10 = r0.zze()     // Catch:{ all -> 0x054a }
            r15 = -2147483648(0xffffffff80000000, double:NaN)
            int r4 = (r10 > r15 ? 1 : (r10 == r15 ? 0 : -1))
            if (r4 == 0) goto L_0x01e2
            long r10 = r0.zze()     // Catch:{ all -> 0x054a }
            long r3 = r2.zzj     // Catch:{ all -> 0x054a }
            int r3 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x01e2
            r3 = 1
            goto L_0x01e3
        L_0x01e2:
            r3 = 0
        L_0x01e3:
            java.lang.String r4 = r0.zzaf()     // Catch:{ all -> 0x054a }
            long r10 = r0.zze()     // Catch:{ all -> 0x054a }
            int r0 = (r10 > r15 ? 1 : (r10 == r15 ? 0 : -1))
            if (r0 != 0) goto L_0x01fb
            if (r4 == 0) goto L_0x01fb
            java.lang.String r0 = r2.zzc     // Catch:{ all -> 0x054a }
            boolean r0 = r4.equals(r0)     // Catch:{ all -> 0x054a }
            if (r0 != 0) goto L_0x01fb
            r15 = 1
            goto L_0x01fc
        L_0x01fb:
            r15 = 0
        L_0x01fc:
            r0 = r3 | r15
            if (r0 == 0) goto L_0x0220
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x054a }
            r0.<init>()     // Catch:{ all -> 0x054a }
            java.lang.String r3 = "_pv"
            r0.putString(r3, r4)     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzbf r3 = new com.google.android.gms.measurement.internal.zzbf     // Catch:{ all -> 0x054a }
            java.lang.String r16 = "_au"
            com.google.android.gms.measurement.internal.zzbe r4 = new com.google.android.gms.measurement.internal.zzbe     // Catch:{ all -> 0x054a }
            r4.<init>(r0)     // Catch:{ all -> 0x054a }
            java.lang.String r18 = "auto"
            r15 = r3
            r17 = r4
            r19 = r13
            r15.<init>(r16, r17, r18, r19)     // Catch:{ all -> 0x054a }
            r1.zza((com.google.android.gms.measurement.internal.zzbf) r3, (com.google.android.gms.measurement.internal.zzo) r2)     // Catch:{ all -> 0x054a }
        L_0x0220:
            r24.zza((com.google.android.gms.measurement.internal.zzo) r25)     // Catch:{ all -> 0x054a }
            if (r9 != 0) goto L_0x0232
            com.google.android.gms.measurement.internal.zzal r0 = r24.zzf()     // Catch:{ all -> 0x054a }
            java.lang.String r3 = r2.zza     // Catch:{ all -> 0x054a }
            java.lang.String r4 = "_f"
            com.google.android.gms.measurement.internal.zzbb r0 = r0.zzd(r3, r4)     // Catch:{ all -> 0x054a }
            goto L_0x0243
        L_0x0232:
            r3 = 1
            if (r9 != r3) goto L_0x0242
            com.google.android.gms.measurement.internal.zzal r0 = r24.zzf()     // Catch:{ all -> 0x054a }
            java.lang.String r3 = r2.zza     // Catch:{ all -> 0x054a }
            java.lang.String r4 = "_v"
            com.google.android.gms.measurement.internal.zzbb r0 = r0.zzd(r3, r4)     // Catch:{ all -> 0x054a }
            goto L_0x0243
        L_0x0242:
            r0 = 0
        L_0x0243:
            if (r0 != 0) goto L_0x051c
            r3 = 3600000(0x36ee80, double:1.7786363E-317)
            long r10 = r13 / r3
            r15 = 1
            long r10 = r10 + r15
            long r10 = r10 * r3
            java.lang.String r3 = "_dac"
            java.lang.String r4 = "_et"
            java.lang.String r12 = "_r"
            java.lang.String r15 = "_c"
            if (r9 != 0) goto L_0x04cd
            com.google.android.gms.measurement.internal.zzon r0 = new com.google.android.gms.measurement.internal.zzon     // Catch:{ all -> 0x054a }
            java.lang.String r16 = "_fot"
            java.lang.Long r19 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x054a }
            java.lang.String r20 = "auto"
            r9 = r15
            r15 = r0
            r17 = r13
            r15.<init>(r16, r17, r19, r20)     // Catch:{ all -> 0x054a }
            r1.zza((com.google.android.gms.measurement.internal.zzon) r0, (com.google.android.gms.measurement.internal.zzo) r2)     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzhv r0 = r24.zzl()     // Catch:{ all -> 0x054a }
            r0.zzt()     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzhf r0 = r1.zzl     // Catch:{ all -> 0x054a }
            java.lang.Object r0 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)     // Catch:{ all -> 0x054a }
            r10 = r0
            com.google.android.gms.measurement.internal.zzhf r10 = (com.google.android.gms.measurement.internal.zzhf) r10     // Catch:{ all -> 0x054a }
            java.lang.String r0 = r2.zza     // Catch:{ all -> 0x054a }
            if (r0 == 0) goto L_0x0371
            boolean r11 = r0.isEmpty()     // Catch:{ all -> 0x054a }
            if (r11 == 0) goto L_0x0288
            goto L_0x0371
        L_0x0288:
            com.google.android.gms.measurement.internal.zzhy r11 = r10.zza     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzhv r11 = r11.zzl()     // Catch:{ all -> 0x054a }
            r11.zzt()     // Catch:{ all -> 0x054a }
            boolean r11 = r10.zza()     // Catch:{ all -> 0x054a }
            if (r11 != 0) goto L_0x02a8
            com.google.android.gms.measurement.internal.zzhy r0 = r10.zza     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzgo r0 = r0.zzj()     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzo()     // Catch:{ all -> 0x054a }
            java.lang.String r6 = "Install Referrer Reporter is not available"
            r0.zza(r6)     // Catch:{ all -> 0x054a }
            goto L_0x0380
        L_0x02a8:
            com.google.android.gms.measurement.internal.zzhi r11 = new com.google.android.gms.measurement.internal.zzhi     // Catch:{ all -> 0x054a }
            r11.<init>(r10, r0)     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzhy r0 = r10.zza     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzhv r0 = r0.zzl()     // Catch:{ all -> 0x054a }
            r0.zzt()     // Catch:{ all -> 0x054a }
            android.content.Intent r0 = new android.content.Intent     // Catch:{ all -> 0x054a }
            java.lang.String r15 = "com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE"
            r0.<init>(r15)     // Catch:{ all -> 0x054a }
            android.content.ComponentName r15 = new android.content.ComponentName     // Catch:{ all -> 0x054a }
            java.lang.String r8 = "com.google.android.finsky.externalreferrer.GetInstallReferrerService"
            r15.<init>(r6, r8)     // Catch:{ all -> 0x054a }
            r0.setComponent(r15)     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzhy r8 = r10.zza     // Catch:{ all -> 0x054a }
            android.content.Context r8 = r8.zza()     // Catch:{ all -> 0x054a }
            android.content.pm.PackageManager r8 = r8.getPackageManager()     // Catch:{ all -> 0x054a }
            if (r8 != 0) goto L_0x02e4
            com.google.android.gms.measurement.internal.zzhy r0 = r10.zza     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzgo r0 = r0.zzj()     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzw()     // Catch:{ all -> 0x054a }
            java.lang.String r6 = "Failed to obtain Package Manager to verify binding conditions for Install Referrer"
            r0.zza(r6)     // Catch:{ all -> 0x054a }
            goto L_0x0380
        L_0x02e4:
            r15 = 0
            java.util.List r8 = r8.queryIntentServices(r0, r15)     // Catch:{ all -> 0x054a }
            if (r8 == 0) goto L_0x0361
            boolean r16 = r8.isEmpty()     // Catch:{ all -> 0x054a }
            if (r16 != 0) goto L_0x0361
            java.lang.Object r8 = r8.get(r15)     // Catch:{ all -> 0x054a }
            android.content.pm.ResolveInfo r8 = (android.content.pm.ResolveInfo) r8     // Catch:{ all -> 0x054a }
            android.content.pm.ServiceInfo r15 = r8.serviceInfo     // Catch:{ all -> 0x054a }
            if (r15 == 0) goto L_0x0380
            android.content.pm.ServiceInfo r15 = r8.serviceInfo     // Catch:{ all -> 0x054a }
            java.lang.String r15 = r15.packageName     // Catch:{ all -> 0x054a }
            android.content.pm.ServiceInfo r8 = r8.serviceInfo     // Catch:{ all -> 0x054a }
            java.lang.String r8 = r8.name     // Catch:{ all -> 0x054a }
            if (r8 == 0) goto L_0x0351
            boolean r6 = r6.equals(r15)     // Catch:{ all -> 0x054a }
            if (r6 == 0) goto L_0x0351
            boolean r6 = r10.zza()     // Catch:{ all -> 0x054a }
            if (r6 == 0) goto L_0x0351
            android.content.Intent r6 = new android.content.Intent     // Catch:{ all -> 0x054a }
            r6.<init>(r0)     // Catch:{ all -> 0x054a }
            com.google.android.gms.common.stats.ConnectionTracker r0 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ RuntimeException -> 0x033c }
            com.google.android.gms.measurement.internal.zzhy r8 = r10.zza     // Catch:{ RuntimeException -> 0x033c }
            android.content.Context r8 = r8.zza()     // Catch:{ RuntimeException -> 0x033c }
            r15 = 1
            boolean r0 = r0.bindService(r8, r6, r11, r15)     // Catch:{ RuntimeException -> 0x033c }
            com.google.android.gms.measurement.internal.zzhy r6 = r10.zza     // Catch:{ RuntimeException -> 0x033c }
            com.google.android.gms.measurement.internal.zzgo r6 = r6.zzj()     // Catch:{ RuntimeException -> 0x033c }
            com.google.android.gms.measurement.internal.zzgq r6 = r6.zzp()     // Catch:{ RuntimeException -> 0x033c }
            java.lang.String r11 = "Install Referrer Service is"
            if (r0 == 0) goto L_0x0336
            java.lang.String r0 = "available"
            goto L_0x0338
        L_0x0336:
            java.lang.String r0 = "not available"
        L_0x0338:
            r6.zza(r11, r0)     // Catch:{ RuntimeException -> 0x033c }
            goto L_0x0380
        L_0x033c:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzhy r6 = r10.zza     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzgo r6 = r6.zzj()     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzgq r6 = r6.zzg()     // Catch:{ all -> 0x054a }
            java.lang.String r10 = "Exception occurred while binding to Install Referrer Service"
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x054a }
            r6.zza(r10, r0)     // Catch:{ all -> 0x054a }
            goto L_0x0380
        L_0x0351:
            com.google.android.gms.measurement.internal.zzhy r0 = r10.zza     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzgo r0 = r0.zzj()     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzu()     // Catch:{ all -> 0x054a }
            java.lang.String r6 = "Play Store version 8.3.73 or higher required for Install Referrer"
            r0.zza(r6)     // Catch:{ all -> 0x054a }
            goto L_0x0380
        L_0x0361:
            com.google.android.gms.measurement.internal.zzhy r0 = r10.zza     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzgo r0 = r0.zzj()     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzo()     // Catch:{ all -> 0x054a }
            java.lang.String r6 = "Play Service for fetching Install Referrer is unavailable on device"
            r0.zza(r6)     // Catch:{ all -> 0x054a }
            goto L_0x0380
        L_0x0371:
            com.google.android.gms.measurement.internal.zzhy r0 = r10.zza     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzgo r0 = r0.zzj()     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzw()     // Catch:{ all -> 0x054a }
            java.lang.String r6 = "Install Referrer Reporter was called with invalid app package name"
            r0.zza(r6)     // Catch:{ all -> 0x054a }
        L_0x0380:
            com.google.android.gms.measurement.internal.zzhv r0 = r24.zzl()     // Catch:{ all -> 0x054a }
            r0.zzt()     // Catch:{ all -> 0x054a }
            r24.zzs()     // Catch:{ all -> 0x054a }
            android.os.Bundle r6 = new android.os.Bundle     // Catch:{ all -> 0x054a }
            r6.<init>()     // Catch:{ all -> 0x054a }
            r10 = 1
            r6.putLong(r9, r10)     // Catch:{ all -> 0x054a }
            r6.putLong(r12, r10)     // Catch:{ all -> 0x054a }
            r9 = 0
            r6.putLong(r7, r9)     // Catch:{ all -> 0x054a }
            r6.putLong(r5, r9)     // Catch:{ all -> 0x054a }
            r11 = r22
            r6.putLong(r11, r9)     // Catch:{ all -> 0x054a }
            r12 = r21
            r6.putLong(r12, r9)     // Catch:{ all -> 0x054a }
            r9 = 1
            r6.putLong(r4, r9)     // Catch:{ all -> 0x054a }
            boolean r0 = r2.zzo     // Catch:{ all -> 0x054a }
            if (r0 == 0) goto L_0x03b5
            r6.putLong(r3, r9)     // Catch:{ all -> 0x054a }
        L_0x03b5:
            java.lang.String r0 = r2.zza     // Catch:{ all -> 0x054a }
            java.lang.Object r0 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)     // Catch:{ all -> 0x054a }
            r3 = r0
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzal r0 = r24.zzf()     // Catch:{ all -> 0x054a }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)     // Catch:{ all -> 0x054a }
            r0.zzt()     // Catch:{ all -> 0x054a }
            r0.zzal()     // Catch:{ all -> 0x054a }
            java.lang.String r4 = "first_open_count"
            long r9 = r0.zzb((java.lang.String) r3, (java.lang.String) r4)     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzhy r0 = r1.zzm     // Catch:{ all -> 0x054a }
            android.content.Context r0 = r0.zza()     // Catch:{ all -> 0x054a }
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch:{ all -> 0x054a }
            if (r0 != 0) goto L_0x03f3
            com.google.android.gms.measurement.internal.zzgo r0 = r24.zzj()     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzg()     // Catch:{ all -> 0x054a }
            java.lang.String r4 = "PackageManager is null, first open report might be inaccurate. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r3)     // Catch:{ all -> 0x054a }
            r0.zza(r4, r3)     // Catch:{ all -> 0x054a }
            r8 = r5
        L_0x03ef:
            r3 = 0
            goto L_0x04ae
        L_0x03f3:
            com.google.android.gms.measurement.internal.zzhy r0 = r1.zzm     // Catch:{ NameNotFoundException -> 0x0403 }
            android.content.Context r0 = r0.zza()     // Catch:{ NameNotFoundException -> 0x0403 }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0)     // Catch:{ NameNotFoundException -> 0x0403 }
            r4 = 0
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r3, r4)     // Catch:{ NameNotFoundException -> 0x0403 }
            goto L_0x0416
        L_0x0403:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzgo r4 = r24.zzj()     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzgq r4 = r4.zzg()     // Catch:{ all -> 0x054a }
            java.lang.String r15 = "Package info is null, first open report might be inaccurate. appId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r3)     // Catch:{ all -> 0x054a }
            r4.zza(r15, r8, r0)     // Catch:{ all -> 0x054a }
            r0 = 0
        L_0x0416:
            if (r0 == 0) goto L_0x0468
            r8 = r5
            long r4 = r0.firstInstallTime     // Catch:{ all -> 0x054a }
            r15 = 0
            int r4 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r4 == 0) goto L_0x0469
            long r4 = r0.firstInstallTime     // Catch:{ all -> 0x054a }
            r22 = r11
            r23 = r12
            long r11 = r0.lastUpdateTime     // Catch:{ all -> 0x054a }
            int r0 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x044c
            com.google.android.gms.measurement.internal.zzag r0 = r24.zze()     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzbh.zzbs     // Catch:{ all -> 0x054a }
            boolean r0 = r0.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r4)     // Catch:{ all -> 0x054a }
            if (r0 == 0) goto L_0x0445
            r4 = 0
            int r0 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x044a
            r4 = 1
            r6.putLong(r7, r4)     // Catch:{ all -> 0x054a }
            goto L_0x044a
        L_0x0445:
            r4 = 1
            r6.putLong(r7, r4)     // Catch:{ all -> 0x054a }
        L_0x044a:
            r15 = 0
            goto L_0x044d
        L_0x044c:
            r15 = 1
        L_0x044d:
            com.google.android.gms.measurement.internal.zzon r0 = new com.google.android.gms.measurement.internal.zzon     // Catch:{ all -> 0x054a }
            java.lang.String r16 = "_fi"
            if (r15 == 0) goto L_0x0456
            r4 = 1
            goto L_0x0458
        L_0x0456:
            r4 = 0
        L_0x0458:
            java.lang.Long r19 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x054a }
            java.lang.String r20 = "auto"
            r15 = r0
            r17 = r13
            r15.<init>(r16, r17, r19, r20)     // Catch:{ all -> 0x054a }
            r1.zza((com.google.android.gms.measurement.internal.zzon) r0, (com.google.android.gms.measurement.internal.zzo) r2)     // Catch:{ all -> 0x054a }
            goto L_0x046d
        L_0x0468:
            r8 = r5
        L_0x0469:
            r22 = r11
            r23 = r12
        L_0x046d:
            com.google.android.gms.measurement.internal.zzhy r0 = r1.zzm     // Catch:{ NameNotFoundException -> 0x047d }
            android.content.Context r0 = r0.zza()     // Catch:{ NameNotFoundException -> 0x047d }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0)     // Catch:{ NameNotFoundException -> 0x047d }
            r4 = 0
            android.content.pm.ApplicationInfo r4 = r0.getApplicationInfo(r3, r4)     // Catch:{ NameNotFoundException -> 0x047d }
            goto L_0x0490
        L_0x047d:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzgo r4 = r24.zzj()     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzgq r4 = r4.zzg()     // Catch:{ all -> 0x054a }
            java.lang.String r5 = "Application info is null, first open report might be inaccurate. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r3)     // Catch:{ all -> 0x054a }
            r4.zza(r5, r3, r0)     // Catch:{ all -> 0x054a }
            r4 = 0
        L_0x0490:
            if (r4 == 0) goto L_0x03ef
            int r0 = r4.flags     // Catch:{ all -> 0x054a }
            r3 = 1
            r0 = r0 & r3
            if (r0 == 0) goto L_0x049f
            r3 = r22
            r11 = 1
            r6.putLong(r3, r11)     // Catch:{ all -> 0x054a }
        L_0x049f:
            int r0 = r4.flags     // Catch:{ all -> 0x054a }
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x03ef
            r3 = r23
            r4 = 1
            r6.putLong(r3, r4)     // Catch:{ all -> 0x054a }
            goto L_0x03ef
        L_0x04ae:
            int r0 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r0 < 0) goto L_0x04b5
            r6.putLong(r8, r9)     // Catch:{ all -> 0x054a }
        L_0x04b5:
            com.google.android.gms.measurement.internal.zzbf r0 = new com.google.android.gms.measurement.internal.zzbf     // Catch:{ all -> 0x054a }
            java.lang.String r16 = "_f"
            com.google.android.gms.measurement.internal.zzbe r3 = new com.google.android.gms.measurement.internal.zzbe     // Catch:{ all -> 0x054a }
            r3.<init>(r6)     // Catch:{ all -> 0x054a }
            java.lang.String r18 = "auto"
            r15 = r0
            r17 = r3
            r19 = r13
            r15.<init>(r16, r17, r18, r19)     // Catch:{ all -> 0x054a }
            r1.zzb((com.google.android.gms.measurement.internal.zzbf) r0, (com.google.android.gms.measurement.internal.zzo) r2)     // Catch:{ all -> 0x054a }
            goto L_0x053b
        L_0x04cd:
            r5 = r15
            r6 = 1
            if (r9 != r6) goto L_0x053b
            com.google.android.gms.measurement.internal.zzon r0 = new com.google.android.gms.measurement.internal.zzon     // Catch:{ all -> 0x054a }
            java.lang.String r16 = "_fvt"
            java.lang.Long r19 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x054a }
            java.lang.String r20 = "auto"
            r15 = r0
            r17 = r13
            r15.<init>(r16, r17, r19, r20)     // Catch:{ all -> 0x054a }
            r1.zza((com.google.android.gms.measurement.internal.zzon) r0, (com.google.android.gms.measurement.internal.zzo) r2)     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzhv r0 = r24.zzl()     // Catch:{ all -> 0x054a }
            r0.zzt()     // Catch:{ all -> 0x054a }
            r24.zzs()     // Catch:{ all -> 0x054a }
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x054a }
            r0.<init>()     // Catch:{ all -> 0x054a }
            r6 = 1
            r0.putLong(r5, r6)     // Catch:{ all -> 0x054a }
            r0.putLong(r12, r6)     // Catch:{ all -> 0x054a }
            r0.putLong(r4, r6)     // Catch:{ all -> 0x054a }
            boolean r4 = r2.zzo     // Catch:{ all -> 0x054a }
            if (r4 == 0) goto L_0x0505
            r0.putLong(r3, r6)     // Catch:{ all -> 0x054a }
        L_0x0505:
            com.google.android.gms.measurement.internal.zzbf r3 = new com.google.android.gms.measurement.internal.zzbf     // Catch:{ all -> 0x054a }
            java.lang.String r16 = "_v"
            com.google.android.gms.measurement.internal.zzbe r4 = new com.google.android.gms.measurement.internal.zzbe     // Catch:{ all -> 0x054a }
            r4.<init>(r0)     // Catch:{ all -> 0x054a }
            java.lang.String r18 = "auto"
            r15 = r3
            r17 = r4
            r19 = r13
            r15.<init>(r16, r17, r18, r19)     // Catch:{ all -> 0x054a }
            r1.zzb((com.google.android.gms.measurement.internal.zzbf) r3, (com.google.android.gms.measurement.internal.zzo) r2)     // Catch:{ all -> 0x054a }
            goto L_0x053b
        L_0x051c:
            boolean r0 = r2.zzi     // Catch:{ all -> 0x054a }
            if (r0 == 0) goto L_0x053b
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x054a }
            r0.<init>()     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzbf r3 = new com.google.android.gms.measurement.internal.zzbf     // Catch:{ all -> 0x054a }
            java.lang.String r16 = "_cd"
            com.google.android.gms.measurement.internal.zzbe r4 = new com.google.android.gms.measurement.internal.zzbe     // Catch:{ all -> 0x054a }
            r4.<init>(r0)     // Catch:{ all -> 0x054a }
            java.lang.String r18 = "auto"
            r15 = r3
            r17 = r4
            r19 = r13
            r15.<init>(r16, r17, r18, r19)     // Catch:{ all -> 0x054a }
            r1.zzb((com.google.android.gms.measurement.internal.zzbf) r3, (com.google.android.gms.measurement.internal.zzo) r2)     // Catch:{ all -> 0x054a }
        L_0x053b:
            com.google.android.gms.measurement.internal.zzal r0 = r24.zzf()     // Catch:{ all -> 0x054a }
            r0.zzw()     // Catch:{ all -> 0x054a }
            com.google.android.gms.measurement.internal.zzal r0 = r24.zzf()
            r0.zzu()
            return
        L_0x054a:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzal r2 = r24.zzf()
            r2.zzu()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zzd(com.google.android.gms.measurement.internal.zzo):void");
    }

    /* access modifiers changed from: package-private */
    public final void zzu() {
        this.zzs++;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzae zzae2) {
        zzo zzc2 = zzc((String) Preconditions.checkNotNull(zzae2.zza));
        if (zzc2 != null) {
            zza(zzae2, zzc2);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzae zzae2, zzo zzo2) {
        Preconditions.checkNotNull(zzae2);
        Preconditions.checkNotEmpty(zzae2.zza);
        Preconditions.checkNotNull(zzae2.zzc);
        Preconditions.checkNotEmpty(zzae2.zzc.zza);
        zzl().zzt();
        zzs();
        if (zzi(zzo2)) {
            if (!zzo2.zzh) {
                zza(zzo2);
                return;
            }
            zzf().zzp();
            try {
                zza(zzo2);
                String str = (String) Preconditions.checkNotNull(zzae2.zza);
                zzae zzc2 = zzf().zzc(str, zzae2.zzc.zza);
                if (zzc2 != null) {
                    zzj().zzc().zza("Removing conditional user property", zzae2.zza, this.zzm.zzk().zzc(zzae2.zzc.zza));
                    zzf().zza(str, zzae2.zzc.zza);
                    if (zzc2.zze) {
                        zzf().zzh(str, zzae2.zzc.zza);
                    }
                    if (zzae2.zzk != null) {
                        zzc((zzbf) Preconditions.checkNotNull(zzq().zza(str, ((zzbf) Preconditions.checkNotNull(zzae2.zzk)).zza, zzae2.zzk.zzb != null ? zzae2.zzk.zzb.zzb() : null, zzc2.zzb, zzae2.zzk.zzd, true, true)), zzo2);
                    }
                } else {
                    zzj().zzu().zza("Conditional user property doesn't exist", zzgo.zza(zzae2.zza), this.zzm.zzk().zzc(zzae2.zzc.zza));
                }
                zzf().zzw();
            } finally {
                zzf().zzu();
            }
        }
    }

    private static void zza(zzfy.zzf.zza zza2, String str) {
        List<zzfy.zzh> zzf2 = zza2.zzf();
        for (int i = 0; i < zzf2.size(); i++) {
            if (str.equals(zzf2.get(i).zzg())) {
                zza2.zza(i);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, zzo zzo2) {
        zzl().zzt();
        zzs();
        if (zzi(zzo2)) {
            if (!zzo2.zzh) {
                zza(zzo2);
                return;
            }
            Boolean zzh2 = zzh(zzo2);
            if (!"_npa".equals(str) || zzh2 == null) {
                zzj().zzc().zza("Removing user property", this.zzm.zzk().zzc(str));
                zzf().zzp();
                try {
                    zza(zzo2);
                    if (Columns.ID.equals(str)) {
                        zzf().zzh((String) Preconditions.checkNotNull(zzo2.zza), "_lair");
                    }
                    zzf().zzh((String) Preconditions.checkNotNull(zzo2.zza), str);
                    zzf().zzw();
                    zzj().zzc().zza("User property removed", this.zzm.zzk().zzc(str));
                } finally {
                    zzf().zzu();
                }
            } else {
                zzj().zzc().zza("Falling back to manifest metadata value for ad personalization");
                zza(new zzon("_npa", zzb().currentTimeMillis(), Long.valueOf(zzh2.booleanValue() ? 1 : 0), "auto"), zzo2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zze(zzo zzo2) {
        if (this.zzz != null) {
            ArrayList arrayList = new ArrayList();
            this.zzaa = arrayList;
            arrayList.addAll(this.zzz);
        }
        zzal zzf2 = zzf();
        String str = (String) Preconditions.checkNotNull(zzo2.zza);
        Preconditions.checkNotEmpty(str);
        zzf2.zzt();
        zzf2.zzal();
        try {
            SQLiteDatabase e_ = zzf2.e_();
            String[] strArr = {str};
            int delete = e_.delete("apps", "app_id=?", strArr) + 0 + e_.delete("events", "app_id=?", strArr) + e_.delete("events_snapshot", "app_id=?", strArr) + e_.delete("user_attributes", "app_id=?", strArr) + e_.delete("conditional_properties", "app_id=?", strArr) + e_.delete("raw_events", "app_id=?", strArr) + e_.delete("raw_events_metadata", "app_id=?", strArr) + e_.delete("queue", "app_id=?", strArr) + e_.delete("audience_filter_values", "app_id=?", strArr) + e_.delete("main_event_params", "app_id=?", strArr) + e_.delete("default_event_params", "app_id=?", strArr) + e_.delete("trigger_uris", "app_id=?", strArr) + e_.delete("upload_queue", "app_id=?", strArr);
            if (delete > 0) {
                zzf2.zzj().zzp().zza("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzf2.zzj().zzg().zza("Error resetting analytics data. appId, error", zzgo.zza(str), e);
        }
        if (zzo2.zzh) {
            zzd(zzo2);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzf(zzo zzo2) {
        zzo zzo3 = zzo2;
        zzl().zzt();
        zzs();
        Preconditions.checkNotEmpty(zzo3.zza);
        zzax zza2 = zzax.zza(zzo3.zzz);
        zzj().zzp().zza("Setting DMA consent for package", zzo3.zza, zza2);
        String str = zzo3.zza;
        zzl().zzt();
        zzs();
        zzjh zzc2 = zzax.zza(zza(str), 100).zzc();
        this.zzad.put(str, zza2);
        zzf().zza(str, zza2);
        zzjh zzc3 = zzax.zza(zza(str), 100).zzc();
        zzl().zzt();
        zzs();
        boolean z = true;
        boolean z2 = zzc2 == zzjh.DENIED && zzc3 == zzjh.GRANTED;
        boolean z3 = zzc2 == zzjh.GRANTED && zzc3 == zzjh.DENIED;
        if (zze().zza(zzbh.zzcq)) {
            if (!z2 && !z3) {
                z = false;
            }
            z2 = z;
        }
        if (z2) {
            zzj().zzp().zza("Generated _dcu event for", str);
            Bundle bundle = new Bundle();
            if (zzf().zza(zzx(), str, false, false, false, false, false, false, false).zzf < ((long) zze().zzb(str, zzbh.zzay))) {
                bundle.putLong("_r", 1);
                zzj().zzp().zza("_dcu realtime event count", str, Long.valueOf(zzf().zza(zzx(), str, false, false, false, false, false, true, false).zzf));
            }
            this.zzah.zza(str, "_dcu", bundle);
        }
    }

    public final void zza(String str, zzlk zzlk) {
        zzl().zzt();
        String str2 = this.zzag;
        if (str2 == null || str2.equals(str) || zzlk != null) {
            this.zzag = str;
            this.zzaf = zzlk;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzg(zzo zzo2) {
        zzl().zzt();
        zzs();
        Preconditions.checkNotEmpty(zzo2.zza);
        zzje zza2 = zzje.zza(zzo2.zzt, zzo2.zzy);
        zzje zzb2 = zzb(zzo2.zza);
        zzj().zzp().zza("Setting storage consent for package", zzo2.zza, zza2);
        zza(zzo2.zza, zza2);
        if ((!zznm.zza() || !zze().zza(zzbh.zzcy)) && zza2.zzc(zzb2)) {
            zze(zzo2);
        }
    }

    private final void zza(List<Long> list) {
        Preconditions.checkArgument(!list.isEmpty());
        if (this.zzz != null) {
            zzj().zzg().zza("Set uploading progress before finishing the previous upload");
        } else {
            this.zzz = new ArrayList(list);
        }
    }

    /* access modifiers changed from: protected */
    public final void zzv() {
        int delete;
        zzl().zzt();
        zzf().zzv();
        zzal zzf2 = zzf();
        zzf2.zzt();
        zzf2.zzal();
        if (zzf2.zzaa() && zzbh.zzbh.zza(null).longValue() != 0 && (delete = zzf2.e_().delete("trigger_uris", "abs(timestamp_millis - ?) > cast(? as integer)", new String[]{String.valueOf(zzf2.zzb().currentTimeMillis()), String.valueOf(zzbh.zzbh.zza(null))})) > 0) {
            zzf2.zzj().zzp().zza("Deleted stale trigger uris. rowsDeleted", Integer.valueOf(delete));
        }
        if (this.zzj.zzd.zza() == 0) {
            this.zzj.zzd.zza(zzb().currentTimeMillis());
        }
        zzac();
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzae zzae2) {
        zzo zzc2 = zzc((String) Preconditions.checkNotNull(zzae2.zza));
        if (zzc2 != null) {
            zzb(zzae2, zzc2);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzae zzae2, zzo zzo2) {
        Preconditions.checkNotNull(zzae2);
        Preconditions.checkNotEmpty(zzae2.zza);
        Preconditions.checkNotNull(zzae2.zzb);
        Preconditions.checkNotNull(zzae2.zzc);
        Preconditions.checkNotEmpty(zzae2.zzc.zza);
        zzl().zzt();
        zzs();
        if (zzi(zzo2)) {
            if (!zzo2.zzh) {
                zza(zzo2);
                return;
            }
            zzae zzae3 = new zzae(zzae2);
            boolean z = false;
            zzae3.zze = false;
            zzf().zzp();
            try {
                zzae zzc2 = zzf().zzc((String) Preconditions.checkNotNull(zzae3.zza), zzae3.zzc.zza);
                if (zzc2 != null && !zzc2.zzb.equals(zzae3.zzb)) {
                    zzj().zzu().zza("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzm.zzk().zzc(zzae3.zzc.zza), zzae3.zzb, zzc2.zzb);
                }
                if (zzc2 != null && zzc2.zze) {
                    zzae3.zzb = zzc2.zzb;
                    zzae3.zzd = zzc2.zzd;
                    zzae3.zzh = zzc2.zzh;
                    zzae3.zzf = zzc2.zzf;
                    zzae3.zzi = zzc2.zzi;
                    zzae3.zze = zzc2.zze;
                    zzae3.zzc = new zzon(zzae3.zzc.zza, zzc2.zzc.zzb, zzae3.zzc.zza(), zzc2.zzc.zze);
                } else if (TextUtils.isEmpty(zzae3.zzf)) {
                    zzae3.zzc = new zzon(zzae3.zzc.zza, zzae3.zzd, zzae3.zzc.zza(), zzae3.zzc.zze);
                    z = true;
                    zzae3.zze = true;
                }
                if (zzae3.zze) {
                    zzon zzon = zzae3.zzc;
                    zzop zzop = new zzop((String) Preconditions.checkNotNull(zzae3.zza), zzae3.zzb, zzon.zza, zzon.zzb, Preconditions.checkNotNull(zzon.zza()));
                    if (zzf().zza(zzop)) {
                        zzj().zzc().zza("User property updated immediately", zzae3.zza, this.zzm.zzk().zzc(zzop.zzc), zzop.zze);
                    } else {
                        zzj().zzg().zza("(2)Too many active user properties, ignoring", zzgo.zza(zzae3.zza), this.zzm.zzk().zzc(zzop.zzc), zzop.zze);
                    }
                    if (z && zzae3.zzi != null) {
                        zzc(new zzbf(zzae3.zzi, zzae3.zzd), zzo2);
                    }
                }
                if (zzf().zza(zzae3)) {
                    zzj().zzc().zza("Conditional property added", zzae3.zza, this.zzm.zzk().zzc(zzae3.zzc.zza), zzae3.zzc.zza());
                } else {
                    zzj().zzg().zza("Too many conditional properties, ignoring", zzgo.zza(zzae3.zza), this.zzm.zzk().zzc(zzae3.zzc.zza), zzae3.zzc.zza());
                }
                zzf().zzw();
            } finally {
                zzf().zzu();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x0191  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01ad  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzac() {
        /*
            r21 = this;
            r0 = r21
            com.google.android.gms.measurement.internal.zzhv r1 = r21.zzl()
            r1.zzt()
            r21.zzs()
            long r1 = r0.zzp
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x004d
            com.google.android.gms.common.util.Clock r1 = r21.zzb()
            long r1 = r1.elapsedRealtime()
            long r5 = r0.zzp
            long r1 = r1 - r5
            long r1 = java.lang.Math.abs(r1)
            r5 = 3600000(0x36ee80, double:1.7786363E-317)
            long r5 = r5 - r1
            int r1 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x004b
            com.google.android.gms.measurement.internal.zzgo r1 = r21.zzj()
            com.google.android.gms.measurement.internal.zzgq r1 = r1.zzp()
            java.lang.String r2 = "Upload has been suspended. Will update scheduling later in approximately ms"
            java.lang.Long r3 = java.lang.Long.valueOf(r5)
            r1.zza(r2, r3)
            com.google.android.gms.measurement.internal.zzgy r1 = r21.zzy()
            r1.zzb()
            com.google.android.gms.measurement.internal.zznq r1 = r21.zzz()
            r1.zzu()
            return
        L_0x004b:
            r0.zzp = r3
        L_0x004d:
            com.google.android.gms.measurement.internal.zzhy r1 = r0.zzm
            boolean r1 = r1.zzaf()
            if (r1 == 0) goto L_0x024e
            boolean r1 = r21.zzad()
            if (r1 != 0) goto L_0x005d
            goto L_0x024e
        L_0x005d:
            com.google.android.gms.common.util.Clock r1 = r21.zzb()
            long r1 = r1.currentTimeMillis()
            r21.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Long> r5 = com.google.android.gms.measurement.internal.zzbh.zzab
            r6 = 0
            java.lang.Object r5 = r5.zza(r6)
            java.lang.Long r5 = (java.lang.Long) r5
            long r7 = r5.longValue()
            long r7 = java.lang.Math.max(r3, r7)
            com.google.android.gms.measurement.internal.zzal r5 = r21.zzf()
            boolean r5 = r5.zzz()
            if (r5 != 0) goto L_0x0090
            com.google.android.gms.measurement.internal.zzal r5 = r21.zzf()
            boolean r5 = r5.zzy()
            if (r5 == 0) goto L_0x008e
            goto L_0x0090
        L_0x008e:
            r5 = 0
            goto L_0x0091
        L_0x0090:
            r5 = 1
        L_0x0091:
            if (r5 == 0) goto L_0x00d1
            com.google.android.gms.measurement.internal.zzag r10 = r21.zze()
            java.lang.String r10 = r10.zzo()
            boolean r11 = android.text.TextUtils.isEmpty(r10)
            if (r11 != 0) goto L_0x00bd
            java.lang.String r11 = ".none."
            boolean r10 = r11.equals(r10)
            if (r10 != 0) goto L_0x00bd
            r21.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Long> r10 = com.google.android.gms.measurement.internal.zzbh.zzw
            java.lang.Object r10 = r10.zza(r6)
            java.lang.Long r10 = (java.lang.Long) r10
            long r10 = r10.longValue()
            long r10 = java.lang.Math.max(r3, r10)
            goto L_0x00e4
        L_0x00bd:
            r21.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Long> r10 = com.google.android.gms.measurement.internal.zzbh.zzv
            java.lang.Object r10 = r10.zza(r6)
            java.lang.Long r10 = (java.lang.Long) r10
            long r10 = r10.longValue()
            long r10 = java.lang.Math.max(r3, r10)
            goto L_0x00e4
        L_0x00d1:
            r21.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Long> r10 = com.google.android.gms.measurement.internal.zzbh.zzu
            java.lang.Object r10 = r10.zza(r6)
            java.lang.Long r10 = (java.lang.Long) r10
            long r10 = r10.longValue()
            long r10 = java.lang.Math.max(r3, r10)
        L_0x00e4:
            com.google.android.gms.measurement.internal.zzmw r12 = r0.zzj
            com.google.android.gms.measurement.internal.zzhb r12 = r12.zzd
            long r12 = r12.zza()
            com.google.android.gms.measurement.internal.zzmw r14 = r0.zzj
            com.google.android.gms.measurement.internal.zzhb r14 = r14.zze
            long r14 = r14.zza()
            com.google.android.gms.measurement.internal.zzal r16 = r21.zzf()
            r17 = r10
            long r9 = r16.c_()
            com.google.android.gms.measurement.internal.zzal r11 = r21.zzf()
            r19 = r7
            long r6 = r11.d_()
            long r6 = java.lang.Math.max(r9, r6)
            int r8 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r8 != 0) goto L_0x0113
        L_0x0110:
            r10 = r3
            goto L_0x018d
        L_0x0113:
            long r6 = r6 - r1
            long r6 = java.lang.Math.abs(r6)
            long r6 = r1 - r6
            long r12 = r12 - r1
            long r8 = java.lang.Math.abs(r12)
            long r8 = r1 - r8
            long r14 = r14 - r1
            long r10 = java.lang.Math.abs(r14)
            long r1 = r1 - r10
            long r8 = java.lang.Math.max(r8, r1)
            long r10 = r6 + r19
            if (r5 == 0) goto L_0x0139
            int r5 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0139
            long r10 = java.lang.Math.min(r6, r8)
            long r10 = r10 + r17
        L_0x0139:
            com.google.android.gms.measurement.internal.zzoo r5 = r21.zzp()
            r12 = r17
            boolean r5 = r5.zza((long) r8, (long) r12)
            if (r5 != 0) goto L_0x0147
            long r10 = r8 + r12
        L_0x0147:
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x018d
            int r5 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r5 < 0) goto L_0x018d
            r5 = 0
        L_0x0150:
            r21.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Integer> r6 = com.google.android.gms.measurement.internal.zzbh.zzad
            r7 = 0
            java.lang.Object r6 = r6.zza(r7)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r8 = 0
            int r6 = java.lang.Math.max(r8, r6)
            r9 = 20
            int r6 = java.lang.Math.min(r9, r6)
            if (r5 >= r6) goto L_0x0110
            r12 = 1
            long r12 = r12 << r5
            r21.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Long> r6 = com.google.android.gms.measurement.internal.zzbh.zzac
            java.lang.Object r6 = r6.zza(r7)
            java.lang.Long r6 = (java.lang.Long) r6
            long r6 = r6.longValue()
            long r6 = java.lang.Math.max(r3, r6)
            long r6 = r6 * r12
            long r10 = r10 + r6
            int r6 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r6 <= 0) goto L_0x018a
            goto L_0x018d
        L_0x018a:
            int r5 = r5 + 1
            goto L_0x0150
        L_0x018d:
            int r1 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x01ad
            com.google.android.gms.measurement.internal.zzgo r1 = r21.zzj()
            com.google.android.gms.measurement.internal.zzgq r1 = r1.zzp()
            java.lang.String r2 = "Next upload time is 0"
            r1.zza(r2)
            com.google.android.gms.measurement.internal.zzgy r1 = r21.zzy()
            r1.zzb()
            com.google.android.gms.measurement.internal.zznq r1 = r21.zzz()
            r1.zzu()
            return
        L_0x01ad:
            com.google.android.gms.measurement.internal.zzgr r1 = r21.zzh()
            boolean r1 = r1.zzu()
            if (r1 != 0) goto L_0x01d3
            com.google.android.gms.measurement.internal.zzgo r1 = r21.zzj()
            com.google.android.gms.measurement.internal.zzgq r1 = r1.zzp()
            java.lang.String r2 = "No network"
            r1.zza(r2)
            com.google.android.gms.measurement.internal.zzgy r1 = r21.zzy()
            r1.zza()
            com.google.android.gms.measurement.internal.zznq r1 = r21.zzz()
            r1.zzu()
            return
        L_0x01d3:
            com.google.android.gms.measurement.internal.zzmw r1 = r0.zzj
            com.google.android.gms.measurement.internal.zzhb r1 = r1.zzc
            long r1 = r1.zza()
            r21.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Long> r5 = com.google.android.gms.measurement.internal.zzbh.zzs
            r6 = 0
            java.lang.Object r5 = r5.zza(r6)
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            long r5 = java.lang.Math.max(r3, r5)
            com.google.android.gms.measurement.internal.zzoo r7 = r21.zzp()
            boolean r7 = r7.zza((long) r1, (long) r5)
            if (r7 != 0) goto L_0x01fe
            long r1 = r1 + r5
            long r10 = java.lang.Math.max(r10, r1)
        L_0x01fe:
            com.google.android.gms.measurement.internal.zzgy r1 = r21.zzy()
            r1.zzb()
            com.google.android.gms.common.util.Clock r1 = r21.zzb()
            long r1 = r1.currentTimeMillis()
            long r10 = r10 - r1
            int r1 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r1 > 0) goto L_0x0235
            r21.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Long> r1 = com.google.android.gms.measurement.internal.zzbh.zzx
            r2 = 0
            java.lang.Object r1 = r1.zza(r2)
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            long r10 = java.lang.Math.max(r3, r1)
            com.google.android.gms.measurement.internal.zzmw r1 = r0.zzj
            com.google.android.gms.measurement.internal.zzhb r1 = r1.zzd
            com.google.android.gms.common.util.Clock r2 = r21.zzb()
            long r2 = r2.currentTimeMillis()
            r1.zza(r2)
        L_0x0235:
            com.google.android.gms.measurement.internal.zzgo r1 = r21.zzj()
            com.google.android.gms.measurement.internal.zzgq r1 = r1.zzp()
            java.lang.String r2 = "Upload scheduled in approximately ms"
            java.lang.Long r3 = java.lang.Long.valueOf(r10)
            r1.zza(r2, r3)
            com.google.android.gms.measurement.internal.zznq r1 = r21.zzz()
            r1.zza(r10)
            return
        L_0x024e:
            com.google.android.gms.measurement.internal.zzgo r1 = r21.zzj()
            com.google.android.gms.measurement.internal.zzgq r1 = r1.zzp()
            java.lang.String r2 = "Nothing to upload or uploading impossible"
            r1.zza(r2)
            com.google.android.gms.measurement.internal.zzgy r1 = r21.zzy()
            r1.zzb()
            com.google.android.gms.measurement.internal.zznq r1 = r21.zzz()
            r1.zzu()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zzac():void");
    }

    private final void zza(String str, zzje zzje) {
        zzl().zzt();
        zzs();
        this.zzac.put(str, zzje);
        zzf().zzb(str, zzje);
    }

    private final void zza(String str, boolean z, Long l, Long l2) {
        zzg zze2 = zzf().zze(str);
        if (zze2 != null) {
            zze2.zzd(z);
            zze2.zza(l);
            zze2.zzb(l2);
            if (zze2.zzas()) {
                zzf().zza(zze2, false, false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzon zzon, zzo zzo2) {
        zzop zze2;
        long j;
        zzl().zzt();
        zzs();
        if (zzi(zzo2)) {
            if (!zzo2.zzh) {
                zza(zzo2);
                return;
            }
            int zzb2 = zzq().zzb(zzon.zza);
            int i = 0;
            if (zzb2 != 0) {
                zzq();
                String str = zzon.zza;
                zze();
                String zza2 = zzos.zza(str, 24, true);
                int length = zzon.zza != null ? zzon.zza.length() : 0;
                zzq();
                zzos.zza(this.zzah, zzo2.zza, zzb2, "_ev", zza2, length);
                return;
            }
            int zza3 = zzq().zza(zzon.zza, zzon.zza());
            if (zza3 != 0) {
                zzq();
                String str2 = zzon.zza;
                zze();
                String zza4 = zzos.zza(str2, 24, true);
                Object zza5 = zzon.zza();
                if (zza5 != null && ((zza5 instanceof String) || (zza5 instanceof CharSequence))) {
                    i = String.valueOf(zza5).length();
                }
                zzq();
                zzos.zza(this.zzah, zzo2.zza, zza3, "_ev", zza4, i);
                return;
            }
            Object zzc2 = zzq().zzc(zzon.zza, zzon.zza());
            if (zzc2 != null) {
                if ("_sid".equals(zzon.zza)) {
                    long j2 = zzon.zzb;
                    String str3 = zzon.zze;
                    String str4 = (String) Preconditions.checkNotNull(zzo2.zza);
                    zzop zze3 = zzf().zze(str4, "_sno");
                    if (zze3 == null || !(zze3.zze instanceof Long)) {
                        if (zze3 != null) {
                            zzj().zzu().zza("Retrieved last session number from database does not contain a valid (long) value", zze3.zze);
                        }
                        zzbb zzd2 = zzf().zzd(str4, "_s");
                        if (zzd2 != null) {
                            j = zzd2.zzc;
                            zzj().zzp().zza("Backfill the session number. Last used session number", Long.valueOf(j));
                        } else {
                            j = 0;
                        }
                    } else {
                        j = ((Long) zze3.zze).longValue();
                    }
                    zza(new zzon("_sno", j2, Long.valueOf(j + 1), str3), zzo2);
                }
                zzop zzop = new zzop((String) Preconditions.checkNotNull(zzo2.zza), (String) Preconditions.checkNotNull(zzon.zze), zzon.zza, zzon.zzb, zzc2);
                zzj().zzp().zza("Setting user property", this.zzm.zzk().zzc(zzop.zzc), zzc2);
                zzf().zzp();
                try {
                    if (Columns.ID.equals(zzop.zzc) && (zze2 = zzf().zze(zzo2.zza, Columns.ID)) != null && !zzop.zze.equals(zze2.zze)) {
                        zzf().zzh(zzo2.zza, "_lair");
                    }
                    zza(zzo2);
                    boolean zza6 = zzf().zza(zzop);
                    if ("_sid".equals(zzon.zza)) {
                        long zza7 = zzp().zza(zzo2.zzv);
                        zzg zze4 = zzf().zze(zzo2.zza);
                        if (zze4 != null) {
                            zze4.zzs(zza7);
                            if (zze4.zzas()) {
                                zzf().zza(zze4, false, false);
                            }
                        }
                    }
                    zzf().zzw();
                    if (!zza6) {
                        zzj().zzg().zza("Too many unique user properties are set. Ignoring user property", this.zzm.zzk().zzc(zzop.zzc), zzop.zze);
                        zzq();
                        zzos.zza(this.zzah, zzo2.zza, 9, (String) null, (String) null, 0);
                    }
                } finally {
                    zzf().zzu();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:244:0x05fb */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x01d5 A[SYNTHETIC, Splitter:B:103:0x01d5] */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x01ed A[Catch:{ all -> 0x063d }] */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x0320 A[Catch:{ all -> 0x063d }] */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x0360 A[Catch:{ all -> 0x063d }] */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x037a  */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x0558 A[Catch:{ all -> 0x063d }] */
    /* JADX WARNING: Removed duplicated region for block: B:223:0x0561 A[Catch:{ all -> 0x063d }] */
    /* JADX WARNING: Removed duplicated region for block: B:227:0x0573 A[SYNTHETIC, Splitter:B:227:0x0573] */
    /* JADX WARNING: Removed duplicated region for block: B:237:0x05c1 A[Catch:{ MalformedURLException -> 0x05fa }] */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x0346 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzw() {
        /*
            r24 = this;
            r8 = r24
            com.google.android.gms.measurement.internal.zzhv r0 = r24.zzl()
            r0.zzt()
            r24.zzs()
            r0 = 1
            r8.zzw = r0
            r9 = 0
            com.google.android.gms.measurement.internal.zzhy r1 = r8.zzm     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzls r1 = r1.zzr()     // Catch:{ all -> 0x063d }
            java.lang.Boolean r1 = r1.zzab()     // Catch:{ all -> 0x063d }
            if (r1 != 0) goto L_0x0033
            com.google.android.gms.measurement.internal.zzgo r0 = r24.zzj()     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzu()     // Catch:{ all -> 0x002f }
            java.lang.String r1 = "Upload data called on the client side before use of service was decided"
            r0.zza(r1)     // Catch:{ all -> 0x002f }
            r8.zzw = r9
            r24.zzaa()
            return
        L_0x002f:
            r0 = move-exception
            r1 = r9
            goto L_0x063f
        L_0x0033:
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x063d }
            if (r1 == 0) goto L_0x004c
            com.google.android.gms.measurement.internal.zzgo r0 = r24.zzj()     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzg()     // Catch:{ all -> 0x002f }
            java.lang.String r1 = "Upload called in the client side when service should be used"
            r0.zza(r1)     // Catch:{ all -> 0x002f }
            r8.zzw = r9
            r24.zzaa()
            return
        L_0x004c:
            long r1 = r8.zzp     // Catch:{ all -> 0x063d }
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x005d
            r24.zzac()     // Catch:{ all -> 0x002f }
            r8.zzw = r9
            r24.zzaa()
            return
        L_0x005d:
            com.google.android.gms.measurement.internal.zzhv r1 = r24.zzl()     // Catch:{ all -> 0x063d }
            r1.zzt()     // Catch:{ all -> 0x063d }
            java.util.List<java.lang.Long> r1 = r8.zzz     // Catch:{ all -> 0x063d }
            if (r1 == 0) goto L_0x006a
            r1 = r0
            goto L_0x006b
        L_0x006a:
            r1 = r9
        L_0x006b:
            if (r1 == 0) goto L_0x0080
            com.google.android.gms.measurement.internal.zzgo r0 = r24.zzj()     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzp()     // Catch:{ all -> 0x002f }
            java.lang.String r1 = "Uploading requested multiple times"
            r0.zza(r1)     // Catch:{ all -> 0x002f }
            r8.zzw = r9
            r24.zzaa()
            return
        L_0x0080:
            com.google.android.gms.measurement.internal.zzgr r1 = r24.zzh()     // Catch:{ all -> 0x063d }
            boolean r1 = r1.zzu()     // Catch:{ all -> 0x063d }
            if (r1 != 0) goto L_0x00a0
            com.google.android.gms.measurement.internal.zzgo r0 = r24.zzj()     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzp()     // Catch:{ all -> 0x002f }
            java.lang.String r1 = "Network not connected, ignoring upload request"
            r0.zza(r1)     // Catch:{ all -> 0x002f }
            r24.zzac()     // Catch:{ all -> 0x002f }
            r8.zzw = r9
            r24.zzaa()
            return
        L_0x00a0:
            com.google.android.gms.common.util.Clock r1 = r24.zzb()     // Catch:{ all -> 0x063d }
            long r1 = r1.currentTimeMillis()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzag r5 = r24.zze()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Integer> r6 = com.google.android.gms.measurement.internal.zzbh.zzau     // Catch:{ all -> 0x063d }
            r7 = 0
            int r5 = r5.zzb((java.lang.String) r7, (com.google.android.gms.measurement.internal.zzfz<java.lang.Integer>) r6)     // Catch:{ all -> 0x063d }
            r24.zze()     // Catch:{ all -> 0x063d }
            long r10 = com.google.android.gms.measurement.internal.zzag.zzg()     // Catch:{ all -> 0x063d }
            long r10 = r1 - r10
            r6 = r9
        L_0x00bd:
            if (r6 >= r5) goto L_0x00c8
            boolean r12 = r8.zza((java.lang.String) r7, (long) r10)     // Catch:{ all -> 0x002f }
            if (r12 == 0) goto L_0x00c8
            int r6 = r6 + 1
            goto L_0x00bd
        L_0x00c8:
            boolean r5 = com.google.android.gms.internal.measurement.zzpn.zza()     // Catch:{ all -> 0x063d }
            if (r5 == 0) goto L_0x00d1
            r24.zzab()     // Catch:{ all -> 0x002f }
        L_0x00d1:
            com.google.android.gms.measurement.internal.zzmw r5 = r8.zzj     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzhb r5 = r5.zzd     // Catch:{ all -> 0x063d }
            long r5 = r5.zza()     // Catch:{ all -> 0x063d }
            int r3 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x00f4
            com.google.android.gms.measurement.internal.zzgo r3 = r24.zzj()     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzc()     // Catch:{ all -> 0x002f }
            java.lang.String r4 = "Uploading events. Elapsed time since last upload attempt (ms)"
            long r5 = r1 - r5
            long r5 = java.lang.Math.abs(r5)     // Catch:{ all -> 0x002f }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x002f }
            r3.zza(r4, r5)     // Catch:{ all -> 0x002f }
        L_0x00f4:
            com.google.android.gms.measurement.internal.zzal r3 = r24.zzf()     // Catch:{ all -> 0x063d }
            java.lang.String r6 = r3.f_()     // Catch:{ all -> 0x063d }
            boolean r3 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x063d }
            r4 = -1
            if (r3 != 0) goto L_0x0611
            long r10 = r8.zzab     // Catch:{ all -> 0x063d }
            int r3 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r3 != 0) goto L_0x0114
            com.google.android.gms.measurement.internal.zzal r3 = r24.zzf()     // Catch:{ all -> 0x002f }
            long r3 = r3.b_()     // Catch:{ all -> 0x002f }
            r8.zzab = r3     // Catch:{ all -> 0x002f }
        L_0x0114:
            com.google.android.gms.measurement.internal.zzag r3 = r24.zze()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Integer> r4 = com.google.android.gms.measurement.internal.zzbh.zzg     // Catch:{ all -> 0x063d }
            int r3 = r3.zzb((java.lang.String) r6, (com.google.android.gms.measurement.internal.zzfz<java.lang.Integer>) r4)     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzag r4 = r24.zze()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Integer> r5 = com.google.android.gms.measurement.internal.zzbh.zzh     // Catch:{ all -> 0x063d }
            int r4 = r4.zzb((java.lang.String) r6, (com.google.android.gms.measurement.internal.zzfz<java.lang.Integer>) r5)     // Catch:{ all -> 0x063d }
            int r4 = java.lang.Math.max(r9, r4)     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzal r5 = r24.zzf()     // Catch:{ all -> 0x063d }
            java.util.List r3 = r5.zza((java.lang.String) r6, (int) r3, (int) r4)     // Catch:{ all -> 0x063d }
            boolean r4 = r3.isEmpty()     // Catch:{ all -> 0x063d }
            if (r4 != 0) goto L_0x0636
            com.google.android.gms.measurement.internal.zzje r4 = r8.zzb((java.lang.String) r6)     // Catch:{ all -> 0x063d }
            boolean r4 = r4.zzg()     // Catch:{ all -> 0x063d }
            if (r4 == 0) goto L_0x0197
            java.util.Iterator r4 = r3.iterator()     // Catch:{ all -> 0x002f }
        L_0x0148:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x002f }
            if (r5 == 0) goto L_0x0167
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x002f }
            android.util.Pair r5 = (android.util.Pair) r5     // Catch:{ all -> 0x002f }
            java.lang.Object r5 = r5.first     // Catch:{ all -> 0x002f }
            com.google.android.gms.internal.measurement.zzfy$zzk r5 = (com.google.android.gms.internal.measurement.zzfy.zzk) r5     // Catch:{ all -> 0x002f }
            java.lang.String r10 = r5.zzan()     // Catch:{ all -> 0x002f }
            boolean r10 = r10.isEmpty()     // Catch:{ all -> 0x002f }
            if (r10 != 0) goto L_0x0148
            java.lang.String r4 = r5.zzan()     // Catch:{ all -> 0x002f }
            goto L_0x0168
        L_0x0167:
            r4 = r7
        L_0x0168:
            if (r4 == 0) goto L_0x0197
            r5 = r9
        L_0x016b:
            int r10 = r3.size()     // Catch:{ all -> 0x002f }
            if (r5 >= r10) goto L_0x0197
            java.lang.Object r10 = r3.get(r5)     // Catch:{ all -> 0x002f }
            android.util.Pair r10 = (android.util.Pair) r10     // Catch:{ all -> 0x002f }
            java.lang.Object r10 = r10.first     // Catch:{ all -> 0x002f }
            com.google.android.gms.internal.measurement.zzfy$zzk r10 = (com.google.android.gms.internal.measurement.zzfy.zzk) r10     // Catch:{ all -> 0x002f }
            java.lang.String r11 = r10.zzan()     // Catch:{ all -> 0x002f }
            boolean r11 = r11.isEmpty()     // Catch:{ all -> 0x002f }
            if (r11 != 0) goto L_0x0194
            java.lang.String r10 = r10.zzan()     // Catch:{ all -> 0x002f }
            boolean r10 = r10.equals(r4)     // Catch:{ all -> 0x002f }
            if (r10 != 0) goto L_0x0194
            java.util.List r3 = r3.subList(r9, r5)     // Catch:{ all -> 0x002f }
            goto L_0x0197
        L_0x0194:
            int r5 = r5 + 1
            goto L_0x016b
        L_0x0197:
            com.google.android.gms.internal.measurement.zzfy$zzj$zza r4 = com.google.android.gms.internal.measurement.zzfy.zzj.zzb()     // Catch:{ all -> 0x063d }
            int r5 = r3.size()     // Catch:{ all -> 0x063d }
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ all -> 0x063d }
            int r11 = r3.size()     // Catch:{ all -> 0x063d }
            r10.<init>(r11)     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzag r11 = r24.zze()     // Catch:{ all -> 0x063d }
            boolean r11 = r11.zzj(r6)     // Catch:{ all -> 0x063d }
            if (r11 == 0) goto L_0x01be
            com.google.android.gms.measurement.internal.zzje r11 = r8.zzb((java.lang.String) r6)     // Catch:{ all -> 0x002f }
            boolean r11 = r11.zzg()     // Catch:{ all -> 0x002f }
            if (r11 == 0) goto L_0x01be
            r11 = r0
            goto L_0x01bf
        L_0x01be:
            r11 = r9
        L_0x01bf:
            com.google.android.gms.measurement.internal.zzje r12 = r8.zzb((java.lang.String) r6)     // Catch:{ all -> 0x063d }
            boolean r12 = r12.zzg()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzje r13 = r8.zzb((java.lang.String) r6)     // Catch:{ all -> 0x063d }
            boolean r13 = r13.zzh()     // Catch:{ all -> 0x063d }
            boolean r14 = com.google.android.gms.internal.measurement.zzpo.zza()     // Catch:{ all -> 0x063d }
            if (r14 == 0) goto L_0x01e3
            com.google.android.gms.measurement.internal.zzag r14 = r24.zze()     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r15 = com.google.android.gms.measurement.internal.zzbh.zzbw     // Catch:{ all -> 0x002f }
            boolean r14 = r14.zze(r6, r15)     // Catch:{ all -> 0x002f }
            if (r14 == 0) goto L_0x01e3
            r14 = r0
            goto L_0x01e4
        L_0x01e3:
            r14 = r9
        L_0x01e4:
            com.google.android.gms.measurement.internal.zznu r15 = r8.zzk     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zznw r15 = r15.zza((java.lang.String) r6)     // Catch:{ all -> 0x063d }
            r7 = r9
        L_0x01eb:
            if (r7 >= r5) goto L_0x0358
            java.lang.Object r16 = r3.get(r7)     // Catch:{ all -> 0x063d }
            r0 = r16
            android.util.Pair r0 = (android.util.Pair) r0     // Catch:{ all -> 0x063d }
            java.lang.Object r0 = r0.first     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfy$zzk r0 = (com.google.android.gms.internal.measurement.zzfy.zzk) r0     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzjt$zzb r0 = r0.zzcd()     // Catch:{ all -> 0x063d }
            r16 = r0
            com.google.android.gms.internal.measurement.zzjt$zzb r16 = (com.google.android.gms.internal.measurement.zzjt.zzb) r16     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r0 = (com.google.android.gms.internal.measurement.zzfy.zzk.zza) r0     // Catch:{ all -> 0x063d }
            java.lang.Object r16 = r3.get(r7)     // Catch:{ all -> 0x063d }
            r9 = r16
            android.util.Pair r9 = (android.util.Pair) r9     // Catch:{ all -> 0x063d }
            java.lang.Object r9 = r9.second     // Catch:{ all -> 0x063d }
            java.lang.Long r9 = (java.lang.Long) r9     // Catch:{ all -> 0x063d }
            r10.add(r9)     // Catch:{ all -> 0x063d }
            r24.zze()     // Catch:{ all -> 0x063d }
            r16 = r10
            r9 = 106000(0x19e10, double:5.2371E-319)
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r9 = r0.zzl((long) r9)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r9 = r9.zzk((long) r1)     // Catch:{ all -> 0x063d }
            r10 = 0
            r9.zzd((boolean) r10)     // Catch:{ all -> 0x063d }
            if (r11 != 0) goto L_0x022b
            r0.zzk()     // Catch:{ all -> 0x063d }
        L_0x022b:
            if (r12 != 0) goto L_0x0233
            r0.zzq()     // Catch:{ all -> 0x063d }
            r0.zzn()     // Catch:{ all -> 0x063d }
        L_0x0233:
            if (r13 != 0) goto L_0x0238
            r0.zzh()     // Catch:{ all -> 0x063d }
        L_0x0238:
            r8.zza((java.lang.String) r6, (com.google.android.gms.internal.measurement.zzfy.zzk.zza) r0)     // Catch:{ all -> 0x063d }
            if (r14 != 0) goto L_0x0240
            r0.zzr()     // Catch:{ all -> 0x063d }
        L_0x0240:
            boolean r9 = com.google.android.gms.internal.measurement.zznm.zza()     // Catch:{ all -> 0x063d }
            if (r9 == 0) goto L_0x0257
            com.google.android.gms.measurement.internal.zzag r9 = r24.zze()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r10 = com.google.android.gms.measurement.internal.zzbh.zzcz     // Catch:{ all -> 0x063d }
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r10)     // Catch:{ all -> 0x063d }
            if (r9 == 0) goto L_0x0257
            if (r13 != 0) goto L_0x0257
            r0.zzi()     // Catch:{ all -> 0x063d }
        L_0x0257:
            java.lang.String r9 = r0.zzz()     // Catch:{ all -> 0x063d }
            boolean r10 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x063d }
            if (r10 != 0) goto L_0x0276
            java.lang.String r10 = "00000000-0000-0000-0000-000000000000"
            boolean r9 = r9.equals(r10)     // Catch:{ all -> 0x063d }
            if (r9 == 0) goto L_0x026a
            goto L_0x0276
        L_0x026a:
            r17 = r3
            r18 = r11
            r22 = r12
            r21 = r13
            r23 = r14
            goto L_0x031a
        L_0x0276:
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ all -> 0x063d }
            java.util.List r10 = r0.zzaa()     // Catch:{ all -> 0x063d }
            r9.<init>(r10)     // Catch:{ all -> 0x063d }
            java.util.Iterator r10 = r9.iterator()     // Catch:{ all -> 0x063d }
            r17 = r3
            r18 = r11
            r3 = 0
            r11 = 0
            r19 = 0
            r20 = 0
        L_0x028d:
            boolean r21 = r10.hasNext()     // Catch:{ all -> 0x063d }
            if (r21 == 0) goto L_0x0302
            java.lang.Object r21 = r10.next()     // Catch:{ all -> 0x063d }
            r22 = r12
            r12 = r21
            com.google.android.gms.internal.measurement.zzfy$zzf r12 = (com.google.android.gms.internal.measurement.zzfy.zzf) r12     // Catch:{ all -> 0x063d }
            r21 = r13
            java.lang.String r13 = "_fx"
            r23 = r14
            java.lang.String r14 = r12.zzg()     // Catch:{ all -> 0x063d }
            boolean r13 = r13.equals(r14)     // Catch:{ all -> 0x063d }
            if (r13 == 0) goto L_0x02bb
            r10.remove()     // Catch:{ all -> 0x063d }
            r13 = r21
            r12 = r22
            r14 = r23
            r19 = 1
            r20 = 1
            goto L_0x028d
        L_0x02bb:
            java.lang.String r13 = "_f"
            java.lang.String r14 = r12.zzg()     // Catch:{ all -> 0x063d }
            boolean r13 = r13.equals(r14)     // Catch:{ all -> 0x063d }
            if (r13 == 0) goto L_0x02fb
            com.google.android.gms.measurement.internal.zzag r13 = r24.zze()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r14 = com.google.android.gms.measurement.internal.zzbh.zzcw     // Catch:{ all -> 0x063d }
            boolean r13 = r13.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r14)     // Catch:{ all -> 0x063d }
            if (r13 == 0) goto L_0x02f9
            r24.zzp()     // Catch:{ all -> 0x063d }
            java.lang.String r13 = "_pfo"
            com.google.android.gms.internal.measurement.zzfy$zzh r13 = com.google.android.gms.measurement.internal.zzoo.zza((com.google.android.gms.internal.measurement.zzfy.zzf) r12, (java.lang.String) r13)     // Catch:{ all -> 0x063d }
            if (r13 == 0) goto L_0x02e6
            long r13 = r13.zzd()     // Catch:{ all -> 0x063d }
            java.lang.Long r3 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x063d }
        L_0x02e6:
            r24.zzp()     // Catch:{ all -> 0x063d }
            java.lang.String r13 = "_uwa"
            com.google.android.gms.internal.measurement.zzfy$zzh r12 = com.google.android.gms.measurement.internal.zzoo.zza((com.google.android.gms.internal.measurement.zzfy.zzf) r12, (java.lang.String) r13)     // Catch:{ all -> 0x063d }
            if (r12 == 0) goto L_0x02f9
            long r11 = r12.zzd()     // Catch:{ all -> 0x063d }
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x063d }
        L_0x02f9:
            r20 = 1
        L_0x02fb:
            r13 = r21
            r12 = r22
            r14 = r23
            goto L_0x028d
        L_0x0302:
            r22 = r12
            r21 = r13
            r23 = r14
            if (r19 == 0) goto L_0x0310
            r0.zzl()     // Catch:{ all -> 0x063d }
            r0.zzb((java.lang.Iterable<? extends com.google.android.gms.internal.measurement.zzfy.zzf>) r9)     // Catch:{ all -> 0x063d }
        L_0x0310:
            if (r20 == 0) goto L_0x031a
            java.lang.String r9 = r0.zzt()     // Catch:{ all -> 0x063d }
            r10 = 1
            r8.zza((java.lang.String) r9, (boolean) r10, (java.lang.Long) r3, (java.lang.Long) r11)     // Catch:{ all -> 0x063d }
        L_0x031a:
            int r3 = r0.zzc()     // Catch:{ all -> 0x063d }
            if (r3 == 0) goto L_0x0346
            com.google.android.gms.measurement.internal.zzag r3 = r24.zze()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r9 = com.google.android.gms.measurement.internal.zzbh.zzbm     // Catch:{ all -> 0x063d }
            boolean r3 = r3.zze(r6, r9)     // Catch:{ all -> 0x063d }
            if (r3 == 0) goto L_0x0343
            com.google.android.gms.internal.measurement.zzlc r3 = r0.zzai()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzjt r3 = (com.google.android.gms.internal.measurement.zzjt) r3     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfy$zzk r3 = (com.google.android.gms.internal.measurement.zzfy.zzk) r3     // Catch:{ all -> 0x063d }
            byte[] r3 = r3.zzca()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzoo r9 = r24.zzp()     // Catch:{ all -> 0x063d }
            long r9 = r9.zza((byte[]) r3)     // Catch:{ all -> 0x063d }
            r0.zza((long) r9)     // Catch:{ all -> 0x063d }
        L_0x0343:
            r4.zza((com.google.android.gms.internal.measurement.zzfy.zzk.zza) r0)     // Catch:{ all -> 0x063d }
        L_0x0346:
            int r7 = r7 + 1
            r10 = r16
            r3 = r17
            r11 = r18
            r13 = r21
            r12 = r22
            r14 = r23
            r0 = 1
            r9 = 0
            goto L_0x01eb
        L_0x0358:
            r16 = r10
            int r0 = r4.zza()     // Catch:{ all -> 0x063d }
            if (r0 != 0) goto L_0x037a
            r0 = r16
            r8.zza((java.util.List<java.lang.Long>) r0)     // Catch:{ all -> 0x063d }
            r2 = 0
            r3 = 204(0xcc, float:2.86E-43)
            r4 = 0
            r5 = 0
            java.util.List r7 = java.util.Collections.emptyList()     // Catch:{ all -> 0x063d }
            r1 = r24
            r1.zza(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x063d }
            r1 = 0
            r8.zzw = r1
            r24.zzaa()
            return
        L_0x037a:
            r0 = r16
            com.google.android.gms.internal.measurement.zzlc r3 = r4.zzai()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzjt r3 = (com.google.android.gms.internal.measurement.zzjt) r3     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfy$zzj r3 = (com.google.android.gms.internal.measurement.zzfy.zzj) r3     // Catch:{ all -> 0x063d }
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ all -> 0x063d }
            r7.<init>()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzag r9 = r24.zze()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r10 = com.google.android.gms.measurement.internal.zzbh.zzbx     // Catch:{ all -> 0x063d }
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r10)     // Catch:{ all -> 0x063d }
            if (r9 == 0) goto L_0x054c
            r24.zzq()     // Catch:{ all -> 0x063d }
            boolean r9 = com.google.android.gms.measurement.internal.zzos.zzf(r6)     // Catch:{ all -> 0x063d }
            if (r9 == 0) goto L_0x054c
            com.google.android.gms.measurement.internal.zznt r9 = r15.zza()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zznt r10 = com.google.android.gms.measurement.internal.zznt.SGTM     // Catch:{ all -> 0x063d }
            if (r9 != r10) goto L_0x054c
            com.google.android.gms.internal.measurement.zzlc r3 = r4.zzai()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzjt r3 = (com.google.android.gms.internal.measurement.zzjt) r3     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfy$zzj r3 = (com.google.android.gms.internal.measurement.zzfy.zzj) r3     // Catch:{ all -> 0x063d }
            java.util.List r3 = r3.zzf()     // Catch:{ all -> 0x063d }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x063d }
        L_0x03b6:
            boolean r9 = r3.hasNext()     // Catch:{ all -> 0x063d }
            if (r9 == 0) goto L_0x03ca
            java.lang.Object r9 = r3.next()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfy$zzk r9 = (com.google.android.gms.internal.measurement.zzfy.zzk) r9     // Catch:{ all -> 0x063d }
            boolean r9 = r9.zzbh()     // Catch:{ all -> 0x063d }
            if (r9 == 0) goto L_0x03b6
            r3 = 1
            goto L_0x03cb
        L_0x03ca:
            r3 = 0
        L_0x03cb:
            if (r3 == 0) goto L_0x03d6
            java.util.UUID r3 = java.util.UUID.randomUUID()     // Catch:{ all -> 0x063d }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x063d }
            goto L_0x03d7
        L_0x03d6:
            r3 = 0
        L_0x03d7:
            com.google.android.gms.internal.measurement.zzlc r9 = r4.zzai()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzjt r9 = (com.google.android.gms.internal.measurement.zzjt) r9     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfy$zzj r9 = (com.google.android.gms.internal.measurement.zzfy.zzj) r9     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzhv r10 = r24.zzl()     // Catch:{ all -> 0x063d }
            r10.zzt()     // Catch:{ all -> 0x063d }
            r24.zzs()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfy$zzj$zza r10 = com.google.android.gms.internal.measurement.zzfy.zzj.zza((com.google.android.gms.internal.measurement.zzfy.zzj) r9)     // Catch:{ all -> 0x063d }
            boolean r11 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x063d }
            if (r11 != 0) goto L_0x03f6
            r10.zza((java.lang.String) r3)     // Catch:{ all -> 0x063d }
        L_0x03f6:
            com.google.android.gms.measurement.internal.zzhl r11 = r24.zzi()     // Catch:{ all -> 0x063d }
            java.lang.String r11 = r11.zzf(r6)     // Catch:{ all -> 0x063d }
            boolean r12 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x063d }
            if (r12 != 0) goto L_0x0407
            r10.zzb(r11)     // Catch:{ all -> 0x063d }
        L_0x0407:
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ all -> 0x063d }
            r11.<init>()     // Catch:{ all -> 0x063d }
            java.util.List r9 = r9.zzf()     // Catch:{ all -> 0x063d }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x063d }
        L_0x0414:
            boolean r12 = r9.hasNext()     // Catch:{ all -> 0x063d }
            if (r12 == 0) goto L_0x0433
            java.lang.Object r12 = r9.next()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfy$zzk r12 = (com.google.android.gms.internal.measurement.zzfy.zzk) r12     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r12 = com.google.android.gms.internal.measurement.zzfy.zzk.zza((com.google.android.gms.internal.measurement.zzfy.zzk) r12)     // Catch:{ all -> 0x063d }
            r12.zzk()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzlc r12 = r12.zzai()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzjt r12 = (com.google.android.gms.internal.measurement.zzjt) r12     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfy$zzk r12 = (com.google.android.gms.internal.measurement.zzfy.zzk) r12     // Catch:{ all -> 0x063d }
            r11.add(r12)     // Catch:{ all -> 0x063d }
            goto L_0x0414
        L_0x0433:
            r10.zzb()     // Catch:{ all -> 0x063d }
            r10.zza((java.lang.Iterable<? extends com.google.android.gms.internal.measurement.zzfy.zzk>) r11)     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzag r9 = r24.zze()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r11 = com.google.android.gms.measurement.internal.zzbh.zzcc     // Catch:{ all -> 0x063d }
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r11)     // Catch:{ all -> 0x063d }
            if (r9 == 0) goto L_0x0460
            com.google.android.gms.measurement.internal.zzgo r9 = r24.zzj()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzgq r9 = r9.zzp()     // Catch:{ all -> 0x063d }
            java.lang.String r11 = "Processed MeasurementBatch for sGTM with sgtmJoinId: "
            boolean r12 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x063d }
            if (r12 == 0) goto L_0x0458
            java.lang.String r12 = "null"
            goto L_0x045c
        L_0x0458:
            java.lang.String r12 = r10.zzc()     // Catch:{ all -> 0x063d }
        L_0x045c:
            r9.zza(r11, r12)     // Catch:{ all -> 0x063d }
            goto L_0x046d
        L_0x0460:
            com.google.android.gms.measurement.internal.zzgo r9 = r24.zzj()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzgq r9 = r9.zzp()     // Catch:{ all -> 0x063d }
            java.lang.String r11 = "Processed MeasurementBatch for sGTM."
            r9.zza(r11)     // Catch:{ all -> 0x063d }
        L_0x046d:
            com.google.android.gms.internal.measurement.zzlc r9 = r10.zzai()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzjt r9 = (com.google.android.gms.internal.measurement.zzjt) r9     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfy$zzj r9 = (com.google.android.gms.internal.measurement.zzfy.zzj) r9     // Catch:{ all -> 0x063d }
            boolean r10 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x063d }
            if (r10 != 0) goto L_0x0549
            com.google.android.gms.measurement.internal.zzag r10 = r24.zze()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r11 = com.google.android.gms.measurement.internal.zzbh.zzcc     // Catch:{ all -> 0x063d }
            boolean r10 = r10.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r11)     // Catch:{ all -> 0x063d }
            if (r10 == 0) goto L_0x0549
            com.google.android.gms.internal.measurement.zzlc r10 = r4.zzai()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzjt r10 = (com.google.android.gms.internal.measurement.zzjt) r10     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfy$zzj r10 = (com.google.android.gms.internal.measurement.zzfy.zzj) r10     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzhv r11 = r24.zzl()     // Catch:{ all -> 0x063d }
            r11.zzt()     // Catch:{ all -> 0x063d }
            r24.zzs()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfy$zzj$zza r11 = com.google.android.gms.internal.measurement.zzfy.zzj.zzb()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzgo r12 = r24.zzj()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzgq r12 = r12.zzp()     // Catch:{ all -> 0x063d }
            java.lang.String r13 = "Processing Google Signal, sgtmJoinId:"
            r12.zza(r13, r3)     // Catch:{ all -> 0x063d }
            r11.zza((java.lang.String) r3)     // Catch:{ all -> 0x063d }
            java.util.List r3 = r10.zzf()     // Catch:{ all -> 0x063d }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x063d }
        L_0x04b5:
            boolean r10 = r3.hasNext()     // Catch:{ all -> 0x063d }
            if (r10 == 0) goto L_0x04d9
            java.lang.Object r10 = r3.next()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfy$zzk r10 = (com.google.android.gms.internal.measurement.zzfy.zzk) r10     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r12 = com.google.android.gms.internal.measurement.zzfy.zzk.zzw()     // Catch:{ all -> 0x063d }
            java.lang.String r13 = r10.zzah()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r12 = r12.zzj((java.lang.String) r13)     // Catch:{ all -> 0x063d }
            int r10 = r10.zzd()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r10 = r12.zzg((int) r10)     // Catch:{ all -> 0x063d }
            r11.zza((com.google.android.gms.internal.measurement.zzfy.zzk.zza) r10)     // Catch:{ all -> 0x063d }
            goto L_0x04b5
        L_0x04d9:
            com.google.android.gms.internal.measurement.zzlc r3 = r11.zzai()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzjt r3 = (com.google.android.gms.internal.measurement.zzjt) r3     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfy$zzj r3 = (com.google.android.gms.internal.measurement.zzfy.zzj) r3     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zznu r10 = r8.zzk     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzhl r10 = r10.zzm()     // Catch:{ all -> 0x063d }
            java.lang.String r10 = r10.zzf(r6)     // Catch:{ all -> 0x063d }
            boolean r11 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x063d }
            if (r11 != 0) goto L_0x0531
            com.google.android.gms.measurement.internal.zzfz<java.lang.String> r11 = com.google.android.gms.measurement.internal.zzbh.zzr     // Catch:{ all -> 0x063d }
            r12 = 0
            java.lang.Object r11 = r11.zza(r12)     // Catch:{ all -> 0x063d }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x063d }
            android.net.Uri r11 = android.net.Uri.parse(r11)     // Catch:{ all -> 0x063d }
            android.net.Uri$Builder r12 = r11.buildUpon()     // Catch:{ all -> 0x063d }
            java.lang.String r11 = r11.getAuthority()     // Catch:{ all -> 0x063d }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x063d }
            r13.<init>()     // Catch:{ all -> 0x063d }
            java.lang.StringBuilder r10 = r13.append(r10)     // Catch:{ all -> 0x063d }
            java.lang.String r13 = "."
            java.lang.StringBuilder r10 = r10.append(r13)     // Catch:{ all -> 0x063d }
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x063d }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x063d }
            r12.authority(r10)     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zznw r10 = new com.google.android.gms.measurement.internal.zznw     // Catch:{ all -> 0x063d }
            android.net.Uri r11 = r12.build()     // Catch:{ all -> 0x063d }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zznt r12 = com.google.android.gms.measurement.internal.zznt.GOOGLE_SIGNAL     // Catch:{ all -> 0x063d }
            r10.<init>(r11, r12)     // Catch:{ all -> 0x063d }
            r12 = 0
            goto L_0x0541
        L_0x0531:
            com.google.android.gms.measurement.internal.zznw r10 = new com.google.android.gms.measurement.internal.zznw     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.String> r11 = com.google.android.gms.measurement.internal.zzbh.zzr     // Catch:{ all -> 0x063d }
            r12 = 0
            java.lang.Object r11 = r11.zza(r12)     // Catch:{ all -> 0x063d }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zznt r13 = com.google.android.gms.measurement.internal.zznt.GOOGLE_SIGNAL     // Catch:{ all -> 0x063d }
            r10.<init>(r11, r13)     // Catch:{ all -> 0x063d }
        L_0x0541:
            android.util.Pair r3 = android.util.Pair.create(r3, r10)     // Catch:{ all -> 0x063d }
            r7.add(r3)     // Catch:{ all -> 0x063d }
            goto L_0x054a
        L_0x0549:
            r12 = 0
        L_0x054a:
            r3 = r9
            goto L_0x054d
        L_0x054c:
            r12 = 0
        L_0x054d:
            com.google.android.gms.measurement.internal.zzgo r9 = r24.zzj()     // Catch:{ all -> 0x063d }
            r10 = 2
            boolean r9 = r9.zza((int) r10)     // Catch:{ all -> 0x063d }
            if (r9 == 0) goto L_0x0561
            com.google.android.gms.measurement.internal.zzoo r9 = r24.zzp()     // Catch:{ all -> 0x063d }
            java.lang.String r9 = r9.zza((com.google.android.gms.internal.measurement.zzfy.zzj) r3)     // Catch:{ all -> 0x063d }
            goto L_0x0562
        L_0x0561:
            r9 = r12
        L_0x0562:
            r24.zzp()     // Catch:{ all -> 0x063d }
            byte[] r13 = r3.zzca()     // Catch:{ all -> 0x063d }
            boolean r10 = com.google.android.gms.internal.measurement.zzpb.zza()     // Catch:{ all -> 0x063d }
            java.lang.String r11 = "Uploading data. app, uncompressed size, data"
            java.lang.String r12 = "?"
            if (r10 == 0) goto L_0x05b5
            com.google.android.gms.measurement.internal.zzag r10 = r24.zze()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r14 = com.google.android.gms.measurement.internal.zzbh.zzcf     // Catch:{ all -> 0x063d }
            boolean r10 = r10.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r14)     // Catch:{ all -> 0x063d }
            if (r10 == 0) goto L_0x05b5
            r8.zza((java.util.List<java.lang.Long>) r0)     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzmw r0 = r8.zzj     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzhb r0 = r0.zze     // Catch:{ all -> 0x063d }
            r0.zza(r1)     // Catch:{ all -> 0x063d }
            if (r5 <= 0) goto L_0x0594
            r1 = 0
            com.google.android.gms.internal.measurement.zzfy$zzk r0 = r4.zza((int) r1)     // Catch:{ all -> 0x063d }
            java.lang.String r12 = r0.zzz()     // Catch:{ all -> 0x063d }
        L_0x0594:
            com.google.android.gms.measurement.internal.zzgo r0 = r24.zzj()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzp()     // Catch:{ all -> 0x063d }
            int r1 = r13.length     // Catch:{ all -> 0x063d }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x063d }
            r0.zza(r11, r12, r1, r9)     // Catch:{ all -> 0x063d }
            r0 = 1
            r8.zzv = r0     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzgr r0 = r24.zzh()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zznz r1 = new com.google.android.gms.measurement.internal.zznz     // Catch:{ all -> 0x063d }
            r1.<init>(r8, r6, r7)     // Catch:{ all -> 0x063d }
            r0.zza(r6, r15, r3, r1)     // Catch:{ all -> 0x063d }
            goto L_0x0636
        L_0x05b5:
            r8.zza((java.util.List<java.lang.Long>) r0)     // Catch:{ MalformedURLException -> 0x05fa }
            com.google.android.gms.measurement.internal.zzmw r0 = r8.zzj     // Catch:{ MalformedURLException -> 0x05fa }
            com.google.android.gms.measurement.internal.zzhb r0 = r0.zze     // Catch:{ MalformedURLException -> 0x05fa }
            r0.zza(r1)     // Catch:{ MalformedURLException -> 0x05fa }
            if (r5 <= 0) goto L_0x05ca
            r1 = 0
            com.google.android.gms.internal.measurement.zzfy$zzk r0 = r4.zza((int) r1)     // Catch:{ MalformedURLException -> 0x05fa }
            java.lang.String r12 = r0.zzz()     // Catch:{ MalformedURLException -> 0x05fa }
        L_0x05ca:
            com.google.android.gms.measurement.internal.zzgo r0 = r24.zzj()     // Catch:{ MalformedURLException -> 0x05fa }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzp()     // Catch:{ MalformedURLException -> 0x05fa }
            int r1 = r13.length     // Catch:{ MalformedURLException -> 0x05fa }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ MalformedURLException -> 0x05fa }
            r0.zza(r11, r12, r1, r9)     // Catch:{ MalformedURLException -> 0x05fa }
            r0 = 1
            r8.zzv = r0     // Catch:{ MalformedURLException -> 0x05fa }
            com.google.android.gms.measurement.internal.zzgr r10 = r24.zzh()     // Catch:{ MalformedURLException -> 0x05fa }
            java.net.URL r12 = new java.net.URL     // Catch:{ MalformedURLException -> 0x05fa }
            java.lang.String r0 = r15.zzb()     // Catch:{ MalformedURLException -> 0x05fa }
            r12.<init>(r0)     // Catch:{ MalformedURLException -> 0x05fa }
            java.util.Map r14 = r15.zzc()     // Catch:{ MalformedURLException -> 0x05fa }
            com.google.android.gms.measurement.internal.zzoc r0 = new com.google.android.gms.measurement.internal.zzoc     // Catch:{ MalformedURLException -> 0x05fa }
            r0.<init>(r8, r6, r7)     // Catch:{ MalformedURLException -> 0x05fa }
            r11 = r6
            r1 = r15
            r15 = r0
            r10.zza(r11, r12, r13, r14, r15)     // Catch:{ MalformedURLException -> 0x05fb }
            goto L_0x0636
        L_0x05fa:
            r1 = r15
        L_0x05fb:
            com.google.android.gms.measurement.internal.zzgo r0 = r24.zzj()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzg()     // Catch:{ all -> 0x063d }
            java.lang.String r2 = "Failed to parse upload URL. Not uploading. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r6)     // Catch:{ all -> 0x063d }
            java.lang.String r1 = r1.zzb()     // Catch:{ all -> 0x063d }
            r0.zza(r2, r3, r1)     // Catch:{ all -> 0x063d }
            goto L_0x0636
        L_0x0611:
            r8.zzab = r4     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzal r0 = r24.zzf()     // Catch:{ all -> 0x063d }
            r24.zze()     // Catch:{ all -> 0x063d }
            long r3 = com.google.android.gms.measurement.internal.zzag.zzg()     // Catch:{ all -> 0x063d }
            long r1 = r1 - r3
            java.lang.String r0 = r0.zza((long) r1)     // Catch:{ all -> 0x063d }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x063d }
            if (r1 != 0) goto L_0x0636
            com.google.android.gms.measurement.internal.zzal r1 = r24.zzf()     // Catch:{ all -> 0x063d }
            com.google.android.gms.measurement.internal.zzg r0 = r1.zze(r0)     // Catch:{ all -> 0x063d }
            if (r0 == 0) goto L_0x0636
            r8.zzb((com.google.android.gms.measurement.internal.zzg) r0)     // Catch:{ all -> 0x063d }
        L_0x0636:
            r1 = 0
            r8.zzw = r1
            r24.zzaa()
            return
        L_0x063d:
            r0 = move-exception
            r1 = 0
        L_0x063f:
            r8.zzw = r1
            r24.zzaa()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zzw():void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:47|48|49|50) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x0110 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zze(java.lang.String r12) {
        /*
            r11 = this;
            com.google.android.gms.measurement.internal.zzhv r0 = r11.zzl()
            r0.zzt()
            r11.zzs()
            r0 = 1
            r11.zzw = r0
            r1 = 0
            com.google.android.gms.measurement.internal.zzhy r2 = r11.zzm     // Catch:{ all -> 0x012b }
            com.google.android.gms.measurement.internal.zzls r2 = r2.zzr()     // Catch:{ all -> 0x012b }
            java.lang.Boolean r2 = r2.zzab()     // Catch:{ all -> 0x012b }
            if (r2 != 0) goto L_0x002d
            com.google.android.gms.measurement.internal.zzgo r12 = r11.zzj()     // Catch:{ all -> 0x012b }
            com.google.android.gms.measurement.internal.zzgq r12 = r12.zzu()     // Catch:{ all -> 0x012b }
            java.lang.String r0 = "Upload data called on the client side before use of service was decided"
            r12.zza(r0)     // Catch:{ all -> 0x012b }
            r11.zzw = r1
            r11.zzaa()
            return
        L_0x002d:
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x012b }
            if (r2 == 0) goto L_0x0046
            com.google.android.gms.measurement.internal.zzgo r12 = r11.zzj()     // Catch:{ all -> 0x012b }
            com.google.android.gms.measurement.internal.zzgq r12 = r12.zzg()     // Catch:{ all -> 0x012b }
            java.lang.String r0 = "Upload called in the client side when service should be used"
            r12.zza(r0)     // Catch:{ all -> 0x012b }
            r11.zzw = r1
            r11.zzaa()
            return
        L_0x0046:
            long r2 = r11.zzp     // Catch:{ all -> 0x012b }
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x0057
            r11.zzac()     // Catch:{ all -> 0x012b }
            r11.zzw = r1
            r11.zzaa()
            return
        L_0x0057:
            com.google.android.gms.measurement.internal.zzgr r2 = r11.zzh()     // Catch:{ all -> 0x012b }
            boolean r2 = r2.zzu()     // Catch:{ all -> 0x012b }
            if (r2 != 0) goto L_0x0077
            com.google.android.gms.measurement.internal.zzgo r12 = r11.zzj()     // Catch:{ all -> 0x012b }
            com.google.android.gms.measurement.internal.zzgq r12 = r12.zzp()     // Catch:{ all -> 0x012b }
            java.lang.String r0 = "Network not connected, ignoring upload request"
            r12.zza(r0)     // Catch:{ all -> 0x012b }
            r11.zzac()     // Catch:{ all -> 0x012b }
            r11.zzw = r1
            r11.zzaa()
            return
        L_0x0077:
            com.google.android.gms.measurement.internal.zzal r2 = r11.zzf()     // Catch:{ all -> 0x012b }
            boolean r2 = r2.zzs(r12)     // Catch:{ all -> 0x012b }
            if (r2 != 0) goto L_0x0094
            com.google.android.gms.measurement.internal.zzgo r0 = r11.zzj()     // Catch:{ all -> 0x012b }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzp()     // Catch:{ all -> 0x012b }
            java.lang.String r2 = "Upload queue has no batches for appId"
            r0.zza(r2, r12)     // Catch:{ all -> 0x012b }
            r11.zzw = r1
            r11.zzaa()
            return
        L_0x0094:
            com.google.android.gms.measurement.internal.zzal r2 = r11.zzf()     // Catch:{ all -> 0x012b }
            com.google.android.gms.measurement.internal.zzoj r2 = r2.zzj(r12)     // Catch:{ all -> 0x012b }
            if (r2 != 0) goto L_0x00a4
            r11.zzw = r1
            r11.zzaa()
            return
        L_0x00a4:
            com.google.android.gms.internal.measurement.zzfy$zzj r3 = r2.zzc()     // Catch:{ all -> 0x012b }
            if (r3 != 0) goto L_0x00b0
            r11.zzw = r1
            r11.zzaa()
            return
        L_0x00b0:
            com.google.android.gms.measurement.internal.zzoo r4 = r11.zzp()     // Catch:{ all -> 0x012b }
            java.lang.String r4 = r4.zza((com.google.android.gms.internal.measurement.zzfy.zzj) r3)     // Catch:{ all -> 0x012b }
            byte[] r8 = r3.zzca()     // Catch:{ all -> 0x012b }
            com.google.android.gms.measurement.internal.zzgo r5 = r11.zzj()     // Catch:{ all -> 0x012b }
            com.google.android.gms.measurement.internal.zzgq r5 = r5.zzp()     // Catch:{ all -> 0x012b }
            java.lang.String r6 = "Uploading data from upload queue. appId, uncompressed size, data"
            int r7 = r8.length     // Catch:{ all -> 0x012b }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x012b }
            r5.zza(r6, r12, r7, r4)     // Catch:{ all -> 0x012b }
            boolean r4 = com.google.android.gms.internal.measurement.zzpb.zza()     // Catch:{ all -> 0x012b }
            if (r4 == 0) goto L_0x00f3
            com.google.android.gms.measurement.internal.zzag r4 = r11.zze()     // Catch:{ all -> 0x012b }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzbh.zzcf     // Catch:{ all -> 0x012b }
            boolean r4 = r4.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r5)     // Catch:{ all -> 0x012b }
            if (r4 == 0) goto L_0x00f3
            r11.zzv = r0     // Catch:{ all -> 0x012b }
            com.google.android.gms.measurement.internal.zzgr r0 = r11.zzh()     // Catch:{ all -> 0x012b }
            com.google.android.gms.measurement.internal.zznw r4 = r2.zzb()     // Catch:{ all -> 0x012b }
            com.google.android.gms.measurement.internal.zzob r5 = new com.google.android.gms.measurement.internal.zzob     // Catch:{ all -> 0x012b }
            r5.<init>(r11, r12, r2)     // Catch:{ all -> 0x012b }
            r0.zza(r12, r4, r3, r5)     // Catch:{ all -> 0x012b }
            goto L_0x0125
        L_0x00f3:
            r11.zzv = r0     // Catch:{ MalformedURLException -> 0x0110 }
            com.google.android.gms.measurement.internal.zzgr r5 = r11.zzh()     // Catch:{ MalformedURLException -> 0x0110 }
            java.net.URL r7 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0110 }
            java.lang.String r0 = r2.zzd()     // Catch:{ MalformedURLException -> 0x0110 }
            r7.<init>(r0)     // Catch:{ MalformedURLException -> 0x0110 }
            java.util.Map r9 = r2.zze()     // Catch:{ MalformedURLException -> 0x0110 }
            com.google.android.gms.measurement.internal.zzoe r10 = new com.google.android.gms.measurement.internal.zzoe     // Catch:{ MalformedURLException -> 0x0110 }
            r10.<init>(r11, r12, r2)     // Catch:{ MalformedURLException -> 0x0110 }
            r6 = r12
            r5.zza(r6, r7, r8, r9, r10)     // Catch:{ MalformedURLException -> 0x0110 }
            goto L_0x0125
        L_0x0110:
            com.google.android.gms.measurement.internal.zzgo r0 = r11.zzj()     // Catch:{ all -> 0x012b }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzg()     // Catch:{ all -> 0x012b }
            java.lang.String r3 = "Failed to parse URL. Not uploading MeasurementBatch. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r12)     // Catch:{ all -> 0x012b }
            java.lang.String r2 = r2.zzd()     // Catch:{ all -> 0x012b }
            r0.zza(r3, r12, r2)     // Catch:{ all -> 0x012b }
        L_0x0125:
            r11.zzw = r1
            r11.zzaa()
            return
        L_0x012b:
            r12 = move-exception
            r11.zzw = r1
            r11.zzaa()
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zze(java.lang.String):void");
    }

    private final void zza(String str, zzfy.zzh.zza zza2, Bundle bundle, String str2) {
        int i;
        List listOf = CollectionUtils.listOf((T[]) new String[]{"_o", "_sn", "_sc", "_si"});
        if (zzos.zzg(zza2.zzf()) || zzos.zzg(str)) {
            i = zze().zzb(str2, true);
        } else {
            i = zze().zza(str2, true);
        }
        long j = (long) i;
        long codePointCount = (long) zza2.zzg().codePointCount(0, zza2.zzg().length());
        zzq();
        String zzf2 = zza2.zzf();
        zze();
        String zza3 = zzos.zza(zzf2, 40, true);
        if (codePointCount > j && !listOf.contains(zza2.zzf())) {
            if ("_ev".equals(zza2.zzf())) {
                zzq();
                bundle.putString("_ev", zzos.zza(zza2.zzg(), zze().zzb(str2, true), true));
                return;
            }
            zzj().zzv().zza("Param value is too long; discarded. Name, value length", zza3, Long.valueOf(codePointCount));
            if (bundle.getLong("_err") == 0) {
                bundle.putLong("_err", 4);
                if (bundle.getString("_ev") == null) {
                    bundle.putString("_ev", zza3);
                    bundle.putLong("_el", codePointCount);
                }
            }
            bundle.remove(zza2.zzf());
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: IfRegionVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Don't wrap MOVE or CONST insns: 0x0510: MOVE  (r5v19 java.lang.String) = (r23v0 java.lang.String)
        	at jadx.core.dex.instructions.args.InsnArg.wrapArg(InsnArg.java:164)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.assignInline(CodeShrinkVisitor.java:133)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.checkInline(CodeShrinkVisitor.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:65)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.regions.TernaryMod.makeTernaryInsn(TernaryMod.java:122)
        	at jadx.core.dex.visitors.regions.TernaryMod.visitRegion(TernaryMod.java:34)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:73)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.visit(IfRegionVisitor.java:31)
        */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:172:0x0528 */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x031f A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x0358 A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0367  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x03bf A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x03eb  */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x0534 A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x056c A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x05de A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x062a A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x0637 A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x0644 A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x0651 A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x065f A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x0672 A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x06ca A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:259:0x0765 A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:262:0x0779 A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:275:0x07bf A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:287:0x080b A[SYNTHETIC, Splitter:B:287:0x080b] */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x082c A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:300:0x08a1 A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x08ba A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x091e A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:317:0x0946 A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:321:0x0964 A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x09dc A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:348:0x0a3b A[Catch:{ IOException -> 0x0a40, all -> 0x0a88 }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01a6 A[SYNTHETIC, Splitter:B:59:0x01a6] */
    private final void zzc(com.google.android.gms.measurement.internal.zzbf r40, com.google.android.gms.measurement.internal.zzo r41) {
        /*
            r39 = this;
            r1 = r39
            r2 = r40
            r3 = r41
            java.lang.String r4 = "_fx"
            java.lang.String r5 = "_sno"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r41)
            java.lang.String r6 = r3.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r6)
            long r6 = java.lang.System.nanoTime()
            com.google.android.gms.measurement.internal.zzhv r8 = r39.zzl()
            r8.zzt()
            r39.zzs()
            java.lang.String r8 = r3.zza
            r39.zzp()
            boolean r9 = com.google.android.gms.measurement.internal.zzoo.zza((com.google.android.gms.measurement.internal.zzbf) r40, (com.google.android.gms.measurement.internal.zzo) r41)
            if (r9 != 0) goto L_0x002c
            return
        L_0x002c:
            boolean r9 = r3.zzh
            if (r9 != 0) goto L_0x0034
            r1.zza((com.google.android.gms.measurement.internal.zzo) r3)
            return
        L_0x0034:
            com.google.android.gms.measurement.internal.zzhl r9 = r39.zzi()
            java.lang.String r10 = r2.zza
            boolean r9 = r9.zzd(r8, r10)
            java.lang.String r15 = "_err"
            r14 = 0
            if (r9 == 0) goto L_0x00df
            com.google.android.gms.measurement.internal.zzgo r3 = r39.zzj()
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzu()
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r8)
            com.google.android.gms.measurement.internal.zzhy r5 = r1.zzm
            com.google.android.gms.measurement.internal.zzgh r5 = r5.zzk()
            java.lang.String r6 = r2.zza
            java.lang.String r5 = r5.zza((java.lang.String) r6)
            java.lang.String r6 = "Dropping blocked event. appId"
            r3.zza(r6, r4, r5)
            com.google.android.gms.measurement.internal.zzhl r3 = r39.zzi()
            boolean r3 = r3.zzl(r8)
            if (r3 != 0) goto L_0x0077
            com.google.android.gms.measurement.internal.zzhl r3 = r39.zzi()
            boolean r3 = r3.zzn(r8)
            if (r3 == 0) goto L_0x0075
            goto L_0x0077
        L_0x0075:
            r3 = 0
            goto L_0x0078
        L_0x0077:
            r3 = 1
        L_0x0078:
            if (r3 != 0) goto L_0x0095
            java.lang.String r4 = r2.zza
            boolean r4 = r15.equals(r4)
            if (r4 != 0) goto L_0x0095
            r39.zzq()
            com.google.android.gms.measurement.internal.zzor r9 = r1.zzah
            r11 = 11
            java.lang.String r12 = "_ev"
            java.lang.String r13 = r2.zza
            r2 = 0
            r10 = r8
            r4 = r14
            r14 = r2
            com.google.android.gms.measurement.internal.zzos.zza((com.google.android.gms.measurement.internal.zzor) r9, (java.lang.String) r10, (int) r11, (java.lang.String) r12, (java.lang.String) r13, (int) r14)
            goto L_0x0096
        L_0x0095:
            r4 = r14
        L_0x0096:
            if (r3 == 0) goto L_0x00de
            com.google.android.gms.measurement.internal.zzal r2 = r39.zzf()
            com.google.android.gms.measurement.internal.zzg r2 = r2.zze(r8)
            if (r2 == 0) goto L_0x00de
            long r5 = r2.zzp()
            long r7 = r2.zzg()
            long r5 = java.lang.Math.max(r5, r7)
            com.google.android.gms.common.util.Clock r3 = r39.zzb()
            long r7 = r3.currentTimeMillis()
            long r7 = r7 - r5
            long r5 = java.lang.Math.abs(r7)
            r39.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Long> r3 = com.google.android.gms.measurement.internal.zzbh.zzaa
            java.lang.Object r3 = r3.zza(r4)
            java.lang.Long r3 = (java.lang.Long) r3
            long r3 = r3.longValue()
            int r3 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r3 <= 0) goto L_0x00de
            com.google.android.gms.measurement.internal.zzgo r3 = r39.zzj()
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzc()
            java.lang.String r4 = "Fetching config for blocked app"
            r3.zza(r4)
            r1.zzb((com.google.android.gms.measurement.internal.zzg) r2)
        L_0x00de:
            return
        L_0x00df:
            com.google.android.gms.measurement.internal.zzgs r2 = com.google.android.gms.measurement.internal.zzgs.zza(r40)
            com.google.android.gms.measurement.internal.zzos r9 = r39.zzq()
            com.google.android.gms.measurement.internal.zzag r10 = r39.zze()
            int r10 = r10.zzb(r8)
            r9.zza((com.google.android.gms.measurement.internal.zzgs) r2, (int) r10)
            com.google.android.gms.measurement.internal.zzag r9 = r39.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Integer> r10 = com.google.android.gms.measurement.internal.zzbh.zzas
            r11 = 10
            r12 = 35
            int r9 = r9.zza(r8, r10, r11, r12)
            java.util.TreeSet r10 = new java.util.TreeSet
            android.os.Bundle r11 = r2.zzc
            java.util.Set r11 = r11.keySet()
            r10.<init>(r11)
            java.util.Iterator r10 = r10.iterator()
        L_0x010f:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x0131
            java.lang.Object r11 = r10.next()
            java.lang.String r11 = (java.lang.String) r11
            java.lang.String r12 = "items"
            boolean r12 = r12.equals(r11)
            if (r12 == 0) goto L_0x010f
            com.google.android.gms.measurement.internal.zzos r12 = r39.zzq()
            android.os.Bundle r13 = r2.zzc
            android.os.Parcelable[] r11 = r13.getParcelableArray(r11)
            r12.zza((android.os.Parcelable[]) r11, (int) r9)
            goto L_0x010f
        L_0x0131:
            com.google.android.gms.measurement.internal.zzbf r2 = r2.zza()
            com.google.android.gms.measurement.internal.zzgo r9 = r39.zzj()
            r10 = 2
            boolean r9 = r9.zza((int) r10)
            if (r9 == 0) goto L_0x0157
            com.google.android.gms.measurement.internal.zzgo r9 = r39.zzj()
            com.google.android.gms.measurement.internal.zzgq r9 = r9.zzp()
            com.google.android.gms.measurement.internal.zzhy r11 = r1.zzm
            com.google.android.gms.measurement.internal.zzgh r11 = r11.zzk()
            java.lang.String r11 = r11.zza((com.google.android.gms.measurement.internal.zzbf) r2)
            java.lang.String r12 = "Logging event"
            r9.zza(r12, r11)
        L_0x0157:
            boolean r9 = com.google.android.gms.internal.measurement.zzow.zza()
            if (r9 == 0) goto L_0x0166
            com.google.android.gms.measurement.internal.zzag r9 = r39.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r11 = com.google.android.gms.measurement.internal.zzbh.zzcd
            r9.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r11)
        L_0x0166:
            com.google.android.gms.measurement.internal.zzal r9 = r39.zzf()
            r9.zzp()
            r1.zza((com.google.android.gms.measurement.internal.zzo) r3)     // Catch:{ all -> 0x0a88 }
            java.lang.String r9 = "ecommerce_purchase"
            java.lang.String r11 = r2.zza     // Catch:{ all -> 0x0a88 }
            boolean r9 = r9.equals(r11)     // Catch:{ all -> 0x0a88 }
            java.lang.String r11 = "refund"
            if (r9 != 0) goto L_0x0191
            java.lang.String r9 = "purchase"
            java.lang.String r12 = r2.zza     // Catch:{ all -> 0x0a88 }
            boolean r9 = r9.equals(r12)     // Catch:{ all -> 0x0a88 }
            if (r9 != 0) goto L_0x0191
            java.lang.String r9 = r2.zza     // Catch:{ all -> 0x0a88 }
            boolean r9 = r11.equals(r9)     // Catch:{ all -> 0x0a88 }
            if (r9 == 0) goto L_0x018f
            goto L_0x0191
        L_0x018f:
            r9 = 0
            goto L_0x0192
        L_0x0191:
            r9 = 1
        L_0x0192:
            java.lang.String r12 = "_iap"
            java.lang.String r13 = r2.zza     // Catch:{ all -> 0x0a88 }
            boolean r12 = r12.equals(r13)     // Catch:{ all -> 0x0a88 }
            if (r12 != 0) goto L_0x01a1
            if (r9 == 0) goto L_0x019f
            goto L_0x01a1
        L_0x019f:
            r12 = 0
            goto L_0x01a2
        L_0x01a1:
            r12 = 1
        L_0x01a2:
            java.lang.String r13 = "value"
            if (r12 == 0) goto L_0x0367
            com.google.android.gms.measurement.internal.zzbe r12 = r2.zzb     // Catch:{ all -> 0x0a88 }
            java.lang.String r14 = "currency"
            java.lang.String r12 = r12.zzd(r14)     // Catch:{ all -> 0x0a88 }
            if (r9 == 0) goto L_0x0211
            com.google.android.gms.measurement.internal.zzbe r9 = r2.zzb     // Catch:{ all -> 0x0a88 }
            java.lang.Double r9 = r9.zza((java.lang.String) r13)     // Catch:{ all -> 0x0a88 }
            double r19 = r9.doubleValue()     // Catch:{ all -> 0x0a88 }
            r21 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r19 = r19 * r21
            r23 = 0
            int r9 = (r19 > r23 ? 1 : (r19 == r23 ? 0 : -1))
            if (r9 != 0) goto L_0x01d6
            com.google.android.gms.measurement.internal.zzbe r9 = r2.zzb     // Catch:{ all -> 0x0a88 }
            java.lang.Long r9 = r9.zzb(r13)     // Catch:{ all -> 0x0a88 }
            r14 = r11
            long r10 = r9.longValue()     // Catch:{ all -> 0x0a88 }
            double r9 = (double) r10     // Catch:{ all -> 0x0a88 }
            double r19 = r9 * r21
            goto L_0x01d7
        L_0x01d6:
            r14 = r11
        L_0x01d7:
            r9 = 4890909195324358656(0x43e0000000000000, double:9.223372036854776E18)
            int r9 = (r19 > r9 ? 1 : (r19 == r9 ? 0 : -1))
            if (r9 > 0) goto L_0x01f1
            r9 = -4332462841530417152(0xc3e0000000000000, double:-9.223372036854776E18)
            int r9 = (r19 > r9 ? 1 : (r19 == r9 ? 0 : -1))
            if (r9 < 0) goto L_0x01f1
            long r9 = java.lang.Math.round(r19)     // Catch:{ all -> 0x0a88 }
            java.lang.String r11 = r2.zza     // Catch:{ all -> 0x0a88 }
            boolean r11 = r14.equals(r11)     // Catch:{ all -> 0x0a88 }
            if (r11 == 0) goto L_0x021b
            long r9 = -r9
            goto L_0x021b
        L_0x01f1:
            com.google.android.gms.measurement.internal.zzgo r9 = r39.zzj()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzgq r9 = r9.zzu()     // Catch:{ all -> 0x0a88 }
            java.lang.String r10 = "Data lost. Currency value is too big. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a88 }
            java.lang.Double r12 = java.lang.Double.valueOf(r19)     // Catch:{ all -> 0x0a88 }
            r9.zza(r10, r11, r12)     // Catch:{ all -> 0x0a88 }
            r27 = r4
            r24 = r6
            r7 = r13
            r26 = r15
            r6 = 0
            r13 = 0
            goto L_0x0356
        L_0x0211:
            com.google.android.gms.measurement.internal.zzbe r9 = r2.zzb     // Catch:{ all -> 0x0a88 }
            java.lang.Long r9 = r9.zzb(r13)     // Catch:{ all -> 0x0a88 }
            long r9 = r9.longValue()     // Catch:{ all -> 0x0a88 }
        L_0x021b:
            boolean r11 = android.text.TextUtils.isEmpty(r12)     // Catch:{ all -> 0x0a88 }
            if (r11 != 0) goto L_0x034d
            java.util.Locale r11 = java.util.Locale.US     // Catch:{ all -> 0x0a88 }
            java.lang.String r11 = r12.toUpperCase(r11)     // Catch:{ all -> 0x0a88 }
            java.lang.String r12 = "[A-Z]{3}"
            boolean r12 = r11.matches(r12)     // Catch:{ all -> 0x0a88 }
            if (r12 == 0) goto L_0x034d
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0a88 }
            java.lang.String r14 = "_ltv_"
            r12.<init>(r14)     // Catch:{ all -> 0x0a88 }
            java.lang.StringBuilder r11 = r12.append(r11)     // Catch:{ all -> 0x0a88 }
            java.lang.String r12 = r11.toString()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzal r11 = r39.zzf()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzop r11 = r11.zze(r8, r12)     // Catch:{ all -> 0x0a88 }
            if (r11 == 0) goto L_0x0281
            java.lang.Object r14 = r11.zze     // Catch:{ all -> 0x0a88 }
            boolean r14 = r14 instanceof java.lang.Long     // Catch:{ all -> 0x0a88 }
            if (r14 != 0) goto L_0x024f
            goto L_0x0281
        L_0x024f:
            java.lang.Object r11 = r11.zze     // Catch:{ all -> 0x0a88 }
            java.lang.Long r11 = (java.lang.Long) r11     // Catch:{ all -> 0x0a88 }
            long r19 = r11.longValue()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzop r21 = new com.google.android.gms.measurement.internal.zzop     // Catch:{ all -> 0x0a88 }
            java.lang.String r11 = r2.zzc     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.common.util.Clock r14 = r39.zzb()     // Catch:{ all -> 0x0a88 }
            long r22 = r14.currentTimeMillis()     // Catch:{ all -> 0x0a88 }
            long r19 = r19 + r9
            java.lang.Long r19 = java.lang.Long.valueOf(r19)     // Catch:{ all -> 0x0a88 }
            r9 = r21
            r10 = r8
            r14 = 0
            r24 = r6
            r7 = r13
            r6 = 1
            r13 = r22
            r26 = r15
            r15 = r19
            r9.<init>(r10, r11, r12, r13, r15)     // Catch:{ all -> 0x0a88 }
            r27 = r4
            r4 = r21
            r6 = 0
            goto L_0x0315
        L_0x0281:
            r24 = r6
            r7 = r13
            r26 = r15
            r6 = 1
            com.google.android.gms.measurement.internal.zzal r11 = r39.zzf()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzag r13 = r39.zze()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Integer> r14 = com.google.android.gms.measurement.internal.zzbh.zzag     // Catch:{ all -> 0x0a88 }
            int r13 = r13.zzb((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzfz<java.lang.Integer>) r14)     // Catch:{ all -> 0x0a88 }
            int r13 = r13 - r6
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r8)     // Catch:{ all -> 0x0a88 }
            r11.zzt()     // Catch:{ all -> 0x0a88 }
            r11.zzal()     // Catch:{ all -> 0x0a88 }
            android.database.sqlite.SQLiteDatabase r14 = r11.e_()     // Catch:{ SQLiteException -> 0x02e5 }
            com.google.android.gms.measurement.internal.zzag r15 = r11.zze()     // Catch:{ SQLiteException -> 0x02e5 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzbh.zzdl     // Catch:{ SQLiteException -> 0x02e5 }
            boolean r6 = r15.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r6)     // Catch:{ SQLiteException -> 0x02e5 }
            if (r6 == 0) goto L_0x02b2
            java.lang.String r6 = "and name like '!_ltv!_%' escape '!'"
            goto L_0x02b4
        L_0x02b2:
            java.lang.String r6 = "and name like '_ltv_%' "
        L_0x02b4:
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x02e5 }
            r27 = r4
            java.lang.String r4 = "delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? "
            r15.<init>(r4)     // Catch:{ SQLiteException -> 0x02e3 }
            java.lang.StringBuilder r4 = r15.append(r6)     // Catch:{ SQLiteException -> 0x02e3 }
            java.lang.String r6 = "order by set_timestamp desc limit ?,10);"
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ SQLiteException -> 0x02e3 }
            java.lang.String r4 = r4.toString()     // Catch:{ SQLiteException -> 0x02e3 }
            r6 = 3
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x02e3 }
            r15 = 0
            r6[r15] = r8     // Catch:{ SQLiteException -> 0x02e1 }
            r16 = 1
            r6[r16] = r8     // Catch:{ SQLiteException -> 0x02e1 }
            java.lang.String r13 = java.lang.String.valueOf(r13)     // Catch:{ SQLiteException -> 0x02e1 }
            r16 = 2
            r6[r16] = r13     // Catch:{ SQLiteException -> 0x02e1 }
            r14.execSQL(r4, r6)     // Catch:{ SQLiteException -> 0x02e1 }
            goto L_0x02fb
        L_0x02e1:
            r0 = move-exception
            goto L_0x02e9
        L_0x02e3:
            r0 = move-exception
            goto L_0x02e8
        L_0x02e5:
            r0 = move-exception
            r27 = r4
        L_0x02e8:
            r15 = 0
        L_0x02e9:
            r4 = r0
            com.google.android.gms.measurement.internal.zzgo r6 = r11.zzj()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzgq r6 = r6.zzg()     // Catch:{ all -> 0x0a88 }
            java.lang.String r11 = "Error pruning currencies. appId"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a88 }
            r6.zza(r11, r13, r4)     // Catch:{ all -> 0x0a88 }
        L_0x02fb:
            com.google.android.gms.measurement.internal.zzop r21 = new com.google.android.gms.measurement.internal.zzop     // Catch:{ all -> 0x0a88 }
            java.lang.String r11 = r2.zzc     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.common.util.Clock r4 = r39.zzb()     // Catch:{ all -> 0x0a88 }
            long r13 = r4.currentTimeMillis()     // Catch:{ all -> 0x0a88 }
            java.lang.Long r4 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x0a88 }
            r9 = r21
            r10 = r8
            r6 = r15
            r15 = r4
            r9.<init>(r10, r11, r12, r13, r15)     // Catch:{ all -> 0x0a88 }
            r4 = r21
        L_0x0315:
            com.google.android.gms.measurement.internal.zzal r9 = r39.zzf()     // Catch:{ all -> 0x0a88 }
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzop) r4)     // Catch:{ all -> 0x0a88 }
            if (r9 != 0) goto L_0x0355
            com.google.android.gms.measurement.internal.zzgo r9 = r39.zzj()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzgq r9 = r9.zzg()     // Catch:{ all -> 0x0a88 }
            java.lang.String r10 = "Too many unique user properties are set. Ignoring user property. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzhy r12 = r1.zzm     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzgh r12 = r12.zzk()     // Catch:{ all -> 0x0a88 }
            java.lang.String r13 = r4.zzc     // Catch:{ all -> 0x0a88 }
            java.lang.String r12 = r12.zzc(r13)     // Catch:{ all -> 0x0a88 }
            java.lang.Object r4 = r4.zze     // Catch:{ all -> 0x0a88 }
            r9.zza(r10, r11, r12, r4)     // Catch:{ all -> 0x0a88 }
            r39.zzq()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzor r9 = r1.zzah     // Catch:{ all -> 0x0a88 }
            r11 = 9
            r12 = 0
            r13 = 0
            r14 = 0
            r10 = r8
            com.google.android.gms.measurement.internal.zzos.zza((com.google.android.gms.measurement.internal.zzor) r9, (java.lang.String) r10, (int) r11, (java.lang.String) r12, (java.lang.String) r13, (int) r14)     // Catch:{ all -> 0x0a88 }
            goto L_0x0355
        L_0x034d:
            r27 = r4
            r24 = r6
            r7 = r13
            r26 = r15
            r6 = 0
        L_0x0355:
            r13 = 1
        L_0x0356:
            if (r13 != 0) goto L_0x036f
            com.google.android.gms.measurement.internal.zzal r2 = r39.zzf()     // Catch:{ all -> 0x0a88 }
            r2.zzw()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzal r2 = r39.zzf()
            r2.zzu()
            return
        L_0x0367:
            r27 = r4
            r24 = r6
            r7 = r13
            r26 = r15
            r6 = 0
        L_0x036f:
            java.lang.String r4 = r2.zza     // Catch:{ all -> 0x0a88 }
            boolean r4 = com.google.android.gms.measurement.internal.zzos.zzh(r4)     // Catch:{ all -> 0x0a88 }
            java.lang.String r9 = r2.zza     // Catch:{ all -> 0x0a88 }
            r10 = r26
            boolean r22 = r10.equals(r9)     // Catch:{ all -> 0x0a88 }
            r39.zzq()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzbe r9 = r2.zzb     // Catch:{ all -> 0x0a88 }
            long r9 = com.google.android.gms.measurement.internal.zzos.zza((com.google.android.gms.measurement.internal.zzbe) r9)     // Catch:{ all -> 0x0a88 }
            r13 = 1
            long r15 = r9 + r13
            com.google.android.gms.measurement.internal.zzal r9 = r39.zzf()     // Catch:{ all -> 0x0a88 }
            long r10 = r39.zzx()     // Catch:{ all -> 0x0a88 }
            r17 = 1
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r12 = r8
            r23 = r7
            r6 = r13
            r13 = r15
            r15 = r17
            r16 = r4
            r17 = r18
            r18 = r22
            com.google.android.gms.measurement.internal.zzaq r9 = r9.zza(r10, r12, r13, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ all -> 0x0a88 }
            long r10 = r9.zzb     // Catch:{ all -> 0x0a88 }
            r39.zze()     // Catch:{ all -> 0x0a88 }
            long r12 = com.google.android.gms.measurement.internal.zzag.zzh()     // Catch:{ all -> 0x0a88 }
            long r10 = r10 - r12
            r14 = 0
            int r12 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            r16 = 1000(0x3e8, double:4.94E-321)
            if (r12 <= 0) goto L_0x03eb
            long r10 = r10 % r16
            int r2 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r2 != 0) goto L_0x03dc
            com.google.android.gms.measurement.internal.zzgo r2 = r39.zzj()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzg()     // Catch:{ all -> 0x0a88 }
            java.lang.String r3 = "Data loss. Too many events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a88 }
            long r5 = r9.zzb     // Catch:{ all -> 0x0a88 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0a88 }
            r2.zza(r3, r4, r5)     // Catch:{ all -> 0x0a88 }
        L_0x03dc:
            com.google.android.gms.measurement.internal.zzal r2 = r39.zzf()     // Catch:{ all -> 0x0a88 }
            r2.zzw()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzal r2 = r39.zzf()
            r2.zzu()
            return
        L_0x03eb:
            if (r4 == 0) goto L_0x0443
            long r10 = r9.zza     // Catch:{ all -> 0x0a88 }
            r39.zze()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Integer> r12 = com.google.android.gms.measurement.internal.zzbh.zzm     // Catch:{ all -> 0x0a88 }
            r13 = 0
            java.lang.Object r12 = r12.zza(r13)     // Catch:{ all -> 0x0a88 }
            java.lang.Integer r12 = (java.lang.Integer) r12     // Catch:{ all -> 0x0a88 }
            int r12 = r12.intValue()     // Catch:{ all -> 0x0a88 }
            long r6 = (long) r12     // Catch:{ all -> 0x0a88 }
            long r10 = r10 - r6
            int r6 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r6 <= 0) goto L_0x0444
            long r10 = r10 % r16
            r3 = 1
            int r3 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r3 != 0) goto L_0x0424
            com.google.android.gms.measurement.internal.zzgo r3 = r39.zzj()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzg()     // Catch:{ all -> 0x0a88 }
            java.lang.String r4 = "Data loss. Too many public events logged. appId, count"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a88 }
            long r6 = r9.zza     // Catch:{ all -> 0x0a88 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0a88 }
            r3.zza(r4, r5, r6)     // Catch:{ all -> 0x0a88 }
        L_0x0424:
            r39.zzq()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzor r9 = r1.zzah     // Catch:{ all -> 0x0a88 }
            r11 = 16
            java.lang.String r12 = "_ev"
            java.lang.String r13 = r2.zza     // Catch:{ all -> 0x0a88 }
            r14 = 0
            r10 = r8
            com.google.android.gms.measurement.internal.zzos.zza((com.google.android.gms.measurement.internal.zzor) r9, (java.lang.String) r10, (int) r11, (java.lang.String) r12, (java.lang.String) r13, (int) r14)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzal r2 = r39.zzf()     // Catch:{ all -> 0x0a88 }
            r2.zzw()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzal r2 = r39.zzf()
            r2.zzu()
            return
        L_0x0443:
            r13 = 0
        L_0x0444:
            if (r22 == 0) goto L_0x0492
            long r6 = r9.zzd     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzag r10 = r39.zze()     // Catch:{ all -> 0x0a88 }
            java.lang.String r11 = r3.zza     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Integer> r12 = com.google.android.gms.measurement.internal.zzbh.zzl     // Catch:{ all -> 0x0a88 }
            int r10 = r10.zzb((java.lang.String) r11, (com.google.android.gms.measurement.internal.zzfz<java.lang.Integer>) r12)     // Catch:{ all -> 0x0a88 }
            r11 = 1000000(0xf4240, float:1.401298E-39)
            int r10 = java.lang.Math.min(r11, r10)     // Catch:{ all -> 0x0a88 }
            r11 = 0
            int r10 = java.lang.Math.max(r11, r10)     // Catch:{ all -> 0x0a88 }
            long r10 = (long) r10     // Catch:{ all -> 0x0a88 }
            long r6 = r6 - r10
            int r10 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r10 <= 0) goto L_0x0492
            r10 = 1
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 != 0) goto L_0x0483
            com.google.android.gms.measurement.internal.zzgo r2 = r39.zzj()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzg()     // Catch:{ all -> 0x0a88 }
            java.lang.String r3 = "Too many error events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a88 }
            long r5 = r9.zzd     // Catch:{ all -> 0x0a88 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0a88 }
            r2.zza(r3, r4, r5)     // Catch:{ all -> 0x0a88 }
        L_0x0483:
            com.google.android.gms.measurement.internal.zzal r2 = r39.zzf()     // Catch:{ all -> 0x0a88 }
            r2.zzw()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzal r2 = r39.zzf()
            r2.zzu()
            return
        L_0x0492:
            com.google.android.gms.measurement.internal.zzbe r6 = r2.zzb     // Catch:{ all -> 0x0a88 }
            android.os.Bundle r6 = r6.zzb()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzos r7 = r39.zzq()     // Catch:{ all -> 0x0a88 }
            java.lang.String r9 = "_o"
            java.lang.String r10 = r2.zzc     // Catch:{ all -> 0x0a88 }
            r7.zza((android.os.Bundle) r6, (java.lang.String) r9, (java.lang.Object) r10)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzos r7 = r39.zzq()     // Catch:{ all -> 0x0a88 }
            java.lang.String r9 = r3.zzac     // Catch:{ all -> 0x0a88 }
            boolean r7 = r7.zzd(r8, r9)     // Catch:{ all -> 0x0a88 }
            java.lang.String r12 = "_r"
            if (r7 == 0) goto L_0x04cb
            com.google.android.gms.measurement.internal.zzos r7 = r39.zzq()     // Catch:{ all -> 0x0a88 }
            java.lang.String r9 = "_dbg"
            r10 = 1
            java.lang.Long r13 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x0a88 }
            r7.zza((android.os.Bundle) r6, (java.lang.String) r9, (java.lang.Object) r13)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzos r7 = r39.zzq()     // Catch:{ all -> 0x0a88 }
            java.lang.Long r9 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x0a88 }
            r7.zza((android.os.Bundle) r6, (java.lang.String) r12, (java.lang.Object) r9)     // Catch:{ all -> 0x0a88 }
        L_0x04cb:
            java.lang.String r7 = "_s"
            java.lang.String r9 = r2.zza     // Catch:{ all -> 0x0a88 }
            boolean r7 = r7.equals(r9)     // Catch:{ all -> 0x0a88 }
            if (r7 == 0) goto L_0x04f0
            com.google.android.gms.measurement.internal.zzal r7 = r39.zzf()     // Catch:{ all -> 0x0a88 }
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzop r7 = r7.zze(r9, r5)     // Catch:{ all -> 0x0a88 }
            if (r7 == 0) goto L_0x04f0
            java.lang.Object r9 = r7.zze     // Catch:{ all -> 0x0a88 }
            boolean r9 = r9 instanceof java.lang.Long     // Catch:{ all -> 0x0a88 }
            if (r9 == 0) goto L_0x04f0
            com.google.android.gms.measurement.internal.zzos r9 = r39.zzq()     // Catch:{ all -> 0x0a88 }
            java.lang.Object r7 = r7.zze     // Catch:{ all -> 0x0a88 }
            r9.zza((android.os.Bundle) r6, (java.lang.String) r5, (java.lang.Object) r7)     // Catch:{ all -> 0x0a88 }
        L_0x04f0:
            com.google.android.gms.measurement.internal.zzag r5 = r39.zze()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbh.zzdj     // Catch:{ all -> 0x0a88 }
            boolean r5 = r5.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r7)     // Catch:{ all -> 0x0a88 }
            if (r5 == 0) goto L_0x0528
            java.lang.String r5 = r2.zzc     // Catch:{ all -> 0x0a88 }
            java.lang.String r7 = "am"
            boolean r5 = java.util.Objects.equals(r5, r7)     // Catch:{ all -> 0x0a88 }
            if (r5 == 0) goto L_0x0528
            java.lang.String r5 = r2.zza     // Catch:{ all -> 0x0a88 }
            java.lang.String r7 = "_ai"
            boolean r5 = java.util.Objects.equals(r5, r7)     // Catch:{ all -> 0x0a88 }
            if (r5 == 0) goto L_0x0528
            r5 = r23
            java.lang.Object r7 = r6.get(r5)     // Catch:{ all -> 0x0a88 }
            if (r7 == 0) goto L_0x0528
            boolean r9 = r7 instanceof java.lang.String     // Catch:{ all -> 0x0a88 }
            if (r9 == 0) goto L_0x0528
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ NumberFormatException -> 0x0528 }
            double r9 = java.lang.Double.parseDouble(r7)     // Catch:{ NumberFormatException -> 0x0528 }
            r6.remove(r5)     // Catch:{ NumberFormatException -> 0x0528 }
            r6.putDouble(r5, r9)     // Catch:{ NumberFormatException -> 0x0528 }
        L_0x0528:
            com.google.android.gms.measurement.internal.zzal r5 = r39.zzf()     // Catch:{ all -> 0x0a88 }
            long r9 = r5.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a88 }
            int r5 = (r9 > r14 ? 1 : (r9 == r14 ? 0 : -1))
            if (r5 <= 0) goto L_0x0549
            com.google.android.gms.measurement.internal.zzgo r5 = r39.zzj()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzgq r5 = r5.zzu()     // Catch:{ all -> 0x0a88 }
            java.lang.String r7 = "Data lost. Too many events stored on disk, deleted. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a88 }
            java.lang.Long r9 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x0a88 }
            r5.zza(r7, r11, r9)     // Catch:{ all -> 0x0a88 }
        L_0x0549:
            com.google.android.gms.measurement.internal.zzbc r5 = new com.google.android.gms.measurement.internal.zzbc     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzhy r10 = r1.zzm     // Catch:{ all -> 0x0a88 }
            java.lang.String r11 = r2.zzc     // Catch:{ all -> 0x0a88 }
            java.lang.String r13 = r2.zza     // Catch:{ all -> 0x0a88 }
            long r14 = r2.zzd     // Catch:{ all -> 0x0a88 }
            r18 = 0
            r9 = r5
            r2 = r12
            r12 = r8
            r7 = 0
            r16 = r18
            r18 = r6
            r9.<init>((com.google.android.gms.measurement.internal.zzhy) r10, (java.lang.String) r11, (java.lang.String) r12, (java.lang.String) r13, (long) r14, (long) r16, (android.os.Bundle) r18)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzal r6 = r39.zzf()     // Catch:{ all -> 0x0a88 }
            java.lang.String r9 = r5.zzb     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzbb r6 = r6.zzd(r8, r9)     // Catch:{ all -> 0x0a88 }
            if (r6 != 0) goto L_0x05de
            com.google.android.gms.measurement.internal.zzal r6 = r39.zzf()     // Catch:{ all -> 0x0a88 }
            long r9 = r6.zzc(r8)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzag r6 = r39.zze()     // Catch:{ all -> 0x0a88 }
            int r6 = r6.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a88 }
            long r11 = (long) r6     // Catch:{ all -> 0x0a88 }
            int r6 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r6 < 0) goto L_0x05c2
            if (r4 == 0) goto L_0x05c2
            com.google.android.gms.measurement.internal.zzgo r2 = r39.zzj()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzg()     // Catch:{ all -> 0x0a88 }
            java.lang.String r3 = "Too many event names used, ignoring event. appId, name, supported count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzhy r6 = r1.zzm     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzgh r6 = r6.zzk()     // Catch:{ all -> 0x0a88 }
            java.lang.String r5 = r5.zzb     // Catch:{ all -> 0x0a88 }
            java.lang.String r5 = r6.zza((java.lang.String) r5)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzag r6 = r39.zze()     // Catch:{ all -> 0x0a88 }
            int r6 = r6.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a88 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0a88 }
            r2.zza(r3, r4, r5, r6)     // Catch:{ all -> 0x0a88 }
            r39.zzq()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzor r9 = r1.zzah     // Catch:{ all -> 0x0a88 }
            r11 = 8
            r12 = 0
            r13 = 0
            r14 = 0
            r10 = r8
            com.google.android.gms.measurement.internal.zzos.zza((com.google.android.gms.measurement.internal.zzor) r9, (java.lang.String) r10, (int) r11, (java.lang.String) r12, (java.lang.String) r13, (int) r14)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzal r2 = r39.zzf()
            r2.zzu()
            return
        L_0x05c2:
            com.google.android.gms.measurement.internal.zzbb r4 = new com.google.android.gms.measurement.internal.zzbb     // Catch:{ all -> 0x0a88 }
            java.lang.String r11 = r5.zzb     // Catch:{ all -> 0x0a88 }
            r12 = 0
            r14 = 0
            long r9 = r5.zzd     // Catch:{ all -> 0x0a88 }
            r18 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r16 = r9
            r9 = r4
            r10 = r8
            r9.<init>(r10, r11, r12, r14, r16, r18, r20, r21, r22, r23)     // Catch:{ all -> 0x0a88 }
            goto L_0x05ec
        L_0x05de:
            com.google.android.gms.measurement.internal.zzhy r4 = r1.zzm     // Catch:{ all -> 0x0a88 }
            long r8 = r6.zzf     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzbc r5 = r5.zza(r4, r8)     // Catch:{ all -> 0x0a88 }
            long r8 = r5.zzd     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzbb r4 = r6.zza(r8)     // Catch:{ all -> 0x0a88 }
        L_0x05ec:
            com.google.android.gms.measurement.internal.zzal r6 = r39.zzf()     // Catch:{ all -> 0x0a88 }
            r6.zza((com.google.android.gms.measurement.internal.zzbb) r4)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzhv r4 = r39.zzl()     // Catch:{ all -> 0x0a88 }
            r4.zzt()     // Catch:{ all -> 0x0a88 }
            r39.zzs()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r41)     // Catch:{ all -> 0x0a88 }
            java.lang.String r4 = r5.zza     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)     // Catch:{ all -> 0x0a88 }
            java.lang.String r4 = r5.zza     // Catch:{ all -> 0x0a88 }
            java.lang.String r6 = r3.zza     // Catch:{ all -> 0x0a88 }
            boolean r4 = r4.equals(r6)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.common.internal.Preconditions.checkArgument(r4)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r4 = com.google.android.gms.internal.measurement.zzfy.zzk.zzw()     // Catch:{ all -> 0x0a88 }
            r6 = 1
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r4 = r4.zzh((int) r6)     // Catch:{ all -> 0x0a88 }
            java.lang.String r8 = "android"
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r4 = r4.zzp(r8)     // Catch:{ all -> 0x0a88 }
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x0a88 }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0a88 }
            if (r8 != 0) goto L_0x062f
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x0a88 }
            r4.zzb((java.lang.String) r8)     // Catch:{ all -> 0x0a88 }
        L_0x062f:
            java.lang.String r8 = r3.zzd     // Catch:{ all -> 0x0a88 }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0a88 }
            if (r8 != 0) goto L_0x063c
            java.lang.String r8 = r3.zzd     // Catch:{ all -> 0x0a88 }
            r4.zzd((java.lang.String) r8)     // Catch:{ all -> 0x0a88 }
        L_0x063c:
            java.lang.String r8 = r3.zzc     // Catch:{ all -> 0x0a88 }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0a88 }
            if (r8 != 0) goto L_0x0649
            java.lang.String r8 = r3.zzc     // Catch:{ all -> 0x0a88 }
            r4.zze((java.lang.String) r8)     // Catch:{ all -> 0x0a88 }
        L_0x0649:
            java.lang.String r8 = r3.zzv     // Catch:{ all -> 0x0a88 }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0a88 }
            if (r8 != 0) goto L_0x0656
            java.lang.String r8 = r3.zzv     // Catch:{ all -> 0x0a88 }
            r4.zzr(r8)     // Catch:{ all -> 0x0a88 }
        L_0x0656:
            long r8 = r3.zzj     // Catch:{ all -> 0x0a88 }
            r10 = -2147483648(0xffffffff80000000, double:NaN)
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 == 0) goto L_0x0665
            long r8 = r3.zzj     // Catch:{ all -> 0x0a88 }
            int r8 = (int) r8     // Catch:{ all -> 0x0a88 }
            r4.zze((int) r8)     // Catch:{ all -> 0x0a88 }
        L_0x0665:
            long r8 = r3.zze     // Catch:{ all -> 0x0a88 }
            r4.zzf((long) r8)     // Catch:{ all -> 0x0a88 }
            java.lang.String r8 = r3.zzb     // Catch:{ all -> 0x0a88 }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0a88 }
            if (r8 != 0) goto L_0x0677
            java.lang.String r8 = r3.zzb     // Catch:{ all -> 0x0a88 }
            r4.zzm(r8)     // Catch:{ all -> 0x0a88 }
        L_0x0677:
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x0a88 }
            java.lang.Object r8 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r8)     // Catch:{ all -> 0x0a88 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzje r8 = r1.zzb((java.lang.String) r8)     // Catch:{ all -> 0x0a88 }
            java.lang.String r9 = r3.zzt     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzje r9 = com.google.android.gms.measurement.internal.zzje.zzb((java.lang.String) r9)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzje r8 = r8.zza((com.google.android.gms.measurement.internal.zzje) r9)     // Catch:{ all -> 0x0a88 }
            java.lang.String r9 = r8.zze()     // Catch:{ all -> 0x0a88 }
            r4.zzg((java.lang.String) r9)     // Catch:{ all -> 0x0a88 }
            java.lang.String r9 = r4.zzx()     // Catch:{ all -> 0x0a88 }
            boolean r9 = r9.isEmpty()     // Catch:{ all -> 0x0a88 }
            if (r9 == 0) goto L_0x06ab
            java.lang.String r9 = r3.zzp     // Catch:{ all -> 0x0a88 }
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x0a88 }
            if (r9 != 0) goto L_0x06ab
            java.lang.String r9 = r3.zzp     // Catch:{ all -> 0x0a88 }
            r4.zza((java.lang.String) r9)     // Catch:{ all -> 0x0a88 }
        L_0x06ab:
            boolean r9 = com.google.android.gms.internal.measurement.zzpn.zza()     // Catch:{ all -> 0x0a88 }
            if (r9 == 0) goto L_0x075d
            com.google.android.gms.measurement.internal.zzag r9 = r39.zze()     // Catch:{ all -> 0x0a88 }
            java.lang.String r10 = r3.zza     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r11 = com.google.android.gms.measurement.internal.zzbh.zzch     // Catch:{ all -> 0x0a88 }
            boolean r9 = r9.zze(r10, r11)     // Catch:{ all -> 0x0a88 }
            if (r9 == 0) goto L_0x075d
            r39.zzq()     // Catch:{ all -> 0x0a88 }
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x0a88 }
            boolean r9 = com.google.android.gms.measurement.internal.zzos.zzd(r9)     // Catch:{ all -> 0x0a88 }
            if (r9 == 0) goto L_0x075d
            int r9 = r3.zzaa     // Catch:{ all -> 0x0a88 }
            r4.zzd((int) r9)     // Catch:{ all -> 0x0a88 }
            long r9 = r3.zzab     // Catch:{ all -> 0x0a88 }
            boolean r8 = r8.zzg()     // Catch:{ all -> 0x0a88 }
            r11 = 32
            r13 = 0
            if (r8 != 0) goto L_0x06e5
            int r8 = (r9 > r13 ? 1 : (r9 == r13 ? 0 : -1))
            if (r8 == 0) goto L_0x06e5
            r15 = -2
            long r8 = r9 & r15
            long r9 = r8 | r11
        L_0x06e5:
            r15 = 1
            int r8 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r8 != 0) goto L_0x06ed
            r8 = r6
            goto L_0x06ee
        L_0x06ed:
            r8 = 0
        L_0x06ee:
            r4.zza((boolean) r8)     // Catch:{ all -> 0x0a88 }
            int r8 = (r9 > r13 ? 1 : (r9 == r13 ? 0 : -1))
            if (r8 == 0) goto L_0x075f
            com.google.android.gms.internal.measurement.zzfy$zzc$zza r8 = com.google.android.gms.internal.measurement.zzfy.zzc.zza()     // Catch:{ all -> 0x0a88 }
            long r17 = r9 & r15
            int r15 = (r17 > r13 ? 1 : (r17 == r13 ? 0 : -1))
            if (r15 == 0) goto L_0x0701
            r15 = r6
            goto L_0x0702
        L_0x0701:
            r15 = 0
        L_0x0702:
            r8.zzc(r15)     // Catch:{ all -> 0x0a88 }
            r15 = 2
            long r15 = r15 & r9
            int r15 = (r15 > r13 ? 1 : (r15 == r13 ? 0 : -1))
            if (r15 == 0) goto L_0x070e
            r15 = r6
            goto L_0x070f
        L_0x070e:
            r15 = 0
        L_0x070f:
            r8.zze(r15)     // Catch:{ all -> 0x0a88 }
            r15 = 4
            long r15 = r15 & r9
            int r15 = (r15 > r13 ? 1 : (r15 == r13 ? 0 : -1))
            if (r15 == 0) goto L_0x071b
            r15 = r6
            goto L_0x071c
        L_0x071b:
            r15 = 0
        L_0x071c:
            r8.zzf(r15)     // Catch:{ all -> 0x0a88 }
            r15 = 8
            long r15 = r15 & r9
            int r15 = (r15 > r13 ? 1 : (r15 == r13 ? 0 : -1))
            if (r15 == 0) goto L_0x0728
            r15 = r6
            goto L_0x0729
        L_0x0728:
            r15 = 0
        L_0x0729:
            r8.zzg(r15)     // Catch:{ all -> 0x0a88 }
            r15 = 16
            long r15 = r15 & r9
            int r15 = (r15 > r13 ? 1 : (r15 == r13 ? 0 : -1))
            if (r15 == 0) goto L_0x0735
            r15 = r6
            goto L_0x0736
        L_0x0735:
            r15 = 0
        L_0x0736:
            r8.zzb(r15)     // Catch:{ all -> 0x0a88 }
            long r11 = r11 & r9
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 == 0) goto L_0x0740
            r11 = r6
            goto L_0x0741
        L_0x0740:
            r11 = 0
        L_0x0741:
            r8.zza(r11)     // Catch:{ all -> 0x0a88 }
            r11 = 64
            long r9 = r9 & r11
            int r9 = (r9 > r13 ? 1 : (r9 == r13 ? 0 : -1))
            if (r9 == 0) goto L_0x074d
            r9 = r6
            goto L_0x074e
        L_0x074d:
            r9 = 0
        L_0x074e:
            r8.zzd(r9)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.internal.measurement.zzlc r8 = r8.zzai()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.internal.measurement.zzjt r8 = (com.google.android.gms.internal.measurement.zzjt) r8     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.internal.measurement.zzfy$zzc r8 = (com.google.android.gms.internal.measurement.zzfy.zzc) r8     // Catch:{ all -> 0x0a88 }
            r4.zza((com.google.android.gms.internal.measurement.zzfy.zzc) r8)     // Catch:{ all -> 0x0a88 }
            goto L_0x075f
        L_0x075d:
            r13 = 0
        L_0x075f:
            long r8 = r3.zzf     // Catch:{ all -> 0x0a88 }
            int r8 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
            if (r8 == 0) goto L_0x076a
            long r8 = r3.zzf     // Catch:{ all -> 0x0a88 }
            r4.zzc((long) r8)     // Catch:{ all -> 0x0a88 }
        L_0x076a:
            long r8 = r3.zzr     // Catch:{ all -> 0x0a88 }
            r4.zzd((long) r8)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzoo r8 = r39.zzp()     // Catch:{ all -> 0x0a88 }
            java.util.List r8 = r8.zzu()     // Catch:{ all -> 0x0a88 }
            if (r8 == 0) goto L_0x077c
            r4.zzc((java.lang.Iterable<? extends java.lang.Integer>) r8)     // Catch:{ all -> 0x0a88 }
        L_0x077c:
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x0a88 }
            java.lang.Object r8 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r8)     // Catch:{ all -> 0x0a88 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzje r8 = r1.zzb((java.lang.String) r8)     // Catch:{ all -> 0x0a88 }
            java.lang.String r9 = r3.zzt     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzje r9 = com.google.android.gms.measurement.internal.zzje.zzb((java.lang.String) r9)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzje r8 = r8.zza((com.google.android.gms.measurement.internal.zzje) r9)     // Catch:{ all -> 0x0a88 }
            boolean r9 = r8.zzg()     // Catch:{ all -> 0x0a88 }
            if (r9 == 0) goto L_0x085a
            boolean r9 = r3.zzn     // Catch:{ all -> 0x0a88 }
            if (r9 == 0) goto L_0x085a
            com.google.android.gms.measurement.internal.zzmw r9 = r1.zzj     // Catch:{ all -> 0x0a88 }
            java.lang.String r10 = r3.zza     // Catch:{ all -> 0x0a88 }
            android.util.Pair r9 = r9.zza((java.lang.String) r10, (com.google.android.gms.measurement.internal.zzje) r8)     // Catch:{ all -> 0x0a88 }
            if (r9 == 0) goto L_0x085a
            java.lang.Object r10 = r9.first     // Catch:{ all -> 0x0a88 }
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ all -> 0x0a88 }
            boolean r10 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x0a88 }
            if (r10 != 0) goto L_0x085a
            boolean r10 = r3.zzn     // Catch:{ all -> 0x0a88 }
            if (r10 == 0) goto L_0x085a
            java.lang.Object r10 = r9.first     // Catch:{ all -> 0x0a88 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x0a88 }
            r4.zzq(r10)     // Catch:{ all -> 0x0a88 }
            java.lang.Object r10 = r9.second     // Catch:{ all -> 0x0a88 }
            if (r10 == 0) goto L_0x07ca
            java.lang.Object r10 = r9.second     // Catch:{ all -> 0x0a88 }
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ all -> 0x0a88 }
            boolean r10 = r10.booleanValue()     // Catch:{ all -> 0x0a88 }
            r4.zzc((boolean) r10)     // Catch:{ all -> 0x0a88 }
        L_0x07ca:
            java.lang.String r10 = r5.zzb     // Catch:{ all -> 0x0a88 }
            r11 = r27
            boolean r10 = r10.equals(r11)     // Catch:{ all -> 0x0a88 }
            if (r10 != 0) goto L_0x085a
            java.lang.Object r9 = r9.first     // Catch:{ all -> 0x0a88 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x0a88 }
            java.lang.String r10 = "00000000-0000-0000-0000-000000000000"
            boolean r9 = r9.equals(r10)     // Catch:{ all -> 0x0a88 }
            if (r9 != 0) goto L_0x085a
            com.google.android.gms.measurement.internal.zzal r9 = r39.zzf()     // Catch:{ all -> 0x0a88 }
            java.lang.String r10 = r3.zza     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzg r9 = r9.zze(r10)     // Catch:{ all -> 0x0a88 }
            if (r9 == 0) goto L_0x085a
            boolean r10 = r9.zzau()     // Catch:{ all -> 0x0a88 }
            if (r10 == 0) goto L_0x085a
            java.lang.String r10 = r3.zza     // Catch:{ all -> 0x0a88 }
            r12 = 0
            r1.zza((java.lang.String) r10, (boolean) r12, (java.lang.Long) r7, (java.lang.Long) r7)     // Catch:{ all -> 0x0a88 }
            android.os.Bundle r10 = new android.os.Bundle     // Catch:{ all -> 0x0a88 }
            r10.<init>()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzag r12 = r39.zze()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r15 = com.google.android.gms.measurement.internal.zzbh.zzcw     // Catch:{ all -> 0x0a88 }
            boolean r12 = r12.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r15)     // Catch:{ all -> 0x0a88 }
            java.lang.String r15 = "_pfo"
            if (r12 == 0) goto L_0x082c
            java.lang.Long r12 = r9.zzy()     // Catch:{ all -> 0x0a88 }
            if (r12 == 0) goto L_0x081c
            long r6 = r12.longValue()     // Catch:{ all -> 0x0a88 }
            long r6 = java.lang.Math.max(r13, r6)     // Catch:{ all -> 0x0a88 }
            r10.putLong(r15, r6)     // Catch:{ all -> 0x0a88 }
        L_0x081c:
            java.lang.Long r6 = r9.zzz()     // Catch:{ all -> 0x0a88 }
            if (r6 == 0) goto L_0x084e
            java.lang.String r7 = "_uwa"
            long r13 = r6.longValue()     // Catch:{ all -> 0x0a88 }
            r10.putLong(r7, r13)     // Catch:{ all -> 0x0a88 }
            goto L_0x084e
        L_0x082c:
            com.google.android.gms.measurement.internal.zzag r6 = r39.zze()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbh.zzcv     // Catch:{ all -> 0x0a88 }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r7)     // Catch:{ all -> 0x0a88 }
            if (r6 == 0) goto L_0x084e
            com.google.android.gms.measurement.internal.zzal r6 = r39.zzf()     // Catch:{ all -> 0x0a88 }
            java.lang.String r7 = r3.zza     // Catch:{ all -> 0x0a88 }
            long r6 = r6.zzb(r7)     // Catch:{ all -> 0x0a88 }
            r12 = 1
            long r6 = r6 - r12
            r12 = 0
            long r6 = java.lang.Math.max(r12, r6)     // Catch:{ all -> 0x0a88 }
            r10.putLong(r15, r6)     // Catch:{ all -> 0x0a88 }
        L_0x084e:
            r6 = 1
            r10.putLong(r2, r6)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzor r6 = r1.zzah     // Catch:{ all -> 0x0a88 }
            java.lang.String r7 = r3.zza     // Catch:{ all -> 0x0a88 }
            r6.zza(r7, r11, r10)     // Catch:{ all -> 0x0a88 }
        L_0x085a:
            com.google.android.gms.measurement.internal.zzhy r6 = r1.zzm     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzaz r6 = r6.zzg()     // Catch:{ all -> 0x0a88 }
            r6.zzac()     // Catch:{ all -> 0x0a88 }
            java.lang.String r6 = android.os.Build.MODEL     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r6 = r4.zzi((java.lang.String) r6)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzhy r7 = r1.zzm     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzaz r7 = r7.zzg()     // Catch:{ all -> 0x0a88 }
            r7.zzac()     // Catch:{ all -> 0x0a88 }
            java.lang.String r7 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r6 = r6.zzo(r7)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzhy r7 = r1.zzm     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzaz r7 = r7.zzg()     // Catch:{ all -> 0x0a88 }
            long r9 = r7.zzc()     // Catch:{ all -> 0x0a88 }
            int r7 = (int) r9     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r6 = r6.zzj((int) r7)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzhy r7 = r1.zzm     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzaz r7 = r7.zzg()     // Catch:{ all -> 0x0a88 }
            java.lang.String r7 = r7.zzg()     // Catch:{ all -> 0x0a88 }
            r6.zzs(r7)     // Catch:{ all -> 0x0a88 }
            long r6 = r3.zzx     // Catch:{ all -> 0x0a88 }
            r4.zzj((long) r6)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzhy r6 = r1.zzm     // Catch:{ all -> 0x0a88 }
            boolean r6 = r6.zzac()     // Catch:{ all -> 0x0a88 }
            if (r6 == 0) goto L_0x08ae
            r4.zzt()     // Catch:{ all -> 0x0a88 }
            r6 = 0
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x0a88 }
            if (r7 != 0) goto L_0x08ae
            r4.zzj((java.lang.String) r6)     // Catch:{ all -> 0x0a88 }
        L_0x08ae:
            com.google.android.gms.measurement.internal.zzal r6 = r39.zzf()     // Catch:{ all -> 0x0a88 }
            java.lang.String r7 = r3.zza     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzg r6 = r6.zze(r7)     // Catch:{ all -> 0x0a88 }
            if (r6 != 0) goto L_0x091e
            com.google.android.gms.measurement.internal.zzg r6 = new com.google.android.gms.measurement.internal.zzg     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzhy r7 = r1.zzm     // Catch:{ all -> 0x0a88 }
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x0a88 }
            r6.<init>(r7, r9)     // Catch:{ all -> 0x0a88 }
            java.lang.String r7 = r1.zza((com.google.android.gms.measurement.internal.zzje) r8)     // Catch:{ all -> 0x0a88 }
            r6.zzb((java.lang.String) r7)     // Catch:{ all -> 0x0a88 }
            java.lang.String r7 = r3.zzk     // Catch:{ all -> 0x0a88 }
            r6.zze((java.lang.String) r7)     // Catch:{ all -> 0x0a88 }
            java.lang.String r7 = r3.zzb     // Catch:{ all -> 0x0a88 }
            r6.zzf((java.lang.String) r7)     // Catch:{ all -> 0x0a88 }
            boolean r7 = r8.zzg()     // Catch:{ all -> 0x0a88 }
            if (r7 == 0) goto L_0x08e7
            com.google.android.gms.measurement.internal.zzmw r7 = r1.zzj     // Catch:{ all -> 0x0a88 }
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x0a88 }
            boolean r10 = r3.zzn     // Catch:{ all -> 0x0a88 }
            java.lang.String r7 = r7.zza((java.lang.String) r9, (boolean) r10)     // Catch:{ all -> 0x0a88 }
            r6.zzh((java.lang.String) r7)     // Catch:{ all -> 0x0a88 }
        L_0x08e7:
            r9 = 0
            r6.zzq(r9)     // Catch:{ all -> 0x0a88 }
            r6.zzr(r9)     // Catch:{ all -> 0x0a88 }
            r6.zzp(r9)     // Catch:{ all -> 0x0a88 }
            java.lang.String r7 = r3.zzc     // Catch:{ all -> 0x0a88 }
            r6.zzd((java.lang.String) r7)     // Catch:{ all -> 0x0a88 }
            long r9 = r3.zzj     // Catch:{ all -> 0x0a88 }
            r6.zzb((long) r9)     // Catch:{ all -> 0x0a88 }
            java.lang.String r7 = r3.zzd     // Catch:{ all -> 0x0a88 }
            r6.zzc((java.lang.String) r7)     // Catch:{ all -> 0x0a88 }
            long r9 = r3.zze     // Catch:{ all -> 0x0a88 }
            r6.zzn(r9)     // Catch:{ all -> 0x0a88 }
            long r9 = r3.zzf     // Catch:{ all -> 0x0a88 }
            r6.zzk((long) r9)     // Catch:{ all -> 0x0a88 }
            boolean r7 = r3.zzh     // Catch:{ all -> 0x0a88 }
            r6.zzb((boolean) r7)     // Catch:{ all -> 0x0a88 }
            long r9 = r3.zzr     // Catch:{ all -> 0x0a88 }
            r6.zzl(r9)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzal r7 = r39.zzf()     // Catch:{ all -> 0x0a88 }
            r9 = 0
            r7.zza((com.google.android.gms.measurement.internal.zzg) r6, (boolean) r9, (boolean) r9)     // Catch:{ all -> 0x0a88 }
            goto L_0x091f
        L_0x091e:
            r9 = 0
        L_0x091f:
            boolean r7 = r8.zzh()     // Catch:{ all -> 0x0a88 }
            if (r7 == 0) goto L_0x093c
            java.lang.String r7 = r6.zzad()     // Catch:{ all -> 0x0a88 }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x0a88 }
            if (r7 != 0) goto L_0x093c
            java.lang.String r7 = r6.zzad()     // Catch:{ all -> 0x0a88 }
            java.lang.Object r7 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r7)     // Catch:{ all -> 0x0a88 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x0a88 }
            r4.zzc((java.lang.String) r7)     // Catch:{ all -> 0x0a88 }
        L_0x093c:
            java.lang.String r7 = r6.zzag()     // Catch:{ all -> 0x0a88 }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x0a88 }
            if (r7 != 0) goto L_0x0953
            java.lang.String r7 = r6.zzag()     // Catch:{ all -> 0x0a88 }
            java.lang.Object r7 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r7)     // Catch:{ all -> 0x0a88 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x0a88 }
            r4.zzl((java.lang.String) r7)     // Catch:{ all -> 0x0a88 }
        L_0x0953:
            com.google.android.gms.measurement.internal.zzal r7 = r39.zzf()     // Catch:{ all -> 0x0a88 }
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x0a88 }
            java.util.List r7 = r7.zzl(r8)     // Catch:{ all -> 0x0a88 }
            r12 = r9
        L_0x095e:
            int r8 = r7.size()     // Catch:{ all -> 0x0a88 }
            if (r12 >= r8) goto L_0x09c4
            com.google.android.gms.internal.measurement.zzfy$zzo$zza r8 = com.google.android.gms.internal.measurement.zzfy.zzo.zze()     // Catch:{ all -> 0x0a88 }
            java.lang.Object r10 = r7.get(r12)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzop r10 = (com.google.android.gms.measurement.internal.zzop) r10     // Catch:{ all -> 0x0a88 }
            java.lang.String r10 = r10.zzc     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.internal.measurement.zzfy$zzo$zza r8 = r8.zza((java.lang.String) r10)     // Catch:{ all -> 0x0a88 }
            java.lang.Object r10 = r7.get(r12)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzop r10 = (com.google.android.gms.measurement.internal.zzop) r10     // Catch:{ all -> 0x0a88 }
            long r10 = r10.zzd     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.internal.measurement.zzfy$zzo$zza r8 = r8.zzb((long) r10)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzoo r10 = r39.zzp()     // Catch:{ all -> 0x0a88 }
            java.lang.Object r11 = r7.get(r12)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzop r11 = (com.google.android.gms.measurement.internal.zzop) r11     // Catch:{ all -> 0x0a88 }
            java.lang.Object r11 = r11.zze     // Catch:{ all -> 0x0a88 }
            r10.zza((com.google.android.gms.internal.measurement.zzfy.zzo.zza) r8, (java.lang.Object) r11)     // Catch:{ all -> 0x0a88 }
            r4.zza((com.google.android.gms.internal.measurement.zzfy.zzo.zza) r8)     // Catch:{ all -> 0x0a88 }
            java.lang.String r8 = "_sid"
            java.lang.Object r10 = r7.get(r12)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzop r10 = (com.google.android.gms.measurement.internal.zzop) r10     // Catch:{ all -> 0x0a88 }
            java.lang.String r10 = r10.zzc     // Catch:{ all -> 0x0a88 }
            boolean r8 = r8.equals(r10)     // Catch:{ all -> 0x0a88 }
            if (r8 == 0) goto L_0x09c1
            long r10 = r6.zzv()     // Catch:{ all -> 0x0a88 }
            r13 = 0
            int r8 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r8 == 0) goto L_0x09c1
            com.google.android.gms.measurement.internal.zzoo r8 = r39.zzp()     // Catch:{ all -> 0x0a88 }
            java.lang.String r10 = r3.zzv     // Catch:{ all -> 0x0a88 }
            long r10 = r8.zza((java.lang.String) r10)     // Catch:{ all -> 0x0a88 }
            long r13 = r6.zzv()     // Catch:{ all -> 0x0a88 }
            int r8 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r8 == 0) goto L_0x09c1
            r4.zzr()     // Catch:{ all -> 0x0a88 }
        L_0x09c1:
            int r12 = r12 + 1
            goto L_0x095e
        L_0x09c4:
            com.google.android.gms.measurement.internal.zzal r3 = r39.zzf()     // Catch:{ IOException -> 0x0a40 }
            com.google.android.gms.internal.measurement.zzlc r6 = r4.zzai()     // Catch:{ IOException -> 0x0a40 }
            com.google.android.gms.internal.measurement.zzjt r6 = (com.google.android.gms.internal.measurement.zzjt) r6     // Catch:{ IOException -> 0x0a40 }
            com.google.android.gms.internal.measurement.zzfy$zzk r6 = (com.google.android.gms.internal.measurement.zzfy.zzk) r6     // Catch:{ IOException -> 0x0a40 }
            long r3 = r3.zza((com.google.android.gms.internal.measurement.zzfy.zzk) r6)     // Catch:{ IOException -> 0x0a40 }
            com.google.android.gms.measurement.internal.zzal r6 = r39.zzf()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzbe r7 = r5.zzf     // Catch:{ all -> 0x0a88 }
            if (r7 == 0) goto L_0x0a34
            com.google.android.gms.measurement.internal.zzbe r7 = r5.zzf     // Catch:{ all -> 0x0a88 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x0a88 }
        L_0x09e2:
            boolean r8 = r7.hasNext()     // Catch:{ all -> 0x0a88 }
            if (r8 == 0) goto L_0x09f6
            java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x0a88 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x0a88 }
            boolean r8 = r2.equals(r8)     // Catch:{ all -> 0x0a88 }
            if (r8 == 0) goto L_0x09e2
        L_0x09f4:
            r13 = 1
            goto L_0x0a35
        L_0x09f6:
            com.google.android.gms.measurement.internal.zzhl r2 = r39.zzi()     // Catch:{ all -> 0x0a88 }
            java.lang.String r7 = r5.zza     // Catch:{ all -> 0x0a88 }
            java.lang.String r8 = r5.zzb     // Catch:{ all -> 0x0a88 }
            boolean r2 = r2.zzc((java.lang.String) r7, (java.lang.String) r8)     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzal r28 = r39.zzf()     // Catch:{ all -> 0x0a88 }
            long r29 = r39.zzx()     // Catch:{ all -> 0x0a88 }
            java.lang.String r7 = r5.zza     // Catch:{ all -> 0x0a88 }
            r32 = 0
            r33 = 0
            r34 = 0
            r35 = 0
            r36 = 0
            r37 = 0
            r38 = 0
            r31 = r7
            com.google.android.gms.measurement.internal.zzaq r7 = r28.zza(r29, r31, r32, r33, r34, r35, r36, r37, r38)     // Catch:{ all -> 0x0a88 }
            if (r2 == 0) goto L_0x0a34
            long r7 = r7.zze     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzag r2 = r39.zze()     // Catch:{ all -> 0x0a88 }
            java.lang.String r10 = r5.zza     // Catch:{ all -> 0x0a88 }
            int r2 = r2.zzc(r10)     // Catch:{ all -> 0x0a88 }
            long r10 = (long) r2     // Catch:{ all -> 0x0a88 }
            int r2 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x0a34
            goto L_0x09f4
        L_0x0a34:
            r13 = r9
        L_0x0a35:
            boolean r2 = r6.zza((com.google.android.gms.measurement.internal.zzbc) r5, (long) r3, (boolean) r13)     // Catch:{ all -> 0x0a88 }
            if (r2 == 0) goto L_0x0a57
            r2 = 0
            r1.zzp = r2     // Catch:{ all -> 0x0a88 }
            goto L_0x0a57
        L_0x0a40:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.measurement.internal.zzgo r3 = r39.zzj()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzg()     // Catch:{ all -> 0x0a88 }
            java.lang.String r5 = "Data loss. Failed to insert raw event metadata. appId"
            java.lang.String r4 = r4.zzt()     // Catch:{ all -> 0x0a88 }
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r4)     // Catch:{ all -> 0x0a88 }
            r3.zza(r5, r4, r2)     // Catch:{ all -> 0x0a88 }
        L_0x0a57:
            com.google.android.gms.measurement.internal.zzal r2 = r39.zzf()     // Catch:{ all -> 0x0a88 }
            r2.zzw()     // Catch:{ all -> 0x0a88 }
            com.google.android.gms.measurement.internal.zzal r2 = r39.zzf()
            r2.zzu()
            r39.zzac()
            com.google.android.gms.measurement.internal.zzgo r2 = r39.zzj()
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzp()
            long r3 = java.lang.System.nanoTime()
            long r3 = r3 - r24
            r5 = 500000(0x7a120, double:2.47033E-318)
            long r3 = r3 + r5
            r5 = 1000000(0xf4240, double:4.940656E-318)
            long r3 = r3 / r5
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            java.lang.String r4 = "Background event processing time, ms"
            r2.zza(r4, r3)
            return
        L_0x0a88:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.measurement.internal.zzal r3 = r39.zzf()
            r3.zzu()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zzc(com.google.android.gms.measurement.internal.zzbf, com.google.android.gms.measurement.internal.zzo):void");
    }

    private static boolean zzi(zzo zzo2) {
        return !TextUtils.isEmpty(zzo2.zzb) || !TextUtils.isEmpty(zzo2.zzp);
    }

    /* JADX WARNING: Removed duplicated region for block: B:108:0x0242 A[SYNTHETIC, Splitter:B:108:0x0242] */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0249 A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0257 A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x03d6 A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x03d8 A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x03db A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x03dc A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x03fa A[SYNTHETIC, Splitter:B:175:0x03fa] */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x0497 A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x04f9 A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x04fd A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:202:0x0563 A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:213:0x0596 A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x05b5 A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x0699 A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:266:0x06ed A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:280:0x074d A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x086f A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:501:0x0e77 A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:502:0x0e8a A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:504:0x0e8d A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:505:0x0eb2 A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:585:0x111e A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:586:0x1122 A[Catch:{ IOException -> 0x0205, all -> 0x1240 }] */
    /* JADX WARNING: Removed duplicated region for block: B:621:0x1228 A[SYNTHETIC, Splitter:B:621:0x1228] */
    /* JADX WARNING: Removed duplicated region for block: B:628:0x123c A[SYNTHETIC, Splitter:B:628:0x123c] */
    /* JADX WARNING: Removed duplicated region for block: B:644:0x045d A[EDGE_INSN: B:644:0x045d->B:183:0x045d ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zza(java.lang.String r44, long r45) {
        /*
            r43 = this;
            r1 = r43
            java.lang.String r2 = "_ai"
            java.lang.String r3 = "items"
            com.google.android.gms.measurement.internal.zzal r4 = r43.zzf()
            r4.zzp()
            com.google.android.gms.measurement.internal.zznv$zza r4 = new com.google.android.gms.measurement.internal.zznv$zza     // Catch:{ all -> 0x1240 }
            r5 = 0
            r4.<init>()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzal r6 = r43.zzf()     // Catch:{ all -> 0x1240 }
            long r7 = r1.zzab     // Catch:{ all -> 0x1240 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)     // Catch:{ all -> 0x1240 }
            r6.zzt()     // Catch:{ all -> 0x1240 }
            r6.zzal()     // Catch:{ all -> 0x1240 }
            r10 = -1
            r12 = 1
            r13 = 0
            android.database.sqlite.SQLiteDatabase r15 = r6.e_()     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            boolean r14 = android.text.TextUtils.isEmpty(r44)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            java.lang.String r16 = ""
            r5 = 2
            if (r14 == 0) goto L_0x0086
            int r14 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r14 == 0) goto L_0x0046
            java.lang.String[] r9 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            java.lang.String r17 = java.lang.String.valueOf(r7)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            r9[r13] = r17     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            java.lang.String r17 = java.lang.String.valueOf(r45)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            r9[r12] = r17     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            goto L_0x004e
        L_0x0046:
            java.lang.String[] r9 = new java.lang.String[r12]     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            java.lang.String r17 = java.lang.String.valueOf(r45)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            r9[r13] = r17     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
        L_0x004e:
            if (r14 == 0) goto L_0x0052
            java.lang.String r16 = "rowid <= ? and "
        L_0x0052:
            r14 = r16
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            java.lang.String r10 = "select app_id, metadata_fingerprint from raw_events where "
            r5.<init>(r10)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            java.lang.StringBuilder r5 = r5.append(r14)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            java.lang.String r10 = "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;"
            java.lang.StringBuilder r5 = r5.append(r10)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            java.lang.String r5 = r5.toString()     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            android.database.Cursor r5 = r15.rawQuery(r5, r9)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            boolean r9 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x0220 }
            if (r9 != 0) goto L_0x007a
            if (r5 == 0) goto L_0x0245
            r5.close()     // Catch:{ all -> 0x1240 }
            goto L_0x0245
        L_0x007a:
            java.lang.String r9 = r5.getString(r13)     // Catch:{ SQLiteException -> 0x0220 }
            java.lang.String r10 = r5.getString(r12)     // Catch:{ SQLiteException -> 0x021e }
            r5.close()     // Catch:{ SQLiteException -> 0x021e }
            goto L_0x00d0
        L_0x0086:
            r9 = r10
            int r5 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r5 == 0) goto L_0x0097
            r9 = 2
            java.lang.String[] r10 = new java.lang.String[r9]     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            r10[r13] = r44     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            java.lang.String r9 = java.lang.String.valueOf(r7)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            r10[r12] = r9     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            goto L_0x009b
        L_0x0097:
            java.lang.String[] r10 = new java.lang.String[]{r44}     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
        L_0x009b:
            if (r5 == 0) goto L_0x009f
            java.lang.String r16 = " and rowid <= ?"
        L_0x009f:
            r5 = r16
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            java.lang.String r11 = "select metadata_fingerprint from raw_events where app_id = ?"
            r9.<init>(r11)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            java.lang.StringBuilder r5 = r9.append(r5)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            java.lang.String r9 = " order by rowid limit 1;"
            java.lang.StringBuilder r5 = r5.append(r9)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            java.lang.String r5 = r5.toString()     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            android.database.Cursor r5 = r15.rawQuery(r5, r10)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0225 }
            boolean r9 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x0220 }
            if (r9 != 0) goto L_0x00c7
            if (r5 == 0) goto L_0x0245
            r5.close()     // Catch:{ all -> 0x1240 }
            goto L_0x0245
        L_0x00c7:
            java.lang.String r10 = r5.getString(r13)     // Catch:{ SQLiteException -> 0x0220 }
            r5.close()     // Catch:{ SQLiteException -> 0x0220 }
            r9 = r44
        L_0x00d0:
            java.lang.String r11 = "raw_events_metadata"
            java.lang.String[] r14 = new java.lang.String[r12]     // Catch:{ SQLiteException -> 0x021e }
            java.lang.String r16 = "metadata"
            r14[r13] = r16     // Catch:{ SQLiteException -> 0x021e }
            java.lang.String r17 = "app_id = ? and metadata_fingerprint = ?"
            java.lang.String[] r18 = new java.lang.String[]{r9, r10}     // Catch:{ SQLiteException -> 0x021e }
            r19 = 0
            r20 = 0
            java.lang.String r21 = "rowid"
            java.lang.String r22 = "2"
            r16 = r14
            r14 = r15
            r25 = r15
            r15 = r11
            android.database.Cursor r5 = r14.query(r15, r16, r17, r18, r19, r20, r21, r22)     // Catch:{ SQLiteException -> 0x021e }
            boolean r11 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x021e }
            if (r11 != 0) goto L_0x010e
            com.google.android.gms.measurement.internal.zzgo r7 = r6.zzj()     // Catch:{ SQLiteException -> 0x021e }
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzg()     // Catch:{ SQLiteException -> 0x021e }
            java.lang.String r8 = "Raw event metadata record is missing. appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r9)     // Catch:{ SQLiteException -> 0x021e }
            r7.zza(r8, r10)     // Catch:{ SQLiteException -> 0x021e }
            if (r5 == 0) goto L_0x0245
            r5.close()     // Catch:{ all -> 0x1240 }
            goto L_0x0245
        L_0x010e:
            byte[] r11 = r5.getBlob(r13)     // Catch:{ SQLiteException -> 0x021e }
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r14 = com.google.android.gms.internal.measurement.zzfy.zzk.zzw()     // Catch:{ IOException -> 0x0205 }
            com.google.android.gms.internal.measurement.zzlb r11 = com.google.android.gms.measurement.internal.zzoo.zza(r14, (byte[]) r11)     // Catch:{ IOException -> 0x0205 }
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r11 = (com.google.android.gms.internal.measurement.zzfy.zzk.zza) r11     // Catch:{ IOException -> 0x0205 }
            com.google.android.gms.internal.measurement.zzlc r11 = r11.zzai()     // Catch:{ IOException -> 0x0205 }
            com.google.android.gms.internal.measurement.zzjt r11 = (com.google.android.gms.internal.measurement.zzjt) r11     // Catch:{ IOException -> 0x0205 }
            com.google.android.gms.internal.measurement.zzfy$zzk r11 = (com.google.android.gms.internal.measurement.zzfy.zzk) r11     // Catch:{ IOException -> 0x0205 }
            boolean r14 = r5.moveToNext()     // Catch:{ SQLiteException -> 0x021e }
            if (r14 == 0) goto L_0x013b
            com.google.android.gms.measurement.internal.zzgo r14 = r6.zzj()     // Catch:{ SQLiteException -> 0x021e }
            com.google.android.gms.measurement.internal.zzgq r14 = r14.zzu()     // Catch:{ SQLiteException -> 0x021e }
            java.lang.String r15 = "Get multiple raw event metadata records, expected one. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r9)     // Catch:{ SQLiteException -> 0x021e }
            r14.zza(r15, r12)     // Catch:{ SQLiteException -> 0x021e }
        L_0x013b:
            r5.close()     // Catch:{ SQLiteException -> 0x021e }
            r4.zza(r11)     // Catch:{ SQLiteException -> 0x021e }
            r11 = -1
            int r14 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r14 == 0) goto L_0x015d
            java.lang.String r11 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?"
            r12 = 3
            java.lang.String[] r14 = new java.lang.String[r12]     // Catch:{ SQLiteException -> 0x021e }
            r14[r13] = r9     // Catch:{ SQLiteException -> 0x021e }
            r12 = 1
            r14[r12] = r10     // Catch:{ SQLiteException -> 0x021e }
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ SQLiteException -> 0x021e }
            r8 = 2
            r14[r8] = r7     // Catch:{ SQLiteException -> 0x021e }
            r17 = r11
            r18 = r14
            goto L_0x0167
        L_0x015d:
            java.lang.String r7 = "app_id = ? and metadata_fingerprint = ?"
            java.lang.String[] r8 = new java.lang.String[]{r9, r10}     // Catch:{ SQLiteException -> 0x021e }
            r17 = r7
            r18 = r8
        L_0x0167:
            java.lang.String r15 = "raw_events"
            r7 = 4
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch:{ SQLiteException -> 0x021e }
            java.lang.String r8 = "rowid"
            r7[r13] = r8     // Catch:{ SQLiteException -> 0x021e }
            java.lang.String r8 = "name"
            r10 = 1
            r7[r10] = r8     // Catch:{ SQLiteException -> 0x021e }
            java.lang.String r8 = "timestamp"
            r10 = 2
            r7[r10] = r8     // Catch:{ SQLiteException -> 0x021e }
            java.lang.String r8 = "data"
            r10 = 3
            r7[r10] = r8     // Catch:{ SQLiteException -> 0x021e }
            r19 = 0
            r20 = 0
            java.lang.String r21 = "rowid"
            r22 = 0
            r14 = r25
            r16 = r7
            android.database.Cursor r5 = r14.query(r15, r16, r17, r18, r19, r20, r21, r22)     // Catch:{ SQLiteException -> 0x021e }
            boolean r7 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x021e }
            if (r7 != 0) goto L_0x01ad
            com.google.android.gms.measurement.internal.zzgo r7 = r6.zzj()     // Catch:{ SQLiteException -> 0x021e }
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzu()     // Catch:{ SQLiteException -> 0x021e }
            java.lang.String r8 = "Raw event data disappeared while in transaction. appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r9)     // Catch:{ SQLiteException -> 0x021e }
            r7.zza(r8, r10)     // Catch:{ SQLiteException -> 0x021e }
            if (r5 == 0) goto L_0x0245
            r5.close()     // Catch:{ all -> 0x1240 }
            goto L_0x0245
        L_0x01ad:
            long r7 = r5.getLong(r13)     // Catch:{ SQLiteException -> 0x021e }
            r10 = 3
            byte[] r11 = r5.getBlob(r10)     // Catch:{ SQLiteException -> 0x021e }
            com.google.android.gms.internal.measurement.zzfy$zzf$zza r10 = com.google.android.gms.internal.measurement.zzfy.zzf.zze()     // Catch:{ IOException -> 0x01e5 }
            com.google.android.gms.internal.measurement.zzlb r10 = com.google.android.gms.measurement.internal.zzoo.zza(r10, (byte[]) r11)     // Catch:{ IOException -> 0x01e5 }
            com.google.android.gms.internal.measurement.zzfy$zzf$zza r10 = (com.google.android.gms.internal.measurement.zzfy.zzf.zza) r10     // Catch:{ IOException -> 0x01e5 }
            r11 = 1
            java.lang.String r12 = r5.getString(r11)     // Catch:{ SQLiteException -> 0x021e }
            com.google.android.gms.internal.measurement.zzfy$zzf$zza r11 = r10.zza((java.lang.String) r12)     // Catch:{ SQLiteException -> 0x021e }
            r12 = 2
            long r14 = r5.getLong(r12)     // Catch:{ SQLiteException -> 0x021e }
            r11.zzb((long) r14)     // Catch:{ SQLiteException -> 0x021e }
            com.google.android.gms.internal.measurement.zzlc r10 = r10.zzai()     // Catch:{ SQLiteException -> 0x021e }
            com.google.android.gms.internal.measurement.zzjt r10 = (com.google.android.gms.internal.measurement.zzjt) r10     // Catch:{ SQLiteException -> 0x021e }
            com.google.android.gms.internal.measurement.zzfy$zzf r10 = (com.google.android.gms.internal.measurement.zzfy.zzf) r10     // Catch:{ SQLiteException -> 0x021e }
            boolean r7 = r4.zza(r7, r10)     // Catch:{ SQLiteException -> 0x021e }
            if (r7 != 0) goto L_0x01f9
            if (r5 == 0) goto L_0x0245
            r5.close()     // Catch:{ all -> 0x1240 }
            goto L_0x0245
        L_0x01e5:
            r0 = move-exception
            r12 = 2
            r7 = r0
            com.google.android.gms.measurement.internal.zzgo r8 = r6.zzj()     // Catch:{ SQLiteException -> 0x021e }
            com.google.android.gms.measurement.internal.zzgq r8 = r8.zzg()     // Catch:{ SQLiteException -> 0x021e }
            java.lang.String r10 = "Data loss. Failed to merge raw event. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r9)     // Catch:{ SQLiteException -> 0x021e }
            r8.zza(r10, r11, r7)     // Catch:{ SQLiteException -> 0x021e }
        L_0x01f9:
            boolean r7 = r5.moveToNext()     // Catch:{ SQLiteException -> 0x021e }
            if (r7 != 0) goto L_0x01ad
            if (r5 == 0) goto L_0x0245
            r5.close()     // Catch:{ all -> 0x1240 }
            goto L_0x0245
        L_0x0205:
            r0 = move-exception
            r7 = r0
            com.google.android.gms.measurement.internal.zzgo r8 = r6.zzj()     // Catch:{ SQLiteException -> 0x021e }
            com.google.android.gms.measurement.internal.zzgq r8 = r8.zzg()     // Catch:{ SQLiteException -> 0x021e }
            java.lang.String r10 = "Data loss. Failed to merge raw event metadata. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r9)     // Catch:{ SQLiteException -> 0x021e }
            r8.zza(r10, r11, r7)     // Catch:{ SQLiteException -> 0x021e }
            if (r5 == 0) goto L_0x0245
            r5.close()     // Catch:{ all -> 0x1240 }
            goto L_0x0245
        L_0x021e:
            r0 = move-exception
            goto L_0x0223
        L_0x0220:
            r0 = move-exception
            r9 = r44
        L_0x0223:
            r7 = r0
            goto L_0x022f
        L_0x0225:
            r0 = move-exception
            r1 = r0
            r5 = 0
            goto L_0x123a
        L_0x022a:
            r0 = move-exception
            r9 = r44
            r7 = r0
            r5 = 0
        L_0x022f:
            com.google.android.gms.measurement.internal.zzgo r6 = r6.zzj()     // Catch:{ all -> 0x1238 }
            com.google.android.gms.measurement.internal.zzgq r6 = r6.zzg()     // Catch:{ all -> 0x1238 }
            java.lang.String r8 = "Data loss. Error selecting raw event. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r9)     // Catch:{ all -> 0x1238 }
            r6.zza(r8, r9, r7)     // Catch:{ all -> 0x1238 }
            if (r5 == 0) goto L_0x0245
            r5.close()     // Catch:{ all -> 0x1240 }
        L_0x0245:
            java.util.List<com.google.android.gms.internal.measurement.zzfy$zzf> r5 = r4.zzc     // Catch:{ all -> 0x1240 }
            if (r5 == 0) goto L_0x0254
            java.util.List<com.google.android.gms.internal.measurement.zzfy$zzf> r5 = r4.zzc     // Catch:{ all -> 0x1240 }
            boolean r5 = r5.isEmpty()     // Catch:{ all -> 0x1240 }
            if (r5 == 0) goto L_0x0252
            goto L_0x0254
        L_0x0252:
            r5 = r13
            goto L_0x0255
        L_0x0254:
            r5 = 1
        L_0x0255:
            if (r5 != 0) goto L_0x1228
            com.google.android.gms.internal.measurement.zzfy$zzk r5 = r4.zza     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt$zzb r5 = r5.zzcd()     // Catch:{ all -> 0x1240 }
            r6 = r5
            com.google.android.gms.internal.measurement.zzjt$zzb r6 = (com.google.android.gms.internal.measurement.zzjt.zzb) r6     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r5 = (com.google.android.gms.internal.measurement.zzfy.zzk.zza) r5     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r5 = r5.zzl()     // Catch:{ all -> 0x1240 }
            r9 = r13
            r10 = r9
            r11 = r10
            r7 = 0
            r8 = 0
            r12 = -1
            r14 = -1
        L_0x026d:
            java.util.List<com.google.android.gms.internal.measurement.zzfy$zzf> r15 = r4.zzc     // Catch:{ all -> 0x1240 }
            int r15 = r15.size()     // Catch:{ all -> 0x1240 }
            java.lang.String r13 = "_fr"
            java.lang.String r6 = "_et"
            r17 = r10
            java.lang.String r10 = "_e"
            r45 = r11
            java.lang.String r11 = "_c"
            r46 = r5
            r18 = r6
            if (r9 >= r15) goto L_0x0898
            java.util.List<com.google.android.gms.internal.measurement.zzfy$zzf> r15 = r4.zzc     // Catch:{ all -> 0x1240 }
            java.lang.Object r15 = r15.get(r9)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzf r15 = (com.google.android.gms.internal.measurement.zzfy.zzf) r15     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt$zzb r15 = r15.zzcd()     // Catch:{ all -> 0x1240 }
            r19 = r15
            com.google.android.gms.internal.measurement.zzjt$zzb r19 = (com.google.android.gms.internal.measurement.zzjt.zzb) r19     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzf$zza r15 = (com.google.android.gms.internal.measurement.zzfy.zzf.zza) r15     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzhl r5 = r43.zzi()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r6 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r6 = r6.zzz()     // Catch:{ all -> 0x1240 }
            r21 = r9
            java.lang.String r9 = r15.zze()     // Catch:{ all -> 0x1240 }
            boolean r5 = r5.zzd(r6, r9)     // Catch:{ all -> 0x1240 }
            java.lang.String r6 = "_err"
            if (r5 == 0) goto L_0x032b
            com.google.android.gms.measurement.internal.zzgo r5 = r43.zzj()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgq r5 = r5.zzu()     // Catch:{ all -> 0x1240 }
            java.lang.String r9 = "Dropping blocked raw event. appId"
            com.google.android.gms.internal.measurement.zzfy$zzk r10 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r10 = r10.zzz()     // Catch:{ all -> 0x1240 }
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r10)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzhy r11 = r1.zzm     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgh r11 = r11.zzk()     // Catch:{ all -> 0x1240 }
            java.lang.String r13 = r15.zze()     // Catch:{ all -> 0x1240 }
            java.lang.String r11 = r11.zza((java.lang.String) r13)     // Catch:{ all -> 0x1240 }
            r5.zza(r9, r10, r11)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzhl r5 = r43.zzi()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r9 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r9 = r9.zzz()     // Catch:{ all -> 0x1240 }
            boolean r5 = r5.zzl(r9)     // Catch:{ all -> 0x1240 }
            if (r5 != 0) goto L_0x02f7
            com.google.android.gms.measurement.internal.zzhl r5 = r43.zzi()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r9 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r9 = r9.zzz()     // Catch:{ all -> 0x1240 }
            boolean r5 = r5.zzn(r9)     // Catch:{ all -> 0x1240 }
            if (r5 == 0) goto L_0x02f5
            goto L_0x02f7
        L_0x02f5:
            r5 = 0
            goto L_0x02f8
        L_0x02f7:
            r5 = 1
        L_0x02f8:
            if (r5 != 0) goto L_0x031e
            java.lang.String r5 = r15.zze()     // Catch:{ all -> 0x1240 }
            boolean r5 = r6.equals(r5)     // Catch:{ all -> 0x1240 }
            if (r5 != 0) goto L_0x031e
            r43.zzq()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzor r5 = r1.zzah     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r6 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r27 = r6.zzz()     // Catch:{ all -> 0x1240 }
            r28 = 11
            java.lang.String r29 = "_ev"
            java.lang.String r30 = r15.zze()     // Catch:{ all -> 0x1240 }
            r31 = 0
            r26 = r5
            com.google.android.gms.measurement.internal.zzos.zza((com.google.android.gms.measurement.internal.zzor) r26, (java.lang.String) r27, (int) r28, (java.lang.String) r29, (java.lang.String) r30, (int) r31)     // Catch:{ all -> 0x1240 }
        L_0x031e:
            r11 = r45
            r6 = r46
            r24 = r2
            r13 = r3
            r10 = r17
            r5 = r21
            goto L_0x088f
        L_0x032b:
            java.lang.String r5 = r15.zze()     // Catch:{ all -> 0x1240 }
            java.lang.String r9 = com.google.android.gms.measurement.internal.zzji.zza(r2)     // Catch:{ all -> 0x1240 }
            boolean r5 = r5.equals(r9)     // Catch:{ all -> 0x1240 }
            if (r5 == 0) goto L_0x039d
            r15.zza((java.lang.String) r2)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgo r5 = r43.zzj()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgq r5 = r5.zzp()     // Catch:{ all -> 0x1240 }
            java.lang.String r9 = "Renaming ad_impression to _ai"
            r5.zza(r9)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgo r5 = r43.zzj()     // Catch:{ all -> 0x1240 }
            r9 = 5
            boolean r5 = r5.zza((int) r9)     // Catch:{ all -> 0x1240 }
            if (r5 == 0) goto L_0x039d
            r5 = 0
        L_0x0355:
            int r9 = r15.zza()     // Catch:{ all -> 0x1240 }
            if (r5 >= r9) goto L_0x039d
            java.lang.String r9 = "ad_platform"
            com.google.android.gms.internal.measurement.zzfy$zzh r22 = r15.zzb((int) r5)     // Catch:{ all -> 0x1240 }
            r24 = r2
            java.lang.String r2 = r22.zzg()     // Catch:{ all -> 0x1240 }
            boolean r2 = r9.equals(r2)     // Catch:{ all -> 0x1240 }
            if (r2 == 0) goto L_0x0398
            com.google.android.gms.internal.measurement.zzfy$zzh r2 = r15.zzb((int) r5)     // Catch:{ all -> 0x1240 }
            java.lang.String r2 = r2.zzh()     // Catch:{ all -> 0x1240 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x1240 }
            if (r2 != 0) goto L_0x0398
            java.lang.String r2 = "admob"
            com.google.android.gms.internal.measurement.zzfy$zzh r9 = r15.zzb((int) r5)     // Catch:{ all -> 0x1240 }
            java.lang.String r9 = r9.zzh()     // Catch:{ all -> 0x1240 }
            boolean r2 = r2.equalsIgnoreCase(r9)     // Catch:{ all -> 0x1240 }
            if (r2 == 0) goto L_0x0398
            com.google.android.gms.measurement.internal.zzgo r2 = r43.zzj()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzv()     // Catch:{ all -> 0x1240 }
            java.lang.String r9 = "AdMob ad impression logged from app. Potentially duplicative."
            r2.zza(r9)     // Catch:{ all -> 0x1240 }
        L_0x0398:
            int r5 = r5 + 1
            r2 = r24
            goto L_0x0355
        L_0x039d:
            r24 = r2
            com.google.android.gms.measurement.internal.zzhl r2 = r43.zzi()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r5 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r5 = r5.zzz()     // Catch:{ all -> 0x1240 }
            java.lang.String r9 = r15.zze()     // Catch:{ all -> 0x1240 }
            boolean r2 = r2.zzc((java.lang.String) r5, (java.lang.String) r9)     // Catch:{ all -> 0x1240 }
            if (r2 != 0) goto L_0x03e9
            r43.zzp()     // Catch:{ all -> 0x1240 }
            java.lang.String r5 = r15.zze()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5)     // Catch:{ all -> 0x1240 }
            int r9 = r5.hashCode()     // Catch:{ all -> 0x1240 }
            r22 = r3
            r3 = 95027(0x17333, float:1.33161E-40)
            if (r9 == r3) goto L_0x03c9
            goto L_0x03d3
        L_0x03c9:
            java.lang.String r3 = "_ui"
            boolean r3 = r5.equals(r3)     // Catch:{ all -> 0x1240 }
            if (r3 == 0) goto L_0x03d3
            r3 = 0
            goto L_0x03d4
        L_0x03d3:
            r3 = -1
        L_0x03d4:
            if (r3 == 0) goto L_0x03d8
            r3 = 0
            goto L_0x03d9
        L_0x03d8:
            r3 = 1
        L_0x03d9:
            if (r3 == 0) goto L_0x03dc
            goto L_0x03eb
        L_0x03dc:
            r26 = r8
            r27 = r12
            r5 = r13
            r25 = r14
            r14 = r7
            r7 = r10
        L_0x03e5:
            r10 = r17
            goto L_0x05ce
        L_0x03e9:
            r22 = r3
        L_0x03eb:
            r25 = r14
            r3 = 0
            r5 = 0
            r9 = 0
        L_0x03f0:
            int r14 = r15.zza()     // Catch:{ all -> 0x1240 }
            r26 = r8
            java.lang.String r8 = "_r"
            if (r3 >= r14) goto L_0x045d
            com.google.android.gms.internal.measurement.zzfy$zzh r14 = r15.zzb((int) r3)     // Catch:{ all -> 0x1240 }
            java.lang.String r14 = r14.zzg()     // Catch:{ all -> 0x1240 }
            boolean r14 = r11.equals(r14)     // Catch:{ all -> 0x1240 }
            if (r14 == 0) goto L_0x0429
            com.google.android.gms.internal.measurement.zzfy$zzh r5 = r15.zzb((int) r3)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt$zzb r5 = r5.zzcd()     // Catch:{ all -> 0x1240 }
            r8 = r5
            com.google.android.gms.internal.measurement.zzjt$zzb r8 = (com.google.android.gms.internal.measurement.zzjt.zzb) r8     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r5 = (com.google.android.gms.internal.measurement.zzfy.zzh.zza) r5     // Catch:{ all -> 0x1240 }
            r14 = r7
            r7 = 1
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r5 = r5.zza((long) r7)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzlc r5 = r5.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r5 = (com.google.android.gms.internal.measurement.zzjt) r5     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh r5 = (com.google.android.gms.internal.measurement.zzfy.zzh) r5     // Catch:{ all -> 0x1240 }
            r15.zza((int) r3, (com.google.android.gms.internal.measurement.zzfy.zzh) r5)     // Catch:{ all -> 0x1240 }
            r5 = 1
            goto L_0x0457
        L_0x0429:
            r14 = r7
            com.google.android.gms.internal.measurement.zzfy$zzh r7 = r15.zzb((int) r3)     // Catch:{ all -> 0x1240 }
            java.lang.String r7 = r7.zzg()     // Catch:{ all -> 0x1240 }
            boolean r7 = r8.equals(r7)     // Catch:{ all -> 0x1240 }
            if (r7 == 0) goto L_0x0457
            com.google.android.gms.internal.measurement.zzfy$zzh r7 = r15.zzb((int) r3)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt$zzb r7 = r7.zzcd()     // Catch:{ all -> 0x1240 }
            r8 = r7
            com.google.android.gms.internal.measurement.zzjt$zzb r8 = (com.google.android.gms.internal.measurement.zzjt.zzb) r8     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r7 = (com.google.android.gms.internal.measurement.zzfy.zzh.zza) r7     // Catch:{ all -> 0x1240 }
            r8 = 1
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r7 = r7.zza((long) r8)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzlc r7 = r7.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r7 = (com.google.android.gms.internal.measurement.zzjt) r7     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh r7 = (com.google.android.gms.internal.measurement.zzfy.zzh) r7     // Catch:{ all -> 0x1240 }
            r15.zza((int) r3, (com.google.android.gms.internal.measurement.zzfy.zzh) r7)     // Catch:{ all -> 0x1240 }
            r9 = 1
        L_0x0457:
            int r3 = r3 + 1
            r7 = r14
            r8 = r26
            goto L_0x03f0
        L_0x045d:
            r14 = r7
            if (r5 != 0) goto L_0x0492
            if (r2 == 0) goto L_0x0492
            com.google.android.gms.measurement.internal.zzgo r3 = r43.zzj()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzp()     // Catch:{ all -> 0x1240 }
            java.lang.String r5 = "Marking event as conversion"
            com.google.android.gms.measurement.internal.zzhy r7 = r1.zzm     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgh r7 = r7.zzk()     // Catch:{ all -> 0x1240 }
            r27 = r12
            java.lang.String r12 = r15.zze()     // Catch:{ all -> 0x1240 }
            java.lang.String r7 = r7.zza((java.lang.String) r12)     // Catch:{ all -> 0x1240 }
            r3.zza(r5, r7)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r3 = com.google.android.gms.internal.measurement.zzfy.zzh.zze()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r3 = r3.zza((java.lang.String) r11)     // Catch:{ all -> 0x1240 }
            r5 = r13
            r12 = 1
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r3 = r3.zza((long) r12)     // Catch:{ all -> 0x1240 }
            r15.zza((com.google.android.gms.internal.measurement.zzfy.zzh.zza) r3)     // Catch:{ all -> 0x1240 }
            goto L_0x0495
        L_0x0492:
            r27 = r12
            r5 = r13
        L_0x0495:
            if (r9 != 0) goto L_0x04c3
            com.google.android.gms.measurement.internal.zzgo r3 = r43.zzj()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzp()     // Catch:{ all -> 0x1240 }
            java.lang.String r7 = "Marking event as real-time"
            com.google.android.gms.measurement.internal.zzhy r9 = r1.zzm     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgh r9 = r9.zzk()     // Catch:{ all -> 0x1240 }
            java.lang.String r12 = r15.zze()     // Catch:{ all -> 0x1240 }
            java.lang.String r9 = r9.zza((java.lang.String) r12)     // Catch:{ all -> 0x1240 }
            r3.zza(r7, r9)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r3 = com.google.android.gms.internal.measurement.zzfy.zzh.zze()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r3 = r3.zza((java.lang.String) r8)     // Catch:{ all -> 0x1240 }
            r12 = 1
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r3 = r3.zza((long) r12)     // Catch:{ all -> 0x1240 }
            r15.zza((com.google.android.gms.internal.measurement.zzfy.zzh.zza) r3)     // Catch:{ all -> 0x1240 }
        L_0x04c3:
            com.google.android.gms.measurement.internal.zzal r28 = r43.zzf()     // Catch:{ all -> 0x1240 }
            long r29 = r43.zzx()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r3 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r31 = r3.zzz()     // Catch:{ all -> 0x1240 }
            r32 = 0
            r33 = 0
            r34 = 0
            r35 = 0
            r36 = 1
            r37 = 0
            r38 = 0
            com.google.android.gms.measurement.internal.zzaq r3 = r28.zza(r29, r31, r32, r33, r34, r35, r36, r37, r38)     // Catch:{ all -> 0x1240 }
            long r12 = r3.zze     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzag r3 = r43.zze()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r7 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r7 = r7.zzz()     // Catch:{ all -> 0x1240 }
            int r3 = r3.zzc(r7)     // Catch:{ all -> 0x1240 }
            r7 = r10
            long r9 = (long) r3     // Catch:{ all -> 0x1240 }
            int r3 = (r12 > r9 ? 1 : (r12 == r9 ? 0 : -1))
            if (r3 <= 0) goto L_0x04fd
            zza((com.google.android.gms.internal.measurement.zzfy.zzf.zza) r15, (java.lang.String) r8)     // Catch:{ all -> 0x1240 }
            goto L_0x04ff
        L_0x04fd:
            r17 = 1
        L_0x04ff:
            java.lang.String r3 = r15.zze()     // Catch:{ all -> 0x1240 }
            boolean r3 = com.google.android.gms.measurement.internal.zzos.zzh(r3)     // Catch:{ all -> 0x1240 }
            if (r3 == 0) goto L_0x03e5
            if (r2 == 0) goto L_0x03e5
            com.google.android.gms.measurement.internal.zzal r28 = r43.zzf()     // Catch:{ all -> 0x1240 }
            long r29 = r43.zzx()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r3 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r31 = r3.zzz()     // Catch:{ all -> 0x1240 }
            r32 = 0
            r33 = 0
            r34 = 1
            r35 = 0
            r36 = 0
            r37 = 0
            r38 = 0
            com.google.android.gms.measurement.internal.zzaq r3 = r28.zza(r29, r31, r32, r33, r34, r35, r36, r37, r38)     // Catch:{ all -> 0x1240 }
            long r8 = r3.zzc     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzag r3 = r43.zze()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r10 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r10 = r10.zzz()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Integer> r12 = com.google.android.gms.measurement.internal.zzbh.zzn     // Catch:{ all -> 0x1240 }
            int r3 = r3.zzb((java.lang.String) r10, (com.google.android.gms.measurement.internal.zzfz<java.lang.Integer>) r12)     // Catch:{ all -> 0x1240 }
            long r12 = (long) r3     // Catch:{ all -> 0x1240 }
            int r3 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r3 <= 0) goto L_0x03e5
            com.google.android.gms.measurement.internal.zzgo r3 = r43.zzj()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzu()     // Catch:{ all -> 0x1240 }
            java.lang.String r8 = "Too many conversions. Not logging as conversion. appId"
            com.google.android.gms.internal.measurement.zzfy$zzk r9 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r9 = r9.zzz()     // Catch:{ all -> 0x1240 }
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r9)     // Catch:{ all -> 0x1240 }
            r3.zza(r8, r9)     // Catch:{ all -> 0x1240 }
            r3 = -1
            r8 = 0
            r9 = 0
            r10 = 0
        L_0x055d:
            int r12 = r15.zza()     // Catch:{ all -> 0x1240 }
            if (r8 >= r12) goto L_0x058b
            com.google.android.gms.internal.measurement.zzfy$zzh r12 = r15.zzb((int) r8)     // Catch:{ all -> 0x1240 }
            java.lang.String r13 = r12.zzg()     // Catch:{ all -> 0x1240 }
            boolean r13 = r11.equals(r13)     // Catch:{ all -> 0x1240 }
            if (r13 == 0) goto L_0x057d
            com.google.android.gms.internal.measurement.zzjt$zzb r3 = r12.zzcd()     // Catch:{ all -> 0x1240 }
            r9 = r3
            com.google.android.gms.internal.measurement.zzjt$zzb r9 = (com.google.android.gms.internal.measurement.zzjt.zzb) r9     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r3 = (com.google.android.gms.internal.measurement.zzfy.zzh.zza) r3     // Catch:{ all -> 0x1240 }
            r9 = r3
            r3 = r8
            goto L_0x0588
        L_0x057d:
            java.lang.String r12 = r12.zzg()     // Catch:{ all -> 0x1240 }
            boolean r12 = r6.equals(r12)     // Catch:{ all -> 0x1240 }
            if (r12 == 0) goto L_0x0588
            r10 = 1
        L_0x0588:
            int r8 = r8 + 1
            goto L_0x055d
        L_0x058b:
            if (r10 == 0) goto L_0x0594
            if (r9 == 0) goto L_0x0594
            r15.zza((int) r3)     // Catch:{ all -> 0x1240 }
            goto L_0x03e5
        L_0x0594:
            if (r9 == 0) goto L_0x05b5
            java.lang.Object r8 = r9.clone()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt$zzb r8 = (com.google.android.gms.internal.measurement.zzjt.zzb) r8     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r8 = (com.google.android.gms.internal.measurement.zzfy.zzh.zza) r8     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r6 = r8.zza((java.lang.String) r6)     // Catch:{ all -> 0x1240 }
            r8 = 10
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r6 = r6.zza((long) r8)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzlc r6 = r6.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r6 = (com.google.android.gms.internal.measurement.zzjt) r6     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh r6 = (com.google.android.gms.internal.measurement.zzfy.zzh) r6     // Catch:{ all -> 0x1240 }
            r15.zza((int) r3, (com.google.android.gms.internal.measurement.zzfy.zzh) r6)     // Catch:{ all -> 0x1240 }
            goto L_0x03e5
        L_0x05b5:
            com.google.android.gms.measurement.internal.zzgo r3 = r43.zzj()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzg()     // Catch:{ all -> 0x1240 }
            java.lang.String r6 = "Did not find conversion parameter. appId"
            com.google.android.gms.internal.measurement.zzfy$zzk r8 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r8 = r8.zzz()     // Catch:{ all -> 0x1240 }
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r8)     // Catch:{ all -> 0x1240 }
            r3.zza(r6, r8)     // Catch:{ all -> 0x1240 }
            goto L_0x03e5
        L_0x05ce:
            if (r2 == 0) goto L_0x068d
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x1240 }
            java.util.List r3 = r15.zzf()     // Catch:{ all -> 0x1240 }
            r2.<init>(r3)     // Catch:{ all -> 0x1240 }
            r3 = 0
            r6 = -1
            r8 = -1
        L_0x05dc:
            int r9 = r2.size()     // Catch:{ all -> 0x1240 }
            java.lang.String r12 = "currency"
            java.lang.String r13 = "value"
            if (r3 >= r9) goto L_0x060c
            java.lang.Object r9 = r2.get(r3)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh r9 = (com.google.android.gms.internal.measurement.zzfy.zzh) r9     // Catch:{ all -> 0x1240 }
            java.lang.String r9 = r9.zzg()     // Catch:{ all -> 0x1240 }
            boolean r9 = r13.equals(r9)     // Catch:{ all -> 0x1240 }
            if (r9 == 0) goto L_0x05f8
            r6 = r3
            goto L_0x0609
        L_0x05f8:
            java.lang.Object r9 = r2.get(r3)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh r9 = (com.google.android.gms.internal.measurement.zzfy.zzh) r9     // Catch:{ all -> 0x1240 }
            java.lang.String r9 = r9.zzg()     // Catch:{ all -> 0x1240 }
            boolean r9 = r12.equals(r9)     // Catch:{ all -> 0x1240 }
            if (r9 == 0) goto L_0x0609
            r8 = r3
        L_0x0609:
            int r3 = r3 + 1
            goto L_0x05dc
        L_0x060c:
            r3 = -1
            if (r6 == r3) goto L_0x068e
            java.lang.Object r3 = r2.get(r6)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh r3 = (com.google.android.gms.internal.measurement.zzfy.zzh) r3     // Catch:{ all -> 0x1240 }
            boolean r3 = r3.zzl()     // Catch:{ all -> 0x1240 }
            if (r3 != 0) goto L_0x0640
            java.lang.Object r3 = r2.get(r6)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh r3 = (com.google.android.gms.internal.measurement.zzfy.zzh) r3     // Catch:{ all -> 0x1240 }
            boolean r3 = r3.zzj()     // Catch:{ all -> 0x1240 }
            if (r3 != 0) goto L_0x0640
            com.google.android.gms.measurement.internal.zzgo r2 = r43.zzj()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzv()     // Catch:{ all -> 0x1240 }
            java.lang.String r3 = "Value must be specified with a numeric type."
            r2.zza(r3)     // Catch:{ all -> 0x1240 }
            r15.zza((int) r6)     // Catch:{ all -> 0x1240 }
            zza((com.google.android.gms.internal.measurement.zzfy.zzf.zza) r15, (java.lang.String) r11)     // Catch:{ all -> 0x1240 }
            r2 = 18
            zza((com.google.android.gms.internal.measurement.zzfy.zzf.zza) r15, (int) r2, (java.lang.String) r13)     // Catch:{ all -> 0x1240 }
            goto L_0x068d
        L_0x0640:
            r3 = -1
            if (r8 != r3) goto L_0x0646
            r2 = 1
            r9 = 3
            goto L_0x0672
        L_0x0646:
            java.lang.Object r2 = r2.get(r8)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh r2 = (com.google.android.gms.internal.measurement.zzfy.zzh) r2     // Catch:{ all -> 0x1240 }
            java.lang.String r2 = r2.zzh()     // Catch:{ all -> 0x1240 }
            int r8 = r2.length()     // Catch:{ all -> 0x1240 }
            r9 = 3
            if (r8 == r9) goto L_0x0659
        L_0x0657:
            r2 = 1
            goto L_0x0672
        L_0x0659:
            r8 = 0
        L_0x065a:
            int r13 = r2.length()     // Catch:{ all -> 0x1240 }
            if (r8 >= r13) goto L_0x0671
            int r13 = r2.codePointAt(r8)     // Catch:{ all -> 0x1240 }
            boolean r17 = java.lang.Character.isLetter(r13)     // Catch:{ all -> 0x1240 }
            if (r17 != 0) goto L_0x066b
            goto L_0x0657
        L_0x066b:
            int r13 = java.lang.Character.charCount(r13)     // Catch:{ all -> 0x1240 }
            int r8 = r8 + r13
            goto L_0x065a
        L_0x0671:
            r2 = 0
        L_0x0672:
            if (r2 == 0) goto L_0x068f
            com.google.android.gms.measurement.internal.zzgo r2 = r43.zzj()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzv()     // Catch:{ all -> 0x1240 }
            java.lang.String r8 = "Value parameter discarded. You must also supply a 3-letter ISO_4217 currency code in the currency parameter."
            r2.zza(r8)     // Catch:{ all -> 0x1240 }
            r15.zza((int) r6)     // Catch:{ all -> 0x1240 }
            zza((com.google.android.gms.internal.measurement.zzfy.zzf.zza) r15, (java.lang.String) r11)     // Catch:{ all -> 0x1240 }
            r2 = 19
            zza((com.google.android.gms.internal.measurement.zzfy.zzf.zza) r15, (int) r2, (java.lang.String) r12)     // Catch:{ all -> 0x1240 }
            goto L_0x068f
        L_0x068d:
            r3 = -1
        L_0x068e:
            r9 = 3
        L_0x068f:
            java.lang.String r2 = r15.zze()     // Catch:{ all -> 0x1240 }
            boolean r2 = r7.equals(r2)     // Catch:{ all -> 0x1240 }
            if (r2 == 0) goto L_0x06ed
            r43.zzp()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzlc r2 = r15.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r2 = (com.google.android.gms.internal.measurement.zzjt) r2     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzf r2 = (com.google.android.gms.internal.measurement.zzfy.zzf) r2     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh r2 = com.google.android.gms.measurement.internal.zzoo.zza((com.google.android.gms.internal.measurement.zzfy.zzf) r2, (java.lang.String) r5)     // Catch:{ all -> 0x1240 }
            if (r2 != 0) goto L_0x06e8
            if (r14 == 0) goto L_0x06dc
            long r5 = r14.zzc()     // Catch:{ all -> 0x1240 }
            long r7 = r15.zzc()     // Catch:{ all -> 0x1240 }
            long r5 = r5 - r7
            long r5 = java.lang.Math.abs(r5)     // Catch:{ all -> 0x1240 }
            r7 = 1000(0x3e8, double:4.94E-321)
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 > 0) goto L_0x06dc
            java.lang.Object r2 = r14.clone()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt$zzb r2 = (com.google.android.gms.internal.measurement.zzjt.zzb) r2     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzf$zza r2 = (com.google.android.gms.internal.measurement.zzfy.zzf.zza) r2     // Catch:{ all -> 0x1240 }
            boolean r5 = r1.zza((com.google.android.gms.internal.measurement.zzfy.zzf.zza) r15, (com.google.android.gms.internal.measurement.zzfy.zzf.zza) r2)     // Catch:{ all -> 0x1240 }
            if (r5 == 0) goto L_0x06dc
            r6 = r46
            r5 = r27
            r6.zza((int) r5, (com.google.android.gms.internal.measurement.zzfy.zzf.zza) r2)     // Catch:{ all -> 0x1240 }
            r12 = r5
            r14 = r25
        L_0x06d7:
            r2 = 0
            r26 = 0
            goto L_0x0747
        L_0x06dc:
            r6 = r46
            r5 = r27
            r12 = r5
            r2 = r14
            r26 = r15
            r14 = r45
            goto L_0x0747
        L_0x06e8:
            r6 = r46
            r5 = r27
            goto L_0x0742
        L_0x06ed:
            r6 = r46
            r5 = r27
            java.lang.String r2 = "_vs"
            java.lang.String r7 = r15.zze()     // Catch:{ all -> 0x1240 }
            boolean r2 = r2.equals(r7)     // Catch:{ all -> 0x1240 }
            if (r2 == 0) goto L_0x0742
            r43.zzp()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzlc r2 = r15.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r2 = (com.google.android.gms.internal.measurement.zzjt) r2     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzf r2 = (com.google.android.gms.internal.measurement.zzfy.zzf) r2     // Catch:{ all -> 0x1240 }
            r8 = r18
            com.google.android.gms.internal.measurement.zzfy$zzh r2 = com.google.android.gms.measurement.internal.zzoo.zza((com.google.android.gms.internal.measurement.zzfy.zzf) r2, (java.lang.String) r8)     // Catch:{ all -> 0x1240 }
            if (r2 != 0) goto L_0x0742
            if (r26 == 0) goto L_0x073b
            long r7 = r26.zzc()     // Catch:{ all -> 0x1240 }
            long r11 = r15.zzc()     // Catch:{ all -> 0x1240 }
            long r7 = r7 - r11
            long r7 = java.lang.Math.abs(r7)     // Catch:{ all -> 0x1240 }
            r11 = 1000(0x3e8, double:4.94E-321)
            int r2 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r2 > 0) goto L_0x073b
            java.lang.Object r2 = r26.clone()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt$zzb r2 = (com.google.android.gms.internal.measurement.zzjt.zzb) r2     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzf$zza r2 = (com.google.android.gms.internal.measurement.zzfy.zzf.zza) r2     // Catch:{ all -> 0x1240 }
            boolean r7 = r1.zza((com.google.android.gms.internal.measurement.zzfy.zzf.zza) r2, (com.google.android.gms.internal.measurement.zzfy.zzf.zza) r15)     // Catch:{ all -> 0x1240 }
            if (r7 == 0) goto L_0x073b
            r7 = r25
            r6.zza((int) r7, (com.google.android.gms.internal.measurement.zzfy.zzf.zza) r2)     // Catch:{ all -> 0x1240 }
            r12 = r5
            r14 = r7
            goto L_0x06d7
        L_0x073b:
            r7 = r25
            r12 = r45
            r14 = r7
            r2 = r15
            goto L_0x0747
        L_0x0742:
            r7 = r25
            r12 = r5
            r2 = r14
            r14 = r7
        L_0x0747:
            int r5 = r15.zza()     // Catch:{ all -> 0x1240 }
            if (r5 == 0) goto L_0x086f
            r43.zzp()     // Catch:{ all -> 0x1240 }
            java.util.List r5 = r15.zzf()     // Catch:{ all -> 0x1240 }
            android.os.Bundle r5 = com.google.android.gms.measurement.internal.zzoo.zza((java.util.List<com.google.android.gms.internal.measurement.zzfy.zzh>) r5)     // Catch:{ all -> 0x1240 }
            r7 = 0
        L_0x0759:
            int r8 = r15.zza()     // Catch:{ all -> 0x1240 }
            if (r7 >= r8) goto L_0x0816
            com.google.android.gms.internal.measurement.zzfy$zzh r8 = r15.zzb((int) r7)     // Catch:{ all -> 0x1240 }
            java.lang.String r11 = r8.zzg()     // Catch:{ all -> 0x1240 }
            r13 = r22
            boolean r11 = r11.equals(r13)     // Catch:{ all -> 0x1240 }
            if (r11 == 0) goto L_0x07e6
            java.util.List r11 = r8.zzi()     // Catch:{ all -> 0x1240 }
            boolean r11 = r11.isEmpty()     // Catch:{ all -> 0x1240 }
            if (r11 != 0) goto L_0x07e6
            com.google.android.gms.internal.measurement.zzfy$zzk r11 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r11 = r11.zzz()     // Catch:{ all -> 0x1240 }
            java.util.List r8 = r8.zzi()     // Catch:{ all -> 0x1240 }
            int r3 = r8.size()     // Catch:{ all -> 0x1240 }
            android.os.Bundle[] r3 = new android.os.Bundle[r3]     // Catch:{ all -> 0x1240 }
            r46 = r2
            r9 = 0
        L_0x078c:
            int r2 = r8.size()     // Catch:{ all -> 0x1240 }
            if (r9 >= r2) goto L_0x07e0
            java.lang.Object r2 = r8.get(r9)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh r2 = (com.google.android.gms.internal.measurement.zzfy.zzh) r2     // Catch:{ all -> 0x1240 }
            r43.zzp()     // Catch:{ all -> 0x1240 }
            java.util.List r17 = r2.zzi()     // Catch:{ all -> 0x1240 }
            r18 = r8
            android.os.Bundle r8 = com.google.android.gms.measurement.internal.zzoo.zza((java.util.List<com.google.android.gms.internal.measurement.zzfy.zzh>) r17)     // Catch:{ all -> 0x1240 }
            java.util.List r2 = r2.zzi()     // Catch:{ all -> 0x1240 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x1240 }
        L_0x07ad:
            boolean r17 = r2.hasNext()     // Catch:{ all -> 0x1240 }
            if (r17 == 0) goto L_0x07d5
            java.lang.Object r17 = r2.next()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh r17 = (com.google.android.gms.internal.measurement.zzfy.zzh) r17     // Catch:{ all -> 0x1240 }
            r19 = r2
            java.lang.String r2 = r15.zze()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt$zzb r17 = r17.zzcd()     // Catch:{ all -> 0x1240 }
            r20 = r17
            com.google.android.gms.internal.measurement.zzjt$zzb r20 = (com.google.android.gms.internal.measurement.zzjt.zzb) r20     // Catch:{ all -> 0x1240 }
            r20 = r10
            r10 = r17
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r10 = (com.google.android.gms.internal.measurement.zzfy.zzh.zza) r10     // Catch:{ all -> 0x1240 }
            r1.zza((java.lang.String) r2, (com.google.android.gms.internal.measurement.zzfy.zzh.zza) r10, (android.os.Bundle) r8, (java.lang.String) r11)     // Catch:{ all -> 0x1240 }
            r2 = r19
            r10 = r20
            goto L_0x07ad
        L_0x07d5:
            r20 = r10
            r3[r9] = r8     // Catch:{ all -> 0x1240 }
            int r9 = r9 + 1
            r8 = r18
            r10 = r20
            goto L_0x078c
        L_0x07e0:
            r20 = r10
            r5.putParcelableArray(r13, r3)     // Catch:{ all -> 0x1240 }
            goto L_0x080a
        L_0x07e6:
            r46 = r2
            r20 = r10
            java.lang.String r2 = r8.zzg()     // Catch:{ all -> 0x1240 }
            boolean r2 = r2.equals(r13)     // Catch:{ all -> 0x1240 }
            if (r2 != 0) goto L_0x080a
            java.lang.String r2 = r15.zze()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt$zzb r3 = r8.zzcd()     // Catch:{ all -> 0x1240 }
            r8 = r3
            com.google.android.gms.internal.measurement.zzjt$zzb r8 = (com.google.android.gms.internal.measurement.zzjt.zzb) r8     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r3 = (com.google.android.gms.internal.measurement.zzfy.zzh.zza) r3     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r8 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r8 = r8.zzz()     // Catch:{ all -> 0x1240 }
            r1.zza((java.lang.String) r2, (com.google.android.gms.internal.measurement.zzfy.zzh.zza) r3, (android.os.Bundle) r5, (java.lang.String) r8)     // Catch:{ all -> 0x1240 }
        L_0x080a:
            int r7 = r7 + 1
            r2 = r46
            r22 = r13
            r10 = r20
            r3 = -1
            r9 = 3
            goto L_0x0759
        L_0x0816:
            r46 = r2
            r20 = r10
            r13 = r22
            r15.zzd()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzoo r2 = r43.zzp()     // Catch:{ all -> 0x1240 }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x1240 }
            r3.<init>()     // Catch:{ all -> 0x1240 }
            java.util.Set r7 = r5.keySet()     // Catch:{ all -> 0x1240 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x1240 }
        L_0x0830:
            boolean r8 = r7.hasNext()     // Catch:{ all -> 0x1240 }
            if (r8 == 0) goto L_0x0859
            java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x1240 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r9 = com.google.android.gms.internal.measurement.zzfy.zzh.zze()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r9 = r9.zza((java.lang.String) r8)     // Catch:{ all -> 0x1240 }
            java.lang.Object r8 = r5.get(r8)     // Catch:{ all -> 0x1240 }
            if (r8 == 0) goto L_0x0830
            r2.zza((com.google.android.gms.internal.measurement.zzfy.zzh.zza) r9, (java.lang.Object) r8)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzlc r8 = r9.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r8 = (com.google.android.gms.internal.measurement.zzjt) r8     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh r8 = (com.google.android.gms.internal.measurement.zzfy.zzh) r8     // Catch:{ all -> 0x1240 }
            r3.add(r8)     // Catch:{ all -> 0x1240 }
            goto L_0x0830
        L_0x0859:
            r2 = r3
            java.util.ArrayList r2 = (java.util.ArrayList) r2     // Catch:{ all -> 0x1240 }
            int r2 = r3.size()     // Catch:{ all -> 0x1240 }
            r5 = 0
        L_0x0861:
            if (r5 >= r2) goto L_0x0875
            java.lang.Object r7 = r3.get(r5)     // Catch:{ all -> 0x1240 }
            int r5 = r5 + 1
            com.google.android.gms.internal.measurement.zzfy$zzh r7 = (com.google.android.gms.internal.measurement.zzfy.zzh) r7     // Catch:{ all -> 0x1240 }
            r15.zza((com.google.android.gms.internal.measurement.zzfy.zzh) r7)     // Catch:{ all -> 0x1240 }
            goto L_0x0861
        L_0x086f:
            r46 = r2
            r20 = r10
            r13 = r22
        L_0x0875:
            java.util.List<com.google.android.gms.internal.measurement.zzfy$zzf> r2 = r4.zzc     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzlc r3 = r15.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r3 = (com.google.android.gms.internal.measurement.zzjt) r3     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzf r3 = (com.google.android.gms.internal.measurement.zzfy.zzf) r3     // Catch:{ all -> 0x1240 }
            r5 = r21
            r2.set(r5, r3)     // Catch:{ all -> 0x1240 }
            int r11 = r45 + 1
            r6.zza((com.google.android.gms.internal.measurement.zzfy.zzf.zza) r15)     // Catch:{ all -> 0x1240 }
            r7 = r46
            r10 = r20
            r8 = r26
        L_0x088f:
            int r9 = r5 + 1
            r5 = r6
            r3 = r13
            r2 = r24
            r13 = 0
            goto L_0x026d
        L_0x0898:
            r6 = r46
            r7 = r10
            r5 = r13
            r8 = r18
            r2 = 0
            r9 = r45
            r12 = r2
            r10 = 0
        L_0x08a4:
            if (r10 >= r9) goto L_0x08f0
            com.google.android.gms.internal.measurement.zzfy$zzf r14 = r6.zza((int) r10)     // Catch:{ all -> 0x1240 }
            java.lang.String r15 = r14.zzg()     // Catch:{ all -> 0x1240 }
            boolean r15 = r7.equals(r15)     // Catch:{ all -> 0x1240 }
            if (r15 == 0) goto L_0x08c5
            r43.zzp()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh r15 = com.google.android.gms.measurement.internal.zzoo.zza((com.google.android.gms.internal.measurement.zzfy.zzf) r14, (java.lang.String) r5)     // Catch:{ all -> 0x1240 }
            if (r15 == 0) goto L_0x08c5
            r6.zzb((int) r10)     // Catch:{ all -> 0x1240 }
            int r9 = r9 + -1
            int r10 = r10 + -1
            goto L_0x08ed
        L_0x08c5:
            r43.zzp()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh r14 = com.google.android.gms.measurement.internal.zzoo.zza((com.google.android.gms.internal.measurement.zzfy.zzf) r14, (java.lang.String) r8)     // Catch:{ all -> 0x1240 }
            if (r14 == 0) goto L_0x08ed
            boolean r15 = r14.zzl()     // Catch:{ all -> 0x1240 }
            if (r15 == 0) goto L_0x08dd
            long r14 = r14.zzd()     // Catch:{ all -> 0x1240 }
            java.lang.Long r14 = java.lang.Long.valueOf(r14)     // Catch:{ all -> 0x1240 }
            goto L_0x08de
        L_0x08dd:
            r14 = 0
        L_0x08de:
            if (r14 == 0) goto L_0x08ed
            long r21 = r14.longValue()     // Catch:{ all -> 0x1240 }
            int r15 = (r21 > r2 ? 1 : (r21 == r2 ? 0 : -1))
            if (r15 <= 0) goto L_0x08ed
            long r14 = r14.longValue()     // Catch:{ all -> 0x1240 }
            long r12 = r12 + r14
        L_0x08ed:
            r14 = 1
            int r10 = r10 + r14
            goto L_0x08a4
        L_0x08f0:
            r5 = 0
            r1.zza((com.google.android.gms.internal.measurement.zzfy.zzk.zza) r6, (long) r12, (boolean) r5)     // Catch:{ all -> 0x1240 }
            java.util.List r5 = r6.zzaa()     // Catch:{ all -> 0x1240 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x1240 }
        L_0x08fc:
            boolean r7 = r5.hasNext()     // Catch:{ all -> 0x1240 }
            if (r7 == 0) goto L_0x0916
            java.lang.Object r7 = r5.next()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzf r7 = (com.google.android.gms.internal.measurement.zzfy.zzf) r7     // Catch:{ all -> 0x1240 }
            java.lang.String r8 = "_s"
            java.lang.String r7 = r7.zzg()     // Catch:{ all -> 0x1240 }
            boolean r7 = r8.equals(r7)     // Catch:{ all -> 0x1240 }
            if (r7 == 0) goto L_0x08fc
            r5 = 1
            goto L_0x0917
        L_0x0916:
            r5 = 0
        L_0x0917:
            java.lang.String r7 = "_se"
            if (r5 == 0) goto L_0x0926
            com.google.android.gms.measurement.internal.zzal r5 = r43.zzf()     // Catch:{ all -> 0x1240 }
            java.lang.String r8 = r6.zzt()     // Catch:{ all -> 0x1240 }
            r5.zzh(r8, r7)     // Catch:{ all -> 0x1240 }
        L_0x0926:
            java.lang.String r5 = "_sid"
            int r5 = com.google.android.gms.measurement.internal.zzoo.zza((com.google.android.gms.internal.measurement.zzfy.zzk.zza) r6, (java.lang.String) r5)     // Catch:{ all -> 0x1240 }
            if (r5 < 0) goto L_0x0930
            r5 = 1
            goto L_0x0931
        L_0x0930:
            r5 = 0
        L_0x0931:
            if (r5 == 0) goto L_0x0938
            r5 = 1
            r1.zza((com.google.android.gms.internal.measurement.zzfy.zzk.zza) r6, (long) r12, (boolean) r5)     // Catch:{ all -> 0x1240 }
            goto L_0x0958
        L_0x0938:
            int r5 = com.google.android.gms.measurement.internal.zzoo.zza((com.google.android.gms.internal.measurement.zzfy.zzk.zza) r6, (java.lang.String) r7)     // Catch:{ all -> 0x1240 }
            if (r5 < 0) goto L_0x0958
            r6.zzc((int) r5)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgo r5 = r43.zzj()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgq r5 = r5.zzg()     // Catch:{ all -> 0x1240 }
            java.lang.String r7 = "Session engagement user property is in the bundle without session ID. appId"
            com.google.android.gms.internal.measurement.zzfy$zzk r8 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r8 = r8.zzz()     // Catch:{ all -> 0x1240 }
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r8)     // Catch:{ all -> 0x1240 }
            r5.zza(r7, r8)     // Catch:{ all -> 0x1240 }
        L_0x0958:
            com.google.android.gms.internal.measurement.zzfy$zzk r5 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r5 = r5.zzz()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzhv r7 = r43.zzl()     // Catch:{ all -> 0x1240 }
            r7.zzt()     // Catch:{ all -> 0x1240 }
            r43.zzs()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzal r7 = r43.zzf()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzg r7 = r7.zze(r5)     // Catch:{ all -> 0x1240 }
            if (r7 != 0) goto L_0x0984
            com.google.android.gms.measurement.internal.zzgo r7 = r43.zzj()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzg()     // Catch:{ all -> 0x1240 }
            java.lang.String r8 = "Cannot fix consent fields without appInfo. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r5)     // Catch:{ all -> 0x1240 }
            r7.zza(r8, r5)     // Catch:{ all -> 0x1240 }
            goto L_0x0987
        L_0x0984:
            r1.zza((com.google.android.gms.measurement.internal.zzg) r7, (com.google.android.gms.internal.measurement.zzfy.zzk.zza) r6)     // Catch:{ all -> 0x1240 }
        L_0x0987:
            boolean r5 = com.google.android.gms.internal.measurement.zzov.zza()     // Catch:{ all -> 0x1240 }
            if (r5 == 0) goto L_0x09c8
            com.google.android.gms.measurement.internal.zzag r5 = r43.zze()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbh.zzcu     // Catch:{ all -> 0x1240 }
            boolean r5 = r5.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r7)     // Catch:{ all -> 0x1240 }
            if (r5 == 0) goto L_0x09c8
            com.google.android.gms.internal.measurement.zzfy$zzk r5 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r5 = r5.zzz()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzhv r7 = r43.zzl()     // Catch:{ all -> 0x1240 }
            r7.zzt()     // Catch:{ all -> 0x1240 }
            r43.zzs()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzal r7 = r43.zzf()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzg r7 = r7.zze(r5)     // Catch:{ all -> 0x1240 }
            if (r7 != 0) goto L_0x09c5
            com.google.android.gms.measurement.internal.zzgo r7 = r43.zzj()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzu()     // Catch:{ all -> 0x1240 }
            java.lang.String r8 = "Cannot populate ad_campaign_info without appInfo. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r5)     // Catch:{ all -> 0x1240 }
            r7.zza(r8, r5)     // Catch:{ all -> 0x1240 }
            goto L_0x09c8
        L_0x09c5:
            r1.zzb((com.google.android.gms.measurement.internal.zzg) r7, (com.google.android.gms.internal.measurement.zzfy.zzk.zza) r6)     // Catch:{ all -> 0x1240 }
        L_0x09c8:
            r7 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r5 = r6.zzi((long) r7)     // Catch:{ all -> 0x1240 }
            r7 = -9223372036854775808
            r5.zze((long) r7)     // Catch:{ all -> 0x1240 }
            r5 = 0
        L_0x09d7:
            int r7 = r6.zzc()     // Catch:{ all -> 0x1240 }
            if (r5 >= r7) goto L_0x0a0a
            com.google.android.gms.internal.measurement.zzfy$zzf r7 = r6.zza((int) r5)     // Catch:{ all -> 0x1240 }
            long r8 = r7.zzd()     // Catch:{ all -> 0x1240 }
            long r12 = r6.zzf()     // Catch:{ all -> 0x1240 }
            int r8 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r8 >= 0) goto L_0x09f4
            long r8 = r7.zzd()     // Catch:{ all -> 0x1240 }
            r6.zzi((long) r8)     // Catch:{ all -> 0x1240 }
        L_0x09f4:
            long r8 = r7.zzd()     // Catch:{ all -> 0x1240 }
            long r12 = r6.zze()     // Catch:{ all -> 0x1240 }
            int r8 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r8 <= 0) goto L_0x0a07
            long r7 = r7.zzd()     // Catch:{ all -> 0x1240 }
            r6.zze((long) r7)     // Catch:{ all -> 0x1240 }
        L_0x0a07:
            int r5 = r5 + 1
            goto L_0x09d7
        L_0x0a0a:
            r6.zzs()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzje r5 = com.google.android.gms.measurement.internal.zzje.zza     // Catch:{ all -> 0x1240 }
            boolean r7 = com.google.android.gms.internal.measurement.zznm.zza()     // Catch:{ all -> 0x1240 }
            if (r7 == 0) goto L_0x0aa2
            com.google.android.gms.measurement.internal.zzag r7 = r43.zze()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbh.zzcy     // Catch:{ all -> 0x1240 }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r8)     // Catch:{ all -> 0x1240 }
            if (r7 == 0) goto L_0x0aa2
            com.google.android.gms.internal.measurement.zzfy$zzk r5 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r5 = r5.zzz()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzje r5 = r1.zzb((java.lang.String) r5)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r7 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r7 = r7.zzae()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzje r7 = com.google.android.gms.measurement.internal.zzje.zzb((java.lang.String) r7)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzje r5 = r5.zza((com.google.android.gms.measurement.internal.zzje) r7)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzal r7 = r43.zzf()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r8 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r8 = r8.zzz()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzje r7 = r7.zzh(r8)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzal r8 = r43.zzf()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r9 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r9 = r9.zzz()     // Catch:{ all -> 0x1240 }
            r8.zza((java.lang.String) r9, (com.google.android.gms.measurement.internal.zzje) r5)     // Catch:{ all -> 0x1240 }
            boolean r8 = r5.zzh()     // Catch:{ all -> 0x1240 }
            if (r8 != 0) goto L_0x0a6e
            boolean r8 = r7.zzh()     // Catch:{ all -> 0x1240 }
            if (r8 == 0) goto L_0x0a6e
            com.google.android.gms.measurement.internal.zzal r7 = r43.zzf()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r8 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r8 = r8.zzz()     // Catch:{ all -> 0x1240 }
            r7.zzq(r8)     // Catch:{ all -> 0x1240 }
            goto L_0x0a87
        L_0x0a6e:
            boolean r8 = r5.zzh()     // Catch:{ all -> 0x1240 }
            if (r8 == 0) goto L_0x0a87
            boolean r7 = r7.zzh()     // Catch:{ all -> 0x1240 }
            if (r7 != 0) goto L_0x0a87
            com.google.android.gms.measurement.internal.zzal r7 = r43.zzf()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r8 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r8 = r8.zzz()     // Catch:{ all -> 0x1240 }
            r7.zzr(r8)     // Catch:{ all -> 0x1240 }
        L_0x0a87:
            boolean r7 = r5.zzg()     // Catch:{ all -> 0x1240 }
            if (r7 != 0) goto L_0x0a96
            r6.zzq()     // Catch:{ all -> 0x1240 }
            r6.zzn()     // Catch:{ all -> 0x1240 }
            r6.zzk()     // Catch:{ all -> 0x1240 }
        L_0x0a96:
            boolean r7 = r5.zzh()     // Catch:{ all -> 0x1240 }
            if (r7 != 0) goto L_0x0aa2
            r6.zzh()     // Catch:{ all -> 0x1240 }
            r6.zzr()     // Catch:{ all -> 0x1240 }
        L_0x0aa2:
            boolean r7 = com.google.android.gms.internal.measurement.zzpn.zza()     // Catch:{ all -> 0x1240 }
            if (r7 == 0) goto L_0x0cb1
            com.google.android.gms.measurement.internal.zzag r7 = r43.zze()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r8 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r8 = r8.zzz()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r9 = com.google.android.gms.measurement.internal.zzbh.zzch     // Catch:{ all -> 0x1240 }
            boolean r7 = r7.zze(r8, r9)     // Catch:{ all -> 0x1240 }
            if (r7 == 0) goto L_0x0cb1
            r43.zzq()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r7 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r7 = r7.zzz()     // Catch:{ all -> 0x1240 }
            boolean r7 = com.google.android.gms.measurement.internal.zzos.zzd(r7)     // Catch:{ all -> 0x1240 }
            if (r7 == 0) goto L_0x0cb1
            com.google.android.gms.internal.measurement.zzfy$zzk r7 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r7 = r7.zzz()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzje r7 = r1.zzb((java.lang.String) r7)     // Catch:{ all -> 0x1240 }
            boolean r7 = r7.zzg()     // Catch:{ all -> 0x1240 }
            if (r7 == 0) goto L_0x0cb1
            com.google.android.gms.internal.measurement.zzfy$zzk r7 = r4.zza     // Catch:{ all -> 0x1240 }
            boolean r7 = r7.zzat()     // Catch:{ all -> 0x1240 }
            if (r7 == 0) goto L_0x0cb1
            r7 = 0
        L_0x0ae2:
            int r8 = r6.zzc()     // Catch:{ all -> 0x1240 }
            if (r7 >= r8) goto L_0x0cb1
            com.google.android.gms.internal.measurement.zzfy$zzf r8 = r6.zza((int) r7)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt$zzb r8 = r8.zzcd()     // Catch:{ all -> 0x1240 }
            r9 = r8
            com.google.android.gms.internal.measurement.zzjt$zzb r9 = (com.google.android.gms.internal.measurement.zzjt.zzb) r9     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzf$zza r8 = (com.google.android.gms.internal.measurement.zzfy.zzf.zza) r8     // Catch:{ all -> 0x1240 }
            java.util.List r9 = r8.zzf()     // Catch:{ all -> 0x1240 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x1240 }
        L_0x0afd:
            boolean r10 = r9.hasNext()     // Catch:{ all -> 0x1240 }
            if (r10 == 0) goto L_0x0b15
            java.lang.Object r10 = r9.next()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh r10 = (com.google.android.gms.internal.measurement.zzfy.zzh) r10     // Catch:{ all -> 0x1240 }
            java.lang.String r10 = r10.zzg()     // Catch:{ all -> 0x1240 }
            boolean r10 = r11.equals(r10)     // Catch:{ all -> 0x1240 }
            if (r10 == 0) goto L_0x0afd
            r9 = 1
            goto L_0x0b16
        L_0x0b15:
            r9 = 0
        L_0x0b16:
            if (r9 == 0) goto L_0x0cad
            com.google.android.gms.internal.measurement.zzfy$zzk r9 = r4.zza     // Catch:{ all -> 0x1240 }
            int r9 = r9.zza()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzag r10 = r43.zze()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r12 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r12 = r12.zzz()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Integer> r13 = com.google.android.gms.measurement.internal.zzbh.zzax     // Catch:{ all -> 0x1240 }
            int r10 = r10.zzb((java.lang.String) r12, (com.google.android.gms.measurement.internal.zzfz<java.lang.Integer>) r13)     // Catch:{ all -> 0x1240 }
            if (r9 < r10) goto L_0x0ca2
            com.google.android.gms.measurement.internal.zzag r9 = r43.zze()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r10 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r10 = r10.zzz()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Integer> r12 = com.google.android.gms.measurement.internal.zzbh.zzbi     // Catch:{ all -> 0x1240 }
            int r9 = r9.zzb((java.lang.String) r10, (com.google.android.gms.measurement.internal.zzfz<java.lang.Integer>) r12)     // Catch:{ all -> 0x1240 }
            if (r9 <= 0) goto L_0x0c15
            com.google.android.gms.measurement.internal.zzal r26 = r43.zzf()     // Catch:{ all -> 0x1240 }
            long r27 = r43.zzx()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r10 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r29 = r10.zzz()     // Catch:{ all -> 0x1240 }
            r30 = 0
            r31 = 0
            r32 = 0
            r33 = 0
            r34 = 0
            r35 = 0
            r36 = 1
            com.google.android.gms.measurement.internal.zzaq r10 = r26.zza(r27, r29, r30, r31, r32, r33, r34, r35, r36)     // Catch:{ all -> 0x1240 }
            long r12 = r10.zzg     // Catch:{ all -> 0x1240 }
            long r9 = (long) r9     // Catch:{ all -> 0x1240 }
            int r9 = (r12 > r9 ? 1 : (r12 == r9 ? 0 : -1))
            if (r9 <= 0) goto L_0x0b86
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r9 = com.google.android.gms.internal.measurement.zzfy.zzh.zze()     // Catch:{ all -> 0x1240 }
            java.lang.String r10 = "_tnr"
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r9 = r9.zza((java.lang.String) r10)     // Catch:{ all -> 0x1240 }
            r12 = 1
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r9 = r9.zza((long) r12)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzlc r9 = r9.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r9 = (com.google.android.gms.internal.measurement.zzjt) r9     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh r9 = (com.google.android.gms.internal.measurement.zzfy.zzh) r9     // Catch:{ all -> 0x1240 }
            r8.zza((com.google.android.gms.internal.measurement.zzfy.zzh) r9)     // Catch:{ all -> 0x1240 }
            goto L_0x0ca2
        L_0x0b86:
            com.google.android.gms.measurement.internal.zzag r9 = r43.zze()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r10 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r10 = r10.zzz()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzbh.zzcj     // Catch:{ all -> 0x1240 }
            boolean r9 = r9.zze(r10, r12)     // Catch:{ all -> 0x1240 }
            if (r9 == 0) goto L_0x0bba
            com.google.android.gms.measurement.internal.zzos r9 = r43.zzq()     // Catch:{ all -> 0x1240 }
            java.lang.String r9 = r9.zzp()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r10 = com.google.android.gms.internal.measurement.zzfy.zzh.zze()     // Catch:{ all -> 0x1240 }
            java.lang.String r12 = "_tu"
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r10 = r10.zza((java.lang.String) r12)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r10 = r10.zzb(r9)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzlc r10 = r10.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r10 = (com.google.android.gms.internal.measurement.zzjt) r10     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh r10 = (com.google.android.gms.internal.measurement.zzfy.zzh) r10     // Catch:{ all -> 0x1240 }
            r8.zza((com.google.android.gms.internal.measurement.zzfy.zzh) r10)     // Catch:{ all -> 0x1240 }
            goto L_0x0bbb
        L_0x0bba:
            r9 = 0
        L_0x0bbb:
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r10 = com.google.android.gms.internal.measurement.zzfy.zzh.zze()     // Catch:{ all -> 0x1240 }
            java.lang.String r12 = "_tr"
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r10 = r10.zza((java.lang.String) r12)     // Catch:{ all -> 0x1240 }
            r12 = 1
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r10 = r10.zza((long) r12)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzlc r10 = r10.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r10 = (com.google.android.gms.internal.measurement.zzjt) r10     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh r10 = (com.google.android.gms.internal.measurement.zzfy.zzh) r10     // Catch:{ all -> 0x1240 }
            r8.zza((com.google.android.gms.internal.measurement.zzfy.zzh) r10)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzoo r10 = r43.zzp()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r12 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r12 = r12.zzz()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzno r9 = r10.zza((java.lang.String) r12, (com.google.android.gms.internal.measurement.zzfy.zzk.zza) r6, (com.google.android.gms.internal.measurement.zzfy.zzf.zza) r8, (java.lang.String) r9)     // Catch:{ all -> 0x1240 }
            if (r9 == 0) goto L_0x0ca2
            com.google.android.gms.measurement.internal.zzgo r10 = r43.zzj()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgq r10 = r10.zzp()     // Catch:{ all -> 0x1240 }
            java.lang.String r12 = "Generated trigger URI. appId, uri"
            com.google.android.gms.internal.measurement.zzfy$zzk r13 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r13 = r13.zzz()     // Catch:{ all -> 0x1240 }
            java.lang.String r14 = r9.zza     // Catch:{ all -> 0x1240 }
            r10.zza(r12, r13, r14)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzal r10 = r43.zzf()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r12 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r12 = r12.zzz()     // Catch:{ all -> 0x1240 }
            r10.zza((java.lang.String) r12, (com.google.android.gms.measurement.internal.zzno) r9)     // Catch:{ all -> 0x1240 }
            java.util.Set<java.lang.String> r9 = r1.zzr     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r10 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r10 = r10.zzz()     // Catch:{ all -> 0x1240 }
            r9.add(r10)     // Catch:{ all -> 0x1240 }
            goto L_0x0ca2
        L_0x0c15:
            com.google.android.gms.measurement.internal.zzag r9 = r43.zze()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r10 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r10 = r10.zzz()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzbh.zzcj     // Catch:{ all -> 0x1240 }
            boolean r9 = r9.zze(r10, r12)     // Catch:{ all -> 0x1240 }
            if (r9 == 0) goto L_0x0c49
            com.google.android.gms.measurement.internal.zzos r9 = r43.zzq()     // Catch:{ all -> 0x1240 }
            java.lang.String r9 = r9.zzp()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r10 = com.google.android.gms.internal.measurement.zzfy.zzh.zze()     // Catch:{ all -> 0x1240 }
            java.lang.String r12 = "_tu"
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r10 = r10.zza((java.lang.String) r12)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r10 = r10.zzb(r9)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzlc r10 = r10.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r10 = (com.google.android.gms.internal.measurement.zzjt) r10     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh r10 = (com.google.android.gms.internal.measurement.zzfy.zzh) r10     // Catch:{ all -> 0x1240 }
            r8.zza((com.google.android.gms.internal.measurement.zzfy.zzh) r10)     // Catch:{ all -> 0x1240 }
            goto L_0x0c4a
        L_0x0c49:
            r9 = 0
        L_0x0c4a:
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r10 = com.google.android.gms.internal.measurement.zzfy.zzh.zze()     // Catch:{ all -> 0x1240 }
            java.lang.String r12 = "_tr"
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r10 = r10.zza((java.lang.String) r12)     // Catch:{ all -> 0x1240 }
            r12 = 1
            com.google.android.gms.internal.measurement.zzfy$zzh$zza r10 = r10.zza((long) r12)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzlc r10 = r10.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r10 = (com.google.android.gms.internal.measurement.zzjt) r10     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh r10 = (com.google.android.gms.internal.measurement.zzfy.zzh) r10     // Catch:{ all -> 0x1240 }
            r8.zza((com.google.android.gms.internal.measurement.zzfy.zzh) r10)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzoo r10 = r43.zzp()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r12 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r12 = r12.zzz()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzno r9 = r10.zza((java.lang.String) r12, (com.google.android.gms.internal.measurement.zzfy.zzk.zza) r6, (com.google.android.gms.internal.measurement.zzfy.zzf.zza) r8, (java.lang.String) r9)     // Catch:{ all -> 0x1240 }
            if (r9 == 0) goto L_0x0ca2
            com.google.android.gms.measurement.internal.zzgo r10 = r43.zzj()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgq r10 = r10.zzp()     // Catch:{ all -> 0x1240 }
            java.lang.String r12 = "Generated trigger URI. appId, uri"
            com.google.android.gms.internal.measurement.zzfy$zzk r13 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r13 = r13.zzz()     // Catch:{ all -> 0x1240 }
            java.lang.String r14 = r9.zza     // Catch:{ all -> 0x1240 }
            r10.zza(r12, r13, r14)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzal r10 = r43.zzf()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r12 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r12 = r12.zzz()     // Catch:{ all -> 0x1240 }
            r10.zza((java.lang.String) r12, (com.google.android.gms.measurement.internal.zzno) r9)     // Catch:{ all -> 0x1240 }
            java.util.Set<java.lang.String> r9 = r1.zzr     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r10 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r10 = r10.zzz()     // Catch:{ all -> 0x1240 }
            r9.add(r10)     // Catch:{ all -> 0x1240 }
        L_0x0ca2:
            com.google.android.gms.internal.measurement.zzlc r8 = r8.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r8 = (com.google.android.gms.internal.measurement.zzjt) r8     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzf r8 = (com.google.android.gms.internal.measurement.zzfy.zzf) r8     // Catch:{ all -> 0x1240 }
            r6.zza((int) r7, (com.google.android.gms.internal.measurement.zzfy.zzf) r8)     // Catch:{ all -> 0x1240 }
        L_0x0cad:
            int r7 = r7 + 1
            goto L_0x0ae2
        L_0x0cb1:
            boolean r7 = com.google.android.gms.internal.measurement.zznm.zza()     // Catch:{ all -> 0x1240 }
            if (r7 == 0) goto L_0x0cf8
            com.google.android.gms.measurement.internal.zzag r7 = r43.zze()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbh.zzcy     // Catch:{ all -> 0x1240 }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r8)     // Catch:{ all -> 0x1240 }
            if (r7 == 0) goto L_0x0cf8
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r7 = r6.zzi()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzt r8 = r43.zzc()     // Catch:{ all -> 0x1240 }
            java.lang.String r9 = r6.zzt()     // Catch:{ all -> 0x1240 }
            java.util.List r10 = r6.zzaa()     // Catch:{ all -> 0x1240 }
            java.util.List r11 = r6.zzab()     // Catch:{ all -> 0x1240 }
            long r12 = r6.zzf()     // Catch:{ all -> 0x1240 }
            java.lang.Long r12 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x1240 }
            long r13 = r6.zze()     // Catch:{ all -> 0x1240 }
            java.lang.Long r13 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x1240 }
            boolean r5 = r5.zzh()     // Catch:{ all -> 0x1240 }
            if (r5 != 0) goto L_0x0cef
            r14 = 1
            goto L_0x0cf0
        L_0x0cef:
            r14 = 0
        L_0x0cf0:
            java.util.List r5 = r8.zza(r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x1240 }
            r7.zza((java.lang.Iterable<? extends com.google.android.gms.internal.measurement.zzfy.zzd>) r5)     // Catch:{ all -> 0x1240 }
            goto L_0x0d23
        L_0x0cf8:
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r5 = r6.zzi()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzt r7 = r43.zzc()     // Catch:{ all -> 0x1240 }
            java.lang.String r8 = r6.zzt()     // Catch:{ all -> 0x1240 }
            java.util.List r9 = r6.zzaa()     // Catch:{ all -> 0x1240 }
            java.util.List r10 = r6.zzab()     // Catch:{ all -> 0x1240 }
            long r11 = r6.zzf()     // Catch:{ all -> 0x1240 }
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x1240 }
            long r12 = r6.zze()     // Catch:{ all -> 0x1240 }
            java.lang.Long r12 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x1240 }
            java.util.List r7 = r7.zza(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x1240 }
            r5.zza((java.lang.Iterable<? extends com.google.android.gms.internal.measurement.zzfy.zzd>) r7)     // Catch:{ all -> 0x1240 }
        L_0x0d23:
            com.google.android.gms.measurement.internal.zzag r5 = r43.zze()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r7 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r7 = r7.zzz()     // Catch:{ all -> 0x1240 }
            boolean r5 = r5.zzk(r7)     // Catch:{ all -> 0x1240 }
            if (r5 == 0) goto L_0x1077
            java.util.HashMap r5 = new java.util.HashMap     // Catch:{ all -> 0x1240 }
            r5.<init>()     // Catch:{ all -> 0x1240 }
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ all -> 0x1240 }
            r7.<init>()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzos r8 = r43.zzq()     // Catch:{ all -> 0x1240 }
            java.security.SecureRandom r8 = r8.zzv()     // Catch:{ all -> 0x1240 }
            r9 = 0
        L_0x0d46:
            int r10 = r6.zzc()     // Catch:{ all -> 0x1240 }
            if (r9 >= r10) goto L_0x103e
            com.google.android.gms.internal.measurement.zzfy$zzf r10 = r6.zza((int) r9)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt$zzb r10 = r10.zzcd()     // Catch:{ all -> 0x1240 }
            r11 = r10
            com.google.android.gms.internal.measurement.zzjt$zzb r11 = (com.google.android.gms.internal.measurement.zzjt.zzb) r11     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzf$zza r10 = (com.google.android.gms.internal.measurement.zzfy.zzf.zza) r10     // Catch:{ all -> 0x1240 }
            java.lang.String r11 = r10.zze()     // Catch:{ all -> 0x1240 }
            java.lang.String r12 = "_ep"
            boolean r11 = r11.equals(r12)     // Catch:{ all -> 0x1240 }
            java.lang.String r12 = "_sr"
            if (r11 == 0) goto L_0x0deb
            r43.zzp()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzlc r11 = r10.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r11 = (com.google.android.gms.internal.measurement.zzjt) r11     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzf r11 = (com.google.android.gms.internal.measurement.zzfy.zzf) r11     // Catch:{ all -> 0x1240 }
            java.lang.String r13 = "_en"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzoo.zzb(r11, r13)     // Catch:{ all -> 0x1240 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x1240 }
            java.lang.Object r13 = r5.get(r11)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzbb r13 = (com.google.android.gms.measurement.internal.zzbb) r13     // Catch:{ all -> 0x1240 }
            if (r13 != 0) goto L_0x0d9b
            com.google.android.gms.measurement.internal.zzal r13 = r43.zzf()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r14 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r14 = r14.zzz()     // Catch:{ all -> 0x1240 }
            java.lang.Object r15 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r11)     // Catch:{ all -> 0x1240 }
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzbb r13 = r13.zzd(r14, r15)     // Catch:{ all -> 0x1240 }
            if (r13 == 0) goto L_0x0d9b
            r5.put(r11, r13)     // Catch:{ all -> 0x1240 }
        L_0x0d9b:
            if (r13 == 0) goto L_0x0dde
            java.lang.Long r11 = r13.zzi     // Catch:{ all -> 0x1240 }
            if (r11 != 0) goto L_0x0dde
            java.lang.Long r11 = r13.zzj     // Catch:{ all -> 0x1240 }
            if (r11 == 0) goto L_0x0db9
            java.lang.Long r11 = r13.zzj     // Catch:{ all -> 0x1240 }
            long r14 = r11.longValue()     // Catch:{ all -> 0x1240 }
            r18 = 1
            int r11 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1))
            if (r11 <= 0) goto L_0x0db9
            r43.zzp()     // Catch:{ all -> 0x1240 }
            java.lang.Long r11 = r13.zzj     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzoo.zza((com.google.android.gms.internal.measurement.zzfy.zzf.zza) r10, (java.lang.String) r12, (java.lang.Object) r11)     // Catch:{ all -> 0x1240 }
        L_0x0db9:
            java.lang.Boolean r11 = r13.zzk     // Catch:{ all -> 0x1240 }
            if (r11 == 0) goto L_0x0dd3
            java.lang.Boolean r11 = r13.zzk     // Catch:{ all -> 0x1240 }
            boolean r11 = r11.booleanValue()     // Catch:{ all -> 0x1240 }
            if (r11 == 0) goto L_0x0dd3
            r43.zzp()     // Catch:{ all -> 0x1240 }
            java.lang.String r11 = "_efs"
            r12 = 1
            java.lang.Long r14 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzoo.zza((com.google.android.gms.internal.measurement.zzfy.zzf.zza) r10, (java.lang.String) r11, (java.lang.Object) r14)     // Catch:{ all -> 0x1240 }
        L_0x0dd3:
            com.google.android.gms.internal.measurement.zzlc r11 = r10.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r11 = (com.google.android.gms.internal.measurement.zzjt) r11     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzf r11 = (com.google.android.gms.internal.measurement.zzfy.zzf) r11     // Catch:{ all -> 0x1240 }
            r7.add(r11)     // Catch:{ all -> 0x1240 }
        L_0x0dde:
            r6.zza((int) r9, (com.google.android.gms.internal.measurement.zzfy.zzf.zza) r10)     // Catch:{ all -> 0x1240 }
        L_0x0de1:
            r18 = r4
            r3 = r5
            r46 = r8
            r1 = r9
            r8 = 1
            goto L_0x1030
        L_0x0deb:
            com.google.android.gms.measurement.internal.zzhl r11 = r43.zzi()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r13 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r13 = r13.zzz()     // Catch:{ all -> 0x1240 }
            long r13 = r11.zza((java.lang.String) r13)     // Catch:{ all -> 0x1240 }
            r43.zzq()     // Catch:{ all -> 0x1240 }
            long r2 = r10.zzc()     // Catch:{ all -> 0x1240 }
            long r2 = com.google.android.gms.measurement.internal.zzos.zza((long) r2, (long) r13)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzlc r11 = r10.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r11 = (com.google.android.gms.internal.measurement.zzjt) r11     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzf r11 = (com.google.android.gms.internal.measurement.zzfy.zzf) r11     // Catch:{ all -> 0x1240 }
            java.lang.String r15 = "_dbg"
            r18 = 1
            java.lang.Long r1 = java.lang.Long.valueOf(r18)     // Catch:{ all -> 0x1240 }
            boolean r18 = android.text.TextUtils.isEmpty(r15)     // Catch:{ all -> 0x1240 }
            if (r18 != 0) goto L_0x0e74
            if (r1 != 0) goto L_0x0e1d
            goto L_0x0e74
        L_0x0e1d:
            java.util.List r11 = r11.zzh()     // Catch:{ all -> 0x1240 }
            java.util.Iterator r11 = r11.iterator()     // Catch:{ all -> 0x1240 }
        L_0x0e25:
            boolean r18 = r11.hasNext()     // Catch:{ all -> 0x1240 }
            if (r18 == 0) goto L_0x0e74
            java.lang.Object r18 = r11.next()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzh r18 = (com.google.android.gms.internal.measurement.zzfy.zzh) r18     // Catch:{ all -> 0x1240 }
            r46 = r11
            java.lang.String r11 = r18.zzg()     // Catch:{ all -> 0x1240 }
            boolean r11 = r15.equals(r11)     // Catch:{ all -> 0x1240 }
            if (r11 == 0) goto L_0x0e71
            boolean r11 = r1 instanceof java.lang.Long     // Catch:{ all -> 0x1240 }
            if (r11 == 0) goto L_0x0e4f
            long r21 = r18.zzd()     // Catch:{ all -> 0x1240 }
            java.lang.Long r11 = java.lang.Long.valueOf(r21)     // Catch:{ all -> 0x1240 }
            boolean r11 = r1.equals(r11)     // Catch:{ all -> 0x1240 }
            if (r11 != 0) goto L_0x0e6f
        L_0x0e4f:
            boolean r11 = r1 instanceof java.lang.String     // Catch:{ all -> 0x1240 }
            if (r11 == 0) goto L_0x0e5d
            java.lang.String r11 = r18.zzh()     // Catch:{ all -> 0x1240 }
            boolean r11 = r1.equals(r11)     // Catch:{ all -> 0x1240 }
            if (r11 != 0) goto L_0x0e6f
        L_0x0e5d:
            boolean r11 = r1 instanceof java.lang.Double     // Catch:{ all -> 0x1240 }
            if (r11 == 0) goto L_0x0e74
            double r21 = r18.zza()     // Catch:{ all -> 0x1240 }
            java.lang.Double r11 = java.lang.Double.valueOf(r21)     // Catch:{ all -> 0x1240 }
            boolean r1 = r1.equals(r11)     // Catch:{ all -> 0x1240 }
            if (r1 == 0) goto L_0x0e74
        L_0x0e6f:
            r1 = 1
            goto L_0x0e75
        L_0x0e71:
            r11 = r46
            goto L_0x0e25
        L_0x0e74:
            r1 = 0
        L_0x0e75:
            if (r1 != 0) goto L_0x0e8a
            com.google.android.gms.measurement.internal.zzhl r1 = r43.zzi()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r11 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r11 = r11.zzz()     // Catch:{ all -> 0x1240 }
            java.lang.String r15 = r10.zze()     // Catch:{ all -> 0x1240 }
            int r1 = r1.zzb((java.lang.String) r11, (java.lang.String) r15)     // Catch:{ all -> 0x1240 }
            goto L_0x0e8b
        L_0x0e8a:
            r1 = 1
        L_0x0e8b:
            if (r1 > 0) goto L_0x0eb2
            com.google.android.gms.measurement.internal.zzgo r2 = r43.zzj()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzu()     // Catch:{ all -> 0x1240 }
            java.lang.String r3 = "Sample rate must be positive. event, rate"
            java.lang.String r11 = r10.zze()     // Catch:{ all -> 0x1240 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x1240 }
            r2.zza(r3, r11, r1)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzlc r1 = r10.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r1 = (com.google.android.gms.internal.measurement.zzjt) r1     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzf r1 = (com.google.android.gms.internal.measurement.zzfy.zzf) r1     // Catch:{ all -> 0x1240 }
            r7.add(r1)     // Catch:{ all -> 0x1240 }
            r6.zza((int) r9, (com.google.android.gms.internal.measurement.zzfy.zzf.zza) r10)     // Catch:{ all -> 0x1240 }
            goto L_0x0de1
        L_0x0eb2:
            java.lang.String r11 = r10.zze()     // Catch:{ all -> 0x1240 }
            java.lang.Object r11 = r5.get(r11)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzbb r11 = (com.google.android.gms.measurement.internal.zzbb) r11     // Catch:{ all -> 0x1240 }
            if (r11 != 0) goto L_0x0f11
            com.google.android.gms.measurement.internal.zzal r11 = r43.zzf()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r15 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r15 = r15.zzz()     // Catch:{ all -> 0x1240 }
            r21 = r13
            java.lang.String r13 = r10.zze()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzbb r11 = r11.zzd(r15, r13)     // Catch:{ all -> 0x1240 }
            if (r11 != 0) goto L_0x0f13
            com.google.android.gms.measurement.internal.zzgo r11 = r43.zzj()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgq r11 = r11.zzu()     // Catch:{ all -> 0x1240 }
            java.lang.String r13 = "Event being bundled has no eventAggregate. appId, eventName"
            com.google.android.gms.internal.measurement.zzfy$zzk r14 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r14 = r14.zzz()     // Catch:{ all -> 0x1240 }
            java.lang.String r15 = r10.zze()     // Catch:{ all -> 0x1240 }
            r11.zza(r13, r14, r15)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzbb r11 = new com.google.android.gms.measurement.internal.zzbb     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r13 = r4.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r27 = r13.zzz()     // Catch:{ all -> 0x1240 }
            java.lang.String r28 = r10.zze()     // Catch:{ all -> 0x1240 }
            r29 = 1
            r31 = 1
            r33 = 1
            long r35 = r10.zzc()     // Catch:{ all -> 0x1240 }
            r37 = 0
            r39 = 0
            r40 = 0
            r41 = 0
            r42 = 0
            r26 = r11
            r26.<init>(r27, r28, r29, r31, r33, r35, r37, r39, r40, r41, r42)     // Catch:{ all -> 0x1240 }
            goto L_0x0f13
        L_0x0f11:
            r21 = r13
        L_0x0f13:
            r43.zzp()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzlc r13 = r10.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r13 = (com.google.android.gms.internal.measurement.zzjt) r13     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzf r13 = (com.google.android.gms.internal.measurement.zzfy.zzf) r13     // Catch:{ all -> 0x1240 }
            java.lang.String r14 = "_eid"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzoo.zzb(r13, r14)     // Catch:{ all -> 0x1240 }
            java.lang.Long r13 = (java.lang.Long) r13     // Catch:{ all -> 0x1240 }
            if (r13 == 0) goto L_0x0f2a
            r14 = 1
            goto L_0x0f2b
        L_0x0f2a:
            r14 = 0
        L_0x0f2b:
            java.lang.Boolean r14 = java.lang.Boolean.valueOf(r14)     // Catch:{ all -> 0x1240 }
            r15 = 1
            if (r1 != r15) goto L_0x0f60
            com.google.android.gms.internal.measurement.zzlc r1 = r10.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r1 = (com.google.android.gms.internal.measurement.zzjt) r1     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzf r1 = (com.google.android.gms.internal.measurement.zzfy.zzf) r1     // Catch:{ all -> 0x1240 }
            r7.add(r1)     // Catch:{ all -> 0x1240 }
            boolean r1 = r14.booleanValue()     // Catch:{ all -> 0x1240 }
            if (r1 == 0) goto L_0x0f5b
            java.lang.Long r1 = r11.zzi     // Catch:{ all -> 0x1240 }
            if (r1 != 0) goto L_0x0f4f
            java.lang.Long r1 = r11.zzj     // Catch:{ all -> 0x1240 }
            if (r1 != 0) goto L_0x0f4f
            java.lang.Boolean r1 = r11.zzk     // Catch:{ all -> 0x1240 }
            if (r1 == 0) goto L_0x0f5b
        L_0x0f4f:
            r1 = 0
            com.google.android.gms.measurement.internal.zzbb r2 = r11.zza(r1, r1, r1)     // Catch:{ all -> 0x1240 }
            java.lang.String r1 = r10.zze()     // Catch:{ all -> 0x1240 }
            r5.put(r1, r2)     // Catch:{ all -> 0x1240 }
        L_0x0f5b:
            r6.zza((int) r9, (com.google.android.gms.internal.measurement.zzfy.zzf.zza) r10)     // Catch:{ all -> 0x1240 }
            goto L_0x0de1
        L_0x0f60:
            int r15 = r8.nextInt(r1)     // Catch:{ all -> 0x1240 }
            if (r15 != 0) goto L_0x0fa5
            r43.zzp()     // Catch:{ all -> 0x1240 }
            r46 = r8
            r15 = r9
            long r8 = (long) r1     // Catch:{ all -> 0x1240 }
            java.lang.Long r1 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzoo.zza((com.google.android.gms.internal.measurement.zzfy.zzf.zza) r10, (java.lang.String) r12, (java.lang.Object) r1)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzlc r1 = r10.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r1 = (com.google.android.gms.internal.measurement.zzjt) r1     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzf r1 = (com.google.android.gms.internal.measurement.zzfy.zzf) r1     // Catch:{ all -> 0x1240 }
            r7.add(r1)     // Catch:{ all -> 0x1240 }
            boolean r1 = r14.booleanValue()     // Catch:{ all -> 0x1240 }
            if (r1 == 0) goto L_0x0f8e
            java.lang.Long r1 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x1240 }
            r8 = 0
            com.google.android.gms.measurement.internal.zzbb r11 = r11.zza(r8, r1, r8)     // Catch:{ all -> 0x1240 }
        L_0x0f8e:
            java.lang.String r1 = r10.zze()     // Catch:{ all -> 0x1240 }
            long r8 = r10.zzc()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzbb r2 = r11.zza(r8, r2)     // Catch:{ all -> 0x1240 }
            r5.put(r1, r2)     // Catch:{ all -> 0x1240 }
            r18 = r4
            r3 = r5
            r1 = r15
            r8 = 1
            goto L_0x102d
        L_0x0fa5:
            r46 = r8
            r15 = r9
            java.lang.Long r8 = r11.zzh     // Catch:{ all -> 0x1240 }
            if (r8 == 0) goto L_0x0fb7
            java.lang.Long r8 = r11.zzh     // Catch:{ all -> 0x1240 }
            long r8 = r8.longValue()     // Catch:{ all -> 0x1240 }
            r18 = r4
            r23 = r5
            goto L_0x0fc8
        L_0x0fb7:
            r43.zzq()     // Catch:{ all -> 0x1240 }
            long r8 = r10.zzb()     // Catch:{ all -> 0x1240 }
            r18 = r4
            r23 = r5
            r4 = r21
            long r8 = com.google.android.gms.measurement.internal.zzos.zza((long) r8, (long) r4)     // Catch:{ all -> 0x1240 }
        L_0x0fc8:
            int r4 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x1016
            r43.zzp()     // Catch:{ all -> 0x1240 }
            java.lang.String r4 = "_efs"
            r8 = 1
            java.lang.Long r5 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzoo.zza((com.google.android.gms.internal.measurement.zzfy.zzf.zza) r10, (java.lang.String) r4, (java.lang.Object) r5)     // Catch:{ all -> 0x1240 }
            r43.zzp()     // Catch:{ all -> 0x1240 }
            long r4 = (long) r1     // Catch:{ all -> 0x1240 }
            java.lang.Long r1 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzoo.zza((com.google.android.gms.internal.measurement.zzfy.zzf.zza) r10, (java.lang.String) r12, (java.lang.Object) r1)     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzlc r1 = r10.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r1 = (com.google.android.gms.internal.measurement.zzjt) r1     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzf r1 = (com.google.android.gms.internal.measurement.zzfy.zzf) r1     // Catch:{ all -> 0x1240 }
            r7.add(r1)     // Catch:{ all -> 0x1240 }
            boolean r1 = r14.booleanValue()     // Catch:{ all -> 0x1240 }
            if (r1 == 0) goto L_0x1004
            java.lang.Long r1 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x1240 }
            r4 = 1
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r4)     // Catch:{ all -> 0x1240 }
            r4 = 0
            com.google.android.gms.measurement.internal.zzbb r11 = r11.zza(r4, r1, r5)     // Catch:{ all -> 0x1240 }
        L_0x1004:
            java.lang.String r1 = r10.zze()     // Catch:{ all -> 0x1240 }
            long r4 = r10.zzc()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzbb r2 = r11.zza(r4, r2)     // Catch:{ all -> 0x1240 }
            r3 = r23
            r3.put(r1, r2)     // Catch:{ all -> 0x1240 }
            goto L_0x102c
        L_0x1016:
            r3 = r23
            r8 = 1
            boolean r1 = r14.booleanValue()     // Catch:{ all -> 0x1240 }
            if (r1 == 0) goto L_0x102c
            java.lang.String r1 = r10.zze()     // Catch:{ all -> 0x1240 }
            r2 = 0
            com.google.android.gms.measurement.internal.zzbb r4 = r11.zza(r13, r2, r2)     // Catch:{ all -> 0x1240 }
            r3.put(r1, r4)     // Catch:{ all -> 0x1240 }
        L_0x102c:
            r1 = r15
        L_0x102d:
            r6.zza((int) r1, (com.google.android.gms.internal.measurement.zzfy.zzf.zza) r10)     // Catch:{ all -> 0x1240 }
        L_0x1030:
            int r1 = r1 + 1
            r8 = r46
            r9 = r1
            r5 = r3
            r4 = r18
            r2 = 0
            r1 = r43
            goto L_0x0d46
        L_0x103e:
            r18 = r4
            r3 = r5
            int r1 = r7.size()     // Catch:{ all -> 0x1240 }
            int r2 = r6.zzc()     // Catch:{ all -> 0x1240 }
            if (r1 >= r2) goto L_0x1052
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r1 = r6.zzl()     // Catch:{ all -> 0x1240 }
            r1.zzb((java.lang.Iterable<? extends com.google.android.gms.internal.measurement.zzfy.zzf>) r7)     // Catch:{ all -> 0x1240 }
        L_0x1052:
            java.util.Set r1 = r3.entrySet()     // Catch:{ all -> 0x1240 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x1240 }
        L_0x105a:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x1240 }
            if (r2 == 0) goto L_0x1074
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x1240 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzal r3 = r43.zzf()     // Catch:{ all -> 0x1240 }
            java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzbb r2 = (com.google.android.gms.measurement.internal.zzbb) r2     // Catch:{ all -> 0x1240 }
            r3.zza((com.google.android.gms.measurement.internal.zzbb) r2)     // Catch:{ all -> 0x1240 }
            goto L_0x105a
        L_0x1074:
            r1 = r18
            goto L_0x1078
        L_0x1077:
            r1 = r4
        L_0x1078:
            com.google.android.gms.internal.measurement.zzfy$zzk r2 = r1.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r2 = r2.zzz()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzal r3 = r43.zzf()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzg r3 = r3.zze(r2)     // Catch:{ all -> 0x1240 }
            if (r3 != 0) goto L_0x10a1
            com.google.android.gms.measurement.internal.zzgo r3 = r43.zzj()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzg()     // Catch:{ all -> 0x1240 }
            java.lang.String r4 = "Bundling raw events w/o app info. appId"
            com.google.android.gms.internal.measurement.zzfy$zzk r5 = r1.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r5 = r5.zzz()     // Catch:{ all -> 0x1240 }
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r5)     // Catch:{ all -> 0x1240 }
            r3.zza(r4, r5)     // Catch:{ all -> 0x1240 }
            goto L_0x112d
        L_0x10a1:
            int r4 = r6.zzc()     // Catch:{ all -> 0x1240 }
            if (r4 <= 0) goto L_0x112d
            long r4 = r3.zzs()     // Catch:{ all -> 0x1240 }
            r7 = 0
            int r9 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x10b5
            r6.zzg((long) r4)     // Catch:{ all -> 0x1240 }
            goto L_0x10b8
        L_0x10b5:
            r6.zzo()     // Catch:{ all -> 0x1240 }
        L_0x10b8:
            long r7 = r3.zzu()     // Catch:{ all -> 0x1240 }
            r9 = 0
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x10c3
            goto L_0x10c4
        L_0x10c3:
            r4 = r7
        L_0x10c4:
            int r7 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r7 == 0) goto L_0x10cc
            r6.zzh((long) r4)     // Catch:{ all -> 0x1240 }
            goto L_0x10cf
        L_0x10cc:
            r6.zzp()     // Catch:{ all -> 0x1240 }
        L_0x10cf:
            boolean r4 = com.google.android.gms.internal.measurement.zzpu.zza()     // Catch:{ all -> 0x1240 }
            if (r4 == 0) goto L_0x10ff
            com.google.android.gms.measurement.internal.zzag r4 = r43.zze()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzbh.zzbx     // Catch:{ all -> 0x1240 }
            boolean r4 = r4.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r5)     // Catch:{ all -> 0x1240 }
            if (r4 == 0) goto L_0x10ff
            r43.zzq()     // Catch:{ all -> 0x1240 }
            java.lang.String r4 = r3.zzac()     // Catch:{ all -> 0x1240 }
            boolean r4 = com.google.android.gms.measurement.internal.zzos.zzf(r4)     // Catch:{ all -> 0x1240 }
            if (r4 == 0) goto L_0x10ff
            int r4 = r6.zzc()     // Catch:{ all -> 0x1240 }
            long r4 = (long) r4     // Catch:{ all -> 0x1240 }
            r3.zza((long) r4)     // Catch:{ all -> 0x1240 }
            long r4 = r3.zzr()     // Catch:{ all -> 0x1240 }
            int r4 = (int) r4     // Catch:{ all -> 0x1240 }
            r6.zzg((int) r4)     // Catch:{ all -> 0x1240 }
            goto L_0x1102
        L_0x10ff:
            r3.zzap()     // Catch:{ all -> 0x1240 }
        L_0x1102:
            long r4 = r3.zzt()     // Catch:{ all -> 0x1240 }
            int r4 = (int) r4     // Catch:{ all -> 0x1240 }
            r6.zzf((int) r4)     // Catch:{ all -> 0x1240 }
            long r4 = r6.zzf()     // Catch:{ all -> 0x1240 }
            r3.zzr(r4)     // Catch:{ all -> 0x1240 }
            long r4 = r6.zze()     // Catch:{ all -> 0x1240 }
            r3.zzp(r4)     // Catch:{ all -> 0x1240 }
            java.lang.String r4 = r3.zzab()     // Catch:{ all -> 0x1240 }
            if (r4 == 0) goto L_0x1122
            r6.zzn(r4)     // Catch:{ all -> 0x1240 }
            goto L_0x1125
        L_0x1122:
            r6.zzm()     // Catch:{ all -> 0x1240 }
        L_0x1125:
            com.google.android.gms.measurement.internal.zzal r4 = r43.zzf()     // Catch:{ all -> 0x1240 }
            r5 = 0
            r4.zza((com.google.android.gms.measurement.internal.zzg) r3, (boolean) r5, (boolean) r5)     // Catch:{ all -> 0x1240 }
        L_0x112d:
            int r3 = r6.zzc()     // Catch:{ all -> 0x1240 }
            if (r3 <= 0) goto L_0x118c
            com.google.android.gms.measurement.internal.zzhl r3 = r43.zzi()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r4 = r1.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r4 = r4.zzz()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfr$zzd r3 = r3.zzc(r4)     // Catch:{ all -> 0x1240 }
            if (r3 == 0) goto L_0x1152
            boolean r4 = r3.zzr()     // Catch:{ all -> 0x1240 }
            if (r4 != 0) goto L_0x114a
            goto L_0x1152
        L_0x114a:
            long r3 = r3.zzc()     // Catch:{ all -> 0x1240 }
            r6.zzb((long) r3)     // Catch:{ all -> 0x1240 }
            goto L_0x117b
        L_0x1152:
            com.google.android.gms.internal.measurement.zzfy$zzk r3 = r1.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r3 = r3.zzaj()     // Catch:{ all -> 0x1240 }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x1240 }
            if (r3 == 0) goto L_0x1164
            r3 = -1
            r6.zzb((long) r3)     // Catch:{ all -> 0x1240 }
            goto L_0x117b
        L_0x1164:
            com.google.android.gms.measurement.internal.zzgo r3 = r43.zzj()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzu()     // Catch:{ all -> 0x1240 }
            java.lang.String r4 = "Did not find measurement config or missing version info. appId"
            com.google.android.gms.internal.measurement.zzfy$zzk r5 = r1.zza     // Catch:{ all -> 0x1240 }
            java.lang.String r5 = r5.zzz()     // Catch:{ all -> 0x1240 }
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r5)     // Catch:{ all -> 0x1240 }
            r3.zza(r4, r5)     // Catch:{ all -> 0x1240 }
        L_0x117b:
            com.google.android.gms.measurement.internal.zzal r3 = r43.zzf()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzlc r4 = r6.zzai()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzjt r4 = (com.google.android.gms.internal.measurement.zzjt) r4     // Catch:{ all -> 0x1240 }
            com.google.android.gms.internal.measurement.zzfy$zzk r4 = (com.google.android.gms.internal.measurement.zzfy.zzk) r4     // Catch:{ all -> 0x1240 }
            r13 = r17
            r3.zza((com.google.android.gms.internal.measurement.zzfy.zzk) r4, (boolean) r13)     // Catch:{ all -> 0x1240 }
        L_0x118c:
            com.google.android.gms.measurement.internal.zzal r3 = r43.zzf()     // Catch:{ all -> 0x1240 }
            java.util.List<java.lang.Long> r1 = r1.zzb     // Catch:{ all -> 0x1240 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r1)     // Catch:{ all -> 0x1240 }
            r3.zzt()     // Catch:{ all -> 0x1240 }
            r3.zzal()     // Catch:{ all -> 0x1240 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x1240 }
            java.lang.String r5 = "rowid in ("
            r4.<init>(r5)     // Catch:{ all -> 0x1240 }
            r13 = 0
        L_0x11a3:
            int r5 = r1.size()     // Catch:{ all -> 0x1240 }
            if (r13 >= r5) goto L_0x11c0
            if (r13 == 0) goto L_0x11b0
            java.lang.String r5 = ","
            r4.append(r5)     // Catch:{ all -> 0x1240 }
        L_0x11b0:
            java.lang.Object r5 = r1.get(r13)     // Catch:{ all -> 0x1240 }
            java.lang.Long r5 = (java.lang.Long) r5     // Catch:{ all -> 0x1240 }
            long r5 = r5.longValue()     // Catch:{ all -> 0x1240 }
            r4.append(r5)     // Catch:{ all -> 0x1240 }
            int r13 = r13 + 1
            goto L_0x11a3
        L_0x11c0:
            java.lang.String r5 = ")"
            r4.append(r5)     // Catch:{ all -> 0x1240 }
            android.database.sqlite.SQLiteDatabase r5 = r3.e_()     // Catch:{ all -> 0x1240 }
            java.lang.String r6 = "raw_events"
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x1240 }
            r7 = 0
            int r4 = r5.delete(r6, r4, r7)     // Catch:{ all -> 0x1240 }
            int r5 = r1.size()     // Catch:{ all -> 0x1240 }
            if (r4 == r5) goto L_0x11f3
            com.google.android.gms.measurement.internal.zzgo r3 = r3.zzj()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzg()     // Catch:{ all -> 0x1240 }
            java.lang.String r5 = "Deleted fewer rows from raw events table than expected"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x1240 }
            int r1 = r1.size()     // Catch:{ all -> 0x1240 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x1240 }
            r3.zza(r5, r4, r1)     // Catch:{ all -> 0x1240 }
        L_0x11f3:
            com.google.android.gms.measurement.internal.zzal r1 = r43.zzf()     // Catch:{ all -> 0x1240 }
            android.database.sqlite.SQLiteDatabase r3 = r1.e_()     // Catch:{ all -> 0x1240 }
            java.lang.String r4 = "delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)"
            java.lang.String[] r5 = new java.lang.String[]{r2, r2}     // Catch:{ SQLiteException -> 0x1205 }
            r3.execSQL(r4, r5)     // Catch:{ SQLiteException -> 0x1205 }
            goto L_0x1218
        L_0x1205:
            r0 = move-exception
            r3 = r0
            com.google.android.gms.measurement.internal.zzgo r1 = r1.zzj()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzgq r1 = r1.zzg()     // Catch:{ all -> 0x1240 }
            java.lang.String r4 = "Failed to remove unused event metadata. appId"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r2)     // Catch:{ all -> 0x1240 }
            r1.zza(r4, r2, r3)     // Catch:{ all -> 0x1240 }
        L_0x1218:
            com.google.android.gms.measurement.internal.zzal r1 = r43.zzf()     // Catch:{ all -> 0x1240 }
            r1.zzw()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzal r1 = r43.zzf()
            r1.zzu()
            r1 = 1
            return r1
        L_0x1228:
            com.google.android.gms.measurement.internal.zzal r1 = r43.zzf()     // Catch:{ all -> 0x1240 }
            r1.zzw()     // Catch:{ all -> 0x1240 }
            com.google.android.gms.measurement.internal.zzal r1 = r43.zzf()
            r1.zzu()
            r1 = 0
            return r1
        L_0x1238:
            r0 = move-exception
            r1 = r0
        L_0x123a:
            if (r5 == 0) goto L_0x123f
            r5.close()     // Catch:{ all -> 0x1240 }
        L_0x123f:
            throw r1     // Catch:{ all -> 0x1240 }
        L_0x1240:
            r0 = move-exception
            r1 = r0
            com.google.android.gms.measurement.internal.zzal r2 = r43.zzf()
            r2.zzu()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zza(java.lang.String, long):boolean");
    }

    private final boolean zzad() {
        zzl().zzt();
        zzs();
        return zzf().zzx() || !TextUtils.isEmpty(zzf().f_());
    }

    private final boolean zzae() {
        zzl().zzt();
        FileLock fileLock = this.zzx;
        if (fileLock == null || !fileLock.isValid()) {
            try {
                FileChannel channel = new RandomAccessFile(new File(zzcf.zza().zza(this.zzm.zza().getFilesDir(), "google_app_measurement.db")), "rw").getChannel();
                this.zzy = channel;
                FileLock tryLock = channel.tryLock();
                this.zzx = tryLock;
                if (tryLock != null) {
                    zzj().zzp().zza("Storage concurrent access okay");
                    return true;
                }
                zzj().zzg().zza("Storage concurrent data access panic");
                return false;
            } catch (FileNotFoundException e) {
                zzj().zzg().zza("Failed to acquire storage lock", e);
                return false;
            } catch (IOException e2) {
                zzj().zzg().zza("Failed to access storage lock file", e2);
                return false;
            } catch (OverlappingFileLockException e3) {
                zzj().zzu().zza("Storage lock already acquired", e3);
                return false;
            }
        } else {
            zzj().zzp().zza("Storage concurrent access okay");
            return true;
        }
    }

    private final boolean zza(zzfy.zzf.zza zza2, zzfy.zzf.zza zza3) {
        String str;
        Preconditions.checkArgument("_e".equals(zza2.zze()));
        zzp();
        zzfy.zzh zza4 = zzoo.zza((zzfy.zzf) ((zzjt) zza2.zzai()), "_sc");
        String str2 = null;
        if (zza4 == null) {
            str = null;
        } else {
            str = zza4.zzh();
        }
        zzp();
        zzfy.zzh zza5 = zzoo.zza((zzfy.zzf) ((zzjt) zza3.zzai()), "_pc");
        if (zza5 != null) {
            str2 = zza5.zzh();
        }
        if (str2 == null || !str2.equals(str)) {
            return false;
        }
        Preconditions.checkArgument("_e".equals(zza2.zze()));
        zzp();
        zzfy.zzh zza6 = zzoo.zza((zzfy.zzf) ((zzjt) zza2.zzai()), "_et");
        if (zza6 == null || !zza6.zzl() || zza6.zzd() <= 0) {
            return true;
        }
        long zzd2 = zza6.zzd();
        zzp();
        zzfy.zzh zza7 = zzoo.zza((zzfy.zzf) ((zzjt) zza3.zzai()), "_et");
        if (zza7 != null && zza7.zzd() > 0) {
            zzd2 += zza7.zzd();
        }
        zzp();
        zzoo.zza(zza3, "_et", (Object) Long.valueOf(zzd2));
        zzp();
        zzoo.zza(zza2, "_fr", (Object) 1L);
        return true;
    }

    private final boolean zza(int i, FileChannel fileChannel) {
        zzl().zzt();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzj().zzg().zza("Bad channel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() != 4) {
                zzj().zzg().zza("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            }
            return true;
        } catch (IOException e) {
            zzj().zzg().zza("Failed to write to channel", e);
            return false;
        }
    }
}
