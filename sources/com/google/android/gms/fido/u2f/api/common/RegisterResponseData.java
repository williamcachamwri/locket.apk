package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fido.u2f.api.common.ProtocolVersion;
import com.google.android.gms.internal.fido.zzam;
import com.google.android.gms.internal.fido.zzan;
import com.google.android.gms.internal.fido.zzch;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
public class RegisterResponseData extends ResponseData {
    public static final Parcelable.Creator<RegisterResponseData> CREATOR = new zzi();
    private final byte[] zza;
    private final ProtocolVersion zzb;
    private final String zzc;

    public RegisterResponseData(byte[] bArr) {
        this.zza = (byte[]) Preconditions.checkNotNull(bArr);
        this.zzb = ProtocolVersion.V1;
        this.zzc = null;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RegisterResponseData)) {
            return false;
        }
        RegisterResponseData registerResponseData = (RegisterResponseData) obj;
        if (!Objects.equal(this.zzb, registerResponseData.zzb) || !Arrays.equals(this.zza, registerResponseData.zza) || !Objects.equal(this.zzc, registerResponseData.zzc)) {
            return false;
        }
        return true;
    }

    public String getClientDataString() {
        return this.zzc;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.zzb;
    }

    public byte[] getRegisterData() {
        return this.zza;
    }

    public int getVersionCode() {
        ProtocolVersion protocolVersion = ProtocolVersion.UNKNOWN;
        int ordinal = this.zzb.ordinal();
        int i = 1;
        if (ordinal != 1) {
            i = 2;
            if (ordinal != 2) {
                return -1;
            }
        }
        return i;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzb, Integer.valueOf(Arrays.hashCode(this.zza)), this.zzc);
    }

    public JSONObject toJsonObject() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("registrationData", Base64.encodeToString(this.zza, 11));
            jSONObject.put("version", this.zzb.toString());
            String str = this.zzc;
            if (str != null) {
                jSONObject.put(SignResponseData.JSON_RESPONSE_DATA_CLIENT_DATA, Base64.encodeToString(str.getBytes(), 11));
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        zzam zza2 = zzan.zza(this);
        zza2.zzb("protocolVersion", this.zzb);
        zzch zzf = zzch.zzf();
        byte[] bArr = this.zza;
        zza2.zzb("registerData", zzf.zzg(bArr, 0, bArr.length));
        String str = this.zzc;
        if (str != null) {
            zza2.zzb("clientDataString", str);
        }
        return zza2.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByteArray(parcel, 2, getRegisterData(), false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb.toString(), false);
        SafeParcelWriter.writeString(parcel, 4, getClientDataString(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public RegisterResponseData(byte[] bArr, ProtocolVersion protocolVersion, String str) {
        this.zza = (byte[]) Preconditions.checkNotNull(bArr);
        this.zzb = (ProtocolVersion) Preconditions.checkNotNull(protocolVersion);
        boolean z = true;
        Preconditions.checkArgument(protocolVersion != ProtocolVersion.UNKNOWN);
        if (protocolVersion == ProtocolVersion.V1) {
            Preconditions.checkArgument(str != null ? false : z);
            this.zzc = null;
            return;
        }
        this.zzc = (String) Preconditions.checkNotNull(str);
    }

    RegisterResponseData(byte[] bArr, String str, String str2) {
        this.zza = bArr;
        try {
            this.zzb = ProtocolVersion.fromString(str);
            this.zzc = str2;
        } catch (ProtocolVersion.UnsupportedProtocolException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
