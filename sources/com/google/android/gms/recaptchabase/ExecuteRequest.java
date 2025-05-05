package com.google.android.gms.recaptchabase;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-recaptchabase@@16.1.0 */
public final class ExecuteRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ExecuteRequest> CREATOR = new zzb();
    /* access modifiers changed from: private */
    public String zza;
    /* access modifiers changed from: private */
    public String zzb;

    /* compiled from: com.google.android.gms:play-services-recaptchabase@@16.1.0 */
    public static final class Builder {
        private final ExecuteRequest zza;

        public Builder() {
            this.zza = new ExecuteRequest((zza) null);
        }

        public ExecuteRequest build() {
            return this.zza;
        }

        public Builder setExecuteId(String str) {
            this.zza.zzb = str;
            return this;
        }

        public Builder setNonce(String str) {
            this.zza.zza = str;
            return this;
        }

        public Builder(ExecuteRequest executeRequest) {
            ExecuteRequest executeRequest2 = new ExecuteRequest((zza) null);
            this.zza = executeRequest2;
            executeRequest2.zza = executeRequest.zza;
            executeRequest2.zzb = executeRequest.zzb;
        }
    }

    private ExecuteRequest() {
        throw null;
    }

    /* synthetic */ ExecuteRequest(zza zza2) {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ExecuteRequest) {
            ExecuteRequest executeRequest = (ExecuteRequest) obj;
            return Objects.equal(this.zza, executeRequest.zza) && Objects.equal(this.zzb, executeRequest.zzb);
        }
    }

    public String getExecuteId() {
        return this.zzb;
    }

    public String getNonce() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getNonce(), false);
        SafeParcelWriter.writeString(parcel, 2, getExecuteId(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    ExecuteRequest(String str, String str2) {
        this.zza = str;
        this.zzb = str2;
    }
}
