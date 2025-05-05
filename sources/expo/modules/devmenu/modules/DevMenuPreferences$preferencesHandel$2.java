package expo.modules.devmenu.modules;

import android.content.Context;
import expo.modules.kotlin.exception.Exceptions;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lexpo/modules/devmenu/modules/DevMenuPreferencesHandle;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuPreferences.kt */
final class DevMenuPreferences$preferencesHandel$2 extends Lambda implements Function0<DevMenuPreferencesHandle> {
    final /* synthetic */ DevMenuPreferences this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DevMenuPreferences$preferencesHandel$2(DevMenuPreferences devMenuPreferences) {
        super(0);
        this.this$0 = devMenuPreferences;
    }

    public final DevMenuPreferencesHandle invoke() {
        Context reactContext = this.this$0.getAppContext().getReactContext();
        if (reactContext != null) {
            return new DevMenuPreferencesHandle(reactContext);
        }
        throw new Exceptions.ReactContextLost();
    }
}
