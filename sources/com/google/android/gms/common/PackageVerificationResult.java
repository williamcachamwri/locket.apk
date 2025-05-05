package com.google.android.gms.common;

import com.google.errorprone.annotations.CheckReturnValue;
import javax.annotation.Nullable;

@CheckReturnValue
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public class PackageVerificationResult {
    private final String zza;
    private final boolean zzb;
    @Nullable
    private final String zzc;
    @Nullable
    private final Throwable zzd;

    private PackageVerificationResult(String str, int i, boolean z, @Nullable String str2, @Nullable Throwable th) {
        this.zza = str;
        this.zzb = z;
        this.zzc = str2;
        this.zzd = th;
    }

    public static PackageVerificationResult zza(String str, String str2, @Nullable Throwable th) {
        return new PackageVerificationResult(str, 1, false, str2, th);
    }

    public static PackageVerificationResult zzd(String str, int i) {
        return new PackageVerificationResult(str, i, true, (String) null, (Throwable) null);
    }

    public final void zzb() {
        if (!this.zzb) {
            String str = this.zzc;
            Throwable th = this.zzd;
            String concat = "PackageVerificationRslt: ".concat(String.valueOf(str));
            if (th != null) {
                throw new SecurityException(concat, th);
            }
            throw new SecurityException(concat);
        }
    }

    public final boolean zzc() {
        return this.zzb;
    }
}
