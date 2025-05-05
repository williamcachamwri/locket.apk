package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.checker.IntersectionTypeKt;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* compiled from: TypeParameterUpperBoundEraser.kt */
public final class TypeParameterUpperBoundEraser {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Lazy erroneousErasedBound$delegate;
    private final MemoizedFunctionToNotNull<DataToEraseUpperBound, KotlinType> getErasedUpperBound;
    private final TypeParameterErasureOptions options;
    private final ErasureProjectionComputer projectionComputer;
    private final LockBasedStorageManager storage;

    public TypeParameterUpperBoundEraser(ErasureProjectionComputer erasureProjectionComputer, TypeParameterErasureOptions typeParameterErasureOptions) {
        Intrinsics.checkNotNullParameter(erasureProjectionComputer, "projectionComputer");
        Intrinsics.checkNotNullParameter(typeParameterErasureOptions, "options");
        this.projectionComputer = erasureProjectionComputer;
        this.options = typeParameterErasureOptions;
        LockBasedStorageManager lockBasedStorageManager = new LockBasedStorageManager("Type parameter upper bound erasure results");
        this.storage = lockBasedStorageManager;
        this.erroneousErasedBound$delegate = LazyKt.lazy(new TypeParameterUpperBoundEraser$erroneousErasedBound$2(this));
        MemoizedFunctionToNotNull<DataToEraseUpperBound, KotlinType> createMemoizedFunction = lockBasedStorageManager.createMemoizedFunction(new TypeParameterUpperBoundEraser$getErasedUpperBound$1(this));
        Intrinsics.checkNotNullExpressionValue(createMemoizedFunction, "createMemoizedFunction(...)");
        this.getErasedUpperBound = createMemoizedFunction;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TypeParameterUpperBoundEraser(ErasureProjectionComputer erasureProjectionComputer, TypeParameterErasureOptions typeParameterErasureOptions, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(erasureProjectionComputer, (i & 2) != 0 ? new TypeParameterErasureOptions(false, false) : typeParameterErasureOptions);
    }

    private final ErrorType getErroneousErasedBound() {
        return (ErrorType) this.erroneousErasedBound$delegate.getValue();
    }

    /* compiled from: TypeParameterUpperBoundEraser.kt */
    private static final class DataToEraseUpperBound {
        private final ErasureTypeAttributes typeAttr;
        private final TypeParameterDescriptor typeParameter;

        public String toString() {
            return "DataToEraseUpperBound(typeParameter=" + this.typeParameter + ", typeAttr=" + this.typeAttr + ')';
        }

        public DataToEraseUpperBound(TypeParameterDescriptor typeParameterDescriptor, ErasureTypeAttributes erasureTypeAttributes) {
            Intrinsics.checkNotNullParameter(typeParameterDescriptor, "typeParameter");
            Intrinsics.checkNotNullParameter(erasureTypeAttributes, "typeAttr");
            this.typeParameter = typeParameterDescriptor;
            this.typeAttr = erasureTypeAttributes;
        }

        public final TypeParameterDescriptor getTypeParameter() {
            return this.typeParameter;
        }

        public final ErasureTypeAttributes getTypeAttr() {
            return this.typeAttr;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof DataToEraseUpperBound)) {
                return false;
            }
            DataToEraseUpperBound dataToEraseUpperBound = (DataToEraseUpperBound) obj;
            if (!Intrinsics.areEqual((Object) dataToEraseUpperBound.typeParameter, (Object) this.typeParameter) || !Intrinsics.areEqual((Object) dataToEraseUpperBound.typeAttr, (Object) this.typeAttr)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int hashCode = this.typeParameter.hashCode();
            return hashCode + (hashCode * 31) + this.typeAttr.hashCode();
        }
    }

    public final KotlinType getErasedUpperBound(TypeParameterDescriptor typeParameterDescriptor, ErasureTypeAttributes erasureTypeAttributes) {
        Intrinsics.checkNotNullParameter(typeParameterDescriptor, "typeParameter");
        Intrinsics.checkNotNullParameter(erasureTypeAttributes, "typeAttr");
        KotlinType invoke = this.getErasedUpperBound.invoke(new DataToEraseUpperBound(typeParameterDescriptor, erasureTypeAttributes));
        Intrinsics.checkNotNullExpressionValue(invoke, "invoke(...)");
        return invoke;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r1 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.replaceArgumentsWithStarProjections(r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final kotlin.reflect.jvm.internal.impl.types.KotlinType getDefaultType(kotlin.reflect.jvm.internal.impl.types.ErasureTypeAttributes r1) {
        /*
            r0 = this;
            kotlin.reflect.jvm.internal.impl.types.SimpleType r1 = r1.getDefaultType()
            if (r1 == 0) goto L_0x000e
            kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r1
            kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.replaceArgumentsWithStarProjections(r1)
            if (r1 != 0) goto L_0x0014
        L_0x000e:
            kotlin.reflect.jvm.internal.impl.types.error.ErrorType r1 = r0.getErroneousErasedBound()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r1
        L_0x0014:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.TypeParameterUpperBoundEraser.getDefaultType(kotlin.reflect.jvm.internal.impl.types.ErasureTypeAttributes):kotlin.reflect.jvm.internal.impl.types.KotlinType");
    }

    /* access modifiers changed from: private */
    public final KotlinType getErasedUpperBoundInternal(TypeParameterDescriptor typeParameterDescriptor, ErasureTypeAttributes erasureTypeAttributes) {
        TypeProjection typeProjection;
        Set<TypeParameterDescriptor> visitedTypeParameters = erasureTypeAttributes.getVisitedTypeParameters();
        if (visitedTypeParameters != null && visitedTypeParameters.contains(typeParameterDescriptor.getOriginal())) {
            return getDefaultType(erasureTypeAttributes);
        }
        SimpleType defaultType = typeParameterDescriptor.getDefaultType();
        Intrinsics.checkNotNullExpressionValue(defaultType, "getDefaultType(...)");
        Iterable<TypeParameterDescriptor> extractTypeParametersFromUpperBounds = TypeUtilsKt.extractTypeParametersFromUpperBounds(defaultType, visitedTypeParameters);
        Map linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(extractTypeParametersFromUpperBounds, 10)), 16));
        for (TypeParameterDescriptor typeParameterDescriptor2 : extractTypeParametersFromUpperBounds) {
            if (visitedTypeParameters == null || !visitedTypeParameters.contains(typeParameterDescriptor2)) {
                typeProjection = this.projectionComputer.computeProjection(typeParameterDescriptor2, erasureTypeAttributes, this, getErasedUpperBound(typeParameterDescriptor2, erasureTypeAttributes.withNewVisitedTypeParameter(typeParameterDescriptor)));
            } else {
                typeProjection = TypeUtils.makeStarProjection(typeParameterDescriptor2, erasureTypeAttributes);
                Intrinsics.checkNotNullExpressionValue(typeProjection, "makeStarProjection(...)");
            }
            Pair pair = TuplesKt.to(typeParameterDescriptor2.getTypeConstructor(), typeProjection);
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        boolean z = false;
        TypeSubstitutor create = TypeSubstitutor.create((TypeSubstitution) TypeConstructorSubstitution.Companion.createByConstructorsMap$default(TypeConstructorSubstitution.Companion, linkedHashMap, false, 2, (Object) null));
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        List<KotlinType> upperBounds = typeParameterDescriptor.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds, "getUpperBounds(...)");
        Set<KotlinType> substituteErasedUpperBounds = substituteErasedUpperBounds(create, upperBounds, erasureTypeAttributes);
        if (!(!substituteErasedUpperBounds.isEmpty())) {
            return getDefaultType(erasureTypeAttributes);
        }
        if (!this.options.getIntersectUpperBounds()) {
            if (substituteErasedUpperBounds.size() == 1) {
                z = true;
            }
            if (z) {
                return (KotlinType) CollectionsKt.single(substituteErasedUpperBounds);
            }
            throw new IllegalArgumentException("Should only be one computed upper bound if no need to intersect all bounds".toString());
        }
        Iterable<KotlinType> list = CollectionsKt.toList(substituteErasedUpperBounds);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (KotlinType unwrap : list) {
            arrayList.add(unwrap.unwrap());
        }
        return IntersectionTypeKt.intersectTypes((List) arrayList);
    }

    private final Set<KotlinType> substituteErasedUpperBounds(TypeSubstitutor typeSubstitutor, List<? extends KotlinType> list, ErasureTypeAttributes erasureTypeAttributes) {
        Set createSetBuilder = SetsKt.createSetBuilder();
        for (KotlinType kotlinType : list) {
            ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
            if (declarationDescriptor instanceof ClassDescriptor) {
                createSetBuilder.add(Companion.replaceArgumentsOfUpperBound(kotlinType, typeSubstitutor, erasureTypeAttributes.getVisitedTypeParameters(), this.options.getLeaveNonTypeParameterTypes()));
            } else if (declarationDescriptor instanceof TypeParameterDescriptor) {
                Set<TypeParameterDescriptor> visitedTypeParameters = erasureTypeAttributes.getVisitedTypeParameters();
                boolean z = false;
                if (visitedTypeParameters != null && visitedTypeParameters.contains(declarationDescriptor)) {
                    z = true;
                }
                if (z) {
                    createSetBuilder.add(getDefaultType(erasureTypeAttributes));
                } else {
                    List<KotlinType> upperBounds = ((TypeParameterDescriptor) declarationDescriptor).getUpperBounds();
                    Intrinsics.checkNotNullExpressionValue(upperBounds, "getUpperBounds(...)");
                    createSetBuilder.addAll(substituteErasedUpperBounds(typeSubstitutor, upperBounds, erasureTypeAttributes));
                }
            }
            if (!this.options.getIntersectUpperBounds()) {
                break;
            }
        }
        return SetsKt.build(createSetBuilder);
    }

    /* compiled from: TypeParameterUpperBoundEraser.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:100:0x00b8 A[SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:104:0x015a A[SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:108:0x0203 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final kotlin.reflect.jvm.internal.impl.types.KotlinType replaceArgumentsOfUpperBound(kotlin.reflect.jvm.internal.impl.types.KotlinType r17, kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor r18, java.util.Set<? extends kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor> r19, boolean r20) {
            /*
                r16 = this;
                r0 = r18
                r1 = r19
                java.lang.String r2 = "<this>"
                r3 = r17
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r2)
                java.lang.String r2 = "substitutor"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
                kotlin.reflect.jvm.internal.impl.types.UnwrappedType r2 = r17.unwrap()
                boolean r4 = r2 instanceof kotlin.reflect.jvm.internal.impl.types.FlexibleType
                java.lang.String r5 = "getType(...)"
                r6 = 2
                r7 = 10
                java.lang.String r8 = "getParameters(...)"
                r10 = 0
                if (r4 == 0) goto L_0x016a
                r4 = r2
                kotlin.reflect.jvm.internal.impl.types.FlexibleType r4 = (kotlin.reflect.jvm.internal.impl.types.FlexibleType) r4
                kotlin.reflect.jvm.internal.impl.types.SimpleType r12 = r4.getLowerBound()
                kotlin.reflect.jvm.internal.impl.types.TypeConstructor r13 = r12.getConstructor()
                java.util.List r13 = r13.getParameters()
                boolean r13 = r13.isEmpty()
                if (r13 != 0) goto L_0x00c4
                kotlin.reflect.jvm.internal.impl.types.TypeConstructor r13 = r12.getConstructor()
                kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor r13 = r13.getDeclarationDescriptor()
                if (r13 != 0) goto L_0x0041
                goto L_0x00c4
            L_0x0041:
                kotlin.reflect.jvm.internal.impl.types.TypeConstructor r13 = r12.getConstructor()
                java.util.List r13 = r13.getParameters()
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r8)
                java.lang.Iterable r13 = (java.lang.Iterable) r13
                java.util.ArrayList r14 = new java.util.ArrayList
                int r15 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r13, r7)
                r14.<init>(r15)
                java.util.Collection r14 = (java.util.Collection) r14
                java.util.Iterator r13 = r13.iterator()
            L_0x005d:
                boolean r15 = r13.hasNext()
                if (r15 == 0) goto L_0x00be
                java.lang.Object r15 = r13.next()
                kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r15 = (kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor) r15
                java.util.List r9 = r17.getArguments()
                int r11 = r15.getIndex()
                java.lang.Object r9 = kotlin.collections.CollectionsKt.getOrNull(r9, r11)
                kotlin.reflect.jvm.internal.impl.types.TypeProjection r9 = (kotlin.reflect.jvm.internal.impl.types.TypeProjection) r9
                if (r20 == 0) goto L_0x0090
                if (r9 == 0) goto L_0x008c
                kotlin.reflect.jvm.internal.impl.types.KotlinType r11 = r9.getType()
                if (r11 == 0) goto L_0x008c
                kotlin.jvm.internal.Intrinsics.checkNotNull(r11)
                boolean r11 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.containsTypeParameter(r11)
                if (r11 != 0) goto L_0x008c
                r11 = 1
                goto L_0x008d
            L_0x008c:
                r11 = 0
            L_0x008d:
                if (r11 == 0) goto L_0x0090
                goto L_0x00b8
            L_0x0090:
                if (r1 == 0) goto L_0x009a
                boolean r11 = r1.contains(r15)
                if (r11 == 0) goto L_0x009a
                r11 = 1
                goto L_0x009b
            L_0x009a:
                r11 = 0
            L_0x009b:
                if (r9 == 0) goto L_0x00b0
                if (r11 != 0) goto L_0x00b0
                kotlin.reflect.jvm.internal.impl.types.TypeSubstitution r11 = r18.getSubstitution()
                kotlin.reflect.jvm.internal.impl.types.KotlinType r7 = r9.getType()
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r5)
                kotlin.reflect.jvm.internal.impl.types.TypeProjection r7 = r11.get(r7)
                if (r7 != 0) goto L_0x00b8
            L_0x00b0:
                kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl r7 = new kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl
                r7.<init>(r15)
                kotlin.reflect.jvm.internal.impl.types.TypeProjection r7 = (kotlin.reflect.jvm.internal.impl.types.TypeProjection) r7
                r9 = r7
            L_0x00b8:
                r14.add(r9)
                r7 = 10
                goto L_0x005d
            L_0x00be:
                java.util.List r14 = (java.util.List) r14
                kotlin.reflect.jvm.internal.impl.types.SimpleType r12 = kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt.replace$default(r12, r14, r10, r6, r10)
            L_0x00c4:
                kotlin.reflect.jvm.internal.impl.types.SimpleType r4 = r4.getUpperBound()
                kotlin.reflect.jvm.internal.impl.types.TypeConstructor r7 = r4.getConstructor()
                java.util.List r7 = r7.getParameters()
                boolean r7 = r7.isEmpty()
                if (r7 != 0) goto L_0x0164
                kotlin.reflect.jvm.internal.impl.types.TypeConstructor r7 = r4.getConstructor()
                kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor r7 = r7.getDeclarationDescriptor()
                if (r7 != 0) goto L_0x00e2
                goto L_0x0164
            L_0x00e2:
                kotlin.reflect.jvm.internal.impl.types.TypeConstructor r7 = r4.getConstructor()
                java.util.List r7 = r7.getParameters()
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)
                java.lang.Iterable r7 = (java.lang.Iterable) r7
                java.util.ArrayList r8 = new java.util.ArrayList
                r9 = 10
                int r9 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r7, r9)
                r8.<init>(r9)
                java.util.Collection r8 = (java.util.Collection) r8
                java.util.Iterator r7 = r7.iterator()
            L_0x0100:
                boolean r9 = r7.hasNext()
                if (r9 == 0) goto L_0x015e
                java.lang.Object r9 = r7.next()
                kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r9 = (kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor) r9
                java.util.List r11 = r17.getArguments()
                int r13 = r9.getIndex()
                java.lang.Object r11 = kotlin.collections.CollectionsKt.getOrNull(r11, r13)
                kotlin.reflect.jvm.internal.impl.types.TypeProjection r11 = (kotlin.reflect.jvm.internal.impl.types.TypeProjection) r11
                if (r20 == 0) goto L_0x0133
                if (r11 == 0) goto L_0x012f
                kotlin.reflect.jvm.internal.impl.types.KotlinType r13 = r11.getType()
                if (r13 == 0) goto L_0x012f
                kotlin.jvm.internal.Intrinsics.checkNotNull(r13)
                boolean r13 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.containsTypeParameter(r13)
                if (r13 != 0) goto L_0x012f
                r13 = 1
                goto L_0x0130
            L_0x012f:
                r13 = 0
            L_0x0130:
                if (r13 == 0) goto L_0x0133
                goto L_0x015a
            L_0x0133:
                if (r1 == 0) goto L_0x013d
                boolean r13 = r1.contains(r9)
                if (r13 == 0) goto L_0x013d
                r13 = 1
                goto L_0x013e
            L_0x013d:
                r13 = 0
            L_0x013e:
                if (r11 == 0) goto L_0x0153
                if (r13 != 0) goto L_0x0153
                kotlin.reflect.jvm.internal.impl.types.TypeSubstitution r13 = r18.getSubstitution()
                kotlin.reflect.jvm.internal.impl.types.KotlinType r14 = r11.getType()
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r5)
                kotlin.reflect.jvm.internal.impl.types.TypeProjection r13 = r13.get(r14)
                if (r13 != 0) goto L_0x015a
            L_0x0153:
                kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl r11 = new kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl
                r11.<init>(r9)
                kotlin.reflect.jvm.internal.impl.types.TypeProjection r11 = (kotlin.reflect.jvm.internal.impl.types.TypeProjection) r11
            L_0x015a:
                r8.add(r11)
                goto L_0x0100
            L_0x015e:
                java.util.List r8 = (java.util.List) r8
                kotlin.reflect.jvm.internal.impl.types.SimpleType r4 = kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt.replace$default(r4, r8, r10, r6, r10)
            L_0x0164:
                kotlin.reflect.jvm.internal.impl.types.UnwrappedType r1 = kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory.flexibleType(r12, r4)
                goto L_0x0210
            L_0x016a:
                boolean r4 = r2 instanceof kotlin.reflect.jvm.internal.impl.types.SimpleType
                if (r4 == 0) goto L_0x0224
                r4 = r2
                kotlin.reflect.jvm.internal.impl.types.SimpleType r4 = (kotlin.reflect.jvm.internal.impl.types.SimpleType) r4
                kotlin.reflect.jvm.internal.impl.types.TypeConstructor r7 = r4.getConstructor()
                java.util.List r7 = r7.getParameters()
                boolean r7 = r7.isEmpty()
                if (r7 != 0) goto L_0x020d
                kotlin.reflect.jvm.internal.impl.types.TypeConstructor r7 = r4.getConstructor()
                kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor r7 = r7.getDeclarationDescriptor()
                if (r7 != 0) goto L_0x018b
                goto L_0x020d
            L_0x018b:
                kotlin.reflect.jvm.internal.impl.types.TypeConstructor r7 = r4.getConstructor()
                java.util.List r7 = r7.getParameters()
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)
                java.lang.Iterable r7 = (java.lang.Iterable) r7
                java.util.ArrayList r8 = new java.util.ArrayList
                r9 = 10
                int r9 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r7, r9)
                r8.<init>(r9)
                java.util.Collection r8 = (java.util.Collection) r8
                java.util.Iterator r7 = r7.iterator()
            L_0x01a9:
                boolean r9 = r7.hasNext()
                if (r9 == 0) goto L_0x0207
                java.lang.Object r9 = r7.next()
                kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r9 = (kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor) r9
                java.util.List r11 = r17.getArguments()
                int r12 = r9.getIndex()
                java.lang.Object r11 = kotlin.collections.CollectionsKt.getOrNull(r11, r12)
                kotlin.reflect.jvm.internal.impl.types.TypeProjection r11 = (kotlin.reflect.jvm.internal.impl.types.TypeProjection) r11
                if (r20 == 0) goto L_0x01dc
                if (r11 == 0) goto L_0x01d8
                kotlin.reflect.jvm.internal.impl.types.KotlinType r12 = r11.getType()
                if (r12 == 0) goto L_0x01d8
                kotlin.jvm.internal.Intrinsics.checkNotNull(r12)
                boolean r12 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.containsTypeParameter(r12)
                if (r12 != 0) goto L_0x01d8
                r12 = 1
                goto L_0x01d9
            L_0x01d8:
                r12 = 0
            L_0x01d9:
                if (r12 == 0) goto L_0x01dc
                goto L_0x0203
            L_0x01dc:
                if (r1 == 0) goto L_0x01e6
                boolean r12 = r1.contains(r9)
                if (r12 == 0) goto L_0x01e6
                r12 = 1
                goto L_0x01e7
            L_0x01e6:
                r12 = 0
            L_0x01e7:
                if (r11 == 0) goto L_0x01fc
                if (r12 != 0) goto L_0x01fc
                kotlin.reflect.jvm.internal.impl.types.TypeSubstitution r12 = r18.getSubstitution()
                kotlin.reflect.jvm.internal.impl.types.KotlinType r13 = r11.getType()
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r5)
                kotlin.reflect.jvm.internal.impl.types.TypeProjection r12 = r12.get(r13)
                if (r12 != 0) goto L_0x0203
            L_0x01fc:
                kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl r11 = new kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl
                r11.<init>(r9)
                kotlin.reflect.jvm.internal.impl.types.TypeProjection r11 = (kotlin.reflect.jvm.internal.impl.types.TypeProjection) r11
            L_0x0203:
                r8.add(r11)
                goto L_0x01a9
            L_0x0207:
                java.util.List r8 = (java.util.List) r8
                kotlin.reflect.jvm.internal.impl.types.SimpleType r4 = kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt.replace$default(r4, r8, r10, r6, r10)
            L_0x020d:
                r1 = r4
                kotlin.reflect.jvm.internal.impl.types.UnwrappedType r1 = (kotlin.reflect.jvm.internal.impl.types.UnwrappedType) r1
            L_0x0210:
                kotlin.reflect.jvm.internal.impl.types.KotlinType r2 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r2
                kotlin.reflect.jvm.internal.impl.types.UnwrappedType r1 = kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt.inheritEnhancement(r1, r2)
                kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r1
                kotlin.reflect.jvm.internal.impl.types.Variance r2 = kotlin.reflect.jvm.internal.impl.types.Variance.OUT_VARIANCE
                kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r0.safeSubstitute(r1, r2)
                java.lang.String r1 = "safeSubstitute(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                return r0
            L_0x0224:
                kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException
                r0.<init>()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.TypeParameterUpperBoundEraser.Companion.replaceArgumentsOfUpperBound(kotlin.reflect.jvm.internal.impl.types.KotlinType, kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor, java.util.Set, boolean):kotlin.reflect.jvm.internal.impl.types.KotlinType");
        }
    }
}
