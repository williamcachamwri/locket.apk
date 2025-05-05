package com.squareup.kotlinpoet;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.apache.commons.io.FilenameUtils;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 .2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0001.B+\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0007\"\u00020\u0004¢\u0006\u0002\u0010\bB\u000f\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\tB#\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0007\"\u00020\u0004¢\u0006\u0002\u0010\nB\u001d\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b¢\u0006\u0002\u0010\fBI\b\u0000\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000b\u0012\u0018\b\u0002\u0010\u0012\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0012\u0004\u0012\u00020\u00150\u0013¢\u0006\u0002\u0010\u0016J\u0011\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0000H\u0002J\u0006\u0010!\u001a\u00020\"J6\u0010#\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000b2\u0016\u0010\u0012\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0012\u0004\u0012\u00020\u00150\u0013H\u0016J\u0015\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%H\u0010¢\u0006\u0002\b'J\b\u0010(\u001a\u0004\u0018\u00010\u0000J\u000e\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0004J\u000e\u0010+\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0004J\u0006\u0010,\u001a\u00020\u0004J\u0006\u0010-\u001a\u00020\u0000R\u0011\u0010\u0017\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u0005\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006/"}, d2 = {"Lcom/squareup/kotlinpoet/ClassName;", "Lcom/squareup/kotlinpoet/TypeName;", "", "packageName", "", "simpleName", "simpleNames", "", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V", "(Ljava/lang/String;)V", "(Ljava/lang/String;[Ljava/lang/String;)V", "", "(Ljava/lang/String;Ljava/util/List;)V", "names", "nullable", "", "annotations", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "tags", "", "Lkotlin/reflect/KClass;", "", "(Ljava/util/List;ZLjava/util/List;Ljava/util/Map;)V", "canonicalName", "getCanonicalName", "()Ljava/lang/String;", "getPackageName", "getSimpleName", "getSimpleNames", "()Ljava/util/List;", "compareTo", "", "other", "constructorReference", "Lcom/squareup/kotlinpoet/CodeBlock;", "copy", "emit", "Lcom/squareup/kotlinpoet/CodeWriter;", "out", "emit$kotlinpoet", "enclosingClassName", "nestedClass", "name", "peerClass", "reflectionName", "topLevelClassName", "Companion", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: ClassName.kt */
public final class ClassName extends TypeName implements Comparable<ClassName> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String canonicalName;
    private final List<String> names;

    @JvmStatic
    public static final ClassName bestGuess(String str) {
        return Companion.bestGuess(str);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ClassName(List list, boolean z, List list2, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? false : z, (i & 4) != 0 ? CollectionsKt.emptyList() : list2, (i & 8) != 0 ? MapsKt.emptyMap() : map);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ClassName(List<String> list, boolean z, List<AnnotationSpec> list2, Map<KClass<?>, ? extends Object> map) {
        super(z, list2, new TagMap(map), (DefaultConstructorMarker) null);
        String str;
        Intrinsics.checkNotNullParameter(list, "names");
        Intrinsics.checkNotNullParameter(list2, "annotations");
        Intrinsics.checkNotNullParameter(map, "tags");
        this.names = UtilKt.toImmutableList(list);
        if (list.get(0).length() == 0) {
            str = CollectionsKt.joinToString$default(list.subList(1, list.size()), ".", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        } else {
            str = CollectionsKt.joinToString$default(list, ".", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        }
        this.canonicalName = str;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ ClassName(java.lang.String r8, java.lang.String r9, java.lang.String... r10) {
        /*
            r7 = this;
            java.lang.String r0 = "packageName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "simpleName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String r0 = "simpleNames"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            kotlin.jvm.internal.SpreadBuilder r0 = new kotlin.jvm.internal.SpreadBuilder
            r1 = 3
            r0.<init>(r1)
            r0.add(r8)
            r0.add(r9)
            r0.addSpread(r10)
            int r8 = r0.size()
            java.lang.String[] r8 = new java.lang.String[r8]
            java.lang.Object[] r8 = r0.toArray(r8)
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r8)
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 14
            r6 = 0
            r0 = r7
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.ClassName.<init>(java.lang.String, java.lang.String, java.lang.String[]):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.ERROR, message = "Simple names must not be empty. Did you forget an argument?", replaceWith = @ReplaceWith(expression = "ClassName(packageName, TODO())", imports = {}))
    public ClassName(String str) {
        this(str, (List<String>) CollectionsKt.emptyList());
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ClassName(java.lang.String r8, java.lang.String... r9) {
        /*
            r7 = this;
            java.lang.String r0 = "packageName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "simpleNames"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            kotlin.jvm.internal.SpreadBuilder r0 = new kotlin.jvm.internal.SpreadBuilder
            r1 = 2
            r0.<init>(r1)
            r0.add(r8)
            r0.addSpread(r9)
            int r8 = r0.size()
            java.lang.String[] r8 = new java.lang.String[r8]
            java.lang.Object[] r8 = r0.toArray(r8)
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r8)
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 14
            r6 = 0
            r0 = r7
            r0.<init>(r1, r2, r3, r4, r5, r6)
            int r8 = r9.length
            r0 = 0
            r1 = 1
            if (r8 != 0) goto L_0x0035
            r8 = r1
            goto L_0x0036
        L_0x0035:
            r8 = r0
        L_0x0036:
            r8 = r8 ^ r1
            if (r8 == 0) goto L_0x0076
            int r8 = r9.length
            r2 = r0
        L_0x003b:
            if (r2 >= r8) goto L_0x0050
            r3 = r9[r2]
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            int r3 = r3.length()
            if (r3 != 0) goto L_0x0049
            r3 = r1
            goto L_0x004a
        L_0x0049:
            r3 = r0
        L_0x004a:
            if (r3 == 0) goto L_0x004d
            goto L_0x0051
        L_0x004d:
            int r2 = r2 + 1
            goto L_0x003b
        L_0x0050:
            r0 = r1
        L_0x0051:
            if (r0 == 0) goto L_0x0054
            return
        L_0x0054:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = "simpleNames must not contain empty items: "
            r8.<init>(r0)
            java.lang.String r9 = java.util.Arrays.toString(r9)
            java.lang.String r0 = "toString(this)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r0)
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r8 = r8.toString()
            r9.<init>(r8)
            throw r9
        L_0x0076:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "simpleNames must not be empty"
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.ClassName.<init>(java.lang.String, java.lang.String[]):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ClassName(java.lang.String r8, java.util.List<java.lang.String> r9) {
        /*
            r7 = this;
            java.lang.String r0 = "packageName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "simpleNames"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String[] r8 = new java.lang.String[]{r8}
            java.util.List r1 = kotlin.collections.CollectionsKt.mutableListOf(r8)
            r8 = r9
            java.util.Collection r8 = (java.util.Collection) r8
            r1.addAll(r8)
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 14
            r6 = 0
            r0 = r7
            r0.<init>(r1, r2, r3, r4, r5, r6)
            boolean r8 = r8.isEmpty()
            r0 = 1
            r8 = r8 ^ r0
            if (r8 == 0) goto L_0x0076
            r8 = r9
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            boolean r1 = r8 instanceof java.util.Collection
            if (r1 == 0) goto L_0x003b
            r1 = r8
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x003b
            goto L_0x005a
        L_0x003b:
            java.util.Iterator r8 = r8.iterator()
        L_0x003f:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L_0x005a
            java.lang.Object r1 = r8.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            int r1 = r1.length()
            r2 = 0
            if (r1 != 0) goto L_0x0056
            r1 = r0
            goto L_0x0057
        L_0x0056:
            r1 = r2
        L_0x0057:
            if (r1 == 0) goto L_0x003f
            r0 = r2
        L_0x005a:
            if (r0 == 0) goto L_0x005d
            return
        L_0x005d:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = "simpleNames must not contain empty items: "
            r8.<init>(r0)
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r8 = r8.toString()
            r9.<init>(r8)
            throw r9
        L_0x0076:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "simpleNames must not be empty"
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.ClassName.<init>(java.lang.String, java.util.List):void");
    }

    public final String getCanonicalName() {
        return this.canonicalName;
    }

    public final String getPackageName() {
        return this.names.get(0);
    }

    public final String getSimpleName() {
        List<String> list = this.names;
        return list.get(list.size() - 1);
    }

    public final List<String> getSimpleNames() {
        List<String> list = this.names;
        return list.subList(1, list.size());
    }

    public ClassName copy(boolean z, List<AnnotationSpec> list, Map<KClass<?>, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(list, "annotations");
        Intrinsics.checkNotNullParameter(map, "tags");
        return new ClassName(this.names, z, list, map);
    }

    public final ClassName enclosingClassName() {
        if (this.names.size() == 2) {
            return null;
        }
        List<String> list = this.names;
        return new ClassName(list.subList(0, list.size() - 1), false, (List) null, (Map) null, 14, (DefaultConstructorMarker) null);
    }

    public final ClassName topLevelClassName() {
        return new ClassName(this.names.subList(0, 2), false, (List) null, (Map) null, 14, (DefaultConstructorMarker) null);
    }

    public final String reflectionName() {
        if (this.names.size() == 2) {
            if (getPackageName().length() == 0) {
                return this.names.get(1);
            }
            return getPackageName() + FilenameUtils.EXTENSION_SEPARATOR + this.names.get(1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(topLevelClassName().canonicalName);
        for (String append : getSimpleNames().subList(1, getSimpleNames().size())) {
            sb.append(Typography.dollar).append(append);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    public final CodeBlock constructorReference() {
        ClassName enclosingClassName = enclosingClassName();
        if (enclosingClassName != null) {
            return CodeBlock.Companion.of("%T::%N", enclosingClassName, getSimpleName());
        }
        return CodeBlock.Companion.of("::%T", this);
    }

    public final ClassName nestedClass(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return new ClassName(CollectionsKt.plus(this.names, str), false, (List) null, (Map) null, 14, (DefaultConstructorMarker) null);
    }

    public final ClassName peerClass(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        List mutableList = CollectionsKt.toMutableList(this.names);
        mutableList.set(mutableList.size() - 1, str);
        return new ClassName(mutableList, false, (List) null, (Map) null, 14, (DefaultConstructorMarker) null);
    }

    public int compareTo(ClassName className) {
        Intrinsics.checkNotNullParameter(className, "other");
        return this.canonicalName.compareTo(className.canonicalName);
    }

    public CodeWriter emit$kotlinpoet(CodeWriter codeWriter) {
        Intrinsics.checkNotNullParameter(codeWriter, "out");
        return CodeWriter.emit$default(codeWriter, UtilKt.escapeSegmentsIfNecessary$default(codeWriter.lookupName(this), 0, 1, (Object) null), false, 2, (Object) null);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/squareup/kotlinpoet/ClassName$Companion;", "", "()V", "bestGuess", "Lcom/squareup/kotlinpoet/ClassName;", "classNameString", "", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: ClassName.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ClassName bestGuess(String str) {
            boolean z;
            String str2;
            String str3 = str;
            Intrinsics.checkNotNullParameter(str3, "classNameString");
            List arrayList = new ArrayList();
            boolean z2 = false;
            int i = 0;
            do {
                z = true;
                if (i >= str.length() || !Character.isLowerCase(str3.codePointAt(i))) {
                    Collection collection = arrayList;
                    if (i != 0) {
                        str2 = str3.substring(0, i - 1);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    } else {
                        str2 = "";
                    }
                    collection.add(str2);
                    String substring = str3.substring(i);
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                    for (String str4 : StringsKt.split$default((CharSequence) substring, new char[]{FilenameUtils.EXTENSION_SEPARATOR}, false, 0, 6, (Object) null)) {
                        if ((str4.length() > 0) && Character.isUpperCase(str4.codePointAt(0))) {
                            collection.add(str4);
                        } else {
                            throw new IllegalArgumentException(("couldn't make a guess for " + str3).toString());
                        }
                    }
                    if (arrayList.size() >= 2) {
                        z2 = true;
                    }
                    if (z2) {
                        return new ClassName(arrayList, false, (List) null, (Map) null, 14, (DefaultConstructorMarker) null);
                    }
                    throw new IllegalArgumentException(("couldn't make a guess for " + str3).toString());
                }
                i = StringsKt.indexOf$default((CharSequence) str3, (char) FilenameUtils.EXTENSION_SEPARATOR, i, false, 4, (Object) null) + 1;
                if (i == 0) {
                    z = false;
                    continue;
                }
            } while (z);
            throw new IllegalArgumentException(("couldn't make a guess for " + str3).toString());
        }
    }
}
