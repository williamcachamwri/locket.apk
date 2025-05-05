package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzabs extends zzwj {
    static final zzwk zza = new zzabq();
    private final DateFormat zzb = new SimpleDateFormat("MMM d, yyyy");

    private zzabs() {
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        String format;
        Date date = (Date) obj;
        if (date == null) {
            zzace.zzg();
            return;
        }
        synchronized (this) {
            format = this.zzb.format(date);
        }
        zzace.zzl(format);
    }

    /* renamed from: zza */
    public final Date read(zzacc zzacc) throws IOException {
        Date date;
        if (zzacc.zzr() == 9) {
            zzacc.zzm();
            return null;
        }
        String zzh = zzacc.zzh();
        synchronized (this) {
            TimeZone timeZone = this.zzb.getTimeZone();
            try {
                date = new Date(this.zzb.parse(zzh).getTime());
                this.zzb.setTimeZone(timeZone);
            } catch (ParseException e) {
                String zzf = zzacc.zzf();
                throw new zzwe("Failed parsing '" + zzh + "' as SQL Date; at path " + zzf, e);
            } catch (Throwable th) {
                this.zzb.setTimeZone(timeZone);
                throw th;
            }
        }
        return date;
    }

    /* synthetic */ zzabs(zzabr zzabr) {
    }
}
