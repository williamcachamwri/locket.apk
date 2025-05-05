package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdx;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public abstract class zzed<MessageType extends zzed<MessageType, BuilderType>, BuilderType extends zzdx<MessageType, BuilderType>> extends zzck<MessageType, BuilderType> {
    private static final Map zza = new ConcurrentHashMap();
    protected zzgz zzc = zzgz.zzc();
    private int zzd = -1;

    public static zzeb zzH(zzfo zzfo, Object obj, zzfo zzfo2, zzeg zzeg, int i, zzho zzho, Class cls) {
        return new zzeb(zzfo, obj, zzfo2, new zzea((zzeg) null, i, zzho, false, false), cls);
    }

    static zzed zzI(Class cls) {
        Map map = zza;
        zzed zzed = (zzed) map.get(cls);
        if (zzed == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzed = (zzed) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzed == null) {
            zzed = (zzed) ((zzed) zzhi.zze(cls)).zzg(6, (Object) null, (Object) null);
            if (zzed != null) {
                map.put(cls, zzed);
            } else {
                throw new IllegalStateException();
            }
        }
        return zzed;
    }

    protected static zzed zzK(zzed zzed, byte[] bArr, zzdo zzdo) throws zzeo {
        zzed zze = zze(zzed, bArr, 0, bArr.length, zzdo);
        if (zze == null || zze.zzac()) {
            return zze;
        }
        zzeo zza2 = new zzgx(zze).zza();
        zza2.zzf(zze);
        throw zza2;
    }

    protected static zzei zzL() {
        return zzdv.zze();
    }

    protected static zzei zzM(zzei zzei) {
        int size = zzei.size();
        return zzei.zzf(size == 0 ? 10 : size + size);
    }

    protected static zzej zzN() {
        return zzee.zzf();
    }

    protected static zzel zzO() {
        return zzfy.zze();
    }

    protected static zzel zzP(zzel zzel) {
        int size = zzel.size();
        return zzel.zzd(size == 0 ? 10 : size + size);
    }

    static Object zzQ(Method method, Object obj, Object... objArr) {
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

    protected static Object zzR(zzfo zzfo, String str, Object[] objArr) {
        return new zzfz(zzfo, str, objArr);
    }

    protected static void zzU(Class cls, zzed zzed) {
        zzed.zzT();
        zza.put(cls, zzed);
    }

    protected static final boolean zzW(zzed zzed, boolean z) {
        byte byteValue = ((Byte) zzed.zzg(1, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzk = zzfx.zza().zzb(zzed.getClass()).zzk(zzed);
        if (z) {
            zzed.zzg(2, true != zzk ? null : zzed, (Object) null);
        }
        return zzk;
    }

    private final int zzc(zzgh zzgh) {
        if (zzgh != null) {
            return zzgh.zza(this);
        }
        return zzfx.zza().zzb(getClass()).zza(this);
    }

    private static zzed zze(zzed zzed, byte[] bArr, int i, int i2, zzdo zzdo) throws zzeo {
        zzed zzJ = zzed.zzJ();
        try {
            zzgh zzb = zzfx.zza().zzb(zzJ.getClass());
            zzb.zzh(zzJ, bArr, 0, i2, new zzco(zzdo));
            zzb.zzf(zzJ);
            return zzJ;
        } catch (zzeo e) {
            e.zzf(zzJ);
            throw e;
        } catch (zzgx e2) {
            zzeo zza2 = e2.zza();
            zza2.zzf(zzJ);
            throw zza2;
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzeo) {
                throw ((zzeo) e3.getCause());
            }
            zzeo zzeo = new zzeo(e3);
            zzeo.zzf(zzJ);
            throw zzeo;
        } catch (IndexOutOfBoundsException unused) {
            zzeo zzg = zzeo.zzg();
            zzg.zzf(zzJ);
            throw zzg;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return zzfx.zza().zzb(getClass()).zzj(this, (zzed) obj);
    }

    public final int hashCode() {
        if (zzX()) {
            return zzD();
        }
        int i = this.zzb;
        if (i != 0) {
            return i;
        }
        int zzD = zzD();
        this.zzb = zzD;
        return zzD;
    }

    public final String toString() {
        return zzfq.zza(this, super.toString());
    }

    /* access modifiers changed from: package-private */
    public final int zzD() {
        return zzfx.zza().zzb(getClass()).zzb(this);
    }

    /* access modifiers changed from: protected */
    public final zzdx zzF() {
        return (zzdx) zzg(5, (Object) null, (Object) null);
    }

    public final zzdx zzG() {
        zzdx zzdx = (zzdx) zzg(5, (Object) null, (Object) null);
        zzdx.zzg(this);
        return zzdx;
    }

    /* access modifiers changed from: package-private */
    public final zzed zzJ() {
        return (zzed) zzg(4, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final void zzS() {
        zzfx.zza().zzb(getClass()).zzf(this);
        zzT();
    }

    /* access modifiers changed from: package-private */
    public final void zzT() {
        this.zzd &= Integer.MAX_VALUE;
    }

    /* access modifiers changed from: package-private */
    public final void zzV(int i) {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzX() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    public final /* synthetic */ zzfn zzY() {
        return (zzdx) zzg(5, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzfn zzZ() {
        zzdx zzdx = (zzdx) zzg(5, (Object) null, (Object) null);
        zzdx.zzg(this);
        return zzdx;
    }

    public final void zzaa(zzdj zzdj) throws IOException {
        zzfx.zza().zzb(getClass()).zzi(this, zzdk.zza(zzdj));
    }

    public final /* synthetic */ zzfo zzab() {
        return (zzed) zzg(6, (Object) null, (Object) null);
    }

    public final boolean zzac() {
        return zzW(this, Boolean.TRUE.booleanValue());
    }

    /* access modifiers changed from: protected */
    public abstract Object zzg(int i, Object obj, Object obj2);

    /* access modifiers changed from: package-private */
    public final int zzB(zzgh zzgh) {
        if (zzX()) {
            int zzc2 = zzc(zzgh);
            if (zzc2 >= 0) {
                return zzc2;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + zzc2);
        }
        int i = this.zzd & Integer.MAX_VALUE;
        if (i != Integer.MAX_VALUE) {
            return i;
        }
        int zzc3 = zzc(zzgh);
        if (zzc3 >= 0) {
            this.zzd = (this.zzd & Integer.MIN_VALUE) | zzc3;
            return zzc3;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + zzc3);
    }

    public final int zzE() {
        int i;
        if (zzX()) {
            i = zzc((zzgh) null);
            if (i < 0) {
                throw new IllegalStateException("serialized size must be non-negative, was " + i);
            }
        } else {
            i = this.zzd & Integer.MAX_VALUE;
            if (i == Integer.MAX_VALUE) {
                i = zzc((zzgh) null);
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
