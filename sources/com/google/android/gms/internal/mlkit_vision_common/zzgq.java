package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import io.sentry.protocol.Device;
import java.io.IOException;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
final class zzgq implements ObjectEncoder {
    static final zzgq zza = new zzgq();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("stageId");
        zzae zzae = new zzae();
        zzae.zza(1);
        zzb = builder.withProperty(zzae.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder(Device.TYPE);
        zzae zzae2 = new zzae();
        zzae2.zza(2);
        zzc = builder2.withProperty(zzae2.zzb()).build();
    }

    private zzgq() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzlp zzlp = (zzlp) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
