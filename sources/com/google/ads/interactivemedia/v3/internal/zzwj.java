package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzwj<T> {
    public final T fromJson(Reader reader) throws IOException {
        return read(new zzacc(reader));
    }

    public final T fromJsonTree(zzvw zzvw) {
        try {
            return read(new zzyz(zzvw));
        } catch (IOException e) {
            throw new zzvx((Throwable) e);
        }
    }

    public final zzwj<T> nullSafe() {
        return new zzwi(this);
    }

    public abstract T read(zzacc zzacc) throws IOException;

    public final void toJson(Writer writer, T t) throws IOException {
        write(new zzace(writer), t);
    }

    public final zzvw toJsonTree(T t) {
        try {
            zzzb zzzb = new zzzb();
            write(zzzb, t);
            return zzzb.zza();
        } catch (IOException e) {
            throw new zzvx((Throwable) e);
        }
    }

    public abstract void write(zzace zzace, T t) throws IOException;

    public final T fromJson(String str) throws IOException {
        return fromJson((Reader) new StringReader(str));
    }

    public final String toJson(T t) {
        StringWriter stringWriter = new StringWriter();
        try {
            toJson(stringWriter, t);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new zzvx((Throwable) e);
        }
    }
}
