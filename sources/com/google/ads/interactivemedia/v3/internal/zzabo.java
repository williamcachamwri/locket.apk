package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzabo extends zzabk {
    private final Method zza = Class.class.getMethod("isRecord", new Class[0]);
    private final Method zzb = Class.class.getMethod("getRecordComponents", new Class[0]);
    private final Method zzc;
    private final Method zzd;

    private zzabo() throws NoSuchMethodException, ClassNotFoundException {
        super((zzabj) null);
        Class<?> cls = Class.forName("java.lang.reflect.RecordComponent");
        this.zzc = cls.getMethod("getName", new Class[0]);
        this.zzd = cls.getMethod("getType", new Class[0]);
    }

    public final Constructor zza(Class cls) {
        try {
            Object[] objArr = (Object[]) this.zzb.invoke(cls, new Object[0]);
            Class[] clsArr = new Class[objArr.length];
            for (int i = 0; i < objArr.length; i++) {
                clsArr[i] = (Class) this.zzd.invoke(objArr[i], new Object[0]);
            }
            return cls.getDeclaredConstructor(clsArr);
        } catch (ReflectiveOperationException e) {
            throw zzabp.zza(e);
        }
    }

    public final Method zzb(Class cls, Field field) {
        try {
            return cls.getMethod(field.getName(), new Class[0]);
        } catch (ReflectiveOperationException e) {
            throw zzabp.zza(e);
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzc(Class cls) {
        try {
            return ((Boolean) this.zza.invoke(cls, new Object[0])).booleanValue();
        } catch (ReflectiveOperationException e) {
            throw zzabp.zza(e);
        }
    }

    /* access modifiers changed from: package-private */
    public final String[] zzd(Class cls) {
        try {
            Object[] objArr = (Object[]) this.zzb.invoke(cls, new Object[0]);
            String[] strArr = new String[objArr.length];
            for (int i = 0; i < objArr.length; i++) {
                strArr[i] = (String) this.zzc.invoke(objArr[i], new Object[0]);
            }
            return strArr;
        } catch (ReflectiveOperationException e) {
            throw zzabp.zza(e);
        }
    }

    /* synthetic */ zzabo(zzabn zzabn) {
        super((zzabj) null);
        Class<?> cls = Class.forName("java.lang.reflect.RecordComponent");
        this.zzc = cls.getMethod("getName", new Class[0]);
        this.zzd = cls.getMethod("getType", new Class[0]);
    }
}
