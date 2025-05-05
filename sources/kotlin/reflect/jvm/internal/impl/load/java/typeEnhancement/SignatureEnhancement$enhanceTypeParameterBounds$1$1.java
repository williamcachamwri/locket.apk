package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.RawType;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

/* compiled from: signatureEnhancement.kt */
final class SignatureEnhancement$enhanceTypeParameterBounds$1$1 extends Lambda implements Function1<UnwrappedType, Boolean> {
    public static final SignatureEnhancement$enhanceTypeParameterBounds$1$1 INSTANCE = new SignatureEnhancement$enhanceTypeParameterBounds$1$1();

    SignatureEnhancement$enhanceTypeParameterBounds$1$1() {
        super(1);
    }

    public final Boolean invoke(UnwrappedType unwrappedType) {
        Intrinsics.checkNotNullParameter(unwrappedType, "it");
        return Boolean.valueOf(unwrappedType instanceof RawType);
    }
}
