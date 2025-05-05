package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.Calendar;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaao extends zzwj {
    zzaao() {
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object read(com.google.ads.interactivemedia.v3.internal.zzacc r16) throws java.io.IOException {
        /*
            r15 = this;
            int r0 = r16.zzr()
            r1 = 9
            if (r0 != r1) goto L_0x000e
            r16.zzm()
            r0 = 0
            goto L_0x0092
        L_0x000e:
            r16.zzj()
            r0 = 0
            r2 = r0
            r3 = r2
            r4 = r3
            r5 = r4
            r6 = r5
            r7 = r6
        L_0x0018:
            int r1 = r16.zzr()
            r8 = 4
            if (r1 == r8) goto L_0x0089
            java.lang.String r1 = r16.zzg()
            int r9 = r16.zzb()
            int r10 = r1.hashCode()
            r11 = 5
            r12 = 3
            r13 = 2
            r14 = 1
            switch(r10) {
                case -1181204563: goto L_0x0065;
                case -1074026988: goto L_0x005b;
                case -906279820: goto L_0x0051;
                case 3704893: goto L_0x0047;
                case 104080000: goto L_0x003d;
                case 985252545: goto L_0x0033;
                default: goto L_0x0032;
            }
        L_0x0032:
            goto L_0x006f
        L_0x0033:
            java.lang.String r10 = "hourOfDay"
            boolean r1 = r1.equals(r10)
            if (r1 == 0) goto L_0x006f
            r1 = r12
            goto L_0x0070
        L_0x003d:
            java.lang.String r10 = "month"
            boolean r1 = r1.equals(r10)
            if (r1 == 0) goto L_0x006f
            r1 = r14
            goto L_0x0070
        L_0x0047:
            java.lang.String r10 = "year"
            boolean r1 = r1.equals(r10)
            if (r1 == 0) goto L_0x006f
            r1 = r0
            goto L_0x0070
        L_0x0051:
            java.lang.String r10 = "second"
            boolean r1 = r1.equals(r10)
            if (r1 == 0) goto L_0x006f
            r1 = r11
            goto L_0x0070
        L_0x005b:
            java.lang.String r10 = "minute"
            boolean r1 = r1.equals(r10)
            if (r1 == 0) goto L_0x006f
            r1 = r8
            goto L_0x0070
        L_0x0065:
            java.lang.String r10 = "dayOfMonth"
            boolean r1 = r1.equals(r10)
            if (r1 == 0) goto L_0x006f
            r1 = r13
            goto L_0x0070
        L_0x006f:
            r1 = -1
        L_0x0070:
            if (r1 == 0) goto L_0x0087
            if (r1 == r14) goto L_0x0085
            if (r1 == r13) goto L_0x0083
            if (r1 == r12) goto L_0x0081
            if (r1 == r8) goto L_0x007f
            if (r1 == r11) goto L_0x007d
            goto L_0x0018
        L_0x007d:
            r7 = r9
            goto L_0x0018
        L_0x007f:
            r6 = r9
            goto L_0x0018
        L_0x0081:
            r5 = r9
            goto L_0x0018
        L_0x0083:
            r4 = r9
            goto L_0x0018
        L_0x0085:
            r3 = r9
            goto L_0x0018
        L_0x0087:
            r2 = r9
            goto L_0x0018
        L_0x0089:
            r16.zzl()
            java.util.GregorianCalendar r0 = new java.util.GregorianCalendar
            r1 = r0
            r1.<init>(r2, r3, r4, r5, r6, r7)
        L_0x0092:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzaao.read(com.google.ads.interactivemedia.v3.internal.zzacc):java.lang.Object");
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        Calendar calendar = (Calendar) obj;
        if (calendar == null) {
            zzace.zzg();
            return;
        }
        zzace.zzc();
        zzace.zzf("year");
        zzace.zzi((long) calendar.get(1));
        zzace.zzf("month");
        zzace.zzi((long) calendar.get(2));
        zzace.zzf("dayOfMonth");
        zzace.zzi((long) calendar.get(5));
        zzace.zzf("hourOfDay");
        zzace.zzi((long) calendar.get(11));
        zzace.zzf("minute");
        zzace.zzi((long) calendar.get(12));
        zzace.zzf("second");
        zzace.zzi((long) calendar.get(13));
        zzace.zze();
    }
}
