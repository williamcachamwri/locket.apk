package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.io.FileUtils;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public abstract class zzcu implements Map, Serializable {
    @CheckForNull
    private transient zzcv zza;
    @CheckForNull
    private transient zzcv zzb;
    @CheckForNull
    private transient zzcn zzc;

    zzcu() {
    }

    public static zzcu zzc(Object obj, Object obj2) {
        zzby.zzb("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
        return zzdp.zzg(1, new Object[]{"optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID}, (zzct) null);
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
        return zzds.zza(entrySet());
    }

    public final boolean isEmpty() {
        return false;
    }

    public final /* bridge */ /* synthetic */ Set keySet() {
        zzcv zzcv = this.zzb;
        if (zzcv != null) {
            return zzcv;
        }
        zzcv zze = zze();
        this.zzb = zze;
        return zze;
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
        zzby.zza(size, "size");
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

    /* access modifiers changed from: package-private */
    public abstract zzcn zza();

    /* renamed from: zzb */
    public final zzcn values() {
        zzcn zzcn = this.zzc;
        if (zzcn != null) {
            return zzcn;
        }
        zzcn zza2 = zza();
        this.zzc = zza2;
        return zza2;
    }

    /* access modifiers changed from: package-private */
    public abstract zzcv zzd();

    /* access modifiers changed from: package-private */
    public abstract zzcv zze();

    /* renamed from: zzf */
    public final zzcv entrySet() {
        zzcv zzcv = this.zza;
        if (zzcv != null) {
            return zzcv;
        }
        zzcv zzd = zzd();
        this.zza = zzd;
        return zzd;
    }
}
