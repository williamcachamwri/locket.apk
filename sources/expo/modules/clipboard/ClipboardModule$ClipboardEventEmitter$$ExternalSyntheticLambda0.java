package expo.modules.clipboard;

import android.content.ClipboardManager;
import expo.modules.clipboard.ClipboardModule;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ClipboardModule$ClipboardEventEmitter$$ExternalSyntheticLambda0 implements ClipboardManager.OnPrimaryClipChangedListener {
    public final /* synthetic */ ClipboardModule f$0;
    public final /* synthetic */ ClipboardModule.ClipboardEventEmitter f$1;

    public /* synthetic */ ClipboardModule$ClipboardEventEmitter$$ExternalSyntheticLambda0(ClipboardModule clipboardModule, ClipboardModule.ClipboardEventEmitter clipboardEventEmitter) {
        this.f$0 = clipboardModule;
        this.f$1 = clipboardEventEmitter;
    }

    public final void onPrimaryClipChanged() {
        ClipboardModule.ClipboardEventEmitter.listener$lambda$7(this.f$0, this.f$1);
    }
}
