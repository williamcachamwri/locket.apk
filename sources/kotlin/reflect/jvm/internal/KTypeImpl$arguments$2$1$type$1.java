package kotlin.reflect.jvm.internal;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<no name provided>", "Ljava/lang/reflect/Type;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: KTypeImpl.kt */
final class KTypeImpl$arguments$2$1$type$1 extends Lambda implements Function0<Type> {
    final /* synthetic */ int $i;
    final /* synthetic */ Lazy<List<Type>> $parameterizedTypeArguments$delegate;
    final /* synthetic */ KTypeImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KTypeImpl$arguments$2$1$type$1(KTypeImpl kTypeImpl, int i, Lazy<? extends List<? extends Type>> lazy) {
        super(0);
        this.this$0 = kTypeImpl;
        this.$i = i;
        this.$parameterizedTypeArguments$delegate = lazy;
    }

    public final Type invoke() {
        Type javaType = this.this$0.getJavaType();
        if (javaType instanceof Class) {
            Class cls = (Class) javaType;
            Class componentType = cls.isArray() ? cls.getComponentType() : Object.class;
            Intrinsics.checkNotNull(componentType);
            return componentType;
        } else if (javaType instanceof GenericArrayType) {
            if (this.$i == 0) {
                Type genericComponentType = ((GenericArrayType) javaType).getGenericComponentType();
                Intrinsics.checkNotNull(genericComponentType);
                return genericComponentType;
            }
            throw new KotlinReflectionInternalError("Array type has been queried for a non-0th argument: " + this.this$0);
        } else if (javaType instanceof ParameterizedType) {
            Type type = (Type) KTypeImpl$arguments$2.invoke$lambda$0(this.$parameterizedTypeArguments$delegate).get(this.$i);
            if (type instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type;
                Type[] lowerBounds = wildcardType.getLowerBounds();
                Intrinsics.checkNotNullExpressionValue(lowerBounds, "getLowerBounds(...)");
                Type type2 = (Type) ArraysKt.firstOrNull((T[]) (Object[]) lowerBounds);
                if (type2 == null) {
                    Type[] upperBounds = wildcardType.getUpperBounds();
                    Intrinsics.checkNotNullExpressionValue(upperBounds, "getUpperBounds(...)");
                    type = (Type) ArraysKt.first((T[]) (Object[]) upperBounds);
                } else {
                    type = type2;
                }
            }
            Intrinsics.checkNotNull(type);
            return type;
        } else {
            throw new KotlinReflectionInternalError("Non-generic type has been queried for arguments: " + this.this$0);
        }
    }
}
