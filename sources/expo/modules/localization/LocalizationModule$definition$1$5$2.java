package expo.modules.localization;

import android.os.Bundle;
import expo.modules.kotlin.modules.Module;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: LocalizationModule.kt */
final class LocalizationModule$definition$1$5$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ LocalizationModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LocalizationModule$definition$1$5$2(LocalizationModule localizationModule) {
        super(0);
        this.this$0 = localizationModule;
    }

    public final void invoke() {
        Module.sendEvent$default((Module) this.this$0, "onLocaleSettingsChanged", (Bundle) null, 2, (Object) null);
        Module.sendEvent$default((Module) this.this$0, "onCalendarSettingsChanged", (Bundle) null, 2, (Object) null);
    }
}
