package expo.modules.devmenu.extensions;

import com.facebook.react.devsupport.DevMenuInternalSettingsWrapper;
import com.facebook.react.devsupport.HMRClient;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuExtension.kt */
final class DevMenuExtension$devMenuItems$1$9$fastRefreshAction$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ DevMenuInternalSettingsWrapper $devInternalSettings;
    final /* synthetic */ DevSupportManager $reactDevManager;
    final /* synthetic */ DevMenuExtension this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DevMenuExtension$devMenuItems$1$9$fastRefreshAction$1(DevMenuInternalSettingsWrapper devMenuInternalSettingsWrapper, DevMenuExtension devMenuExtension, DevSupportManager devSupportManager) {
        super(0);
        this.$devInternalSettings = devMenuInternalSettingsWrapper;
        this.this$0 = devMenuExtension;
        this.$reactDevManager = devSupportManager;
    }

    public final void invoke() {
        boolean z = !this.$devInternalSettings.isHotModuleReplacementEnabled();
        this.$devInternalSettings.setHotModuleReplacementEnabled(z);
        if (this.this$0.getReactApplicationContext() != null) {
            if (z) {
                ((HMRClient) this.this$0.getReactApplicationContext().getJSModule(HMRClient.class)).enable();
            } else {
                ((HMRClient) this.this$0.getReactApplicationContext().getJSModule(HMRClient.class)).disable();
            }
        }
        if (z && !this.$devInternalSettings.isJSDevModeEnabled()) {
            this.$devInternalSettings.setJSDevModeEnabled(true);
            this.$reactDevManager.handleReloadJS();
        }
    }
}
