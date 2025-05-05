package expo.modules.videothumbnails;

import android.net.Uri;
import android.webkit.URLUtil;
import expo.modules.kotlin.Promise;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineStart;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u00012\u0010\u0010\u0006\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0007H\n¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$21"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class VideoThumbnailsModule$definition$lambda$4$$inlined$AsyncFunction$7 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ VideoThumbnailsModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VideoThumbnailsModule$definition$lambda$4$$inlined$AsyncFunction$7(VideoThumbnailsModule videoThumbnailsModule) {
        super(1);
        this.this$0 = videoThumbnailsModule;
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        String str = objArr[0];
        if (str != null) {
            String str2 = str;
            VideoThumbnailOptions videoThumbnailOptions = objArr[1];
            if (videoThumbnailOptions != null) {
                VideoThumbnailOptions videoThumbnailOptions2 = videoThumbnailOptions;
                Promise promise = objArr[2];
                if (promise != null) {
                    Promise promise2 = promise;
                    if (URLUtil.isFileUrl(str2)) {
                        VideoThumbnailsModule videoThumbnailsModule = this.this$0;
                        String decode = Uri.decode(str2);
                        Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
                        if (!videoThumbnailsModule.isAllowedToRead(StringsKt.replace$default(decode, "file://", "", false, 4, (Object) null))) {
                            throw new ThumbnailFileException();
                        }
                    }
                    return BuildersKt__Builders_commonKt.launch$default(this.this$0.moduleCoroutineScope, (CoroutineContext) null, (CoroutineStart) null, new VideoThumbnailsModule$definition$lambda$4$lambda$2$$inlined$withModuleScope$1(promise2, (Continuation) null, str2, videoThumbnailOptions2, this.this$0, promise2), 3, (Object) null);
                }
                throw new NullPointerException("null cannot be cast to non-null type expo.modules.kotlin.Promise");
            }
            throw new NullPointerException("null cannot be cast to non-null type expo.modules.videothumbnails.VideoThumbnailOptions");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }
}
