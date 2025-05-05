package com.google.android.recaptcha.internal;

import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzjj {
    private boolean zza;
    private long zzb;
    private long zzc;

    zzjj() {
    }

    public static zzjj zzb() {
        zzjj zzjj = new zzjj();
        zzjj.zze();
        return zzjj;
    }

    public static zzjj zzc() {
        return new zzjj();
    }

    private final long zzg() {
        return this.zza ? (System.nanoTime() - this.zzc) + this.zzb : this.zzb;
    }

    public final String toString() {
        TimeUnit timeUnit;
        String str;
        long zzg = zzg();
        if (TimeUnit.DAYS.convert(zzg, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.DAYS;
        } else if (TimeUnit.HOURS.convert(zzg, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.HOURS;
        } else if (TimeUnit.MINUTES.convert(zzg, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.MINUTES;
        } else if (TimeUnit.SECONDS.convert(zzg, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.SECONDS;
        } else if (TimeUnit.MILLISECONDS.convert(zzg, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.MILLISECONDS;
        } else if (TimeUnit.MICROSECONDS.convert(zzg, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.MICROSECONDS;
        } else {
            timeUnit = TimeUnit.NANOSECONDS;
        }
        String format = String.format(Locale.ROOT, "%.4g", new Object[]{Double.valueOf(((double) zzg) / ((double) TimeUnit.NANOSECONDS.convert(1, timeUnit)))});
        switch (zzji.zza[timeUnit.ordinal()]) {
            case 1:
                str = "ns";
                break;
            case 2:
                str = "μs";
                break;
            case 3:
                str = "ms";
                break;
            case 4:
                str = CmcdData.Factory.STREAMING_FORMAT_SS;
                break;
            case 5:
                str = "min";
                break;
            case 6:
                str = CmcdData.Factory.STREAMING_FORMAT_HLS;
                break;
            case 7:
                str = "d";
                break;
            default:
                throw new AssertionError();
        }
        return format + " " + str;
    }

    public final long zza(TimeUnit timeUnit) {
        return timeUnit.convert(zzg(), TimeUnit.NANOSECONDS);
    }

    public final zzjj zzd() {
        this.zzb = 0;
        this.zza = false;
        return this;
    }

    public final zzjj zze() {
        zzjh.zze(!this.zza, "This stopwatch is already running.");
        this.zza = true;
        this.zzc = System.nanoTime();
        return this;
    }

    public final zzjj zzf() {
        long nanoTime = System.nanoTime();
        zzjh.zze(this.zza, "This stopwatch is already stopped.");
        this.zza = false;
        this.zzb += nanoTime - this.zzc;
        return this;
    }
}
