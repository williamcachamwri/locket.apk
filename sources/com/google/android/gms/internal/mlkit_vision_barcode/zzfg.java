package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzfg implements ObjectEncoderContext {
    private static final Charset zza = Charset.forName("UTF-8");
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final ObjectEncoder zzd = new zzff();
    private OutputStream zze;
    private final Map zzf;
    private final Map zzg;
    private final ObjectEncoder zzh;
    private final zzfk zzi = new zzfk(this);

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("key");
        zzfa zzfa = new zzfa();
        zzfa.zza(1);
        zzb = builder.withProperty(zzfa.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("value");
        zzfa zzfa2 = new zzfa();
        zzfa2.zza(2);
        zzc = builder2.withProperty(zzfa2.zzb()).build();
    }

    zzfg(OutputStream outputStream, Map map, Map map2, ObjectEncoder objectEncoder) {
        this.zze = outputStream;
        this.zzf = map;
        this.zzg = map2;
        this.zzh = objectEncoder;
    }

    static /* synthetic */ void zzg(Map.Entry entry, ObjectEncoderContext objectEncoderContext) throws IOException {
        objectEncoderContext.add(zzb, entry.getKey());
        objectEncoderContext.add(zzc, entry.getValue());
    }

    private static int zzh(FieldDescriptor fieldDescriptor) {
        zzfe zzfe = (zzfe) fieldDescriptor.getProperty(zzfe.class);
        if (zzfe != null) {
            return zzfe.zza();
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    private final long zzi(ObjectEncoder objectEncoder, Object obj) throws IOException {
        OutputStream outputStream;
        zzfb zzfb = new zzfb();
        try {
            outputStream = this.zze;
            this.zze = zzfb;
            objectEncoder.encode(obj, this);
            this.zze = outputStream;
            long zza2 = zzfb.zza();
            zzfb.close();
            return zza2;
        } catch (Throwable th) {
            try {
                zzfb.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static zzfe zzj(FieldDescriptor fieldDescriptor) {
        zzfe zzfe = (zzfe) fieldDescriptor.getProperty(zzfe.class);
        if (zzfe != null) {
            return zzfe;
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    private final zzfg zzk(ObjectEncoder objectEncoder, FieldDescriptor fieldDescriptor, Object obj, boolean z) throws IOException {
        long zzi2 = zzi(objectEncoder, obj);
        if (z && zzi2 == 0) {
            return this;
        }
        zzn((zzh(fieldDescriptor) << 3) | 2);
        zzo(zzi2);
        objectEncoder.encode(obj, this);
        return this;
    }

    private final zzfg zzl(ValueEncoder valueEncoder, FieldDescriptor fieldDescriptor, Object obj, boolean z) throws IOException {
        this.zzi.zza(fieldDescriptor, z);
        valueEncoder.encode(obj, this.zzi);
        return this;
    }

    private static ByteBuffer zzm(int i) {
        return ByteBuffer.allocate(i).order(ByteOrder.LITTLE_ENDIAN);
    }

    private final void zzn(int i) throws IOException {
        while (true) {
            int i2 = i & 127;
            if (((long) (i & -128)) != 0) {
                this.zze.write(i2 | 128);
                i >>>= 7;
            } else {
                this.zze.write(i2);
                return;
            }
        }
    }

    private final void zzo(long j) throws IOException {
        while (true) {
            int i = ((int) j) & 127;
            if ((-128 & j) != 0) {
                this.zze.write(i | 128);
                j >>>= 7;
            } else {
                this.zze.write(i);
                return;
            }
        }
    }

    public final ObjectEncoderContext add(FieldDescriptor fieldDescriptor, double d) throws IOException {
        zza(fieldDescriptor, d, true);
        return this;
    }

    public final ObjectEncoderContext inline(Object obj) throws IOException {
        zzf(obj);
        return this;
    }

    public final ObjectEncoderContext nested(FieldDescriptor fieldDescriptor) throws IOException {
        throw new EncodingException("nested() is not implemented for protobuf encoding.");
    }

    /* access modifiers changed from: package-private */
    public final ObjectEncoderContext zza(FieldDescriptor fieldDescriptor, double d, boolean z) throws IOException {
        if (z && d == 0.0d) {
            return this;
        }
        zzn((zzh(fieldDescriptor) << 3) | 1);
        this.zze.write(zzm(8).putDouble(d).array());
        return this;
    }

    /* access modifiers changed from: package-private */
    public final ObjectEncoderContext zzb(FieldDescriptor fieldDescriptor, float f, boolean z) throws IOException {
        if (z && f == 0.0f) {
            return this;
        }
        zzn((zzh(fieldDescriptor) << 3) | 5);
        this.zze.write(zzm(4).putFloat(f).array());
        return this;
    }

    /* access modifiers changed from: package-private */
    public final ObjectEncoderContext zzc(FieldDescriptor fieldDescriptor, Object obj, boolean z) throws IOException {
        if (obj != null) {
            if (obj instanceof CharSequence) {
                CharSequence charSequence = (CharSequence) obj;
                if (!z || charSequence.length() != 0) {
                    zzn((zzh(fieldDescriptor) << 3) | 2);
                    byte[] bytes = charSequence.toString().getBytes(zza);
                    zzn(bytes.length);
                    this.zze.write(bytes);
                    return this;
                }
            } else if (obj instanceof Collection) {
                for (Object zzc2 : (Collection) obj) {
                    zzc(fieldDescriptor, zzc2, false);
                }
            } else if (obj instanceof Map) {
                for (Map.Entry zzk : ((Map) obj).entrySet()) {
                    zzk(zzd, fieldDescriptor, zzk, false);
                }
            } else if (obj instanceof Double) {
                zza(fieldDescriptor, ((Double) obj).doubleValue(), z);
                return this;
            } else if (obj instanceof Float) {
                zzb(fieldDescriptor, ((Float) obj).floatValue(), z);
                return this;
            } else if (obj instanceof Number) {
                zze(fieldDescriptor, ((Number) obj).longValue(), z);
                return this;
            } else if (obj instanceof Boolean) {
                zzd(fieldDescriptor, ((Boolean) obj).booleanValue() ? 1 : 0, z);
                return this;
            } else if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                if (!z || bArr.length != 0) {
                    zzn((zzh(fieldDescriptor) << 3) | 2);
                    zzn(bArr.length);
                    this.zze.write(bArr);
                    return this;
                }
            } else {
                ObjectEncoder objectEncoder = (ObjectEncoder) this.zzf.get(obj.getClass());
                if (objectEncoder != null) {
                    zzk(objectEncoder, fieldDescriptor, obj, z);
                    return this;
                }
                ValueEncoder valueEncoder = (ValueEncoder) this.zzg.get(obj.getClass());
                if (valueEncoder != null) {
                    zzl(valueEncoder, fieldDescriptor, obj, z);
                    return this;
                } else if (obj instanceof zzfc) {
                    zzd(fieldDescriptor, ((zzfc) obj).zza(), true);
                    return this;
                } else if (obj instanceof Enum) {
                    zzd(fieldDescriptor, ((Enum) obj).ordinal(), true);
                    return this;
                } else {
                    zzk(this.zzh, fieldDescriptor, obj, z);
                    return this;
                }
            }
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public final zzfg zzd(FieldDescriptor fieldDescriptor, int i, boolean z) throws IOException {
        if (!z || i != 0) {
            zzfe zzj = zzj(fieldDescriptor);
            int ordinal = zzj.zzb().ordinal();
            if (ordinal == 0) {
                zzn(zzj.zza() << 3);
                zzn(i);
            } else if (ordinal == 1) {
                zzn(zzj.zza() << 3);
                zzn((i + i) ^ (i >> 31));
            } else if (ordinal == 2) {
                zzn((zzj.zza() << 3) | 5);
                this.zze.write(zzm(4).putInt(i).array());
            }
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public final zzfg zze(FieldDescriptor fieldDescriptor, long j, boolean z) throws IOException {
        if (!z || j != 0) {
            zzfe zzj = zzj(fieldDescriptor);
            int ordinal = zzj.zzb().ordinal();
            if (ordinal == 0) {
                zzn(zzj.zza() << 3);
                zzo(j);
            } else if (ordinal == 1) {
                zzn(zzj.zza() << 3);
                zzo((j >> 63) ^ (j + j));
            } else if (ordinal == 2) {
                zzn((zzj.zza() << 3) | 1);
                this.zze.write(zzm(8).putLong(j).array());
            }
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public final zzfg zzf(Object obj) throws IOException {
        if (obj == null) {
            return this;
        }
        ObjectEncoder objectEncoder = (ObjectEncoder) this.zzf.get(obj.getClass());
        if (objectEncoder != null) {
            objectEncoder.encode(obj, this);
            return this;
        }
        throw new EncodingException("No encoder for ".concat(String.valueOf(String.valueOf(obj.getClass()))));
    }

    public final ObjectEncoderContext add(FieldDescriptor fieldDescriptor, float f) throws IOException {
        zzb(fieldDescriptor, f, true);
        return this;
    }

    public final ObjectEncoderContext nested(String str) throws IOException {
        return nested(FieldDescriptor.of(str));
    }

    public final /* synthetic */ ObjectEncoderContext add(FieldDescriptor fieldDescriptor, int i) throws IOException {
        zzd(fieldDescriptor, i, true);
        return this;
    }

    public final /* synthetic */ ObjectEncoderContext add(FieldDescriptor fieldDescriptor, long j) throws IOException {
        zze(fieldDescriptor, j, true);
        return this;
    }

    public final ObjectEncoderContext add(FieldDescriptor fieldDescriptor, Object obj) throws IOException {
        zzc(fieldDescriptor, obj, true);
        return this;
    }

    public final /* synthetic */ ObjectEncoderContext add(FieldDescriptor fieldDescriptor, boolean z) throws IOException {
        zzd(fieldDescriptor, z ? 1 : 0, true);
        return this;
    }

    public final ObjectEncoderContext add(String str, double d) throws IOException {
        zza(FieldDescriptor.of(str), d, true);
        return this;
    }

    public final ObjectEncoderContext add(String str, int i) throws IOException {
        zzd(FieldDescriptor.of(str), i, true);
        return this;
    }

    public final ObjectEncoderContext add(String str, long j) throws IOException {
        zze(FieldDescriptor.of(str), j, true);
        return this;
    }

    public final ObjectEncoderContext add(String str, Object obj) throws IOException {
        zzc(FieldDescriptor.of(str), obj, true);
        return this;
    }

    public final ObjectEncoderContext add(String str, boolean z) throws IOException {
        zzd(FieldDescriptor.of(str), z ? 1 : 0, true);
        return this;
    }
}
