package expo.modules.kotlin.types;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.CollectionElementCastException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.ExpectedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0014\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0014\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u000b\u001a\u00020\u000eH\u0016J\u0014\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0012\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lexpo/modules/kotlin/types/SetTypeConverter;", "Lexpo/modules/kotlin/types/DynamicAwareTypeConverters;", "", "converterProvider", "Lexpo/modules/kotlin/types/TypeConverterProvider;", "setType", "Lkotlin/reflect/KType;", "(Lexpo/modules/kotlin/types/TypeConverterProvider;Lkotlin/reflect/KType;)V", "elementConverter", "Lexpo/modules/kotlin/types/TypeConverter;", "convertFromAny", "value", "", "convertFromDynamic", "Lcom/facebook/react/bridge/Dynamic;", "convertFromReadableArray", "jsArray", "Lcom/facebook/react/bridge/ReadableArray;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "isTrivial", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SetTypeConverter.kt */
public final class SetTypeConverter extends DynamicAwareTypeConverters<Set<?>> {
    private final TypeConverter<?> elementConverter;
    private final KType setType;

    public boolean isTrivial() {
        return false;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SetTypeConverter(TypeConverterProvider typeConverterProvider, KType kType) {
        super(kType.isMarkedNullable());
        Intrinsics.checkNotNullParameter(typeConverterProvider, "converterProvider");
        Intrinsics.checkNotNullParameter(kType, "setType");
        this.setType = kType;
        KType type = ((KTypeProjection) CollectionsKt.first(kType.getArguments())).getType();
        if (type != null) {
            this.elementConverter = typeConverterProvider.obtainTypeConverter(type);
            return;
        }
        throw new IllegalArgumentException("The set type should contain the type of elements.".toString());
    }

    public Set<?> convertFromDynamic(Dynamic dynamic) {
        Intrinsics.checkNotNullParameter(dynamic, "value");
        ReadableArray asArray = dynamic.asArray();
        Intrinsics.checkNotNull(asArray);
        return convertFromReadableArray(asArray);
    }

    public Set<?> convertFromAny(Object obj) {
        CodedException codedException;
        Intrinsics.checkNotNullParameter(obj, "value");
        if (this.elementConverter.isTrivial()) {
            return CollectionsKt.toSet((List) obj);
        }
        Iterable iterable = (List) obj;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Object next : iterable) {
            try {
                arrayList.add(TypeConverter.convert$default(this.elementConverter, next, (AppContext) null, 2, (Object) null));
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
                KType kType = this.setType;
                KType type = ((KTypeProjection) CollectionsKt.first(kType.getArguments())).getType();
                Intrinsics.checkNotNull(type);
                Intrinsics.checkNotNull(next);
                throw new CollectionElementCastException(kType, type, (KClass<?>) Reflection.getOrCreateKotlinClass(next.getClass()), codedException);
            }
        }
        return CollectionsKt.toSet((List) arrayList);
    }

    private final Set<?> convertFromReadableArray(ReadableArray readableArray) {
        CodedException codedException;
        int size = readableArray.size();
        ArrayList arrayList = new ArrayList(size);
        int i = 0;
        while (i < size) {
            Dynamic dynamic = readableArray.getDynamic(i);
            Intrinsics.checkNotNullExpressionValue(dynamic, "getDynamic(...)");
            try {
                Object convert$default = TypeConverter.convert$default(this.elementConverter, dynamic, (AppContext) null, 2, (Object) null);
                dynamic.recycle();
                arrayList.add(convert$default);
                i++;
            } catch (Throwable th) {
                dynamic.recycle();
                throw th;
            }
        }
        return CollectionsKt.toSet(arrayList);
    }

    public ExpectedType getCppRequiredTypes() {
        return ExpectedType.Companion.forList(this.elementConverter.getCppRequiredTypes());
    }
}
