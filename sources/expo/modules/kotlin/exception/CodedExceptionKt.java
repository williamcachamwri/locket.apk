package expo.modules.kotlin.exception;

import androidx.exifinterface.media.ExifInterface;
import expo.modules.core.errors.CodedException;
import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\b\u001a\r\u0010\u0004\u001a\u00020\u0003*\u00020\u0005H\b¨\u0006\u0006"}, d2 = {"errorCodeOf", "", "T", "Lexpo/modules/kotlin/exception/CodedException;", "toCodedException", "", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: CodedException.kt */
public final class CodedExceptionKt {
    public static final CodedException toCodedException(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "<this>");
        if (th instanceof CodedException) {
            return (CodedException) th;
        }
        if (!(th instanceof CodedException)) {
            return new UnexpectedException(th);
        }
        String code = ((CodedException) th).getCode();
        Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
        return new CodedException(code, th.getMessage(), th.getCause());
    }

    public static final /* synthetic */ <T extends CodedException> String errorCodeOf() {
        CodedException.Companion companion = CodedException.Companion;
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Class<CodedException> cls = CodedException.class;
        Class cls2 = cls;
        return companion.inferCode(cls);
    }
}
