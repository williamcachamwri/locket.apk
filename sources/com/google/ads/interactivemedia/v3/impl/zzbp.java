package com.google.ads.interactivemedia.v3.impl;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzbp implements zzbq {
    private zzbp() {
        throw null;
    }

    /* synthetic */ zzbp(zzbo zzbo) {
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x00d8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.ads.interactivemedia.v3.impl.data.zzbz zza(com.google.ads.interactivemedia.v3.impl.data.zzby r9) {
        /*
            r8 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ IOException -> 0x00b1, all -> 0x00af }
            java.lang.String r2 = r9.url()     // Catch:{ IOException -> 0x00b1, all -> 0x00af }
            r1.<init>(r2)     // Catch:{ IOException -> 0x00b1, all -> 0x00af }
            java.net.URLConnection r1 = r1.openConnection()     // Catch:{ IOException -> 0x00b1, all -> 0x00af }
            java.lang.Object r1 = com.google.firebase.perf.network.FirebasePerfUrlConnection.instrument(r1)     // Catch:{ IOException -> 0x00b1, all -> 0x00af }
            java.net.URLConnection r1 = (java.net.URLConnection) r1     // Catch:{ IOException -> 0x00b1, all -> 0x00af }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ IOException -> 0x00b1, all -> 0x00af }
            java.lang.String r0 = "User-Agent"
            java.lang.String r2 = r9.userAgent()     // Catch:{ IOException -> 0x00ad }
            r1.setRequestProperty(r0, r2)     // Catch:{ IOException -> 0x00ad }
            int r0 = r9.connectionTimeoutMs()     // Catch:{ IOException -> 0x00ad }
            r1.setConnectTimeout(r0)     // Catch:{ IOException -> 0x00ad }
            int r0 = r9.readTimeoutMs()     // Catch:{ IOException -> 0x00ad }
            r1.setReadTimeout(r0)     // Catch:{ IOException -> 0x00ad }
            com.google.ads.interactivemedia.v3.impl.data.zzbx r0 = r9.requestType()     // Catch:{ IOException -> 0x00ad }
            com.google.ads.interactivemedia.v3.impl.data.zzbx r2 = com.google.ads.interactivemedia.v3.impl.data.zzbx.POST     // Catch:{ IOException -> 0x00ad }
            if (r0 != r2) goto L_0x0062
            r0 = 1
            r1.setDoOutput(r0)     // Catch:{ IOException -> 0x00ad }
            r0 = 0
            r1.setChunkedStreamingMode(r0)     // Catch:{ IOException -> 0x00ad }
            java.lang.String r0 = r9.content()     // Catch:{ IOException -> 0x00ad }
            if (r0 == 0) goto L_0x0062
            java.io.OutputStream r2 = r1.getOutputStream()     // Catch:{ IOException -> 0x00ad }
            java.nio.charset.Charset r3 = java.nio.charset.StandardCharsets.UTF_8     // Catch:{ all -> 0x0056 }
            byte[] r0 = r0.getBytes(r3)     // Catch:{ all -> 0x0056 }
            r2.write(r0)     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x0062
            r2.close()     // Catch:{ IOException -> 0x00ad }
            goto L_0x0062
        L_0x0056:
            r0 = move-exception
            if (r2 == 0) goto L_0x0061
            r2.close()     // Catch:{ all -> 0x005d }
            goto L_0x0061
        L_0x005d:
            r2 = move-exception
            r0.addSuppressed(r2)     // Catch:{ IOException -> 0x00ad }
        L_0x0061:
            throw r0     // Catch:{ IOException -> 0x00ad }
        L_0x0062:
            java.io.InputStream r0 = r1.getInputStream()     // Catch:{ IOException -> 0x00ad }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ all -> 0x00a1 }
            r2.<init>(r0)     // Catch:{ all -> 0x00a1 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a1 }
            r3.<init>()     // Catch:{ all -> 0x00a1 }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ all -> 0x00a1 }
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ all -> 0x00a1 }
            java.nio.charset.Charset r6 = java.nio.charset.StandardCharsets.UTF_8     // Catch:{ all -> 0x00a1 }
            r5.<init>(r2, r6)     // Catch:{ all -> 0x00a1 }
            r4.<init>(r5)     // Catch:{ all -> 0x00a1 }
        L_0x007c:
            java.lang.String r2 = r4.readLine()     // Catch:{ all -> 0x00a1 }
            if (r2 == 0) goto L_0x0086
            r3.append(r2)     // Catch:{ all -> 0x00a1 }
            goto L_0x007c
        L_0x0086:
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x00a1 }
            if (r0 == 0) goto L_0x008f
            r0.close()     // Catch:{ IOException -> 0x00ad }
        L_0x008f:
            java.lang.String r0 = r1.getContentType()     // Catch:{ IOException -> 0x00ad }
            if (r1 == 0) goto L_0x0098
            r1.disconnect()
        L_0x0098:
            java.lang.String r9 = r9.id()
            com.google.ads.interactivemedia.v3.impl.data.zzbz r9 = com.google.ads.interactivemedia.v3.impl.data.zzbz.forResponse(r9, r2, r0)
            return r9
        L_0x00a1:
            r2 = move-exception
            if (r0 == 0) goto L_0x00ac
            r0.close()     // Catch:{ all -> 0x00a8 }
            goto L_0x00ac
        L_0x00a8:
            r0 = move-exception
            r2.addSuppressed(r0)     // Catch:{ IOException -> 0x00ad }
        L_0x00ac:
            throw r2     // Catch:{ IOException -> 0x00ad }
        L_0x00ad:
            r0 = move-exception
            goto L_0x00b5
        L_0x00af:
            r9 = move-exception
            goto L_0x00d6
        L_0x00b1:
            r1 = move-exception
            r7 = r1
            r1 = r0
            r0 = r7
        L_0x00b5:
            boolean r0 = r0 instanceof java.net.SocketTimeoutException     // Catch:{ all -> 0x00d4 }
            if (r0 == 0) goto L_0x00c4
            java.lang.String r9 = r9.id()     // Catch:{ all -> 0x00d4 }
            r0 = 101(0x65, float:1.42E-43)
            com.google.ads.interactivemedia.v3.impl.data.zzbz r9 = com.google.ads.interactivemedia.v3.impl.data.zzbz.forError(r9, r0)     // Catch:{ all -> 0x00d4 }
            goto L_0x00ce
        L_0x00c4:
            java.lang.String r9 = r9.id()     // Catch:{ all -> 0x00d4 }
            r0 = 100
            com.google.ads.interactivemedia.v3.impl.data.zzbz r9 = com.google.ads.interactivemedia.v3.impl.data.zzbz.forError(r9, r0)     // Catch:{ all -> 0x00d4 }
        L_0x00ce:
            if (r1 == 0) goto L_0x00d3
            r1.disconnect()
        L_0x00d3:
            return r9
        L_0x00d4:
            r9 = move-exception
            r0 = r1
        L_0x00d6:
            if (r0 == 0) goto L_0x00db
            r0.disconnect()
        L_0x00db:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.impl.zzbp.zza(com.google.ads.interactivemedia.v3.impl.data.zzby):com.google.ads.interactivemedia.v3.impl.data.zzbz");
    }
}
