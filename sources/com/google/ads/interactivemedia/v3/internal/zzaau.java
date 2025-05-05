package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaau implements zzwk {
    final /* synthetic */ Class zza;
    final /* synthetic */ zzwj zzb;

    zzaau(Class cls, zzwj zzwj) {
        this.zza = cls;
        this.zzb = zzwj;
    }

    public final String toString() {
        zzwj zzwj = this.zzb;
        String name = this.zza.getName();
        String obj = zzwj.toString();
        return "Factory[type=" + name + ",adapter=" + obj + "]";
    }

    public final zzwj zza(zzvr zzvr, zzaca zzaca) {
        if (zzaca.zzc() == this.zza) {
            return this.zzb;
        }
        return null;
    }
}
