package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.internal.auth.zzbz;
import io.sentry.protocol.SentryStackFrame;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
public final class zzu extends zzbz {
    public static final Parcelable.Creator<zzu> CREATOR = new zzv();
    private static final HashMap zzc;
    final Set zza;
    final int zzb;
    private zzw zzd;
    private String zze;
    private String zzf;
    private String zzg;

    static {
        HashMap hashMap = new HashMap();
        zzc = hashMap;
        hashMap.put("authenticatorInfo", FastJsonResponse.Field.forConcreteType("authenticatorInfo", 2, zzw.class));
        hashMap.put("signature", FastJsonResponse.Field.forString("signature", 3));
        hashMap.put(SentryStackFrame.JsonKeys.PACKAGE, FastJsonResponse.Field.forString(SentryStackFrame.JsonKeys.PACKAGE, 4));
    }

    public zzu() {
        this.zza = new HashSet(3);
        this.zzb = 1;
    }

    public final void addConcreteTypeInternal(FastJsonResponse.Field field, String str, FastJsonResponse fastJsonResponse) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 2) {
            this.zzd = (zzw) fastJsonResponse;
            this.zza.add(Integer.valueOf(safeParcelableFieldId));
            return;
        }
        throw new IllegalArgumentException(String.format("Field with id=%d is not a known custom type. Found %s", new Object[]{Integer.valueOf(safeParcelableFieldId), fastJsonResponse.getClass().getCanonicalName()}));
    }

    public final /* synthetic */ Map getFieldMappings() {
        return zzc;
    }

    /* access modifiers changed from: protected */
    public final Object getFieldValue(FastJsonResponse.Field field) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 1) {
            return Integer.valueOf(this.zzb);
        }
        if (safeParcelableFieldId == 2) {
            return this.zzd;
        }
        if (safeParcelableFieldId == 3) {
            return this.zze;
        }
        if (safeParcelableFieldId == 4) {
            return this.zzf;
        }
        int safeParcelableFieldId2 = field.getSafeParcelableFieldId();
        throw new IllegalStateException("Unknown SafeParcelable id=" + safeParcelableFieldId2);
    }

    /* access modifiers changed from: protected */
    public final boolean isFieldSet(FastJsonResponse.Field field) {
        return this.zza.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
    }

    /* access modifiers changed from: protected */
    public final void setStringInternal(FastJsonResponse.Field field, String str, String str2) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 3) {
            this.zze = str2;
        } else if (safeParcelableFieldId == 4) {
            this.zzf = str2;
        } else {
            throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string.", new Object[]{Integer.valueOf(safeParcelableFieldId)}));
        }
        this.zza.add(Integer.valueOf(safeParcelableFieldId));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        Set set = this.zza;
        if (set.contains(1)) {
            SafeParcelWriter.writeInt(parcel, 1, this.zzb);
        }
        if (set.contains(2)) {
            SafeParcelWriter.writeParcelable(parcel, 2, this.zzd, i, true);
        }
        if (set.contains(3)) {
            SafeParcelWriter.writeString(parcel, 3, this.zze, true);
        }
        if (set.contains(4)) {
            SafeParcelWriter.writeString(parcel, 4, this.zzf, true);
        }
        if (set.contains(5)) {
            SafeParcelWriter.writeString(parcel, 5, this.zzg, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    zzu(Set set, int i, zzw zzw, String str, String str2, String str3) {
        this.zza = set;
        this.zzb = i;
        this.zzd = zzw;
        this.zze = str;
        this.zzf = str2;
        this.zzg = str3;
    }
}
