package expo.modules.clipboard;

import android.content.Context;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.clipboard.ClipboardImageKt", f = "ClipboardImage.kt", i = {0, 1, 1, 1}, l = {58, 66}, m = "imageFromContentUri", n = {"options", "bitmap", "format", "outputStream"}, s = {"L$0", "L$0", "L$1", "L$2"})
/* compiled from: ClipboardImage.kt */
final class ClipboardImageKt$imageFromContentUri$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    ClipboardImageKt$imageFromContentUri$1(Continuation<? super ClipboardImageKt$imageFromContentUri$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ClipboardImageKt.imageFromContentUri((Context) null, (Uri) null, (GetImageOptions) null, this);
    }
}
