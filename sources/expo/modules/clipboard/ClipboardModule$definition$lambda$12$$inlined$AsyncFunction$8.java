package expo.modules.clipboard;

import android.content.ClipData;
import expo.modules.clipboard.ClipboardModule;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u00012\u0010\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0006H\n¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$13"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class ClipboardModule$definition$lambda$12$$inlined$AsyncFunction$8 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ ClipboardModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ClipboardModule$definition$lambda$12$$inlined$AsyncFunction$8(ClipboardModule clipboardModule) {
        super(1);
        this.this$0 = clipboardModule;
    }

    public final Object invoke(Object[] objArr) {
        ClipData clipData;
        Intrinsics.checkNotNullParameter(objArr, "it");
        String str = objArr[0];
        if (str != null) {
            String str2 = str;
            SetStringOptions setStringOptions = objArr[1];
            if (setStringOptions != null) {
                int i = ClipboardModule.WhenMappings.$EnumSwitchMapping$0[setStringOptions.getInputFormat().ordinal()];
                if (i == 1) {
                    clipData = ClipData.newPlainText((CharSequence) null, str2);
                } else if (i == 2) {
                    clipData = ClipData.newHtmlText((CharSequence) null, ClipboardModuleKt.plainTextFromHtml(str2), str2);
                } else {
                    throw new NoWhenBranchMatchedException();
                }
                this.this$0.getClipboardManager().setPrimaryClip(clipData);
                return true;
            }
            throw new NullPointerException("null cannot be cast to non-null type expo.modules.clipboard.SetStringOptions");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }
}
