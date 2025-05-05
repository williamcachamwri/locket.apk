package com.google.android.gms.internal.pal;

import com.google.android.gms.internal.pal.zzacv;
import com.google.android.gms.internal.pal.zzacz;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public abstract class zzacz<MessageType extends zzacz<MessageType, BuilderType>, BuilderType extends zzacv<MessageType, BuilderType>> extends zzabi<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    protected zzafj zzc = zzafj.zzc();
    protected int zzd = -1;

    private static zzacz zza(zzacz zzacz) throws zzadi {
        if (zzacz == null || zzacz.zzaH()) {
            return zzacz;
        }
        zzadi zza = new zzafh(zzacz).zza();
        zza.zzh(zzacz);
        throw zza;
    }

    protected static zzadf zzaA(zzadf zzadf) {
        int size = zzadf.size();
        return zzadf.zzd(size == 0 ? 10 : size + size);
    }

    static Object zzaD(Method method, Object obj, Object... objArr) {
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

    protected static Object zzaE(zzaef zzaef, String str, Object[] objArr) {
        return new zzaep(zzaef, str, objArr);
    }

    protected static void zzaF(Class cls, zzacz zzacz) {
        zzb.put(cls, zzacz);
    }

    static zzacz zzav(Class cls) {
        Map map = zzb;
        zzacz zzacz = (zzacz) map.get(cls);
        if (zzacz == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzacz = (zzacz) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzacz == null) {
            zzacz = (zzacz) ((zzacz) zzafs.zze(cls)).zzb(6, (Object) null, (Object) null);
            if (zzacz != null) {
                map.put(cls, zzacz);
            } else {
                throw new IllegalStateException();
            }
        }
        return zzacz;
    }

    protected static zzacz zzaw(zzacz zzacz, zzaby zzaby, zzacm zzacm) throws zzadi {
        zzacc zzh = zzaby.zzh();
        zzacz zzacz2 = (zzacz) zzacz.zzb(4, (Object) null, (Object) null);
        try {
            zzaer zzb2 = zzaen.zza().zzb(zzacz2.getClass());
            zzb2.zzh(zzacz2, zzacd.zzq(zzh), zzacm);
            zzb2.zzf(zzacz2);
            try {
                zzh.zzm(0);
                zza(zzacz2);
                return zzacz2;
            } catch (zzadi e) {
                e.zzh(zzacz2);
                throw e;
            }
        } catch (zzadi e2) {
            e2.zzh(zzacz2);
            throw e2;
        } catch (zzafh e3) {
            zzadi zza = e3.zza();
            zza.zzh(zzacz2);
            throw zza;
        } catch (IOException e4) {
            if (e4.getCause() instanceof zzadi) {
                throw ((zzadi) e4.getCause());
            }
            zzadi zzadi = new zzadi(e4);
            zzadi.zzh(zzacz2);
            throw zzadi;
        } catch (RuntimeException e5) {
            if (e5.getCause() instanceof zzadi) {
                throw ((zzadi) e5.getCause());
            }
            throw e5;
        }
    }

    protected static zzacz zzax(zzacz zzacz, byte[] bArr, zzacm zzacm) throws zzadi {
        zzacz zzc2 = zzc(zzacz, bArr, 0, bArr.length, zzacm);
        zza(zzc2);
        return zzc2;
    }

    protected static zzade zzay() {
        return zzada.zzf();
    }

    protected static zzadf zzaz() {
        return zzaeo.zze();
    }

    private static zzacz zzc(zzacz zzacz, byte[] bArr, int i, int i2, zzacm zzacm) throws zzadi {
        zzacz zzacz2 = (zzacz) zzacz.zzb(4, (Object) null, (Object) null);
        try {
            zzaer zzb2 = zzaen.zza().zzb(zzacz2.getClass());
            zzb2.zzi(zzacz2, bArr, 0, i2, new zzabl(zzacm));
            zzb2.zzf(zzacz2);
            if (zzacz2.zza == 0) {
                return zzacz2;
            }
            throw new RuntimeException();
        } catch (zzadi e) {
            e.zzh(zzacz2);
            throw e;
        } catch (zzafh e2) {
            zzadi zza = e2.zza();
            zza.zzh(zzacz2);
            throw zza;
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzadi) {
                throw ((zzadi) e3.getCause());
            }
            zzadi zzadi = new zzadi(e3);
            zzadi.zzh(zzacz2);
            throw zzadi;
        } catch (IndexOutOfBoundsException unused) {
            zzadi zzi = zzadi.zzi();
            zzi.zzh(zzacz2);
            throw zzi;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzaen.zza().zzb(getClass()).zzk(this, (zzacz) obj);
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zza;
        if (i != 0) {
            return i;
        }
        int zzb2 = zzaen.zza().zzb(getClass()).zzb(this);
        this.zza = zzb2;
        return zzb2;
    }

    public final String toString() {
        return zzaeh.zza(this, super.toString());
    }

    public final /* synthetic */ zzaee zzaB() {
        return (zzacv) zzb(5, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzaee zzaC() {
        zzacv zzacv = (zzacv) zzb(5, (Object) null, (Object) null);
        zzacv.zzal(this);
        return zzacv;
    }

    public final void zzaG(zzach zzach) throws IOException {
        zzaen.zza().zzb(getClass()).zzj(this, zzaci.zza(zzach));
    }

    public final boolean zzaH() {
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) zzb(1, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzl = zzaen.zza().zzb(getClass()).zzl(this);
        if (!booleanValue) {
            return zzl;
        }
        zzb(2, true != zzl ? null : this, (Object) null);
        return zzl;
    }

    /* access modifiers changed from: package-private */
    public final int zzap() {
        return this.zzd;
    }

    public final /* synthetic */ zzaef zzaq() {
        return (zzacz) zzb(6, (Object) null, (Object) null);
    }

    /* access modifiers changed from: package-private */
    public final void zzar(int i) {
        this.zzd = i;
    }

    public final int zzat() {
        int i = this.zzd;
        if (i != -1) {
            return i;
        }
        int zza = zzaen.zza().zzb(getClass()).zza(this);
        this.zzd = zza;
        return zza;
    }

    /* access modifiers changed from: protected */
    public final zzacv zzau() {
        return (zzacv) zzb(5, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public abstract Object zzb(int i, Object obj, Object obj2);
}
