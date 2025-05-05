package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.io.FileUtils;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzrp<K, V> implements Map<K, V>, Serializable {
    @CheckForNull
    private transient zzrr zza;
    @CheckForNull
    private transient zzrr zzb;
    @CheckForNull
    private transient zzri zzc;

    zzrp() {
    }

    public static zzrp zzc(Map map) {
        if ((map instanceof zzrp) && !(map instanceof SortedMap)) {
            zzrp zzrp = (zzrp) map;
            if (!zzrp.zzk()) {
                return zzrp;
            }
        }
        Set entrySet = map.entrySet();
        zzro zzro = new zzro(entrySet instanceof Collection ? entrySet.size() : 4);
        zzro.zzb(entrySet);
        return zzro.zzc();
    }

    public static zzrp zzd() {
        return zzsj.zza;
    }

    public static zzrp zze(Object obj, Object obj2) {
        zzqt.zzb(obj, obj2);
        return zzsj.zzl(1, new Object[]{obj, obj2}, (zzro) null);
    }

    public static zzrp zzf(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12) {
        zzqt.zzb("IABTCF_AddtlConsent", "String");
        zzqt.zzb("IABTCF_gdprApplies", "Number");
        zzqt.zzb("IABTCF_TCString", "String");
        zzqt.zzb("IABUSPrivacy_String", "String");
        zzqt.zzb("IABGPP_HDR_GppString", "String");
        zzqt.zzb("IABGPP_GppSID", "String");
        return zzsj.zzl(6, new Object[]{"IABTCF_AddtlConsent", "String", "IABTCF_gdprApplies", "Number", "IABTCF_TCString", "String", "IABUSPrivacy_String", "String", "IABGPP_HDR_GppString", "String", "IABGPP_GppSID", "String"}, (zzro) null);
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
        return zzso.zza(entrySet());
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
        zzqt.zza(size, "size");
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
    public abstract zzri zza();

    /* renamed from: zzb */
    public zzri values() {
        zzri zzri = this.zzc;
        if (zzri != null) {
            return zzri;
        }
        zzri zza2 = zza();
        this.zzc = zza2;
        return zza2;
    }

    /* access modifiers changed from: package-private */
    public abstract zzrr zzg();

    /* access modifiers changed from: package-private */
    public abstract zzrr zzh();

    /* renamed from: zzi */
    public final zzrr entrySet() {
        zzrr zzrr = this.zza;
        if (zzrr != null) {
            return zzrr;
        }
        zzrr zzg = zzg();
        this.zza = zzg;
        return zzg;
    }

    /* renamed from: zzj */
    public zzrr keySet() {
        zzrr zzrr = this.zzb;
        if (zzrr != null) {
            return zzrr;
        }
        zzrr zzh = zzh();
        this.zzb = zzh;
        return zzh;
    }

    /* access modifiers changed from: package-private */
    public abstract boolean zzk();
}
