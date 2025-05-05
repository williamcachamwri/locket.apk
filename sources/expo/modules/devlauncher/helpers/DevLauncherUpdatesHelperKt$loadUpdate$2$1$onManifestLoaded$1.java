package expo.modules.devlauncher.helpers;

import expo.modules.updatesinterface.UpdatesInterface;
import kotlin.Metadata;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"expo/modules/devlauncher/helpers/DevLauncherUpdatesHelperKt$loadUpdate$2$1$onManifestLoaded$1", "Lexpo/modules/updatesinterface/UpdatesInterface$Update;", "getLaunchAssetPath", "", "getManifest", "Lorg/json/JSONObject;", "expo-dev-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherUpdatesHelper.kt */
public final class DevLauncherUpdatesHelperKt$loadUpdate$2$1$onManifestLoaded$1 implements UpdatesInterface.Update {
    final /* synthetic */ JSONObject $manifest;

    DevLauncherUpdatesHelperKt$loadUpdate$2$1$onManifestLoaded$1(JSONObject jSONObject) {
        this.$manifest = jSONObject;
    }

    public String getLaunchAssetPath() {
        throw new Exception("Tried to access launch asset path for a manifest that was not loaded");
    }

    public JSONObject getManifest() {
        return this.$manifest;
    }
}
