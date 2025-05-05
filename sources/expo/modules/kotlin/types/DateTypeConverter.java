package expo.modules.kotlin.types;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableType;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.CppType;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.SingleType;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0004H\u0016¨\u0006\u0010"}, d2 = {"Lexpo/modules/kotlin/types/DateTypeConverter;", "Lexpo/modules/kotlin/types/DynamicAwareTypeConverters;", "Ljava/time/LocalDate;", "isOptional", "", "(Z)V", "convertFromAny", "value", "", "convertFromDynamic", "Lcom/facebook/react/bridge/Dynamic;", "convertFromLong", "", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "isTrivial", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DateTypeConverter.kt */
public final class DateTypeConverter extends DynamicAwareTypeConverters<LocalDate> {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DateTypeConverter.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.facebook.react.bridge.ReadableType[] r0 = com.facebook.react.bridge.ReadableType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.String     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.types.DateTypeConverter.WhenMappings.<clinit>():void");
        }
    }

    public boolean isTrivial() {
        return false;
    }

    public DateTypeConverter(boolean z) {
        super(z);
    }

    public LocalDate convertFromDynamic(Dynamic dynamic) {
        Intrinsics.checkNotNullParameter(dynamic, "value");
        ReadableType type = dynamic.getType();
        int i = type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        if (i == 1) {
            LocalDate parse = LocalDate.parse(dynamic.asString(), DateTimeFormatter.ISO_DATE_TIME);
            Intrinsics.checkNotNullExpressionValue(parse, "parse(...)");
            return parse;
        } else if (i == 2) {
            return convertFromLong((long) dynamic.asDouble());
        } else {
            throw new UnexpectedException("Unknown argument type: " + dynamic.getType());
        }
    }

    public LocalDate convertFromAny(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "value");
        if (obj instanceof String) {
            LocalDate parse = LocalDate.parse((CharSequence) obj, DateTimeFormatter.ISO_DATE_TIME);
            Intrinsics.checkNotNullExpressionValue(parse, "parse(...)");
            return parse;
        } else if (obj instanceof Long) {
            return convertFromLong(((Number) obj).longValue());
        } else {
            throw new UnexpectedException("Unknown argument type: " + Reflection.getOrCreateKotlinClass(obj.getClass()));
        }
    }

    private final LocalDate convertFromLong(long j) {
        LocalDate localDate = Instant.ofEpochMilli(j).atZone(ZoneId.systemDefault()).toLocalDate();
        Intrinsics.checkNotNullExpressionValue(localDate, "toLocalDate(...)");
        return localDate;
    }

    public ExpectedType getCppRequiredTypes() {
        return new ExpectedType(new SingleType(CppType.INT, (ExpectedType[]) null, 2, (DefaultConstructorMarker) null), new SingleType(CppType.STRING, (ExpectedType[]) null, 2, (DefaultConstructorMarker) null));
    }
}
