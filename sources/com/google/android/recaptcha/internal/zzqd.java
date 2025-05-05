package com.google.android.recaptcha.internal;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.annotation.Nullable;
import okhttp3.internal.http2.Http2Connection;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzqd {
    private static final ThreadLocal zza = new zzqc();

    static {
        zzpj zzi = zzpl.zzi();
        zzi.zzf(-62135596800L);
        zzi.zze(0);
        zzpl zzpl = (zzpl) zzi.zzk();
        zzpj zzi2 = zzpl.zzi();
        zzi2.zzf(253402300799L);
        zzi2.zze(999999999);
        zzpl zzpl2 = (zzpl) zzi2.zzk();
        zzpj zzi3 = zzpl.zzi();
        zzi3.zzf(0);
        zzi3.zze(0);
        zzpl zzpl3 = (zzpl) zzi3.zzk();
        zzd("now");
        zzd("getEpochSecond");
        zzd("getNano");
    }

    public static zzpl zza(zzpl zzpl) {
        long zzg = zzpl.zzg();
        boolean zze = zze(zzg);
        int zzf = zzpl.zzf();
        if (zze && zzf >= 0 && zzf < 1000000000) {
            return zzpl;
        }
        throw new IllegalArgumentException(String.format("Timestamp is not valid. See proto definition for valid values. Seconds (%s) must be in range [-62,135,596,800, +253,402,300,799]. Nanos (%s) must be in range [0, +999,999,999].", new Object[]{Long.valueOf(zzg), Integer.valueOf(zzf)}));
    }

    public static zzpl zzb(long j) {
        long j2 = j / 1000;
        if (zze(j2)) {
            int i = (int) ((j % 1000) * 1000000);
            if (i <= -1000000000 || i >= 1000000000) {
                j2 = zzkm.zza(j2, (long) (i / Http2Connection.DEGRADED_PONG_TIMEOUT_NS));
                i %= Http2Connection.DEGRADED_PONG_TIMEOUT_NS;
            }
            if (i < 0) {
                i += Http2Connection.DEGRADED_PONG_TIMEOUT_NS;
                j2 = zzkm.zzb(j2, 1);
            }
            zzpj zzi = zzpl.zzi();
            zzi.zzf(j2);
            zzi.zze(i);
            zzpl zzpl = (zzpl) zzi.zzk();
            zza(zzpl);
            return zzpl;
        }
        throw new IllegalArgumentException(String.format("Timestamp is not valid. Input seconds is too large. Seconds (%s) must be in range [-62,135,596,800, +253,402,300,799]. ", new Object[]{Long.valueOf(j2)}));
    }

    public static String zzc(zzpl zzpl) {
        String str;
        zza(zzpl);
        long zzg = zzpl.zzg();
        int zzf = zzpl.zzf();
        StringBuilder sb = new StringBuilder();
        sb.append(((SimpleDateFormat) zza.get()).format(new Date(zzg * 1000)));
        if (zzf != 0) {
            sb.append(".");
            if (zzf % 1000000 == 0) {
                str = String.format(Locale.ENGLISH, "%1$03d", new Object[]{Integer.valueOf(zzf / 1000000)});
            } else if (zzf % 1000 == 0) {
                str = String.format(Locale.ENGLISH, "%1$06d", new Object[]{Integer.valueOf(zzf / 1000)});
            } else {
                str = String.format(Locale.ENGLISH, "%1$09d", new Object[]{Integer.valueOf(zzf)});
            }
            sb.append(str);
        }
        sb.append("Z");
        return sb.toString();
    }

    @Nullable
    private static Method zzd(String str) {
        try {
            return Class.forName("java.time.Instant").getMethod(str, new Class[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    private static boolean zze(long j) {
        return j >= -62135596800L && j <= 253402300799L;
    }
}
