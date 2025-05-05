package kotlinx.serialization.json;

import androidx.media3.muxer.MuxerUtil;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.internal.InlineClassDescriptorKt;
import kotlinx.serialization.json.internal.JsonEncodingException;
import kotlinx.serialization.json.internal.StringOpsKt;
import okhttp3.internal.ws.WebSocketProtocol;

@Metadata(d1 = {"\u0000\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0001\n\u0002\u0010\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u0015\u0010>\u001a\u00020\b2\b\u0010?\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010@\u001a\u0012\u0010>\u001a\u00020-2\b\u0010?\u001a\u0004\u0018\u00010AH\u0007\u001a\u0010\u0010>\u001a\u00020\b2\b\u0010?\u001a\u0004\u0018\u00010B\u001a\u0010\u0010>\u001a\u00020\b2\b\u0010?\u001a\u0004\u0018\u00010\u000f\u001a\u001d\u0010>\u001a\u00020\b2\u0006\u0010?\u001a\u00020CH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bD\u0010E\u001a\u001d\u0010>\u001a\u00020\b2\u0006\u0010?\u001a\u00020FH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bG\u0010H\u001a\u001d\u0010>\u001a\u00020\b2\u0006\u0010?\u001a\u00020IH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bJ\u0010K\u001a\u001d\u0010>\u001a\u00020\b2\u0006\u0010?\u001a\u00020LH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bM\u0010N\u001a\u0012\u0010O\u001a\u00020\b2\b\u0010?\u001a\u0004\u0018\u00010\u000fH\u0007\u001a\u0018\u0010P\u001a\u00020A2\u0006\u0010Q\u001a\u00020\u000f2\u0006\u0010R\u001a\u00020\u000fH\u0001\u001a\u0014\u0010S\u001a\u00020A*\u00020)2\u0006\u0010T\u001a\u00020\u000fH\u0002\"\u001c\u0010\u0000\u001a\u00020\u00018\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n\"\u0017\u0010\u000b\u001a\u0004\u0018\u00010\u0007*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r\"\u0017\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\"\u0015\u0010\u0012\u001a\u00020\u0013*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\"\u0017\u0010\u0016\u001a\u0004\u0018\u00010\u0013*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018\"\u0015\u0010\u0019\u001a\u00020\u001a*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\"\u0017\u0010\u001d\u001a\u0004\u0018\u00010\u001a*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f\"\u0015\u0010 \u001a\u00020!*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\"\u0010#\"\u0017\u0010$\u001a\u0004\u0018\u00010!*\u00020\b8F¢\u0006\u0006\u001a\u0004\b%\u0010&\"\u0015\u0010'\u001a\u00020(*\u00020)8F¢\u0006\u0006\u001a\u0004\b*\u0010+\"\u0015\u0010,\u001a\u00020-*\u00020)8F¢\u0006\u0006\u001a\u0004\b.\u0010/\"\u0015\u00100\u001a\u000201*\u00020)8F¢\u0006\u0006\u001a\u0004\b2\u00103\"\u0015\u00104\u001a\u00020\b*\u00020)8F¢\u0006\u0006\u001a\u0004\b5\u00106\"\u0015\u00107\u001a\u000208*\u00020\b8F¢\u0006\u0006\u001a\u0004\b9\u0010:\"\u0017\u0010;\u001a\u0004\u0018\u000108*\u00020\b8F¢\u0006\u0006\u001a\u0004\b<\u0010=\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006U"}, d2 = {"jsonUnquotedLiteralDescriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getJsonUnquotedLiteralDescriptor$annotations", "()V", "getJsonUnquotedLiteralDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "boolean", "", "Lkotlinx/serialization/json/JsonPrimitive;", "getBoolean", "(Lkotlinx/serialization/json/JsonPrimitive;)Z", "booleanOrNull", "getBooleanOrNull", "(Lkotlinx/serialization/json/JsonPrimitive;)Ljava/lang/Boolean;", "contentOrNull", "", "getContentOrNull", "(Lkotlinx/serialization/json/JsonPrimitive;)Ljava/lang/String;", "double", "", "getDouble", "(Lkotlinx/serialization/json/JsonPrimitive;)D", "doubleOrNull", "getDoubleOrNull", "(Lkotlinx/serialization/json/JsonPrimitive;)Ljava/lang/Double;", "float", "", "getFloat", "(Lkotlinx/serialization/json/JsonPrimitive;)F", "floatOrNull", "getFloatOrNull", "(Lkotlinx/serialization/json/JsonPrimitive;)Ljava/lang/Float;", "int", "", "getInt", "(Lkotlinx/serialization/json/JsonPrimitive;)I", "intOrNull", "getIntOrNull", "(Lkotlinx/serialization/json/JsonPrimitive;)Ljava/lang/Integer;", "jsonArray", "Lkotlinx/serialization/json/JsonArray;", "Lkotlinx/serialization/json/JsonElement;", "getJsonArray", "(Lkotlinx/serialization/json/JsonElement;)Lkotlinx/serialization/json/JsonArray;", "jsonNull", "Lkotlinx/serialization/json/JsonNull;", "getJsonNull", "(Lkotlinx/serialization/json/JsonElement;)Lkotlinx/serialization/json/JsonNull;", "jsonObject", "Lkotlinx/serialization/json/JsonObject;", "getJsonObject", "(Lkotlinx/serialization/json/JsonElement;)Lkotlinx/serialization/json/JsonObject;", "jsonPrimitive", "getJsonPrimitive", "(Lkotlinx/serialization/json/JsonElement;)Lkotlinx/serialization/json/JsonPrimitive;", "long", "", "getLong", "(Lkotlinx/serialization/json/JsonPrimitive;)J", "longOrNull", "getLongOrNull", "(Lkotlinx/serialization/json/JsonPrimitive;)Ljava/lang/Long;", "JsonPrimitive", "value", "(Ljava/lang/Boolean;)Lkotlinx/serialization/json/JsonPrimitive;", "", "", "Lkotlin/UByte;", "JsonPrimitive-7apg3OU", "(B)Lkotlinx/serialization/json/JsonPrimitive;", "Lkotlin/UInt;", "JsonPrimitive-WZ4Q5Ns", "(I)Lkotlinx/serialization/json/JsonPrimitive;", "Lkotlin/ULong;", "JsonPrimitive-VKZWuLQ", "(J)Lkotlinx/serialization/json/JsonPrimitive;", "Lkotlin/UShort;", "JsonPrimitive-xj2QHRw", "(S)Lkotlinx/serialization/json/JsonPrimitive;", "JsonUnquotedLiteral", "unexpectedJson", "key", "expected", "error", "element", "kotlinx-serialization-json"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: JsonElement.kt */
public final class JsonElementKt {
    private static final SerialDescriptor jsonUnquotedLiteralDescriptor = InlineClassDescriptorKt.InlinePrimitiveDescriptor("kotlinx.serialization.json.JsonUnquotedLiteral", BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE));

    public static /* synthetic */ void getJsonUnquotedLiteralDescriptor$annotations() {
    }

    public static final JsonPrimitive JsonPrimitive(Boolean bool) {
        if (bool == null) {
            return JsonNull.INSTANCE;
        }
        return new JsonLiteral(bool, false, (SerialDescriptor) null, 4, (DefaultConstructorMarker) null);
    }

    public static final JsonPrimitive JsonPrimitive(Number number) {
        if (number == null) {
            return JsonNull.INSTANCE;
        }
        return new JsonLiteral(number, false, (SerialDescriptor) null, 4, (DefaultConstructorMarker) null);
    }

    @ExperimentalSerializationApi
    /* renamed from: JsonPrimitive-7apg3OU  reason: not valid java name */
    public static final JsonPrimitive m1909JsonPrimitive7apg3OU(byte b) {
        return m1910JsonPrimitiveVKZWuLQ(ULong.m2618constructorimpl(((long) b) & 255));
    }

    @ExperimentalSerializationApi
    /* renamed from: JsonPrimitive-xj2QHRw  reason: not valid java name */
    public static final JsonPrimitive m1912JsonPrimitivexj2QHRw(short s) {
        return m1910JsonPrimitiveVKZWuLQ(ULong.m2618constructorimpl(((long) s) & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    @ExperimentalSerializationApi
    /* renamed from: JsonPrimitive-WZ4Q5Ns  reason: not valid java name */
    public static final JsonPrimitive m1911JsonPrimitiveWZ4Q5Ns(int i) {
        return m1910JsonPrimitiveVKZWuLQ(ULong.m2618constructorimpl(((long) i) & MuxerUtil.UNSIGNED_INT_MAX_VALUE));
    }

    @ExperimentalSerializationApi
    /* renamed from: JsonPrimitive-VKZWuLQ  reason: not valid java name */
    public static final JsonPrimitive m1910JsonPrimitiveVKZWuLQ(long j) {
        return JsonUnquotedLiteral(Long.toUnsignedString(j));
    }

    public static final JsonPrimitive JsonPrimitive(String str) {
        if (str == null) {
            return JsonNull.INSTANCE;
        }
        return new JsonLiteral(str, true, (SerialDescriptor) null, 4, (DefaultConstructorMarker) null);
    }

    @ExperimentalSerializationApi
    public static final JsonNull JsonPrimitive(Void voidR) {
        return JsonNull.INSTANCE;
    }

    @ExperimentalSerializationApi
    public static final JsonPrimitive JsonUnquotedLiteral(String str) {
        if (str == null) {
            return JsonNull.INSTANCE;
        }
        if (!Intrinsics.areEqual((Object) str, (Object) JsonNull.INSTANCE.getContent())) {
            return new JsonLiteral(str, false, jsonUnquotedLiteralDescriptor);
        }
        throw new JsonEncodingException("Creating a literal unquoted value of 'null' is forbidden. If you want to create JSON null literal, use JsonNull object, otherwise, use JsonPrimitive");
    }

    public static final SerialDescriptor getJsonUnquotedLiteralDescriptor() {
        return jsonUnquotedLiteralDescriptor;
    }

    public static final JsonPrimitive getJsonPrimitive(JsonElement jsonElement) {
        Intrinsics.checkNotNullParameter(jsonElement, "<this>");
        JsonPrimitive jsonPrimitive = jsonElement instanceof JsonPrimitive ? (JsonPrimitive) jsonElement : null;
        if (jsonPrimitive != null) {
            return jsonPrimitive;
        }
        error(jsonElement, "JsonPrimitive");
        throw new KotlinNothingValueException();
    }

    public static final JsonObject getJsonObject(JsonElement jsonElement) {
        Intrinsics.checkNotNullParameter(jsonElement, "<this>");
        JsonObject jsonObject = jsonElement instanceof JsonObject ? (JsonObject) jsonElement : null;
        if (jsonObject != null) {
            return jsonObject;
        }
        error(jsonElement, "JsonObject");
        throw new KotlinNothingValueException();
    }

    public static final JsonArray getJsonArray(JsonElement jsonElement) {
        Intrinsics.checkNotNullParameter(jsonElement, "<this>");
        JsonArray jsonArray = jsonElement instanceof JsonArray ? (JsonArray) jsonElement : null;
        if (jsonArray != null) {
            return jsonArray;
        }
        error(jsonElement, "JsonArray");
        throw new KotlinNothingValueException();
    }

    public static final JsonNull getJsonNull(JsonElement jsonElement) {
        Intrinsics.checkNotNullParameter(jsonElement, "<this>");
        JsonNull jsonNull = jsonElement instanceof JsonNull ? (JsonNull) jsonElement : null;
        if (jsonNull != null) {
            return jsonNull;
        }
        error(jsonElement, "JsonNull");
        throw new KotlinNothingValueException();
    }

    public static final int getInt(JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return Integer.parseInt(jsonPrimitive.getContent());
    }

    public static final Integer getIntOrNull(JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return StringsKt.toIntOrNull(jsonPrimitive.getContent());
    }

    public static final long getLong(JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return Long.parseLong(jsonPrimitive.getContent());
    }

    public static final Long getLongOrNull(JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return StringsKt.toLongOrNull(jsonPrimitive.getContent());
    }

    public static final double getDouble(JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return Double.parseDouble(jsonPrimitive.getContent());
    }

    public static final Double getDoubleOrNull(JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return StringsKt.toDoubleOrNull(jsonPrimitive.getContent());
    }

    public static final float getFloat(JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return Float.parseFloat(jsonPrimitive.getContent());
    }

    public static final Float getFloatOrNull(JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return StringsKt.toFloatOrNull(jsonPrimitive.getContent());
    }

    public static final boolean getBoolean(JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        Boolean booleanStrictOrNull = StringOpsKt.toBooleanStrictOrNull(jsonPrimitive.getContent());
        if (booleanStrictOrNull != null) {
            return booleanStrictOrNull.booleanValue();
        }
        throw new IllegalStateException(jsonPrimitive + " does not represent a Boolean");
    }

    public static final Boolean getBooleanOrNull(JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return StringOpsKt.toBooleanStrictOrNull(jsonPrimitive.getContent());
    }

    public static final String getContentOrNull(JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        if (jsonPrimitive instanceof JsonNull) {
            return null;
        }
        return jsonPrimitive.getContent();
    }

    private static final Void error(JsonElement jsonElement, String str) {
        throw new IllegalArgumentException("Element " + Reflection.getOrCreateKotlinClass(jsonElement.getClass()) + " is not a " + str);
    }

    public static final Void unexpectedJson(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(str2, "expected");
        throw new IllegalArgumentException("Element " + str + " is not a " + str2);
    }
}
