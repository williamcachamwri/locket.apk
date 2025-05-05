package kotlinx.serialization.internal;

import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u0003*\u0014\b\u0003\u0010\u0004*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00052 \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0007\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u0006B#\b\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\t¢\u0006\u0002\u0010\u000bJ-\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00028\u00032\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0004¢\u0006\u0002\u0010\u001bJ-\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00028\u00032\u0006\u0010\u001e\u001a\u00020\u001fH\u0004¢\u0006\u0002\u0010 J\u001d\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010%J)\u0010&\u001a\u00020\u0014*\u00028\u00032\u0006\u0010\u001d\u001a\u00020\u00192\u0006\u0010'\u001a\u00028\u00002\u0006\u0010$\u001a\u00028\u0001H$¢\u0006\u0002\u0010(R\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011\u0001\u0002)*¨\u0006+"}, d2 = {"Lkotlinx/serialization/internal/MapLikeSerializer;", "Key", "Value", "Collection", "Builder", "", "Lkotlinx/serialization/internal/AbstractCollectionSerializer;", "", "keySerializer", "Lkotlinx/serialization/KSerializer;", "valueSerializer", "(Lkotlinx/serialization/KSerializer;Lkotlinx/serialization/KSerializer;)V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "getKeySerializer", "()Lkotlinx/serialization/KSerializer;", "getValueSerializer", "readAll", "", "decoder", "Lkotlinx/serialization/encoding/CompositeDecoder;", "builder", "startIndex", "", "size", "(Lkotlinx/serialization/encoding/CompositeDecoder;Ljava/util/Map;II)V", "readElement", "index", "checkIndex", "", "(Lkotlinx/serialization/encoding/CompositeDecoder;ILjava/util/Map;Z)V", "serialize", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "(Lkotlinx/serialization/encoding/Encoder;Ljava/lang/Object;)V", "insertKeyValuePair", "key", "(Ljava/util/Map;ILjava/lang/Object;Ljava/lang/Object;)V", "Lkotlinx/serialization/internal/HashMapSerializer;", "Lkotlinx/serialization/internal/LinkedHashMapSerializer;", "kotlinx-serialization-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
@InternalSerializationApi
/* compiled from: CollectionSerializers.kt */
public abstract class MapLikeSerializer<Key, Value, Collection, Builder extends Map<Key, Value>> extends AbstractCollectionSerializer<Map.Entry<? extends Key, ? extends Value>, Collection, Builder> {
    private final KSerializer<Key> keySerializer;
    private final KSerializer<Value> valueSerializer;

    public /* synthetic */ MapLikeSerializer(KSerializer kSerializer, KSerializer kSerializer2, DefaultConstructorMarker defaultConstructorMarker) {
        this(kSerializer, kSerializer2);
    }

    public abstract SerialDescriptor getDescriptor();

    /* access modifiers changed from: protected */
    public abstract void insertKeyValuePair(Builder builder, int i, Key key, Value value);

    public final KSerializer<Key> getKeySerializer() {
        return this.keySerializer;
    }

    public final KSerializer<Value> getValueSerializer() {
        return this.valueSerializer;
    }

    private MapLikeSerializer(KSerializer<Key> kSerializer, KSerializer<Value> kSerializer2) {
        super((DefaultConstructorMarker) null);
        this.keySerializer = kSerializer;
        this.valueSerializer = kSerializer2;
    }

    /* access modifiers changed from: protected */
    public final void readAll(CompositeDecoder compositeDecoder, Builder builder, int i, int i2) {
        Intrinsics.checkNotNullParameter(compositeDecoder, "decoder");
        Intrinsics.checkNotNullParameter(builder, "builder");
        if (i2 >= 0) {
            IntProgression step = RangesKt.step((IntProgression) RangesKt.until(0, i2 * 2), 2);
            int first = step.getFirst();
            int last = step.getLast();
            int step2 = step.getStep();
            if ((step2 > 0 && first <= last) || (step2 < 0 && last <= first)) {
                while (true) {
                    readElement(compositeDecoder, i + first, builder, false);
                    if (first != last) {
                        first += step2;
                    } else {
                        return;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Size must be known in advance when using READ_ALL".toString());
        }
    }

    /* access modifiers changed from: protected */
    public final void readElement(CompositeDecoder compositeDecoder, int i, Builder builder, boolean z) {
        int i2;
        Object obj;
        Intrinsics.checkNotNullParameter(compositeDecoder, "decoder");
        Intrinsics.checkNotNullParameter(builder, "builder");
        Object decodeSerializableElement$default = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(compositeDecoder, getDescriptor(), i, this.keySerializer, (Object) null, 8, (Object) null);
        boolean z2 = true;
        if (z) {
            i2 = compositeDecoder.decodeElementIndex(getDescriptor());
            if (i2 != i + 1) {
                z2 = false;
            }
            if (!z2) {
                throw new IllegalArgumentException(("Value must follow key in a map, index for key: " + i + ", returned index for value: " + i2).toString());
            }
        } else {
            i2 = i + 1;
        }
        int i3 = i2;
        if (!builder.containsKey(decodeSerializableElement$default) || (this.valueSerializer.getDescriptor().getKind() instanceof PrimitiveKind)) {
            obj = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(compositeDecoder, getDescriptor(), i3, this.valueSerializer, (Object) null, 8, (Object) null);
        } else {
            obj = compositeDecoder.decodeSerializableElement(getDescriptor(), i3, this.valueSerializer, MapsKt.getValue(builder, decodeSerializableElement$default));
        }
        builder.put(decodeSerializableElement$default, obj);
    }

    public void serialize(Encoder encoder, Collection collection) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        int collectionSize = collectionSize(collection);
        SerialDescriptor descriptor = getDescriptor();
        CompositeEncoder beginCollection = encoder.beginCollection(descriptor, collectionSize);
        Iterator collectionIterator = collectionIterator(collection);
        int i = 0;
        while (collectionIterator.hasNext()) {
            Map.Entry entry = (Map.Entry) collectionIterator.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            int i2 = i + 1;
            beginCollection.encodeSerializableElement(getDescriptor(), i, getKeySerializer(), key);
            beginCollection.encodeSerializableElement(getDescriptor(), i2, getValueSerializer(), value);
            i = i2 + 1;
        }
        beginCollection.endStructure(descriptor);
    }
}
