package expo.modules.devmenu.react;

import com.facebook.fbreact.specs.NativeDevMenuSpec;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.devsupport.DevServerHelper;
import com.facebook.react.devsupport.DevSupportManagerBase;
import com.facebook.react.packagerconnection.JSPackagerClient;
import com.facebook.react.packagerconnection.RequestHandler;
import expo.modules.devmenu.helpers.DevMenuReflectionExtensionsKt;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.devmenu.react.DevMenuPackagerCommandHandlersSwapper$swapCurrentCommandHandlers$1", f = "DevMenuPackagerCommandHandlersSwapper.kt", i = {}, l = {109}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: DevMenuPackagerCommandHandlersSwapper.kt */
final class DevMenuPackagerCommandHandlersSwapper$swapCurrentCommandHandlers$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Map<String, RequestHandler> $handlers;
    final /* synthetic */ ReactInstanceManager $reactInstanceManager;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DevMenuPackagerCommandHandlersSwapper$swapCurrentCommandHandlers$1(ReactInstanceManager reactInstanceManager, Map<String, ? extends RequestHandler> map, Continuation<? super DevMenuPackagerCommandHandlersSwapper$swapCurrentCommandHandlers$1> continuation) {
        super(2, continuation);
        this.$reactInstanceManager = reactInstanceManager;
        this.$handlers = map;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DevMenuPackagerCommandHandlersSwapper$swapCurrentCommandHandlers$1(this.$reactInstanceManager, this.$handlers, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DevMenuPackagerCommandHandlersSwapper$swapCurrentCommandHandlers$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Map map;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e) {
                SentryLogcatAdapter.w(NativeDevMenuSpec.NAME, "Couldn't add packager command handlers to current client: " + e.getMessage(), e);
                return Unit.INSTANCE;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        do {
            Class<DevServerHelper> cls = DevServerHelper.class;
            JSPackagerClient jSPackagerClient = (JSPackagerClient) DevMenuReflectionExtensionsKt.getPrivateDeclaredFieldValue(cls, "mPackagerClient", (DevServerHelper) DevMenuReflectionExtensionsKt.getPrivateDeclaredFieldValue(DevSupportManagerBase.class, "mDevServerHelper", (DevSupportManagerBase) DevMenuReflectionExtensionsKt.getPrivateDeclaredFieldValue(ReactInstanceManager.class, "mDevSupportManager", this.$reactInstanceManager)));
            if (jSPackagerClient != null) {
                Map map2 = (Map) DevMenuReflectionExtensionsKt.getPrivateDeclaredFieldValue(JSPackagerClient.class, "mRequestHandlers", jSPackagerClient);
                if (map2 == null || (map = MapsKt.toMutableMap(map2)) == null) {
                    map = new LinkedHashMap();
                }
                map.putAll(this.$handlers);
                DevMenuReflectionExtensionsKt.setPrivateDeclaredFieldValue(JSPackagerClient.class, "mRequestHandlers", jSPackagerClient, map);
                return Unit.INSTANCE;
            }
            this.label = 1;
        } while (DelayKt.delay(50, this) != coroutine_suspended);
        return coroutine_suspended;
    }
}
