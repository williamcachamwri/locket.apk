package com.squareup.kotlinpoet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0002H\u0007¢\u0006\u0002\b\u0003\u001a\u0011\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\u0007¢\u0006\u0002\b\u0003\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0005H\u0007¢\u0006\u0002\b\u0003¨\u0006\u0006"}, d2 = {"asClassName", "Lcom/squareup/kotlinpoet/ClassName;", "Ljava/lang/Class;", "get", "Ljavax/lang/model/element/TypeElement;", "Lkotlin/reflect/KClass;", "kotlinpoet"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: ClassName.kt */
public final class ClassNames {
    public static final ClassName get(Class<?> cls) {
        Collection collection;
        Intrinsics.checkNotNullParameter(cls, "<this>");
        if (!(!cls.isPrimitive())) {
            throw new IllegalArgumentException("primitive types cannot be represented as a ClassName".toString());
        } else if (!(!Intrinsics.areEqual((Object) Void.TYPE, (Object) cls))) {
            throw new IllegalArgumentException("'void' type cannot be represented as a ClassName".toString());
        } else if (!cls.isArray()) {
            List arrayList = new ArrayList();
            while (true) {
                collection = arrayList;
                collection.add(cls.getSimpleName());
                Class<?> enclosingClass = cls.getEnclosingClass();
                if (enclosingClass == null) {
                    break;
                }
                cls = enclosingClass;
            }
            String name = cls.getName();
            Intrinsics.checkNotNullExpressionValue(name, "c.name");
            int lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) name, (char) FilenameUtils.EXTENSION_SEPARATOR, 0, false, 6, (Object) null);
            if (lastIndexOf$default != -1) {
                String name2 = cls.getName();
                Intrinsics.checkNotNullExpressionValue(name2, "c.name");
                String substring = name2.substring(0, lastIndexOf$default);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                collection.add(substring);
            }
            CollectionsKt.reverse(arrayList);
            return new ClassName(arrayList, false, (List) null, (Map) null, 14, (DefaultConstructorMarker) null);
        } else {
            throw new IllegalArgumentException("array types cannot be represented as a ClassName".toString());
        }
    }

    public static final ClassName get(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        String qualifiedName = kClass.getQualifiedName();
        if (qualifiedName != null) {
            return ClassName.Companion.bestGuess(qualifiedName);
        }
        throw new IllegalArgumentException(kClass + " cannot be represented as a ClassName");
    }

    private static final boolean asClassName$isClassOrInterface(Element element) {
        return element.getKind().isClass() || element.getKind().isInterface();
    }

    private static final PackageElement asClassName$getPackage(Element element) {
        while (element.getKind() != ElementKind.PACKAGE) {
            element = element.getEnclosingElement();
            Intrinsics.checkNotNullExpressionValue(element, "t.enclosingElement");
        }
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type javax.lang.model.element.PackageElement");
        return (PackageElement) element;
    }

    public static final ClassName get(TypeElement typeElement) {
        Intrinsics.checkNotNullParameter(typeElement, "<this>");
        List arrayList = new ArrayList();
        TypeElement typeElement2 = (Element) typeElement;
        TypeElement typeElement3 = typeElement2;
        while (asClassName$isClassOrInterface(typeElement3)) {
            TypeElement typeElement4 = typeElement3;
            if (UtilKt.isOneOf$default(typeElement4.getNestingKind(), NestingKind.TOP_LEVEL, NestingKind.MEMBER, (Object) null, (Object) null, (Object) null, (Object) null, 60, (Object) null)) {
                arrayList.add(typeElement4.getSimpleName().toString());
                typeElement3 = typeElement4.getEnclosingElement();
                Intrinsics.checkNotNullExpressionValue(typeElement3, "eType.enclosingElement");
            } else {
                throw new IllegalArgumentException("unexpected type testing".toString());
            }
        }
        arrayList.add(asClassName$getPackage(typeElement2).getQualifiedName().toString());
        CollectionsKt.reverse(arrayList);
        return new ClassName(arrayList, false, (List) null, (Map) null, 14, (DefaultConstructorMarker) null);
    }
}
