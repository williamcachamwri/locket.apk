package com.squareup.kotlinpoet;

import com.google.firebase.dynamiclinks.DynamicLink;
import com.squareup.kotlinpoet.CodeBlock;
import java.util.Arrays;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\u001a%\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000\u001a0\u0010\u0007\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\nH\u0007\u001a)\u0010\r\u001a\u00020\u0004*\u00020\u00042\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\u000e"}, d2 = {"buildCodeBlock", "Lcom/squareup/kotlinpoet/CodeBlock;", "builderAction", "Lkotlin/Function1;", "Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "", "Lkotlin/ExtensionFunctionType;", "joinToCode", "", "separator", "", "prefix", "suffix", "withIndent", "kotlinpoet"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: CodeBlock.kt */
public final class CodeBlocks {
    public static final CodeBlock joinToCode(Collection<CodeBlock> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        return joinToCode$default(collection, (CharSequence) null, (CharSequence) null, (CharSequence) null, 7, (Object) null);
    }

    public static final CodeBlock joinToCode(Collection<CodeBlock> collection, CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.checkNotNullParameter(charSequence, "separator");
        return joinToCode$default(collection, charSequence, (CharSequence) null, (CharSequence) null, 6, (Object) null);
    }

    public static final CodeBlock joinToCode(Collection<CodeBlock> collection, CharSequence charSequence, CharSequence charSequence2) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.checkNotNullParameter(charSequence, "separator");
        Intrinsics.checkNotNullParameter(charSequence2, "prefix");
        return joinToCode$default(collection, charSequence, charSequence2, (CharSequence) null, 4, (Object) null);
    }

    public static /* synthetic */ CodeBlock joinToCode$default(Collection collection, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, Object obj) {
        if ((i & 1) != 0) {
        }
        if ((i & 2) != 0) {
            charSequence2 = "";
        }
        if ((i & 4) != 0) {
            charSequence3 = "";
        }
        return joinToCode(collection, charSequence, charSequence2, charSequence3);
    }

    public static final CodeBlock buildCodeBlock(Function1<? super CodeBlock.Builder, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "builderAction");
        CodeBlock.Builder builder = CodeBlock.Companion.builder();
        function1.invoke(builder);
        return builder.build();
    }

    public static final CodeBlock.Builder withIndent(CodeBlock.Builder builder, Function1<? super CodeBlock.Builder, Unit> function1) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(function1, "builderAction");
        CodeBlock.Builder indent = builder.indent();
        function1.invoke(indent);
        return indent.unindent();
    }

    public static final CodeBlock joinToCode(Collection<CodeBlock> collection, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.checkNotNullParameter(charSequence, "separator");
        Intrinsics.checkNotNullParameter(charSequence2, "prefix");
        Intrinsics.checkNotNullParameter(charSequence3, DynamicLink.Builder.KEY_SUFFIX);
        Object[] array = collection.toArray(new CodeBlock[0]);
        Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        CodeBlock[] codeBlockArr = (CodeBlock[]) array;
        int length = codeBlockArr.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = "%L";
        }
        return CodeBlock.Companion.of(ArraysKt.joinToString$default((Object[]) strArr, charSequence, charSequence2, charSequence3, 0, (CharSequence) null, (Function1) null, 56, (Object) null), Arrays.copyOf(codeBlockArr, codeBlockArr.length));
    }
}
