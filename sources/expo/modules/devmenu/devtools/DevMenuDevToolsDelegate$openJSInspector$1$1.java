package expo.modules.devmenu.devtools;

import com.facebook.react.bridge.ReactContext;
import expo.modules.devmenu.DevMenuManager;
import expo.modules.devmenu.DevMenuUtilsKt;
import expo.modules.devmenu.api.DevMenuMetroClient;
import io.sentry.android.core.SentryLogcatAdapter;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.devmenu.devtools.DevMenuDevToolsDelegate$openJSInspector$1$1", f = "DevMenuDevToolsDelegate.kt", i = {}, l = {82}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: DevMenuDevToolsDelegate.kt */
final class DevMenuDevToolsDelegate$openJSInspector$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $metroHost;
    final /* synthetic */ ReactContext $reactContext;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DevMenuDevToolsDelegate$openJSInspector$1$1(String str, ReactContext reactContext, Continuation<? super DevMenuDevToolsDelegate$openJSInspector$1$1> continuation) {
        super(2, continuation);
        this.$metroHost = str;
        this.$reactContext = reactContext;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DevMenuDevToolsDelegate$openJSInspector$1$1(this.$metroHost, this.$reactContext, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DevMenuDevToolsDelegate$openJSInspector$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            DevMenuMetroClient metroClient = DevMenuManager.INSTANCE.getMetroClient();
            String str = this.$metroHost;
            String packageName = this.$reactContext.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName(...)");
            this.label = 1;
            if (metroClient.openJSInspector(str, packageName, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e) {
                SentryLogcatAdapter.w(DevMenuUtilsKt.DEV_MENU_TAG, "Unable to open js inspector: " + e.getMessage(), e);
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
