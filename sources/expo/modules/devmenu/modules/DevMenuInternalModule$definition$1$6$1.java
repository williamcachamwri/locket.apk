package expo.modules.devmenu.modules;

import com.facebook.react.devsupport.interfaces.DevSupportManager;
import expo.modules.devmenu.DevMenuManager;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuInternalModule.kt */
final class DevMenuInternalModule$definition$1$6$1 implements Runnable {
    final /* synthetic */ DevSupportManager $devSupportManager;

    DevMenuInternalModule$definition$1$6$1(DevSupportManager devSupportManager) {
        this.$devSupportManager = devSupportManager;
    }

    public final void run() {
        DevMenuManager.INSTANCE.closeMenu();
        this.$devSupportManager.setDevSupportEnabled(true);
        this.$devSupportManager.showDevOptionsDialog();
    }
}
