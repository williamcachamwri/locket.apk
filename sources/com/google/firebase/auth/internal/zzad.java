package com.google.firebase.auth.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.p002firebaseauthapi.zzagl;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.MultiFactor;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.zzan;
import com.google.firebase.auth.zze;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class zzad extends FirebaseUser {
    public static final Parcelable.Creator<zzad> CREATOR = new zzag();
    private zzagl zza;
    private zzz zzb;
    private String zzc;
    private String zzd;
    private List<zzz> zze;
    private List<String> zzf;
    private String zzg;
    private Boolean zzh;
    private zzaf zzi;
    private boolean zzj;
    private zze zzk;
    private zzbl zzl;
    private List<zzan> zzm;

    public Uri getPhotoUrl() {
        return this.zzb.getPhotoUrl();
    }

    public final FirebaseApp zza() {
        return FirebaseApp.getInstance(this.zzc);
    }

    public final zze zzh() {
        return this.zzk;
    }

    public static FirebaseUser zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser) {
        zzad zzad = new zzad(firebaseApp, firebaseUser.getProviderData());
        if (firebaseUser instanceof zzad) {
            zzad zzad2 = (zzad) firebaseUser;
            zzad.zzg = zzad2.zzg;
            zzad.zzd = zzad2.zzd;
            zzad.zzi = (zzaf) zzad2.getMetadata();
        } else {
            zzad.zzi = null;
        }
        if (firebaseUser.zzc() != null) {
            zzad.zza(firebaseUser.zzc());
        }
        if (!firebaseUser.isAnonymous()) {
            zzad.zzb();
        }
        return zzad;
    }

    public final /* synthetic */ FirebaseUser zzb() {
        this.zzh = false;
        return this;
    }

    public final synchronized FirebaseUser zza(List<? extends UserInfo> list) {
        Preconditions.checkNotNull(list);
        this.zze = new ArrayList(list.size());
        this.zzf = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            UserInfo userInfo = (UserInfo) list.get(i);
            if (userInfo.getProviderId().equals("firebase")) {
                this.zzb = (zzz) userInfo;
            } else {
                this.zzf.add(userInfo.getProviderId());
            }
            this.zze.add((zzz) userInfo);
        }
        if (this.zzb == null) {
            this.zzb = this.zze.get(0);
        }
        return this;
    }

    public FirebaseUserMetadata getMetadata() {
        return this.zzi;
    }

    public /* synthetic */ MultiFactor getMultiFactor() {
        return new zzah(this);
    }

    public final zzagl zzc() {
        return this.zza;
    }

    public final zzad zza(String str) {
        this.zzg = str;
        return this;
    }

    public final String zzd() {
        return zzc().zzc();
    }

    public String getDisplayName() {
        return this.zzb.getDisplayName();
    }

    public String getEmail() {
        return this.zzb.getEmail();
    }

    public String getPhoneNumber() {
        return this.zzb.getPhoneNumber();
    }

    public String getProviderId() {
        return this.zzb.getProviderId();
    }

    public final String zze() {
        return this.zza.zzf();
    }

    public String getTenantId() {
        Map map;
        zzagl zzagl = this.zza;
        if (zzagl == null || zzagl.zzc() == null || (map = (Map) zzbg.zza(this.zza.zzc()).getClaims().get("firebase")) == null) {
            return null;
        }
        return (String) map.get("tenant");
    }

    public String getUid() {
        return this.zzb.getUid();
    }

    public final List<MultiFactorInfo> zzi() {
        zzbl zzbl = this.zzl;
        if (zzbl != null) {
            return zzbl.zza();
        }
        return new ArrayList();
    }

    public final List<zzan> zzf() {
        return this.zzm;
    }

    public List<? extends UserInfo> getProviderData() {
        return this.zze;
    }

    public final List<String> zzg() {
        return this.zzf;
    }

    public final List<zzz> zzj() {
        return this.zze;
    }

    public zzad(FirebaseApp firebaseApp, List<? extends UserInfo> list) {
        Preconditions.checkNotNull(firebaseApp);
        this.zzc = firebaseApp.getName();
        this.zzd = "com.google.firebase.auth.internal.DefaultFirebaseUser";
        this.zzg = ExifInterface.GPS_MEASUREMENT_2D;
        zza(list);
    }

    zzad(zzagl zzagl, zzz zzz, String str, String str2, List<zzz> list, List<String> list2, String str3, Boolean bool, zzaf zzaf, boolean z, zze zze2, zzbl zzbl, List<zzan> list3) {
        this.zza = zzagl;
        this.zzb = zzz;
        this.zzc = str;
        this.zzd = str2;
        this.zze = list;
        this.zzf = list2;
        this.zzg = str3;
        this.zzh = bool;
        this.zzi = zzaf;
        this.zzj = z;
        this.zzk = zze2;
        this.zzl = zzbl;
        this.zzm = list3;
    }

    public final void zza(zzagl zzagl) {
        this.zza = (zzagl) Preconditions.checkNotNull(zzagl);
    }

    public final void zza(zze zze2) {
        this.zzk = zze2;
    }

    public final void zzb(List<zzan> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.zzm = list;
    }

    public final void zza(boolean z) {
        this.zzj = z;
    }

    public final void zza(zzaf zzaf) {
        this.zzi = zzaf;
    }

    public final void zzc(List<MultiFactorInfo> list) {
        this.zzl = zzbl.zza(list);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, zzc(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeTypedList(parcel, 5, this.zze, false);
        SafeParcelWriter.writeStringList(parcel, 6, zzg(), false);
        SafeParcelWriter.writeString(parcel, 7, this.zzg, false);
        SafeParcelWriter.writeBooleanObject(parcel, 8, Boolean.valueOf(isAnonymous()), false);
        SafeParcelWriter.writeParcelable(parcel, 9, getMetadata(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzj);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzk, i, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzl, i, false);
        SafeParcelWriter.writeTypedList(parcel, 13, zzf(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public boolean isAnonymous() {
        GetTokenResult zza2;
        Boolean bool = this.zzh;
        if (bool == null || bool.booleanValue()) {
            zzagl zzagl = this.zza;
            String str = "";
            if (!(zzagl == null || (zza2 = zzbg.zza(zzagl.zzc())) == null)) {
                str = zza2.getSignInProvider();
            }
            boolean z = true;
            if (getProviderData().size() > 1 || (str != null && str.equals("custom"))) {
                z = false;
            }
            this.zzh = Boolean.valueOf(z);
        }
        return this.zzh.booleanValue();
    }

    public boolean isEmailVerified() {
        return this.zzb.isEmailVerified();
    }

    public final boolean zzk() {
        return this.zzj;
    }
}
