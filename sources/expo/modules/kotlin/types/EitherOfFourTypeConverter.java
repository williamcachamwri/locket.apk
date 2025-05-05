package expo.modules.kotlin.types;

import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.jni.ExpectedType;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u0002*\b\b\u0002\u0010\u0004*\u00020\u0002*\b\b\u0003\u0010\u0005*\u00020\u00022 \u0012\u001c\u0012\u001a\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00070\u0006B\u0015\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ2\u0010\u001b\u001a\u001a\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00072\u0006\u0010\u001c\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u000fH\u0016R\u000e\u0010\r\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u0011X\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lexpo/modules/kotlin/types/EitherOfFourTypeConverter;", "FirstType", "", "SecondType", "ThirdType", "FourthType", "Lexpo/modules/kotlin/types/NullAwareTypeConverter;", "Lexpo/modules/kotlin/types/EitherOfFour;", "converterProvider", "Lexpo/modules/kotlin/types/TypeConverterProvider;", "eitherType", "Lkotlin/reflect/KType;", "(Lexpo/modules/kotlin/types/TypeConverterProvider;Lkotlin/reflect/KType;)V", "firstJavaType", "firstType", "Lexpo/modules/kotlin/jni/ExpectedType;", "firstTypeConverter", "Lexpo/modules/kotlin/types/TypeConverter;", "fourthJavaType", "fourthType", "fourthTypeConverter", "secondJavaType", "secondType", "secondTypeConverter", "thirdJavaType", "thirdType", "thirdTypeConverter", "convertNonOptional", "value", "context", "Lexpo/modules/kotlin/AppContext;", "getCppRequiredTypes", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: EitherTypeConverter.kt */
public final class EitherOfFourTypeConverter<FirstType, SecondType, ThirdType, FourthType> extends NullAwareTypeConverter<EitherOfFour<FirstType, SecondType, ThirdType, FourthType>> {
    private final KType firstJavaType;
    private final ExpectedType firstType;
    private final TypeConverter<?> firstTypeConverter;
    private final KType fourthJavaType;
    private final ExpectedType fourthType;
    private final TypeConverter<?> fourthTypeConverter;
    private final KType secondJavaType;
    private final ExpectedType secondType;
    private final TypeConverter<?> secondTypeConverter;
    private final KType thirdJavaType;
    private final ExpectedType thirdType;
    private final TypeConverter<?> thirdTypeConverter;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EitherOfFourTypeConverter(TypeConverterProvider typeConverterProvider, KType kType) {
        super(kType.isMarkedNullable());
        Intrinsics.checkNotNullParameter(typeConverterProvider, "converterProvider");
        Intrinsics.checkNotNullParameter(kType, "eitherType");
        KTypeProjection kTypeProjection = (KTypeProjection) CollectionsKt.getOrNull(kType.getArguments(), 0);
        KType kType2 = null;
        KType type = kTypeProjection != null ? kTypeProjection.getType() : null;
        if (type != null) {
            this.firstJavaType = type;
            KTypeProjection kTypeProjection2 = (KTypeProjection) CollectionsKt.getOrNull(kType.getArguments(), 1);
            KType type2 = kTypeProjection2 != null ? kTypeProjection2.getType() : null;
            if (type2 != null) {
                this.secondJavaType = type2;
                KTypeProjection kTypeProjection3 = (KTypeProjection) CollectionsKt.getOrNull(kType.getArguments(), 2);
                KType type3 = kTypeProjection3 != null ? kTypeProjection3.getType() : null;
                if (type3 != null) {
                    this.thirdJavaType = type3;
                    KTypeProjection kTypeProjection4 = (KTypeProjection) CollectionsKt.getOrNull(kType.getArguments(), 3);
                    kType2 = kTypeProjection4 != null ? kTypeProjection4.getType() : kType2;
                    if (kType2 != null) {
                        this.fourthJavaType = kType2;
                        TypeConverter<?> obtainTypeConverter = typeConverterProvider.obtainTypeConverter(type);
                        this.firstTypeConverter = obtainTypeConverter;
                        TypeConverter<?> obtainTypeConverter2 = typeConverterProvider.obtainTypeConverter(type2);
                        this.secondTypeConverter = obtainTypeConverter2;
                        TypeConverter<?> obtainTypeConverter3 = typeConverterProvider.obtainTypeConverter(type3);
                        this.thirdTypeConverter = obtainTypeConverter3;
                        TypeConverter<?> obtainTypeConverter4 = typeConverterProvider.obtainTypeConverter(kType2);
                        this.fourthTypeConverter = obtainTypeConverter4;
                        this.firstType = obtainTypeConverter.getCppRequiredTypes();
                        this.secondType = obtainTypeConverter2.getCppRequiredTypes();
                        this.thirdType = obtainTypeConverter3.getCppRequiredTypes();
                        this.fourthType = obtainTypeConverter4.getCppRequiredTypes();
                        return;
                    }
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    public EitherOfFour<FirstType, SecondType, ThirdType, FourthType> convertNonOptional(Object obj, AppContext appContext) {
        Intrinsics.checkNotNullParameter(obj, "value");
        return new EitherOfFour<>(obj, CollectionsKt.toMutableList(EitherTypeConverterKt.createDeferredValues(obj, appContext, CollectionsKt.listOf(TuplesKt.to(this.firstType, this.firstTypeConverter), TuplesKt.to(this.secondType, this.secondTypeConverter), TuplesKt.to(this.thirdType, this.thirdTypeConverter), TuplesKt.to(this.fourthType, this.fourthTypeConverter)), CollectionsKt.listOf(this.firstJavaType, this.secondJavaType, this.thirdJavaType, this.fourthJavaType))), CollectionsKt.listOf(this.firstJavaType, this.secondJavaType, this.thirdJavaType, this.fourthJavaType));
    }

    public ExpectedType getCppRequiredTypes() {
        return this.firstType.plus(this.secondType).plus(this.thirdType);
    }
}
