package expo.modules.videothumbnails;

import android.net.Uri;
import android.webkit.URLUtil;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.kotlin.Promise;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u00012\u0010\u0010\u0006\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010\t\u001a\u00020\nH\n¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "args", "", "", "promise", "Lexpo/modules/kotlin/Promise;", "invoke", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$17"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class VideoThumbnailsModule$definition$lambda$4$$inlined$AsyncFunction$3 extends Lambda implements Function2<Object[], Promise, Unit> {
    final /* synthetic */ VideoThumbnailsModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VideoThumbnailsModule$definition$lambda$4$$inlined$AsyncFunction$3(VideoThumbnailsModule videoThumbnailsModule) {
        super(2);
        this.this$0 = videoThumbnailsModule;
    }

    public final void invoke(Object[] objArr, Promise promise) {
        Intrinsics.checkNotNullParameter(objArr, "args");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        String str = objArr[0];
        if (str != null) {
            String str2 = str;
            VideoThumbnailOptions videoThumbnailOptions = objArr[1];
            if (videoThumbnailOptions != null) {
                VideoThumbnailOptions videoThumbnailOptions2 = videoThumbnailOptions;
                if (URLUtil.isFileUrl(str2)) {
                    VideoThumbnailsModule videoThumbnailsModule = this.this$0;
                    String decode = Uri.decode(str2);
                    Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
                    if (!videoThumbnailsModule.isAllowedToRead(StringsKt.replace$default(decode, "file://", "", false, 4, (Object) null))) {
                        throw new ThumbnailFileException();
                    }
                }
                CoroutineScope access$getModuleCoroutineScope$p = this.this$0.moduleCoroutineScope;
                Job unused = BuildersKt__Builders_commonKt.launch$default(access$getModuleCoroutineScope$p, (CoroutineContext) null, (CoroutineStart) null, new VideoThumbnailsModule$definition$lambda$4$lambda$2$$inlined$withModuleScope$1(promise, (Continuation) null, str2, videoThumbnailOptions2, this.this$0, promise), 3, (Object) null);
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type expo.modules.videothumbnails.VideoThumbnailOptions");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Object[]) obj, (Promise) obj2);
        return Unit.INSTANCE;
    }
}
