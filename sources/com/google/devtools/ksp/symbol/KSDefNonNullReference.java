package com.google.devtools.ksp.symbol;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J5\u0010\u0006\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\b\"\u0004\b\u0001\u0010\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u00070\n2\u0006\u0010\u000b\u001a\u0002H\bH\u0016¢\u0006\u0002\u0010\fR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSDefNonNullReference;", "Lcom/google/devtools/ksp/symbol/KSReferenceElement;", "enclosedType", "Lcom/google/devtools/ksp/symbol/KSClassifierReference;", "getEnclosedType", "()Lcom/google/devtools/ksp/symbol/KSClassifierReference;", "accept", "R", "D", "visitor", "Lcom/google/devtools/ksp/symbol/KSVisitor;", "data", "(Lcom/google/devtools/ksp/symbol/KSVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "api"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: KSDefNonNullReference.kt */
public interface KSDefNonNullReference extends KSReferenceElement {
    KSClassifierReference getEnclosedType();

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: KSDefNonNullReference.kt */
    public static final class DefaultImpls {
        @Deprecated
        public static <D, R> R accept(KSDefNonNullReference kSDefNonNullReference, KSVisitor<D, R> kSVisitor, D d) {
            Intrinsics.checkNotNullParameter(kSVisitor, "visitor");
            return KSDefNonNullReference.super.accept(kSVisitor, d);
        }
    }

    <D, R> R accept(KSVisitor<D, R> kSVisitor, D d) {
        Intrinsics.checkNotNullParameter(kSVisitor, "visitor");
        return kSVisitor.visitDefNonNullReference(this, d);
    }
}
