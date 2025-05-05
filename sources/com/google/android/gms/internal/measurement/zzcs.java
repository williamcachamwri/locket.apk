package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzcs implements SharedPreferences.Editor {
    private boolean zza;
    private Set<String> zzb;
    private Map<String, Object> zzc;
    private final /* synthetic */ zzcp zzd;

    public final SharedPreferences.Editor clear() {
        this.zza = true;
        return this;
    }

    public final SharedPreferences.Editor putBoolean(String str, boolean z) {
        zza(str, Boolean.valueOf(z));
        return this;
    }

    public final SharedPreferences.Editor putFloat(String str, float f) {
        zza(str, Float.valueOf(f));
        return this;
    }

    public final SharedPreferences.Editor putInt(String str, int i) {
        zza(str, Integer.valueOf(i));
        return this;
    }

    public final SharedPreferences.Editor putLong(String str, long j) {
        zza(str, Long.valueOf(j));
        return this;
    }

    public final SharedPreferences.Editor putString(String str, String str2) {
        zza(str, str2);
        return this;
    }

    public final SharedPreferences.Editor putStringSet(String str, Set<String> set) {
        zza(str, set);
        return this;
    }

    public final SharedPreferences.Editor remove(String str) {
        this.zzb.add(str);
        return this;
    }

    private zzcs(zzcp zzcp) {
        this.zzd = zzcp;
        this.zza = false;
        this.zzb = new HashSet();
        this.zzc = new HashMap();
    }

    public final void apply() {
        commit();
    }

    private final void zza(String str, Object obj) {
        if (obj != null) {
            this.zzc.put(str, obj);
        } else {
            remove(str);
        }
    }

    public final boolean commit() {
        if (this.zza) {
            this.zzd.zza.clear();
        }
        this.zzd.zza.keySet().removeAll(this.zzb);
        for (Map.Entry next : this.zzc.entrySet()) {
            this.zzd.zza.put((String) next.getKey(), next.getValue());
        }
        for (SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener : this.zzd.zzb) {
            UnmodifiableIterator<String> it = Sets.union(this.zzb, this.zzc.keySet()).iterator();
            while (it.hasNext()) {
                onSharedPreferenceChangeListener.onSharedPreferenceChanged(this.zzd, it.next());
            }
        }
        return this.zza || !this.zzb.isEmpty() || !this.zzc.isEmpty();
    }
}
