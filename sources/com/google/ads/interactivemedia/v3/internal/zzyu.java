package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzyu extends zzwj {
    public static final zzwk zza = new zzyq();
    private final zzys zzb;
    private final List zzc;

    /* synthetic */ zzyu(zzys zzys, int i, int i2, zzyt zzyt) {
        ArrayList arrayList = new ArrayList();
        this.zzc = arrayList;
        this.zzb = (zzys) Objects.requireNonNull(zzys);
        arrayList.add(DateFormat.getDateTimeInstance(2, 2, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            arrayList.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (zzxo.zza()) {
            arrayList.add(new SimpleDateFormat("MMM d, yyyy h:mm:ss a", Locale.US));
        }
    }

    /* JADX INFO: finally extract failed */
    private final Date zza(zzacc zzacc) throws IOException {
        List list = this.zzc;
        String zzh = zzacc.zzh();
        synchronized (list) {
            for (DateFormat dateFormat : this.zzc) {
                TimeZone timeZone = dateFormat.getTimeZone();
                try {
                    Date parse = dateFormat.parse(zzh);
                    dateFormat.setTimeZone(timeZone);
                    return parse;
                } catch (ParseException unused) {
                    dateFormat.setTimeZone(timeZone);
                } catch (Throwable th) {
                    dateFormat.setTimeZone(timeZone);
                    throw th;
                }
            }
            try {
                return zzabi.zza(zzh, new ParsePosition(0));
            } catch (ParseException e) {
                String zzf = zzacc.zzf();
                throw new zzwe("Failed parsing '" + zzh + "' as Date; at path " + zzf, e);
            }
        }
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        if (zzacc.zzr() == 9) {
            zzacc.zzm();
            return null;
        }
        Date zza2 = zza(zzacc);
        this.zzb.zza(zza2);
        return zza2;
    }

    public final String toString() {
        DateFormat dateFormat = (DateFormat) this.zzc.get(0);
        if (dateFormat instanceof SimpleDateFormat) {
            String pattern = ((SimpleDateFormat) dateFormat).toPattern();
            return "DefaultDateTypeAdapter(" + pattern + ")";
        }
        String simpleName = dateFormat.getClass().getSimpleName();
        return "DefaultDateTypeAdapter(" + simpleName + ")";
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        String format;
        Date date = (Date) obj;
        if (date == null) {
            zzace.zzg();
            return;
        }
        DateFormat dateFormat = (DateFormat) this.zzc.get(0);
        synchronized (this.zzc) {
            format = dateFormat.format(date);
        }
        zzace.zzl(format);
    }
}
