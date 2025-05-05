package com.google.android.gms.recaptchabase;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-recaptchabase@@16.1.0 */
public final class InitResult extends AbstractSafeParcelable {
    public static final Parcelable.Creator<InitResult> CREATOR = new zzg();

    /* compiled from: com.google.android.gms:play-services-recaptchabase@@16.1.0 */
    public static final class Builder {
        private final InitResult zza = new InitResult();

        public Builder() {
        }

        public InitResult build() {
            return this.zza;
        }

        public Builder(InitResult initResult) {
        }
    }

    InitResult() {
    }

    public boolean equals(Object obj) {
        return this == obj || (obj instanceof InitResult);
    }

    public int hashCode() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        SafeParcelWriter.finishObjectHeader(parcel, SafeParcelWriter.beginObjectHeader(parcel));
    }
}
