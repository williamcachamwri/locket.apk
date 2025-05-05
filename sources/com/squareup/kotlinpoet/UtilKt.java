package com.squareup.kotlinpoet;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.adsbynimbus.render.mraid.HostKt;
import com.facebook.hermes.intl.Constants;
import com.squareup.kotlinpoet.CodeBlock;
import expo.modules.notifications.service.NotificationsService;
import io.sentry.Session;
import io.sentry.protocol.SentryStackFrame;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\n\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\b\u0004\u001a\u0010\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007H\u0000\u001a/\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00052\u0012\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001a0\u001c\"\u00020\u001aH\u0000¢\u0006\u0002\u0010\u001d\u001a/\u0010\u001e\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00052\u0012\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001a0\u001c\"\u00020\u001aH\u0000¢\u0006\u0002\u0010\u001d\u001a$\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u00072\b\b\u0002\u0010\"\u001a\u00020\n2\b\b\u0002\u0010#\u001a\u00020\nH\u0000\u001a\f\u0010$\u001a\u00020\n*\u00020\u0007H\u0002\u001a1\u0010%\u001a\u00020\n\"\u0004\b\u0000\u0010&*\b\u0012\u0004\u0012\u0002H&0'2\u0012\u0010(\u001a\n\u0012\u0006\b\u0001\u0012\u0002H&0\u001c\"\u0002H&H\u0000¢\u0006\u0002\u0010)\u001a\f\u0010*\u001a\u00020+*\u00020+H\u0000\u001a\f\u0010,\u001a\u00020\u0007*\u00020\u0007H\u0002\u001a\f\u0010-\u001a\u00020\u0007*\u00020\u0007H\u0002\u001a\f\u0010.\u001a\u00020\u0007*\u00020\u0007H\u0002\u001a\u0016\u0010/\u001a\u00020\u0007*\u00020\u00072\b\b\u0002\u00100\u001a\u00020\nH\u0000\u001a\f\u00101\u001a\u00020\u0007*\u00020\u0007H\u0002\u001a\u0016\u00102\u001a\u00020\u0007*\u00020\u00072\b\b\u0002\u00103\u001a\u00020\u0001H\u0000\u001a\f\u00104\u001a\u00020\u0018*\u00020\u0007H\u0002\u001aW\u00105\u001a\u00020\n\"\u0004\b\u0000\u0010&*\u0002H&2\u0006\u00106\u001a\u0002H&2\u0006\u00107\u001a\u0002H&2\n\b\u0002\u00108\u001a\u0004\u0018\u0001H&2\n\b\u0002\u00109\u001a\u0004\u0018\u0001H&2\n\b\u0002\u0010:\u001a\u0004\u0018\u0001H&2\n\b\u0002\u0010;\u001a\u0004\u0018\u0001H&H\u0000¢\u0006\u0002\u0010<\u001a+\u0010=\u001a\b\u0012\u0004\u0012\u0002H&0\u0005\"\u0010\b\u0000\u0010&\u0018\u0001*\b\u0012\u0004\u0012\u0002H&0>*\b\u0012\u0004\u0012\u0002H&0'H\b\u001a\u001e\u0010?\u001a\b\u0012\u0004\u0012\u0002H&0@\"\u0004\b\u0000\u0010&*\b\u0012\u0004\u0012\u0002H&0'H\u0000\u001a0\u0010A\u001a\u000e\u0012\u0004\u0012\u0002HC\u0012\u0004\u0012\u0002HD0B\"\u0004\b\u0000\u0010C\"\u0004\b\u0001\u0010D*\u000e\u0012\u0004\u0012\u0002HC\u0012\u0004\u0012\u0002HD0BH\u0000\u001a\u001e\u0010E\u001a\b\u0012\u0004\u0012\u0002H&0\u0005\"\u0004\b\u0000\u0010&*\b\u0012\u0004\u0012\u0002H&0'H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005X\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0018\u0010\t\u001a\u00020\n*\u00020\u00078@X\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u0018\u0010\r\u001a\u00020\n*\u00020\u00078@X\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\f\"\u0018\u0010\u000f\u001a\u00020\n*\u00020\u00078@X\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\f\"\u0018\u0010\u0010\u001a\u00020\n*\u00020\u00018BX\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\"\u0018\u0010\u0012\u001a\u00020\n*\u00020\u00078@X\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\f¨\u0006F"}, d2 = {"ALLOWED_CHARACTER", "", "IDENTIFIER_REGEX", "Lkotlin/text/Regex;", "ILLEGAL_CHARACTERS_TO_ESCAPE", "", "KEYWORDS", "", "UNDERSCORE_CHARACTER", "allCharactersAreUnderscore", "", "getAllCharactersAreUnderscore", "(Ljava/lang/String;)Z", "hasAllowedCharacters", "getHasAllowedCharacters", "isIdentifier", "isIsoControl", "(C)Z", "isKeyword", "characterLiteralWithoutSingleQuotes", "c", "escapeCharacterLiterals", "s", "requireNoneOf", "", "modifiers", "Lcom/squareup/kotlinpoet/KModifier;", "forbidden", "", "(Ljava/util/Set;[Lcom/squareup/kotlinpoet/KModifier;)V", "requireNoneOrOneOf", "mutuallyExclusive", "stringLiteralWithQuotes", "value", "escapeDollarSign", "isConstantContext", "alreadyEscaped", "containsAnyOf", "T", "", "t", "(Ljava/util/Collection;[Ljava/lang/Object;)Z", "ensureEndsWithNewLine", "Lcom/squareup/kotlinpoet/CodeBlock;", "escapeIfAllCharactersAreUnderscore", "escapeIfHasAllowedCharacters", "escapeIfKeyword", "escapeIfNecessary", "validate", "escapeIfNotJavaIdentifier", "escapeSegmentsIfNecessary", "delimiter", "failIfEscapeInvalid", "isOneOf", "t1", "t2", "t3", "t4", "t5", "t6", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z", "toEnumSet", "", "toImmutableList", "", "toImmutableMap", "", "K", "V", "toImmutableSet", "kotlinpoet"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: Util.kt */
public final class UtilKt {
    private static final char ALLOWED_CHARACTER = '$';
    private static final Regex IDENTIFIER_REGEX = new Regex("((\\p{gc=Lu}+|\\p{gc=Ll}+|\\p{gc=Lt}+|\\p{gc=Lm}+|\\p{gc=Lo}+|\\p{gc=Nl}+)+\\d*\\p{gc=Lu}*\\p{gc=Ll}*\\p{gc=Lt}*\\p{gc=Lm}*\\p{gc=Lo}*\\p{gc=Nl}*)|(`[^\n\r`]+`)");
    private static final Set<Character> ILLEGAL_CHARACTERS_TO_ESCAPE = SetsKt.setOf(Character.valueOf(FilenameUtils.EXTENSION_SEPARATOR), ';', Character.valueOf(AbstractJsonLexerKt.BEGIN_LIST), Character.valueOf(AbstractJsonLexerKt.END_LIST), Character.valueOf(IOUtils.DIR_SEPARATOR_UNIX), Character.valueOf(Typography.less), Character.valueOf(Typography.greater), Character.valueOf(AbstractJsonLexerKt.COLON), '\\');
    private static final Set<String> KEYWORDS = SetsKt.setOf("as", "break", "class", "continue", "do", "else", Constants.CASEFIRST_FALSE, "for", "fun", "if", "in", "interface", "is", "null", "object", SentryStackFrame.JsonKeys.PACKAGE, "return", "super", "this", "throw", "true", "try", "typealias", "typeof", "val", "var", "when", "while", "by", "catch", "constructor", "delegate", "dynamic", "field", "file", "finally", "get", "import", Session.JsonKeys.INIT, "param", "property", NotificationsService.RECEIVER_KEY, "set", "setparam", "where", "actual", "abstract", "annotation", "companion", "const", "crossinline", "data", "enum", "expect", "external", "final", "infix", HostKt.INLINE, "inner", "internal", "lateinit", "noinline", TtmlNode.TEXT_EMPHASIS_MARK_OPEN, "operator", "out", "override", "private", "protected", "public", "reified", "sealed", "suspend", "tailrec", "value", "vararg", "header", "impl", "yield");
    private static final char UNDERSCORE_CHARACTER = '_';

    private static final boolean isIsoControl(char c) {
        if (c >= 0 && c < ' ') {
            return true;
        }
        return 127 <= c && c < 160;
    }

    public static final <K, V> Map<K, V> toImmutableMap(Map<K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Map<K, V> unmodifiableMap = Collections.unmodifiableMap(new LinkedHashMap(map));
        Intrinsics.checkNotNullExpressionValue(unmodifiableMap, "unmodifiableMap(LinkedHashMap(this))");
        return unmodifiableMap;
    }

    public static final <T> List<T> toImmutableList(Collection<? extends T> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        List<T> unmodifiableList = Collections.unmodifiableList(new ArrayList(collection));
        Intrinsics.checkNotNullExpressionValue(unmodifiableList, "unmodifiableList(ArrayList(this))");
        return unmodifiableList;
    }

    public static final <T> Set<T> toImmutableSet(Collection<? extends T> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Set<T> unmodifiableSet = Collections.unmodifiableSet(new LinkedHashSet(collection));
        Intrinsics.checkNotNullExpressionValue(unmodifiableSet, "unmodifiableSet(LinkedHashSet(this))");
        return unmodifiableSet;
    }

    public static final /* synthetic */ <T extends Enum<T>> Set<T> toEnumSet(Collection<? extends T> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.reifiedOperationMarker(5, ExifInterface.GPS_DIRECTION_TRUE);
        Enum[] enumArr = new Enum[0];
        return new LinkedHashSet();
    }

    public static /* synthetic */ boolean isOneOf$default(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, int i, Object obj8) {
        return isOneOf(obj, obj2, obj3, (i & 4) != 0 ? null : obj4, (i & 8) != 0 ? null : obj5, (i & 16) != 0 ? null : obj6, (i & 32) != 0 ? null : obj7);
    }

    public static final <T> boolean isOneOf(T t, T t2, T t3, T t4, T t5, T t6, T t7) {
        return Intrinsics.areEqual((Object) t, (Object) t2) || Intrinsics.areEqual((Object) t, (Object) t3) || Intrinsics.areEqual((Object) t, (Object) t4) || Intrinsics.areEqual((Object) t, (Object) t5) || Intrinsics.areEqual((Object) t, (Object) t6) || Intrinsics.areEqual((Object) t, (Object) t7);
    }

    public static final String characterLiteralWithoutSingleQuotes(char c) {
        if (c == 8) {
            return "\\b";
        }
        if (c == 9) {
            return "\\t";
        }
        if (c == 10) {
            return "\\n";
        }
        if (c == 13) {
            return "\\r";
        }
        if (c == '\"') {
            return "\"";
        }
        if (c == '\'') {
            return "\\'";
        }
        if (c == '\\') {
            return "\\\\";
        }
        if (!isIsoControl(c)) {
            return String.valueOf(c);
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("\\u%04x", Arrays.copyOf(new Object[]{Integer.valueOf(c)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    public static final String escapeCharacterLiterals(String str) {
        Intrinsics.checkNotNullParameter(str, CmcdData.Factory.STREAMING_FORMAT_SS);
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            sb.append(characterLiteralWithoutSingleQuotes(str.charAt(i)));
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    public static /* synthetic */ String stringLiteralWithQuotes$default(String str, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return stringLiteralWithQuotes(str, z, z2);
    }

    public static final String stringLiteralWithQuotes(String str, boolean z, boolean z2) {
        int i;
        String str2 = str;
        Intrinsics.checkNotNullParameter(str2, "value");
        if (z2 || !StringsKt.contains$default((CharSequence) str2, 10, false, 2, (Object) null)) {
            StringBuilder sb = new StringBuilder(str.length() + 32);
            if (z) {
                sb.append('\"');
            } else {
                sb.append("\"\"\"");
            }
            int length = str.length();
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = str2.charAt(i2);
                if (charAt == '\'') {
                    sb.append("'");
                } else if (charAt == '\"' && z) {
                    sb.append("\\\"");
                } else if (charAt != '$' || !z) {
                    sb.append(characterLiteralWithoutSingleQuotes(charAt));
                } else {
                    sb.append("${'$'}");
                }
            }
            if (z) {
                sb.append('\"');
            } else {
                sb.append("\"\"\"");
            }
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "result.toString()");
            return sb2;
        }
        StringBuilder sb3 = new StringBuilder(str.length() + 32);
        sb3.append("\"\"\"\n|");
        int i3 = 0;
        while (i3 < str.length()) {
            char charAt2 = str2.charAt(i3);
            int i4 = i3;
            StringBuilder sb4 = sb3;
            if (StringsKt.regionMatches$default(str, i3, "\"\"\"", 0, 3, false, 16, (Object) null)) {
                sb4.append("\"\"${'\"'}");
                i = i4 + 2;
            } else {
                if (charAt2 == 10) {
                    sb4.append("\n|");
                } else if (charAt2 != '$' || !z) {
                    sb4.append(charAt2);
                } else {
                    sb4.append("${'$'}");
                }
                i = i4;
            }
            i3 = i + 1;
            sb3 = sb4;
        }
        StringBuilder sb5 = sb3;
        if (!StringsKt.endsWith$default(str2, "\n", false, 2, (Object) null)) {
            sb5.append("\n");
        }
        sb5.append("\"\"\".trimMargin()");
        String sb6 = sb5.toString();
        Intrinsics.checkNotNullExpressionValue(sb6, "result.toString()");
        return sb6;
    }

    public static final CodeBlock ensureEndsWithNewLine(CodeBlock codeBlock) {
        Intrinsics.checkNotNullParameter(codeBlock, "<this>");
        if (codeBlock.isEmpty()) {
            return codeBlock;
        }
        CodeBlock.Builder builder = codeBlock.toBuilder();
        String str = (String) CollectionsKt.last(codeBlock.trim$kotlinpoet().getFormatParts$kotlinpoet());
        if (!CodeBlock.Companion.isPlaceholder$kotlinpoet(str) || !(!builder.getArgs$kotlinpoet().isEmpty())) {
            builder.getFormatParts$kotlinpoet().set(builder.getFormatParts$kotlinpoet().lastIndexOf(str), StringsKt.trimEnd(str, 10));
            builder.getFormatParts$kotlinpoet().add("\n");
        } else {
            Object last = CollectionsKt.last(builder.getArgs$kotlinpoet());
            if (last instanceof String) {
                builder.getArgs$kotlinpoet().set(builder.getArgs$kotlinpoet().size() - 1, StringsKt.trimEnd((String) last, 10) + 10);
            }
        }
        return builder.build();
    }

    public static final boolean isIdentifier(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return IDENTIFIER_REGEX.matches(str);
    }

    public static final boolean isKeyword(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return KEYWORDS.contains(str);
    }

    public static final boolean getHasAllowedCharacters(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        CharSequence charSequence = str;
        for (int i = 0; i < charSequence.length(); i++) {
            if (charSequence.charAt(i) == '$') {
                return true;
            }
        }
        return false;
    }

    public static final boolean getAllCharactersAreUnderscore(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        CharSequence charSequence = str;
        int i = 0;
        while (true) {
            boolean z = true;
            if (i >= charSequence.length()) {
                return true;
            }
            if (charSequence.charAt(i) != '_') {
                z = false;
            }
            if (!z) {
                return false;
            }
            i++;
        }
    }

    private static final void failIfEscapeInvalid(String str) {
        CharSequence charSequence = str;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= charSequence.length()) {
                break;
            }
            if (ILLEGAL_CHARACTERS_TO_ESCAPE.contains(Character.valueOf(charSequence.charAt(i)))) {
                z = true;
                break;
            }
            i++;
        }
        if (!(!z)) {
            throw new IllegalArgumentException(("Can't escape identifier " + str + " because it contains illegal characters: " + CollectionsKt.joinToString$default(CollectionsKt.intersect(ILLEGAL_CHARACTERS_TO_ESCAPE, StringsKt.toSet(charSequence)), "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null)).toString());
        }
    }

    public static final String escapeIfNecessary(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        String escapeIfAllCharactersAreUnderscore = escapeIfAllCharactersAreUnderscore(escapeIfHasAllowedCharacters(escapeIfKeyword(escapeIfNotJavaIdentifier(str))));
        if (z) {
            failIfEscapeInvalid(escapeIfAllCharactersAreUnderscore);
        }
        return escapeIfAllCharactersAreUnderscore;
    }

    public static /* synthetic */ String escapeIfNecessary$default(String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return escapeIfNecessary(str, z);
    }

    private static final boolean alreadyEscaped(String str) {
        return StringsKt.startsWith$default(str, "`", false, 2, (Object) null) && StringsKt.endsWith$default(str, "`", false, 2, (Object) null);
    }

    private static final String escapeIfKeyword(String str) {
        return (!isKeyword(str) || alreadyEscaped(str)) ? str : "`" + str + '`';
    }

    private static final String escapeIfHasAllowedCharacters(String str) {
        return (!getHasAllowedCharacters(str) || alreadyEscaped(str)) ? str : "`" + str + '`';
    }

    private static final String escapeIfAllCharactersAreUnderscore(String str) {
        return (!getAllCharactersAreUnderscore(str) || alreadyEscaped(str)) ? str : "`" + str + '`';
    }

    private static final String escapeIfNotJavaIdentifier(String str) {
        if (Character.isJavaIdentifierStart(StringsKt.first(str))) {
            boolean z = true;
            CharSequence drop = StringsKt.drop(str, 1);
            int i = 0;
            while (true) {
                if (i >= drop.length()) {
                    z = false;
                    break;
                } else if (!Character.isJavaIdentifierPart(drop.charAt(i))) {
                    break;
                } else {
                    i++;
                }
            }
            if (!z) {
                return str;
            }
        }
        return !alreadyEscaped(str) ? StringsKt.replace$default("`" + str + '`', ' ', (char) Typography.middleDot, false, 4, (Object) null) : str;
    }

    public static final String escapeSegmentsIfNecessary(String str, char c) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Collection arrayList = new ArrayList();
        for (Object next : StringsKt.split$default((CharSequence) str, new char[]{c}, false, 0, 6, (Object) null)) {
            if (((String) next).length() > 0) {
                arrayList.add(next);
            }
        }
        return CollectionsKt.joinToString$default((List) arrayList, String.valueOf(c), (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, UtilKt$escapeSegmentsIfNecessary$2.INSTANCE, 30, (Object) null);
    }

    public static /* synthetic */ String escapeSegmentsIfNecessary$default(String str, char c, int i, Object obj) {
        if ((i & 1) != 0) {
            c = FilenameUtils.EXTENSION_SEPARATOR;
        }
        return escapeSegmentsIfNecessary(str, c);
    }

    public static final void requireNoneOrOneOf(Set<? extends KModifier> set, KModifier... kModifierArr) {
        Intrinsics.checkNotNullParameter(set, "modifiers");
        Intrinsics.checkNotNullParameter(kModifierArr, "mutuallyExclusive");
        boolean z = false;
        int i = 0;
        for (KModifier contains : kModifierArr) {
            if (set.contains(contains)) {
                i++;
            }
        }
        if (i <= 1) {
            z = true;
        }
        if (!z) {
            StringBuilder append = new StringBuilder("modifiers ").append(set).append(" must contain none or only one of ");
            String arrays = Arrays.toString(kModifierArr);
            Intrinsics.checkNotNullExpressionValue(arrays, "toString(this)");
            throw new IllegalArgumentException(append.append(arrays).toString().toString());
        }
    }

    public static final void requireNoneOf(Set<? extends KModifier> set, KModifier... kModifierArr) {
        Intrinsics.checkNotNullParameter(set, "modifiers");
        Intrinsics.checkNotNullParameter(kModifierArr, "forbidden");
        int length = kModifierArr.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = true;
                break;
            } else if (set.contains(kModifierArr[i])) {
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            StringBuilder append = new StringBuilder("modifiers ").append(set).append(" must contain none of ");
            String arrays = Arrays.toString(kModifierArr);
            Intrinsics.checkNotNullExpressionValue(arrays, "toString(this)");
            throw new IllegalArgumentException(append.append(arrays).toString().toString());
        }
    }

    public static final <T> boolean containsAnyOf(Collection<? extends T> collection, T... tArr) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.checkNotNullParameter(tArr, "t");
        for (T contains : tArr) {
            if (collection.contains(contains)) {
                return true;
            }
        }
        return false;
    }
}
