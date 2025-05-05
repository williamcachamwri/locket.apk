package com.google.android.gms.recaptchabase;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-recaptchabase@@16.1.0 */
public final class InitRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<InitRequest> CREATOR = new zzf();

    /* compiled from: com.google.android.gms:play-services-recaptchabase@@16.1.0 */
    public static final class Builder {
        private final InitRequest zza = new InitRequest();

        public Builder() {
        }

        public InitRequest build() {
            return this.zza;
        }

        public Builder(InitRequest initRequest) {
        }
    }

    InitRequest() {
    }

    public boolean equals(Object obj) {
        return this == obj || (obj instanceof InitRequest);
    }

    public int hashCode() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        SafeParcelWriter.finishObjectHeader(parcel, SafeParcelWriter.beginObjectHeader(parcel));
    }
}
