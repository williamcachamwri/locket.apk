package expo.modules.localization;

import android.content.res.Configuration;
import expo.modules.core.interfaces.ApplicationLifecycleListener;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"expo/modules/localization/LocalizationPackage$createApplicationLifecycleListeners$1", "Lexpo/modules/core/interfaces/ApplicationLifecycleListener;", "onConfigurationChanged", "", "newConfig", "Landroid/content/res/Configuration;", "expo-localization_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LocalizationPackage.kt */
public final class LocalizationPackage$createApplicationLifecycleListeners$1 implements ApplicationLifecycleListener {
    LocalizationPackage$createApplicationLifecycleListeners$1() {
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Notifier.INSTANCE.onConfigurationChanged();
    }
}
