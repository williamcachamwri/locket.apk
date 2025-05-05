package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseApp;
import io.sentry.android.core.SentryLogcatAdapter;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadv  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzadv extends zzaej implements zzafd {
    private zzadp zza;
    private zzado zzb;
    private zzaes zzc;
    private final zzads zzd;
    private final FirebaseApp zze;
    private String zzf;
    private zzadu zzg;

    private final zzadu zzb() {
        if (this.zzg == null) {
            this.zzg = new zzadu(this.zze, this.zzd.zzb());
        }
        return this.zzg;
    }

    zzadv(FirebaseApp firebaseApp, zzads zzads) {
        this(firebaseApp, zzads, (zzaes) null, (zzadp) null, (zzado) null);
    }

    private zzadv(FirebaseApp firebaseApp, zzads zzads, zzaes zzaes, zzadp zzadp, zzado zzado) {
        this.zze = firebaseApp;
        this.zzf = firebaseApp.getOptions().getApiKey();
        this.zzd = (zzads) Preconditions.checkNotNull(zzads);
        zza((zzaes) null, (zzadp) null, (zzado) null);
        zzafb.zza(this.zzf, this);
    }

    public final void zza(zzafm zzafm, zzael<zzafl> zzael) {
        Preconditions.checkNotNull(zzafm);
        Preconditions.checkNotNull(zzael);
        zzadp zzadp = this.zza;
        zzaeo.zza(zzadp.zza("/createAuthUri", this.zzf), zzafm, zzael, zzafl.class, zzadp.zza);
    }

    public final void zza(zzafo zzafo, zzael<Void> zzael) {
        Preconditions.checkNotNull(zzafo);
        Preconditions.checkNotNull(zzael);
        zzadp zzadp = this.zza;
        zzaeo.zza(zzadp.zza("/deleteAccount", this.zzf), zzafo, zzael, Void.class, zzadp.zza);
    }

    public final void zza(zzafn zzafn, zzael<zzafq> zzael) {
        Preconditions.checkNotNull(zzafn);
        Preconditions.checkNotNull(zzael);
        zzadp zzadp = this.zza;
        zzaeo.zza(zzadp.zza("/emailLinkSignin", this.zzf), zzafn, zzael, zzafq.class, zzadp.zza);
    }

    public final void zza(zzafp zzafp, zzael<zzafs> zzael) {
        Preconditions.checkNotNull(zzafp);
        Preconditions.checkNotNull(zzael);
        zzado zzado = this.zzb;
        zzaeo.zza(zzado.zza("/accounts/mfaEnrollment:finalize", this.zzf), zzafp, zzael, zzafs.class, zzado.zza);
    }

    public final void zza(zzafr zzafr, zzael<zzafu> zzael) {
        Preconditions.checkNotNull(zzafr);
        Preconditions.checkNotNull(zzael);
        zzado zzado = this.zzb;
        zzaeo.zza(zzado.zza("/accounts/mfaSignIn:finalize", this.zzf), zzafr, zzael, zzafu.class, zzado.zza);
    }

    public final void zza(zzafz zzafz, zzael<zzagl> zzael) {
        Preconditions.checkNotNull(zzafz);
        Preconditions.checkNotNull(zzael);
        zzaes zzaes = this.zzc;
        zzaeo.zza(zzaes.zza("/token", this.zzf), zzafz, zzael, zzagl.class, zzaes.zza);
    }

    public final void zza(zzagc zzagc, zzael<zzagb> zzael) {
        Preconditions.checkNotNull(zzagc);
        Preconditions.checkNotNull(zzael);
        zzadp zzadp = this.zza;
        zzaeo.zza(zzadp.zza("/getAccountInfo", this.zzf), zzagc, zzael, zzagb.class, zzadp.zza);
    }

    public final void zza(zzagg zzagg, zzael<zzagf> zzael) {
        Preconditions.checkNotNull(zzagg);
        Preconditions.checkNotNull(zzael);
        if (zzagg.zzb() != null) {
            zzb().zzb(zzagg.zzb().zzf());
        }
        zzadp zzadp = this.zza;
        zzaeo.zza(zzadp.zza("/getOobConfirmationCode", this.zzf), zzagg, zzael, zzagf.class, zzadp.zza);
    }

    public final void zza(zzagi zzagi, zzael<zzagh> zzael) {
        Preconditions.checkNotNull(zzagi);
        Preconditions.checkNotNull(zzael);
        zzadp zzadp = this.zza;
        zzaeo.zza(zzadp.zza("/getRecaptchaParam", this.zzf), zzael, zzagh.class, zzadp.zza);
    }

    public final void zza(zzagj zzagj, zzael<zzagm> zzael) {
        Preconditions.checkNotNull(zzagj);
        Preconditions.checkNotNull(zzael);
        zzado zzado = this.zzb;
        String str = zzado.zza("/recaptchaConfig", this.zzf) + "&clientType=" + zzagj.zzb() + "&version=" + zzagj.zzc();
        if (!zzag.zzc(zzagj.zzd())) {
            str = str + "&tenantId=" + zzagj.zzd();
        }
        zzaeo.zza(str, zzael, zzagm.class, zzado.zza);
    }

    public final void zza() {
        zza((zzaes) null, (zzadp) null, (zzado) null);
    }

    public final void zza(zzagw zzagw, zzael<zzagv> zzael) {
        Preconditions.checkNotNull(zzagw);
        Preconditions.checkNotNull(zzael);
        zzadp zzadp = this.zza;
        zzaeo.zza(zzadp.zza("/resetPassword", this.zzf), zzagw, zzael, zzagv.class, zzadp.zza);
    }

    public final void zza(zzagy zzagy, zzael<zzaha> zzael) {
        Preconditions.checkNotNull(zzagy);
        Preconditions.checkNotNull(zzael);
        zzado zzado = this.zzb;
        zzaeo.zza(zzado.zza("/accounts:revokeToken", this.zzf), zzagy, zzael, zzaha.class, zzado.zza);
    }

    public final void zza(zzagz zzagz, zzael<zzahc> zzael) {
        Preconditions.checkNotNull(zzagz);
        Preconditions.checkNotNull(zzael);
        if (!TextUtils.isEmpty(zzagz.zzc())) {
            zzb().zzb(zzagz.zzc());
        }
        zzadp zzadp = this.zza;
        zzaeo.zza(zzadp.zza("/sendVerificationCode", this.zzf), zzagz, zzael, zzahc.class, zzadp.zza);
    }

    public final void zza(zzahb zzahb, zzael<zzahe> zzael) {
        Preconditions.checkNotNull(zzahb);
        Preconditions.checkNotNull(zzael);
        zzadp zzadp = this.zza;
        zzaeo.zza(zzadp.zza("/setAccountInfo", this.zzf), zzahb, zzael, zzahe.class, zzadp.zza);
    }

    public final void zza(String str, zzael<Void> zzael) {
        Preconditions.checkNotNull(zzael);
        zzb().zza(str);
        zzael.zza(null);
    }

    public final void zza(zzahg zzahg, zzael<zzahf> zzael) {
        Preconditions.checkNotNull(zzahg);
        Preconditions.checkNotNull(zzael);
        zzadp zzadp = this.zza;
        zzaeo.zza(zzadp.zza("/signupNewUser", this.zzf), zzahg, zzael, zzahf.class, zzadp.zza);
    }

    public final void zza(zzahi zzahi, zzael<zzahh> zzael) {
        Preconditions.checkNotNull(zzahi);
        Preconditions.checkNotNull(zzael);
        if (zzahi instanceof zzahm) {
            zzahm zzahm = (zzahm) zzahi;
            if (!TextUtils.isEmpty(zzahm.zzb())) {
                zzb().zzb(zzahm.zzb());
            }
        }
        zzado zzado = this.zzb;
        zzaeo.zza(zzado.zza("/accounts/mfaEnrollment:start", this.zzf), zzahi, zzael, zzahh.class, zzado.zza);
    }

    public final void zza(zzahk zzahk, zzael<zzahj> zzael) {
        Preconditions.checkNotNull(zzahk);
        Preconditions.checkNotNull(zzael);
        if (!TextUtils.isEmpty(zzahk.zzb())) {
            zzb().zzb(zzahk.zzb());
        }
        zzado zzado = this.zzb;
        zzaeo.zza(zzado.zza("/accounts/mfaSignIn:start", this.zzf), zzahk, zzael, zzahj.class, zzado.zza);
    }

    private final void zza(zzaes zzaes, zzadp zzadp, zzado zzado) {
        this.zzc = null;
        this.zza = null;
        this.zzb = null;
        String zza2 = zzafc.zza("firebear.secureToken");
        if (TextUtils.isEmpty(zza2)) {
            zza2 = zzafb.zzd(this.zzf);
        } else {
            SentryLogcatAdapter.e("LocalClient", "Found hermetic configuration for secureToken URL: " + zza2);
        }
        if (this.zzc == null) {
            this.zzc = new zzaes(zza2, zzb());
        }
        String zza3 = zzafc.zza("firebear.identityToolkit");
        if (TextUtils.isEmpty(zza3)) {
            zza3 = zzafb.zzb(this.zzf);
        } else {
            SentryLogcatAdapter.e("LocalClient", "Found hermetic configuration for identityToolkit URL: " + zza3);
        }
        if (this.zza == null) {
            this.zza = new zzadp(zza3, zzb());
        }
        String zza4 = zzafc.zza("firebear.identityToolkitV2");
        if (TextUtils.isEmpty(zza4)) {
            zza4 = zzafb.zzc(this.zzf);
        } else {
            SentryLogcatAdapter.e("LocalClient", "Found hermetic configuration for identityToolkitV2 URL: " + zza4);
        }
        if (this.zzb == null) {
            this.zzb = new zzado(zza4, zzb());
        }
    }

    public final void zza(zzahr zzahr, zzael<zzaht> zzael) {
        Preconditions.checkNotNull(zzahr);
        Preconditions.checkNotNull(zzael);
        zzadp zzadp = this.zza;
        zzaeo.zza(zzadp.zza("/verifyAssertion", this.zzf), zzahr, zzael, zzaht.class, zzadp.zza);
    }

    public final void zza(zzahw zzahw, zzael<zzahv> zzael) {
        Preconditions.checkNotNull(zzahw);
        Preconditions.checkNotNull(zzael);
        zzadp zzadp = this.zza;
        zzaeo.zza(zzadp.zza("/verifyCustomToken", this.zzf), zzahw, zzael, zzahv.class, zzadp.zza);
    }

    public final void zza(zzahy zzahy, zzael<zzahx> zzael) {
        Preconditions.checkNotNull(zzahy);
        Preconditions.checkNotNull(zzael);
        zzadp zzadp = this.zza;
        zzaeo.zza(zzadp.zza("/verifyPassword", this.zzf), zzahy, zzael, zzahx.class, zzadp.zza);
    }

    public final void zza(zzaia zzaia, zzael<zzahz> zzael) {
        Preconditions.checkNotNull(zzaia);
        Preconditions.checkNotNull(zzael);
        zzadp zzadp = this.zza;
        zzaeo.zza(zzadp.zza("/verifyPhoneNumber", this.zzf), zzaia, zzael, zzahz.class, zzadp.zza);
    }

    public final void zza(zzaic zzaic, zzael<zzaib> zzael) {
        Preconditions.checkNotNull(zzaic);
        Preconditions.checkNotNull(zzael);
        zzado zzado = this.zzb;
        zzaeo.zza(zzado.zza("/accounts/mfaEnrollment:withdraw", this.zzf), zzaic, zzael, zzaib.class, zzado.zza);
    }
}
