package com.google.devtools.ksp.visitor;

import com.google.devtools.ksp.symbol.KSAnnotated;
import com.google.devtools.ksp.symbol.KSAnnotation;
import com.google.devtools.ksp.symbol.KSCallableReference;
import com.google.devtools.ksp.symbol.KSClassDeclaration;
import com.google.devtools.ksp.symbol.KSClassifierReference;
import com.google.devtools.ksp.symbol.KSDeclaration;
import com.google.devtools.ksp.symbol.KSDeclarationContainer;
import com.google.devtools.ksp.symbol.KSDefNonNullReference;
import com.google.devtools.ksp.symbol.KSFunctionDeclaration;
import com.google.devtools.ksp.symbol.KSNode;
import com.google.devtools.ksp.symbol.KSParenthesizedReference;
import com.google.devtools.ksp.symbol.KSPropertyDeclaration;
import com.google.devtools.ksp.symbol.KSPropertyGetter;
import com.google.devtools.ksp.symbol.KSPropertySetter;
import com.google.devtools.ksp.symbol.KSReferenceElement;
import com.google.devtools.ksp.symbol.KSTypeAlias;
import com.google.devtools.ksp.symbol.KSTypeArgument;
import com.google.devtools.ksp.symbol.KSTypeParameter;
import com.google.devtools.ksp.symbol.KSTypeReference;
import com.google.devtools.ksp.symbol.KSValueParameter;
import io.sentry.protocol.SentryStackFrame;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

@Metadata(d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u001d\u0010\u0005\u001a\u00028\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\tJ\u001d\u0010\n\u001a\u00028\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\rJ\u001d\u0010\u000e\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0011J\u001d\u0010\u0012\u001a\u00028\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0015J\u001d\u0010\u0016\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u00020\u00172\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0018J\u001d\u0010\u0019\u001a\u00028\u00012\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001cJ\u001d\u0010\u001d\u001a\u00028\u00012\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010 J\u001d\u0010!\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u00020\"2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010#J\u001d\u0010$\u001a\u00028\u00012\u0006\u0010%\u001a\u00020&2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010'J\u001d\u0010(\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u00020)2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010*J\u001d\u0010+\u001a\u00028\u00012\u0006\u0010,\u001a\u00020-2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010.J\u001d\u0010/\u001a\u00028\u00012\u0006\u00100\u001a\u0002012\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00102J\u001d\u00103\u001a\u00028\u00012\u0006\u00104\u001a\u0002052\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00106J\u001d\u00107\u001a\u00028\u00012\u0006\u00108\u001a\u0002092\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010:J\u001d\u0010;\u001a\u00028\u00012\u0006\u0010<\u001a\u00020=2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010>J\u001d\u0010?\u001a\u00028\u00012\u0006\u0010@\u001a\u00020A2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010BJ\u001d\u0010C\u001a\u00028\u00012\u0006\u0010D\u001a\u00020E2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010FJ\u001d\u0010G\u001a\u00028\u00012\u0006\u0010H\u001a\u00020I2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010JJ\u001d\u0010K\u001a\u00028\u00012\u0006\u0010L\u001a\u00020M2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010NJ\u0019\u0010O\u001a\u00028\u0001*\u00020P2\u0006\u0010\b\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010QJ\u001f\u0010O\u001a\u00020R*\b\u0012\u0004\u0012\u00020P0S2\u0006\u0010\b\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010TJ\u001f\u0010O\u001a\u00020R*\b\u0012\u0004\u0012\u00020P0U2\u0006\u0010\b\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010V¨\u0006W"}, d2 = {"Lcom/google/devtools/ksp/visitor/KSTopDownVisitor;", "D", "R", "Lcom/google/devtools/ksp/visitor/KSDefaultVisitor;", "()V", "visitAnnotated", "annotated", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "data", "(Lcom/google/devtools/ksp/symbol/KSAnnotated;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnnotation", "annotation", "Lcom/google/devtools/ksp/symbol/KSAnnotation;", "(Lcom/google/devtools/ksp/symbol/KSAnnotation;Ljava/lang/Object;)Ljava/lang/Object;", "visitCallableReference", "reference", "Lcom/google/devtools/ksp/symbol/KSCallableReference;", "(Lcom/google/devtools/ksp/symbol/KSCallableReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitClassDeclaration", "classDeclaration", "Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSClassDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitClassifierReference", "Lcom/google/devtools/ksp/symbol/KSClassifierReference;", "(Lcom/google/devtools/ksp/symbol/KSClassifierReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitDeclaration", "declaration", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitDeclarationContainer", "declarationContainer", "Lcom/google/devtools/ksp/symbol/KSDeclarationContainer;", "(Lcom/google/devtools/ksp/symbol/KSDeclarationContainer;Ljava/lang/Object;)Ljava/lang/Object;", "visitDefNonNullReference", "Lcom/google/devtools/ksp/symbol/KSDefNonNullReference;", "(Lcom/google/devtools/ksp/symbol/KSDefNonNullReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitFunctionDeclaration", "function", "Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitParenthesizedReference", "Lcom/google/devtools/ksp/symbol/KSParenthesizedReference;", "(Lcom/google/devtools/ksp/symbol/KSParenthesizedReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertyDeclaration", "property", "Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertyGetter", "getter", "Lcom/google/devtools/ksp/symbol/KSPropertyGetter;", "(Lcom/google/devtools/ksp/symbol/KSPropertyGetter;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertySetter", "setter", "Lcom/google/devtools/ksp/symbol/KSPropertySetter;", "(Lcom/google/devtools/ksp/symbol/KSPropertySetter;Ljava/lang/Object;)Ljava/lang/Object;", "visitReferenceElement", "element", "Lcom/google/devtools/ksp/symbol/KSReferenceElement;", "(Lcom/google/devtools/ksp/symbol/KSReferenceElement;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeAlias", "typeAlias", "Lcom/google/devtools/ksp/symbol/KSTypeAlias;", "(Lcom/google/devtools/ksp/symbol/KSTypeAlias;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeArgument", "typeArgument", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "(Lcom/google/devtools/ksp/symbol/KSTypeArgument;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeParameter", "typeParameter", "Lcom/google/devtools/ksp/symbol/KSTypeParameter;", "(Lcom/google/devtools/ksp/symbol/KSTypeParameter;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeReference", "typeReference", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "(Lcom/google/devtools/ksp/symbol/KSTypeReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitValueParameter", "valueParameter", "Lcom/google/devtools/ksp/symbol/KSValueParameter;", "(Lcom/google/devtools/ksp/symbol/KSValueParameter;Ljava/lang/Object;)Ljava/lang/Object;", "accept", "Lcom/google/devtools/ksp/symbol/KSNode;", "(Lcom/google/devtools/ksp/symbol/KSNode;Ljava/lang/Object;)Ljava/lang/Object;", "", "", "(Ljava/util/List;Ljava/lang/Object;)V", "Lkotlin/sequences/Sequence;", "(Lkotlin/sequences/Sequence;Ljava/lang/Object;)V", "api"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: KSTopDownVisitor.kt */
public abstract class KSTopDownVisitor<D, R> extends KSDefaultVisitor<D, R> {
    private final void accept(List<? extends KSNode> list, D d) {
        for (KSNode accept : list) {
            accept.accept(this, d);
        }
    }

    private final R accept(KSNode kSNode, D d) {
        return kSNode.accept(this, d);
    }

    public R visitPropertyDeclaration(KSPropertyDeclaration kSPropertyDeclaration, D d) {
        Intrinsics.checkNotNullParameter(kSPropertyDeclaration, "property");
        accept((KSNode) kSPropertyDeclaration.getType(), d);
        KSTypeReference extensionReceiver = kSPropertyDeclaration.getExtensionReceiver();
        if (extensionReceiver != null) {
            accept((KSNode) extensionReceiver, d);
        }
        KSPropertyGetter getter = kSPropertyDeclaration.getGetter();
        if (getter != null) {
            accept((KSNode) getter, d);
        }
        KSPropertySetter setter = kSPropertyDeclaration.getSetter();
        if (setter != null) {
            accept((KSNode) setter, d);
        }
        return super.visitPropertyDeclaration(kSPropertyDeclaration, d);
    }

    public R visitAnnotated(KSAnnotated kSAnnotated, D d) {
        Intrinsics.checkNotNullParameter(kSAnnotated, "annotated");
        accept((Sequence<? extends KSNode>) kSAnnotated.getAnnotations(), d);
        return super.visitAnnotated(kSAnnotated, d);
    }

    public R visitClassDeclaration(KSClassDeclaration kSClassDeclaration, D d) {
        Intrinsics.checkNotNullParameter(kSClassDeclaration, "classDeclaration");
        accept((Sequence<? extends KSNode>) kSClassDeclaration.getSuperTypes(), d);
        return super.visitClassDeclaration(kSClassDeclaration, d);
    }

    public R visitDeclaration(KSDeclaration kSDeclaration, D d) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "declaration");
        accept((List<? extends KSNode>) kSDeclaration.getTypeParameters(), d);
        return super.visitDeclaration(kSDeclaration, d);
    }

    public R visitDeclarationContainer(KSDeclarationContainer kSDeclarationContainer, D d) {
        Intrinsics.checkNotNullParameter(kSDeclarationContainer, "declarationContainer");
        accept((Sequence<? extends KSNode>) kSDeclarationContainer.getDeclarations(), d);
        return super.visitDeclarationContainer(kSDeclarationContainer, d);
    }

    public R visitAnnotation(KSAnnotation kSAnnotation, D d) {
        Intrinsics.checkNotNullParameter(kSAnnotation, "annotation");
        accept((KSNode) kSAnnotation.getAnnotationType(), d);
        accept((List<? extends KSNode>) kSAnnotation.getArguments(), d);
        return super.visitAnnotation(kSAnnotation, d);
    }

    public R visitFunctionDeclaration(KSFunctionDeclaration kSFunctionDeclaration, D d) {
        Intrinsics.checkNotNullParameter(kSFunctionDeclaration, SentryStackFrame.JsonKeys.FUNCTION);
        KSTypeReference extensionReceiver = kSFunctionDeclaration.getExtensionReceiver();
        if (extensionReceiver != null) {
            accept((KSNode) extensionReceiver, d);
        }
        accept((List<? extends KSNode>) kSFunctionDeclaration.getParameters(), d);
        KSTypeReference returnType = kSFunctionDeclaration.getReturnType();
        if (returnType != null) {
            accept((KSNode) returnType, d);
        }
        return super.visitFunctionDeclaration(kSFunctionDeclaration, d);
    }

    public R visitCallableReference(KSCallableReference kSCallableReference, D d) {
        Intrinsics.checkNotNullParameter(kSCallableReference, "reference");
        accept((List<? extends KSNode>) kSCallableReference.getFunctionParameters(), d);
        KSTypeReference receiverType = kSCallableReference.getReceiverType();
        if (receiverType != null) {
            accept((KSNode) receiverType, d);
        }
        accept((KSNode) kSCallableReference.getReturnType(), d);
        return super.visitCallableReference(kSCallableReference, d);
    }

    public R visitParenthesizedReference(KSParenthesizedReference kSParenthesizedReference, D d) {
        Intrinsics.checkNotNullParameter(kSParenthesizedReference, "reference");
        accept((KSNode) kSParenthesizedReference.getElement(), d);
        return super.visitParenthesizedReference(kSParenthesizedReference, d);
    }

    public R visitPropertyGetter(KSPropertyGetter kSPropertyGetter, D d) {
        Intrinsics.checkNotNullParameter(kSPropertyGetter, "getter");
        KSTypeReference returnType = kSPropertyGetter.getReturnType();
        if (returnType != null) {
            accept((KSNode) returnType, d);
        }
        return super.visitPropertyGetter(kSPropertyGetter, d);
    }

    public R visitPropertySetter(KSPropertySetter kSPropertySetter, D d) {
        Intrinsics.checkNotNullParameter(kSPropertySetter, "setter");
        accept((KSNode) kSPropertySetter.getParameter(), d);
        return super.visitPropertySetter(kSPropertySetter, d);
    }

    public R visitReferenceElement(KSReferenceElement kSReferenceElement, D d) {
        Intrinsics.checkNotNullParameter(kSReferenceElement, "element");
        accept((List<? extends KSNode>) kSReferenceElement.getTypeArguments(), d);
        return super.visitReferenceElement(kSReferenceElement, d);
    }

    public R visitTypeAlias(KSTypeAlias kSTypeAlias, D d) {
        Intrinsics.checkNotNullParameter(kSTypeAlias, "typeAlias");
        accept((KSNode) kSTypeAlias.getType(), d);
        return super.visitTypeAlias(kSTypeAlias, d);
    }

    public R visitTypeArgument(KSTypeArgument kSTypeArgument, D d) {
        Intrinsics.checkNotNullParameter(kSTypeArgument, "typeArgument");
        KSTypeReference type = kSTypeArgument.getType();
        if (type != null) {
            accept((KSNode) type, d);
        }
        return super.visitTypeArgument(kSTypeArgument, d);
    }

    public R visitTypeParameter(KSTypeParameter kSTypeParameter, D d) {
        Intrinsics.checkNotNullParameter(kSTypeParameter, "typeParameter");
        accept((Sequence<? extends KSNode>) kSTypeParameter.getBounds(), d);
        return super.visitTypeParameter(kSTypeParameter, d);
    }

    public R visitTypeReference(KSTypeReference kSTypeReference, D d) {
        Intrinsics.checkNotNullParameter(kSTypeReference, "typeReference");
        KSReferenceElement element = kSTypeReference.getElement();
        if (element != null) {
            accept((KSNode) element, d);
        }
        return super.visitTypeReference(kSTypeReference, d);
    }

    public R visitClassifierReference(KSClassifierReference kSClassifierReference, D d) {
        Intrinsics.checkNotNullParameter(kSClassifierReference, "reference");
        KSClassifierReference qualifier = kSClassifierReference.getQualifier();
        if (qualifier != null) {
            accept((KSNode) qualifier, d);
        }
        return super.visitClassifierReference(kSClassifierReference, d);
    }

    public R visitDefNonNullReference(KSDefNonNullReference kSDefNonNullReference, D d) {
        Intrinsics.checkNotNullParameter(kSDefNonNullReference, "reference");
        accept((KSNode) kSDefNonNullReference.getEnclosedType(), d);
        return super.visitDefNonNullReference(kSDefNonNullReference, d);
    }

    public R visitValueParameter(KSValueParameter kSValueParameter, D d) {
        Intrinsics.checkNotNullParameter(kSValueParameter, "valueParameter");
        KSTypeReference type = kSValueParameter.getType();
        if (type != null) {
            accept((KSNode) type, d);
        }
        return super.visitValueParameter(kSValueParameter, d);
    }

    private final void accept(Sequence<? extends KSNode> sequence, D d) {
        for (KSNode accept : sequence) {
            accept.accept(this, d);
        }
    }
}
