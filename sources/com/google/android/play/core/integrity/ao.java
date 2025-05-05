package com.google.android.play.core.integrity;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class ao extends IntegrityTokenRequest {

    /* renamed from: a  reason: collision with root package name */
    private final String f33a;
    private final Long b;
    private final Object c = null;

    /* synthetic */ ao(String str, Long l, Object obj, an anVar) {
        this.f33a = str;
        this.b = l;
    }

    private static boolean a() {
        return true;
    }

    public final Long cloudProjectNumber() {
        return this.b;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0043  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = 1
            if (r6 != r5) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r6 instanceof com.google.android.play.core.integrity.IntegrityTokenRequest
            r2 = 0
            if (r1 == 0) goto L_0x002f
            r1 = r6
            com.google.android.play.core.integrity.IntegrityTokenRequest r1 = (com.google.android.play.core.integrity.IntegrityTokenRequest) r1
            java.lang.String r3 = r5.f33a
            java.lang.String r4 = r1.nonce()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x002f
            java.lang.Long r3 = r5.b
            if (r3 != 0) goto L_0x0023
            java.lang.Long r1 = r1.cloudProjectNumber()
            if (r1 != 0) goto L_0x002f
            goto L_0x002d
        L_0x0023:
            java.lang.Long r1 = r1.cloudProjectNumber()
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x002f
        L_0x002d:
            r1 = r0
            goto L_0x0030
        L_0x002f:
            r1 = r2
        L_0x0030:
            boolean r3 = r6 instanceof com.google.android.play.core.integrity.ao
            if (r3 == 0) goto L_0x0043
            boolean r3 = a()
            if (r3 == 0) goto L_0x0043
            com.google.android.play.core.integrity.ao r6 = (com.google.android.play.core.integrity.ao) r6
            if (r1 == 0) goto L_0x0041
            java.lang.Object r6 = r6.c
            goto L_0x0044
        L_0x0041:
            r0 = r2
            goto L_0x0044
        L_0x0043:
            r0 = r1
        L_0x0044:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.integrity.ao.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int i;
        int hashCode = this.f33a.hashCode() ^ 1000003;
        Long l = this.b;
        if (l == null) {
            i = 0;
        } else {
            i = l.hashCode();
        }
        int i2 = (hashCode * 1000003) ^ i;
        return a() ? i2 * 1000003 : i2;
    }

    public final String nonce() {
        return this.f33a;
    }

    public final String toString() {
        String str = "IntegrityTokenRequest{nonce=" + this.f33a + ", cloudProjectNumber=" + this.b;
        if (a()) {
            str = str.concat(", network=null");
        }
        return str.concat("}");
    }
}
