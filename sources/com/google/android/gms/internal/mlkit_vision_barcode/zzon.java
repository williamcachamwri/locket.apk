package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzon implements ObjectEncoder {
    static final zzon zza = new zzon();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;
    private static final FieldDescriptor zzh;
    private static final FieldDescriptor zzi;
    private static final FieldDescriptor zzj;
    private static final FieldDescriptor zzk;
    private static final FieldDescriptor zzl;
    private static final FieldDescriptor zzm;
    private static final FieldDescriptor zzn;
    private static final FieldDescriptor zzo;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.APP_ID);
        zzfa zzfa = new zzfa();
        zzfa.zza(1);
        zzb = builder.withProperty(zzfa.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.APP_VERSION);
        zzfa zzfa2 = new zzfa();
        zzfa2.zza(2);
        zzc = builder2.withProperty(zzfa2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("firebaseProjectId");
        zzfa zzfa3 = new zzfa();
        zzfa3.zza(3);
        zzd = builder3.withProperty(zzfa3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("mlSdkVersion");
        zzfa zzfa4 = new zzfa();
        zzfa4.zza(4);
        zze = builder4.withProperty(zzfa4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("tfliteSchemaVersion");
        zzfa zzfa5 = new zzfa();
        zzfa5.zza(5);
        zzf = builder5.withProperty(zzfa5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("gcmSenderId");
        zzfa zzfa6 = new zzfa();
        zzfa6.zza(6);
        zzg = builder6.withProperty(zzfa6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder(DynamicLink.Builder.KEY_API_KEY);
        zzfa zzfa7 = new zzfa();
        zzfa7.zza(7);
        zzh = builder7.withProperty(zzfa7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("languages");
        zzfa zzfa8 = new zzfa();
        zzfa8.zza(8);
        zzi = builder8.withProperty(zzfa8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("mlSdkInstanceId");
        zzfa zzfa9 = new zzfa();
        zzfa9.zza(9);
        zzj = builder9.withProperty(zzfa9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("isClearcutClient");
        zzfa zzfa10 = new zzfa();
        zzfa10.zza(10);
        zzk = builder10.withProperty(zzfa10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("isStandaloneMlkit");
        zzfa zzfa11 = new zzfa();
        zzfa11.zza(11);
        zzl = builder11.withProperty(zzfa11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("isJsonLogging");
        zzfa zzfa12 = new zzfa();
        zzfa12.zza(12);
        zzm = builder12.withProperty(zzfa12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("buildLevel");
        zzfa zzfa13 = new zzfa();
        zzfa13.zza(13);
        zzn = builder13.withProperty(zzfa13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("optionalModuleVersion");
        zzfa zzfa14 = new zzfa();
        zzfa14.zza(14);
        zzo = builder14.withProperty(zzfa14.zzb()).build();
    }

    private zzon() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzvd zzvd = (zzvd) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zzvd.zzg());
        objectEncoderContext.add(zzc, (Object) zzvd.zzh());
        objectEncoderContext.add(zzd, (Object) null);
        objectEncoderContext.add(zze, (Object) zzvd.zzj());
        objectEncoderContext.add(zzf, (Object) zzvd.zzk());
        objectEncoderContext.add(zzg, (Object) null);
        objectEncoderContext.add(zzh, (Object) null);
        objectEncoderContext.add(zzi, (Object) zzvd.zza());
        objectEncoderContext.add(zzj, (Object) zzvd.zzi());
        objectEncoderContext.add(zzk, (Object) zzvd.zzb());
        objectEncoderContext.add(zzl, (Object) zzvd.zzd());
        objectEncoderContext.add(zzm, (Object) zzvd.zzc());
        objectEncoderContext.add(zzn, (Object) zzvd.zze());
        objectEncoderContext.add(zzo, (Object) zzvd.zzf());
    }
}
