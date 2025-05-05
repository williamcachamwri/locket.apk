package expo.modules.crypto;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.CharsKt;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0005\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "byte", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: CryptoModule.kt */
final class CryptoModule$digestString$1 extends Lambda implements Function1<Byte, CharSequence> {
    public static final CryptoModule$digestString$1 INSTANCE = new CryptoModule$digestString$1();

    CryptoModule$digestString$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).byteValue());
    }

    public final CharSequence invoke(byte b) {
        String num = Integer.toString((b & 255) + 256, CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(num, "toString(...)");
        String substring = num.substring(1);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return substring;
    }
}
