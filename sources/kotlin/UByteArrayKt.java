package kotlin;

import io.sentry.Session;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a-\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005H\bø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u001c\u0010\b\u001a\u00020\u00012\n\u0010\t\u001a\u00020\u0001\"\u00020\u0006H\b¢\u0006\u0004\b\n\u0010\u000b\u0002\u0007\n\u0005\b20\u0001¨\u0006\f"}, d2 = {"UByteArray", "Lkotlin/UByteArray;", "size", "", "init", "Lkotlin/Function1;", "Lkotlin/UByte;", "(ILkotlin/jvm/functions/Function1;)[B", "ubyteArrayOf", "elements", "ubyteArrayOf-GBYM_sE", "([B)[B", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: UByteArray.kt */
public final class UByteArrayKt {
    /* renamed from: ubyteArrayOf-GBYM_sE  reason: not valid java name */
    private static final byte[] m2531ubyteArrayOfGBYM_sE(byte... bArr) {
        Intrinsics.checkNotNullParameter(bArr, "elements");
        return bArr;
    }

    private static final byte[] UByteArray(int i, Function1<? super Integer, UByte> function1) {
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = function1.invoke(Integer.valueOf(i2)).m2512unboximpl();
        }
        return UByteArray.m2515constructorimpl(bArr);
    }
}
