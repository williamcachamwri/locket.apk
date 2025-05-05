package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzzp extends zzzl {
    static final Map zza;
    private final Constructor zzb;
    private final Object[] zzc;
    private final Map zzd = new HashMap();

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(Byte.TYPE, (byte) 0);
        hashMap.put(Short.TYPE, (short) 0);
        hashMap.put(Integer.TYPE, 0);
        hashMap.put(Long.TYPE, 0L);
        hashMap.put(Float.TYPE, Float.valueOf(0.0f));
        hashMap.put(Double.TYPE, Double.valueOf(0.0d));
        hashMap.put(Character.TYPE, 0);
        hashMap.put(Boolean.TYPE, false);
        zza = hashMap;
    }

    zzzp(Class cls, zzzo zzzo, boolean z) {
        super(zzzo);
        Constructor zzg = zzabp.zzg(cls);
        this.zzb = zzg;
        if (z) {
            zzzq.zzb((Object) null, zzg);
        } else {
            zzabp.zzi(zzg);
        }
        String[] zzl = zzabp.zzl(cls);
        for (int i = 0; i < zzl.length; i++) {
            this.zzd.put(zzl[i], Integer.valueOf(i));
        }
        Class[] parameterTypes = this.zzb.getParameterTypes();
        this.zzc = new Object[parameterTypes.length];
        for (int i2 = 0; i2 < parameterTypes.length; i2++) {
            this.zzc[i2] = zza.get(parameterTypes[i2]);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ Object zza() {
        return (Object[]) this.zzc.clone();
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ void zzc(Object obj, zzacc zzacc, zzzm zzzm) throws IllegalAccessException, IOException {
        Object[] objArr = (Object[]) obj;
        Integer num = (Integer) this.zzd.get(zzzm.zzi);
        if (num != null) {
            zzzm.zza(zzacc, num.intValue(), objArr);
            return;
        }
        Constructor constructor = this.zzb;
        String str = zzzm.zzi;
        String zzc2 = zzabp.zzc(constructor);
        throw new IllegalStateException("Could not find the index in the constructor '" + zzc2 + "' for field with name '" + str + "', unable to determine which argument in the constructor the field corresponds to. This is unexpected behavior, as we expect the RecordComponents to have the same names as the fields in the Java class, and that the order of the RecordComponents is the same as the order of the canonical constructor parameters.");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: zzd */
    public final Object zzb(Object[] objArr) {
        try {
            return this.zzb.newInstance(objArr);
        } catch (IllegalAccessException e) {
            throw zzabp.zzb(e);
        } catch (IllegalArgumentException | InstantiationException e2) {
            String zzc2 = zzabp.zzc(this.zzb);
            String arrays = Arrays.toString(objArr);
            throw new RuntimeException("Failed to invoke constructor '" + zzc2 + "' with args " + arrays, e2);
        } catch (InvocationTargetException e3) {
            String zzc3 = zzabp.zzc(this.zzb);
            String arrays2 = Arrays.toString(objArr);
            throw new RuntimeException("Failed to invoke constructor '" + zzc3 + "' with args " + arrays2, e3.getCause());
        }
    }
}
