package expo.modules.imagepicker.exporters;

import android.graphics.Bitmap;
import android.net.Uri;
import androidx.core.net.UriKt;
import com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec;
import expo.modules.imagepicker.FailedToReadFileException;
import expo.modules.imagepicker.MissingModuleException;
import expo.modules.interfaces.imageloader.ImageLoaderInterface;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: CompressionImageExporter.kt */
final class CompressionImageExporter$readBitmap$2 extends Lambda implements Function0<Bitmap> {
    final /* synthetic */ Uri $source;
    final /* synthetic */ CompressionImageExporter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CompressionImageExporter$readBitmap$2(CompressionImageExporter compressionImageExporter, Uri uri) {
        super(0);
        this.this$0 = compressionImageExporter;
        this.$source = uri;
    }

    public final Bitmap invoke() {
        ImageLoaderInterface imageLoader = this.this$0.appContextProvider.getAppContext().getImageLoader();
        Future<Bitmap> loadImageForManipulationFromURL = imageLoader != null ? imageLoader.loadImageForManipulationFromURL(this.$source.toString()) : null;
        if (loadImageForManipulationFromURL != null) {
            try {
                return loadImageForManipulationFromURL.get();
            } catch (ExecutionException e) {
                throw new FailedToReadFileException(UriKt.toFile(this.$source), e);
            }
        } else {
            throw new MissingModuleException(NativeImageLoaderAndroidSpec.NAME);
        }
    }
}
