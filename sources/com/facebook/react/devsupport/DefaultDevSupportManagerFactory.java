package com.facebook.react.devsupport;

import android.content.Context;
import com.facebook.react.common.SurfaceDelegateFactory;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.DevLoadingViewManager;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import com.facebook.react.packagerconnection.RequestHandler;
import java.util.Map;

public class DefaultDevSupportManagerFactory implements DevSupportManagerFactory {
    private static final String DEVSUPPORT_IMPL_CLASS = "BridgeDevSupportManager";
    private static final String DEVSUPPORT_IMPL_PACKAGE = "com.facebook.react.devsupport";

    @Deprecated
    public DevSupportManager create(Context context, ReactInstanceDevHelper reactInstanceDevHelper, String str, boolean z, int i) {
        return create(context, reactInstanceDevHelper, str, z, (RedBoxHandler) null, (DevBundleDownloadListener) null, i, (Map<String, RequestHandler>) null, (SurfaceDelegateFactory) null, (DevLoadingViewManager) null);
    }

    public DevSupportManager create(Context context, ReactInstanceDevHelper reactInstanceDevHelper, String str, boolean z, RedBoxHandler redBoxHandler, DevBundleDownloadListener devBundleDownloadListener, int i, Map<String, RequestHandler> map, SurfaceDelegateFactory surfaceDelegateFactory, DevLoadingViewManager devLoadingViewManager) {
        Context context2 = context;
        if (!z) {
            return new DisabledDevSupportManager();
        }
        try {
            return (DevSupportManager) Class.forName("com.facebook.react.devsupport.BridgeDevSupportManager").getConstructor(new Class[]{Context.class, ReactInstanceDevHelper.class, String.class, Boolean.TYPE, RedBoxHandler.class, DevBundleDownloadListener.class, Integer.TYPE, Map.class, SurfaceDelegateFactory.class, DevLoadingViewManager.class}).newInstance(new Object[]{context2, reactInstanceDevHelper, str, true, redBoxHandler, devBundleDownloadListener, Integer.valueOf(i), map, surfaceDelegateFactory, devLoadingViewManager});
        } catch (Exception unused) {
            return new PerftestDevSupportManager(context2);
        }
    }
}
