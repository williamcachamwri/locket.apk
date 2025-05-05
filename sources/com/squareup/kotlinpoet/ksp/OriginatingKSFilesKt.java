package com.squareup.kotlinpoet.ksp;

import com.google.devtools.ksp.processing.CodeGenerator;
import com.google.devtools.ksp.processing.Dependencies;
import com.google.devtools.ksp.symbol.KSFile;
import com.squareup.kotlinpoet.FileSpec;
import com.squareup.kotlinpoet.FunSpec;
import com.squareup.kotlinpoet.PropertySpec;
import com.squareup.kotlinpoet.Taggable;
import com.squareup.kotlinpoet.TypeAliasSpec;
import com.squareup.kotlinpoet.TypeSpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b*\u00020\tH\u0002\u001a\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b*\u0006\u0012\u0002\b\u00030\fH\u0002\u001a\"\u0010\r\u001a\u00020\u000e*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u0013\u001a\u0010\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\b*\u00020\u000f\u001a\u0010\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\b*\u00020\u0014\u001a\u0010\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\b*\u00020\u0015\u001a\u0010\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\b*\u00020\u0016\u001a\u0010\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\b*\u00020\u0017\u001a\u001a\u0010\u0018\u001a\u00020\u0019*\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000e\u001a*\u0010\u0018\u001a\u00020\u0019*\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0010\u001a\u00020\u00112\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u0013¨\u0006\u001d"}, d2 = {"addOriginatingKSFile", "Lcom/squareup/kotlinpoet/FunSpec$Builder;", "ksFile", "Lcom/google/devtools/ksp/symbol/KSFile;", "Lcom/squareup/kotlinpoet/PropertySpec$Builder;", "Lcom/squareup/kotlinpoet/TypeAliasSpec$Builder;", "Lcom/squareup/kotlinpoet/TypeSpec$Builder;", "getKSFilesTag", "", "Lcom/squareup/kotlinpoet/Taggable;", "getOrCreateKSFilesTag", "", "Lcom/squareup/kotlinpoet/Taggable$Builder;", "kspDependencies", "Lcom/google/devtools/ksp/processing/Dependencies;", "Lcom/squareup/kotlinpoet/FileSpec;", "aggregating", "", "originatingKSFiles", "", "Lcom/squareup/kotlinpoet/FunSpec;", "Lcom/squareup/kotlinpoet/PropertySpec;", "Lcom/squareup/kotlinpoet/TypeAliasSpec;", "Lcom/squareup/kotlinpoet/TypeSpec;", "writeTo", "", "codeGenerator", "Lcom/google/devtools/ksp/processing/CodeGenerator;", "dependencies", "ksp"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: originatingKSFiles.kt */
public final class OriginatingKSFilesKt {
    public static final List<KSFile> originatingKSFiles(TypeSpec typeSpec) {
        Intrinsics.checkNotNullParameter(typeSpec, "<this>");
        return getKSFilesTag(typeSpec);
    }

    public static final List<KSFile> originatingKSFiles(FunSpec funSpec) {
        Intrinsics.checkNotNullParameter(funSpec, "<this>");
        return getKSFilesTag(funSpec);
    }

    public static final List<KSFile> originatingKSFiles(PropertySpec propertySpec) {
        Intrinsics.checkNotNullParameter(propertySpec, "<this>");
        return getKSFilesTag(propertySpec);
    }

    public static final List<KSFile> originatingKSFiles(TypeAliasSpec typeAliasSpec) {
        Intrinsics.checkNotNullParameter(typeAliasSpec, "<this>");
        return getKSFilesTag(typeAliasSpec);
    }

    public static final List<KSFile> originatingKSFiles(FileSpec fileSpec) {
        List<KSFile> list;
        Intrinsics.checkNotNullParameter(fileSpec, "<this>");
        Collection arrayList = new ArrayList();
        for (Object next : fileSpec.getMembers()) {
            if (next instanceof FunSpec) {
                list = originatingKSFiles((FunSpec) next);
            } else if (next instanceof PropertySpec) {
                list = originatingKSFiles((PropertySpec) next);
            } else if (next instanceof TypeSpec) {
                list = originatingKSFiles((TypeSpec) next);
            } else if (next instanceof TypeAliasSpec) {
                list = originatingKSFiles((TypeAliasSpec) next);
            } else {
                list = CollectionsKt.emptyList();
            }
            CollectionsKt.addAll(arrayList, list);
        }
        return CollectionsKt.distinct((List) arrayList);
    }

    public static final TypeAliasSpec.Builder addOriginatingKSFile(TypeAliasSpec.Builder builder, KSFile kSFile) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(kSFile, "ksFile");
        getOrCreateKSFilesTag(builder).add(kSFile);
        return builder;
    }

    public static final PropertySpec.Builder addOriginatingKSFile(PropertySpec.Builder builder, KSFile kSFile) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(kSFile, "ksFile");
        getOrCreateKSFilesTag(builder).add(kSFile);
        return builder;
    }

    public static final FunSpec.Builder addOriginatingKSFile(FunSpec.Builder builder, KSFile kSFile) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(kSFile, "ksFile");
        getOrCreateKSFilesTag(builder).add(kSFile);
        return builder;
    }

    public static final TypeSpec.Builder addOriginatingKSFile(TypeSpec.Builder builder, KSFile kSFile) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(kSFile, "ksFile");
        getOrCreateKSFilesTag(builder).add(kSFile);
        return builder;
    }

    public static /* synthetic */ void writeTo$default(FileSpec fileSpec, CodeGenerator codeGenerator, boolean z, Iterable iterable, int i, Object obj) {
        if ((i & 4) != 0) {
            iterable = originatingKSFiles(fileSpec);
        }
        writeTo(fileSpec, codeGenerator, z, iterable);
    }

    public static final void writeTo(FileSpec fileSpec, CodeGenerator codeGenerator, boolean z, Iterable<? extends KSFile> iterable) {
        Intrinsics.checkNotNullParameter(fileSpec, "<this>");
        Intrinsics.checkNotNullParameter(codeGenerator, "codeGenerator");
        Intrinsics.checkNotNullParameter(iterable, "originatingKSFiles");
        writeTo(fileSpec, codeGenerator, kspDependencies(fileSpec, z, iterable));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003d, code lost:
        throw r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0039, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003a, code lost:
        kotlin.io.CloseableKt.closeFinally(r10, r8);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void writeTo(com.squareup.kotlinpoet.FileSpec r8, com.google.devtools.ksp.processing.CodeGenerator r9, com.google.devtools.ksp.processing.Dependencies r10) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "codeGenerator"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String r0 = "dependencies"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r3 = r8.getPackageName()
            java.lang.String r4 = r8.getName()
            r5 = 0
            r6 = 8
            r7 = 0
            r1 = r9
            r2 = r10
            java.io.OutputStream r9 = com.google.devtools.ksp.processing.CodeGenerator.createNewFile$default(r1, r2, r3, r4, r5, r6, r7)
            java.io.OutputStreamWriter r10 = new java.io.OutputStreamWriter
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.UTF_8
            r10.<init>(r9, r0)
            java.io.Closeable r10 = (java.io.Closeable) r10
            r9 = r10
            java.lang.Appendable r9 = (java.lang.Appendable) r9     // Catch:{ all -> 0x0037 }
            r8.writeTo((java.lang.Appendable) r9)     // Catch:{ all -> 0x0037 }
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0037 }
            r8 = 0
            kotlin.io.CloseableKt.closeFinally(r10, r8)
            return
        L_0x0037:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0039 }
        L_0x0039:
            r9 = move-exception
            kotlin.io.CloseableKt.closeFinally(r10, r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.ksp.OriginatingKSFilesKt.writeTo(com.squareup.kotlinpoet.FileSpec, com.google.devtools.ksp.processing.CodeGenerator, com.google.devtools.ksp.processing.Dependencies):void");
    }

    public static /* synthetic */ Dependencies kspDependencies$default(FileSpec fileSpec, boolean z, Iterable iterable, int i, Object obj) {
        if ((i & 2) != 0) {
            iterable = originatingKSFiles(fileSpec);
        }
        return kspDependencies(fileSpec, z, iterable);
    }

    public static final Dependencies kspDependencies(FileSpec fileSpec, boolean z, Iterable<? extends KSFile> iterable) {
        Intrinsics.checkNotNullParameter(fileSpec, "<this>");
        Intrinsics.checkNotNullParameter(iterable, "originatingKSFiles");
        Object[] array = CollectionsKt.toList(iterable).toArray(new KSFile[0]);
        Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        KSFile[] kSFileArr = (KSFile[]) array;
        return new Dependencies(z, (KSFile[]) Arrays.copyOf(kSFileArr, kSFileArr.length));
    }

    private static final List<KSFile> getOrCreateKSFilesTag(Taggable.Builder<?> builder) {
        Map<KClass<?>, Object> tags = builder.getTags();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(OriginatingKSFiles.class);
        Object obj = tags.get(orCreateKotlinClass);
        if (obj == null) {
            obj = new MutableOriginatingKSFilesImpl((List) null, 1, (DefaultConstructorMarker) null);
            tags.put(orCreateKotlinClass, obj);
        }
        return ((MutableOriginatingKSFiles) obj).getFiles();
    }

    private static final List<KSFile> getKSFilesTag(Taggable taggable) {
        OriginatingKSFiles originatingKSFiles = (OriginatingKSFiles) taggable.tag(Reflection.getOrCreateKotlinClass(OriginatingKSFiles.class));
        List<KSFile> files = originatingKSFiles != null ? originatingKSFiles.getFiles() : null;
        return files == null ? CollectionsKt.emptyList() : files;
    }
}
