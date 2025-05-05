package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.RecaptchaErrorCode;
import com.google.android.recaptcha.RecaptchaException;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzbf extends Exception {
    public static final /* synthetic */ int zza = 0;
    /* access modifiers changed from: private */
    public static final Map zzb = MapsKt.mapOf(TuplesKt.to(zztx.JS_NETWORK_ERROR, new zzbf(zzbd.zzc, zzbc.zzd, (String) null)), TuplesKt.to(zztx.JS_INTERNAL_ERROR, new zzbf(zzbd.zzb, zzbc.zzc, (String) null)), TuplesKt.to(zztx.JS_INVALID_SITE_KEY, new zzbf(zzbd.zzd, zzbc.zze, (String) null)), TuplesKt.to(zztx.JS_INVALID_SITE_KEY_TYPE, new zzbf(zzbd.zze, zzbc.zzf, (String) null)), TuplesKt.to(zztx.JS_THIRD_PARTY_APP_PACKAGE_NAME_NOT_ALLOWED, new zzbf(zzbd.zzf, zzbc.zzg, (String) null)), TuplesKt.to(zztx.JS_INVALID_ACTION, new zzbf(zzbd.zzg, zzbc.zzh, (String) null)), TuplesKt.to(zztx.JS_PROGRAM_ERROR, new zzbf(zzbd.zzb, zzbc.zzj, (String) null)));
    private final zzbd zzc;
    private final zzbc zzd;
    private final String zze;
    private final Map zzf = MapsKt.mapOf(TuplesKt.to(zzbd.zzc, new RecaptchaException(RecaptchaErrorCode.NETWORK_ERROR, (String) null, 2, (DefaultConstructorMarker) null)), TuplesKt.to(zzbd.zzh, new RecaptchaException(RecaptchaErrorCode.NETWORK_ERROR, (String) null, 2, (DefaultConstructorMarker) null)), TuplesKt.to(zzbd.zzi, new RecaptchaException(RecaptchaErrorCode.NETWORK_ERROR, (String) null, 2, (DefaultConstructorMarker) null)), TuplesKt.to(zzbd.zzd, new RecaptchaException(RecaptchaErrorCode.INVALID_SITEKEY, (String) null, 2, (DefaultConstructorMarker) null)), TuplesKt.to(zzbd.zze, new RecaptchaException(RecaptchaErrorCode.INVALID_KEYTYPE, (String) null, 2, (DefaultConstructorMarker) null)), TuplesKt.to(zzbd.zzf, new RecaptchaException(RecaptchaErrorCode.INVALID_PACKAGE_NAME, (String) null, 2, (DefaultConstructorMarker) null)), TuplesKt.to(zzbd.zzg, new RecaptchaException(RecaptchaErrorCode.INVALID_ACTION, (String) null, 2, (DefaultConstructorMarker) null)), TuplesKt.to(zzbd.zzb, new RecaptchaException(RecaptchaErrorCode.INTERNAL_ERROR, (String) null, 2, (DefaultConstructorMarker) null)), TuplesKt.to(zzbd.zzj, new RecaptchaException(RecaptchaErrorCode.INVALID_TIMEOUT, (String) null, 2, (DefaultConstructorMarker) null)));

    public zzbf(zzbd zzbd, zzbc zzbc, String str) {
        this.zzc = zzbd;
        this.zzd = zzbc;
        this.zze = str;
    }

    public final zzbc zza() {
        return this.zzd;
    }

    public final zzbd zzb() {
        return this.zzc;
    }

    public final RecaptchaException zzc() {
        zzbc zzbc = this.zzd;
        if (Intrinsics.areEqual((Object) zzbc, (Object) zzbc.zzI)) {
            return new RecaptchaException(RecaptchaErrorCode.INVALID_TIMEOUT, (String) null, 2, (DefaultConstructorMarker) null);
        }
        if (Intrinsics.areEqual((Object) zzbc, (Object) zzbc.zzao)) {
            return new RecaptchaException(RecaptchaErrorCode.NO_NETWORK_FOUND, (String) null, 2, (DefaultConstructorMarker) null);
        }
        RecaptchaException recaptchaException = (RecaptchaException) this.zzf.get(this.zzc);
        return recaptchaException == null ? new RecaptchaException(RecaptchaErrorCode.INTERNAL_ERROR, (String) null, 2, (DefaultConstructorMarker) null) : recaptchaException;
    }

    public final String zzd() {
        return this.zze;
    }
}
