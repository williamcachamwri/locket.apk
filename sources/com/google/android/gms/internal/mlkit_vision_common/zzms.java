package com.google.android.gms.internal.mlkit_vision_common;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
public final class zzms {
    private static zzmr zza;

    public static synchronized zzmj zza(zzme zzme) {
        zzmj zzmj;
        synchronized (zzms.class) {
            if (zza == null) {
                zza = new zzmr((zzmq) null);
            }
            zzmj = (zzmj) zza.get(zzme);
        }
        return zzmj;
    }

    public static synchronized zzmj zzb(String str) {
        zzmj zza2;
        synchronized (zzms.class) {
            zza2 = zza(zzme.zzd("vision-common").zzd());
        }
        return zza2;
    }
}
