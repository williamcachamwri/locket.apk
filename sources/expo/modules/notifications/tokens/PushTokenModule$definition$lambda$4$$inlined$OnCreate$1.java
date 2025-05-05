package expo.modules.notifications.tokens;

import expo.modules.core.interfaces.services.EventEmitter;
import expo.modules.notifications.ModuleNotFoundException;
import expo.modules.notifications.tokens.interfaces.PushTokenManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "invoke", "expo/modules/kotlin/modules/ModuleDefinitionBuilder$OnCreate$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ModuleDefinitionBuilder.kt */
public final class PushTokenModule$definition$lambda$4$$inlined$OnCreate$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ PushTokenModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PushTokenModule$definition$lambda$4$$inlined$OnCreate$1(PushTokenModule pushTokenModule) {
        super(0);
        this.this$0 = pushTokenModule;
    }

    public final void invoke() {
        Object obj;
        PushTokenModule pushTokenModule = this.this$0;
        try {
            obj = pushTokenModule.getAppContext().getLegacyModuleRegistry().getModule(EventEmitter.class);
        } catch (Exception unused) {
            obj = null;
        }
        EventEmitter eventEmitter = (EventEmitter) obj;
        if (eventEmitter != null) {
            pushTokenModule.eventEmitter = eventEmitter;
            PushTokenManager access$getTokenManager = this.this$0.getTokenManager();
            if (access$getTokenManager != null) {
                access$getTokenManager.addListener(this.this$0);
                return;
            }
            return;
        }
        throw new ModuleNotFoundException(Reflection.getOrCreateKotlinClass(EventEmitter.class));
    }
}
