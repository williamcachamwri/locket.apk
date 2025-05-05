package expo.modules.imagepicker.exporters;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.imagepicker.exporters.CompressionImageExporter", f = "CompressionImageExporter.kt", i = {}, l = {52}, m = "readBitmap", n = {}, s = {})
/* compiled from: CompressionImageExporter.kt */
final class CompressionImageExporter$readBitmap$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CompressionImageExporter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CompressionImageExporter$readBitmap$1(CompressionImageExporter compressionImageExporter, Continuation<? super CompressionImageExporter$readBitmap$1> continuation) {
        super(continuation);
        this.this$0 = compressionImageExporter;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.readBitmap((Uri) null, this);
    }
}
