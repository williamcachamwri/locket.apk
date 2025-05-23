package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;

/* compiled from: LazyJavaClassMemberScope.kt */
final class LazyJavaClassMemberScope$constructors$1 extends Lambda implements Function0<List<? extends ClassConstructorDescriptor>> {
    final /* synthetic */ LazyJavaResolverContext $c;
    final /* synthetic */ LazyJavaClassMemberScope this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LazyJavaClassMemberScope$constructors$1(LazyJavaClassMemberScope lazyJavaClassMemberScope, LazyJavaResolverContext lazyJavaResolverContext) {
        super(0);
        this.this$0 = lazyJavaClassMemberScope;
        this.$c = lazyJavaResolverContext;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x007a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor> invoke() {
        /*
            r9 = this;
            kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope r0 = r9.this$0
            kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass r0 = r0.jClass
            java.util.Collection r0 = r0.getConstructors()
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r0.size()
            r1.<init>(r2)
            java.util.Iterator r0 = r0.iterator()
        L_0x0017:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x002d
            java.lang.Object r2 = r0.next()
            kotlin.reflect.jvm.internal.impl.load.java.structure.JavaConstructor r2 = (kotlin.reflect.jvm.internal.impl.load.java.structure.JavaConstructor) r2
            kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope r3 = r9.this$0
            kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassConstructorDescriptor r2 = r3.resolveConstructor(r2)
            r1.add(r2)
            goto L_0x0017
        L_0x002d:
            kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope r0 = r9.this$0
            kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass r0 = r0.jClass
            boolean r0 = r0.isRecord()
            if (r0 == 0) goto L_0x0094
            kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope r0 = r9.this$0
            kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor r0 = r0.createDefaultRecordConstructor()
            r2 = r0
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r2 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r2
            r3 = 0
            r4 = 2
            r5 = 0
            java.lang.String r2 = kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt.computeJvmDescriptor$default(r2, r3, r3, r4, r5)
            r6 = r1
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            boolean r7 = r6 instanceof java.util.Collection
            r8 = 1
            if (r7 == 0) goto L_0x005c
            r7 = r6
            java.util.Collection r7 = (java.util.Collection) r7
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x005c
        L_0x005a:
            r3 = r8
            goto L_0x0078
        L_0x005c:
            java.util.Iterator r6 = r6.iterator()
        L_0x0060:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x005a
            java.lang.Object r7 = r6.next()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor r7 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor) r7
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r7 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r7
            java.lang.String r7 = kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt.computeJvmDescriptor$default(r7, r3, r3, r4, r5)
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r2)
            if (r7 == 0) goto L_0x0060
        L_0x0078:
            if (r3 == 0) goto L_0x0094
            r1.add(r0)
            kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext r2 = r9.$c
            kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverComponents r2 = r2.getComponents()
            kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache r2 = r2.getJavaResolverCache()
            kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope r3 = r9.this$0
            kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass r3 = r3.jClass
            kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement r3 = (kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement) r3
            kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor) r0
            r2.recordConstructor(r3, r0)
        L_0x0094:
            kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext r0 = r9.$c
            kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope r2 = r9.this$0
            kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverComponents r3 = r0.getComponents()
            kotlin.reflect.jvm.internal.impl.resolve.jvm.SyntheticJavaPartsProvider r3 = r3.getSyntheticPartsProvider()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r2 = r2.getOwnerDescriptor()
            r4 = r1
            java.util.List r4 = (java.util.List) r4
            r3.generateConstructors(r0, r2, r4)
            kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext r0 = r9.$c
            kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverComponents r0 = r0.getComponents()
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement r0 = r0.getSignatureEnhancement()
            kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext r2 = r9.$c
            java.util.Collection r1 = (java.util.Collection) r1
            kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope r3 = r9.this$0
            boolean r4 = r1.isEmpty()
            if (r4 == 0) goto L_0x00ca
            kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor r1 = r3.createDefaultConstructor()
            java.util.List r1 = kotlin.collections.CollectionsKt.listOfNotNull(r1)
            java.util.Collection r1 = (java.util.Collection) r1
        L_0x00ca:
            java.util.Collection r0 = r0.enhanceSignatures(r2, r1)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.List r0 = kotlin.collections.CollectionsKt.toList(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope$constructors$1.invoke():java.util.List");
    }
}
