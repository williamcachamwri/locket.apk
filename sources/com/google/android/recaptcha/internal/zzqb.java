package com.google.android.recaptcha.internal;

import androidx.media3.common.C;
import okhttp3.internal.http2.Http2Connection;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzqb {
    static {
        zzml zzi = zzmn.zzi();
        zzi.zzf(-315576000000L);
        zzi.zze(-999999999);
        zzmn zzmn = (zzmn) zzi.zzk();
        zzml zzi2 = zzmn.zzi();
        zzi2.zzf(315576000000L);
        zzi2.zze(999999999);
        zzmn zzmn2 = (zzmn) zzi2.zzk();
        zzml zzi3 = zzmn.zzi();
        zzi3.zzf(0);
        zzi3.zze(0);
        zzmn zzmn3 = (zzmn) zzi3.zzk();
    }

    public static zzmn zza(long j) {
        int i;
        int i2 = (int) (j % C.NANOS_PER_SECOND);
        long j2 = j / C.NANOS_PER_SECOND;
        if (i2 <= -1000000000 || i2 >= 1000000000) {
            j2 = zzkm.zza(j2, (long) (i2 / Http2Connection.DEGRADED_PONG_TIMEOUT_NS));
            i2 %= Http2Connection.DEGRADED_PONG_TIMEOUT_NS;
        }
        if (j2 > 0 && i2 < 0) {
            i2 += Http2Connection.DEGRADED_PONG_TIMEOUT_NS;
            j2--;
        }
        if (j2 < 0 && i2 > 0) {
            i2 -= 1000000000;
            j2++;
        }
        zzml zzi = zzmn.zzi();
        zzi.zzf(j2);
        zzi.zze(i2);
        zzmn zzmn = (zzmn) zzi.zzk();
        long zzg = zzmn.zzg();
        int zzf = zzmn.zzf();
        if (zzg >= -315576000000L && zzg <= 315576000000L && ((long) zzf) >= -999999999 && zzf < 1000000000 && ((zzg >= 0 && zzf >= 0) || (i <= 0 && zzf <= 0))) {
            return zzmn;
        }
        throw new IllegalArgumentException(String.format("Duration is not valid. See proto definition for valid values. Seconds (%s) must be in range [-315,576,000,000, +315,576,000,000]. Nanos (%s) must be in range [-999,999,999, +999,999,999]. Nanos must have the same sign as seconds", new Object[]{Long.valueOf(zzg), Integer.valueOf(zzf)}));
    }
}
