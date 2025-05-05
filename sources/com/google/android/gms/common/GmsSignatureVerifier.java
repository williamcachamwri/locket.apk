package com.google.android.gms.common;

import com.google.android.gms.internal.common.zzag;
import com.google.errorprone.annotations.RestrictedInheritance;
import java.util.HashMap;

@RestrictedInheritance(allowedOnPath = ".*javatests/com/google/android/gmscore/integ/client/common/robolectric/.*", explanation = "Sub classing of GMS Core's APIs are restricted to testing fakes.", link = "go/gmscore-restrictedinheritance")
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public class GmsSignatureVerifier {
    private static final zzab zza;
    private static final zzab zzb;
    private static final HashMap zzc = new HashMap();

    static {
        zzz zzz = new zzz();
        zzz.zzd("com.google.android.gms");
        zzz.zza(204200000);
        zzz.zzc(zzag.zzn(zzn.zzd.zzf(), zzn.zzb.zzf()));
        zzz.zzb(zzag.zzn(zzn.zzc.zzf(), zzn.zza.zzf()));
        zza = zzz.zze();
        zzz zzz2 = new zzz();
        zzz2.zzd("com.android.vending");
        zzz2.zza(82240000);
        zzz2.zzc(zzag.zzm(zzn.zzd.zzf()));
        zzz2.zzb(zzag.zzm(zzn.zzc.zzf()));
        zzb = zzz2.zze();
    }
}
