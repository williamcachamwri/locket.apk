package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import io.sentry.SentryEvent;
import io.sentry.protocol.Device;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzjr implements ObjectEncoder {
    static final zzjr zza = new zzjr();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
        zzfa zzfa = new zzfa();
        zzfa.zza(1);
        builder.withProperty(zzfa.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("osBuild");
        zzfa zzfa2 = new zzfa();
        zzfa2.zza(2);
        builder2.withProperty(zzfa2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder(Device.JsonKeys.BRAND);
        zzfa zzfa3 = new zzfa();
        zzfa3.zza(3);
        builder3.withProperty(zzfa3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder(Device.TYPE);
        zzfa zzfa4 = new zzfa();
        zzfa4.zza(4);
        builder4.withProperty(zzfa4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("hardware");
        zzfa zzfa5 = new zzfa();
        zzfa5.zza(5);
        builder5.withProperty(zzfa5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder(Device.JsonKeys.MANUFACTURER);
        zzfa zzfa6 = new zzfa();
        zzfa6.zza(6);
        builder6.withProperty(zzfa6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder(Device.JsonKeys.MODEL);
        zzfa zzfa7 = new zzfa();
        zzfa7.zza(7);
        builder7.withProperty(zzfa7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("product");
        zzfa zzfa8 = new zzfa();
        zzfa8.zza(8);
        builder8.withProperty(zzfa8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("soc");
        zzfa zzfa9 = new zzfa();
        zzfa9.zza(9);
        builder9.withProperty(zzfa9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("socMetaBuildId");
        zzfa zzfa10 = new zzfa();
        zzfa10.zza(10);
        builder10.withProperty(zzfa10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder(SentryEvent.JsonKeys.FINGERPRINT);
        zzfa zzfa11 = new zzfa();
        zzfa11.zza(11);
        builder11.withProperty(zzfa11.zzb()).build();
    }

    private zzjr() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzvk zzvk = (zzvk) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
