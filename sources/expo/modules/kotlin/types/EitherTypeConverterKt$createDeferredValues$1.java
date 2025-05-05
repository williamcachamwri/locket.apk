package expo.modules.kotlin.types;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KType;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lkotlin/reflect/KType;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: EitherTypeConverter.kt */
final class EitherTypeConverterKt$createDeferredValues$1 extends Lambda implements Function1<KType, CharSequence> {
    public static final EitherTypeConverterKt$createDeferredValues$1 INSTANCE = new EitherTypeConverterKt$createDeferredValues$1();

    EitherTypeConverterKt$createDeferredValues$1() {
        super(1);
    }

    public final CharSequence invoke(KType kType) {
        Intrinsics.checkNotNullParameter(kType, "it");
        return kType.toString();
    }
}
