package expo.modules.kotlin.types;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.bridge.ReadableMap;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.CollectionElementCastException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.ExpectedType;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\n\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00022\u0006\u0010\u000b\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lexpo/modules/kotlin/types/MapTypeConverter;", "Lexpo/modules/kotlin/types/DynamicAwareTypeConverters;", "", "converterProvider", "Lexpo/modules/kotlin/types/TypeConverterProvider;", "mapType", "Lkotlin/reflect/KType;", "(Lexpo/modules/kotlin/types/TypeConverterProvider;Lkotlin/reflect/KType;)V", "valueConverter", "Lexpo/modules/kotlin/types/TypeConverter;", "convertFromAny", "value", "", "convertFromDynamic", "Lcom/facebook/react/bridge/Dynamic;", "convertFromReadableMap", "jsMap", "Lcom/facebook/react/bridge/ReadableMap;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "isTrivial", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MapTypeConverter.kt */
public final class MapTypeConverter extends DynamicAwareTypeConverters<Map<?, ?>> {
    private final KType mapType;
    private final TypeConverter<?> valueConverter;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MapTypeConverter(TypeConverterProvider typeConverterProvider, KType kType) {
        super(kType.isMarkedNullable());
        Intrinsics.checkNotNullParameter(typeConverterProvider, "converterProvider");
        Intrinsics.checkNotNullParameter(kType, "mapType");
        this.mapType = kType;
        KType type = ((KTypeProjection) CollectionsKt.first(kType.getArguments())).getType();
        KType kType2 = null;
        if (Intrinsics.areEqual((Object) type != null ? type.getClassifier() : null, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            KTypeProjection kTypeProjection = (KTypeProjection) CollectionsKt.getOrNull(kType.getArguments(), 1);
            kType2 = kTypeProjection != null ? kTypeProjection.getType() : kType2;
            if (kType2 != null) {
                this.valueConverter = typeConverterProvider.obtainTypeConverter(kType2);
                return;
            }
            throw new IllegalArgumentException("The map type should contain the key type.".toString());
        }
        throw new IllegalArgumentException(("The map key type should be String, but received " + CollectionsKt.first(kType.getArguments()) + ".").toString());
    }

    public Map<?, ?> convertFromDynamic(Dynamic dynamic) {
        Intrinsics.checkNotNullParameter(dynamic, "value");
        ReadableMap asMap = dynamic.asMap();
        Intrinsics.checkNotNull(asMap);
        return convertFromReadableMap(asMap);
    }

    public Map<?, ?> convertFromAny(Object obj) {
        CodedException codedException;
        Intrinsics.checkNotNullParameter(obj, "value");
        if (this.valueConverter.isTrivial()) {
            return (Map) obj;
        }
        Map map = (Map) obj;
        Map<?, ?> linkedHashMap = new LinkedHashMap<>(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            try {
                linkedHashMap.put(key, TypeConverter.convert$default(this.valueConverter, value, (AppContext) null, 2, (Object) null));
            } catch (Throwable th) {
                if (th instanceof CodedException) {
                    codedException = th;
                } else if (th instanceof expo.modules.core.errors.CodedException) {
                    String code = th.getCode();
                    Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
                    codedException = new CodedException(code, th.getMessage(), th.getCause());
                } else {
                    codedException = new UnexpectedException((Throwable) th);
                }
                KType kType = this.mapType;
                KType type = kType.getArguments().get(1).getType();
                Intrinsics.checkNotNull(type);
                Intrinsics.checkNotNull(value);
                throw new CollectionElementCastException(kType, type, (KClass<?>) Reflection.getOrCreateKotlinClass(value.getClass()), codedException);
            }
        }
        return linkedHashMap;
    }

    private final Map<?, ?> convertFromReadableMap(ReadableMap readableMap) {
        CodedException codedException;
        Map<?, ?> linkedHashMap = new LinkedHashMap<>();
        Iterator<Map.Entry<String, Object>> entryIterator = readableMap.getEntryIterator();
        Intrinsics.checkNotNullExpressionValue(entryIterator, "getEntryIterator(...)");
        while (entryIterator.hasNext()) {
            Map.Entry next = entryIterator.next();
            Intrinsics.checkNotNull(next);
            String str = (String) next.getKey();
            Dynamic dynamicFromObject = new DynamicFromObject(next.getValue());
            try {
                Intrinsics.checkNotNull(str);
                linkedHashMap.put(str, TypeConverter.convert$default(this.valueConverter, dynamicFromObject, (AppContext) null, 2, (Object) null));
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
                dynamicFromObject.recycle();
            } catch (Throwable th) {
                dynamicFromObject.recycle();
                throw th;
            }
        }
        return linkedHashMap;
    }

    public ExpectedType getCppRequiredTypes() {
        return ExpectedType.Companion.forMap(this.valueConverter.getCppRequiredTypes());
    }

    public boolean isTrivial() {
        return this.valueConverter.isTrivial();
    }
}
