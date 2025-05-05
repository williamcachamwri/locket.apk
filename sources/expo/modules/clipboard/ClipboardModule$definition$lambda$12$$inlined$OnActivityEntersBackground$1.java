package expo.modules.clipboard;

import expo.modules.clipboard.ClipboardModule;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "invoke", "expo/modules/kotlin/modules/ModuleDefinitionBuilder$OnActivityEntersBackground$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ModuleDefinitionBuilder.kt */
public final class ClipboardModule$definition$lambda$12$$inlined$OnActivityEntersBackground$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ ClipboardModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ClipboardModule$definition$lambda$12$$inlined$OnActivityEntersBackground$1(ClipboardModule clipboardModule) {
        super(0);
        this.this$0 = clipboardModule;
    }

    public final void invoke() {
        ClipboardModule.ClipboardEventEmitter access$getClipboardEventEmitter$p = this.this$0.clipboardEventEmitter;
        if (access$getClipboardEventEmitter$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("clipboardEventEmitter");
            access$getClipboardEventEmitter$p = null;
        }
        access$getClipboardEventEmitter$p.pauseListening();
    }
}
