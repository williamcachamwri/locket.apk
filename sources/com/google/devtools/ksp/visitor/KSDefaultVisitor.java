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
import io.sentry.protocol.SentryStackFrame;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000Ð\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u001d\u0010\u0005\u001a\u00028\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\tJ\u001d\u0010\n\u001a\u00028\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\rJ\u001d\u0010\u000e\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0011J\u001d\u0010\u0012\u001a\u00028\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0015J\u001d\u0010\u0016\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u00020\u00172\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0018J\u001d\u0010\u0019\u001a\u00028\u00012\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001cJ\u001d\u0010\u001d\u001a\u00028\u00012\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010 J\u001d\u0010!\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u00020\"2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010#J\u001d\u0010$\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u00020%2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010&J\u001d\u0010'\u001a\u00028\u00012\u0006\u0010(\u001a\u00020)2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010*J\u001d\u0010+\u001a\u00028\u00012\u0006\u0010,\u001a\u00020-2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010.J\u001d\u0010/\u001a\u00028\u00012\u0006\u00100\u001a\u0002012\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00102J\u001d\u00103\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u0002042\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00105J\u001d\u00106\u001a\u00028\u00012\u0006\u00107\u001a\u0002082\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00109J\u001d\u0010:\u001a\u00028\u00012\u0006\u0010;\u001a\u00020<2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010=J\u001d\u0010>\u001a\u00028\u00012\u0006\u0010?\u001a\u00020@2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010AJ\u001d\u0010B\u001a\u00028\u00012\u0006\u0010C\u001a\u00020D2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010EJ\u001d\u0010F\u001a\u00028\u00012\u0006\u0010G\u001a\u00020H2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010IJ\u001d\u0010J\u001a\u00028\u00012\u0006\u0010K\u001a\u00020L2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010MJ\u001d\u0010N\u001a\u00028\u00012\u0006\u0010O\u001a\u00020P2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010QJ\u001d\u0010R\u001a\u00028\u00012\u0006\u0010S\u001a\u00020T2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010UJ\u001d\u0010V\u001a\u00028\u00012\u0006\u0010W\u001a\u00020X2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010YJ\u001d\u0010Z\u001a\u00028\u00012\u0006\u0010[\u001a\u00020\\2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010]J\u001d\u0010^\u001a\u00028\u00012\u0006\u0010_\u001a\u00020`2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010a¨\u0006b"}, d2 = {"Lcom/google/devtools/ksp/visitor/KSDefaultVisitor;", "D", "R", "Lcom/google/devtools/ksp/visitor/KSEmptyVisitor;", "()V", "visitAnnotated", "annotated", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "data", "(Lcom/google/devtools/ksp/symbol/KSAnnotated;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnnotation", "annotation", "Lcom/google/devtools/ksp/symbol/KSAnnotation;", "(Lcom/google/devtools/ksp/symbol/KSAnnotation;Ljava/lang/Object;)Ljava/lang/Object;", "visitCallableReference", "reference", "Lcom/google/devtools/ksp/symbol/KSCallableReference;", "(Lcom/google/devtools/ksp/symbol/KSCallableReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitClassDeclaration", "classDeclaration", "Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSClassDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitClassifierReference", "Lcom/google/devtools/ksp/symbol/KSClassifierReference;", "(Lcom/google/devtools/ksp/symbol/KSClassifierReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitDeclaration", "declaration", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitDeclarationContainer", "declarationContainer", "Lcom/google/devtools/ksp/symbol/KSDeclarationContainer;", "(Lcom/google/devtools/ksp/symbol/KSDeclarationContainer;Ljava/lang/Object;)Ljava/lang/Object;", "visitDefNonNullReference", "Lcom/google/devtools/ksp/symbol/KSDefNonNullReference;", "(Lcom/google/devtools/ksp/symbol/KSDefNonNullReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitDynamicReference", "Lcom/google/devtools/ksp/symbol/KSDynamicReference;", "(Lcom/google/devtools/ksp/symbol/KSDynamicReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitFile", "file", "Lcom/google/devtools/ksp/symbol/KSFile;", "(Lcom/google/devtools/ksp/symbol/KSFile;Ljava/lang/Object;)Ljava/lang/Object;", "visitFunctionDeclaration", "function", "Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitModifierListOwner", "modifierListOwner", "Lcom/google/devtools/ksp/symbol/KSModifierListOwner;", "(Lcom/google/devtools/ksp/symbol/KSModifierListOwner;Ljava/lang/Object;)Ljava/lang/Object;", "visitParenthesizedReference", "Lcom/google/devtools/ksp/symbol/KSParenthesizedReference;", "(Lcom/google/devtools/ksp/symbol/KSParenthesizedReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertyAccessor", "accessor", "Lcom/google/devtools/ksp/symbol/KSPropertyAccessor;", "(Lcom/google/devtools/ksp/symbol/KSPropertyAccessor;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertyDeclaration", "property", "Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertyGetter", "getter", "Lcom/google/devtools/ksp/symbol/KSPropertyGetter;", "(Lcom/google/devtools/ksp/symbol/KSPropertyGetter;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertySetter", "setter", "Lcom/google/devtools/ksp/symbol/KSPropertySetter;", "(Lcom/google/devtools/ksp/symbol/KSPropertySetter;Ljava/lang/Object;)Ljava/lang/Object;", "visitReferenceElement", "element", "Lcom/google/devtools/ksp/symbol/KSReferenceElement;", "(Lcom/google/devtools/ksp/symbol/KSReferenceElement;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeAlias", "typeAlias", "Lcom/google/devtools/ksp/symbol/KSTypeAlias;", "(Lcom/google/devtools/ksp/symbol/KSTypeAlias;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeArgument", "typeArgument", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "(Lcom/google/devtools/ksp/symbol/KSTypeArgument;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeParameter", "typeParameter", "Lcom/google/devtools/ksp/symbol/KSTypeParameter;", "(Lcom/google/devtools/ksp/symbol/KSTypeParameter;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeReference", "typeReference", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "(Lcom/google/devtools/ksp/symbol/KSTypeReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitValueArgument", "valueArgument", "Lcom/google/devtools/ksp/symbol/KSValueArgument;", "(Lcom/google/devtools/ksp/symbol/KSValueArgument;Ljava/lang/Object;)Ljava/lang/Object;", "visitValueParameter", "valueParameter", "Lcom/google/devtools/ksp/symbol/KSValueParameter;", "(Lcom/google/devtools/ksp/symbol/KSValueParameter;Ljava/lang/Object;)Ljava/lang/Object;", "api"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: KSDefaultVisitor.kt */
public abstract class KSDefaultVisitor<D, R> extends KSEmptyVisitor<D, R> {
    public R visitDynamicReference(KSDynamicReference kSDynamicReference, D d) {
        Intrinsics.checkNotNullParameter(kSDynamicReference, "reference");
        visitReferenceElement(kSDynamicReference, d);
        return super.visitDynamicReference(kSDynamicReference, d);
    }

    public R visitFile(KSFile kSFile, D d) {
        Intrinsics.checkNotNullParameter(kSFile, "file");
        visitAnnotated(kSFile, d);
        visitDeclarationContainer(kSFile, d);
        return super.visitFile(kSFile, d);
    }

    public R visitFunctionDeclaration(KSFunctionDeclaration kSFunctionDeclaration, D d) {
        Intrinsics.checkNotNullParameter(kSFunctionDeclaration, SentryStackFrame.JsonKeys.FUNCTION);
        visitDeclaration(kSFunctionDeclaration, d);
        visitDeclarationContainer(kSFunctionDeclaration, d);
        return super.visitFunctionDeclaration(kSFunctionDeclaration, d);
    }

    public R visitCallableReference(KSCallableReference kSCallableReference, D d) {
        Intrinsics.checkNotNullParameter(kSCallableReference, "reference");
        visitReferenceElement(kSCallableReference, d);
        return super.visitCallableReference(kSCallableReference, d);
    }

    public R visitParenthesizedReference(KSParenthesizedReference kSParenthesizedReference, D d) {
        Intrinsics.checkNotNullParameter(kSParenthesizedReference, "reference");
        visitReferenceElement(kSParenthesizedReference, d);
        return super.visitParenthesizedReference(kSParenthesizedReference, d);
    }

    public R visitPropertyDeclaration(KSPropertyDeclaration kSPropertyDeclaration, D d) {
        Intrinsics.checkNotNullParameter(kSPropertyDeclaration, "property");
        visitDeclaration(kSPropertyDeclaration, d);
        return super.visitPropertyDeclaration(kSPropertyDeclaration, d);
    }

    public R visitPropertyAccessor(KSPropertyAccessor kSPropertyAccessor, D d) {
        Intrinsics.checkNotNullParameter(kSPropertyAccessor, "accessor");
        visitModifierListOwner(kSPropertyAccessor, d);
        visitAnnotated(kSPropertyAccessor, d);
        return super.visitPropertyAccessor(kSPropertyAccessor, d);
    }

    public R visitPropertyGetter(KSPropertyGetter kSPropertyGetter, D d) {
        Intrinsics.checkNotNullParameter(kSPropertyGetter, "getter");
        visitPropertyAccessor(kSPropertyGetter, d);
        return super.visitPropertyGetter(kSPropertyGetter, d);
    }

    public R visitPropertySetter(KSPropertySetter kSPropertySetter, D d) {
        Intrinsics.checkNotNullParameter(kSPropertySetter, "setter");
        visitPropertyAccessor(kSPropertySetter, d);
        return super.visitPropertySetter(kSPropertySetter, d);
    }

    public R visitTypeAlias(KSTypeAlias kSTypeAlias, D d) {
        Intrinsics.checkNotNullParameter(kSTypeAlias, "typeAlias");
        visitDeclaration(kSTypeAlias, d);
        return super.visitTypeAlias(kSTypeAlias, d);
    }

    public R visitClassDeclaration(KSClassDeclaration kSClassDeclaration, D d) {
        Intrinsics.checkNotNullParameter(kSClassDeclaration, "classDeclaration");
        visitDeclaration(kSClassDeclaration, d);
        visitDeclarationContainer(kSClassDeclaration, d);
        return super.visitClassDeclaration(kSClassDeclaration, d);
    }

    public R visitTypeParameter(KSTypeParameter kSTypeParameter, D d) {
        Intrinsics.checkNotNullParameter(kSTypeParameter, "typeParameter");
        visitDeclaration(kSTypeParameter, d);
        return super.visitTypeParameter(kSTypeParameter, d);
    }

    public R visitTypeReference(KSTypeReference kSTypeReference, D d) {
        Intrinsics.checkNotNullParameter(kSTypeReference, "typeReference");
        visitAnnotated(kSTypeReference, d);
        visitModifierListOwner(kSTypeReference, d);
        return super.visitTypeReference(kSTypeReference, d);
    }

    public R visitValueParameter(KSValueParameter kSValueParameter, D d) {
        Intrinsics.checkNotNullParameter(kSValueParameter, "valueParameter");
        visitAnnotated(kSValueParameter, d);
        return super.visitValueParameter(kSValueParameter, d);
    }

    public R visitValueArgument(KSValueArgument kSValueArgument, D d) {
        Intrinsics.checkNotNullParameter(kSValueArgument, "valueArgument");
        visitAnnotated(kSValueArgument, d);
        return super.visitValueArgument(kSValueArgument, d);
    }

    public R visitClassifierReference(KSClassifierReference kSClassifierReference, D d) {
        Intrinsics.checkNotNullParameter(kSClassifierReference, "reference");
        visitReferenceElement(kSClassifierReference, d);
        return super.visitClassifierReference(kSClassifierReference, d);
    }

    public R visitDefNonNullReference(KSDefNonNullReference kSDefNonNullReference, D d) {
        Intrinsics.checkNotNullParameter(kSDefNonNullReference, "reference");
        visitReferenceElement(kSDefNonNullReference, d);
        return super.visitDefNonNullReference(kSDefNonNullReference, d);
    }

    public R visitTypeArgument(KSTypeArgument kSTypeArgument, D d) {
        Intrinsics.checkNotNullParameter(kSTypeArgument, "typeArgument");
        visitAnnotated(kSTypeArgument, d);
        return super.visitTypeArgument(kSTypeArgument, d);
    }

    public R visitDeclaration(KSDeclaration kSDeclaration, D d) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "declaration");
        visitAnnotated(kSDeclaration, d);
        visitModifierListOwner(kSDeclaration, d);
        return super.visitDeclaration(kSDeclaration, d);
    }

    public R visitAnnotated(KSAnnotated kSAnnotated, D d) {
        Intrinsics.checkNotNullParameter(kSAnnotated, "annotated");
        visitNode(kSAnnotated, d);
        return super.visitAnnotated(kSAnnotated, d);
    }

    public R visitAnnotation(KSAnnotation kSAnnotation, D d) {
        Intrinsics.checkNotNullParameter(kSAnnotation, "annotation");
        visitNode(kSAnnotation, d);
        return super.visitAnnotation(kSAnnotation, d);
    }

    public R visitDeclarationContainer(KSDeclarationContainer kSDeclarationContainer, D d) {
        Intrinsics.checkNotNullParameter(kSDeclarationContainer, "declarationContainer");
        visitNode(kSDeclarationContainer, d);
        return super.visitDeclarationContainer(kSDeclarationContainer, d);
    }

    public R visitModifierListOwner(KSModifierListOwner kSModifierListOwner, D d) {
        Intrinsics.checkNotNullParameter(kSModifierListOwner, "modifierListOwner");
        visitNode(kSModifierListOwner, d);
        return super.visitModifierListOwner(kSModifierListOwner, d);
    }

    public R visitReferenceElement(KSReferenceElement kSReferenceElement, D d) {
        Intrinsics.checkNotNullParameter(kSReferenceElement, "element");
        visitNode(kSReferenceElement, d);
        return super.visitReferenceElement(kSReferenceElement, d);
    }
}
