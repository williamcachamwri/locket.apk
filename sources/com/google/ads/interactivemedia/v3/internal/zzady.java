package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.internal.zzadu;
import com.google.ads.interactivemedia.v3.internal.zzady;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzady<MessageType extends zzady<MessageType, BuilderType>, BuilderType extends zzadu<MessageType, BuilderType>> extends zzach<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    protected zzagi zzc = zzagi.zzc();
    private int zzd = -1;

    static zzady zzaA(Class cls) {
        Map map = zzb;
        zzady zzady = (zzady) map.get(cls);
        if (zzady == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzady = (zzady) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzady == null) {
            zzady = (zzady) ((zzady) zzago.zze(cls)).zzm(6, (Object) null, (Object) null);
            if (zzady != null) {
                map.put(cls, zzady);
            } else {
                throw new IllegalStateException();
            }
        }
        return zzady;
    }

    protected static zzady zzaC(zzady zzady, zzacw zzacw) throws zzaeg {
        int i = zzadk.zzb;
        int i2 = zzafi.zza;
        zzadk zzadk = zzadk.zza;
        zzada zzk = zzacw.zzk();
        zzady zzaB = zzady.zzaB();
        try {
            zzaft zzb2 = zzafi.zza().zzb(zzaB.getClass());
            zzb2.zzh(zzaB, zzadb.zzq(zzk), zzadk);
            zzb2.zzf(zzaB);
            zzk.zzy(0);
            zzd(zzaB);
            zzd(zzaB);
            return zzaB;
        } catch (zzaeg e) {
            throw e;
        } catch (zzagg e2) {
            throw e2.zza();
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzaeg) {
                throw ((zzaeg) e3.getCause());
            }
            throw new zzaeg(e3);
        } catch (RuntimeException e4) {
            if (e4.getCause() instanceof zzaeg) {
                throw ((zzaeg) e4.getCause());
            }
            throw e4;
        }
    }

    protected static zzady zzaD(zzady zzady, zzacw zzacw, zzadk zzadk) throws zzaeg {
        zzada zzk = zzacw.zzk();
        zzady zzaB = zzady.zzaB();
        try {
            zzaft zzb2 = zzafi.zza().zzb(zzaB.getClass());
            zzb2.zzh(zzaB, zzadb.zzq(zzk), zzadk);
            zzb2.zzf(zzaB);
            zzk.zzy(0);
            zzd(zzaB);
            return zzaB;
        } catch (zzaeg e) {
            throw e;
        } catch (zzagg e2) {
            throw e2.zza();
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzaeg) {
                throw ((zzaeg) e3.getCause());
            }
            throw new zzaeg(e3);
        } catch (RuntimeException e4) {
            if (e4.getCause() instanceof zzaeg) {
                throw ((zzaeg) e4.getCause());
            }
            throw e4;
        }
    }

    protected static zzady zzaE(zzady zzady, byte[] bArr, zzadk zzadk) throws zzaeg {
        zzady zze = zze(zzady, bArr, 0, bArr.length, zzadk);
        zzd(zze);
        return zze;
    }

    protected static zzaec zzaF() {
        return zzadz.zzf();
    }

    protected static zzaed zzaG() {
        return zzafj.zze();
    }

    protected static zzaed zzaH(zzaed zzaed) {
        int size = zzaed.size();
        return zzaed.zzd(size == 0 ? 10 : size + size);
    }

    static Object zzaI(Method method, Object obj, Object... objArr) {
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

    protected static Object zzaJ(zzafb zzafb, String str, Object[] objArr) {
        return new zzafk(zzafb, str, objArr);
    }

    protected static void zzaM(Class cls, zzady zzady) {
        zzady.zzaL();
        zzb.put(cls, zzady);
    }

    protected static final boolean zzaO(zzady zzady, boolean z) {
        byte byteValue = ((Byte) zzady.zzm(1, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzl = zzafi.zza().zzb(zzady.getClass()).zzl(zzady);
        if (z) {
            zzady.zzm(2, true != zzl ? null : zzady, (Object) null);
        }
        return zzl;
    }

    private final int zzc(zzaft zzaft) {
        return zzafi.zza().zzb(getClass()).zza(this);
    }

    private static zzady zzd(zzady zzady) throws zzaeg {
        if (zzady == null || zzaO(zzady, true)) {
            return zzady;
        }
        throw new zzagg(zzady).zza();
    }

    private static zzady zze(zzady zzady, byte[] bArr, int i, int i2, zzadk zzadk) throws zzaeg {
        if (i2 == 0) {
            return zzady;
        }
        zzady zzaB = zzady.zzaB();
        try {
            zzaft zzb2 = zzafi.zza().zzb(zzaB.getClass());
            zzb2.zzi(zzaB, bArr, 0, i2, new zzacl(zzadk));
            zzb2.zzf(zzaB);
            return zzaB;
        } catch (zzaeg e) {
            throw e;
        } catch (zzagg e2) {
            throw e2.zza();
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzaeg) {
                throw ((zzaeg) e3.getCause());
            }
            throw new zzaeg(e3);
        } catch (IndexOutOfBoundsException unused) {
            throw new zzaeg("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return zzafi.zza().zzb(getClass()).zzk(this, (zzady) obj);
    }

    public final int hashCode() {
        if (zzaP()) {
            return zzaw();
        }
        int i = this.zza;
        if (i != 0) {
            return i;
        }
        int zzaw = zzaw();
        this.zza = zzaw;
        return zzaw;
    }

    public final String toString() {
        return zzafd.zza(this, super.toString());
    }

    /* access modifiers changed from: package-private */
    public final zzady zzaB() {
        return (zzady) zzm(4, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final void zzaK() {
        zzafi.zza().zzb(getClass()).zzf(this);
        zzaL();
    }

    /* access modifiers changed from: package-private */
    public final void zzaL() {
        this.zzd &= Integer.MAX_VALUE;
    }

    /* access modifiers changed from: package-private */
    public final void zzaN(int i) {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaP() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    public final /* synthetic */ zzafa zzaQ() {
        return (zzadu) zzm(5, (Object) null, (Object) null);
    }

    public final void zzaR(zzadf zzadf) throws IOException {
        zzafi.zza().zzb(getClass()).zzj(this, zzadg.zza(zzadf));
    }

    public final /* synthetic */ zzafb zzaS() {
        return (zzady) zzm(6, (Object) null, (Object) null);
    }

    public final boolean zzaT() {
        return zzaO(this, true);
    }

    /* access modifiers changed from: package-private */
    public final int zzat(zzaft zzaft) {
        if (zzaP()) {
            int zza = zzaft.zza(this);
            if (zza >= 0) {
                return zza;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + zza);
        }
        int i = this.zzd & Integer.MAX_VALUE;
        if (i != Integer.MAX_VALUE) {
            return i;
        }
        int zza2 = zzaft.zza(this);
        if (zza2 >= 0) {
            this.zzd = (this.zzd & Integer.MIN_VALUE) | zza2;
            return zza2;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + zza2);
    }

    /* access modifiers changed from: package-private */
    public final int zzaw() {
        return zzafi.zza().zzb(getClass()).zzb(this);
    }

    /* access modifiers changed from: protected */
    public final zzadu zzay() {
        return (zzadu) zzm(5, (Object) null, (Object) null);
    }

    public final zzadu zzaz() {
        zzadu zzadu = (zzadu) zzm(5, (Object) null, (Object) null);
        zzadu.zzaj(this);
        return zzadu;
    }

    /* access modifiers changed from: protected */
    public abstract Object zzm(int i, Object obj, Object obj2);

    public final int zzax() {
        int i;
        if (zzaP()) {
            i = zzc((zzaft) null);
            if (i < 0) {
                throw new IllegalStateException("serialized size must be non-negative, was " + i);
            }
        } else {
            i = this.zzd & Integer.MAX_VALUE;
            if (i == Integer.MAX_VALUE) {
                i = zzc((zzaft) null);
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
