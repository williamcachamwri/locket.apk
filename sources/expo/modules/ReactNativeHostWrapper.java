package expo.modules;

import android.app.Application;
import androidx.media3.common.MimeTypes;
import com.facebook.react.JSEngineResolutionAlgorithm;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.common.SurfaceDelegateFactory;
import com.facebook.react.devsupport.DevSupportManagerFactory;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\n\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\n\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016¨\u0006\u0013"}, d2 = {"Lexpo/modules/ReactNativeHostWrapper;", "Lexpo/modules/ReactNativeHostWrapperBase;", "application", "Landroid/app/Application;", "host", "Lcom/facebook/react/ReactNativeHost;", "(Landroid/app/Application;Lcom/facebook/react/ReactNativeHost;)V", "getDevSupportManagerFactory", "Lcom/facebook/react/devsupport/DevSupportManagerFactory;", "getJSEngineResolutionAlgorithm", "Lcom/facebook/react/JSEngineResolutionAlgorithm;", "getReactPackageTurboModuleManagerDelegateBuilder", "Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate$Builder;", "getRedBoxHandler", "Lcom/facebook/react/devsupport/interfaces/RedBoxHandler;", "getShouldRequireActivity", "", "getSurfaceDelegateFactory", "Lcom/facebook/react/common/SurfaceDelegateFactory;", "expo_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ReactNativeHostWrapper.kt */
public final class ReactNativeHostWrapper extends ReactNativeHostWrapperBase {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactNativeHostWrapper(Application application, ReactNativeHost reactNativeHost) {
        super(application, reactNativeHost);
        Intrinsics.checkNotNullParameter(application, MimeTypes.BASE_TYPE_APPLICATION);
        Intrinsics.checkNotNullParameter(reactNativeHost, "host");
    }

    /* access modifiers changed from: protected */
    public DevSupportManagerFactory getDevSupportManagerFactory() {
        DevSupportManagerFactory devSupportManagerFactory = (DevSupportManagerFactory) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(getReactNativeHostHandlers$expo_release()), ReactNativeHostWrapper$getDevSupportManagerFactory$1.INSTANCE));
        return devSupportManagerFactory == null ? (DevSupportManagerFactory) invokeDelegateMethod$expo_release("getDevSupportManagerFactory") : devSupportManagerFactory;
    }

    /* access modifiers changed from: protected */
    public ReactPackageTurboModuleManagerDelegate.Builder getReactPackageTurboModuleManagerDelegateBuilder() {
        return (ReactPackageTurboModuleManagerDelegate.Builder) invokeDelegateMethod$expo_release("getReactPackageTurboModuleManagerDelegateBuilder");
    }

    /* access modifiers changed from: protected */
    public JSEngineResolutionAlgorithm getJSEngineResolutionAlgorithm() {
        return (JSEngineResolutionAlgorithm) invokeDelegateMethod$expo_release("getJSEngineResolutionAlgorithm");
    }

    public boolean getShouldRequireActivity() {
        return getHost().getShouldRequireActivity();
    }

    public SurfaceDelegateFactory getSurfaceDelegateFactory() {
        SurfaceDelegateFactory surfaceDelegateFactory = getHost().getSurfaceDelegateFactory();
        Intrinsics.checkNotNullExpressionValue(surfaceDelegateFactory, "getSurfaceDelegateFactory(...)");
        return surfaceDelegateFactory;
    }

    /* access modifiers changed from: protected */
    public RedBoxHandler getRedBoxHandler() {
        return (RedBoxHandler) invokeDelegateMethod$expo_release("getRedBoxHandler");
    }
}
