package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* compiled from: NameResolverUtil.kt */
public final class NameResolverUtilKt {
    public static final ClassId getClassId(NameResolver nameResolver, int i) {
        Intrinsics.checkNotNullParameter(nameResolver, "<this>");
        ClassId fromString = ClassId.fromString(nameResolver.getQualifiedClassName(i), nameResolver.isLocalClassName(i));
        Intrinsics.checkNotNullExpressionValue(fromString, "fromString(...)");
        return fromString;
    }

    public static final Name getName(NameResolver nameResolver, int i) {
        Intrinsics.checkNotNullParameter(nameResolver, "<this>");
        Name guessByFirstCharacter = Name.guessByFirstCharacter(nameResolver.getString(i));
        Intrinsics.checkNotNullExpressionValue(guessByFirstCharacter, "guessByFirstCharacter(...)");
        return guessByFirstCharacter;
    }
}
