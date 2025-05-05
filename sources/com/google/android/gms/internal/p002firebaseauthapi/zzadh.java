package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.MultiFactorAssertion;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneMultiFactorAssertion;
import com.google.firebase.auth.TotpMultiFactorAssertion;
import com.google.firebase.auth.TotpSecret;
import com.google.firebase.auth.UserProfileChangeRequest;
import java.util.concurrent.ScheduledExecutorService;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadh  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzadh {
    private static final Logger zza = new Logger("FirebaseAuth", "FirebaseAuthFallback:");
    private final zzzk zzb;
    private final zzaew zzc;

    zzadh(FirebaseApp firebaseApp, ScheduledExecutorService scheduledExecutorService) {
        Preconditions.checkNotNull(firebaseApp);
        Context applicationContext = firebaseApp.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zzb = new zzzk(new zzadv(firebaseApp, zzads.zza()));
        this.zzc = new zzaew(applicationContext, scheduledExecutorService);
    }

    public final void zza(String str, String str2, zzadf zzadf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadf);
        this.zzb.zza(str, str2, new zzade(zzadf, zza));
    }

    public final void zzb(String str, String str2, zzadf zzadf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadf);
        this.zzb.zzb(str, str2, new zzade(zzadf, zza));
    }

    public final void zzc(String str, String str2, zzadf zzadf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadf);
        this.zzb.zzc(str, str2, new zzade(zzadf, zza));
    }

    public final void zzd(String str, String str2, zzadf zzadf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadf);
        this.zzb.zzd(str, str2, new zzade(zzadf, zza));
    }

    public final void zza(zzagw zzagw, zzadf zzadf) {
        Preconditions.checkNotNull(zzagw);
        Preconditions.checkNotEmpty(zzagw.zzb());
        Preconditions.checkNotNull(zzadf);
        this.zzb.zza(zzagw, new zzade(zzadf, zza));
    }

    public final void zza(String str, String str2, String str3, String str4, zzadf zzadf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadf);
        this.zzb.zza(str, str2, str3, str4, new zzade(zzadf, zza));
    }

    public final void zza(String str, zzadf zzadf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadf);
        this.zzb.zza(str, new zzade(zzadf, zza));
    }

    public final void zza(MultiFactorAssertion multiFactorAssertion, String str, String str2, String str3, zzadf zzadf) {
        zzafp zzafp;
        Preconditions.checkNotNull(multiFactorAssertion);
        Preconditions.checkNotEmpty(str, "cachedTokenState should not be empty.");
        Preconditions.checkNotNull(zzadf);
        if (multiFactorAssertion instanceof PhoneMultiFactorAssertion) {
            PhoneAuthCredential zza2 = ((PhoneMultiFactorAssertion) multiFactorAssertion).zza();
            zzafp = zzaft.zza(str, (String) Preconditions.checkNotNull(zza2.zzc()), (String) Preconditions.checkNotNull(zza2.getSmsCode()), str2, str3);
        } else if (multiFactorAssertion instanceof TotpMultiFactorAssertion) {
            TotpMultiFactorAssertion totpMultiFactorAssertion = (TotpMultiFactorAssertion) multiFactorAssertion;
            zzafp = zzafv.zza(str, Preconditions.checkNotEmpty(str2), Preconditions.checkNotEmpty(((TotpSecret) Preconditions.checkNotNull(totpMultiFactorAssertion.zza())).getSessionInfo()), Preconditions.checkNotEmpty(totpMultiFactorAssertion.zzc()), str3);
        } else {
            throw new IllegalArgumentException("multiFactorAssertion must be either PhoneMultiFactorAssertion or TotpMultiFactorAssertion.");
        }
        this.zzb.zza(zzafp, str, new zzade(zzadf, zza));
    }

    public final void zza(String str, MultiFactorAssertion multiFactorAssertion, String str2, zzadf zzadf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(multiFactorAssertion);
        Preconditions.checkNotNull(zzadf);
        if (multiFactorAssertion instanceof PhoneMultiFactorAssertion) {
            PhoneAuthCredential zza2 = ((PhoneMultiFactorAssertion) multiFactorAssertion).zza();
            this.zzb.zza((zzafr) zzafw.zza(str, (String) Preconditions.checkNotNull(zza2.zzc()), (String) Preconditions.checkNotNull(zza2.getSmsCode()), str2), new zzade(zzadf, zza));
        } else if (multiFactorAssertion instanceof TotpMultiFactorAssertion) {
            TotpMultiFactorAssertion totpMultiFactorAssertion = (TotpMultiFactorAssertion) multiFactorAssertion;
            this.zzb.zza((zzafr) zzafy.zza(str, Preconditions.checkNotEmpty(totpMultiFactorAssertion.zzc()), str2, Preconditions.checkNotEmpty(totpMultiFactorAssertion.zzb())), new zzade(zzadf, zza));
        } else {
            throw new IllegalArgumentException("multiFactorAssertion must be either PhoneMultiFactorAssertion or TotpMultiFactorAssertion.");
        }
    }

    public final void zzb(String str, zzadf zzadf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadf);
        this.zzb.zzb(str, new zzade(zzadf, zza));
    }

    public final void zza(zzagi zzagi, zzadf zzadf) {
        Preconditions.checkNotNull(zzagi);
        this.zzb.zza(zzagi, new zzade(zzadf, zza));
    }

    public final void zze(String str, String str2, zzadf zzadf) {
        Preconditions.checkNotEmpty(str);
        this.zzb.zze(str, str2, new zzade(zzadf, zza));
    }

    public final void zza(zzagj zzagj, zzadf zzadf) {
        Preconditions.checkNotNull(zzagj);
        this.zzb.zza(zzagj, new zzade(zzadf, zza));
    }

    public final void zza(String str, String str2, String str3, String str4, String str5, zzadf zzadf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzadf);
        this.zzb.zza(str, str2, str3, str4, str5, new zzade(zzadf, zza));
    }

    public final void zza(String str, zzahr zzahr, zzadf zzadf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzahr);
        Preconditions.checkNotNull(zzadf);
        this.zzb.zza(str, zzahr, new zzade(zzadf, zza));
    }

    public final void zza(zzagp zzagp, zzadf zzadf) {
        Preconditions.checkNotNull(zzadf);
        Preconditions.checkNotNull(zzagp);
        this.zzb.zza(Preconditions.checkNotEmpty(zzagp.zzb()), zzagp.zza(), new zzade(zzadf, zza));
    }

    public final void zzc(String str, zzadf zzadf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadf);
        this.zzb.zzc(str, new zzade(zzadf, zza));
    }

    public final void zza(zzagy zzagy, zzadf zzadf) {
        Preconditions.checkNotNull(zzagy);
        this.zzb.zza(zzagy, new zzade(zzadf, zza));
    }

    public final void zza(zzagg zzagg, zzadf zzadf) {
        Preconditions.checkNotNull(zzagg);
        Preconditions.checkNotEmpty(zzagg.zzd());
        Preconditions.checkNotNull(zzadf);
        this.zzb.zza(zzagg, new zzade(zzadf, zza));
    }

    public final void zzb(zzagg zzagg, zzadf zzadf) {
        Preconditions.checkNotNull(zzagg);
        Preconditions.checkNotEmpty(zzagg.zzc());
        Preconditions.checkNotNull(zzadf);
        this.zzb.zzb(zzagg, new zzade(zzadf, zza));
    }

    public final void zza(zzagz zzagz, zzadf zzadf) {
        Preconditions.checkNotNull(zzadf);
        Preconditions.checkNotNull(zzagz);
        String zzd = zzagz.zzd();
        zzade zzade = new zzade(zzadf, zza);
        if (this.zzc.zzd(zzd)) {
            if (zzagz.zze()) {
                this.zzc.zzc(zzd);
            } else {
                this.zzc.zzb(zzade, zzd);
                return;
            }
        }
        long zzb2 = zzagz.zzb();
        boolean zzf = zzagz.zzf();
        if (zza(zzb2, zzf)) {
            zzagz.zza(new zzafg(this.zzc.zzb()));
        }
        this.zzc.zza(zzd, zzade, zzb2, zzf);
        this.zzb.zza(zzagz, this.zzc.zza(zzade, zzd));
    }

    public final void zza(zzahd zzahd, zzadf zzadf) {
        Preconditions.checkNotNull(zzahd);
        Preconditions.checkNotNull(zzadf);
        this.zzb.zzd(zzahd.zza(), new zzade(zzadf, zza));
    }

    public final void zzd(String str, zzadf zzadf) {
        Preconditions.checkNotNull(zzadf);
        this.zzb.zze(str, new zzade(zzadf, zza));
    }

    public final void zza(zzahr zzahr, zzadf zzadf) {
        Preconditions.checkNotNull(zzahr);
        Preconditions.checkNotNull(zzadf);
        this.zzb.zza(zzahr, new zzade(zzadf, zza));
    }

    public final void zza(zzahw zzahw, zzadf zzadf) {
        Preconditions.checkNotNull(zzahw);
        Preconditions.checkNotNull(zzadf);
        this.zzb.zza(zzahw, new zzade(zzadf, zza));
    }

    public final void zzb(String str, String str2, String str3, String str4, zzadf zzadf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadf);
        Preconditions.checkNotNull(zzadf);
        this.zzb.zzb(str, str2, str3, str4, new zzade(zzadf, zza));
    }

    public final void zza(zzafn zzafn, zzadf zzadf) {
        Preconditions.checkNotNull(zzadf);
        Preconditions.checkNotNull(zzafn.zzb());
        this.zzb.zza(zzafn.zzb(), zzafn.zzc(), new zzade(zzadf, zza));
    }

    public final void zza(zzzg zzzg, zzadf zzadf) {
        Preconditions.checkNotNull(zzadf);
        Preconditions.checkNotNull(zzzg);
        this.zzb.zza(zzaeq.zza((PhoneAuthCredential) Preconditions.checkNotNull(zzzg.zza())), new zzade(zzadf, zza));
    }

    public final void zza(String str, String str2, String str3, long j, boolean z, boolean z2, String str4, String str5, String str6, boolean z3, zzadf zzadf) {
        String str7 = str2;
        String str8 = str;
        Preconditions.checkNotEmpty(str, "idToken should not be empty.");
        Preconditions.checkNotNull(zzadf);
        zzade zzade = new zzade(zzadf, zza);
        if (this.zzc.zzd(str2)) {
            if (z) {
                this.zzc.zzc(str2);
            } else {
                this.zzc.zzb(zzade, str2);
                return;
            }
        }
        zzahm zza2 = zzahm.zza(str, str2, str3, str4, str5, str6, (String) null);
        long j2 = j;
        if (zza(j, z3)) {
            zza2.zza(new zzafg(this.zzc.zzb()));
        }
        this.zzc.zza(str2, zzade, j, z3);
        this.zzb.zza((zzahi) zza2, this.zzc.zza(zzade, str2));
    }

    public final void zza(zzzj zzzj, zzadf zzadf) {
        Preconditions.checkNotNull(zzzj);
        Preconditions.checkNotNull(zzadf);
        String phoneNumber = zzzj.zzb().getPhoneNumber();
        zzade zzade = new zzade(zzadf, zza);
        if (this.zzc.zzd(phoneNumber)) {
            if (zzzj.zzh()) {
                this.zzc.zzc(phoneNumber);
            } else {
                this.zzc.zzb(zzade, phoneNumber);
                return;
            }
        }
        long zza2 = zzzj.zza();
        boolean zzi = zzzj.zzi();
        zzahk zza3 = zzahk.zza(zzzj.zze(), zzzj.zzb().getUid(), zzzj.zzb().getPhoneNumber(), zzzj.zzd(), zzzj.zzg(), zzzj.zzf(), zzzj.zzc());
        if (zza(zza2, zzi)) {
            zza3.zza(new zzafg(this.zzc.zzb()));
        }
        this.zzc.zza(phoneNumber, zzade, zza2, zzi);
        this.zzb.zza(zza3, this.zzc.zza(zzade, phoneNumber));
    }

    public final void zza(zzaho zzaho, zzadf zzadf) {
        this.zzb.zza((zzahi) zzaho, new zzade((zzadf) Preconditions.checkNotNull(zzadf), zza));
    }

    public final void zza(String str, String str2, String str3, zzadf zzadf) {
        Preconditions.checkNotEmpty(str, "cachedTokenState should not be empty.");
        Preconditions.checkNotEmpty(str2, "uid should not be empty.");
        Preconditions.checkNotNull(zzadf);
        this.zzb.zza(str, str2, str3, new zzade(zzadf, zza));
    }

    public final void zze(String str, zzadf zzadf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadf);
        this.zzb.zzf(str, new zzade(zzadf, zza));
    }

    public final void zzf(String str, String str2, zzadf zzadf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadf);
        this.zzb.zzf(str, str2, new zzade(zzadf, zza));
    }

    public final void zza(String str, UserProfileChangeRequest userProfileChangeRequest, zzadf zzadf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(userProfileChangeRequest);
        Preconditions.checkNotNull(zzadf);
        this.zzb.zza(str, userProfileChangeRequest, new zzade(zzadf, zza));
    }

    public final void zzc(zzagg zzagg, zzadf zzadf) {
        Preconditions.checkNotNull(zzagg);
        this.zzb.zzc(zzagg, new zzade(zzadf, zza));
    }

    private static boolean zza(long j, boolean z) {
        if (j > 0 && z) {
            return true;
        }
        zza.w("App hash will not be appended to the request.", new Object[0]);
        return false;
    }
}
