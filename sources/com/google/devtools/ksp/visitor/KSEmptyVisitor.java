package com.google.devtools.ksp.visitor;

import com.google.devtools.ksp.symbol.KSAnnotated;
import com.google.devtools.ksp.symbol.KSAnnotation;
import com.google.devtools.ksp.symbol.KSCallableReference;
import com.google.devtools.ksp.symbol.KSClassDeclaration;
import com.google.devtools.ksp.symbol.KSClassifierReference;
import com.google.devtools.ksp.symbol.KSDeclaration;
import com.google.devtools.ksp.symbol.KSDeclarationContainer;
import com.google.devtools.ksp.symbol.KSDefNonNullReference;
import com.google.devtools.ksp.symbol.KSDynamicReference;
import com.google.devtools.ksp.symbol.KSFile;
import com.google.devtools.ksp.symbol.KSFunctionDeclaration;
import com.google.devtools.ksp.symbol.KSModifierListOwner;
import com.google.devtools.ksp.symbol.KSNode;
import com.google.devtools.ksp.symbol.KSParenthesizedReference;
import com.google.devtools.ksp.symbol.KSPropertyAccessor;
import com.google.devtools.ksp.symbol.KSPropertyDeclaration;
import com.google.devtools.ksp.symbol.KSPropertyGetter;
import com.google.devtools.ksp.symbol.KSPropertySetter;
import com.google.devtools.ksp.symbol.KSReferenceElement;
import com.google.devtools.ksp.symbol.KSTypeAlias;
import com.google.devtools.ksp.symbol.KSTypeArgument;
import com.google.devtools.ksp.symbol.KSTypeParameter;
import com.google.devtools.ksp.symbol.KSTypeReference;
import com.google.devtools.ksp.symbol.KSValueArgument;
import com.google.devtools.ksp.symbol.KSValueParameter;
import com.google.devtools.ksp.symbol.KSVisitor;
import io.sentry.protocol.SentryStackFrame;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000Ø\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u001d\u0010\u0005\u001a\u00028\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\tJ\u001d\u0010\n\u001a\u00028\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\rJ\u001d\u0010\u000e\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0011J\u001d\u0010\u0012\u001a\u00028\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0015J\u001d\u0010\u0016\u001a\u00028\u00012\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0019J\u001d\u0010\u001a\u001a\u00028\u00012\u0006\u0010\u0013\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001cJ\u001d\u0010\u001d\u001a\u00028\u00012\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010 J\u001d\u0010!\u001a\u00028\u00012\u0006\u0010\"\u001a\u00020#2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010$J\u001d\u0010%\u001a\u00028\u00012\u0006\u0010\u0013\u001a\u00020&2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010'J\u001d\u0010(\u001a\u00028\u00012\u0006\u0010\u0013\u001a\u00020)2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010*J\u001d\u0010+\u001a\u00028\u00012\u0006\u0010,\u001a\u00020-2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010.J\u001d\u0010/\u001a\u00028\u00012\u0006\u00100\u001a\u0002012\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00102J\u001d\u00103\u001a\u00028\u00012\u0006\u00104\u001a\u0002052\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00106J\u001d\u00107\u001a\u00028\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\tJ\u001d\u00108\u001a\u00028\u00012\u0006\u0010\u0013\u001a\u0002092\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010:J\u001d\u0010;\u001a\u00028\u00012\u0006\u0010<\u001a\u00020=2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010>J\u001d\u0010?\u001a\u00028\u00012\u0006\u0010@\u001a\u00020A2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010BJ\u001d\u0010C\u001a\u00028\u00012\u0006\u0010D\u001a\u00020E2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010FJ\u001d\u0010G\u001a\u00028\u00012\u0006\u0010H\u001a\u00020I2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010JJ\u001d\u0010K\u001a\u00028\u00012\u0006\u0010L\u001a\u00020M2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010NJ\u001d\u0010O\u001a\u00028\u00012\u0006\u0010P\u001a\u00020Q2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010RJ\u001d\u0010S\u001a\u00028\u00012\u0006\u0010T\u001a\u00020U2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010VJ\u001d\u0010W\u001a\u00028\u00012\u0006\u0010X\u001a\u00020Y2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010ZJ\u001d\u0010[\u001a\u00028\u00012\u0006\u0010\\\u001a\u00020]2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010^J\u001d\u0010_\u001a\u00028\u00012\u0006\u0010`\u001a\u00020a2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010bJ\u001d\u0010c\u001a\u00028\u00012\u0006\u0010d\u001a\u00020e2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010f¨\u0006g"}, d2 = {"Lcom/google/devtools/ksp/visitor/KSEmptyVisitor;", "D", "R", "Lcom/google/devtools/ksp/symbol/KSVisitor;", "()V", "defaultHandler", "node", "Lcom/google/devtools/ksp/symbol/KSNode;", "data", "(Lcom/google/devtools/ksp/symbol/KSNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnnotated", "annotated", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "(Lcom/google/devtools/ksp/symbol/KSAnnotated;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnnotation", "annotation", "Lcom/google/devtools/ksp/symbol/KSAnnotation;", "(Lcom/google/devtools/ksp/symbol/KSAnnotation;Ljava/lang/Object;)Ljava/lang/Object;", "visitCallableReference", "reference", "Lcom/google/devtools/ksp/symbol/KSCallableReference;", "(Lcom/google/devtools/ksp/symbol/KSCallableReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitClassDeclaration", "classDeclaration", "Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSClassDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitClassifierReference", "Lcom/google/devtools/ksp/symbol/KSClassifierReference;", "(Lcom/google/devtools/ksp/symbol/KSClassifierReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitDeclaration", "declaration", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitDeclarationContainer", "declarationContainer", "Lcom/google/devtools/ksp/symbol/KSDeclarationContainer;", "(Lcom/google/devtools/ksp/symbol/KSDeclarationContainer;Ljava/lang/Object;)Ljava/lang/Object;", "visitDefNonNullReference", "Lcom/google/devtools/ksp/symbol/KSDefNonNullReference;", "(Lcom/google/devtools/ksp/symbol/KSDefNonNullReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitDynamicReference", "Lcom/google/devtools/ksp/symbol/KSDynamicReference;", "(Lcom/google/devtools/ksp/symbol/KSDynamicReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitFile", "file", "Lcom/google/devtools/ksp/symbol/KSFile;", "(Lcom/google/devtools/ksp/symbol/KSFile;Ljava/lang/Object;)Ljava/lang/Object;", "visitFunctionDeclaration", "function", "Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitModifierListOwner", "modifierListOwner", "Lcom/google/devtools/ksp/symbol/KSModifierListOwner;", "(Lcom/google/devtools/ksp/symbol/KSModifierListOwner;Ljava/lang/Object;)Ljava/lang/Object;", "visitNode", "visitParenthesizedReference", "Lcom/google/devtools/ksp/symbol/KSParenthesizedReference;", "(Lcom/google/devtools/ksp/symbol/KSParenthesizedReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertyAccessor", "accessor", "Lcom/google/devtools/ksp/symbol/KSPropertyAccessor;", "(Lcom/google/devtools/ksp/symbol/KSPropertyAccessor;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertyDeclaration", "property", "Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertyGetter", "getter", "Lcom/google/devtools/ksp/symbol/KSPropertyGetter;", "(Lcom/google/devtools/ksp/symbol/KSPropertyGetter;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertySetter", "setter", "Lcom/google/devtools/ksp/symbol/KSPropertySetter;", "(Lcom/google/devtools/ksp/symbol/KSPropertySetter;Ljava/lang/Object;)Ljava/lang/Object;", "visitReferenceElement", "element", "Lcom/google/devtools/ksp/symbol/KSReferenceElement;", "(Lcom/google/devtools/ksp/symbol/KSReferenceElement;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeAlias", "typeAlias", "Lcom/google/devtools/ksp/symbol/KSTypeAlias;", "(Lcom/google/devtools/ksp/symbol/KSTypeAlias;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeArgument", "typeArgument", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "(Lcom/google/devtools/ksp/symbol/KSTypeArgument;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeParameter", "typeParameter", "Lcom/google/devtools/ksp/symbol/KSTypeParameter;", "(Lcom/google/devtools/ksp/symbol/KSTypeParameter;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeReference", "typeReference", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "(Lcom/google/devtools/ksp/symbol/KSTypeReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitValueArgument", "valueArgument", "Lcom/google/devtools/ksp/symbol/KSValueArgument;", "(Lcom/google/devtools/ksp/symbol/KSValueArgument;Ljava/lang/Object;)Ljava/lang/Object;", "visitValueParameter", "valueParameter", "Lcom/google/devtools/ksp/symbol/KSValueParameter;", "(Lcom/google/devtools/ksp/symbol/KSValueParameter;Ljava/lang/Object;)Ljava/lang/Object;", "api"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: KSEmptyVisitor.kt */
public abstract class KSEmptyVisitor<D, R> implements KSVisitor<D, R> {
    public abstract R defaultHandler(KSNode kSNode, D d);

    public R visitNode(KSNode kSNode, D d) {
        Intrinsics.checkNotNullParameter(kSNode, "node");
        return defaultHandler(kSNode, d);
    }

    public R visitAnnotated(KSAnnotated kSAnnotated, D d) {
        Intrinsics.checkNotNullParameter(kSAnnotated, "annotated");
        return defaultHandler(kSAnnotated, d);
    }

    public R visitAnnotation(KSAnnotation kSAnnotation, D d) {
        Intrinsics.checkNotNullParameter(kSAnnotation, "annotation");
        return defaultHandler(kSAnnotation, d);
    }

    public R visitModifierListOwner(KSModifierListOwner kSModifierListOwner, D d) {
        Intrinsics.checkNotNullParameter(kSModifierListOwner, "modifierListOwner");
        return defaultHandler(kSModifierListOwner, d);
    }

    public R visitDeclaration(KSDeclaration kSDeclaration, D d) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "declaration");
        return defaultHandler(kSDeclaration, d);
    }

    public R visitDeclarationContainer(KSDeclarationContainer kSDeclarationContainer, D d) {
        Intrinsics.checkNotNullParameter(kSDeclarationContainer, "declarationContainer");
        return defaultHandler(kSDeclarationContainer, d);
    }

    public R visitDynamicReference(KSDynamicReference kSDynamicReference, D d) {
        Intrinsics.checkNotNullParameter(kSDynamicReference, "reference");
        return defaultHandler(kSDynamicReference, d);
    }

    public R visitFile(KSFile kSFile, D d) {
        Intrinsics.checkNotNullParameter(kSFile, "file");
        return defaultHandler(kSFile, d);
    }

    public R visitFunctionDeclaration(KSFunctionDeclaration kSFunctionDeclaration, D d) {
        Intrinsics.checkNotNullParameter(kSFunctionDeclaration, SentryStackFrame.JsonKeys.FUNCTION);
        return defaultHandler(kSFunctionDeclaration, d);
    }

    public R visitCallableReference(KSCallableReference kSCallableReference, D d) {
        Intrinsics.checkNotNullParameter(kSCallableReference, "reference");
        return defaultHandler(kSCallableReference, d);
    }

    public R visitParenthesizedReference(KSParenthesizedReference kSParenthesizedReference, D d) {
        Intrinsics.checkNotNullParameter(kSParenthesizedReference, "reference");
        return defaultHandler(kSParenthesizedReference, d);
    }

    public R visitPropertyDeclaration(KSPropertyDeclaration kSPropertyDeclaration, D d) {
        Intrinsics.checkNotNullParameter(kSPropertyDeclaration, "property");
        return defaultHandler(kSPropertyDeclaration, d);
    }

    public R visitPropertyAccessor(KSPropertyAccessor kSPropertyAccessor, D d) {
        Intrinsics.checkNotNullParameter(kSPropertyAccessor, "accessor");
        return defaultHandler(kSPropertyAccessor, d);
    }

    public R visitPropertyGetter(KSPropertyGetter kSPropertyGetter, D d) {
        Intrinsics.checkNotNullParameter(kSPropertyGetter, "getter");
        return defaultHandler(kSPropertyGetter, d);
    }

    public R visitPropertySetter(KSPropertySetter kSPropertySetter, D d) {
        Intrinsics.checkNotNullParameter(kSPropertySetter, "setter");
        return defaultHandler(kSPropertySetter, d);
    }

    public R visitClassifierReference(KSClassifierReference kSClassifierReference, D d) {
        Intrinsics.checkNotNullParameter(kSClassifierReference, "reference");
        return defaultHandler(kSClassifierReference, d);
    }

    public R visitDefNonNullReference(KSDefNonNullReference kSDefNonNullReference, D d) {
        Intrinsics.checkNotNullParameter(kSDefNonNullReference, "reference");
        return defaultHandler(kSDefNonNullReference, d);
    }

    public R visitReferenceElement(KSReferenceElement kSReferenceElement, D d) {
        Intrinsics.checkNotNullParameter(kSReferenceElement, "element");
        return defaultHandler(kSReferenceElement, d);
    }

    public R visitTypeAlias(KSTypeAlias kSTypeAlias, D d) {
        Intrinsics.checkNotNullParameter(kSTypeAlias, "typeAlias");
        return defaultHandler(kSTypeAlias, d);
    }

    public R visitTypeArgument(KSTypeArgument kSTypeArgument, D d) {
        Intrinsics.checkNotNullParameter(kSTypeArgument, "typeArgument");
        return defaultHandler(kSTypeArgument, d);
    }

    public R visitClassDeclaration(KSClassDeclaration kSClassDeclaration, D d) {
        Intrinsics.checkNotNullParameter(kSClassDeclaration, "classDeclaration");
        return defaultHandler(kSClassDeclaration, d);
    }

    public R visitTypeParameter(KSTypeParameter kSTypeParameter, D d) {
        Intrinsics.checkNotNullParameter(kSTypeParameter, "typeParameter");
        return defaultHandler(kSTypeParameter, d);
    }

    public R visitTypeReference(KSTypeReference kSTypeReference, D d) {
        Intrinsics.checkNotNullParameter(kSTypeReference, "typeReference");
        return defaultHandler(kSTypeReference, d);
    }

    public R visitValueParameter(KSValueParameter kSValueParameter, D d) {
        Intrinsics.checkNotNullParameter(kSValueParameter, "valueParameter");
        return defaultHandler(kSValueParameter, d);
    }

    public R visitValueArgument(KSValueArgument kSValueArgument, D d) {
        Intrinsics.checkNotNullParameter(kSValueArgument, "valueArgument");
        return defaultHandler(kSValueArgument, d);
    }
}
