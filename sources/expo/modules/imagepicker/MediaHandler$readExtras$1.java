package expo.modules.imagepicker;

import android.net.Uri;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.imagepicker.MediaHandler", f = "MediaHandler.kt", i = {0, 0, 0, 1, 1, 1}, l = {27, 28}, m = "readExtras$expo_image_picker_release", n = {"this", "options", "destination$iv$iv", "this", "options", "destination$iv$iv"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
/* compiled from: MediaHandler.kt */
final class MediaHandler$readExtras$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MediaHandler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaHandler$readExtras$1(MediaHandler mediaHandler, Continuation<? super MediaHandler$readExtras$1> continuation) {
        super(continuation);
        this.this$0 = mediaHandler;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.readExtras$expo_image_picker_release((List<? extends Pair<? extends MediaType, ? extends Uri>>) null, (ImagePickerOptions) null, this);
    }
}
