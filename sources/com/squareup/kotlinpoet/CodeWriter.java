package com.squareup.kotlinpoet;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

@Metadata(d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0002\b\u0007\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0000\u0018\u00002\u00020\u0001Bg\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b0\b\u0012\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r0\b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\b\u0010)\u001a\u00020*H\u0016J\u0018\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u00062\b\b\u0002\u0010-\u001a\u00020\u0012J\u001c\u0010.\u001a\u00020*2\f\u0010/\u001a\b\u0012\u0004\u0012\u000201002\u0006\u00102\u001a\u00020\u0012J\"\u00103\u001a\u00020\u00002\u0006\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u00020\u00122\b\b\u0002\u00107\u001a\u00020\u0012J\u000e\u00103\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u0006J+\u00103\u001a\u00020\u00002\u0006\u00108\u001a\u00020\u00062\u0016\u00109\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010;0:\"\u0004\u0018\u00010;¢\u0006\u0002\u0010<J\u000e\u0010=\u001a\u00020*2\u0006\u00104\u001a\u000205J\u001e\u0010>\u001a\u00020*2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020@002\b\b\u0002\u0010A\u001a\u00020\u0006J\b\u0010B\u001a\u00020*H\u0002J1\u0010C\u001a\u00020*2\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\u0017\u0010D\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020*0E¢\u0006\u0002\bFH\bø\u0001\u0000J\u000e\u0010G\u001a\u00020*2\u0006\u0010H\u001a\u000205J\u001a\u0010I\u001a\u00020*2\b\u0010J\u001a\u0004\u0018\u00010;2\u0006\u00106\u001a\u00020\u0012H\u0002J$\u0010K\u001a\u00020*2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020N0M2\u000e\b\u0002\u0010O\u001a\b\u0012\u0004\u0012\u00020N0MJ\u0018\u0010P\u001a\u00020\u00122\u0006\u0010Q\u001a\u00020\u00062\u0006\u0010R\u001a\u00020\u0006H\u0002J\u0014\u0010S\u001a\u00020*2\f\u0010T\u001a\b\u0012\u0004\u0012\u00020U00J\u0014\u0010V\u001a\u00020*2\f\u0010T\u001a\b\u0012\u0004\u0012\u00020U00J\u0010\u0010W\u001a\u00020*2\u0006\u0010X\u001a\u00020\rH\u0002J\u0010\u0010Y\u001a\u00020*2\u0006\u0010Z\u001a\u00020\u000bH\u0002J\u0010\u0010\u0005\u001a\u00020\u00002\b\b\u0002\u0010[\u001a\u00020\u000fJ\u0010\u0010\\\u001a\u00020\u00122\u0006\u0010]\u001a\u00020\u0006H\u0002J\u000e\u0010^\u001a\u00020\u00062\u0006\u0010Z\u001a\u00020\u000bJ\u000e\u0010^\u001a\u00020\u00062\u0006\u0010X\u001a\u00020\rJ\u0006\u0010_\u001a\u00020\u0000J\u0006\u0010`\u001a\u00020\u0000J\u000e\u0010a\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0006J\u000e\u0010b\u001a\u00020\u00002\u0006\u0010c\u001a\u00020(J\u0012\u0010d\u001a\u0004\u0018\u00010\u000b2\u0006\u0010]\u001a\u00020\u0006H\u0002J$\u0010e\u001a\u00020\u00122\f\u0010L\u001a\b\u0012\u0004\u0012\u00020N0M2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020N0MH\u0002J\u0018\u0010f\u001a\u00020\u000b2\u0006\u0010g\u001a\u00020\u000f2\u0006\u0010]\u001a\u00020\u0006H\u0002J\u0012\u0010h\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r0\bJ\u0012\u0010i\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b0\bJ\u0010\u0010j\u001a\u00020\u00002\b\b\u0002\u0010[\u001a\u00020\u000fR\u000e\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r0\u0014X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b0\u0014X\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u001cX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u001dX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00060\u001cX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u000e\u0010%\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'X\u0004¢\u0006\u0002\n\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006k"}, d2 = {"Lcom/squareup/kotlinpoet/CodeWriter;", "Ljava/io/Closeable;", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "indent", "", "memberImports", "", "Lcom/squareup/kotlinpoet/Import;", "importedTypes", "Lcom/squareup/kotlinpoet/ClassName;", "importedMembers", "Lcom/squareup/kotlinpoet/MemberName;", "columnLimit", "", "(Ljava/lang/Appendable;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;I)V", "comment", "", "importableMembers", "", "importableTypes", "getImportedMembers", "()Ljava/util/Map;", "getImportedTypes", "indentLevel", "kdoc", "memberImportNames", "", "Lcom/squareup/kotlinpoet/LineWrapper;", "packageName", "referencedNames", "statementLine", "getStatementLine", "()I", "setStatementLine", "(I)V", "trailingNewline", "typeSpecStack", "", "Lcom/squareup/kotlinpoet/TypeSpec;", "close", "", "emit", "s", "nonWrapping", "emitAnnotations", "annotations", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "inline", "emitCode", "codeBlock", "Lcom/squareup/kotlinpoet/CodeBlock;", "isConstantContext", "ensureTrailingNewline", "format", "args", "", "", "(Ljava/lang/String;[Ljava/lang/Object;)Lcom/squareup/kotlinpoet/CodeWriter;", "emitComment", "emitContextReceivers", "contextReceivers", "Lcom/squareup/kotlinpoet/TypeName;", "suffix", "emitIndentation", "emitInto", "action", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "emitKdoc", "kdocCodeBlock", "emitLiteral", "o", "emitModifiers", "modifiers", "", "Lcom/squareup/kotlinpoet/KModifier;", "implicitModifiers", "emitStaticImportMember", "canonical", "part", "emitTypeVariables", "typeVariables", "Lcom/squareup/kotlinpoet/TypeVariableName;", "emitWhereBlock", "importableMember", "memberName", "importableType", "className", "levels", "isMethodNameUsedInCurrentContext", "simpleName", "lookupName", "popPackage", "popType", "pushPackage", "pushType", "type", "resolve", "shouldEmitPublicModifier", "stackClassName", "stackDepth", "suggestedMemberImports", "suggestedTypeImports", "unindent", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: CodeWriter.kt */
public final class CodeWriter implements Closeable {
    private boolean comment;
    private final Map<String, MemberName> importableMembers;
    private final Map<String, ClassName> importableTypes;
    private final Map<String, MemberName> importedMembers;
    private final Map<String, ClassName> importedTypes;
    private final String indent;
    private int indentLevel;
    private boolean kdoc;
    private final Set<String> memberImportNames;
    private final Map<String, Import> memberImports;
    /* access modifiers changed from: private */
    public LineWrapper out;
    private String packageName;
    private final Set<String> referencedNames;
    private int statementLine;
    private boolean trailingNewline;
    private final List<TypeSpec> typeSpecStack;

    public CodeWriter(Appendable appendable, String str, Map<String, Import> map, Map<String, ClassName> map2, Map<String, MemberName> map3, int i) {
        Intrinsics.checkNotNullParameter(appendable, "out");
        Intrinsics.checkNotNullParameter(str, "indent");
        Intrinsics.checkNotNullParameter(map, "memberImports");
        Intrinsics.checkNotNullParameter(map2, "importedTypes");
        Intrinsics.checkNotNullParameter(map3, "importedMembers");
        this.indent = str;
        this.memberImports = map;
        this.importedTypes = map2;
        this.importedMembers = map3;
        this.out = new LineWrapper(appendable, str, i);
        this.packageName = CodeWriterKt.NO_PACKAGE;
        this.typeSpecStack = new ArrayList();
        this.memberImportNames = new LinkedHashSet();
        this.importableTypes = new LinkedHashMap();
        this.importableMembers = new LinkedHashMap();
        this.referencedNames = new LinkedHashSet();
        this.statementLine = -1;
        for (Map.Entry<String, Import> key : map.entrySet()) {
            String str2 = (String) key.getKey();
            int lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) str2, (char) FilenameUtils.EXTENSION_SEPARATOR, 0, false, 6, (Object) null);
            if (lastIndexOf$default >= 0) {
                Set<String> set = this.memberImportNames;
                String substring = str2.substring(0, lastIndexOf$default);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                set.add(substring);
            }
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CodeWriter(Appendable appendable, String str, Map map, Map map2, Map map3, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(appendable, (i2 & 2) != 0 ? FileSpecKt.DEFAULT_INDENT : str, (i2 & 4) != 0 ? MapsKt.emptyMap() : map, (i2 & 8) != 0 ? MapsKt.emptyMap() : map2, (i2 & 16) != 0 ? MapsKt.emptyMap() : map3, (i2 & 32) != 0 ? 100 : i);
    }

    public final Map<String, ClassName> getImportedTypes() {
        return this.importedTypes;
    }

    public final Map<String, MemberName> getImportedMembers() {
        return this.importedMembers;
    }

    public final int getStatementLine() {
        return this.statementLine;
    }

    public final void setStatementLine(int i) {
        this.statementLine = i;
    }

    public static /* synthetic */ CodeWriter indent$default(CodeWriter codeWriter, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 1;
        }
        return codeWriter.indent(i);
    }

    public final CodeWriter indent(int i) {
        CodeWriter codeWriter = this;
        this.indentLevel += i;
        return this;
    }

    public static /* synthetic */ CodeWriter unindent$default(CodeWriter codeWriter, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 1;
        }
        return codeWriter.unindent(i);
    }

    public final CodeWriter unindent(int i) {
        CodeWriter codeWriter = this;
        int i2 = this.indentLevel;
        if (i2 - i >= 0) {
            this.indentLevel = i2 - i;
            return this;
        }
        throw new IllegalArgumentException(("cannot unindent " + i + " from " + this.indentLevel).toString());
    }

    public final CodeWriter pushPackage(String str) {
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        CodeWriter codeWriter = this;
        if (this.packageName == CodeWriterKt.NO_PACKAGE) {
            this.packageName = str;
            return this;
        }
        throw new IllegalStateException(("package already set: " + this.packageName).toString());
    }

    public final CodeWriter popPackage() {
        CodeWriter codeWriter = this;
        if (this.packageName != CodeWriterKt.NO_PACKAGE) {
            this.packageName = CodeWriterKt.NO_PACKAGE;
            return this;
        }
        throw new IllegalStateException(("package already set: " + this.packageName).toString());
    }

    public final CodeWriter pushType(TypeSpec typeSpec) {
        Intrinsics.checkNotNullParameter(typeSpec, "type");
        CodeWriter codeWriter = this;
        this.typeSpecStack.add(typeSpec);
        return this;
    }

    public final CodeWriter popType() {
        CodeWriter codeWriter = this;
        List<TypeSpec> list = this.typeSpecStack;
        list.remove(list.size() - 1);
        return this;
    }

    public final void emitComment(CodeBlock codeBlock) {
        Intrinsics.checkNotNullParameter(codeBlock, "codeBlock");
        this.trailingNewline = true;
        this.comment = true;
        try {
            emitCode$default(this, codeBlock, false, false, 6, (Object) null);
            emit$default(this, "\n", false, 2, (Object) null);
        } finally {
            this.comment = false;
        }
    }

    /* JADX INFO: finally extract failed */
    public final void emitKdoc(CodeBlock codeBlock) {
        Intrinsics.checkNotNullParameter(codeBlock, "kdocCodeBlock");
        if (!codeBlock.isEmpty()) {
            emit$default(this, "/**\n", false, 2, (Object) null);
            this.kdoc = true;
            try {
                emitCode$default(this, codeBlock, false, true, 2, (Object) null);
                this.kdoc = false;
                emit$default(this, " */\n", false, 2, (Object) null);
            } catch (Throwable th) {
                this.kdoc = false;
                throw th;
            }
        }
    }

    public final void emitAnnotations(List<AnnotationSpec> list, boolean z) {
        Intrinsics.checkNotNullParameter(list, "annotations");
        for (AnnotationSpec emit$kotlinpoet$default : list) {
            AnnotationSpec.emit$kotlinpoet$default(emit$kotlinpoet$default, this, z, false, 4, (Object) null);
            emit$default(this, z ? " " : "\n", false, 2, (Object) null);
        }
    }

    public static /* synthetic */ void emitModifiers$default(CodeWriter codeWriter, Set set, Set set2, int i, Object obj) {
        if ((i & 2) != 0) {
            set2 = SetsKt.emptySet();
        }
        codeWriter.emitModifiers(set, set2);
    }

    public final void emitModifiers(Set<? extends KModifier> set, Set<? extends KModifier> set2) {
        Intrinsics.checkNotNullParameter(set, "modifiers");
        Intrinsics.checkNotNullParameter(set2, "implicitModifiers");
        if (shouldEmitPublicModifier(set, set2)) {
            emit$default(this, KModifier.PUBLIC.getKeyword$kotlinpoet(), false, 2, (Object) null);
            emit$default(this, " ", false, 2, (Object) null);
        }
        Collection collection = set;
        KModifier[] values = KModifier.values();
        Collection linkedHashSet = new LinkedHashSet();
        for (KModifier kModifier : values) {
            if (collection.contains(kModifier)) {
                linkedHashSet.add(kModifier);
            }
        }
        for (KModifier kModifier2 : (Set) linkedHashSet) {
            if (!set2.contains(kModifier2)) {
                emit$default(this, kModifier2.getKeyword$kotlinpoet(), false, 2, (Object) null);
                emit$default(this, " ", false, 2, (Object) null);
            }
        }
    }

    public static /* synthetic */ void emitContextReceivers$default(CodeWriter codeWriter, List list, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        codeWriter.emitContextReceivers(list, str);
    }

    public final void emitContextReceivers(List<? extends TypeName> list, String str) {
        Intrinsics.checkNotNullParameter(list, "contextReceivers");
        Intrinsics.checkNotNullParameter(str, DynamicLink.Builder.KEY_SUFFIX);
        if (!list.isEmpty()) {
            Iterable<TypeName> iterable = list;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (TypeName typeName : iterable) {
                arrayList.add(CodeBlock.Companion.of("%T", typeName));
            }
            emitCode$default(this, CodeBlocks.joinToCode$default((List) arrayList, (CharSequence) null, "context(", ")", 1, (Object) null), false, false, 6, (Object) null);
            emit$default(this, str, false, 2, (Object) null);
        }
    }

    public final void emitTypeVariables(List<TypeVariableName> list) {
        Intrinsics.checkNotNullParameter(list, "typeVariables");
        if (!list.isEmpty()) {
            emit$default(this, "<", false, 2, (Object) null);
            int i = 0;
            for (Object next : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                TypeVariableName typeVariableName = (TypeVariableName) next;
                if (i > 0) {
                    emit$default(this, ", ", false, 2, (Object) null);
                }
                if (typeVariableName.getVariance() != null) {
                    emit$default(this, typeVariableName.getVariance().getKeyword$kotlinpoet() + ' ', false, 2, (Object) null);
                }
                if (typeVariableName.isReified()) {
                    emit$default(this, "reified ", false, 2, (Object) null);
                }
                emitCode("%L", typeVariableName.getName());
                if (typeVariableName.getBounds().size() == 1 && !Intrinsics.areEqual((Object) typeVariableName.getBounds().get(0), (Object) CodeWriterKt.getNULLABLE_ANY())) {
                    emitCode(" : %T", typeVariableName.getBounds().get(0));
                }
                i = i2;
            }
            emit$default(this, ">", false, 2, (Object) null);
        }
    }

    public final void emitWhereBlock(List<TypeVariableName> list) {
        Intrinsics.checkNotNullParameter(list, "typeVariables");
        if (!list.isEmpty()) {
            boolean z = true;
            for (TypeVariableName next : list) {
                if (next.getBounds().size() > 1) {
                    for (TypeName next2 : next.getBounds()) {
                        emitCode(!z ? ", " : " where ");
                        emitCode("%L : %T", next.getName(), next2);
                        z = false;
                    }
                }
            }
        }
    }

    public final CodeWriter emitCode(String str) {
        Intrinsics.checkNotNullParameter(str, CmcdData.Factory.STREAMING_FORMAT_SS);
        return emitCode$default(this, CodeBlock.Companion.of(str, new Object[0]), false, false, 6, (Object) null);
    }

    public final CodeWriter emitCode(String str, Object... objArr) {
        Intrinsics.checkNotNullParameter(str, "format");
        Intrinsics.checkNotNullParameter(objArr, "args");
        return emitCode$default(this, CodeBlock.Companion.of(str, Arrays.copyOf(objArr, objArr.length)), false, false, 6, (Object) null);
    }

    public static /* synthetic */ CodeWriter emitCode$default(CodeWriter codeWriter, CodeBlock codeBlock, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return codeWriter.emitCode(codeBlock, z, z2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x0141  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.squareup.kotlinpoet.CodeWriter emitCode(com.squareup.kotlinpoet.CodeBlock r17, boolean r18, boolean r19) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            java.lang.String r2 = "codeBlock"
            r3 = r17
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r2)
            r2 = r0
            com.squareup.kotlinpoet.CodeWriter r2 = (com.squareup.kotlinpoet.CodeWriter) r2
            java.util.List r2 = r17.getFormatParts$kotlinpoet()
            java.util.ListIterator r2 = r2.listIterator()
            r4 = 0
            r5 = 0
            r6 = r4
            r7 = r5
        L_0x001a:
            boolean r8 = r2.hasNext()
            r9 = 2
            if (r8 == 0) goto L_0x02b7
            java.lang.Object r8 = r2.next()
            java.lang.String r8 = (java.lang.String) r8
            int r10 = r8.hashCode()
            r11 = 171(0xab, float:2.4E-43)
            java.lang.String r12 = "\n            |\n            "
            java.lang.String r13 = "\n            |- Arguments: "
            r15 = -1
            r14 = 1
            if (r10 == r11) goto L_0x021f
            r11 = 187(0xbb, float:2.62E-43)
            if (r10 == r11) goto L_0x01a9
            r11 = 1184(0x4a0, float:1.659E-42)
            java.lang.String r12 = "%"
            if (r10 == r11) goto L_0x019a
            r11 = 1227(0x4cb, float:1.72E-42)
            java.lang.String r13 = "null"
            if (r10 == r11) goto L_0x016a
            r11 = 1230(0x4ce, float:1.724E-42)
            if (r10 == r11) goto L_0x0149
            r11 = 1231(0x4cf, float:1.725E-42)
            if (r10 == r11) goto L_0x00d0
            r11 = 8676(0x21e4, float:1.2158E-41)
            if (r10 == r11) goto L_0x00c1
            r11 = 8677(0x21e5, float:1.2159E-41)
            if (r10 == r11) goto L_0x00b2
            switch(r10) {
                case 1223: goto L_0x0098;
                case 1224: goto L_0x0079;
                case 1225: goto L_0x005a;
                default: goto L_0x0058;
            }
        L_0x0058:
            goto L_0x0227
        L_0x005a:
            java.lang.String r10 = "%N"
            boolean r10 = r8.equals(r10)
            if (r10 != 0) goto L_0x0064
            goto L_0x0227
        L_0x0064:
            java.util.List r8 = r17.getArgs$kotlinpoet()
            int r10 = r6 + 1
            java.lang.Object r6 = r8.get(r6)
            java.lang.String r8 = "null cannot be cast to non-null type kotlin.String"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6, r8)
            java.lang.String r6 = (java.lang.String) r6
            emit$default(r0, r6, r4, r9, r5)
            goto L_0x00af
        L_0x0079:
            java.lang.String r10 = "%M"
            boolean r10 = r8.equals(r10)
            if (r10 != 0) goto L_0x0083
            goto L_0x0227
        L_0x0083:
            java.util.List r8 = r17.getArgs$kotlinpoet()
            int r10 = r6 + 1
            java.lang.Object r6 = r8.get(r6)
            java.lang.String r8 = "null cannot be cast to non-null type com.squareup.kotlinpoet.MemberName"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6, r8)
            com.squareup.kotlinpoet.MemberName r6 = (com.squareup.kotlinpoet.MemberName) r6
            r6.emit$kotlinpoet(r0)
            goto L_0x00af
        L_0x0098:
            java.lang.String r10 = "%L"
            boolean r10 = r8.equals(r10)
            if (r10 != 0) goto L_0x00a2
            goto L_0x0227
        L_0x00a2:
            java.util.List r8 = r17.getArgs$kotlinpoet()
            int r10 = r6 + 1
            java.lang.Object r6 = r8.get(r6)
            r0.emitLiteral(r6, r1)
        L_0x00af:
            r6 = r10
            goto L_0x001a
        L_0x00b2:
            java.lang.String r10 = "⇥"
            boolean r10 = r8.equals(r10)
            if (r10 != 0) goto L_0x00bc
            goto L_0x0227
        L_0x00bc:
            indent$default(r0, r4, r14, r5)
            goto L_0x001a
        L_0x00c1:
            java.lang.String r10 = "⇤"
            boolean r10 = r8.equals(r10)
            if (r10 != 0) goto L_0x00cb
            goto L_0x0227
        L_0x00cb:
            unindent$default(r0, r4, r14, r5)
            goto L_0x001a
        L_0x00d0:
            java.lang.String r10 = "%T"
            boolean r10 = r8.equals(r10)
            if (r10 != 0) goto L_0x00da
            goto L_0x0227
        L_0x00da:
            java.util.List r8 = r17.getArgs$kotlinpoet()
            int r10 = r6 + 1
            java.lang.Object r6 = r8.get(r6)
            java.lang.String r8 = "null cannot be cast to non-null type com.squareup.kotlinpoet.TypeName"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6, r8)
            com.squareup.kotlinpoet.TypeName r6 = (com.squareup.kotlinpoet.TypeName) r6
            boolean r8 = r6.isAnnotated()
            if (r8 == 0) goto L_0x00fc
            r6.emitAnnotations$kotlinpoet(r0)
            java.util.List r8 = kotlin.collections.CollectionsKt.emptyList()
            com.squareup.kotlinpoet.TypeName r6 = com.squareup.kotlinpoet.TypeName.copy$default(r6, r4, r8, r14, r5)
        L_0x00fc:
            boolean r8 = r6 instanceof com.squareup.kotlinpoet.ClassName
            if (r8 == 0) goto L_0x013e
            boolean r8 = r2.hasNext()
            if (r8 == 0) goto L_0x013e
            java.util.List r8 = r17.getFormatParts$kotlinpoet()
            int r11 = r2.nextIndex()
            java.lang.Object r8 = r8.get(r11)
            java.lang.String r8 = (java.lang.String) r8
            boolean r8 = kotlin.text.StringsKt.startsWith$default(r8, r12, r4, r9, r5)
            if (r8 != 0) goto L_0x013e
            java.util.Set<java.lang.String> r8 = r0.memberImportNames
            r9 = r6
            com.squareup.kotlinpoet.ClassName r9 = (com.squareup.kotlinpoet.ClassName) r9
            java.lang.String r11 = r9.getCanonicalName()
            boolean r8 = r8.contains(r11)
            if (r8 == 0) goto L_0x013e
            if (r7 != 0) goto L_0x012d
            r7 = r14
            goto L_0x012e
        L_0x012d:
            r7 = r4
        L_0x012e:
            if (r7 == 0) goto L_0x0132
            r7 = r9
            goto L_0x013f
        L_0x0132:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "pending type for static import?!"
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x013e:
            r14 = r4
        L_0x013f:
            if (r14 != 0) goto L_0x0144
            r6.emit$kotlinpoet(r0)
        L_0x0144:
            r6.emitNullable$kotlinpoet(r0)
            goto L_0x00af
        L_0x0149:
            java.lang.String r10 = "%S"
            boolean r10 = r8.equals(r10)
            if (r10 != 0) goto L_0x0153
            goto L_0x0227
        L_0x0153:
            java.util.List r8 = r17.getArgs$kotlinpoet()
            int r10 = r6 + 1
            java.lang.Object r6 = r8.get(r6)
            java.lang.String r6 = (java.lang.String) r6
            if (r6 == 0) goto L_0x0165
            java.lang.String r13 = com.squareup.kotlinpoet.UtilKt.stringLiteralWithQuotes(r6, r14, r1)
        L_0x0165:
            r0.emit(r13, r14)
            goto L_0x00af
        L_0x016a:
            java.lang.String r10 = "%P"
            boolean r10 = r8.equals(r10)
            if (r10 != 0) goto L_0x0174
            goto L_0x0227
        L_0x0174:
            java.util.List r8 = r17.getArgs$kotlinpoet()
            int r10 = r6 + 1
            java.lang.Object r6 = r8.get(r6)
            if (r6 == 0) goto L_0x018e
            boolean r8 = r6 instanceof com.squareup.kotlinpoet.CodeBlock
            if (r8 == 0) goto L_0x018b
            com.squareup.kotlinpoet.CodeBlock r6 = (com.squareup.kotlinpoet.CodeBlock) r6
            java.lang.String r6 = r6.toString$kotlinpoet(r0)
            goto L_0x018f
        L_0x018b:
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x018f
        L_0x018e:
            r6 = r5
        L_0x018f:
            if (r6 == 0) goto L_0x0195
            java.lang.String r13 = com.squareup.kotlinpoet.UtilKt.stringLiteralWithQuotes(r6, r4, r1)
        L_0x0195:
            r0.emit(r13, r14)
            goto L_0x00af
        L_0x019a:
            java.lang.String r10 = "%%"
            boolean r10 = r8.equals(r10)
            if (r10 != 0) goto L_0x01a4
            goto L_0x0227
        L_0x01a4:
            emit$default(r0, r12, r4, r9, r5)
            goto L_0x001a
        L_0x01a9:
            java.lang.String r10 = "»"
            boolean r10 = r8.equals(r10)
            if (r10 != 0) goto L_0x01b3
            goto L_0x0227
        L_0x01b3:
            int r8 = r0.statementLine
            if (r8 == r15) goto L_0x01b9
            r10 = r14
            goto L_0x01ba
        L_0x01b9:
            r10 = r4
        L_0x01ba:
            if (r10 != 0) goto L_0x0216
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "\n            |Can't close a statement that hasn't been opened (closing » is not preceded by an\n            |opening «).\n            |Current code block:\n            |- Format parts: "
            r1.<init>(r2)
            java.util.List r2 = r17.getFormatParts$kotlinpoet()
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.ArrayList r4 = new java.util.ArrayList
            r6 = 10
            int r6 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r2, r6)
            r4.<init>(r6)
            java.util.Collection r4 = (java.util.Collection) r4
            java.util.Iterator r2 = r2.iterator()
        L_0x01da:
            boolean r6 = r2.hasNext()
            if (r6 == 0) goto L_0x01ee
            java.lang.Object r6 = r2.next()
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r6 = com.squareup.kotlinpoet.UtilKt.escapeCharacterLiterals(r6)
            r4.add(r6)
            goto L_0x01da
        L_0x01ee:
            java.util.List r4 = (java.util.List) r4
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.StringBuilder r1 = r1.append(r13)
            java.util.List r2 = r17.getArgs$kotlinpoet()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r12)
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = kotlin.text.StringsKt.trimMargin$default(r1, r5, r14, r5)
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r1 = r1.toString()
            r2.<init>(r1)
            throw r2
        L_0x0216:
            if (r8 <= 0) goto L_0x021b
            r0.unindent(r9)
        L_0x021b:
            r0.statementLine = r15
            goto L_0x001a
        L_0x021f:
            java.lang.String r10 = "«"
            boolean r10 = r8.equals(r10)
            if (r10 != 0) goto L_0x0250
        L_0x0227:
            if (r7 == 0) goto L_0x0248
            java.lang.String r10 = "."
            boolean r10 = kotlin.text.StringsKt.startsWith$default(r8, r10, r4, r9, r5)
            if (r10 == 0) goto L_0x023d
            java.lang.String r10 = r7.getCanonicalName()
            boolean r10 = r0.emitStaticImportMember(r10, r8)
            if (r10 == 0) goto L_0x023d
            r7 = r5
            goto L_0x023e
        L_0x023d:
            r14 = r4
        L_0x023e:
            if (r14 != 0) goto L_0x0249
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            r7.emit$kotlinpoet(r0)
            r7 = r5
            goto L_0x0249
        L_0x0248:
            r14 = r4
        L_0x0249:
            if (r14 != 0) goto L_0x001a
            emit$default(r0, r8, r4, r9, r5)
            goto L_0x001a
        L_0x0250:
            int r8 = r0.statementLine
            if (r8 != r15) goto L_0x0256
            r8 = r14
            goto L_0x0257
        L_0x0256:
            r8 = r4
        L_0x0257:
            if (r8 != 0) goto L_0x02b3
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "\n            |Can't open a new statement until the current statement is closed (opening « followed\n            |by another « without a closing »).\n            |Current code block:\n            |- Format parts: "
            r1.<init>(r2)
            java.util.List r2 = r17.getFormatParts$kotlinpoet()
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.ArrayList r4 = new java.util.ArrayList
            r6 = 10
            int r6 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r2, r6)
            r4.<init>(r6)
            java.util.Collection r4 = (java.util.Collection) r4
            java.util.Iterator r2 = r2.iterator()
        L_0x0277:
            boolean r6 = r2.hasNext()
            if (r6 == 0) goto L_0x028b
            java.lang.Object r6 = r2.next()
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r6 = com.squareup.kotlinpoet.UtilKt.escapeCharacterLiterals(r6)
            r4.add(r6)
            goto L_0x0277
        L_0x028b:
            java.util.List r4 = (java.util.List) r4
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.StringBuilder r1 = r1.append(r13)
            java.util.List r2 = r17.getArgs$kotlinpoet()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r12)
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = kotlin.text.StringsKt.trimMargin$default(r1, r5, r14, r5)
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r1 = r1.toString()
            r2.<init>(r1)
            throw r2
        L_0x02b3:
            r0.statementLine = r4
            goto L_0x001a
        L_0x02b7:
            if (r19 == 0) goto L_0x02c6
            com.squareup.kotlinpoet.LineWrapper r1 = r0.out
            boolean r1 = r1.getHasPendingSegments()
            if (r1 == 0) goto L_0x02c6
            java.lang.String r1 = "\n"
            emit$default(r0, r1, r4, r9, r5)
        L_0x02c6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.CodeWriter.emitCode(com.squareup.kotlinpoet.CodeBlock, boolean, boolean):com.squareup.kotlinpoet.CodeWriter");
    }

    private final boolean emitStaticImportMember(String str, String str2) {
        Import importR;
        String substring = str2.substring(1);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
        if ((substring.length() == 0) || !Character.isJavaIdentifierStart(substring.charAt(0)) || (importR = this.memberImports.get(str + FilenameUtils.EXTENSION_SEPARATOR + CodeWriterKt.extractMemberName(substring))) == null) {
            return false;
        }
        if (importR.getAlias() != null) {
            emit$default(this, StringsKt.replaceFirst$default(substring, CodeWriterKt.extractMemberName(substring), importR.getAlias(), false, 4, (Object) null), false, 2, (Object) null);
        } else {
            emit$default(this, substring, false, 2, (Object) null);
        }
        return true;
    }

    private final void emitLiteral(Object obj, boolean z) {
        if (obj instanceof TypeSpec) {
            TypeSpec.emit$kotlinpoet$default((TypeSpec) obj, this, (String) null, (Set) null, false, 12, (Object) null);
        } else if (obj instanceof AnnotationSpec) {
            ((AnnotationSpec) obj).emit$kotlinpoet(this, true, z);
        } else if (obj instanceof PropertySpec) {
            PropertySpec.emit$kotlinpoet$default((PropertySpec) obj, this, SetsKt.emptySet(), false, false, false, false, 60, (Object) null);
        } else if (obj instanceof FunSpec) {
            ((FunSpec) obj).emit$kotlinpoet(this, (String) null, SetsKt.setOf(KModifier.PUBLIC), true);
        } else if (obj instanceof TypeAliasSpec) {
            ((TypeAliasSpec) obj).emit$kotlinpoet(this);
        } else if (obj instanceof CodeBlock) {
            emitCode$default(this, (CodeBlock) obj, z, false, 4, (Object) null);
        } else {
            emit$default(this, String.valueOf(obj), false, 2, (Object) null);
        }
    }

    public final String lookupName(ClassName className) {
        Intrinsics.checkNotNullParameter(className, "className");
        ClassName className2 = className;
        boolean z = false;
        while (className2 != null) {
            Import importR = this.memberImports.get(className2.getCanonicalName());
            String alias = importR != null ? importR.getAlias() : null;
            ClassName resolve = resolve(alias == null ? className2.getSimpleName() : alias);
            boolean z2 = resolve != null;
            if (!Intrinsics.areEqual((Object) resolve, (Object) className2.copy(false, CollectionsKt.emptyList()))) {
                className2 = className2.enclosingClassName();
                z = z2;
            } else if (alias != null) {
                return alias;
            } else {
                this.referencedNames.add(className.topLevelClassName().getSimpleName());
                return CollectionsKt.joinToString$default(className.getSimpleNames().subList(className2.getSimpleNames().size() - 1, className.getSimpleNames().size()), ".", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
            }
        }
        if (z) {
            return className.getCanonicalName();
        }
        if (Intrinsics.areEqual((Object) this.packageName, (Object) className.getPackageName())) {
            this.referencedNames.add(className.topLevelClassName().getSimpleName());
            return CollectionsKt.joinToString$default(className.getSimpleNames(), ".", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        }
        if (!this.kdoc) {
            importableType(className);
        }
        return className.getCanonicalName();
    }

    public final String lookupName(MemberName memberName) {
        String str;
        Intrinsics.checkNotNullParameter(memberName, "memberName");
        Import importR = this.memberImports.get(memberName.getCanonicalName());
        if (importR == null || (str = importR.getAlias()) == null) {
            str = memberName.getSimpleName();
        }
        MemberName memberName2 = this.importedMembers.get(str);
        if (Intrinsics.areEqual((Object) memberName2, (Object) memberName)) {
            return str;
        }
        if (memberName2 != null && memberName.getEnclosingClassName() != null) {
            return lookupName(memberName.getEnclosingClassName()) + FilenameUtils.EXTENSION_SEPARATOR + str;
        } else if (!Intrinsics.areEqual((Object) this.packageName, (Object) memberName.getPackageName()) || memberName.getEnclosingClassName() != null) {
            if (!this.kdoc && (memberName.isExtension() || !isMethodNameUsedInCurrentContext(memberName.getSimpleName()))) {
                importableMember(memberName);
            }
            return memberName.getCanonicalName();
        } else {
            this.referencedNames.add(memberName.getSimpleName());
            return memberName.getSimpleName();
        }
    }

    private final boolean isMethodNameUsedInCurrentContext(String str) {
        boolean z;
        for (TypeSpec typeSpec : CollectionsKt.reversed(this.typeSpecStack)) {
            Iterable funSpecs = typeSpec.getFunSpecs();
            if (!(funSpecs instanceof Collection) || !((Collection) funSpecs).isEmpty()) {
                Iterator it = funSpecs.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (Intrinsics.areEqual((Object) ((FunSpec) it.next()).getName(), (Object) str)) {
                            z = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            z = false;
            if (!z) {
                if (!typeSpec.getModifiers().contains(KModifier.INNER)) {
                    break;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    private final void importableType(ClassName className) {
        String str;
        ClassName className2 = className.topLevelClassName();
        Import importR = this.memberImports.get(className.getCanonicalName());
        if (importR == null || (str = importR.getAlias()) == null) {
            str = className2.getSimpleName();
        }
        if (!this.importableMembers.containsKey(str)) {
            this.importableTypes.putIfAbsent(str, className2);
        }
    }

    private final void importableMember(MemberName memberName) {
        String str;
        if (memberName.getPackageName().length() > 0) {
            Import importR = this.memberImports.get(memberName.getCanonicalName());
            if (importR == null || (str = importR.getAlias()) == null) {
                str = memberName.getSimpleName();
            }
            if (!this.importableTypes.containsKey(str) && this.importableMembers.putIfAbsent(str, memberName) != null && memberName.getEnclosingClassName() != null) {
                importableType(memberName.getEnclosingClassName());
            }
        }
    }

    private final ClassName resolve(String str) {
        int size = this.typeSpecStack.size() - 1;
        if (size >= 0) {
            while (true) {
                int i = size - 1;
                if (this.typeSpecStack.get(size).getNestedTypesSimpleNames$kotlinpoet().contains(str)) {
                    return stackClassName(size, str);
                }
                if (i < 0) {
                    break;
                }
                size = i;
            }
        }
        if (this.typeSpecStack.size() > 0) {
            TypeSpec typeSpec = this.typeSpecStack.get(0);
            if (Intrinsics.areEqual((Object) typeSpec.getName(), (Object) str)) {
                return new ClassName(this.packageName, str);
            }
            if (typeSpec.isEnum() && typeSpec.getEnumConstants().keySet().contains(str)) {
                String str2 = this.packageName;
                String name = typeSpec.getName();
                Intrinsics.checkNotNull(name);
                return new ClassName(str2, name).nestedClass(str);
            }
        }
        ClassName className = this.importedTypes.get(str);
        if (className != null) {
            return className;
        }
        return null;
    }

    private final ClassName stackClassName(int i, String str) {
        String str2 = this.packageName;
        String name = this.typeSpecStack.get(0).getName();
        Intrinsics.checkNotNull(name);
        ClassName className = new ClassName(str2, name);
        int i2 = 1;
        if (1 <= i) {
            while (true) {
                String name2 = this.typeSpecStack.get(i2).getName();
                Intrinsics.checkNotNull(name2);
                className = className.nestedClass(name2);
                if (i2 == i) {
                    break;
                }
                i2++;
            }
        }
        return className.nestedClass(str);
    }

    public static /* synthetic */ CodeWriter emit$default(CodeWriter codeWriter, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return codeWriter.emit(str, z);
    }

    public final CodeWriter emit(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, CmcdData.Factory.STREAMING_FORMAT_SS);
        CodeWriter codeWriter = this;
        boolean z2 = true;
        for (String str2 : StringsKt.split$default((CharSequence) str, new char[]{10}, false, 0, 6, (Object) null)) {
            if (!z2) {
                if ((this.kdoc || this.comment) && this.trailingNewline) {
                    emitIndentation();
                    this.out.appendNonWrapping(this.kdoc ? " *" : "//");
                }
                this.out.newline();
                this.trailingNewline = true;
                int i = this.statementLine;
                if (i != -1) {
                    if (i == 0) {
                        indent(2);
                    }
                    this.statementLine++;
                }
            }
            if (!(str2.length() == 0)) {
                String str3 = " * ";
                if (this.trailingNewline) {
                    emitIndentation();
                    if (this.kdoc) {
                        this.out.appendNonWrapping(str3);
                    } else if (this.comment) {
                        this.out.appendNonWrapping("// ");
                    }
                }
                if (z) {
                    this.out.appendNonWrapping(str2);
                } else {
                    LineWrapper lineWrapper = this.out;
                    boolean z3 = this.kdoc;
                    int i2 = z3 ? this.indentLevel : 2 + this.indentLevel;
                    if (!z3) {
                        str3 = "";
                    }
                    lineWrapper.append(str2, i2, str3);
                }
                this.trailingNewline = false;
            }
            z2 = false;
        }
        return this;
    }

    private final void emitIndentation() {
        int i = this.indentLevel;
        for (int i2 = 0; i2 < i; i2++) {
            this.out.appendNonWrapping(this.indent);
        }
    }

    private final boolean shouldEmitPublicModifier(Set<? extends KModifier> set, Set<? extends KModifier> set2) {
        if (set.contains(KModifier.PUBLIC)) {
            return true;
        }
        if (!set2.contains(KModifier.PUBLIC)) {
            return false;
        }
        return !UtilKt.containsAnyOf(set, KModifier.PRIVATE, KModifier.INTERNAL, KModifier.PROTECTED);
    }

    public final Map<String, ClassName> suggestedTypeImports() {
        Map<String, ClassName> map = this.importableTypes;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry next : map.entrySet()) {
            if (!this.referencedNames.contains((String) next.getKey())) {
                linkedHashMap.put(next.getKey(), next.getValue());
            }
        }
        return linkedHashMap;
    }

    public final Map<String, MemberName> suggestedMemberImports() {
        Map<String, MemberName> map = this.importableMembers;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry next : map.entrySet()) {
            if (!this.referencedNames.contains((String) next.getKey())) {
                linkedHashMap.put(next.getKey(), next.getValue());
            }
        }
        return linkedHashMap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0040, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0036, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0037, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        kotlin.io.CloseableKt.closeFinally(r0, r5);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void emitInto(java.lang.Appendable r4, kotlin.jvm.functions.Function1<? super com.squareup.kotlinpoet.CodeWriter, kotlin.Unit> r5) {
        /*
            r3 = this;
            java.lang.String r0 = "out"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "action"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            com.squareup.kotlinpoet.LineWrapper r0 = new com.squareup.kotlinpoet.LineWrapper
            java.lang.String r1 = "  "
            r2 = 2147483647(0x7fffffff, float:NaN)
            r0.<init>(r4, r1, r2)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r4 = 1
            r1 = r0
            com.squareup.kotlinpoet.LineWrapper r1 = (com.squareup.kotlinpoet.LineWrapper) r1     // Catch:{ all -> 0x0034 }
            com.squareup.kotlinpoet.LineWrapper r2 = r3.out     // Catch:{ all -> 0x0034 }
            r3.out = r1     // Catch:{ all -> 0x0034 }
            r5.invoke(r3)     // Catch:{ all -> 0x0034 }
            r3.out = r2     // Catch:{ all -> 0x0034 }
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0034 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            r5 = 0
            kotlin.io.CloseableKt.closeFinally(r0, r5)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            return
        L_0x0034:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0036 }
        L_0x0036:
            r1 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            kotlin.io.CloseableKt.closeFinally(r0, r5)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.CodeWriter.emitInto(java.lang.Appendable, kotlin.jvm.functions.Function1):void");
    }

    public void close() {
        this.out.close();
    }
}
