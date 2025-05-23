package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* compiled from: constantValues.kt */
public class ArrayValue extends ConstantValue<List<? extends ConstantValue<?>>> {
    private final Function1<ModuleDescriptor, KotlinType> computeType;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ArrayValue(List<? extends ConstantValue<?>> list, Function1<? super ModuleDescriptor, ? extends KotlinType> function1) {
        super(list);
        Intrinsics.checkNotNullParameter(list, "value");
        Intrinsics.checkNotNullParameter(function1, "computeType");
        this.computeType = function1;
    }

    public KotlinType getType(ModuleDescriptor moduleDescriptor) {
        Intrinsics.checkNotNullParameter(moduleDescriptor, "module");
        KotlinType invoke = this.computeType.invoke(moduleDescriptor);
        if (!KotlinBuiltIns.isArray(invoke) && !KotlinBuiltIns.isPrimitiveArray(invoke)) {
            KotlinBuiltIns.isUnsignedArrayType(invoke);
        }
        return invoke;
    }
}
