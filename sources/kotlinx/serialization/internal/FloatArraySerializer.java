package kotlinx.serialization.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÁ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0002H\u0014J(\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0014J \u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\rH\u0014J\f\u0010\u0016\u001a\u00020\r*\u00020\u0002H\u0014J\f\u0010\u0017\u001a\u00020\u0005*\u00020\u0002H\u0014¨\u0006\u0018"}, d2 = {"Lkotlinx/serialization/internal/FloatArraySerializer;", "Lkotlinx/serialization/KSerializer;", "", "Lkotlinx/serialization/internal/PrimitiveArraySerializer;", "", "Lkotlinx/serialization/internal/FloatArrayBuilder;", "()V", "empty", "readElement", "", "decoder", "Lkotlinx/serialization/encoding/CompositeDecoder;", "index", "", "builder", "checkIndex", "", "writeContent", "encoder", "Lkotlinx/serialization/encoding/CompositeEncoder;", "content", "size", "collectionSize", "toBuilder", "kotlinx-serialization-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PrimitiveArraysSerializers.kt */
public final class FloatArraySerializer extends PrimitiveArraySerializer<Float, float[], FloatArrayBuilder> implements KSerializer<float[]> {
    public static final FloatArraySerializer INSTANCE = new FloatArraySerializer();

    /* access modifiers changed from: protected */
    public float[] empty() {
        return new float[0];
    }

    private FloatArraySerializer() {
        super(BuiltinSerializersKt.serializer(FloatCompanionObject.INSTANCE));
    }

    /* access modifiers changed from: protected */
    public int collectionSize(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr.length;
    }

    /* access modifiers changed from: protected */
    public FloatArrayBuilder toBuilder(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return new FloatArrayBuilder(fArr);
    }

    /* access modifiers changed from: protected */
    public void readElement(CompositeDecoder compositeDecoder, int i, FloatArrayBuilder floatArrayBuilder, boolean z) {
        Intrinsics.checkNotNullParameter(compositeDecoder, "decoder");
        Intrinsics.checkNotNullParameter(floatArrayBuilder, "builder");
        floatArrayBuilder.append$kotlinx_serialization_core(compositeDecoder.decodeFloatElement(getDescriptor(), i));
    }

    /* access modifiers changed from: protected */
    public void writeContent(CompositeEncoder compositeEncoder, float[] fArr, int i) {
        Intrinsics.checkNotNullParameter(compositeEncoder, "encoder");
        Intrinsics.checkNotNullParameter(fArr, "content");
        for (int i2 = 0; i2 < i; i2++) {
            compositeEncoder.encodeFloatElement(getDescriptor(), i2, fArr[i2]);
        }
    }
}
