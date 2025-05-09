package kotlinx.serialization.json.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0001\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0010\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J\u0018\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0018\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0004H\u0002J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004H\u0014J\b\u0010\u001f\u001a\u00020 H&J\u0006\u0010!\u001a\u00020 J\u0010\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u0004H\u0003J\u0006\u0010#\u001a\u00020 J\u0018\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\b\u0010&\u001a\u00020\u000fH&J\u001a\u0010'\u001a\u0004\u0018\u00010\u000f2\u0006\u0010(\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020 H&J\b\u0010*\u001a\u00020+H&J\u000e\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020+J\u0010\u0010*\u001a\u00020\u001c2\u0006\u0010,\u001a\u00020-H\u0016J\u0006\u0010.\u001a\u00020/J\u0006\u00100\u001a\u00020\u000fJ \u00100\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0005J3\u00101\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020 2!\u00102\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u001c03H\u0016J\u0006\u00107\u001a\u00020\u000fJ\u0006\u00108\u001a\u00020\u000fJ\u0018\u00109\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010:\u001a\u00020\u001cH\u0016J\u0006\u0010;\u001a\u00020\u001cJ\u0015\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020+H\u0000¢\u0006\u0002\b?J\"\u0010<\u001a\u00020=2\u0006\u0010@\u001a\u00020\u000f2\b\b\u0002\u0010A\u001a\u00020\u00042\b\b\u0002\u0010B\u001a\u00020\u000fJ\u000e\u0010C\u001a\u00020\u001c2\u0006\u0010D\u001a\u00020\u000fJ\u0018\u0010E\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\u0018\u0010F\u001a\u00020\u00042\u0006\u0010G\u001a\u00020-2\u0006\u0010\u001a\u001a\u00020\u0004H\u0016J\u0018\u0010H\u001a\u00020 2\u0006\u0010)\u001a\u00020 2\u0006\u0010G\u001a\u00020-H\u0002J\u0006\u0010I\u001a\u00020 J\u0010\u0010J\u001a\u00020 2\u0006\u0010K\u001a\u00020-H\u0004J\u0006\u0010L\u001a\u00020+J\u0010\u0010M\u001a\u0004\u0018\u00010\u000f2\u0006\u0010)\u001a\u00020 J\u0010\u0010N\u001a\u00020\u00042\u0006\u0010A\u001a\u00020\u0004H&J1\u0010O\u001a\u00020\u001c2\u0006\u0010P\u001a\u00020 2\b\b\u0002\u0010A\u001a\u00020\u00042\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u000f0QH\bø\u0001\u0000¢\u0006\u0002\bRJ\u000e\u0010S\u001a\u00020\u001c2\u0006\u0010T\u001a\u00020 J\b\u0010U\u001a\u00020\u0004H\u0016J\u0018\u0010V\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010W\u001a\u00020\u0004H\u0016J\b\u0010X\u001a\u00020\u000fH\u0002J\b\u0010Y\u001a\u00020\u000fH\u0016J\b\u0010Z\u001a\u00020 H&J\u0010\u0010[\u001a\u00020 2\b\b\u0002\u0010\\\u001a\u00020 J\u0010\u0010]\u001a\u00020\u001c2\u0006\u0010,\u001a\u00020-H\u0004J\b\u0010^\u001a\u00020 H\u0002JC\u0010_\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010`\u001a\u00020 2!\u00102\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u001c03H\u0002R\u0012\u0010\u0003\u001a\u00020\u00048\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u00020\r8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00020\u0011X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\u0002\u0007\n\u0005\b20\u0001¨\u0006a"}, d2 = {"Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "", "()V", "currentPosition", "", "escapedString", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getEscapedString", "()Ljava/lang/StringBuilder;", "setEscapedString", "(Ljava/lang/StringBuilder;)V", "path", "Lkotlinx/serialization/json/internal/JsonPath;", "peekedString", "", "source", "", "getSource", "()Ljava/lang/CharSequence;", "appendEsc", "startPosition", "appendEscape", "lastPosition", "current", "appendHex", "startPos", "appendRange", "", "fromIndex", "toIndex", "canConsumeValue", "", "consumeBoolean", "start", "consumeBooleanLenient", "consumeBooleanLiteral", "literalSuffix", "consumeKeyString", "consumeLeadingMatchingValue", "keyToMatch", "isLenient", "consumeNextToken", "", "expected", "", "consumeNumericLiteral", "", "consumeString", "consumeStringChunked", "consumeChunk", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "stringChunk", "consumeStringLenient", "consumeStringLenientNotNull", "decodedString", "ensureHaveChars", "expectEof", "fail", "", "expectedToken", "fail$kotlinx_serialization_json", "message", "position", "hint", "failOnUnknownKey", "key", "fromHexChar", "indexOf", "char", "insideString", "isNotEof", "isValidValueStart", "c", "peekNextToken", "peekString", "prefetchOrEof", "require", "condition", "Lkotlin/Function0;", "require$kotlinx_serialization_json", "skipElement", "allowLenientStrings", "skipWhitespaces", "substring", "endPos", "takePeeked", "toString", "tryConsumeComma", "tryConsumeNull", "doConsume", "unexpectedToken", "wasUnquotedString", "writeRange", "currentChunkHasEscape", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AbstractJsonLexer.kt */
public abstract class AbstractJsonLexer {
    /* access modifiers changed from: protected */
    public int currentPosition;
    private StringBuilder escapedString = new StringBuilder();
    public final JsonPath path = new JsonPath();
    private String peekedString;

    public abstract boolean canConsumeValue();

    public abstract String consumeKeyString();

    public abstract String consumeLeadingMatchingValue(String str, boolean z);

    public abstract byte consumeNextToken();

    public void ensureHaveChars() {
    }

    /* access modifiers changed from: protected */
    public abstract CharSequence getSource();

    /* access modifiers changed from: protected */
    public final boolean isValidValueStart(char c) {
        boolean z = false;
        if (((c == '}' || c == ']') || c == ':') || c == ',') {
            z = true;
        }
        return !z;
    }

    public abstract int prefetchOrEof(int i);

    public abstract boolean tryConsumeComma();

    public final boolean isNotEof() {
        return peekNextToken() != 10;
    }

    public final void expectEof() {
        if (consumeNextToken() != 10) {
            fail$default(this, "Expected EOF after parsing, but had " + getSource().charAt(this.currentPosition - 1) + " instead", 0, (String) null, 6, (Object) null);
            throw new KotlinNothingValueException();
        }
    }

    /* access modifiers changed from: protected */
    public final StringBuilder getEscapedString() {
        return this.escapedString;
    }

    /* access modifiers changed from: protected */
    public final void setEscapedString(StringBuilder sb) {
        Intrinsics.checkNotNullParameter(sb, "<set-?>");
        this.escapedString = sb;
    }

    public final byte consumeNextToken(byte b) {
        byte consumeNextToken = consumeNextToken();
        if (consumeNextToken == b) {
            return consumeNextToken;
        }
        fail$kotlinx_serialization_json(b);
        throw new KotlinNothingValueException();
    }

    public void consumeNextToken(char c) {
        ensureHaveChars();
        CharSequence source = getSource();
        int i = this.currentPosition;
        while (true) {
            int prefetchOrEof = prefetchOrEof(i);
            if (prefetchOrEof != -1) {
                int i2 = prefetchOrEof + 1;
                char charAt = source.charAt(prefetchOrEof);
                if (!(charAt == ' ' || charAt == 10 || charAt == 13 || charAt == 9)) {
                    this.currentPosition = i2;
                    if (charAt != c) {
                        unexpectedToken(c);
                    } else {
                        return;
                    }
                }
                i = i2;
            } else {
                this.currentPosition = prefetchOrEof;
                unexpectedToken(c);
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void unexpectedToken(char c) {
        int i = this.currentPosition - 1;
        this.currentPosition = i;
        if (i < 0 || c != '\"' || !Intrinsics.areEqual((Object) consumeStringLenient(), (Object) "null")) {
            fail$kotlinx_serialization_json(AbstractJsonLexerKt.charToTokenClass(c));
            throw new KotlinNothingValueException();
        } else {
            fail("Expected string literal but 'null' literal was found", this.currentPosition - 4, AbstractJsonLexerKt.coerceInputValuesHint);
            throw new KotlinNothingValueException();
        }
    }

    public final Void fail$kotlinx_serialization_json(byte b) {
        fail$default(this, "Expected " + (b == 1 ? "quotation mark '\"'" : b == 4 ? "comma ','" : b == 5 ? "colon ':'" : b == 6 ? "start of the object '{'" : b == 7 ? "end of the object '}'" : b == 8 ? "start of the array '['" : b == 9 ? "end of the array ']'" : "valid token") + ", but had '" + ((this.currentPosition == getSource().length() || this.currentPosition <= 0) ? "EOF" : String.valueOf(getSource().charAt(this.currentPosition - 1))) + "' instead", this.currentPosition - 1, (String) null, 4, (Object) null);
        throw new KotlinNothingValueException();
    }

    public final byte peekNextToken() {
        CharSequence source = getSource();
        int i = this.currentPosition;
        while (true) {
            int prefetchOrEof = prefetchOrEof(i);
            if (prefetchOrEof != -1) {
                char charAt = source.charAt(prefetchOrEof);
                if (charAt == ' ' || charAt == 10 || charAt == 13 || charAt == 9) {
                    i = prefetchOrEof + 1;
                } else {
                    this.currentPosition = prefetchOrEof;
                    return AbstractJsonLexerKt.charToTokenClass(charAt);
                }
            } else {
                this.currentPosition = prefetchOrEof;
                return 10;
            }
        }
    }

    public static /* synthetic */ boolean tryConsumeNull$default(AbstractJsonLexer abstractJsonLexer, boolean z, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                z = true;
            }
            return abstractJsonLexer.tryConsumeNull(z);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: tryConsumeNull");
    }

    public final boolean tryConsumeNull(boolean z) {
        int prefetchOrEof = prefetchOrEof(skipWhitespaces());
        int length = getSource().length() - prefetchOrEof;
        if (length < 4 || prefetchOrEof == -1) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if ("null".charAt(i) != getSource().charAt(prefetchOrEof + i)) {
                return false;
            }
        }
        if (length > 4 && AbstractJsonLexerKt.charToTokenClass(getSource().charAt(prefetchOrEof + 4)) == 0) {
            return false;
        }
        if (!z) {
            return true;
        }
        this.currentPosition = prefetchOrEof + 4;
        return true;
    }

    public int skipWhitespaces() {
        int prefetchOrEof;
        char charAt;
        int i = this.currentPosition;
        while (true) {
            prefetchOrEof = prefetchOrEof(i);
            if (prefetchOrEof == -1 || !((charAt = getSource().charAt(prefetchOrEof)) == ' ' || charAt == 10 || charAt == 13 || charAt == 9)) {
                this.currentPosition = prefetchOrEof;
            } else {
                i = prefetchOrEof + 1;
            }
        }
        this.currentPosition = prefetchOrEof;
        return prefetchOrEof;
    }

    public final String peekString(boolean z) {
        String str;
        byte peekNextToken = peekNextToken();
        if (z) {
            if (peekNextToken != 1 && peekNextToken != 0) {
                return null;
            }
            str = consumeStringLenient();
        } else if (peekNextToken != 1) {
            return null;
        } else {
            str = consumeString();
        }
        this.peekedString = str;
        return str;
    }

    public int indexOf(char c, int i) {
        return StringsKt.indexOf$default(getSource(), c, i, false, 4, (Object) null);
    }

    public String substring(int i, int i2) {
        return getSource().subSequence(i, i2).toString();
    }

    private final boolean insideString(boolean z, char c) {
        if (z) {
            if (AbstractJsonLexerKt.charToTokenClass(c) == 0) {
                return true;
            }
        } else if (c != '\"') {
            return true;
        }
        return false;
    }

    public void consumeStringChunked(boolean z, Function1<? super String, Unit> function1) {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(function1, "consumeChunk");
        byte peekNextToken = peekNextToken();
        if (!z || peekNextToken == 0) {
            if (!z) {
                consumeNextToken('\"');
            }
            int i3 = this.currentPosition;
            char charAt = getSource().charAt(i3);
            boolean z2 = false;
            int i4 = i3;
            while (insideString(z, charAt)) {
                if (z || charAt != '\\') {
                    int i5 = i4 + 1;
                    i = i3;
                    i2 = i5;
                } else {
                    i2 = prefetchOrEof(appendEscape(i3, i4));
                    z2 = true;
                    i = i2;
                }
                if (i2 >= getSource().length()) {
                    writeRange(i, i2, z2, function1);
                    int prefetchOrEof = prefetchOrEof(i2);
                    if (prefetchOrEof != -1) {
                        z2 = false;
                        i3 = prefetchOrEof;
                        i4 = i3;
                    } else {
                        fail$default(this, "EOF", prefetchOrEof, (String) null, 4, (Object) null);
                        throw new KotlinNothingValueException();
                    }
                } else {
                    int i6 = i;
                    i4 = i2;
                    i3 = i6;
                }
                charAt = getSource().charAt(i4);
            }
            writeRange(i3, i4, z2, function1);
            this.currentPosition = i4;
            if (!z) {
                consumeNextToken('\"');
            }
        }
    }

    private final void writeRange(int i, int i2, boolean z, Function1<? super String, Unit> function1) {
        if (z) {
            function1.invoke(decodedString(i, i2));
        } else {
            function1.invoke(substring(i, i2));
        }
    }

    public final String consumeString() {
        if (this.peekedString != null) {
            return takePeeked();
        }
        return consumeKeyString();
    }

    /* access modifiers changed from: protected */
    public final String consumeString(CharSequence charSequence, int i, int i2) {
        String str;
        int prefetchOrEof;
        Intrinsics.checkNotNullParameter(charSequence, "source");
        char charAt = charSequence.charAt(i2);
        boolean z = false;
        while (charAt != '\"') {
            if (charAt == '\\') {
                prefetchOrEof = prefetchOrEof(appendEscape(i, i2));
                if (prefetchOrEof == -1) {
                    fail$default(this, "EOF", prefetchOrEof, (String) null, 4, (Object) null);
                    throw new KotlinNothingValueException();
                }
            } else {
                i2++;
                if (i2 >= charSequence.length()) {
                    appendRange(i, i2);
                    prefetchOrEof = prefetchOrEof(i2);
                    if (prefetchOrEof == -1) {
                        fail$default(this, "EOF", prefetchOrEof, (String) null, 4, (Object) null);
                        throw new KotlinNothingValueException();
                    }
                } else {
                    continue;
                    charAt = charSequence.charAt(i2);
                }
            }
            z = true;
            i = prefetchOrEof;
            i2 = i;
            charAt = charSequence.charAt(i2);
        }
        if (!z) {
            str = substring(i, i2);
        } else {
            str = decodedString(i, i2);
        }
        this.currentPosition = i2 + 1;
        return str;
    }

    private final int appendEscape(int i, int i2) {
        appendRange(i, i2);
        return appendEsc(i2 + 1);
    }

    private final String decodedString(int i, int i2) {
        appendRange(i, i2);
        String sb = this.escapedString.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "escapedString.toString()");
        this.escapedString.setLength(0);
        return sb;
    }

    private final String takePeeked() {
        String str = this.peekedString;
        Intrinsics.checkNotNull(str);
        this.peekedString = null;
        return str;
    }

    public final String consumeStringLenientNotNull() {
        String consumeStringLenient = consumeStringLenient();
        if (!Intrinsics.areEqual((Object) consumeStringLenient, (Object) "null") || !wasUnquotedString()) {
            return consumeStringLenient;
        }
        fail$default(this, "Unexpected 'null' value instead of string literal", 0, (String) null, 6, (Object) null);
        throw new KotlinNothingValueException();
    }

    private final boolean wasUnquotedString() {
        return getSource().charAt(this.currentPosition - 1) != '\"';
    }

    public final String consumeStringLenient() {
        String str;
        if (this.peekedString != null) {
            return takePeeked();
        }
        int skipWhitespaces = skipWhitespaces();
        if (skipWhitespaces >= getSource().length() || skipWhitespaces == -1) {
            fail$default(this, "EOF", skipWhitespaces, (String) null, 4, (Object) null);
            throw new KotlinNothingValueException();
        }
        byte charToTokenClass = AbstractJsonLexerKt.charToTokenClass(getSource().charAt(skipWhitespaces));
        if (charToTokenClass == 1) {
            return consumeString();
        }
        if (charToTokenClass == 0) {
            boolean z = false;
            while (AbstractJsonLexerKt.charToTokenClass(getSource().charAt(skipWhitespaces)) == 0) {
                skipWhitespaces++;
                if (skipWhitespaces >= getSource().length()) {
                    appendRange(this.currentPosition, skipWhitespaces);
                    int prefetchOrEof = prefetchOrEof(skipWhitespaces);
                    if (prefetchOrEof == -1) {
                        this.currentPosition = skipWhitespaces;
                        return decodedString(0, 0);
                    }
                    skipWhitespaces = prefetchOrEof;
                    z = true;
                }
            }
            if (!z) {
                str = substring(this.currentPosition, skipWhitespaces);
            } else {
                str = decodedString(this.currentPosition, skipWhitespaces);
            }
            this.currentPosition = skipWhitespaces;
            return str;
        }
        fail$default(this, "Expected beginning of the string, but got " + getSource().charAt(skipWhitespaces), 0, (String) null, 6, (Object) null);
        throw new KotlinNothingValueException();
    }

    /* access modifiers changed from: protected */
    public void appendRange(int i, int i2) {
        this.escapedString.append(getSource(), i, i2);
    }

    private final int appendEsc(int i) {
        int prefetchOrEof = prefetchOrEof(i);
        if (prefetchOrEof != -1) {
            int i2 = prefetchOrEof + 1;
            char charAt = getSource().charAt(prefetchOrEof);
            if (charAt == 'u') {
                return appendHex(getSource(), i2);
            }
            char escapeToChar = AbstractJsonLexerKt.escapeToChar(charAt);
            if (escapeToChar != 0) {
                this.escapedString.append(escapeToChar);
                return i2;
            }
            fail$default(this, "Invalid escaped char '" + charAt + '\'', 0, (String) null, 6, (Object) null);
            throw new KotlinNothingValueException();
        }
        fail$default(this, "Expected escape sequence to continue, got EOF", 0, (String) null, 6, (Object) null);
        throw new KotlinNothingValueException();
    }

    private final int appendHex(CharSequence charSequence, int i) {
        int i2 = i + 4;
        if (i2 >= charSequence.length()) {
            this.currentPosition = i;
            ensureHaveChars();
            if (this.currentPosition + 4 < charSequence.length()) {
                return appendHex(charSequence, this.currentPosition);
            }
            fail$default(this, "Unexpected EOF during unicode escape", 0, (String) null, 6, (Object) null);
            throw new KotlinNothingValueException();
        }
        this.escapedString.append((char) ((fromHexChar(charSequence, i) << 12) + (fromHexChar(charSequence, i + 1) << 8) + (fromHexChar(charSequence, i + 2) << 4) + fromHexChar(charSequence, i + 3)));
        return i2;
    }

    public static /* synthetic */ void require$kotlinx_serialization_json$default(AbstractJsonLexer abstractJsonLexer, boolean z, int i, Function0 function0, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 2) != 0) {
                i = abstractJsonLexer.currentPosition;
            }
            int i3 = i;
            Intrinsics.checkNotNullParameter(function0, "message");
            if (!z) {
                fail$default(abstractJsonLexer, (String) function0.invoke(), i3, (String) null, 4, (Object) null);
                throw new KotlinNothingValueException();
            }
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: require");
    }

    public final void require$kotlinx_serialization_json(boolean z, int i, Function0<String> function0) {
        Intrinsics.checkNotNullParameter(function0, "message");
        if (!z) {
            fail$default(this, function0.invoke(), i, (String) null, 4, (Object) null);
            throw new KotlinNothingValueException();
        }
    }

    private final int fromHexChar(CharSequence charSequence, int i) {
        char charAt = charSequence.charAt(i);
        boolean z = true;
        if ('0' <= charAt && charAt < ':') {
            return charAt - '0';
        }
        char c = 'a';
        if (!('a' <= charAt && charAt < 'g')) {
            c = 'A';
            if ('A' > charAt || charAt >= 'G') {
                z = false;
            }
            if (!z) {
                fail$default(this, "Invalid toHexChar char '" + charAt + "' in unicode escape", 0, (String) null, 6, (Object) null);
                throw new KotlinNothingValueException();
            }
        }
        return (charAt - c) + 10;
    }

    public final void skipElement(boolean z) {
        List arrayList = new ArrayList();
        byte peekNextToken = peekNextToken();
        if (peekNextToken == 8 || peekNextToken == 6) {
            while (true) {
                byte peekNextToken2 = peekNextToken();
                boolean z2 = true;
                if (peekNextToken2 != 1) {
                    if (!(peekNextToken2 == 8 || peekNextToken2 == 6)) {
                        z2 = false;
                    }
                    if (z2) {
                        arrayList.add(Byte.valueOf(peekNextToken2));
                    } else if (peekNextToken2 == 9) {
                        if (((Number) CollectionsKt.last(arrayList)).byteValue() == 8) {
                            CollectionsKt.removeLast(arrayList);
                        } else {
                            throw JsonExceptionsKt.JsonDecodingException(this.currentPosition, "found ] instead of } at path: " + this.path, getSource());
                        }
                    } else if (peekNextToken2 == 7) {
                        if (((Number) CollectionsKt.last(arrayList)).byteValue() == 6) {
                            CollectionsKt.removeLast(arrayList);
                        } else {
                            throw JsonExceptionsKt.JsonDecodingException(this.currentPosition, "found } instead of ] at path: " + this.path, getSource());
                        }
                    } else if (peekNextToken2 == 10) {
                        fail$default(this, "Unexpected end of input due to malformed JSON during ignoring unknown keys", 0, (String) null, 6, (Object) null);
                        throw new KotlinNothingValueException();
                    }
                    consumeNextToken();
                    if (arrayList.size() == 0) {
                        return;
                    }
                } else if (z) {
                    consumeStringLenient();
                } else {
                    consumeKeyString();
                }
            }
        } else {
            consumeStringLenient();
        }
    }

    public String toString() {
        return "JsonReader(source='" + getSource() + "', currentPosition=" + this.currentPosition + ')';
    }

    public final void failOnUnknownKey(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        fail("Encountered an unknown key '" + str + '\'', StringsKt.lastIndexOf$default((CharSequence) substring(0, this.currentPosition), str, 0, false, 6, (Object) null), AbstractJsonLexerKt.ignoreUnknownKeysHint);
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ Void fail$default(AbstractJsonLexer abstractJsonLexer, String str, int i, String str2, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 2) != 0) {
                i = abstractJsonLexer.currentPosition;
            }
            if ((i2 & 4) != 0) {
                str2 = "";
            }
            return abstractJsonLexer.fail(str, i, str2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fail");
    }

    public final Void fail(String str, int i, String str2) {
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(str2, "hint");
        throw JsonExceptionsKt.JsonDecodingException(i, str + " at path: " + this.path.getPath() + (str2.length() == 0 ? "" : "\n" + str2), getSource());
    }

    public final long consumeNumericLiteral() {
        boolean z;
        int prefetchOrEof = prefetchOrEof(skipWhitespaces());
        if (prefetchOrEof >= getSource().length() || prefetchOrEof == -1) {
            fail$default(this, "EOF", 0, (String) null, 6, (Object) null);
            throw new KotlinNothingValueException();
        }
        if (getSource().charAt(prefetchOrEof) == '\"') {
            prefetchOrEof++;
            if (prefetchOrEof != getSource().length()) {
                z = true;
            } else {
                fail$default(this, "EOF", 0, (String) null, 6, (Object) null);
                throw new KotlinNothingValueException();
            }
        } else {
            z = false;
        }
        int i = prefetchOrEof;
        long j = 0;
        boolean z2 = true;
        boolean z3 = false;
        while (z2) {
            char charAt = getSource().charAt(i);
            if (charAt != '-') {
                if (AbstractJsonLexerKt.charToTokenClass(charAt) != 0) {
                    break;
                }
                i++;
                z2 = i != getSource().length();
                int i2 = charAt - '0';
                if (i2 >= 0 && i2 < 10) {
                    j = (j * ((long) 10)) - ((long) i2);
                    if (j > 0) {
                        fail$default(this, "Numeric value overflow", 0, (String) null, 6, (Object) null);
                        throw new KotlinNothingValueException();
                    }
                } else {
                    fail$default(this, "Unexpected symbol '" + charAt + "' in numeric literal", 0, (String) null, 6, (Object) null);
                    throw new KotlinNothingValueException();
                }
            } else if (i == prefetchOrEof) {
                i++;
                z3 = true;
            } else {
                fail$default(this, "Unexpected symbol '-' in numeric literal", 0, (String) null, 6, (Object) null);
                throw new KotlinNothingValueException();
            }
        }
        if (prefetchOrEof == i || (z3 && prefetchOrEof == i - 1)) {
            fail$default(this, "Expected numeric literal", 0, (String) null, 6, (Object) null);
            throw new KotlinNothingValueException();
        }
        if (z) {
            if (!z2) {
                fail$default(this, "EOF", 0, (String) null, 6, (Object) null);
                throw new KotlinNothingValueException();
            } else if (getSource().charAt(i) == '\"') {
                i++;
            } else {
                fail$default(this, "Expected closing quotation mark", 0, (String) null, 6, (Object) null);
                throw new KotlinNothingValueException();
            }
        }
        this.currentPosition = i;
        if (z3) {
            return j;
        }
        if (j != Long.MIN_VALUE) {
            return -j;
        }
        fail$default(this, "Numeric value overflow", 0, (String) null, 6, (Object) null);
        throw new KotlinNothingValueException();
    }

    public final boolean consumeBoolean() {
        return consumeBoolean(skipWhitespaces());
    }

    public final boolean consumeBooleanLenient() {
        boolean z;
        int skipWhitespaces = skipWhitespaces();
        if (skipWhitespaces != getSource().length()) {
            if (getSource().charAt(skipWhitespaces) == '\"') {
                skipWhitespaces++;
                z = true;
            } else {
                z = false;
            }
            boolean consumeBoolean = consumeBoolean(skipWhitespaces);
            if (z) {
                if (this.currentPosition == getSource().length()) {
                    fail$default(this, "EOF", 0, (String) null, 6, (Object) null);
                    throw new KotlinNothingValueException();
                } else if (getSource().charAt(this.currentPosition) == '\"') {
                    this.currentPosition++;
                } else {
                    fail$default(this, "Expected closing quotation mark", 0, (String) null, 6, (Object) null);
                    throw new KotlinNothingValueException();
                }
            }
            return consumeBoolean;
        }
        fail$default(this, "EOF", 0, (String) null, 6, (Object) null);
        throw new KotlinNothingValueException();
    }

    private final boolean consumeBoolean(int i) {
        int prefetchOrEof = prefetchOrEof(i);
        if (prefetchOrEof >= getSource().length() || prefetchOrEof == -1) {
            fail$default(this, "EOF", 0, (String) null, 6, (Object) null);
            throw new KotlinNothingValueException();
        }
        int i2 = prefetchOrEof + 1;
        char charAt = getSource().charAt(prefetchOrEof) | ' ';
        if (charAt == 'f') {
            consumeBooleanLiteral("alse", i2);
            return false;
        } else if (charAt == 't') {
            consumeBooleanLiteral("rue", i2);
            return true;
        } else {
            fail$default(this, "Expected valid boolean literal prefix, but had '" + consumeStringLenient() + '\'', 0, (String) null, 6, (Object) null);
            throw new KotlinNothingValueException();
        }
    }

    private final void consumeBooleanLiteral(String str, int i) {
        if (getSource().length() - i >= str.length()) {
            int length = str.length();
            int i2 = 0;
            while (i2 < length) {
                if (str.charAt(i2) == (getSource().charAt(i + i2) | ' ')) {
                    i2++;
                } else {
                    fail$default(this, "Expected valid boolean literal prefix, but had '" + consumeStringLenient() + '\'', 0, (String) null, 6, (Object) null);
                    throw new KotlinNothingValueException();
                }
            }
            this.currentPosition = i + str.length();
            return;
        }
        fail$default(this, "Unexpected end of boolean literal", 0, (String) null, 6, (Object) null);
        throw new KotlinNothingValueException();
    }
}
