package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.io.StringWriter;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public class zzvw {
    public final String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            zzace zzace = new zzace(stringWriter);
            zzace.zzr(zzwg.LENIENT);
            zzabh.zzV.write(zzace, this);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
