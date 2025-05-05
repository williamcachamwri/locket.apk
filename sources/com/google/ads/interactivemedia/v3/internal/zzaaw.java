package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaaw implements zzwk {
    final /* synthetic */ Class zza;
    final /* synthetic */ Class zzb;
    final /* synthetic */ zzwj zzc;

    zzaaw(Class cls, Class cls2, zzwj zzwj) {
        this.zza = cls;
        this.zzb = cls2;
        this.zzc = zzwj;
    }

    public final String toString() {
        zzwj zzwj = this.zzc;
        Class cls = this.zzb;
        String name = this.zza.getName();
        String name2 = cls.getName();
        String obj = zzwj.toString();
        return "Factory[type=" + name + "+" + name2 + ",adapter=" + obj + "]";
    }

    public final zzwj zza(zzvr zzvr, zzaca zzaca) {
        Class zzc2 = zzaca.zzc();
        if (zzc2 == this.zza || zzc2 == this.zzb) {
            return this.zzc;
        }
        return null;
    }
}
