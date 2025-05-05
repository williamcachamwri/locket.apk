package com.squareup.kotlinpoet;

import androidx.media3.exoplayer.upstream.CmcdData;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 #2\u00020\u0001:\u0002\"#B%\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\u0002\u0010\u0006J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0002J\r\u0010\r\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0006\u0010\u0011\u001a\u00020\u000bJ\u0006\u0010\u0012\u001a\u00020\u000bJ\u001d\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u0016J\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\u0004H\u0016J\u0015\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001bH\u0000¢\u0006\u0002\b\u001cJ\r\u0010\u001d\u001a\u00020\u0000H\u0000¢\u0006\u0002\b\u001eJ\u0017\u0010\u001f\u001a\u0004\u0018\u00010\u00002\u0006\u0010 \u001a\u00020\u0000H\u0000¢\u0006\u0002\b!R\u001c\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006$"}, d2 = {"Lcom/squareup/kotlinpoet/CodeBlock;", "", "formatParts", "", "", "args", "(Ljava/util/List;Ljava/util/List;)V", "getArgs$kotlinpoet", "()Ljava/util/List;", "getFormatParts$kotlinpoet", "equals", "", "other", "hasStatements", "hasStatements$kotlinpoet", "hashCode", "", "isEmpty", "isNotEmpty", "replaceAll", "oldValue", "newValue", "replaceAll$kotlinpoet", "toBuilder", "Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "toString", "codeWriter", "Lcom/squareup/kotlinpoet/CodeWriter;", "toString$kotlinpoet", "trim", "trim$kotlinpoet", "withoutPrefix", "prefix", "withoutPrefix$kotlinpoet", "Builder", "Companion", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: CodeBlock.kt */
public final class CodeBlock {
    private static final int ARG_NAME = 1;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final CodeBlock EMPTY = new CodeBlock(CollectionsKt.emptyList(), CollectionsKt.emptyList());
    /* access modifiers changed from: private */
    public static final Regex LOWERCASE = new Regex("[a-z]+[\\w_]*");
    /* access modifiers changed from: private */
    public static final Regex NAMED_ARGUMENT = new Regex("%([\\w_]+):([\\w]).*");
    private static final Set<String> NO_ARG_PLACEHOLDERS = SetsKt.setOf("⇥", "⇤", "«", "»");
    private static final int TYPE_NAME = 2;
    private final List<Object> args;
    private final List<String> formatParts;

    public /* synthetic */ CodeBlock(List list, List list2, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, list2);
    }

    @JvmStatic
    public static final Builder builder() {
        return Companion.builder();
    }

    @JvmStatic
    public static final CodeBlock of(String str, Object... objArr) {
        return Companion.of(str, objArr);
    }

    private CodeBlock(List<String> list, List<? extends Object> list2) {
        this.formatParts = list;
        this.args = list2;
    }

    public final List<String> getFormatParts$kotlinpoet() {
        return this.formatParts;
    }

    public final List<Object> getArgs$kotlinpoet() {
        return this.args;
    }

    public final boolean isEmpty() {
        return this.formatParts.isEmpty();
    }

    public final boolean isNotEmpty() {
        return !isEmpty();
    }

    public final CodeBlock withoutPrefix$kotlinpoet(CodeBlock codeBlock) {
        Intrinsics.checkNotNullParameter(codeBlock, "prefix");
        if (this.formatParts.size() < codeBlock.formatParts.size() || this.args.size() < codeBlock.args.size()) {
            return null;
        }
        int i = 0;
        int i2 = 0;
        String str = null;
        for (Object next : codeBlock.formatParts) {
            int i3 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str2 = (String) next;
            if (!Intrinsics.areEqual((Object) this.formatParts.get(i), (Object) str2)) {
                if (i != codeBlock.formatParts.size() - 1 || !StringsKt.startsWith$default(this.formatParts.get(i), str2, false, 2, (Object) null)) {
                    return null;
                }
                str = this.formatParts.get(i).substring(str2.length());
                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).substring(startIndex)");
            }
            if (StringsKt.startsWith$default(str2, "%", false, 2, (Object) null) && !Companion.isMultiCharNoArgPlaceholder$kotlinpoet(str2.charAt(1))) {
                if (!Intrinsics.areEqual(this.args.get(i2), codeBlock.args.get(i2))) {
                    return null;
                }
                i2++;
            }
            i = i3;
        }
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            arrayList.add(str);
        }
        int size = this.formatParts.size();
        for (int size2 = codeBlock.formatParts.size(); size2 < size; size2++) {
            arrayList.add(this.formatParts.get(size2));
        }
        ArrayList arrayList2 = new ArrayList();
        int size3 = this.args.size();
        for (int size4 = codeBlock.args.size(); size4 < size3; size4++) {
            arrayList2.add(this.args.get(size4));
        }
        return new CodeBlock(arrayList, arrayList2);
    }

    public final CodeBlock trim$kotlinpoet() {
        int size = this.formatParts.size();
        int i = 0;
        while (i < size && NO_ARG_PLACEHOLDERS.contains(this.formatParts.get(i))) {
            i++;
        }
        while (i < size && NO_ARG_PLACEHOLDERS.contains(this.formatParts.get(size - 1))) {
            size--;
        }
        return (i > 0 || size < this.formatParts.size()) ? new CodeBlock(this.formatParts.subList(i, size), this.args) : this;
    }

    public final CodeBlock replaceAll$kotlinpoet(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "oldValue");
        Intrinsics.checkNotNullParameter(str2, "newValue");
        Iterable<String> iterable = this.formatParts;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String replace$default : iterable) {
            arrayList.add(StringsKt.replace$default(replace$default, str, str2, false, 4, (Object) null));
        }
        return new CodeBlock((List) arrayList, this.args);
    }

    public final boolean hasStatements$kotlinpoet() {
        Iterable<String> iterable = this.formatParts;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (String contains$default : iterable) {
            if (StringsKt.contains$default((CharSequence) contains$default, (CharSequence) "«", false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
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

    public final String toString$kotlinpoet(CodeWriter codeWriter) {
        Intrinsics.checkNotNullParameter(codeWriter, "codeWriter");
        return CodeWriterKt.buildCodeString(codeWriter, new CodeBlock$toString$2(this));
    }

    public final Builder toBuilder() {
        Builder builder = new Builder();
        CollectionsKt.addAll(builder.getFormatParts$kotlinpoet(), this.formatParts);
        builder.getArgs$kotlinpoet().addAll(this.args);
        return builder;
    }

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fJ+\u0010\n\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\b2\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000e\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u000fJ\"\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0002J \u0010\u0015\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\b2\u0010\u0010\u0016\u001a\f\u0012\u0004\u0012\u00020\b\u0012\u0002\b\u00030\u0017J+\u0010\u0018\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\b2\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000e\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u000fJ\u0014\u0010\u0019\u001a\u0004\u0018\u00010\u00012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u0002J\u0012\u0010\u001b\u001a\u00020\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u0002J\u0014\u0010\u001c\u001a\u0004\u0018\u00010\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u0002J\u0012\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u0002J+\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\b2\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000e\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u000fJ\u0006\u0010!\u001a\u00020\fJ\u0006\u0010\"\u001a\u00020\u0000J\u0006\u0010#\u001a\u00020\u0000J\u0006\u0010$\u001a\u00020\u0000J\u0006\u0010%\u001a\u00020&J\u0006\u0010'\u001a\u00020&J\u0010\u0010(\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0001H\u0002J+\u0010)\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\b2\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000e\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u000fJ\u0006\u0010*\u001a\u00020\u0000J\f\u0010+\u001a\u00020\b*\u00020\bH\u0002R\u001c\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0006¨\u0006,"}, d2 = {"Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "", "()V", "args", "", "getArgs$kotlinpoet", "()Ljava/util/List;", "formatParts", "", "getFormatParts$kotlinpoet", "add", "codeBlock", "Lcom/squareup/kotlinpoet/CodeBlock;", "format", "", "(Ljava/lang/String;[Ljava/lang/Object;)Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "addArgument", "", "c", "", "arg", "addNamed", "arguments", "", "addStatement", "argToLiteral", "o", "argToName", "argToString", "argToType", "Lcom/squareup/kotlinpoet/TypeName;", "beginControlFlow", "controlFlow", "build", "clear", "endControlFlow", "indent", "isEmpty", "", "isNotEmpty", "logDeprecationWarning", "nextControlFlow", "unindent", "withOpeningBrace", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CodeBlock.kt */
    public static final class Builder {
        private final List<Object> args = new ArrayList();
        private final List<String> formatParts = new ArrayList();

        private final Object argToLiteral(Object obj) {
            return obj;
        }

        public final List<String> getFormatParts$kotlinpoet() {
            return this.formatParts;
        }

        public final List<Object> getArgs$kotlinpoet() {
            return this.args;
        }

        public final boolean isEmpty() {
            return this.formatParts.isEmpty();
        }

        public final boolean isNotEmpty() {
            return !isEmpty();
        }

        public final Builder addNamed(String str, Map<String, ?> map) {
            MatchResult matchResult;
            int i;
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(map, "arguments");
            Builder builder = this;
            for (String next : map.keySet()) {
                if (!CodeBlock.LOWERCASE.matches(next)) {
                    throw new IllegalArgumentException(("argument '" + next + "' must start with a lowercase character").toString());
                }
            }
            int i2 = 0;
            while (true) {
                if (i2 >= str.length()) {
                    break;
                }
                int nextPotentialPlaceholderPosition$kotlinpoet = CodeBlock.Companion.nextPotentialPlaceholderPosition$kotlinpoet(str, i2);
                if (nextPotentialPlaceholderPosition$kotlinpoet == -1) {
                    String substring = str.substring(i2, str.length());
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                    this.formatParts.add(substring);
                    break;
                }
                if (i2 != nextPotentialPlaceholderPosition$kotlinpoet) {
                    String substring2 = str.substring(i2, nextPotentialPlaceholderPosition$kotlinpoet);
                    Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
                    this.formatParts.add(substring2);
                    i2 = nextPotentialPlaceholderPosition$kotlinpoet;
                }
                int indexOf$default = StringsKt.indexOf$default((CharSequence) str, (char) AbstractJsonLexerKt.COLON, i2, false, 4, (Object) null);
                if (indexOf$default != -1) {
                    int coerceAtMost = RangesKt.coerceAtMost(indexOf$default + 2, str.length());
                    Regex access$getNAMED_ARGUMENT$cp = CodeBlock.NAMED_ARGUMENT;
                    String substring3 = str.substring(i2, coerceAtMost);
                    Intrinsics.checkNotNullExpressionValue(substring3, "this as java.lang.String…ing(startIndex, endIndex)");
                    matchResult = access$getNAMED_ARGUMENT$cp.matchEntire(substring3);
                } else {
                    matchResult = null;
                }
                boolean z = true;
                if (matchResult != null) {
                    String str2 = matchResult.getGroupValues().get(1);
                    if (map.containsKey(str2)) {
                        char first = StringsKt.first(matchResult.getGroupValues().get(2));
                        addArgument(str, first, map.get(str2));
                        this.formatParts.add("%" + first);
                        i2 += matchResult.getRange().getLast() + 1;
                    } else {
                        throw new IllegalArgumentException(("Missing named argument for %" + str2).toString());
                    }
                } else {
                    if (CodeBlock.Companion.isSingleCharNoArgPlaceholder$kotlinpoet(str.charAt(i2))) {
                        i = i2 + 1;
                        String substring4 = str.substring(i2, i);
                        Intrinsics.checkNotNullExpressionValue(substring4, "this as java.lang.String…ing(startIndex, endIndex)");
                        this.formatParts.add(substring4);
                    } else {
                        if (i2 >= str.length() - 1) {
                            z = false;
                        }
                        if (z) {
                            int i3 = i2 + 1;
                            if (CodeBlock.Companion.isMultiCharNoArgPlaceholder$kotlinpoet(str.charAt(i3))) {
                                i = i2 + 2;
                                String substring5 = str.substring(i2, i);
                                Intrinsics.checkNotNullExpressionValue(substring5, "this as java.lang.String…ing(startIndex, endIndex)");
                                this.formatParts.add(substring5);
                            } else {
                                throw new IllegalArgumentException(("unknown format %" + str.charAt(i3) + " at " + i3 + " in '" + str + '\'').toString());
                            }
                        } else {
                            throw new IllegalArgumentException("dangling % at end".toString());
                        }
                    }
                    i2 = i;
                }
            }
            return this;
        }

        public final Builder add(String str, Object... objArr) {
            boolean z;
            int i;
            String str2 = str;
            Object[] objArr2 = objArr;
            Intrinsics.checkNotNullParameter(str2, "format");
            Intrinsics.checkNotNullParameter(objArr2, "args");
            Builder builder = this;
            int[] iArr = new int[objArr2.length];
            int i2 = 0;
            boolean z2 = false;
            int i3 = 0;
            boolean z3 = false;
            while (true) {
                boolean z4 = true;
                if (i2 >= str.length()) {
                    if (z2) {
                        if (!(i3 >= objArr2.length)) {
                            throw new IllegalArgumentException(("unused arguments: expected " + i3 + ", received " + objArr2.length).toString());
                        }
                    }
                    if (z3) {
                        List arrayList = new ArrayList();
                        int length = objArr2.length;
                        for (int i4 = 0; i4 < length; i4++) {
                            if (iArr[i4] == 0) {
                                arrayList.add("%" + (i4 + 1));
                            }
                        }
                        String str3 = arrayList.size() == 1 ? "" : CmcdData.Factory.STREAMING_FORMAT_SS;
                        if (!arrayList.isEmpty()) {
                            throw new IllegalArgumentException(("unused argument" + str3 + ": " + CollectionsKt.joinToString$default(arrayList, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null)).toString());
                        }
                    }
                    return this;
                } else if (CodeBlock.Companion.isSingleCharNoArgPlaceholder$kotlinpoet(str2.charAt(i2))) {
                    this.formatParts.add(String.valueOf(str2.charAt(i2)));
                    i2++;
                } else if (str2.charAt(i2) != '%') {
                    int nextPotentialPlaceholderPosition$kotlinpoet = CodeBlock.Companion.nextPotentialPlaceholderPosition$kotlinpoet(str2, i2 + 1);
                    if (nextPotentialPlaceholderPosition$kotlinpoet == -1) {
                        nextPotentialPlaceholderPosition$kotlinpoet = str.length();
                    }
                    String substring = str2.substring(i2, nextPotentialPlaceholderPosition$kotlinpoet);
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                    this.formatParts.add(substring);
                    i2 = nextPotentialPlaceholderPosition$kotlinpoet;
                } else {
                    int i5 = i2 + 1;
                    int i6 = i5;
                    while (true) {
                        if (i6 < str.length()) {
                            int i7 = i6 + 1;
                            char charAt = str2.charAt(i6);
                            if (!('0' <= charAt && charAt < ':')) {
                                int i8 = i7 - 1;
                                if (CodeBlock.Companion.isMultiCharNoArgPlaceholder$kotlinpoet(charAt)) {
                                    if (i5 != i8) {
                                        z4 = false;
                                    }
                                    if (z4) {
                                        this.formatParts.add("%" + charAt);
                                        i2 = i7;
                                    } else {
                                        throw new IllegalArgumentException("%% may not have an index".toString());
                                    }
                                } else {
                                    if (i5 < i8) {
                                        String substring2 = str2.substring(i5, i8);
                                        Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
                                        int parseInt = Integer.parseInt(substring2) - 1;
                                        if (!(objArr2.length == 0)) {
                                            int length2 = parseInt % objArr2.length;
                                            iArr[length2] = iArr[length2] + 1;
                                        }
                                        z = true;
                                        int i9 = parseInt;
                                        i = i3;
                                        i3 = i9;
                                    } else {
                                        z = z3;
                                        i = i3 + 1;
                                        z2 = true;
                                    }
                                    if (i3 >= 0 && i3 < objArr2.length) {
                                        if (z && z2) {
                                            z4 = false;
                                        }
                                        if (z4) {
                                            addArgument(str2, charAt, objArr2[i3]);
                                            this.formatParts.add("%" + charAt);
                                            i3 = i;
                                            i2 = i7;
                                            z3 = z;
                                        } else {
                                            throw new IllegalArgumentException("cannot mix indexed and positional parameters".toString());
                                        }
                                    } else {
                                        StringBuilder append = new StringBuilder("index ").append(i3 + 1).append(" for '");
                                        String substring3 = str2.substring(i5 - 1, i8 + 1);
                                        Intrinsics.checkNotNullExpressionValue(substring3, "this as java.lang.String…ing(startIndex, endIndex)");
                                        throw new IllegalArgumentException(append.append(substring3).append("' not in range (received ").append(objArr2.length).append(" arguments)").toString().toString());
                                    }
                                }
                            } else {
                                i6 = i7;
                            }
                        } else {
                            throw new IllegalArgumentException(("dangling format characters in '" + str2 + '\'').toString());
                        }
                    }
                }
            }
        }

        private final void addArgument(String str, char c, Object obj) {
            if (c == 'N') {
                this.args.add(UtilKt.escapeIfNecessary$default(argToName(obj), false, 1, (Object) null));
            } else if (c == 'L') {
                this.args.add(argToLiteral(obj));
            } else if (c == 'S') {
                this.args.add(argToString(obj));
            } else if (c == 'P') {
                Collection collection = this.args;
                if (!(obj instanceof CodeBlock)) {
                    obj = argToString(obj);
                }
                collection.add(obj);
            } else if (c == 'T') {
                this.args.add(argToType(obj));
            } else if (c == 'M') {
                this.args.add(obj);
            } else {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("invalid format string: '%s'", Arrays.copyOf(new Object[]{str}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                throw new IllegalArgumentException(format);
            }
        }

        private final String argToName(Object obj) {
            if (obj instanceof CharSequence) {
                return obj.toString();
            }
            if (obj instanceof ParameterSpec) {
                return ((ParameterSpec) obj).getName();
            }
            if (obj instanceof PropertySpec) {
                return ((PropertySpec) obj).getName();
            }
            if (obj instanceof FunSpec) {
                return ((FunSpec) obj).getName();
            }
            if (obj instanceof TypeSpec) {
                String name = ((TypeSpec) obj).getName();
                Intrinsics.checkNotNull(name);
                return name;
            } else if (obj instanceof MemberName) {
                return ((MemberName) obj).getSimpleName();
            } else {
                throw new IllegalArgumentException("expected name but was " + obj);
            }
        }

        private final String argToString(Object obj) {
            if (obj != null) {
                return obj.toString();
            }
            return null;
        }

        private final void logDeprecationWarning(Object obj) {
            System.out.println("Deprecation warning: converting " + obj + " to TypeName. Conversion of TypeMirror and TypeElement is deprecated in KotlinPoet, use kotlin-metadata APIs instead.");
        }

        private final TypeName argToType(Object obj) {
            if (obj instanceof TypeName) {
                return (TypeName) obj;
            }
            if (obj instanceof TypeMirror) {
                logDeprecationWarning(obj);
                return TypeNames.get((TypeMirror) obj);
            } else if (obj instanceof Element) {
                logDeprecationWarning(obj);
                TypeMirror asType = ((Element) obj).asType();
                Intrinsics.checkNotNullExpressionValue(asType, "o.asType()");
                return TypeNames.get(asType);
            } else if (obj instanceof Type) {
                return TypeNames.get((Type) obj);
            } else {
                if (obj instanceof KClass) {
                    return TypeNames.get((KClass<?>) (KClass) obj);
                }
                throw new IllegalArgumentException("expected type but was " + obj);
            }
        }

        public final Builder beginControlFlow(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "controlFlow");
            Intrinsics.checkNotNullParameter(objArr, "args");
            Builder builder = this;
            add(withOpeningBrace(str), Arrays.copyOf(objArr, objArr.length));
            indent();
            return this;
        }

        private final String withOpeningBrace(String str) {
            int length = str.length();
            while (true) {
                length--;
                if (-1 >= length) {
                    break;
                } else if (str.charAt(length) == '{') {
                    return str + 10;
                } else {
                    if (str.charAt(length) == '}') {
                        break;
                    }
                }
            }
            return str + "·{\n";
        }

        public final Builder nextControlFlow(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "controlFlow");
            Intrinsics.checkNotNullParameter(objArr, "args");
            Builder builder = this;
            unindent();
            add("}·" + str + "·{\n", Arrays.copyOf(objArr, objArr.length));
            indent();
            return this;
        }

        public final Builder endControlFlow() {
            Builder builder = this;
            unindent();
            add("}\n", new Object[0]);
            return this;
        }

        public final Builder addStatement(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(objArr, "args");
            Builder builder = this;
            add("«", new Object[0]);
            add(str, Arrays.copyOf(objArr, objArr.length));
            add("\n»", new Object[0]);
            return this;
        }

        public final Builder add(CodeBlock codeBlock) {
            Intrinsics.checkNotNullParameter(codeBlock, "codeBlock");
            Builder builder = this;
            CollectionsKt.addAll(this.formatParts, codeBlock.getFormatParts$kotlinpoet());
            this.args.addAll(codeBlock.getArgs$kotlinpoet());
            return this;
        }

        public final Builder indent() {
            Builder builder = this;
            this.formatParts.add("⇥");
            return this;
        }

        public final Builder unindent() {
            Builder builder = this;
            this.formatParts.add("⇤");
            return this;
        }

        public final Builder clear() {
            Builder builder = this;
            this.formatParts.clear();
            this.args.clear();
            return this;
        }

        public final CodeBlock build() {
            return new CodeBlock(UtilKt.toImmutableList(this.formatParts), UtilKt.toImmutableList(this.args), (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0007J-\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u000e2\u0016\u0010\u001e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u001f\"\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010 J\u0019\u0010!\u001a\u00020\u0004*\u00020\u000e2\u0006\u0010\"\u001a\u00020\u0004H\u0000¢\u0006\u0002\b#R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0018\u0010\u0010\u001a\u00020\u0011*\u00020\u00128@X\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0015\u001a\u00020\u0011*\u00020\u000e8@X\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0018\u001a\u00020\u0011*\u00020\u00128@X\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0014¨\u0006$"}, d2 = {"Lcom/squareup/kotlinpoet/CodeBlock$Companion;", "", "()V", "ARG_NAME", "", "EMPTY", "Lcom/squareup/kotlinpoet/CodeBlock;", "getEMPTY$kotlinpoet", "()Lcom/squareup/kotlinpoet/CodeBlock;", "LOWERCASE", "Lkotlin/text/Regex;", "NAMED_ARGUMENT", "NO_ARG_PLACEHOLDERS", "", "", "TYPE_NAME", "isMultiCharNoArgPlaceholder", "", "", "isMultiCharNoArgPlaceholder$kotlinpoet", "(C)Z", "isPlaceholder", "isPlaceholder$kotlinpoet", "(Ljava/lang/String;)Z", "isSingleCharNoArgPlaceholder", "isSingleCharNoArgPlaceholder$kotlinpoet", "builder", "Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "of", "format", "args", "", "(Ljava/lang/String;[Ljava/lang/Object;)Lcom/squareup/kotlinpoet/CodeBlock;", "nextPotentialPlaceholderPosition", "startIndex", "nextPotentialPlaceholderPosition$kotlinpoet", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CodeBlock.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isMultiCharNoArgPlaceholder$kotlinpoet(char c) {
            return c == '%';
        }

        private Companion() {
        }

        public final CodeBlock getEMPTY$kotlinpoet() {
            return CodeBlock.EMPTY;
        }

        @JvmStatic
        public final CodeBlock of(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(objArr, "args");
            return new Builder().add(str, Arrays.copyOf(objArr, objArr.length)).build();
        }

        @JvmStatic
        public final Builder builder() {
            return new Builder();
        }

        public final boolean isSingleCharNoArgPlaceholder$kotlinpoet(char c) {
            return UtilKt.isOneOf$default(Character.valueOf(c), 8677, 8676, 171, 187, (Object) null, (Object) null, 48, (Object) null);
        }

        public final boolean isPlaceholder$kotlinpoet(String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            if (str.length() == 1 && isSingleCharNoArgPlaceholder$kotlinpoet(StringsKt.first(str))) {
                return true;
            }
            if (str.length() != 2 || !isMultiCharNoArgPlaceholder$kotlinpoet(StringsKt.first(str))) {
                return false;
            }
            return true;
        }

        public final int nextPotentialPlaceholderPosition$kotlinpoet(String str, int i) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            return StringsKt.indexOfAny$default((CharSequence) str, new char[]{'%', 171, 187, 8677, 8676}, i, false, 4, (Object) null);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003b, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0037, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0038, code lost:
        kotlin.io.CloseableKt.closeFinally(r10, r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString() {
        /*
            r11 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            com.squareup.kotlinpoet.CodeWriter r10 = new com.squareup.kotlinpoet.CodeWriter
            r2 = r0
            java.lang.Appendable r2 = (java.lang.Appendable) r2
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 2147483647(0x7fffffff, float:NaN)
            r8 = 30
            r9 = 0
            r1 = r10
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            java.io.Closeable r10 = (java.io.Closeable) r10
            r1 = r10
            com.squareup.kotlinpoet.CodeWriter r1 = (com.squareup.kotlinpoet.CodeWriter) r1     // Catch:{ all -> 0x0035 }
            r3 = 0
            r4 = 0
            r5 = 6
            r6 = 0
            r2 = r11
            com.squareup.kotlinpoet.CodeWriter.emitCode$default(r1, r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0035 }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0035 }
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r10, r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "stringBuilder.toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            return r0
        L_0x0035:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0037 }
        L_0x0037:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r10, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.CodeBlock.toString():java.lang.String");
    }
}
