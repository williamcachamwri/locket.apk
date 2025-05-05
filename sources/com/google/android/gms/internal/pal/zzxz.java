package com.google.android.gms.internal.pal;

import com.google.android.gms.security.ProviderInstaller;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzxz {
    public static final zzxz zza = new zzxz(new zzya());
    public static final zzxz zzb = new zzxz(new zzye());
    public static final zzxz zzc = new zzxz(new zzyg());
    public static final zzxz zzd = new zzxz(new zzyf());
    public static final zzxz zze = new zzxz(new zzyb());
    public static final zzxz zzf = new zzxz(new zzyd());
    public static final zzxz zzg = new zzxz(new zzyc());
    private static final Logger zzh = Logger.getLogger(zzxz.class.getName());
    private static final List zzi;
    private static final boolean zzj;
    private final zzyh zzk;

    static {
        if (zznb.zzb()) {
            zzi = zzb(ProviderInstaller.PROVIDER_NAME, "AndroidOpenSSL", "Conscrypt");
            zzj = false;
        } else if (zzyr.zza()) {
            zzi = zzb(ProviderInstaller.PROVIDER_NAME, "AndroidOpenSSL");
            zzj = true;
        } else {
            zzi = new ArrayList();
            zzj = true;
        }
    }

    public zzxz(zzyh zzyh) {
        this.zzk = zzyh;
    }

    public static List zzb(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            Provider provider = Security.getProvider(str);
            if (provider != null) {
                arrayList.add(provider);
            } else {
                zzh.logp(Level.INFO, "com.google.crypto.tink.subtle.EngineFactory", "toProviderList", String.format("Provider %s not available", new Object[]{str}));
            }
        }
        return arrayList;
    }

    public final Object zza(String str) throws GeneralSecurityException {
        Exception exc = null;
        for (Provider zza2 : zzi) {
            try {
                return this.zzk.zza(str, zza2);
            } catch (Exception e) {
                if (exc == null) {
                    exc = e;
                }
            }
        }
        if (zzj) {
            return this.zzk.zza(str, (Provider) null);
        }
        throw new GeneralSecurityException("No good Provider found.", exc);
    }
}
