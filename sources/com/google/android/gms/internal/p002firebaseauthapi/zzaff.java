package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaff  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaff extends zzagt {
    private final String zza;
    private final String zzb;

    public final int hashCode() {
        String str = this.zza;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003;
        String str2 = this.zzb;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode ^ i;
    }

    /* access modifiers changed from: package-private */
    public final String zza() {
        return this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final String zzb() {
        return this.zza;
    }

    public final String toString() {
        String str = this.zza;
        return "RecaptchaEnforcementState{provider=" + str + ", enforcementState=" + this.zzb + "}";
    }

    zzaff(String str, String str2) {
        this.zza = str;
        this.zzb = str2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzagt) {
            zzagt zzagt = (zzagt) obj;
            String str = this.zza;
            if (str != null ? str.equals(zzagt.zzb()) : zzagt.zzb() == null) {
                String str2 = this.zzb;
                return str2 != null ? str2.equals(zzagt.zza()) : zzagt.zza() == null;
            }
        }
    }
}
