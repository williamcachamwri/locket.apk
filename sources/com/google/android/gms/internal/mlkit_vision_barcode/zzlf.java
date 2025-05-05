package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzlf implements ObjectEncoder {
    static final zzlf zza = new zzlf();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("inferenceCommonLogEvent");
        zzfa zzfa = new zzfa();
        zzfa.zza(1);
        zzb = builder.withProperty(zzfa.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("options");
        zzfa zzfa2 = new zzfa();
        zzfa2.zza(2);
        zzc = builder2.withProperty(zzfa2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("detectedBarcodeFormats");
        zzfa zzfa3 = new zzfa();
        zzfa3.zza(3);
        zzd = builder3.withProperty(zzfa3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("detectedBarcodeValueTypes");
        zzfa zzfa4 = new zzfa();
        zzfa4.zza(4);
        zze = builder4.withProperty(zzfa4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("imageInfo");
        zzfa zzfa5 = new zzfa();
        zzfa5.zza(5);
        zzf = builder5.withProperty(zzfa5.zzb()).build();
    }

    private zzlf() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzrr zzrr = (zzrr) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zzrr.zzd());
        objectEncoderContext.add(zzc, (Object) zzrr.zze());
        objectEncoderContext.add(zzd, (Object) zzrr.zza());
        objectEncoderContext.add(zze, (Object) zzrr.zzb());
        objectEncoderContext.add(zzf, (Object) zzrr.zzc());
    }
}
