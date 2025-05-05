package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TypeSubstitution.kt */
public abstract class TypeConstructorSubstitution extends TypeSubstitution {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @JvmStatic
    public static final TypeSubstitution create(TypeConstructor typeConstructor, List<? extends TypeProjection> list) {
        return Companion.create(typeConstructor, list);
    }

    @JvmStatic
    public static final TypeConstructorSubstitution createByConstructorsMap(Map<TypeConstructor, ? extends TypeProjection> map) {
        return Companion.createByConstructorsMap(map);
    }

    public abstract TypeProjection get(TypeConstructor typeConstructor);

    public TypeProjection get(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "key");
        return get(kotlinType.getConstructor());
    }

    /* compiled from: TypeSubstitution.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final TypeConstructorSubstitution createByConstructorsMap(Map<TypeConstructor, ? extends TypeProjection> map) {
            Intrinsics.checkNotNullParameter(map, "map");
            return createByConstructorsMap$default(this, map, false, 2, (Object) null);
        }

        private Companion() {
        }

        public static /* synthetic */ TypeConstructorSubstitution createByConstructorsMap$default(Companion companion, Map map, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            return companion.createByConstructorsMap(map, z);
        }

        @JvmStatic
        public final TypeConstructorSubstitution createByConstructorsMap(Map<TypeConstructor, ? extends TypeProjection> map, boolean z) {
            Intrinsics.checkNotNullParameter(map, "map");
            return new TypeConstructorSubstitution$Companion$createByConstructorsMap$1(map, z);
        }

        @JvmStatic
        public final TypeSubstitution create(KotlinType kotlinType) {
            Intrinsics.checkNotNullParameter(kotlinType, "kotlinType");
            return create(kotlinType.getConstructor(), kotlinType.getArguments());
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0021, code lost:
            if (r2.isCapturedFromOuterDeclaration() == true) goto L_0x0025;
         */
        @kotlin.jvm.JvmStatic
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final kotlin.reflect.jvm.internal.impl.types.TypeSubstitution create(kotlin.reflect.jvm.internal.impl.types.TypeConstructor r6, java.util.List<? extends kotlin.reflect.jvm.internal.impl.types.TypeProjection> r7) {
            /*
                r5 = this;
                java.lang.String r0 = "typeConstructor"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                java.lang.String r0 = "arguments"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
                java.util.List r0 = r6.getParameters()
                java.lang.String r1 = "getParameters(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                java.lang.Object r2 = kotlin.collections.CollectionsKt.lastOrNull(r0)
                kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r2 = (kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor) r2
                r3 = 0
                if (r2 == 0) goto L_0x0024
                boolean r2 = r2.isCapturedFromOuterDeclaration()
                r4 = 1
                if (r2 != r4) goto L_0x0024
                goto L_0x0025
            L_0x0024:
                r4 = r3
            L_0x0025:
                if (r4 == 0) goto L_0x006e
                java.util.List r6 = r6.getParameters()
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r1)
                java.lang.Iterable r6 = (java.lang.Iterable) r6
                java.util.ArrayList r0 = new java.util.ArrayList
                r1 = 10
                int r1 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r6, r1)
                r0.<init>(r1)
                java.util.Collection r0 = (java.util.Collection) r0
                java.util.Iterator r6 = r6.iterator()
            L_0x0041:
                boolean r1 = r6.hasNext()
                if (r1 == 0) goto L_0x0055
                java.lang.Object r1 = r6.next()
                kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r1 = (kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor) r1
                kotlin.reflect.jvm.internal.impl.types.TypeConstructor r1 = r1.getTypeConstructor()
                r0.add(r1)
                goto L_0x0041
            L_0x0055:
                java.util.List r0 = (java.util.List) r0
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                java.lang.Iterable r7 = (java.lang.Iterable) r7
                java.util.List r6 = kotlin.collections.CollectionsKt.zip(r0, r7)
                java.lang.Iterable r6 = (java.lang.Iterable) r6
                java.util.Map r6 = kotlin.collections.MapsKt.toMap(r6)
                r7 = 2
                r0 = 0
                kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution r6 = createByConstructorsMap$default(r5, r6, r3, r7, r0)
                kotlin.reflect.jvm.internal.impl.types.TypeSubstitution r6 = (kotlin.reflect.jvm.internal.impl.types.TypeSubstitution) r6
                return r6
            L_0x006e:
                kotlin.reflect.jvm.internal.impl.types.IndexedParametersSubstitution r6 = new kotlin.reflect.jvm.internal.impl.types.IndexedParametersSubstitution
                r6.<init>(r0, r7)
                kotlin.reflect.jvm.internal.impl.types.TypeSubstitution r6 = (kotlin.reflect.jvm.internal.impl.types.TypeSubstitution) r6
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution.Companion.create(kotlin.reflect.jvm.internal.impl.types.TypeConstructor, java.util.List):kotlin.reflect.jvm.internal.impl.types.TypeSubstitution");
        }
    }
}
