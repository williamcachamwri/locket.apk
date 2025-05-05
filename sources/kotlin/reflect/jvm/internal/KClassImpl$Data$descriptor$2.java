package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeModuleData;
import kotlin.reflect.jvm.internal.impl.name.ClassId;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/impl/descriptors/ClassDescriptor;", "kotlin.jvm.PlatformType", "T", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: KClassImpl.kt */
final class KClassImpl$Data$descriptor$2 extends Lambda implements Function0<ClassDescriptor> {
    final /* synthetic */ KClassImpl<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KClassImpl$Data$descriptor$2(KClassImpl<T> kClassImpl) {
        super(0);
        this.this$0 = kClassImpl;
    }

    public final ClassDescriptor invoke() {
        ClassDescriptor classDescriptor;
        ClassId access$getClassId = this.this$0.getClassId();
        RuntimeModuleData moduleData = this.this$0.getData().getValue().getModuleData();
        ModuleDescriptor module = moduleData.getModule();
        if (!access$getClassId.isLocal() || !this.this$0.getJClass().isAnnotationPresent(Metadata.class)) {
            classDescriptor = FindClassInModuleKt.findClassAcrossModuleDependencies(module, access$getClassId);
        } else {
            classDescriptor = moduleData.getDeserialization().deserializeClass(access$getClassId);
        }
        return classDescriptor == null ? this.this$0.createSyntheticClassOrFail(access$getClassId, moduleData) : classDescriptor;
    }
}
