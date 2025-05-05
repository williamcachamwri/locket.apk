package org.koin.core.qualifier;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.koin.ext.KClassExtKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\bH\u0016R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\u00060\bj\u0002`\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Lorg/koin/core/qualifier/TypeQualifier;", "Lorg/koin/core/qualifier/Qualifier;", "type", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)V", "getType", "()Lkotlin/reflect/KClass;", "value", "", "Lorg/koin/core/qualifier/QualifierValue;", "getValue", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "toString", "koin-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TypeQualifier.kt */
public final class TypeQualifier implements Qualifier {
    private final KClass<?> type;
    private final String value;

    public TypeQualifier(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "type");
        this.type = kClass;
        this.value = KClassExtKt.getFullName(kClass);
    }

    public final KClass<?> getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        return "q:'" + getValue() + '\'';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && Intrinsics.areEqual((Object) getValue(), (Object) ((TypeQualifier) obj).getValue());
    }

    public int hashCode() {
        return getValue().hashCode();
    }
}
