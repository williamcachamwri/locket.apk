package expo.modules.kotlin.types;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableMap;
import expo.modules.kotlin.jni.ExpectedType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0015\u0010\u0002\u001a\u00028\u00002\u0006\u0010\u0003\u001a\u00020\u0004H\u0016¢\u0006\u0002\u0010\u0005J\u0015\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0003\u001a\u00020\u0007H\u0016¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b¸\u0006\u0000"}, d2 = {"expo/modules/kotlin/types/TypeConverterKt$createTrivialTypeConverter$2", "Lexpo/modules/kotlin/types/DynamicAwareTypeConverters;", "convertFromAny", "value", "", "(Ljava/lang/Object;)Ljava/lang/Object;", "convertFromDynamic", "Lcom/facebook/react/bridge/Dynamic;", "(Lcom/facebook/react/bridge/Dynamic;)Ljava/lang/Object;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TypeConverter.kt */
public final class TypeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$8 extends DynamicAwareTypeConverters<ReadableMap> {
    final /* synthetic */ ExpectedType $cppRequireType;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TypeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$8(boolean z, ExpectedType expectedType) {
        super(z);
        this.$cppRequireType = expectedType;
    }

    public ExpectedType getCppRequiredTypes() {
        return this.$cppRequireType;
    }

    public ReadableMap convertFromAny(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "value");
        return (ReadableMap) obj;
    }

    public ReadableMap convertFromDynamic(Dynamic dynamic) {
        Intrinsics.checkNotNullParameter(dynamic, "value");
        return dynamic.asMap();
    }
}
