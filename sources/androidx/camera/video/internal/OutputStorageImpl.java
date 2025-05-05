package androidx.camera.video.internal;

import android.net.Uri;
import androidx.camera.video.FileDescriptorOutputOptions;
import androidx.camera.video.FileOutputOptions;
import androidx.camera.video.MediaStoreOutputOptions;
import androidx.camera.video.OutputOptions;
import androidx.camera.video.internal.utils.StorageUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u0000 \b2\u00020\u0001:\u0001\bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/camera/video/internal/OutputStorageImpl;", "Landroidx/camera/video/internal/OutputStorage;", "outputOptions", "Landroidx/camera/video/OutputOptions;", "(Landroidx/camera/video/OutputOptions;)V", "getAvailableBytes", "", "getOutputOptions", "Companion", "camera-video_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OutputStorageImpl.kt */
public final class OutputStorageImpl implements OutputStorage {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "OutputStorageImpl";
    private final OutputOptions outputOptions;

    public OutputStorageImpl(OutputOptions outputOptions2) {
        Intrinsics.checkNotNullParameter(outputOptions2, "outputOptions");
        this.outputOptions = outputOptions2;
    }

    public OutputOptions getOutputOptions() {
        return this.outputOptions;
    }

    public long getAvailableBytes() {
        OutputOptions outputOptions2 = this.outputOptions;
        if (outputOptions2 instanceof FileOutputOptions) {
            String path = ((FileOutputOptions) outputOptions2).getFile().getPath();
            Intrinsics.checkNotNullExpressionValue(path, "outputOptions.file.path");
            return StorageUtil.getAvailableBytes(path);
        } else if (outputOptions2 instanceof MediaStoreOutputOptions) {
            Uri collectionUri = ((MediaStoreOutputOptions) outputOptions2).getCollectionUri();
            Intrinsics.checkNotNullExpressionValue(collectionUri, "outputOptions.collectionUri");
            return StorageUtil.getAvailableBytesForMediaStoreUri(collectionUri);
        } else if (outputOptions2 instanceof FileDescriptorOutputOptions) {
            return Long.MAX_VALUE;
        } else {
            throw new AssertionError("Unknown OutputOptions: " + this.outputOptions);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/camera/video/internal/OutputStorageImpl$Companion;", "", "()V", "TAG", "", "camera-video_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: OutputStorageImpl.kt */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
