package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.apache.commons.io.FilenameUtils;

/* compiled from: ReflectKotlinClassFinder.kt */
public final class ReflectKotlinClassFinderKt {
    /* access modifiers changed from: private */
    public static final String toRuntimeFqName(ClassId classId) {
        String asString = classId.getRelativeClassName().asString();
        Intrinsics.checkNotNullExpressionValue(asString, "asString(...)");
        String replace$default = StringsKt.replace$default(asString, (char) FilenameUtils.EXTENSION_SEPARATOR, (char) Typography.dollar, false, 4, (Object) null);
        return classId.getPackageFqName().isRoot() ? replace$default : classId.getPackageFqName() + FilenameUtils.EXTENSION_SEPARATOR + replace$default;
    }
}
