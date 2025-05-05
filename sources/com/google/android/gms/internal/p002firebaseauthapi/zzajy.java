package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;
import com.google.android.gms.internal.p002firebaseauthapi.zzajy.zza;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajy  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public abstract class zzajy<MessageType extends zzajy<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzaif<MessageType, BuilderType> {
    private static Map<Class<?>, zzajy<?, ?>> zzc = new ConcurrentHashMap();
    protected zzamn zzb = zzamn.zzc();
    private int zzd = -1;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajy$zzb */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static abstract class zzb<MessageType extends zzb<MessageType, BuilderType>, BuilderType> extends zzajy<MessageType, BuilderType> implements zzale {
        protected zzajr<zze> zzc = zzajr.zzb();

        /* access modifiers changed from: package-private */
        public final zzajr<zze> zza() {
            if (this.zzc.zzf()) {
                this.zzc = (zzajr) this.zzc.clone();
            }
            return this.zzc;
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajy$zzc */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    protected static class zzc<T extends zzajy<T, ?>> extends zzaig<T> {
        public zzc(T t) {
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajy$zzd */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static class zzd<ContainingType extends zzalc, Type> extends zzajl<ContainingType, Type> {
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajy$zze */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    static final class zze implements zzajt<zze> {
        public final /* synthetic */ int compareTo(Object obj) {
            throw new NoSuchMethodError();
        }

        public final int zza() {
            throw new NoSuchMethodError();
        }

        public final zzalf zza(zzalf zzalf, zzalc zzalc) {
            throw new NoSuchMethodError();
        }

        public final zzall zza(zzall zzall, zzall zzall2) {
            throw new NoSuchMethodError();
        }

        public final zzamw zzb() {
            throw new NoSuchMethodError();
        }

        public final zzand zzc() {
            throw new NoSuchMethodError();
        }

        public final boolean zzd() {
            throw new NoSuchMethodError();
        }

        public final boolean zze() {
            throw new NoSuchMethodError();
        }
    }

    private final int zza() {
        return zzalr.zza().zza(this).zzb(this);
    }

    /* access modifiers changed from: protected */
    public abstract Object zza(int i, Object obj, Object obj2);

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajy$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static abstract class zza<MessageType extends zzajy<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzaie<MessageType, BuilderType> {
        protected MessageType zza;
        private final MessageType zzb;

        public final /* synthetic */ zzaie zzb() {
            return (zza) clone();
        }

        public final BuilderType zza(MessageType messagetype) {
            MessageType messagetype2 = this.zzb;
            zzajy zzajy = (zzajy) messagetype2;
            if (messagetype2.equals(messagetype)) {
                return this;
            }
            if (!this.zza.zzu()) {
                zzi();
            }
            zza(this.zza, messagetype);
            return this;
        }

        /* renamed from: zzc */
        public final MessageType zze() {
            MessageType messagetype = (zzajy) zzf();
            if (zzajy.zza(messagetype, true)) {
                return messagetype;
            }
            throw new zzaml(messagetype);
        }

        /* renamed from: zzd */
        public MessageType zzf() {
            if (!this.zza.zzu()) {
                return this.zza;
            }
            this.zza.zzs();
            return this.zza;
        }

        public final /* synthetic */ zzalc zzg() {
            return this.zzb;
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            MessageType messagetype = this.zzb;
            zzajy zzajy = (zzajy) messagetype;
            zza zza2 = (zza) messagetype.zza(zzf.zze, (Object) null, (Object) null);
            zza2.zza = (zzajy) zzf();
            return zza2;
        }

        protected zza(MessageType messagetype) {
            this.zzb = messagetype;
            if (!messagetype.zzu()) {
                this.zza = messagetype.zzo();
                return;
            }
            throw new IllegalArgumentException("Default instance must be immutable.");
        }

        /* access modifiers changed from: protected */
        public final void zzh() {
            if (!this.zza.zzu()) {
                zzi();
            }
        }

        /* access modifiers changed from: protected */
        public void zzi() {
            MessageType zzo = this.zzb.zzo();
            zza(zzo, this.zza);
            this.zza = zzo;
        }

        private static <MessageType> void zza(MessageType messagetype, MessageType messagetype2) {
            zzalr.zza().zza(messagetype).zza(messagetype, messagetype2);
        }

        public final boolean zzj() {
            return zzajy.zza(this.zza, false);
        }
    }

    private final int zzb(zzalv<?> zzalv) {
        if (zzalv == null) {
            return zzalr.zza().zza(this).zza(this);
        }
        return zzalv.zza(this);
    }

    /* access modifiers changed from: package-private */
    public final int zzi() {
        return this.zzd & Integer.MAX_VALUE;
    }

    public final int zzl() {
        return zza((zzalv) null);
    }

    /* 'enum' modifier removed */
    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajy$zzf */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zzf {
        public static final int zza = 1;
        public static final int zzb = 2;
        public static final int zzc = 3;
        public static final int zzd = 4;
        public static final int zze = 5;
        public static final int zzf = 6;
        public static final int zzg = 7;
        private static final /* synthetic */ int[] zzh = {1, 2, 3, 4, 5, 6, 7};

        public static int[] zza() {
            return (int[]) zzh.clone();
        }
    }

    /* access modifiers changed from: package-private */
    public final int zza(zzalv zzalv) {
        if (zzu()) {
            int zzb2 = zzb((zzalv<?>) zzalv);
            if (zzb2 >= 0) {
                return zzb2;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + zzb2);
        } else if (zzi() != Integer.MAX_VALUE) {
            return zzi();
        } else {
            int zzb3 = zzb((zzalv<?>) zzalv);
            zzb(zzb3);
            return zzb3;
        }
    }

    public int hashCode() {
        if (zzu()) {
            return zza();
        }
        if (this.zza == 0) {
            this.zza = zza();
        }
        return this.zza;
    }

    /* access modifiers changed from: protected */
    public final <MessageType extends zzajy<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> BuilderType zzm() {
        return (zza) zza(zzf.zze, (Object) null, (Object) null);
    }

    public final BuilderType zzn() {
        return ((zza) zza(zzf.zze, (Object) null, (Object) null)).zza(this);
    }

    private static <T extends zzajy<T, ?>> T zza(T t) throws zzakf {
        if (t == null || zza(t, true)) {
            return t;
        }
        zzakf zza2 = new zzaml(t).zza();
        zza2.getClass();
        throw zza2;
    }

    static <T extends zzajy<?, ?>> T zza(Class<T> cls) {
        T t = (zzajy) zzc.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzajy) zzc.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = (zzajy) ((zzajy) zzamp.zza(cls)).zza(zzf.zzf, (Object) null, (Object) null);
            if (t != null) {
                zzc.put(cls, t);
            } else {
                throw new IllegalStateException();
            }
        }
        return t;
    }

    /* access modifiers changed from: package-private */
    public final MessageType zzo() {
        return (zzajy) zza(zzf.zzd, (Object) null, (Object) null);
    }

    protected static <T extends zzajy<T, ?>> T zza(T t, zzaip zzaip, zzajk zzajk) throws zzakf {
        return zza(zzb(t, zzaip, zzajk));
    }

    protected static <T extends zzajy<T, ?>> T zza(T t, InputStream inputStream, zzajk zzajk) throws zzakf {
        zzajb zzajb;
        if (inputStream == null) {
            byte[] bArr = zzakb.zzb;
            zzajb = zzajb.zza(bArr, 0, bArr.length, false);
        } else {
            zzajb = new zzajd(inputStream);
        }
        return zza(zza(t, zzajb, zzajk));
    }

    protected static <T extends zzajy<T, ?>> T zza(T t, byte[] bArr, zzajk zzajk) throws zzakf {
        return zza(zza(t, bArr, 0, bArr.length, zzajk));
    }

    private static <T extends zzajy<T, ?>> T zzb(T t, zzaip zzaip, zzajk zzajk) throws zzakf {
        zzajb zzc2 = zzaip.zzc();
        T zza2 = zza(t, zzc2, zzajk);
        zzc2.zzb(0);
        return zza2;
    }

    private static <T extends zzajy<T, ?>> T zza(T t, zzajb zzajb, zzajk zzajk) throws zzakf {
        T zzo = t.zzo();
        try {
            zzalv zza2 = zzalr.zza().zza(zzo);
            zza2.zza(zzo, zzajf.zza(zzajb), zzajk);
            zza2.zzd(zzo);
            return zzo;
        } catch (zzakf e) {
            e = e;
            if (e.zzl()) {
                e = new zzakf((IOException) e);
            }
            throw e;
        } catch (zzaml e2) {
            zzakf zza3 = e2.zza();
            zza3.getClass();
            throw zza3;
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzakf) {
                throw ((zzakf) e3.getCause());
            }
            throw new zzakf(e3);
        } catch (RuntimeException e4) {
            if (e4.getCause() instanceof zzakf) {
                throw ((zzakf) e4.getCause());
            }
            throw e4;
        }
    }

    private static <T extends zzajy<T, ?>> T zza(T t, byte[] bArr, int i, int i2, zzajk zzajk) throws zzakf {
        if (i2 == 0) {
            return t;
        }
        T zzo = t.zzo();
        try {
            zzalv zza2 = zzalr.zza().zza(zzo);
            zza2.zza(zzo, bArr, 0, i2, new zzaik(zzajk));
            zza2.zzd(zzo);
            return zzo;
        } catch (zzakf e) {
            e = e;
            if (e.zzl()) {
                e = new zzakf((IOException) e);
            }
            throw e;
        } catch (zzaml e2) {
            zzakf zza3 = e2.zza();
            zza3.getClass();
            throw zza3;
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzakf) {
                throw ((zzakf) e3.getCause());
            }
            throw new zzakf(e3);
        } catch (IndexOutOfBoundsException unused) {
            zzakf zzj = zzakf.zzj();
            zzj.getClass();
            throw zzj;
        }
    }

    protected static <E> zzakc<E> zzp() {
        return zzalq.zzd();
    }

    protected static <E> zzakc<E> zza(zzakc<E> zzakc) {
        return zzakc.zza(zzakc.size() << 1);
    }

    public final /* synthetic */ zzalf zzq() {
        return (zza) zza(zzf.zze, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzalf zzr() {
        return ((zza) zza(zzf.zze, (Object) null, (Object) null)).zza(this);
    }

    public final /* synthetic */ zzalc zzg() {
        return (zzajy) zza(zzf.zzf, (Object) null, (Object) null);
    }

    static Object zza(Method method, Object obj, Object... objArr) {
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

    protected static Object zza(zzalc zzalc, String str, Object[] objArr) {
        return new zzalt(zzalc, str, objArr);
    }

    public String toString() {
        return zzalh.zza((zzalc) this, super.toString());
    }

    /* access modifiers changed from: protected */
    public final void zzs() {
        zzalr.zza().zza(this).zzd(this);
        zzt();
    }

    /* access modifiers changed from: package-private */
    public final void zzt() {
        this.zzd &= Integer.MAX_VALUE;
    }

    protected static <T extends zzajy<?, ?>> void zza(Class<T> cls, T t) {
        t.zzt();
        zzc.put(cls, t);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(int i) {
        if (i >= 0) {
            this.zzd = (i & Integer.MAX_VALUE) | (this.zzd & Integer.MIN_VALUE);
            return;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + i);
    }

    public final void zza(zzajg zzajg) throws IOException {
        zzalr.zza().zza(this).zza(this, (zzanf) zzajj.zza(zzajg));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzalr.zza().zza(this).zzb(this, (zzajy) obj);
        }
        return false;
    }

    public final boolean zzj() {
        return zza(this, true);
    }

    protected static final <T extends zzajy<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zzf.zza, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zze2 = zzalr.zza().zza(t).zze(t);
        if (z) {
            t.zza(zzf.zzb, (Object) zze2 ? t : null, (Object) null);
        }
        return zze2;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzu() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }
}
