package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import io.sentry.protocol.Message;
import java.io.IOException;

/* compiled from: com.google.mlkit:common@@18.11.0 */
final class zzdl implements ObjectEncoder {
    static final zzdl zza = new zzdl();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("errorCode");
        zzay zzay = new zzay();
        zzay.zza(1);
        builder.withProperty(zzay.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("imageInfo");
        zzay zzay2 = new zzay();
        zzay2.zza(2);
        builder2.withProperty(zzay2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("isColdCall");
        zzay zzay3 = new zzay();
        zzay3.zza(3);
        builder3.withProperty(zzay3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder(Message.JsonKeys.PARAMS);
        zzay zzay4 = new zzay();
        zzay4.zza(4);
        builder4.withProperty(zzay4.zzb()).build();
    }

    private zzdl() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzbu zzbu = (zzbu) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
