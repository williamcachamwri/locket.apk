package expo.modules.contacts;

import expo.modules.core.interfaces.services.UIManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "invoke", "expo/modules/kotlin/modules/ModuleDefinitionBuilder$OnDestroy$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ModuleDefinitionBuilder.kt */
public final class ContactsModule$definition$lambda$13$$inlined$OnDestroy$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ ContactsModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContactsModule$definition$lambda$13$$inlined$OnDestroy$1(ContactsModule contactsModule) {
        super(0);
        this.this$0 = contactsModule;
    }

    public final void invoke() {
        Object obj;
        try {
            obj = this.this$0.getAppContext().getLegacyModuleRegistry().getModule(UIManager.class);
        } catch (Exception unused) {
            obj = null;
        }
        UIManager uIManager = (UIManager) obj;
        if (uIManager != null) {
            uIManager.unregisterActivityEventListener(this.this$0.mActivityEventListener);
        }
    }
}
