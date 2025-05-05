package com.google.android.gms.recaptchabase;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-recaptchabase@@16.1.0 */
public final class ExecuteResult extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ExecuteResult> CREATOR = new zzd();
    /* access modifiers changed from: private */
    public String zza;

    /* compiled from: com.google.android.gms:play-services-recaptchabase@@16.1.0 */
    public static final class Builder {
        private final ExecuteResult zza;

        public Builder() {
            this.zza = new ExecuteResult((zzc) null);
        }

        public ExecuteResult build() {
            return this.zza;
        }

        public Builder setPayload(String str) {
            this.zza.zza = str;
            return this;
        }

        public Builder(ExecuteResult executeResult) {
            ExecuteResult executeResult2 = new ExecuteResult((zzc) null);
            this.zza = executeResult2;
            executeResult2.zza = executeResult.zza;
        }
    }

    private ExecuteResult() {
        throw null;
    }

    /* synthetic */ ExecuteResult(zzc zzc) {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ExecuteResult) {
            return Objects.equal(this.zza, ((ExecuteResult) obj).zza);
        }
        return false;
    }

    public String getPayload() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getPayload(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    ExecuteResult(String str) {
        this.zza = str;
    }
}
