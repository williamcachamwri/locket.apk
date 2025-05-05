package com.google.devtools.ksp;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.google.devtools.ksp.processing.Resolver;
import com.google.devtools.ksp.symbol.ClassKind;
import com.google.devtools.ksp.symbol.KSAnnotated;
import com.google.devtools.ksp.symbol.KSAnnotation;
import com.google.devtools.ksp.symbol.KSClassDeclaration;
import com.google.devtools.ksp.symbol.KSDeclaration;
import com.google.devtools.ksp.symbol.KSFile;
import com.google.devtools.ksp.symbol.KSFunctionDeclaration;
import com.google.devtools.ksp.symbol.KSName;
import com.google.devtools.ksp.symbol.KSNode;
import com.google.devtools.ksp.symbol.KSPropertyDeclaration;
import com.google.devtools.ksp.symbol.KSPropertyGetter;
import com.google.devtools.ksp.symbol.KSPropertySetter;
import com.google.devtools.ksp.symbol.KSType;
import com.google.devtools.ksp.symbol.KSTypeAlias;
import com.google.devtools.ksp.symbol.KSTypeArgument;
import com.google.devtools.ksp.symbol.KSTypeParameter;
import com.google.devtools.ksp.symbol.KSValueArgument;
import com.google.devtools.ksp.symbol.Modifier;
import com.google.devtools.ksp.symbol.Origin;
import com.google.devtools.ksp.symbol.Visibility;
import com.google.devtools.ksp.visitor.KSValidateVisitor;
import io.sentry.profilemeasurements.ProfileMeasurement;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000Ø\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0003\u001a \u0010\u0015\u001a\u00020\u0011*\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0003\u001a$\u0010\u0015\u001a\u00020\u0011*\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0003\u001a\f\u0010\u0019\u001a\u00020\u001a*\u00020\u0011H\u0002\u001a(\u0010\u001b\u001a\u0012\u0012\u0002\b\u0003 \u001c*\b\u0012\u0002\b\u0003\u0018\u00010\u00140\u0014*\u00020\n2\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0003\u001a4\u0010\u001d\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \u001c*\b\u0012\u0002\b\u0003\u0018\u00010\u00140\u00140\b*\b\u0012\u0004\u0012\u00020\n0\b2\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0003\u001a\f\u0010\u001e\u001a\u00020\u001f*\u00020\u0011H\u0002\u001a%\u0010 \u001a\u0002H!\"\u0004\b\u0000\u0010!*\u00020\u00112\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H!0\u0014H\u0002¢\u0006\u0002\u0010#\u001a\f\u0010$\u001a\u00020%*\u00020\u0011H\u0002\u001a\f\u0010&\u001a\u00020'*\u00020\u0011H\u0002\u001a\f\u0010(\u001a\u00020)*\u00020\u0011H\u0002\u001a\f\u0010*\u001a\u0004\u0018\u00010+*\u00020,\u001a\u0018\u0010-\u001a\u00020.*\u00020\u00122\n\u0010/\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0003\u001a\n\u00100\u001a\u00020+*\u000201\u001a\u0010\u00102\u001a\b\u0012\u0004\u0012\u00020\n03*\u00020+\u001a*\u00104\u001a\b\u0012\u0004\u0012\u0002H!03\"\b\b\u0000\u0010!*\u000205*\u0002062\f\u00107\u001a\b\u0012\u0004\u0012\u0002H!08H\u0007\u001a\u0017\u00109\u001a\u0004\u0018\u00010+\"\u0006\b\u0000\u0010!\u0018\u0001*\u00020:H\b\u001a\u0014\u00109\u001a\u0004\u0018\u00010+*\u00020:2\u0006\u0010;\u001a\u00020\u0001\u001a\u0010\u0010<\u001a\b\u0012\u0004\u0012\u00020=03*\u00020+\u001a\u0010\u0010>\u001a\b\u0012\u0004\u0012\u00020=03*\u00020+\u001a\u0010\u0010?\u001a\b\u0012\u0004\u0012\u00020@03*\u00020+\u001a\"\u0010A\u001a\b\u0012\u0004\u0012\u00020=03*\u00020:2\u0006\u0010;\u001a\u00020\u00012\b\b\u0002\u0010B\u001a\u00020C\u001a\u0016\u0010D\u001a\u0004\u0018\u00010+*\u00020:2\u0006\u0010;\u001a\u00020EH\u0007\u001a\u0016\u0010D\u001a\u0004\u0018\u00010+*\u00020:2\u0006\u0010;\u001a\u00020\u0001H\u0007\u001a\u0016\u0010F\u001a\u0004\u0018\u00010+*\u00020:2\u0006\u0010;\u001a\u00020EH\u0007\u001a\u0016\u0010F\u001a\u0004\u0018\u00010+*\u00020:2\u0006\u0010;\u001a\u00020\u0001H\u0007\u001a\u001e\u0010G\u001a\u0004\u0018\u00010@*\u00020:2\u0006\u0010;\u001a\u00020\u00012\b\b\u0002\u0010B\u001a\u00020C\u001a\n\u0010H\u001a\u00020I*\u00020,\u001a\n\u0010J\u001a\u00020C*\u00020+\u001a\n\u0010J\u001a\u00020C*\u00020@\u001a$\u0010K\u001a\u00020C\"\b\b\u0000\u0010!*\u000205*\u0002062\f\u00107\u001a\b\u0012\u0004\u0012\u0002H!08H\u0007\u001a\n\u0010L\u001a\u00020C*\u00020=\u001a\n\u0010M\u001a\u00020C*\u00020N\u001a\n\u0010O\u001a\u00020C*\u00020,\u001a\n\u0010P\u001a\u00020C*\u00020,\u001a\n\u0010Q\u001a\u00020C*\u00020,\u001a\n\u0010R\u001a\u00020C*\u00020,\u001a\n\u0010S\u001a\u00020C*\u00020,\u001a\n\u0010T\u001a\u00020C*\u00020,\u001a\n\u0010U\u001a\u00020C*\u00020,\u001a\u0012\u0010V\u001a\u00020C*\u00020,2\u0006\u0010W\u001a\u00020,\u001a)\u0010X\u001a\u0002H!\"\b\b\u0000\u0010!*\u000205*\u00020\u00122\f\u0010Y\u001a\b\u0012\u0004\u0012\u0002H!0\u0014H\u0003¢\u0006\u0002\u0010Z\u001a9\u0010[\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\\*\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u0016\u001a\u00020\u00172\u0012\u0010]\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110^H\u0002¢\u0006\u0002\u0010_\u001a(\u0010`\u001a\u00020C*\u00020\u00042\u001c\b\u0002\u0010a\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020C0b\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0017\u0010\u0002\u001a\u0004\u0018\u00010\u0003*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"\u001b\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b*\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u0017\u0010\r\u001a\u0004\u0018\u00010\n*\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006c"}, d2 = {"ExceptionMessage", "", "containingFile", "Lcom/google/devtools/ksp/symbol/KSFile;", "Lcom/google/devtools/ksp/symbol/KSNode;", "getContainingFile", "(Lcom/google/devtools/ksp/symbol/KSNode;)Lcom/google/devtools/ksp/symbol/KSFile;", "innerArguments", "", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "Lcom/google/devtools/ksp/symbol/KSType;", "getInnerArguments", "(Lcom/google/devtools/ksp/symbol/KSType;)Ljava/util/List;", "outerType", "getOuterType", "(Lcom/google/devtools/ksp/symbol/KSType;)Lcom/google/devtools/ksp/symbol/KSType;", "asAnnotation", "", "Lcom/google/devtools/ksp/symbol/KSAnnotation;", "annotationInterface", "Ljava/lang/Class;", "asArray", "method", "Ljava/lang/reflect/Method;", "proxyClass", "asByte", "", "asClass", "kotlin.jvm.PlatformType", "asClasses", "asDouble", "", "asEnum", "T", "returnType", "(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;", "asFloat", "", "asLong", "", "asShort", "", "closestClassDeclaration", "Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "createInvocationHandler", "Ljava/lang/reflect/InvocationHandler;", "clazz", "findActualType", "Lcom/google/devtools/ksp/symbol/KSTypeAlias;", "getAllSuperTypes", "Lkotlin/sequences/Sequence;", "getAnnotationsByType", "", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "annotationKClass", "Lkotlin/reflect/KClass;", "getClassDeclarationByName", "Lcom/google/devtools/ksp/processing/Resolver;", "name", "getConstructors", "Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;", "getDeclaredFunctions", "getDeclaredProperties", "Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;", "getFunctionDeclarationsByName", "includeTopLevel", "", "getJavaClassByName", "Lcom/google/devtools/ksp/symbol/KSName;", "getKotlinClassByName", "getPropertyDeclarationByName", "getVisibility", "Lcom/google/devtools/ksp/symbol/Visibility;", "isAbstract", "isAnnotationPresent", "isConstructor", "isDefault", "Lcom/google/devtools/ksp/symbol/KSValueArgument;", "isInternal", "isJavaPackagePrivate", "isLocal", "isOpen", "isPrivate", "isProtected", "isPublic", "isVisibleFrom", "other", "toAnnotation", "annotationClass", "(Lcom/google/devtools/ksp/symbol/KSAnnotation;Ljava/lang/Class;)Ljava/lang/annotation/Annotation;", "toArray", "", "valueProvider", "Lkotlin/Function1;", "(Ljava/util/List;Ljava/lang/reflect/Method;Lkotlin/jvm/functions/Function1;)[Ljava/lang/Object;", "validate", "predicate", "Lkotlin/Function2;", "api"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: utils.kt */
public final class UtilsKt {
    public static final String ExceptionMessage = "please file a bug at https://github.com/google/ksp/issues/new";

    public static final /* synthetic */ <T> KSClassDeclaration getClassDeclarationByName(Resolver resolver) {
        Intrinsics.checkNotNullParameter(resolver, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        String qualifiedName = Reflection.getOrCreateKotlinClass(Object.class).getQualifiedName();
        if (qualifiedName == null) {
            return null;
        }
        String str = qualifiedName;
        KSClassDeclaration classDeclarationByName = resolver.getClassDeclarationByName(resolver.getKSNameFromString(qualifiedName));
        KSClassDeclaration kSClassDeclaration = classDeclarationByName;
        return classDeclarationByName;
    }

    public static final KSClassDeclaration getClassDeclarationByName(Resolver resolver, String str) {
        Intrinsics.checkNotNullParameter(resolver, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        return resolver.getClassDeclarationByName(resolver.getKSNameFromString(str));
    }

    public static /* synthetic */ Sequence getFunctionDeclarationsByName$default(Resolver resolver, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return getFunctionDeclarationsByName(resolver, str, z);
    }

    public static final Sequence<KSFunctionDeclaration> getFunctionDeclarationsByName(Resolver resolver, String str, boolean z) {
        Intrinsics.checkNotNullParameter(resolver, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        return resolver.getFunctionDeclarationsByName(resolver.getKSNameFromString(str), z);
    }

    public static /* synthetic */ KSPropertyDeclaration getPropertyDeclarationByName$default(Resolver resolver, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return getPropertyDeclarationByName(resolver, str, z);
    }

    public static final KSPropertyDeclaration getPropertyDeclarationByName(Resolver resolver, String str, boolean z) {
        Intrinsics.checkNotNullParameter(resolver, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        return resolver.getPropertyDeclarationByName(resolver.getKSNameFromString(str), z);
    }

    public static final KSFile getContainingFile(KSNode kSNode) {
        Intrinsics.checkNotNullParameter(kSNode, "<this>");
        KSNode parent = kSNode.getParent();
        while (parent != null && !(parent instanceof KSFile)) {
            parent = parent.getParent();
        }
        if (parent instanceof KSFile) {
            return (KSFile) parent;
        }
        return null;
    }

    public static final Sequence<KSFunctionDeclaration> getDeclaredFunctions(KSClassDeclaration kSClassDeclaration) {
        Intrinsics.checkNotNullParameter(kSClassDeclaration, "<this>");
        Sequence<KSFunctionDeclaration> filter = SequencesKt.filter(kSClassDeclaration.getDeclarations(), UtilsKt$getDeclaredFunctions$$inlined$filterIsInstance$1.INSTANCE);
        Intrinsics.checkNotNull(filter, "null cannot be cast to non-null type kotlin.sequences.Sequence<R of kotlin.sequences.SequencesKt___SequencesKt.filterIsInstance>");
        return filter;
    }

    public static final Sequence<KSPropertyDeclaration> getDeclaredProperties(KSClassDeclaration kSClassDeclaration) {
        Intrinsics.checkNotNullParameter(kSClassDeclaration, "<this>");
        Sequence<KSPropertyDeclaration> filter = SequencesKt.filter(kSClassDeclaration.getDeclarations(), UtilsKt$getDeclaredProperties$$inlined$filterIsInstance$1.INSTANCE);
        Intrinsics.checkNotNull(filter, "null cannot be cast to non-null type kotlin.sequences.Sequence<R of kotlin.sequences.SequencesKt___SequencesKt.filterIsInstance>");
        return filter;
    }

    public static final Sequence<KSFunctionDeclaration> getConstructors(KSClassDeclaration kSClassDeclaration) {
        Intrinsics.checkNotNullParameter(kSClassDeclaration, "<this>");
        return SequencesKt.filter(getDeclaredFunctions(kSClassDeclaration), UtilsKt$getConstructors$1.INSTANCE);
    }

    public static final boolean isLocal(KSDeclaration kSDeclaration) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "<this>");
        return kSDeclaration.getParentDeclaration() != null && !(kSDeclaration.getParentDeclaration() instanceof KSClassDeclaration);
    }

    public static /* synthetic */ boolean validate$default(KSNode kSNode, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function2 = UtilsKt$validate$1.INSTANCE;
        }
        return validate(kSNode, function2);
    }

    public static final boolean validate(KSNode kSNode, Function2<? super KSNode, ? super KSNode, Boolean> function2) {
        Intrinsics.checkNotNullParameter(kSNode, "<this>");
        Intrinsics.checkNotNullParameter(function2, "predicate");
        return ((Boolean) kSNode.accept(new KSValidateVisitor(function2), null)).booleanValue();
    }

    public static final KSClassDeclaration findActualType(KSTypeAlias kSTypeAlias) {
        Intrinsics.checkNotNullParameter(kSTypeAlias, "<this>");
        KSDeclaration declaration = kSTypeAlias.getType().resolve().getDeclaration();
        if (declaration instanceof KSTypeAlias) {
            return findActualType((KSTypeAlias) declaration);
        }
        Intrinsics.checkNotNull(declaration, "null cannot be cast to non-null type com.google.devtools.ksp.symbol.KSClassDeclaration");
        return (KSClassDeclaration) declaration;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.google.devtools.ksp.symbol.Visibility getVisibility(com.google.devtools.ksp.symbol.KSDeclaration r2) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.util.Set r0 = r2.getModifiers()
            com.google.devtools.ksp.symbol.Modifier r1 = com.google.devtools.ksp.symbol.Modifier.PUBLIC
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L_0x0015
            com.google.devtools.ksp.symbol.Visibility r2 = com.google.devtools.ksp.symbol.Visibility.PUBLIC
            goto L_0x00a4
        L_0x0015:
            java.util.Set r0 = r2.getModifiers()
            com.google.devtools.ksp.symbol.Modifier r1 = com.google.devtools.ksp.symbol.Modifier.OVERRIDE
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L_0x004c
            boolean r0 = r2 instanceof com.google.devtools.ksp.symbol.KSFunctionDeclaration
            r1 = 0
            if (r0 == 0) goto L_0x0033
            com.google.devtools.ksp.symbol.KSFunctionDeclaration r2 = (com.google.devtools.ksp.symbol.KSFunctionDeclaration) r2
            com.google.devtools.ksp.symbol.KSDeclaration r2 = r2.findOverridee()
            if (r2 == 0) goto L_0x0046
            com.google.devtools.ksp.symbol.Visibility r2 = getVisibility(r2)
            goto L_0x0047
        L_0x0033:
            boolean r0 = r2 instanceof com.google.devtools.ksp.symbol.KSPropertyDeclaration
            if (r0 == 0) goto L_0x0046
            com.google.devtools.ksp.symbol.KSPropertyDeclaration r2 = (com.google.devtools.ksp.symbol.KSPropertyDeclaration) r2
            com.google.devtools.ksp.symbol.KSPropertyDeclaration r2 = r2.findOverridee()
            if (r2 == 0) goto L_0x0046
            com.google.devtools.ksp.symbol.KSDeclaration r2 = (com.google.devtools.ksp.symbol.KSDeclaration) r2
            com.google.devtools.ksp.symbol.Visibility r2 = getVisibility(r2)
            goto L_0x0047
        L_0x0046:
            r2 = r1
        L_0x0047:
            if (r2 != 0) goto L_0x00a4
            com.google.devtools.ksp.symbol.Visibility r2 = com.google.devtools.ksp.symbol.Visibility.PUBLIC
            goto L_0x00a4
        L_0x004c:
            boolean r0 = isLocal(r2)
            if (r0 == 0) goto L_0x0055
            com.google.devtools.ksp.symbol.Visibility r2 = com.google.devtools.ksp.symbol.Visibility.LOCAL
            goto L_0x00a4
        L_0x0055:
            java.util.Set r0 = r2.getModifiers()
            com.google.devtools.ksp.symbol.Modifier r1 = com.google.devtools.ksp.symbol.Modifier.PRIVATE
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L_0x0064
            com.google.devtools.ksp.symbol.Visibility r2 = com.google.devtools.ksp.symbol.Visibility.PRIVATE
            goto L_0x00a4
        L_0x0064:
            java.util.Set r0 = r2.getModifiers()
            com.google.devtools.ksp.symbol.Modifier r1 = com.google.devtools.ksp.symbol.Modifier.PROTECTED
            boolean r0 = r0.contains(r1)
            if (r0 != 0) goto L_0x00a2
            java.util.Set r0 = r2.getModifiers()
            com.google.devtools.ksp.symbol.Modifier r1 = com.google.devtools.ksp.symbol.Modifier.OVERRIDE
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L_0x007d
            goto L_0x00a2
        L_0x007d:
            java.util.Set r0 = r2.getModifiers()
            com.google.devtools.ksp.symbol.Modifier r1 = com.google.devtools.ksp.symbol.Modifier.INTERNAL
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L_0x008c
            com.google.devtools.ksp.symbol.Visibility r2 = com.google.devtools.ksp.symbol.Visibility.INTERNAL
            goto L_0x00a4
        L_0x008c:
            com.google.devtools.ksp.symbol.Origin r0 = r2.getOrigin()
            com.google.devtools.ksp.symbol.Origin r1 = com.google.devtools.ksp.symbol.Origin.JAVA
            if (r0 == r1) goto L_0x009f
            com.google.devtools.ksp.symbol.Origin r2 = r2.getOrigin()
            com.google.devtools.ksp.symbol.Origin r0 = com.google.devtools.ksp.symbol.Origin.JAVA_LIB
            if (r2 == r0) goto L_0x009f
            com.google.devtools.ksp.symbol.Visibility r2 = com.google.devtools.ksp.symbol.Visibility.PUBLIC
            goto L_0x00a4
        L_0x009f:
            com.google.devtools.ksp.symbol.Visibility r2 = com.google.devtools.ksp.symbol.Visibility.JAVA_PACKAGE
            goto L_0x00a4
        L_0x00a2:
            com.google.devtools.ksp.symbol.Visibility r2 = com.google.devtools.ksp.symbol.Visibility.PROTECTED
        L_0x00a4:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.devtools.ksp.UtilsKt.getVisibility(com.google.devtools.ksp.symbol.KSDeclaration):com.google.devtools.ksp.symbol.Visibility");
    }

    /* access modifiers changed from: private */
    public static final Sequence<KSClassDeclaration> getAllSuperTypes$getTypesUpperBound(KSTypeParameter kSTypeParameter) {
        return SequencesKt.flatMap(kSTypeParameter.getBounds(), UtilsKt$getAllSuperTypes$getTypesUpperBound$1.INSTANCE);
    }

    public static final Sequence<KSType> getAllSuperTypes(KSClassDeclaration kSClassDeclaration) {
        Intrinsics.checkNotNullParameter(kSClassDeclaration, "<this>");
        return SequencesKt.distinct(SequencesKt.plus(SequencesKt.map(kSClassDeclaration.getSuperTypes(), UtilsKt$getAllSuperTypes$1.INSTANCE), SequencesKt.flatMap(SequencesKt.mapNotNull(kSClassDeclaration.getSuperTypes(), UtilsKt$getAllSuperTypes$2.INSTANCE), UtilsKt$getAllSuperTypes$3.INSTANCE)));
    }

    public static final boolean isAbstract(KSClassDeclaration kSClassDeclaration) {
        Intrinsics.checkNotNullParameter(kSClassDeclaration, "<this>");
        return kSClassDeclaration.getClassKind() == ClassKind.INTERFACE || kSClassDeclaration.getModifiers().contains(Modifier.ABSTRACT);
    }

    public static final boolean isAbstract(KSPropertyDeclaration kSPropertyDeclaration) {
        Set<Modifier> modifiers;
        Set<Modifier> modifiers2;
        Intrinsics.checkNotNullParameter(kSPropertyDeclaration, "<this>");
        if (kSPropertyDeclaration.getModifiers().contains(Modifier.ABSTRACT)) {
            return true;
        }
        KSDeclaration parentDeclaration = kSPropertyDeclaration.getParentDeclaration();
        KSClassDeclaration kSClassDeclaration = parentDeclaration instanceof KSClassDeclaration ? (KSClassDeclaration) parentDeclaration : null;
        if (kSClassDeclaration == null || kSClassDeclaration.getClassKind() != ClassKind.INTERFACE) {
            return false;
        }
        KSPropertyGetter getter = kSPropertyDeclaration.getGetter();
        if ((getter == null || (modifiers2 = getter.getModifiers()) == null) ? true : modifiers2.contains(Modifier.ABSTRACT)) {
            KSPropertySetter setter = kSPropertyDeclaration.getSetter();
            if ((setter == null || (modifiers = setter.getModifiers()) == null) ? true : modifiers.contains(Modifier.ABSTRACT)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0067, code lost:
        if (r1 != com.google.devtools.ksp.symbol.ClassKind.INTERFACE) goto L_0x0069;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean isOpen(com.google.devtools.ksp.symbol.KSDeclaration r4) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            boolean r0 = isLocal(r4)
            if (r0 != 0) goto L_0x007f
            boolean r0 = r4 instanceof com.google.devtools.ksp.symbol.KSClassDeclaration
            r1 = 0
            if (r0 == 0) goto L_0x0014
            r2 = r4
            com.google.devtools.ksp.symbol.KSClassDeclaration r2 = (com.google.devtools.ksp.symbol.KSClassDeclaration) r2
            goto L_0x0015
        L_0x0014:
            r2 = r1
        L_0x0015:
            if (r2 == 0) goto L_0x001c
            com.google.devtools.ksp.symbol.ClassKind r2 = r2.getClassKind()
            goto L_0x001d
        L_0x001c:
            r2 = r1
        L_0x001d:
            com.google.devtools.ksp.symbol.ClassKind r3 = com.google.devtools.ksp.symbol.ClassKind.INTERFACE
            if (r2 == r3) goto L_0x007d
            java.util.Set r2 = r4.getModifiers()
            com.google.devtools.ksp.symbol.Modifier r3 = com.google.devtools.ksp.symbol.Modifier.OVERRIDE
            boolean r2 = r2.contains(r3)
            if (r2 != 0) goto L_0x007d
            java.util.Set r2 = r4.getModifiers()
            com.google.devtools.ksp.symbol.Modifier r3 = com.google.devtools.ksp.symbol.Modifier.ABSTRACT
            boolean r2 = r2.contains(r3)
            if (r2 != 0) goto L_0x007d
            java.util.Set r2 = r4.getModifiers()
            com.google.devtools.ksp.symbol.Modifier r3 = com.google.devtools.ksp.symbol.Modifier.OPEN
            boolean r2 = r2.contains(r3)
            if (r2 != 0) goto L_0x007d
            java.util.Set r2 = r4.getModifiers()
            com.google.devtools.ksp.symbol.Modifier r3 = com.google.devtools.ksp.symbol.Modifier.SEALED
            boolean r2 = r2.contains(r3)
            if (r2 != 0) goto L_0x007d
            if (r0 != 0) goto L_0x0069
            com.google.devtools.ksp.symbol.KSDeclaration r0 = r4.getParentDeclaration()
            boolean r2 = r0 instanceof com.google.devtools.ksp.symbol.KSClassDeclaration
            if (r2 == 0) goto L_0x005e
            com.google.devtools.ksp.symbol.KSClassDeclaration r0 = (com.google.devtools.ksp.symbol.KSClassDeclaration) r0
            goto L_0x005f
        L_0x005e:
            r0 = r1
        L_0x005f:
            if (r0 == 0) goto L_0x0065
            com.google.devtools.ksp.symbol.ClassKind r1 = r0.getClassKind()
        L_0x0065:
            com.google.devtools.ksp.symbol.ClassKind r0 = com.google.devtools.ksp.symbol.ClassKind.INTERFACE
            if (r1 == r0) goto L_0x007d
        L_0x0069:
            java.util.Set r0 = r4.getModifiers()
            com.google.devtools.ksp.symbol.Modifier r1 = com.google.devtools.ksp.symbol.Modifier.FINAL
            boolean r0 = r0.contains(r1)
            if (r0 != 0) goto L_0x007f
            com.google.devtools.ksp.symbol.Origin r4 = r4.getOrigin()
            com.google.devtools.ksp.symbol.Origin r0 = com.google.devtools.ksp.symbol.Origin.JAVA
            if (r4 != r0) goto L_0x007f
        L_0x007d:
            r4 = 1
            goto L_0x0080
        L_0x007f:
            r4 = 0
        L_0x0080:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.devtools.ksp.UtilsKt.isOpen(com.google.devtools.ksp.symbol.KSDeclaration):boolean");
    }

    public static final boolean isPublic(KSDeclaration kSDeclaration) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "<this>");
        return getVisibility(kSDeclaration) == Visibility.PUBLIC;
    }

    public static final boolean isProtected(KSDeclaration kSDeclaration) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "<this>");
        return getVisibility(kSDeclaration) == Visibility.PROTECTED;
    }

    public static final boolean isInternal(KSDeclaration kSDeclaration) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "<this>");
        return kSDeclaration.getModifiers().contains(Modifier.INTERNAL);
    }

    public static final boolean isPrivate(KSDeclaration kSDeclaration) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "<this>");
        return kSDeclaration.getModifiers().contains(Modifier.PRIVATE);
    }

    public static final boolean isJavaPackagePrivate(KSDeclaration kSDeclaration) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "<this>");
        return getVisibility(kSDeclaration) == Visibility.JAVA_PACKAGE;
    }

    public static final KSClassDeclaration closestClassDeclaration(KSDeclaration kSDeclaration) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "<this>");
        if (kSDeclaration instanceof KSClassDeclaration) {
            return (KSClassDeclaration) kSDeclaration;
        }
        KSDeclaration parentDeclaration = kSDeclaration.getParentDeclaration();
        if (parentDeclaration != null) {
            return closestClassDeclaration(parentDeclaration);
        }
        return null;
    }

    private static final boolean isVisibleFrom$isSamePackage(KSDeclaration kSDeclaration, KSDeclaration kSDeclaration2) {
        return Intrinsics.areEqual((Object) kSDeclaration.getPackageName(), (Object) kSDeclaration2.getPackageName());
    }

    private static final List<KSDeclaration> isVisibleFrom$parentDeclarationsForLocal(KSDeclaration kSDeclaration) {
        List<KSDeclaration> arrayList = new ArrayList<>();
        KSDeclaration parentDeclaration = kSDeclaration.getParentDeclaration();
        Intrinsics.checkNotNull(parentDeclaration);
        while (isLocal(parentDeclaration)) {
            arrayList.add(parentDeclaration);
            parentDeclaration = parentDeclaration.getParentDeclaration();
            Intrinsics.checkNotNull(parentDeclaration);
        }
        arrayList.add(parentDeclaration);
        return arrayList;
    }

    private static final boolean isVisibleFrom$isVisibleInPrivate(KSDeclaration kSDeclaration, KSDeclaration kSDeclaration2) {
        return (isLocal(kSDeclaration2) && CollectionsKt.contains(isVisibleFrom$parentDeclarationsForLocal(kSDeclaration2), kSDeclaration.getParentDeclaration())) || Intrinsics.areEqual((Object) kSDeclaration.getParentDeclaration(), (Object) kSDeclaration2.getParentDeclaration()) || Intrinsics.areEqual((Object) kSDeclaration.getParentDeclaration(), (Object) kSDeclaration2) || (kSDeclaration.getParentDeclaration() == null && kSDeclaration2.getParentDeclaration() == null && Intrinsics.areEqual((Object) kSDeclaration.getContainingFile(), (Object) kSDeclaration2.getContainingFile()));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x007a, code lost:
        if (r3 != false) goto L_0x002c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean isVisibleFrom(com.google.devtools.ksp.symbol.KSDeclaration r3, com.google.devtools.ksp.symbol.KSDeclaration r4) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "other"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            boolean r0 = isLocal(r3)
            if (r0 == 0) goto L_0x001a
            java.util.List r3 = isVisibleFrom$parentDeclarationsForLocal(r3)
            boolean r3 = r3.contains(r4)
            goto L_0x007e
        L_0x001a:
            boolean r0 = isPrivate(r3)
            if (r0 == 0) goto L_0x0025
            boolean r3 = isVisibleFrom$isVisibleInPrivate(r3, r4)
            goto L_0x007e
        L_0x0025:
            boolean r0 = isPublic(r3)
            r1 = 1
            if (r0 == 0) goto L_0x002e
        L_0x002c:
            r3 = r1
            goto L_0x007e
        L_0x002e:
            boolean r0 = isInternal(r3)
            if (r0 == 0) goto L_0x0041
            com.google.devtools.ksp.symbol.KSFile r0 = r4.getContainingFile()
            if (r0 == 0) goto L_0x0041
            com.google.devtools.ksp.symbol.KSFile r0 = r3.getContainingFile()
            if (r0 == 0) goto L_0x0041
            goto L_0x002c
        L_0x0041:
            boolean r0 = isJavaPackagePrivate(r3)
            if (r0 == 0) goto L_0x004c
            boolean r3 = isVisibleFrom$isSamePackage(r3, r4)
            goto L_0x007e
        L_0x004c:
            boolean r0 = isProtected(r3)
            r2 = 0
            if (r0 == 0) goto L_0x007d
            boolean r0 = isVisibleFrom$isVisibleInPrivate(r3, r4)
            if (r0 != 0) goto L_0x002c
            boolean r0 = isVisibleFrom$isSamePackage(r3, r4)
            if (r0 != 0) goto L_0x002c
            com.google.devtools.ksp.symbol.KSClassDeclaration r4 = closestClassDeclaration(r4)
            if (r4 == 0) goto L_0x0079
            com.google.devtools.ksp.symbol.KSClassDeclaration r3 = closestClassDeclaration(r3)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            com.google.devtools.ksp.symbol.KSType r3 = r3.asStarProjectedType()
            com.google.devtools.ksp.symbol.KSType r4 = r4.asStarProjectedType()
            boolean r3 = r3.isAssignableFrom(r4)
            goto L_0x007a
        L_0x0079:
            r3 = r2
        L_0x007a:
            if (r3 == 0) goto L_0x007d
            goto L_0x002c
        L_0x007d:
            r3 = r2
        L_0x007e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.devtools.ksp.UtilsKt.isVisibleFrom(com.google.devtools.ksp.symbol.KSDeclaration, com.google.devtools.ksp.symbol.KSDeclaration):boolean");
    }

    public static final boolean isConstructor(KSFunctionDeclaration kSFunctionDeclaration) {
        Intrinsics.checkNotNullParameter(kSFunctionDeclaration, "<this>");
        return Intrinsics.areEqual((Object) kSFunctionDeclaration.getSimpleName().asString(), (Object) "<init>");
    }

    public static final KSType getOuterType(KSType kSType) {
        Intrinsics.checkNotNullParameter(kSType, "<this>");
        if (!kSType.getDeclaration().getModifiers().contains(Modifier.INNER)) {
            return null;
        }
        KSDeclaration parentDeclaration = kSType.getDeclaration().getParentDeclaration();
        KSClassDeclaration kSClassDeclaration = parentDeclaration instanceof KSClassDeclaration ? (KSClassDeclaration) parentDeclaration : null;
        if (kSClassDeclaration == null) {
            return null;
        }
        return kSClassDeclaration.asType(kSType.getArguments().subList(kSType.getDeclaration().getTypeParameters().size(), kSType.getArguments().size()));
    }

    public static final List<KSTypeArgument> getInnerArguments(KSType kSType) {
        Intrinsics.checkNotNullParameter(kSType, "<this>");
        return kSType.getArguments().subList(0, kSType.getDeclaration().getTypeParameters().size());
    }

    public static final KSClassDeclaration getKotlinClassByName(Resolver resolver, KSName kSName) {
        Intrinsics.checkNotNullParameter(resolver, "<this>");
        Intrinsics.checkNotNullParameter(kSName, "name");
        KSName mapJavaNameToKotlin = resolver.mapJavaNameToKotlin(kSName);
        if (mapJavaNameToKotlin != null) {
            kSName = mapJavaNameToKotlin;
        }
        return resolver.getClassDeclarationByName(kSName);
    }

    public static final KSClassDeclaration getKotlinClassByName(Resolver resolver, String str) {
        Intrinsics.checkNotNullParameter(resolver, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        return getKotlinClassByName(resolver, resolver.getKSNameFromString(str));
    }

    public static final KSClassDeclaration getJavaClassByName(Resolver resolver, KSName kSName) {
        Intrinsics.checkNotNullParameter(resolver, "<this>");
        Intrinsics.checkNotNullParameter(kSName, "name");
        KSName mapKotlinNameToJava = resolver.mapKotlinNameToJava(kSName);
        if (mapKotlinNameToJava != null) {
            kSName = mapKotlinNameToJava;
        }
        return resolver.getClassDeclarationByName(kSName);
    }

    public static final KSClassDeclaration getJavaClassByName(Resolver resolver, String str) {
        Intrinsics.checkNotNullParameter(resolver, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        return getJavaClassByName(resolver, resolver.getKSNameFromString(str));
    }

    public static final <T extends Annotation> Sequence<T> getAnnotationsByType(KSAnnotated kSAnnotated, KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kSAnnotated, "<this>");
        Intrinsics.checkNotNullParameter(kClass, "annotationKClass");
        return SequencesKt.map(SequencesKt.filter(kSAnnotated.getAnnotations(), new UtilsKt$getAnnotationsByType$1(kClass)), new UtilsKt$getAnnotationsByType$2(kClass));
    }

    public static final <T extends Annotation> boolean isAnnotationPresent(KSAnnotated kSAnnotated, KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kSAnnotated, "<this>");
        Intrinsics.checkNotNullParameter(kClass, "annotationKClass");
        return SequencesKt.firstOrNull(getAnnotationsByType(kSAnnotated, kClass)) != null;
    }

    /* access modifiers changed from: private */
    public static final <T extends Annotation> T toAnnotation(KSAnnotation kSAnnotation, Class<T> cls) {
        T newProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, createInvocationHandler(kSAnnotation, cls));
        Intrinsics.checkNotNull(newProxyInstance, "null cannot be cast to non-null type T of com.google.devtools.ksp.UtilsKt.toAnnotation");
        return (Annotation) newProxyInstance;
    }

    private static final InvocationHandler createInvocationHandler(KSAnnotation kSAnnotation, Class<?> cls) {
        return new UtilsKt$$ExternalSyntheticLambda0(kSAnnotation, cls, new ConcurrentHashMap(kSAnnotation.getArguments().size()));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0180, code lost:
        r7 = r9.invoke();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object createInvocationHandler$lambda$8(com.google.devtools.ksp.symbol.KSAnnotation r6, java.lang.Class r7, java.util.concurrent.ConcurrentHashMap r8, java.lang.Object r9, java.lang.reflect.Method r10, java.lang.Object[] r11) {
        /*
            java.lang.String r11 = "$this_createInvocationHandler"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r11)
            java.lang.String r11 = "$clazz"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r11)
            java.lang.String r11 = "$cache"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r11)
            java.lang.String r11 = r10.getName()
            java.lang.String r0 = "toString"
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r0)
            r1 = 0
            r2 = 0
            if (r11 == 0) goto L_0x00f3
            java.util.List r11 = r6.getArguments()
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            boolean r3 = r11 instanceof java.util.Collection
            r4 = 1
            if (r3 == 0) goto L_0x0032
            r3 = r11
            java.util.Collection r3 = (java.util.Collection) r3
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x0032
            goto L_0x0055
        L_0x0032:
            java.util.Iterator r11 = r11.iterator()
        L_0x0036:
            boolean r3 = r11.hasNext()
            if (r3 == 0) goto L_0x0055
            java.lang.Object r3 = r11.next()
            com.google.devtools.ksp.symbol.KSValueArgument r3 = (com.google.devtools.ksp.symbol.KSValueArgument) r3
            com.google.devtools.ksp.symbol.KSName r3 = r3.getName()
            if (r3 == 0) goto L_0x004d
            java.lang.String r3 = r3.asString()
            goto L_0x004e
        L_0x004d:
            r3 = r2
        L_0x004e:
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r0)
            if (r3 == 0) goto L_0x0036
            r4 = r1
        L_0x0055:
            if (r4 == 0) goto L_0x00f3
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r7 = r7.getCanonicalName()
            java.lang.StringBuilder r7 = r8.append(r7)
            java.util.List r6 = r6.getArguments()
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.ArrayList r8 = new java.util.ArrayList
            r10 = 10
            int r10 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r6, r10)
            r8.<init>(r10)
            java.util.Collection r8 = (java.util.Collection) r8
            java.util.Iterator r6 = r6.iterator()
        L_0x007b:
            boolean r10 = r6.hasNext()
            if (r10 == 0) goto L_0x00e1
            java.lang.Object r10 = r6.next()
            com.google.devtools.ksp.symbol.KSValueArgument r10 = (com.google.devtools.ksp.symbol.KSValueArgument) r10
            com.google.devtools.ksp.symbol.KSName r10 = r10.getName()
            if (r10 == 0) goto L_0x0092
            java.lang.String r10 = r10.asString()
            goto L_0x0093
        L_0x0092:
            r10 = r2
        L_0x0093:
            java.lang.Class r11 = r9.getClass()
            java.lang.reflect.Method[] r11 = r11.getMethods()
            java.lang.String r0 = "proxy.javaClass.methods"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r0)
            java.lang.Object[] r11 = (java.lang.Object[]) r11
            int r0 = r11.length
            r3 = r1
        L_0x00a4:
            if (r3 >= r0) goto L_0x00b9
            r4 = r11[r3]
            r5 = r4
            java.lang.reflect.Method r5 = (java.lang.reflect.Method) r5
            java.lang.String r5 = r5.getName()
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r10)
            if (r5 == 0) goto L_0x00b6
            goto L_0x00ba
        L_0x00b6:
            int r3 = r3 + 1
            goto L_0x00a4
        L_0x00b9:
            r4 = r2
        L_0x00ba:
            java.lang.reflect.Method r4 = (java.lang.reflect.Method) r4
            if (r4 == 0) goto L_0x00c5
            java.lang.Object[] r11 = new java.lang.Object[r1]
            java.lang.Object r11 = r4.invoke(r9, r11)
            goto L_0x00c6
        L_0x00c5:
            r11 = r2
        L_0x00c6:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.StringBuilder r10 = r0.append(r10)
            r0 = 61
            java.lang.StringBuilder r10 = r10.append(r0)
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            r8.add(r10)
            goto L_0x007b
        L_0x00e1:
            java.util.List r8 = (java.util.List) r8
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.List r6 = kotlin.collections.CollectionsKt.toList(r8)
            java.lang.StringBuilder r6 = r7.append(r6)
            java.lang.String r6 = r6.toString()
            goto L_0x0372
        L_0x00f3:
            java.util.List r6 = r6.getArguments()
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.Iterator r6 = r6.iterator()
        L_0x00fd:
            boolean r9 = r6.hasNext()
            if (r9 == 0) goto L_0x0373
            java.lang.Object r9 = r6.next()
            com.google.devtools.ksp.symbol.KSValueArgument r9 = (com.google.devtools.ksp.symbol.KSValueArgument) r9
            com.google.devtools.ksp.symbol.KSName r11 = r9.getName()
            if (r11 == 0) goto L_0x0114
            java.lang.String r11 = r11.asString()
            goto L_0x0115
        L_0x0114:
            r11 = r2
        L_0x0115:
            java.lang.String r0 = r10.getName()
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r0)
            if (r11 == 0) goto L_0x00fd
            java.lang.Object r6 = r9.getValue()
            if (r6 != 0) goto L_0x0129
            java.lang.Object r6 = r10.getDefaultValue()
        L_0x0129:
            boolean r9 = r6 instanceof java.lang.reflect.Proxy
            if (r9 == 0) goto L_0x012f
            goto L_0x0372
        L_0x012f:
            boolean r9 = r6 instanceof java.util.List
            if (r9 == 0) goto L_0x015a
            com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$1 r9 = new com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$1
            r9.<init>(r6, r10, r7)
            kotlin.jvm.functions.Function0 r9 = (kotlin.jvm.functions.Function0) r9
            java.util.concurrent.ConcurrentMap r8 = (java.util.concurrent.ConcurrentMap) r8
            kotlin.Pair r7 = new kotlin.Pair
            java.lang.Class r10 = r10.getReturnType()
            r7.<init>(r10, r6)
            java.lang.Object r6 = r8.get(r7)
            if (r6 != 0) goto L_0x0372
            java.lang.Object r6 = r9.invoke()
            java.lang.Object r7 = r8.putIfAbsent(r7, r6)
            if (r7 != 0) goto L_0x0157
            goto L_0x0372
        L_0x0157:
            r6 = r7
            goto L_0x0372
        L_0x015a:
            java.lang.Class r9 = r10.getReturnType()
            boolean r9 = r9.isArray()
            if (r9 == 0) goto L_0x0195
            boolean r9 = r6 instanceof java.lang.Object[]
            if (r9 != 0) goto L_0x018d
            com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$2 r9 = new com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$2
            r9.<init>(r6, r10, r7)
            kotlin.jvm.functions.Function0 r9 = (kotlin.jvm.functions.Function0) r9
            java.util.concurrent.ConcurrentMap r8 = (java.util.concurrent.ConcurrentMap) r8
            kotlin.Pair r6 = new kotlin.Pair
            java.lang.Class r7 = r10.getReturnType()
            r6.<init>(r7, r9)
            java.lang.Object r7 = r8.get(r6)
            if (r7 != 0) goto L_0x0157
            java.lang.Object r7 = r9.invoke()
            java.lang.Object r6 = r8.putIfAbsent(r6, r7)
            if (r6 != 0) goto L_0x018b
            goto L_0x0157
        L_0x018b:
            r7 = r6
            goto L_0x0157
        L_0x018d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "unhandled value type, please file a bug at https://github.com/google/ksp/issues/new"
            r6.<init>(r7)
            throw r6
        L_0x0195:
            java.lang.Class r9 = r10.getReturnType()
            boolean r9 = r9.isEnum()
            if (r9 == 0) goto L_0x01c3
            com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$3 r7 = new com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$3
            r7.<init>(r6, r10)
            kotlin.jvm.functions.Function0 r7 = (kotlin.jvm.functions.Function0) r7
            java.util.concurrent.ConcurrentMap r8 = (java.util.concurrent.ConcurrentMap) r8
            kotlin.Pair r9 = new kotlin.Pair
            java.lang.Class r10 = r10.getReturnType()
            r9.<init>(r10, r6)
            java.lang.Object r6 = r8.get(r9)
            if (r6 != 0) goto L_0x0372
            java.lang.Object r6 = r7.invoke()
            java.lang.Object r7 = r8.putIfAbsent(r9, r6)
            if (r7 != 0) goto L_0x0157
            goto L_0x0372
        L_0x01c3:
            java.lang.Class r9 = r10.getReturnType()
            boolean r9 = r9.isAnnotation()
            if (r9 == 0) goto L_0x01f1
            com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$4 r7 = new com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$4
            r7.<init>(r6, r10)
            kotlin.jvm.functions.Function0 r7 = (kotlin.jvm.functions.Function0) r7
            java.util.concurrent.ConcurrentMap r8 = (java.util.concurrent.ConcurrentMap) r8
            kotlin.Pair r9 = new kotlin.Pair
            java.lang.Class r10 = r10.getReturnType()
            r9.<init>(r10, r6)
            java.lang.Object r6 = r8.get(r9)
            if (r6 != 0) goto L_0x0372
            java.lang.Object r6 = r7.invoke()
            java.lang.Object r7 = r8.putIfAbsent(r9, r6)
            if (r7 != 0) goto L_0x0157
            goto L_0x0372
        L_0x01f1:
            java.lang.Class r9 = r10.getReturnType()
            java.lang.String r9 = r9.getName()
            java.lang.String r11 = "java.lang.Class"
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r11)
            if (r9 == 0) goto L_0x0272
            java.util.concurrent.ConcurrentMap r8 = (java.util.concurrent.ConcurrentMap) r8
            kotlin.Pair r9 = new kotlin.Pair
            java.lang.Class r10 = r10.getReturnType()
            r9.<init>(r10, r6)
            java.lang.Object r10 = r8.get(r9)
            if (r10 != 0) goto L_0x026f
            boolean r10 = r6 instanceof com.google.devtools.ksp.symbol.KSType
            if (r10 == 0) goto L_0x0222
            java.lang.String r10 = "result"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r10)
            com.google.devtools.ksp.symbol.KSType r6 = (com.google.devtools.ksp.symbol.KSType) r6
            java.lang.Class r6 = asClass(r6, r7)
            goto L_0x025c
        L_0x0222:
            java.lang.Class r7 = r6.getClass()
            java.lang.reflect.Method[] r7 = r7.getMethods()
            java.lang.String r10 = "result.javaClass.methods"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r10)
            java.lang.Object[] r7 = (java.lang.Object[]) r7
            int r10 = r7.length
            r11 = r1
        L_0x0233:
            if (r11 >= r10) goto L_0x0267
            r0 = r7[r11]
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            java.lang.String r2 = r0.getName()
            java.lang.String r3 = "getCanonicalText"
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            if (r2 == 0) goto L_0x0264
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r1)
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            java.lang.Object r6 = r0.invoke(r6, r7)
            java.lang.String r7 = "null cannot be cast to non-null type kotlin.String"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6, r7)
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Class r6 = java.lang.Class.forName(r6)
        L_0x025c:
            java.lang.Object r7 = r8.putIfAbsent(r9, r6)
            if (r7 != 0) goto L_0x0157
            goto L_0x0372
        L_0x0264:
            int r11 = r11 + 1
            goto L_0x0233
        L_0x0267:
            java.util.NoSuchElementException r6 = new java.util.NoSuchElementException
            java.lang.String r7 = "Array contains no element matching the predicate."
            r6.<init>(r7)
            throw r6
        L_0x026f:
            r6 = r10
            goto L_0x0372
        L_0x0272:
            java.lang.Class r7 = r10.getReturnType()
            java.lang.String r7 = r7.getName()
            java.lang.String r9 = "byte"
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)
            if (r7 == 0) goto L_0x02a6
            com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$5 r7 = new com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$5
            r7.<init>(r6)
            kotlin.jvm.functions.Function0 r7 = (kotlin.jvm.functions.Function0) r7
            java.util.concurrent.ConcurrentMap r8 = (java.util.concurrent.ConcurrentMap) r8
            kotlin.Pair r9 = new kotlin.Pair
            java.lang.Class r10 = r10.getReturnType()
            r9.<init>(r10, r6)
            java.lang.Object r6 = r8.get(r9)
            if (r6 != 0) goto L_0x0372
            java.lang.Object r6 = r7.invoke()
            java.lang.Object r7 = r8.putIfAbsent(r9, r6)
            if (r7 != 0) goto L_0x0157
            goto L_0x0372
        L_0x02a6:
            java.lang.Class r7 = r10.getReturnType()
            java.lang.String r7 = r7.getName()
            java.lang.String r9 = "short"
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)
            if (r7 == 0) goto L_0x02da
            com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$6 r7 = new com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$6
            r7.<init>(r6)
            kotlin.jvm.functions.Function0 r7 = (kotlin.jvm.functions.Function0) r7
            java.util.concurrent.ConcurrentMap r8 = (java.util.concurrent.ConcurrentMap) r8
            kotlin.Pair r9 = new kotlin.Pair
            java.lang.Class r10 = r10.getReturnType()
            r9.<init>(r10, r6)
            java.lang.Object r6 = r8.get(r9)
            if (r6 != 0) goto L_0x0372
            java.lang.Object r6 = r7.invoke()
            java.lang.Object r7 = r8.putIfAbsent(r9, r6)
            if (r7 != 0) goto L_0x0157
            goto L_0x0372
        L_0x02da:
            java.lang.Class r7 = r10.getReturnType()
            java.lang.String r7 = r7.getName()
            java.lang.String r9 = "long"
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)
            if (r7 == 0) goto L_0x030d
            com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$7 r7 = new com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$7
            r7.<init>(r6)
            kotlin.jvm.functions.Function0 r7 = (kotlin.jvm.functions.Function0) r7
            java.util.concurrent.ConcurrentMap r8 = (java.util.concurrent.ConcurrentMap) r8
            kotlin.Pair r9 = new kotlin.Pair
            java.lang.Class r10 = r10.getReturnType()
            r9.<init>(r10, r6)
            java.lang.Object r6 = r8.get(r9)
            if (r6 != 0) goto L_0x0372
            java.lang.Object r6 = r7.invoke()
            java.lang.Object r7 = r8.putIfAbsent(r9, r6)
            if (r7 != 0) goto L_0x0157
            goto L_0x0372
        L_0x030d:
            java.lang.Class r7 = r10.getReturnType()
            java.lang.String r7 = r7.getName()
            java.lang.String r9 = "float"
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)
            if (r7 == 0) goto L_0x0340
            com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$8 r7 = new com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$8
            r7.<init>(r6)
            kotlin.jvm.functions.Function0 r7 = (kotlin.jvm.functions.Function0) r7
            java.util.concurrent.ConcurrentMap r8 = (java.util.concurrent.ConcurrentMap) r8
            kotlin.Pair r9 = new kotlin.Pair
            java.lang.Class r10 = r10.getReturnType()
            r9.<init>(r10, r6)
            java.lang.Object r6 = r8.get(r9)
            if (r6 != 0) goto L_0x0372
            java.lang.Object r6 = r7.invoke()
            java.lang.Object r7 = r8.putIfAbsent(r9, r6)
            if (r7 != 0) goto L_0x0157
            goto L_0x0372
        L_0x0340:
            java.lang.Class r7 = r10.getReturnType()
            java.lang.String r7 = r7.getName()
            java.lang.String r9 = "double"
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)
            if (r7 == 0) goto L_0x0372
            com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$9 r7 = new com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$9
            r7.<init>(r6)
            kotlin.jvm.functions.Function0 r7 = (kotlin.jvm.functions.Function0) r7
            java.util.concurrent.ConcurrentMap r8 = (java.util.concurrent.ConcurrentMap) r8
            kotlin.Pair r9 = new kotlin.Pair
            java.lang.Class r10 = r10.getReturnType()
            r9.<init>(r10, r6)
            java.lang.Object r6 = r8.get(r9)
            if (r6 != 0) goto L_0x0372
            java.lang.Object r6 = r7.invoke()
            java.lang.Object r7 = r8.putIfAbsent(r9, r6)
            if (r7 != 0) goto L_0x0157
        L_0x0372:
            return r6
        L_0x0373:
            java.util.NoSuchElementException r6 = new java.util.NoSuchElementException
            java.lang.String r7 = "Collection contains no element matching the predicate."
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.devtools.ksp.UtilsKt.createInvocationHandler$lambda$8(com.google.devtools.ksp.symbol.KSAnnotation, java.lang.Class, java.util.concurrent.ConcurrentHashMap, java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final Object asAnnotation(KSAnnotation kSAnnotation, Class<?> cls) {
        Object newProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, createInvocationHandler(kSAnnotation, cls));
        Intrinsics.checkNotNull(newProxyInstance, "null cannot be cast to non-null type java.lang.reflect.Proxy");
        return (Proxy) newProxyInstance;
    }

    /* access modifiers changed from: private */
    public static final Object asArray(List<?> list, Method method, Class<?> cls) {
        String name = method.getReturnType().getComponentType().getName();
        if (name != null) {
            switch (name.hashCode()) {
                case -1325958191:
                    if (name.equals("double")) {
                        Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Double>");
                        return CollectionsKt.toDoubleArray(list);
                    }
                    break;
                case -530663260:
                    if (name.equals("java.lang.Class")) {
                        Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<com.google.devtools.ksp.symbol.KSType>");
                        return asClasses(list, cls).toArray(new Class[0]);
                    }
                    break;
                case 104431:
                    if (name.equals("int")) {
                        Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Int>");
                        return CollectionsKt.toIntArray(list);
                    }
                    break;
                case 3039496:
                    if (name.equals(ProfileMeasurement.UNIT_BYTES)) {
                        Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Byte>");
                        return CollectionsKt.toByteArray(list);
                    }
                    break;
                case 3052374:
                    if (name.equals("char")) {
                        Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Char>");
                        return CollectionsKt.toCharArray(list);
                    }
                    break;
                case 3327612:
                    if (name.equals("long")) {
                        Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Long>");
                        return CollectionsKt.toLongArray(list);
                    }
                    break;
                case 64711720:
                    if (name.equals(TypedValues.Custom.S_BOOLEAN)) {
                        Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Boolean>");
                        return CollectionsKt.toBooleanArray(list);
                    }
                    break;
                case 97526364:
                    if (name.equals(TypedValues.Custom.S_FLOAT)) {
                        Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Float>");
                        return CollectionsKt.toFloatArray(list);
                    }
                    break;
                case 109413500:
                    if (name.equals("short")) {
                        Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Short>");
                        return CollectionsKt.toShortArray(list);
                    }
                    break;
                case 1195259493:
                    if (name.equals("java.lang.String")) {
                        Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.String>");
                        return list.toArray(new String[0]);
                    }
                    break;
            }
        }
        if (method.getReturnType().getComponentType().isEnum()) {
            return toArray(list, method, new UtilsKt$asArray$1(method));
        }
        if (method.getReturnType().getComponentType().isAnnotation()) {
            return toArray(list, method, new UtilsKt$asArray$2(method));
        }
        throw new IllegalStateException("Unable to process type " + method.getReturnType().getComponentType().getName());
    }

    private static final Object[] toArray(List<?> list, Method method, Function1<Object, ? extends Object> function1) {
        Object newInstance = Array.newInstance(method.getReturnType().getComponentType(), list.size());
        Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        Object[] objArr = (Object[]) newInstance;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Object obj = list.get(i);
            objArr[i] = obj != null ? function1.invoke(obj) : null;
        }
        return objArr;
    }

    /* access modifiers changed from: private */
    public static final <T> T asEnum(Object obj, Class<T> cls) {
        String str;
        Method declaredMethod = cls.getDeclaredMethod("valueOf", new Class[]{String.class});
        Object[] objArr = new Object[1];
        if (obj instanceof KSType) {
            str = ((KSType) obj).getDeclaration().getSimpleName().getShortName();
        } else {
            str = obj.toString();
        }
        objArr[0] = str;
        return declaredMethod.invoke((Object) null, objArr);
    }

    /* access modifiers changed from: private */
    public static final byte asByte(Object obj) {
        if (obj instanceof Integer) {
            return (byte) ((Number) obj).intValue();
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Byte");
        return ((Byte) obj).byteValue();
    }

    /* access modifiers changed from: private */
    public static final short asShort(Object obj) {
        if (obj instanceof Integer) {
            return (short) ((Number) obj).intValue();
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Short");
        return ((Short) obj).shortValue();
    }

    /* access modifiers changed from: private */
    public static final long asLong(Object obj) {
        if (obj instanceof Integer) {
            return (long) ((Number) obj).intValue();
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Long");
        return ((Long) obj).longValue();
    }

    /* access modifiers changed from: private */
    public static final float asFloat(Object obj) {
        if (obj instanceof Integer) {
            return (float) ((Number) obj).intValue();
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Float");
        return ((Float) obj).floatValue();
    }

    /* access modifiers changed from: private */
    public static final double asDouble(Object obj) {
        if (obj instanceof Integer) {
            return (double) ((Number) obj).intValue();
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Double");
        return ((Double) obj).doubleValue();
    }

    private static final Class<?> asClass(KSType kSType, Class<?> cls) {
        try {
            KSName qualifiedName = kSType.getDeclaration().getQualifiedName();
            Intrinsics.checkNotNull(qualifiedName);
            return Class.forName(qualifiedName.asString(), true, cls.getClassLoader());
        } catch (Exception e) {
            throw new KSTypeNotPresentException(kSType, e);
        }
    }

    private static final List<Class<?>> asClasses(List<? extends KSType> list, Class<?> cls) {
        try {
            Iterable<KSType> iterable = list;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (KSType asClass : iterable) {
                arrayList.add(asClass(asClass, cls));
            }
            return (List) arrayList;
        } catch (Exception e) {
            throw new KSTypesNotPresentException(list, e);
        }
    }

    public static final boolean isDefault(KSValueArgument kSValueArgument) {
        Intrinsics.checkNotNullParameter(kSValueArgument, "<this>");
        return kSValueArgument.getOrigin() == Origin.SYNTHETIC;
    }

    /* access modifiers changed from: private */
    public static final Object asArray(Object obj, Method method, Class<?> cls) {
        return asArray((List<?>) CollectionsKt.listOf(obj), method, cls);
    }
}
