package expo.modules.devmenu.modules;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import expo.modules.devmenu.DevMenuManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u00012\u0010\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "R", "P0", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$7"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class DevMenuModule$definition$lambda$5$$inlined$AsyncFunction$3 extends Lambda implements Function1<Object[], Object> {
    public DevMenuModule$definition$lambda$5$$inlined$AsyncFunction$3() {
        super(1);
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        ReadableArray readableArray = objArr[0];
        if (readableArray != null) {
            ReadableArray readableArray2 = readableArray;
            int size = readableArray2.size();
            for (int i = 0; i < size; i++) {
                ReadableMap map = readableArray2.getMap(i);
                Intrinsics.checkNotNullExpressionValue(map, "getMap(...)");
                String string = map.getString("name");
                if (string != null) {
                    Intrinsics.checkNotNull(string);
                    DevMenuManager.INSTANCE.getRegisteredCallbacks().add(new DevMenuManager.Callback(string, map.hasKey("shouldCollapse") ? map.getBoolean("shouldCollapse") : true));
                }
            }
            return Unit.INSTANCE;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.facebook.react.bridge.ReadableArray");
    }
}
