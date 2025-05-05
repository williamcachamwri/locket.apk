package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.amplitude.api.Constants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzjg;
import com.google.android.gms.internal.measurement.zzjt;
import com.google.android.gms.internal.measurement.zzkb;
import com.google.android.gms.internal.measurement.zzlb;
import com.google.android.gms.internal.measurement.zzov;
import com.google.android.gms.internal.measurement.zzpn;
import com.google.android.gms.internal.measurement.zzpo;
import com.google.android.gms.internal.measurement.zzpu;
import com.squareup.kotlinpoet.FileSpecKt;
import io.sentry.protocol.App;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import me.leolin.shortcutbadger.impl.NewHtcHomeBadger;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final class zzoo extends zznr {
    /* access modifiers changed from: protected */
    public final boolean zzc() {
        return false;
    }

    static int zza(zzfy.zzk.zza zza, String str) {
        if (zza == null) {
            return -1;
        }
        for (int i = 0; i < zza.zzd(); i++) {
            if (str.equals(zza.zzk(i).zzg())) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public final long zza(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return zza(str.getBytes(Charset.forName("UTF-8")));
    }

    /* access modifiers changed from: package-private */
    public final long zza(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        zzq().zzt();
        MessageDigest zzu = zzos.zzu();
        if (zzu != null) {
            return zzos.zza(zzu.digest(bArr));
        }
        zzj().zzg().zza("Failed to get MD5");
        return 0;
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    static Bundle zza(List<zzfy.zzh> list) {
        Bundle bundle = new Bundle();
        for (zzfy.zzh next : list) {
            String zzg = next.zzg();
            if (next.zzj()) {
                bundle.putDouble(zzg, next.zza());
            } else if (next.zzk()) {
                bundle.putFloat(zzg, next.zzb());
            } else if (next.zzn()) {
                bundle.putString(zzg, next.zzh());
            } else if (next.zzl()) {
                bundle.putLong(zzg, next.zzd());
            }
        }
        return bundle;
    }

    private final Bundle zza(Map<String, Object> map, boolean z) {
        Bundle bundle = new Bundle();
        for (String next : map.keySet()) {
            Object obj = map.get(next);
            if (obj == null) {
                bundle.putString(next, (String) null);
            } else if (obj instanceof Long) {
                bundle.putLong(next, ((Long) obj).longValue());
            } else if (obj instanceof Double) {
                bundle.putDouble(next, ((Double) obj).doubleValue());
            } else if (!(obj instanceof ArrayList)) {
                bundle.putString(next, obj.toString());
            } else if (z) {
                ArrayList arrayList = (ArrayList) obj;
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = arrayList;
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj2 = arrayList.get(i);
                    i++;
                    arrayList2.add(zza((Map<String, Object>) (Map) obj2, false));
                }
                bundle.putParcelableArray(next, (Parcelable[]) arrayList2.toArray(new Parcelable[0]));
            }
        }
        return bundle;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        zzj().zzg().zza("Failed to load parcelable from buffer");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r1.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x001c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T extends android.os.Parcelable> T zza(byte[] r5, android.os.Parcelable.Creator<T> r6) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            android.os.Parcel r1 = android.os.Parcel.obtain()
            int r2 = r5.length     // Catch:{ ParseException -> 0x001c }
            r3 = 0
            r1.unmarshall(r5, r3, r2)     // Catch:{ ParseException -> 0x001c }
            r1.setDataPosition(r3)     // Catch:{ ParseException -> 0x001c }
            java.lang.Object r5 = r6.createFromParcel(r1)     // Catch:{ ParseException -> 0x001c }
            android.os.Parcelable r5 = (android.os.Parcelable) r5     // Catch:{ ParseException -> 0x001c }
            r1.recycle()
            return r5
        L_0x001a:
            r5 = move-exception
            goto L_0x002d
        L_0x001c:
            com.google.android.gms.measurement.internal.zzgo r5 = r4.zzj()     // Catch:{ all -> 0x001a }
            com.google.android.gms.measurement.internal.zzgq r5 = r5.zzg()     // Catch:{ all -> 0x001a }
            java.lang.String r6 = "Failed to load parcelable from buffer"
            r5.zza(r6)     // Catch:{ all -> 0x001a }
            r1.recycle()
            return r0
        L_0x002d:
            r1.recycle()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzoo.zza(byte[], android.os.Parcelable$Creator):android.os.Parcelable");
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public final /* bridge */ /* synthetic */ zzt zzg() {
        return super.zzg();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzab zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzag zze() {
        return super.zze();
    }

    public final /* bridge */ /* synthetic */ zzal zzh() {
        return super.zzh();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzaz zzf() {
        return super.zzf();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0011, code lost:
        r1 = r0.get("_o");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzbf zza(com.google.android.gms.internal.measurement.zzad r9) {
        /*
            r8 = this;
            java.util.Map r0 = r9.zzc()
            r1 = 1
            android.os.Bundle r0 = r8.zza((java.util.Map<java.lang.String, java.lang.Object>) r0, (boolean) r1)
            java.lang.String r1 = "_o"
            boolean r2 = r0.containsKey(r1)
            if (r2 == 0) goto L_0x001c
            java.lang.Object r1 = r0.get(r1)
            if (r1 == 0) goto L_0x001c
            java.lang.String r1 = r1.toString()
            goto L_0x001e
        L_0x001c:
            java.lang.String r1 = "app"
        L_0x001e:
            r5 = r1
            java.lang.String r1 = r9.zzb()
            java.lang.String r1 = com.google.android.gms.measurement.internal.zzji.zzb(r1)
            if (r1 != 0) goto L_0x002d
            java.lang.String r1 = r9.zzb()
        L_0x002d:
            r3 = r1
            com.google.android.gms.measurement.internal.zzbf r1 = new com.google.android.gms.measurement.internal.zzbf
            com.google.android.gms.measurement.internal.zzbe r4 = new com.google.android.gms.measurement.internal.zzbe
            r4.<init>(r0)
            long r6 = r9.zza()
            r2 = r1
            r2.<init>(r3, r4, r5, r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzoo.zza(com.google.android.gms.internal.measurement.zzad):com.google.android.gms.measurement.internal.zzbf");
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

    public final /* bridge */ /* synthetic */ zzhl zzm() {
        return super.zzm();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhv zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ zzmw zzn() {
        return super.zzn();
    }

    /* access modifiers changed from: package-private */
    public final zzno zza(String str, zzfy.zzk.zza zza, zzfy.zzf.zza zza2, String str2) {
        int indexOf;
        if (!zzpn.zza() || !zze().zze(str, zzbh.zzch)) {
            return null;
        }
        long currentTimeMillis = zzb().currentTimeMillis();
        String[] split = zze().zzd(str, zzbh.zzbg).split(",");
        HashSet hashSet = new HashSet(split.length);
        int length = split.length;
        int i = 0;
        while (i < length) {
            String str3 = split[i];
            if (hashSet.add(Objects.requireNonNull(str3))) {
                i++;
            } else {
                throw new IllegalArgumentException("duplicate element: " + str3);
            }
        }
        Set unmodifiableSet = Collections.unmodifiableSet(hashSet);
        zznu zzo = zzo();
        String zzf = zzo.zzm().zzf(str);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(zzo.zze().zzd(str, zzbh.zzaz));
        if (!TextUtils.isEmpty(zzf)) {
            builder.authority(zzf + "." + zzo.zze().zzd(str, zzbh.zzba));
        } else {
            builder.authority(zzo.zze().zzd(str, zzbh.zzba));
        }
        builder.path(zzo.zze().zzd(str, zzbh.zzbb));
        zza(builder, "gmp_app_id", zza.zzx(), (Set<String>) unmodifiableSet);
        zza(builder, "gmp_version", "106000", (Set<String>) unmodifiableSet);
        String zzu = zza.zzu();
        if (zze().zze(str, zzbh.zzck) && zzm().zzo(str)) {
            zzu = "";
        }
        zza(builder, "app_instance_id", zzu, (Set<String>) unmodifiableSet);
        zza(builder, "rdid", zza.zzz(), (Set<String>) unmodifiableSet);
        zza(builder, "bundle_id", zza.zzt(), (Set<String>) unmodifiableSet);
        String zze = zza2.zze();
        String zza3 = zzji.zza(zze);
        if (!TextUtils.isEmpty(zza3)) {
            zze = zza3;
        }
        zza(builder, "app_event_name", zze, (Set<String>) unmodifiableSet);
        zza(builder, App.JsonKeys.APP_VERSION, String.valueOf(zza.zzb()), (Set<String>) unmodifiableSet);
        String zzy = zza.zzy();
        if (zze().zze(str, zzbh.zzck) && zzm().zzs(str) && !TextUtils.isEmpty(zzy) && (indexOf = zzy.indexOf(".")) != -1) {
            zzy = zzy.substring(0, indexOf);
        }
        zza(builder, Constants.AMP_TRACKING_OPTION_OS_VERSION, zzy, (Set<String>) unmodifiableSet);
        zza(builder, "timestamp", String.valueOf(zza2.zzc()), (Set<String>) unmodifiableSet);
        String str4 = "1";
        if (zza.zzad()) {
            zza(builder, "lat", str4, (Set<String>) unmodifiableSet);
        }
        zza(builder, "privacy_sandbox_version", String.valueOf(zza.zza()), (Set<String>) unmodifiableSet);
        zza(builder, "trigger_uri_source", str4, (Set<String>) unmodifiableSet);
        zza(builder, "trigger_uri_timestamp", String.valueOf(currentTimeMillis), (Set<String>) unmodifiableSet);
        zza(builder, "request_uuid", str2, (Set<String>) unmodifiableSet);
        List<zzfy.zzh> zzf2 = zza2.zzf();
        Bundle bundle = new Bundle();
        for (zzfy.zzh next : zzf2) {
            String zzg = next.zzg();
            if (next.zzj()) {
                bundle.putString(zzg, String.valueOf(next.zza()));
            } else if (next.zzk()) {
                bundle.putString(zzg, String.valueOf(next.zzb()));
            } else if (next.zzn()) {
                bundle.putString(zzg, next.zzh());
            } else if (next.zzl()) {
                bundle.putString(zzg, String.valueOf(next.zzd()));
            }
        }
        zza(builder, zze().zzd(str, zzbh.zzbf).split("\\|"), bundle, (Set<String>) unmodifiableSet);
        List<zzfy.zzo> zzab = zza.zzab();
        Bundle bundle2 = new Bundle();
        for (zzfy.zzo next2 : zzab) {
            String zzg2 = next2.zzg();
            if (next2.zzi()) {
                bundle2.putString(zzg2, String.valueOf(next2.zza()));
            } else if (next2.zzj()) {
                bundle2.putString(zzg2, String.valueOf(next2.zzb()));
            } else if (next2.zzm()) {
                bundle2.putString(zzg2, next2.zzh());
            } else if (next2.zzk()) {
                bundle2.putString(zzg2, String.valueOf(next2.zzc()));
            }
        }
        zza(builder, zze().zzd(str, zzbh.zzbe).split("\\|"), bundle2, (Set<String>) unmodifiableSet);
        if (!zza.zzac()) {
            str4 = "0";
        }
        zza(builder, Constants.AMP_TRACKING_OPTION_DMA, str4, (Set<String>) unmodifiableSet);
        if (!zza.zzw().isEmpty()) {
            zza(builder, "dma_cps", zza.zzw(), (Set<String>) unmodifiableSet);
        }
        if (zze().zza(zzbh.zzcm) && zza.zzae()) {
            zzfy.zza zzg3 = zza.zzg();
            if (!zzg3.zzh().isEmpty()) {
                zza(builder, "dl_gclid", zzg3.zzh(), (Set<String>) unmodifiableSet);
            }
            if (!zzg3.zzg().isEmpty()) {
                zza(builder, "dl_gbraid", zzg3.zzg(), (Set<String>) unmodifiableSet);
            }
            if (!zzg3.zzf().isEmpty()) {
                zza(builder, "dl_gs", zzg3.zzf(), (Set<String>) unmodifiableSet);
            }
            if (zzg3.zza() > 0) {
                zza(builder, "dl_ss_ts", String.valueOf(zzg3.zza()), (Set<String>) unmodifiableSet);
            }
            if (!zzg3.zzk().isEmpty()) {
                zza(builder, "mr_gclid", zzg3.zzk(), (Set<String>) unmodifiableSet);
            }
            if (!zzg3.zzj().isEmpty()) {
                zza(builder, "mr_gbraid", zzg3.zzj(), (Set<String>) unmodifiableSet);
            }
            if (!zzg3.zzi().isEmpty()) {
                zza(builder, "mr_gs", zzg3.zzi(), (Set<String>) unmodifiableSet);
            }
            if (zzg3.zzb() > 0) {
                zza(builder, "mr_click_ts", String.valueOf(zzg3.zzb()), (Set<String>) unmodifiableSet);
            }
        }
        return new zzno(builder.build().toString(), currentTimeMillis, 1);
    }

    public final /* bridge */ /* synthetic */ zznu zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzoo g_() {
        return super.g_();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzos zzq() {
        return super.zzq();
    }

    /* access modifiers changed from: package-private */
    public final zzfy.zzf zza(zzbc zzbc) {
        zzfy.zzf.zza zza = zzfy.zzf.zze().zza(zzbc.zze);
        Iterator<String> it = zzbc.zzf.iterator();
        while (it.hasNext()) {
            String next = it.next();
            zzfy.zzh.zza zza2 = zzfy.zzh.zze().zza(next);
            Object zzc = zzbc.zzf.zzc(next);
            Preconditions.checkNotNull(zzc);
            zza(zza2, zzc);
            zza.zza(zza2);
        }
        if (zze().zza(zzbh.zzdi) && !TextUtils.isEmpty(zzbc.zzc) && zzbc.zzf.zzc("_o") == null) {
            zza.zza((zzfy.zzh) ((zzjt) zzfy.zzh.zze().zza("_o").zzb(zzbc.zzc).zzai()));
        }
        return (zzfy.zzf) ((zzjt) zza.zzai());
    }

    static zzfy.zzh zza(zzfy.zzf zzf, String str) {
        for (zzfy.zzh next : zzf.zzh()) {
            if (next.zzg().equals(str)) {
                return next;
            }
        }
        return null;
    }

    static <BuilderT extends zzlb> BuilderT zza(BuilderT buildert, byte[] bArr) throws zzkb {
        zzjg zza = zzjg.zza();
        if (zza != null) {
            return buildert.zza(bArr, zza);
        }
        return buildert.zza(bArr);
    }

    static Object zzb(zzfy.zzf zzf, String str) {
        zzfy.zzh zza = zza(zzf, str);
        if (zza == null) {
            return null;
        }
        if (zza.zzn()) {
            return zza.zzh();
        }
        if (zza.zzl()) {
            return Long.valueOf(zza.zzd());
        }
        if (zza.zzj()) {
            return Double.valueOf(zza.zza());
        }
        if (zza.zzc() > 0) {
            return zzb(zza.zzi());
        }
        return null;
    }

    static Object zza(zzfy.zzf zzf, String str, Object obj) {
        Object zzb = zzb(zzf, str);
        return zzb == null ? obj : zzb;
    }

    /* access modifiers changed from: package-private */
    public final String zza(zzfy.zzj zzj) {
        zzfy.zzc zzv;
        if (zzj == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        if (zzpu.zza() && zze().zza(zzbh.zzbx) && zzj.zza() > 0) {
            zzq();
            if (zzos.zzf(zzj.zza(0).zzz())) {
                if (zzj.zzh()) {
                    zza(sb, 0, "upload_subdomain", (Object) zzj.zze());
                }
                if (zzj.zzg()) {
                    zza(sb, 0, "sgtm_join_id", (Object) zzj.zzd());
                }
            }
        }
        for (zzfy.zzk next : zzj.zzf()) {
            if (next != null) {
                zza(sb, 1);
                sb.append("bundle {\n");
                if (next.zzbp()) {
                    zza(sb, 1, "protocol_version", (Object) Integer.valueOf(next.zzf()));
                }
                if (zzpo.zza() && zze().zze(next.zzz(), zzbh.zzbw) && next.zzbs()) {
                    zza(sb, 1, "session_stitching_token", (Object) next.zzao());
                }
                zza(sb, 1, "platform", (Object) next.zzam());
                if (next.zzbk()) {
                    zza(sb, 1, "gmp_version", (Object) Long.valueOf(next.zzn()));
                }
                if (next.zzbx()) {
                    zza(sb, 1, "uploading_gmp_version", (Object) Long.valueOf(next.zzt()));
                }
                if (next.zzbi()) {
                    zza(sb, 1, "dynamite_version", (Object) Long.valueOf(next.zzl()));
                }
                if (next.zzbb()) {
                    zza(sb, 1, "config_version", (Object) Long.valueOf(next.zzj()));
                }
                zza(sb, 1, "gmp_app_id", (Object) next.zzaj());
                zza(sb, 1, "admob_app_id", (Object) next.zzy());
                zza(sb, 1, "app_id", (Object) next.zzz());
                zza(sb, 1, App.JsonKeys.APP_VERSION, (Object) next.zzac());
                if (next.zzay()) {
                    zza(sb, 1, "app_version_major", (Object) Integer.valueOf(next.zzb()));
                }
                zza(sb, 1, "firebase_instance_id", (Object) next.zzai());
                if (next.zzbg()) {
                    zza(sb, 1, "dev_cert_hash", (Object) Long.valueOf(next.zzk()));
                }
                zza(sb, 1, "app_store", (Object) next.zzab());
                if (next.zzbw()) {
                    zza(sb, 1, "upload_timestamp_millis", (Object) Long.valueOf(next.zzs()));
                }
                if (next.zzbt()) {
                    zza(sb, 1, "start_timestamp_millis", (Object) Long.valueOf(next.zzq()));
                }
                if (next.zzbj()) {
                    zza(sb, 1, "end_timestamp_millis", (Object) Long.valueOf(next.zzm()));
                }
                if (next.zzbo()) {
                    zza(sb, 1, "previous_bundle_start_timestamp_millis", (Object) Long.valueOf(next.zzp()));
                }
                if (next.zzbn()) {
                    zza(sb, 1, "previous_bundle_end_timestamp_millis", (Object) Long.valueOf(next.zzo()));
                }
                zza(sb, 1, "app_instance_id", (Object) next.zzaa());
                zza(sb, 1, "resettable_device_id", (Object) next.zzan());
                zza(sb, 1, "ds_id", (Object) next.zzah());
                if (next.zzbm()) {
                    zza(sb, 1, "limited_ad_tracking", (Object) Boolean.valueOf(next.zzav()));
                }
                zza(sb, 1, Constants.AMP_TRACKING_OPTION_OS_VERSION, (Object) next.zzal());
                zza(sb, 1, "device_model", (Object) next.zzag());
                zza(sb, 1, "user_default_language", (Object) next.zzap());
                if (next.zzbv()) {
                    zza(sb, 1, "time_zone_offset_minutes", (Object) Integer.valueOf(next.zzh()));
                }
                if (next.zzba()) {
                    zza(sb, 1, "bundle_sequential_index", (Object) Integer.valueOf(next.zzc()));
                }
                if (zzpu.zza()) {
                    zzq();
                    if (zzos.zzf(next.zzz()) && zze().zza(zzbh.zzbx) && next.zzbf()) {
                        zza(sb, 1, "delivery_index", (Object) Integer.valueOf(next.zzd()));
                    }
                }
                if (next.zzbr()) {
                    zza(sb, 1, "service_upload", (Object) Boolean.valueOf(next.zzaw()));
                }
                zza(sb, 1, "health_monitor", (Object) next.zzak());
                if (next.zzbq()) {
                    zza(sb, 1, "retry_counter", (Object) Integer.valueOf(next.zzg()));
                }
                if (next.zzbd()) {
                    zza(sb, 1, "consent_signals", (Object) next.zzae());
                }
                if (next.zzbl()) {
                    zza(sb, 1, "is_dma_region", (Object) Boolean.valueOf(next.zzau()));
                }
                if (next.zzbe()) {
                    zza(sb, 1, "core_platform_services", (Object) next.zzaf());
                }
                if (next.zzbc()) {
                    zza(sb, 1, "consent_diagnostics", (Object) next.zzad());
                }
                if (next.zzbu()) {
                    zza(sb, 1, "target_os_version", (Object) Long.valueOf(next.zzr()));
                }
                if (zzpn.zza() && zze().zze(next.zzz(), zzbh.zzch)) {
                    zza(sb, 1, "ad_services_version", (Object) Integer.valueOf(next.zza()));
                    if (next.zzaz() && (zzv = next.zzv()) != null) {
                        zza(sb, 2);
                        sb.append("attribution_eligibility_status {\n");
                        zza(sb, 2, "eligible", (Object) Boolean.valueOf(zzv.zzf()));
                        zza(sb, 2, "no_access_adservices_attribution_permission", (Object) Boolean.valueOf(zzv.zzh()));
                        zza(sb, 2, "pre_r", (Object) Boolean.valueOf(zzv.zzi()));
                        zza(sb, 2, "r_extensions_too_old", (Object) Boolean.valueOf(zzv.zzj()));
                        zza(sb, 2, "adservices_extension_too_old", (Object) Boolean.valueOf(zzv.zze()));
                        zza(sb, 2, "ad_storage_not_allowed", (Object) Boolean.valueOf(zzv.zzd()));
                        zza(sb, 2, "measurement_manager_disabled", (Object) Boolean.valueOf(zzv.zzg()));
                        zza(sb, 2);
                        sb.append("}\n");
                    }
                }
                if (zzov.zza() && zze().zza(zzbh.zzcu) && next.zzax()) {
                    zzfy.zza zzu = next.zzu();
                    zza(sb, 2);
                    sb.append("ad_campaign_info {\n");
                    if (zzu.zzn()) {
                        zza(sb, 2, "deep_link_gclid", (Object) zzu.zzh());
                    }
                    if (zzu.zzm()) {
                        zza(sb, 2, "deep_link_gbraid", (Object) zzu.zzg());
                    }
                    if (zzu.zzl()) {
                        zza(sb, 2, "deep_link_gad_source", (Object) zzu.zzf());
                    }
                    if (zzu.zzo()) {
                        zza(sb, 2, "deep_link_session_millis", (Object) Long.valueOf(zzu.zza()));
                    }
                    if (zzu.zzs()) {
                        zza(sb, 2, "market_referrer_gclid", (Object) zzu.zzk());
                    }
                    if (zzu.zzr()) {
                        zza(sb, 2, "market_referrer_gbraid", (Object) zzu.zzj());
                    }
                    if (zzu.zzq()) {
                        zza(sb, 2, "market_referrer_gad_source", (Object) zzu.zzi());
                    }
                    if (zzu.zzp()) {
                        zza(sb, 2, "market_referrer_click_millis", (Object) Long.valueOf(zzu.zzb()));
                    }
                    zza(sb, 2);
                    sb.append("}\n");
                }
                List<zzfy.zzo> zzas = next.zzas();
                if (zzas != null) {
                    for (zzfy.zzo next2 : zzas) {
                        if (next2 != null) {
                            zza(sb, 2);
                            sb.append("user_property {\n");
                            Double d = null;
                            zza(sb, 2, "set_timestamp_millis", (Object) next2.zzl() ? Long.valueOf(next2.zzd()) : null);
                            zza(sb, 2, "name", (Object) zzi().zzc(next2.zzg()));
                            zza(sb, 2, "string_value", (Object) next2.zzh());
                            zza(sb, 2, "int_value", (Object) next2.zzk() ? Long.valueOf(next2.zzc()) : null);
                            if (next2.zzi()) {
                                d = Double.valueOf(next2.zza());
                            }
                            zza(sb, 2, "double_value", (Object) d);
                            zza(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<zzfy.zzd> zzaq = next.zzaq();
                next.zzz();
                if (zzaq != null) {
                    for (zzfy.zzd next3 : zzaq) {
                        if (next3 != null) {
                            zza(sb, 2);
                            sb.append("audience_membership {\n");
                            if (next3.zzg()) {
                                zza(sb, 2, "audience_id", (Object) Integer.valueOf(next3.zza()));
                            }
                            if (next3.zzh()) {
                                zza(sb, 2, "new_audience", (Object) Boolean.valueOf(next3.zzf()));
                            }
                            zza(sb, 2, "current_data", next3.zzd());
                            if (next3.zzi()) {
                                zza(sb, 2, "previous_data", next3.zze());
                            }
                            zza(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<zzfy.zzf> zzar = next.zzar();
                if (zzar != null) {
                    for (zzfy.zzf next4 : zzar) {
                        if (next4 != null) {
                            zza(sb, 2);
                            sb.append("event {\n");
                            zza(sb, 2, "name", (Object) zzi().zza(next4.zzg()));
                            if (next4.zzk()) {
                                zza(sb, 2, "timestamp_millis", (Object) Long.valueOf(next4.zzd()));
                            }
                            if (next4.zzj()) {
                                zza(sb, 2, "previous_timestamp_millis", (Object) Long.valueOf(next4.zzc()));
                            }
                            if (next4.zzi()) {
                                zza(sb, 2, NewHtcHomeBadger.COUNT, (Object) Integer.valueOf(next4.zza()));
                            }
                            if (next4.zzb() != 0) {
                                zza(sb, 2, next4.zzh());
                            }
                            zza(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                zza(sb, 1);
                sb.append("}\n");
            }
        }
        sb.append("} // End-of-batch\n");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final String zza(zzfo.zzb zzb) {
        if (zzb == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        if (zzb.zzl()) {
            zza(sb, 0, "filter_id", (Object) Integer.valueOf(zzb.zzb()));
        }
        zza(sb, 0, "event_name", (Object) zzi().zza(zzb.zzf()));
        String zza = zza(zzb.zzh(), zzb.zzi(), zzb.zzj());
        if (!zza.isEmpty()) {
            zza(sb, 0, "filter_type", (Object) zza);
        }
        if (zzb.zzk()) {
            zza(sb, 1, "event_count_filter", zzb.zze());
        }
        if (zzb.zza() > 0) {
            sb.append("  filters {\n");
            for (zzfo.zzc zza2 : zzb.zzg()) {
                zza(sb, 2, zza2);
            }
        }
        zza(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    private static String zza(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("Dynamic ");
        }
        if (z2) {
            sb.append("Sequence ");
        }
        if (z3) {
            sb.append("Session-Scoped ");
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final String zza(zzfo.zze zze) {
        if (zze == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        if (zze.zzi()) {
            zza(sb, 0, "filter_id", (Object) Integer.valueOf(zze.zza()));
        }
        zza(sb, 0, "property_name", (Object) zzi().zzc(zze.zze()));
        String zza = zza(zze.zzf(), zze.zzg(), zze.zzh());
        if (!zza.isEmpty()) {
            zza(sb, 0, "filter_type", (Object) zza);
        }
        zza(sb, 1, zze.zzb());
        sb.append("}\n");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final List<Long> zza(List<Long> list, List<Integer> list2) {
        int i;
        ArrayList arrayList = new ArrayList(list);
        for (Integer next : list2) {
            if (next.intValue() < 0) {
                zzj().zzu().zza("Ignoring negative bit index to be cleared", next);
            } else {
                int intValue = next.intValue() / 64;
                if (intValue >= arrayList.size()) {
                    zzj().zzu().zza("Ignoring bit index greater than bitSet size", next, Integer.valueOf(arrayList.size()));
                } else {
                    arrayList.set(intValue, Long.valueOf(((Long) arrayList.get(intValue)).longValue() & (~(1 << (next.intValue() % 64)))));
                }
            }
        }
        int size = arrayList.size();
        int size2 = arrayList.size() - 1;
        while (true) {
            int i2 = size2;
            i = size;
            size = i2;
            if (size >= 0 && ((Long) arrayList.get(size)).longValue() == 0) {
                size2 = size - 1;
            }
        }
        return arrayList.subList(0, i);
    }

    /* access modifiers changed from: package-private */
    public final List<Integer> zzu() {
        Map<String, String> zza = zzbh.zza(this.zzg.zza());
        if (zza == null || zza.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int intValue = zzbh.zzar.zza(null).intValue();
        Iterator<Map.Entry<String, String>> it = zza.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry next = it.next();
            if (((String) next.getKey()).startsWith("measurement.id.")) {
                try {
                    int parseInt = Integer.parseInt((String) next.getValue());
                    if (parseInt != 0) {
                        arrayList.add(Integer.valueOf(parseInt));
                        if (arrayList.size() >= intValue) {
                            zzj().zzu().zza("Too many experiment IDs. Number of IDs", Integer.valueOf(arrayList.size()));
                            break;
                        }
                    } else {
                        continue;
                    }
                } catch (NumberFormatException e) {
                    zzj().zzu().zza("Experiment ID NumberFormatException", e);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    static List<Long> zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            long j = 0;
            for (int i2 = 0; i2 < 64; i2++) {
                int i3 = (i << 6) + i2;
                if (i3 >= bitSet.length()) {
                    break;
                }
                if (bitSet.get(i3)) {
                    j |= 1 << i2;
                }
            }
            arrayList.add(Long.valueOf(j));
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public final Map<String, Object> zza(Bundle bundle, boolean z) {
        HashMap hashMap = new HashMap();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            boolean z2 = obj instanceof Parcelable[];
            if (z2 || (obj instanceof ArrayList) || (obj instanceof Bundle)) {
                if (z) {
                    ArrayList arrayList = new ArrayList();
                    if (z2) {
                        for (Parcelable parcelable : (Parcelable[]) obj) {
                            if (parcelable instanceof Bundle) {
                                arrayList.add(zza((Bundle) parcelable, false));
                            }
                        }
                    } else if (obj instanceof ArrayList) {
                        ArrayList arrayList2 = (ArrayList) obj;
                        int size = arrayList2.size();
                        int i = 0;
                        while (i < size) {
                            Object obj2 = arrayList2.get(i);
                            i++;
                            if (obj2 instanceof Bundle) {
                                arrayList.add(zza((Bundle) obj2, false));
                            }
                        }
                    } else if (obj instanceof Bundle) {
                        arrayList.add(zza((Bundle) obj, false));
                    }
                    hashMap.put(str, arrayList);
                }
            } else if (obj != null) {
                hashMap.put(str, obj);
            }
        }
        return hashMap;
    }

    zzoo(zznv zznv) {
        super(zznv);
    }

    static void zza(zzfy.zzf.zza zza, String str, Object obj) {
        List<zzfy.zzh> zzf = zza.zzf();
        int i = 0;
        while (true) {
            if (i >= zzf.size()) {
                i = -1;
                break;
            } else if (str.equals(zzf.get(i).zzg())) {
                break;
            } else {
                i++;
            }
        }
        zzfy.zzh.zza zza2 = zzfy.zzh.zze().zza(str);
        if (obj instanceof Long) {
            zza2.zza(((Long) obj).longValue());
        } else if (obj instanceof String) {
            zza2.zzb((String) obj);
        } else if (obj instanceof Double) {
            zza2.zza(((Double) obj).doubleValue());
        }
        if (i >= 0) {
            zza.zza(i, zza2);
        } else {
            zza.zza(zza2);
        }
    }

    private static void zza(Uri.Builder builder, String[] strArr, Bundle bundle, Set<String> set) {
        for (String split : strArr) {
            String[] split2 = split.split(",");
            String str = split2[0];
            String str2 = split2[split2.length - 1];
            String string = bundle.getString(str);
            if (string != null) {
                zza(builder, str2, string, set);
            }
        }
    }

    private static void zza(StringBuilder sb, int i, String str, zzfy.zzm zzm) {
        if (zzm != null) {
            zza(sb, 3);
            sb.append(str);
            sb.append(" {\n");
            if (zzm.zzb() != 0) {
                zza(sb, 4);
                sb.append("results: ");
                int i2 = 0;
                for (Long next : zzm.zzi()) {
                    int i3 = i2 + 1;
                    if (i2 != 0) {
                        sb.append(", ");
                    }
                    sb.append(next);
                    i2 = i3;
                }
                sb.append(10);
            }
            if (zzm.zzd() != 0) {
                zza(sb, 4);
                sb.append("status: ");
                int i4 = 0;
                for (Long next2 : zzm.zzk()) {
                    int i5 = i4 + 1;
                    if (i4 != 0) {
                        sb.append(", ");
                    }
                    sb.append(next2);
                    i4 = i5;
                }
                sb.append(10);
            }
            if (zzm.zza() != 0) {
                zza(sb, 4);
                sb.append("dynamic_filter_timestamps: {");
                int i6 = 0;
                for (zzfy.zze next3 : zzm.zzh()) {
                    int i7 = i6 + 1;
                    if (i6 != 0) {
                        sb.append(", ");
                    }
                    sb.append(next3.zzf() ? Integer.valueOf(next3.zza()) : null).append(":").append(next3.zze() ? Long.valueOf(next3.zzb()) : null);
                    i6 = i7;
                }
                sb.append("}\n");
            }
            if (zzm.zzc() != 0) {
                zza(sb, 4);
                sb.append("sequence_filter_timestamps: {");
                int i8 = 0;
                for (zzfy.zzn next4 : zzm.zzj()) {
                    int i9 = i8 + 1;
                    if (i8 != 0) {
                        sb.append(", ");
                    }
                    sb.append(next4.zzf() ? Integer.valueOf(next4.zzb()) : null).append(": [");
                    int i10 = 0;
                    for (Long longValue : next4.zze()) {
                        long longValue2 = longValue.longValue();
                        int i11 = i10 + 1;
                        if (i10 != 0) {
                            sb.append(", ");
                        }
                        sb.append(longValue2);
                        i10 = i11;
                    }
                    sb.append("]");
                    i8 = i9;
                }
                sb.append("}\n");
            }
            zza(sb, 3);
            sb.append("}\n");
        }
    }

    private final void zza(StringBuilder sb, int i, List<zzfy.zzh> list) {
        if (list != null) {
            int i2 = i + 1;
            for (zzfy.zzh next : list) {
                if (next != null) {
                    zza(sb, i2);
                    sb.append("param {\n");
                    Double d = null;
                    zza(sb, i2, "name", (Object) next.zzm() ? zzi().zzb(next.zzg()) : null);
                    zza(sb, i2, "string_value", (Object) next.zzn() ? next.zzh() : null);
                    zza(sb, i2, "int_value", (Object) next.zzl() ? Long.valueOf(next.zzd()) : null);
                    if (next.zzj()) {
                        d = Double.valueOf(next.zza());
                    }
                    zza(sb, i2, "double_value", (Object) d);
                    if (next.zzc() > 0) {
                        zza(sb, i2, next.zzi());
                    }
                    zza(sb, i2);
                    sb.append("}\n");
                }
            }
        }
    }

    private final void zza(StringBuilder sb, int i, zzfo.zzc zzc) {
        if (zzc != null) {
            zza(sb, i);
            sb.append("filter {\n");
            if (zzc.zzg()) {
                zza(sb, i, "complement", (Object) Boolean.valueOf(zzc.zzf()));
            }
            if (zzc.zzi()) {
                zza(sb, i, "param_name", (Object) zzi().zzb(zzc.zze()));
            }
            if (zzc.zzj()) {
                int i2 = i + 1;
                zzfo.zzf zzd = zzc.zzd();
                if (zzd != null) {
                    zza(sb, i2);
                    sb.append("string_filter");
                    sb.append(" {\n");
                    if (zzd.zzj()) {
                        zza(sb, i2, "match_type", (Object) zzd.zzb().name());
                    }
                    if (zzd.zzi()) {
                        zza(sb, i2, "expression", (Object) zzd.zze());
                    }
                    if (zzd.zzh()) {
                        zza(sb, i2, "case_sensitive", (Object) Boolean.valueOf(zzd.zzg()));
                    }
                    if (zzd.zza() > 0) {
                        zza(sb, i2 + 1);
                        sb.append("expression_list {\n");
                        for (String append : zzd.zzf()) {
                            zza(sb, i2 + 2);
                            sb.append(append);
                            sb.append("\n");
                        }
                        sb.append("}\n");
                    }
                    zza(sb, i2);
                    sb.append("}\n");
                }
            }
            if (zzc.zzh()) {
                zza(sb, i + 1, "number_filter", zzc.zzc());
            }
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(FileSpecKt.DEFAULT_INDENT);
        }
    }

    private static void zza(StringBuilder sb, int i, String str, zzfo.zzd zzd) {
        if (zzd != null) {
            zza(sb, i);
            sb.append(str);
            sb.append(" {\n");
            if (zzd.zzh()) {
                zza(sb, i, "comparison_type", (Object) zzd.zza().name());
            }
            if (zzd.zzj()) {
                zza(sb, i, "match_as_float", (Object) Boolean.valueOf(zzd.zzg()));
            }
            if (zzd.zzi()) {
                zza(sb, i, "comparison_value", (Object) zzd.zzd());
            }
            if (zzd.zzl()) {
                zza(sb, i, "min_comparison_value", (Object) zzd.zzf());
            }
            if (zzd.zzk()) {
                zza(sb, i, "max_comparison_value", (Object) zzd.zze());
            }
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(Uri.Builder builder, String str, String str2, Set<String> set) {
        if (!set.contains(str) && !TextUtils.isEmpty(str2)) {
            builder.appendQueryParameter(str, str2);
        }
    }

    private static void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj != null) {
            zza(sb, i + 1);
            sb.append(str);
            sb.append(": ");
            sb.append(obj);
            sb.append(10);
        }
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
    public final void zza(zzfy.zzh.zza zza, Object obj) {
        Preconditions.checkNotNull(obj);
        zza.zze().zzc().zzb().zzd();
        if (obj instanceof String) {
            zza.zzb((String) obj);
        } else if (obj instanceof Long) {
            zza.zza(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zza.zza(((Double) obj).doubleValue());
        } else if (obj instanceof Bundle[]) {
            ArrayList arrayList = new ArrayList();
            for (Bundle bundle : (Bundle[]) obj) {
                if (bundle != null) {
                    zzfy.zzh.zza zze = zzfy.zzh.zze();
                    for (String str : bundle.keySet()) {
                        zzfy.zzh.zza zza2 = zzfy.zzh.zze().zza(str);
                        Object obj2 = bundle.get(str);
                        if (obj2 instanceof Long) {
                            zza2.zza(((Long) obj2).longValue());
                        } else if (obj2 instanceof String) {
                            zza2.zzb((String) obj2);
                        } else if (obj2 instanceof Double) {
                            zza2.zza(((Double) obj2).doubleValue());
                        }
                        zze.zza(zza2);
                    }
                    if (zze.zza() > 0) {
                        arrayList.add((zzfy.zzh) ((zzjt) zze.zzai()));
                    }
                }
            }
            zza.zza((Iterable<? extends zzfy.zzh>) arrayList);
        } else {
            zzj().zzg().zza("Ignoring invalid (type) event param value", obj);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzfy.zzo.zza zza, Object obj) {
        Preconditions.checkNotNull(obj);
        zza.zzc().zzb().zza();
        if (obj instanceof String) {
            zza.zzb((String) obj);
        } else if (obj instanceof Long) {
            zza.zza(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zza.zza(((Double) obj).doubleValue());
        } else {
            zzj().zzg().zza("Ignoring invalid (type) user attribute value", obj);
        }
    }

    static boolean zza(zzbf zzbf, zzo zzo) {
        Preconditions.checkNotNull(zzbf);
        Preconditions.checkNotNull(zzo);
        return !TextUtils.isEmpty(zzo.zzb) || !TextUtils.isEmpty(zzo.zzp);
    }

    static boolean zza(List<Long> list, int i) {
        if (i >= (list.size() << 6)) {
            return false;
        }
        return ((1 << (i % 64)) & list.get(i / 64).longValue()) != 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(zzb().currentTimeMillis() - j) > j2;
    }

    static boolean zzb(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    /* access modifiers changed from: package-private */
    public final byte[] zzb(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            zzj().zzg().zza("Failed to gzip content", e);
            throw e;
        }
    }

    /* access modifiers changed from: package-private */
    public final byte[] zzc(byte[] bArr) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            zzj().zzg().zza("Failed to ungzip content", e);
            throw e;
        }
    }

    static Bundle[] zzb(List<zzfy.zzh> list) {
        ArrayList arrayList = new ArrayList();
        for (zzfy.zzh next : list) {
            if (next != null) {
                Bundle bundle = new Bundle();
                for (zzfy.zzh next2 : next.zzi()) {
                    if (next2.zzn()) {
                        bundle.putString(next2.zzg(), next2.zzh());
                    } else if (next2.zzl()) {
                        bundle.putLong(next2.zzg(), next2.zzd());
                    } else if (next2.zzj()) {
                        bundle.putDouble(next2.zzg(), next2.zza());
                    }
                }
                if (!bundle.isEmpty()) {
                    arrayList.add(bundle);
                }
            }
        }
        return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
    }
}
