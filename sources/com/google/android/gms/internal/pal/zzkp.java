package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzkp {
    private static final CopyOnWriteArrayList zza = new CopyOnWriteArrayList();

    public static zzko zza(String str) throws GeneralSecurityException {
        Iterator it = zza.iterator();
        while (it.hasNext()) {
            zzko zzko = (zzko) it.next();
            if (zzko.zza()) {
                return zzko;
            }
        }
        throw new GeneralSecurityException("No KMS client does support: ".concat(String.valueOf(str)));
    }
}
