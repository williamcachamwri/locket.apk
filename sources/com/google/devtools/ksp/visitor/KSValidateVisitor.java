package com.google.devtools.ksp.visitor;

import com.google.devtools.ksp.symbol.KSAnnotated;
import com.google.devtools.ksp.symbol.KSAnnotation;
import com.google.devtools.ksp.symbol.KSClassDeclaration;
import com.google.devtools.ksp.symbol.KSDeclaration;
import com.google.devtools.ksp.symbol.KSDeclarationContainer;
import com.google.devtools.ksp.symbol.KSNode;
import com.google.devtools.ksp.symbol.KSPropertyDeclaration;
import com.google.devtools.ksp.symbol.KSType;
import com.google.devtools.ksp.symbol.KSTypeParameter;
import com.google.devtools.ksp.symbol.KSTypeReference;
import com.google.devtools.ksp.symbol.KSValueArgument;
import com.google.devtools.ksp.symbol.KSValueParameter;
import com.google.devtools.ksp.symbol.KSVisitor;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0001B!\u0012\u001a\u0010\u0004\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\u001f\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00022\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0002J\u001f\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\u0011J\u001f\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\u0015J\u001f\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\u0019J\u001f\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\u001dJ\u001f\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020 2\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010!J\u001f\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020$2\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010%J\u001f\u0010&\u001a\u00020\u00032\u0006\u0010'\u001a\u00020(2\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010)J\u001f\u0010*\u001a\u00020\u00032\u0006\u0010+\u001a\u00020,2\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010-J\u001f\u0010.\u001a\u00020\u00032\u0006\u0010/\u001a\u0002002\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u00101J\u001f\u00102\u001a\u00020\u00032\u0006\u00103\u001a\u0002042\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u00105J\u001f\u00106\u001a\u00020\u00032\u0006\u00107\u001a\u0002082\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u00109R\"\u0010\u0004\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/google/devtools/ksp/visitor/KSValidateVisitor;", "Lcom/google/devtools/ksp/visitor/KSDefaultVisitor;", "Lcom/google/devtools/ksp/symbol/KSNode;", "", "predicate", "Lkotlin/Function2;", "(Lkotlin/jvm/functions/Function2;)V", "defaultHandler", "node", "data", "(Lcom/google/devtools/ksp/symbol/KSNode;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "validateType", "type", "Lcom/google/devtools/ksp/symbol/KSType;", "visitAnnotated", "annotated", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "(Lcom/google/devtools/ksp/symbol/KSAnnotated;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "visitAnnotation", "annotation", "Lcom/google/devtools/ksp/symbol/KSAnnotation;", "(Lcom/google/devtools/ksp/symbol/KSAnnotation;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "visitClassDeclaration", "classDeclaration", "Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSClassDeclaration;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "visitDeclaration", "declaration", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSDeclaration;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "visitDeclarationContainer", "declarationContainer", "Lcom/google/devtools/ksp/symbol/KSDeclarationContainer;", "(Lcom/google/devtools/ksp/symbol/KSDeclarationContainer;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "visitFunctionDeclaration", "function", "Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "visitPropertyDeclaration", "property", "Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "visitTypeParameter", "typeParameter", "Lcom/google/devtools/ksp/symbol/KSTypeParameter;", "(Lcom/google/devtools/ksp/symbol/KSTypeParameter;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "visitTypeReference", "typeReference", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "(Lcom/google/devtools/ksp/symbol/KSTypeReference;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "visitValueArgument", "valueArgument", "Lcom/google/devtools/ksp/symbol/KSValueArgument;", "(Lcom/google/devtools/ksp/symbol/KSValueArgument;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "visitValueParameter", "valueParameter", "Lcom/google/devtools/ksp/symbol/KSValueParameter;", "(Lcom/google/devtools/ksp/symbol/KSValueParameter;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "api"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: KSValidateVisitor.kt */
public class KSValidateVisitor extends KSDefaultVisitor<KSNode, Boolean> {
    private final Function2<KSNode, KSNode, Boolean> predicate;

    public KSValidateVisitor(Function2<? super KSNode, ? super KSNode, Boolean> function2) {
        Intrinsics.checkNotNullParameter(function2, "predicate");
        this.predicate = function2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean validateType(com.google.devtools.ksp.symbol.KSType r6) {
        /*
            r5 = this;
            boolean r0 = r6.isError()
            r1 = 0
            if (r0 != 0) goto L_0x004c
            java.util.List r6 = r6.getArguments()
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            boolean r0 = r6 instanceof java.util.Collection
            r2 = 1
            if (r0 == 0) goto L_0x001d
            r0 = r6
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x001d
        L_0x001b:
            r6 = r1
            goto L_0x0049
        L_0x001d:
            java.util.Iterator r6 = r6.iterator()
        L_0x0021:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L_0x001b
            java.lang.Object r0 = r6.next()
            com.google.devtools.ksp.symbol.KSTypeArgument r0 = (com.google.devtools.ksp.symbol.KSTypeArgument) r0
            com.google.devtools.ksp.symbol.KSTypeReference r0 = r0.getType()
            if (r0 == 0) goto L_0x0045
            r3 = r5
            com.google.devtools.ksp.symbol.KSVisitor r3 = (com.google.devtools.ksp.symbol.KSVisitor) r3
            r4 = 0
            java.lang.Object r0 = r0.accept(r3, r4)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x0045
            r0 = r2
            goto L_0x0046
        L_0x0045:
            r0 = r1
        L_0x0046:
            if (r0 == 0) goto L_0x0021
            r6 = r2
        L_0x0049:
            if (r6 != 0) goto L_0x004c
            r1 = r2
        L_0x004c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.devtools.ksp.visitor.KSValidateVisitor.validateType(com.google.devtools.ksp.symbol.KSType):boolean");
    }

    public Boolean defaultHandler(KSNode kSNode, KSNode kSNode2) {
        Intrinsics.checkNotNullParameter(kSNode, "node");
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Boolean visitDeclaration(com.google.devtools.ksp.symbol.KSDeclaration r6, com.google.devtools.ksp.symbol.KSNode r7) {
        /*
            r5 = this;
            java.lang.String r0 = "declaration"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            kotlin.jvm.functions.Function2<com.google.devtools.ksp.symbol.KSNode, com.google.devtools.ksp.symbol.KSNode, java.lang.Boolean> r0 = r5.predicate
            java.lang.Object r0 = r0.invoke(r7, r6)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            r1 = 1
            if (r0 != 0) goto L_0x0019
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r1)
            return r6
        L_0x0019:
            java.util.List r0 = r6.getTypeParameters()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            boolean r2 = r0 instanceof java.util.Collection
            r3 = 0
            if (r2 == 0) goto L_0x002f
            r2 = r0
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x002f
        L_0x002d:
            r1 = r3
            goto L_0x004f
        L_0x002f:
            java.util.Iterator r0 = r0.iterator()
        L_0x0033:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x002d
            java.lang.Object r2 = r0.next()
            com.google.devtools.ksp.symbol.KSTypeParameter r2 = (com.google.devtools.ksp.symbol.KSTypeParameter) r2
            r4 = r5
            com.google.devtools.ksp.symbol.KSVisitor r4 = (com.google.devtools.ksp.symbol.KSVisitor) r4
            java.lang.Object r2 = r2.accept(r4, r6)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            r2 = r2 ^ r1
            if (r2 == 0) goto L_0x0033
        L_0x004f:
            if (r1 == 0) goto L_0x0056
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r3)
            return r6
        L_0x0056:
            com.google.devtools.ksp.symbol.KSAnnotated r6 = (com.google.devtools.ksp.symbol.KSAnnotated) r6
            java.lang.Boolean r6 = r5.visitAnnotated((com.google.devtools.ksp.symbol.KSAnnotated) r6, (com.google.devtools.ksp.symbol.KSNode) r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.devtools.ksp.visitor.KSValidateVisitor.visitDeclaration(com.google.devtools.ksp.symbol.KSDeclaration, com.google.devtools.ksp.symbol.KSNode):java.lang.Boolean");
    }

    public Boolean visitDeclarationContainer(KSDeclarationContainer kSDeclarationContainer, KSNode kSNode) {
        boolean z;
        Intrinsics.checkNotNullParameter(kSDeclarationContainer, "declarationContainer");
        Iterator<KSDeclaration> it = kSDeclarationContainer.getDeclarations().iterator();
        while (true) {
            z = true;
            if (!it.hasNext()) {
                break;
            }
            KSDeclaration next = it.next();
            if (this.predicate.invoke(kSDeclarationContainer, next).booleanValue() && !((Boolean) next.accept(this, kSDeclarationContainer)).booleanValue()) {
                z = false;
                continue;
            }
            if (!z) {
                z = false;
                break;
            }
        }
        return Boolean.valueOf(z);
    }

    public Boolean visitTypeParameter(KSTypeParameter kSTypeParameter, KSNode kSNode) {
        boolean z;
        Intrinsics.checkNotNullParameter(kSTypeParameter, "typeParameter");
        boolean z2 = true;
        if (this.predicate.invoke(kSNode, kSTypeParameter).booleanValue()) {
            Iterator<KSTypeReference> it = kSTypeParameter.getBounds().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (!((Boolean) it.next().accept(this, kSTypeParameter)).booleanValue()) {
                        z = false;
                        break;
                    }
                } else {
                    z = true;
                    break;
                }
            }
            if (!z) {
                z2 = false;
            }
        }
        return Boolean.valueOf(z2);
    }

    public Boolean visitAnnotated(KSAnnotated kSAnnotated, KSNode kSNode) {
        boolean z;
        Intrinsics.checkNotNullParameter(kSAnnotated, "annotated");
        boolean z2 = true;
        if (this.predicate.invoke(kSNode, kSAnnotated).booleanValue()) {
            Iterator<KSAnnotation> it = kSAnnotated.getAnnotations().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (!((Boolean) it.next().accept(this, kSAnnotated)).booleanValue()) {
                        z = false;
                        break;
                    }
                } else {
                    z = true;
                    break;
                }
            }
            if (!z) {
                z2 = false;
            }
        }
        return Boolean.valueOf(z2);
    }

    public Boolean visitAnnotation(KSAnnotation kSAnnotation, KSNode kSNode) {
        Intrinsics.checkNotNullParameter(kSAnnotation, "annotation");
        boolean z = true;
        if (!this.predicate.invoke(kSNode, kSAnnotation).booleanValue()) {
            return true;
        }
        KSVisitor kSVisitor = this;
        if (!((Boolean) kSAnnotation.getAnnotationType().accept(kSVisitor, kSAnnotation)).booleanValue()) {
            return false;
        }
        Iterable arguments = kSAnnotation.getArguments();
        if (!(arguments instanceof Collection) || !((Collection) arguments).isEmpty()) {
            Iterator it = arguments.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                KSValueArgument kSValueArgument = (KSValueArgument) it.next();
                if (!((Boolean) kSValueArgument.accept(kSVisitor, kSValueArgument)).booleanValue()) {
                    break;
                }
            }
        }
        z = false;
        if (z) {
            return false;
        }
        return true;
    }

    public Boolean visitTypeReference(KSTypeReference kSTypeReference, KSNode kSNode) {
        Intrinsics.checkNotNullParameter(kSTypeReference, "typeReference");
        return Boolean.valueOf(validateType(kSTypeReference.resolve()));
    }

    public Boolean visitClassDeclaration(KSClassDeclaration kSClassDeclaration, KSNode kSNode) {
        Intrinsics.checkNotNullParameter(kSClassDeclaration, "classDeclaration");
        boolean z = false;
        if (kSClassDeclaration.asStarProjectedType().isError()) {
            return false;
        }
        Iterator<KSTypeReference> it = kSClassDeclaration.getSuperTypes().iterator();
        while (true) {
            if (it.hasNext()) {
                if (!((Boolean) it.next().accept(this, kSClassDeclaration)).booleanValue()) {
                    break;
                }
            } else {
                z = true;
                break;
            }
        }
        if (z && visitDeclaration((KSDeclaration) kSClassDeclaration, kSNode).booleanValue() && visitDeclarationContainer((KSDeclarationContainer) kSClassDeclaration, kSNode).booleanValue()) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0080 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0081  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Boolean visitFunctionDeclaration(com.google.devtools.ksp.symbol.KSFunctionDeclaration r7, com.google.devtools.ksp.symbol.KSNode r8) {
        /*
            r6 = this;
            java.lang.String r0 = "function"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            com.google.devtools.ksp.symbol.KSTypeReference r0 = r7.getReturnType()
            r1 = 0
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)
            if (r0 == 0) goto L_0x003c
            kotlin.jvm.functions.Function2<com.google.devtools.ksp.symbol.KSNode, com.google.devtools.ksp.symbol.KSNode, java.lang.Boolean> r0 = r6.predicate
            com.google.devtools.ksp.symbol.KSTypeReference r3 = r7.getReturnType()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            java.lang.Object r0 = r0.invoke(r7, r3)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x003c
            com.google.devtools.ksp.symbol.KSTypeReference r0 = r7.getReturnType()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r3 = r6
            com.google.devtools.ksp.symbol.KSVisitor r3 = (com.google.devtools.ksp.symbol.KSVisitor) r3
            java.lang.Object r0 = r0.accept(r3, r8)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x003c
            return r2
        L_0x003c:
            java.util.List r0 = r7.getParameters()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            boolean r3 = r0 instanceof java.util.Collection
            r4 = 1
            if (r3 == 0) goto L_0x0052
            r3 = r0
            java.util.Collection r3 = (java.util.Collection) r3
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x0052
        L_0x0050:
            r1 = r4
            goto L_0x0071
        L_0x0052:
            java.util.Iterator r0 = r0.iterator()
        L_0x0056:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0050
            java.lang.Object r3 = r0.next()
            com.google.devtools.ksp.symbol.KSValueParameter r3 = (com.google.devtools.ksp.symbol.KSValueParameter) r3
            r5 = r6
            com.google.devtools.ksp.symbol.KSVisitor r5 = (com.google.devtools.ksp.symbol.KSVisitor) r5
            java.lang.Object r3 = r3.accept(r5, r7)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r3 != 0) goto L_0x0056
        L_0x0071:
            if (r1 != 0) goto L_0x0074
            return r2
        L_0x0074:
            com.google.devtools.ksp.symbol.KSDeclaration r7 = (com.google.devtools.ksp.symbol.KSDeclaration) r7
            java.lang.Boolean r7 = r6.visitDeclaration((com.google.devtools.ksp.symbol.KSDeclaration) r7, (com.google.devtools.ksp.symbol.KSNode) r8)
            boolean r7 = r7.booleanValue()
            if (r7 != 0) goto L_0x0081
            return r2
        L_0x0081:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r4)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.devtools.ksp.visitor.KSValidateVisitor.visitFunctionDeclaration(com.google.devtools.ksp.symbol.KSFunctionDeclaration, com.google.devtools.ksp.symbol.KSNode):java.lang.Boolean");
    }

    public Boolean visitPropertyDeclaration(KSPropertyDeclaration kSPropertyDeclaration, KSNode kSNode) {
        Intrinsics.checkNotNullParameter(kSPropertyDeclaration, "property");
        if ((!this.predicate.invoke(kSPropertyDeclaration, kSPropertyDeclaration.getType()).booleanValue() || ((Boolean) kSPropertyDeclaration.getType().accept(this, kSNode)).booleanValue()) && visitDeclaration((KSDeclaration) kSPropertyDeclaration, kSNode).booleanValue()) {
            return true;
        }
        return false;
    }

    private static final boolean visitValueArgument$visitValue(KSValidateVisitor kSValidateVisitor, KSNode kSNode, Object obj) {
        if (obj instanceof KSType) {
            return kSValidateVisitor.validateType((KSType) obj);
        }
        if (obj instanceof KSAnnotation) {
            return kSValidateVisitor.visitAnnotation((KSAnnotation) obj, kSNode).booleanValue();
        }
        if (obj instanceof List) {
            Iterable<Object> iterable = (Iterable) obj;
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                for (Object visitValueArgument$visitValue : iterable) {
                    if (!visitValueArgument$visitValue(kSValidateVisitor, kSNode, visitValueArgument$visitValue)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public Boolean visitValueArgument(KSValueArgument kSValueArgument, KSNode kSNode) {
        Intrinsics.checkNotNullParameter(kSValueArgument, "valueArgument");
        return Boolean.valueOf(visitValueArgument$visitValue(this, kSNode, kSValueArgument.getValue()));
    }

    public Boolean visitValueParameter(KSValueParameter kSValueParameter, KSNode kSNode) {
        Intrinsics.checkNotNullParameter(kSValueParameter, "valueParameter");
        return (Boolean) kSValueParameter.getType().accept(this, kSValueParameter);
    }
}
