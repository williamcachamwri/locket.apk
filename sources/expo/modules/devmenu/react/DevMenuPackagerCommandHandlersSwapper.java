package expo.modules.devmenu.react;

import com.facebook.fbreact.specs.NativeDevMenuSpec;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.devsupport.DevSupportManagerBase;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.packagerconnection.RequestHandler;
import expo.modules.devmenu.DevMenuManager;
import expo.modules.devmenu.helpers.DevMenuReflectionExtensionsKt;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u0002J\"\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¨\u0006\f"}, d2 = {"Lexpo/modules/devmenu/react/DevMenuPackagerCommandHandlersSwapper;", "", "()V", "swapCurrentCommandHandlers", "", "reactInstanceManager", "Lcom/facebook/react/ReactInstanceManager;", "handlers", "", "", "Lcom/facebook/react/packagerconnection/RequestHandler;", "swapPackagerCommandHandlers", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuPackagerCommandHandlersSwapper.kt */
public final class DevMenuPackagerCommandHandlersSwapper {
    public final void swapPackagerCommandHandlers(ReactInstanceManager reactInstanceManager, Map<String, ? extends RequestHandler> map) {
        Map map2;
        Intrinsics.checkNotNullParameter(reactInstanceManager, "reactInstanceManager");
        Intrinsics.checkNotNullParameter(map, "handlers");
        try {
            DevSupportManager devSupportManager = (DevSupportManager) DevMenuReflectionExtensionsKt.getPrivateDeclaredFieldValue(ReactInstanceManager.class, "mDevSupportManager", reactInstanceManager);
            if (devSupportManager instanceof DevSupportManagerBase) {
                Map map3 = (Map) DevMenuReflectionExtensionsKt.getPrivateDeclaredFieldValue(DevSupportManagerBase.class, "mCustomPackagerCommandHandlers", devSupportManager);
                if (map3 == null || (map2 = MapsKt.toMutableMap(map3)) == null) {
                    map2 = new LinkedHashMap();
                }
                map2.putAll(map);
                DevMenuReflectionExtensionsKt.setPrivateDeclaredFieldValue(DevSupportManagerBase.class, "mCustomPackagerCommandHandlers", devSupportManager, map2);
                swapCurrentCommandHandlers(reactInstanceManager, map);
            }
        } catch (Exception e) {
            SentryLogcatAdapter.w(NativeDevMenuSpec.NAME, "Couldn't add packager command handlers to current client: " + e.getMessage(), e);
        }
    }

    private final void swapCurrentCommandHandlers(ReactInstanceManager reactInstanceManager, Map<String, ? extends RequestHandler> map) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(DevMenuManager.INSTANCE.getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new DevMenuPackagerCommandHandlersSwapper$swapCurrentCommandHandlers$1(reactInstanceManager, map, (Continuation<? super DevMenuPackagerCommandHandlersSwapper$swapCurrentCommandHandlers$1>) null), 3, (Object) null);
    }
}
