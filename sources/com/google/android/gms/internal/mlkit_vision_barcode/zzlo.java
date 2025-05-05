package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzlo implements ObjectEncoder {
    static final zzlo zza = new zzlo();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("totalStrokeCount");
        zzfa zzfa = new zzfa();
        zzfa.zza(1);
        builder.withProperty(zzfa.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("totalPointCount");
        zzfa zzfa2 = new zzfa();
        zzfa2.zza(2);
        builder2.withProperty(zzfa2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("addedStrokeCount");
        zzfa zzfa3 = new zzfa();
        zzfa3.zza(3);
        builder3.withProperty(zzfa3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("addedPointCount");
        zzfa zzfa4 = new zzfa();
        zzfa4.zza(4);
        builder4.withProperty(zzfa4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("removedStrokeCount");
        zzfa zzfa5 = new zzfa();
        zzfa5.zza(5);
        builder5.withProperty(zzfa5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("removedPointCount");
        zzfa zzfa6 = new zzfa();
        zzfa6.zza(6);
        builder6.withProperty(zzfa6.zzb()).build();
    }

    private zzlo() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzsb zzsb = (zzsb) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
