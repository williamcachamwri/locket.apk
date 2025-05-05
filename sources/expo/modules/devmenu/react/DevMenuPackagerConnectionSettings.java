package expo.modules.devmenu.react;

import android.content.Context;
import com.facebook.react.packagerconnection.PackagerConnectionSettings;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\b\u0010\b\u001a\u00020\u0003H\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lexpo/modules/devmenu/react/DevMenuPackagerConnectionSettings;", "Lcom/facebook/react/packagerconnection/PackagerConnectionSettings;", "serverIp", "", "applicationContext", "Landroid/content/Context;", "(Ljava/lang/String;Landroid/content/Context;)V", "getDebugServerHost", "getInspectorServerHost", "setDebugServerHost", "", "host", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuPackagerConnectionSettings.kt */
public final class DevMenuPackagerConnectionSettings extends PackagerConnectionSettings {
    private final String serverIp;

    public void setDebugServerHost(String str) {
        Intrinsics.checkNotNullParameter(str, "host");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DevMenuPackagerConnectionSettings(String str, Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(str, "serverIp");
        Intrinsics.checkNotNullParameter(context, "applicationContext");
        this.serverIp = str;
    }

    public String getDebugServerHost() {
        return this.serverIp;
    }

    public String getInspectorServerHost() {
        return this.serverIp;
    }
}
