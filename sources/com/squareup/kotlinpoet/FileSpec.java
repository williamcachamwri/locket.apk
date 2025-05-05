package com.squareup.kotlinpoet;

import androidx.webkit.ProxyConfig;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.squareup.kotlinpoet.AnnotationSpec;
import com.squareup.kotlinpoet.CodeBlock;
import com.squareup.kotlinpoet.Taggable;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.tools.JavaFileObject;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 H2\u00020\u0001:\u0002GHB\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u001aH\u0002J\u0013\u00100\u001a\u00020\u001a2\b\u00101\u001a\u0004\u0018\u00010 H\u0002J\b\u00102\u001a\u000203H\u0016J(\u00104\u001a\u0004\u0018\u0001H5\"\b\b\u0000\u00105*\u00020 2\f\u00106\u001a\b\u0012\u0004\u0012\u0002H507H\u0001¢\u0006\u0002\u00108J(\u00104\u001a\u0004\u0018\u0001H5\"\b\b\u0000\u00105*\u00020 2\f\u00106\u001a\b\u0012\u0004\u0012\u0002H50(H\u0001¢\u0006\u0002\u00109J\u001c\u0010:\u001a\u00020\u00032\b\b\u0002\u0010%\u001a\u00020\u00142\b\b\u0002\u0010\"\u001a\u00020\u0014H\u0007J\u0006\u0010;\u001a\u00020<J\b\u0010=\u001a\u00020\u0014H\u0016J\u000e\u0010>\u001a\u00020,2\u0006\u0010?\u001a\u00020@J\u000e\u0010>\u001a\u00020,2\u0006\u0010?\u001a\u00020AJ\u000e\u0010>\u001a\u00020,2\u0006\u0010B\u001a\u00020CJ\u0012\u0010>\u001a\u00020,2\n\u0010D\u001a\u00060Ej\u0002`FR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001bR\u001a\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u001e0\u001dX\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\b¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u000bR\u0011\u0010\"\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b&\u0010$R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R$\u0010'\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030(\u0012\u0004\u0012\u00020 0\u001d8VX\u0005¢\u0006\u0006\u001a\u0004\b)\u0010*¨\u0006I"}, d2 = {"Lcom/squareup/kotlinpoet/FileSpec;", "Lcom/squareup/kotlinpoet/Taggable;", "builder", "Lcom/squareup/kotlinpoet/FileSpec$Builder;", "tagMap", "Lcom/squareup/kotlinpoet/TagMap;", "(Lcom/squareup/kotlinpoet/FileSpec$Builder;Lcom/squareup/kotlinpoet/TagMap;)V", "annotations", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "getAnnotations", "()Ljava/util/List;", "body", "Lcom/squareup/kotlinpoet/CodeBlock;", "getBody", "()Lcom/squareup/kotlinpoet/CodeBlock;", "comment", "getComment", "defaultImports", "", "", "getDefaultImports", "()Ljava/util/Set;", "extension", "indent", "isScript", "", "()Z", "memberImports", "", "Lcom/squareup/kotlinpoet/Import;", "members", "", "getMembers", "name", "getName", "()Ljava/lang/String;", "packageName", "getPackageName", "tags", "Lkotlin/reflect/KClass;", "getTags", "()Ljava/util/Map;", "emit", "", "codeWriter", "Lcom/squareup/kotlinpoet/CodeWriter;", "collectingImports", "equals", "other", "hashCode", "", "tag", "T", "type", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "toBuilder", "toJavaFileObject", "Ljavax/tools/JavaFileObject;", "toString", "writeTo", "directory", "Ljava/io/File;", "Ljava/nio/file/Path;", "filer", "Ljavax/annotation/processing/Filer;", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "Builder", "Companion", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: FileSpec.kt */
public final class FileSpec implements Taggable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<AnnotationSpec> annotations;
    private final CodeBlock body;
    private final CodeBlock comment;
    private final Set<String> defaultImports;
    private final String extension;
    private final String indent;
    private final boolean isScript;
    /* access modifiers changed from: private */
    public final Map<String, Import> memberImports;
    private final List<Object> members;
    private final String name;
    private final String packageName;
    private final TagMap tagMap;

    @JvmStatic
    public static final Builder builder(String str, String str2) {
        return Companion.builder(str, str2);
    }

    @JvmStatic
    public static final FileSpec get(String str, TypeSpec typeSpec) {
        return Companion.get(str, typeSpec);
    }

    @JvmStatic
    public static final Builder scriptBuilder(String str, String str2) {
        return Companion.scriptBuilder(str, str2);
    }

    public Map<KClass<?>, Object> getTags() {
        return this.tagMap.getTags();
    }

    public <T> T tag(Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, "type");
        return this.tagMap.tag(cls);
    }

    public <T> T tag(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "type");
        return this.tagMap.tag(kClass);
    }

    public final Builder toBuilder() {
        return toBuilder$default(this, (String) null, (String) null, 3, (Object) null);
    }

    public final Builder toBuilder(String str) {
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        return toBuilder$default(this, str, (String) null, 2, (Object) null);
    }

    private FileSpec(Builder builder, TagMap tagMap2) {
        this.tagMap = tagMap2;
        this.annotations = UtilKt.toImmutableList(builder.getAnnotations());
        this.comment = builder.getComment$kotlinpoet().build();
        this.packageName = builder.getPackageName();
        this.name = builder.getName();
        this.members = CollectionsKt.toList(builder.getMembers());
        this.defaultImports = CollectionsKt.toSet(builder.getDefaultImports());
        this.body = builder.getBody$kotlinpoet().build();
        this.isScript = builder.isScript();
        Iterable memberImports$kotlinpoet = builder.getMemberImports$kotlinpoet();
        Map<String, Import> linkedHashMap = new LinkedHashMap<>(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(memberImports$kotlinpoet, 10)), 16));
        for (Object next : memberImports$kotlinpoet) {
            linkedHashMap.put(((Import) next).getQualifiedName(), next);
        }
        this.memberImports = linkedHashMap;
        this.indent = builder.getIndent$kotlinpoet();
        this.extension = this.isScript ? "kts" : "kt";
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ FileSpec(Builder builder, TagMap tagMap2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder, (i & 2) != 0 ? TaggableKt.buildTagMap(builder) : tagMap2);
    }

    public final List<AnnotationSpec> getAnnotations() {
        return this.annotations;
    }

    public final CodeBlock getComment() {
        return this.comment;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getName() {
        return this.name;
    }

    public final List<Object> getMembers() {
        return this.members;
    }

    public final Set<String> getDefaultImports() {
        return this.defaultImports;
    }

    public final CodeBlock getBody() {
        return this.body;
    }

    public final boolean isScript() {
        return this.isScript;
    }

    public final void writeTo(Appendable appendable) throws IOException {
        Intrinsics.checkNotNullParameter(appendable, "out");
        CodeWriter codeWriter = new CodeWriter(NullAppendable.INSTANCE, this.indent, this.memberImports, (Map) null, (Map) null, Integer.MAX_VALUE, 24, (DefaultConstructorMarker) null);
        emit(codeWriter, true);
        Map<String, ClassName> suggestedTypeImports = codeWriter.suggestedTypeImports();
        Map<String, MemberName> suggestedMemberImports = codeWriter.suggestedMemberImports();
        codeWriter.close();
        CodeWriter codeWriter2 = new CodeWriter(appendable, this.indent, this.memberImports, suggestedTypeImports, suggestedMemberImports, 0, 32, (DefaultConstructorMarker) null);
        emit(codeWriter2, false);
        codeWriter2.close();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00d3, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00d6, code lost:
        throw r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0080 A[LOOP:1: B:26:0x007a->B:28:0x0080, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void writeTo(java.nio.file.Path r11) throws java.io.IOException {
        /*
            r10 = this;
            java.lang.String r0 = "directory"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            r0 = 0
            java.nio.file.LinkOption[] r1 = new java.nio.file.LinkOption[r0]
            boolean r1 = java.nio.file.Files.notExists(r11, r1)
            r2 = 1
            if (r1 != 0) goto L_0x001a
            java.nio.file.LinkOption[] r1 = new java.nio.file.LinkOption[r0]
            boolean r1 = java.nio.file.Files.isDirectory(r11, r1)
            if (r1 == 0) goto L_0x0018
            goto L_0x001a
        L_0x0018:
            r1 = r0
            goto L_0x001b
        L_0x001a:
            r1 = r2
        L_0x001b:
            if (r1 == 0) goto L_0x00d7
            java.lang.String r1 = r10.packageName
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            int r1 = r1.length()
            if (r1 <= 0) goto L_0x0029
            r1 = r2
            goto L_0x002a
        L_0x0029:
            r1 = r0
        L_0x002a:
            r3 = 46
            if (r1 == 0) goto L_0x0090
            java.lang.String r1 = r10.packageName
            r4 = r1
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            char[] r5 = new char[r2]
            r5[r0] = r3
            r6 = 0
            r7 = 0
            r8 = 6
            r9 = 0
            java.util.List r1 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r4, (char[]) r5, (boolean) r6, (int) r7, (int) r8, (java.lang.Object) r9)
            boolean r4 = r1.isEmpty()
            if (r4 != 0) goto L_0x0072
            int r4 = r1.size()
            java.util.ListIterator r4 = r1.listIterator(r4)
        L_0x004d:
            boolean r5 = r4.hasPrevious()
            if (r5 == 0) goto L_0x0072
            java.lang.Object r5 = r4.previous()
            java.lang.String r5 = (java.lang.String) r5
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            int r5 = r5.length()
            if (r5 != 0) goto L_0x0063
            r5 = r2
            goto L_0x0064
        L_0x0063:
            r5 = r0
        L_0x0064:
            if (r5 != 0) goto L_0x004d
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            int r4 = r4.nextIndex()
            int r4 = r4 + r2
            java.util.List r1 = kotlin.collections.CollectionsKt.take(r1, r4)
            goto L_0x0076
        L_0x0072:
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0076:
            java.util.Iterator r1 = r1.iterator()
        L_0x007a:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0090
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.nio.file.Path r11 = r11.resolve(r2)
            java.lang.String r2 = "outputDirectory.resolve(packageComponent)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r2)
            goto L_0x007a
        L_0x0090:
            java.nio.file.attribute.FileAttribute[] r1 = new java.nio.file.attribute.FileAttribute[r0]
            java.nio.file.Files.createDirectories(r11, r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r10.name
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r2 = r10.extension
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.nio.file.Path r11 = r11.resolve(r1)
            java.io.OutputStreamWriter r1 = new java.io.OutputStreamWriter
            java.nio.file.OpenOption[] r0 = new java.nio.file.OpenOption[r0]
            java.io.OutputStream r11 = java.nio.file.Files.newOutputStream(r11, r0)
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.UTF_8
            r1.<init>(r11, r0)
            java.io.Closeable r1 = (java.io.Closeable) r1
            r11 = r1
            java.io.OutputStreamWriter r11 = (java.io.OutputStreamWriter) r11     // Catch:{ all -> 0x00d0 }
            java.lang.Appendable r11 = (java.lang.Appendable) r11     // Catch:{ all -> 0x00d0 }
            r10.writeTo((java.lang.Appendable) r11)     // Catch:{ all -> 0x00d0 }
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00d0 }
            r11 = 0
            kotlin.io.CloseableKt.closeFinally(r1, r11)
            return
        L_0x00d0:
            r11 = move-exception
            throw r11     // Catch:{ all -> 0x00d2 }
        L_0x00d2:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r11)
            throw r0
        L_0x00d7:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "path "
            r0.<init>(r1)
            java.lang.StringBuilder r11 = r0.append(r11)
            java.lang.String r0 = " exists but is not a directory."
            java.lang.StringBuilder r11 = r11.append(r0)
            java.lang.String r11 = r11.toString()
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r11 = r11.toString()
            r0.<init>(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.FileSpec.writeTo(java.nio.file.Path):void");
    }

    public final void writeTo(File file) throws IOException {
        Intrinsics.checkNotNullParameter(file, "directory");
        Path path = file.toPath();
        Intrinsics.checkNotNullExpressionValue(path, "directory.toPath()");
        writeTo(path);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0082, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0086, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void writeTo(javax.annotation.processing.Filer r6) throws java.io.IOException {
        /*
            r5 = this;
            java.lang.String r0 = "filer"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.util.List<java.lang.Object> r0 = r5.members
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            kotlin.sequences.Sequence r0 = kotlin.collections.CollectionsKt.asSequence(r0)
            com.squareup.kotlinpoet.FileSpec$writeTo$$inlined$filterIsInstance$1 r1 = com.squareup.kotlinpoet.FileSpec$writeTo$$inlined$filterIsInstance$1.INSTANCE
            kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
            kotlin.sequences.Sequence r0 = kotlin.sequences.SequencesKt.filter(r0, r1)
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.sequences.Sequence<R of kotlin.sequences.SequencesKt___SequencesKt.filterIsInstance>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            com.squareup.kotlinpoet.FileSpec$writeTo$originatingElements$1 r1 = com.squareup.kotlinpoet.FileSpec$writeTo$originatingElements$1.INSTANCE
            kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
            kotlin.sequences.Sequence r0 = kotlin.sequences.SequencesKt.flatMap(r0, r1)
            java.util.Set r0 = kotlin.sequences.SequencesKt.toSet(r0)
            javax.tools.StandardLocation r1 = javax.tools.StandardLocation.SOURCE_OUTPUT
            javax.tools.JavaFileManager$Location r1 = (javax.tools.JavaFileManager.Location) r1
            java.lang.String r2 = r5.packageName
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r5.name
            java.lang.StringBuilder r3 = r3.append(r4)
            r4 = 46
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = r5.extension
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            java.util.Collection r0 = (java.util.Collection) r0
            r4 = 0
            javax.lang.model.element.Element[] r4 = new javax.lang.model.element.Element[r4]
            java.lang.Object[] r0 = r0.toArray(r4)
            java.lang.String r4 = "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r4)
            javax.lang.model.element.Element[] r0 = (javax.lang.model.element.Element[]) r0
            int r4 = r0.length
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r0, r4)
            javax.lang.model.element.Element[] r0 = (javax.lang.model.element.Element[]) r0
            javax.tools.FileObject r6 = r6.createResource(r1, r2, r3, r0)
            java.io.Writer r0 = r6.openWriter()     // Catch:{ Exception -> 0x0087 }
            java.io.Closeable r0 = (java.io.Closeable) r0     // Catch:{ Exception -> 0x0087 }
            r1 = r0
            java.io.Writer r1 = (java.io.Writer) r1     // Catch:{ all -> 0x0080 }
            java.lang.String r2 = "writer"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)     // Catch:{ all -> 0x0080 }
            java.lang.Appendable r1 = (java.lang.Appendable) r1     // Catch:{ all -> 0x0080 }
            r5.writeTo((java.lang.Appendable) r1)     // Catch:{ all -> 0x0080 }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0080 }
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r0, r1)     // Catch:{ Exception -> 0x0087 }
            return
        L_0x0080:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0082 }
        L_0x0082:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r1)     // Catch:{ Exception -> 0x0087 }
            throw r2     // Catch:{ Exception -> 0x0087 }
        L_0x0087:
            r0 = move-exception
            r6.delete()     // Catch:{ Exception -> 0x008b }
        L_0x008b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.FileSpec.writeTo(javax.annotation.processing.Filer):void");
    }

    private final void emit(CodeWriter codeWriter, boolean z) {
        CodeWriter codeWriter2 = codeWriter;
        if (this.comment.isNotEmpty()) {
            codeWriter2.emitComment(this.comment);
        }
        if (!this.annotations.isEmpty()) {
            codeWriter2.emitAnnotations(this.annotations, false);
            CodeWriter.emit$default(codeWriter2, "\n", false, 2, (Object) null);
        }
        codeWriter2.pushPackage(this.packageName);
        String escapeSegmentsIfNecessary$default = UtilKt.escapeSegmentsIfNecessary$default(this.packageName, 0, 1, (Object) null);
        if (escapeSegmentsIfNecessary$default.length() > 0) {
            codeWriter2.emitCode("package·%L\n", escapeSegmentsIfNecessary$default);
            CodeWriter.emit$default(codeWriter2, "\n", false, 2, (Object) null);
        }
        Iterable<ClassName> values = codeWriter.getImportedTypes().values();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(values, 10));
        for (ClassName canonicalName : values) {
            arrayList.add(canonicalName.getCanonicalName());
        }
        List list = (List) arrayList;
        Iterable<MemberName> values2 = codeWriter.getImportedMembers().values();
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(values2, 10));
        for (MemberName canonicalName2 : values2) {
            arrayList2.add(canonicalName2.getCanonicalName());
        }
        List list2 = (List) arrayList2;
        Function1 function1 = FileSpec$emit$isDefaultImport$1.INSTANCE;
        if (!z && (!this.defaultImports.isEmpty())) {
            Iterable<String> iterable = this.defaultImports;
            Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (String escapeSegmentsIfNecessary$default2 : iterable) {
                arrayList3.add(UtilKt.escapeSegmentsIfNecessary$default(escapeSegmentsIfNecessary$default2, 0, 1, (Object) null));
            }
            function1 = new FileSpec$emit$1((List) arrayList3);
        }
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        for (Object next : this.memberImports.values()) {
            if (((Import) next).getAlias() != null) {
                arrayList4.add(next);
            } else {
                arrayList5.add(next);
            }
        }
        Pair pair = new Pair(arrayList4, arrayList5);
        Set sortedSet = SequencesKt.toSortedSet(SequencesKt.filterNot(SequencesKt.plus(SequencesKt.map(SequencesKt.filterNot(CollectionsKt.asSequence(CollectionsKt.plus(list, list2)), new FileSpec$emit$imports$1(this)), FileSpec$emit$imports$2.INSTANCE), SequencesKt.map(CollectionsKt.asSequence((List) pair.component2()), FileSpec$emit$imports$3.INSTANCE)), function1));
        Iterable<Import> iterable2 = (List) pair.component1();
        Collection arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
        for (Import importR : iterable2) {
            arrayList6.add(importR.toString());
        }
        Set<String> plus = SetsKt.plus(sortedSet, CollectionsKt.toSortedSet((List) arrayList6));
        if (!plus.isEmpty()) {
            for (String str : plus) {
                codeWriter2.emitCode("import·%L", str);
                CodeWriter.emit$default(codeWriter2, "\n", false, 2, (Object) null);
            }
            CodeWriter.emit$default(codeWriter2, "\n", false, 2, (Object) null);
        }
        if (this.isScript) {
            CodeWriter.emitCode$default(codeWriter, this.body, false, false, 6, (Object) null);
        } else {
            int i = 0;
            for (Object next2 : this.members) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                if (i > 0) {
                    CodeWriter.emit$default(codeWriter2, "\n", false, 2, (Object) null);
                }
                if (next2 instanceof TypeSpec) {
                    TypeSpec.emit$kotlinpoet$default((TypeSpec) next2, codeWriter, (String) null, (Set) null, false, 12, (Object) null);
                } else if (next2 instanceof FunSpec) {
                    ((FunSpec) next2).emit$kotlinpoet(codeWriter2, (String) null, SetsKt.setOf(KModifier.PUBLIC), true);
                } else if (next2 instanceof PropertySpec) {
                    PropertySpec.emit$kotlinpoet$default((PropertySpec) next2, codeWriter, SetsKt.setOf(KModifier.PUBLIC), false, false, false, false, 60, (Object) null);
                } else if (next2 instanceof TypeAliasSpec) {
                    ((TypeAliasSpec) next2).emit$kotlinpoet(codeWriter2);
                } else {
                    throw new AssertionError();
                }
                i = i2;
            }
        }
        codeWriter.popPackage();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Intrinsics.areEqual((Object) getClass(), (Object) obj.getClass())) {
            return Intrinsics.areEqual((Object) toString(), (Object) obj.toString());
        }
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        writeTo((Appendable) sb);
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    public final JavaFileObject toJavaFileObject() {
        String str;
        StringBuilder sb = new StringBuilder();
        if (this.packageName.length() == 0) {
            str = this.name;
        } else {
            str = StringsKt.replace$default(this.packageName, (char) FilenameUtils.EXTENSION_SEPARATOR, (char) IOUtils.DIR_SEPARATOR_UNIX, false, 4, (Object) null) + IOUtils.DIR_SEPARATOR_UNIX + this.name;
        }
        return new FileSpec$toJavaFileObject$1(URI.create(sb.append(str).append(FilenameUtils.EXTENSION_SEPARATOR).append(this.extension).toString()), this, JavaFileObject.Kind.SOURCE);
    }

    public static /* synthetic */ Builder toBuilder$default(FileSpec fileSpec, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = fileSpec.packageName;
        }
        if ((i & 2) != 0) {
            str2 = fileSpec.name;
        }
        return fileSpec.toBuilder(str, str2);
    }

    public final Builder toBuilder(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        Intrinsics.checkNotNullParameter(str2, "name");
        Builder builder = new Builder(str, str2, this.isScript);
        builder.getAnnotations().addAll(this.annotations);
        builder.getComment$kotlinpoet().add(this.comment);
        builder.getMembers().addAll(this.members);
        builder.setIndent$kotlinpoet(this.indent);
        builder.getMemberImports$kotlinpoet().addAll(this.memberImports.values());
        builder.getDefaultImports().addAll(this.defaultImports);
        builder.getTags().putAll(this.tagMap.getTags());
        builder.getBody$kotlinpoet().add(this.body);
        return builder;
    }

    @Metadata(d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0002\b\u0003\n\u0002\u0010\u0010\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010/\u001a\u00020\u00002\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u0003J\u001e\u0010/\u001a\u00020\u00002\u0006\u00100\u001a\u0002012\u0006\u00103\u001a\u00020\u00032\u0006\u00102\u001a\u00020\u0003J\u0016\u0010/\u001a\u00020\u00002\u0006\u00103\u001a\u0002042\u0006\u00102\u001a\u00020\u0003J\u001a\u0010/\u001a\u00020\u00002\n\u00105\u001a\u0006\u0012\u0002\b\u0003062\u0006\u00102\u001a\u00020\u0003J\u001a\u0010/\u001a\u00020\u00002\n\u00105\u001a\u0006\u0012\u0002\b\u00030,2\u0006\u00102\u001a\u00020\u0003J\u000e\u00107\u001a\u00020\u00002\u0006\u00108\u001a\u00020\nJ\u000e\u00107\u001a\u00020\u00002\u0006\u00109\u001a\u000201J\u0012\u00107\u001a\u00020\u00002\n\u00109\u001a\u0006\u0012\u0002\b\u000306J\u0012\u00107\u001a\u00020\u00002\n\u00109\u001a\u0006\u0012\u0002\b\u00030,J'\u0010:\u001a\u00020\u00002\u0006\u0010;\u001a\u00020\u00032\u0012\u0010<\u001a\n\u0012\u0006\b\u0001\u0012\u00020&0=\"\u00020&¢\u0006\u0002\u0010>J\u000e\u0010?\u001a\u00020\u00002\u0006\u0010@\u001a\u00020AJ+\u0010?\u001a\u00020\u00002\u0006\u0010;\u001a\u00020\u00032\u0016\u0010<\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010&0=\"\u0004\u0018\u00010&¢\u0006\u0002\u0010>J)\u0010B\u001a\u00020\u00002\u0006\u0010;\u001a\u00020\u00032\u0012\u0010<\u001a\n\u0012\u0006\b\u0001\u0012\u00020&0=\"\u00020&H\u0007¢\u0006\u0002\u0010>J\u000e\u0010C\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J'\u0010D\u001a\u00020\u00002\u0006\u0010;\u001a\u00020\u00032\u0012\u0010<\u001a\n\u0012\u0006\b\u0001\u0012\u00020&0=\"\u00020&¢\u0006\u0002\u0010>J\u000e\u0010E\u001a\u00020\u00002\u0006\u0010F\u001a\u00020GJ'\u0010H\u001a\u00020\u00002\u0006\u00100\u001a\u0002012\u0012\u0010I\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030=\"\u00020\u0003¢\u0006\u0002\u0010JJ\u001c\u0010H\u001a\u00020\u00002\u0006\u00100\u001a\u0002012\f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00030KJ\u000e\u0010H\u001a\u00020\u00002\u0006\u0010L\u001a\u00020\u0019J+\u0010H\u001a\u00020\u00002\n\u00105\u001a\u0006\u0012\u0002\b\u0003062\u0012\u0010I\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030=\"\u00020\u0003¢\u0006\u0002\u0010MJ \u0010H\u001a\u00020\u00002\n\u00105\u001a\u0006\u0012\u0002\b\u0003062\f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00030KJ\u0012\u0010H\u001a\u00020\u00002\n\u0010N\u001a\u0006\u0012\u0002\b\u00030OJ'\u0010H\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010I\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030=\"\u00020\u0003¢\u0006\u0002\u0010PJ\u001c\u0010H\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00030KJ+\u0010H\u001a\u00020\u00002\n\u00105\u001a\u0006\u0012\u0002\b\u00030,2\u0012\u0010I\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030=\"\u00020\u0003¢\u0006\u0002\u0010QJ \u0010H\u001a\u00020\u00002\n\u00105\u001a\u0006\u0012\u0002\b\u00030,2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00030KJ\u001a\u0010R\u001a\u00020\u00002\b\b\u0002\u0010S\u001a\u00020\u00062\b\b\u0002\u0010T\u001a\u00020\u0006J \u0010U\u001a\u00020\u00002\u0006\u0010;\u001a\u00020\u00032\u0010\u0010<\u001a\f\u0012\u0004\u0012\u00020\u0003\u0012\u0002\b\u00030VJ\u000e\u0010W\u001a\u00020\u00002\u0006\u0010X\u001a\u00020YJ'\u0010Z\u001a\u00020\u00002\u0006\u0010;\u001a\u00020\u00032\u0012\u0010<\u001a\n\u0012\u0006\b\u0001\u0012\u00020&0=\"\u00020&¢\u0006\u0002\u0010>J\u000e\u0010[\u001a\u00020\u00002\u0006\u0010\\\u001a\u00020]J\u000e\u0010^\u001a\u00020\u00002\u0006\u0010_\u001a\u00020`J'\u0010a\u001a\u00020\u00002\u0006\u0010b\u001a\u00020\u00032\u0012\u0010<\u001a\n\u0012\u0006\b\u0001\u0012\u00020&0=\"\u00020&¢\u0006\u0002\u0010>J\u0006\u0010c\u001a\u00020dJ\u0006\u0010e\u001a\u00020\u0000J\u0006\u0010f\u001a\u00020\u0000J\u0006\u0010g\u001a\u00020\u0000J\u0006\u0010h\u001a\u00020\u0000J\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0003J'\u0010i\u001a\u00020\u00002\u0006\u0010b\u001a\u00020\u00032\u0012\u0010<\u001a\n\u0012\u0006\b\u0001\u0012\u00020&0=\"\u00020&¢\u0006\u0002\u0010>R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00188F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\fR\u001a\u0010\u001b\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010 R\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00190\"X\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\t¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001dR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001dR$\u0010*\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030,\u0012\u0004\u0012\u00020&0+X\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.¨\u0006j"}, d2 = {"Lcom/squareup/kotlinpoet/FileSpec$Builder;", "Lcom/squareup/kotlinpoet/Taggable$Builder;", "packageName", "", "name", "isScript", "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "annotations", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "getAnnotations", "()Ljava/util/List;", "body", "Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "getBody$kotlinpoet", "()Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "comment", "getComment$kotlinpoet", "defaultImports", "", "getDefaultImports", "()Ljava/util/Set;", "imports", "", "Lcom/squareup/kotlinpoet/Import;", "getImports", "indent", "getIndent$kotlinpoet", "()Ljava/lang/String;", "setIndent$kotlinpoet", "(Ljava/lang/String;)V", "()Z", "memberImports", "Ljava/util/TreeSet;", "getMemberImports$kotlinpoet", "()Ljava/util/TreeSet;", "members", "", "getMembers", "getName", "getPackageName", "tags", "", "Lkotlin/reflect/KClass;", "getTags", "()Ljava/util/Map;", "addAliasedImport", "className", "Lcom/squareup/kotlinpoet/ClassName;", "as", "memberName", "Lcom/squareup/kotlinpoet/MemberName;", "class", "Ljava/lang/Class;", "addAnnotation", "annotationSpec", "annotation", "addBodyComment", "format", "args", "", "(Ljava/lang/String;[Ljava/lang/Object;)Lcom/squareup/kotlinpoet/FileSpec$Builder;", "addCode", "codeBlock", "Lcom/squareup/kotlinpoet/CodeBlock;", "addComment", "addDefaultPackageImport", "addFileComment", "addFunction", "funSpec", "Lcom/squareup/kotlinpoet/FunSpec;", "addImport", "names", "(Lcom/squareup/kotlinpoet/ClassName;[Ljava/lang/String;)Lcom/squareup/kotlinpoet/FileSpec$Builder;", "", "import", "(Ljava/lang/Class;[Ljava/lang/String;)Lcom/squareup/kotlinpoet/FileSpec$Builder;", "constant", "", "(Ljava/lang/String;[Ljava/lang/String;)Lcom/squareup/kotlinpoet/FileSpec$Builder;", "(Lkotlin/reflect/KClass;[Ljava/lang/String;)Lcom/squareup/kotlinpoet/FileSpec$Builder;", "addKotlinDefaultImports", "includeJvm", "includeJs", "addNamedCode", "", "addProperty", "propertySpec", "Lcom/squareup/kotlinpoet/PropertySpec;", "addStatement", "addType", "typeSpec", "Lcom/squareup/kotlinpoet/TypeSpec;", "addTypeAlias", "typeAliasSpec", "Lcom/squareup/kotlinpoet/TypeAliasSpec;", "beginControlFlow", "controlFlow", "build", "Lcom/squareup/kotlinpoet/FileSpec;", "clearBody", "clearComment", "clearImports", "endControlFlow", "nextControlFlow", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: FileSpec.kt */
    public static final class Builder implements Taggable.Builder<Builder> {
        private final List<AnnotationSpec> annotations = new ArrayList();
        private final CodeBlock.Builder body = CodeBlock.Companion.builder();
        private final CodeBlock.Builder comment = CodeBlock.Companion.builder();
        private final Set<String> defaultImports = new LinkedHashSet();
        private String indent = FileSpecKt.DEFAULT_INDENT;
        private final boolean isScript;
        private final TreeSet<Import> memberImports = SetsKt.sortedSetOf(new Import[0]);
        private final List<Object> members = new ArrayList();
        private final String name;
        private final String packageName;
        private final Map<KClass<?>, Object> tags = new LinkedHashMap();

        @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
        /* compiled from: FileSpec.kt */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[AnnotationSpec.UseSiteTarget.values().length];
                iArr[AnnotationSpec.UseSiteTarget.FILE.ordinal()] = 1;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public Builder(String str, String str2, boolean z) {
            Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
            Intrinsics.checkNotNullParameter(str2, "name");
            this.packageName = str;
            this.name = str2;
            this.isScript = z;
        }

        public Builder tag(Class<?> cls, Object obj) {
            return (Builder) Taggable.Builder.DefaultImpls.tag(this, cls, obj);
        }

        public Builder tag(KClass<?> kClass, Object obj) {
            return (Builder) Taggable.Builder.DefaultImpls.tag(this, kClass, obj);
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public final String getName() {
            return this.name;
        }

        public final boolean isScript() {
            return this.isScript;
        }

        public final CodeBlock.Builder getComment$kotlinpoet() {
            return this.comment;
        }

        public final TreeSet<Import> getMemberImports$kotlinpoet() {
            return this.memberImports;
        }

        public final String getIndent$kotlinpoet() {
            return this.indent;
        }

        public final void setIndent$kotlinpoet(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.indent = str;
        }

        public Map<KClass<?>, Object> getTags() {
            return this.tags;
        }

        public final Set<String> getDefaultImports() {
            return this.defaultImports;
        }

        public final List<Import> getImports() {
            return CollectionsKt.toList(this.memberImports);
        }

        public final List<Object> getMembers() {
            return this.members;
        }

        public final List<AnnotationSpec> getAnnotations() {
            return this.annotations;
        }

        public final CodeBlock.Builder getBody$kotlinpoet() {
            return this.body;
        }

        public final Builder addAnnotation(AnnotationSpec annotationSpec) {
            Intrinsics.checkNotNullParameter(annotationSpec, "annotationSpec");
            Builder builder = this;
            AnnotationSpec.UseSiteTarget useSiteTarget = annotationSpec.getUseSiteTarget();
            int i = useSiteTarget == null ? -1 : WhenMappings.$EnumSwitchMapping$0[useSiteTarget.ordinal()];
            if (i == -1) {
                annotationSpec = annotationSpec.toBuilder().useSiteTarget(AnnotationSpec.UseSiteTarget.FILE).build();
            } else if (i != 1) {
                throw new IllegalStateException(("Use-site target " + annotationSpec.getUseSiteTarget() + " not supported for file annotations.").toString());
            }
            this.annotations.add(annotationSpec);
            return this;
        }

        public final Builder addAnnotation(ClassName className) {
            Intrinsics.checkNotNullParameter(className, "annotation");
            return addAnnotation(AnnotationSpec.Companion.builder(className).build());
        }

        public final Builder addAnnotation(Class<?> cls) {
            Intrinsics.checkNotNullParameter(cls, "annotation");
            return addAnnotation(ClassNames.get(cls));
        }

        public final Builder addAnnotation(KClass<?> kClass) {
            Intrinsics.checkNotNullParameter(kClass, "annotation");
            return addAnnotation(ClassNames.get(kClass));
        }

        public final Builder addFileComment(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(objArr, "args");
            Builder builder = this;
            this.comment.add(StringsKt.replace$default(str, ' ', (char) Typography.middleDot, false, 4, (Object) null), Arrays.copyOf(objArr, objArr.length));
            return this;
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Use addFileComment() instead.", replaceWith = @ReplaceWith(expression = "addFileComment(format, args)", imports = {}))
        public final Builder addComment(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(objArr, "args");
            return addFileComment(str, Arrays.copyOf(objArr, objArr.length));
        }

        public final Builder clearComment() {
            Builder builder = this;
            this.comment.clear();
            return this;
        }

        public final Builder addType(TypeSpec typeSpec) {
            Intrinsics.checkNotNullParameter(typeSpec, "typeSpec");
            Builder builder = this;
            if (this.isScript) {
                this.body.add("%L", typeSpec);
            } else {
                this.members.add(typeSpec);
            }
            return this;
        }

        public final Builder addFunction(FunSpec funSpec) {
            Intrinsics.checkNotNullParameter(funSpec, "funSpec");
            Builder builder = this;
            if (!funSpec.isConstructor() && !funSpec.isAccessor()) {
                if (this.isScript) {
                    this.body.add("%L", funSpec);
                } else {
                    this.members.add(funSpec);
                }
                return this;
            }
            throw new IllegalArgumentException(("cannot add " + funSpec.getName() + " to file " + this.name).toString());
        }

        public final Builder addProperty(PropertySpec propertySpec) {
            Intrinsics.checkNotNullParameter(propertySpec, "propertySpec");
            Builder builder = this;
            if (this.isScript) {
                this.body.add("%L", propertySpec);
            } else {
                this.members.add(propertySpec);
            }
            return this;
        }

        public final Builder addTypeAlias(TypeAliasSpec typeAliasSpec) {
            Intrinsics.checkNotNullParameter(typeAliasSpec, "typeAliasSpec");
            Builder builder = this;
            if (this.isScript) {
                this.body.add("%L", typeAliasSpec);
            } else {
                this.members.add(typeAliasSpec);
            }
            return this;
        }

        public final Builder addImport(Enum<?> enumR) {
            Intrinsics.checkNotNullParameter(enumR, "constant");
            Class<?> declaringClass = enumR.getDeclaringClass();
            Intrinsics.checkNotNullExpressionValue(declaringClass, "constant as java.lang.Enum<*>).declaringClass");
            return addImport(ClassNames.get(declaringClass), enumR.name());
        }

        public final Builder addImport(Class<?> cls, String... strArr) {
            Intrinsics.checkNotNullParameter(cls, "class");
            Intrinsics.checkNotNullParameter(strArr, "names");
            Builder builder = this;
            if (!(strArr.length == 0)) {
                addImport(ClassNames.get(cls), (Iterable<String>) ArraysKt.toList((T[]) strArr));
                return this;
            }
            throw new IllegalArgumentException("names array is empty".toString());
        }

        public final Builder addImport(KClass<?> kClass, String... strArr) {
            Intrinsics.checkNotNullParameter(kClass, "class");
            Intrinsics.checkNotNullParameter(strArr, "names");
            Builder builder = this;
            if (!(strArr.length == 0)) {
                addImport(ClassNames.get(kClass), (Iterable<String>) ArraysKt.toList((T[]) strArr));
                return this;
            }
            throw new IllegalArgumentException("names array is empty".toString());
        }

        public final Builder addImport(ClassName className, String... strArr) {
            Intrinsics.checkNotNullParameter(className, "className");
            Intrinsics.checkNotNullParameter(strArr, "names");
            Builder builder = this;
            if (!(strArr.length == 0)) {
                addImport(className, (Iterable<String>) ArraysKt.toList((T[]) strArr));
                return this;
            }
            throw new IllegalArgumentException("names array is empty".toString());
        }

        public final Builder addImport(Class<?> cls, Iterable<String> iterable) {
            Intrinsics.checkNotNullParameter(cls, "class");
            Intrinsics.checkNotNullParameter(iterable, "names");
            return addImport(ClassNames.get(cls), iterable);
        }

        public final Builder addImport(KClass<?> kClass, Iterable<String> iterable) {
            Intrinsics.checkNotNullParameter(kClass, "class");
            Intrinsics.checkNotNullParameter(iterable, "names");
            return addImport(ClassNames.get(kClass), iterable);
        }

        public final Builder addImport(ClassName className, Iterable<String> iterable) {
            Intrinsics.checkNotNullParameter(className, "className");
            Intrinsics.checkNotNullParameter(iterable, "names");
            Builder builder = this;
            if (!CollectionsKt.contains(iterable, ProxyConfig.MATCH_ALL_SCHEMES)) {
                for (String str : iterable) {
                    this.memberImports.add(new Import(className.getCanonicalName() + FilenameUtils.EXTENSION_SEPARATOR + str, (String) null, 2, (DefaultConstructorMarker) null));
                }
                return this;
            }
            throw new IllegalArgumentException("Wildcard imports are not allowed".toString());
        }

        public final Builder addImport(String str, String... strArr) {
            Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
            Intrinsics.checkNotNullParameter(strArr, "names");
            Builder builder = this;
            if (!(strArr.length == 0)) {
                addImport(str, (Iterable<String>) ArraysKt.toList((T[]) strArr));
                return this;
            }
            throw new IllegalArgumentException("names array is empty".toString());
        }

        public final Builder addImport(String str, Iterable<String> iterable) {
            Import importR;
            Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
            Intrinsics.checkNotNullParameter(iterable, "names");
            Builder builder = this;
            if (!CollectionsKt.contains(iterable, ProxyConfig.MATCH_ALL_SCHEMES)) {
                for (String next : iterable) {
                    Collection collection = this.memberImports;
                    if (str.length() > 0) {
                        importR = new Import(str + FilenameUtils.EXTENSION_SEPARATOR + next, (String) null, 2, (DefaultConstructorMarker) null);
                    } else {
                        importR = new Import(next, (String) null, 2, (DefaultConstructorMarker) null);
                    }
                    collection.add(importR);
                }
                return this;
            }
            throw new IllegalArgumentException("Wildcard imports are not allowed".toString());
        }

        public final Builder addImport(Import importR) {
            Intrinsics.checkNotNullParameter(importR, "import");
            Builder builder = this;
            this.memberImports.add(importR);
            return this;
        }

        public final Builder clearImports() {
            Builder builder = this;
            this.memberImports.clear();
            return this;
        }

        public final Builder addAliasedImport(Class<?> cls, String str) {
            Intrinsics.checkNotNullParameter(cls, "class");
            Intrinsics.checkNotNullParameter(str, "as");
            return addAliasedImport(ClassNames.get(cls), str);
        }

        public final Builder addAliasedImport(KClass<?> kClass, String str) {
            Intrinsics.checkNotNullParameter(kClass, "class");
            Intrinsics.checkNotNullParameter(str, "as");
            return addAliasedImport(ClassNames.get(kClass), str);
        }

        public final Builder addAliasedImport(ClassName className, String str) {
            Intrinsics.checkNotNullParameter(className, "className");
            Intrinsics.checkNotNullParameter(str, "as");
            Builder builder = this;
            this.memberImports.add(new Import(className.getCanonicalName(), str));
            return this;
        }

        public final Builder addAliasedImport(ClassName className, String str, String str2) {
            Intrinsics.checkNotNullParameter(className, "className");
            Intrinsics.checkNotNullParameter(str, "memberName");
            Intrinsics.checkNotNullParameter(str2, "as");
            Builder builder = this;
            this.memberImports.add(new Import(className.getCanonicalName() + FilenameUtils.EXTENSION_SEPARATOR + str, str2));
            return this;
        }

        public final Builder addAliasedImport(MemberName memberName, String str) {
            Intrinsics.checkNotNullParameter(memberName, "memberName");
            Intrinsics.checkNotNullParameter(str, "as");
            Builder builder = this;
            this.memberImports.add(new Import(memberName.getCanonicalName(), str));
            return this;
        }

        public final Builder addDefaultPackageImport(String str) {
            Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
            Builder builder = this;
            this.defaultImports.add(str);
            return this;
        }

        public static /* synthetic */ Builder addKotlinDefaultImports$default(Builder builder, boolean z, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = true;
            }
            if ((i & 2) != 0) {
                z2 = true;
            }
            return builder.addKotlinDefaultImports(z, z2);
        }

        public final Builder addKotlinDefaultImports(boolean z, boolean z2) {
            Builder builder = this;
            CollectionsKt.addAll(this.defaultImports, FileSpecKt.KOTLIN_DEFAULT_IMPORTS);
            if (z) {
                CollectionsKt.addAll(this.defaultImports, FileSpecKt.KOTLIN_DEFAULT_JVM_IMPORTS);
            }
            if (z2) {
                CollectionsKt.addAll(this.defaultImports, FileSpecKt.KOTLIN_DEFAULT_JS_IMPORTS);
            }
            return this;
        }

        public final Builder indent(String str) {
            Intrinsics.checkNotNullParameter(str, "indent");
            Builder builder = this;
            this.indent = str;
            return this;
        }

        public final Builder addCode(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(objArr, "args");
            Builder builder = this;
            if (this.isScript) {
                this.body.add(str, Arrays.copyOf(objArr, objArr.length));
                return this;
            }
            throw new IllegalStateException("addCode() is only allowed in script files".toString());
        }

        public final Builder addNamedCode(String str, Map<String, ?> map) {
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(map, "args");
            Builder builder = this;
            if (this.isScript) {
                this.body.addNamed(str, map);
                return this;
            }
            throw new IllegalStateException("addNamedCode() is only allowed in script files".toString());
        }

        public final Builder addCode(CodeBlock codeBlock) {
            Intrinsics.checkNotNullParameter(codeBlock, "codeBlock");
            Builder builder = this;
            if (this.isScript) {
                this.body.add(codeBlock);
                return this;
            }
            throw new IllegalStateException("addCode() is only allowed in script files".toString());
        }

        public final Builder addBodyComment(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(objArr, "args");
            Builder builder = this;
            if (this.isScript) {
                this.body.add("//·" + StringsKt.replace$default(str, ' ', (char) Typography.middleDot, false, 4, (Object) null) + 10, Arrays.copyOf(objArr, objArr.length));
                return this;
            }
            throw new IllegalStateException("addBodyComment() is only allowed in script files".toString());
        }

        public final Builder beginControlFlow(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "controlFlow");
            Intrinsics.checkNotNullParameter(objArr, "args");
            Builder builder = this;
            if (this.isScript) {
                this.body.beginControlFlow(str, Arrays.copyOf(objArr, objArr.length));
                return this;
            }
            throw new IllegalStateException("beginControlFlow() is only allowed in script files".toString());
        }

        public final Builder nextControlFlow(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "controlFlow");
            Intrinsics.checkNotNullParameter(objArr, "args");
            Builder builder = this;
            if (this.isScript) {
                this.body.nextControlFlow(str, Arrays.copyOf(objArr, objArr.length));
                return this;
            }
            throw new IllegalStateException("nextControlFlow() is only allowed in script files".toString());
        }

        public final Builder endControlFlow() {
            Builder builder = this;
            if (this.isScript) {
                this.body.endControlFlow();
                return this;
            }
            throw new IllegalStateException("endControlFlow() is only allowed in script files".toString());
        }

        public final Builder addStatement(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(objArr, "args");
            Builder builder = this;
            if (this.isScript) {
                this.body.addStatement(str, Arrays.copyOf(objArr, objArr.length));
                return this;
            }
            throw new IllegalStateException("addStatement() is only allowed in script files".toString());
        }

        public final Builder clearBody() {
            Builder builder = this;
            if (this.isScript) {
                this.body.clear();
                return this;
            }
            throw new IllegalStateException("clearBody() is only allowed in script files".toString());
        }

        public final FileSpec build() {
            for (AnnotationSpec next : this.annotations) {
                if (next.getUseSiteTarget() != AnnotationSpec.UseSiteTarget.FILE) {
                    throw new IllegalStateException(("Use-site target " + next.getUseSiteTarget() + " not supported for file annotations.").toString());
                }
            }
            return new FileSpec(this, (TagMap) null, 2, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u001a\u0010\f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\r"}, d2 = {"Lcom/squareup/kotlinpoet/FileSpec$Companion;", "", "()V", "builder", "Lcom/squareup/kotlinpoet/FileSpec$Builder;", "packageName", "", "fileName", "get", "Lcom/squareup/kotlinpoet/FileSpec;", "typeSpec", "Lcom/squareup/kotlinpoet/TypeSpec;", "scriptBuilder", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: FileSpec.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final FileSpec get(String str, TypeSpec typeSpec) {
            Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
            Intrinsics.checkNotNullParameter(typeSpec, "typeSpec");
            String name = typeSpec.getName();
            if (name != null) {
                return builder(str, name).addType(typeSpec).build();
            }
            throw new IllegalArgumentException("file name required but type has no name");
        }

        @JvmStatic
        public final Builder builder(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
            Intrinsics.checkNotNullParameter(str2, "fileName");
            return new Builder(str, str2, false);
        }

        public static /* synthetic */ Builder scriptBuilder$default(Companion companion, String str, String str2, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = "";
            }
            return companion.scriptBuilder(str, str2);
        }

        @JvmStatic
        public final Builder scriptBuilder(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "fileName");
            Intrinsics.checkNotNullParameter(str2, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
            return new Builder(str2, str, true);
        }
    }
}
