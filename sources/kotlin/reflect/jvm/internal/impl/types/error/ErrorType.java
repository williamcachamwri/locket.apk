package kotlin.reflect.jvm.internal.impl.types.error;

import java.util.Arrays;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

/* compiled from: ErrorType.kt */
public final class ErrorType extends SimpleType {
    private final List<TypeProjection> arguments;
    private final TypeConstructor constructor;
    private final String debugMessage;
    private final String[] formatParams;
    private final boolean isMarkedNullable;
    private final ErrorTypeKind kind;
    private final MemberScope memberScope;

    public ErrorType refine(KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        return this;
    }

    public TypeConstructor getConstructor() {
        return this.constructor;
    }

    public MemberScope getMemberScope() {
        return this.memberScope;
    }

    public final ErrorTypeKind getKind() {
        return this.kind;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ErrorType(TypeConstructor typeConstructor, MemberScope memberScope2, ErrorTypeKind errorTypeKind, List list, boolean z, String[] strArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(typeConstructor, memberScope2, errorTypeKind, (i & 8) != 0 ? CollectionsKt.emptyList() : list, (i & 16) != 0 ? false : z, strArr);
    }

    public List<TypeProjection> getArguments() {
        return this.arguments;
    }

    public boolean isMarkedNullable() {
        return this.isMarkedNullable;
    }

    public ErrorType(TypeConstructor typeConstructor, MemberScope memberScope2, ErrorTypeKind errorTypeKind, List<? extends TypeProjection> list, boolean z, String... strArr) {
        Intrinsics.checkNotNullParameter(typeConstructor, "constructor");
        Intrinsics.checkNotNullParameter(memberScope2, "memberScope");
        Intrinsics.checkNotNullParameter(errorTypeKind, "kind");
        Intrinsics.checkNotNullParameter(list, "arguments");
        Intrinsics.checkNotNullParameter(strArr, "formatParams");
        this.constructor = typeConstructor;
        this.memberScope = memberScope2;
        this.kind = errorTypeKind;
        this.arguments = list;
        this.isMarkedNullable = z;
        this.formatParams = strArr;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String debugMessage2 = errorTypeKind.getDebugMessage();
        Object[] copyOf = Arrays.copyOf(strArr, strArr.length);
        String format = String.format(debugMessage2, Arrays.copyOf(copyOf, copyOf.length));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        this.debugMessage = format;
    }

    public final String getDebugMessage() {
        return this.debugMessage;
    }

    public TypeAttributes getAttributes() {
        return TypeAttributes.Companion.getEmpty();
    }

    public SimpleType replaceAttributes(TypeAttributes typeAttributes) {
        Intrinsics.checkNotNullParameter(typeAttributes, "newAttributes");
        return this;
    }

    public final ErrorType replaceArguments(List<? extends TypeProjection> list) {
        Intrinsics.checkNotNullParameter(list, "newArguments");
        TypeConstructor constructor2 = getConstructor();
        MemberScope memberScope2 = getMemberScope();
        ErrorTypeKind errorTypeKind = this.kind;
        boolean isMarkedNullable2 = isMarkedNullable();
        String[] strArr = this.formatParams;
        return new ErrorType(constructor2, memberScope2, errorTypeKind, list, isMarkedNullable2, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public SimpleType makeNullableAsSpecified(boolean z) {
        TypeConstructor constructor2 = getConstructor();
        MemberScope memberScope2 = getMemberScope();
        ErrorTypeKind errorTypeKind = this.kind;
        List<TypeProjection> arguments2 = getArguments();
        String[] strArr = this.formatParams;
        return new ErrorType(constructor2, memberScope2, errorTypeKind, arguments2, z, (String[]) Arrays.copyOf(strArr, strArr.length));
    }
}
