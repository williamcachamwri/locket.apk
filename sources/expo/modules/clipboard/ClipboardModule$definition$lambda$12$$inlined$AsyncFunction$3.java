package expo.modules.clipboard;

import android.content.ClipData;
import expo.modules.clipboard.ClipboardModule;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u00012\u0010\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "R", "P0", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$7"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class ClipboardModule$definition$lambda$12$$inlined$AsyncFunction$3 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ ClipboardModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ClipboardModule$definition$lambda$12$$inlined$AsyncFunction$3(ClipboardModule clipboardModule) {
        super(1);
        this.this$0 = clipboardModule;
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        GetStringOptions getStringOptions = objArr[0];
        if (getStringOptions != null) {
            ClipboardModule clipboardModule = this.this$0;
            ClipData.Item access$getFirstItem = clipboardModule.getFirstItem(clipboardModule.getClipboardManager());
            int i = ClipboardModule.WhenMappings.$EnumSwitchMapping$0[getStringOptions.getPreferredFormat().ordinal()];
            String str = null;
            if (i != 1) {
                if (i != 2) {
                    throw new NoWhenBranchMatchedException();
                } else if (access$getFirstItem != null) {
                    str = access$getFirstItem.coerceToHtmlText(this.this$0.getContext());
                }
            } else if (access$getFirstItem != null) {
                str = ClipboardModuleKt.coerceToPlainText(access$getFirstItem, this.this$0.getContext());
            }
            return str == null ? "" : str;
        }
        throw new NullPointerException("null cannot be cast to non-null type expo.modules.clipboard.GetStringOptions");
    }
}
