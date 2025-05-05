package com.google.android.gms.internal.atv_ads_framework;

import com.google.android.gms.internal.atv_ads_framework.zzdf;
import com.google.android.gms.internal.atv_ads_framework.zzdh;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public abstract class zzdh<MessageType extends zzdh<MessageType, BuilderType>, BuilderType extends zzdf<MessageType, BuilderType>> extends zzca<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    protected zzfq zzc = zzfq.zzc();
    private int zzd = -1;

    private final int zza(zzey zzey) {
        if (zzey != null) {
            return zzey.zza(this);
        }
        return zzew.zza().zzb(getClass()).zza(this);
    }

    static zzdh zzp(Class cls) {
        Map map = zzb;
        zzdh zzdh = (zzdh) map.get(cls);
        if (zzdh == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzdh = (zzdh) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzdh == null) {
            zzdh = (zzdh) ((zzdh) zzfz.zze(cls)).zze(6, (Object) null, (Object) null);
            if (zzdh != null) {
                map.put(cls, zzdh);
            } else {
                throw new IllegalStateException();
            }
        }
        return zzdh;
    }

    protected static zzdm zzr() {
        return zzdi.zze();
    }

    static Object zzt(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    protected static Object zzu(zzeo zzeo, String str, Object[] objArr) {
        return new zzex(zzeo, str, objArr);
    }

    protected static void zzx(Class cls, zzdh zzdh) {
        zzdh.zzw();
        zzb.put(cls, zzdh);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return zzew.zza().zzb(getClass()).zzg(this, (zzdh) obj);
    }

    public final int hashCode() {
        if (zzA()) {
            return zzm();
        }
        int i = this.zza;
        if (i != 0) {
            return i;
        }
        int zzm = zzm();
        this.zza = zzm;
        return zzm;
    }

    public final String toString() {
        return zzeq.zza(this, super.toString());
    }

    /* access modifiers changed from: package-private */
    public final boolean zzA() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    /* access modifiers changed from: protected */
    public abstract Object zze(int i, Object obj, Object obj2);

    public final /* synthetic */ zzeo zzl() {
        return (zzdh) zze(6, (Object) null, (Object) null);
    }

    /* access modifiers changed from: package-private */
    public final int zzm() {
        return zzew.zza().zzb(getClass()).zzb(this);
    }

    /* access modifiers changed from: protected */
    public final zzdf zzo() {
        return (zzdf) zze(5, (Object) null, (Object) null);
    }

    /* access modifiers changed from: package-private */
    public final zzdh zzq() {
        return (zzdh) zze(4, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzen zzs() {
        return (zzdf) zze(5, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final void zzv() {
        zzew.zza().zzb(getClass()).zzd(this);
        zzw();
    }

    /* access modifiers changed from: package-private */
    public final void zzw() {
        this.zzd &= Integer.MAX_VALUE;
    }

    /* access modifiers changed from: package-private */
    public final void zzy(int i) {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    public final void zzz(zzcv zzcv) throws IOException {
        zzew.zza().zzb(getClass()).zzf(this, zzcw.zza(zzcv));
    }

    /* access modifiers changed from: package-private */
    public final int zzj(zzey zzey) {
        if (zzA()) {
            int zza = zza(zzey);
            if (zza >= 0) {
                return zza;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + zza);
        }
        int i = this.zzd & Integer.MAX_VALUE;
        if (i != Integer.MAX_VALUE) {
            return i;
        }
        int zza2 = zza(zzey);
        if (zza2 >= 0) {
            this.zzd = (this.zzd & Integer.MIN_VALUE) | zza2;
            return zza2;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + zza2);
    }

    public final int zzn() {
        int i;
        if (zzA()) {
            i = zza((zzey) null);
            if (i < 0) {
                throw new IllegalStateException("serialized size must be non-negative, was " + i);
            }
        } else {
            i = this.zzd & Integer.MAX_VALUE;
            if (i == Integer.MAX_VALUE) {
                i = zza((zzey) null);
                if (i >= 0) {
                    this.zzd = (this.zzd & Integer.MIN_VALUE) | i;
                } else {
                    throw new IllegalStateException("serialized size must be non-negative, was " + i);
                }
            }
        }
        return i;
    }
}
