package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.p002firebaseauthapi.zzzl;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactorAssertion;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.MultiFactorResolver;
import com.google.firebase.auth.MultiFactorSession;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.TotpMultiFactorInfo;
import com.google.firebase.auth.zze;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzaj extends MultiFactorResolver {
    public static final Parcelable.Creator<zzaj> CREATOR = new zzal();
    private final List<PhoneMultiFactorInfo> zza;
    private final zzao zzb;
    private final String zzc;
    /* access modifiers changed from: private */
    public final zze zzd;
    private final zzad zze;
    private final List<TotpMultiFactorInfo> zzf;

    public final Task<AuthResult> resolveSignIn(MultiFactorAssertion multiFactorAssertion) {
        return getFirebaseAuth().zza(multiFactorAssertion, this.zzb, (FirebaseUser) this.zze).continueWithTask(new zzam(this));
    }

    public final FirebaseAuth getFirebaseAuth() {
        return FirebaseAuth.getInstance(FirebaseApp.getInstance(this.zzc));
    }

    public final MultiFactorSession getSession() {
        return this.zzb;
    }

    public static zzaj zza(zzzl zzzl, FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
        List<MultiFactorInfo> zzc2 = zzzl.zzc();
        ArrayList arrayList = new ArrayList();
        for (MultiFactorInfo next : zzc2) {
            if (next instanceof PhoneMultiFactorInfo) {
                arrayList.add((PhoneMultiFactorInfo) next);
            }
        }
        List<MultiFactorInfo> zzc3 = zzzl.zzc();
        ArrayList arrayList2 = new ArrayList();
        for (MultiFactorInfo next2 : zzc3) {
            if (next2 instanceof TotpMultiFactorInfo) {
                arrayList2.add((TotpMultiFactorInfo) next2);
            }
        }
        return new zzaj(arrayList, zzao.zza(zzzl.zzc(), zzzl.zzb()), firebaseAuth.getApp().getName(), zzzl.zza(), (zzad) firebaseUser, arrayList2);
    }

    public final List<MultiFactorInfo> getHints() {
        ArrayList arrayList = new ArrayList();
        for (PhoneMultiFactorInfo add : this.zza) {
            arrayList.add(add);
        }
        for (TotpMultiFactorInfo add2 : this.zzf) {
            arrayList.add(add2);
        }
        return arrayList;
    }

    public zzaj(List<PhoneMultiFactorInfo> list, zzao zzao, String str, zze zze2, zzad zzad, List<TotpMultiFactorInfo> list2) {
        this.zza = (List) Preconditions.checkNotNull(list);
        this.zzb = (zzao) Preconditions.checkNotNull(zzao);
        this.zzc = Preconditions.checkNotEmpty(str);
        this.zzd = zze2;
        this.zze = zzad;
        this.zzf = (List) Preconditions.checkNotNull(list2);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zza, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getSession(), i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zze, i, false);
        SafeParcelWriter.writeTypedList(parcel, 6, this.zzf, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
