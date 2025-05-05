package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzpc extends zzka {
    private final zzps zza;

    public zzpc(zzps zzps, @Nullable zzlg zzlg) throws GeneralSecurityException {
        int i = zzpb.zza[zzps.zza().ordinal()];
        this.zza = zzps;
    }

    public final zzks zza() {
        throw new UnsupportedOperationException("Cannot get parameters on LegacyProtoKey");
    }
}
