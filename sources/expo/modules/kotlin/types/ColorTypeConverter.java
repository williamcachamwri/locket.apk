package expo.modules.kotlin.types;

import android.graphics.Color;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableType;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.CppType;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.SingleType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0004H\u0016¨\u0006\u0014"}, d2 = {"Lexpo/modules/kotlin/types/ColorTypeConverter;", "Lexpo/modules/kotlin/types/DynamicAwareTypeConverters;", "Landroid/graphics/Color;", "isOptional", "", "(Z)V", "colorFromDoubleArray", "value", "", "colorFromInt", "", "colorFromString", "", "convertFromAny", "", "convertFromDynamic", "Lcom/facebook/react/bridge/Dynamic;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "isTrivial", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ColorTypeConverter.kt */
public final class ColorTypeConverter extends DynamicAwareTypeConverters<Color> {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ColorTypeConverter.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                com.facebook.react.bridge.ReadableType[] r0 = com.facebook.react.bridge.ReadableType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.String     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Array     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.types.ColorTypeConverter.WhenMappings.<clinit>():void");
        }
    }

    public boolean isTrivial() {
        return false;
    }

    public ColorTypeConverter(boolean z) {
        super(z);
    }

    public Color convertFromDynamic(Dynamic dynamic) {
        Intrinsics.checkNotNullParameter(dynamic, "value");
        ReadableType type = dynamic.getType();
        int i = type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        if (i == 1) {
            return colorFromInt((int) dynamic.asDouble());
        }
        if (i == 2) {
            String asString = dynamic.asString();
            Intrinsics.checkNotNullExpressionValue(asString, "asString(...)");
            return colorFromString(asString);
        } else if (i == 3) {
            ArrayList<Object> arrayList = dynamic.asArray().toArrayList();
            Intrinsics.checkNotNullExpressionValue(arrayList, "toArrayList(...)");
            Iterable iterable = arrayList;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (Object next : iterable) {
                Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlin.Double");
                arrayList2.add(Double.valueOf(((Double) next).doubleValue()));
            }
            return colorFromDoubleArray(CollectionsKt.toDoubleArray((List) arrayList2));
        } else {
            throw new UnexpectedException("Unknown argument type: " + dynamic.getType());
        }
    }

    public Color convertFromAny(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "value");
        if (obj instanceof Integer) {
            return colorFromInt(((Number) obj).intValue());
        }
        if (obj instanceof String) {
            return colorFromString((String) obj);
        }
        if (obj instanceof double[]) {
            return colorFromDoubleArray((double[]) obj);
        }
        throw new UnexpectedException("Unknown argument type: " + Reflection.getOrCreateKotlinClass(obj.getClass()));
    }

    private final Color colorFromDoubleArray(double[] dArr) {
        Double orNull = ArraysKt.getOrNull(dArr, 3);
        Color valueOf = Color.valueOf((float) dArr[0], (float) dArr[1], (float) dArr[2], (float) (orNull != null ? orNull.doubleValue() : 1.0d));
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
        return valueOf;
    }

    private final Color colorFromInt(int i) {
        Color valueOf = Color.valueOf(i);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
        return valueOf;
    }

    private final Color colorFromString(String str) {
        List list = (List) ColorTypeConverterKt.namedColors.get(str);
        if (list != null) {
            Color valueOf = Color.valueOf(((Number) list.get(0)).floatValue(), ((Number) list.get(1)).floatValue(), ((Number) list.get(2)).floatValue(), ((Number) list.get(3)).floatValue());
            Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
            return valueOf;
        }
        Color valueOf2 = Color.valueOf(Color.parseColor(str));
        Intrinsics.checkNotNullExpressionValue(valueOf2, "valueOf(...)");
        return valueOf2;
    }

    public ExpectedType getCppRequiredTypes() {
        return new ExpectedType(new SingleType(CppType.INT, (ExpectedType[]) null, 2, (DefaultConstructorMarker) null), new SingleType(CppType.STRING, (ExpectedType[]) null, 2, (DefaultConstructorMarker) null), new SingleType(CppType.PRIMITIVE_ARRAY, new ExpectedType[]{new ExpectedType(CppType.DOUBLE)}));
    }
}
