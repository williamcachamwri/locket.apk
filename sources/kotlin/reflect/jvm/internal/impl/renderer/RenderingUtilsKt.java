package kotlin.reflect.jvm.internal.impl.renderer;

import com.facebook.hermes.intl.Constants;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.text.StringsKt;

/* compiled from: RenderingUtils.kt */
public final class RenderingUtilsKt {
    public static final String render(Name name) {
        Intrinsics.checkNotNullParameter(name, "<this>");
        if (shouldBeEscaped(name)) {
            StringBuilder sb = new StringBuilder();
            String asString = name.asString();
            Intrinsics.checkNotNullExpressionValue(asString, "asString(...)");
            return sb.append("`" + asString).append('`').toString();
        }
        String asString2 = name.asString();
        Intrinsics.checkNotNullExpressionValue(asString2, "asString(...)");
        return asString2;
    }

    private static final boolean shouldBeEscaped(Name name) {
        boolean z;
        String asString = name.asString();
        Intrinsics.checkNotNullExpressionValue(asString, "asString(...)");
        if (KeywordStringsGenerated.KEYWORDS.contains(asString)) {
            return true;
        }
        CharSequence charSequence = asString;
        int i = 0;
        while (true) {
            if (i >= charSequence.length()) {
                z = false;
                break;
            }
            char charAt = charSequence.charAt(i);
            if (!Character.isLetterOrDigit(charAt) && charAt != '_') {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            return true;
        }
        if ((charSequence.length() == 0) || !Character.isJavaIdentifierStart(asString.codePointAt(0))) {
            return true;
        }
        return false;
    }

    public static final String render(FqNameUnsafe fqNameUnsafe) {
        Intrinsics.checkNotNullParameter(fqNameUnsafe, "<this>");
        List<Name> pathSegments = fqNameUnsafe.pathSegments();
        Intrinsics.checkNotNullExpressionValue(pathSegments, "pathSegments(...)");
        return renderFqName(pathSegments);
    }

    public static final String renderFqName(List<Name> list) {
        Intrinsics.checkNotNullParameter(list, "pathSegments");
        StringBuilder sb = new StringBuilder();
        for (Name next : list) {
            if (sb.length() > 0) {
                sb.append(".");
            }
            sb.append(render(next));
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    public static final String replacePrefixesInTypeRepresentations(String str, String str2, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(str, "lowerRendered");
        Intrinsics.checkNotNullParameter(str2, "lowerPrefix");
        Intrinsics.checkNotNullParameter(str3, "upperRendered");
        Intrinsics.checkNotNullParameter(str4, "upperPrefix");
        Intrinsics.checkNotNullParameter(str5, "foldedPrefix");
        if (StringsKt.startsWith$default(str, str2, false, 2, (Object) null) && StringsKt.startsWith$default(str3, str4, false, 2, (Object) null)) {
            String substring = str.substring(str2.length());
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            String substring2 = str3.substring(str4.length());
            Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
            String str6 = str5 + substring;
            if (Intrinsics.areEqual((Object) substring, (Object) substring2)) {
                return str6;
            }
            if (typeStringsDifferOnlyInNullability(substring, substring2)) {
                return str6 + '!';
            }
        }
        return null;
    }

    public static final boolean typeStringsDifferOnlyInNullability(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, Constants.CASEFIRST_LOWER);
        Intrinsics.checkNotNullParameter(str2, Constants.CASEFIRST_UPPER);
        return Intrinsics.areEqual((Object) str, (Object) StringsKt.replace$default(str2, "?", "", false, 4, (Object) null)) || (StringsKt.endsWith$default(str2, "?", false, 2, (Object) null) && Intrinsics.areEqual((Object) new StringBuilder().append(str).append('?').toString(), (Object) str2)) || Intrinsics.areEqual((Object) new StringBuilder("(").append(str).append(")?").toString(), (Object) str2);
    }
}
