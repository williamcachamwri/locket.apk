package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzzk extends zzzm {
    final /* synthetic */ boolean zza;
    final /* synthetic */ Method zzb;
    final /* synthetic */ zzwj zzc;
    final /* synthetic */ zzwj zzd;
    final /* synthetic */ boolean zze;
    final /* synthetic */ boolean zzf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzzk(zzzq zzzq, String str, Field field, boolean z, Method method, zzwj zzwj, zzwj zzwj2, boolean z2, boolean z3) {
        super(str, field);
        this.zza = z;
        this.zzb = method;
        this.zzc = zzwj;
        this.zzd = zzwj2;
        this.zze = z2;
        this.zzf = z3;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzacc zzacc, int i, Object[] objArr) throws IOException, zzwa {
        Object read = this.zzd.read(zzacc);
        if (read != null || !this.zze) {
            objArr[i] = read;
            return;
        }
        String str = this.zzi;
        String zze2 = zzacc.zze();
        throw new zzwa("null is not allowed as value for record component '" + str + "' of primitive type; at path " + zze2);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzacc zzacc, Object obj) throws IOException, IllegalAccessException {
        Object read = this.zzd.read(zzacc);
        if (read != null || !this.zze) {
            if (this.zza) {
                zzzq.zzb(obj, this.zzh);
            } else if (this.zzf) {
                throw new zzvx("Cannot set value of 'static final' ".concat(zzabp.zze(this.zzh, false)));
            }
            this.zzh.set(obj, read);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzc(zzace zzace, Object obj) throws IOException, IllegalAccessException {
        Object obj2;
        if (this.zza) {
            Method method = this.zzb;
            if (method == null) {
                zzzq.zzb(obj, this.zzh);
            } else {
                zzzq.zzb(obj, method);
            }
        }
        Method method2 = this.zzb;
        if (method2 != null) {
            try {
                obj2 = method2.invoke(obj, new Object[0]);
            } catch (InvocationTargetException e) {
                String zze2 = zzabp.zze(this.zzb, false);
                throw new zzvx("Accessor " + zze2 + " threw exception", e.getCause());
            }
        } else {
            obj2 = this.zzh.get(obj);
        }
        if (obj2 != obj) {
            zzace.zzf(this.zzg);
            this.zzc.write(zzace, obj2);
        }
    }
}
