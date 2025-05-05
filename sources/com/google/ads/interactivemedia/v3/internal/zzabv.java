package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzabv extends zzwj {
    static final zzwk zza = new zzabt();
    private final DateFormat zzb = new SimpleDateFormat("hh:mm:ss a");

    private zzabv() {
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        String format;
        Time time = (Time) obj;
        if (time == null) {
            zzace.zzg();
            return;
        }
        synchronized (this) {
            format = this.zzb.format(time);
        }
        zzace.zzl(format);
    }

    /* renamed from: zza */
    public final Time read(zzacc zzacc) throws IOException {
        Time time;
        if (zzacc.zzr() == 9) {
            zzacc.zzm();
            return null;
        }
        String zzh = zzacc.zzh();
        synchronized (this) {
            TimeZone timeZone = this.zzb.getTimeZone();
            try {
                time = new Time(this.zzb.parse(zzh).getTime());
                this.zzb.setTimeZone(timeZone);
            } catch (ParseException e) {
                String zzf = zzacc.zzf();
                throw new zzwe("Failed parsing '" + zzh + "' as SQL Time; at path " + zzf, e);
            } catch (Throwable th) {
                this.zzb.setTimeZone(timeZone);
                throw th;
            }
        }
        return time;
    }

    /* synthetic */ zzabv(zzabu zzabu) {
    }
}
