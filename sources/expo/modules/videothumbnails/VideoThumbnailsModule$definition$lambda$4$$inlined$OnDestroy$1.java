package expo.modules.videothumbnails;

import expo.modules.core.errors.ModuleDestroyedException;
import io.sentry.android.core.SentryLogcatAdapter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScopeKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "invoke", "expo/modules/kotlin/modules/ModuleDefinitionBuilder$OnDestroy$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ModuleDefinitionBuilder.kt */
public final class VideoThumbnailsModule$definition$lambda$4$$inlined$OnDestroy$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ VideoThumbnailsModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VideoThumbnailsModule$definition$lambda$4$$inlined$OnDestroy$1(VideoThumbnailsModule videoThumbnailsModule) {
        super(0);
        this.this$0 = videoThumbnailsModule;
    }

    public final void invoke() {
        try {
            CoroutineScopeKt.cancel(this.this$0.moduleCoroutineScope, new ModuleDestroyedException((String) null, 1, (DefaultConstructorMarker) null));
        } catch (IllegalStateException unused) {
            SentryLogcatAdapter.e("ExpoVideoThumbnails", "The scope does not have a job in it");
        }
    }
}
