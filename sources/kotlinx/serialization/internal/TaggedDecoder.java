package kotlinx.serialization.internal;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleBuildersKt;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0016\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0004J\u0006\u0010\u0019\u001a\u00020\u000bJ\u0016\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u001eJ\u0016\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010 \u001a\u00020!J\u0016\u0010\"\u001a\u00020!2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010#\u001a\u00020$J\u0016\u0010%\u001a\u00020$2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010&\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020\u0015J\u0006\u0010(\u001a\u00020)J\u0016\u0010*\u001a\u00020)2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001cJ\u0010\u0010+\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0016\u0010,\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010-\u001a\u00020\u001cJ\u0016\u0010.\u001a\u00020\u001c2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010/\u001a\u000200J\u0016\u00101\u001a\u0002002\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001cJ\b\u00102\u001a\u00020\u000bH\u0016J\b\u00103\u001a\u0004\u0018\u000104JA\u00105\u001a\u0004\u0018\u0001H6\"\b\b\u0001\u00106*\u0002072\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001c2\u000e\u00108\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H6092\b\u0010:\u001a\u0004\u0018\u0001H6¢\u0006\u0002\u0010;J9\u0010<\u001a\u0002H6\"\u0004\b\u0001\u001062\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001c2\f\u00108\u001a\b\u0012\u0004\u0012\u0002H6092\b\u0010:\u001a\u0004\u0018\u0001H6¢\u0006\u0002\u0010;J+\u0010=\u001a\u0002H6\"\u0004\b\u0001\u001062\f\u00108\u001a\b\u0012\u0004\u0012\u0002H6092\b\u0010:\u001a\u0004\u0018\u0001H6H\u0014¢\u0006\u0002\u0010>J\u0006\u0010?\u001a\u00020@J\u0016\u0010A\u001a\u00020@2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010B\u001a\u00020CJ\u0016\u0010D\u001a\u00020C2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001cJ\u0015\u0010E\u001a\u00020\u000b2\u0006\u0010F\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010GJ\u0015\u0010H\u001a\u00020\u001e2\u0006\u0010F\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010IJ\u0015\u0010J\u001a\u00020!2\u0006\u0010F\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010KJ\u0015\u0010L\u001a\u00020$2\u0006\u0010F\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010MJ\u001d\u0010N\u001a\u00020\u001c2\u0006\u0010F\u001a\u00028\u00002\u0006\u0010'\u001a\u00020\u0015H\u0014¢\u0006\u0002\u0010OJ\u0015\u0010P\u001a\u00020)2\u0006\u0010F\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010QJ\u001d\u0010R\u001a\u00020\u00022\u0006\u0010F\u001a\u00028\u00002\u0006\u0010S\u001a\u00020\u0015H\u0014¢\u0006\u0002\u0010TJ\u0015\u0010U\u001a\u00020\u001c2\u0006\u0010F\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010VJ\u0015\u0010W\u001a\u0002002\u0006\u0010F\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010XJ\u0015\u0010Y\u001a\u00020\u000b2\u0006\u0010F\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010GJ\u0017\u0010Z\u001a\u0004\u0018\u0001042\u0006\u0010F\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010[J\u0015\u0010\\\u001a\u00020@2\u0006\u0010F\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010]J\u0015\u0010^\u001a\u00020C2\u0006\u0010F\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010_J\u0015\u0010`\u001a\u0002072\u0006\u0010F\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010aJ\u0010\u0010b\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\r\u0010c\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010\u0007J\u0015\u0010d\u001a\u00020\u00172\u0006\u0010e\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010fJ)\u0010g\u001a\u0002Hh\"\u0004\b\u0001\u0010h2\u0006\u0010F\u001a\u00028\u00002\f\u0010i\u001a\b\u0012\u0004\u0012\u0002Hh0jH\u0002¢\u0006\u0002\u0010kJ\u0019\u0010l\u001a\u00028\u0000*\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001cH$¢\u0006\u0002\u0010mR\u0014\u0010\u0005\u001a\u00028\u00008DX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u0004\u0018\u00018\u00008DX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0011j\b\u0012\u0004\u0012\u00028\u0000`\u0012X\u0004¢\u0006\u0002\n\u0000¨\u0006n"}, d2 = {"Lkotlinx/serialization/internal/TaggedDecoder;", "Tag", "Lkotlinx/serialization/encoding/Decoder;", "Lkotlinx/serialization/encoding/CompositeDecoder;", "()V", "currentTag", "getCurrentTag", "()Ljava/lang/Object;", "currentTagOrNull", "getCurrentTagOrNull", "flag", "", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "tagStack", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "beginStructure", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "copyTagsTo", "", "other", "decodeBoolean", "decodeBooleanElement", "index", "", "decodeByte", "", "decodeByteElement", "decodeChar", "", "decodeCharElement", "decodeDouble", "", "decodeDoubleElement", "decodeEnum", "enumDescriptor", "decodeFloat", "", "decodeFloatElement", "decodeInline", "decodeInlineElement", "decodeInt", "decodeIntElement", "decodeLong", "", "decodeLongElement", "decodeNotNullMark", "decodeNull", "", "decodeNullableSerializableElement", "T", "", "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "previousValue", "(Lkotlinx/serialization/descriptors/SerialDescriptor;ILkotlinx/serialization/DeserializationStrategy;Ljava/lang/Object;)Ljava/lang/Object;", "decodeSerializableElement", "decodeSerializableValue", "(Lkotlinx/serialization/DeserializationStrategy;Ljava/lang/Object;)Ljava/lang/Object;", "decodeShort", "", "decodeShortElement", "decodeString", "", "decodeStringElement", "decodeTaggedBoolean", "tag", "(Ljava/lang/Object;)Z", "decodeTaggedByte", "(Ljava/lang/Object;)B", "decodeTaggedChar", "(Ljava/lang/Object;)C", "decodeTaggedDouble", "(Ljava/lang/Object;)D", "decodeTaggedEnum", "(Ljava/lang/Object;Lkotlinx/serialization/descriptors/SerialDescriptor;)I", "decodeTaggedFloat", "(Ljava/lang/Object;)F", "decodeTaggedInline", "inlineDescriptor", "(Ljava/lang/Object;Lkotlinx/serialization/descriptors/SerialDescriptor;)Lkotlinx/serialization/encoding/Decoder;", "decodeTaggedInt", "(Ljava/lang/Object;)I", "decodeTaggedLong", "(Ljava/lang/Object;)J", "decodeTaggedNotNullMark", "decodeTaggedNull", "(Ljava/lang/Object;)Ljava/lang/Void;", "decodeTaggedShort", "(Ljava/lang/Object;)S", "decodeTaggedString", "(Ljava/lang/Object;)Ljava/lang/String;", "decodeTaggedValue", "(Ljava/lang/Object;)Ljava/lang/Object;", "endStructure", "popTag", "pushTag", "name", "(Ljava/lang/Object;)V", "tagBlock", "E", "block", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getTag", "(Lkotlinx/serialization/descriptors/SerialDescriptor;I)Ljava/lang/Object;", "kotlinx-serialization-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
@InternalSerializationApi
/* compiled from: Tagged.kt */
public abstract class TaggedDecoder<Tag> implements Decoder, CompositeDecoder {
    private boolean flag;
    private final ArrayList<Tag> tagStack = new ArrayList<>();

    public final Void decodeNull() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean decodeTaggedNotNullMark(Tag tag) {
        return true;
    }

    /* access modifiers changed from: protected */
    public Void decodeTaggedNull(Tag tag) {
        return null;
    }

    public void endStructure(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
    }

    /* access modifiers changed from: protected */
    public abstract Tag getTag(SerialDescriptor serialDescriptor, int i);

    public int decodeCollectionSize(SerialDescriptor serialDescriptor) {
        return CompositeDecoder.DefaultImpls.decodeCollectionSize(this, serialDescriptor);
    }

    @ExperimentalSerializationApi
    public <T> T decodeNullableSerializableValue(DeserializationStrategy<? extends T> deserializationStrategy) {
        return Decoder.DefaultImpls.decodeNullableSerializableValue(this, deserializationStrategy);
    }

    @ExperimentalSerializationApi
    public boolean decodeSequentially() {
        return CompositeDecoder.DefaultImpls.decodeSequentially(this);
    }

    public <T> T decodeSerializableValue(DeserializationStrategy<? extends T> deserializationStrategy) {
        return Decoder.DefaultImpls.decodeSerializableValue(this, deserializationStrategy);
    }

    public SerializersModule getSerializersModule() {
        return SerializersModuleBuildersKt.EmptySerializersModule();
    }

    /* access modifiers changed from: protected */
    public Object decodeTaggedValue(Tag tag) {
        throw new SerializationException(Reflection.getOrCreateKotlinClass(getClass()) + " can't retrieve untyped values");
    }

    /* access modifiers changed from: protected */
    public boolean decodeTaggedBoolean(Tag tag) {
        Object decodeTaggedValue = decodeTaggedValue(tag);
        Intrinsics.checkNotNull(decodeTaggedValue, "null cannot be cast to non-null type kotlin.Boolean");
        return ((Boolean) decodeTaggedValue).booleanValue();
    }

    /* access modifiers changed from: protected */
    public byte decodeTaggedByte(Tag tag) {
        Object decodeTaggedValue = decodeTaggedValue(tag);
        Intrinsics.checkNotNull(decodeTaggedValue, "null cannot be cast to non-null type kotlin.Byte");
        return ((Byte) decodeTaggedValue).byteValue();
    }

    /* access modifiers changed from: protected */
    public short decodeTaggedShort(Tag tag) {
        Object decodeTaggedValue = decodeTaggedValue(tag);
        Intrinsics.checkNotNull(decodeTaggedValue, "null cannot be cast to non-null type kotlin.Short");
        return ((Short) decodeTaggedValue).shortValue();
    }

    /* access modifiers changed from: protected */
    public int decodeTaggedInt(Tag tag) {
        Object decodeTaggedValue = decodeTaggedValue(tag);
        Intrinsics.checkNotNull(decodeTaggedValue, "null cannot be cast to non-null type kotlin.Int");
        return ((Integer) decodeTaggedValue).intValue();
    }

    /* access modifiers changed from: protected */
    public long decodeTaggedLong(Tag tag) {
        Object decodeTaggedValue = decodeTaggedValue(tag);
        Intrinsics.checkNotNull(decodeTaggedValue, "null cannot be cast to non-null type kotlin.Long");
        return ((Long) decodeTaggedValue).longValue();
    }

    /* access modifiers changed from: protected */
    public float decodeTaggedFloat(Tag tag) {
        Object decodeTaggedValue = decodeTaggedValue(tag);
        Intrinsics.checkNotNull(decodeTaggedValue, "null cannot be cast to non-null type kotlin.Float");
        return ((Float) decodeTaggedValue).floatValue();
    }

    /* access modifiers changed from: protected */
    public double decodeTaggedDouble(Tag tag) {
        Object decodeTaggedValue = decodeTaggedValue(tag);
        Intrinsics.checkNotNull(decodeTaggedValue, "null cannot be cast to non-null type kotlin.Double");
        return ((Double) decodeTaggedValue).doubleValue();
    }

    /* access modifiers changed from: protected */
    public char decodeTaggedChar(Tag tag) {
        Object decodeTaggedValue = decodeTaggedValue(tag);
        Intrinsics.checkNotNull(decodeTaggedValue, "null cannot be cast to non-null type kotlin.Char");
        return ((Character) decodeTaggedValue).charValue();
    }

    /* access modifiers changed from: protected */
    public String decodeTaggedString(Tag tag) {
        Object decodeTaggedValue = decodeTaggedValue(tag);
        Intrinsics.checkNotNull(decodeTaggedValue, "null cannot be cast to non-null type kotlin.String");
        return (String) decodeTaggedValue;
    }

    /* access modifiers changed from: protected */
    public int decodeTaggedEnum(Tag tag, SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "enumDescriptor");
        Object decodeTaggedValue = decodeTaggedValue(tag);
        Intrinsics.checkNotNull(decodeTaggedValue, "null cannot be cast to non-null type kotlin.Int");
        return ((Integer) decodeTaggedValue).intValue();
    }

    /* access modifiers changed from: protected */
    public Decoder decodeTaggedInline(Tag tag, SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "inlineDescriptor");
        TaggedDecoder taggedDecoder = this;
        pushTag(tag);
        return this;
    }

    /* access modifiers changed from: protected */
    public <T> T decodeSerializableValue(DeserializationStrategy<? extends T> deserializationStrategy, T t) {
        Intrinsics.checkNotNullParameter(deserializationStrategy, "deserializer");
        return decodeSerializableValue(deserializationStrategy);
    }

    public Decoder decodeInline(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        return decodeTaggedInline(popTag(), serialDescriptor);
    }

    public boolean decodeNotNullMark() {
        Object currentTagOrNull = getCurrentTagOrNull();
        if (currentTagOrNull == null) {
            return false;
        }
        return decodeTaggedNotNullMark(currentTagOrNull);
    }

    public final boolean decodeBoolean() {
        return decodeTaggedBoolean(popTag());
    }

    public final byte decodeByte() {
        return decodeTaggedByte(popTag());
    }

    public final short decodeShort() {
        return decodeTaggedShort(popTag());
    }

    public final int decodeInt() {
        return decodeTaggedInt(popTag());
    }

    public final long decodeLong() {
        return decodeTaggedLong(popTag());
    }

    public final float decodeFloat() {
        return decodeTaggedFloat(popTag());
    }

    public final double decodeDouble() {
        return decodeTaggedDouble(popTag());
    }

    public final char decodeChar() {
        return decodeTaggedChar(popTag());
    }

    public final String decodeString() {
        return decodeTaggedString(popTag());
    }

    public final int decodeEnum(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "enumDescriptor");
        return decodeTaggedEnum(popTag(), serialDescriptor);
    }

    public CompositeDecoder beginStructure(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        return this;
    }

    public final boolean decodeBooleanElement(SerialDescriptor serialDescriptor, int i) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        return decodeTaggedBoolean(getTag(serialDescriptor, i));
    }

    public final byte decodeByteElement(SerialDescriptor serialDescriptor, int i) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        return decodeTaggedByte(getTag(serialDescriptor, i));
    }

    public final short decodeShortElement(SerialDescriptor serialDescriptor, int i) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        return decodeTaggedShort(getTag(serialDescriptor, i));
    }

    public final int decodeIntElement(SerialDescriptor serialDescriptor, int i) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        return decodeTaggedInt(getTag(serialDescriptor, i));
    }

    public final long decodeLongElement(SerialDescriptor serialDescriptor, int i) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        return decodeTaggedLong(getTag(serialDescriptor, i));
    }

    public final float decodeFloatElement(SerialDescriptor serialDescriptor, int i) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        return decodeTaggedFloat(getTag(serialDescriptor, i));
    }

    public final double decodeDoubleElement(SerialDescriptor serialDescriptor, int i) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        return decodeTaggedDouble(getTag(serialDescriptor, i));
    }

    public final char decodeCharElement(SerialDescriptor serialDescriptor, int i) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        return decodeTaggedChar(getTag(serialDescriptor, i));
    }

    public final String decodeStringElement(SerialDescriptor serialDescriptor, int i) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        return decodeTaggedString(getTag(serialDescriptor, i));
    }

    public final Decoder decodeInlineElement(SerialDescriptor serialDescriptor, int i) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        return decodeTaggedInline(getTag(serialDescriptor, i), serialDescriptor.getElementDescriptor(i));
    }

    public final <T> T decodeSerializableElement(SerialDescriptor serialDescriptor, int i, DeserializationStrategy<? extends T> deserializationStrategy, T t) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        Intrinsics.checkNotNullParameter(deserializationStrategy, "deserializer");
        return tagBlock(getTag(serialDescriptor, i), new TaggedDecoder$decodeSerializableElement$1(this, deserializationStrategy, t));
    }

    public final <T> T decodeNullableSerializableElement(SerialDescriptor serialDescriptor, int i, DeserializationStrategy<? extends T> deserializationStrategy, T t) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        Intrinsics.checkNotNullParameter(deserializationStrategy, "deserializer");
        return tagBlock(getTag(serialDescriptor, i), new TaggedDecoder$decodeNullableSerializableElement$1(this, deserializationStrategy, t));
    }

    private final <E> E tagBlock(Tag tag, Function0<? extends E> function0) {
        pushTag(tag);
        E invoke = function0.invoke();
        if (!this.flag) {
            popTag();
        }
        this.flag = false;
        return invoke;
    }

    /* access modifiers changed from: protected */
    public final Tag getCurrentTag() {
        return CollectionsKt.last(this.tagStack);
    }

    /* access modifiers changed from: protected */
    public final Tag getCurrentTagOrNull() {
        return CollectionsKt.lastOrNull(this.tagStack);
    }

    /* access modifiers changed from: protected */
    public final void pushTag(Tag tag) {
        this.tagStack.add(tag);
    }

    /* access modifiers changed from: protected */
    public final void copyTagsTo(TaggedDecoder<Tag> taggedDecoder) {
        Intrinsics.checkNotNullParameter(taggedDecoder, "other");
        taggedDecoder.tagStack.addAll(this.tagStack);
    }

    /* access modifiers changed from: protected */
    public final Tag popTag() {
        ArrayList<Tag> arrayList = this.tagStack;
        Tag remove = arrayList.remove(CollectionsKt.getLastIndex(arrayList));
        this.flag = true;
        return remove;
    }
}
