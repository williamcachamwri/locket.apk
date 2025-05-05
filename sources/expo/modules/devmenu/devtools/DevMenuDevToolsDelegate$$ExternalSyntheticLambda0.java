package expo.modules.devmenu.devtools;

import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DevMenuDevToolsDelegate$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DeveloperSettings f$0;
    public final /* synthetic */ DevSupportManager f$1;

    public /* synthetic */ DevMenuDevToolsDelegate$$ExternalSyntheticLambda0(DeveloperSettings developerSettings, DevSupportManager devSupportManager) {
        this.f$0 = developerSettings;
        this.f$1 = devSupportManager;
    }

    public final void run() {
        DevMenuDevToolsDelegate.toggleRemoteDebugging$lambda$3$lambda$2(this.f$0, this.f$1);
    }
}
