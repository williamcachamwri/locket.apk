package com.google.android.gms.internal.fido;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.io.FileUtils;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
public abstract class zzba implements Map, Serializable {
    static final Map.Entry[] zza = new Map.Entry[0];
    @CheckForNull
    private transient zzbc zzb;

    zzba() {
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public final boolean containsKey(@CheckForNull Object obj) {
        return get(obj) != null;
    }

    public final boolean containsValue(@CheckForNull Object obj) {
        return values().contains(obj);
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        return entrySet().equals(((Map) obj).entrySet());
    }

    @CheckForNull
    public abstract Object get(@CheckForNull Object obj);

    @CheckForNull
    public final Object getOrDefault(@CheckForNull Object obj, @CheckForNull Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    public final int hashCode() {
        return zzbx.zza(entrySet());
    }

    public final boolean isEmpty() {
        return size() == 0;
    }

    @CheckForNull
    @Deprecated
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @CheckForNull
    @Deprecated
    public final Object remove(@CheckForNull Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        if (size >= 0) {
            StringBuilder sb = new StringBuilder((int) Math.min(((long) size) * 8, FileUtils.ONE_GB));
            sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
            boolean z = true;
            for (Map.Entry entry : entrySet()) {
                if (!z) {
                    sb.append(", ");
                }
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
                z = false;
            }
            sb.append(AbstractJsonLexerKt.END_OBJ);
            return sb.toString();
        }
        throw new IllegalArgumentException("size cannot be negative but was: " + size);
    }

    /* renamed from: zza */
    public zzav values() {
        throw null;
    }

    /* access modifiers changed from: package-private */
    public abstract zzbc zzb();

    /* renamed from: zzc */
    public final zzbc entrySet() {
        zzbc zzbc = this.zzb;
        if (zzbc != null) {
            return zzbc;
        }
        zzbc zzb2 = zzb();
        this.zzb = zzb2;
        return zzb2;
    }

    /* renamed from: zzd */
    public zzbc keySet() {
        throw null;
    }
}
