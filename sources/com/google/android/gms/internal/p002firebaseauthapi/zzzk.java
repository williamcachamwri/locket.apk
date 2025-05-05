package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzaq;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzk  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzzk {
    /* access modifiers changed from: private */
    public final zzaej zza;

    static /* synthetic */ void zza(zzzk zzzk, zzaht zzaht, zzade zzade, zzaem zzaem) {
        Status status;
        if (zzaht.zzo()) {
            zze zzb = zzaht.zzb();
            String zzc = zzaht.zzc();
            String zzj = zzaht.zzj();
            if (zzaht.zzm()) {
                status = new Status(FirebaseError.ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL);
            } else {
                status = zzaq.zza(zzaht.zzd());
            }
            zzade.zza(new zzzi(status, zzb, zzc, zzj));
            return;
        }
        zzzk.zza(new zzagl(zzaht.zzi(), zzaht.zze(), Long.valueOf(zzaht.zza()), "Bearer"), zzaht.zzh(), zzaht.zzg(), Boolean.valueOf(zzaht.zzn()), zzaht.zzb(), zzade, zzaem);
    }

    static /* synthetic */ void zza(zzzk zzzk, zzade zzade, zzahg zzahg, zzaem zzaem) {
        Preconditions.checkNotNull(zzade);
        Preconditions.checkNotNull(zzahg);
        Preconditions.checkNotNull(zzaem);
        zzzk.zza.zza(zzahg, (zzael<zzahf>) new zzaad(zzzk, zzade, zzaem));
    }

    static /* synthetic */ void zza(zzzk zzzk, zzade zzade, zzagl zzagl, zzahb zzahb, zzaem zzaem) {
        Preconditions.checkNotNull(zzade);
        Preconditions.checkNotNull(zzagl);
        Preconditions.checkNotNull(zzahb);
        Preconditions.checkNotNull(zzaem);
        zzzk.zza.zza(new zzagc(zzagl.zzc()), (zzael<zzagb>) new zzzq(zzzk, zzaem, zzade, zzagl, zzahb));
    }

    static /* synthetic */ void zza(zzzk zzzk, zzade zzade, zzagl zzagl, zzage zzage, zzahb zzahb, zzaem zzaem) {
        Preconditions.checkNotNull(zzade);
        Preconditions.checkNotNull(zzagl);
        Preconditions.checkNotNull(zzage);
        Preconditions.checkNotNull(zzahb);
        Preconditions.checkNotNull(zzaem);
        zzzk.zza.zza(zzahb, (zzael<zzahe>) new zzzt(zzzk, zzahb, zzage, zzade, zzagl, zzaem));
    }

    public zzzk(zzaej zzaej) {
        this.zza = (zzaej) Preconditions.checkNotNull(zzaej);
    }

    public final void zza(String str, String str2, zzade zzade) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzade);
        zzahb zzahb = new zzahb();
        zzahb.zze(str);
        zzahb.zzh(str2);
        this.zza.zza(zzahb, (zzael<zzahe>) new zzabe(this, zzade));
    }

    public final void zzb(String str, String str2, zzade zzade) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzade);
        zza(str, (zzael<zzagl>) new zzabc(this, str2, zzade));
    }

    public final void zzc(String str, String str2, zzade zzade) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzade);
        zza(str, (zzael<zzagl>) new zzabf(this, str2, zzade));
    }

    public final void zzd(String str, String str2, zzade zzade) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzade);
        this.zza.zza(new zzagw(str, (String) null, str2), (zzael<zzagv>) new zzzx(this, zzade));
    }

    public final void zza(zzagw zzagw, zzade zzade) {
        Preconditions.checkNotEmpty(zzagw.zzb());
        Preconditions.checkNotNull(zzade);
        this.zza.zza(zzagw, (zzael<zzagv>) new zzzz(this, zzade));
    }

    public final void zza(String str, String str2, String str3, String str4, zzade zzade) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzade);
        this.zza.zza(new zzahg(str, str2, (String) null, str3, str4, (String) null), (zzael<zzahf>) new zzzm(this, zzade));
    }

    public final void zza(String str, zzade zzade) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzade);
        zza(str, (zzael<zzagl>) new zzaax(this, zzade));
    }

    private final void zza(String str, zzael<zzagl> zzael) {
        Preconditions.checkNotNull(zzael);
        Preconditions.checkNotEmpty(str);
        zzagl zzb = zzagl.zzb(str);
        if (zzb.zzg()) {
            zzael.zza(zzb);
            return;
        }
        this.zza.zza(new zzafz(zzb.zzd()), (zzael<zzagl>) new zzabh(this, zzael));
    }

    public final void zza(zzafp zzafp, String str, zzade zzade) {
        Preconditions.checkNotNull(zzafp);
        Preconditions.checkNotNull(zzade);
        zza(str, (zzael<zzagl>) new zzaap(this, zzafp, zzade));
    }

    public final void zza(zzafr zzafr, zzade zzade) {
        Preconditions.checkNotNull(zzafr);
        Preconditions.checkNotNull(zzade);
        this.zza.zza(zzafr, (zzael<zzafu>) new zzaar(this, zzade));
    }

    /* access modifiers changed from: private */
    public final void zza(zzafn zzafn, zzade zzade) {
        Preconditions.checkNotNull(zzafn);
        Preconditions.checkNotNull(zzade);
        this.zza.zza(zzafn, (zzael<zzafq>) new zzzr(this, zzade));
    }

    public final void zzb(String str, zzade zzade) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzade);
        this.zza.zza(new zzafz(str), (zzael<zzagl>) new zzzn(this, zzade));
    }

    /* access modifiers changed from: private */
    public final void zza(zzagl zzagl, String str, String str2, Boolean bool, zze zze, zzade zzade, zzaem zzaem) {
        Preconditions.checkNotNull(zzagl);
        Preconditions.checkNotNull(zzaem);
        Preconditions.checkNotNull(zzade);
        this.zza.zza(new zzagc(zzagl.zzc()), (zzael<zzagb>) new zzzs(this, zzaem, str2, str, bool, zze, zzade, zzagl));
    }

    private final void zzd(zzagg zzagg, zzade zzade) {
        Preconditions.checkNotNull(zzagg);
        Preconditions.checkNotNull(zzade);
        this.zza.zza(zzagg, (zzael<zzagf>) new zzaay(this, zzade));
    }

    public final void zza(zzagi zzagi, zzade zzade) {
        Preconditions.checkNotNull(zzagi);
        Preconditions.checkNotNull(zzade);
        this.zza.zza(zzagi, (zzael<zzagh>) new zzaas(this, zzade));
    }

    public final void zze(String str, String str2, zzade zzade) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzade);
        this.zza.zza(new zzafm(str, str2), (zzael<zzafl>) new zzzv(this, zzade));
    }

    public final void zza(zzagj zzagj, zzade zzade) {
        Preconditions.checkNotNull(zzagj);
        Preconditions.checkNotNull(zzade);
        this.zza.zza(zzagj, (zzael<zzagm>) new zzaat(this, zzade));
    }

    public final void zza(String str, String str2, String str3, String str4, String str5, zzade zzade) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzade);
        zza(str3, (zzael<zzagl>) new zzaaa(this, str, str2, str4, str5, zzade));
    }

    public final void zza(String str, zzahr zzahr, zzade zzade) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzahr);
        Preconditions.checkNotNull(zzade);
        zza(str, (zzael<zzagl>) new zzaae(this, zzahr, zzade));
    }

    public final void zza(String str, zzaia zzaia, zzade zzade) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzaia);
        Preconditions.checkNotNull(zzade);
        zza(str, (zzael<zzagl>) new zzaac(this, zzaia, zzade));
    }

    public final void zzc(String str, zzade zzade) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzade);
        zza(str, (zzael<zzagl>) new zzaav(this, zzade));
    }

    public final void zza(zzagy zzagy, zzade zzade) {
        this.zza.zza(zzagy, (zzael<zzaha>) new zzaba(this, zzade));
    }

    public final void zza(zzagg zzagg, zzade zzade) {
        Preconditions.checkNotEmpty(zzagg.zzd());
        Preconditions.checkNotNull(zzade);
        zzd(zzagg, zzade);
    }

    public final void zzb(zzagg zzagg, zzade zzade) {
        Preconditions.checkNotEmpty(zzagg.zzc());
        Preconditions.checkNotNull(zzade);
        this.zza.zza(zzagg, (zzael<zzagf>) new zzzu(this, zzade));
    }

    public final void zza(zzagz zzagz, zzade zzade) {
        Preconditions.checkNotEmpty(zzagz.zzd());
        Preconditions.checkNotNull(zzade);
        this.zza.zza(zzagz, (zzael<zzahc>) new zzzy(this, zzade));
    }

    public final void zzd(String str, zzade zzade) {
        Preconditions.checkNotNull(zzade);
        this.zza.zza(str, (zzael<Void>) new zzaaz(this, zzade));
    }

    public final void zze(String str, zzade zzade) {
        Preconditions.checkNotNull(zzade);
        this.zza.zza(new zzahg(str), (zzael<zzahf>) new zzabb(this, zzade));
    }

    public final void zza(zzahr zzahr, zzade zzade) {
        Preconditions.checkNotNull(zzahr);
        Preconditions.checkNotNull(zzade);
        zzahr.zzb(true);
        this.zza.zza(zzahr, (zzael<zzaht>) new zzaan(this, zzade));
    }

    public final void zza(zzahw zzahw, zzade zzade) {
        Preconditions.checkNotNull(zzahw);
        Preconditions.checkNotNull(zzade);
        this.zza.zza(zzahw, (zzael<zzahv>) new zzzw(this, zzade));
    }

    public final void zzb(String str, String str2, String str3, String str4, zzade zzade) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzade);
        this.zza.zza(new zzahy(str, str2, str3, str4), (zzael<zzahx>) new zzzp(this, zzade));
    }

    public final void zza(EmailAuthCredential emailAuthCredential, String str, zzade zzade) {
        Preconditions.checkNotNull(emailAuthCredential);
        Preconditions.checkNotNull(zzade);
        if (emailAuthCredential.zzg()) {
            zza(emailAuthCredential.zzb(), (zzael<zzagl>) new zzzo(this, emailAuthCredential, str, zzade));
        } else {
            zza(new zzafn(emailAuthCredential, (String) null, str), zzade);
        }
    }

    public final void zza(zzaia zzaia, zzade zzade) {
        Preconditions.checkNotNull(zzaia);
        Preconditions.checkNotNull(zzade);
        this.zza.zza(zzaia, (zzael<zzahz>) new zzaab(this, zzade));
    }

    public final void zza(zzahi zzahi, zzade zzade) {
        Preconditions.checkNotNull(zzahi);
        Preconditions.checkNotNull(zzade);
        this.zza.zza(zzahi, (zzael<zzahh>) new zzaam(this, zzahi, zzade));
    }

    public final void zza(zzahk zzahk, zzade zzade) {
        Preconditions.checkNotNull(zzahk);
        Preconditions.checkNotNull(zzade);
        this.zza.zza(zzahk, (zzael<zzahj>) new zzaaq(this, zzade));
    }

    public final void zza(String str, String str2, String str3, zzade zzade) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzade);
        zza(str, (zzael<zzagl>) new zzaal(this, str2, str3, zzade));
    }

    public final void zzf(String str, zzade zzade) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzade);
        zza(str, (zzael<zzagl>) new zzaag(this, zzade));
    }

    public final void zzf(String str, String str2, zzade zzade) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzade);
        zza(str2, (zzael<zzagl>) new zzaaj(this, str, zzade));
    }

    public final void zza(String str, UserProfileChangeRequest userProfileChangeRequest, zzade zzade) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(userProfileChangeRequest);
        Preconditions.checkNotNull(zzade);
        zza(str, (zzael<zzagl>) new zzabd(this, userProfileChangeRequest, zzade));
    }

    public final void zzc(zzagg zzagg, zzade zzade) {
        zzd(zzagg, zzade);
    }
}
