package expo.modules.clipboard;

import android.content.Context;
import java.io.File;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.clipboard.ClipboardImageKt", f = "ClipboardImage.kt", i = {0, 0, 1, 1, 1, 2, 2}, l = {103, 107, 110}, m = "clipDataFromBase64Image", n = {"context", "bitmap", "context", "bitmap", "file", "context", "file"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1"})
/* compiled from: ClipboardImage.kt */
final class ClipboardImageKt$clipDataFromBase64Image$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    ClipboardImageKt$clipDataFromBase64Image$1(Continuation<? super ClipboardImageKt$clipDataFromBase64Image$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ClipboardImageKt.clipDataFromBase64Image((Context) null, (String) null, (File) null, this);
    }
}
