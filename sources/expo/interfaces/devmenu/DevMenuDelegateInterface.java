package expo.interfaces.devmenu;

import android.os.Bundle;
import com.facebook.react.ReactInstanceManager;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lexpo/interfaces/devmenu/DevMenuDelegateInterface;", "", "appInfo", "Landroid/os/Bundle;", "reactInstanceManager", "Lcom/facebook/react/ReactInstanceManager;", "supportsDevelopment", "", "expo-dev-menu-interface_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuDelegateInterface.kt */
public interface DevMenuDelegateInterface {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DevMenuDelegateInterface.kt */
    public static final class DefaultImpls {
        public static boolean supportsDevelopment(DevMenuDelegateInterface devMenuDelegateInterface) {
            return true;
        }
    }

    Bundle appInfo();

    ReactInstanceManager reactInstanceManager();

    boolean supportsDevelopment();
}
