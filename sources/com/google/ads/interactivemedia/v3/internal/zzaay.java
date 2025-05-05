package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaay implements zzwk {
    final /* synthetic */ Class zza;
    final /* synthetic */ zzwj zzb;

    zzaay(Class cls, zzwj zzwj) {
        this.zza = cls;
        this.zzb = zzwj;
    }

    public final String toString() {
        zzwj zzwj = this.zzb;
        String name = this.zza.getName();
        String obj = zzwj.toString();
        return "Factory[typeHierarchy=" + name + ",adapter=" + obj + "]";
    }

    public final zzwj zza(zzvr zzvr, zzaca zzaca) {
        Class cls = this.zza;
        Class zzc = zzaca.zzc();
        if (!cls.isAssignableFrom(zzc)) {
            return null;
        }
        return new zzaax(this, zzc);
    }
}
