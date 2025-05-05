package com.google.devtools.ksp.processing;

import com.google.devtools.ksp.symbol.KSAnnotated;
import com.google.devtools.ksp.symbol.KSClassDeclaration;
import com.google.devtools.ksp.symbol.KSDeclaration;
import com.google.devtools.ksp.symbol.KSDeclarationContainer;
import com.google.devtools.ksp.symbol.KSFile;
import com.google.devtools.ksp.symbol.KSFunctionDeclaration;
import com.google.devtools.ksp.symbol.KSName;
import com.google.devtools.ksp.symbol.KSPropertyAccessor;
import com.google.devtools.ksp.symbol.KSPropertyDeclaration;
import com.google.devtools.ksp.symbol.KSType;
import com.google.devtools.ksp.symbol.KSTypeArgument;
import com.google.devtools.ksp.symbol.KSTypeReference;
import com.google.devtools.ksp.symbol.Modifier;
import com.google.devtools.ksp.symbol.Variance;
import java.util.Set;
import kotlin.Metadata;
import kotlin.sequences.Sequence;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH'J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H&J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0015H&J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00102\u0006\u0010\u0017\u001a\u00020\u0018H'J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00102\u0006\u0010\u001a\u001a\u00020\u001bH'J \u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00102\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u001e\u001a\u00020\u001fH&J\u0010\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u0007H'J\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00020\t0\u00102\u0006\u0010#\u001a\u00020\u001dH'J\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00020\t0\u00102\u0006\u0010$\u001a\u00020%H'J\u0012\u0010&\u001a\u0004\u0018\u00010\u00182\u0006\u0010\r\u001a\u00020\u001dH'J\u0012\u0010&\u001a\u0004\u0018\u00010\u00182\u0006\u0010$\u001a\u00020%H'J\u0010\u0010'\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0018H&J\u000e\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H&J\u0012\u0010)\u001a\u0004\u0018\u00010\u00182\u0006\u0010\r\u001a\u00020\u001dH'J\u0012\u0010)\u001a\u0004\u0018\u00010\u00182\u0006\u0010\r\u001a\u00020*H'J\u001c\u0010+\u001a\u0004\u0018\u00010*2\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u001e\u001a\u00020\u001fH&J \u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u00102\u0006\u0010.\u001a\u00020\u00182\b\b\u0002\u0010/\u001a\u00020\u001fH&J\u0018\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u00072\u0006\u00103\u001a\u000204H&J\u0010\u00105\u001a\u00020\u001f2\u0006\u0010\b\u001a\u00020\tH'J\u0012\u00106\u001a\u0004\u0018\u00010\u00152\u0006\u00107\u001a\u00020\u0015H'J\u0012\u00108\u001a\u0004\u0018\u00010\u00152\u0006\u00109\u001a\u00020\u0015H'J\u0012\u0010:\u001a\u0004\u0018\u00010\u00182\u0006\u0010\r\u001a\u00020\u000eH'J\u0018\u0010;\u001a\u00020\u001f2\u0006\u0010<\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020\u000eH&J \u0010;\u001a\u00020\u001f2\u0006\u0010<\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020\u000e2\u0006\u0010>\u001a\u00020\u0013H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006?À\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/processing/Resolver;", "", "builtIns", "Lcom/google/devtools/ksp/processing/KSBuiltIns;", "getBuiltIns", "()Lcom/google/devtools/ksp/processing/KSBuiltIns;", "createKSTypeReferenceFromKSType", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "type", "Lcom/google/devtools/ksp/symbol/KSType;", "effectiveJavaModifiers", "", "Lcom/google/devtools/ksp/symbol/Modifier;", "declaration", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "getAllFiles", "Lkotlin/sequences/Sequence;", "Lcom/google/devtools/ksp/symbol/KSFile;", "getClassDeclarationByName", "Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "name", "Lcom/google/devtools/ksp/symbol/KSName;", "getDeclarationsFromPackage", "packageName", "", "getDeclarationsInSourceOrder", "container", "Lcom/google/devtools/ksp/symbol/KSDeclarationContainer;", "getFunctionDeclarationsByName", "Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;", "includeTopLevel", "", "getJavaWildcard", "reference", "getJvmCheckedException", "function", "accessor", "Lcom/google/devtools/ksp/symbol/KSPropertyAccessor;", "getJvmName", "getKSNameFromString", "getNewFiles", "getOwnerJvmClassName", "Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;", "getPropertyDeclarationByName", "getSymbolsWithAnnotation", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "annotationName", "inDepth", "getTypeArgument", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "typeRef", "variance", "Lcom/google/devtools/ksp/symbol/Variance;", "isJavaRawType", "mapJavaNameToKotlin", "javaName", "mapKotlinNameToJava", "kotlinName", "mapToJvmSignature", "overrides", "overrider", "overridee", "containingClass", "api"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Resolver.kt */
public interface Resolver {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Resolver.kt */
    public static final class DefaultImpls {
    }

    KSTypeReference createKSTypeReferenceFromKSType(KSType kSType);

    Set<Modifier> effectiveJavaModifiers(KSDeclaration kSDeclaration);

    Sequence<KSFile> getAllFiles();

    KSBuiltIns getBuiltIns();

    KSClassDeclaration getClassDeclarationByName(KSName kSName);

    Sequence<KSDeclaration> getDeclarationsFromPackage(String str);

    Sequence<KSDeclaration> getDeclarationsInSourceOrder(KSDeclarationContainer kSDeclarationContainer);

    Sequence<KSFunctionDeclaration> getFunctionDeclarationsByName(KSName kSName, boolean z);

    KSTypeReference getJavaWildcard(KSTypeReference kSTypeReference);

    Sequence<KSType> getJvmCheckedException(KSFunctionDeclaration kSFunctionDeclaration);

    Sequence<KSType> getJvmCheckedException(KSPropertyAccessor kSPropertyAccessor);

    String getJvmName(KSFunctionDeclaration kSFunctionDeclaration);

    String getJvmName(KSPropertyAccessor kSPropertyAccessor);

    KSName getKSNameFromString(String str);

    Sequence<KSFile> getNewFiles();

    String getOwnerJvmClassName(KSFunctionDeclaration kSFunctionDeclaration);

    String getOwnerJvmClassName(KSPropertyDeclaration kSPropertyDeclaration);

    KSPropertyDeclaration getPropertyDeclarationByName(KSName kSName, boolean z);

    Sequence<KSAnnotated> getSymbolsWithAnnotation(String str, boolean z);

    KSTypeArgument getTypeArgument(KSTypeReference kSTypeReference, Variance variance);

    boolean isJavaRawType(KSType kSType);

    KSName mapJavaNameToKotlin(KSName kSName);

    KSName mapKotlinNameToJava(KSName kSName);

    String mapToJvmSignature(KSDeclaration kSDeclaration);

    boolean overrides(KSDeclaration kSDeclaration, KSDeclaration kSDeclaration2);

    boolean overrides(KSDeclaration kSDeclaration, KSDeclaration kSDeclaration2, KSClassDeclaration kSClassDeclaration);

    static /* synthetic */ Sequence getSymbolsWithAnnotation$default(Resolver resolver, String str, boolean z, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                z = false;
            }
            return resolver.getSymbolsWithAnnotation(str, z);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getSymbolsWithAnnotation");
    }

    static /* synthetic */ Sequence getFunctionDeclarationsByName$default(Resolver resolver, KSName kSName, boolean z, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                z = false;
            }
            return resolver.getFunctionDeclarationsByName(kSName, z);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getFunctionDeclarationsByName");
    }

    static /* synthetic */ KSPropertyDeclaration getPropertyDeclarationByName$default(Resolver resolver, KSName kSName, boolean z, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                z = false;
            }
            return resolver.getPropertyDeclarationByName(kSName, z);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getPropertyDeclarationByName");
    }
}
