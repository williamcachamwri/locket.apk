package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzld implements ObjectEncoder {
    static final zzld zza = new zzld();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("deviceInfos");
        zzfa zzfa = new zzfa();
        zzfa.zza(1);
        builder.withProperty(zzfa.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("errorInfo");
        zzfa zzfa2 = new zzfa();
        zzfa2.zza(2);
        builder2.withProperty(zzfa2.zzb()).build();
    }

    private zzld() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzvo zzvo = (zzvo) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
