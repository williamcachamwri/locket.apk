package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
final class zzgu implements ObjectEncoder {
    static final zzgu zza = new zzgu();
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
        zzae zzae = new zzae();
        zzae.zza(1);
        zzb = builder.withProperty(zzae.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.APP_VERSION);
        zzae zzae2 = new zzae();
        zzae2.zza(2);
        zzc = builder2.withProperty(zzae2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("firebaseProjectId");
        zzae zzae3 = new zzae();
        zzae3.zza(3);
        zzd = builder3.withProperty(zzae3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("mlSdkVersion");
        zzae zzae4 = new zzae();
        zzae4.zza(4);
        zze = builder4.withProperty(zzae4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("tfliteSchemaVersion");
        zzae zzae5 = new zzae();
        zzae5.zza(5);
        zzf = builder5.withProperty(zzae5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("gcmSenderId");
        zzae zzae6 = new zzae();
        zzae6.zza(6);
        zzg = builder6.withProperty(zzae6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder(DynamicLink.Builder.KEY_API_KEY);
        zzae zzae7 = new zzae();
        zzae7.zza(7);
        zzh = builder7.withProperty(zzae7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("languages");
        zzae zzae8 = new zzae();
        zzae8.zza(8);
        zzi = builder8.withProperty(zzae8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("mlSdkInstanceId");
        zzae zzae9 = new zzae();
        zzae9.zza(9);
        zzj = builder9.withProperty(zzae9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("isClearcutClient");
        zzae zzae10 = new zzae();
        zzae10.zza(10);
        zzk = builder10.withProperty(zzae10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("isStandaloneMlkit");
        zzae zzae11 = new zzae();
        zzae11.zza(11);
        zzl = builder11.withProperty(zzae11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("isJsonLogging");
        zzae zzae12 = new zzae();
        zzae12.zza(12);
        zzm = builder12.withProperty(zzae12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("buildLevel");
        zzae zzae13 = new zzae();
        zzae13.zza(13);
        zzn = builder13.withProperty(zzae13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("optionalModuleVersion");
        zzae zzae14 = new zzae();
        zzae14.zza(14);
        zzo = builder14.withProperty(zzae14.zzb()).build();
    }

    private zzgu() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzla zzla = (zzla) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zzla.zzg());
        objectEncoderContext.add(zzc, (Object) zzla.zzh());
        objectEncoderContext.add(zzd, (Object) null);
        objectEncoderContext.add(zze, (Object) zzla.zzj());
        objectEncoderContext.add(zzf, (Object) zzla.zzk());
        objectEncoderContext.add(zzg, (Object) null);
        objectEncoderContext.add(zzh, (Object) null);
        objectEncoderContext.add(zzi, (Object) zzla.zza());
        objectEncoderContext.add(zzj, (Object) zzla.zzi());
        objectEncoderContext.add(zzk, (Object) zzla.zzb());
        objectEncoderContext.add(zzl, (Object) zzla.zzd());
        objectEncoderContext.add(zzm, (Object) zzla.zzc());
        objectEncoderContext.add(zzn, (Object) zzla.zze());
        objectEncoderContext.add(zzo, (Object) zzla.zzf());
    }
}
