package com.google.firebase.auth.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p002firebaseauthapi.zzags;
import com.google.android.gms.internal.p002firebaseauthapi.zzahp;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.TotpMultiFactorInfo;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzbi {
    public static MultiFactorInfo zza(zzags zzags) {
        if (zzags == null) {
            return null;
        }
        if (!TextUtils.isEmpty(zzags.zze())) {
            return new PhoneMultiFactorInfo(zzags.zzd(), zzags.zzc(), zzags.zza(), Preconditions.checkNotEmpty(zzags.zze()));
        }
        if (zzags.zzb() != null) {
            return new TotpMultiFactorInfo(zzags.zzd(), zzags.zzc(), zzags.zza(), (zzahp) Preconditions.checkNotNull(zzags.zzb(), "totpInfo cannot be null."));
        }
        return null;
    }

    public static List<MultiFactorInfo> zza(List<zzags> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (zzags zza : list) {
            MultiFactorInfo zza2 = zza(zza);
            if (zza2 != null) {
                arrayList.add(zza2);
            }
        }
        return arrayList;
    }
}
