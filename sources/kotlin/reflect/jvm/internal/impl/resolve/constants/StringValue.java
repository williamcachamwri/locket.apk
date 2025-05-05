package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

/* compiled from: constantValues.kt */
public final class StringValue extends ConstantValue<String> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StringValue(String str) {
        super(str);
        Intrinsics.checkNotNullParameter(str, "value");
    }

    public SimpleType getType(ModuleDescriptor moduleDescriptor) {
        Intrinsics.checkNotNullParameter(moduleDescriptor, "module");
        SimpleType stringType = moduleDescriptor.getBuiltIns().getStringType();
        Intrinsics.checkNotNullExpressionValue(stringType, "getStringType(...)");
        return stringType;
    }

    public String toString() {
        return "\"" + ((String) getValue()) + '\"';
    }
}
