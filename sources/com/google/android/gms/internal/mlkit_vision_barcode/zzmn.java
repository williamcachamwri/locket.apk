package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzmn implements ObjectEncoder {
    static final zzmn zza = new zzmn();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("errorCode");
        zzfa zzfa = new zzfa();
        zzfa.zza(1);
        builder.withProperty(zzfa.zzb()).build();
    }

    private zzmn() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zztb zztb = (zztb) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
