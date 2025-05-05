package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.internal.zzmz;
import com.google.android.recaptcha.internal.zznf;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public abstract class zznf<MessageType extends zznf<MessageType, BuilderType>, BuilderType extends zzmz<MessageType, BuilderType>> extends zzkq<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    protected zzpo zzc = zzpo.zzc();
    private int zzd = -1;

    protected static zznl zzA() {
        return zznz.zzf();
    }

    protected static zznm zzB() {
        return zzov.zze();
    }

    protected static zznm zzC(zznm zznm) {
        int size = zznm.size();
        return zznm.zzd(size + size);
    }

    static Object zzE(Method method, Object obj, Object... objArr) {
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

    protected static Object zzF(zzok zzok, String str, Object[] objArr) {
        return new zzow(zzok, str, objArr);
    }

    protected static void zzI(Class cls, zznf zznf) {
        zznf.zzH();
        zzb.put(cls, zznf);
    }

    protected static final boolean zzK(zznf zznf, boolean z) {
        byte byteValue = ((Byte) zznf.zzh(1, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzl = zzou.zza().zzb(zznf.getClass()).zzl(zznf);
        if (z) {
            zznf.zzh(2, true != zzl ? null : zznf, (Object) null);
        }
        return zzl;
    }

    private final int zzf(zzoy zzoy) {
        return zzou.zza().zzb(getClass()).zza(this);
    }

    private static zznf zzg(zznf zznf) throws zznp {
        if (zznf == null || zzK(zznf, true)) {
            return zznf;
        }
        throw new zzpm(zznf).zza();
    }

    /* access modifiers changed from: private */
    public static zznf zzi(zznf zznf, byte[] bArr, int i, int i2, zzmq zzmq) throws zznp {
        if (i2 == 0) {
            return zznf;
        }
        zznf zzv = zznf.zzv();
        try {
            zzoy zzb2 = zzou.zza().zzb(zzv.getClass());
            zzb2.zzi(zzv, bArr, 0, i2, new zzkv(zzmq));
            zzb2.zzf(zzv);
            return zzv;
        } catch (zznp e) {
            if (e.zzb()) {
                throw new zznp((IOException) e);
            }
            throw e;
        } catch (zzpm e2) {
            throw e2.zza();
        } catch (IOException e3) {
            if (e3.getCause() instanceof zznp) {
                throw ((zznp) e3.getCause());
            }
            throw new zznp(e3);
        } catch (IndexOutOfBoundsException unused) {
            throw new zznp("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    public static zzne zzs(zzok zzok, Object obj, zzok zzok2, zzni zzni, int i, zzpy zzpy, Class cls) {
        return new zzne(zzok, "", (zzok) null, new zznd((zzni) null, i, zzpy, false, false), cls);
    }

    static zznf zzu(Class cls) {
        Map map = zzb;
        zznf zznf = (zznf) map.get(cls);
        if (zznf == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zznf = (zznf) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zznf == null) {
            zznf = (zznf) ((zznf) zzpu.zze(cls)).zzh(6, (Object) null, (Object) null);
            if (zznf != null) {
                map.put(cls, zznf);
            } else {
                throw new IllegalStateException();
            }
        }
        return zznf;
    }

    protected static zznf zzw(zznf zznf, InputStream inputStream) throws zznp {
        zzlk zzlk;
        int i = zzlk.zzd;
        if (inputStream == null) {
            byte[] bArr = zznn.zzb;
            int length = bArr.length;
            zzlk = zzlk.zzH(bArr, 0, 0, false);
        } else {
            zzlk = new zzli(inputStream, 4096, (zzlj) null);
        }
        int i2 = zzmq.zzb;
        int i3 = zzou.zza;
        zzmq zzmq = zzmq.zza;
        zznf zzv = zznf.zzv();
        try {
            zzoy zzb2 = zzou.zza().zzb(zzv.getClass());
            zzb2.zzh(zzv, zzll.zzq(zzlk), zzmq);
            zzb2.zzf(zzv);
            zzg(zzv);
            return zzv;
        } catch (zznp e) {
            if (e.zzb()) {
                throw new zznp((IOException) e);
            }
            throw e;
        } catch (zzpm e2) {
            throw e2.zza();
        } catch (IOException e3) {
            if (e3.getCause() instanceof zznp) {
                throw ((zznp) e3.getCause());
            }
            throw new zznp(e3);
        } catch (RuntimeException e4) {
            if (e4.getCause() instanceof zznp) {
                throw ((zznp) e4.getCause());
            }
            throw e4;
        }
    }

    protected static zznf zzx(zznf zznf, byte[] bArr) throws zznp {
        int i = zzmq.zzb;
        int i2 = zzou.zza;
        zznf zzi = zzi(zznf, bArr, 0, bArr.length, zzmq.zza);
        zzg(zzi);
        return zzi;
    }

    protected static zznk zzy() {
        return zzng.zzf();
    }

    protected static zznk zzz(zznk zznk) {
        int size = zznk.size();
        return zznk.zzg(size + size);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return zzou.zza().zzb(getClass()).zzk(this, (zznf) obj);
    }

    public final int hashCode() {
        if (zzL()) {
            return zzn();
        }
        int i = this.zza;
        if (i != 0) {
            return i;
        }
        int zzn = zzn();
        this.zza = zzn;
        return zzn;
    }

    public final String toString() {
        return zzom.zza(this, super.toString());
    }

    public final zzos zzD() {
        return (zzos) zzh(7, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final void zzG() {
        zzou.zza().zzb(getClass()).zzf(this);
        zzH();
    }

    /* access modifiers changed from: package-private */
    public final void zzH() {
        this.zzd &= Integer.MAX_VALUE;
    }

    /* access modifiers changed from: package-private */
    public final void zzJ(int i) {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzL() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    /* access modifiers changed from: package-private */
    public final int zza(zzoy zzoy) {
        if (zzL()) {
            int zza = zzoy.zza(this);
            if (zza >= 0) {
                return zza;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + zza);
        }
        int i = this.zzd & Integer.MAX_VALUE;
        if (i != Integer.MAX_VALUE) {
            return i;
        }
        int zza2 = zzoy.zza(this);
        if (zza2 >= 0) {
            this.zzd = (this.zzd & Integer.MIN_VALUE) | zza2;
            return zza2;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + zza2);
    }

    public final /* synthetic */ zzoj zzad() {
        return (zzmz) zzh(5, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzoj zzae() {
        zzmz zzmz = (zzmz) zzh(5, (Object) null, (Object) null);
        zzmz.zzh(this);
        return zzmz;
    }

    public final void zze(zzlp zzlp) throws IOException {
        zzou.zza().zzb(getClass()).zzj(this, zzlq.zza(zzlp));
    }

    /* access modifiers changed from: protected */
    public abstract Object zzh(int i, Object obj, Object obj2);

    public final /* synthetic */ zzok zzm() {
        return (zznf) zzh(6, (Object) null, (Object) null);
    }

    /* access modifiers changed from: package-private */
    public final int zzn() {
        return zzou.zza().zzb(getClass()).zzb(this);
    }

    public final boolean zzp() {
        return zzK(this, true);
    }

    /* access modifiers changed from: protected */
    public final zzmz zzq() {
        return (zzmz) zzh(5, (Object) null, (Object) null);
    }

    public final zzmz zzr() {
        zzmz zzmz = (zzmz) zzh(5, (Object) null, (Object) null);
        zzmz.zzh(this);
        return zzmz;
    }

    /* access modifiers changed from: package-private */
    public final zznf zzv() {
        return (zznf) zzh(4, (Object) null, (Object) null);
    }

    public final int zzo() {
        int i;
        if (zzL()) {
            i = zzf((zzoy) null);
            if (i < 0) {
                throw new IllegalStateException("serialized size must be non-negative, was " + i);
            }
        } else {
            i = this.zzd & Integer.MAX_VALUE;
            if (i == Integer.MAX_VALUE) {
                i = zzf((zzoy) null);
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
