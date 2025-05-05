package expo.modules.imagepicker.exporters;

import android.content.ContentResolver;
import android.os.Bundle;
import java.io.ByteArrayOutputStream;
import java.io.File;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.InterruptibleKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH@¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u000eH@¢\u0006\u0002\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0012"}, d2 = {"Lexpo/modules/imagepicker/exporters/ImageExportResult;", "", "width", "", "height", "imageFile", "Ljava/io/File;", "(IILjava/io/File;)V", "getHeight", "()I", "getWidth", "data", "Ljava/io/ByteArrayOutputStream;", "contentResolver", "Landroid/content/ContentResolver;", "(Landroid/content/ContentResolver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "exif", "Landroid/os/Bundle;", "expo-image-picker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImageExporter.kt */
public class ImageExportResult {
    private final int height;
    /* access modifiers changed from: private */
    public final File imageFile;
    private final int width;

    public Object data(ContentResolver contentResolver, Continuation<? super ByteArrayOutputStream> continuation) {
        return InterruptibleKt.runInterruptible$default((CoroutineContext) null, new ImageExportResult$data$2(contentResolver, this), continuation, 1, (Object) null);
    }

    public Object exif(ContentResolver contentResolver, Continuation<? super Bundle> continuation) {
        return InterruptibleKt.runInterruptible$default((CoroutineContext) null, new ImageExportResult$exif$2(contentResolver, this), continuation, 1, (Object) null);
    }

    public ImageExportResult(int i, int i2, File file) {
        Intrinsics.checkNotNullParameter(file, "imageFile");
        this.width = i;
        this.height = i2;
        this.imageFile = file;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }
}
