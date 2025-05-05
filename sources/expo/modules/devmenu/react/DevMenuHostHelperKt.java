package expo.modules.devmenu.react;

import android.app.Application;
import androidx.media3.common.MimeTypes;
import com.facebook.hermes.reactexecutor.HermesExecutorFactory;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.jscexecutor.JSCExecutorFactory;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.facebook.soloader.SoLoader;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"createNonDebuggableJavaScriptExecutorFactory", "Lcom/facebook/react/bridge/JavaScriptExecutorFactory;", "application", "Landroid/app/Application;", "expo-dev-menu_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuHostHelper.kt */
public final class DevMenuHostHelperKt {
    public static final JavaScriptExecutorFactory createNonDebuggableJavaScriptExecutorFactory(Application application) {
        Intrinsics.checkNotNullParameter(application, MimeTypes.BASE_TYPE_APPLICATION);
        SoLoader.init(application.getApplicationContext(), false);
        if (SoLoader.getLibraryPath("libjsc.so") != null) {
            String packageName = application.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName(...)");
            String friendlyDeviceName = AndroidInfoHelpers.getFriendlyDeviceName();
            Intrinsics.checkNotNullExpressionValue(friendlyDeviceName, "getFriendlyDeviceName(...)");
            return new JSCExecutorFactory(packageName, friendlyDeviceName);
        }
        HermesExecutorFactory hermesExecutorFactory = new HermesExecutorFactory();
        Class<HermesExecutorFactory> cls = HermesExecutorFactory.class;
        try {
            cls.getMethod("setEnableDebugger", new Class[]{Boolean.TYPE}).invoke(hermesExecutorFactory, new Object[]{false});
        } catch (Throwable unused) {
        }
        return hermesExecutorFactory;
    }
}
