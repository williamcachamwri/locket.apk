package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp;
import java.util.List;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class BarhopperProto$BarhopperResponse extends zzed<BarhopperProto$BarhopperResponse, zzj> implements zzfp {
    /* access modifiers changed from: private */
    public static final BarhopperProto$BarhopperResponse zza;
    private int zzd;
    private zzel zze = zzO();
    private int zzf;
    private String zzg = "";
    private zzdb zzh = zzdb.zzb;
    private byte zzi = 2;

    static {
        BarhopperProto$BarhopperResponse barhopperProto$BarhopperResponse = new BarhopperProto$BarhopperResponse();
        zza = barhopperProto$BarhopperResponse;
        zzed.zzU(BarhopperProto$BarhopperResponse.class, barhopperProto$BarhopperResponse);
    }

    private BarhopperProto$BarhopperResponse() {
    }

    public static BarhopperProto$BarhopperResponse zzb(byte[] bArr, zzdo zzdo) throws zzeo {
        return (BarhopperProto$BarhopperResponse) zzed.zzK(zza, bArr, zzdo);
    }

    public final List zzc() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzi);
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0002\u0001Л\u0002ᴌ\u0000\u0003ဈ\u0001\u0004ည\u0002", new Object[]{"zzd", "zze", zzc.class, "zzf", zzah.zza, "zzg", "zzh"});
        } else if (i2 == 3) {
            return new BarhopperProto$BarhopperResponse();
        } else {
            if (i2 == 4) {
                return new zzj((zza) null);
            }
            if (i2 == 5) {
                return zza;
            }
            this.zzi = obj == null ? (byte) 0 : 1;
            return null;
        }
    }
}
