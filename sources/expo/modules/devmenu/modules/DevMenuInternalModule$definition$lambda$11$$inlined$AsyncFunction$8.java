package expo.modules.devmenu.modules;

import expo.interfaces.devmenu.DevMenuPreferencesInterface;
import expo.modules.devmenu.DevMenuManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u00012\u0010\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "R", "P0", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$7"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$8 extends Lambda implements Function1<Object[], Object> {
    public DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$8() {
        super(1);
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        Boolean bool = objArr[0];
        if (bool != null) {
            boolean booleanValue = bool.booleanValue();
            DevMenuPreferencesInterface settings = DevMenuManager.INSTANCE.getSettings();
            if (settings != null) {
                settings.setOnboardingFinished(booleanValue);
            }
            return Unit.INSTANCE;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
    }
}
