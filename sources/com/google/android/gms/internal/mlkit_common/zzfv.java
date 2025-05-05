package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:common@@18.11.0 */
final class zzfv implements ObjectEncoder {
    static final zzfv zza = new zzfv();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("options");
        zzay zzay = new zzay();
        zzay.zza(1);
        builder.withProperty(zzay.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("eventType");
        zzay zzay2 = new zzay();
        zzay2.zza(2);
        builder2.withProperty(zzay2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("errorCode");
        zzay zzay3 = new zzay();
        zzay3.zza(3);
        builder3.withProperty(zzay3.zzb()).build();
    }

    private zzfv() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzly zzly = (zzly) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
