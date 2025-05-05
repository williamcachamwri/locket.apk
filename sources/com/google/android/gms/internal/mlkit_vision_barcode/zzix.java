package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzix implements ObjectEncoder {
    static final zzix zza = new zzix();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("cameraSource");
        zzfa zzfa = new zzfa();
        zzfa.zza(1);
        builder.withProperty(zzfa.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("eventType");
        zzfa zzfa2 = new zzfa();
        zzfa2.zza(2);
        builder2.withProperty(zzfa2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("requestedPreviewHeight");
        zzfa zzfa3 = new zzfa();
        zzfa3.zza(3);
        builder3.withProperty(zzfa3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("requestedPreviewWidth");
        zzfa zzfa4 = new zzfa();
        zzfa4.zza(4);
        builder4.withProperty(zzfa4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("actualPreviewHeight");
        zzfa zzfa5 = new zzfa();
        zzfa5.zza(5);
        builder5.withProperty(zzfa5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("actualPreviewWidth");
        zzfa zzfa6 = new zzfa();
        zzfa6.zza(6);
        builder6.withProperty(zzfa6.zzb()).build();
    }

    private zzix() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzoz zzoz = (zzoz) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
