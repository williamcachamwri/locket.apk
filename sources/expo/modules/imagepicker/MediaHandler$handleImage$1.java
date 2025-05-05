package expo.modules.imagepicker;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.imagepicker.MediaHandler", f = "MediaHandler.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2}, l = {53, 55, 58}, m = "handleImage", n = {"this", "sourceUri", "options", "mimeType", "outputFile", "this", "sourceUri", "options", "mimeType", "outputFile", "exportedImage", "this", "sourceUri", "mimeType", "outputFile", "exportedImage", "base64"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
/* compiled from: MediaHandler.kt */
final class MediaHandler$handleImage$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MediaHandler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaHandler$handleImage$1(MediaHandler mediaHandler, Continuation<? super MediaHandler$handleImage$1> continuation) {
        super(continuation);
        this.this$0 = mediaHandler;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.handleImage((Uri) null, (ImagePickerOptions) null, this);
    }
}
