package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import io.sentry.protocol.ViewHierarchyNode;
import java.io.IOException;

/* compiled from: com.google.mlkit:common@@18.11.0 */
final class zzjw implements ObjectEncoder {
    static final zzjw zza = new zzjw();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder(ViewHierarchyNode.JsonKeys.X);
        zzay zzay = new zzay();
        zzay.zza(1);
        builder.withProperty(zzay.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder(ViewHierarchyNode.JsonKeys.Y);
        zzay zzay2 = new zzay();
        zzay2.zza(2);
        builder2.withProperty(zzay2.zzb()).build();
    }

    private zzjw() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzqi zzqi = (zzqi) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
