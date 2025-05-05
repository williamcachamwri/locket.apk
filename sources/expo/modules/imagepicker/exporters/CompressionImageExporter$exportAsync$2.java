package expo.modules.imagepicker.exporters;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;
import java.io.File;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H@¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"expo/modules/imagepicker/exporters/CompressionImageExporter$exportAsync$2", "Lexpo/modules/imagepicker/exporters/ImageExportResult;", "data", "Ljava/io/ByteArrayOutputStream;", "contentResolver", "Landroid/content/ContentResolver;", "(Landroid/content/ContentResolver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-image-picker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CompressionImageExporter.kt */
public final class CompressionImageExporter$exportAsync$2 extends ImageExportResult {
    final /* synthetic */ Bitmap $bitmap;
    final /* synthetic */ CompressionImageExporter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CompressionImageExporter$exportAsync$2(File file, Bitmap bitmap, CompressionImageExporter compressionImageExporter, int i, int i2) {
        super(i, i2, file);
        this.$bitmap = bitmap;
        this.this$0 = compressionImageExporter;
    }

    public Object data(ContentResolver contentResolver, Continuation<? super ByteArrayOutputStream> continuation) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.$bitmap.compress(Bitmap.CompressFormat.JPEG, this.this$0.compressQuality, byteArrayOutputStream);
        return byteArrayOutputStream;
    }
}
