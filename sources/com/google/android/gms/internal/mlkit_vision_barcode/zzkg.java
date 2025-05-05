package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzkg implements ObjectEncoder {
    static final zzkg zza = new zzkg();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("imageFormat");
        zzfa zzfa = new zzfa();
        zzfa.zza(1);
        zzb = builder.withProperty(zzfa.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("originalImageSize");
        zzfa zzfa2 = new zzfa();
        zzfa2.zza(2);
        zzc = builder2.withProperty(zzfa2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("compressedImageSize");
        zzfa zzfa3 = new zzfa();
        zzfa3.zza(3);
        zzd = builder3.withProperty(zzfa3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("isOdmlImage");
        zzfa zzfa4 = new zzfa();
        zzfa4.zza(4);
        zze = builder4.withProperty(zzfa4.zzb()).build();
    }

    private zzkg() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzqk zzqk = (zzqk) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zzqk.zza());
        objectEncoderContext.add(zzc, (Object) zzqk.zzb());
        objectEncoderContext.add(zzd, (Object) null);
        objectEncoderContext.add(zze, (Object) null);
    }
}
