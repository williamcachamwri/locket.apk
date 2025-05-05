package expo.modules.clipboard;

import io.sentry.instrumentation.file.SentryFileOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/io/FileOutputStream;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ClipboardImage.kt */
final class ClipboardImageKt$clipDataFromBase64Image$fileStream$1 extends Lambda implements Function0<FileOutputStream> {
    final /* synthetic */ File $file;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ClipboardImageKt$clipDataFromBase64Image$fileStream$1(File file) {
        super(0);
        this.$file = file;
    }

    public final FileOutputStream invoke() {
        File file = this.$file;
        return SentryFileOutputStream.Factory.create(new FileOutputStream(file, false), file, false);
    }
}
