package expo.modules.clipboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.Uri;
import expo.modules.imagepicker.MediaTypes;
import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001*\u00020\u00042\u0010\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0006H@¨\u0006\b"}, d2 = {"<anonymous>", "", "R", "P0", "Lkotlinx/coroutines/CoroutineScope;", "it", "", "expo/modules/kotlin/functions/AsyncFunctionBuilder$SuspendBody$4", "expo/modules/kotlin/functions/AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$2"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.clipboard.ClipboardModule$definition$lambda$12$$inlined$Coroutine$2", f = "ClipboardModule.kt", i = {}, l = {184}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AsyncFunctionBuilder.kt */
public final class ClipboardModule$definition$lambda$12$$inlined$Coroutine$2 extends SuspendLambda implements Function3<CoroutineScope, Object[], Continuation<? super Object>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ClipboardModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ClipboardModule$definition$lambda$12$$inlined$Coroutine$2(Continuation continuation, ClipboardModule clipboardModule) {
        super(3, continuation);
        this.this$0 = clipboardModule;
    }

    public final Object invoke(CoroutineScope coroutineScope, Object[] objArr, Continuation<Object> continuation) {
        ClipboardModule$definition$lambda$12$$inlined$Coroutine$2 clipboardModule$definition$lambda$12$$inlined$Coroutine$2 = new ClipboardModule$definition$lambda$12$$inlined$Coroutine$2(continuation, this.this$0);
        clipboardModule$definition$lambda$12$$inlined$Coroutine$2.L$0 = objArr;
        return clipboardModule$definition$lambda$12$$inlined$Coroutine$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        ClipData.Item access$getFirstItem;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Object obj2 = ((Object[]) this.L$0)[0];
            if (obj2 != null) {
                GetImageOptions getImageOptions = (GetImageOptions) obj2;
                Continuation continuation = this;
                ClipboardManager access$getClipboardManager = this.this$0.getClipboardManager();
                if (!this.this$0.clipboardHasItemWithType(MediaTypes.ImageAllMimeType)) {
                    access$getClipboardManager = null;
                }
                Uri uri = (access$getClipboardManager == null || (access$getFirstItem = this.this$0.getFirstItem(access$getClipboardManager)) == null) ? null : access$getFirstItem.getUri();
                if (uri == null) {
                    return null;
                }
                Context access$getContext = this.this$0.getContext();
                this.label = 1;
                obj = ClipboardImageKt.imageFromContentUri(access$getContext, uri, getImageOptions, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type expo.modules.clipboard.GetImageOptions");
            }
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th) {
                th = th;
                th.printStackTrace();
                if (!(th instanceof CodedException)) {
                    th = th instanceof SecurityException ? new NoPermissionException((SecurityException) th) : new PasteFailureException(th, "image");
                }
                throw th;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return ((ImageResult) obj).toBundle();
    }
}
