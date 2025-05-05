package org.koin.core.qualifier;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0010\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\u0011\u0010\u0006\u001a\u00020\u0007\"\u0006\b\u0000\u0010\b\u0018\u0001H\b\u001a\u000e\u0010\u0006\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b\u001a\u0011\u0010\f\u001a\u00020\u0007\"\u0006\b\u0000\u0010\b\u0018\u0001H\b\u001a$\u0010\f\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001a\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b\u001a\u0011\u0010\u0000\u001a\u00020\u0007\"\u0006\b\u0000\u0010\b\u0018\u0001H\b\u001a$\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001a\u000e\u0010\u0000\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b\"+\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005*\n\u0010\u000e\"\u00020\u000b2\u00020\u000b¨\u0006\u000f"}, d2 = {"qualifier", "Lorg/koin/core/qualifier/Qualifier;", "E", "", "getQualifier", "(Ljava/lang/Enum;)Lorg/koin/core/qualifier/Qualifier;", "_q", "Lorg/koin/core/qualifier/TypeQualifier;", "T", "Lorg/koin/core/qualifier/StringQualifier;", "name", "", "named", "enum", "QualifierValue", "koin-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Qualifier.kt */
public final class QualifierKt {
    public static final StringQualifier named(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return new StringQualifier(str);
    }

    public static final <E extends Enum<E>> Qualifier named(Enum<E> enumR) {
        Intrinsics.checkNotNullParameter(enumR, "enum");
        return getQualifier(enumR);
    }

    public static final StringQualifier qualifier(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return new StringQualifier(str);
    }

    public static final <E extends Enum<E>> Qualifier qualifier(Enum<E> enumR) {
        Intrinsics.checkNotNullParameter(enumR, "enum");
        return getQualifier(enumR);
    }

    public static final StringQualifier _q(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return new StringQualifier(str);
    }

    public static final <E extends Enum<E>> Qualifier getQualifier(Enum<E> enumR) {
        Intrinsics.checkNotNullParameter(enumR, "<this>");
        String lowerCase = enumR.toString().toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        return new StringQualifier(lowerCase);
    }
}
