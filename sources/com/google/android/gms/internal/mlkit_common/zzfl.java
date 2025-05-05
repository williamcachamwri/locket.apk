package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import io.sentry.SentryEvent;
import io.sentry.protocol.Device;
import java.io.IOException;

/* compiled from: com.google.mlkit:common@@18.11.0 */
final class zzfl implements ObjectEncoder {
    static final zzfl zza = new zzfl();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
        zzay zzay = new zzay();
        zzay.zza(1);
        builder.withProperty(zzay.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("osBuild");
        zzay zzay2 = new zzay();
        zzay2.zza(2);
        builder2.withProperty(zzay2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder(Device.JsonKeys.BRAND);
        zzay zzay3 = new zzay();
        zzay3.zza(3);
        builder3.withProperty(zzay3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder(Device.TYPE);
        zzay zzay4 = new zzay();
        zzay4.zza(4);
        builder4.withProperty(zzay4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("hardware");
        zzay zzay5 = new zzay();
        zzay5.zza(5);
        builder5.withProperty(zzay5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder(Device.JsonKeys.MANUFACTURER);
        zzay zzay6 = new zzay();
        zzay6.zza(6);
        builder6.withProperty(zzay6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder(Device.JsonKeys.MODEL);
        zzay zzay7 = new zzay();
        zzay7.zza(7);
        builder7.withProperty(zzay7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("product");
        zzay zzay8 = new zzay();
        zzay8.zza(8);
        builder8.withProperty(zzay8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("soc");
        zzay zzay9 = new zzay();
        zzay9.zza(9);
        builder9.withProperty(zzay9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("socMetaBuildId");
        zzay zzay10 = new zzay();
        zzay10.zza(10);
        builder10.withProperty(zzay10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder(SentryEvent.JsonKeys.FINGERPRINT);
        zzay zzay11 = new zzay();
        zzay11.zza(11);
        builder11.withProperty(zzay11.zzb()).build();
    }

    private zzfl() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzrc zzrc = (zzrc) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
