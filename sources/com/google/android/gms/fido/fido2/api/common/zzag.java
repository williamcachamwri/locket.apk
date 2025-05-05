package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
public final class zzag extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzag> CREATOR = new zzah();
    private final String zza;

    public zzag(String str) {
        this.zza = (String) Preconditions.checkNotNull(str);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzag)) {
            return false;
        }
        return this.zza.equals(((zzag) obj).zza);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
