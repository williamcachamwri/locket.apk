package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import io.sentry.protocol.Device;
import java.io.IOException;

/* compiled from: com.google.mlkit:common@@18.11.0 */
final class zzjv implements ObjectEncoder {
    static final zzjv zza = new zzjv();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("stageId");
        zzay zzay = new zzay();
        zzay.zza(1);
        builder.withProperty(zzay.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder(Device.TYPE);
        zzay zzay2 = new zzay();
        zzay2.zza(2);
        builder2.withProperty(zzay2.zzb()).build();
    }

    private zzjv() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzrk zzrk = (zzrk) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
