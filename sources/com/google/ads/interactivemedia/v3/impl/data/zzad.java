package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.AdErrorEvent;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzad extends zzbr {
    private final AdErrorEvent adErrorEvent;
    private final zzbp component;
    private final Long latency;
    private final String latencyMeasurementProtoBase64String;
    private final zzbw loggableException;
    private final zzbq method;
    private final long timestamp;

    zzad(long j, zzbp zzbp, zzbq zzbq, AdErrorEvent adErrorEvent2, zzbw zzbw, Long l, String str) {
        this.timestamp = j;
        this.component = zzbp;
        this.method = zzbq;
        this.adErrorEvent = adErrorEvent2;
        this.loggableException = zzbw;
        this.latency = l;
        this.latencyMeasurementProtoBase64String = str;
    }

    public AdErrorEvent adErrorEvent() {
        return this.adErrorEvent;
    }

    public zzbp component() {
        return this.component;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002a, code lost:
        r1 = r7.method;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003f, code lost:
        r1 = r7.adErrorEvent;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0054, code lost:
        r1 = r7.loggableException;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0069, code lost:
        r1 = r7.latency;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x007e, code lost:
        r1 = r7.latencyMeasurementProtoBase64String;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
        r1 = r7.component;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r8) {
        /*
            r7 = this;
            r0 = 1
            if (r8 != r7) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r8 instanceof com.google.ads.interactivemedia.v3.impl.data.zzbr
            r2 = 0
            if (r1 == 0) goto L_0x0095
            com.google.ads.interactivemedia.v3.impl.data.zzbr r8 = (com.google.ads.interactivemedia.v3.impl.data.zzbr) r8
            long r3 = r7.timestamp
            long r5 = r8.timestamp()
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x0095
            com.google.ads.interactivemedia.v3.impl.data.zzbp r1 = r7.component
            if (r1 != 0) goto L_0x0020
            com.google.ads.interactivemedia.v3.impl.data.zzbp r1 = r8.component()
            if (r1 != 0) goto L_0x0095
            goto L_0x002a
        L_0x0020:
            com.google.ads.interactivemedia.v3.impl.data.zzbp r3 = r8.component()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0095
        L_0x002a:
            com.google.ads.interactivemedia.v3.impl.data.zzbq r1 = r7.method
            if (r1 != 0) goto L_0x0035
            com.google.ads.interactivemedia.v3.impl.data.zzbq r1 = r8.method()
            if (r1 != 0) goto L_0x0095
            goto L_0x003f
        L_0x0035:
            com.google.ads.interactivemedia.v3.impl.data.zzbq r3 = r8.method()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0095
        L_0x003f:
            com.google.ads.interactivemedia.v3.api.AdErrorEvent r1 = r7.adErrorEvent
            if (r1 != 0) goto L_0x004a
            com.google.ads.interactivemedia.v3.api.AdErrorEvent r1 = r8.adErrorEvent()
            if (r1 != 0) goto L_0x0095
            goto L_0x0054
        L_0x004a:
            com.google.ads.interactivemedia.v3.api.AdErrorEvent r3 = r8.adErrorEvent()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0095
        L_0x0054:
            com.google.ads.interactivemedia.v3.impl.data.zzbw r1 = r7.loggableException
            if (r1 != 0) goto L_0x005f
            com.google.ads.interactivemedia.v3.impl.data.zzbw r1 = r8.loggableException()
            if (r1 != 0) goto L_0x0095
            goto L_0x0069
        L_0x005f:
            com.google.ads.interactivemedia.v3.impl.data.zzbw r3 = r8.loggableException()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0095
        L_0x0069:
            java.lang.Long r1 = r7.latency
            if (r1 != 0) goto L_0x0074
            java.lang.Long r1 = r8.latency()
            if (r1 != 0) goto L_0x0095
            goto L_0x007e
        L_0x0074:
            java.lang.Long r3 = r8.latency()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0095
        L_0x007e:
            java.lang.String r1 = r7.latencyMeasurementProtoBase64String
            if (r1 != 0) goto L_0x0089
            java.lang.String r8 = r8.latencyMeasurementProtoBase64String()
            if (r8 != 0) goto L_0x0095
            goto L_0x0094
        L_0x0089:
            java.lang.String r8 = r8.latencyMeasurementProtoBase64String()
            boolean r8 = r1.equals(r8)
            if (r8 != 0) goto L_0x0094
            goto L_0x0095
        L_0x0094:
            return r0
        L_0x0095:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.impl.data.zzad.equals(java.lang.Object):boolean");
    }

    public Long latency() {
        return this.latency;
    }

    public String latencyMeasurementProtoBase64String() {
        return this.latencyMeasurementProtoBase64String;
    }

    public zzbw loggableException() {
        return this.loggableException;
    }

    public zzbq method() {
        return this.method;
    }

    public long timestamp() {
        return this.timestamp;
    }

    public String toString() {
        zzbw zzbw = this.loggableException;
        AdErrorEvent adErrorEvent2 = this.adErrorEvent;
        zzbq zzbq = this.method;
        String valueOf = String.valueOf(this.component);
        String valueOf2 = String.valueOf(zzbq);
        String valueOf3 = String.valueOf(adErrorEvent2);
        String valueOf4 = String.valueOf(zzbw);
        return "InstrumentationData{timestamp=" + this.timestamp + ", component=" + valueOf + ", method=" + valueOf2 + ", adErrorEvent=" + valueOf3 + ", loggableException=" + valueOf4 + ", latency=" + this.latency + ", latencyMeasurementProtoBase64String=" + this.latencyMeasurementProtoBase64String + "}";
    }

    public int hashCode() {
        int i;
        int i2;
        int i3;
        int i4;
        zzbp zzbp = this.component;
        int i5 = 0;
        int hashCode = zzbp == null ? 0 : zzbp.hashCode();
        long j = this.timestamp;
        zzbq zzbq = this.method;
        if (zzbq == null) {
            i = 0;
        } else {
            i = zzbq.hashCode();
        }
        int i6 = hashCode ^ ((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003);
        AdErrorEvent adErrorEvent2 = this.adErrorEvent;
        if (adErrorEvent2 == null) {
            i2 = 0;
        } else {
            i2 = adErrorEvent2.hashCode();
        }
        int i7 = ((((i6 * 1000003) ^ i) * 1000003) ^ i2) * 1000003;
        zzbw zzbw = this.loggableException;
        if (zzbw == null) {
            i3 = 0;
        } else {
            i3 = zzbw.hashCode();
        }
        int i8 = (i7 ^ i3) * 1000003;
        Long l = this.latency;
        if (l == null) {
            i4 = 0;
        } else {
            i4 = l.hashCode();
        }
        int i9 = (i8 ^ i4) * 1000003;
        String str = this.latencyMeasurementProtoBase64String;
        if (str != null) {
            i5 = str.hashCode();
        }
        return i9 ^ i5;
    }
}
