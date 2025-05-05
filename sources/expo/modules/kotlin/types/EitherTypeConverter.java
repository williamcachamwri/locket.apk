package expo.modules.kotlin.types;

import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.jni.ExpectedType;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00050\u0004B\u0015\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ&\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010\u0014\u001a\u00020\u00022\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0017\u001a\u00020\rH\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u000e\u0010\u000b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u000fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lexpo/modules/kotlin/types/EitherTypeConverter;", "FirstType", "", "SecondType", "Lexpo/modules/kotlin/types/NullAwareTypeConverter;", "Lexpo/modules/kotlin/types/Either;", "converterProvider", "Lexpo/modules/kotlin/types/TypeConverterProvider;", "eitherType", "Lkotlin/reflect/KType;", "(Lexpo/modules/kotlin/types/TypeConverterProvider;Lkotlin/reflect/KType;)V", "firstJavaType", "firstType", "Lexpo/modules/kotlin/jni/ExpectedType;", "firstTypeConverter", "Lexpo/modules/kotlin/types/TypeConverter;", "secondJavaType", "secondType", "secondTypeConverter", "convertNonOptional", "value", "context", "Lexpo/modules/kotlin/AppContext;", "getCppRequiredTypes", "isTrivial", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: EitherTypeConverter.kt */
public final class EitherTypeConverter<FirstType, SecondType> extends NullAwareTypeConverter<Either<FirstType, SecondType>> {
    private final KType firstJavaType;
    private final ExpectedType firstType;
    private final TypeConverter<?> firstTypeConverter;
    private final KType secondJavaType;
    private final ExpectedType secondType;
    private final TypeConverter<?> secondTypeConverter;

    public boolean isTrivial() {
        return false;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EitherTypeConverter(TypeConverterProvider typeConverterProvider, KType kType) {
        super(kType.isMarkedNullable());
        Intrinsics.checkNotNullParameter(typeConverterProvider, "converterProvider");
        Intrinsics.checkNotNullParameter(kType, "eitherType");
        KTypeProjection kTypeProjection = (KTypeProjection) CollectionsKt.getOrNull(kType.getArguments(), 0);
        KType kType2 = null;
        KType type = kTypeProjection != null ? kTypeProjection.getType() : null;
        if (type != null) {
            this.firstJavaType = type;
            KTypeProjection kTypeProjection2 = (KTypeProjection) CollectionsKt.getOrNull(kType.getArguments(), 1);
            kType2 = kTypeProjection2 != null ? kTypeProjection2.getType() : kType2;
            if (kType2 != null) {
                this.secondJavaType = kType2;
                TypeConverter<?> obtainTypeConverter = typeConverterProvider.obtainTypeConverter(type);
                this.firstTypeConverter = obtainTypeConverter;
                TypeConverter<?> obtainTypeConverter2 = typeConverterProvider.obtainTypeConverter(kType2);
                this.secondTypeConverter = obtainTypeConverter2;
                this.firstType = obtainTypeConverter.getCppRequiredTypes();
                this.secondType = obtainTypeConverter2.getCppRequiredTypes();
                return;
            }
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    public Either<FirstType, SecondType> convertNonOptional(Object obj, AppContext appContext) {
        Intrinsics.checkNotNullParameter(obj, "value");
        List listOf = CollectionsKt.listOf(this.firstJavaType, this.secondJavaType);
        return new Either<>(obj, CollectionsKt.toMutableList(EitherTypeConverterKt.createDeferredValues(obj, appContext, CollectionsKt.listOf(TuplesKt.to(this.firstType, this.firstTypeConverter), TuplesKt.to(this.secondType, this.secondTypeConverter)), listOf)), listOf);
    }

    public ExpectedType getCppRequiredTypes() {
        return this.firstType.plus(this.secondType);
    }
}
