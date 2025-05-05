package coil.util;

import coil.size.Dimension;
import coil.size.Size;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0006H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcoil/util/LimitedFileDescriptorHardwareBitmapService;", "Lcoil/util/HardwareBitmapService;", "logger", "Lcoil/util/Logger;", "(Lcoil/util/Logger;)V", "allowHardwareMainThread", "", "size", "Lcoil/size/Size;", "allowHardwareWorkerThread", "Companion", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: HardwareBitmaps.kt */
final class LimitedFileDescriptorHardwareBitmapService implements HardwareBitmapService {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int MIN_SIZE_DIMENSION = 100;
    private final Logger logger;

    public LimitedFileDescriptorHardwareBitmapService(Logger logger2) {
        this.logger = logger2;
    }

    public boolean allowHardwareMainThread(Size size) {
        Dimension width = size.getWidth();
        int i = Integer.MAX_VALUE;
        if ((width instanceof Dimension.Pixels ? ((Dimension.Pixels) width).px : Integer.MAX_VALUE) > 100) {
            Dimension height = size.getHeight();
            if (height instanceof Dimension.Pixels) {
                i = ((Dimension.Pixels) height).px;
            }
            if (i > 100) {
                return true;
            }
        }
        return false;
    }

    public boolean allowHardwareWorkerThread() {
        return FileDescriptorCounter.INSTANCE.hasAvailableFileDescriptors(this.logger);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcoil/util/LimitedFileDescriptorHardwareBitmapService$Companion;", "", "()V", "MIN_SIZE_DIMENSION", "", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: HardwareBitmaps.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
