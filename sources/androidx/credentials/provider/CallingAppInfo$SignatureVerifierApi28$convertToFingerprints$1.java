package androidx.credentials.provider;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0005\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CallingAppInfo.kt */
final class CallingAppInfo$SignatureVerifierApi28$convertToFingerprints$1 extends Lambda implements Function1<Byte, CharSequence> {
    public static final CallingAppInfo$SignatureVerifierApi28$convertToFingerprints$1 INSTANCE = new CallingAppInfo$SignatureVerifierApi28$convertToFingerprints$1();

    CallingAppInfo$SignatureVerifierApi28$convertToFingerprints$1() {
        super(1);
    }

    public final CharSequence invoke(byte b) {
        String format = String.format("%02X", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(this, *args)");
        return format;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).byteValue());
    }
}
