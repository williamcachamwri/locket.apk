package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
final class zzdq implements ObjectEncoder {
    static final zzdq zza = new zzdq();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("inferenceCommonLogEvent");
        zzae zzae = new zzae();
        zzae.zza(1);
        zzb = builder.withProperty(zzae.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("options");
        zzae zzae2 = new zzae();
        zzae2.zza(2);
        zzc = builder2.withProperty(zzae2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("inputsFormats");
        zzae zzae3 = new zzae();
        zzae3.zza(3);
        zzd = builder3.withProperty(zzae3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("outputFormats");
        zzae zzae4 = new zzae();
        zzae4.zza(4);
        zze = builder4.withProperty(zzae4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("modelInitializationMs");
        zzae zzae5 = new zzae();
        zzae5.zza(5);
        zzf = builder5.withProperty(zzae5.zzb()).build();
    }

    private zzdq() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzhx zzhx = (zzhx) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
