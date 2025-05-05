package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzcp implements SharedPreferences {
    /* access modifiers changed from: private */
    public final Map<String, Object> zza = new HashMap();
    /* access modifiers changed from: private */
    public final Set<SharedPreferences.OnSharedPreferenceChangeListener> zzb = new HashSet();

    public final float getFloat(String str, float f) {
        return ((Float) zza(str, Float.valueOf(f))).floatValue();
    }

    public final int getInt(String str, int i) {
        return ((Integer) zza(str, Integer.valueOf(i))).intValue();
    }

    public final long getLong(String str, long j) {
        return ((Long) zza(str, Long.valueOf(j))).longValue();
    }

    public final SharedPreferences.Editor edit() {
        return new zzcs(this);
    }

    private final <T> T zza(String str, T t) {
        T t2 = this.zza.get(str);
        return t2 != null ? t2 : t;
    }

    public final String getString(String str, String str2) {
        return (String) zza(str, str2);
    }

    public final Map<String, ?> getAll() {
        return this.zza;
    }

    public final Set<String> getStringSet(String str, Set<String> set) {
        return (Set) zza(str, set);
    }

    public final void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.zzb.add(onSharedPreferenceChangeListener);
    }

    public final void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.zzb.remove(onSharedPreferenceChangeListener);
    }

    public final boolean contains(String str) {
        return this.zza.containsKey(str);
    }

    public final boolean getBoolean(String str, boolean z) {
        return ((Boolean) zza(str, Boolean.valueOf(z))).booleanValue();
    }
}
