package coil.decode;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/io/File;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImageSource.kt */
final class ImageSources$ImageSource$4 extends Lambda implements Function0<File> {
    final /* synthetic */ File $cacheDirectory;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ImageSources$ImageSource$4(File file) {
        super(0);
        this.$cacheDirectory = file;
    }

    public final File invoke() {
        return this.$cacheDirectory;
    }
}
