package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzxk implements zzya {
    final /* synthetic */ Constructor zza;

    zzxk(Constructor constructor) {
        this.zza = constructor;
    }

    public final Object zza() {
        try {
            return this.zza.newInstance(new Object[0]);
        } catch (InstantiationException e) {
            String zzc = zzabp.zzc(this.zza);
            throw new RuntimeException("Failed to invoke constructor '" + zzc + "' with no args", e);
        } catch (InvocationTargetException e2) {
            String zzc2 = zzabp.zzc(this.zza);
            throw new RuntimeException("Failed to invoke constructor '" + zzc2 + "' with no args", e2.getCause());
        } catch (IllegalAccessException e3) {
            throw zzabp.zzb(e3);
        }
    }
}
