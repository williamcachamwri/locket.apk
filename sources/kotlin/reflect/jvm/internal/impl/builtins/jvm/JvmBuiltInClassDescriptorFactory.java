package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.Collection;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

/* compiled from: JvmBuiltInClassDescriptorFactory.kt */
public final class JvmBuiltInClassDescriptorFactory implements ClassDescriptorFactory {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmBuiltInClassDescriptorFactory.class), "cloneable", "getCloneable()Lorg/jetbrains/kotlin/descriptors/impl/ClassDescriptorImpl;"))};
    /* access modifiers changed from: private */
    public static final ClassId CLONEABLE_CLASS_ID;
    /* access modifiers changed from: private */
    public static final Name CLONEABLE_NAME;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final FqName KOTLIN_FQ_NAME = StandardNames.BUILT_INS_PACKAGE_FQ_NAME;
    private final NotNullLazyValue cloneable$delegate;
    /* access modifiers changed from: private */
    public final Function1<ModuleDescriptor, DeclarationDescriptor> computeContainingDeclaration;
    /* access modifiers changed from: private */
    public final ModuleDescriptor moduleDescriptor;

    public JvmBuiltInClassDescriptorFactory(StorageManager storageManager, ModuleDescriptor moduleDescriptor2, Function1<? super ModuleDescriptor, ? extends DeclarationDescriptor> function1) {
        Intrinsics.checkNotNullParameter(storageManager, "storageManager");
        Intrinsics.checkNotNullParameter(moduleDescriptor2, "moduleDescriptor");
        Intrinsics.checkNotNullParameter(function1, "computeContainingDeclaration");
        this.moduleDescriptor = moduleDescriptor2;
        this.computeContainingDeclaration = function1;
        this.cloneable$delegate = storageManager.createLazyValue(new JvmBuiltInClassDescriptorFactory$cloneable$2(this, storageManager));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ JvmBuiltInClassDescriptorFactory(StorageManager storageManager, ModuleDescriptor moduleDescriptor2, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(storageManager, moduleDescriptor2, (i & 4) != 0 ? AnonymousClass1.INSTANCE : function1);
    }

    static {
        Name shortName = StandardNames.FqNames.cloneable.shortName();
        Intrinsics.checkNotNullExpressionValue(shortName, "shortName(...)");
        CLONEABLE_NAME = shortName;
        ClassId classId = ClassId.topLevel(StandardNames.FqNames.cloneable.toSafe());
        Intrinsics.checkNotNullExpressionValue(classId, "topLevel(...)");
        CLONEABLE_CLASS_ID = classId;
    }

    private final ClassDescriptorImpl getCloneable() {
        return (ClassDescriptorImpl) StorageKt.getValue(this.cloneable$delegate, (Object) this, (KProperty<?>) $$delegatedProperties[0]);
    }

    public boolean shouldCreateClass(FqName fqName, Name name) {
        Intrinsics.checkNotNullParameter(fqName, "packageFqName");
        Intrinsics.checkNotNullParameter(name, "name");
        return Intrinsics.areEqual((Object) name, (Object) CLONEABLE_NAME) && Intrinsics.areEqual((Object) fqName, (Object) KOTLIN_FQ_NAME);
    }

    public ClassDescriptor createClass(ClassId classId) {
        Intrinsics.checkNotNullParameter(classId, "classId");
        if (Intrinsics.areEqual((Object) classId, (Object) CLONEABLE_CLASS_ID)) {
            return getCloneable();
        }
        return null;
    }

    public Collection<ClassDescriptor> getAllContributedClassesIfPossible(FqName fqName) {
        Intrinsics.checkNotNullParameter(fqName, "packageFqName");
        if (Intrinsics.areEqual((Object) fqName, (Object) KOTLIN_FQ_NAME)) {
            return SetsKt.setOf(getCloneable());
        }
        return SetsKt.emptySet();
    }

    /* compiled from: JvmBuiltInClassDescriptorFactory.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ClassId getCLONEABLE_CLASS_ID() {
            return JvmBuiltInClassDescriptorFactory.CLONEABLE_CLASS_ID;
        }
    }
}
