package kotlin.text;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0002\u0010\r\n\u0000\u001a>\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u001b\u0010\u0004\u001a\u0017\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\b\tH\bø\u0001\u0000\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001a6\u0010\u0000\u001a\u00020\u00012\u001b\u0010\u0004\u001a\u0017\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\b\tH\bø\u0001\u0000\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a\u001f\u0010\n\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\b\u001a/\u0010\n\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0016\u0010\r\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\f0\u000e\"\u0004\u0018\u00010\f¢\u0006\u0002\u0010\u000f\u001a/\u0010\n\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0016\u0010\r\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000e\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0010\u001a-\u0010\n\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0003H\b\u001a\u0015\u0010\u0015\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u0007H\b\u001a\u001f\u0010\u0015\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\r\u001a\u0004\u0018\u00010\fH\b\u001a\u001d\u0010\u0015\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\r\u001a\u00020\u0016H\b\u001a\u001d\u0010\u0015\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\r\u001a\u00020\u0017H\b\u001a\u001d\u0010\u0015\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\r\u001a\u00020\u0012H\b\u001a\u001f\u0010\u0015\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u0018H\b\u001a\u001f\u0010\u0015\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\b\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0019"}, d2 = {"buildString", "", "capacity", "", "builderAction", "Lkotlin/Function1;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "", "Lkotlin/ExtensionFunctionType;", "append", "obj", "", "value", "", "(Ljava/lang/StringBuilder;[Ljava/lang/Object;)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;[Ljava/lang/String;)Ljava/lang/StringBuilder;", "str", "", "offset", "len", "appendLine", "", "", "", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/text/StringsKt")
/* compiled from: StringBuilder.kt */
class StringsKt__StringBuilderKt extends StringsKt__StringBuilderJVMKt {
    @Deprecated(level = DeprecationLevel.WARNING, message = "Use append(value: Any?) instead", replaceWith = @ReplaceWith(expression = "append(value = obj)", imports = {}))
    private static final StringBuilder append(StringBuilder sb, Object obj) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        StringBuilder append = sb.append(obj);
        Intrinsics.checkNotNullExpressionValue(append, "append(...)");
        return append;
    }

    private static final String buildString(Function1<? super StringBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "builderAction");
        StringBuilder sb = new StringBuilder();
        function1.invoke(sb);
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    private static final String buildString(int i, Function1<? super StringBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "builderAction");
        StringBuilder sb = new StringBuilder(i);
        function1.invoke(sb);
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    public static final StringBuilder append(StringBuilder sb, String... strArr) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(strArr, "value");
        for (String append : strArr) {
            sb.append(append);
        }
        return sb;
    }

    public static final StringBuilder append(StringBuilder sb, Object... objArr) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(objArr, "value");
        for (Object append : objArr) {
            sb.append(append);
        }
        return sb;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use appendRange instead.", replaceWith = @ReplaceWith(expression = "this.appendRange(str, offset, offset + len)", imports = {}))
    private static final StringBuilder append(StringBuilder sb, char[] cArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(cArr, "str");
        throw new NotImplementedError((String) null, 1, (DefaultConstructorMarker) null);
    }

    private static final StringBuilder appendLine(StringBuilder sb) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        StringBuilder append = sb.append(10);
        Intrinsics.checkNotNullExpressionValue(append, "append(...)");
        return append;
    }

    private static final StringBuilder appendLine(StringBuilder sb, CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        StringBuilder append = sb.append(charSequence);
        Intrinsics.checkNotNullExpressionValue(append, "append(...)");
        StringBuilder append2 = append.append(10);
        Intrinsics.checkNotNullExpressionValue(append2, "append(...)");
        return append2;
    }

    private static final StringBuilder appendLine(StringBuilder sb, String str) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        StringBuilder append = sb.append(str);
        Intrinsics.checkNotNullExpressionValue(append, "append(...)");
        StringBuilder append2 = append.append(10);
        Intrinsics.checkNotNullExpressionValue(append2, "append(...)");
        return append2;
    }

    private static final StringBuilder appendLine(StringBuilder sb, Object obj) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        StringBuilder append = sb.append(obj);
        Intrinsics.checkNotNullExpressionValue(append, "append(...)");
        StringBuilder append2 = append.append(10);
        Intrinsics.checkNotNullExpressionValue(append2, "append(...)");
        return append2;
    }

    private static final StringBuilder appendLine(StringBuilder sb, char[] cArr) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(cArr, "value");
        StringBuilder append = sb.append(cArr);
        Intrinsics.checkNotNullExpressionValue(append, "append(...)");
        StringBuilder append2 = append.append(10);
        Intrinsics.checkNotNullExpressionValue(append2, "append(...)");
        return append2;
    }

    private static final StringBuilder appendLine(StringBuilder sb, char c) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        StringBuilder append = sb.append(c);
        Intrinsics.checkNotNullExpressionValue(append, "append(...)");
        StringBuilder append2 = append.append(10);
        Intrinsics.checkNotNullExpressionValue(append2, "append(...)");
        return append2;
    }

    private static final StringBuilder appendLine(StringBuilder sb, boolean z) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        StringBuilder append = sb.append(z);
        Intrinsics.checkNotNullExpressionValue(append, "append(...)");
        StringBuilder append2 = append.append(10);
        Intrinsics.checkNotNullExpressionValue(append2, "append(...)");
        return append2;
    }
}
