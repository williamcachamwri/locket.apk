package expo.interfaces.devmenu.items;

import com.facebook.react.bridge.ReadableMap;
import io.sentry.protocol.SentryStackFrame;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u0006R\u001f\u0010\u0004\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lexpo/interfaces/devmenu/items/DevMenuExportedFunction;", "Lexpo/interfaces/devmenu/items/DevMenuExportedCallable;", "id", "", "function", "Lkotlin/Function1;", "Lcom/facebook/react/bridge/ReadableMap;", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getFunction", "()Lkotlin/jvm/functions/Function1;", "call", "args", "expo-dev-menu-interface_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuExportedCallable.kt */
public final class DevMenuExportedFunction extends DevMenuExportedCallable {
    private final Function1<ReadableMap, Unit> function;

    public final Function1<ReadableMap, Unit> getFunction() {
        return this.function;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DevMenuExportedFunction(String str, Function1<? super ReadableMap, Unit> function1) {
        super(str, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(function1, SentryStackFrame.JsonKeys.FUNCTION);
        this.function = function1;
    }

    public final void call(ReadableMap readableMap) {
        this.function.invoke(readableMap);
    }
}
