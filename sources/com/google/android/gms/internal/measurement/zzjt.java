package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzjt;
import com.google.android.gms.internal.measurement.zzjt.zzb;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
public abstract class zzjt<MessageType extends zzjt<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzib<MessageType, BuilderType> {
    private static Map<Class<?>, zzjt<?, ?>> zzc = new ConcurrentHashMap();
    protected zzmj zzb = zzmj.zzc();
    private int zzd = -1;

    /* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
    protected static class zza<T extends zzjt<T, ?>> extends zzif<T> {
        public zza(T t) {
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
    public static abstract class zzb<MessageType extends zzjt<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzid<MessageType, BuilderType> {
        protected MessageType zza;
        private final MessageType zzb;

        public final /* synthetic */ zzid zzaf() {
            return (zzb) clone();
        }

        public final /* synthetic */ zzid zza(zziw zziw, zzjg zzjg) throws IOException {
            return (zzb) zzb(zziw, zzjg);
        }

        public final /* synthetic */ zzid zza(byte[] bArr, int i, int i2) throws zzkb {
            return zzb(bArr, 0, i2, zzjg.zza);
        }

        public final /* synthetic */ zzid zza(byte[] bArr, int i, int i2, zzjg zzjg) throws zzkb {
            return zzb(bArr, 0, i2, zzjg);
        }

        public final BuilderType zza(MessageType messagetype) {
            MessageType messagetype2 = this.zzb;
            zzjt zzjt = (zzjt) messagetype2;
            if (messagetype2.equals(messagetype)) {
                return this;
            }
            if (!this.zza.zzco()) {
                zzal();
            }
            zza(this.zza, messagetype);
            return this;
        }

        /* access modifiers changed from: private */
        /* renamed from: zzc */
        public final BuilderType zzb(zziw zziw, zzjg zzjg) throws IOException {
            if (!this.zza.zzco()) {
                zzal();
            }
            try {
                zzlq.zza().zza(this.zza).zza(this.zza, zzjb.zza(zziw), zzjg);
                return this;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) {
                    throw ((IOException) e.getCause());
                }
                throw e;
            }
        }

        private final BuilderType zzb(byte[] bArr, int i, int i2, zzjg zzjg) throws zzkb {
            if (!this.zza.zzco()) {
                zzal();
            }
            try {
                zzlq.zza().zza(this.zza).zza(this.zza, bArr, 0, i2, new zzij(zzjg));
                return this;
            } catch (zzkb e) {
                throw e;
            } catch (IndexOutOfBoundsException unused) {
                throw zzkb.zzi();
            } catch (IOException e2) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
            }
        }

        /* renamed from: zzag */
        public final MessageType zzai() {
            MessageType messagetype = (zzjt) zzaj();
            if (zzjt.zza(messagetype, true)) {
                return messagetype;
            }
            throw new zzmh(messagetype);
        }

        /* renamed from: zzah */
        public MessageType zzaj() {
            if (!this.zza.zzco()) {
                return this.zza;
            }
            this.zza.zzcl();
            return this.zza;
        }

        public final /* synthetic */ zzlc zzck() {
            return this.zzb;
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            MessageType messagetype = this.zzb;
            zzjt zzjt = (zzjt) messagetype;
            zzb zzb2 = (zzb) messagetype.zza(zze.zze, (Object) null, (Object) null);
            zzb2.zza = (zzjt) zzaj();
            return zzb2;
        }

        protected zzb(MessageType messagetype) {
            this.zzb = messagetype;
            if (!messagetype.zzco()) {
                this.zza = messagetype.zzce();
                return;
            }
            throw new IllegalArgumentException("Default instance must be immutable.");
        }

        /* access modifiers changed from: protected */
        public final void zzak() {
            if (!this.zza.zzco()) {
                zzal();
            }
        }

        /* access modifiers changed from: protected */
        public void zzal() {
            MessageType zzce = this.zzb.zzce();
            zza(zzce, this.zza);
            this.zza = zzce;
        }

        private static <MessageType> void zza(MessageType messagetype, MessageType messagetype2) {
            zzlq.zza().zza(messagetype).zza(messagetype, messagetype2);
        }

        public final boolean zzcn() {
            return zzjt.zza(this.zza, false);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
    static final class zzc implements zzjo<zzc> {
        public final /* synthetic */ int compareTo(Object obj) {
            throw new NoSuchMethodError();
        }

        public final int zza() {
            throw new NoSuchMethodError();
        }

        public final zzlb zza(zzlb zzlb, zzlc zzlc) {
            throw new NoSuchMethodError();
        }

        public final zzlh zza(zzlh zzlh, zzlh zzlh2) {
            throw new NoSuchMethodError();
        }

        public final zzms zzb() {
            throw new NoSuchMethodError();
        }

        public final zzmz zzc() {
            throw new NoSuchMethodError();
        }

        public final boolean zzd() {
            throw new NoSuchMethodError();
        }

        public final boolean zze() {
            throw new NoSuchMethodError();
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
    public static abstract class zzd<MessageType extends zzd<MessageType, BuilderType>, BuilderType> extends zzjt<MessageType, BuilderType> implements zzle {
        protected zzjm<zzc> zzc = zzjm.zzb();

        /* access modifiers changed from: package-private */
        public final zzjm<zzc> zza() {
            if (this.zzc.zzf()) {
                this.zzc = (zzjm) this.zzc.clone();
            }
            return this.zzc;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
    public static class zzf<ContainingType extends zzlc, Type> extends zzjh<ContainingType, Type> {
    }

    private final int zza() {
        return zzlq.zza().zza(this).zzb(this);
    }

    /* access modifiers changed from: protected */
    public abstract Object zza(int i, Object obj, Object obj2);

    private final int zzb(zzlu<?> zzlu) {
        if (zzlu == null) {
            return zzlq.zza().zza(this).zza(this);
        }
        return zzlu.zza(this);
    }

    /* access modifiers changed from: package-private */
    public final int zzby() {
        return this.zzd & Integer.MAX_VALUE;
    }

    public final int zzcb() {
        return zza((zzlu) null);
    }

    /* 'enum' modifier removed */
    /* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
    public static final class zze {
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
    public final int zza(zzlu zzlu) {
        if (zzco()) {
            int zzb2 = zzb(zzlu);
            if (zzb2 >= 0) {
                return zzb2;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + zzb2);
        } else if (zzby() != Integer.MAX_VALUE) {
            return zzby();
        } else {
            int zzb3 = zzb(zzlu);
            zzc(zzb3);
            return zzb3;
        }
    }

    public int hashCode() {
        if (zzco()) {
            return zza();
        }
        if (this.zza == 0) {
            this.zza = zza();
        }
        return this.zza;
    }

    /* access modifiers changed from: protected */
    public final <MessageType extends zzjt<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> BuilderType zzcc() {
        return (zzb) zza(zze.zze, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final <MessageType extends zzjt<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> BuilderType zza(MessageType messagetype) {
        return zzcc().zza(messagetype);
    }

    public final BuilderType zzcd() {
        return ((zzb) zza(zze.zze, (Object) null, (Object) null)).zza(this);
    }

    static <T extends zzjt<?, ?>> T zza(Class<T> cls) {
        T t = (zzjt) zzc.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzjt) zzc.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = (zzjt) ((zzjt) zzml.zza(cls)).zza(zze.zzf, (Object) null, (Object) null);
            if (t != null) {
                zzc.put(cls, t);
            } else {
                throw new IllegalStateException();
            }
        }
        return t;
    }

    /* access modifiers changed from: package-private */
    public final MessageType zzce() {
        return (zzjt) zza(zze.zzd, (Object) null, (Object) null);
    }

    protected static zzka zzcf() {
        return zzjw.zzd();
    }

    protected static zzjz zzcg() {
        return zzkn.zzd();
    }

    protected static zzjz zza(zzjz zzjz) {
        return zzjz.zzc(zzjz.size() << 1);
    }

    protected static <E> zzkc<E> zzch() {
        return zzlp.zzd();
    }

    protected static <E> zzkc<E> zza(zzkc<E> zzkc) {
        return zzkc.zza(zzkc.size() << 1);
    }

    public final /* synthetic */ zzlb zzci() {
        return (zzb) zza(zze.zze, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzlb zzcj() {
        return ((zzb) zza(zze.zze, (Object) null, (Object) null)).zza(this);
    }

    public final /* synthetic */ zzlc zzck() {
        return (zzjt) zza(zze.zzf, (Object) null, (Object) null);
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

    protected static Object zza(zzlc zzlc, String str, Object[] objArr) {
        return new zzls(zzlc, str, objArr);
    }

    public String toString() {
        return zzld.zza((zzlc) this, super.toString());
    }

    /* access modifiers changed from: protected */
    public final void zzcl() {
        zzlq.zza().zza(this).zzd(this);
        zzcm();
    }

    /* access modifiers changed from: package-private */
    public final void zzcm() {
        this.zzd &= Integer.MAX_VALUE;
    }

    protected static <T extends zzjt<?, ?>> void zza(Class<T> cls, T t) {
        t.zzcm();
        zzc.put(cls, t);
    }

    /* access modifiers changed from: package-private */
    public final void zzc(int i) {
        if (i >= 0) {
            this.zzd = (i & Integer.MAX_VALUE) | (this.zzd & Integer.MIN_VALUE);
            return;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + i);
    }

    public final void zza(zzjc zzjc) throws IOException {
        zzlq.zza().zza(this).zza(this, (zznb) zzjf.zza(zzjc));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzlq.zza().zza(this).zzb(this, (zzjt) obj);
        }
        return false;
    }

    public final boolean zzcn() {
        return zza(this, true);
    }

    protected static final <T extends zzjt<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zze.zza, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zze2 = zzlq.zza().zza(t).zze(t);
        if (z) {
            t.zza(zze.zzb, (Object) zze2 ? t : null, (Object) null);
        }
        return zze2;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzco() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }
}
