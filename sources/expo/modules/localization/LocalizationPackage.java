package expo.modules.localization;

import android.content.Context;
import expo.modules.core.interfaces.ApplicationLifecycleListener;
import expo.modules.core.interfaces.Package;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, d2 = {"Lexpo/modules/localization/LocalizationPackage;", "Lexpo/modules/core/interfaces/Package;", "()V", "createApplicationLifecycleListeners", "", "Lexpo/modules/core/interfaces/ApplicationLifecycleListener;", "context", "Landroid/content/Context;", "expo-localization_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LocalizationPackage.kt */
public final class LocalizationPackage implements Package {
    public List<ApplicationLifecycleListener> createApplicationLifecycleListeners(Context context) {
        return CollectionsKt.listOf(new LocalizationPackage$createApplicationLifecycleListeners$1());
    }
}
