package expo.modules.localization;

import android.content.Context;
import expo.modules.kotlin.AppContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "invoke", "expo/modules/kotlin/modules/ModuleDefinitionBuilder$OnCreate$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ModuleDefinitionBuilder.kt */
public final class LocalizationModule$definition$lambda$6$$inlined$OnCreate$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ LocalizationModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LocalizationModule$definition$lambda$6$$inlined$OnCreate$1(LocalizationModule localizationModule) {
        super(0);
        this.this$0 = localizationModule;
    }

    public final void invoke() {
        Context reactContext;
        AppContext appContext = this.this$0.getAppContext();
        if (!(appContext == null || (reactContext = appContext.getReactContext()) == null)) {
            this.this$0.setRTLFromStringResources(reactContext);
        }
        this.this$0.observer = new LocalizationModule$definition$1$5$2(this.this$0);
        Notifier.INSTANCE.registerObserver(this.this$0.observer);
    }
}
