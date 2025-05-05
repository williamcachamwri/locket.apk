package expo.modules.devlauncher.launcher.manifest;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.devlauncher.launcher.manifest.DevLauncherManifestParser", f = "DevLauncherManifestParser.kt", i = {0, 0}, l = {55}, m = "downloadManifest", n = {"$this$await$iv", "okHttpClient$iv"}, s = {"L$0", "L$1"})
/* compiled from: DevLauncherManifestParser.kt */
final class DevLauncherManifestParser$downloadManifest$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DevLauncherManifestParser this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DevLauncherManifestParser$downloadManifest$1(DevLauncherManifestParser devLauncherManifestParser, Continuation<? super DevLauncherManifestParser$downloadManifest$1> continuation) {
        super(continuation);
        this.this$0 = devLauncherManifestParser;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.downloadManifest(this);
    }
}
