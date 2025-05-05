package expo.modules.imagepicker.exporters;

import android.content.ContentResolver;
import android.net.Uri;
import java.io.File;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.imagepicker.exporters.RawImageExporter", f = "RawImageExporter.kt", i = {0}, l = {16}, m = "exportAsync", n = {"output"}, s = {"L$0"})
/* compiled from: RawImageExporter.kt */
final class RawImageExporter$exportAsync$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RawImageExporter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RawImageExporter$exportAsync$1(RawImageExporter rawImageExporter, Continuation<? super RawImageExporter$exportAsync$1> continuation) {
        super(continuation);
        this.this$0 = rawImageExporter;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.exportAsync((Uri) null, (File) null, (ContentResolver) null, this);
    }
}
