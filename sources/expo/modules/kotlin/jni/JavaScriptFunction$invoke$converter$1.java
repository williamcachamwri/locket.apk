package expo.modules.kotlin.jni;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lkotlin/reflect/KType;", "ReturnType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: JavaScriptFunction.kt */
final class JavaScriptFunction$invoke$converter$1 extends Lambda implements Function0<KType> {
    public static final JavaScriptFunction$invoke$converter$1 INSTANCE = new JavaScriptFunction$invoke$converter$1();

    JavaScriptFunction$invoke$converter$1() {
        super(0);
    }

    public final KType invoke() {
        return Reflection.typeOf(Unit.class);
    }
}
